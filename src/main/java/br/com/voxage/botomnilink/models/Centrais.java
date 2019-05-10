package br.com.voxage.botomnilink.models;

import java.util.List;

public class Centrais {
	private List<Central> centraisCliente;
	private String qtdeEspelhamento;
	
	public List<Central> getCentraisCliente() {
		return centraisCliente;
	}
	
	public void setCentraisCliente(List<Central> centraisCliente) {
		this.centraisCliente = centraisCliente;
	}
	
	public String getQtdeEspelhamento() {
		return qtdeEspelhamento;
	}
	
	public void setQtdeEspelhamento(String qtdeEspelhamento) {
		this.qtdeEspelhamento = qtdeEspelhamento;
	}

	@Override
	public String toString() {
		return "Centrais [centraisCliente=" + centraisCliente + ", qtdeEspelhamento=" + qtdeEspelhamento + "]";
	}
}
