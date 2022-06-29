package com.api.credenciales.serviceImpl;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.credenciales.dto.IdentityDTO;
import com.api.credenciales.dto.InformationDTO;
import com.api.credenciales.dto.RoleDTO;
import com.api.credenciales.helper.CreateHelper;
import com.api.credenciales.helper.FindAll;
import com.api.credenciales.helper.FindByIdHelper;
import com.api.credenciales.helper.UpdateHelper;
import com.api.credenciales.model.Identity;
import com.api.credenciales.model.Information;
import com.api.credenciales.model.Role;
import com.api.credenciales.repository.IIdentityRepository;
import com.api.credenciales.service.IIdentityService;
import com.api.credenciales.util.MapperUtil;

@Service
@Transactional
@Qualifier("identity")
public class IdentityServiceImpl implements IIdentityService {
	
	
	@Autowired
	private IIdentityRepository iIdentityRepository ;
	
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
	public List<IdentityDTO> getAllIdentitys() {
		
		return this.findAll.getAllIdentitys().join() ;
		
	}

	
	
	@Override
	public IdentityDTO getIdentity( UUID identityID ) {
		
		CompletableFuture< Identity > identification = 
				this.findByIdHelper.getIdentityById( identityID ) ;
		
		return mapperUtil.identityEntityToIdentityDTO( identification.join() ) ;
		
	}

	
	
	@Override
	public IdentityDTO createIdentity( IdentityDTO identityDTO , UUID informationID , UUID roleID ) {
		
		CompletableFuture< Information > information = this.findByIdHelper.getInformationById( informationID ) ;		
		CompletableFuture< Role > role = this.findByIdHelper.getRoleById( roleID ) ;
		
		InformationDTO informationDTO = this.mapperUtil.informationEntityToInformationDTO( information.join() ) ;
		RoleDTO roleDTO = this.mapperUtil.roleEntityToRoleDTO( role.join() ) ;
		
		return this.createHelper.createIdentity( identityDTO , informationDTO , roleDTO ).join() ;
		
	}

	
	
	@Override
	public IdentityDTO updateIdentity( 
			UUID identityID , IdentityDTO identityDTO , UUID informationID , UUID roleID ) {
		
		CompletableFuture< Identity > identity = 
				this.findByIdHelper.getIdentityById( identityID ) ;
		CompletableFuture< Information > information = 
				this.findByIdHelper.getInformationById( informationID ) ;
		CompletableFuture< Role > role = this.findByIdHelper.getRoleById( roleID ) ;
		
		return this.updateHelper.updateIdentity( 
				identity.join() , identityDTO , information.join() , role.join() ).join() ;
		
	}
	
	
	
	@Override
	public void deleteIdentity( UUID identityID ) {
		
		CompletableFuture< Identity > identity = this.findByIdHelper.getIdentityById( identityID ) ;
		
		this.iIdentityRepository.delete( identity.join() ) ;
		
	}

}