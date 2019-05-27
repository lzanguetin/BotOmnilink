package br.com.voxage.botomnilink;

import com.fasterxml.jackson.databind.ObjectMapper;
import br.com.voxage.botomnilink.BotOmnilink;
import br.com.voxage.botomnilink.models.AlteradoExistente;
import br.com.voxage.botomnilink.models.Assessoria;
import br.com.voxage.botomnilink.models.Authorization;
import br.com.voxage.botomnilink.models.Clientes;
import br.com.voxage.botomnilink.models.Contratos;
import br.com.voxage.botomnilink.models.EnvTitulos;
import br.com.voxage.botomnilink.models.Envio;
import br.com.voxage.botomnilink.models.Espelhamento;
import br.com.voxage.botomnilink.models.Incluido;
import br.com.voxage.botomnilink.models.Incluir;
import br.com.voxage.botomnilink.models.InfoRemover;
import br.com.voxage.botomnilink.models.PIN;
import br.com.voxage.botomnilink.models.Remover;
import br.com.voxage.botomnilink.models.Titulos;
import br.com.voxage.botomnilink.models.TokenMicro;
import br.com.voxage.botomnilink.models.Token;

import static br.com.voxage.chat.botintegration.utils.AppLogger.log;
import br.com.voxage.chat.botintegration.utils.AsyncHttpUtils;
import br.com.voxage.chat.botintegration.utils.JsonUtils;

import com.google.gson.JsonSyntaxException;
import java.util.HashMap;

public class BotOmnilinkIntegration {
	private static final String BASE_URL = "http://localhost:8080/";
	
	public static Token validarUsuario(BotOmnilink bot) {
		String url = String.format("%s%s/%s", BASE_URL, "omnilinkapi", "tokens");
		
		HashMap<String, String> headers = new HashMap<String, String>();
		
		headers.putAll(Authorization.getHeaderMap(bot));
		
		try {
			AsyncHttpUtils asyncHttpUtils = new AsyncHttpUtils();
			Token trab = asyncHttpUtils.get(url, headers)
					.exceptionally(t->{
						throw(new RuntimeException(t));
					})
					.thenApply(resp-> {
						try {
							Token customerInfo = null;
	                        
	                        switch(resp.getStatusCode()) {
	                        	case 200:
	                        		String json = resp.getResponseBody();
	                        		customerInfo = JsonUtils.parseJson(json, Token.class);
	                                break;
	                        	case 500:
	                        		bot.setError(500);
	                            	throw(new RuntimeException(resp.getResponseBody()));	
	                        }
	                        return( customerInfo );
	                    } catch( JsonSyntaxException e ) {
	                        log.error(resp.getResponseBody(), bot.getSessionId());
	                        log.error("Erro ao fazer parse do json", e, bot.getSessionId());
	                        throw( new JsonSyntaxException(e) );
	                    } catch(Exception e) {
	                        log.error(resp.getResponseBody(), bot.getSessionId());
	                        log.error("Erro ao fazer parse do json", e, bot.getSessionId());
	                        throw( new RuntimeException(e) );
	                    }
	                }).get();

	        	return trab;
	        }catch(Exception e) {
	            throw( new RuntimeException(e) );
	        }
	    }
	
