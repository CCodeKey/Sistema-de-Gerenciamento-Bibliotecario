package Biblioteca.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Biblioteca.model.Devolucao;
import Biblioteca.model.Obra;
import Excecoes.ObraNaoExisteException;
import TypeAdapter.LocalDateTypeAdapter;
import TypeAdapter.ObraTypeAdapter;

public class DevolucaoDao {
	ArrayList<Devolucao> devolucoes = new ArrayList<>();
	private static final String ARQUIVO_JSON_DEVOLUCOES = "/home/code/Documents/workspace-spring-tool-suite-4-4.29.1.RELEASE/Sistema_de_Gerenciamento_Bibliotecario_SPRING/src/resources/devolucoes.json";
	private Gson gson;

	public DevolucaoDao() {
		this.gson = new GsonBuilder().setPrettyPrinting()
				.registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
				.registerTypeAdapter(Obra.class, new ObraTypeAdapter()).create();
		carregarEmprestimos();
	}

	private void carregarEmprestimos() {
		try {
			String json = new String(Files.readAllBytes(Paths.get(ARQUIVO_JSON_DEVOLUCOES)));
			Devolucao[] arrayDevolucoes = gson.fromJson(json, Devolucao[].class);
			if (arrayDevolucoes != null) {
				devolucoes = new ArrayList<>(Arrays.asList(arrayDevolucoes));
			}
		} catch (IOException e) {
			devolucoes = new ArrayList<>();
		}
	}

	private void salvarDadosEmJson() throws IOException {
		String jsonDevolucoes = gson.toJson(devolucoes);
		try (FileWriter file = new FileWriter(ARQUIVO_JSON_DEVOLUCOES)) {
			file.write(jsonDevolucoes);
		}
	}

	public void registrarDevolucao(Devolucao dev) throws IOException {
		devolucoes.add(dev);
		salvarDadosEmJson();
	}

	public void atualizarStatusPagamentoEmMulta(Devolucao dev) throws IOException {
		if (devolucoes.size() > 0) {
			for (Devolucao d : devolucoes) {
				if (d.getEmprestimo().getId() == dev.getEmprestimo().getId()) {
					d.getMulta().setMultaPaga(true);
					salvarDadosEmJson();
					break;
				}
			}
		}
	}

	public boolean verificarIdMulta(long id) throws IOException {
		if (devolucoes.size() > 0) {
			for (Devolucao d : devolucoes) {
				if (d.getMulta() != null) {
					if (d.getMulta().getId() == id) {
						return false;
					}
				}
			}
		}
		return true;
	}

	public List<Devolucao> devolucoesNaoPagas() {
		ArrayList<Devolucao> devolucoesASeremPagas = new ArrayList<>();
		if (devolucoes.size() > 0) {
			for (Devolucao d : devolucoes) {
				if (d.getMulta() != null) {
					if (d.getMulta().getMultaPaga() == false) {
						devolucoesASeremPagas.add(d);
					}
				}
			}
		}
		return devolucoesASeremPagas;
	}

	public List<Obra> listarDevoluoesComObrasAtrasadas() {
		if (devolucoes.size() > 0) {
			List<Obra> obras = new ArrayList<>();
			for (Devolucao d : devolucoes) {
				if (d.getMulta() != null) {
					obras.add(d.getEmprestimo().getObra());
				}
			}
			return obras;
		}
		return null;
	}

}
