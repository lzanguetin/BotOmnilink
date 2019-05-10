package br.com.voxage.botomnilink.models;

public class Contratos {
	private String contratoId;
	private String status;
	private String tecnologiaId;
	private String permiteEspelhamentoSinal;
	
	public String getContratoId() {
		return contratoId;
	}
	
	public void setContratoId(String contratoId) {
		this.contratoId = contratoId;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getTecnologiaId() {
		return tecnologiaId;
	}
	
	public void setTecnologiaId(String tecnologiaId) {
		this.tecnologiaId = tecnologiaId;
	}
	
	public String getPermiteEspelhamentoSinal() {
		return permiteEspelhamentoSinal;
	}
	
	public void setPermiteEspelhamentoSinal(String permiteEspelhamentoSinal) {
		this.permiteEspelhamentoSinal = permiteEspelhamentoSinal;
	}	
}