	public static Clientes getCliente(BotOmnilink bot,  String cpf) {
		String url = String.format("%s%s/%s/%s", BASE_URL, "omnilinkapi", "clientes", cpf);
		
		HashMap<String, String> headers = new HashMap<String, String>();
		
		headers.putAll(Authorization.getHeaderMap(bot));
		
		try {
			AsyncHttpUtils asyncHttpUtils = new AsyncHttpUtils();
			Clientes trab = asyncHttpUtils.get(url, headers)
					.exceptionally(t->{
						throw(new RuntimeException(t));
					})
					.thenApply(resp-> {
						try {
							Clientes customerInfo = null;
	                        
	                        switch(resp.getStatusCode()) {
	                        	case 200:
	                        		String json = resp.getResponseBody();
	                        		customerInfo = JsonUtils.parseJson(json, Clientes.class);
	                                break;
	                        	case 500:
	                        		bot.setError(500);
	                            	throw(new RuntimeException(resp.getResponseBody()));
	                        	case 404:
	                        		bot.setError(404);
	                            	throw(new RuntimeException(resp.getResponseBody()));
	                        }
	                        return( customerInfo );
	                    } catch( JsonSyntaxException e ) {
	                        log.error(resp.getResponseBody(), bot.getSessionId());
	                        log.error("Erro ao fazer parse do json", e, bot.getSessionId());
	                        throw( new JsonSyntaxException(e) );
	                    } catch(Exception e) {
	                        log.error(resp.getResponseBody(), bot.getSessionId());
	                        log.error("Erro ao fazer parse do json", e, bot.getSessionId());
	                        throw( new RuntimeException(e) );
	                    }
	                }).get();

	        	return trab;
	        }catch(Exception e) {
	            throw( new RuntimeException(e) );
	        }
	    }
	
	public static Titulos getTitulo(BotOmnilink bot,  String loja, String cod) {
		String url = String.format("%s%s/%s/%s/%s", BASE_URL, "omnilinkapi", "titulos", loja, cod);
		
		HashMap<String, String> headers = new HashMap<String, String>();
		
		headers.putAll(Authorization.getHeaderMap(bot));
		
		try {
			AsyncHttpUtils asyncHttpUtils = new AsyncHttpUtils();
			Titulos trab = asyncHttpUtils.get(url, headers)
					.exceptionally(t->{
						throw(new RuntimeException(t));
					})
					.thenApply(resp-> {
						try {
							Titulos customerInfo = null;
	                        
	                        switch(resp.getStatusCode()) {
	                        	case 200:
	                        		String json = resp.getResponseBody();
	                        		customerInfo = JsonUtils.parseJson(json, Titulos.class);
	                                break;
	                        	case 500:
	                        		bot.setError(500);
	                            	throw(new RuntimeException(resp.getResponseBody()));
	                        	case 404:
	                        		bot.setError(404);
	                            	throw(new RuntimeException(resp.getResponseBody()));
	                        }
	                        return( customerInfo );
	                    } catch( JsonSyntaxException e ) {
	                        log.error(resp.getResponseBody(), bot.getSessionId());
	                        log.error("Erro ao fazer parse do json", e, bot.getSessionId());
	                        throw( new JsonSyntaxException(e) );
	                    } catch(Exception e) {
	                        log.error(resp.getResponseBody(), bot.getSessionId());
	                        log.error("Erro ao fazer parse do json", e, bot.getSessionId());
	                        throw( new RuntimeException(e) );
	                    }
	                }).get();

	        	return trab;
	        }catch(Exception e) {
	            throw( new RuntimeException(e) );
	        }
	    }

	public static Contratos getContratos(BotOmnilink bot, String serie, String id) {
		String url = String.format("%s%s/%s/%s/%s", BASE_URL, "omnilinkapi", "contratos", serie, id);

		HashMap<String, String> headers = new HashMap<String, String>();
		
		headers.putAll(Authorization.getHeaderMap(bot));
		
		try {
			AsyncHttpUtils asyncHttpUtils = new AsyncHttpUtils();
			Contratos trab = asyncHttpUtils.get(url, headers)
					.exceptionally(t->{
						throw(new RuntimeException(t));
					})
					.thenApply(resp-> {
						try {
							Contratos customerInfo = null;
	                        
	                        switch(resp.getStatusCode()) {
	                        	case 200:
	                        		String json = resp.getResponseBody();
	                        		customerInfo = JsonUtils.parseJson(json, Contratos.class);
	                                break;
	                        	case 500:
	                        		bot.setError(500);
	                            	throw(new RuntimeException(resp.getResponseBody()));
	                        	case 404:
	                        		bot.setError(404);
	                            	throw(new RuntimeException(resp.getResponseBody()));
	                        }
	                        return( customerInfo );
	                    } catch( JsonSyntaxException e ) {
	                        log.error(resp.getResponseBody(), bot.getSessionId());
	                        log.error("Erro ao fazer parse do json", e, bot.getSessionId());
	                        throw( new JsonSyntaxException(e) );
	                    } catch(Exception e) {
	                        log.error(resp.getResponseBody(), bot.getSessionId());
	                        log.error("Erro ao fazer parse do json", e, bot.getSessionId());
	                        throw( new RuntimeException(e) );
	                    }
	                }).get();

	        	return trab;
	        }catch(Exception e) {
	            throw( new RuntimeException(e) );
	        }
	    }

