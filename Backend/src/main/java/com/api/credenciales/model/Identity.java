package com.api.credenciales.model;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("serial")
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table( name = "IDENTITYS" )
public class Identity implements UserDetails {

  @Id
	@GeneratedValue( generator = "hibernate-uuid" )
	@GenericGenerator( name = "uuid" , strategy = "uuid4" )
	@Type( type = "org.hibernate.type.UUIDCharType" )
	@Column( name = "id" , updatable = false , nullable = false )
	private UUID id ;
	
	// foreign key of the table information
	@OneToOne( cascade = { CascadeType.MERGE , CascadeType.REMOVE } , fetch = FetchType.EAGER )
	@JoinColumn( name = "information_id" , referencedColumnName = "id" )
	private Information information ;
	
	// foreign key of the table roles
  @ManyToMany( cascade = { CascadeType.MERGE , CascadeType.REMOVE } , fetch=FetchType.EAGER )
  @JoinTable(
	name = "IDENTITYS_ROLES" ,
      joinColumns = { @JoinColumn( name = "identity_id" , referencedColumnName = "id" ) } ,
      inverseJoinColumns = { @JoinColumn( name = "role_id" , referencedColumnName = "id" ) }
  )
	private Set< Role > listRoles = new HashSet<>() ;
	
	@Column( name = "keyword" )
	private String keyword ;
	
	@Column( name = "status" )
	private boolean status ;
	
	@Temporal( TemporalType.TIMESTAMP )	
	@Column( name = "create_at" , insertable = false, updatable = false )
	private Date createAt ;
	
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "update_at" , insertable = false, updatable = false )
	private Date updateAt ;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    
    return this.getListRoles()
               .stream()
               .map( role -> 
                  new SimpleGrantedAuthority( "ROLE_" + role.getName() ) 
                )
               .collect( Collectors.toList() )
               ;
    
  }
  
  @Override
  public String getUsername() {
    return this.information.getEmail() ;
  }

  @Override
  public String getPassword() {
    return this.getKeyword() ;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true ;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true ;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true ;
  }

  @Override
  public boolean isEnabled() {
    return true ;
  }
  
  
}
