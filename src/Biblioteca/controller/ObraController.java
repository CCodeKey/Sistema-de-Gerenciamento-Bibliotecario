package Biblioteca.controller;

import java.io.IOException;
import java.util.List;

import Biblioteca.dao.DevolucaoDao;
import Biblioteca.dao.ObraDao;

import Biblioteca.model.Artigo;
import Biblioteca.model.Livro;
import Biblioteca.model.Obra;
import Biblioteca.model.Revista;
import Excecoes.ObraExistenteException;
import Excecoes.ObraNaoEncontradaException;
import Excecoes.ObraNaoExisteException;

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
			return dao.buscarObraPorCodigo(codigo, tipoDeObra);

		} else if (tipoDeObra.equals("livro")) {
			return dao.buscarObraPorCodigo(codigo, tipoDeObra);

		} else if (tipoDeObra.equals("revista")) {
			return dao.buscarObraPorCodigo(codigo, tipoDeObra);
		}
		return null;
	}

	public List<Obra> listarObrasDisponiveis() throws ObraNaoExisteException {
		if (dao.listarObrasDisponiveis() == null || dao.listarObrasDisponiveis().isEmpty()) {
			throw new ObraNaoExisteException();
		}
		return dao.listarObrasDisponiveis();
	}

	public List<Obra> listarObrasEmprestadas() throws ObraNaoExisteException {
		if (dao.listarObrasEmprestadas() == null || dao.listarObrasEmprestadas().isEmpty()) {
			throw new ObraNaoExisteException();
		}
		return dao.listarObrasEmprestadas();
	}

	public List<Obra> listarObrasAtrasadas() throws ObraNaoExisteException {
		if (dao.listarObrasAtrasadas() == null || dao.listarObrasAtrasadas().isEmpty()) {
			throw new ObraNaoExisteException();
		}
		return dao.listarObrasAtrasadas();
	}

	public List<Obra> buscarPorTitulo(String titulo) throws ObraNaoEncontradaException {
		List<Obra> obrasEncontradas = dao.buscarPorTitulo(titulo);
		if (obrasEncontradas == null || obrasEncontradas.isEmpty()) {
			throw new ObraNaoEncontradaException();
		}
		return obrasEncontradas;
	}


	public List<Obra> buscarPorAutor(String autor) throws ObraNaoEncontradaException {
		List<Obra> obrasEncontradas = dao.buscarPorAutor(autor);
		if (obrasEncontradas == null || obrasEncontradas.isEmpty()) {
			throw new ObraNaoEncontradaException();
		}
		return obrasEncontradas;
	}

	public List<Obra> buscarPorTipo(String tipo) throws ObraNaoEncontradaException {
		if (dao.buscarPorTipo(tipo) == null || dao.buscarPorTipo(tipo).isEmpty()) {
			throw new ObraNaoEncontradaException();
		}
		return dao.buscarPorTipo(tipo);
	}

	public void ocuparObra(long codigo) throws IOException {
		dao.ocuparObra(codigo);
	}

	public void disponibilizarObra(long codigo) throws IOException {
		dao.disponibilizarObra(codigo);
	}
}
