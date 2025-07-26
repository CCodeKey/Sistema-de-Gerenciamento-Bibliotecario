package Biblioteca.view.adm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import Biblioteca.controller.ObraController;
import Biblioteca.model.Obra;
import Excecoes.ObraNaoEncontradaException;
import Excecoes.ObraNaoExisteException;

public class telaListagemObraTitulo extends JFrame {
    private JTextField txtTitulo;
    private JTextArea txtResultado;

    public telaListagemObraTitulo() {
        setTitle("Listagem de Obras por Título");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        JPanel painelBusca = new JPanel();
        painelBusca.add(new JLabel("Título:"));
        txtTitulo = new JTextField(20);
        painelBusca.add(txtTitulo);
        JButton btnBuscar = new JButton("Buscar");
        painelBusca.add(btnBuscar);

        add(painelBusca, BorderLayout.NORTH);

        txtResultado = new JTextArea();
        txtResultado.setEditable(false);
        add(new JScrollPane(txtResultado), BorderLayout.CENTER);

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarPorTitulo();
            }
        });
    }

    private void buscarPorTitulo() {
        txtResultado.setText("");
        ObraController controller = new ObraController();
        String titulo = txtTitulo.getText().trim();

        try {
            List<Obra> obras = controller.buscarPorTitulo(titulo);
            for (Obra o : obras) {
                txtResultado.append(formatarObra(o));
            }
        } catch (ObraNaoEncontradaException e) {
            txtResultado.setText("Nenhuma obra encontrada com esse título.");
        }
    }

    private String formatarObra(Obra o) {
        return String.format("Código: %d\nTítulo: %s\nAutor: %s\nAno: %d\nTipo: %s\n\n",
                o.getCodigo(), o.getTitulo(), o.getAutor(), o.getAnoDePublicacao(), o.getClass().getSimpleName());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new telaListagemObraTitulo().setVisible(true));
    }
}
