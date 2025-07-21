package Biblioteca.view.adm;

import javax.swing.*;
import java.awt.*;

public class telaPagamentoMulta extends JFrame {
    public telaPagamentoMulta(double valorMulta) {
        setTitle("Pagamento de Multa");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(5, 2, 10, 10));

        JLabel lblValor = new JLabel("Valor da Multa:");
        JTextField txtValor = new JTextField(String.format("%.2f", valorMulta));
        txtValor.setEditable(false);

        JLabel lblForma = new JLabel("Forma de Pagamento:");
        String[] opcoes = {"Dinheiro", "Cartão", "Pix"};
        JComboBox<String> comboForma = new JComboBox<>(opcoes);

        JLabel lblData = new JLabel("Data do Pagamento:");
        JTextField txtData = new JTextField(); // você pode preencher com a data atual por padrão depois

        JButton btnConfirmar = new JButton("Confirmar");
        JButton btnCancelar = new JButton("Cancelar");

        painel.add(lblValor);
        painel.add(txtValor);

        painel.add(lblForma);
        painel.add(comboForma);

        painel.add(lblData);
        painel.add(txtData);

        painel.add(btnConfirmar);
        painel.add(btnCancelar);

        add(painel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new telaPagamentoMulta(12.50).setVisible(true);
        });
    }
}
