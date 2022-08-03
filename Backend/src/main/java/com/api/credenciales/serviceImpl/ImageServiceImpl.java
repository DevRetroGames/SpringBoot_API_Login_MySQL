package com.api.credenciales.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.api.credenciales.service.IImageService;
import com.api.credenciales.util.FtpUtil;
import com.api.credenciales.util.ImageUtil;

import lombok.extern.log4j.Log4j2;

@Service
@Qualifier( "ImageServiceImpl" )
@Log4j2
public class ImageServiceImpl implements IImageService {
  
  
  @Autowired
  private ImageUtil imageUtil ;
  
  @Autowired
  private FtpUtil ftpUtil ;
  
  
  
  @Value( "${image.format}" )
  private String imageFormat ;
  
  
  
  @Override
  public InputStreamResource getImage( String imageName ) {
    
    this.ftpUtil.downloadImage( imageName + this.imageFormat ) ;
    
    InputStreamResource inputStreamResource = 
        this.imageUtil.downloadImage( imageName + this.imageFormat ) ;
    
    if( !this.imageUtil.deleteImage( imageName + this.imageFormat ) ) {
      log.info( "error al eliminar la imagen." ) ;
    }
    
    return inputStreamResource ;
    
  }
  
  
  
  @Override
  public void uploadImage( MultipartFile image , String name ) {
    
    if( !this.imageUtil.imageType( image ) ) {
      log.info( "tipo no permitido." ) ;
    }
    
    this.imageUtil.saveImage( image ) ;
    this.ftpUtil.uploadImage( image.getOriginalFilename() , name + this.imageFormat ) ;
    
    if( !this.imageUtil.deleteImage( image.getOriginalFilename() ) ) {
      log.info( "error al eliminar la imagen." ) ;
    }
    
  }
  
  
  
  @Override
  public void deleteIdentity( String imageName ) {
    this.ftpUtil.deleteImage( imageName + this.imageFormat ) ;
  }
  
  
}