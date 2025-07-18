package Biblioteca.view;

import javax.swing.*;
import java.awt.*;

public class telaCadastroUsuario extends JFrame {
    public telaCadastroUsuario(){
        setTitle("Cadastro de Usuário");
        setSize(500,400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(7, 5, 10, 10));

        JLabel lblNome =  new JLabel("Nome: ");
        JTextField txtNome = new JTextField();

        JLabel lblMatricula = new JLabel("Matrícula: ");
        JTextField txtMatricula = new JTextField();

        JLabel lblTipoUsuario = new JLabel("Tipo do usuário: ");
        JTextField txtTipoUsuario = new JTextField();

        JLabel lblNumTelefone = new JLabel("Número de Telefone: ");
        JTextField txtNumTelefone = new JTextField();

        JLabel lblEmail = new JLabel("Email: ");
        JTextField txtEmail = new JTextField();

        JButton btnSalvar = new JButton("Salvar");
        JButton btnCancelar = new JButton("Cancelar");

        painel.add(lblNome);
        painel.add(txtNome);
        painel.add(lblMatricula);
        painel.add(txtMatricula);
        painel.add(lblTipoUsuario);
        painel.add(txtTipoUsuario);
        painel.add(lblNumTelefone);
        painel.add(txtNumTelefone);
        painel.add(lblEmail);
        painel.add(txtEmail);
        painel.add(btnSalvar);
        painel.add(btnCancelar);

        add(painel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new telaCadastroUsuario().setVisible(true);
        });
    }
}
