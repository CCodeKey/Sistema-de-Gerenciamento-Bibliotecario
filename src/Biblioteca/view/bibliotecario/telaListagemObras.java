package Biblioteca.view.bibliotecario;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import Biblioteca.controller.ObraController;
import Biblioteca.model.Obra;
import Excecoes.ObraNaoExisteException;

public class telaListagemObras extends JFrame {
    private JTextArea txtDisponiveis;
    private JTextArea txtEmprestadas;
    private JTextArea txtAtrasadas;

    public telaListagemObras() {
        setTitle("Listagem de Obras");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
        carregarListas();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        JPanel painelListas = new JPanel(new GridLayout(1, 3, 10, 10));

        txtDisponiveis = new JTextArea();
        txtDisponiveis.setEditable(false);
        txtDisponiveis.setBorder(BorderFactory.createTitledBorder("Obras Disponíveis"));

        txtEmprestadas = new JTextArea();
        txtEmprestadas.setEditable(false);
        txtEmprestadas.setBorder(BorderFactory.createTitledBorder("Obras Ocupadas"));

        txtAtrasadas = new JTextArea();
        txtAtrasadas.setEditable(false);
        txtAtrasadas.setBorder(BorderFactory.createTitledBorder("Obras Atrasadas"));

        painelListas.add(new JScrollPane(txtDisponiveis));
        painelListas.add(new JScrollPane(txtEmprestadas));
        painelListas.add(new JScrollPane(txtAtrasadas));

        add(painelListas, BorderLayout.CENTER);
    }

    private void carregarListas() {
        ObraController controller = new ObraController();

        try {
            List<Obra> disponiveis = controller.listarObrasDisponiveis();
            for (Obra o : disponiveis) {
                txtDisponiveis.append(formatarObra(o));
            }
        } catch (ObraNaoExisteException e) {
            txtDisponiveis.setText("Nenhuma obra disponível.");
        }

        try {
            List<Obra> ocupadas = controller.listarObrasEmprestadas();
            for (Obra o : ocupadas) {
                txtEmprestadas.append(formatarObra(o));
            }
        } catch (ObraNaoExisteException e) {
            txtEmprestadas.setText("Nenhuma obra ocupada.");
        }

        try {
            List<Obra> atrasadas = controller.listarObrasAtrasadas();
            for (Obra o : atrasadas) {
                txtAtrasadas.append(formatarObra(o));
            }
        } catch (ObraNaoExisteException e) {
            txtAtrasadas.setText("Nenhuma obra atrasada.");
        }
    }

    private String formatarObra(Obra o) {
        return String.format("Código: %d\nTítulo: %s\nAutor: %s\nAno: %d\nTipo: %s\n\n",
                o.getCodigo(), o.getTitulo(), o.getAutor(), o.getAnoDePublicacao(), o.getClass().getSimpleName());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new telaListagemObras().setVisible(true);
        });
    }
}
