package pe.com.interbank.spectrum.promart.microservice.server;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
@ApplicationPath("/spectrum/promart/ms")
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		register(pe.com.interbank.spectrum.promart.microservice.controller.PromartController.class);		
	}

}