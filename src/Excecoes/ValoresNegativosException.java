package Excecoes;

public class ValoresNegativosException extends Exception {
	private static final long serialVersionUID = 1L;

	public ValoresNegativosException() {
		super("Valores negativos não são permitidos!");
	}
}
