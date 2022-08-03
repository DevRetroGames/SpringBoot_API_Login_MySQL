CREATE TABLE IF NOT EXISTS CREDENTIALS.INFORMATIONS (  
    
    -- uuid
    id VARCHAR( 36 ) DEFAULT ( uuid() ) PRIMARY KEY ,
    
    -- personal information
    name VARCHAR( 255 ) NOT NULL ,
    last_name VARCHAR( 255 ) NOT NULL ,
    age TINYINT NOT NULL ,
    cell_phono_number VARCHAR( 12 ) NOT NULL UNIQUE ,
    email VARCHAR( 255 ) NOT NULL UNIQUE ,

    -- locality data
    dni VARCHAR( 255 ) UNIQUE ,
    country VARCHAR( 255 ) ,
    city VARCHAR( 255 ) ,
    
    -- user image 
    image VARCHAR( 255 ) DEFAULT 'avatar.png' NOT NULL ,
    
    -- dates
    create_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
	update_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ;