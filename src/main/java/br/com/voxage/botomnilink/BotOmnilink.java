package br.com.voxage.botomnilink;

import static br.com.voxage.chat.botintegration.utils.AppLogger.log;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.google.gson.Gson;

import br.com.voxage.botomnilink.models.AlteradoExistente;
import br.com.voxage.botomnilink.models.Assessoria;
import br.com.voxage.botomnilink.models.Centrais;
import br.com.voxage.botomnilink.models.Central;
import br.com.voxage.botomnilink.models.Clientes;
import br.com.voxage.botomnilink.models.Contratos;
import br.com.voxage.botomnilink.models.DadosFluxo;
import br.com.voxage.botomnilink.models.EnvTitulos;
import br.com.voxage.botomnilink.models.Envio;
import br.com.voxage.botomnilink.models.Espelhamento;
import br.com.voxage.botomnilink.models.EspelhamentoLista;
import br.com.voxage.botomnilink.models.Incluido;
import br.com.voxage.botomnilink.models.Incluir;
import br.com.voxage.botomnilink.models.InfoRemover;
import br.com.voxage.botomnilink.models.PIN;
import br.com.voxage.botomnilink.models.Remover;
import br.com.voxage.botomnilink.models.Titulos;
import br.com.voxage.botomnilink.models.Token;
import br.com.voxage.botomnilink.models.TokenMicro;
import br.com.voxage.botomnilink.models.TransferType;
import br.com.voxage.chat.botintegration.ISearchEngine;
import br.com.voxage.chat.botintegration.ISearchEngineCredentials;
import br.com.voxage.chat.botintegration.TextSearchEngine;
import br.com.voxage.chat.botintegration.annotation.Bot;
import br.com.voxage.chat.botintegration.entities.AttendantClientInfo;
import br.com.voxage.chat.botintegration.entities.BotImageType;
import br.com.voxage.vbot.BotContext;
import br.com.voxage.vbot.BotState;
import br.com.voxage.vbot.BotStateInteractionType;
import br.com.voxage.vbot.VBot;
import br.com.voxage.vbot.utils.ClassFinder;

@SuppressWarnings("serial")
@Bot(name="BotOmnilink")
public class BotOmnilink extends VBot {

	public static int NO_INPUT_TIMEOUT = 300000;
	
	private String lastState;
	private DadosFluxo dadosFluxo;
	private Token key;
	private Clientes clientes;
	private Contratos contrato;
	private PIN pin;
	private Espelhamento esp;
	private Remover rem;
	private Central cent;
	private Centrais cents;
	private TokenMicro micro;
	private Titulos titulos;
	private Assessoria assessoria;
	private EnvTitulos env;
	private Envio envio;
	private InfoRemover info;
	private Incluido inc;
	private Incluir incluir;
	private AlteradoExistente alter;
	private EspelhamentoLista list;
	private TransferType type;
	private String option;
	private String group;
	private Integer error;
	private AttendantClientInfo aInfo;
	private List<AttendantClientInfo> cInfo;
	private Integer qtd;
	
	 public interface STATES{
		 //start
		 String START = "start";
		 
		 //direct transfer
		 String AGENDAMENTO = "agendamento";
		 String CANCELAMENTO = "cancelamento";
		 String SUPORTE = "suporte";
		 String ATIVACAO = "ativacao";
		 String OAGENDAMENTO = "oagendamento";
		 String OCANCELAMENTO =	"ocancelamento";
		 String OSUPORTE = "osuporte";
		 String OATIVACAO = "oativacao";
	    
