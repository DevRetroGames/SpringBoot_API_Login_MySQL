package com.api.credenciales.dto;

import java.util.UUID;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class InformationDTO {

	private UUID id ;
	
	@NotBlank( message = "Names is required." )
	@Size( min = 3 , max = 255 , message = "Invalid name length." )
	private String name ;
	
	@NotBlank( message = "Surnames required." )
	@Size( min = 3 , max = 255 , message = "Invalid surname length." )
	private String lastName ;
	
	@NotNull( message = "Age required." )
	@Min( value = 18 , message = "An older person is required." )
	@Max( value = 60 , message = "A younger person is required." )
	private int age ;
	
	@NotBlank( message = "Phone number required." )
	private String cellPhonoNumber ;
	
	@NotBlank( message = "Email required." )
	@Email( 
		regexp = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$" , // RFC 5322 
		message = "Invalid email." 
	)
	private String email ;
	
	@NotBlank( message = "DNI or RUT required." )
	@Size( min = 11 , max = 12 , message = "Invalid route length." )
	private String dni ;
	
	@NotBlank( message = "Country required." )
	@Size( min = 5 , max = 255 , message = "Invalid country name length." )
	private String country ;
	
	@NotBlank( message = "City required." )
	@Size( min = 5 , max = 255 , message = "Invalid city name length." )
	private String city ;
	
}
