package Biblioteca.view.adm;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class telaPagamentoMulta extends JFrame {

    public telaPagamentoMulta(double valorMulta) {
        setTitle("Pagamento de Multa");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BorderLayout(10, 10));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel painelCampos = new JPanel(new GridLayout(3, 2, 10, 10));

        JLabel lblValor = new JLabel("Valor da Multa:");
        JTextField txtValor = new JTextField(String.format("R$ %.2f", valorMulta));
        txtValor.setEditable(false);

        JLabel lblForma = new JLabel("Forma de Pagamento:");
        String[] opcoes = {"Dinheiro", "Cartão", "Pix"};
        JComboBox<String> comboForma = new JComboBox<>(opcoes);

        JLabel lblData = new JLabel("Data do Pagamento:");
        JTextField txtData = new JTextField(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        txtData.setEditable(false);

        painelCampos.add(lblValor);
        painelCampos.add(txtValor);
        painelCampos.add(lblForma);
        painelCampos.add(comboForma);
        painelCampos.add(lblData);
        painelCampos.add(txtData);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        JButton btnConfirmar = new JButton("Confirmar");
        JButton btnCancelar = new JButton("Cancelar");

        painelBotoes.add(btnConfirmar);
        painelBotoes.add(btnCancelar);

        painelPrincipal.add(painelCampos, BorderLayout.CENTER);
        painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);

        add(painelPrincipal);

        btnCancelar.addActionListener(e -> dispose());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new telaPagamentoMulta(12.50).setVisible(true);
        });
    }
}
