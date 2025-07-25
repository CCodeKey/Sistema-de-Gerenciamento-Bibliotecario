package Biblioteca.view.adm;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import Biblioteca.controller.ObraController;
import Excecoes.ObraExistenteException;

public class telaCadastroObra extends JFrame {

    public telaCadastroObra() {
        setTitle("Cadastro de Obra");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(6, 2, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

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
        painel.add(lblTipoObra);
        painel.add(comboTipoObra);
        painel.add(lblAnoPublicacao);
        painel.add(txtAnoPublicacao);
        painel.add(lblCodigoObra);
        painel.add(txtCodigoObra);
        painel.add(btnSalvar);
        painel.add(btnCancelar);

        add(painel);

        btnCancelar.addActionListener(e -> dispose());

        btnSalvar.addActionListener(e -> {
            // Ler os dados dos campos
            String titulo = txtTitulo.getText().trim();
            String autor = txtAutor.getText().trim();
            String tipo = ((String) comboTipoObra.getSelectedItem()).toLowerCase();
            String anoStr = txtAnoPublicacao.getText().trim();
            String codigoStr = txtCodigoObra.getText().trim();

            // Validação básica dos campos
            if (titulo.isEmpty() || autor.isEmpty() || anoStr.isEmpty() || codigoStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int ano;
            long codigo;
            try {
                ano = Integer.parseInt(anoStr);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Ano de publicação inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                codigo = Long.parseLong(codigoStr);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Código inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Aqui você pode gerar ou pedir a identificação se for necessário, no seu controller você tem o campo "identificacao"
            String identificacao = "ID" + codigo; // Exemplo simples de identificação (pode mudar conforme necessidade)

            // Criar o controller e tentar salvar a obra
            ObraController controller = new ObraController(codigo, titulo, autor, ano, tipo, identificacao);

            try {
                controller.novaObra();
                JOptionPane.showMessageDialog(this, "Obra cadastrada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                dispose(); // fecha a janela após salvar
            } catch (ObraExistenteException ex) {
                JOptionPane.showMessageDialog(this, "Já existe uma obra cadastrada com esse código.", "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao salvar a obra.", "Erro", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new telaCadastroObra().setVisible(true);
        });
    }
}
