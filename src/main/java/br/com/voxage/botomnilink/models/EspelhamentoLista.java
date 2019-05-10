package br.com.voxage.botomnilink.models;

public class EspelhamentoLista {	
		private String cnpj;
		private Integer porta;
		private String numSerie;
		private String idCentral;
		private String periodo;
		
		public String getCnpj() {
			return cnpj;
		}
		
		public void setCnpj(String cnpj) {
			this.cnpj = cnpj;
		}
		
		public Integer getPorta() {
			return porta;
		}
		
		public void setPorta(Integer porta) {
			this.porta = porta;
		}
		
		public String getNumSerie() {
			return numSerie;
		}
		
		public void setNumSerie(String numSerie) {
			this.numSerie = numSerie;
		}
		
		public String getIdCentral() {
			return idCentral;
		}
		
		public void setIdCentral(String idCentral) {
			this.idCentral = idCentral;
		}
		
		public String getPeriodo() {
			return periodo;
		}
		
		public void setPeriodo(String periodo) {
			this.periodo = periodo;
		} 
}