		 //menus
		 String MENU_WEB = "menu_web";
		 String MENU_WHATS = "menu_whats";
		 String ESPELHAMENTO_WEB = "espelhamento_web";
		 String ESPELHAMENTO_WHATS = "espelhamento_whats";		 
		 String FINALIZAR_ESP_WEB = "finalizar_esp_web";
		 String FINALIZAR_ESP_WHATS = "finalizar_esp_whats";
		 String EXCLUIR_CENTRAL_WEB = "excluir_central_web";
		 String EXCLUIR_CENTRAL_WHATS = "excluir_central_whats";
		 String FINANCEIRO_WEB = "financeiro_web";
		 String FINANCEIRO_WHATS = "financeiro_whats";
		 String ESCOLHER_TITULO_WEB = "escolher_titulo_web";
		 String ESCOLHER_TITULO_WHATS = "escolher_titulo_whats";		 
		 String FINALIZAR_FINANCEIRO_WEB = "finalizar_financeiro_web";
		 String FINALIZAR_FINANCEIRO_WHATS = "finalizar_financeiro_whats";
		 String TEC_VALIDA_WEB = "tec_valida_web";
		 String TEC_VALIDA_WHATS = "tec_valida_whats";
		 String INCLUIR_ESP_WEB = "incluir_esp_web";
		 String INCLUIR_ESP_WHATS = "incluir_esp_whats";
		 String RETIRAR_ESP_WEB = "retirar_esp_web";
		 String RETIRAR_ESP_WHATS = "retirar_esp_whats";		 
		 String INCLUSAO_ESP_WEB = "inclusao_esp_web";
		 String INCLUSAO_ESP_WHATS = "inclusao_esp_whats";	 
		 String CENT_INEXIST_WEB = "cent_inexist_web";
		 String CENT_INEXIST_WHATS = "cent_inexist_whats";
		 String OUTROS_WEB = "outros_web";
		 String OUTROS_WHATS = "outros_whats";
		 
		//tipos de atendimento
		 String FINANCEIRO = "financeiro";
		 String SDADOS = "sdados";
		 
		 //campos globais
		 String TOKEN = "token";
		 String CPFCNPJ = "cpfcnpj";
		 String VERIFCLIENTE = "verifcliente";
		 String ATENDENTE = "atendente";
		 String FINALIZAR = "finalizar";
		 String FORA_HORARIO = "fora_horario";
		 
		 //utils		 
		 String TIPO_ESPELHAMENTO = "tipo_espelhamento";
		 String TIPO_FINALIZAR_ESP = "tipo_finalizar_esp";
		 String TIPO_FINANCEIRO = "tipo_financeiro";
		 String TIPO_FINALIZAR_FINANCEIRO = "tipo_finalizar_financeiro";
		 String TIPO_ESCOLHER_TITULO = "tipo_escolhet_titulo";
		 String TIPO_TEC_VALIDA = "tipo_tec_valida";
		 String TIPO_EXCLUIR_CENTRAL = "tipo_excluir_central";
		 String TIPO_INCLUIR_ESP = "tipo_incluir_esp";
		 
		//campos espec�ficos espelhamento		 
		 String SERIE_ESP = "serie_esp";
		 String CONS_CONTRATO = "cons_contrato";
		 String OBTER_PIN = "obter_pin";
		 String CONSULTAR_ESP = "consultar_esp";
		 String VALIDAR_PIN = "validar_pin";
		 String ERRO_PIN = "erro_pin";		 
		 String ERRO_INTEGRA_ESP = "erro_integra_esp";
		 String VALIDAR_ESP = "validar_esp";
		 String AGUARDA_ATIV = "aguarda_ativ";
		 String CANCELADO = "cancelado";
		 String SUSPENSO = "suspenso";		 
		 String ERRO_TEC = "erro_tec";
		 String SEM_ESP = "sem_esp";
		 String LISTR_CENTRAL = "listr_central";
		 String MAX_PORT = "max_port";
		 String CNPJ_ESP = "cnpj_esp";
		 String OUTROS = "outros";
		 String REMOVER_ESP = "remover_esp";
		 String ERRO_REMOVER = "erro_remover";
		 String RETIRAR_ESP = "retirar_esp";
		 String PORTA_ESP = "porta_esp";
		 String INCLUIR_ESP = "incluir_esp";
		 String ERRO_INC = "erro_inc";
		 String INCLUSAO_ESP = "inclusao_esp";
		 String ESP_EXISTE = "esp_existe";
		 String CENT_INEXIST = "cent_inexist";
		 String INC_EXIST = "inc_exist";
		 String ERRO_ALTER = "erro_alter";
		 String ERRO_CONTRATO = "erro_contrato";
		 String SEM_SERIE = "sem_serie";
		 String ERRO_INPUT = "erro_input";
		 
