package model;

public class Aluno extends Usuario {

	public Aluno(String nome, String matricula, String tipoUsuario, String telefone, String email) {
		super(nome, matricula, tipoUsuario, telefone, email);
	}

	@Override
	public String toString() {
		return "Aluno [nome=" + getNome() + ", matricula=" + getMatricula() + ", telefone=" + getTelefone() + ", email="
				+ getEmail() + "]";
	}

}
