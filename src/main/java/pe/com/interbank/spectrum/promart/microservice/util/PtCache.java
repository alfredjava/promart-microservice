package pe.com.interbank.spectrum.promart.microservice.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase que carga los properties y funciona como un singleton
 * 
 * @author Gary Espinoza
 *
 */
public class PtCache {      
 
	private final Properties configProp = new Properties();

	private static final String PROPERTIES_EXTENSION = ".properties";

	private static final Logger logger = LoggerFactory.getLogger(PtCache.class);

	private PtCache() {

		String ambiente = getPropertyFromEnvOrSystem("ambiente");
		String propertiesNonSensitivePath = getPropertyFromEnvOrSystem("propertiesNonSensitivePath");

		if (propertiesNonSensitivePath == null) {
			propertiesNonSensitivePath = "/data/promart-microservice/properties/config";
		}

		if (ambiente != null) {
			try (InputStream configIn = new FileInputStream(
					propertiesNonSensitivePath + "-" + ambiente + PROPERTIES_EXTENSION);) {
                logger.info("Leer todos los properties desde el archivo externo config" + "-" + ambiente
                        + PROPERTIES_EXTENSION);
                configProp.load(configIn);
			} catch (FileNotFoundException ex) {
				logger.error("Archivo de properties no encontrado");
				logger.error(ex.toString(), ex);
			} catch (IOException e) {
				logger.error("Error al cargar los properties");
				logger.error(e.toString(), e);
			}
		} else {
			logger.error("No se pudo obtener ambiente de las variables de entorno");
		}
	}

	private String getPropertyFromEnvOrSystem(String name) {
		String property = System.getenv(name);
		if (property == null) {
			property = System.getProperty(name);
		}

		return property;
	}


	private static class LazyHolder {
		private static final PtCache INSTANCE = new PtCache();

		private LazyHolder() {

		}
	}

	public static PtCache getInstance() {
		return LazyHolder.INSTANCE;
	}

	public String getProperty(String key) {
		return configProp.getProperty(key);
	}

	public Set<String> getAllPropertyNames() {
		return configProp.stringPropertyNames();
	}

	public boolean containsKey(String key) {
		return configProp.containsKey(key);
	}
	
  public static String getMessage(String key){
//			Locale en_US = new Locale("en", "US");
		Locale es_ES = new Locale("es", "ES");
		ResourceBundle bundle = ResourceBundle.getBundle(Constantes.PROPERTY_FILE_MESSAGES, es_ES);
		String value = bundle.getString(key);
		return value;
	}
	public static String getMessage(String key,Object [] params) {
//			Locale en_US = new Locale("en", "US");
		Locale es_ES = new Locale("es", "ES");
		ResourceBundle bundle = ResourceBundle.getBundle(Constantes.PROPERTY_FILE_MESSAGES, es_ES);
		return MessageFormat.format(bundle.getString(key), params);		
	}
		
}
