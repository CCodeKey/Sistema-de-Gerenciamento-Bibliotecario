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

import Biblioteca.model.Usuario;

public class UsuarioDAO {
	ArrayList<Usuario> usuarios = new ArrayList<>();
	private static final String ARQUIVO_JSON_USUARIOS = "/home/code/Documents/workspace-spring-tool-suite-4-4.29.1.RELEASE/Sistema_de_Gerenciamento_Bibliotecario_SPRING/src/resources/usuarios.json";
	private Gson gson = new GsonBuilder().setPrettyPrinting().create();

	public UsuarioDAO() {
		carregarUsuarios();
	}

	private void carregarUsuarios() {
		try {
			String json = new String(Files.readAllBytes(Paths.get(ARQUIVO_JSON_USUARIOS))); // convertendo bytes em
																							// String
			Usuario[] arrayUsuarios = gson.fromJson(json, Usuario[].class); // convertendo json para array de usuarios
			if (arrayUsuarios != null) {
				usuarios = new ArrayList<>(Arrays.asList(arrayUsuarios)); // array -> lista -> ArrayList
			}
		} catch (IOException e) {
			// Se não tiver usuarios, vai começar vazia
			usuarios = new ArrayList<>();
		}
	}

	private void salvarDadosEmJson() throws IOException {
		String jsonUser = gson.toJson(usuarios);

		try (FileWriter file = new FileWriter(ARQUIVO_JSON_USUARIOS)) {
			file.write(jsonUser);
		}
	}

	public void salvarUsuario(Usuario usuario) throws IOException {
		usuarios.add(usuario);
		salvarDadosEmJson();
	}

	public void editarUsuario(Usuario usuarioNovo) throws IOException {
		for (Usuario userAntigo : usuarios) {
			if (userAntigo.getMatricula().equals(usuarioNovo.getMatricula())) {
				usuarios.remove(userAntigo);
				usuarios.add(usuarioNovo);
				break;
			}
		}
		salvarDadosEmJson();
	}

	public Usuario buscarUsuarioPorMatricula(String matricula) {
		for (Usuario usr : usuarios) {
			if (usr.getMatricula().equals(matricula)) {
				return usr;
			}
		}
		return null;
	}

	public void excluirUsuario(String matricula) throws IOException {
		for (Usuario user : usuarios) {
			if (user.getMatricula().equals(matricula)) {
				usuarios.remove(user);
				break;
			}
		}
		salvarDadosEmJson();
	}

}