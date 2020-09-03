package pe.com.interbank.spectrum.promart.microservice.controller;

	import java.net.HttpURLConnection;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pe.com.interbank.spectrum.promart.microservice.model.PromartBody;
import pe.com.interbank.spectrum.promart.microservice.model.PromartError;
import pe.com.interbank.spectrum.promart.microservice.model.PromartResponse;
import pe.com.interbank.spectrum.promart.microservice.rest.client.PromartRestClient;
import pe.com.interbank.spectrum.promart.microservice.util.Constantes;
import pe.com.interbank.spectrum.promart.microservice.util.PtCache;
import pe.com.interbank.spectrum.promart.microservice.util.ResponseConstants;


/**
 * Controller que expone la interfaz rest de ProviderId
 * 
 * @author Gary Espinoza
 *
 */
@Component
@Path(Constantes.PATH_REST_GENERAL)
public class PromartController {

  private static final Logger logger = LoggerFactory.getLogger(PromartController.class);

  @Autowired
  private PromartRestClient promartRestClient;
  
  /**
   * Metodo REST para crear el login
   * 
   * @param request
   * @return
   * @throws Exception
   */
  @GET
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Path(Constantes.PATH_REST_VALIDATE)
  public String validate(@HeaderParam("Authorization") String tokenHeader) {
    try {       	
    	logger.info("REST ProviderIdController - validate - INICIO ");
    	Response res = promartRestClient.executeRestClient(tokenHeader,PtCache.getInstance().getProperty(Constantes.URL_SERV_GEN_TOK_VALIDATE));
    	if(res == null){
    		PromartError error = new PromartError(ResponseConstants.VALUE_ERROR_GENERIC_CODE, PtCache.getMessage(Constantes.MSG_ERROR_MS));
        	int responseCode = ResponseConstants.VALUE_ERROR_GENERIC_CODE;
        	String responseDescription = PtCache.getMessage(Constantes.MSG_ERROR_MS);
        	PromartBody body = null;
        	PromartResponse PromartResponse = new PromartResponse(responseCode, responseDescription, error, body);
	        logger.info("REST ProviderIdController - validate - FIN ");
	        return ControllerSupport.buildJSONResponse(PromartResponse);
    	}else if(res.getStatus()!=HttpURLConnection.HTTP_OK){
			return "false";
		}else{
			return "true";					
		}    	
    } catch (Exception e) {
    	logger.error("Error en Assembler:" + e.getMessage(), e);
    	PromartError error = new PromartError(ResponseConstants.VALUE_ERROR_GENERIC_CODE, PtCache.getMessage(Constantes.MSG_ERROR_MS));
    	int responseCode = ResponseConstants.VALUE_ERROR_GENERIC_CODE;
    	String responseDescription = PtCache.getMessage(Constantes.MSG_ERROR_MS);
    	PromartBody body = null;
    	PromartResponse PromartResponse = new PromartResponse(responseCode, responseDescription, error, body);
    	return ControllerSupport.buildJSONResponse(PromartResponse);
    }
  }

  
  /**
   * Metodo REST para crear el login
   * 
   * @param request
   * @return
   * @throws Exception
   */
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Path(Constantes.PATH_REST_GENERATE)
  public String generate(@HeaderParam("Authorization") String tokenHeader) {
    try {       	
    	logger.info("REST ProviderIdController - generate - INICIO ");
    	Response res = promartRestClient.executeRestClient(tokenHeader,PtCache.getInstance().getProperty(Constantes.URL_SERV_GEN_TOK_AUTH));
    	if(res == null){
    		PromartError error = new PromartError(ResponseConstants.VALUE_ERROR_GENERIC_CODE, PtCache.getMessage(Constantes.MSG_ERROR_MS));
        	int responseCode = ResponseConstants.VALUE_ERROR_GENERIC_CODE;
        	String responseDescription = PtCache.getMessage(Constantes.MSG_ERROR_MS);
        	PromartBody body = null;
        	PromartResponse PromartResponse = new PromartResponse(responseCode, responseDescription, error, body);
	        logger.info("REST ProviderIdController - validate - FIN ");
	        return ControllerSupport.buildJSONResponse(PromartResponse);
    	}else if(res.getStatus()!=HttpURLConnection.HTTP_OK){
			return "false";
		}else{
			return "true";					
		}    	
    } catch (Exception e) {
    	logger.error("Error en Assembler:" + e.getMessage(), e);
    	PromartError error = new PromartError(ResponseConstants.VALUE_ERROR_GENERIC_CODE, PtCache.getMessage(Constantes.MSG_ERROR_MS));
    	int responseCode = ResponseConstants.VALUE_ERROR_GENERIC_CODE;
    	String responseDescription = PtCache.getMessage(Constantes.MSG_ERROR_MS);
    	PromartBody body = null;
    	PromartResponse PromartResponse = new PromartResponse(responseCode, responseDescription, error, body);
    	return ControllerSupport.buildJSONResponse(PromartResponse);
    }
  }
}
