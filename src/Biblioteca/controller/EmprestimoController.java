package Biblioteca.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import Biblioteca.dao.EmprestimoDao;
import Biblioteca.model.Emprestimo;
import Biblioteca.model.Obra;
import Biblioteca.model.Usuario;
import Excecoes.EmprestimoNaoEncontradoException;
import Excecoes.ObraNaoDisponivelException;
import Excecoes.ObraNaoExisteException;
import Excecoes.UsuarioNaoExisteException;

public class EmprestimoController {
	private Usuario usuario;
	private Obra obra;
	private EmprestimoDao dao;
	private LocalDate dataDoEmprestimo;
	private LocalDate dataDaDevolucao;

	public EmprestimoController() {
		this.dao = new EmprestimoDao();
	}

	public EmprestimoController(Usuario usuario, Obra obra) {
		this.usuario = usuario;
		this.obra = obra;
		this.dataDoEmprestimo = LocalDate.now();
		this.dao = new EmprestimoDao();
	}

	private boolean validarInformacoes()
			throws UsuarioNaoExisteException, ObraNaoExisteException, ObraNaoDisponivelException {
		if (this.usuario == null) {
			throw new UsuarioNaoExisteException();
		}

		if (this.obra == null) {
			throw new ObraNaoExisteException();
		}

		if (this.obra.getStatus().equals("ocupado")) {
			throw new ObraNaoDisponivelException(this.usuario.getNome());
		}

		return true;
	}

	private void dataDeDevolucaoDaObra() {
		int prazo = this.obra.getTempoEmprestimo();
		LocalDate devolucao = dataDoEmprestimo.plusDays(prazo);
		this.dataDaDevolucao = devolucao;
	}

	private int idEmprestimo() {
		Random ramdom = new Random();
		int uid = ramdom.nextInt(1000000000);

		if (dao.verificarId(uid) == false) {
			idEmprestimo();
		}

		return uid;
	}

	public void realizarEmprestimo()
			throws UsuarioNaoExisteException, ObraNaoExisteException, IOException, ObraNaoDisponivelException {
		if (validarInformacoes() == true) {
			this.obra.setStatus("ocupado");

			ObraController controller = new ObraController();
			controller.ocuparObra(this.obra.getCodigo());

			dataDeDevolucaoDaObra();

			Emprestimo emprestimo = new Emprestimo(idEmprestimo(), obra, usuario, dataDoEmprestimo.toString(),
					dataDaDevolucao.toString(), false);

			dao.salvarEmprestimo(emprestimo);
		}
	}

	public Emprestimo buscarEmprestimo(int idEmprestimo) {
		return dao.buscarEmprestimo(idEmprestimo);
	}

	public void disponibilizarObraDeEmprestimo(Emprestimo emp) throws IOException {
		dao.disponibilizarObraDeEmprestimo(emp);
	}

	public List<Emprestimo> listarEmprestimos() throws EmprestimoNaoEncontradoException {
		if (dao.listarEmprestimos() == null) {
			throw new EmprestimoNaoEncontradoException();
		}
		return dao.listarEmprestimos();
	}

}
