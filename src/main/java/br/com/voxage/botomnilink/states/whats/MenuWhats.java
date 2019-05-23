package br.com.voxage.botomnilink.states.whats;

import java.util.HashMap;

import br.com.voxage.botomnilink.BotOmnilink;
import br.com.voxage.botomnilink.models.DadosFluxo;
import br.com.voxage.vbot.BotInputResult;
import br.com.voxage.vbot.BotState;
import br.com.voxage.vbot.BotStateFlow;
import br.com.voxage.vbot.BotStateInteractionType;

public class MenuWhats {
	@SuppressWarnings("serial")
	public static BotState load(BotOmnilink bot) {
		return new BotState("/") {{
			setId("MENU_WHATS");
			
			setBotStateInteractionType(BotStateInteractionType.DIRECT_INPUT);
			
			setProcessDirectInputFunction((botState, userInputs)->{
				BotInputResult botInputResult = new BotInputResult();
				DadosFluxo dadosFluxo = bot.getDadosFluxo();
				botInputResult.setResult(BotInputResult.Result.OK);
				
				String userInput = userInputs.getConcatenatedInputs();
				dadosFluxo.setMax(1);
				
				switch(userInput) {
					case"1 - Agendamento":
						try {
							dadosFluxo.setOption("1");
							botInputResult.setIntentName(BotOmnilink.STATES.AGENDAMENTO);
						}catch(Exception e){
							botInputResult.setResult(BotInputResult.Result.ERROR);
						}
						break;
					case"2 - Cancelamento de Contrato":
						try {
							dadosFluxo.setOption("2");
							botInputResult.setIntentName(BotOmnilink.STATES.CANCELAMENTO);
						}catch(Exception e){
							botInputResult.setResult(BotInputResult.Result.ERROR);
						}
						break;
					case"3 - Espelhamento de Sinal":
						try {
							dadosFluxo.setOption("3");
							botInputResult.setIntentName(BotOmnilink.STATES.TOKEN);
						}catch(Exception e){
							botInputResult.setResult(BotInputResult.Result.ERROR);
						}
						break;
					case"4 - Financeiro":
						try {
							dadosFluxo.setOption("4");
							botInputResult.setIntentName(BotOmnilink.STATES.TOKEN);
						}catch(Exception e){
							botInputResult.setResult(BotInputResult.Result.ERROR);
						}
						break;
					case"5 - Suporte":
						try {
							dadosFluxo.setOption("5");
							botInputResult.setIntentName(BotOmnilink.STATES.SUPORTE);
						}catch(Exception e){
							botInputResult.setResult(BotInputResult.Result.ERROR);
						}
						break;
					case"6 - Ativação":
						try {
							dadosFluxo.setOption("6");
							botInputResult.setIntentName(BotOmnilink.STATES.ATIVACAO);
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
				put(BotOmnilink.STATES.ATENDENTE, "/ATENDENTE");
				put(BotOmnilink.STATES.AGENDAMENTO, "/AGENDAMENTO");
				put(BotOmnilink.STATES.CANCELAMENTO, "/CANCELAMENTO");
				put(BotOmnilink.STATES.SUPORTE, "/SUPORTE");
				put(BotOmnilink.STATES.ATIVACAO, "/ATIVACAO");
				put(BotOmnilink.STATES.TOKEN, "/TOKEN");
                put("MAX_INPUT_ERROR", "/FINALIZAR");
                put("MAX_NO_INPUT", "/FINALIZAR");  
			}});
		}};
	}
}