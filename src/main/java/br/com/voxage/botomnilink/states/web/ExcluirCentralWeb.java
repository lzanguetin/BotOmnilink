package br.com.voxage.botomnilink.states.web;

import java.util.HashMap;

import br.com.voxage.botomnilink.BotOmnilink;
import br.com.voxage.botomnilink.models.DadosFluxo;
import br.com.voxage.botomnilink.models.Espelhamento;
import br.com.voxage.vbot.BotInputResult;
import br.com.voxage.vbot.BotState;
import br.com.voxage.vbot.BotStateFlow;
import br.com.voxage.vbot.BotStateInteractionType;

public class ExcluirCentralWeb {
	@SuppressWarnings("serial")
	public static BotState load(BotOmnilink bot) {
		return new BotState("/") {{
			setId("EXCLUIR_CENTRAL_WEB");
			
			setBotStateInteractionType(BotStateInteractionType.DIRECT_INPUT);
			
			setPreFunction(botState ->{
				BotStateFlow botStateFlow = new BotStateFlow();
				Espelhamento esp = bot.getEspelhamento();
				botStateFlow.flow = BotStateFlow.Flow.CONTINUE;
				
				botState.setCustomField("nome", esp.getCentraisClientes().get(0).getNome());
				System.out.println(esp.getCentraisClientes().get(0).getNome());
				
				return botStateFlow;
			});
			
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
					case"1 - sim":
						try {
							botInputResult.setIntentName(BotOmnilink.STATES.REMOVER_ESP);
						}catch(Exception e){
							botInputResult.setResult(BotInputResult.Result.ERROR);
						}
						break;
					case"2 - não":
						try {
							botInputResult.setIntentName(BotOmnilink.STATES.TIPO_FINALIZAR_ESP);
						}catch(Exception e){
							botInputResult.setResult(BotInputResult.Result.ERROR);
						}
						break;
					case"1":
						try {
							botInputResult.setIntentName(BotOmnilink.STATES.REMOVER_ESP);
						}catch(Exception e){
							botInputResult.setResult(BotInputResult.Result.ERROR);
						}
						break;
					case"2":
						try {
							botInputResult.setIntentName(BotOmnilink.STATES.TIPO_FINALIZAR_ESP);
						}catch(Exception e){
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
				put(BotOmnilink.STATES.REMOVER_ESP, "#REMOVER_ESP");
				put(BotOmnilink.STATES.TIPO_FINALIZAR_ESP, "#TIPO_FINALIZAR_ESP");
				put(BotOmnilink.STATES.FINALIZAR, "/FINALIZAR");
                put("MAX_INPUT_ERROR", "/FINALIZAR");
                put("MAX_NO_INPUT", "/FINALIZAR"); 
			}});
		}};
	}
}
