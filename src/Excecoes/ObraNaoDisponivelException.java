package Excecoes;

public class ObraNaoDisponivelException extends Exception {

	private static final long serialVersionUID = 1L;

	public ObraNaoDisponivelException(String usuario) {
		super("Usuário: " + usuario + ", tentou ocupar uma obra não disponível.");
	}

}
