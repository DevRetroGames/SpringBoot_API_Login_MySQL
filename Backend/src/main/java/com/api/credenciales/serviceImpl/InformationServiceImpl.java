package com.api.credenciales.serviceImpl;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.credenciales.dto.InformationDTO;
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
	public List<InformationDTO> getAllInformations() {
		
		return this.findAll.getAllInformations().join() ;
		
	}

	
	
	@Override
	public InformationDTO getInformation( UUID informationID ) {		
		
		CompletableFuture< Information > information = this.findByIdHelper.getInformationById( informationID ) ;
		
		return mapperUtil.informationEntityToInformationDTO( information.join() ) ;
		
	}

	
	
	@Override
	public InformationDTO createInformation( InformationDTO informationDTO ) {
		
		return this.createHelper.createInformation( informationDTO ).join() ;
		
	}

	
	
	@Override
	public InformationDTO updateInformation( UUID informationID , InformationDTO informationDTO ) {
		
		CompletableFuture< Information > information = this.findByIdHelper.getInformationById( informationID ) ;
		
		return this.updateHelper.updateInformation( information.join() , informationDTO ).join() ;
		
	}

	
	
	@Override
	public void deleteInformation( UUID informationID ) {
		
		CompletableFuture< Information > information = this.findByIdHelper.getInformationById( informationID ) ;
		
		this.iInformationRepository.delete( information.join() ) ;
		
	}

}
