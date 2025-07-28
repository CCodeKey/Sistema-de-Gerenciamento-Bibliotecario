package relatorio;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import Biblioteca.controller.DevolucaoController;
import Biblioteca.controller.EmprestimoController;
import Biblioteca.model.Artigo;
import Biblioteca.model.Emprestimo;
import Biblioteca.model.Livro;
import Biblioteca.model.Obra;
import Biblioteca.model.Revista;
import Biblioteca.model.Usuario;
import Excecoes.EmprestimoNaoEncontradoException;
import Excecoes.UsuarioNaoExisteException;
import interfaces.Relatorio;

public class RelatorioUsuarios implements Relatorio {

	private String arquivoPDF;
	private DevolucaoController devolucoes;

	public RelatorioUsuarios() {
		this.devolucoes = new DevolucaoController();

		String path = "C:\\Users\\kaleu\\IdeaProjects\\Sistema-de-Gerenciamento-Bibliotecario\\src\\relatorio\\relatorios\\";

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
		String dataHoraFormatada = LocalDateTime.now().format(formatter);

		this.arquivoPDF = path + "relatorio_de_emprestimos_" + dataHoraFormatada + ".pdf";
	}

	@Override
	public void gerarRelatorio() throws Exception {
		Document documento = new Document();
		PdfWriter.getInstance(documento, new FileOutputStream(arquivoPDF));
		documento.open();

		Paragraph titulo = new Paragraph("Relatório de usuários com mais atrasos\n\n",
				new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD));
		titulo.setAlignment(Element.ALIGN_CENTER);
		documento.add(titulo);

		PdfPTable tabela = new PdfPTable(4);
		tabela.setWidthPercentage(100);

		adicionarCelula(tabela, "Tipo", true);
		adicionarCelula(tabela, "Nome", true);
		adicionarCelula(tabela, "E-mail", true);
		adicionarCelula(tabela, "Qtd. de atrasos", true);

		List<Usuario> lista_usuario_com_atraso = organizarUsuariosPorRepeticao(devolucoes.listaUsuariosComAtraso());

		List<Usuario> lista_organizada = new ArrayList<>();

		for (Usuario u : lista_usuario_com_atraso) {
			int vezes = 0;
			for (Usuario user : lista_organizada) {
				if (u.getEmail().toLowerCase().equals(user.getEmail().toLowerCase())) {
					vezes += 1;
				}
			}

			if (vezes < 1) {
				lista_organizada.add(u);

			}
			vezes = 0;
		}

		for (Usuario u : lista_organizada) {
			adicionarLinha(tabela, u.getTipoUsuario(), u.getNome(), u.getEmail(), contadorDeVezes(u));
		}

		documento.add(tabela);

		documento.add(adicionarDataNoRodape());

		documento.close();

		System.out.println("Relatório gerado com sucesso!");
	}

	private void adicionarCelula(PdfPTable tabela, String texto, boolean cabecalho) {
		PdfPCell celula = new PdfPCell(new Phrase(texto));
		celula.setPadding(5);
		if (cabecalho) {
			celula.setBackgroundColor(new BaseColor(200, 200, 200)); // Cinza
		}
		tabela.addCell(celula);
	}

	private void adicionarLinha(PdfPTable tabela, String tipoUser, String nomeUser, String emailUser,
			String qtdAtraso) {
		adicionarCelula(tabela, tipoUser, false);
		adicionarCelula(tabela, nomeUser, false);
		adicionarCelula(tabela, emailUser, false);
		adicionarCelula(tabela, qtdAtraso, false);
	}

	private Paragraph adicionarDataNoRodape() {
		Paragraph rodape = new Paragraph("\nGerado em: " + new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
		return rodape;
	}

	private String contadorDeVezes(Usuario user) throws UsuarioNaoExisteException {
		int vezes = 0;

		for (Usuario u : devolucoes.listaUsuariosComAtraso()) {
			if (u.getEmail().toLowerCase().equals(user.getEmail().toLowerCase())) {
				vezes++;
			}
		}
		String qtd = String.valueOf(vezes);
		return qtd;
	}

	private List<Usuario> organizarUsuariosPorRepeticao(List<Usuario> usuarios) {
		Map<String, Integer> contagemUsuarios = new HashMap<>();
		Map<String, Usuario> usuariosPorMatricula = new HashMap<>();

		for (Usuario u : usuarios) {
			String matricula = u.getMatricula();

			contagemUsuarios.put(matricula, contagemUsuarios.getOrDefault(matricula, 0) + 1);
			usuariosPorMatricula.putIfAbsent(matricula, u);
		}

		List<Usuario> resultado = new ArrayList<>(usuariosPorMatricula.values());

		resultado.sort((a, b) -> {
			int cmp = Integer.compare(contagemUsuarios.get(b.getMatricula()), contagemUsuarios.get(a.getMatricula()));
			return cmp != 0 ? cmp : a.getNome().compareToIgnoreCase(b.getNome());
		});

		return resultado;
	}

}
