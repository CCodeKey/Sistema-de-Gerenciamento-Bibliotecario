package Biblioteca.view.estagiario;

import Biblioteca.view.Login;

import Biblioteca.view.bibliotecario.telaDevolucaoObra;
import Biblioteca.view.bibliotecario.telaPagamentoMulta;

import javax.swing.*;
import java.awt.*;

public class telaMenuPrincipalE extends JFrame {
	private JButton btnDevolucaoObra;
	private JButton btnPagarMulta;
	private JButton btnLogout;

	public telaMenuPrincipalE() {
		setTitle("Menu Estagiário");
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);

		JPanel painel = new JPanel();
		painel.setLayout(new GridLayout(3, 2, 5, 5));
		painel.setBorder(BorderFactory.createEmptyBorder(190, 70, 190, 70));

		btnDevolucaoObra = new JButton("Devolução de Obra");
		btnPagarMulta = new JButton("Pagar Devolução Pendente");
		JButton logoutButton = new JButton("Logout");
		logoutButton.addActionListener(e -> {
			Login.usuarioSessao = "";
			dispose();
			new Login();
		});

		painel.add(btnDevolucaoObra);
		painel.add(btnPagarMulta);
		painel.add(logoutButton);

		add(painel);

		btnDevolucaoObra.addActionListener(e -> {
			new telaDevolucaoObra().setVisible(true);
		});
		btnPagarMulta.addActionListener(e -> {
			new telaPagamentoMulta().setVisible(true);
		});
	}
}
