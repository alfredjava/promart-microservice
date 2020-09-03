package pe.com.interbank.spectrum.promart.microservice.rest.client;

import java.io.File;

import javax.net.ssl.SSLContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.SslConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import pe.com.interbank.spectrum.promart.microservice.util.Constantes;
import pe.com.interbank.spectrum.promart.microservice.util.PtCache;


@Service
public class PromartRestClient {

	private static final String SSL = "SSL";
	private static final Logger logger = LoggerFactory.getLogger(PromartRestClient.class);
	private WebTarget target;

	public static SSLContext getSSLContextCertified() {
		SSLContext context = null;
		try {
			String resourceDir = PtCache.getInstance().getProperty(Constantes.APP_RESOURCES_LOCATION);

			SslConfigurator sslConfig = SslConfigurator.newInstance()
					.keyStoreFile(resourceDir + File.separator + PtCache.getInstance().getProperty("keystore.blx.file"))
					.keyPassword(PtCache.getInstance().getProperty("keystore.blx.password"))
					.keyStoreType(PtCache.getInstance().getProperty("keystore.blx.type")).securityProtocol(SSL);
			context = sslConfig.createSSLContext();

		} catch (Exception e) {
			logger.error(e.toString(), e);
		}

		return context;
	}
	
	public Response executeRestClient(String header, String url) {
	    Client client = ClientBuilder.newBuilder().sslContext(getSSLContextCertified())
	        .hostnameVerifier((String hostname, javax.net.ssl.SSLSession sslSession) -> true).build();
	    target = client.target(url);
	    Builder b = target.request(MediaType.APPLICATION_JSON_TYPE);
	    return b.header("Authorization",header).get();
	  }
}
