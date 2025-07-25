package Biblioteca.view.adm;

import Biblioteca.controller.EmprestimoController;
import Biblioteca.dao.ObraDao;
import Biblioteca.dao.UsuarioDao;
import Biblioteca.model.Obra;
import Biblioteca.model.Usuario;
import Excecoes.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class telaEmprestimo extends JFrame {

    private JTextField matriculaField;
    private JTextField tipoObraField;
    private JTextField codigoObraField;
    private JButton emprestarButton;

    private UsuarioDao usuarioDao;
    private ObraDao obraDao;
    private EmprestimoController emprestimoController;

    public telaEmprestimo() {
        super("Empréstimo de Obra");

        usuarioDao = new UsuarioDao();
        obraDao = new ObraDao();
        emprestimoController = new EmprestimoController();

        setLayout(null);

        JLabel matriculaLabel = new JLabel("Matrícula do Usuário:");
        matriculaLabel.setBounds(20, 20, 150, 25);
        add(matriculaLabel);

        matriculaField = new JTextField();
        matriculaField.setBounds(180, 20, 150, 25);
        add(matriculaField);

        JLabel tipoObraLabel = new JLabel("Tipo da Obra:");
        tipoObraLabel.setBounds(20, 60, 150, 25);
        add(tipoObraLabel);

        tipoObraField = new JTextField();
        tipoObraField.setBounds(180, 60, 150, 25);
        add(tipoObraField);

        JLabel codigoObraLabel = new JLabel("Código da Obra:");
        codigoObraLabel.setBounds(20, 100, 150, 25);
        add(codigoObraLabel);

        codigoObraField = new JTextField();
        codigoObraField.setBounds(180, 100, 150, 25);
        add(codigoObraField);

        emprestarButton = new JButton("Realizar Empréstimo");
        emprestarButton.setBounds(100, 150, 180, 30);
        add(emprestarButton);

        emprestarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarEmprestimo();
            }
        });

        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void realizarEmprestimo() {
        try {
            String matricula = matriculaField.getText().trim();
            String tipoObra = tipoObraField.getText().trim().toLowerCase();
            long codigoObra = Long.parseLong(codigoObraField.getText());

            Usuario usuario = usuarioDao.buscarUsuarioPorMatricula(matricula);
            if (usuario == null) {
                JOptionPane.showMessageDialog(this, "Usuário não encontrado.");
                return;
            }

            Obra obra = obraDao.buscarObraPorCodigo(codigoObra, tipoObra);
            if (obra == null) {
                JOptionPane.showMessageDialog(this, "Obra não encontrada.");
                return;
            }

            if (!obra.getStatus().equalsIgnoreCase("disponivel")) {
                JOptionPane.showMessageDialog(this, "Obra não está disponível.");
                return;
            }

            EmprestimoController emprestimoController = new EmprestimoController(usuario, obra);
            emprestimoController.realizarEmprestimo();

            JOptionPane.showMessageDialog(this, "Empréstimo realizado com sucesso!");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Campos de matrícula e código devem ser numéricos.");
        } catch (UsuarioNaoExisteException | ObraNaoExisteException | ObraNaoDisponivelException ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar empréstimo: " + ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro inesperado: " + ex.getMessage());
        }
    }


    // Main para teste isolado
    public static void main(String[] args) {
        SwingUtilities.invokeLater(telaEmprestimo::new);
    }
}
