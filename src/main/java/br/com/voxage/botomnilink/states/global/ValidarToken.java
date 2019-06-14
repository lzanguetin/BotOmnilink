package br.com.voxage.botomnilink.states.global;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

import br.com.voxage.botomnilink.BotOmnilink;
import br.com.voxage.botomnilink.BotOmnilinkIntegration;
import br.com.voxage.botomnilink.models.Token;
import br.com.voxage.vbot.BotState;
import br.com.voxage.vbot.BotStateFlow;
import br.com.voxage.vbot.BotStateInteractionType;

public class ValidarToken {
	@SuppressWarnings("serial")
	public static BotState load(BotOmnilink bot) {
		return new BotState("/") {{
			setId("TOKEN");
			
			setBotStateInteractionType(BotStateInteractionType.NO_INPUT);
			
			setAsyncPosFunction((botState, inputResult)-> CompletableFuture.supplyAsync(()->{
				BotStateFlow botStateFlow = new BotStateFlow();
				botStateFlow.flow = BotStateFlow.Flow.CONTINUE;
				
				Token customerInfo = null;
				
				if(bot.getMicro() != null) {
					bot.getMicro().setAccessToken(null);
				}
				
				try {
					customerInfo = BotOmnilinkIntegration.validarUsuario(bot);
					bot.setToken(customerInfo);
					
					botStateFlow.navigationKey = BotOmnilink.STATES.CPFCNPJ;
				}catch(Exception e) {
					botStateFlow.navigationKey = BotOmnilink.STATES.ATENDENTE;					
				}
				return botStateFlow;
			}));
			
			setNextNavigationMap(new HashMap<String, String>(){{
				put(BotOmnilink.STATES.CPFCNPJ, "#CPFCNPJ");
				put(BotOmnilink.STATES.ATENDENTE, "#ATENDENTE");
			}});
		}};
	}
}