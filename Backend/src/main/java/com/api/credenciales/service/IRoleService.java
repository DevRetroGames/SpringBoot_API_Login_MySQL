package com.api.credenciales.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.api.credenciales.dto.RoleDTO;

@Service
public interface IRoleService {
	
	/**
	 * 
	 * List with all the records of the roles table.
	 * 
	 * @return List<RoleDTO>
	 */
	public List<RoleDTO> getAllRoles() ;
	
	
	/**
	 * 
	 * Get a record from the roles table based on an id.
	 * 
	 * @param roleID
	 * @return RoleDTO
	 */
	public RoleDTO getRole( UUID roleID ) ;
	
	
	/**
	 * 
	 * Create a new record in the roles table.
	 * 
	 * @param roleDTO
	 * @return RoleDTO 
	 */
	public RoleDTO createRole( RoleDTO roleDTO ) ;
	
	
	/**
	 * 
	 * Updates a record in the roles table.
	 * 
	 * @param roleID
	 * @param roleDTO
	 * @return RoleDTO
	 */
	public RoleDTO updateRole( UUID roleID , RoleDTO roleDTO ) ;
	
	
	/**
	 * 
	 * Deletes a record from the roles table based on an id.
	 * 
	 * @param roleID
	 */
	public void deleteRole( UUID roleID ) ;
	
}