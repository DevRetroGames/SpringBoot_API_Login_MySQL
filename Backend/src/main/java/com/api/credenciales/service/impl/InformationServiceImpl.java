package com.api.credenciales.service.impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.credenciales.dto.InformationDTO;
import com.api.credenciales.dto.PageDTO;
import com.api.credenciales.exceptions.CustomNotFoundException;
import com.api.credenciales.helper.CreateHelper;
import com.api.credenciales.helper.FindAll;
import com.api.credenciales.helper.FindByIdHelper;
import com.api.credenciales.helper.UpdateHelper;
import com.api.credenciales.model.Information;
import com.api.credenciales.repository.IInformationRepository;
import com.api.credenciales.service.IInformationService;
import com.api.credenciales.util.MapperUtil;

@Service
@Transactional
@Qualifier("information")
public class InformationServiceImpl implements IInformationService {
	
	
	@Autowired
	private IInformationRepository iInformationRepository ;
	
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
	public Page<InformationDTO> getAllInformations( PageDTO pageDTO ) {
	  
	  PageRequest pageRequest = 
        PageRequest.of( 
            pageDTO.getPage() , 
            pageDTO.getQuantityRecords() 
          ) ;
		
		return this.findAll.getAllInformations( pageRequest ).join() ;
		
	}

	
	
	@Override
	public InformationDTO getInformation( UUID informationID ) {		
		
	  Optional< Information > information = 
		    this.findByIdHelper.getInformationById( informationID ).join() ;
    
    if( information.isEmpty() ) {
      throw new CustomNotFoundException( "Identity not found." ) ;
    }
		
		return mapperUtil.informationEntityToInformationDTO( information.get() ) ;
		
	}

	
	
	@Override
	public InformationDTO createInformation( InformationDTO informationDTO ) {
		
		return this.createHelper.createInformation( informationDTO ).join() ;
		
	}

	
	
	@Override
	public InformationDTO updateInformation( UUID informationID , InformationDTO informationDTO ) {
		
	  Optional< Information > information = 
        this.findByIdHelper.getInformationById( informationID ).join() ;
    
    if( information.isEmpty() ) {
      throw new CustomNotFoundException( "Identity not found." ) ;
    }
		
		return this.updateHelper
		    .updateInformation( information.get() , informationDTO )
		    .join() 
		    ;
		
	}

	
	
	@Override
	public void deleteInformation( UUID informationID ) {
		
	  Optional< Information > information = 
        this.findByIdHelper.getInformationById( informationID ).join() ;
    
    if( information.isEmpty() ) {
      throw new CustomNotFoundException( "Identity not found." ) ;
    }
		
		this.iInformationRepository.delete( information.get() ) ;
		
	}
	

}