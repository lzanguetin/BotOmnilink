package br.com.voxage.botomnilink.models;

public class Token {
	private String accessToken;
	private String tokenType;
	private Integer expiresIn;
	
	public String getAcessToken() {
		return accessToken;
	}
	
	public void setAcessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	public String getTokenType() {
		return tokenType;
	}
	
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}
	
	public Integer getExpiresIn() {
		return expiresIn;
	}
	
	public void setExpiresIn(Integer expiresIn) {
		this.expiresIn = expiresIn;
	}
}
