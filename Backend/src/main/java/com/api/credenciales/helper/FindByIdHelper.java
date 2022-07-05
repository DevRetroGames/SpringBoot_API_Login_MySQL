package com.api.credenciales.helper;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.api.credenciales.exceptions.NotFoundException;
import com.api.credenciales.model.Identity;
import com.api.credenciales.model.Information;
import com.api.credenciales.model.Role;
import com.api.credenciales.repository.IIdentityRepository;
import com.api.credenciales.repository.IInformationRepository;
import com.api.credenciales.repository.IRoleRepository;

@Component
public class FindByIdHelper {
	
	
	@Autowired
	private IRoleRepository iRoleRepository ;
	
	@Autowired
	private IInformationRepository iInformationRepository ;
	
	@Autowired
	private IIdentityRepository iIdentityRepository ;
	
	
	
	@Async( "asyncExecutor" )
	public CompletableFuture< Role > getRoleById( UUID roleID ) {
		
		Role role = this.iRoleRepository
				.findById( roleID )
				.orElseThrow( () -> new NotFoundException( "Role" , "id" , roleID ) ) ;
		
		return CompletableFuture.completedFuture( role ) ;
		
	}
	
	
	@Async( "asyncExecutor" )
	public CompletableFuture< Information > getInformationById( UUID informationID ) {
		
		Information information = this.iInformationRepository
				.findById( informationID )
				.orElseThrow( () -> new NotFoundException( "Information" , "id" , informationID ) ) ;
		
		return CompletableFuture.completedFuture( information ) ;
		
	}
	
	

	@Async( "asyncExecutor" )
	public CompletableFuture< Identity > getIdentityById( UUID identityID ) {
		
		Identity identity = this.iIdentityRepository
				.findById( identityID )
				.orElseThrow( () -> new NotFoundException( "Identity" , "id" , identityID ) ) ;
		
		return CompletableFuture.completedFuture( identity ) ;
		
	}

}
