package com.api.credenciales.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.api.credenciales.exceptions.CustomServerException;
import com.api.credenciales.exceptions.CustomUnsupportedMediaType;
import com.api.credenciales.exceptions.FileNotFoundException;
import com.api.credenciales.service.IImageService;
import com.api.credenciales.util.FtpUtil;
import com.api.credenciales.util.ImageUtil;

@Service
@Qualifier( "ImageServiceImpl" )
public class ImageServiceImpl implements IImageService {
  
  
  @Autowired
  private ImageUtil imageUtil ;
  
  @Autowired
  private FtpUtil ftpUtil ;
  
  
  @Value( "${image.format}" )
  private String imageFormat ;
  
  
  
  @Override
  public InputStreamResource getImage( String imageName ) {
    
    if( !this.ftpUtil.downloadImage( imageName + this.imageFormat ) ) {
      throw new FileNotFoundException( "Image not found on SFTP server." ) ;
    }
    
    
    InputStreamResource inputStreamResource = 
        this.imageUtil.downloadImage( imageName + this.imageFormat ) ;
    
    if( inputStreamResource == null ) {
      throw new FileNotFoundException( "Image not found in local folder." ) ;
    }
    
    
    return inputStreamResource ;
    
  }
  
  
  
  @Override
  public void uploadImage( MultipartFile image , String name ) {
    
    if( !this.imageUtil.imageType( image ) ) {
      throw new CustomUnsupportedMediaType( "Type not allowed." ) ;
    }
    
    
    if( !this.imageUtil.saveImage( image ) ) {
      throw new CustomServerException( "Error with local folder." ) ;
    }
    
    
    if( !this.ftpUtil.uploadImage( image.getOriginalFilename() , name + this.imageFormat ) ) {
      throw new CustomServerException( "Failed connect to SFTP server." ) ;
    }
    
    
    if( !this.imageUtil.deleteImage( image.getOriginalFilename() ) ) {
      throw new CustomServerException( "Failed to delete temporary image." ) ;
    }
    
  }
  
  
  
  @Override
  public void deleteIdentity( String imageName ) {
    
    if( !this.ftpUtil.deleteImage( imageName + this.imageFormat ) ) {
      throw new FileNotFoundException( "Image not found on SFTP server." ) ;
    }
    
  }
  
  
}