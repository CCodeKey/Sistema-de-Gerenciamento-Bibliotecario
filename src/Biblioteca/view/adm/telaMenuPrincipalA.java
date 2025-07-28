package Biblioteca.view.adm;

import Biblioteca.view.Login;
import Biblioteca.view.telaLogin;

import javax.swing.*;
import java.awt.*;

public class telaMenuPrincipalA extends JFrame {
    private JButton btnCadastrarUsuario;
    private JButton btnEditarUsuario;
    private JButton btnExcluirUsuario;
    private JButton btnCadastrarObra;
    private JButton btnCadastrarUsuarioAdministrador;
    private JButton btnLogout;

    public telaMenuPrincipalA() {
        setTitle("Menu Admin");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(6, 1, 5, 5));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        btnCadastrarUsuario = new JButton("Cadastrar Usuário");
        btnEditarUsuario = new JButton("Editar Usuário");
        btnExcluirUsuario = new JButton("Excluir Usuário");
        btnCadastrarObra = new JButton("Cadastrar Obra");
        btnCadastrarUsuarioAdministrador = new JButton("Cadastrar Usuário Administrador");
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> {
            Login.usuarioSessao = "";
            dispose();
            new telaLogin();
        });


        painel.add(btnCadastrarUsuario);
        painel.add(btnEditarUsuario);
        painel.add(btnExcluirUsuario);
        painel.add(btnCadastrarObra);
        painel.add(btnCadastrarUsuarioAdministrador);
        painel.add(logoutButton);

        add(painel);

        btnCadastrarUsuario.addActionListener(e -> {
            new telaCadastroUsuario().setVisible(true);
        });
        btnEditarUsuario.addActionListener(e -> {
            new telaEdicaoUsuario().setVisible(true);
        });
        btnExcluirUsuario.addActionListener(e -> {;
            new telaExcluirUsuario().setVisible(true);
        });
        btnCadastrarObra.addActionListener(e -> {
            new telaCadastroObra().setVisible(true);
        });
        btnCadastrarUsuarioAdministrador.addActionListener(e -> {
            new telaCadastroAdmin().setVisible(true);
        });
    }
}
