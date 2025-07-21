package Biblioteca.view.adm;

import javax.swing.*;
import java.awt.*;

public class telaDevolucaoObra extends JFrame {
    public telaDevolucaoObra() {
        setTitle("Devolução de Obra");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(7, 2, 10, 10)); // 7 linhas, 2 colunas, espaçamento 10px

        JLabel lblMatricula = new JLabel("Matrícula do Usuário:");
        JTextField txtMatricula = new JTextField();

        JLabel lblCodigoObra = new JLabel("Código da Obra:");
        JTextField txtCodigoObra = new JTextField();

        JLabel lblDataDevolucao = new JLabel("Data da Devolução:");
        JTextField txtDataDevolucao = new JTextField();

        JLabel lblStatus = new JLabel("Status da Devolução:");
        JTextField txtStatus = new JTextField();
        txtStatus.setEditable(false); // só exibição

        JLabel lblMulta = new JLabel("Multa (se houver):");
        JTextField txtMulta = new JTextField();
        txtMulta.setEditable(false); // só exibição

        JButton btnCalcular = new JButton("Calcular");
        JButton btnRegistrarPagamento = new JButton("Registrar Pagamento");
        JButton btnCancelar = new JButton("Cancelar");

        painel.add(lblMatricula);
        painel.add(txtMatricula);

        painel.add(lblCodigoObra);
        painel.add(txtCodigoObra);

        painel.add(lblDataDevolucao);
        painel.add(txtDataDevolucao);

        painel.add(lblStatus);
        painel.add(txtStatus);

        painel.add(lblMulta);
        painel.add(txtMulta);

        painel.add(btnCalcular);
        painel.add(btnRegistrarPagamento);

        // última linha só com botão cancelar centralizado
        JPanel painelCancelar = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelCancelar.add(btnCancelar);

        add(painel, BorderLayout.CENTER);
        add(painelCancelar, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new telaDevolucaoObra().setVisible(true);
        });
    }
}
