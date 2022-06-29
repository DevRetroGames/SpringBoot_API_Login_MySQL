USE CREDENTIALS ;

-- id               :           -- desc:
-- name             : Admin     -- desc: nombre del rol
-- status           : 1         -- desc: rol activo 
-- create_at        :           -- desc:
-- update_at        :           -- desc:
INSERT INTO CREDENTIALS.ROLES( name , status ) 
VALUES( "dev_admin" , 1 ) ;

-- id               :           -- desc:
-- name             :           -- desc: secretaria
-- estado           : 1         -- desc: rol activo
-- create_at        :           -- desc:
-- update_at        :           -- desc:
INSERT INTO CREDENTIALS.ROLES( name , status ) 
VALUES( "secretaria" , 1 ) ;