package br.com.voxage.botomnilink.states.espelhamento;

import java.util.HashMap;

import br.com.voxage.botomnilink.BotOmnilink;
import br.com.voxage.vbot.BotState;
import br.com.voxage.vbot.BotStateFlow;
import br.com.voxage.vbot.BotStateInteractionType;

public class CentralInexistente {
	@SuppressWarnings("serial")
	public static BotState load(BotOmnilink bot) {
		return new BotState("/") {{
				setId("CENT_INEXIST");
				
				setBotStateInteractionType(BotStateInteractionType.NO_INPUT);
				
				setPosFunction((botState, inputResult) ->{
					if(bot.getSessionId().contains("whatsapp")) {
						return(new BotStateFlow(BotStateFlow.Flow.CONTINUE, "#CENT_INEXIST_WHATS"));
					}else {
						return(new BotStateFlow(BotStateFlow.Flow.CONTINUE, "#CENT_INEXIST_WEB"));
						
					}
				});
				
				setNextNavigationMap(new HashMap<String, String>(){{
					put("#CENT_INEXIST_WHATS", "#CENT_INEXIST_WHATS");
					put("#CENT_INEXIST_WEB", "#CENT_INEXIST_WEB");				
				}});
		}};
	}
}
