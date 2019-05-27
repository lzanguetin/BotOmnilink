package br.com.voxage.botomnilink.states.financeiro;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

import br.com.voxage.botomnilink.BotOmnilink;
import br.com.voxage.botomnilink.BotOmnilinkIntegration;
import br.com.voxage.botomnilink.models.Clientes;
import br.com.voxage.botomnilink.models.DadosFluxo;
import br.com.voxage.botomnilink.models.EnvTitulos;
import br.com.voxage.botomnilink.models.Envio;
import br.com.voxage.vbot.BotState;
import br.com.voxage.vbot.BotStateFlow;
import br.com.voxage.vbot.BotStateInteractionType;

public class EnviarTitulos {
	@SuppressWarnings("serial")
	public static BotState load(BotOmnilink bot) {
		return new BotState("/") {{
			setId("ENVIAR_TITULOS");
			setMaxInputError(1);
			
			setBotStateInteractionType(BotStateInteractionType.NO_INPUT);
		
			setAsyncPosFunction((botState, inputResult) ->CompletableFuture.supplyAsync(()->{
				BotStateFlow botStateFlow = new BotStateFlow();
				Envio env = new Envio();
				DadosFluxo dadosFluxo = bot.getDadosFluxo();
				Clientes cli = bot.getClientes();
				botStateFlow.flow = BotStateFlow.Flow.CONTINUE;
				
				env.setCpfCnpj(dadosFluxo.getCpfCnpj());
				env.setCodClienteErp(cli.getCliente().getCodClienteErp());
				env.setLojaCliente(cli.getCliente().getLojaErp());
				env.setEntidadeCliente(cli.getCliente().getEntidade());
				env.setClienteId(cli.getCliente().getClienteId());
				env.setStatusTitulos(dadosFluxo.getTitle());

				bot.setEnvio(env);
				
				EnvTitulos customerInfo = null;
								
				try {
					customerInfo = BotOmnilinkIntegration.enviaTitulos(bot, env);
					bot.setEnv(customerInfo);
					
					if(bot.getEnv().getEmail() != null){
						botStateFlow.navigationKey = BotOmnilink.STATES.CONFIRMA_ENVIO;
					}else {
						bot.getUserSession().put("CLIENTINFO_Transfer", "Enviar Titulo - Sem E-mail Cadastado");
						botStateFlow.navigationKey = BotOmnilink.STATES.SDADOS;
					}						
				}catch(Exception e) {
					bot.getUserSession().put("CLIENTINFO_Transfer", "Enviar Titulo - Erro de Integração");
					botStateFlow.navigationKey = BotOmnilink.STATES.ERRO_ENVIO;
				}
				return botStateFlow;
			}));
			
			setNextNavigationMap(new HashMap<String, String>(){{
				put(BotOmnilink.STATES.CONFIRMA_ENVIO, "#CONFIRMA_ENVIO");
				put(BotOmnilink.STATES.ERRO_ENVIO, "#ERRO_ENVIO");
				put(BotOmnilink.STATES.SDADOS, "#SDADOS");
				put("MAX_INPUT_ERROR", "/ATENDENTE");
			}});
		}};
	}
}

