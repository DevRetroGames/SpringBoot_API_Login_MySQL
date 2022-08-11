package com.api.credenciales.helper;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.api.credenciales.dto.IdentityDTO;
import com.api.credenciales.dto.InformationDTO;
import com.api.credenciales.dto.RoleDTO;
import com.api.credenciales.repository.IIdentityRepository;
import com.api.credenciales.repository.IInformationRepository;
import com.api.credenciales.repository.IRoleRepository;
import com.api.credenciales.util.MapperUtil;

@ExtendWith(MockitoExtension.class)
class CreateHelperTest {
  
  
  @Mock
  private IIdentityRepository iIdentityRepository;

  @Mock
  private IInformationRepository iInformationRepository;

  @Mock
  private IRoleRepository iRoleRepository;

  @Mock
  private MapperUtil mapperUtil;

  @Mock
  private PasswordEncoder passwordEncoder;
  
  @InjectMocks
  private CreateHelper createHelper;
  
  // ----------------------------------------------------------------------
  
  private RoleDTO roleDTO ;
  
  private InformationDTO informationDTO ;
  
  private IdentityDTO identityDTO ;
  
  private Set< RoleDTO > listRoleDTO = new HashSet< RoleDTO >() ;
  
  // ----------------------------------------------------------------------

  @BeforeEach
  protected void setUp() throws Exception {
    
    this.createHelper = Mockito.mock( CreateHelper.class ) ;
    
    this.roleDTO = Mockito.mock( RoleDTO.class ) ;
    this.roleDTO.setName( "test01" ) ;
    this.roleDTO.setStatus( true ) ;
    
    this.informationDTO = Mockito.mock( InformationDTO.class ) ;
    this.informationDTO.setName( "nombre" ) ;
    this.informationDTO.setLastName( "apellido" ) ;
    this.informationDTO.setAge( 20 ) ;
    this.informationDTO.setCellPhonoNumber( "123456789" ) ;
    this.informationDTO.setEmail( "email@email.com" ) ;
    this.informationDTO.setDni( "987654321" ) ;
    this.informationDTO.setCountry( "pais" ) ;
    this.informationDTO.setCity( "ciudad" ) ;
    this.informationDTO.setImage( true ) ;
    
    this.identityDTO = Mockito.mock( IdentityDTO.class ) ;
    this.identityDTO.setInformation( this.informationDTO ) ;
    this.identityDTO.setKeyword( "password" ) ;
    this.identityDTO.setStatus( true ) ;
    
    this.listRoleDTO.add( this.roleDTO ) ;
    
    this.identityDTO.setListRoles( new ArrayList<>( this.listRoleDTO ) ) ;
    
  }
  
  @AfterEach
  protected void tearDown() throws Exception {
  }
  
  
  // ----------------------------------------------------------------------
  
  
  @Test
  void testCreateRole() {
    
    Mockito
      .when( this.createHelper.createRole( this.roleDTO ) )
      .thenReturn( CompletableFuture.completedFuture( new RoleDTO() ) ) 
      ;
    
    CompletableFuture< RoleDTO > completableFutureRoleDTOTest = 
        this.createHelper.createRole( this.roleDTO ) ;
    
    
    RoleDTO roleDTOTest = completableFutureRoleDTOTest.join() ;
    
    
    assertEquals( this.roleDTO.getName() , roleDTOTest.getName() ) ;
    
    assertEquals( this.roleDTO.isStatus() , roleDTOTest.isStatus() ) ;
    
  }
  
  
  // ----------------------------------------------------------------------
  
  
  @Test
  void testCreateInformation() {
    
    Mockito
      .when( this.createHelper.createInformation( this.informationDTO ) )
      .thenReturn( CompletableFuture.completedFuture( new InformationDTO() ) ) 
      ;
    
    CompletableFuture< InformationDTO > completableFutureInformationDTOTest = 
        this.createHelper.createInformation( this.informationDTO ) ;
    
    
    InformationDTO informationDTOTest = completableFutureInformationDTOTest.join() ;
    
    
    assertEquals( this.informationDTO.getName() , informationDTOTest.getName() ) ;
    
    assertEquals( this.informationDTO.getLastName() , informationDTOTest.getLastName() ) ;
    
    assertEquals( this.informationDTO.getAge() , informationDTOTest.getAge() ) ;
    
    assertEquals( this.informationDTO.getCellPhonoNumber() , informationDTOTest.getCellPhonoNumber() ) ;
    
    assertEquals( this.informationDTO.getEmail() , informationDTOTest.getEmail() ) ;
    
    assertEquals( this.informationDTO.getDni() , informationDTOTest.getDni() ) ;
    
    assertEquals( this.informationDTO.getCountry() , informationDTOTest.getCountry() ) ;
    
    assertEquals( this.informationDTO.getCity() , informationDTOTest.getCity() ) ;
    
    assertEquals( this.informationDTO.isImage() , informationDTOTest.isImage() ) ;
    
  }
  
  
  // ----------------------------------------------------------------------
  
  
  @Test
  void testCreateIdentity() {
    
    Mockito
      .when( this.createHelper.createIdentity( 
          this.identityDTO , this.informationDTO , this.listRoleDTO ) )
      .thenReturn( CompletableFuture.completedFuture( new IdentityDTO() ) ) 
      ;
    
    CompletableFuture< IdentityDTO > completableFutureIdentityDTOTest = 
        this.createHelper
            .createIdentity( this.identityDTO , this.informationDTO , this.listRoleDTO ) 
            ;
    
    
    IdentityDTO IdentityDTOTest = completableFutureIdentityDTOTest.join() ;
    
    
    assertEquals( this.identityDTO.getInformation() , IdentityDTOTest.getInformation() ) ;
    
    assertEquals( this.identityDTO.getKeyword() , IdentityDTOTest.getKeyword() ) ;
    
    assertEquals( this.identityDTO.getListRoles() , IdentityDTOTest.getListRoles() ) ;
    
    assertEquals( this.identityDTO.isStatus() , IdentityDTOTest.isStatus() ) ;
    
  }
  
  
}
