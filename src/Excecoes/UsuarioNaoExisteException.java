package Excecoes;

public class UsuarioNaoExisteException extends Exception {
	private static final long serialVersionUID = 1L;

	public UsuarioNaoExisteException() {
		super("Este usuário não existe no sistema!");
	}
	
	public UsuarioNaoExisteException(String tipoUsuario) {
		super("Este " + tipoUsuario + " não existe no sistema!");
	}
}
