package Biblioteca.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class telaCadastroUsuario extends JFrame {
    private JTextField txtNome;
    private JTextField txtMatricula;
    private JTextField txtTipoUsuario;
    private JTextField txtNumTelefone;
    private JTextField txtEmail;
    private JButton btnSalvar;
    private JButton btnCancelar;

    public telaCadastroUsuario() {
        setTitle("Cadastro de Usuário");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(7, 2, 10, 10));

        JLabel lblNome = new JLabel("Nome:");
        txtNome = new JTextField();

        JLabel lblMatricula = new JLabel("Matrícula:");
        txtMatricula = new JTextField();

        JLabel lblTipoUsuario = new JLabel("Tipo do usuário:");
        txtTipoUsuario = new JTextField();

        JLabel lblNumTelefone = new JLabel("Número de Telefone:");
        txtNumTelefone = new JTextField();

        JLabel lblEmail = new JLabel("Email:");
        txtEmail = new JTextField();

        btnSalvar = new JButton("Salvar");
        btnCancelar = new JButton("Cancelar");

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

        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = txtNome.getText().trim();
                String matricula = txtMatricula.getText().trim();
                String tipoUsuario = txtTipoUsuario.getText().trim().toLowerCase();
                String numTelefone = txtNumTelefone.getText().trim();
                String email = txtEmail.getText().trim();

                if (nome.isEmpty() || matricula.isEmpty() || tipoUsuario.isEmpty()
                        || numTelefone.isEmpty() || email.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos!");
                    return;
                }

                if (!(tipoUsuario.equals("aluno") || tipoUsuario.equals("professor") || tipoUsuario.equals("funcionário"))) {
                    JOptionPane.showMessageDialog(null, "Tipo de usuário deve ser: Aluno, Professor ou Funcionário.");
                    return;
                }

                if (!matricula.matches("\\d+")) {
                    JOptionPane.showMessageDialog(null, "A matrícula deve conter apenas números.");
                    return;
                }

                if (!numTelefone.matches("\\d{8,15}")) {
                    JOptionPane.showMessageDialog(null, "Número de telefone inválido.");
                    return;
                }

                if (!email.contains("@") || !email.contains(".")) {
                    JOptionPane.showMessageDialog(null, "Email inválido.");
                    return;
                }

                JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
                System.out.println("Nome: " + nome);
                System.out.println("Matrícula: " + matricula);
                System.out.println("Tipo de usuário: " + tipoUsuario);
                System.out.println("Telefone: " + numTelefone);
                System.out.println("Email: " + email);
            }
        });


    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new telaCadastroUsuario().setVisible(true);
        });
    }
}