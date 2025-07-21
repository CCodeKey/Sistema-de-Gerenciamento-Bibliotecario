package Biblioteca.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class telaMenuPrincipal extends JFrame {
    private JButton btnCadastrarUsuario;
    private JButton btnEditarUsuario;
    private JButton btnExcluirUsuario;
    private JButton btnCadastrarObra;
    private JButton btnRealizarEmprestimo;
    private JButton btnDevolucaoObra;
    private JButton btnPagarMulta;
    private JButton btnListarObras;
    private JButton btnBuscarObraTitulo;
    private JButton btnBuscarObraAutor;
    private JButton btnBuscarObraTipo;
    private JButton btnRelatorioEmprestimosMes;
    private JButton btnRelatorioObrasMaisEmprestadas;
    private JButton btnRelatorioUsuariosAtrasados;
    private JButton btnLogout;

    public telaMenuPrincipal() {
        setTitle("Menu Principal");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(15, 1, 5, 5));

        btnCadastrarUsuario = new JButton("1. Cadastrar Usuário");
        btnEditarUsuario = new JButton("2. Editar Usuário");
        btnExcluirUsuario = new JButton("3. Excluir Usuário");
        btnCadastrarObra = new JButton("4. Cadastrar Obra");
        btnRealizarEmprestimo = new JButton("5. Realizar Empréstimo");
        btnDevolucaoObra = new JButton("6. Devolução de Obra");
        btnPagarMulta = new JButton("7. Pagar Devolução Pendente");
        btnListarObras = new JButton("8. Listar Obras");
        btnBuscarObraTitulo = new JButton("9. Buscar Obra por Título");
        btnBuscarObraAutor = new JButton("10. Buscar Obra por Autor");
        btnBuscarObraTipo = new JButton("11. Buscar Obra por Tipo");
        btnRelatorioEmprestimosMes = new JButton("12. Relatório de Empréstimos do Mês");
        btnRelatorioObrasMaisEmprestadas = new JButton("13. Relatório de Obras Mais Emprestadas");
        btnRelatorioUsuariosAtrasados = new JButton("14. Relatório de Usuários Atrasados");
        btnLogout = new JButton("15. Logout");

        painel.add(btnCadastrarUsuario);
        painel.add(btnEditarUsuario);
        painel.add(btnExcluirUsuario);
        painel.add(btnCadastrarObra);
        painel.add(btnRealizarEmprestimo);
        painel.add(btnDevolucaoObra);
        painel.add(btnPagarMulta);
        painel.add(btnListarObras);
        painel.add(btnBuscarObraTitulo);
        painel.add(btnBuscarObraAutor);
        painel.add(btnBuscarObraTipo);
        painel.add(btnRelatorioEmprestimosMes);
        painel.add(btnRelatorioObrasMaisEmprestadas);
        painel.add(btnRelatorioUsuariosAtrasados);
        painel.add(btnLogout);

        add(painel);

        btnCadastrarUsuario.addActionListener(e -> {
            new telaCadastroUsuario().setVisible(true);
        });
        btnEditarUsuario.addActionListener(e -> {
            new telaEdicaoUsuario().setVisible(true);
        });
        btnExcluirUsuario.addActionListener(e -> {;
            new telaExcluirUsuario().setVisible(true);
        });
        btnCadastrarObra.addActionListener(e -> {
            new telaCadastroObra().setVisible(true);
        });
        btnRealizarEmprestimo.addActionListener(e -> {
            new telaEmprestimo().setVisible(true);
        });
        btnDevolucaoObra.addActionListener(e -> {
            new telaDevolucaoObra().setVisible(true);
        });
        btnPagarMulta.addActionListener(e -> {
            new telaDevolucaoObra().setVisible(true);
        });
        btnListarObras.addActionListener(e -> {
            new telaListagemObras().setVisible(true);
        });
        btnBuscarObraTitulo.addActionListener(e -> {
           new telaListagemObras().setVisible(true);
        });
        btnBuscarObraAutor.addActionListener(e -> {
          new telaListagemObras().setVisible(true);
        });
        btnBuscarObraTipo.addActionListener(e -> {
            new telaListagemObras().setVisible(true);
        });
        btnRelatorioEmprestimosMes.addActionListener(e -> {
            new telaRelatorios().setVisible(true);
        });
        btnRelatorioObrasMaisEmprestadas.addActionListener(e -> {
            new telaRelatorios().setVisible(true);
        });
        btnRelatorioUsuariosAtrasados.addActionListener(e -> {
            new telaRelatorios().setVisible(true);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new telaMenuPrincipal().setVisible(true);
        });
    }
}
