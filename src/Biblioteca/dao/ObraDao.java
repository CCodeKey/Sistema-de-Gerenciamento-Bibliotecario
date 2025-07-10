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
import TypeAdapter.ObraTypeAdapter;

public class ObraDao {
	private ArrayList<Obra> obras = new ArrayList<>();
	private static final String ARQUIVO_JSON_OBRAS = "path";
	private Gson gson;

	public ObraDao() {
		this.gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(Obra.class, new ObraTypeAdapter())
				.create();
		carregarObras();
	}

	private void carregarObras() {
		try {
			String json = new String(Files.readAllBytes(Paths.get(ARQUIVO_JSON_OBRAS)));
			Obra[] arrayObras = gson.fromJson(json, Obra[].class);
			if (arrayObras != null) {
				obras = new ArrayList<>(Arrays.asList(arrayObras));
			}
		} catch (IOException e) {
			obras = new ArrayList<>();
		}
	}

	private void salvarDadosEmJson() throws IOException {
		String json = gson.toJson(obras);

		try (FileWriter file = new FileWriter(ARQUIVO_JSON_OBRAS)) {
			file.write(json);
		}
	}

	public void salvarObra(Obra obra) throws IOException {
		obras.add(obra);
		salvarDadosEmJson();
	}

	private void atualizarObra(Obra obraAtualizada) throws IOException {
		for (int i = 0; i < obras.size(); i++) {
			if (obras.get(i).getCodigo() == obraAtualizada.getCodigo()) {
				obras.set(i, obraAtualizada);
				salvarDadosEmJson();
				return;
			}
		}
		throw new IllegalArgumentException("Obra não encontrada com código: " + obraAtualizada.getCodigo());
	}

	public Obra buscarObraPorCodigo(long codigo) {
		for (Obra ob : obras) {
			if (ob.getCodigo() == codigo) {
				return ob;
			}
		}
		return null;
	}

	public void ocuparObra(long codigoObra) throws IOException {
		for (Obra obr : obras) {
			if (obr.getCodigo() == codigoObra) {
				obr.setStatus("ocupado");
				salvarDadosEmJson();
				break;
			}
		}
	}

	public void disponibilizarObra(long codigoObra) throws IOException {
		for (Obra obr : obras) {
			if (obr.getCodigo() == codigoObra) {
				obr.setStatus("disponivel");
				salvarDadosEmJson();
				break;
			}
		}
	}
}
