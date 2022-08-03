package com.api.credenciales.service;

import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface IImageService {

  public InputStreamResource getImage( String imageName ) ;
  
  public void uploadImage( MultipartFile image , String name ) ;
  
  public void deleteIdentity( String imageName ) ;
  
}
