package Biblioteca.view.bibliotecario;

import Biblioteca.view.Login;
import relatorio.RelatorioEmprestimos;
import relatorio.RelatorioObras;
import relatorio.RelatorioUsuarios;

import javax.swing.*;
import java.awt.*;

public class telaMenuPrincipalB extends JFrame {
	private JButton btnRealizarEmprestimo;
	private JButton btnDevolucaoObra;
	private JButton btnPagarMulta;
	private JButton btnListarObras;
	private JButton btnBuscarObraTitulo;
	private JButton btnBuscarObraAutor;
	private JButton btnBuscarObraTipo;
	private JButton btnRelatorioEmprestimosMes;
	private JButton btnRelatorioObrasMaisEmprestadas;
	private JButton btnRelatorioUsuariosAtrasados;
	private JButton btnLogout;

	public telaMenuPrincipalB() {
		setTitle("Menu Bibliotecário");
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);

		JPanel painel = new JPanel();
		painel.setLayout(new GridLayout(11, 1, 5, 5));
		painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		btnRealizarEmprestimo = new JButton("Realizar Empréstimo");
		btnDevolucaoObra = new JButton("Devolução de Obra");
		btnPagarMulta = new JButton("Pagar Devolução Pendente");
		btnListarObras = new JButton("Listar Obras");
		btnBuscarObraTitulo = new JButton("Buscar Obra por Título");
		btnBuscarObraAutor = new JButton("Buscar Obra por Autor");
		btnBuscarObraTipo = new JButton("Buscar Obra por Tipo");
		btnRelatorioEmprestimosMes = new JButton("Relatório de Empréstimos do Mês");
		btnRelatorioObrasMaisEmprestadas = new JButton("Relatório de Obras Mais Emprestadas");
		btnRelatorioUsuariosAtrasados = new JButton("Relatório de Usuários Atrasados");
		JButton logoutButton = new JButton("Logout");
		logoutButton.addActionListener(e -> {
			Login.usuarioSessao = "";
			dispose();
			new Login();
		});

		painel.add(btnRealizarEmprestimo);
		painel.add(btnDevolucaoObra);
		painel.add(btnPagarMulta);
		painel.add(btnListarObras);
		painel.add(btnBuscarObraTitulo);
		painel.add(btnBuscarObraAutor);
		painel.add(btnBuscarObraTipo);
		painel.add(btnRelatorioEmprestimosMes);
		painel.add(btnRelatorioObrasMaisEmprestadas);
		painel.add(btnRelatorioUsuariosAtrasados);
		painel.add(logoutButton);

		add(painel);

		btnRealizarEmprestimo.addActionListener(e -> {
			new telaEmprestimo().setVisible(true);
		});
		btnDevolucaoObra.addActionListener(e -> {
			new telaDevolucaoObra().setVisible(true);
		});
		btnPagarMulta.addActionListener(e -> {
			new telaPagamentoMulta().setVisible(true);
		});
		btnListarObras.addActionListener(e -> {
			new telaListagemObras().setVisible(true);
		});
		btnBuscarObraTitulo.addActionListener(e -> {
			new telaListagemObraTitulo().setVisible(true);
		});
		btnBuscarObraAutor.addActionListener(e -> {
			new telaListagemObraAutor().setVisible(true);
		});
		btnBuscarObraTipo.addActionListener(e -> {
			new telaListagemObraTipo().setVisible(true);
		});
		btnRelatorioEmprestimosMes.addActionListener(e -> {
			try {
				RelatorioEmprestimos relatorioEmprestimos = new RelatorioEmprestimos();
				relatorioEmprestimos.gerarRelatorio();
				JOptionPane.showMessageDialog(this, "Relatório gerado com sucesso!");
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(this, "Erro ao gerar relatório: " + ex.getMessage());
			}
		});
		btnRelatorioObrasMaisEmprestadas.addActionListener(e -> {
			try {
				RelatorioObras relatorioObras = new RelatorioObras();
				relatorioObras.gerarRelatorio();
				JOptionPane.showMessageDialog(this, "Relatório gerado com sucesso!");
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(this, "Erro ao gerar relatório: " + ex.getMessage());
			}
		});
		btnRelatorioUsuariosAtrasados.addActionListener(e -> {
			try {
				RelatorioUsuarios relatorioUsuarios = new RelatorioUsuarios();
				relatorioUsuarios.gerarRelatorio();
				JOptionPane.showMessageDialog(this, "Relatório gerado com sucesso!");
			} catch (Exception ex) {
				ex.printStackTrace(); // Mostra o erro no console
				JOptionPane.showMessageDialog(this, "Erro ao gerar relatório: " + ex.getMessage());
			}
		});
	}
}
