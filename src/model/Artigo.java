package model;

import java.util.Date;

public class Artigo extends Obra {

	public Artigo(long codigo, String titulo, Autor autor, Date anoDePublicacao, boolean status) {
		super(codigo, titulo, autor, anoDePublicacao, status);
	}

	@Override
	public void getTempoEmprestimo() {

	}

}
