package Biblioteca.view;

import Biblioteca.controller.LoginController;
import Biblioteca.model.UsuarioLogin;
import Excecoes.UsuarioInformacoesInvalidas;
import Biblioteca.view.adm.telaMenuPrincipalA;
import Biblioteca.view.bibliotecario.telaMenuPrincipalB;
import Biblioteca.view.estagiario.telaMenuPrincipalE;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class telaLogin extends JFrame {
    private JTextField cpfFieldLogin;
    private JPasswordField senhaFieldLogin;

    private JTextField nomeField;
    private JTextField matriculaField;
    private JComboBox<String> tipoUsuarioComboBox;
    private JTextField telefoneField;
    private JTextField emailField;
    private JTextField cpfFieldCadastro;
    private JPasswordField senhaFieldCadastro;

    public telaLogin() {
        setTitle("Sistema de Login");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel loginPanel = new JPanel(new GridLayout(3, 6, 10, 10));
        loginPanel.setBorder(BorderFactory.createEmptyBorder(140, 20, 140, 20));
        cpfFieldLogin = new JTextField();
        senhaFieldLogin = new JPasswordField();
        JButton loginButton = new JButton("Login");

        loginPanel.add(new JLabel("CPF:"));
        loginPanel.add(cpfFieldLogin);
        loginPanel.add(new JLabel("Senha:"));
        loginPanel.add(senhaFieldLogin);
        loginPanel.add(new JLabel());
        loginPanel.add(loginButton);

        loginButton.addActionListener(this::loginAction);

        JPanel cadastroPanel = new JPanel(new GridLayout(8, 2, 10, 10));
        nomeField = new JTextField();
        matriculaField = new JTextField();
        tipoUsuarioComboBox = new JComboBox<>(new String[]{"bibliotecário", "estagiário"});
        telefoneField = new JTextField();
        emailField = new JTextField();
        cpfFieldCadastro = new JTextField();
        senhaFieldCadastro = new JPasswordField();
        JButton cadastrarButton = new JButton("Cadastrar");

        cadastroPanel.add(new JLabel("Nome:"));
        cadastroPanel.add(nomeField);
        cadastroPanel.add(new JLabel("Matrícula:"));
        cadastroPanel.add(matriculaField);
        cadastroPanel.add(new JLabel("Tipo de Usuário:"));
        cadastroPanel.add(tipoUsuarioComboBox);
        cadastroPanel.add(new JLabel("Telefone: +55"));
        cadastroPanel.add(telefoneField);
        cadastroPanel.add(new JLabel("Email:"));
        cadastroPanel.add(emailField);
        cadastroPanel.add(new JLabel("CPF:"));
        cadastroPanel.add(cpfFieldCadastro);
        cadastroPanel.add(new JLabel("Senha:"));
        cadastroPanel.add(senhaFieldCadastro);
        cadastroPanel.add(new JLabel());
        cadastroPanel.add(cadastrarButton);

        cadastrarButton.addActionListener(this::cadastroAction);

        tabbedPane.addTab("Login", loginPanel);
        tabbedPane.addTab("Cadastro", cadastroPanel);

        add(tabbedPane);
        setVisible(true);
    }

    private void loginAction(ActionEvent e) {
        String cpf = cpfFieldLogin.getText().trim();
        String senha = new String(senhaFieldLogin.getPassword()).trim();

        LoginController controller = new LoginController(cpf, senha);
        UsuarioLogin user = controller.logar();

        if (user != null) {
            String tipo = user.getTipoUsuario().toLowerCase();
            JOptionPane.showMessageDialog(this, "Login realizado com sucesso!\nBem-vindo(a), " + user.getNome());

            switch (tipo) {
                case "administrador":
                    JOptionPane.showMessageDialog(this, "Acesso - Administrador");
                    new telaMenuPrincipalA();
                    dispose();
                    break;
                case "bibliotecário":
                    JOptionPane.showMessageDialog(this, "Acesso - Bibliotecário.");
                    new telaMenuPrincipalB();
                    dispose();
                    break;
                case "estagiário":
                    JOptionPane.showMessageDialog(this, "Acesso - Estagiário.");
                    new telaMenuPrincipalE();
                    dispose();
                    break;
                default:
                    JOptionPane.showMessageDialog(this, "Tipo de usuário não reconhecido.");
            }

        } else {
            JOptionPane.showMessageDialog(this, "CPF ou senha inválidos!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cadastroAction(ActionEvent e) {
        try {
            String nome = nomeField.getText().trim();
            String matricula = matriculaField.getText().trim();
            String tipo = tipoUsuarioComboBox.getSelectedItem().toString().toLowerCase();
            String telefone = telefoneField.getText().trim();
            String email = emailField.getText().trim();
            String cpf = cpfFieldCadastro.getText().trim();
            String senha = new String(senhaFieldCadastro.getPassword()).trim();


            if (nome.isBlank() || matricula.isBlank() || tipo.isBlank() || telefone.isBlank() ||
                    email.isBlank() || cpf.isBlank() || senha.isBlank()) {
                JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!tipo.equals("bibliotecário") && !tipo.equals("estagiário")) {
                JOptionPane.showMessageDialog(this, "Tipo de usuário inválido! Use: bibliotecário ou estagiário.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!email.matches("^[\\w\\.-]+@[\\w\\.-]+\\.\\w{2,}$")) {
                JOptionPane.showMessageDialog(this, "Email inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!telefone.matches("\\d{10,11}")) {
                JOptionPane.showMessageDialog(this, "Telefone inválido! Deve conter apenas números (com DDD).", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            LoginController novaConta = new LoginController(cpf, senha, nome, matricula, tipo, telefone, email);
            novaConta.criarConta();;

            JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso!");

            nomeField.setText("");
            matriculaField.setText("");
            tipoUsuarioComboBox.setSelectedIndex(0);
            telefoneField.setText("");
            emailField.setText("");
            cpfFieldCadastro.setText("");
            senhaFieldCadastro.setText("");

        } catch (UsuarioInformacoesInvalidas ex) {
            JOptionPane.showMessageDialog(this, "Informações inválidas ou usuário já existe!", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar os dados!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}