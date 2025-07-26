package Biblioteca.view.adm;

import Biblioteca.view.adm.*;

import javax.swing.*;
import java.awt.*;

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
    private JButton btnCadastrarUsuarioAdministrador;
    private JButton btnLogout;

    public telaMenuPrincipal() {
        setTitle("Menu Principal");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(16, 1, 5, 5));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        btnCadastrarUsuario = new JButton("Cadastrar Usuário");
        btnEditarUsuario = new JButton("Editar Usuário");
        btnExcluirUsuario = new JButton("Excluir Usuário");
        btnCadastrarObra = new JButton("Cadastrar Obra");
        btnRealizarEmprestimo = new JButton("Realizar Empréstimo");
        btnDevolucaoObra = new JButton("Devolução de Obra");
        btnPagarMulta = new JButton("Pagar Devolução Pendente");
        btnListarObras = new JButton("Listar Obras");
        btnBuscarObraTitulo = new JButton("Buscar Obra por Título");
        btnBuscarObraAutor = new JButton("Buscar Obra por Autor");
        btnBuscarObraTipo = new JButton("Buscar Obra por Tipo");
        btnRelatorioEmprestimosMes = new JButton("Relatório de Empréstimos do Mês");
        btnRelatorioObrasMaisEmprestadas = new JButton("Relatório de Obras Mais Emprestadas");
        btnRelatorioUsuariosAtrasados = new JButton("Relatório de Usuários Atrasados");
        btnCadastrarUsuarioAdministrador = new JButton("Cadastrar Usuário Administrador");
        btnLogout = new JButton("Logout");

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
        painel.add(btnCadastrarUsuarioAdministrador);
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
            new telaPagamentoMulta().setVisible(true);
        });
        btnListarObras.addActionListener(e -> {
            new telaListagemObras().setVisible(true);
        });
        btnBuscarObraTitulo.addActionListener(e -> {
           new telaListagemObraTitulo().setVisible(true);
        });
        btnBuscarObraAutor.addActionListener(e -> {
          new telaListagemObraAutor().setVisible(true);
        });
        btnBuscarObraTipo.addActionListener(e -> {
            new telaListagemObraTipo().setVisible(true);
        });
        btnRelatorioEmprestimosMes.addActionListener(e -> {
            new telaRelatorios().setVisible(true);
        });
        btnRelatorioObrasMaisEmprestadas.addActionListener(e -> {
            new telaRelatorios().setVisible(true);
        });
        btnCadastrarUsuarioAdministrador.addActionListener(e -> {
            new telaCadastroAdmin();
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
