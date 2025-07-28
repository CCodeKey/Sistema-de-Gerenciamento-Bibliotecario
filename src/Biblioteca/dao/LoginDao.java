package Biblioteca.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Biblioteca.model.Usuario;
import Biblioteca.model.UsuarioLogin;
import interfaces.ManipulacaoDeArquivos;

public class LoginDao implements ManipulacaoDeArquivos {

	ArrayList<UsuarioLogin> usuarios_login = new ArrayList<>();
	private static final String ARQUIVO_JSON_USUARIOS_LOGIN = "C:\\Users\\kaleu\\IdeaProjects\\Sistema-de-Gerenciamento-Bibliotecario\\src\\resources\\json\\login.json";
	private Gson gson = new GsonBuilder().setPrettyPrinting().create();

	public LoginDao() {
		carregar();
	}

	@Override
	public void carregar() {
		try {
			String json = new String(Files.readAllBytes(Paths.get(ARQUIVO_JSON_USUARIOS_LOGIN))); // convertendo bytes
																									// em
			// String
			UsuarioLogin[] arrayUsuarios = gson.fromJson(json, UsuarioLogin[].class); // convertendo json para array de
																						// usuarios
			if (arrayUsuarios != null) {
				usuarios_login = new ArrayList<>(Arrays.asList(arrayUsuarios)); // array -> lista -> ArrayList
			}
		} catch (IOException e) {
			// Se não tiver usuarios, vai começar vazia
			usuarios_login = new ArrayList<>();
		}
	}

	@Override
	public void salvarDadosEmJson() throws IOException {
		String jsonUser = gson.toJson(usuarios_login);

		try (FileWriter file = new FileWriter(ARQUIVO_JSON_USUARIOS_LOGIN)) {
			file.write(jsonUser);
		}
	}

	public void criarConta(UsuarioLogin usuario) throws IOException {
		usuarios_login.add(usuario);
		salvarDadosEmJson();
	}

	public UsuarioLogin verificarExistenciaDoUsuario(UsuarioLogin u) {
		if (usuarios_login.size() > 0) {
			for (UsuarioLogin user : usuarios_login) {
				if (user.getCpf().equals(u.getCpf()) || user.getEmail().equals(u.getEmail())
						|| user.getMatricula().equals(u.getMatricula()) || user.getTelefone().equals(u.getTelefone())) {
					return user;
				}
			}
		}
		return null;
	}

	public UsuarioLogin efetuarLogin(String cpf, String password) {
		if (usuarios_login.size() > 0) {
			for (UsuarioLogin user : usuarios_login) {
				if (user.getCpf().equals(cpf) && user.getPassword().equals(password)) {
					return user;
				}
			}
		}
		return null;
	}

//	public void salvarUsuario(Usuario usuario) throws IOException {
//		usuarios.add(usuario);
//		salvarDadosEmJson();
//	}
//
//	public void editarUsuario(Usuario usuarioNovo) throws IOException {
//		for (Usuario userAntigo : usuarios) {
//			if (userAntigo.getMatricula().equals(usuarioNovo.getMatricula())) {
//				usuarios.remove(userAntigo);
//				usuarios.add(usuarioNovo);
//				break;
//			}
//		}
//		salvarDadosEmJson();
//	}
//
//	public Usuario buscarUsuarioPorMatricula(String matricula) {
//		for (Usuario usr : usuarios) {
//			if (usr.getMatricula().equals(matricula)) {
//				return usr;
//			}
//		}
//		return null;
//	}
//
//	public void excluirUsuario(String matricula) throws IOException {
//		for (Usuario user : usuarios) {
//			if (user.getMatricula().equals(matricula)) {
//				usuarios.remove(user);
//				break;
//			}
//		}
//		salvarDadosEmJson();
//	}

}
