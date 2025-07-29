package Biblioteca.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;

import Biblioteca.dao.PagamentoDao;
import Biblioteca.model.Devolucao;
import Biblioteca.model.Pagamento;
import Biblioteca.model.Usuario;
import Excecoes.DevolucaoException;
import Excecoes.MetodoDePagamentoException;
import Excecoes.MultaException;
import Excecoes.UsuarioNaoExisteException;
import Excecoes.ValoresNegativosException;

public class PagamentoController {
	private double valorPago;
	private LocalTime horarioDoPagamento;
	private LocalDate dataDoPagamento;
	private String metodoDePagamento;
	private String status;
	private Usuario usuario;
	private PagamentoDao dao;
	private Devolucao devolucao;
	private DevolucaoController daoDevolucao;

	public PagamentoController() {
		this.dao = new PagamentoDao();
		this.daoDevolucao = new DevolucaoController();
	}

	public PagamentoController(double valorPago, String metodoDePagamento, Usuario usuario, Devolucao devolucao) {
		this.valorPago = valorPago;
		this.horarioDoPagamento = LocalTime.now();
		this.dataDoPagamento = LocalDate.now();
		this.metodoDePagamento = metodoDePagamento;
		this.usuario = usuario;
		this.devolucao = devolucao;
		this.dao = new PagamentoDao();
		this.daoDevolucao = new DevolucaoController();
		this.status = "CONFIRMADO";
	}

	private boolean validarInformações() throws UsuarioNaoExisteException, ValoresNegativosException,
			MetodoDePagamentoException, DevolucaoException {
		if (usuario == null) {
			throw new UsuarioNaoExisteException();
		}
		if (valorPago < 0) {
			throw new ValoresNegativosException();
		}

		if (metodoDePagamento.equals("pix") || metodoDePagamento.equals("cartão")
				|| metodoDePagamento.equals("dinheiro")) {

		} else {
			throw new MetodoDePagamentoException(usuario);
		}

		if (devolucao == null) {
			throw new DevolucaoException();
		}
		return true;
	}

	private long gerarIdPagamento() {
		Random ramdom = new Random();
		int uid = ramdom.nextInt(1000000000);

		if (dao.verificarId(uid) == false) {
			gerarIdPagamento();
		}

		return uid;
	}

	public void pagar() throws MetodoDePagamentoException, UsuarioNaoExisteException, ValoresNegativosException,
			IOException, DevolucaoException {
		if (validarInformações() == true) {
			Pagamento pg = new Pagamento(gerarIdPagamento(), valorPago, horarioDoPagamento.toString(),
					dataDoPagamento.toString(), metodoDePagamento, usuario, status);

			dao.salvarPagamento(pg);

			daoDevolucao.atualizarStatusPagamentoEmMulta(this.devolucao);
		}
	}

	public Devolucao verificarIdMultaNaoPaga(long idMulta) throws MultaException {
		DevolucaoController devCtrl = new DevolucaoController();
		for (Devolucao d : devCtrl.devoulocoesNaoPagas()) {
			if (d.getMulta().getId() != idMulta) {
				throw new MultaException();
			}
			this.devolucao = d;

			return devolucao;
		}
		return null;
	}

}
