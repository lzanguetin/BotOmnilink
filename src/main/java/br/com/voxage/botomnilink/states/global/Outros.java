package br.com.voxage.botomnilink.states.global;

import java.util.HashMap;

import br.com.voxage.botomnilink.BotOmnilink;
import br.com.voxage.vbot.BotState;
import br.com.voxage.vbot.BotStateFlow;
import br.com.voxage.vbot.BotStateInteractionType;

public class Outros {
	@SuppressWarnings("serial")
	public static BotState load(BotOmnilink bot) {
		return new BotState("/") {{
			setId("OUTROS");
			
			setBotStateInteractionType(BotStateInteractionType.NO_INPUT);
			
			setPosFunction((botState, inputResult) ->{
				if(bot.getSessionId().contains("whatsapp")) {
					return(new BotStateFlow(BotStateFlow.Flow.CONTINUE, "#OUTROS_WHATS"));
				}else {
					return(new BotStateFlow(BotStateFlow.Flow.CONTINUE, "#OUTROS_WEB"));
					
				}
			});
			
			setNextNavigationMap(new HashMap<String, String>(){{
				put("#OUTROS_WHATS", "#OUTROS_WHATS");
				put("#OUTROS_WEB", "#OUTROS_WEB");				
			}});
		}};
	}
}
