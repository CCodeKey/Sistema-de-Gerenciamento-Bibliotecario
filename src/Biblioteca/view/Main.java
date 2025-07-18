package Biblioteca.view;

import java.io.IOException;
import java.util.Scanner;

import Excecoes.UsuarioInformacoesInvalidas;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		System.out.println("\n\n#######   SISTEMA BIBLIOTECÁRIO   #######");

		while (true) {
			String sessao = Login.usuarioSessao;

			if (sessao.isBlank()) {
				Login telaLogin = new Login();
				try {
					telaLogin.main();

				} catch (IOException | UsuarioInformacoesInvalidas e) {
					System.out.println(e.getMessage());
				}

			} else {
				Menu menu = new Menu();
				menu.main();
			}
		}

	}

}
