package Biblioteca.model;

public class Pagamento {
	private long id;
	private double valorPago;
	private String horarioPagamento;
	private String dataPagamento;
	private String metodoDePagamento;
	private Usuario usuario;
	private String status;

	public Pagamento(long id, double valorPago, String horarioPagamento, String dataPagamento, String metodoDePagamento,
			Usuario usuario, String status) {
		this.id = id;
		this.valorPago = valorPago;
		this.horarioPagamento = horarioPagamento;
		this.dataPagamento = dataPagamento;
		this.metodoDePagamento = metodoDePagamento;
		this.usuario = usuario;
		this.status = status;
	}

	public long getId() {
		return id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getValorPago() {
		return valorPago;
	}

	public String getHorarioPagamento() {
		return horarioPagamento;
	}

	public String getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(String dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public String getMetodoDePagamento() {
		return metodoDePagamento;
	}

	public void setMetodoDePagamento(String metodoDePagamento) {
		this.metodoDePagamento = metodoDePagamento;
	}

	public Usuario getUsuario() {
		return usuario;
	}

}
