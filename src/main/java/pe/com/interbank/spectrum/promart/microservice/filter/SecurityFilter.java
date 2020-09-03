package pe.com.interbank.spectrum.promart.microservice.filter;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SecurityFilter implements Filter {

	private static final String HTTPS = "https";
	private static final String X_FORWARDED_PROTO = "X-Forwarded-Proto";
	private static final Logger logger = LoggerFactory.getLogger(SecurityFilter.class);

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// No se inicializa parametros

	}

	@Override
	public void destroy() {
		// No se ejecuta nada despues del proceso

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;

		logger.debug("protocolo:" + httpRequest.getHeader(X_FORWARDED_PROTO));

		if (httpRequest.getHeader(X_FORWARDED_PROTO) != null) {
			if (HTTPS.equalsIgnoreCase(httpRequest.getHeader(X_FORWARDED_PROTO))) {
				logger.debug("HTTPS");
				chain.doFilter(request, response);
			} else {
				logger.debug("HTTP");
				HttpServletResponse httpResponse = (HttpServletResponse) response;
				try {
					URI uri1 = new URI(httpRequest.getRequestURL().toString());
					logger.debug("uri1 Path:" + uri1.getPath());

					URI uri2 = new URI(HTTPS, uri1.getUserInfo(), uri1.getHost(), -1, uri1.getPath(), httpRequest.getQueryString(), null);
					logger.debug("uri2 Path:" + uri1.getPath());

					httpResponse.sendRedirect(uri2.toString());
				} catch (URISyntaxException e) {
					logger.error("Ocurrio un error al obtener las URIs");
					throw new ServletException("Ocurrio un error al obtener las URIs", e);
				}
			}
		}
	}
}
