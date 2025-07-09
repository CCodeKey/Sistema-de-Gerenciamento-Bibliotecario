package Excecoes;

public class ObraExistenteException extends Exception {
	private static final long serialVersionUID = 1L;

	public ObraExistenteException(String obra) {
		super("Este/a " + obra + " já existe no sistema!");
	}
}
