package interfaces;

import java.io.IOException;

public interface ManipulacaoDeArquivos {
	public void carregar();

	public void salvarDadosEmJson() throws IOException;

}
