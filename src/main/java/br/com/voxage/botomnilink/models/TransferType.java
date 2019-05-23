package br.com.voxage.botomnilink.models;

public class TransferType {
	private TransferPropWhats web;
	private TransferPropWhats telegram;
	private TransferPropWhats whatsapp;
	private TransferPropWhats messenger;
	
	public TransferPropWhats getWeb() {
		return web;
	}
	public void setWeb(TransferPropWhats web) {
		this.web = web;
	}
	public TransferPropWhats getTelegram() {
		return telegram;
	}
	public void setTelegram(TransferPropWhats telegram) {
		this.telegram = telegram;
	}
	public TransferPropWhats getWhatsapp() {
		return whatsapp;
	}
	public void setWhatsapp(TransferPropWhats whatsapp) {
		this.whatsapp = whatsapp;
	}
	public TransferPropWhats getMessenger() {
		return messenger;
	}
	public void setMessenger(TransferPropWhats messenger) {
		this.messenger = messenger;
	}	
}
