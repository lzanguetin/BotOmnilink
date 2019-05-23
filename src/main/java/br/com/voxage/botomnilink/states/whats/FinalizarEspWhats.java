package br.com.voxage.botomnilink.states.whats;

import java.util.HashMap;

import br.com.voxage.botomnilink.BotOmnilink;
import br.com.voxage.vbot.BotInputResult;
import br.com.voxage.vbot.BotState;
import br.com.voxage.vbot.BotStateFlow;
import br.com.voxage.vbot.BotStateInteractionType;

public class FinalizarEspWhats {
	@SuppressWarnings("serial")
	public static BotState load(BotOmnilink bot) {
		return new BotState("/") {{
			setId("FINALIZAR_ESP_WHATS");
					
			setBotStateInteractionType(BotStateInteractionType.DIRECT_INPUT);
					
			setProcessDirectInputFunction((botState, userInputs)->{
				BotInputResult botInputResult = new BotInputResult();
				botInputResult.setResult(BotInputResult.Result.OK);
				
				String userInput = userInputs.getConcatenatedInputs();
					
				switch(userInput) {
					case"1 � Consultar outro n�mero de s�rie":
						try {
							botInputResult.setIntentName(BotOmnilink.STATES.TIPO_ESPELHAMENTO);
						}catch(Exception e){
							botInputResult.setResult(BotInputResult.Result.ERROR);
						}
						break;
					case"2 � Falar de outros assuntos":
						try {
							botInputResult.setIntentName(BotOmnilink.STATES.OUTROS);
						}catch(Exception e){
							botInputResult.setResult(BotInputResult.Result.ERROR);
						}
						break;
					case"3 � Finalizar atendimento":
						try {
							botInputResult.setIntentName(BotOmnilink.STATES.FINALIZAR);
						}catch(Exception e){
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
				put(BotOmnilink.STATES.TIPO_ESPELHAMENTO, "/TIPO_ESPELHAMENTO");
				put(BotOmnilink.STATES.FINALIZAR, "/FINALIZAR");
				put(BotOmnilink.STATES.OUTROS, "/OUTROS");
	            put("MAX_INPUT_ERROR", "/FINALIZAR");
	            put("MAX_NO_INPUT", "/FINALIZAR"); 
			}});
		}};
	}
}