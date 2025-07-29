package Biblioteca.view.bibliotecario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

import Biblioteca.controller.ObraController;
import Biblioteca.model.Obra;
import Excecoes.ObraNaoEncontradaException;

public class telaListagemObraTipo extends JFrame {
    private JTextField txtTipo;
    private JTextArea txtResultado;

    public telaListagemObraTipo() {
        setTitle("Listagem de Obras por Tipo");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        JPanel painelBusca = new JPanel();
        painelBusca.add(new JLabel("Tipo (Artigo, Livro, Revista):"));
        txtTipo = new JTextField(20);
        painelBusca.add(txtTipo);
        JButton btnBuscar = new JButton("Buscar");
        painelBusca.add(btnBuscar);

        add(painelBusca, BorderLayout.NORTH);

        txtResultado = new JTextArea();
        txtResultado.setEditable(false);
        add(new JScrollPane(txtResultado), BorderLayout.CENTER);

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarPorTipo();
            }
        });
    }

    private void buscarPorTipo() {
        txtResultado.setText("");
        ObraController controller = new ObraController();
        String tipo = txtTipo.getText().trim();

        try {
            List<Obra> obras = controller.buscarPorTipo(tipo);
            for (Obra o : obras) {
                txtResultado.append(formatarObra(o));
            }
        } catch (ObraNaoEncontradaException e) {
            txtResultado.setText("Nenhuma obra encontrada com esse tipo.");
        }
    }

    private String formatarObra(Obra o) {
        return String.format("Código: %d\nTítulo: %s\nAutor: %s\nAno: %d\nTipo: %s\n\n",
                o.getCodigo(), o.getTitulo(), o.getAutor(), o.getAnoDePublicacao(), o.getClass().getSimpleName());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new telaListagemObraTipo().setVisible(true));
    }
}
