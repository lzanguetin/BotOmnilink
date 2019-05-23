package br.com.voxage.botomnilink.states.espelhamento;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

import br.com.voxage.botomnilink.BotOmnilink;
import br.com.voxage.botomnilink.BotOmnilinkIntegration;
import br.com.voxage.botomnilink.models.DadosFluxo;
import br.com.voxage.botomnilink.models.Espelhamento;
import br.com.voxage.botomnilink.models.TokenMicro;
import br.com.voxage.vbot.BotState;
import br.com.voxage.vbot.BotStateFlow;
import br.com.voxage.vbot.BotStateInteractionType;

public class ConsultaEspelhamento {
	@SuppressWarnings("serial")
	public static BotState load(BotOmnilink bot) {
		return new BotState("/") {{
			setId("CONSULTAR_ESP");
			
			setBotStateInteractionType(BotStateInteractionType.NO_INPUT);
		
			setAsyncPosFunction((botState, inputResult) ->CompletableFuture.supplyAsync(()->{
				BotStateFlow botStateFlow = new BotStateFlow();
				DadosFluxo dadosFluxo = bot.getDadosFluxo();
				botStateFlow.flow = BotStateFlow.Flow.CONTINUE;
				
				TokenMicro customInfo = null;
				Espelhamento customerInfo = null;
								
				try {
					customInfo = BotOmnilinkIntegration.getMicro(bot);
					bot.setMicro(customInfo);
					try {
						customerInfo = BotOmnilinkIntegration.getCentrais(bot,dadosFluxo.getSerie());
						bot.setEspelhamento(customerInfo);
						
						if(bot.getEspelhamento().getQtdeEspelhamentos() == 0) {
							botStateFlow.navigationKey = BotOmnilink.STATES.ERRO_INTEGRA_ESP;
						}else {
							botStateFlow.navigationKey = BotOmnilink.STATES.VALIDAR_ESP;
						}	
					}catch(Exception e) {
						botStateFlow.navigationKey = BotOmnilink.STATES.ERRO_INTEGRA_ESP;
					}
				}catch(Exception e) {
					botStateFlow.navigationKey = BotOmnilink.STATES.ERRO_INTEGRA_ESP;
				}
				
				return botStateFlow;
			}));
			
			setNextNavigationMap(new HashMap<String, String>(){{
				put(BotOmnilink.STATES.VALIDAR_ESP, "#VALIDAR_ESP");
				put(BotOmnilink.STATES.ERRO_INTEGRA_ESP, "#ERRO_INTEGRA_ESP");
			}});
		}};
	}
}
