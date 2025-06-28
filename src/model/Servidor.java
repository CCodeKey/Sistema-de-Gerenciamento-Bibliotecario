package model;

public class Servidor extends Usuario {

	public Servidor(String nome, String matricula, String tipoUsuario, String telefone, String email) {
		super(nome, matricula, tipoUsuario, telefone, email);
	}

	@Override
	public String toString() {
		return "Servidor [getNome()=" + getNome() + ", getMatricula()=" + getMatricula() + ", getTipoUsuario()="
				+ getTipoUsuario() + ", getTelefone()=" + getTelefone() + ", getEmail()=" + getEmail() + ", toString()="
				+ super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}

}
