package Excecoes;

public class EmprestimoNaoEncontradoException extends Exception {

	private static final long serialVersionUID = 1L;

	public EmprestimoNaoEncontradoException() {
		super("O empréstimo não foi encontrado!");
	}

}
