package com.api.credenciales.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.credenciales.dto.ApiResponse;
import com.api.credenciales.dto.IdentityDTO;
import com.api.credenciales.service.IIdentityService;

@RestController
@CrossOrigin( origins = "http://localhost:8081" )
@RequestMapping( "/api/identity" )
public class IdentityController {
	
	
	@Autowired
	@Qualifier("identity")
	private IIdentityService service ;

	
	
	@GetMapping( "/" )
	@ResponseStatus( HttpStatus.OK )
	public List<IdentityDTO> getAllIdentitys() {		
		return this.service.getAllIdentitys() ;
	}
	
	
	
	@GetMapping( "/identity" )
	@ResponseStatus( HttpStatus.OK )
	public IdentityDTO getSingleIdentity( @RequestParam UUID identityID ) {
		return this.service.getIdentity( identityID ) ;
	}
	
	
	
	@PostMapping( "/" )
	@ResponseStatus( HttpStatus.CREATED )
	public IdentityDTO createIdentity( 
			@Valid @RequestBody IdentityDTO identityDTO , 
			@RequestParam UUID informationID , 
			@RequestParam List< UUID > listRoleID ) {
		return service.createIdentity( identityDTO , informationID , listRoleID ) ;
	}
	
	
	
	@PutMapping( "/")
	@ResponseStatus( HttpStatus.OK )
	public IdentityDTO updateIdentity( 
			@RequestParam UUID identityID , 
			@Valid @RequestBody IdentityDTO identityDTO ,
			@RequestParam List< UUID > listRoleID ) {		
		return this.service.updateIdentity( identityID , identityDTO , listRoleID ) ;		
	}
	
	
	
	@DeleteMapping( "/" )
	@ResponseStatus( HttpStatus.OK )
	public ApiResponse deleteIdentification( @RequestParam UUID identityID ) {		
		this.service.deleteIdentity( identityID ) ;
		return new ApiResponse( "Information Deleted Successfully." , true ) ;
	}
	
}
