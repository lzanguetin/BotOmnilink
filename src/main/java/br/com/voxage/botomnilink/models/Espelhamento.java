package br.com.voxage.botomnilink.models;

import java.util.List;

public class Espelhamento {
	private List<Central> centraisCliente;
	private Integer qtdeEspelhamento;

	public List<Central> getCentraisClientes() {
		return centraisCliente;
	}

	public void setCentraisClientes(List<Central> centraisCliente) {
		this.centraisCliente = centraisCliente;
	}

	public Integer getQtdeEspelhamentos() {
		return qtdeEspelhamento;
	}

	public void setQtdeEspelhamentos(Integer qtdeEspelhamento) {
		this.qtdeEspelhamento = qtdeEspelhamento;
	}
}
