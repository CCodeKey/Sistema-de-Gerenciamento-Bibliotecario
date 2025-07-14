package Biblioteca.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;

import Biblioteca.dao.PagamentoDao;
import Biblioteca.model.Pagamento;
import Biblioteca.model.Usuario;
import Excecoes.MetodoDePagamentoException;
import Excecoes.UsuarioNaoExisteException;
import Excecoes.ValoresNegativosException;

public class PagamentoController {
	private double valorPago;
	private LocalTime horarioDoPagamento;
	private LocalDate dataDoPagamento;
	private String metodoDePagamento;
	private String status;
	private Usuario usuario;
	private PagamentoDao dao;

	public PagamentoController(double valorPago, String metodoDePagamento, Usuario usuario) {
		this.valorPago = valorPago;
		this.horarioDoPagamento = LocalTime.now();
		this.dataDoPagamento = LocalDate.now();
		this.metodoDePagamento = metodoDePagamento;
		this.usuario = usuario;
		this.dao = new PagamentoDao();
		this.status = "CONFIRMADO";
	}

	private boolean validarInformações()
			throws UsuarioNaoExisteException, ValoresNegativosException {
		if (usuario == null) {
			throw new UsuarioNaoExisteException();
		}
		if (valorPago < 0) {
			throw new ValoresNegativosException();
		}

		return true;
	}

	private long gerarIdPagamento() {
		Random ramdom = new Random();
		int uid = ramdom.nextInt(1000000000);

		if (dao.verificarId(uid) == false) {
			gerarIdPagamento();
		}

		return uid;
	}

	public void pagar()
			throws MetodoDePagamentoException, UsuarioNaoExisteException, ValoresNegativosException, IOException {
		if (validarInformações() == true) {
			Pagamento pg = new Pagamento(gerarIdPagamento(), valorPago, horarioDoPagamento.toString(),
					dataDoPagamento.toString(), metodoDePagamento, usuario, status);

			dao.salvarPagamento(pg);
		}
	}

}
