package br.com.voxage.botomnilink.models;

public class Clientes {
	private String tecnico;
	private Cliente cliente;
	private String existeContrato;
	private String mensagem;
	
	public String getTecnico() {
		return tecnico;
	}
	
	public void setTecnico(String tecnico) {
		this.tecnico = tecnico;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public String getExisteContrato() {
		return existeContrato;
	}
	
	public void setExisteContrato(String existeContrato) {
		this.existeContrato = existeContrato;
	}
	
	public String getMensagem() {
		return mensagem;
	}
	
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}


