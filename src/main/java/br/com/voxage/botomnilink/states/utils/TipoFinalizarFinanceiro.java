package br.com.voxage.botomnilink.states.utils;

import java.util.HashMap;

import br.com.voxage.botomnilink.BotOmnilink;
import br.com.voxage.vbot.BotState;
import br.com.voxage.vbot.BotStateFlow;
import br.com.voxage.vbot.BotStateInteractionType;

public class TipoFinalizarFinanceiro {
	@SuppressWarnings("serial")
	public static BotState load(BotOmnilink bot) {
		return new BotState("/") {{
			setId("TIPO_FINALIZAR_FINANCEIRO");
			
			setBotStateInteractionType(BotStateInteractionType.NO_INPUT);
			
			setPosFunction((botState, inputResult) ->{
				BotStateFlow botStateFlow = new BotStateFlow();
				botStateFlow.flow = BotStateFlow.Flow.CONTINUE;
				
				if(bot.getSessionId().contains("whatsapp")) {
					botStateFlow.navigationKey = BotOmnilink.STATES.FINALIZAR_FINANCEIRO_WHATS;
				}else {
					botStateFlow.navigationKey = BotOmnilink.STATES.FINALIZAR_FINANCEIRO_WEB;
				}
				return botStateFlow;
			});
			
			setNextNavigationMap(new HashMap<String, String>(){{
				put(BotOmnilink.STATES.FINALIZAR_FINANCEIRO_WHATS, "#FINALIZAR_FINANCEIRO_WHATS");
				put(BotOmnilink.STATES.FINALIZAR_FINANCEIRO_WEB, "#FINALIZAR_FINANCEIRO_WEB");
			}});
		}};
	}
}
