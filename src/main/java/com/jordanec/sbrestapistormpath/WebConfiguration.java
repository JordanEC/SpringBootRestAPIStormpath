package com.jordanec.sbrestapistormpath;


import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module.Feature;

@Configuration
public class WebConfiguration {
	@EnableWebMvc
	protected static class WebMvcConfiguration extends WebMvcConfigurerAdapter {
		@Override
		public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
			converters.add(jacksonMessageConverter());
			super.configureMessageConverters(converters);
		}

		private MappingJackson2HttpMessageConverter jacksonMessageConverter() {
			MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
			ObjectMapper mapper = new ObjectMapper();
			Hibernate4Module hibernate4Module = new Hibernate4Module();
			hibernate4Module.configure(Feature.FORCE_LAZY_LOADING, true);
			hibernate4Module.configure(Feature.USE_TRANSIENT_ANNOTATION, false);
			mapper.registerModule(hibernate4Module);
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			mapper.configure(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE, false);
			mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
			messageConverter.setObjectMapper(mapper);
			return messageConverter;
		}
	}	
}

@Component
class SimpleFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;
        String headerName, paramName;
        
        System.out.println("\nRequest headers\n");
        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()) {
        	headerName = enumeration.nextElement();
        	System.out.println(headerName + ": " + request.getHeader(headerName));
        }
        
        System.out.println("\nRequest params\n");
        request.getCookies();
        request.getSession();
        enumeration = request.getParameterNames();
        while (enumeration.hasMoreElements()) {
        	paramName = enumeration.nextElement();
        	System.out.println(paramName + ": " + request.getParameter(paramName));
        }
        
        System.out.println("\nResponse headers\n");
        Iterator<String> iterator = response.getHeaderNames().iterator();
        
        while(iterator.hasNext()) {
        	headerName = iterator.next();
        	System.out.println(headerName+": "+response.getHeader(headerName));
        }
        chain.doFilter(req, res);
    }

    public void init(FilterConfig filterConfig) {
    	System.out.println(filterConfig.getFilterName());
    }

    public void destroy() {}

}