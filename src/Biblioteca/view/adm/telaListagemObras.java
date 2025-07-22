package Biblioteca.view.adm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

import Biblioteca.dao.ObraDao;
import Biblioteca.model.Obra;

public class telaListagemObras extends JFrame {

    private JTextField txtBusca;
    private JComboBox<String> cmbFiltro;
    private JTextArea areaResultado;
    private ObraDao obraDao;

    public telaListagemObras() {
        setTitle("Listagem de Obras");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        obraDao = new ObraDao();

        JPanel painel = new JPanel(new BorderLayout(10, 10));
        JPanel painelBusca = new JPanel();

        txtBusca = new JTextField(20);
        cmbFiltro = new JComboBox<>(new String[]{"Título", "Autor", "Tipo"});
        JButton btnBuscar = new JButton("Buscar");

        painelBusca.add(new JLabel("Buscar por:"));
        painelBusca.add(cmbFiltro);
        painelBusca.add(txtBusca);
        painelBusca.add(btnBuscar);

        areaResultado = new JTextArea();
        areaResultado.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaResultado);

        painel.add(painelBusca, BorderLayout.NORTH);
        painel.add(scrollPane, BorderLayout.CENTER);

        add(painel);

        // Ação do botão de buscar
        btnBuscar.addActionListener((ActionEvent e) -> buscarObras());
    }

    private void buscarObras() {
        String filtro = cmbFiltro.getSelectedItem().toString();
        String valor = txtBusca.getText().trim();
        areaResultado.setText("");

        if (valor.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Digite um valor para buscar.");
            return;
        }

        if (filtro.equals("Título")) {
            Obra obra = obraDao.buscarPorTitulo(valor);
            if (obra != null) {
                areaResultado.append(obra.toString() + "\n");
            } else {
                areaResultado.append("Nenhuma obra encontrada com esse título.\n");
            }

        } else if (filtro.equals("Autor")) {
            List<Obra> obras = obraDao.buscarPorAutor(valor);
            if (obras != null && !obras.isEmpty()) {
                for (Obra o : obras) {
                    areaResultado.append(o.toString() + "\n");
                }
            } else {
                areaResultado.append("Nenhuma obra encontrada com esse autor.\n");
            }

        } else if (filtro.equals("Tipo")) {
            List<Obra> obras = obraDao.buscarPorTipo(valor);
            if (obras != null && !obras.isEmpty()) {
                for (Obra o : obras) {
                    areaResultado.append(o.toString() + "\n");
                }
            } else {
                areaResultado.append("Nenhuma obra encontrada com esse tipo.\n");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new telaListagemObras().setVisible(true);
        });
    }
}
