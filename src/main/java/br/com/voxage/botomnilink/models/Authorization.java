package br.com.voxage.botomnilink.models;

import java.util.HashMap;
import java.util.Map;

import br.com.voxage.botomnilink.BotOmnilink;

public class Authorization {
    
    private static final HashMap<String, String> MAP = new HashMap<String, String>();
    private static final HashMap<String, String> MAP_TOKEN = new HashMap<String, String>();
    private static final HashMap<String, String> MAP_MICRO = new HashMap<String, String>();
    public final static String HASHCODE = "ktl6bVaZpVr-pgYyRzkAvAj878pZXOmqYvcPqjij_TyJKyn8ZOgl5R4NH6sATOvrE7wp6NP3WFLJdWilPN57TZNFqHyKandLbqlw0_WIgfFRAYAAwtJtQFLGlKecOx84SMlli18CnwzbcphPMBLkmc9j-dSlAkZCHH1GUJhYswkB2qg-9HWzW6s35vKDkQXz";
    
    static {
        MAP.put("Authorization", "Bearer " + HASHCODE);
    }
      
    public static Map<String, String> getHeaderMap(BotOmnilink bot){
    	if ((bot.getMicro() != null) && (bot.getMicro().getAccessToken() != null)){
    		final String HASHCODE_MICRO = bot.getMicro().getAccessToken();
        	
            MAP_MICRO.put("Authorization", "Bearer " + HASHCODE_MICRO);
            
        	return MAP_MICRO;
    	}else if ((bot.getToken() != null) && (bot.getToken().getAcessToken() != null)){
    		final String HASHCODE_TOKEN = bot.getToken().getAcessToken();
        	
            MAP_TOKEN.put("Authorization", "Bearer " + HASHCODE_TOKEN);
            
        	return MAP_TOKEN;
    	}else {
    		return MAP;
    	}   
    }
}
	