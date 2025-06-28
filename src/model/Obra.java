package model;

import java.util.Date;

public abstract class Obra {
	private long codigo;
	private String titulo;
	private Autor autor;
	private Date anoDePublicacao;
	private boolean status;

	public abstract void getTempoEmprestimo();

	public Obra() {
	}

	public Obra(long codigo, String titulo, Autor autor, Date anoDePublicacao, boolean status) {
		this.codigo = codigo;
		this.titulo = titulo;
		this.autor = autor;
		this.anoDePublicacao = anoDePublicacao;
		this.status = status;
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

	public Date getAnoDePublicacao() {
		return anoDePublicacao;
	}

	public void setAnoDePublicacao(Date anoDePublicacao) {
		this.anoDePublicacao = anoDePublicacao;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
}
