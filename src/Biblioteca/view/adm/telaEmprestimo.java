package Biblioteca.view.adm;

import javax.swing.*;
import java.awt.*;

public class telaEmprestimo extends JFrame {

    public telaEmprestimo() {
        setTitle("Empréstimo de Obra");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(5, 2, 10, 10));

        JLabel lblMatricula = new JLabel("Matrícula do Usuário:");
        JTextField txtMatricula = new JTextField();

        JLabel lblTipoObra = new JLabel("Tipo da Obra:");
        JComboBox<String> comboTipoObra = new JComboBox<>();
        comboTipoObra.addItem("Artigo");
        comboTipoObra.addItem("Livro");
        comboTipoObra.addItem("Revista");

        JLabel lblCodigoObra = new JLabel("Código da Obra:");
        JTextField txtCodigoObra = new JTextField();

        JButton btnSalvar = new JButton("Salvar");
        JButton btnCancelar = new JButton("Cancelar");

        painel.add(lblMatricula);
        painel.add(txtMatricula);
        painel.add(lblTipoObra);
        painel.add(comboTipoObra);
        painel.add(lblCodigoObra);
        painel.add(txtCodigoObra);
        painel.add(btnSalvar);
        painel.add(btnCancelar);

        add(painel);

        btnCancelar.addActionListener(e -> dispose());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new telaEmprestimo().setVisible(true);
        });
    }
}
