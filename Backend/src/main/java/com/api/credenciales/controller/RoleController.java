package com.api.credenciales.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
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
import com.api.credenciales.dto.PageDTO;
import com.api.credenciales.dto.RoleDTO;
import com.api.credenciales.service.IRoleService;

@RestController
@CrossOrigin( origins = "http://localhost:8081" )
@RequestMapping( "/api/role" )
public class RoleController {
	
  
	@Autowired
	@Qualifier("role")
	private IRoleService service ;
	
	
	
	@GetMapping( "/" )
	@ResponseStatus( HttpStatus.OK )
	public Page<RoleDTO> getAllRoles( @Valid @RequestBody PageDTO pageDTO ) {		
		return this.service.getAllRoles( pageDTO ) ;
	}
	
	
	
	@GetMapping( "/role" )
	@ResponseStatus( HttpStatus.OK )
	public RoleDTO getSingleRole( @RequestParam UUID roleID ) {
		return this.service.getRole( roleID ) ;
	}
	
	
	
	@PostMapping( "/" )
	@ResponseStatus( HttpStatus.CREATED )
	public RoleDTO createRole( @Valid @RequestBody RoleDTO rolesDTO ) {		
		return service.createRole( rolesDTO ) ;
	}
	
	
	
	@PutMapping( "/")
	@ResponseStatus( HttpStatus.OK )
	public RoleDTO updateRole( @RequestParam UUID roleID , @Valid @RequestBody RoleDTO rolesDTO ) {		
		return this.service.updateRole( roleID , rolesDTO ) ;		
	}
	
	
	
	@DeleteMapping( "/" )
	@ResponseStatus( HttpStatus.OK )
	public ApiResponse deleteRole( @RequestParam UUID roleID ) {	
		this.service.deleteRole( roleID ) ;
		return new ApiResponse( "Role Deleted Successfully." , true ) ;
	}
	
}