package br.com.voxage.botomnilink.states.web;

import java.util.HashMap;

import br.com.voxage.botomnilink.BotOmnilink;
import br.com.voxage.vbot.BotInputResult;
import br.com.voxage.vbot.BotState;
import br.com.voxage.vbot.BotStateFlow;
import br.com.voxage.vbot.BotStateInteractionType;

public class OutrosWeb {
	@SuppressWarnings("serial")
	public static BotState load(BotOmnilink bot) {
		return new BotState("/") {{
			setId("OUTROS_WEB");
			
			setBotStateInteractionType(BotStateInteractionType.DIRECT_INPUT);
			
			setProcessDirectInputFunction((botState, userInputs)->{
				BotInputResult botInputResult = new BotInputResult();
				botInputResult.setResult(BotInputResult.Result.OK);
				
				String userInput = userInputs.getConcatenatedInputs().trim();
				String auto = BotOmnilink.readString("id.flag.obterAuto");
				
				System.out.println("!!!!!!!!!!!!!!");
				System.out.println(userInput);
				
				switch(userInput) {
					case"1 - Agendamento":
						try {
							botInputResult.setIntentName(BotOmnilink.STATES.OAGENDAMENTO);
						}catch(Exception e) {
							botInputResult.setResult(BotInputResult.Result.ERROR);
						}
						break;
					case"2 - Cancelamento de Contrato":
						try {
							botInputResult.setIntentName(BotOmnilink.STATES.OCANCELAMENTO);
						}catch(Exception e) {
							botInputResult.setResult(BotInputResult.Result.ERROR);
						}
						break;
					case"3 - Espelhamento de Sinal":
						try {
							if("true".equals(auto)) {
								botInputResult.setIntentName(BotOmnilink.STATES.TIPO_ESPELHAMENTO);
							}else {
								botInputResult.setIntentName(BotOmnilink.STATES.ATENDENTE);
							}
						}catch(Exception e) {
							botInputResult.setResult(BotInputResult.Result.ERROR);
						}
						break;
					case"4 - Financeiro":
						try {
							if("true".equals(auto)) {
								botInputResult.setIntentName(BotOmnilink.STATES.TIPO_FINANCEIRO);
							}else {
								botInputResult.setIntentName(BotOmnilink.STATES.ATENDENTE);
							}
						}catch(Exception e) {
							botInputResult.setResult(BotInputResult.Result.ERROR);
						}
						break;
					case"5 - Suporte":
						try {
							botInputResult.setIntentName(BotOmnilink.STATES.OSUPORTE);
						}catch(Exception e) {
							botInputResult.setResult(BotInputResult.Result.ERROR);
						}
						break;
					case"6 - Ativação":
						try {
							botInputResult.setIntentName(BotOmnilink.STATES.OATIVACAO);
						}catch(Exception e) {
							botInputResult.setResult(BotInputResult.Result.ERROR);
						}
						break;
					default:
						botInputResult.setIntentName(BotOmnilink.STATES.ATENDENTE);
				}
				
				bot.getUserSession().put("CLIENTINFO_Transfer", userInput);
				
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
				put(BotOmnilink.STATES.OAGENDAMENTO, "/OAGENDAMENTO");
				put(BotOmnilink.STATES.OCANCELAMENTO, "/OCANCELAMENTO");
				put(BotOmnilink.STATES.OSUPORTE, "/OSUPORTE");
				put(BotOmnilink.STATES.OATIVACAO, "/OATIVACAO");
				put(BotOmnilink.STATES.TOKEN, "/TOKEN");
                put("MAX_INPUT_ERROR", "/FINALIZAR");
                put("MAX_NO_INPUT", "/FINALIZAR");  
			}});
		}};
	}
}