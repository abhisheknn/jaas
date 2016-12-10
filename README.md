# jaas


Jaas 02:

Comment System.setProperty("java.security.auth.login.config","jaas.config");
-Djava.security.auth.login.config=file:filpath

Jass 03 :
comment System.setProperty("java.security.auth.login.config","jaas.config");
Go to your projects ..JRE/lib/security/
Open java.SECURITY file
search for login.config.url.1= path to jaas config file


Jaas Web App :
Authentication and Authorization using mysql in JBOSS Web App


CREATE DATABASE JaasUserAndRoles;
USE JaasUserAndRoles;
CREATE TABLE users (
username varchar(20) NOT NULL PRIMARY KEY,
password varchar(20) NOT NULL
);
CREATE TABLE roles (
rolename varchar(20) NOT NULL PRIMARY KEY
);
CREATE TABLE users_roles (
username varchar(20) NOT NULL,
rolename varchar(20) NOT NULL,
PRIMARY KEY (username, rolename),
CONSTRAINT users_roles_fk1 FOREIGN KEY (username) REFERENCES users (username),
CONSTRAINT users_roles_fk2 FOREIGN KEY (rolename) REFERENCES roles (rolename)
);

