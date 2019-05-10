package br.com.voxage.botomnilink.states.utils;

import java.util.HashMap;

import br.com.voxage.botomnilink.BotOmnilink;
import br.com.voxage.vbot.BotState;
import br.com.voxage.vbot.BotStateFlow;
import br.com.voxage.vbot.BotStateInteractionType;

public class TipoFinalizarEspelhamento {
	@SuppressWarnings("serial")
	public static BotState load(BotOmnilink bot) {
		return new BotState("/") {{
			setId("TIPO_FINALIZAR_ESP");
			
			setBotStateInteractionType(BotStateInteractionType.NO_INPUT);
			
			setPosFunction((botState, inputResult) ->{
				if(bot.getSessionId().contains("whatsapp")) {
					return(new BotStateFlow(BotStateFlow.Flow.CONTINUE, "#FINALIZAR_ESP_WHATS"));
				}else {
					return(new BotStateFlow(BotStateFlow.Flow.CONTINUE, "#FINALIZAR_ESP_WEB"));
					
				}
			});
			
			setNextNavigationMap(new HashMap<String, String>(){{
				put("#FINALIZAR_ESP_WHATS", "#FINALIZAR_ESP_WHATS");
				put("#FINALIZAR_ESP_WEB", "#FINALIZAR_ESP_WEB");				
			}});
		}};
	}
}
