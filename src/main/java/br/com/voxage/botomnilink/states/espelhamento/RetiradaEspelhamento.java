package br.com.voxage.botomnilink.states.espelhamento;

import java.util.HashMap;

import br.com.voxage.botomnilink.BotOmnilink;
import br.com.voxage.vbot.BotState;
import br.com.voxage.vbot.BotStateFlow;
import br.com.voxage.vbot.BotStateInteractionType;

public class RetiradaEspelhamento {
	@SuppressWarnings("serial")
	public static BotState load(BotOmnilink bot) {
		return new BotState("/") {{
			setId("RETIRAR_ESP");
			
			setBotStateInteractionType(BotStateInteractionType.NO_INPUT);
			
			setPosFunction((botState, inputResult) ->{
				if(bot.getSessionId().contains("whatsapp")) {
					return(new BotStateFlow(BotStateFlow.Flow.CONTINUE, "#RETIRAR_ESP_WHATS"));
				}else {
					return(new BotStateFlow(BotStateFlow.Flow.CONTINUE, "#RETIRAR_ESP_WEB"));
					
				}
			});
			
			setNextNavigationMap(new HashMap<String, String>(){{
				put("#RETIRAR_ESP_WHATS", "#RETIRAR_ESP_WHATS");
				put("#RETIRAR_ESP_WEB", "#RETIRAR_ESP_WEB");				
			}});
	}};
}
}
