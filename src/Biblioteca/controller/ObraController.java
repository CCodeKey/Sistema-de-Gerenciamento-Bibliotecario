package Biblioteca.controller;

import java.io.IOException;

import Biblioteca.dao.ArtigoDao;
import Biblioteca.dao.LivroDao;
import Biblioteca.dao.RevistaDao;
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
	private RevistaDao revistaDao;
	private LivroDao livroDao;
	private ArtigoDao artigoDao;

	public ObraController(String tipoDeObra) {
		this.tipoDeObra = tipoDeObra;
		this.revistaDao = new RevistaDao();
		this.artigoDao = new ArtigoDao();
		this.livroDao = new LivroDao();
	}

	public ObraController(long codigo, String titulo, String autor, int anoDePublicacao, String tipoDeObra) {
		this.codigo = codigo;
		this.titulo = titulo;
		this.autor = autor;
		this.anoDePublicacao = anoDePublicacao;
		this.tipoDeObra = tipoDeObra.toLowerCase();
		this.revistaDao = new RevistaDao();
		this.artigoDao = new ArtigoDao();
		this.livroDao = new LivroDao();
	}

	public void novaObra() throws ObraExistenteException, IOException {
		if (tipoDeObra.equals("artigo")) {
			Obra buscarObra = artigoDao.buscarObraPorCodigo(codigo);
			if (buscarObra == null) {
				Obra obra = new Artigo(codigo, titulo, autor, anoDePublicacao);
				artigoDao.salvarObra(obra);

			} else {
				throw new ObraExistenteException("Artigo");
			}

		} else if (tipoDeObra.equals("livro")) {
			Obra buscarObra = livroDao.buscarObraPorCodigo(codigo);
			if (buscarObra == null) {
				Obra obra = new Livro(codigo, titulo, autor, anoDePublicacao);
				livroDao.salvarObra(obra);

			} else {
				throw new ObraExistenteException("Livro");
			}

		} else if (tipoDeObra.equals("revista")) {
			Obra buscarObra = revistaDao.buscarObraPorCodigo(codigo);
			if (buscarObra == null) {
				Obra obra = new Revista(codigo, titulo, autor, anoDePublicacao);
				revistaDao.salvarObra(obra);

			} else {
				throw new ObraExistenteException("Revista");
			}
		}

	}

}
