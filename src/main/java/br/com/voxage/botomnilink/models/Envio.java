package br.com.voxage.botomnilink.models;

public class Envio {
	private String cpfCnpj;
	private String codClienteErp;
	private String lojaCliente;
	private String entidadeCliente;
	private String clienteId;
	private Integer statusTitulos;
	
	public String getCpfCnpj() {
		return cpfCnpj;
	}
	
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}
	
	public String getCodClienteErp() {
		return codClienteErp;
	}
	
	public void setCodClienteErp(String codClienteErp) {
		this.codClienteErp = codClienteErp;
	}
	
	public String getLojaCliente() {
		return lojaCliente;
	}
	
	public void setLojaCliente(String lojaCliente) {
		this.lojaCliente = lojaCliente;
	}
	
	public String getEntidadeCliente() {
		return entidadeCliente;
	}
	
	public void setEntidadeCliente(String entidadeCliente) {
		this.entidadeCliente = entidadeCliente;
	}
	
	public String getClienteId() {
		return clienteId;
	}
	
	public void setClienteId(String clienteId) {
		this.clienteId = clienteId;
	}
	
	public Integer getStatusTitulos() {
		return statusTitulos;
	}
	
	public void setStatusTitulos(Integer statusTitulos) {
		this.statusTitulos = statusTitulos;
	}
}
