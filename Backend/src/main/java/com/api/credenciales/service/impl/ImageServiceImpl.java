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
    
    if( !this.ftpUtil.downloadImage( imageName + this.imageFormat ) ) {
      throw new FileNotFoundException( "Imagen no encontrada en el servidor ftp." ) ;
    }
    
    
    InputStreamResource inputStreamResource = 
        this.imageUtil.downloadImage( imageName + this.imageFormat ) ;
    
    if( inputStreamResource == null ) {
      throw new FileNotFoundException( "Imagen no encontrada en la carpeta local." ) ;
    }
    
    
    return inputStreamResource ;
    
  }
  
  
  
  @Override
  public void uploadImage( MultipartFile image , String name ) {
    
    if( !this.imageUtil.imageType( image ) ) {
      log.info( "tipo no permitido." ) ;
      throw new CustomUnsupportedMediaType( "Tipo no permitido." ) ;
    }
    
    
    if( !this.imageUtil.saveImage( image ) ) {
      throw new CustomServerException( "Error en la conexión con la carpeta local." ) ;
    }
    
    
    if( !this.ftpUtil.uploadImage( image.getOriginalFilename() , name + this.imageFormat ) ) {
      throw new CustomServerException( "Error en la conexión con el servidor ftp." ) ;
    }
    
    
    if( !this.imageUtil.deleteImage( image.getOriginalFilename() ) ) {
      log.info( "error al eliminar la imagen." ) ;
      throw new CustomServerException( "Error al eliminar la imagen temporal." ) ;
    }
    
  }
  
  
  
  @Override
  public void deleteIdentity( String imageName ) {
    
    if( !this.ftpUtil.deleteImage( imageName + this.imageFormat ) ) {
      log.info( "Imagen no encontrada en el servidor ftp." ) ;
      throw new FileNotFoundException( "Imagen no encontrada en el servidor ftp." ) ;
    }
    
  }
  
  
}