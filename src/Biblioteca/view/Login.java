package Biblioteca.view;

import java.io.IOException;
import java.util.Scanner;

import Biblioteca.controller.LoginController;

import Biblioteca.model.UsuarioLogin;
import Excecoes.UsuarioInformacoesInvalidas;

public class Login {
	Scanner in = new Scanner(System.in);
	public static String usuarioSessao = "";

	public void main() throws IOException, UsuarioInformacoesInvalidas {
		System.out.print("\n##  ÁREA DE LOGIN  ##\n1. Login\n2. Cadastro de usuário\n: ");
		int op = in.nextInt();

		if (op == 1) {
			login();

		} else if (op == 2) {
			cadastrar();

		} else {
			System.out.println("\nEscolha uma opção válida!");
		}
	}

	private void login() {
		System.out.print("Digite seu CPF: ");
		String cpf = in.next();

		System.out.print("Digite sua senha: ");
		String password = in.next();

		LoginController login = new LoginController(cpf, password);

		UsuarioLogin loginUser = login.logar();
		if (loginUser != null) {
			usuarioSessao = loginUser.getNome();
			System.out.println("SESSÃO USER: " + usuarioSessao);
			System.out.println("\nLogin efetuado com sucesso!");

		} else {
			System.out.println("\nFalha ao efetuar login!");
		}

	}

	private void cadastrar() throws IOException, UsuarioInformacoesInvalidas {
		System.out.print("\nSeu nome: ");
		String nome = in.next();

		System.out.print("Sua matricula: ");
		String matricula = in.next();

		System.out.print("Tipo de usuario (Administrador - Bibliotecário - Estagário): ");
		String tipoDeUsuario = in.next();

		System.out.print("Seu telefone: +55 ");
		String telefone = in.next();

		System.out.print("Seu email: ");
		String email = in.next();

		System.out.print("Digite seu CPF: ");
		String cpf = in.next();

		System.out.print("Digite sua senha: ");
		String password = in.next();

		LoginController novaConta = new LoginController(cpf, password, nome, matricula, tipoDeUsuario.toLowerCase(),
				telefone, email);

		novaConta.criarContaADM();

	}
}
