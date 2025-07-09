package Biblioteca.model;

public class Artigo extends Obra {

	public Artigo(long codigo, String titulo, String autor, int anoDePublicacao) {
		super(codigo, titulo, autor, anoDePublicacao);
	}

	@Override
	public int getTempoEmprestimo() {
		return 2;
	}

	@Override
	public String toString() {
		return "Artigo [codigo=" + getCodigo() + ", titulo=" + getTitulo() + ", autor=" + getAutor()
				+ ", ano_de_publicacao=" + getAnoDePublicacao() + ", status=" + getStatus() + "]";
	}
}