	public static PIN validaPin(BotOmnilink bot, String codigo, String id) {
		String url = String.format("%s%s/%s/%s/%s", BASE_URL, "omnilinkapi", "codigos-seguranca", codigo, id);
		
		HashMap<String, String> headers = new HashMap<String, String>();
		
		headers.putAll(Authorization.getHeaderMap(bot));
		
		try {
			AsyncHttpUtils asyncHttpUtils = new AsyncHttpUtils();
			PIN trab = asyncHttpUtils.get(url, headers)
					.exceptionally(t->{
						throw(new RuntimeException(t));
					})
					.thenApply(resp-> {
						try {
							PIN customerInfo = null;
	                        
	                        switch(resp.getStatusCode()) {
	                        	case 200:
	                        		String json = resp.getResponseBody();
	                        		customerInfo = JsonUtils.parseJson(json, PIN.class);
	                                break;
	                        	case 500:
	                        		bot.setError(500);
	                            	throw(new RuntimeException(resp.getResponseBody()));
	                        	case 404:
	                        		bot.setError(404);
	                            	throw(new RuntimeException(resp.getResponseBody()));
	                        }
	                        return( customerInfo );
	                    } catch( JsonSyntaxException e ) {
	                        log.error(resp.getResponseBody(), bot.getSessionId());
	                        log.error("Erro ao fazer parse do json", e, bot.getSessionId());
	                        throw( new JsonSyntaxException(e) );
	                    } catch(Exception e) {
	                        log.error(resp.getResponseBody(), bot.getSessionId());
	                        log.error("Erro ao fazer parse do json", e, bot.getSessionId());
	                        throw( new RuntimeException(e) );
	                    }
	                }).get();

	        	return trab;
	        }catch(Exception e) {
	            throw( new RuntimeException(e) );
	        }
	    }

	public static TokenMicro getMicro(BotOmnilink bot) {
		String url = String.format("%s%s/%s", BASE_URL, "omnilinkapi", "tokens-micro-services");
		
		HashMap<String, String> headers = new HashMap<String, String>();
		
		headers.putAll(Authorization.getHeaderMap(bot));
		
		try {
			AsyncHttpUtils asyncHttpUtils = new AsyncHttpUtils();
			TokenMicro trab = asyncHttpUtils.get(url, headers)
					.exceptionally(t->{
						throw(new RuntimeException(t));
					})
					.thenApply(resp-> {
						try {
							TokenMicro customerInfo = null;
	                        
	                        switch(resp.getStatusCode()) {
	                        	case 200:
	                        		String json = resp.getResponseBody();
	                        		customerInfo = JsonUtils.parseJson(json, TokenMicro.class);
	                                break;
	                        	case 500:
	                        		bot.setError(500);
	                            	throw(new RuntimeException(resp.getResponseBody()));
	                        }
	                        return( customerInfo );
	                    } catch( JsonSyntaxException e ) {
	                        log.error(resp.getResponseBody(), bot.getSessionId());
	                        log.error("Erro ao fazer parse do json", e, bot.getSessionId());
	                        throw( new JsonSyntaxException(e) );
	                    } catch(Exception e) {
	                        log.error(resp.getResponseBody(), bot.getSessionId());
	                        log.error("Erro ao fazer parse do json", e, bot.getSessionId());
	                        throw( new RuntimeException(e) );
	                    }
	                }).get();

	        	return trab;
	        }catch(Exception e) {
	            throw( new RuntimeException(e) );
	        }
	    }
 	
