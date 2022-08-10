package com.api.credenciales.util;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.api.credenciales.dto.IdentityDTO;
import com.api.credenciales.dto.InformationDTO;
import com.api.credenciales.dto.RoleDTO;
import com.api.credenciales.model.Identity;
import com.api.credenciales.model.Information;
import com.api.credenciales.model.Role;

@ExtendWith(MockitoExtension.class)
class MapperUtilTest {
  
  
  @Mock
  private ModelMapper modelMapper;
  
  @InjectMocks
  private MapperUtil mapperUtil;
  
  // ----------------------------------------------------------------------
  
  private Role role ;
  private RoleDTO roleDTO ;
  
  private Information information ;
  private InformationDTO informationDTO ;
  
  private Identity identity ;
  private IdentityDTO identityDTO ;
  
  // ----------------------------------------------------------------------

  @BeforeEach
  protected void setUp() throws Exception {
    
    this.mapperUtil = Mockito.mock( MapperUtil.class ) ;
    
    this.role = Mockito.mock( Role.class ) ;
    this.role.setName( "test01" ) ;
    this.role.setStatus( true ) ;
    
    this.roleDTO = Mockito.mock( RoleDTO.class ) ;
    this.roleDTO.setName( "test01" ) ;
    this.roleDTO.setStatus( true ) ;
    
    this.information = Mockito.mock( Information.class ) ;
    this.information.setName( "" ) ;
    this.information.setLastName( "" ) ;
    this.information.setAge( 0 ) ;
    this.information.setCellPhonoNumber( "" ) ;
    this.information.setEmail( "" ) ;
    this.information.setDni( "" ) ;
    this.information.setCountry( "" ) ;
    this.information.setCity( "" ) ;
    this.information.setImage( true ) ;
    
    this.informationDTO = Mockito.mock( InformationDTO.class ) ;
    this.informationDTO.setName( "" ) ;
    this.informationDTO.setLastName( "" ) ;
    this.informationDTO.setAge( 0 ) ;
    this.informationDTO.setCellPhonoNumber( "" ) ;
    this.informationDTO.setEmail( "" ) ;
    this.informationDTO.setDni( "" ) ;
    this.informationDTO.setCountry( "" ) ;
    this.informationDTO.setCity( "" ) ;
    this.informationDTO.setImage( true ) ;
    
    this.identity = Mockito.mock( Identity.class ) ;
    this.identity.setInformation( this.information ) ;
    this.identity.setKeyword( "" ) ;
    this.identity.setStatus( true ) ;
    
    this.identityDTO = Mockito.mock( IdentityDTO.class ) ;
    this.identityDTO.setInformation( this.informationDTO ) ;
    this.identityDTO.setKeyword( "" ) ;
    this.identityDTO.setStatus( true ) ;
    
  }

  @AfterEach
  protected void tearDown() throws Exception {
  }
  
  // ----------------------------------------------------------------------
  
  @Test
  void testRoleEntityToRoleDTO() {
    
    Mockito
      .when( this.mapperUtil.roleEntityToRoleDTO( this.role ) )
      .thenReturn( roleDTO ) 
      ;
    
    RoleDTO rolDTOTest = this.mapperUtil.roleEntityToRoleDTO( this.role ) ;
    
    // name equals
    assertEquals( this.roleDTO.getName() , rolDTOTest.getName() ) ;
    
    // status equals
    assertEquals( this.roleDTO.isStatus() , rolDTOTest.isStatus() ) ;
    
    // dto equals
    assertEquals( this.roleDTO , rolDTOTest ) ;
    
  }
  
  
  // ----------------------------------------------------------------------
  
  
  @Test
  void testRoleDTOToRoleEntity() {
    
    Mockito
      .when( this.mapperUtil.roleDTOToRoleEntity( this.roleDTO ) )
      .thenReturn( role ) 
      ;
    
    Role rolTest = this.mapperUtil.roleDTOToRoleEntity( roleDTO ) ;
    
    // name equals
    assertEquals( this.role.getName() , rolTest.getName() ) ;
    
    // status equals
    assertEquals( this.role.isStatus() , rolTest.isStatus() ) ;
    
    // dto equals
    assertEquals( this.role , rolTest ) ;
    
  }
  
  
  // ----------------------------------------------------------------------
  
  
  @Test
  void testInformationEntityToInformationDTO() {
    
    
    
  }
  
  
  // ----------------------------------------------------------------------
  
  
  @Test
  void testInformationDTOToInformationEntity() {
    
    
    
  }
  
  
  // ----------------------------------------------------------------------
  
  
  @Test
  void testIdentityEntityToIdentityDTO() {
    
    
    
  }
  
  
  // ----------------------------------------------------------------------
  
  
  @Test
  void testIdentityDTOToIdentityEntity() {
    
    
    
  }
  
  
}
