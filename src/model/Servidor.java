package model;

public class Servidor extends Usuario {

	public Servidor(String nome, String matricula, String tipoUsuario, String telefone, String email) {
		super(nome, matricula, tipoUsuario, telefone, email);
	}

	@Override
	public String toString() {
		return "Servidor [nome=" + getNome() + ", matricula=" + getMatricula() + ", telefone=" + getTelefone()
				+ ", email=" + getEmail() + "]";
	}

}
