package Biblioteca.view.adm;

import javax.swing.*;
import java.awt.*;

public class telaCadastroObra extends JFrame{
    public telaCadastroObra(){
        setTitle("Cadastro de Obra");
        setSize(500,400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(7, 5, 10, 10));

        JLabel lblCodigo =  new JLabel("Código ");
        JTextField txtCodigo = new JTextField();

        JLabel lblTitulo = new JLabel("Titulo: ");
        JTextField txtTitulo = new JTextField();

        JLabel lblAutor = new JLabel("Autor: ");
        JTextField txtAutor = new JTextField();

        JLabel lblAnoPublicacao = new JLabel("Ano de Publicação: ");
        JTextField txtAnoPublicacao = new JTextField();

        JLabel lblStatus = new JLabel("Status: ");
        JTextField txtStatus = new JTextField();

        JButton btnSalvar = new JButton("Salvar");
        JButton btnCancelar = new JButton("Cancelar");

        painel.add(lblCodigo);
        painel.add(txtCodigo);
        painel.add(lblTitulo);
        painel.add(txtTitulo);
        painel.add(lblAutor);
        painel.add(txtAutor);
        painel.add(lblAnoPublicacao);
        painel.add(txtAnoPublicacao);
        painel.add(lblStatus);
        painel.add(txtStatus);
        painel.add(btnSalvar);
        painel.add(btnCancelar);

        add(painel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->{
            new telaCadastroObra().setVisible(true);
        });
    }
}
