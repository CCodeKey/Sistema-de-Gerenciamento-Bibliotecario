package Excecoes;

public class ObraNaoExisteException extends Exception {
	private static final long serialVersionUID = 1L;

	public ObraNaoExisteException() {
		super("Este/a obra não existe no sistema!");
	}

	public ObraNaoExisteException(String obra) {
		super("Este/a " + obra + " não existe no sistema!");
	}

}
