package com.api.credenciales.helper;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

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

@Component
public class UpdateHelper {
	
	
	@Autowired
	private IRoleRepository iRoleRepository ;
	
	@Autowired
	private IInformationRepository iInformationRepository ;
	
	@Autowired
	private IIdentityRepository iIdentityRepository ;
	
	@Autowired
	private MapperUtil mapperUtil ;
	
	
	@Async( "asyncExecutor" )
	public CompletableFuture< RoleDTO > updateRole( Role role , RoleDTO roleDTO ) {
		
		Role roleEntity = role ;
		RoleDTO roleDTOCopy = roleDTO ;
		
		roleEntity.setName( roleDTOCopy.getName() ) ;
		roleEntity.setStatus( roleDTOCopy.isStatus() ) ;
		
		Role roleSave = this.iRoleRepository.save( roleEntity ) ;
		
		RoleDTO roleSaveDTO = this.mapperUtil.roleEntityToRoleDTO( roleSave ) ;
		
		return CompletableFuture.completedFuture( roleSaveDTO ) ;
		
	}
	
	
	@Async( "asyncExecutor" )
	public CompletableFuture< InformationDTO > updateInformation( 
			Information information , InformationDTO informationDTO ) {
		
		Information informationEntity = information ;
		InformationDTO informationDTOCopy = informationDTO ;
		
		informationEntity.setName( informationDTOCopy.getName() ) ;
		informationEntity.setLastName( informationDTOCopy.getLastName() ) ;
		informationEntity.setAge( informationDTOCopy.getAge() ) ;
		informationEntity.setCellPhonoNumber( informationDTOCopy.getCellPhonoNumber() ) ;
		informationEntity.setEmail( informationDTOCopy.getEmail() ) ;
		informationEntity.setDni( informationDTOCopy.getDni() ) ;
		informationEntity.setCountry( informationDTOCopy.getCountry() ) ;
		informationEntity.setCity( informationDTOCopy.getCity() ) ;
		
		Information informationSave = this.iInformationRepository.save( informationEntity ) ;
		
		InformationDTO informationSaveDTO = mapperUtil.informationEntityToInformationDTO( informationSave ) ; 
		
		return CompletableFuture.completedFuture( informationSaveDTO ) ;
		
	}
	
	
	@Async( "asyncExecutor" )
	public CompletableFuture< IdentityDTO > updateIdentity( 
			Identity identity , IdentityDTO identityDTO , List< RoleDTO > listRoleDTO ) {
		
		Identity identityEntity = identity ; 
		
		IdentityDTO identityDTOCopy = identityDTO ;
		
		Set< Role > listRoleEntity = 
				listRoleDTO
				.stream()
				.map( this.mapperUtil::roleDTOToRoleEntity )
				.collect( Collectors.toSet() ) ; 
		
		identityEntity.setListRoles( listRoleEntity ) ;
		identityEntity.setUsername( identityDTOCopy.getUsername() ) ;
		identityEntity.setKeyword( identityDTOCopy.getKeyword() ) ;
		identityEntity.setStatus( identityDTOCopy.isStatus() ) ;
		
		Identity identitySave = this.iIdentityRepository.save( identityEntity ) ;
		
		IdentityDTO identitySaveDTO = mapperUtil.identityEntityToIdentityDTO( identitySave ) ;
		
		return CompletableFuture.completedFuture( identitySaveDTO ) ;
		
	}
	
}