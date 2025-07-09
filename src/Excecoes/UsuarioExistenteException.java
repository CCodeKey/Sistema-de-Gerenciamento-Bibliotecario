package Excecoes;

public class UsuarioExistenteException extends Exception {
	private static final long serialVersionUID = 1L;

	public UsuarioExistenteException(String tipoUsuario) {
		super("Este " + tipoUsuario + " já existe no sistema!");
	}

}
