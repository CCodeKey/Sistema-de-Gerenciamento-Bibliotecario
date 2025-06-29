package model;

public class Emprestimo {
	private Obra obra;
	private Usuario usuario;

	public Emprestimo(Obra obra, Usuario usuario) {
		this.obra = obra;
		this.usuario = usuario;
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

	@Override
	public String toString() {
		return "EmprestimoDeObra [obra=" + obra + ", usuario=" + usuario + "]";
	}

}
