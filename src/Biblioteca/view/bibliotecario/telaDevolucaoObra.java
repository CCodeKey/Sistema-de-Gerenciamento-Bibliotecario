package Biblioteca.view.bibliotecario;

import javax.swing.*;
import java.awt.*;

import Biblioteca.controller.DevolucaoController;
import Biblioteca.controller.EmprestimoController;
import Biblioteca.model.Emprestimo;
import Excecoes.*;

import java.io.IOException;

public class telaDevolucaoObra extends JFrame {
	private EmprestimoController empController;

	public telaDevolucaoObra() {
		setTitle("Devolução de Obra");
		setSize(400, 200);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);

		JPanel painel = new JPanel(new GridLayout(3, 2, 10, 10));
		painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		JLabel lblIdEmprestimo = new JLabel("ID do Empréstimo:");
		JTextField txtIdEmprestimo = new JTextField();

		JButton btnEmp = new JButton("Mostrar Emprestimos");
		JButton btnConfirmar = new JButton("Confirmar Devolução");
		JButton btnCancelar = new JButton("Cancelar");

		painel.add(lblIdEmprestimo);
		painel.add(txtIdEmprestimo);
		painel.add(btnEmp);
		painel.add(btnConfirmar);
		painel.add(btnCancelar);

		add(painel);

		btnEmp.addActionListener(e -> {
			empController = new EmprestimoController();
			String listaEmString = "";
			try {
				for (Emprestimo emp : empController.listarEmprestimos()) {
					if(emp.getDevolvido() == false) {
						listaEmString += "° ID=" + emp.getId() + ", NOME=" + emp.getUsuario().getNome() + "\n";
					}
				}
			} catch (EmprestimoNaoEncontradoException e1) {
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(this, listaEmString, "Empréstimos", 1);
		});

		btnCancelar.addActionListener(e -> dispose());

		btnConfirmar.addActionListener(e -> {
			try {
				int idEmprestimo = Integer.parseInt(txtIdEmprestimo.getText());

				DevolucaoController devolucaoController = new DevolucaoController(idEmprestimo);
				devolucaoController.registrarDevolucao();

				if (devolucaoController.isMulta() != null) {
					String[] opcoes = { "Dinheiro", "Pix", "Cartão" };
					String metodo = (String) JOptionPane.showInputDialog(this,
							"Escolha o método de pagamento da multa:", "Pagamento de Multa",
							JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);

					if (metodo != null) {
						devolucaoController.pagamentoDeMulta(metodo.toLowerCase());
						JOptionPane.showMessageDialog(this, "Pagamento realizado com sucesso!");
					}
				}

				JOptionPane.showMessageDialog(this, "Devolução registrada com sucesso!");

			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(this, "Por favor, insira um número válido para o ID do empréstimo.");
			} catch (EmprestimoNaoEncontradoException | MetodoDePagamentoException | UsuarioNaoExisteException
					| ValoresNegativosException | IOException | DevolucaoException ex) {
				JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			}
		});
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			new telaDevolucaoObra().setVisible(true);
		});
	}
}
