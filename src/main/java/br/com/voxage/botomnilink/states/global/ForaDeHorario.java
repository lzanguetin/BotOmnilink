package br.com.voxage.botomnilink.states.global;

import java.util.HashMap;

import br.com.voxage.botomnilink.BotOmnilink;
import br.com.voxage.vbot.BotState;
import br.com.voxage.vbot.BotStateFlow;
import br.com.voxage.vbot.BotStateInteractionType;

public class ForaDeHorario {
	@SuppressWarnings("serial")
	public static BotState load(BotOmnilink bot) {
        return new BotState("/") {{
            setId("FORA_HORARIO");
            setBotStateInteractionType(BotStateInteractionType.NO_INPUT);
            setPreFunction(botState -> {
                BotStateFlow state = new BotStateFlow();
                state.flow = BotStateFlow.Flow.CONTINUE;

                state.navigationKey = "#FINALIZAR";

                return state;
            });
            setNextNavigationMap(new HashMap<String, String>() {{
                put("#FINALIZAR", "#FINALIZAR");
            }});
        }};
    }
}