package Biblioteca.view.adm;

import javax.swing.*;

import Biblioteca.controller.LoginController;
import Excecoes.UsuarioInformacoesInvalidas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class telaCadastroAdmin extends JFrame {

    private JTextField txtNome, txtMatricula, txtTipoUsuario, txtTelefone, txtEmail, txtCpf;
    private JPasswordField txtSenha;
    private JButton btnCadastrar;

    public telaCadastroAdmin() {
        setTitle("Cadastro de Administrador");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        JPanel painel = new JPanel(new GridLayout(8, 2, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        txtNome = new JTextField();
        txtMatricula = new JTextField();
        txtTipoUsuario = new JTextField("Administrador"); // Padrão
        txtTelefone = new JTextField();
        txtEmail = new JTextField();
        txtCpf = new JTextField();
        txtSenha = new JPasswordField();

        painel.add(new JLabel("Nome:"));
        painel.add(txtNome);
        painel.add(new JLabel("Matrícula:"));
        painel.add(txtMatricula);
        painel.add(new JLabel("Tipo de Usuário:"));
        painel.add(txtTipoUsuario);
        txtTipoUsuario.setEditable(false);
        painel.add(new JLabel("Telefone:"));
        painel.add(txtTelefone);
        painel.add(new JLabel("Email:"));
        painel.add(txtEmail);
        painel.add(new JLabel("CPF:"));
        painel.add(txtCpf);
        painel.add(new JLabel("Senha:"));
        painel.add(txtSenha);

        btnCadastrar = new JButton("Cadastrar");
        painel.add(btnCadastrar);

        painel.add(new JLabel(""));

        add(painel);

        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarAdm();
            }
        });
    }

    private void cadastrarAdm() {
        String nome = txtNome.getText().trim();
        String matricula = txtMatricula.getText().trim();
        String tipoUsuario = txtTipoUsuario.getText().trim();
        String telefone = txtTelefone.getText().trim();
        String email = txtEmail.getText().trim();
        String cpf = txtCpf.getText().trim();
        String senha = new String(txtSenha.getPassword()).trim();

        if (nome.isEmpty() || matricula.isEmpty() || tipoUsuario.isEmpty() || telefone.isEmpty()
                || email.isEmpty() || cpf.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        LoginController controller = new LoginController(cpf, senha, nome, matricula, tipoUsuario, telefone, email);

        try {
            controller.criarContaADM();
            JOptionPane.showMessageDialog(this, "Administrador cadastrado com sucesso!");
            dispose();
        } catch (UsuarioInformacoesInvalidas ex) {
            JOptionPane.showMessageDialog(this, "Usuário já existe com CPF, matrícula, email ou telefone informado.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar os dados. Verifique se o arquivo está acessível.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new telaCadastroAdmin().setVisible(true));
    }
}
