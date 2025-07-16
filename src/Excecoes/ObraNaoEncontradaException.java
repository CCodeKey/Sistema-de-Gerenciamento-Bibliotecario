package Excecoes;

public class ObraNaoEncontradaException extends Exception{
	private static final long serialVersionUID = 1L;

	public ObraNaoEncontradaException() {
		super("Esta obra não foi encontrada no sistema!");
	}

}
