package Biblioteca.view.bibliotecario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import Biblioteca.controller.ObraController;
import Biblioteca.model.Obra;
import Excecoes.ObraNaoEncontradaException;

public class telaListagemObraAutor extends JFrame {
    private JTextField txtAutor;
    private JTextArea txtResultado;

    public telaListagemObraAutor() {
        setTitle("Listagem de Obras por Autor");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        JPanel painelBusca = new JPanel();
        painelBusca.add(new JLabel("Autor:"));
        txtAutor = new JTextField(20);
        painelBusca.add(txtAutor);
        JButton btnBuscar = new JButton("Buscar");
        painelBusca.add(btnBuscar);

        add(painelBusca, BorderLayout.NORTH);

        txtResultado = new JTextArea();
        txtResultado.setEditable(false);
        add(new JScrollPane(txtResultado), BorderLayout.CENTER);

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarPorAutor();
            }
        });
    }

    private void buscarPorAutor() {
        txtResultado.setText("");
        ObraController controller = new ObraController();
        String autor = txtAutor.getText().trim();

        try {
            List<Obra> obras = controller.buscarPorAutor(autor);
            for (Obra o : obras) {
                txtResultado.append(formatarObra(o));
            }
        } catch (ObraNaoEncontradaException e) {
            txtResultado.setText("Nenhuma obra encontrada com esse autor.");
        }
    }

    private String formatarObra(Obra o) {
        return String.format("Código: %d\nTítulo: %s\nAutor: %s\nAno: %d\nTipo: %s\n\n",
                o.getCodigo(), o.getTitulo(), o.getAutor(), o.getAnoDePublicacao(), o.getClass().getSimpleName());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new telaListagemObraAutor().setVisible(true));
    }
}
