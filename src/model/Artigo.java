package model;

public class Artigo extends Obra {

	public Artigo(long codigo, String titulo, Autor autor, int anoDePublicacao) {
		super(codigo, titulo, autor, anoDePublicacao);
	}

	@Override
	public int getTempoEmprestimo() {
		return 2;
	}

}
