package Biblioteca.model;

public class UsuarioLogin extends Usuario {
	private long id;
	private String cpf;
	private String password;

	public UsuarioLogin(long id, String nome, String matricula, String tipoUsuario, String telefone, String email,
			String cpf, String password) {
		super(nome, matricula, tipoUsuario, telefone, email);
		this.id = id;
		this.cpf = cpf;
		this.password = password;
	}

	public long getId() {
		return id;
	}

	public String getCpf() {
		return cpf;
	}

	public String getPassword() {
		return password;
	}

}
