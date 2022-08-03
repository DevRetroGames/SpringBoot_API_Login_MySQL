package com.api.credenciales.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.api.credenciales.dto.ApiResponse;
import com.api.credenciales.service.IImageService;

@RestController
@RequestMapping( "/api/image" )
public class ImageController {
  
  
  @Autowired
  @Qualifier( "ImageServiceImpl" )
  private IImageService imageService ;
  
  
  
  @GetMapping( value = "/image" , produces = MediaType.IMAGE_JPEG_VALUE )
  @ResponseStatus( HttpStatus.OK )
  public InputStreamResource getSingleImage( @RequestParam String imageName ) {
    return this.imageService.getImage( imageName ) ;
  }
  
  
  
  @PostMapping( "/" )
  @ResponseStatus( HttpStatus.CREATED )
  public ApiResponse createImage( @RequestParam MultipartFile image , @RequestParam String name ) {    
    this.imageService.uploadImage( image , name ) ;
    return new ApiResponse( "Image upload Successfully." ) ;
    
  }
  
  
  
  @DeleteMapping( "/" )
  @ResponseStatus( HttpStatus.OK )
  public ApiResponse deleteImage( @RequestParam String imageName ) {
    this.imageService.deleteIdentity( imageName ) ;
    return new ApiResponse( "Image Deleted Successfully." ) ;
  }
  
  
}
