package Excecoes;

import java.time.LocalDate;

import Biblioteca.model.Usuario;

public class MetodoDePagamentoException extends Exception {
	private Usuario usuario;
	private LocalDate data;
	private static final long serialVersionUID = 1L;

	public MetodoDePagamentoException(Usuario usuario) {
		super();
		this.data = LocalDate.now();
		this.usuario = usuario;

	}

	@Override
	public String getMessage() {
		return "O usuário: " + usuario + "\nfez uma tentativa de pagamento com o método de pagamento inválido!\nData: "
				+ data;
	}
}
