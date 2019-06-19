package br.com.voxage.botomnilink.states.financeiro;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import br.com.voxage.botomnilink.BotOmnilink;
import br.com.voxage.botomnilink.BotOmnilinkIntegration;
import br.com.voxage.botomnilink.models.Clientes;
import br.com.voxage.botomnilink.models.Titulos;
import br.com.voxage.chat.botintegration.entities.AttendantClientInfo;
import br.com.voxage.vbot.BotState;
import br.com.voxage.vbot.BotStateFlow;
import br.com.voxage.vbot.BotStateInteractionType;

public class ObterTitulos {
	@SuppressWarnings("serial")
	public static BotState load(BotOmnilink bot) {
		return new BotState("/") {{
			setId("OBTER_TITULOS");
			
			setBotStateInteractionType(BotStateInteractionType.NO_INPUT);
			
			setAsyncPosFunction((botState, inputResult)-> CompletableFuture.supplyAsync(()->{
				BotStateFlow botStateFlow = new BotStateFlow();
				Clientes cli = bot.getClientes();
				List<AttendantClientInfo> att;
				att = bot.getcInfo();
				botStateFlow.flow = BotStateFlow.Flow.CONTINUE;
				
				Titulos customerInfo = null;
				
				try {
					customerInfo = BotOmnilinkIntegration.getTitulo(bot, cli.getCliente().getLojaErp(), cli.getCliente().getCodClienteErp());
					bot.setTitulos(customerInfo);
					
					if((Integer.parseInt(bot.getTitulos().getDadosTitulos().getQtdeAbertos()) == 0)&&(Integer.parseInt(bot.getTitulos().getDadosTitulos().getQtdeAssessoria()) == 0)&&(Integer.parseInt(bot.getTitulos().getDadosTitulos().getQtdeVencidos()) == 0)) {
						att.get(0).setValue("Obter Títulos - Sem Boletos Pendentes");
						bot.setcInfo(att);
						botStateFlow.navigationKey = BotOmnilink.STATES.SEM_BOLETO;
					}else if(Integer.parseInt(bot.getTitulos().getDadosTitulos().getQtdeAssessoria()) > 0) {
						botStateFlow.navigationKey = BotOmnilink.STATES.FONE_ASSESSORIA;
					}else {
						botStateFlow.navigationKey = BotOmnilink.STATES.ESCOLHER_TITULO;
					}

				}catch(Exception e) {
					if(bot.getError() == 500) {
						att.get(0).setValue("Obter Títulos - Erro de Integração");
						bot.setcInfo(att);
						botStateFlow.navigationKey = BotOmnilink.STATES.ERRO_TITULO;	
					}else{
						att.get(0).setValue("Obter Títulos - Boletos não Localizados");
						bot.setcInfo(att);
						botStateFlow.navigationKey = BotOmnilink.STATES.SDADOS;	
					}
					
				}
				return botStateFlow;
			}));
			
			setNextNavigationMap(new HashMap<String, String>(){{
				put(BotOmnilink.STATES.SEM_BOLETO, "#SEM_BOLETO");
				put(BotOmnilink.STATES.FONE_ASSESSORIA, "#FONE_ASSESSORIA");
				put(BotOmnilink.STATES.ESCOLHER_TITULO, "#ESCOLHER_TITULO");
				put(BotOmnilink.STATES.ERRO_TITULO, "#ERRO_TITULO");
				put(BotOmnilink.STATES.SDADOS, "#SDADOS");
				put("MAX_INPUT_ERROR", "#ATENDENTE");
			}});
		}};
	}
}

