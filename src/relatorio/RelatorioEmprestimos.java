package relatorio;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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
import Biblioteca.model.Revista;
import interfaces.Relatorio;

public class RelatorioEmprestimos implements Relatorio {
	private String arquivoPDF;
	private EmprestimoController emprestimos;

	public RelatorioEmprestimos() {
		this.emprestimos = new EmprestimoController();

		String path = "resources/pdf/";

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
		String dataHoraFormatada = LocalDateTime.now().format(formatter);

		this.arquivoPDF = path + "relatorio_de_emprestimos_" + dataHoraFormatada + ".pdf";
	}

	@Override
	public void gerarRelatorio() {
		try {
			// criando o documento
			Document documento = new Document();
			PdfWriter.getInstance(documento, new FileOutputStream(arquivoPDF));
			documento.open();

			// Adicionando o titulo
			Paragraph titulo = new Paragraph("Relatório Mensal de Empréstimos\n\n",
					new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD)); // estilizando
			titulo.setAlignment(Element.ALIGN_CENTER);
			documento.add(titulo);

			// Criando dados
			PdfPTable tabela = new PdfPTable(4); // para três colunas
			tabela.setWidthPercentage(100);

			// Cabeçalho de cada coluna
			adicionarCelula(tabela, "Tipo", true);
			adicionarCelula(tabela, "Obra", true);
			adicionarCelula(tabela, "Usuário", true);
			adicionarCelula(tabela, "Data", true);

			for (Emprestimo e : emprestimos.listarEmprestimos()) {
				LocalDate dataDoEmprestimo = LocalDate.parse(e.getDataDoEmprestimo());
				Month mesDoEmprestimo = dataDoEmprestimo.getMonth();
				Month mesAtual = LocalDate.now().getMonth();

				if (mesDoEmprestimo == mesAtual) {
					String tipo = "";

					if (e.getObra() instanceof Artigo) {
						tipo = "Artigo";
					} else if (e.getObra() instanceof Livro) {
						tipo = "Livro";
					} else if (e.getObra() instanceof Revista) {
						tipo = "Revista";
					}

					adicionarLinha(tabela, tipo, e.getObra().getTitulo(), e.getUsuario().getNome(),
							e.getDataDoEmprestimo().toString());
				}
			}

			documento.add(tabela);

			documento.add(adicionarDataNoRodape());

			documento.close();

		} catch (Exception e) {
			System.out.println("Erro ao gerar relatório: " + e.getMessage());

		}
	}

	private void adicionarCelula(PdfPTable tabela, String texto, boolean cabecalho) {
		PdfPCell celula = new PdfPCell(new Phrase(texto));
		celula.setPadding(5);
		if (cabecalho) {
			celula.setBackgroundColor(new BaseColor(200, 200, 200)); // Cinza
		}
		tabela.addCell(celula);
	}

	private void adicionarLinha(PdfPTable tabela, String tipo, String obra, String usuario, String data) {
		adicionarCelula(tabela, tipo, false);
		adicionarCelula(tabela, obra, false);
		adicionarCelula(tabela, usuario, false);
		adicionarCelula(tabela, data, false);
	}

	private Paragraph adicionarDataNoRodape() {
		Paragraph rodape = new Paragraph("\nGerado em: " + new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
		return rodape;
	}

}
