package Biblioteca.model;

public class Livro extends Obra {
	private String isbn;

	public Livro(long codigo, String titulo, String autor, int anoDePublicacao, String isbn) {
		super(codigo, titulo, autor, anoDePublicacao);
		this.isbn = isbn;
	}

	@Override
	public int getTempoEmprestimo() {
		return 7;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	@Override
	public String toString() {
		return "Livro [codigo=" + getCodigo() + ", titulo=" + getTitulo() + ", autor=" + getAutor()
				+ ", ano_de_publicacao=" + getAnoDePublicacao() + ", status=" + getStatus() + "]";
	}

}
