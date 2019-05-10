package br.com.voxage.botomnilink.states.utils;

import java.util.HashMap;

import br.com.voxage.botomnilink.BotOmnilink;
import br.com.voxage.vbot.BotState;
import br.com.voxage.vbot.BotStateFlow;
import br.com.voxage.vbot.BotStateInteractionType;

public class TipoFinanceiro {
	@SuppressWarnings("serial")
	public static BotState load(BotOmnilink bot) {
		return new BotState("/") {{
				setId("TIPO_FINANCEIRO");
				
				setBotStateInteractionType(BotStateInteractionType.NO_INPUT);
				
				setPosFunction((botState, inputResult) ->{
					if(bot.getSessionId().contains("whatsapp")) {
						return(new BotStateFlow(BotStateFlow.Flow.CONTINUE, "#FINANCEIRO_WHATS"));
					}else {
						return(new BotStateFlow(BotStateFlow.Flow.CONTINUE, "#FINANCEIRO_WEB"));
						
					}
				});
				
				setNextNavigationMap(new HashMap<String, String>(){{
					put("#FINANCEIRO_WHATS", "#FINANCEIRO_WHATS");
					put("#FINANCEIRO_WEB", "#FINANCEIRO_WEB");				
				}});
		}};
	}
}
