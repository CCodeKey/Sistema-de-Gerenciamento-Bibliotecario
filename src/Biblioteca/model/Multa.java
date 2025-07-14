package Biblioteca.model;

public class Multa {
	private long id;
	private Emprestimo emprestimo;
	private double valorDaMulta;
	private String dataDeVencimento;
	private boolean multaPaga;

	public Multa(long id, Emprestimo emprestimo, double valorDaMulta, String dataDeVencimento, boolean multaPaga) {
		this.id = id;
		this.emprestimo = emprestimo;
		this.valorDaMulta = valorDaMulta;
		this.dataDeVencimento = dataDeVencimento;
		this.multaPaga = multaPaga;
	}

	public long getId() {
		return id;
	}

	public Emprestimo getDadosEmprestimo() {
		return emprestimo;
	}

	public double getValorDaMulta() {
		return valorDaMulta;
	}

	public String getDataDeVencimento() {
		return dataDeVencimento;
	}

	public boolean getMultaPaga() {
		return multaPaga;
	}

	public void setMmultaPaga(boolean estamultaPaga) {
		this.multaPaga = estamultaPaga;
	}

}
