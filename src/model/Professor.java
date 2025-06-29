package model;

public class Professor extends Usuario {

	public Professor(String nome, String matricula, String tipoUsuario, String telefone, String email) {
		super(nome, matricula, tipoUsuario, telefone, email);
	}

	@Override
	public String toString() {
		return "Professor [nome=" + getNome() + ", matricula=" + getMatricula() + ", telefone=" + getTelefone()
				+ ", email=" + getEmail() + "]";
	}

}