	public static Espelhamento getCentrais(BotOmnilink bot, String serie) {
		String url = String.format("%s%s/%s/%s", BASE_URL, "omnilinkapi", "centrais-espelhadas", serie);
		
		HashMap<String, String> headers = new HashMap<String, String>();
		
		headers.putAll(Authorization.getHeaderMap(bot));
		
		try {
			AsyncHttpUtils asyncHttpUtils = new AsyncHttpUtils();
			Espelhamento trab = asyncHttpUtils.get(url, headers)
					.exceptionally(t->{
						throw(new RuntimeException(t));
					})
					.thenApply(resp-> {
						try {
							Espelhamento customerInfo = null;
	                        
	                        switch(resp.getStatusCode()) {
	                        	case 200:
	                        		String json = resp.getResponseBody();
	                        		customerInfo = JsonUtils.parseJson(json, Espelhamento.class);
	                                break;
	                        	case 500:
	                        		bot.setError(500);
	                            	throw(new RuntimeException(resp.getResponseBody()));
	                        	case 404:
	                        		bot.setError(404);
	                            	throw(new RuntimeException(resp.getResponseBody()));
	                        }
	                        return( customerInfo );
	                    } catch( JsonSyntaxException e ) {
	                        log.error(resp.getResponseBody(), bot.getSessionId());
	                        log.error("Erro ao fazer parse do json", e, bot.getSessionId());
	                        throw( new JsonSyntaxException(e) );
	                    } catch(Exception e) {
	                        log.error(resp.getResponseBody(), bot.getSessionId());
	                        log.error("Erro ao fazer parse do json", e, bot.getSessionId());
	                        throw( new RuntimeException(e) );
	                    }
	                }).get();

	        	return trab;
	        }catch(Exception e) {
	            throw( new RuntimeException(e) );
	        }
	    }

	public static Assessoria getAssessoria(BotOmnilink bot, String cnpj) {
		String url = String.format("%s%s/%s/%s", BASE_URL, "omnilinkapi", "assessorias", cnpj);
		
		HashMap<String, String> headers = new HashMap<String, String>();
		
		headers.putAll(Authorization.getHeaderMap(bot));
		
		try {
			AsyncHttpUtils asyncHttpUtils = new AsyncHttpUtils();
			Assessoria trab = asyncHttpUtils.get(url, headers)
					.exceptionally(t->{
						throw(new RuntimeException(t));
					})
					.thenApply(resp-> {
						try {
							Assessoria customerInfo = null;
	                        
	                        switch(resp.getStatusCode()) {
	                        	case 200:
	                        		String json = resp.getResponseBody();
	                        		customerInfo = JsonUtils.parseJson(json, Assessoria.class);
	                                break;
	                        	case 500:
	                        		bot.setError(500);
	                            	throw(new RuntimeException(resp.getResponseBody()));
	                        	case 404:
	                        		bot.setError(404);
	                            	throw(new RuntimeException(resp.getResponseBody()));
	                        }
	                        return( customerInfo );
	                    } catch( JsonSyntaxException e ) {
	                        log.error(resp.getResponseBody(), bot.getSessionId());
	                        log.error("Erro ao fazer parse do json", e, bot.getSessionId());
	                        throw( new JsonSyntaxException(e) );
	                    } catch(Exception e) {
	                        log.error(resp.getResponseBody(), bot.getSessionId());
	                        log.error("Erro ao fazer parse do json", e, bot.getSessionId());
	                        throw( new RuntimeException(e) );
	                    }
	                }).get();

	        	return trab;
	        }catch(Exception e) {
	            throw( new RuntimeException(e) );
	        }
	    }

