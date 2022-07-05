package com.api.credenciales.model;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table( name = "ROLES" )
public class Role {

	@Id
	@GeneratedValue( generator = "hibernate-uuid" )
	@GenericGenerator( name = "uuid" , strategy = "uuid4" )
	@Type( type = "org.hibernate.type.UUIDCharType" )
	@Column( name = "id" , updatable = false , nullable = false )
	private UUID id ;
	
	@Column( name = "name" )
	private String name ;
	
	@Column( name = "status" )
	private boolean status ;
	
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "create_at" , insertable = false, updatable = false )
	private Date createAt ;
	
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "update_at" , insertable = false, updatable = false )
	private Date updateAt ;
	
	@ManyToMany( mappedBy = "listRoles" , cascade = { CascadeType.MERGE , CascadeType.REMOVE } , fetch=FetchType.EAGER )
	private Set< Identity > identity ;
	
}
