package Biblioteca.model;

public class Livro extends Obra {

	public Livro() {
		super();
	}

	public Livro(long codigo, String titulo, String autor, int anoDePublicacao) {
		super(codigo, titulo, autor, anoDePublicacao);
	}

	@Override
	public int getTempoEmprestimo() {
		return 7;
	}

	@Override
	public String toString() {
		return "Livro [codigo=" + getCodigo() + ", titulo=" + getTitulo() + ", autor=" + getAutor()
				+ ", ano_de_publicacao=" + getAnoDePublicacao() + ", status=" + getStatus() + "]";
	}

}
