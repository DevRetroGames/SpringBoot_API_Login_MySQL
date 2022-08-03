USE CREDENTIALS ;

-- id
-- information_id
-- rol_id 
-- keyword 
-- status
-- create_at     : TIMESTAMP     --
-- update_at : TIMESTAMP     --
INSERT INTO CREDENTIALS.IDENTITYS( 
  information_id , 
  keyword , 
  status 
) 
VALUES( 
  ( SELECT id FROM INFORMATIONS WHERE name = "nombres" ) ,
  "$2a$10$WaTD1I748SNiPF.j0Q0IA.rZMcyDUFCl37K1tSCefeaaLo028mdcq" ,
  1 
) ;