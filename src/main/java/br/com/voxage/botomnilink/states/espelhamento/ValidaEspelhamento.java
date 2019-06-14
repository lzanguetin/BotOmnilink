package br.com.voxage.botomnilink.states.espelhamento;

import java.util.HashMap;

import br.com.voxage.botomnilink.BotOmnilink;
import br.com.voxage.botomnilink.models.Contratos;
import br.com.voxage.botomnilink.models.DadosFluxo;
import br.com.voxage.botomnilink.models.Espelhamento;
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
				DadosFluxo dadosFluxo = bot.getDadosFluxo();
				Espelhamento esp = bot.getEspelhamento();
				botStateFlow.flow = BotStateFlow.Flow.CONTINUE;
				
				if(contrato.getStatus() == 2) {
					botStateFlow.navigationKey = BotOmnilink.STATES.AGUARDA_ATIV;
				}else if(contrato.getStatus() == 3) {
					botStateFlow.navigationKey = BotOmnilink.STATES.CANCELADO;
				}else if(contrato.getStatus() == 4) {
					botStateFlow.navigationKey = BotOmnilink.STATES.SUSPENSO;
				}else if(!(("ed4c5d63-916d-e211-a070-005056800011".equals(contrato.getTecnologiaId())) || ("3151a7ee-cf84-e211-88b0-005056800011".equals(contrato.getTecnologiaId())))) {
					botStateFlow.navigationKey = BotOmnilink.STATES.ERRO_TEC;
				}else {
					if(dadosFluxo.getEspelha() == 1) {
						if(esp.getQtdeEspelhamentos() >= 8) {
							botStateFlow.navigationKey =  BotOmnilink.STATES.MAX_PORT;
						}else {
							botStateFlow.navigationKey = BotOmnilink.STATES.CNPJ_ESP;
						}
					}
					else {
						if((esp.getQtdeEspelhamentos() > 0) && (esp.getQtdeEspelhamentos() >= 2)){
							botStateFlow.navigationKey = BotOmnilink.STATES.LISTR_CENTRAL;
						}else if(esp.getQtdeEspelhamentos() == 1){
							botStateFlow.navigationKey = BotOmnilink.STATES.TIPO_EXCLUIR_CENTRAL;
						}else {
							botStateFlow.navigationKey = BotOmnilink.STATES.SEM_ESP;
						}
					}
				}
				return botStateFlow;
			});
			
			setNextNavigationMap(new HashMap<String, String>(){{
				put(BotOmnilink.STATES.AGUARDA_ATIV, "#AGUARDA_ATIV");
				put(BotOmnilink.STATES.CANCELADO, "#CANCELADO");
				put(BotOmnilink.STATES.SUSPENSO, "#SUSPENSO");
				put(BotOmnilink.STATES.ERRO_TEC, "#ERRO_TEC");
				put(BotOmnilink.STATES.LISTR_CENTRAL, "#LISTR_CENTRAL");
				put(BotOmnilink.STATES.TIPO_EXCLUIR_CENTRAL, "#TIPO_EXCLUIR_CENTRAL");
				put(BotOmnilink.STATES.MAX_PORT, "#MAX_PORT");
				put(BotOmnilink.STATES.CNPJ_ESP, "#CNPJ_ESP");
				put(BotOmnilink.STATES.SEM_ESP, "#SEM_ESP");
                put("MAX_INPUT_ERROR", "/TERMINATE");
                put("MAX_NO_INPUT", "/TERMINATE");
			}});
		}};
	}
}
