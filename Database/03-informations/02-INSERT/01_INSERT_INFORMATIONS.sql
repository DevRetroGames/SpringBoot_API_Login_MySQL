USE CREDENTIALS ;

-- id : 
-- personal information
  -- name              :
  -- last_name         :
  -- age              :
  -- cell_phono_number  :
  -- email              :
-- locality data
  -- dni      :
  -- country  :
  -- city     :
-- image: 
-- create_at     : TIMESTAMP     --
-- update_at : TIMESTAMP     --
INSERT INTO CREDENTIALS.INFORMATIONS( 
  name , last_name , age , cell_phono_number , EMAIL ,
  dni , country , city , 
  image
) 
VALUES( 
  "nombres" , "apellidos" , 30 , "+56912345678" , "correo@live.cl" ,
  "12345678-9" , "pais" , "ciudad"
) ;