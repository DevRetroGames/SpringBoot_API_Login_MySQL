package com.api.credenciales.helper;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.api.credenciales.dto.IdentityDTO;
import com.api.credenciales.dto.InformationDTO;
import com.api.credenciales.dto.RoleDTO;
import com.api.credenciales.repository.IIdentityRepository;
import com.api.credenciales.repository.IInformationRepository;
import com.api.credenciales.repository.IRoleRepository;
import com.api.credenciales.util.MapperUtil;

@Component
public class FindAll {

	
	@Autowired
	private IRoleRepository iRoleRepository ;
	
	@Autowired
	private IInformationRepository iInformationRepository ;
	
	@Autowired
	private IIdentityRepository iIdentityRepository ;
	
	@Autowired
	private MapperUtil mapperUtil ;
	
	
	@Async( "asyncExecutor" )
	public CompletableFuture< Page< RoleDTO > > getAllRoles( PageRequest pageRequest ) {
		
	  List< RoleDTO > listRoles = 
		    this.iRoleRepository
		      .findAll( pageRequest )
		      .stream()
	        .map( this.mapperUtil::roleEntityToRoleDTO )
	        .collect( Collectors.toList() )
		      ;
		
		return CompletableFuture.completedFuture( new PageImpl<>( listRoles ) ) ;
		
	}
	
	
	@Async( "asyncExecutor" )
	public CompletableFuture< Page< InformationDTO > > getAllInformations( PageRequest pageRequest ) {
		
		List< InformationDTO > listInformations = 
				this.iInformationRepository
				.findAll()
				.stream()
				.map( this.mapperUtil::informationEntityToInformationDTO )
				.collect( Collectors.toList() ) ;
		
		return CompletableFuture.completedFuture( new PageImpl<>( listInformations ) ) ;
		
	}
	
	
	@Async( "asyncExecutor" )
	public CompletableFuture< Page< IdentityDTO > > getAllIdentitys( PageRequest pageRequest ) {
		
		List< IdentityDTO > listIndetitysDTO = 
				this.iIdentityRepository
					.findAll()
					.stream()
					.map( this.mapperUtil::identityEntityToIdentityDTO )
					.collect( Collectors.toList() ) ;
		
		return CompletableFuture.completedFuture( new PageImpl<>( listIndetitysDTO ) ) ;
		
	}
	
}