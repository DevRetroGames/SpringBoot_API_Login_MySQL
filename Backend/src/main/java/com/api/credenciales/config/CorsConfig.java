package com.api.credenciales.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
	
	private static final String GET = "get" ;
	private static final String POST = "post" ;
	private static final String DELETE = "delete" ;
	private static final String PUT = "put" ;

	public WebMvcConfigurer corsConfig() {
		
		return new WebMvcConfigurer() {
			
			@Override
			public void addCorsMappings( CorsRegistry registry ) {				
				registry.addMapping( "/**" )
						.allowedMethods( GET , POST , DELETE , PUT )
						.allowedHeaders( "*" )
						.allowedOriginPatterns( "*" ) 
						.allowCredentials( true ) ;				
			}
			
		} ;
	}
	
}