	///////////////////////////////////////////////////////POSTS//////////////////////////////////////////////////////////

	public static EnvTitulos enviaTitulos(BotOmnilink bot, Envio env) {
		String url = String.format("%s%s/%s", BASE_URL, "omnilinkapi", "titulos");
		
		HashMap<String, String> headers = new HashMap<String, String>();
		
		headers.putAll(Authorization.getHeaderMap(bot));
		
		final ObjectMapper mapper = new ObjectMapper();
		
		try {
			AsyncHttpUtils asyncHttpUtils = new AsyncHttpUtils();
			EnvTitulos trab = asyncHttpUtils.post(url, headers, "application/json;charset=utf-8", mapper.writeValueAsString(env))
					.exceptionally(t->{
						throw(new RuntimeException(t));
					})
					.thenApply(resp-> {
						try {
							EnvTitulos customerInfo = null;
	                        
	                        switch(resp.getStatusCode()) {
	                        	case 200:
	                        		String json = resp.getResponseBody();
	                        		customerInfo = JsonUtils.parseJson(json, EnvTitulos.class);
	                                break;
	                        	case 500:
	                            	throw(new RuntimeException(resp.getResponseBody()));
	                        }
	                        return( customerInfo );
	                    } catch( JsonSyntaxException e ) {
	                        log.error(resp.getResponseBody(), bot.getSessionId());
	                        log.error("Erro ao fazer parse do json", e, bot.getSessionId());
	                        throw( new JsonSyntaxException(e) );
	                    } catch(Exception e) {
	                        log.error(resp.getResponseBody(), bot.getSessionId());
	                        log.error("Erro ao fazer parse do json", e, bot.getSessionId());
	                        throw( new RuntimeException(e) );
	                    }
	                }).get();

	        	return trab;
	        }catch(Exception e) {
	            throw( new RuntimeException(e) );
	        }
	    }

	public static Incluido addEspelhamentoCnpj(BotOmnilink bot, Incluir inc) {
		String url = String.format("%s%s/%s", BASE_URL, "omnilinkapi", "espelhamentos");
		
		HashMap<String, String> headers = new HashMap<String, String>();
		
		headers.putAll(Authorization.getHeaderMap(bot));
		
		final ObjectMapper mapper = new ObjectMapper();
		
		try {
			AsyncHttpUtils asyncHttpUtils = new AsyncHttpUtils();
			Incluido trab = asyncHttpUtils.post(url, headers, "application/json;charset=utf-8", mapper.writeValueAsString(inc))
					.exceptionally(t->{
						throw(new RuntimeException(t));
					})
					.thenApply(resp-> {
						try {
							Incluido customerInfo = null;
	                        
	                        switch(resp.getStatusCode()) {
	                        	case 200:
	                        		String json = resp.getResponseBody();
	                        		customerInfo = JsonUtils.parseJson(json, Incluido.class);
	                                break;
	                        	case 500:
	                            	throw(new RuntimeException(resp.getResponseBody()));
	                        }
	                        return( customerInfo );
	                    } catch( JsonSyntaxException e ) {
	                        log.error(resp.getResponseBody(), bot.getSessionId());
	                        log.error("Erro ao fazer parse do json", e, bot.getSessionId());
	                        throw( new JsonSyntaxException(e) );
	                    } catch(Exception e) {
	                        log.error(resp.getResponseBody(), bot.getSessionId());
	                        log.error("Erro ao fazer parse do json", e, bot.getSessionId());
	                        throw( new RuntimeException(e) );
	                    }
	                }).get();

	        	return trab;
	        }catch(Exception e) {
	            throw( new RuntimeException(e) );
	        }
	    }

