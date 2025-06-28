package model;

public class Livro extends Obra {

	public Livro() {
		super();
	}

	public Livro(long codigo, String titulo, Autor autor, int anoDePublicacao) {
		super(codigo, titulo, autor, anoDePublicacao);
	}

	@Override
	public int getTempoEmprestimo() {
		return 7;
	}

}
