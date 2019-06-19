package br.com.voxage.botomnilink.states.global;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import br.com.voxage.botomnilink.BotOmnilink;
import br.com.voxage.botomnilink.BotOmnilinkIntegration;
import br.com.voxage.botomnilink.models.Clientes;
import br.com.voxage.botomnilink.models.DadosFluxo;
import br.com.voxage.chat.botintegration.entities.AttendantClientInfo;
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
				List<AttendantClientInfo> att;
				att = bot.getcInfo();
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
						att.get(0).setValue("Verifica Cliente - Erro de Integração");
						bot.setcInfo(att);
						botStateFlow.navigationKey = BotOmnilink.STATES.ATENDENTE;
					}else {
						att.get(0).setValue("Verifica Cliente - Cliente não Localizado");
						bot.setcInfo(att);
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
