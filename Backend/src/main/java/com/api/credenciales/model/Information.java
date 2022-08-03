package com.api.credenciales.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
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
@Table( name = "INFORMATIONS" )
public class Information {
	
	@Id
	@GeneratedValue( generator = "hibernate-uuid" )
	@GenericGenerator( name = "uuid" , strategy = "uuid4" )
	@Type( type = "org.hibernate.type.UUIDCharType" )
	@Column( name = "id" , updatable = false , nullable = false )
	private UUID id ;
	
	@Column( name = "name" )
	private String name ;
	
	@Column( name = "last_name" )
	private String lastName ;
	
	@Column( name = "age" )
	private int age ;
	
	@Column( name = "cell_phono_number" )
	private String cellPhonoNumber ;
	
	@Column( name = "email" )
	private String email ;
	
	@Column( name = "dni" )
	private String dni ;
	
	@Column( name = "country" )
	private String country ;
	
	@Column( name = "city" )
	private String city ;
	
	@Column( name = "image" )
	private boolean image ;
	
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "create_at" , insertable = false, updatable = false )
	private Date createAt ;
	
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "update_at" , insertable = false, updatable = false )
	private Date updateAt ;
	
	@OneToOne( mappedBy = "information" , fetch = FetchType.LAZY , cascade = CascadeType.ALL )
	private Identity identity ;

}
