package br.com.voxage.botomnilink.states.whats;

import java.util.HashMap;

import br.com.voxage.botomnilink.BotOmnilink;
import br.com.voxage.botomnilink.models.Central;
import br.com.voxage.botomnilink.models.DadosFluxo;
import br.com.voxage.vbot.BotInputResult;
import br.com.voxage.vbot.BotState;
import br.com.voxage.vbot.BotStateFlow;
import br.com.voxage.vbot.BotStateInteractionType;

public class ExcluirCentralWhats {
	@SuppressWarnings({ "serial", "unused" })
	public static BotState load(BotOmnilink bot) {
		return new BotState("/") {{
			setId("EXCLUIR_CENTRAL_WHATS");
			
			setBotStateInteractionType(BotStateInteractionType.NO_INPUT);
			
			setPreFunction(botState ->{
				BotStateFlow botStateFlow = new BotStateFlow();
				Central cent = bot.getCentrais();
				botStateFlow.flow = BotStateFlow.Flow.CONTINUE;
				
				botState.setCustomField("central", cent.getNome());
				
				return botStateFlow;
			});
			
			setProcessDirectInputFunction((botState, userInputs)->{
				BotInputResult botInputResult = new BotInputResult();
				DadosFluxo dadosFluxo = bot.getDadosFluxo();
				botInputResult.setResult(BotInputResult.Result.OK);
				
				String userInput = userInputs.getConcatenatedInputs().trim();
				
				switch(userInput) {
					case"1 - Sim":
						try {
							botInputResult.setIntentName(BotOmnilink.STATES.REMOVER_ESP);
						}catch(Exception e){
							botInputResult.setResult(BotInputResult.Result.ERROR);
						}
						break;
					case"2 - Não":
						try {
							botInputResult.setIntentName(BotOmnilink.STATES.TIPO_FINALIZAR_ESP);
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
				put(BotOmnilink.STATES.REMOVER_ESP, "/REMOVER_ESP");
				put(BotOmnilink.STATES.TIPO_FINALIZAR_ESP, "/TIPO_FINALIZ_ARESP");
                put("MAX_INPUT_ERROR", "/FINALIZAR");
                put("MAX_NO_INPUT", "/FINALIZAR"); 
			}});
		}};
	}
}