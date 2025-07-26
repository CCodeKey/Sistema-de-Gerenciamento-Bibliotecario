package Biblioteca.model;

public class Emprestimo {
	private int id;
	private Obra obra;
	private Usuario usuario;
	private String dataDoEmprestimo;
	private String dataDaDevolucao;
	private boolean devolvido;

	public Emprestimo(int id, Obra obra, Usuario usuario, String dataDoEmprestimo, String dataDaDevolucao,
			boolean devolvido) {
		this.id = id;
		this.obra = obra;
		this.usuario = usuario;
		this.dataDoEmprestimo = dataDoEmprestimo;
		this.dataDaDevolucao = dataDaDevolucao;
		this.devolvido = devolvido;
	}

	public int getId() {
		return id;
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

	public String getDataDoEmprestimo() {
		return dataDoEmprestimo;
	}

	public String getDataDaDevolucao() {
		return dataDaDevolucao;
	}

	public void setDataDaDevolucao(String dataDaDevolucao) {
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
