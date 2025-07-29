package Biblioteca.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;

import Biblioteca.dao.DevolucaoDao;
import Biblioteca.model.Devolucao;
import Biblioteca.model.Emprestimo;
import Biblioteca.model.Multa;
import Biblioteca.model.Usuario;
import Excecoes.DevolucaoException;
import Excecoes.EmprestimoNaoEncontradoException;
import Excecoes.MetodoDePagamentoException;
import Excecoes.UsuarioNaoExisteException;
import Excecoes.ValoresNegativosException;

public class DevolucaoController {
	private Emprestimo emprestimo;
	private LocalDate dataDevolucaoDoEmprestimo;
	private LocalDate dataDevolucaoReal;
	private boolean atrasado;
	private double multaCalculada;
	private Multa multa;
	private EmprestimoController emprestimoController;
	private ObraController obraController;
	private DevolucaoDao dao;
	private Devolucao devolucao;
	private static final double VALOR_MULTA_POR_DIA = 8.75;
	private static final double MULTA_MAXIMA = 1000.00;

	public DevolucaoController() {
		this.dao = new DevolucaoDao();
	}

	public DevolucaoController(int idEmprestimo) throws EmprestimoNaoEncontradoException {
		this.dataDevolucaoReal = LocalDate.now();
		this.atrasado = false;
		this.multaCalculada = 0;
		this.multa = null;
		this.emprestimoController = new EmprestimoController();
		this.obraController = new ObraController();
		this.dao = new DevolucaoDao();

		if (emprestimoController.buscarEmprestimo(idEmprestimo) != null
				&& emprestimoController.buscarEmprestimo(idEmprestimo).getDevolvido() == false) {
			this.emprestimo = emprestimoController.buscarEmprestimo(idEmprestimo);
			this.dataDevolucaoDoEmprestimo = LocalDate.parse(emprestimo.getDataDaDevolucao());

		} else {
			throw new EmprestimoNaoEncontradoException();
		}
	}

	private void isAtraso() {
		if (dataDevolucaoReal.isAfter(dataDevolucaoDoEmprestimo)) {
			this.atrasado = true;
		}
	}

	private long gerarIdMulta() throws IOException {
		Random ramdom = new Random();
		int uid = ramdom.nextInt(1000000000);

		if (dao.verificarIdMulta(uid) == false) {
			gerarIdMulta();
		}

		return uid;
	}

	private void calcularMulta() throws IOException {
		long diasDeAtraso = ChronoUnit.DAYS.between(dataDevolucaoReal, dataDevolucaoDoEmprestimo);
		double mult = Math.min(diasDeAtraso * VALOR_MULTA_POR_DIA, MULTA_MAXIMA);
		this.multaCalculada = mult * (-1);
		this.multa = new Multa(gerarIdMulta(), emprestimo, multaCalculada, dataDevolucaoDoEmprestimo.toString(), false);
	}

	public void registrarDevolucao()
			throws IOException, MetodoDePagamentoException, UsuarioNaoExisteException, ValoresNegativosException {
		isAtraso();
		if (this.atrasado == true) {
			calcularMulta();
		}

		obraController.disponibilizarObra(emprestimo.getObra().getCodigo());
		emprestimoController.disponibilizarObraDeEmprestimo(emprestimo);

		this.devolucao = new Devolucao(emprestimo, dataDevolucaoReal, atrasado, multa);
		dao.registrarDevolucao(this.devolucao);
	}

	public void pagamentoDeMulta(String metodoDePagamento) throws MetodoDePagamentoException, UsuarioNaoExisteException,
			ValoresNegativosException, IOException, DevolucaoException {
		if (metodoDePagamento.equals("pix") || metodoDePagamento.equals("cartao")
				|| metodoDePagamento.equals("dinheiro")) {
			PagamentoController pgctl = new PagamentoController(multaCalculada, metodoDePagamento,
					emprestimo.getUsuario(), this.devolucao);
			pgctl.pagar();

			atualizarStatusPagamentoEmMulta(this.devolucao);

		} else {
			throw new MetodoDePagamentoException(emprestimo.getUsuario());
		}
	}

	public Multa isMulta() {
		return this.multa;
	}

	public List<Devolucao> devoulocoesNaoPagas() {
		return dao.devolucoesNaoPagas();
	}

	protected void atualizarStatusPagamentoEmMulta(Devolucao dev) throws IOException {
		dao.atualizarStatusPagamentoEmMulta(dev);
	}

	public List<Usuario> listaUsuariosComAtraso() {
		return dao.listarUsuariosComAtraso();
	}
}
