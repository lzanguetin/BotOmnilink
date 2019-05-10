package br.com.voxage.botomnilink.states.global;


import java.util.List;
import java.util.Calendar;
import java.util.HashMap;
import java.util.stream.Collectors;
import br.com.voxage.botomnilink.BotOmnilink;
import br.com.voxage.vbot.BotInputResult;
import br.com.voxage.vbot.BotState;
import br.com.voxage.vbot.BotStateFlow;
import br.com.voxage.vbot.BotStateInteractionType;
import br.com.voxage.chat.botintegration.entities.BotMessage;

public class Atendente {
	@SuppressWarnings("serial")
	public static BotState load(BotOmnilink bot) {
		return new BotState("/") {{
			setId("ATENDENTE");
			
			setBotStateInteractionType(BotStateInteractionType.NO_INPUT);
			
			setPreFunction(botState ->{               
				List<BotMessage> initialMessages = botState.getCustomMessageById("state_transferir").get(0)
						.getMessages().stream().map(rm -> rm.clone()).collect(Collectors.toList());
				botState.setInitialMessages(initialMessages);
				
				BotStateFlow botStateFlow = new BotStateFlow();
				botStateFlow.flow = BotStateFlow.Flow.CONTINUE;
				return botStateFlow;
			});
			
			setPosFunction((botState, inputResult) ->{
				BotStateFlow botStateFlow = new BotStateFlow();
	            Calendar dataVencimento = Calendar.getInstance();
	            int dayOfWeek = dataVencimento.get(Calendar.DAY_OF_WEEK);
				botStateFlow.flow = BotStateFlow.Flow.TRANSFER;

				switch(dayOfWeek) {
            		case Calendar.SUNDAY:
            			inputResult.setResult(BotInputResult.Result.ERROR);
            			break;
            		case Calendar.SATURDAY:
            			inputResult.setResult(BotInputResult.Result.ERROR);
            			break;
            		default:
            			inputResult.setResult(BotInputResult.Result.OK);
				}
				
				bot.setBotNameToTransfer(BotOmnilink.STATES.TRANSFERIR);
				botStateFlow.navigationKey = "TRANSFER";
				
				return botStateFlow;
			});
			
			setNextNavigationMap(new HashMap<String, String>(){{
				put("TRANSFER", "/TRANSFER");
			}});
		}};
	}
}