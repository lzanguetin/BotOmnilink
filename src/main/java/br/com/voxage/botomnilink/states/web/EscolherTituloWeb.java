package br.com.voxage.botomnilink.states.web;

import java.util.HashMap;

import br.com.voxage.botomnilink.BotOmnilink;
import br.com.voxage.botomnilink.models.DadosFluxo;
import br.com.voxage.vbot.BotInputResult;
import br.com.voxage.vbot.BotState;
import br.com.voxage.vbot.BotStateFlow;
import br.com.voxage.vbot.BotStateInteractionType;

public class EscolherTituloWeb {
	@SuppressWarnings("serial")
	public static BotState load(BotOmnilink bot) {
		return new BotState("/") {{
			setId("ESCOLHER_TITULO_WEB");
			
			setMaxInputTime(1200000);
			setMaxNoInput(0);
			
			setBotStateInteractionType(BotStateInteractionType.DIRECT_INPUT);
			
			setProcessDirectInputFunction((botState, userInputs)->{
				BotInputResult botInputResult = new BotInputResult();
				DadosFluxo dadosFluxo = bot.getDadosFluxo();
				botInputResult.setResult(BotInputResult.Result.OK);
				
				String userInput = userInputs.getConcatenatedInputs().trim();
				System.out.println("!!!!!!!!!!!!!!");
				System.out.println(userInput);
				
				String str = userInput.toLowerCase();
				
				if(str.equals("sair")) {
					userInput = "7";
				}
				
				switch(userInput.toLowerCase()) {
					case"1 - boletos a vencer":
						try {
							dadosFluxo.setTitle(1);
							dadosFluxo.setStatus(1);
							botInputResult.setIntentName(BotOmnilink.STATES.ENVIAR_TITULOS);
						}catch(Exception e) {
							botInputResult.setResult(BotInputResult.Result.ERROR);
						}
						break;
					case"2 - boletos atrasados":
						try {
							dadosFluxo.setTitle(2);
							dadosFluxo.setStatus(2);
							botInputResult.setIntentName(BotOmnilink.STATES.ENVIAR_TITULOS);
						}catch(Exception e) {
							botInputResult.setResult(BotInputResult.Result.ERROR);
						}
						break;
					case"3 - todos os boletos pendentes":
						try {
							dadosFluxo.setTitle(3);
							dadosFluxo.setStatus(3);
							botInputResult.setIntentName(BotOmnilink.STATES.ENVIAR_TITULOS);
						}catch(Exception e) {
							botInputResult.setResult(BotInputResult.Result.ERROR);
						}
						break;
					case"1":
						try {
							dadosFluxo.setTitle(1);
							dadosFluxo.setStatus(1);
							botInputResult.setIntentName(BotOmnilink.STATES.ENVIAR_TITULOS);
						}catch(Exception e) {
							botInputResult.setResult(BotInputResult.Result.ERROR);
						}
						break;
					case"2":
						try {
							dadosFluxo.setTitle(2);
							dadosFluxo.setStatus(2);
							botInputResult.setIntentName(BotOmnilink.STATES.ENVIAR_TITULOS);
						}catch(Exception e) {
							botInputResult.setResult(BotInputResult.Result.ERROR);
						}
						break;
					case"3":
						try {
							dadosFluxo.setTitle(3);
							dadosFluxo.setStatus(3);
							botInputResult.setIntentName(BotOmnilink.STATES.ENVIAR_TITULOS);
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
				put(BotOmnilink.STATES.ENVIAR_TITULOS, "/ENVIAR_TITULOS");
				put(BotOmnilink.STATES.FINALIZAR, "/FINALIZAR");
                put("MAX_INPUT_ERROR", "/MAX_INPUT_ERROR");
                put("MAX_NO_INPUT", "/MAX_NO_INPUT");   
			}});
		}};
	}
}