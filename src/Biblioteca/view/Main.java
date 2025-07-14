package Biblioteca.view;

import java.io.IOException;
import java.util.Scanner;

import Biblioteca.controller.DevolucaoController;
import Biblioteca.controller.EmprestimoController;
import Biblioteca.controller.ObraController;
import Biblioteca.controller.UsuarioController;
import Biblioteca.model.Obra;
import Biblioteca.model.Usuario;
import Excecoes.EmprestimoNaoEncontradoException;
import Excecoes.MetodoDePagamentoException;
import Excecoes.ObraExistenteException;
import Excecoes.ObraNaoDisponivelException;
import Excecoes.ObraNaoExisteException;
import Excecoes.UsuarioExistenteException;
import Excecoes.UsuarioNaoExisteException;
import Excecoes.ValoresNegativosException;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		while (true) {
			System.out.println(
					"\n###  SISTEMA BIBLIOTECÁRIO  ###\n1. Cadastrar usuário\n2. Editar usuário\n3. Excluir usuário\n4. Cadastrar Obra\n5. Realizar empréstimo\n6. Devolução de Obra\n7. Listar obras");
			System.out.print(": ");
			int op = in.nextInt();

			if (op == 1) {
				System.out.print("\nSeu nome: ");
				String nome = in.next();

				System.out.print("Sua matricula: ");
				String matricula = in.next();

				System.out.print("Tipo de usuario (Aluno - Professor - Servidor): ");
				String tipoDeUsuario = in.next();

				System.out.print("Seu telefone: +55 ");
				String telefone = in.next();

				System.out.print("Seu email: ");
				String email = in.next();

				matricula = matricula.toLowerCase();

				UsuarioController usuario = new UsuarioController(nome, matricula, tipoDeUsuario, telefone, email);
				try {
					usuario.novoUsuario();

				} catch (UsuarioExistenteException | IOException e) {
					System.out.println(e.getMessage());
				}

			} else if (op == 2) {
				System.out.print("\nDigite a matricula do usuario: ");
				String matricula = in.next();

				System.out.print("Novo nome: ");
				String nome = in.next();

				System.out.print("Novo telefone: +55 ");
				String telefone = in.next();

				System.out.print("Novo email: ");
				String email = in.next();

				matricula = matricula.toLowerCase();

				UsuarioController usuario = new UsuarioController();
				try {
					usuario.editarUsuario(matricula, nome, telefone, email);

				} catch (UsuarioNaoExisteException | IOException e) {
					System.out.println(e.getMessage());
				}

			} else if (op == 3) {
				System.out.print("\nDigite a matricula do usuario para a exclusao: ");
				String matricula = in.next();

				matricula = matricula.toLowerCase();

				UsuarioController usuario = new UsuarioController();
				try {
					usuario.excluirUsuario(matricula);

				} catch (UsuarioNaoExisteException | IOException e) {
					System.out.println(e.getMessage());
				}

			} else if (op == 4) {
				System.out.print("\nTítulo da obra: ");
				String titulo = in.next();

				System.out.print("Nome do autor: ");
				String autor = in.next();

				System.out.print("Tipo da obra (Artigo - Livro - Revista): ");
				String tipoDaObra = in.next();

				System.out.print("Ano de publicação: ");
				int anoDePublicacao = in.nextInt();

				System.out.print("Digite o código da obra: ");
				long codigo = in.nextLong();

				String id = null;

				if (tipoDaObra.toLowerCase().equals("artigo")) {
					System.out.print("Categoria do artigo: ");
					String categoria = in.next();
					id = categoria;

				} else if (tipoDaObra.toLowerCase().equals("livro")) {
					System.out.print("ISBN do livro: ");
					String isbn = in.next();
					id = isbn;

				} else if (tipoDaObra.toLowerCase().equals("revista")) {
					System.out.print("ISSN da revista: ");
					String issn = in.next();
					id = issn;
				}

				ObraController obraCtrl = new ObraController(codigo, titulo, autor, anoDePublicacao, tipoDaObra, id);

				try {
					obraCtrl.novaObra();

				} catch (ObraExistenteException | IOException e) {
					System.out.println(e.getMessage());
				}

			} else if (op == 5) {

				System.out.print("\nDigite a matrícula do usuário: ");
				String matriculaUser = in.next();

				System.out.print("Digite o tipo da obra: ");
				String tipoObra = in.next();

				System.out.print(tipoObra + " - código da obra: ");
				long codigoObra = in.nextLong();

				UsuarioController usuarioController = new UsuarioController();
				Usuario usuario = usuarioController.buscarUsuarioPorMatricula(matriculaUser);

				ObraController obraController = new ObraController(tipoObra);

				Obra obra = obraController.buscarObraPorCodigo(codigoObra);

				EmprestimoController emprestimo = new EmprestimoController(usuario, obra);

				try {
					emprestimo.realizarEmprestimo();

				} catch (UsuarioNaoExisteException | ObraNaoExisteException | ObraNaoDisponivelException
						| IOException emp) {
					System.out.println(emp.getMessage());
				}

			} else if (op == 6) {
				System.out.print("\nDigite o ID do emprestimo: ");
				int idEmprestimo = in.nextInt();

				try {
					DevolucaoController devol = new DevolucaoController(idEmprestimo);
					devol.registrarDevolucao();
					if (devol.isMulta() != null) {
						System.out.print("\n\nDigite o método de pagamento para a multa.\n. Dinheiro\n. Pix\n. Cartão\n: ");
						String metodoPagamento = in.next();
						devol.pagamentoDeMulta(metodoPagamento.toLowerCase());
					}

				} catch (EmprestimoNaoEncontradoException | IOException e) {
					System.out.println(e.getMessage());
				} catch (MetodoDePagamentoException e) {
					System.out.println(e.getMessage());

				} catch (UsuarioNaoExisteException e) {
					System.out.println(e.getMessage());

				} catch (ValoresNegativosException e) {
					System.out.println(e.getMessage());
				}

			} else if (op == 7) {
				ObraController obraController = new ObraController();
				for (Obra o : obraController.listarObras()) {
					System.out.println("- " + o);
				}

			} else {
				System.out.println("\nInforme uma opção válida!");
			}
		}

	}

}