		//campos espec�ficos financeiro
		 String TITULOS = "titulos";
		 String INFORME = "informe";
		 String OBTER_TITULOS = "obter_titulos";
		 String SEM_BOLETO = "sem_boleto";
		 String ESCOLHER_TITULO = "escolher_titulo";
		 String FONE_ASSESSORIA = "fone_assessoria";
		 String MULTI_ASSESSORIA = "multi_assessoria";
		 String INFO_ASSESSORIA = "info_assessoria";
		 String BOLETOS_ATRASADOS = "boletos_atrasados";
		 String BOLETOS_VENCER = "boletos_vencer";
		 String ENVIAR_TITULOS = "enviar_titulos";
		 String CONFIRMA_ENVIO = "confirma_envio";
		 String ERRO_ENVIO = "erro_envio";
		 String ERRO_TITULO = "erro_titulo";
		 String ERRO_ASSE = "erro_asse";
		 
		 //transferencia
		 String TRANSFERIR = "transfer_to_attendant(1)";
	}
	 
	public BotOmnilink() {
		this.dadosFluxo = new DadosFluxo();
	}
	 
	public Map<String, Object> getDefaultParameters() {
		return null;
	}

	public String getCustomBotName() {
		return "Omnilink";
	}

	public String getCustomImageName() {
		return "omnilink-logo.png";
	}

	public BotImageType getBotImageType() {
		return BotImageType.CUSTOM;
	}

	public boolean recordDialog() {
		return false;
	}

	public void writeTimeoutResult() {
	}

	@Override
	public String getDebugCommand(String arg0) {
		return null;
	}

	@Override
	protected TextSearchEngine getDefaultAutoCompleteSearchEngine() {
		return null;
	}

	@Override
	protected ISearchEngineCredentials getDefaultEngineCredentials() {
		return null;
	}

	@Override
	protected ISearchEngine getDefaultNLPSearchEngine() {
		return null;
	}

	@Override
	protected String getPackageName() {
		return "br.com.voxage.botomnilink";
	}

	@Override
	public void loadStates() {
		BotContext botContext = new BotContext();
		Map<String, BotState> m = new HashMap<>();
		m.putAll(ClassFinder.loadAllStates("br.com.voxage.botomnilink.states", BotOmnilink.class, BotOmnilink.this));
		botContext.setId("/");
		botContext.setContextNavigationMap(new HashMap<String, String>() {			
        });
		m.put("END", new BotState("/") {
            {
                setId("END");
                setBotStateInteractionType(BotStateInteractionType.TERMINATE);
            }
        });
		
		m.put("TERMINATE", new BotState("/") {
            {
                setId("TERMINATE");
                setBotStateInteractionType(BotStateInteractionType.TERMINATE);
            }
        });
		
		botContext.setStatesMap(m);
		super.contexts.put("/", botContext);
	}
	
    public void setLastState(String lastState) {
        this.lastState = lastState;
    }
    
    public String getLastState() {
        return lastState;
    }

	public DadosFluxo getDadosFluxo() {
		return this.dadosFluxo;
	}
	
