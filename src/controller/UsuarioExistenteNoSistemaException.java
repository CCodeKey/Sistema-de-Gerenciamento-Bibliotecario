public class UsuarioExistenteNoSistemaException extends Exception{

	public UsuarioExistenteNoSistemaException() {
		super("Usuário já existe no sistema!");
	}
	
}
