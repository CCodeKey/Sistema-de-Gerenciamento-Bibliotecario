package model;

public abstract class Obra {
	private long codigo;
	private String titulo;
	private Autor autor;
	private int anoDePublicacao;
	private String status;

	public abstract int getTempoEmprestimo();

	public Obra() {
	}

	public Obra(long codigo, String titulo, Autor autor, int anoDePublicacao) {
		this.codigo = codigo;
		this.titulo = titulo;
		this.autor = autor;
		this.anoDePublicacao = anoDePublicacao;
		this.status = "disponivel";
	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public int getAnoDePublicacao() {
		return anoDePublicacao;
	}

	public void setAnoDePublicacao(int anoDePublicacao) {
		this.anoDePublicacao = anoDePublicacao;
	}

	public String isStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Obra [codigo=" + codigo + ", titulo=" + titulo + ", autor=" + autor + ", anoDePublicacao="
				+ anoDePublicacao + ", status=" + status + "]";
	}

}
