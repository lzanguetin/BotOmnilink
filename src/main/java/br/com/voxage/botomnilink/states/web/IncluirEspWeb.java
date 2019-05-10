package br.com.voxage.botomnilink.states.web;

import java.util.HashMap;

import br.com.voxage.botomnilink.BotOmnilink;
import br.com.voxage.vbot.BotInputResult;
import br.com.voxage.vbot.BotState;
import br.com.voxage.vbot.BotStateFlow;
import br.com.voxage.vbot.BotStateInteractionType;

public class IncluirEspWeb {
	@SuppressWarnings("serial")
	public static BotState load(BotOmnilink bot) {
		return new BotState("/") {{
			setId("INCLUIR_ESP_WEB");
			
			setBotStateInteractionType(BotStateInteractionType.DIRECT_INPUT);
			
			setProcessDirectInputFunction((botState, userInputs)->{
				BotInputResult botInputResult = new BotInputResult();
				botInputResult.setResult(BotInputResult.Result.OK);
				
				String userInput = userInputs.getConcatenatedInputs();
				
				switch(userInput) {
				case"Sim":
					try {
						botInputResult.setIntentName(BotOmnilink.STATES.CNPJ_ESP);
					}catch(Exception e) {
						botInputResult.setResult(BotInputResult.Result.ERROR);
					}
					break;
				case"Não":
					try {
						botInputResult.setIntentName(BotOmnilink.STATES.RETIRAR_ESP);
					}catch(Exception e) {
						botInputResult.setResult(BotInputResult.Result.ERROR);
					}
					break;
				default:
					botInputResult.setResult(BotInputResult.Result.ERROR);
			}
				return botInputResult;
			});
			
			setPosFunction((botState, inputResult)->{
				BotStateFlow botStateFlow = new BotStateFlow();
				botStateFlow.flow = BotStateFlow.Flow.CONTINUE;
				botStateFlow.navigationKey = inputResult.getIntentName();
				
				return botStateFlow;
			});
			
			setNextNavigationMap(new HashMap<String, String>(){{
				put(BotOmnilink.STATES.CNPJ_ESP, "/CNPJ_ESP");
				put(BotOmnilink.STATES.RETIRAR_ESP, "/RETIRAR_ESP");
                put("MAX_INPUT_ERROR", "/FINALIZAR");
                put("MAX_NO_INPUT", "/FINALIZAR"); 
			}});
		}};
	}
}