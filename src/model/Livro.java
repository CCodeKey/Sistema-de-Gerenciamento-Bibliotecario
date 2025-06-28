package model;

import java.util.Date;

public class Livro extends Obra {

	private int capitulos;
	private int paginas;

	public Livro() {
		super();
	}

	public Livro(long codigo, String titulo, Autor autor, Date anoDePublicacao, boolean status) {
		super(codigo, titulo, autor, anoDePublicacao, status);
	}

	@Override
	public void getTempoEmprestimo() {
		// TODO: implementar função de tempo de emprestimo
	}

	public int getCapitulos() {
		return capitulos;
	}

	public void setCapitulos(int capitulos) {
		this.capitulos = capitulos;
	}

	public int getPaginas() {
		return paginas;
	}

	public void setPaginas(int paginas) {
		this.paginas = paginas;
	}
}
