package com.api.credenciales.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.api.credenciales.dto.InformationDTO;

@Service
public interface IInformationService {

	/**
	 * 
	 * List with all the records of the information table.
	 * 
	 * @return List<InformationDTO>
	 */
	public List<InformationDTO> getAllInformations() ;
	
	
	/**
	 * 
	 * Get a record from the information table based on an id.
	 * 
	 * @param informationID
	 * @return InformationDTO
	 */
	public InformationDTO getInformation( UUID informationID ) ;
	
	
	/**
	 * 
	 * Create a new record in the information table.
	 * 
	 * @param informationDTO
	 * @return InformationDTO
	 */
	public InformationDTO createInformation( InformationDTO informationDTO ) ;
	
	
	/**
	 * 
	 * Updates a record in the information table.
	 * 
	 * @param informationID
	 * @param informationDTO
	 * @return InformationDTO
	 */
	public InformationDTO updateInformation( UUID informationID , InformationDTO informationDTO ) ;
	
	
	/**
	 * 
	 * Deletes a record from the information table based on an id.
	 * 
	 * @param informationID
	 */
	public void deleteInformation( UUID informationID ) ;
	
}
