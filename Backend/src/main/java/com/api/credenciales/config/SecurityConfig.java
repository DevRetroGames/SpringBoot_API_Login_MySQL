package com.api.credenciales.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity( prePostEnabled = true )
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  
  
  @Autowired
  private UserDetailsService userDetailsService ;
  
  @Autowired
  private AuthenticationProvider authenticationProvider ;
  
  
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder() ;
  }
  
  
  
  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean() ;
  }
  
  
  
  @Override
  protected void configure( AuthenticationManagerBuilder auth ) throws Exception {
    /*
    auth
      .userDetailsService( this.userDetailsService )
      .passwordEncoder( this.passwordEncoder() ) 
      ;*/
    
    auth
      .authenticationProvider( this.authenticationProvider )
      ;
    
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
      .antMatchers( "/api/auth/**" ).permitAll()
      .anyRequest()
      .authenticated()
      .and()
      .httpBasic()
      ;
    
  }
  
  
}