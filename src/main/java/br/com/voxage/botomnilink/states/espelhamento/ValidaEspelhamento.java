package br.com.voxage.botomnilink.states.espelhamento;

import java.util.HashMap;

import br.com.voxage.botomnilink.BotOmnilink;
import br.com.voxage.botomnilink.models.Contratos;
import br.com.voxage.vbot.BotState;
import br.com.voxage.vbot.BotStateFlow;
import br.com.voxage.vbot.BotStateInteractionType;

public class ValidaEspelhamento {
	@SuppressWarnings({"serial"})
	public static BotState load(BotOmnilink bot) {
		return new BotState("/") {{
			setId("VALIDAR_ESP");
			
			setBotStateInteractionType(BotStateInteractionType.NO_INPUT);
			
			setPosFunction((botState, inputResult)->{
				BotStateFlow botStateFlow = new BotStateFlow();
				Contratos contrato = bot.getContratos();
				botStateFlow.flow = BotStateFlow.Flow.CONTINUE;
				
				if(contrato.getStatus() == "2") {
					botStateFlow.navigationKey = BotOmnilink.STATES.AGUARDA_ATIV;
				}else if(contrato.getStatus() == "3") {
					botStateFlow.navigationKey = BotOmnilink.STATES.CANCELADO;
				}else if(contrato.getStatus() == "4") {
					botStateFlow.navigationKey = BotOmnilink.STATES.SUSPENSO;
				}else if((contrato.getTecnologiaId() == "ed4c5d63-916d-e211-a070-005056800011") || (contrato.getTecnologiaId() == "3151a7ee-cf84-e211-88b0-005056800011")) {
					botStateFlow.navigationKey = BotOmnilink.STATES.ERRO_TEC;
				}else {
					botStateFlow.navigationKey = BotOmnilink.STATES.TIPO_TEC_VALIDA;
				}
				return botStateFlow;
			});
			
			setNextNavigationMap(new HashMap<String, String>(){{
				put(BotOmnilink.STATES.AGUARDA_ATIV, "#AGUARDA_ATIV");
				put(BotOmnilink.STATES.CANCELADO, "#CANCELADO");
				put(BotOmnilink.STATES.SUSPENSO, "#SUSPENSO");
				put(BotOmnilink.STATES.ERRO_TEC, "#ERRO_TEC");
				put(BotOmnilink.STATES.TIPO_TEC_VALIDA, "#TIPO_TEC_VALIDA");
                put("MAX_INPUT_ERROR", "/TERMINATE");
                put("MAX_NO_INPUT", "/TERMINATE");
			}});
		}};
	}
}
