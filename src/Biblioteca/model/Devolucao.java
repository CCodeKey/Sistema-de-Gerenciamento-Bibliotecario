package Biblioteca.model;

import java.time.LocalDate;

public class Devolucao {
	private Emprestimo emprestimo;
	private LocalDate dataDevolucao;
	private boolean atrasado;
	private Multa multa;

	public Devolucao(Emprestimo emprestimo, LocalDate dataDevolucao, boolean atrasado, Multa multa) {
		this.emprestimo = emprestimo;
		this.dataDevolucao = dataDevolucao;
		this.atrasado = atrasado;
		this.multa = multa;
	}

	public Emprestimo getEmprestimo() {
		return emprestimo;
	}

	public void setEmprestimo(Emprestimo emprestimo) {
		this.emprestimo = emprestimo;
	}

	public LocalDate getDataDevolucaoReal() {
		return dataDevolucao;
	}

	public boolean isAtrasado() {
		return atrasado;
	}

	public void setAtrasado(boolean atrasado) {
		this.atrasado = atrasado;
	}

	public Multa getMulta() {
		return multa;
	}

}
