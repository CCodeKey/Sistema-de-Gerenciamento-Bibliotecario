package Biblioteca.controller;

import relatorio.RelatorioEmprestimos;
import relatorio.RelatorioObras;
import relatorio.RelatorioUsuarios;

public class RelatorioController {
	private RelatorioEmprestimos emprestimosDoMes;
	private RelatorioObras obrasEmprestadas;
	private RelatorioUsuarios usuariosComAtraso;

	public RelatorioController() {
		this.emprestimosDoMes = new RelatorioEmprestimos();
		this.obrasEmprestadas = new RelatorioObras();
		this.usuariosComAtraso = new RelatorioUsuarios();
	}

	public void relatorioDeEmprestimosDoMes() {
		emprestimosDoMes.gerarRelatorio();
	}

	public void relatorioDeObrasMaisEmprestadas() throws Exception {
		obrasEmprestadas.gerarRelatorio();
	}

	public void relatorioDeUsuariosComMaisAtrasos() throws Exception {
		usuariosComAtraso.gerarRelatorio();
	}

}
