package model;

public class Revista extends Obra {

	public Revista() {
		super();
	}

	public Revista(long codigo, String titulo, Autor autor, int anoDePublicacao) {
		super(codigo, titulo, autor, anoDePublicacao);
	}

	@Override
	public int getTempoEmprestimo() {
		return 3;
	}

}
