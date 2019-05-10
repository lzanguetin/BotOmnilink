package br.com.voxage.botomnilink.states.utils;

import java.util.HashMap;

import br.com.voxage.botomnilink.BotOmnilink;
import br.com.voxage.vbot.BotState;
import br.com.voxage.vbot.BotStateFlow;
import br.com.voxage.vbot.BotStateInteractionType;

public class TipoTecnologiaValida {
	@SuppressWarnings("serial")
	public static BotState load(BotOmnilink bot) {
		return new BotState("/") {{
				setId("TIPO_TEC_VALIDA");
				
				setBotStateInteractionType(BotStateInteractionType.NO_INPUT);
				
				setPosFunction((botState, inputResult) ->{
					if(bot.getSessionId().contains("whatsapp")) {
						return(new BotStateFlow(BotStateFlow.Flow.CONTINUE, "#TEC_VALIDA_WHATS"));
					}else {
						return(new BotStateFlow(BotStateFlow.Flow.CONTINUE, "#TEC_VALIDA_WEB"));
						
					}
				});
				
				setNextNavigationMap(new HashMap<String, String>(){{
					put("#TEC_VALIDA_WHATS", "#TEC_VALIDA_WHATS");
					put("#TEC_VALIDA_WEB", "#TEC_VALIDA_WEB");				
				}});
		}};
	}
}
