CREATE TABLE IF NOT EXISTS CREDENTIALS.IDENTITYS_ROLES (  

    -- uuid identity
    identity_id VARCHAR( 36 ) NOT NULL ,

    -- uuid role
    role_id VARCHAR( 36 ) NOT NULL ,
    
    -- 
    PRIMARY KEY( identity_id , role_id ) ,
    
    -- 
    CONSTRAINT identitysRoles_identity_fk
		  FOREIGN KEY identity_fk( identity_id ) REFERENCES IDENTITYS( id )
      ON DELETE CASCADE
      ON UPDATE CASCADE ,

    -- 
    CONSTRAINT identitysRoles_role_fk
      FOREIGN KEY role_fk( role_id ) REFERENCES ROLES( id )
      ON DELETE CASCADE
      ON UPDATE CASCADE
        

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ;