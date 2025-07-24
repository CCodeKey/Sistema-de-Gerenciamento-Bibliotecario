package Biblioteca.view.adm;

import javax.swing.*;
import java.awt.*;

import Biblioteca.dao.ObraDao;
import Biblioteca.model.Obra;

public class telaCadastroObra extends JFrame {

    public telaCadastroObra() {
        setTitle("Cadastro de Obra");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(6, 2, 10, 10));

        JLabel lblTitulo = new JLabel("Título:");
        JTextField txtTitulo = new JTextField();

        JLabel lblAutor = new JLabel("Autor:");
        JTextField txtAutor = new JTextField();

        JLabel lblTipoObra = new JLabel("Tipo da obra:");
        JComboBox<String> comboTipoObra = new JComboBox<>();
        comboTipoObra.addItem("Artigo");
        comboTipoObra.addItem("Livro");
        comboTipoObra.addItem("Revista");

        JLabel lblAnoPublicacao = new JLabel("Ano de Publicação:");
        JTextField txtAnoPublicacao = new JTextField();

        JLabel lblCodigoObra = new JLabel("Código:");
        JTextField txtCodigoObra = new JTextField();

        JButton btnSalvar = new JButton("Salvar");
        JButton btnCancelar = new JButton("Cancelar");

        painel.add(lblTitulo);
        painel.add(txtTitulo);
        painel.add(lblAutor);
        painel.add(txtAutor);
        painel.add(comboTipoObra);
        painel.add(lblAnoPublicacao);
        painel.add(txtAnoPublicacao);
        painel.add(lblCodigoObra);
        painel.add(txtCodigoObra);
        painel.add(btnSalvar);
        painel.add(btnCancelar);

        add(painel);

        btnCancelar.addActionListener(e -> dispose());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new telaCadastroObra().setVisible(true);
        });
    }
}
