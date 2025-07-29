package Biblioteca.view.adm;

import Biblioteca.controller.UsuarioController;
import Biblioteca.model.Usuario;
import Excecoes.UsuarioNaoExisteException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class telaExcluirUsuario extends JFrame {
	private JTextField txtMatricula;
	private JLabel lblNome, lblTelefone, lblEmail;
	private JButton btnBuscar, btnExcluir;

	private Usuario usuarioEncontrado;
	private UsuarioController controller;

	public telaExcluirUsuario() {
		setTitle("Excluir Usuário");
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new GridLayout(5, 2));

		controller = new UsuarioController();

		add(new JLabel("Matrícula:"));
		txtMatricula = new JTextField();
		add(txtMatricula);

		btnBuscar = new JButton("Buscar");
		add(btnBuscar);
		btnExcluir = new JButton("Excluir");
		btnExcluir.setEnabled(false);
		add(btnExcluir);

		lblNome = new JLabel("Nome: ");
		add(lblNome);
		lblTelefone = new JLabel("Telefone: ");
		add(lblTelefone);
		lblEmail = new JLabel("Email: ");
		add(lblEmail);

		btnBuscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String matricula = txtMatricula.getText();
				try {
					usuarioEncontrado = controller.buscarUsuarioPorMatricula(matricula);
					lblNome.setText("Nome: " + usuarioEncontrado.getNome());
					lblTelefone.setText("Telefone: " + usuarioEncontrado.getTelefone());
					lblEmail.setText("Email: " + usuarioEncontrado.getEmail());
					btnExcluir.setEnabled(true);

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Usuário não encontrado.");
					btnExcluir.setEnabled(false);
					lblNome.setText("Nome:");
					lblTelefone.setText("Telefone:");
					lblEmail.setText("Email:");
				}
			}
		});

		btnExcluir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (usuarioEncontrado != null) {
					int confirm = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir este usuário?",
							"Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
					if (confirm == JOptionPane.YES_OPTION) {
						try {
							controller.excluirUsuario(usuarioEncontrado.getMatricula());
							JOptionPane.showMessageDialog(null, "Usuário excluído com sucesso!");
							dispose();
						} catch (UsuarioNaoExisteException | IOException ex) {
							JOptionPane.showMessageDialog(null, "Erro ao excluir usuário: " + ex.getMessage());
						}
					}
				}
			}
		});

		setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			new telaExcluirUsuario().setVisible(true);
		});
	}
}
