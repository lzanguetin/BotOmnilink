package br.com.voxage.botomnilink.models;

public class Contratos {
	private String contratoId;
	private Integer status;
	private String tecnologiaId;
	private String permiteEspelhamentoSinal;
	
	public String getContratoId() {
		return contratoId;
	}
	
	public void setContratoId(String contratoId) {
		this.contratoId = contratoId;
	}
	
	public Integer getStatus() {
		return status;
	}
	
	public void setStatus(Integer status) {
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
