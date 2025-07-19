package Biblioteca.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class telaMenuPrincipal extends JFrame {

    public telaMenuPrincipal() {
        setTitle("Menu Principal");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(16, 1, 10, 10));

        JButton btnCadastrarUsuario = new JButton("1. Cadastrar Usuário");
        JButton btnEditarUsuario = new JButton("2. Editar Usuário");
        JButton btnExcluirUsuario = new JButton("3. Excluir Usuário");
        JButton btnCadastrarObra = new JButton("4. Cadastrar Obra");
        JButton btnEmprestimo = new JButton("5. Realizar Empréstimo");
        JButton btnDevolucao = new JButton("6. Devolução de Obra");
        JButton btnPagarMulta = new JButton("7. Pagar Devolução Pendente");
        JButton btnListarObras = new JButton("8. Listar Obras");
        JButton btnBuscarTitulo = new JButton("9. Buscar Obra por Título");
        JButton btnBuscarAutor = new JButton("10. Buscar Obra por Autor");
        JButton btnBuscarTipo = new JButton("11. Buscar Obra por Tipo");
        JButton btnRelatorioMes = new JButton("12. Relatório de Empréstimos do Mês");
        JButton btnRelatorioObras = new JButton("13. Relatório Obras + Emprestadas");
        JButton btnRelatorioUsuarios = new JButton("14. Relatório Usuários + Atrasos");
        JButton btnLogout = new JButton("15. Logout");

        painel.add(btnCadastrarUsuario);
        painel.add(btnEditarUsuario);
        painel.add(btnExcluirUsuario);
        painel.add(btnCadastrarObra);
        painel.add(btnEmprestimo);
        painel.add(btnDevolucao);
        painel.add(btnPagarMulta);
        painel.add(btnListarObras);
        painel.add(btnBuscarTitulo);
        painel.add(btnBuscarAutor);
        painel.add(btnBuscarTipo);
        painel.add(btnRelatorioMes);
        painel.add(btnRelatorioObras);
        painel.add(btnRelatorioUsuarios);
        painel.add(btnLogout);

        add(painel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new telaMenuPrincipal().setVisible(true);
        });
    }
}
