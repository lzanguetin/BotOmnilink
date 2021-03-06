package br.com.voxage.botomnilink.states.espelhamento;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import br.com.voxage.botomnilink.BotOmnilink;
import br.com.voxage.botomnilink.BotOmnilinkIntegration;
import br.com.voxage.botomnilink.models.Clientes;
import br.com.voxage.botomnilink.models.DadosFluxo;
import br.com.voxage.botomnilink.models.PIN;
import br.com.voxage.chat.botintegration.entities.AttendantClientInfo;
import br.com.voxage.vbot.BotState;
import br.com.voxage.vbot.BotStateFlow;
import br.com.voxage.vbot.BotStateInteractionType;

public class ValidarPIN {
	@SuppressWarnings("serial")
	public static BotState load(BotOmnilink bot) {
		return new BotState("/") {{
			setId("VALIDAR_PIN");
			setMaxInputError(1);
			
			setBotStateInteractionType(BotStateInteractionType.NO_INPUT);
			
			setAsyncPosFunction((botState, inputResult) ->CompletableFuture.supplyAsync(()->{
				BotStateFlow botStateFlow = new BotStateFlow();
				Clientes cliente = bot.getClientes();
				DadosFluxo dadosFluxo= bot.getDadosFluxo();
				List<AttendantClientInfo> att;
				att = bot.getcInfo();
				botStateFlow.flow = BotStateFlow.Flow.CONTINUE;
				
				att.get(0).setValue(dadosFluxo.getMenu());
				bot.setcInfo(att);

				PIN customerInfo = null;
				
				try {
					customerInfo = BotOmnilinkIntegration.validaPin(bot, dadosFluxo.getPin(), cliente.getCliente().getClienteId());
					bot.setPIN(customerInfo);
					if("true".equals(bot.getPIN().getCodigoValido())) {
						botStateFlow.navigationKey = BotOmnilink.STATES.CONSULTAR_ESP;
					}else {
						att.get(0).setValue("Validar PIN - PIN Inválido");
						bot.setcInfo(att);
						botStateFlow.navigationKey = BotOmnilink.STATES.ERRO_PIN;
					}	
				}catch(Exception e) {
					if(bot.getError() == 500) {
						att.get(0).setValue("Validar PIN - PIN Inválido");
						bot.setcInfo(att);
						botStateFlow.navigationKey = BotOmnilink.STATES.ERRO_PIN;
					}else {
						att.get(0).setValue("Validar PIN - PIN Inválido");
						bot.setcInfo(att);
						botStateFlow.navigationKey = BotOmnilink.STATES.SDADOS;	
					}	
				}				
				return botStateFlow;
			}));
			
			setNextNavigationMap(new HashMap<String, String>(){{
				put(BotOmnilink.STATES.CONSULTAR_ESP, "#CONSULTAR_ESP");
				put(BotOmnilink.STATES.ERRO_PIN, "#ERRO_PIN");
				put(BotOmnilink.STATES.SDADOS, "#SDADOS");
                put("MAX_INPUT_ERROR", "/ATENDENTE");
			}});
		}};
	}
}
