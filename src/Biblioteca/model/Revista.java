package Biblioteca.model;

public class Revista extends Obra {

	private String issn;

	public Revista() {
		super();
	}

	public Revista(long codigo, String titulo, String autor, int anoDePublicacao, String issn) {
		super(codigo, titulo, autor, anoDePublicacao);
		this.issn = issn;
	}

	@Override
	public int getTempoEmprestimo() {
		return 3;
	}

	public String getIssn() {
		return issn;
	}

	public void setIssn(String issn) {
		this.issn = issn;
	}

	@Override
	public String toString() {
		return "Revista [codigo=" + getCodigo() + ", titulo=" + getTitulo() + ", autor=" + getAutor()
				+ ", ano_de_publicacao=" + getAnoDePublicacao() + ", status=" + getStatus() + "]";
	}
}
