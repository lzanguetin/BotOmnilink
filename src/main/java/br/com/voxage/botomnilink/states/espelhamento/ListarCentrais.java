package br.com.voxage.botomnilink.states.espelhamento;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import br.com.voxage.botomnilink.BotOmnilink;
import br.com.voxage.botomnilink.models.Central;
import br.com.voxage.botomnilink.models.DadosFluxo;
import br.com.voxage.botomnilink.models.Espelhamento;
import br.com.voxage.chat.botintegration.entities.BotMessage;
import br.com.voxage.vbot.BotInputResult;
import br.com.voxage.vbot.BotState;
import br.com.voxage.vbot.BotStateFlow;
import br.com.voxage.vbot.BotStateInteractionType;

public class ListarCentrais {
	@SuppressWarnings("serial")
	public static BotState load(BotOmnilink bot) {
		return new BotState("/") {{
			setId("LISTR_CENTRAL");
			
			setMaxInputTime(1200000);
			setMaxNoInput(0);
			
			setBotStateInteractionType(BotStateInteractionType.DIRECT_INPUT);
			
			setPreFunction(botState -> {              
                BotStateFlow botStateFlow = new BotStateFlow();
                Espelhamento esp = bot.getEspelhamento();
                botStateFlow.flow = BotStateFlow.Flow.CONTINUE;
                
                List<BotMessage> mensagens = botState.getCustomMessageById("inicial").get(0).getMessages().stream()
                        .map( rm -> rm.clone() ).collect(Collectors.toList());
                
                int i = 1;
                StringBuilder dividas = new StringBuilder();
                
                for(Central c : esp.getCentraisClientes()) {
                    String nome = c.getNome();
                    
                    dividas.append(String.format("%d - %s\n", i++, nome));
                }
                
                bot.setQtd(i);
                
                botState.setInitialMessages(mensagens);
                botState.setCustomField(BotOmnilink.STATES.LISTR_CENTRAL, dividas.toString());
                
                return botStateFlow;
            });
			
			setProcessDirectInputFunction((botState, userInputs)->{
				BotInputResult botInputResult = new BotInputResult();
				DadosFluxo dadosFluxo = bot.getDadosFluxo();
				botInputResult.setResult(BotInputResult.Result.OK);
				
				String userInput = userInputs.getConcatenatedInputs();
				dadosFluxo.setExcluir(userInput);
				
				System.out.println("QUANTIDADE");
				System.out.println(bot.getQtd());
				
				if(dadosFluxo.getExcluir().matches("[0-9,]+")){
					if(dadosFluxo.getExcluir().equals(",")) {
						botInputResult.setResult(BotInputResult.Result.ERROR);	
					}else {
						String a = dadosFluxo.getExcluir();
						String[] split = a.split(",");
						for (String string : split) {
							if("".equals(string)) {
								botInputResult.setResult(BotInputResult.Result.ERROR);
							}else {
								if(((Integer.parseInt(string)) <= bot.getQtd()-1) || ((Integer.parseInt(string)) != 0)){
									botInputResult.setIntentName(BotOmnilink.STATES.REMOVER_ESP);
								}else {
									botInputResult.setResult(BotInputResult.Result.ERROR);
								}
							}
						}
					}
				}else if("sair".equals(userInput.toLowerCase())){
					botInputResult.setIntentName(BotOmnilink.STATES.FINALIZAR);
				}else {
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
				put(BotOmnilink.STATES.FINALIZAR, "/FINALIZAR");
                put("MAX_INPUT_ERROR", "/FINALIZAR");
                put("MAX_NO_INPUT", "/FINALIZAR"); 
			}});
		}};
	}
}