package model;

public class Aluno extends Usuario {

	public Aluno(String nome, String matricula, String tipoUsuario, String telefone, String email) {
		super(nome, matricula, tipoUsuario, telefone, email);
	}

	@Override
	public String toString() {
		return "Aluno [getNome()=" + getNome() + ", getMatricula()=" + getMatricula() + ", getTipoUsuario()="
				+ getTipoUsuario() + ", getTelefone()=" + getTelefone() + ", getEmail()=" + getEmail() + ", toString()="
				+ super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}

}
