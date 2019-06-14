package br.com.voxage.botomnilink.models;

import java.util.List;

public class Assessoria {
	private String unicaAssessoria;
	private List<Assessor> assessoria;
	
	public String getUnicaAssessoria() {
		return unicaAssessoria;
	}
	
	public void setUnicaAssessoria(String unicaAssessoria) {
		this.unicaAssessoria = unicaAssessoria;
	}
	
	public List<Assessor> getAssessoria() {
		return assessoria;
	}
	
	public void setAssessoria(List<Assessor> assessoria) {
		this.assessoria = assessoria;
	}
}
