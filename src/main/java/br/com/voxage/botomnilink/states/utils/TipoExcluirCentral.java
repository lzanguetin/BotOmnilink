package br.com.voxage.botomnilink.states.utils;

import java.util.HashMap;

import br.com.voxage.botomnilink.BotOmnilink;
import br.com.voxage.vbot.BotState;
import br.com.voxage.vbot.BotStateFlow;
import br.com.voxage.vbot.BotStateInteractionType;

public class TipoExcluirCentral {
	@SuppressWarnings({"serial"})
	public static BotState load(BotOmnilink bot) {
		return new BotState("/") {{
			setId("TIPO_EXCLUIR_CENTRAL");
			
			setBotStateInteractionType(BotStateInteractionType.NO_INPUT);
			
			setPosFunction((botState, inputResult) ->{
				if(bot.getSessionId().contains("whatsapp")) {
					return(new BotStateFlow(BotStateFlow.Flow.CONTINUE, "#EXCLUIR_CENTRAL_WHATS"));
				}else {
					return(new BotStateFlow(BotStateFlow.Flow.CONTINUE, "#EXCLUIR_CENTRAL_WEB"));
					
				}
			});
			
			setNextNavigationMap(new HashMap<String, String>(){{
				put("#EXCLUIR_CENTRAL_WHATS", "#EXCLUIR_CENTRAL_WHATS");
				put("#EXCLUIR_CENTRAL_WEB", "#EXCLUIR_CENTRAL_WEB");				
			}});
	}};
}
}
