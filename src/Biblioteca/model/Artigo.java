package Biblioteca.model;

public class Artigo extends Obra {
	private String categoria;

	public Artigo(long codigo, String titulo, String autor, int anoDePublicacao, String categoria) {
		super(codigo, titulo, autor, anoDePublicacao);
		this.categoria = categoria;
	}

	@Override
	public int getTempoEmprestimo() {
		return 2;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "Artigo [codigo=" + getCodigo() + ", titulo=" + getTitulo() + ", autor=" + getAutor()
				+ ", ano_de_publicacao=" + getAnoDePublicacao() + ", status=" + getStatus() + "]";
	}
}
