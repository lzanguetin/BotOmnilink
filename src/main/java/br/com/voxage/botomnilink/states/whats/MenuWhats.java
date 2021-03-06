package br.com.voxage.botomnilink.states.whats;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.voxage.botomnilink.BotOmnilink;
import br.com.voxage.botomnilink.models.DadosFluxo;
import br.com.voxage.chat.botintegration.entities.AttendantClientInfo;
import br.com.voxage.vbot.BotInputResult;
import br.com.voxage.vbot.BotState;
import br.com.voxage.vbot.BotStateFlow;
import br.com.voxage.vbot.BotStateInteractionType;

public class MenuWhats {
	@SuppressWarnings("serial")
	public static BotState load(BotOmnilink bot) {
		return new BotState("/") {{
			setId("MENU_WHATS");
			
			setMaxInputTime(1200000);
			setMaxNoInput(0);
			
			setBotStateInteractionType(BotStateInteractionType.DIRECT_INPUT);
			
			setProcessDirectInputFunction((botState, userInputs)->{
				BotInputResult botInputResult = new BotInputResult();
				DadosFluxo dadosFluxo = bot.getDadosFluxo();
				AttendantClientInfo cInfo = new AttendantClientInfo();
				List<AttendantClientInfo> att;
				att = new ArrayList<AttendantClientInfo>();	
				botInputResult.setResult(BotInputResult.Result.OK);
				
				String userInput = userInputs.getConcatenatedInputs().trim();
				cInfo.setName("Transf");
				cInfo.setValue(userInput);
				bot.setaInfo(cInfo);
				att.add(bot.getaInfo());
				bot.setcInfo(att);
				dadosFluxo.setMax(1);
				dadosFluxo.setMenu(userInput);
				
				String str = userInput.toLowerCase();
				
				if(str.equals("sair")) {
					userInput = "7";
				}
				
				System.out.println("!!!!!!!!!!!!!!");
				System.out.println(userInput);
				
				switch(userInput.toLowerCase()) {
					case"1 - agendamento":
						try {
							dadosFluxo.setOption("1");
							botInputResult.setIntentName(BotOmnilink.STATES.AGENDAMENTO);
						}catch(Exception e){
							botInputResult.setResult(BotInputResult.Result.ERROR);
						}
						break;
					case"2 - cancelamento de contrato":
						try {
							dadosFluxo.setOption("2");
							botInputResult.setIntentName(BotOmnilink.STATES.CANCELAMENTO);
						}catch(Exception e){
							botInputResult.setResult(BotInputResult.Result.ERROR);
						}
						break;
					case"3 - espelhamento de sinal":
						try {
							dadosFluxo.setOption("3");
							botInputResult.setIntentName(BotOmnilink.STATES.ESPELHAMENTO);
						}catch(Exception e){
							botInputResult.setResult(BotInputResult.Result.ERROR);
						}
						break;
					case"4 - financeiro":
						try {
							dadosFluxo.setOption("4");
							botInputResult.setIntentName(BotOmnilink.STATES.FINANCEIRO);
						}catch(Exception e){
							botInputResult.setResult(BotInputResult.Result.ERROR);
						}
						break;
					case"5 - suporte":
						try {
							dadosFluxo.setOption("5");
							botInputResult.setIntentName(BotOmnilink.STATES.SUPORTE);
						}catch(Exception e){
							botInputResult.setResult(BotInputResult.Result.ERROR);
						}
						break;
					case"6 - ativação":
						try {
							dadosFluxo.setOption("6");
							botInputResult.setIntentName(BotOmnilink.STATES.ATIVACAO);
						}catch(Exception e){
							botInputResult.setResult(BotInputResult.Result.ERROR);
						}
						break;
					case"1":
						try {
							dadosFluxo.setOption("1");
							botInputResult.setIntentName(BotOmnilink.STATES.AGENDAMENTO);
						}catch(Exception e){
							botInputResult.setResult(BotInputResult.Result.ERROR);
						}
						break;
					case"2":
						try {
							dadosFluxo.setOption("2");
							botInputResult.setIntentName(BotOmnilink.STATES.CANCELAMENTO);
						}catch(Exception e){
							botInputResult.setResult(BotInputResult.Result.ERROR);
						}
						break;
					case"3":
						try {
							dadosFluxo.setOption("3");
							botInputResult.setIntentName(BotOmnilink.STATES.ESPELHAMENTO);
						}catch(Exception e){
							botInputResult.setResult(BotInputResult.Result.ERROR);
						}
						break;
					case"4":
						try {
							dadosFluxo.setOption("4");
							botInputResult.setIntentName(BotOmnilink.STATES.FINANCEIRO);
						}catch(Exception e){
							botInputResult.setResult(BotInputResult.Result.ERROR);
						}
						break;
					case"5":
						try {
							dadosFluxo.setOption("5");
							botInputResult.setIntentName(BotOmnilink.STATES.SUPORTE);
						}catch(Exception e){
							botInputResult.setResult(BotInputResult.Result.ERROR);
						}
						break;
					case"6":
						try {
							dadosFluxo.setOption("6");
							botInputResult.setIntentName(BotOmnilink.STATES.ATIVACAO);
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
				
				bot.getUserSession().put("CLIENT_INFO", bot.getcInfo());
				
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
				put(BotOmnilink.STATES.FINANCEIRO, "/FINANCEIRO");
				put(BotOmnilink.STATES.ESPELHAMENTO, "/ESPELHAMENTO");
				put(BotOmnilink.STATES.FINALIZAR, "/FINALIZAR");
                put("MAX_INPUT_ERROR", "/MAX_INPUT_ERROR");
                put("MAX_NO_INPUT", "/MAX_NO_INPUT");   
			}});
		}};
	}
}