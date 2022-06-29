package com.api.credenciales.helper;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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
	public CompletableFuture< List<RoleDTO> > getAllRoles() {
		
		List<RoleDTO> listRoles = this.iRoleRepository
				.findAll()
				.stream()
				.map( mapperUtil::roleEntityToRoleDTO )
				.collect( Collectors.toList() ) ;
		
		return CompletableFuture.completedFuture( listRoles ) ;
		
	}
	
	
	@Async( "asyncExecutor" )
	public CompletableFuture< List<InformationDTO> > getAllInformations() {
		
		List<InformationDTO> listInformations = this.iInformationRepository
				.findAll()
				.stream()
				.map( mapperUtil::informationEntityToInformationDTO )
				.collect( Collectors.toList() ) ;
		
		return CompletableFuture.completedFuture( listInformations ) ;
		
	}
	
	
	@Async( "asyncExecutor" )
	public CompletableFuture< List<IdentityDTO> > getAllIdentitys() {
		
		List<IdentityDTO> listIdentitys = this.iIdentityRepository.findAll()
				.stream()
				.map( mapperUtil::identityEntityToIdentityDTO )
				.collect( Collectors.toList() ) ;
		
		return CompletableFuture.completedFuture( listIdentitys ) ;
		
	}
	
}