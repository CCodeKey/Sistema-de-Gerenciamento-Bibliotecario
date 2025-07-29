package Biblioteca.view.adm;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import Biblioteca.controller.ObraController;
import Excecoes.ObraExistenteException;

public class telaCadastroObra extends JFrame {
	private JTextField txtTitulo;
	private JTextField txtAutor;
	private JComboBox<String> comboTipoObra;
	private JTextField txtAnoPublicacao;
	private JTextField txtCodigoObra;
	private JPanel camposEspecificosPanel;
	private JTextField txtCategoria; // Para Artigo
	private JTextField txtIsbn; // Para Livro
	private JTextField txtIssn; // Para Revista

	public telaCadastroObra() {
		setTitle("Cadastro de Obra");
		setSize(400, 400); // Aumentei o tamanho para acomodar campos extras
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);

		JPanel painel = new JPanel();
		painel.setLayout(new BorderLayout(10, 10));
		painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		// Painel para campos comuns
		JPanel camposComunsPanel = new JPanel(new GridLayout(5, 2, 10, 10));

		JLabel lblTitulo = new JLabel("Título:");
		txtTitulo = new JTextField();

		JLabel lblAutor = new JLabel("Autor:");
		txtAutor = new JTextField();

		JLabel lblTipoObra = new JLabel("Tipo da obra:");
		comboTipoObra = new JComboBox<>();
		comboTipoObra.addItem("Artigo");
		comboTipoObra.addItem("Livro");
		comboTipoObra.addItem("Revista");

		JLabel lblAnoPublicacao = new JLabel("Ano de Publicação:");
		txtAnoPublicacao = new JTextField();

		JLabel lblCodigoObra = new JLabel("Código:");
		txtCodigoObra = new JTextField();

		camposComunsPanel.add(lblTitulo);
		camposComunsPanel.add(txtTitulo);
		camposComunsPanel.add(lblAutor);
		camposComunsPanel.add(txtAutor);
		camposComunsPanel.add(lblTipoObra);
		camposComunsPanel.add(comboTipoObra);
		camposComunsPanel.add(lblAnoPublicacao);
		camposComunsPanel.add(txtAnoPublicacao);
		camposComunsPanel.add(lblCodigoObra);
		camposComunsPanel.add(txtCodigoObra);

		// Painel para campos específicos (inicialmente vazio)
		camposEspecificosPanel = new JPanel();
		camposEspecificosPanel.setLayout(new GridLayout(1, 2, 10, 10));
		atualizarCamposEspecificos();

		// Painel para botões
		JPanel botoesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
		JButton btnSalvar = new JButton("Salvar");
		JButton btnCancelar = new JButton("Cancelar");
		botoesPanel.add(btnSalvar);
		botoesPanel.add(btnCancelar);

		// Adiciona todos os painéis ao painel principal
		painel.add(camposComunsPanel, BorderLayout.NORTH);
		painel.add(camposEspecificosPanel, BorderLayout.CENTER);
		painel.add(botoesPanel, BorderLayout.SOUTH);

		add(painel);

		// Listener para o combobox
		comboTipoObra.addActionListener(e -> atualizarCamposEspecificos());

		btnCancelar.addActionListener(e -> dispose());

		btnSalvar.addActionListener(e -> {
			String titulo = txtTitulo.getText().trim();
			String autor = txtAutor.getText().trim();
			String tipo = ((String) comboTipoObra.getSelectedItem()).toLowerCase();
			String anoStr = txtAnoPublicacao.getText().trim();
			String codigoStr = txtCodigoObra.getText().trim();
			String identificacao = "";

			// Obter o valor do campo específico
			switch (tipo) {
			case "artigo":
				identificacao = txtCategoria.getText().trim();
				break;
			case "livro":
				identificacao = txtIsbn.getText().trim();
				break;
			case "revista":
				identificacao = txtIssn.getText().trim();
				break;
			}

			// Validação básica dos campos
			if (titulo.isEmpty() || autor.isEmpty() || anoStr.isEmpty() || codigoStr.isEmpty()
					|| identificacao.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.", "Erro",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			int ano;
			long codigo;
			try {
				ano = Integer.parseInt(anoStr);
				codigo = Long.parseLong(codigoStr);
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(this, "Ano ou código inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
				return;
			}

			cadastrar(codigo, titulo, autor, ano, tipo, identificacao);
		});
	}

	private void atualizarCamposEspecificos() {
		camposEspecificosPanel.removeAll();

		String tipoSelecionado = (String) comboTipoObra.getSelectedItem();

		// Inicializa os campos se ainda não existirem
		if (txtCategoria == null)
			txtCategoria = new JTextField();
		if (txtIsbn == null)
			txtIsbn = new JTextField();
		if (txtIssn == null)
			txtIssn = new JTextField();

		switch (tipoSelecionado) {
		case "Artigo":
			camposEspecificosPanel.add(new JLabel("Categoria:"));
			camposEspecificosPanel.add(txtCategoria);
			break;
		case "Livro":
			camposEspecificosPanel.add(new JLabel("ISBN:"));
			camposEspecificosPanel.add(txtIsbn);
			break;
		case "Revista":
			camposEspecificosPanel.add(new JLabel("ISSN:"));
			camposEspecificosPanel.add(txtIssn);
			break;
		}

		camposEspecificosPanel.revalidate();
		camposEspecificosPanel.repaint();
	}

	protected void cadastrar(long codigo, String titulo, String autor, int ano, String tipo, String identificacao) {

		ObraController controller = new ObraController(codigo, titulo, autor, ano, tipo, identificacao);

		try {
			controller.novaObra();
			JOptionPane.showMessageDialog(this, "Obra cadastrada com sucesso!", "Sucesso",
					JOptionPane.INFORMATION_MESSAGE);
			dispose(); // fecha a janela após salvar
		} catch (ObraExistenteException ex) {
			JOptionPane.showMessageDialog(this, "Já existe uma obra cadastrada com esse código.", "Erro",
					JOptionPane.ERROR_MESSAGE);
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(this, "Erro ao salvar a obra.", "Erro", JOptionPane.ERROR_MESSAGE);
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			new telaCadastroObra().setVisible(true);
		});
	}
}
