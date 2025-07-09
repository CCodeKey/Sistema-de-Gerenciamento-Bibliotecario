package Biblioteca.model;

public abstract class Obra {
	private long codigo;
	private String titulo;
	private String autor;
	private int anoDePublicacao;
	private String status;

	public abstract int getTempoEmprestimo();

	public Obra() {
	}

	public Obra(long codigo, String titulo, String autor, int anoDePublicacao) {
		this.codigo = codigo;
		this.titulo = titulo;
		this.autor = autor;
		this.anoDePublicacao = anoDePublicacao;
		this.status = "disponivel";
	}

	public long getCodigo() {
		return codigo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public int getAnoDePublicacao() {
		return anoDePublicacao;
	}

	public void setAnoDePublicacao(int anoDePublicacao) {
		this.anoDePublicacao = anoDePublicacao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
