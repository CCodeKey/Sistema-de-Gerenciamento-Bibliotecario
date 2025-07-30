package Biblioteca.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Biblioteca.model.Pagamento;
import interfaces.ManipulacaoDeArquivos;

public class PagamentoDao implements ManipulacaoDeArquivos {
	private ArrayList<Pagamento> pagamentos = new ArrayList<>();
	private static final String ARQUIVO_JSON_PAGAMENTOS = "resources/json/pagamento.json";
	private Gson gson;

	public PagamentoDao() {
		this.gson = new GsonBuilder().setPrettyPrinting().create();
		carregar();
	}

	@Override
	public void carregar() {
		try {
			String json = new String(Files.readAllBytes(Paths.get(ARQUIVO_JSON_PAGAMENTOS)));

			Pagamento[] arrayPagamentos = gson.fromJson(json, Pagamento[].class);
			if (arrayPagamentos != null) {
				pagamentos = new ArrayList<>(Arrays.asList(arrayPagamentos));
			}
		} catch (IOException e) {
			pagamentos = new ArrayList<>();
		}
	}

	@Override
	public void salvarDadosEmJson() throws IOException {
		String json = gson.toJson(pagamentos);

		try (FileWriter file = new FileWriter(ARQUIVO_JSON_PAGAMENTOS)) {
			file.write(json);
		}
	}

	public void salvarPagamento(Pagamento pagamento) throws IOException {
		pagamentos.add(pagamento);
		salvarDadosEmJson();
	}

	public boolean verificarId(long id) {
		if (pagamentos.size() > 0) {
			for (Pagamento pg : pagamentos) {
				if (pg.getId() == id) {
					return false;
				}
			}
		}
		return true;
	}

}
