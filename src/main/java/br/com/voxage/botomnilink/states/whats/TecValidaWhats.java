package br.com.voxage.botomnilink.states.whats;

import java.util.HashMap;

import br.com.voxage.botomnilink.BotOmnilink;
import br.com.voxage.botomnilink.models.DadosFluxo;
import br.com.voxage.botomnilink.models.Espelhamento;
import br.com.voxage.vbot.BotInputResult;
import br.com.voxage.vbot.BotState;
import br.com.voxage.vbot.BotStateFlow;
import br.com.voxage.vbot.BotStateInteractionType;

public class TecValidaWhats {
	@SuppressWarnings("serial")
	public static BotState load(BotOmnilink bot) {
		return new BotState("/") {{
			setId("TEC_VALIDA_WHATS");
			
			setMaxInputTime(1200000);
			setMaxNoInput(0);
			
			setBotStateInteractionType(BotStateInteractionType.DIRECT_INPUT);
			
			setProcessDirectInputFunction((botState, userInputs) ->{
				BotInputResult botInputResult = new BotInputResult();
				Espelhamento esp = bot.getEspelhamento();
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
					case"sim":
						try {
							if((esp.getQtdeEspelhamentos() > 0) && (esp.getQtdeEspelhamentos() >= 2)){
								botInputResult.setIntentName(BotOmnilink.STATES.LISTR_CENTRAL);
							}else {
								botInputResult.setIntentName(BotOmnilink.STATES.TIPO_EXCLUIR_CENTRAL);
							}	
						}
						catch(Exception e) {
							botInputResult.setResult(BotInputResult.Result.ERROR);
						}
						break;
					case"não":
						try {
							if(esp.getQtdeEspelhamentos() >= 8) {
								botInputResult.setIntentName(BotOmnilink.STATES.MAX_PORT);
							}else {
								botInputResult.setIntentName(BotOmnilink.STATES.CNPJ_ESP);
							}
							
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
				put(BotOmnilink.STATES.MAX_PORT, "#MAX_PORT");
				put(BotOmnilink.STATES.LISTR_CENTRAL, "#LISTR_CENTRAL");
				put(BotOmnilink.STATES.TIPO_EXCLUIR_CENTRAL, "#TIPO_EXCLUIR_CENTRAL");
				put(BotOmnilink.STATES.SEM_ESP, "#SEM_ESP");
				put(BotOmnilink.STATES.CNPJ_ESP, "#CNPJ_ESP");
				put(BotOmnilink.STATES.FINALIZAR, "/FINALIZAR");
                put("MAX_INPUT_ERROR", "/MAX_INPUT_ERROR");
                put("MAX_NO_INPUT", "/MAX_NO_INPUT");  
			}});
		}};
	}
}
