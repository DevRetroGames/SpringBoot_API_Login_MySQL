package com.api.credenciales.dto;

import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class RoleDTO {
	
	private UUID id ;
	
	@NotBlank( message = "Name is required." )
	@Size( min = 5 , max = 255 , message = "The length of the name is invalid." )
	private String name ;
	
	@NotNull( message = "Status is required." )
	private boolean status ;
	
}
