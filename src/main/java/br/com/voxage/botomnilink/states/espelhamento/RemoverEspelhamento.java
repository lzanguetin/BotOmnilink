package br.com.voxage.botomnilink.states.espelhamento;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import br.com.voxage.botomnilink.BotOmnilink;
import br.com.voxage.botomnilink.BotOmnilinkIntegration;
import br.com.voxage.botomnilink.models.DadosFluxo;
import br.com.voxage.botomnilink.models.Espelhamento;
import br.com.voxage.botomnilink.models.EspelhamentoLista;
import br.com.voxage.botomnilink.models.InfoRemover;
import br.com.voxage.botomnilink.models.Remover;
import br.com.voxage.vbot.BotState;
import br.com.voxage.vbot.BotStateFlow;
import br.com.voxage.vbot.BotStateInteractionType;

public class RemoverEspelhamento {
	@SuppressWarnings("serial")
	public static BotState load(BotOmnilink bot) {
		return new BotState("/") {{
			setId("REMOVER_ESP");
			
			setBotStateInteractionType(BotStateInteractionType.NO_INPUT);
			
			setAsyncPosFunction((botState, inputResult)->CompletableFuture.supplyAsync(()->{
				BotStateFlow botStateFlow = new BotStateFlow();
				DadosFluxo dadosFluxo = bot.getDadosFluxo();
				Espelhamento esp = bot.getEspelhamento();
				InfoRemover info = new InfoRemover();
				EspelhamentoLista list = new EspelhamentoLista();
				botStateFlow.flow = BotStateFlow.Flow.CONTINUE;

				Remover customerInfo = null;
				
				if(esp.getQtdeEspelhamentos() == 1) {
					List<EspelhamentoLista> centraisCliente;
					centraisCliente = new ArrayList<EspelhamentoLista>();	
					list.setCnpj("");
					list.setIdCentral(esp.getCentraisClientes().get(0).getId());
					list.setNumSerie(dadosFluxo.getSerie());
					list.setPeriodo("");
					list.setPorta(Integer.parseInt(esp.getCentraisClientes().get(0).getPorta()));
					bot.setList(list);
					centraisCliente.add(bot.getList());
					info.setEspelhamentoLista(centraisCliente);
				}else {
					List<EspelhamentoLista> centraisCliente;
					centraisCliente = new ArrayList<EspelhamentoLista>();		
					
					String a = dadosFluxo.getExcluir();
					String[] split = a.split(",");
					
					for (String string : split) {
						list.setCnpj("");
						list.setIdCentral(esp.getCentraisClientes().get((Integer.parseInt(string))-1).getId());
						list.setNumSerie(dadosFluxo.getSerie());
						list.setPeriodo("");
						list.setPorta(Integer.parseInt(esp.getCentraisClientes().get((Integer.parseInt(string))-1).getPorta()));
						bot.setList(list);
						centraisCliente.add(bot.getList());				
						info.setEspelhamentoLista(centraisCliente);
					}	
				}
				
				try {
					customerInfo = BotOmnilinkIntegration.removeEspelhamento(bot, info);
					bot.setRemover(customerInfo);
					if("true".equals(bot.getRemover().getSucesso())) {
						if(dadosFluxo.getEspelha() == 1) {
							botStateFlow.navigationKey = BotOmnilink.STATES.CNPJ_ESP;
						}else {
							botStateFlow.navigationKey = BotOmnilink.STATES.RETIRAR_ESP;
						}
					}else {
						bot.getUserSession().put("CLIENTINFO_Transfer", "Remover Espelhamento - Erro de Integração");
						botStateFlow.navigationKey = BotOmnilink.STATES.ERRO_REMOVER;
					}
				}catch(Exception e){
					bot.getUserSession().put("CLIENTINFO_Transfer", "Remover Espelhamento - Erro de Integração");
					botStateFlow.navigationKey = BotOmnilink.STATES.ERRO_REMOVER;
				}
				return botStateFlow;
			}));
			
			setNextNavigationMap(new HashMap<String, String>(){{
				put(BotOmnilink.STATES.CNPJ_ESP, "#CNPJ_ESP");
				put(BotOmnilink.STATES.RETIRAR_ESP, "#RETIRAR_ESP");
				put(BotOmnilink.STATES.ERRO_REMOVER, "#ERRO_REMOVER");
                put("MAX_INPUT_ERROR", "/TERMINATE");
                put("MAX_NO_INPUT", "/TERMINATE");
			}});
		}};
	}
}