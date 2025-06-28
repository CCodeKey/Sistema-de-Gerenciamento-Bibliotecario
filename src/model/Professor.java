package model;

public class Professor extends Usuario {

	public Professor(String nome, String matricula, String tipoUsuario, String telefone, String email) {
		super(nome, matricula, tipoUsuario, telefone, email);
	}

	@Override
	public String toString() {
		return "Professor [getNome()=" + getNome() + ", getMatricula()=" + getMatricula() + ", getTipoUsuario()="
				+ getTipoUsuario() + ", getTelefone()=" + getTelefone() + ", getEmail()=" + getEmail() + ", toString()="
				+ super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}

}
