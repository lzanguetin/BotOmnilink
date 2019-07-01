package br.com.voxage.botomnilink.states.global;

import java.util.HashMap;
import java.util.List;

import br.com.voxage.basicvalidators.CNPJValidator;
import br.com.voxage.basicvalidators.CPFValidator;
import br.com.voxage.botomnilink.BotOmnilink;
import br.com.voxage.botomnilink.models.DadosFluxo;
import br.com.voxage.chat.botintegration.entities.AttendantClientInfo;
import br.com.voxage.vbot.BotInputResult;
import br.com.voxage.vbot.BotState;
import br.com.voxage.vbot.BotStateFlow;
import br.com.voxage.vbot.BotStateInteractionType;

public class CpfCnpj{	
	@SuppressWarnings("serial")
	public static BotState load(BotOmnilink bot) {
		return new BotState("/") {{
			setId("CPFCNPJ");
			
			setMaxInputTime(1200000);
			setMaxNoInput(0);
			
			setBotStateInteractionType(BotStateInteractionType.DIRECT_INPUT);
			
			setProcessDirectInputFunction((botState, userInputs)->{
				BotInputResult botInputResult = new BotInputResult();
				DadosFluxo dadosFluxo = bot.getDadosFluxo(); 
				AttendantClientInfo cInfo = new AttendantClientInfo();
				List<AttendantClientInfo> att;
				att = bot.getcInfo();	
				botInputResult.setResult(BotInputResult.Result.OK);
				
				String userInput = userInputs.getConcatenatedInputs().replaceAll("[/.-]", "");;
				cInfo.setName("CPF-CNPJ");
				cInfo.setValue(userInput);
				bot.setaInfo(cInfo);
				att.add(bot.getaInfo());
				bot.setcInfo(att);	
				
				if(((CPFValidator.isValidCPF(userInput)) == true) || ((CNPJValidator.isValidCNPJ(userInput)) == true)){
					dadosFluxo.setCpfCnpj(userInput);
					botInputResult.setIntentName(BotOmnilink.STATES.VERIFCLIENTE);
				}else if("sair".equals(userInput.toLowerCase())){
					botInputResult.setIntentName(BotOmnilink.STATES.FINALIZAR);
				}else {
					botInputResult.setResult(BotInputResult.Result.ERROR);
				}
				
				return botInputResult;
			});
			
			setPosFunction((botState, inputResult) ->{
				BotStateFlow botStateFlow = new BotStateFlow();
				botStateFlow.flow = BotStateFlow.Flow.CONTINUE;
				botStateFlow.navigationKey = inputResult.getIntentName();
				
				return botStateFlow;
			});
			
			setNextNavigationMap(new HashMap<String, String>(){{
				put(BotOmnilink.STATES.VERIFCLIENTE, "#VERIFCLIENTE");
				put(BotOmnilink.STATES.FINALIZAR, "#FINALIZAR");
                put("MAX_INPUT_ERROR", "/FINALIZAR");
                put("MAX_NO_INPUT", "/FINALIZAR");
			}});
		}};
	}
}