	public static synchronized String readString(String name) {

		String path = BotOmnilink.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		path = path.replace("BotOmnilink.jar", "").substring(1);
		
		System.out.println("ABSOLUTE PATH  ====> " + path);
		
		String value = "";

		try {
			FileReader reader = new FileReader(path + "botomnilink.ini");
			Properties properties = new Properties();
			properties.load(reader);
			
			reader.close();
			value = properties.getProperty(name, null);

			log.info("propertie " + name + " => " + value , "");
			
			if (value == null) {
				return null;
			} else if (value.length() == 0) {
				value = null;
			}
		} catch (IOException e) {
			log.error("ERRO ARQUIVO NAO ENCONTRADO" , "");
			return null;
		}

		return value;
	}
	
	public void setToken(Token key) {
		this.key = key;
	}
	
	public Token getToken() {
		return this.key;
	}
    
	public void setClientes(Clientes clientes) {
		this.clientes = clientes;
	}
	
	public Clientes getClientes() {
		return this.clientes;
	}
	
	public void setContratos(Contratos contrato) {
		this.contrato = contrato;
	}
	
	public Contratos getContratos() {
		return this.contrato;
	}
    
	public void setPIN(PIN pin) {
		this.pin = pin;
	}
	
	public PIN getPIN() {
		return this.pin;
	}
	
	public void setEspelhamento(Espelhamento esp) {
		this.esp = esp;
	}
	
	public Espelhamento getEspelhamento() {
		return this.esp;
	}
	
	public void setRemover(Remover rem) {
		this.rem = rem;
	}
	
	public Remover getRemover() {
		return this.rem;
	}
	
	public void setCentrais(Central cent) {
		this.cent = cent;
	}
	
	public Central getCentrais() {
		return this.cent;
	}

	public TokenMicro getMicro() {
		return micro;
	}

	public void setMicro(TokenMicro micro) {
		this.micro = micro;
	}

	public Titulos getTitulos() {
		return titulos;
	}

	public void setTitulos(Titulos titulos) {
		this.titulos = titulos;
	}

	public Assessoria getAssessoria() {
		return assessoria;
	}

	public void setAssessoria(Assessoria assessoria) {
		this.assessoria = assessoria;
	}

	public EnvTitulos getEnv() {
		return env;
	}

	public void setEnv(EnvTitulos env) {
		this.env = env;
	}

	public Centrais getCents() {
		return cents;
	}

	public void setCents(Centrais cents) {
		this.cents = cents;
	}

	public Envio getEnvio() {
		return envio;
	}

	public void setEnvio(Envio envio) {
		this.envio = envio;
	}

	public InfoRemover getInfo() {
		return info;
	}

	public void setInfo(InfoRemover info) {
		this.info = info;
	}

	public Incluido getInc() {
		return inc;
	}

	public void setInc(Incluido inc) {
		this.inc = inc;
	}

	public Incluir getIncluir() {
		return incluir;
	}

	public void setIncluir(Incluir incluir) {
		this.incluir = incluir;
	}

	public AlteradoExistente getAlter() {
		return alter;
	}

	public void setAlter(AlteradoExistente alter) {
		this.alter = alter;
	}

	public EspelhamentoLista getList() {
		return list;
	}

	public void setList(EspelhamentoLista list) {
		this.list = list;
	}

	public TransferType getType() {
		return type;
	}

	public void setType(TransferType type) {
		this.type = type;
	}
	
    public void transferType() {    	
        Gson gson = new Gson();
        TransferType type = gson.fromJson(jsonCustomParams, TransferType.class);
		
        setType(type);
    }

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String string) {
		this.group = string;
	}

	public Integer getError() {
		return error;
	}

	public void setError(Integer error) {
		this.error = error;
	}

	public List<AttendantClientInfo> getcInfo() {
		return cInfo;
	}

	public void setcInfo(List<AttendantClientInfo> cInfo) {
		this.cInfo = cInfo;
	}

	public AttendantClientInfo getaInfo() {
		return aInfo;
	}

	public void setaInfo(AttendantClientInfo aInfo) {
		this.aInfo = aInfo;
	}

	public Integer getQtd() {
		return qtd;
	}

	public void setQtd(Integer qtd) {
		this.qtd = qtd;
	}
}