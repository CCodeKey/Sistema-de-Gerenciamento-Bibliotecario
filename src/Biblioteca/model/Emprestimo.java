package Biblioteca.model;

import java.util.Date;

public class Emprestimo {
	private Obra obra;
	private Usuario usuario;
	private Date dataDoEmprestimo;
	private Date dataDaDevolucao;
	private boolean devolvido;

	public Emprestimo(Obra obra, Usuario usuario, Date dataDoEmprestimo, Date dataDaDevolucao, boolean devolvido) {
		this.obra = obra;
		this.usuario = usuario;
		this.dataDoEmprestimo = dataDoEmprestimo;
		this.dataDaDevolucao = dataDaDevolucao;
		this.devolvido = devolvido;
	}

	public Obra getObra() {
		return obra;
	}

	public void setObra(Obra obra) {
		this.obra = obra;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getDataDoEmprestimo() {
		return dataDoEmprestimo;
	}

	public Date getDataDaDevolucao() {
		return dataDaDevolucao;
	}

	public void setDataDaDevolucao(Date dataDaDevolucao) {
		this.dataDaDevolucao = dataDaDevolucao;
	}

	public boolean getDevolvido() {
		return devolvido;
	}

	public void setDevolvido(boolean devolvido) {
		this.devolvido = devolvido;
	}

	@Override
	public String toString() {
		return "Emprestimo [obra=" + obra + ", usuario=" + usuario + ", dataDoEmprestimo=" + dataDoEmprestimo
				+ ", dataDaDevolucao=" + dataDaDevolucao + ", devolvido=" + devolvido + "]";
	}

}
