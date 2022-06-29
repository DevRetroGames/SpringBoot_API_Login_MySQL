package com.api.credenciales.serviceImpl;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.credenciales.dto.RoleDTO;
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
	public List<RoleDTO> getAllRoles() {
		
		return this.findAll.getAllRoles().join() ;
		
	}
	
	
	
	@Override
	public RoleDTO getRole( UUID roleID ) {	
		
		CompletableFuture< Role > role = this.findByIdHelper.getRoleById( roleID ) ;
		
		return mapperUtil.roleEntityToRoleDTO( role.join() ) ;
		
	}
	
	
	
	@Override
	public RoleDTO createRole( RoleDTO roleDTO ) {	
		
		return this.createHelper.createRole( roleDTO ).join() ;
		
	}
	
	
	
	@Override
	public RoleDTO updateRole( UUID roleID , RoleDTO roleDTO ) {
		
		CompletableFuture< Role > role = this.findByIdHelper.getRoleById( roleID ) ;
		
		return this.updateHelper.updateRole( role.join() , roleDTO ).join() ;
		
	} 
	
	
	
	@Override
	public void deleteRole( UUID roleID ) {
		
		CompletableFuture< Role > role = this.findByIdHelper.getRoleById( roleID ) ;
		
		this.iRoleRepository.delete( role.join() ) ;
		
	} 

}