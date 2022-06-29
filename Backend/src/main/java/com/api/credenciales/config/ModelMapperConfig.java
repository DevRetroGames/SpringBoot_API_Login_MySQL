package com.api.credenciales.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice( annotations = RestController.class )
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper() ;
	}
	
}
