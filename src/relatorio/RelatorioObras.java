package relatorio;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;

import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import Biblioteca.controller.EmprestimoController;
import Biblioteca.model.Artigo;
import Biblioteca.model.Emprestimo;
import Biblioteca.model.Livro;
import Biblioteca.model.Obra;
import Biblioteca.model.Revista;
import Excecoes.EmprestimoNaoEncontradoException;
import interfaces.Relatorio;

public class RelatorioObras implements Relatorio {

	private String arquivoPDF;
	private EmprestimoController emprestimos;

	public RelatorioObras() {
		this.emprestimos = new EmprestimoController();

		String path = "/home/code/Documents/workspace-spring-tool-suite-4-4.29.1.RELEASE/Sistema_versao_interface/Sistema-de-Gerenciamento-Bibliotecario/src/resources/pdf/";

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
		String dataHoraFormatada = LocalDateTime.now().format(formatter);

		this.arquivoPDF = path + "relatorio_de_obras_mais_emprestadas_" + dataHoraFormatada + ".pdf";
	}

	@Override
	public void gerarRelatorio() throws Exception {
		Document documento = new Document();
		PdfWriter.getInstance(documento, new FileOutputStream(arquivoPDF));
		documento.open();

		Paragraph titulo = new Paragraph("Relatório de obras mais emprestadas\n\n",
				new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD));
		titulo.setAlignment(Element.ALIGN_CENTER);
		documento.add(titulo);

		PdfPTable tabela = new PdfPTable(3);
		tabela.setWidthPercentage(100);

		adicionarCelula(tabela, "Tipo da obra", true);
		adicionarCelula(tabela, "Nome da obra", true);
		adicionarCelula(tabela, "Qtd. de empréstimos", true);

		List<Obra> lista_obras = organizarPorRepeticao(emprestimos.listarEmprestimos());

		List<Obra> obrasOrganizadas = new ArrayList<>();

		for (Obra ob : lista_obras) {
			int vezes = 0;
			for (Obra obr : obrasOrganizadas) {
				if (ob.getTitulo().toLowerCase().equals(obr.getTitulo().toLowerCase())) {
					vezes += 1;
				}
			}

			if (vezes < 1) {
				obrasOrganizadas.add(ob);

			}
			vezes = 0;
		}

		for (Obra ob : obrasOrganizadas) {

			String tipo = "";

			if (ob instanceof Artigo) {
				tipo = "Artigo";
			} else if (ob instanceof Livro) {
				tipo = "Livro";
			} else if (ob instanceof Revista) {
				tipo = "Revista";
			}

			adicionarLinha(tabela, tipo, ob.getTitulo(), contadorDeVezes(ob));
		}

		documento.add(tabela);

		documento.add(adicionarDataNoRodape());

		documento.close();

	}

	private void adicionarCelula(PdfPTable tabela, String texto, boolean cabecalho) {
		PdfPCell celula = new PdfPCell(new Phrase(texto));
		celula.setPadding(5);
		if (cabecalho) {
			celula.setBackgroundColor(new BaseColor(200, 200, 200)); // cor Cinza
		}
		tabela.addCell(celula);
	}

	private void adicionarLinha(PdfPTable tabela, String tipo, String nomeObra, String qtdVezes) {
		adicionarCelula(tabela, tipo, false);
		adicionarCelula(tabela, nomeObra, false);
		adicionarCelula(tabela, qtdVezes, false);
	}

	private Paragraph adicionarDataNoRodape() {
		Paragraph rodape = new Paragraph("\nGerado em: " + new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
		return rodape;
	}

	private String contadorDeVezes(Obra obra) throws EmprestimoNaoEncontradoException {
		int vezes = 0;

		for (Emprestimo emp : emprestimos.listarEmprestimos()) {
			if (emp.getObra().getAutor().toLowerCase().equals(obra.getAutor().toLowerCase())
					&& emp.getObra().getTitulo().toLowerCase().equals(obra.getTitulo().toLowerCase())) {
				vezes++;
			}
		}
		String qtd = String.valueOf(vezes);
		return qtd;
	}

	private List<Obra> organizarPorRepeticao(List<Emprestimo> emprestimos) {
		Map<String, Integer> contagemTitulos = new HashMap<>();
		Map<String, Obra> obrasPorTitulo = new HashMap<>();

		for (Emprestimo emp : emprestimos) {
			Obra obra = emp.getObra();
			String titulo = obra.getTitulo();

			contagemTitulos.put(titulo, contagemTitulos.getOrDefault(titulo, 0) + 1);
			obrasPorTitulo.putIfAbsent(titulo, obra);
		}

		List<Obra> resultado = new ArrayList<>(obrasPorTitulo.values());

		resultado.sort((a, b) -> {
			int cmp = Integer.compare(contagemTitulos.get(b.getTitulo()), contagemTitulos.get(a.getTitulo()));
			return cmp != 0 ? cmp : a.getTitulo().compareToIgnoreCase(b.getTitulo());
		});

		return resultado;
	}

}
