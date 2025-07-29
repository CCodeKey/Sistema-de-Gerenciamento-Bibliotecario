package Biblioteca.view.bibliotecario;

import Biblioteca.controller.PagamentoController;
import Biblioteca.dao.DevolucaoDao;
import Biblioteca.model.Devolucao;
import Excecoes.DevolucaoException;
import Excecoes.MetodoDePagamentoException;
import Excecoes.MultaException;
import Excecoes.UsuarioNaoExisteException;
import Excecoes.ValoresNegativosException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class telaPagamentoMulta extends JFrame {

	private DevolucaoDao dao = new DevolucaoDao();
	private List<Devolucao> devolucoesPendentes;

	private JComboBox<String> comboMultas;
	private JButton botaoPagar;

	public telaPagamentoMulta() {
		setTitle("Pagamento de Multa");
		setSize(400, 200);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);

		// Inicialização dos componentes
		comboMultas = new JComboBox<>();
		botaoPagar = new JButton("Pagar");

		// Painel e layout
		JPanel painel = new JPanel();
		painel.add(comboMultas);
		painel.add(botaoPagar);
		setContentPane(painel);

		// Carrega multas pendentes no comboBox
		carregarMultasPendentes();

		// Ação do botão
		botaoPagar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				realizarPagamento();
			}
		});
	}

	private void carregarMultasPendentes() {
		devolucoesPendentes = dao.devolucoesNaoPagas();

		comboMultas.removeAllItems(); // Limpa o comboBox antes de adicionar novamente

		for (Devolucao d : devolucoesPendentes) {
			comboMultas.addItem("ID: " + d.getEmprestimo().getId() + " | Valor: R$ " + d.getMulta().getValorDaMulta());
		}
	}

	private void realizarPagamento() {
		int indexSelecionado = comboMultas.getSelectedIndex();

		if (indexSelecionado >= 0) {
			Devolucao devolucaoSelecionada = devolucoesPendentes.get(indexSelecionado);

			String[] opcoes = { "Dinheiro", "Pix", "Cartão" };
			String metodo = (String) JOptionPane.showInputDialog(this, "Escolha o método de pagamento da multa:",
					"Pagamento de Multa", JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);

			if (metodo != null) {
				try {
					dao.atualizarStatusPagamentoEmMulta(devolucaoSelecionada);

					try {

						PagamentoController pagamento = new PagamentoController(
								devolucaoSelecionada.getMulta().getValorDaMulta(), metodo.toLowerCase(),
								devolucaoSelecionada.getEmprestimo().getUsuario(), devolucaoSelecionada);
						pagamento.pagar();

					} catch (MetodoDePagamentoException e) {
						System.out.println(e.getMessage());

					} catch (UsuarioNaoExisteException e) {
						System.out.println(e.getMessage());

					} catch (ValoresNegativosException e) {
						System.out.println(e.getMessage());

					} catch (IOException e) {
						System.out.println(e.getMessage());

					} catch (DevolucaoException e) {
						System.out.println(e.getMessage());
					}

					JOptionPane.showMessageDialog(this, "Pagamento realizado com sucesso!");
					carregarMultasPendentes(); // Atualiza a lista após pagamento
				} catch (IOException e) {
					JOptionPane.showMessageDialog(this, "Erro ao pagar multa: " + e.getMessage());
					e.printStackTrace();

				}

			}
		} else {
			JOptionPane.showMessageDialog(this, "Selecione uma multa para pagar.");
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			telaPagamentoMulta tela = new telaPagamentoMulta();
			tela.setVisible(true);
		});
	}
}
