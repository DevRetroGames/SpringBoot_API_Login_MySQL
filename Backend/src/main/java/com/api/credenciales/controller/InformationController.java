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
import com.api.credenciales.dto.InformationDTO;
import com.api.credenciales.service.IInformationService;

@RestController
@CrossOrigin( origins = "http://localhost:8081" )
@RequestMapping( "/api/information" )
public class InformationController {
	
	@Autowired
	@Qualifier("information")
	private IInformationService service ;

	
	
	@GetMapping( "/" )
	@ResponseStatus( HttpStatus.OK )
	public List<InformationDTO> getAllInformations() {		
		return this.service.getAllInformations() ;
	}
	
	
	
	@GetMapping( "/informacion" )
	@ResponseStatus( HttpStatus.OK )
	public InformationDTO getSingleInformation( 
			@RequestParam UUID informacionID ) {
		return this.service.getInformation( informacionID ) ;
	}
	
	
	
	@PostMapping( "/" )
	@ResponseStatus( HttpStatus.CREATED )
	public InformationDTO createInformation( 
			@Valid @RequestBody InformationDTO informacionDTO ) {		
		return service.createInformation( informacionDTO ) ;
	}
	
	
	
	@PutMapping( "/")
	@ResponseStatus( HttpStatus.OK )
	public InformationDTO updateInformation( 
			@RequestParam UUID informacionID , 
			@Valid @RequestBody InformationDTO informacionDTO ) {		
		return this.service.updateInformation( informacionID , informacionDTO ) ;		
	}
	
	
	
	@DeleteMapping( "/" )
	@ResponseStatus( HttpStatus.OK )
	public ApiResponse deleteInformation( @RequestParam UUID informacionID ) {		
		this.service.deleteInformation( informacionID ) ;
		return new ApiResponse( "Information Deleted Successfully." , true ) ;
	}
	
}