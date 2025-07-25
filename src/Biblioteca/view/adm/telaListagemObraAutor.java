package Biblioteca.view.adm;

import javax.swing.*;
import java.awt.*;

public class telaListagemObraAutor extends JFrame {
    public telaListagemObraAutor() {
        setTitle("Listagem de Obras por Autor");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] colunas = {"Autor", "Título", "Tipo", "Ano"};
        Object[][] dados = {}; // Dados serão preenchidos depois

        JTable tabela = new JTable(dados, colunas);
        JScrollPane scrollPane = new JScrollPane(tabela);

        JButton btnFechar = new JButton("Fechar");
        btnFechar.addActionListener(e -> dispose());

        JPanel painel = new JPanel(new BorderLayout());
        painel.add(scrollPane, BorderLayout.CENTER);
        painel.add(btnFechar, BorderLayout.SOUTH);

        add(painel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new telaListagemObraAutor().setVisible(true);
        });
    }
}