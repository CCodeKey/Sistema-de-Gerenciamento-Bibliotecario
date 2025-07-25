package Biblioteca.view.adm;

import javax.swing.*;
import java.awt.*;

public class telaListagemObraTitulo extends JFrame {
    public telaListagemObraTitulo() {
        setTitle("Listagem de Obras por Título");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] colunas = {"Título", "Autor", "Tipo", "Ano"};
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
            new telaListagemObraTitulo().setVisible(true);
        });
    }
}