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

public class SerieEspelhamento {
	@SuppressWarnings("serial")
	public static BotState load(BotOmnilink bot) {
		return new BotState("/") {{
				setId("SERIE_ESP");
				
				setBotStateInteractionType(BotStateInteractionType.DIRECT_INPUT);
				
				setProcessDirectInputFunction((botState, userInputs)->{
					BotInputResult botInputResult = new BotInputResult();
					DadosFluxo dadosFluxo = bot.getDadosFluxo();
					List<AttendantClientInfo> att;
					att = bot.getcInfo();
					botInputResult.setResult(BotInputResult.Result.OK);
					
					String userInput = userInputs.getConcatenatedInputs();
					dadosFluxo.setSerie(userInput);
					
					if("sair".equals(userInput.toLowerCase())){
						botInputResult.setIntentName(BotOmnilink.STATES.FINALIZAR);
					}else if((dadosFluxo.getSerie().matches("[0-9,]+")) && (dadosFluxo.getSerie().length() >= 1) && (dadosFluxo.getSerie().length() <= 24)) {
						botInputResult.setIntentName(BotOmnilink.STATES.CONS_CONTRATO);
					}else {
						att.get(0).setValue("Obter S�rie Espelhamento - S�rie Inv�lida");
						bot.setcInfo(att);
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
					put(BotOmnilink.STATES.CONS_CONTRATO, "#CONS_CONTRATO");
					put(BotOmnilink.STATES.FINALIZAR, "#FINALIZAR");
	                put("MAX_INPUT_ERROR", "/ATENDENTE");
	                put("MAX_NO_INPUT", "/FINALIZAR"); 
				}});
		}};
	}
}
		
		
				
