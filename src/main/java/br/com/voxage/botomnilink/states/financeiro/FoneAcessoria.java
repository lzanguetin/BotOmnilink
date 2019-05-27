package br.com.voxage.botomnilink.states.financeiro;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

import br.com.voxage.botomnilink.BotOmnilink;
import br.com.voxage.botomnilink.BotOmnilinkIntegration;
import br.com.voxage.botomnilink.models.Assessoria;
import br.com.voxage.botomnilink.models.DadosFluxo;
import br.com.voxage.vbot.BotState;
import br.com.voxage.vbot.BotStateFlow;
import br.com.voxage.vbot.BotStateInteractionType;
import static br.com.voxage.chat.botintegration.utils.AppLogger.log;


public class FoneAcessoria {
	@SuppressWarnings("serial")
	public static BotState load(BotOmnilink bot) {
		return new BotState("/") {{
			setId("FONE_ASSESSORIA");
			setMaxInputError(1);
			
			setBotStateInteractionType(BotStateInteractionType.NO_INPUT);
			
			setAsyncPosFunction((botState, inputResult) ->CompletableFuture.supplyAsync(()->{
				BotStateFlow botStateFlow = new BotStateFlow();
				DadosFluxo dadosFluxo= bot.getDadosFluxo();
				botStateFlow.flow = BotStateFlow.Flow.CONTINUE;
				
				Assessoria customerInfo = null;
				
				try {
					customerInfo = BotOmnilinkIntegration.getAssessoria(bot, dadosFluxo.getCpfCnpj());
					bot.setAssessoria(customerInfo);
					log.info(null, customerInfo.toString());
					if(Integer.parseInt(bot.getTitulos().getDadosTitulos().getQtdeAssessoria()) == 0) {
						botStateFlow.navigationKey = BotOmnilink.STATES.ESCOLHER_TITULO;
					}else if(Integer.parseInt(bot.getTitulos().getDadosTitulos().getQtdeAssessoria()) > 1) {
						bot.getUserSession().put("CLIENTINFO_Transfer", "Mais de Uma Assessoria");
						botStateFlow.navigationKey = BotOmnilink.STATES.MULTI_ASSESSORIA;
					}else {
						botStateFlow.navigationKey = BotOmnilink.STATES.INFO_ASSESSORIA;
					}
				}catch(Exception e) {
					if(bot.getError() == 404) {
						botStateFlow.navigationKey = BotOmnilink.STATES.ESCOLHER_TITULO;
					}else {
						bot.getUserSession().put("CLIENTINFO_Transfer", "Erro de Integração");
						botStateFlow.navigationKey = BotOmnilink.STATES.ERRO_ASSE;	
					}
					
				}				
				return botStateFlow;
			}));
			
			setNextNavigationMap(new HashMap<String, String>(){{
				put(BotOmnilink.STATES.ESCOLHER_TITULO, "#ESCOLHER_TITULO");
				put(BotOmnilink.STATES.MULTI_ASSESSORIA, "#MULTI_ASSESSORIA");
				put(BotOmnilink.STATES.INFO_ASSESSORIA, "#INFO_ASSESSORIA");
				put(BotOmnilink.STATES.SDADOS, "#SDADOS");
				put(BotOmnilink.STATES.ERRO_ASSE, "#ERRO_ASSE");
                put("MAX_INPUT_ERROR", "/ATENDENTE");
			}});
		}};
	}
}
