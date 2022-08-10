package com.api.credenciales.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
@Getter
public class JwtRoles {
  
  
  @Value( "#{'${jwt.roles.full}'.split(',')}" )
  private String[] jwtRolesFull ;
  
  @Value( "#{'${jwt.roles.onlyRead}'.split(',')}" )
  private String[] jwtRolesOnlyRead ;
  
  @Value( "#{'${jwt.roles.onlyInsert}'.split(',')}" )
  private String[] jwtRolesOnlyInsert ;
  
  @Value( "#{'${jwt.roles.onlyUpdate}'.split(',')}" )
  private String[] jwtRolesOnlyUpdate ;
  
  @Value( "#{'${jwt.roles.readInsert}'.split(',')}" )
  private String[] jwtRolesReadInsert ;
  
  @Value( "#{'${jwt.roles.readUpdate}'.split(',')}" )
  private String[] jwtRolesReadUpdate ;
  
  @Value( "#{'${jwt.roles.insertUpdate}'.split(',')}" )
  private String[] jwtRolesInsertUpdate ;
  
  
}
