package Biblioteca.view.adm;

import Biblioteca.dao.DevolucaoDao;
import Biblioteca.model.Devolucao;

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
            try {
                dao.atualizarStatusPagamentoEmMulta(devolucaoSelecionada);
                JOptionPane.showMessageDialog(this, "Multa paga com sucesso!");
                carregarMultasPendentes(); // Atualiza a lista após pagamento
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Erro ao pagar multa: " + e.getMessage());
                e.printStackTrace();
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
