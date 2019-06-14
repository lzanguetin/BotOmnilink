package br.com.voxage.botomnilink.states.espelhamento;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import br.com.voxage.botomnilink.BotOmnilink;
import br.com.voxage.botomnilink.BotOmnilinkIntegration;
import br.com.voxage.botomnilink.models.AlteradoExistente;
import br.com.voxage.botomnilink.models.DadosFluxo;
import br.com.voxage.botomnilink.models.Espelhamento;
import br.com.voxage.botomnilink.models.Incluir;
import br.com.voxage.chat.botintegration.entities.AttendantClientInfo;
import br.com.voxage.vbot.BotInputResult;
import br.com.voxage.vbot.BotState;
import br.com.voxage.vbot.BotStateFlow;
import br.com.voxage.vbot.BotStateInteractionType;

public class IncluirEspExistente {
	@SuppressWarnings("serial")
	public static BotState load(BotOmnilink bot) {
		return new BotState("/") {{
			setId("INC_EXIST");
			
			setBotStateInteractionType(BotStateInteractionType.NO_INPUT);
			
			setAsyncPosFunction((botState, inputResult) ->CompletableFuture.supplyAsync(()->{
				BotStateFlow botStateFlow = new BotStateFlow();
				Espelhamento esp = bot.getEspelhamento();
				Incluir incluir = new Incluir();
				DadosFluxo dadosFluxo= bot.getDadosFluxo();
				List<AttendantClientInfo> att;
				att = bot.getcInfo();
				botStateFlow.flow = BotStateFlow.Flow.CONTINUE;
				
				System.out.println("!!!!!!!!!!!!!!!!!!!");
				System.out.println("INCLUSAO");
				System.out.println(esp.getCentraisClientes().get(0).getId());
				System.out.println(dadosFluxo.getExcluir());
				
				incluir.setCnpj(dadosFluxo.getCnpjEsp());
				incluir.setPorta(dadosFluxo.getPortaEsp());
				incluir.setNumSerie(dadosFluxo.getSerie());
				incluir.setIdCentral(esp.getCentraisClientes().get((Integer.parseInt(dadosFluxo.getExcluir()))-1).getId());
				incluir.setPeriodo("");
				
				bot.setIncluir(incluir);
				
				AlteradoExistente customerInfo = null;
				
				try {
					customerInfo = BotOmnilinkIntegration.incluirEspelhamento(bot, incluir);
					bot.setAlter(customerInfo);
					if("true".equals(bot.getAlter().getSucesso())) {
						botStateFlow.navigationKey = BotOmnilink.STATES.INCLUSAO_ESP;
					}else {
						att.get(0).setValue("Incluir Espelhamento - N�o Permitido");
						bot.setcInfo(att);
						botStateFlow.navigationKey = BotOmnilink.STATES.ERRO_ALTER;
					}	
				}catch(Exception e) {
					att.get(0).setValue("Incluir Espelhamento - Erro de Integra��o");
					bot.setcInfo(att);
					inputResult.setResult(BotInputResult.Result.ERROR);
				}				
				return botStateFlow;
			}));
			
			setNextNavigationMap(new HashMap<String, String>(){{
				put(BotOmnilink.STATES.INCLUSAO_ESP, "#INCLUSAO_ESP");
				put(BotOmnilink.STATES.ERRO_ALTER, "#ERRO_ALTER");
                put("MAX_INPUT_ERROR", "/ATENDENTE");
			}});
		}};
	}
}