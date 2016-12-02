# jaas


Jaas 02:

Comment System.setProperty("java.security.auth.login.config","jaas.config");
-Djava.security.auth.login.config=file:filpath

Jass 03 :
comment System.setProperty("java.security.auth.login.config","jaas.config");
Go to your projects ..JRE/lib/security/
Open java.SECURITY file
search for login.config.url.1= path to jaas config file
