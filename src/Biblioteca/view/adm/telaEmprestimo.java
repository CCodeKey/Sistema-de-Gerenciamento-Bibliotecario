package Biblioteca.view.adm;

import javax.swing.*;
import java.awt.*;

public class telaEmprestimo extends JFrame{
    public telaEmprestimo(){
        setTitle("Empréstimo");
        setSize(500,400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(7, 5, 10, 10));

        JLabel lblID =  new JLabel("ID: ");
        JTextField txtID = new JTextField();

        JLabel lblObra = new JLabel("Obra: ");
        JTextField txtObra = new JTextField();

        JLabel lblUsuario = new JLabel("Usuário: ");
        JTextField txtUsuario = new JTextField();

        JLabel lblDataDoEmprestimo= new JLabel("Data do empréstimo: ");
        JTextField txtDataDoEmprestimo = new JTextField();

        JLabel lblDataDeDevolucao = new JLabel("Data de devolução: ");
        JTextField txtDataDeDevolucao = new JTextField();

        JLabel lblDevolvido= new JLabel("Devolvido: ");
        JTextField txtDevolvido = new JTextField();

        JButton btnSalvar = new JButton("Salvar");
        JButton btnCancelar = new JButton("Cancelar");

        painel.add(lblID);
        painel.add(txtID);
        painel.add(lblObra);
        painel.add(txtObra);
        painel.add(lblUsuario);
        painel.add(txtUsuario);
        painel.add(lblDataDoEmprestimo);
        painel.add(txtDataDoEmprestimo);
        painel.add(lblDataDeDevolucao);
        painel.add(txtDataDeDevolucao);
        painel.add(btnSalvar);
        painel.add(btnCancelar);

        add(painel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new telaEmprestimo().setVisible(true);
        });
    }
}
