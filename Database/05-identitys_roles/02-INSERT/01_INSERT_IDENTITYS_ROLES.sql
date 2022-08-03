USE CREDENTIALS ;

-- id
-- identity_id
-- role_id 
-- create_at     : TIMESTAMP     --
-- update_at : TIMESTAMP     --
INSERT INTO CREDENTIALS.IDENTITYS_ROLES( 
  identity_id , 
  role_id
) 
VALUES( 
  ( SELECT IDENTITYS.id FROM IDENTITYS INNER JOIN INFORMATIONS ON INFORMATIONS.name = "nombres" ) ,
  ( SELECT id FROM ROLES WHERE ROLES.name = "dev_admin" )
) ;