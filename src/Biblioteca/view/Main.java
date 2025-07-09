package Biblioteca.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Biblioteca.controller.ObraController;
import Biblioteca.controller.UsuarioController;
import Biblioteca.model.Artigo;
import Biblioteca.model.Emprestimo;
import Biblioteca.model.Livro;
import Biblioteca.model.Obra;
import Biblioteca.model.Revista;
import Biblioteca.model.Usuario;
import Excecoes.ObraExistenteException;
import Excecoes.UsuarioExistenteException;
import Excecoes.UsuarioNaoExisteException;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		ArrayList<Obra> obras = new ArrayList<>();
		ArrayList<Usuario> usuarios = new ArrayList<>();
		ArrayList<Emprestimo> emprestimos = new ArrayList<>();

		while (true) {
			System.out.println(
					"\n###  SISTEMA BIBLIOTECÁRIO  ###\n1. Cadastrar usuário\n2. Editar usuário\n3. Excluir usuário\n4. Cadastrar Obra\n=======================================\n5. Excluir obra cadastrada\n6. Atualizar usuário\n7. Atualizar obra\n8. Visualizar usuários cadastrados\n9. Visualizar obras cadastradas\n10. Visualizar empréstimos");
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

				ObraController obraCtrl = new ObraController(codigo, titulo, autor, anoDePublicacao, tipoDaObra);

				try {
					obraCtrl.novaObra();

				} catch (ObraExistenteException | IOException e) {
					System.out.println(e.getMessage());
				}

			} else if (op == 5) {
//				ObraController ob = new ObraController();
//				for (Obra o : ob.listarObras()){
//					System.out.println(o);
//				}

			} else if (op == 20) {

				Obra obraBuscada = null;
				System.out.print("\nDigite o código da obra: ");
				long codigo = in.nextLong();

				for (Obra obraB : obras) {
					if (obraB.getCodigo() == codigo) {
						obraBuscada = obraB;
						break;
					}
				}

				if (obraBuscada == null) {
					System.out.print("Título da obra: ");
					String titulo = in.next();

					System.out.print("Nome do autor: ");
					String autor = in.next();

					System.out.print("Tipo da obra (Artigo - Livro - Revista): ");
					String tipoDaObra = in.next();

					System.out.print("Ano de publicação: ");
					int anoDePublicacao = in.nextInt();

					if (tipoDaObra.toLowerCase().equals("artigo")) {
						Obra artigo = new Artigo(codigo, titulo, autor, anoDePublicacao);
						obras.add(artigo);

					} else if (tipoDaObra.toLowerCase().equals("livro")) {
						Obra livro = new Livro(codigo, titulo, autor, anoDePublicacao);
						obras.add(livro);

					} else if (tipoDaObra.toLowerCase().equals("revista")) {
						Obra revista = new Revista(codigo, titulo, autor, anoDePublicacao);
						obras.add(revista);

					} else {
						System.out.println("\nInforme uma opção válida!");
					}
				} else {
					System.out.println("\nObra com o código já cadastrado!");
				}

			} else if (op == 30) {
				Usuario user = null;
				Obra obra = null;
				System.out.print("\nDigite a matrícula do usuário: ");
				String matriculaUser = in.next();
				for (Usuario usr : usuarios) {
					if (usr.getMatricula().equals(matriculaUser.toLowerCase())) {
						user = usr;
						break;
					}
				}

				if (user != null) {
					System.out.print("Digite o código da obra: ");
					long codigoObra = in.nextLong();
					for (Obra obr : obras) {
						if (obr.getCodigo() == codigoObra) {
							obra = obr;
							break;
						}
					}
					if (obra != null) {
						if (obra.getStatus().equals("disponivel")) {
							obra.setStatus("ocupado");
							Emprestimo emprestimo = new Emprestimo(obra, user, null, null, false);
							emprestimos.add(emprestimo);
							System.out.println("\nEmpréstimo realizado com sucesso!");
						} else {
							System.out.println("\nEssa obra já está emprestada!");
						}

					} else {
						System.out.println("\nObra não encontrada!");
					}

				} else {
					System.out.println("\nUsuário não econtrado!");
				}

			} else if (op == 44) {
				Usuario usarioParaExclusao = null;

				System.out.print("\nDigite a mátricula do usúario que deseja excluir: ");
				String matriculaParaExclusao = in.next();

				for (Usuario usr : usuarios) {
					if (usr.getMatricula().equals(matriculaParaExclusao.toLowerCase())) {
						usarioParaExclusao = usr;
						break;
					}
				}

				if (usarioParaExclusao != null) {
					System.out.print("Tem certeza que deseja excluir o usuario (s/n): ");
					String decisao = in.next();
					if (decisao.toLowerCase().equals("s")) {
						usuarios.remove(usarioParaExclusao);
						System.out.println("\nUsuário excluído com sucesso!");
					}
				} else {
					System.out.println("\nUsuário não encontrado!");
				}

			} else if (op == 50) {
				// TODO: excluir obra

			} else if (op == 60) {
				// TODO: editar usuário

			} else if (op == 70) {
				// TODO: editar obra

			} else if (op == 80) {

			} else if (op == 90) {
				if (obras.size() != 0) {
					for (Obra obra : obras) {
						System.out.println("* " + obra);
					}
				} else {
					System.out.println("\nNenhuma obra cadastrada!");
				}
			} else if (op == 10) {
				if (emprestimos.size() != 0) {
					for (Emprestimo emp : emprestimos) {
						System.out.println("* " + emp);
					}
				} else {
					System.out.println("\nNenhum empréstimo realizado!");
				}
			} else {
				System.out.println("\nInforme uma opção válida!");
			}
		}

	}

}
