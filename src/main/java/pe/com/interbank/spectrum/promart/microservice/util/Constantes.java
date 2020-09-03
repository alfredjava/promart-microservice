package pe.com.interbank.spectrum.promart.microservice.util;

public class Constantes {

	// PATH REQUEST
	public static final String PATH_REST_GENERAL = "/";
	public static final String PATH_REST_VALIDATE = "validate";
	public static final String PATH_REST_GENERATE = "generate";
	
	// PAHT PROPERTIES
	public static final String APP_RESOURCES_LOCATION = "app.resources.location";
	
	// PATH REQUEST
	
	public static final String URL_SERV_GEN_TOK_VALIDATE = "url.rest.service.generate.token.validate";//validate
	public static final String URL_SERV_GEN_TOK_AUTH = "url.rest.service.generate.token.auth";//validate
	
	
	
	public static final String PROPERTY_FILE_MESSAGES = "message.Messages";
	public static final String MSG_ERROR_MS = "msg.error.occurred.ms";
	
	protected Constantes() {
		throw new IllegalAccessError("Clase Constantes");
	}

}
