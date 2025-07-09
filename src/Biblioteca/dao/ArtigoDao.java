package Biblioteca.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Biblioteca.model.Artigo;
import Biblioteca.model.Obra;

public class ArtigoDao {
	ArrayList<Obra> artigos = new ArrayList<>();

	private static final String ARQUIVO_JSON_ARTIGOS = "/home/code/Documents/workspace-spring-tool-suite-4-4.29.1.RELEASE/Sistema_de_Gerenciamento_Bibliotecario_novo/src/resources/artigos.json";

	private Gson gson = new GsonBuilder().setPrettyPrinting().create();

	public ArtigoDao() {
		carregarArtigos();
	}

	private void carregarArtigos() {
		try {
			String json = new String(Files.readAllBytes(Paths.get(ARQUIVO_JSON_ARTIGOS)));

			Obra[] arrayObras = gson.fromJson(json, Artigo[].class);
			if (arrayObras != null) {
				artigos = new ArrayList<>(Arrays.asList(arrayObras));
			}
		} catch (IOException e) {
			artigos = new ArrayList<>();
		}
	}

	private void salvarDadosEmJson() throws IOException {
		String jsonObras = gson.toJson(artigos);

		try (FileWriter file = new FileWriter(ARQUIVO_JSON_ARTIGOS)) {
			file.write(jsonObras);
		}
	}

	public void salvarObra(Obra obra) throws IOException {
		artigos.add(obra);
		salvarDadosEmJson();
	}

	public Obra buscarObraPorCodigo(long codigo) {
		Obra obra = null;
		for (Obra ob : artigos) {
			if (ob.getCodigo() == codigo) {
				obra = ob;
				break;
			}
		}
		return obra;
	}
}