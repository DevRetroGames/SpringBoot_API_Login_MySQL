package com.api.credenciales.serviceImpl;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.credenciales.dto.IdentityDTO;
import com.api.credenciales.dto.InformationDTO;
import com.api.credenciales.dto.PageDTO;
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
	public Page<IdentityDTO> getAllIdentitys( PageDTO pageDTO ) {
	  
	  PageRequest pageRequest = 
        PageRequest.of( 
            pageDTO.getPage() , 
            pageDTO.getQuantityRecords() 
          ) ;
	  
		return this.findAll.getAllIdentitys( pageRequest ).join() ;
		
	}

	
	
	@Override
	public IdentityDTO getIdentity( UUID identityID ) {
		
		CompletableFuture< Identity > identification = 
				this.findByIdHelper.getIdentityById( identityID ) ;
		
		return mapperUtil.identityEntityToIdentityDTO( identification.join() ) ;
		
	}

	
	
	@Override
	public IdentityDTO createIdentity( 
			IdentityDTO identityDTO , 
			UUID informationID , 
			List< UUID > listRoleID ) {
		
		CompletableFuture< Information > information = 
				this.findByIdHelper.getInformationById( informationID ) ;	
		
		Set< CompletableFuture< Role > > listRole = 
				listRoleID
				.stream()
				.map( role -> this.findByIdHelper.getRoleById( role ) )
				.collect( Collectors.toSet() ) ;
		
		InformationDTO informationDTO = 
				this.mapperUtil.informationEntityToInformationDTO( information.join() ) ;
		
		Set< RoleDTO > listRoleDTO = 
				listRole.
				stream()
				.map( role -> this.mapperUtil.roleEntityToRoleDTO( role.join() ) )
				.collect( Collectors.toSet() ) ;
		
		return this.createHelper.createIdentity( 
				identityDTO , informationDTO , listRoleDTO ).join() ;
		
	}

	
	
	@Override
	public IdentityDTO updateIdentity( 
			UUID identityID , IdentityDTO identityDTO , List< UUID > listRoleID ) {
		
		CompletableFuture< Identity > identity = 
				this.findByIdHelper.getIdentityById( identityID ) ;
		
		List< CompletableFuture< Role > > listRole = 
				listRoleID
				.stream()
				.map( role -> this.findByIdHelper.getRoleById( role ) )
				.collect( Collectors.toList() ) ;
		
		List< RoleDTO > listRoleDTO = 
				listRole
				.stream()
				.map( role -> this.mapperUtil.roleEntityToRoleDTO( role.join() ) )
				.collect( Collectors.toList() ) ;
		
		return this.updateHelper.updateIdentity( 
				identity.join() , identityDTO , listRoleDTO ).join() ;
		
	}
	
	
	
	@Override
	public void deleteIdentity( UUID identityID ) {
		
		CompletableFuture< Identity > identity = 
				this.findByIdHelper.getIdentityById( identityID ) ;
		
		this.iIdentityRepository.delete( identity.join() ) ;
		
	}

}