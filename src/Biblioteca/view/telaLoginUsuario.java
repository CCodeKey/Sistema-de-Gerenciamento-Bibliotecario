package Biblioteca.view;

import javax.swing.*;
import java.awt.*;

public class telaLoginUsuario extends JFrame {
    public telaLoginUsuario(){
        setTitle("Login Usuário");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(5, 3, 10, 10));

        JLabel lblID =  new JLabel("ID: ");
        JTextField txtID = new JTextField();

        JLabel lblCPF = new JLabel("CPF: ");
        JTextField txtCPF = new JTextField();

        JLabel lblPassword = new JLabel("Senha: ");
        JTextField txtPassword = new JTextField();

        JButton btnSalvar = new JButton("Salvar");
        JButton btnCancelar = new JButton("Cancelar");

        painel.add(lblID);
        painel.add(txtID);
        painel.add(lblCPF);
        painel.add(txtCPF);
        painel.add(lblPassword);
        painel.add(txtPassword);
        painel.add(btnSalvar);
        painel.add(btnCancelar);

        add(painel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->{
            new telaLoginUsuario().setVisible(true);
        });
    }
}
