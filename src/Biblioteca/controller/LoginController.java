package Biblioteca.controller;

import java.io.IOException;
import java.util.Random;

import Biblioteca.dao.LoginDao;
import Biblioteca.model.UsuarioLogin;
import Excecoes.UsuarioInformacoesInvalidas;

public class LoginController {
	private String cpf;
	private String password;
	private LoginDao dao;
	private String nome;
	private String matricula;
	private String tipoUsuario;
	private String telefone;
	private String email;
	private long id;

	public LoginController(String cpf, String password) {
		this.dao = new LoginDao();
		this.cpf = cpf;
		this.password = password;

	}

	public LoginController(String cpf, String password, String nome, String matricula, String tipoUsuario,
			String telefone, String email) {
		this.dao = new LoginDao();
		this.cpf = cpf;
		this.password = password;
		this.nome = nome;
		this.matricula = matricula;
		this.tipoUsuario = tipoUsuario;
		this.telefone = telefone;
		this.email = email;
		this.id = gerarId();
	}

	private long gerarId() {
		Random ramdom = new Random();
		int uid = ramdom.nextInt(1000000000);

		if (dao.verificarId(uid) == false) {
			gerarId();
		}

		return uid;
	}

	public void criarConta() throws IOException, UsuarioInformacoesInvalidas {
		UsuarioLogin user = new UsuarioLogin(id, nome, matricula, tipoUsuario, telefone, email, cpf, password);

		if (dao.verificarExistenciaDoUsuario(user) != null) {
			throw new UsuarioInformacoesInvalidas();
		}

		dao.criarConta(user);

	}

	public void criarContaADM() throws IOException, UsuarioInformacoesInvalidas {

		UsuarioLogin user = new UsuarioLogin(id, nome, matricula, "administrador", telefone, email, cpf, password);

		if (dao.verificarExistenciaDoUsuario(user) != null) {
			throw new UsuarioInformacoesInvalidas();
		}

		dao.criarConta(user);
	}

	public UsuarioLogin logar() {
		UsuarioLogin user = dao.efetuarLogin(cpf, password);
		if (user != null) {
			return user;
		}
		return null;
	}
}
