package Biblioteca.controller;

import java.io.IOException;

import Biblioteca.dao.ObraDao;

import Biblioteca.model.Artigo;
import Biblioteca.model.Livro;
import Biblioteca.model.Obra;
import Biblioteca.model.Revista;
import Excecoes.ObraExistenteException;

public class ObraController {
	private long codigo;
	private String titulo;
	private String autor;
	private int anoDePublicacao;
	private String tipoDeObra;
	private ObraDao dao;
	private String identificacao;

	public ObraController() {
		this.dao = new ObraDao();
	}

	public ObraController(String tipoDeObra) {
		this.tipoDeObra = tipoDeObra.toLowerCase();
		this.dao = new ObraDao();
	}

	public ObraController(long codigo, String titulo, String autor, int anoDePublicacao, String tipoDeObra, String id) {
		this.codigo = codigo;
		this.titulo = titulo;
		this.autor = autor;
		this.anoDePublicacao = anoDePublicacao;
		this.tipoDeObra = tipoDeObra.toLowerCase();
		this.dao = new ObraDao();
		this.identificacao = id;
	}

	public void novaObra() throws ObraExistenteException, IOException {
		Obra buscarObra = dao.buscarObraPorCodigo(codigo);
		if (buscarObra == null) {
			if (tipoDeObra.equals("artigo")) {
				Obra obra = new Artigo(codigo, titulo, autor, anoDePublicacao, identificacao);
				dao.salvarObra(obra);

			} else if (tipoDeObra.equals("livro")) {
				Obra obra = new Livro(codigo, titulo, autor, anoDePublicacao, identificacao);
				dao.salvarObra(obra);

			} else if (tipoDeObra.equals("revista")) {
				Obra obra = new Revista(codigo, titulo, autor, anoDePublicacao, identificacao);
				dao.salvarObra(obra);

			}
		} else {
			throw new ObraExistenteException();
		}
	}

	public Obra buscarObraPorCodigo(long codigo) {
		if (tipoDeObra.equals("artigo")) {
			return dao.buscarObraPorCodigo(codigo);

		} else if (tipoDeObra.equals("livro")) {
			return dao.buscarObraPorCodigo(codigo);

		} else if (tipoDeObra.equals("revista")) {
			return dao.buscarObraPorCodigo(codigo);
		}
		return null;
	}

	public void ocuparObra(long codigo) throws IOException {
		dao.ocuparObra(codigo);
	}

	public void disponibilizarObra(long codigo) throws IOException {
		dao.disponibilizarObra(codigo);
	}
}
