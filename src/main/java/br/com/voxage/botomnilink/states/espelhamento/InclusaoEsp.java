package br.com.voxage.botomnilink.states.espelhamento;

import java.util.HashMap;

import br.com.voxage.botomnilink.BotOmnilink;
import br.com.voxage.vbot.BotState;
import br.com.voxage.vbot.BotStateFlow;
import br.com.voxage.vbot.BotStateInteractionType;

public class InclusaoEsp {
	@SuppressWarnings("serial")
	public static BotState load(BotOmnilink bot) {
		return new BotState("/") {{
				setId("INCLUSAO_ESP");
				
				setBotStateInteractionType(BotStateInteractionType.NO_INPUT);
				
				setPosFunction((botState, inputResult) ->{
					if(bot.getSessionId().contains("whatsapp")) {
						return(new BotStateFlow(BotStateFlow.Flow.CONTINUE, "#INCLUSAO_ESP_WHATS"));
					}else {
						return(new BotStateFlow(BotStateFlow.Flow.CONTINUE, "#INCLUSAO_ESP_WEB"));
						
					}
				});
				
				setNextNavigationMap(new HashMap<String, String>(){{
					put("#INCLUSAO_ESP_WHATS", "#INCLUSAO_ESP_WHATS");
					put("#INCLUSAO_ESP_WEB", "#INCLUSAO_ESP_WEB");				
				}});
		}};
	}
}
