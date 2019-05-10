package br.com.voxage.botomnilink.states.financeiro;

import java.util.HashMap;

import br.com.voxage.botomnilink.BotOmnilink;
import br.com.voxage.botomnilink.models.Titulos;
import br.com.voxage.vbot.BotState;
import br.com.voxage.vbot.BotStateFlow;
import br.com.voxage.vbot.BotStateInteractionType;

public class EscolherTitulo {
	@SuppressWarnings("serial")
	public static BotState load(BotOmnilink bot) {
		return new BotState("/") {{
			setId("ESCOLHER_TITULO");
			
			setBotStateInteractionType(BotStateInteractionType.NO_INPUT);
			
			setPosFunction((botState, inputResult)->{
				BotStateFlow botStateFlow = new BotStateFlow();
				Titulos titulo = bot.getTitulos();
				botStateFlow.flow = BotStateFlow.Flow.CONTINUE;
				
				if(((Integer.parseInt(titulo.getDadosTitulos().getQtdeAbertos()) > 0)) && (Integer.parseInt(titulo.getDadosTitulos().getQtdeVencidos()) > 0)) {
					botStateFlow.navigationKey = BotOmnilink.STATES.TIPO_ESCOLHER_TITULO;
				}else if((Integer.parseInt(titulo.getDadosTitulos().getQtdeAbertos()) == 0) && (Integer.parseInt(titulo.getDadosTitulos().getQtdeVencidos()) > 0)){
					botStateFlow.navigationKey = BotOmnilink.STATES.BOLETOS_ATRASADOS;
				}else {
					botStateFlow.navigationKey = BotOmnilink.STATES.BOLETOS_VENCER;
				}
				return botStateFlow;
			});
			
			setNextNavigationMap(new HashMap<String, String>(){{
				put(BotOmnilink.STATES.TIPO_ESCOLHER_TITULO, "/TIPO_ESCOLHER_TITULO");
				put(BotOmnilink.STATES.BOLETOS_ATRASADOS, "/BOLETOS_ATRASADOS");
				put(BotOmnilink.STATES.BOLETOS_VENCER, "/BOLETOS_VENCER");
			}});
		}};
	}
}