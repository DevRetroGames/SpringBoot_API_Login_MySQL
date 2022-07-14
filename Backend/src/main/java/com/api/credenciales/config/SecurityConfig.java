package com.api.credenciales.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  
  
  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder() ;
  }
  
  
  
  @Override
  protected void configure( AuthenticationManagerBuilder auth ) throws Exception {
    
    
    
  }
  
  
  
  @Override
  protected void configure( HttpSecurity http ) throws Exception {
    
    http
      .csrf()
      .disable() ;
    
    http
      .sessionManagement()
      .sessionCreationPolicy( SessionCreationPolicy.STATELESS )
      ;
    
    http
      .authorizeRequests()
      .anyRequest()
      .permitAll()
      ;
    
  }
  
  
}
