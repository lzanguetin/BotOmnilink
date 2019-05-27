package br.com.voxage.botomnilink.states.global;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

import br.com.voxage.botomnilink.BotOmnilink;
import br.com.voxage.botomnilink.BotOmnilinkIntegration;
import br.com.voxage.botomnilink.models.DadosFluxo;
import br.com.voxage.botomnilink.models.Clientes;
import br.com.voxage.vbot.BotState;
import br.com.voxage.vbot.BotStateFlow;
import br.com.voxage.vbot.BotStateInteractionType;

public class VerificaCliente {
	@SuppressWarnings("serial")
	public static BotState load(BotOmnilink bot) {
		return new BotState("/") {{
			setId("VERIFCLIENTE");
			
			setBotStateInteractionType(BotStateInteractionType.NO_INPUT);
			
			setAsyncPosFunction((botState, inputResult)-> CompletableFuture.supplyAsync(()->{
				BotStateFlow botStateFlow = new BotStateFlow();
				DadosFluxo dadosFluxo = bot.getDadosFluxo();
				botStateFlow.flow = BotStateFlow.Flow.CONTINUE;
				
				Clientes customerInfo = null;

				try {
					customerInfo = BotOmnilinkIntegration.getCliente(bot, dadosFluxo.getCpfCnpj());
					bot.setClientes(customerInfo);
					
					if(dadosFluxo.getOption() == "3") {					
						botStateFlow.navigationKey = BotOmnilink.STATES.TIPO_ESPELHAMENTO;
					}else if(dadosFluxo.getOption() == "4") {
						botStateFlow.navigationKey = BotOmnilink.STATES.TIPO_FINANCEIRO;
					}else {
						botStateFlow.navigationKey = BotOmnilink.STATES.ATENDENTE;
					}					
				}catch(Exception e){
					if(bot.getError() == 500) {
						bot.getUserSession().put("CLIENTINFO_Transfer", "Verifica Cliente - Erro de Integração");
						botStateFlow.navigationKey = BotOmnilink.STATES.ATENDENTE;
					}else {
						bot.getUserSession().put("CLIENTINFO_Transfer", "Verifica Cliente - Cliente não Localizado");
						botStateFlow.navigationKey = BotOmnilink.STATES.ATENDENTE;
					}					
				}
				return botStateFlow;
			}));
			
			setNextNavigationMap(new HashMap<String, String>(){{
				put(BotOmnilink.STATES.TIPO_ESPELHAMENTO, "#TIPO_ESPELHAMENTO");
				put(BotOmnilink.STATES.TIPO_FINANCEIRO, "#TIPO_FINANCEIRO");
				put(BotOmnilink.STATES.ATENDENTE, "#ATENDENTE");
			}});
		}};
	}
}
