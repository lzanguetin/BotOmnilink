package br.com.voxage.botomnilink.states.espelhamento;

import java.util.HashMap;

import br.com.voxage.basicvalidators.CNPJValidator;
import br.com.voxage.botomnilink.BotOmnilink;
import br.com.voxage.botomnilink.models.DadosFluxo;
import br.com.voxage.vbot.BotInputResult;
import br.com.voxage.vbot.BotState;
import br.com.voxage.vbot.BotStateFlow;
import br.com.voxage.vbot.BotStateInteractionType;

public class CnpjEspelhamento {
	@SuppressWarnings("serial")
	public static BotState load(BotOmnilink bot) {
		return new BotState("/") {{
			setId("CNPJ_ESP");
			
			setBotStateInteractionType(BotStateInteractionType.DIRECT_INPUT);
			
			setProcessDirectInputFunction((botState, userInputs)->{
				BotInputResult botInputResult = new BotInputResult();
				DadosFluxo dadosFluxo = bot.getDadosFluxo();
				botInputResult.setResult(BotInputResult.Result.OK);
				
				String userInput = userInputs.getConcatenatedInputs().replaceAll("\\D+", "");;
				
				if((CNPJValidator.isValidCNPJ(userInput)) == true){
					dadosFluxo.setCnpjEsp(userInput);
					botInputResult.setResult(BotInputResult.Result.OK);
				}else {
					botInputResult.setResult(BotInputResult.Result.ERROR);
				}
					
				return botInputResult;
			});
			
			setPosFunction((botState, inputResult) ->{
				BotStateFlow botStateFlow = new BotStateFlow();
				botStateFlow.flow = BotStateFlow.Flow.CONTINUE;
				botStateFlow.navigationKey = BotOmnilink.STATES.PORTA_ESP;
				
				return botStateFlow;
			});
			
			setNextNavigationMap(new HashMap<String, String>(){{
				put(BotOmnilink.STATES.PORTA_ESP, "#PORTA_ESP");
                put("MAX_INPUT_ERROR", "/FINALIZAR");
                put("MAX_NO_INPUT", "/FINALIZAR");
			}});
		}};
	}
}