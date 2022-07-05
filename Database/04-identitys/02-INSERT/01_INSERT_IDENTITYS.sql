USE CREDENTIALS ;

-- id
-- information_id
-- rol_id 
-- username  
-- keyword 
-- status
-- create_at     : TIMESTAMP     --
-- update_at : TIMESTAMP     --
INSERT INTO CREDENTIALS.IDENTITYS( 
  information_id , 
--  role_id ,
  username , 
  keyword , 
  status 
) 
VALUES( 
  ( SELECT id FROM INFORMATIONS WHERE name = "nombres" ) ,
--  ( SELECT id FROM ROLES WHERE name = "dev_admin" ) , 
  "dev" , 
  "dev" ,
  1 
) ;