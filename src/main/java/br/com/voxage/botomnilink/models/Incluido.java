package br.com.voxage.botomnilink.models;

import java.util.List;

public class Incluido {
	private String status;
	private List<Central> centrais;
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public List<Central> getCentrais() {
		return centrais;
	}
	
	public void setCentrais(List<Central> centrais) {
		this.centrais = centrais;
	}
}