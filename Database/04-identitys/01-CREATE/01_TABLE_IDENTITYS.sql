CREATE TABLE IF NOT EXISTS CREDENTIALS.IDENTITYS (  
    
    -- uuid
    id VARCHAR( 36 ) DEFAULT ( uuid() ) PRIMARY KEY ,
    
    -- fk of information table
    information_id VARCHAR( 36 ) ,

    -- fk of roles table
    -- role_id VARCHAR( 36 ) ,

    -- access credentials
    keyword VARCHAR( 255 ) NOT NULL ,

    -- 0: off
    -- 1: on
    status BOOLEAN DEFAULT 0 ,

    -- dates
    create_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
	update_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
    
    -- fk to informations table
    FOREIGN KEY ( information_id ) REFERENCES INFORMATIONS( id )
    ON DELETE RESTRICT
    ON UPDATE CASCADE

    -- fk to roles table
    -- FOREIGN KEY ( role_id ) REFERENCES ROLES( id )
    -- ON DELETE SET NULL 
    -- ON UPDATE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ;