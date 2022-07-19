package com.api.credenciales.helper;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.api.credenciales.dto.IdentityDTO;
import com.api.credenciales.dto.InformationDTO;
import com.api.credenciales.dto.RoleDTO;
import com.api.credenciales.model.Identity;
import com.api.credenciales.model.Information;
import com.api.credenciales.model.Role;
import com.api.credenciales.repository.IIdentityRepository;
import com.api.credenciales.repository.IInformationRepository;
import com.api.credenciales.repository.IRoleRepository;
import com.api.credenciales.util.MapperUtil;

import lombok.extern.log4j.Log4j2;

@Component
@Transactional
@Log4j2
public class CreateHelper {

	
	@Autowired
	private IRoleRepository iRoleRepository ;
	
	@Autowired
	private IInformationRepository iInformationRepository ;
	
	@Autowired
	private IIdentityRepository iIdentityRepository ;
	
	@Autowired
	private MapperUtil mapperUtil ;
	
	
	
	@Async( "asyncExecutor" )
	public CompletableFuture< RoleDTO > createRole( RoleDTO roleDTO ) {	
		
		Role roleEntity = mapperUtil.roleDTOToRoleEntity( roleDTO ) ;
		Role roleSave = this.iRoleRepository.save( roleEntity ) ;
		
		RoleDTO roleSaveDTO = mapperUtil.roleEntityToRoleDTO( roleSave ) ;	
		
		return CompletableFuture.completedFuture( roleSaveDTO ) ;
		
	}
	
	
	
	@Async( "asyncExecutor" )
	public CompletableFuture< InformationDTO > createInformation( InformationDTO informationDTO ) {
		
		Information informationEntity = this.mapperUtil.informationDTOToInformationEntity( informationDTO ) ;
		Information informationSave = this.iInformationRepository.save( informationEntity ) ;
		
		InformationDTO informationSaveDTO = mapperUtil.informationEntityToInformationDTO( informationSave ) ;
		
		return CompletableFuture.completedFuture( informationSaveDTO ) ;
		
	}
	
	
	
	@Async( "asyncExecutor" )
	public CompletableFuture< IdentityDTO > createIdentity( 
			IdentityDTO identityDTO , 
			InformationDTO informationDTO , 
			Set< RoleDTO > listRoleDTO ) {
		
		Identity identityEntity = 
				this.mapperUtil.identityDTOToIdentityEntity( identityDTO ) ;
		
		Information informationEntity = 
				this.mapperUtil.informationDTOToInformationEntity( informationDTO ) ;
		
		Set< Role > listRoleEntity = new HashSet<>() ;
		listRoleEntity
			.addAll( 
				listRoleDTO
				.stream()
				.map( role -> this.mapperUtil.roleDTOToRoleEntity( role ) )
				.collect( Collectors.toSet() ) 
			) ;
		
		identityEntity.setInformation( informationEntity ) ;
		identityEntity.setListRoles( listRoleEntity ) ;
		
		Identity identitySave = this.iIdentityRepository.save( identityEntity ) ;
		IdentityDTO identitySaveDTO = this.mapperUtil.identityEntityToIdentityDTO( identitySave ) ;
		
		log.info( "entity: " + identitySave ) ;
		log.info( "entityDTO: " + identitySaveDTO ) ;
		
		return CompletableFuture.completedFuture( identitySaveDTO ) ;
		
	}
		
}