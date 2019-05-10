package br.com.voxage.botomnilink.models;

public class Cliente {
    private String clienteId;
    private String nome;
    private String cpfCnpj;
    private String codClienteErp;
    private String lojaErp;
    private String entidade;
    private String status;
    private String atendimentoveiculo;
    private String comunicacaoChip;
    private String comunicacaoSatelital;
    private String emissaoPV;
    private String bloqueioTotal;
	
    public String getClienteId() {
		return clienteId;
	}
	
    public void setClienteId(String clienteId) {
		this.clienteId = clienteId;
	}
	
    public String getNome() {
		return nome;
	}
	
    public void setNome(String nome) {
		this.nome = nome;
	}
	
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
	
    public String getLojaErp() {
		return lojaErp;
	}
	
    public void setLojaErp(String lojaErp) {
		this.lojaErp = lojaErp;
	}
	
    public String getEntidade() {
		return entidade;
	}
	
    public void setEntidade(String entidade) {
		this.entidade = entidade;
	}
	
    public String getStatus() {
		return status;
	}
	
    public void setStatus(String status) {
		this.status = status;
	}
	
    public String getAtendimentoveiculo() {
		return atendimentoveiculo;
	}
	
    public void setAtendimentoveiculo(String atendimentoveiculo) {
		this.atendimentoveiculo = atendimentoveiculo;
	}
	
    public String getComunicacaoChip() {
		return comunicacaoChip;
	}
	
    public void setComunicacaoChip(String comunicacaoChip) {
		this.comunicacaoChip = comunicacaoChip;
	}
	
    public String getComunicacaoSatelital() {
		return comunicacaoSatelital;
	}
	
    public void setComunicacaoSatelital(String comunicacaoSatelital) {
		this.comunicacaoSatelital = comunicacaoSatelital;
	}
	
    public String getEmissaoPV() {
		return emissaoPV;
	}
	
    public void setEmissaoPV(String emissaoPV) {
		this.emissaoPV = emissaoPV;
	}
	
    public String getBloqueioTotal() {
		return bloqueioTotal;
	}
	
    public void setBloqueioTotal(String bloqueioTotal) {
		this.bloqueioTotal = bloqueioTotal;
	}	
}
