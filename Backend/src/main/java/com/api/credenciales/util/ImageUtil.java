package com.api.credenciales.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class ImageUtil {
  
  
  @Value( "${sftp.dir_local}" )
  private String sftpDirLocal ;
  
  @Value( "${image.type}" )
  private String imageType ; 
  
  
  
  public InputStreamResource downloadImage( String imageName ) {
    
    File file = new File( this.sftpDirLocal + imageName ) ;
    
    
    
    try {
      
      return new InputStreamResource( new FileInputStream( file ) ) ;
      
    } catch( FileNotFoundException e ) {
      
      log.info( "Imagen no entrada en directorio local." ) ;
      
    }
    
    
    return null ;
    
    
  }
  
  
  
  public boolean saveImage( MultipartFile image ) {
    
    try {
      
      Files
        .copy( 
            image.getInputStream() , 
            Paths.get( this.sftpDirLocal + image.getOriginalFilename() ) , 
            StandardCopyOption.REPLACE_EXISTING ) 
        ;
      
      return true ;
      
    } catch( IOException e ) {
      
      log.info( "error al guardar la imagen en la carpeta local." ) ;
      
    }
    
    
    return false ;
    
    
  }
  
  
  
  public boolean deleteImage( String imageName ) {
    
    Path path = Paths.get( this.sftpDirLocal + imageName ) ;
    
    
    
    try {
      
      return Files.deleteIfExists( path ) ;
      
    } catch( IOException e ) {
      
      log.info( "error al eliminar la imagen en la carpeta local." ) ;
      
    }
    
    
    return false ;
    
    
  }
  
  
  
  public boolean imageType( MultipartFile image ) {
    
    String type = image.getContentType() ;
    
    return type != null ? type.equals( this.imageType ) : null ;
    
  }
  
  
}