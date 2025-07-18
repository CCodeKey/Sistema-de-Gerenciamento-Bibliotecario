package Biblioteca.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Biblioteca.model.Artigo;
import Biblioteca.model.Livro;
import Biblioteca.model.Obra;
import Biblioteca.model.Revista;
import TypeAdapter.ObraTypeAdapter;
import interfaces.ManipulacaoDeArquivos;

public class ObraDao implements ManipulacaoDeArquivos {
	private ArrayList<Obra> obras = new ArrayList<>();
	private static final String ARQUIVO_JSON_OBRAS = "/home/code/Documents/workspace-spring-tool-suite-4-4.29.1.RELEASE/Sistema_de_Gerenciamento_Bibliotecario_SPRING/src/resources/json/obras.json";
	private Gson gson;
	private DevolucaoDao devolucaoDao;

	public ObraDao() {
		this.gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(Obra.class, new ObraTypeAdapter())
				.create();
		carregar();
		this.devolucaoDao = new DevolucaoDao();
	}

	@Override
	public void carregar() {
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

	@Override
	public void salvarDadosEmJson() throws IOException {
		String json = gson.toJson(obras);

		try (FileWriter file = new FileWriter(ARQUIVO_JSON_OBRAS)) {
			file.write(json);
		}
	}

	public void salvarObra(Obra obra) throws IOException {
		obras.add(obra);
		salvarDadosEmJson();
	}

	public Obra buscarObraPorCodigo(long codigo) {
		for (Obra art : obras) {
			if (art.getCodigo() == codigo) {
				return art;
			}
		}
		return null;
	}

	public Obra buscarObraPorCodigo(long codigo, String identificacaoDoTipo) {
		if (identificacaoDoTipo.equals("artigo")) {
			for (Obra art : obras) {
				if (art.getCodigo() == codigo && art instanceof Artigo) {
					return art;
				}
			}

		} else if (identificacaoDoTipo.equals("livro")) {
			for (Obra liv : obras) {
				if (liv.getCodigo() == codigo && liv instanceof Livro) {
					return liv;
				}
			}

		} else if (identificacaoDoTipo.equals("revista")) {
			for (Obra rev : obras) {
				if (rev.getCodigo() == codigo && rev instanceof Revista) {
					return rev;
				}
			}
		}
		return null;
	}

	public List<Obra> listarObrasDisponiveis() {
		List<Obra> obrasDisponiveis = new ArrayList<>();
		for (Obra ob : obras) {
			if (ob.getStatus().equals("disponivel")) {
				obrasDisponiveis.add(ob);
			}
		}
		return obrasDisponiveis;
	}

	public List<Obra> listarObrasEmprestadas() {
		List<Obra> obrasEmprestadas = new ArrayList<>();
		for (Obra ob : obras) {
			if (ob.getStatus().equals("ocupado")) {
				obrasEmprestadas.add(ob);
			}
		}
		return obrasEmprestadas;
	}

	public List<Obra> listarObrasAtrasadas() {
		return this.devolucaoDao.listarDevoluoesComObrasAtrasadas();
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

	public Obra buscarPorTitulo(String titulo) {
		if (obras.size() > 0) {
			for (Obra obra : obras) {
				if (obra.getTitulo().toLowerCase().equals(titulo.toLowerCase())) {
					return obra;
				}
			}
		}
		return null;
	}

	public List<Obra> buscarPorAutor(String autor) {
		if (obras.size() > 0) {
			List<Obra> obras_autor = new ArrayList<>();
			for (Obra obra : obras) {
				if (obra.getAutor().toLowerCase().equals(autor.toLowerCase())) {
					obras_autor.add(obra);
				}
			}
			return obras_autor;
		}
		return null;
	}

	public List<Obra> buscarPorTipo(String tipo) {
		if (obras.size() > 0) {
			List<Obra> obras_encontradas = new ArrayList<>();

			if (tipo.toLowerCase().equals("livro")) {
				for (Obra obra : obras) {
					if (obra instanceof Livro) {
						obras_encontradas.add(obra);
					}
				}
			} else if (tipo.toLowerCase().equals("artigo")) {
				for (Obra obra : obras) {
					if (obra instanceof Artigo) {
						obras_encontradas.add(obra);
					}
				}
			} else if (tipo.toLowerCase().equals("revista")) {
				for (Obra obra : obras) {
					if (obra instanceof Revista) {
						obras_encontradas.add(obra);
					}
				}
			}
			return obras_encontradas;
		}
		return null;
	}
}
