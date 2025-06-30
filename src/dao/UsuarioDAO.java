import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import model.Usuario;

public class UsuarioDAO {
	ArrayList<Usuario> usuarios = new ArrayList<>();
	private static final String ARQUIVO_JSON_USUARIOS = "/home/code/Downloads/Spring/Sistema-de-Gerenciamento-Bibliotecario/src/resources/data/usuarios.json";
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

	public void salvarUsuario(Usuario usuario) throws UsuarioExistenteNoSistemaException, IOException {
		Usuario usuarioEncontrado = buscarUsuario(usuario.getMatricula());

		if (usuarioEncontrado != null) {
			throw new UsuarioExistenteNoSistemaException();
		}
		usuarios.add(usuario);

		String jsonUser = gson.toJson(usuarios);

		try (FileWriter file = new FileWriter(ARQUIVO_JSON_USUARIOS)) {
			file.write(jsonUser);
		}

	}

	public void editarUsuario(Usuario usuario) {

	}

	public List<Usuario> listarUsuarios() {
		return usuarios;
	}

	public Usuario buscarUsuario(String matricula) {
		Usuario user = null;
		for (Usuario usr : usuarios) {
			if (usr.getMatricula().equals(matricula)) {
				user = usr;
				break;
			}
		}
		return user;
	}

	public void excluirUsuario(String matricula) {

	}

}