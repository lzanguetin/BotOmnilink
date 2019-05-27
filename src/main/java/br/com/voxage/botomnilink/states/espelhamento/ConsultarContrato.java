package br.com.voxage.botomnilink.states.espelhamento;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

import br.com.voxage.botomnilink.BotOmnilink;
import br.com.voxage.botomnilink.BotOmnilinkIntegration;
import br.com.voxage.botomnilink.models.Clientes;
import br.com.voxage.botomnilink.models.Contratos;
import br.com.voxage.botomnilink.models.DadosFluxo;
import br.com.voxage.vbot.BotState;
import br.com.voxage.vbot.BotStateFlow;
import br.com.voxage.vbot.BotStateInteractionType;

public class ConsultarContrato {
	@SuppressWarnings("serial")
	public static BotState load(BotOmnilink bot) {
		return new BotState("/") {{
			setId("CONS_CONTRATO");
			
			setBotStateInteractionType(BotStateInteractionType.NO_INPUT);
			
			setAsyncPosFunction((botState, inputResult)->CompletableFuture.supplyAsync(()->{
				BotStateFlow botStateFlow = new BotStateFlow();
				DadosFluxo dadosFluxo = bot.getDadosFluxo();
				Clientes cliente = bot.getClientes();
				botStateFlow.flow = BotStateFlow.Flow.CONTINUE;
				
				bot.getUserSession().put("CLIENTINFO_Transfer", dadosFluxo.getMenu());
				
				String obterPin = BotOmnilink.readString("id.flag.obterPin");
				int aux = dadosFluxo.getMax();
				Contratos customerInfo = null;
				
				try {
					customerInfo = BotOmnilinkIntegration.getContratos(bot, dadosFluxo.getSerie(), cliente.getCliente().getClienteId());
					bot.setContratos(customerInfo);
					if("true".equals(obterPin)) {
						botStateFlow.navigationKey = BotOmnilink.STATES.OBTER_PIN;
					}else {
						botStateFlow.navigationKey = BotOmnilink.STATES.CONSULTAR_ESP;
					}				
				}catch(Exception e){
					if(aux<3){
						aux = aux+1;
						dadosFluxo.setMax(aux);
						botStateFlow.navigationKey = BotOmnilink.STATES.SEM_SERIE;
					}else {
						botStateFlow.navigationKey = BotOmnilink.STATES.ERRO_CONTRATO;
					}			
				}	
				return botStateFlow;
			}));
			
			setNextNavigationMap(new HashMap<String, String>(){{
				put(BotOmnilink.STATES.OBTER_PIN, "/OBTER_PIN");
				put(BotOmnilink.STATES.CONSULTAR_ESP, "/CONSULTAR_ESP");
				put(BotOmnilink.STATES.SERIE_ESP, "/SERIE_ESP");
				put(BotOmnilink.STATES.SEM_SERIE, "/SEM_SERIE");
				put(BotOmnilink.STATES.ERRO_CONTRATO, "/ERRO_CONTRATO");
                put("MAX_INPUT_ERROR", "/TERMINATE");
                put("MAX_NO_INPUT", "/TERMINATE");
			}});
		}};
	}
}
