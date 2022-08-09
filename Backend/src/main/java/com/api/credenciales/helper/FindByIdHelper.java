package com.api.credenciales.helper;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.api.credenciales.model.Identity;
import com.api.credenciales.model.Information;
import com.api.credenciales.model.Role;
import com.api.credenciales.repository.IIdentityRepository;
import com.api.credenciales.repository.IInformationRepository;
import com.api.credenciales.repository.IRoleRepository;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class FindByIdHelper {
	
	
	@Autowired
	private IRoleRepository iRoleRepository ;
	
	@Autowired
	private IInformationRepository iInformationRepository ;
	
	@Autowired
	private IIdentityRepository iIdentityRepository ;
	
	
	
	@Async( "asyncExecutor" )
	public CompletableFuture< Optional< Role > > getRoleById( UUID roleID ) {
		
		try {
		  
		  Optional< Role > role = this.iRoleRepository
	        .findById( roleID )
	        ;
		  
		  return CompletableFuture.completedFuture( role ) ;
		  
		} catch( Exception e ) {
		  log.error( "error: " + e.getMessage() ) ;
		}
		
		return CompletableFuture.completedFuture( null ) ;
		
	}
	
	
	@Async( "asyncExecutor" )
	public CompletableFuture< Optional< Information > > getInformationById( UUID informationID ) {
		
		try {
		  
		  Optional< Information > information = this.iInformationRepository
	        .findById( informationID ) 
	        ;
		  
		  return CompletableFuture.completedFuture( information ) ;
		  
		} catch( Exception e ) {
		  log.error( "error: " + e.getMessage() ) ;
		}
		
		return CompletableFuture.completedFuture( null ) ;
		
	}
	
	

	@Async( "asyncExecutor" )
	public CompletableFuture< Optional< Identity > > getIdentityById( UUID identityID ) {
		
		try {
		  
		  Optional< Identity > identity = this.iIdentityRepository
	        .findById( identityID ) 
	        ;
		  
		  return CompletableFuture.completedFuture( identity ) ;
		  
		} catch( Exception e ) {
		  log.error( "error: " + e.getMessage() ) ;
		}
		
		return CompletableFuture.completedFuture( null ) ;
		
	}

}
