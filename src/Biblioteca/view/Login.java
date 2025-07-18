package Biblioteca.view;

import java.io.IOException;
import java.util.Scanner;

import Biblioteca.controller.LoginController;

import Biblioteca.model.UsuarioLogin;
import Excecoes.UsuarioInformacoesInvalidas;

public class Login {
	Scanner in = new Scanner(System.in);
	public static String usuarioSessao = "";
	public static String perfilAcessoUser = "";

	public void main() throws IOException, UsuarioInformacoesInvalidas {
		System.out.print("\n##  ÁREA DE LOGIN  ##");
		login();
	}

	private void login() {
		System.out.print("\n- Digite seu CPF: ");
		String cpf = in.next();

		System.out.print("- Digite sua senha: ");
		String password = in.next();

		LoginController login = new LoginController(cpf, password);

		UsuarioLogin loginUser = login.logar();
		if (loginUser != null) {
			usuarioSessao = loginUser.getNome();
			perfilAcessoUser = loginUser.getTipoUsuario();
			System.out.println("\nLogin efetuado com sucesso!");

		} else {
			System.out.println("\nFalha ao efetuar login!");
		}

	}
}
