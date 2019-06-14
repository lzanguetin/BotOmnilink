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

public class OutrosWhats {
	@SuppressWarnings("serial")
	public static BotState load(BotOmnilink bot) {
		return new BotState("/") {{
			setId("OUTROS_WHATS");
			
			setBotStateInteractionType(BotStateInteractionType.DIRECT_INPUT);
			
			setProcessDirectInputFunction((botState, userInputs)->{
				BotInputResult botInputResult = new BotInputResult();
				DadosFluxo dadosFluxo = bot.getDadosFluxo();
				List<AttendantClientInfo> att;
				att = bot.getcInfo();
				botInputResult.setResult(BotInputResult.Result.OK);
				
				String userInput = userInputs.getConcatenatedInputs().trim();
				String auto = BotOmnilink.readString("id.flag.obterAuto");
				
				System.out.println("!!!!!!!!!!!!!!");
				System.out.println(userInput);
				
				String str = userInput.toLowerCase();
				
				if(str.equals("sair")) {
					userInput = "7";
				}
				
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
								dadosFluxo.setOption("3");
								botInputResult.setIntentName(BotOmnilink.STATES.TOKEN);
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
								dadosFluxo.setOption("4");
								botInputResult.setIntentName(BotOmnilink.STATES.TOKEN);
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
					case"1":
						try {
							botInputResult.setIntentName(BotOmnilink.STATES.OAGENDAMENTO);
						}catch(Exception e) {
							botInputResult.setResult(BotInputResult.Result.ERROR);
						}
						break;
					case"2":
						try {
							botInputResult.setIntentName(BotOmnilink.STATES.OCANCELAMENTO);
						}catch(Exception e) {
							botInputResult.setResult(BotInputResult.Result.ERROR);
						}
						break;
					case"3":
						try {
							if("true".equals(auto)) {
								dadosFluxo.setOption("3");
								botInputResult.setIntentName(BotOmnilink.STATES.TOKEN);
							}else {
								botInputResult.setIntentName(BotOmnilink.STATES.ATENDENTE);
							}
						}catch(Exception e) {
							botInputResult.setResult(BotInputResult.Result.ERROR);
						}
						break;
					case"4":
						try {
							if("true".equals(auto)) {
								dadosFluxo.setOption("4");
								botInputResult.setIntentName(BotOmnilink.STATES.TOKEN);
							}else {
								botInputResult.setIntentName(BotOmnilink.STATES.ATENDENTE);
							}
						}catch(Exception e) {
							botInputResult.setResult(BotInputResult.Result.ERROR);
						}
						break;
					case"5":
						try {
							botInputResult.setIntentName(BotOmnilink.STATES.OSUPORTE);
						}catch(Exception e) {
							botInputResult.setResult(BotInputResult.Result.ERROR);
						}
						break;
					case"6":
						try {
							botInputResult.setIntentName(BotOmnilink.STATES.OATIVACAO);
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
						botInputResult.setIntentName(BotOmnilink.STATES.ATENDENTE);
				}
				
				att.get(0).setValue(userInput);
				bot.setcInfo(att);
				
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
				put(BotOmnilink.STATES.TIPO_ESPELHAMENTO, "/TIPO_ESPELHAMENTO");
				put(BotOmnilink.STATES.TIPO_FINANCEIRO, "/TIPO_FINANCEIRO");
				put(BotOmnilink.STATES.OSUPORTE, "/OSUPORTE");
				put(BotOmnilink.STATES.OATIVACAO, "/OATIVACAO");
				put(BotOmnilink.STATES.TOKEN, "/TOKEN");
				put(BotOmnilink.STATES.FINALIZAR, "/FINALIZAR");
                put("MAX_INPUT_ERROR", "/FINALIZAR");
                put("MAX_NO_INPUT", "/FINALIZAR");  
			}});
		}};
	}
}