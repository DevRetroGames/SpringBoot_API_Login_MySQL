package com.api.credenciales.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Log4j2
public class FtpUtil {
  
  
  private static final String KEYCHECKING = "StrictHostKeyChecking" ;
  
  
  @Value( "${sftp.host}" )
  private String sftpHost ;
  
  @Value( "${sftp.port}" )
  private Integer sftpPort ;
  
  @Value( "${sftp.user}" )
  private String sftpUser ;
  
  @Value( "${sftp.password}" )
  private String sftpPassword ;
  
  @Value( "${sftp.upload}" )
  private String sftpUpload ;
  
  @Value( "${sftp.dir_local}" )
  private String sftpDirLocal ;
  
  @Value( "${sftp.timeout.session}" )
  private Integer sftpTimeoutSession ;
  
  @Value( "${sftp.timeout.channel}" )
  private Integer sftpTimeoutChannel ;
  
  
  
  public void downloadImage( String imageName ) {
    
    ChannelSftp channelSftp = this.createChannelSftp() ;
    
    try {
      
      File file = new File( this.sftpDirLocal + imageName ) ;
      OutputStream outputStream = new FileOutputStream( file ) ;
      
      channelSftp.get( this.sftpUpload + imageName , outputStream ) ;
      
      file.createNewFile() ;
      
    } catch( SftpException | IOException e ) {
      log.error( "Error download image" ) ;
    } finally {
      this.disconnectChannelSftp( channelSftp ) ;
    }
    
  }
  
  
  
  public void uploadImage( String imageName , String name ) {
    
    ChannelSftp channelSftp = this.createChannelSftp() ;
    
    try {
      
      channelSftp.put( this.sftpDirLocal + imageName , this.sftpUpload ) ;
      channelSftp.rename( 
        this.sftpUpload + imageName , 
        this.sftpUpload + name ) ;
      
    } catch( SftpException e ) {
      log.error( "Error upload image" ) ;
    } finally {
      this.disconnectChannelSftp( channelSftp ) ;
    }
    
  }
  
  
  
  public void deleteImage( String imageName ) {
    
    ChannelSftp channelSftp = this.createChannelSftp() ;
    
    try {
      
      channelSftp.rm( this.sftpUpload + imageName ) ;
      
    } catch( SftpException e ) {
      log.error( "Error download image" ) ;
    } finally {
      this.disconnectChannelSftp( channelSftp ) ;
    }
    
  }
  
  
  
  private ChannelSftp createChannelSftp() {
    
    try {
      
      JSch jSch = new JSch() ;
      
      Session session = 
          jSch.getSession( 
              this.sftpUser , 
              this.sftpHost , 
              this.sftpPort ) ;
      
      session.setConfig( KEYCHECKING , "no" ) ;
      session.setPassword( this.sftpPassword ) ;
      session.connect( this.sftpTimeoutSession ) ;
      
      Channel channel = session.openChannel( "sftp" ) ;
      channel.connect( this.sftpTimeoutChannel ) ;
      
      return (ChannelSftp) channel ;
      
      
    } catch( JSchException e ) {
      log.error( "Create ChannelSftp error" ) ;
    }
    
    return null ;
    
  }
  
  
  
  private void disconnectChannelSftp( ChannelSftp channelSftp ) {
    
    try {
      
      if( channelSftp == null ) { 
        return ; 
      }
      
      if( channelSftp.isConnected() ) { 
        channelSftp.disconnect() ; 
      }
      
      if( channelSftp.getSession() != null ) { 
        channelSftp.getSession().disconnect() ;
      }
      
    } catch( Exception e ) {
      log.error( "SFTP disconnect error" ) ;
    }
    
  }
  
}