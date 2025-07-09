package Biblioteca.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Biblioteca.model.Obra;
import Biblioteca.model.Revista;

public class RevistaDao {
	ArrayList<Obra> revistas = new ArrayList<>();

	private static final String ARQUIVO_JSON_REVISTAS = "/home/code/Documents/workspace-spring-tool-suite-4-4.29.1.RELEASE/Sistema_de_Gerenciamento_Bibliotecario_novo/src/resources/revistas.json";

	private Gson gson = new GsonBuilder().setPrettyPrinting().create();

	public RevistaDao() {
		carregarRevistas();
	}

	private void carregarRevistas() {
		try {
			String json = new String(Files.readAllBytes(Paths.get(ARQUIVO_JSON_REVISTAS)));

			Obra[] arrayObras = gson.fromJson(json, Revista[].class);
			if (arrayObras != null) {
				revistas = new ArrayList<>(Arrays.asList(arrayObras));
			}
		} catch (IOException e) {
			revistas = new ArrayList<>();
		}
	}

	private void salvarDadosEmJson() throws IOException {
		String jsonObras = gson.toJson(revistas);

		try (FileWriter file = new FileWriter(ARQUIVO_JSON_REVISTAS)) {
			file.write(jsonObras);
		}
	}

	public void salvarObra(Obra obra) throws IOException {
		revistas.add(obra);
		salvarDadosEmJson();
	}

	public Obra buscarObraPorCodigo(long codigo) {
		Obra obra = null;
		for (Obra ob : revistas) {
			if (ob.getCodigo() == codigo) {
				obra = ob;
				break;
			}
		}
		return obra;
	}
}