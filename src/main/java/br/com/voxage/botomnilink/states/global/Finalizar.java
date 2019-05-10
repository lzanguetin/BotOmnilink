package br.com.voxage.botomnilink.states.global;

import java.util.HashMap;

import br.com.voxage.botomnilink.BotOmnilink;
import br.com.voxage.vbot.BotState;
import br.com.voxage.vbot.BotStateFlow;
import br.com.voxage.vbot.BotStateInteractionType;

public class Finalizar {
	@SuppressWarnings("serial")
	public static BotState load(BotOmnilink bot) {
		return new BotState("/") {{
			setId("FINALIZAR");
			
			setBotStateInteractionType(BotStateInteractionType.NO_INPUT);
				
			setPosFunction((botState, inputResult) ->{
				BotStateFlow botStateFlow = new BotStateFlow();
				botStateFlow.flow = BotStateFlow.Flow.CONTINUE;
				botStateFlow.navigationKey = "TERMINATE";
					
				return botStateFlow;
			});
				
			setNextNavigationMap(new HashMap<String, String>(){{
				put("TERMINATE", "/TERMINATE");
			}});
		}};
	}
}