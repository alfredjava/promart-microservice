package pe.com.interbank.spectrum.promart.microservice.util;

public class ResponseConstants {

	public static final String KEY_ERROR = "error";
	public static final String KEY_ERROR_CODE = "code";
	public static final String KEY_ERROR_MESSAGE = "message";
	public static final int VALUE_ERROR_GENERIC_CODE = 1;
	public static final int VALUE_CORRECT_GENERIC_CODE = 0;
	
	protected ResponseConstants() {
		throw new IllegalAccessError("Clase ErrorConstants");
	}

}
