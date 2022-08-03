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
    
    File file = null ;
    InputStreamResource inputStreamResource = null ;
    
    try {
      
      file = new File( this.sftpDirLocal + imageName ) ;
      inputStreamResource 
        = new InputStreamResource( new FileInputStream( file ) ) ;
      
    } catch( FileNotFoundException e ) {
      log.info( "error el descargar la imagen." ) ;
    }
    
    return inputStreamResource ;
    
  }
  
  
  
  public void saveImage( MultipartFile image ) {
    
    try {
      
      Files
        .copy( 
            image.getInputStream() , 
            Paths.get( this.sftpDirLocal + image.getOriginalFilename() ) , 
            StandardCopyOption.REPLACE_EXISTING ) 
        ;
      
    } catch( IOException e ) {
      log.info( "error al guardar la imagen en la carpeta local." ) ;
    }
    
  }
  
  
  
  public boolean deleteImage( String imageName ) {
    
    boolean isDeleteImage = false ;
    
    Path path = Paths.get( this.sftpDirLocal + imageName ) ;
    
    try {
      isDeleteImage = Files.deleteIfExists( path ) ;
    } catch (IOException e) {
      log.info( "error al eliminar la imagen en la carpeta local." ) ;
    }
    
    return isDeleteImage ;
    
  }
  
  
  public boolean imageType( MultipartFile image ) {
    
    return image.getContentType().equals( this.imageType ) ;
    
  }
  
  
  
}