package br.com.voxage.botomnilink.states.whats;

import java.util.HashMap;

import br.com.voxage.botomnilink.BotOmnilink;
import br.com.voxage.botomnilink.models.DadosFluxo;
import br.com.voxage.vbot.BotInputResult;
import br.com.voxage.vbot.BotState;
import br.com.voxage.vbot.BotStateFlow;
import br.com.voxage.vbot.BotStateInteractionType;

public class EscolherTituloWhats {
	@SuppressWarnings("serial")
	public static BotState load(BotOmnilink bot) {
		return new BotState("/") {{
			setId("ESCOLHER_TITULO_WHATS");
			
			setBotStateInteractionType(BotStateInteractionType.DIRECT_INPUT);
			
			setProcessDirectInputFunction((botState, userInputs)->{
				BotInputResult botInputResult = new BotInputResult();
				DadosFluxo dadosFluxo = bot.getDadosFluxo();
				botInputResult.setResult(BotInputResult.Result.OK);
				
				String userInput = userInputs.getConcatenatedInputs().trim();
				
				switch(userInput) {
					case"1 � Boletos a Vencer":
						try {
							dadosFluxo.setStatus(1);
							dadosFluxo.setTitle(1);
							botInputResult.setIntentName(BotOmnilink.STATES.ENVIAR_TITULOS);
						}catch(Exception e) {
							botInputResult.setResult(BotInputResult.Result.ERROR);
						}
						break;
					case"2 � Boletos Atrasados":
						try {
							dadosFluxo.setStatus(2);
							dadosFluxo.setTitle(2);
							botInputResult.setIntentName(BotOmnilink.STATES.ENVIAR_TITULOS);
						}catch(Exception e) {
							botInputResult.setResult(BotInputResult.Result.ERROR);
						}
						break;
					case"3 � Todos os Boletos Pendentes":
						try {
							dadosFluxo.setStatus(3);
							dadosFluxo.setTitle(3);
							botInputResult.setIntentName(BotOmnilink.STATES.ENVIAR_TITULOS);
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
				put(BotOmnilink.STATES.ENVIAR_TITULOS, "/ENVIAR_TITULOS");
                put("MAX_INPUT_ERROR", "/FINALIZAR");
                put("MAX_NO_INPUT", "/FINALIZAR"); 
			}});
		}};
	}
}