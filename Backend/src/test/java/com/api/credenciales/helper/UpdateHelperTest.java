package com.api.credenciales.helper;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
import com.api.credenciales.model.Identity;
import com.api.credenciales.model.Information;
import com.api.credenciales.model.Role;
import com.api.credenciales.repository.IIdentityRepository;
import com.api.credenciales.repository.IInformationRepository;
import com.api.credenciales.repository.IRoleRepository;
import com.api.credenciales.util.MapperUtil;

@ExtendWith(MockitoExtension.class)
class UpdateHelperTest {
  
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
  private UpdateHelper updateHelper;
  
  // ----------------------------------------------------------------------
  
  private Role role ; 
  private RoleDTO roleDTO ;
  
  private Information information ; 
  private InformationDTO informationDTO ;
  
  private Identity identity ; 
  private IdentityDTO identityDTO ; 
  
  private Set< Role > listRole = new HashSet< Role >() ;
  private List< RoleDTO > listRoleDTO = new ArrayList< RoleDTO >() ;
  
  // ----------------------------------------------------------------------
  
  @BeforeEach
  protected void setUp() throws Exception {
    
    this.updateHelper = Mockito.mock( UpdateHelper.class ) ;
    
    // -------------------------
    
    this.role = Mockito.mock( Role.class ) ;
    this.role.setName( "test01" ) ;
    this.role.setStatus( true ) ;
    
    this.roleDTO = Mockito.mock( RoleDTO.class ) ;
    this.roleDTO.setName( "test01" ) ;
    this.roleDTO.setStatus( true ) ;
    
    // -------------------------
    
    this.information = Mockito.mock( Information.class ) ;
    this.information.setName( "nombre" ) ;
    this.information.setLastName( "apellido" ) ;
    this.information.setAge( 20 ) ;
    this.information.setCellPhonoNumber( "123456789" ) ;
    this.information.setEmail( "email@email.com" ) ;
    this.information.setDni( "987654321" ) ;
    this.information.setCountry( "pais" ) ;
    this.information.setCity( "ciudad" ) ;
    this.information.setImage( true ) ;
    
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
    
    // -------------------------
    
    this.identity = Mockito.mock( Identity.class ) ;
    this.identity.setInformation( this.information ) ;
    this.identity.setKeyword( "password" ) ;
    this.identity.setStatus( true ) ;
    
    this.listRole.add( this.role ) ;
    
    this.identity.setListRoles( this.listRole ) ;
    
    this.identityDTO = Mockito.mock( IdentityDTO.class ) ;
    this.identityDTO.setInformation( this.informationDTO ) ;
    this.identityDTO.setKeyword( "password" ) ;
    this.identityDTO.setStatus( true ) ;
    
    this.listRoleDTO.add( this.roleDTO ) ;
    
    this.identityDTO.setListRoles( this.listRoleDTO ) ;
    
  }
  
  @AfterEach
  protected void tearDown() throws Exception {
  }
  
  // ----------------------------------------------------------------------
  
  @Test
  void testUpdateRole() {
    
    Mockito
      .when( this.updateHelper.updateRole( this.role , this.roleDTO ) )
      .thenReturn( CompletableFuture.completedFuture( new RoleDTO() ) ) 
      ;
    
    CompletableFuture< RoleDTO > completableFutureRoleDTOTest = 
        this.updateHelper.updateRole( this.role , this.roleDTO ) ;
    
    RoleDTO roleDTOTest = completableFutureRoleDTOTest.join() ;
    
    assertEquals( this.roleDTO.getName() , roleDTOTest.getName() ) ;
    
    assertEquals( this.roleDTO.isStatus() , roleDTOTest.isStatus() ) ;
    
  }
  
  // ----------------------------------------------------------------------
  
  @Test
  void testUpdateInformation() {
    
    Mockito
      .when( this.updateHelper.updateInformation( this.information , this.informationDTO ) )
      .thenReturn( CompletableFuture.completedFuture( new InformationDTO() ) ) 
      ;
    
    CompletableFuture< InformationDTO > completableFutureInformationDTOTest = 
        this.updateHelper.updateInformation( this.information , this.informationDTO ) ;
    
    
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
  void testUpdateIdentity() {
    
    Mockito
      .when( this.updateHelper.updateIdentity( identity , identityDTO , listRoleDTO ) )
      .thenReturn( CompletableFuture.completedFuture( new IdentityDTO() ) ) 
      ;
    
    CompletableFuture< IdentityDTO > completableFutureIdentityDTOTest = 
        this.updateHelper.updateIdentity( identity , identityDTO , listRoleDTO ) ;
    
    
    IdentityDTO IdentityDTOTest = completableFutureIdentityDTOTest.join() ;
    
    
    assertEquals( this.identityDTO.getInformation() , IdentityDTOTest.getInformation() ) ;
    
    assertEquals( this.identityDTO.getKeyword() , IdentityDTOTest.getKeyword() ) ;
    
    assertEquals( this.identityDTO.getListRoles() , IdentityDTOTest.getListRoles() ) ;
    
    assertEquals( this.identityDTO.isStatus() , IdentityDTOTest.isStatus() ) ;
    
  }

}