	///////////////////////////////////////////////////////PUTS//////////////////////////////////////////////////////////	

	public static AlteradoExistente incluirEspelhamento(BotOmnilink bot, Incluir inc) {
		String url = String.format("%s%s/%s", BASE_URL, "omnilinkapi", "espelhamentos");
		
		HashMap<String, String> headers = new HashMap<String, String>();
		
		headers.putAll(Authorization.getHeaderMap(bot));
		
		final ObjectMapper mapper = new ObjectMapper();
		
		try {
			AsyncHttpUtils asyncHttpUtils = new AsyncHttpUtils();
			AlteradoExistente trab = asyncHttpUtils.put(url, headers, "application/json;charset=utf-8", mapper.writeValueAsString(inc), true)
					.exceptionally(t->{
						throw(new RuntimeException(t));
					})
					.thenApply(resp-> {
						try {
							AlteradoExistente customerInfo = null;
	                        
	                        switch(resp.getStatusCode()) {
	                        	case 200:
	                        		String json = resp.getResponseBody();
	                        		customerInfo = JsonUtils.parseJson(json, AlteradoExistente.class);
	                                break;
	                        	case 500:
	                            	throw(new RuntimeException(resp.getResponseBody()));
	                        }
	                        return( customerInfo );
	                    } catch( JsonSyntaxException e ) {
	                        log.error(resp.getResponseBody(), bot.getSessionId());
	                        log.error("Erro ao fazer parse do json", e, bot.getSessionId());
	                        throw( new JsonSyntaxException(e) );
	                    } catch(Exception e) {
	                        log.error(resp.getResponseBody(), bot.getSessionId());
	                        log.error("Erro ao fazer parse do json", e, bot.getSessionId());
	                        throw( new RuntimeException(e) );
	                    }
	                }).get();

	        	return trab;
	        }catch(Exception e) {
	            throw( new RuntimeException(e) );
	        }
	    }
	
	///////////////////////////////////////////////////////DELETES//////////////////////////////////////////////////////////
	
	public static Remover removeEspelhamento(BotOmnilink bot, InfoRemover list) {
		String url = String.format("%s%s/%s", BASE_URL, "omnilinkapi", "espelhamentos");
		
		HashMap<String, String> headers = new HashMap<String, String>();
		
		headers.putAll(Authorization.getHeaderMap(bot));
		
		final ObjectMapper mapper = new ObjectMapper();
		
		try {
			AsyncHttpUtils asyncHttpUtils = new AsyncHttpUtils();
			Remover trab = asyncHttpUtils.delete(url, headers, "application/json;charset=utf-8", mapper.writeValueAsString(list), true)
					.exceptionally(t->{
						throw(new RuntimeException(t));
					})
					.thenApply(resp-> {
						try {
							Remover customerInfo = null;
	                        
	                        switch(resp.getStatusCode()) {
	                        	case 200:
	                        		String json = resp.getResponseBody();
	                        		customerInfo = JsonUtils.parseJson(json, Remover.class);
	                                break;
	                        	case 500:
	                            	throw(new RuntimeException(resp.getResponseBody()));
	                        }
	                        return( customerInfo );
	                    } catch( JsonSyntaxException e ) {
	                        log.error(resp.getResponseBody(), bot.getSessionId());
	                        log.error("Erro ao fazer parse do json", e, bot.getSessionId());
	                        throw( new JsonSyntaxException(e) );
	                    } catch(Exception e) {
	                        log.error(resp.getResponseBody(), bot.getSessionId());
	                        log.error("Erro ao fazer parse do json", e, bot.getSessionId());
	                        throw( new RuntimeException(e) );
	                    }
	                }).get();

	        	return trab;
	        }catch(Exception e) {
	            throw( new RuntimeException(e) );
	        }
	    }

	
	
	
	
	
	
	
	
	
	
	
	
	
}