package Biblioteca.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Biblioteca.model.Livro;
import Biblioteca.model.Obra;

public class LivroDao {
	ArrayList<Obra> livros = new ArrayList<>();

	private static final String ARQUIVO_JSON_LIVROS = "/home/code/Documents/workspace-spring-tool-suite-4-4.29.1.RELEASE/Sistema_de_Gerenciamento_Bibliotecario_novo/src/resources/livros.json";

	private Gson gson = new GsonBuilder().setPrettyPrinting().create();

	public LivroDao() {
		carregarLivros();
	}

	private void carregarLivros() {
		try {
			String json = new String(Files.readAllBytes(Paths.get(ARQUIVO_JSON_LIVROS)));

			Obra[] arrayObras = gson.fromJson(json, Livro[].class);
			if (arrayObras != null) {
				livros = new ArrayList<>(Arrays.asList(arrayObras));
			}
		} catch (IOException e) {
			livros = new ArrayList<>();
		}
	}

	private void salvarDadosEmJson() throws IOException {
		String jsonObras = gson.toJson(livros);

		try (FileWriter file = new FileWriter(ARQUIVO_JSON_LIVROS)) {
			file.write(jsonObras);
		}
	}

	public void salvarObra(Obra obra) throws IOException {
		livros.add(obra);
		salvarDadosEmJson();
	}

	public Obra buscarObraPorCodigo(long codigo) {
		Obra obra = null;
		for (Obra ob : livros) {
			if (ob.getCodigo() == codigo) {
				obra = ob;
				break;
			}
		}
		return obra;
	}

}