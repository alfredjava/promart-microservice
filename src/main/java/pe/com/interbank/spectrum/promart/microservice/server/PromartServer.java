package pe.com.interbank.spectrum.promart.microservice.server;

import javax.servlet.Filter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import pe.com.interbank.spectrum.promart.microservice.filter.SecurityFilter;





/**
 * Clase que realiza la configuracion del contexto spring del microservicio
 * 
 * @author lpazdavi
 *
 */

@Configuration
@EnableAutoConfiguration
@EnableAsync
@ComponentScan(basePackages = "pe.com.interbank.spectrum.promart.microservice")
public class PromartServer {
	
  /**
   * Ejecuta la aplicacion sobre Spring Boot y un contenedor de Servlets
   * 
   * @param args
   *        Argumentos de programa
   */
  public static void main(String[] args) {
    System.setProperty("spring.config.name", "promart-jersey-config");
    SpringApplication.run(PromartServer.class, args);
  }
  
  /**
   * Bean para filtro de seguridad
   * 
   * @return
   */
//  @Bean
//  public Filter securityFilter() {
//    return new SecurityFilter();
//  }
}
