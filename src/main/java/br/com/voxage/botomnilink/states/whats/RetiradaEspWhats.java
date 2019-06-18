package br.com.voxage.botomnilink.states.whats;

import java.util.HashMap;

import br.com.voxage.botomnilink.BotOmnilink;
import br.com.voxage.botomnilink.models.DadosFluxo;
import br.com.voxage.vbot.BotInputResult;
import br.com.voxage.vbot.BotState;
import br.com.voxage.vbot.BotStateFlow;
import br.com.voxage.vbot.BotStateInteractionType;

public class RetiradaEspWhats {
	@SuppressWarnings("serial")
	public static BotState load(BotOmnilink bot) {
		return new BotState("/") {{
			setId("RETIRAR_ESP_WHATS");
			
			setBotStateInteractionType(BotStateInteractionType.DIRECT_INPUT);
			
			setProcessDirectInputFunction((botState, userInputs)->{
				BotInputResult botInputResult = new BotInputResult();
				DadosFluxo dadosFluxo = bot.getDadosFluxo();
				botInputResult.setResult(BotInputResult.Result.OK);
				
				String userInput = userInputs.getConcatenatedInputs().trim();
				dadosFluxo.setOpEspelha(userInput);
				
				System.out.println("!!!!!!!!!!!!!!");
				System.out.println(userInput);
				
				String str = userInput.toLowerCase();
				
				if(str.equals("sair")) {
					userInput = "7";
				}
				
				switch(userInput.toLowerCase()) {
					case"1 - retirada ou espelhamento de sinal":
						try {
							botInputResult.setIntentName(BotOmnilink.STATES.TIPO_ESPELHAMENTO);
						}catch(Exception e) {
							botInputResult.setResult(BotInputResult.Result.ERROR);
						}
						break;
					case"2 - outros assuntos":
						try {
							botInputResult.setIntentName(BotOmnilink.STATES.OUTROS);
						}catch(Exception e) {
							botInputResult.setResult(BotInputResult.Result.ERROR);
						}
						break;
					case"3 - finalizar":
						try {
							botInputResult.setIntentName(BotOmnilink.STATES.FINALIZAR);
						}catch(Exception e) {
							botInputResult.setResult(BotInputResult.Result.ERROR);
						}
						break;
					case"1":
						try {
							botInputResult.setIntentName(BotOmnilink.STATES.TIPO_ESPELHAMENTO);
						}catch(Exception e) {
							botInputResult.setResult(BotInputResult.Result.ERROR);
						}
						break;
					case"2":
						try {
							botInputResult.setIntentName(BotOmnilink.STATES.OUTROS);
						}catch(Exception e) {
							botInputResult.setResult(BotInputResult.Result.ERROR);
						}
						break;
					case"3":
						try {
							botInputResult.setIntentName(BotOmnilink.STATES.FINALIZAR);
						}catch(Exception e) {
							botInputResult.setResult(BotInputResult.Result.ERROR);
						}
						break;
					case "7":
						try {
							dadosFluxo.setOption("7");
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
				put(BotOmnilink.STATES.TIPO_ESPELHAMENTO, "#TIPO_ESPELHAMENTO");
				put(BotOmnilink.STATES.OUTROS, "#OUTROS");
				put(BotOmnilink.STATES.FINALIZAR, "#FINALIZAR");
                put("MAX_INPUT_ERROR", "/FINALIZAR");
                put("MAX_NO_INPUT", "/FINALIZAR"); 
			}});
		}};
	}
}