package br.com.voxage.botomnilink.states.espelhamento;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import br.com.voxage.botomnilink.BotOmnilink;
import br.com.voxage.botomnilink.BotOmnilinkIntegration;
import br.com.voxage.botomnilink.models.DadosFluxo;
import br.com.voxage.botomnilink.models.Incluido;
import br.com.voxage.botomnilink.models.Incluir;
import br.com.voxage.chat.botintegration.entities.AttendantClientInfo;
import br.com.voxage.vbot.BotState;
import br.com.voxage.vbot.BotStateFlow;
import br.com.voxage.vbot.BotStateInteractionType;

public class IncluirEspelhamento {
	@SuppressWarnings("serial")
	public static BotState load(BotOmnilink bot) {
		return new BotState("/") {{
			setId("INCLUIR_ESP");
			
			setBotStateInteractionType(BotStateInteractionType.NO_INPUT);
			
			setAsyncPosFunction((botState, inputResult) ->CompletableFuture.supplyAsync(()->{
				BotStateFlow botStateFlow = new BotStateFlow();
				Incluir incluir = new Incluir();
				DadosFluxo dadosFluxo= bot.getDadosFluxo();
				List<AttendantClientInfo> att;
				att = bot.getcInfo();
				botStateFlow.flow = BotStateFlow.Flow.CONTINUE;
								
				incluir.setCnpj(dadosFluxo.getCnpjEsp());
				incluir.setIdCentral("");
				incluir.setNumSerie(dadosFluxo.getSerie());
				incluir.setPeriodo("");
				incluir.setPorta(dadosFluxo.getPortaEsp());
				
				bot.setIncluir(incluir);
				
				att.get(0).setValue(dadosFluxo.getMenu());
				bot.setcInfo(att);
				
				Incluido customerInfo = null;
				
				try {
					customerInfo = BotOmnilinkIntegration.addEspelhamentoCnpj(bot, incluir);
					bot.setInc(customerInfo);
					if("-2".equals(bot.getInc().getStatus())){
						att.get(0).setValue("Incluir Espelhamento - Erro na Execu��o");
						bot.setcInfo(att);
						botStateFlow.navigationKey = BotOmnilink.STATES.ERRO_INC;
					}else if("1".equals(bot.getInc().getStatus())){
						botStateFlow.navigationKey = BotOmnilink.STATES.INCLUSAO_ESP;
					}else if("0".equals(bot.getInc().getStatus())) {
						botStateFlow.navigationKey = BotOmnilink.STATES.ESP_EXISTE;
					}else {
						botStateFlow.navigationKey = BotOmnilink.STATES.CENT_INEXIST;
					}
				}catch(Exception e) {
					att.get(0).setValue("Incluir Espelhamento - Erro de Execu��o");
					bot.setcInfo(att);
					botStateFlow.navigationKey = BotOmnilink.STATES.ERRO_INC;
				}				
				return botStateFlow;
			}));
			
			setNextNavigationMap(new HashMap<String, String>(){{
				put(BotOmnilink.STATES.ERRO_INC, "#ERRO_INC");
				put(BotOmnilink.STATES.INCLUSAO_ESP, "#INCLUSAO_ESP");
				put(BotOmnilink.STATES.ESP_EXISTE, "#ESP_EXISTE");
				put(BotOmnilink.STATES.CENT_INEXIST, "#CENT_INEXIST");
			}});
		}};
	}
}