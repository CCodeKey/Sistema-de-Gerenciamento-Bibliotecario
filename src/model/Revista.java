package model;

import java.util.Date;

public class Revista extends Obra {

	public Revista() {
		super();
	}

	public Revista(long codigo, String titulo, Autor autor, Date anoDePublicacao, boolean status) {
		super(codigo, titulo, autor, anoDePublicacao, status);
	}

	@Override
	public void getTempoEmprestimo() {

	}
}
