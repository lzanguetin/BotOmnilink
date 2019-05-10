package br.com.voxage.botomnilink.states.web;

import java.util.HashMap;

import br.com.voxage.botomnilink.BotOmnilink;
import br.com.voxage.vbot.BotInputResult;
import br.com.voxage.vbot.BotState;
import br.com.voxage.vbot.BotStateFlow;
import br.com.voxage.vbot.BotStateInteractionType;

public class FinanceiroWeb {
	@SuppressWarnings("serial")
	public static BotState load(BotOmnilink bot) {
		return new BotState("/") {{
			setId("FINANCEIRO_WEB");
			
			setBotStateInteractionType(BotStateInteractionType.DIRECT_INPUT);
			
			setProcessDirectInputFunction((botState, userInputs)->{
				BotInputResult botInputResult = new BotInputResult();
				botInputResult.setResult(BotInputResult.Result.OK);
				
				String userInput = userInputs.getConcatenatedInputs();
				
				switch(userInput) {
				case"1 – Segunda via de Boletos":
					try {
						botInputResult.setIntentName(BotOmnilink.STATES.TITULOS);
					}catch(Exception e) {
						botInputResult.setResult(BotInputResult.Result.ERROR);
					}
					break;
				case"2 – Informar um Pagamento":
					try {
						botInputResult.setIntentName(BotOmnilink.STATES.INFORME);
					}catch(Exception e) {
						botInputResult.setResult(BotInputResult.Result.ERROR);
					}
					break;
				case"3 – Outros Assuntos Financeiros":
					try {
						botInputResult.setIntentName(BotOmnilink.STATES.ATENDENTE);
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
				put(BotOmnilink.STATES.TITULOS, "#TITULOS");
				put(BotOmnilink.STATES.INFORME, "#INFORME");
				put(BotOmnilink.STATES.ATENDENTE, "#ATENDENTE");
                put("MAX_INPUT_ERROR", "#FINALIZAR");
                put("MAX_NO_INPUT", "#FINALIZAR"); 
			}});
		}};
	}
}