package pe.com.interbank.spectrum.promart.microservice.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

/**
 * Clase que configura el filtro de las interfaces Rest para aceptar headers e invocaciones desde
 * dominios especificos
 * 
 * @author Adrian Pareja
 *
 */
@Provider
public class CORSResponseFilter implements ContainerResponseFilter {

  public void filter(ContainerRequestContext requestContext,
      ContainerResponseContext responseContext) throws IOException {

    MultivaluedMap<String, Object> headers = responseContext.getHeaders();

    headers.add("Access-Control-Allow-Origin", "*");
    headers.add("Access-Control-Allow-Credentials", false);
    headers.add("Access-Control-Allow-Methods", "POST");
    headers.add("Access-Control-Allow-Headers", "X-Requested-With, Content-Type");
  }

}
