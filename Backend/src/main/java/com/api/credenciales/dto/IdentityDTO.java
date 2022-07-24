package com.api.credenciales.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class IdentityDTO {
	
	private UUID id ;
	
	private InformationDTO information ;
	
	private List< RoleDTO > listRoles = new ArrayList<>() ;
	
	@NotBlank( message = "Keyword required." )
	@Size( min = 9 , max = 50 , message = "Invalid keyword length." )
	private String keyword ;
	
	@NotNull( message = "Status required." )
	private boolean status ;

}
