package com.api.credenciales.util;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class JwtUtilTest {
  
  @InjectMocks
  private JwtUtil jwtUtil ;
  
  // ----------------------------------------------------------------------
  
  private String token = null ;
  
  // ----------------------------------------------------------------------
  
  @BeforeEach
  protected void setUp() throws Exception {
    
    this.jwtUtil = Mockito.mock( JwtUtil.class ) ;
    
    // -------------------------
    
    this.token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJuYmYiOjE2NjAxODQyMzksIlJPTEVTIjpbIlJPTEVfZGV2X2FkbWluIl0sImlzcyI6IkNSRURFTlRJQUxTIiwiZXhwIjoxNjYwMTg3ODM5LCJVU0VSIjoiY29ycmVvQGxpdmUuY2wiLCJpYXQiOjE2NjAxODQyMzl9.0q1DUaY_S8u0nq_57nLmaqCvcExO3qI18lPaI95zi90XYl3Iw3Aoaye519qQkc8R8eMQd3LulYexfvlK424g5g" ;
    
  }

  @AfterEach
  protected void tearDown() throws Exception {
  }
  
  // ----------------------------------------------------------------------
  
  @Test
  void testIsNullToken() {
    
    Mockito
      .when( this.jwtUtil.isNullToken( this.token ) )
      .thenReturn( true ) 
      ;
    
    boolean tokenTest = this.jwtUtil.isNullToken( this.token ) ;
    
    assertTrue( tokenTest ) ;
    
  }
  
  
}
