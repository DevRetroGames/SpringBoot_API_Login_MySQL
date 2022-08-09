package com.api.credenciales.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.api.credenciales.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
  
  
  @Autowired
  private AuthenticationEntryPoint authenticationEntryPoint ;
  
  @Autowired
  private JwtAuthenticationFilter jwtAuthenticationFilter ;
  
  
  
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder() ;
  }
  
  
  
  @Bean
  public AuthenticationManager authenticationManager( 
      AuthenticationConfiguration authenticationConfiguration ) throws Exception {
    
    return authenticationConfiguration.getAuthenticationManager() ;
    
  }
  
  
  
  @Bean
  protected SecurityFilterChain filterChain( HttpSecurity http ) throws Exception {
    
    http
      .csrf()
      .disable() 
      ;
    
    http
      .authorizeRequests()
      .antMatchers( "/swagger-ui/**" ).permitAll()
      .antMatchers( "/v3/**" ).permitAll()
      .antMatchers( "/api/auth/**" ).permitAll()    
      .antMatchers( "/api/role/**" ).hasRole( "dev_admin" )
      .antMatchers( "/api/information/**" ).hasRole( "dev_admin" )
      .antMatchers( "/api/identity/**" ).hasRole( "dev_admin" )
      .antMatchers( "/api/image/**" ).hasRole( "dev_admin" )
      .anyRequest()
      .authenticated()
      ;
    
    http
      .exceptionHandling()
      .authenticationEntryPoint( this.authenticationEntryPoint )
      .and()
      .sessionManagement()
      .sessionCreationPolicy( SessionCreationPolicy.STATELESS )
      ;
    
    http
      .addFilterBefore( 
          this.jwtAuthenticationFilter , 
          UsernamePasswordAuthenticationFilter.class )
      ;
    
    return http.build() ;
    
  }
  
  
}