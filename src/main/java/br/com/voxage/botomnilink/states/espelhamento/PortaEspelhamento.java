package br.com.voxage.botomnilink.states.espelhamento;

import java.util.HashMap;
import java.util.List;

import br.com.voxage.botomnilink.BotOmnilink;
import br.com.voxage.botomnilink.models.DadosFluxo;
import br.com.voxage.chat.botintegration.entities.AttendantClientInfo;
import br.com.voxage.vbot.BotInputResult;
import br.com.voxage.vbot.BotState;
import br.com.voxage.vbot.BotStateFlow;
import br.com.voxage.vbot.BotStateInteractionType;

public class PortaEspelhamento {
	@SuppressWarnings("serial")
	public static BotState load(BotOmnilink bot) {
		return new BotState("/") {{
			setId("PORTA_ESP");
			
			setBotStateInteractionType(BotStateInteractionType.DIRECT_INPUT);
			
			setProcessDirectInputFunction((botState, userInputs)->{
				BotInputResult botInputResult = new BotInputResult();
				DadosFluxo dadosFluxo = bot.getDadosFluxo();
				List<AttendantClientInfo> att;
				att = bot.getcInfo();
				botInputResult.setResult(BotInputResult.Result.OK);
				
				String userInput = userInputs.getConcatenatedInputs();
				
				if("sair".equals(userInput.toLowerCase())){
					botInputResult.setIntentName(BotOmnilink.STATES.FINALIZAR);
				}else if((userInput.length() == 4)&&(userInput.matches("[0-9]+"))){
					dadosFluxo.setPortaEsp(userInput);
					botInputResult.setIntentName(BotOmnilink.STATES.INCLUIR_ESP);
				}else {
					att.get(0).setValue("Obter Porta Espelhamento - Porta Inválida");
					bot.setcInfo(att);
					botInputResult.setResult(BotInputResult.Result.ERROR);
				}
					
				return botInputResult;
			});
			
			setPosFunction((botState, inputResult) ->{
				BotStateFlow botStateFlow = new BotStateFlow();
				botStateFlow.flow = BotStateFlow.Flow.CONTINUE;
				botStateFlow.navigationKey = inputResult.getIntentName();
				
				return botStateFlow;
			});
			
			setNextNavigationMap(new HashMap<String, String>(){{
				put(BotOmnilink.STATES.INCLUIR_ESP, "#INCLUIR_ESP");
				put(BotOmnilink.STATES.FINALIZAR, "#FINALIZAR");
                put("MAX_INPUT_ERROR", "/ATENDENTE");
                put("MAX_NO_INPUT", "/FINALIZAR");
			}});
		}};
	}
}