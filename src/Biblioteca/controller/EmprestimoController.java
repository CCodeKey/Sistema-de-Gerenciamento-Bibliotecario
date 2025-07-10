package Biblioteca.controller;

import java.io.IOException;
import java.time.LocalDate;

import Biblioteca.dao.EmprestimoDao;
import Biblioteca.model.Emprestimo;
import Biblioteca.model.Obra;
import Biblioteca.model.Usuario;
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

	public void realizarEmprestimo()
			throws UsuarioNaoExisteException, ObraNaoExisteException, IOException, ObraNaoDisponivelException {
		if (validarInformacoes() == true) {
			this.obra.setStatus("ocupado");

			ObraController controller = new ObraController();
			controller.ocuparObra(this.obra.getCodigo());

			dataDeDevolucaoDaObra();

			Emprestimo emprestimo = new Emprestimo(obra, usuario, dataDoEmprestimo.toString(),
					dataDaDevolucao.toString(), false);

			dao.salvarEmprestimo(emprestimo);
		}
	}

}
