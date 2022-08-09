package com.api.credenciales.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
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
import com.api.credenciales.exceptions.CustomNotFoundException;
import com.api.credenciales.helper.CreateHelper;
import com.api.credenciales.helper.FindAll;
import com.api.credenciales.helper.FindByIdHelper;
import com.api.credenciales.helper.UpdateHelper;
import com.api.credenciales.model.Identity;
import com.api.credenciales.model.Information;
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
	private RoleServiceImpl roleServiceImpl ;
	
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
		
		Optional< Identity > identity = 
		    this.findByIdHelper.getIdentityById( identityID ).join() ;
    
    if( identity.isEmpty() ) {
      throw new CustomNotFoundException( "Identity not found." ) ;
    }
		
		return mapperUtil.identityEntityToIdentityDTO( identity.get() ) ;
		
	}

	
	
	@Override
	public IdentityDTO createIdentity( 
			IdentityDTO identityDTO , 
			UUID informationID , 
			List< UUID > listRoleID ) {
		
		Optional< Information > information = 
        this.findByIdHelper.getInformationById( informationID ).join() ;
    
    if( information.isEmpty() ) {
      throw new CustomNotFoundException( "Identity not found." ) ;
    }
    
    InformationDTO informationDTO = 
        this.mapperUtil.informationEntityToInformationDTO( information.get() ) ;
		
		Set< RoleDTO > listRoleDTO = 
				listRoleID
				.stream()
				.map( role -> this.roleServiceImpl.getRole( role ) )
				.collect( Collectors.toSet() ) ;
		
		return this.createHelper.createIdentity( 
				identityDTO , informationDTO , listRoleDTO ).join() ;
		
	}

	
	
	@Override
	public IdentityDTO updateIdentity( 
			UUID identityID , 
			IdentityDTO identityDTO , 
			List< UUID > listRoleID ) {
		
	  Optional< Identity > identity = 
        this.findByIdHelper.getIdentityById( identityID ).join() ;
    
    if( identity.isEmpty() ) {
      throw new CustomNotFoundException( "Identity not found." ) ;
    }
		
		List< RoleDTO > listRoleDTO = 
				listRoleID
				.stream()
				.map( role -> this.roleServiceImpl.getRole( role ) )
				.collect( Collectors.toList() ) ;
		
		return this.updateHelper.updateIdentity( 
				identity.get() , identityDTO , listRoleDTO ).join() ;
		
	}
	
	
	
	@Override
	public void deleteIdentity( UUID identityID ) {
		
		Optional< Identity > identity = 
        this.findByIdHelper.getIdentityById( identityID ).join() ;
    
    if( identity.isEmpty() ) {
      throw new CustomNotFoundException( "Identity not found." ) ;
    }
		
		this.iIdentityRepository.delete( identity.get() ) ;
		
	}

}