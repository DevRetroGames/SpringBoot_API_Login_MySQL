package com.api.credenciales.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.api.credenciales.dto.IdentityDTO;
import com.api.credenciales.dto.PageDTO;

@Service
public interface IIdentityService {
	
	
	/**
	 * 
	 * List with all the records of the information table.
	 * 
	 * @return List<IdentityDTO>
	 */
	public Page<IdentityDTO> getAllIdentitys( PageDTO pageDTO ) ;
	
	
	
	/**
	 * 
	 * Returns a record from the identifications table.
	 * 
	 * @param identityID
	 * @return IdentityDTO
	 */
	public IdentityDTO getIdentity( UUID identityID ) ;
	
	
	
	/**
	 * 
	 * Create a new record in the identifications table 
	 * with the relationship with the roles and information tables.
	 * 
	 * @param identityDTO
	 * @param informationID
	 * @param roleID
	 * @return IdentityDTO
	 */
	public IdentityDTO createIdentity( 
			IdentityDTO identityDTO , UUID informationID , List< UUID > listRoleID ) ;
	
	
	
	/**
	 * 
	 * Updates a record in the identifications table.
	 * 
	 * @param identityID
	 * @param identityDTO
	 * @param informationID
	 * @param roleID
	 * @return IdentityDTO
	 */
	public IdentityDTO updateIdentity( 
			UUID identityID , IdentityDTO identityDTO , List< UUID > listRoleID ) ;
	
	
	
	/**
	 * 
	 * Deletes a record from the ids table based on an id.
	 * 
	 * @param identityID
	 */
	public void deleteIdentity( UUID identityID ) ;

}
