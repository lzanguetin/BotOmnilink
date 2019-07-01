package br.com.voxage.botomnilink.states.whats;

import java.util.HashMap;
import java.util.List;

import br.com.voxage.botomnilink.BotOmnilink;
import br.com.voxage.botomnilink.models.DadosFluxo;
import br.com.voxage.chat.botintegration.entities.AttendantClientInfo;
import br.com.voxage.vbot.BotInputResult;
import br.com.voxage.vbot.BotState;
import br.com.voxage.vbot.BotStateFlow;
import br.com.voxage.vbot.BotStateInteractionType;

public class FinanceiroWhats {
	@SuppressWarnings("serial")
	public static BotState load(BotOmnilink bot) {
		return new BotState("/") {{
			setId("FINANCEIRO_WHATS");
			
			setMaxInputTime(1200000);
			setMaxNoInput(0);
			
			setBotStateInteractionType(BotStateInteractionType.DIRECT_INPUT);
			
			setProcessDirectInputFunction((botState, userInputs)->{
				BotInputResult botInputResult = new BotInputResult();
				DadosFluxo dadosFluxo = bot.getDadosFluxo();
				List<AttendantClientInfo> att;
				att = bot.getcInfo();
				botInputResult.setResult(BotInputResult.Result.OK);
				
				String userInput = userInputs.getConcatenatedInputs().trim();
				
				if(bot.getMicro() != null) {
					bot.getMicro().setAccessToken(null);
				}
				
				dadosFluxo.setMax(1);
				
				System.out.println("!!!!!!!!!!!!!!");
				System.out.println(userInput);
				
				String str = userInput.toLowerCase();
				
				if(str.equals("sair")) {
					userInput = "7";
				}
				
				switch(userInput.toLowerCase()) {
					case"1 - segunda via de boletos":
						try {
							botInputResult.setIntentName(BotOmnilink.STATES.TITULOS);
						}catch(Exception e) {
							botInputResult.setResult(BotInputResult.Result.ERROR);
						}
						break;
					case"2 - informar um pagamento":
						try {
							botInputResult.setIntentName(BotOmnilink.STATES.INFORME);
						}catch(Exception e) {
							botInputResult.setResult(BotInputResult.Result.ERROR);
						}
						break;
					case"3 - outros assuntos financeiros":
						try {
							att.get(0).setValue("Demandas Financeiras - Outros Assuntos");
							bot.setcInfo(att);
							botInputResult.setIntentName(BotOmnilink.STATES.ATENDENTE);
						}catch(Exception e) {
							botInputResult.setResult(BotInputResult.Result.ERROR);
						}
						break;
					case"1":
						try {
							botInputResult.setIntentName(BotOmnilink.STATES.TITULOS);
						}catch(Exception e) {
							botInputResult.setResult(BotInputResult.Result.ERROR);
						}
						break;
					case"2":
						try {
							botInputResult.setIntentName(BotOmnilink.STATES.INFORME);
						}catch(Exception e) {
							botInputResult.setResult(BotInputResult.Result.ERROR);
						}
						break;
					case"3":
						try {
							att.get(0).setValue("Demandas Financeiras - Outros Assuntos");
							bot.setcInfo(att);
							botInputResult.setIntentName(BotOmnilink.STATES.ATENDENTE);
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
				put(BotOmnilink.STATES.TITULOS, "#TITULOS");
				put(BotOmnilink.STATES.INFORME, "#INFORME");
				put(BotOmnilink.STATES.ATENDENTE, "#ATENDENTE");
				put(BotOmnilink.STATES.FINALIZAR, "/FINALIZAR");
                put("MAX_INPUT_ERROR", "/MAX_INPUT_ERROR");
                put("MAX_NO_INPUT", "/MAX_NO_INPUT");   
			}});
		}};
	}
}