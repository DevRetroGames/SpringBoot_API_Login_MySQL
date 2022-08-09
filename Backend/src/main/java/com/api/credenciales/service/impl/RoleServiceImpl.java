package com.api.credenciales.service.impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.credenciales.dto.PageDTO;
import com.api.credenciales.dto.RoleDTO;
import com.api.credenciales.exceptions.CustomNotFoundException;
import com.api.credenciales.helper.CreateHelper;
import com.api.credenciales.helper.FindAll;
import com.api.credenciales.helper.FindByIdHelper;
import com.api.credenciales.helper.UpdateHelper;
import com.api.credenciales.model.Role;
import com.api.credenciales.repository.IRoleRepository;
import com.api.credenciales.service.IRoleService;
import com.api.credenciales.util.MapperUtil;

@Service
@Transactional
@Qualifier("role")
public class RoleServiceImpl implements IRoleService {
	
	
	@Autowired
	private IRoleRepository iRoleRepository ;
	
	@Autowired
	private CreateHelper createHelper ;
	
	@Autowired
	private FindAll findAll ;
	
	@Autowired
	private FindByIdHelper findByIdHelper ;
	
	@Autowired
	private UpdateHelper updateHelper ;
	
	@Autowired
	private MapperUtil mapperUtil ;

	
	
	@Override	
	public Page<RoleDTO> getAllRoles( PageDTO pageDTO ) {
	  
	  PageRequest pageRequest = 
	      PageRequest.of( 
	          pageDTO.getPage() , 
	          pageDTO.getQuantityRecords() 
	        ) ;
	  
	  return this.findAll.getAllRoles( pageRequest ).join() ;
		
	}
	
	
	
	@Override
	public RoleDTO getRole( UUID roleID ) {	
		
	  Optional< Role > role = this.findByIdHelper.getRoleById( roleID ).join() ;
	  
	  if( role.isEmpty() ) {
	    throw new CustomNotFoundException( "Role not found." ) ;
	  }
		
		return mapperUtil.roleEntityToRoleDTO( role.get() ) ;
		
	}
	
	
	
	@Override
	public RoleDTO createRole( RoleDTO roleDTO ) {	
		
		return this.createHelper.createRole( roleDTO ).join() ;
		
	}
	
	
	
	@Override
	public RoleDTO updateRole( UUID roleID , RoleDTO roleDTO ) {
		
	  Optional< Role > role = this.findByIdHelper.getRoleById( roleID ).join() ;
    
    if( role.isEmpty() ) {
      throw new CustomNotFoundException( "Role not found." ) ;
    }
		
		return this.updateHelper.updateRole( role.get() , roleDTO ).join() ;
		
	} 
	
	
	
	@Override
	public void deleteRole( UUID roleID ) {
		
	  Optional< Role > role = this.findByIdHelper.getRoleById( roleID ).join() ;
    
    if( role.isEmpty() ) {
      throw new CustomNotFoundException( "Role not found." ) ;
    }
		
		this.iRoleRepository.delete( role.get() ) ;
		
	} 

}