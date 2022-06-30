package com.api.credenciales.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.credenciales.dto.RoleDTO;

@RestController
@CrossOrigin( origins = "http://localhost:8081" )
@RequestMapping( "/api/login" )
public class LoginController {

	@PostMapping( "/" )
	@ResponseStatus( HttpStatus.CREATED )
	public RoleDTO createRole( @Valid @RequestBody RoleDTO rolesDTO ) {		
		return null ;
	}
	
}
