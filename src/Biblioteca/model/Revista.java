package Biblioteca.model;

public class Revista extends Obra {

	public Revista() {
		super();
	}

	public Revista(long codigo, String titulo, String autor, int anoDePublicacao) {
		super(codigo, titulo, autor, anoDePublicacao);
	}

	@Override
	public int getTempoEmprestimo() {
		return 3;
	}

	@Override
	public String toString() {
		return "Revista [codigo=" + getCodigo() + ", titulo=" + getTitulo() + ", autor=" + getAutor()
				+ ", ano_de_publicacao=" + getAnoDePublicacao() + ", status=" + getStatus() + "]";
	}
}
