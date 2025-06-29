import java.util.ArrayList;
import java.util.Scanner;

import model.Aluno;
import model.Artigo;
import model.Emprestimo;
import model.Livro;
import model.Obra;
import model.Professor;
import model.Revista;
import model.Servidor;
import model.Usuario;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		ArrayList<Obra> obras = new ArrayList<>();
		ArrayList<Usuario> usuarios = new ArrayList<>();
		ArrayList<Emprestimo> emprestimos = new ArrayList<>();

		// TODO: verificar se obra ja existe pelo código
		// TODO: não deixar o usuario pegar emprestado uma nova obra
		while (true) {
			System.out.println(
					"\n###  SISTEMA BIBLIOTECÁRIO  ###\n1. Cadastrar usuário\n2. Cadastrar obra\n3. Realizar empréstimo\n4. Excluir usúario cadastrado\n5. Excluir obra cadastrada\n6. Atualizar usuário\n7. Atualizar obra\n8. Visualizar usuários cadastrados\n9. Visualizar obras cadastradas\n10. Visualizar empréstimos");
			System.out.print(": ");
			int op = in.nextInt();

			if (op == 1) {
				Usuario usuarioBuscado = null;
				System.out.print("\nSeu nome: ");
				String nome = in.next();

				System.out.print("Sua matricula: ");
				String matricula = in.next();

				for (Usuario usr : usuarios) {
					if (usr.getMatricula().equals(matricula)) {
						usuarioBuscado = usr;
						break;
					}
				}

				if (usuarioBuscado == null) {
					System.out.print("Tipo de usuario (Aluno - Professor - Servidor): ");
					String tipoDeUsuario = in.next();

					System.out.print("Seu telefone: +55 ");
					String telefone = in.next();

					System.out.print("Seu email: ");
					String email = in.next();

					matricula = matricula.toLowerCase();

					if (tipoDeUsuario.toLowerCase().equals("professor")) {
						Usuario professor = new Professor(nome, matricula, tipoDeUsuario, telefone, email);
						usuarios.add(professor);

					} else if (tipoDeUsuario.toLowerCase().equals("aluno")) {
						Usuario aluno = new Aluno(nome, matricula, tipoDeUsuario, telefone, email);
						usuarios.add(aluno);

					} else if (tipoDeUsuario.toLowerCase().equals("servidor")) {
						Usuario servidor = new Servidor(nome, matricula, tipoDeUsuario, telefone, email);
						usuarios.add(servidor);

					} else {
						System.out.println("\nInforme um valor válido!");
					}
				} else {
					System.out.println("\nUsuário com matrícula já cadastrada!");
				}

			} else if (op == 2) {
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

			} else if (op == 3) {
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
						obra.setStatus("Ocupado");
						Emprestimo emprestimo = new Emprestimo(obra, user);
						emprestimos.add(emprestimo);
						System.out.println("\nEmpréstimo realizado com sucesso!");
					} else {
						System.out.println("\nObra não encontrada!");
					}

				} else {
					System.out.println("\nUsuário não econtrado!");
				}

			} else if (op == 4) {
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

			} else if (op == 5) {
				// TODO: excluir obra

			} else if (op == 6) {
				// TODO: editar usuário

			} else if (op == 7) {
				// TODO: editar obra

			} else if (op == 8) {
				if (usuarios.size() != 0) {
					for (Usuario usr : usuarios) {
						System.out.println("* " + usr);
					}
				} else {
					System.out.println("\nNenhum usuário cadastrado!");
				}
			} else if (op == 9) {
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
