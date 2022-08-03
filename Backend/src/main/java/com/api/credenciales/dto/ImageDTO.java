package com.api.credenciales.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ImageDTO {
  
  @NotBlank( message = "nombre vacio." )
  private String name ;
  
  @NotEmpty( message = "imagen vacia." )
  private MultipartFile image ;
  
  
}
