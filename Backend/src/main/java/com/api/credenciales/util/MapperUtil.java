package com.api.credenciales.util;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.api.credenciales.dto.IdentityDTO;
import com.api.credenciales.dto.InformationDTO;
import com.api.credenciales.dto.RoleDTO;
import com.api.credenciales.model.Identity;
import com.api.credenciales.model.Information;
import com.api.credenciales.model.Role;

@Component
public class MapperUtil {

	@Autowired
	private ModelMapper modelMapper ;
	
	
	
	// roles
	
	public RoleDTO roleEntityToRoleDTO( Role roles ) {
		return this.modelMapper.map( roles , RoleDTO.class ) ;
	}
	
	public Role roleDTOToRoleEntity( RoleDTO rolesDTO ) {
		return this.modelMapper.map( rolesDTO , Role.class ) ;
	}
	
	
	
	// Information
	
	public InformationDTO informationEntityToInformationDTO( Information information ) {
		return modelMapper.map( information , InformationDTO.class ) ;
	}
	
	public Information informationDTOToInformationEntity( InformationDTO informationDTO ) {
		return modelMapper.map( informationDTO , Information.class ) ;
	}
	
	
	
	// Identity
	
	public IdentityDTO identityEntityToIdentityDTO( Identity identity ) {
		return modelMapper.map( identity , IdentityDTO.class ) ;
	}
	
	public Identity identityDTOToIdentityEntity( IdentityDTO identityDTO ) {
		return modelMapper.map( identityDTO , Identity.class ) ;
	}
	
}