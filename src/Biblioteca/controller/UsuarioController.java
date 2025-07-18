package Biblioteca.controller;

import java.io.IOException;

import Biblioteca.dao.UsuarioDao;
import Biblioteca.model.Usuario;
import Excecoes.UsuarioExistenteException;
import Excecoes.UsuarioNaoExisteException;

public class UsuarioController {
	private String nome;
	private String matricula;
	private String tipoUsuario;
	private String telefone;
	private String email;
	private UsuarioDao dao;

	public UsuarioController() {
		this.dao = new UsuarioDao();
	}

	public UsuarioController(String nome, String matricula, String tipoUsuario, String telefone, String email) {
		this.nome = nome;
		this.matricula = matricula;
		this.tipoUsuario = tipoUsuario;
		this.telefone = telefone;
		this.email = email;
		this.dao = new UsuarioDao();
	}

	public void novoUsuario() throws UsuarioExistenteException, IOException {
		if (dao.buscarUsuarioPorMatricula(matricula) == null) {
			Usuario user = new Usuario(nome, matricula, tipoUsuario, telefone, email);
			dao.salvarUsuario(user);
		} else {
			throw new UsuarioExistenteException(tipoUsuario);
		}

	}

	public void editarUsuario(String matricula, String nome, String telefone)
			throws UsuarioNaoExisteException, IOException {
		Usuario usuario = dao.buscarUsuarioPorMatricula(matricula);

		if (usuario != null) {
			if (nome.isEmpty() == false) {
				usuario.setNome(nome);

			}
			if (telefone.isEmpty() == false) {
				usuario.setTelefone(telefone);

			}
			dao.editarUsuario(usuario);

		} else {
			throw new UsuarioNaoExisteException();
		}
	}

	public void excluirUsuario(String matricula) throws UsuarioNaoExisteException, IOException {
		Usuario usuario = dao.buscarUsuarioPorMatricula(matricula);

		if (usuario != null) {
			dao.excluirUsuario(matricula);
		} else {
			throw new UsuarioNaoExisteException();
		}
	}

	public Usuario buscarUsuarioPorMatricula(String mat) {
		return dao.buscarUsuarioPorMatricula(mat);
	}

}
