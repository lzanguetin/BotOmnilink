package br.com.voxage.botomnilink.models;

public class Central {
	private String id;
	private String ip;
	private String porta;
	private String nome;
	private String cnpj;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getIp() {
		return ip;
	}
	
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public String getPorta() {
		return porta;
	}
	
	public void setPorta(String porta) {
		this.porta = porta;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
		
	public String getCnpj() {
		return cnpj;
	}
	
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	@Override
	public String toString() {
		return "Central [id=" + id + ", ip=" + ip + ", porta=" + porta + ", nome=" + nome + ", cnpj=" + cnpj + "]";
	}
}
