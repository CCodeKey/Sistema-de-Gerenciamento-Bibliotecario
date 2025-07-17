package Biblioteca.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.time.LocalDate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import Biblioteca.model.Obra;
import TypeAdapter.LocalDateTypeAdapter;
import TypeAdapter.ObraTypeAdapter;
import Biblioteca.model.Emprestimo;

public class EmprestimoDao {
	ArrayList<Emprestimo> emprestimos = new ArrayList<>();
	private static final String ARQUIVO_JSON_EMPRESTIMOS = "/home/code/Documents/workspace-spring-tool-suite-4-4.29.1.RELEASE/Sistema_de_Gerenciamento_Bibliotecario_SPRING/src/resources/json/emprestimos.json";
	private Gson gson;

	public EmprestimoDao() {
		this.gson = new GsonBuilder().setPrettyPrinting()
				.registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
				.registerTypeAdapter(Obra.class, new ObraTypeAdapter()).create();
		carregarEmprestimos();
	}

	private void carregarEmprestimos() {
		try {
			String json = new String(Files.readAllBytes(Paths.get(ARQUIVO_JSON_EMPRESTIMOS)));
			Emprestimo[] arrayEmprestimos = gson.fromJson(json, Emprestimo[].class);
			if (arrayEmprestimos != null) {
				emprestimos = new ArrayList<>(Arrays.asList(arrayEmprestimos));
			}
		} catch (IOException e) {
			emprestimos = new ArrayList<>();
		}
	}

	private void salvarDadosEmJson() throws IOException {
		String jsonEmprestimos = gson.toJson(emprestimos);
		try (FileWriter file = new FileWriter(ARQUIVO_JSON_EMPRESTIMOS)) {
			file.write(jsonEmprestimos);
		}
	}

	public void salvarEmprestimo(Emprestimo emprestimo) throws IOException {
		emprestimos.add(emprestimo);
		salvarDadosEmJson();
	}

	public boolean verificarId(int id) {
		if (emprestimos.size() > 0) {
			for (Emprestimo emp : emprestimos) {
				if (emp.getId() == id) {
					return false;
				}
			}
		}
		return true;
	}

	public Emprestimo buscarEmprestimo(int idEmprestimo) {
		for (Emprestimo emp : emprestimos) {
			if (emp.getId() == idEmprestimo) {
				return emp;
			}
		}
		return null;
	}

	public void disponibilizarObraDeEmprestimo(Emprestimo emprestimo) throws IOException {
		for (Emprestimo emp : emprestimos) {
			if (emp.getId() == emprestimo.getId()) {
				emp.setDevolvido(true);
				emp.getObra().setStatus("disponivel");
				salvarDadosEmJson();
				break;
			}
		}

	}

	public List<Emprestimo> listarEmprestimos() {
		if (emprestimos.size() > 1) {
			return emprestimos;
		}
		return null;
	}
}
