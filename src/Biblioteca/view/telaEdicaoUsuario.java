package Biblioteca.view;

import javax.swing.*;
import java.awt.*;
import Biblioteca.controller.UsuarioController;
import Biblioteca.model.Usuario;
import javax.swing.JOptionPane;
import Excecoes.UsuarioNaoExisteException;

public class telaEdicaoUsuario extends JFrame {
    private JTextField txtMatriculaBusca;
    private JTextField txtNome;
    private JTextField txtTelefone;
    private JTextField txtEmail;
    private JButton btnBuscar;
    private JButton btnSalvar;
    private JButton btnCancelar;

    public telaEdicaoUsuario() {
        setTitle("Editar Usuário");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        inicializarComponentes();

        setVisible(true);
    }

    private void inicializarComponentes() {
        JPanel painel = new JPanel(new GridLayout(6, 2, 10, 10));

        painel.add(new JLabel("Matrícula para busca:"));
        txtMatriculaBusca = new JTextField();
        painel.add(txtMatriculaBusca);

        btnBuscar = new JButton("Buscar");
        painel.add(btnBuscar);
        painel.add(new JLabel("")); // espaço vazio

        painel.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        txtNome.setEnabled(false);
        painel.add(txtNome);

        painel.add(new JLabel("Telefone:"));
        txtTelefone = new JTextField();
        txtTelefone.setEnabled(false);
        painel.add(txtTelefone);

        painel.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        txtEmail.setEnabled(false);
        painel.add(txtEmail);

        btnSalvar = new JButton("Salvar");
        btnSalvar.setEnabled(false);
        btnCancelar = new JButton("Cancelar");

        painel.add(btnSalvar);
        painel.add(btnCancelar);

        add(painel);

        configurarAcoes();
    }

    private void configurarAcoes() {
        btnBuscar.addActionListener(e -> {
            String matricula = txtMatriculaBusca.getText().trim();

            if (matricula.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Informe a matrícula para busca.");
                return;
            }

            UsuarioController usuarioCtrl = new UsuarioController();

            try {
                Usuario usuario = usuarioCtrl.buscarUsuarioPorMatricula(matricula);

                if (usuario != null) {
                    txtNome.setText(usuario.getNome());
                    txtTelefone.setText(usuario.getTelefone());
                    txtEmail.setText(usuario.getEmail());

                    txtNome.setEnabled(true);
                    txtTelefone.setEnabled(true);
                    txtEmail.setEnabled(true);
                    btnSalvar.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Usuário não encontrado.");
                }
            } catch (UsuarioNaoExisteException ex) {
                JOptionPane.showMessageDialog(this, "Usuário não encontrado: " + ex.getMessage());
            }
        });

        btnSalvar.addActionListener(e -> {
            String matricula = txtMatriculaBusca.getText().trim();
            String nome = txtNome.getText().trim();
            String telefone = txtTelefone.getText().trim();
            String email = txtEmail.getText().trim();

            if (nome.isEmpty() || telefone.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
                return;
            }

            UsuarioController usuarioCtrl = new UsuarioController();
            try {
                usuarioCtrl.editarUsuario(matricula, nome, telefone, email);
                JOptionPane.showMessageDialog(this, "Usuário atualizado com sucesso.");
                limparCampos();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
            }
        });

        btnCancelar.addActionListener(e -> limparCampos());
    }

    private void limparCampos() {
        txtMatriculaBusca.setText("");
        txtNome.setText("");
        txtTelefone.setText("");
        txtEmail.setText("");
        txtNome.setEnabled(false);
        txtTelefone.setEnabled(false);
        txtEmail.setEnabled(false);
        btnSalvar.setEnabled(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new telaEdicaoUsuario());
    }
}