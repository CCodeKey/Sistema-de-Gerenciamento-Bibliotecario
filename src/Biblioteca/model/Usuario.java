package Biblioteca.model;

import java.util.Objects;

public class Usuario {
	private String nome;
	private String matricula;
	private String tipoUsuario;
	private String telefone;
	private String email;

	public Usuario(String nome, String matricula, String tipoUsuario, String telefone, String email) {
		this.nome = nome;
		this.matricula = matricula;
		this.tipoUsuario = tipoUsuario;
		this.telefone = telefone;
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMatricula() {
		return matricula;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Usuario [nome=" + nome + ", matricula=" + matricula + ", tipoUsuario=" + tipoUsuario + ", telefone="
				+ telefone + ", email=" + email + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, matricula, nome, telefone, tipoUsuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(email, other.email) && Objects.equals(matricula, other.matricula)
				&& Objects.equals(nome, other.nome) && Objects.equals(telefone, other.telefone)
				&& Objects.equals(tipoUsuario, other.tipoUsuario);
	}

}
