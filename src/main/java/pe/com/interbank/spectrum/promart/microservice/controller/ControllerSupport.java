package pe.com.interbank.spectrum.promart.microservice.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ControllerSupport {
	
	private static final Logger logger = Logger.getLogger(ControllerSupport.class);
	
	/**
	   * Descripcion: Construir respuesta JSON en base a objeto
	   * @return
	   */
	  public static String buildJSONResponse(Object respuesta){
	    ObjectMapper objectMapper= new ObjectMapper();
	    String response;
	    try {
	      response = objectMapper.writeValueAsString(respuesta);
	    } catch (JsonProcessingException e) {
	      logger.error(e.getMessage(), e);
	      return "Error en estructura de objeto de respuesta";
	    }
	    return response;
	  }
	  public static Map<String,Object> buildMapFromString(String string) throws IOException{
			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> map = new HashMap<String, Object>();
			map = mapper.readValue(string, new TypeReference<Map<String, Object>>(){});
			return map;
		}
}
