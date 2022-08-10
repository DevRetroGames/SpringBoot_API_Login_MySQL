package com.api.credenciales.security;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class JwtAuthRequest {
	
  @NotBlank( message = "Username not null." )
	private String username ;
	
  @NotBlank( message = "Password not null." )
	private String password ;

}
