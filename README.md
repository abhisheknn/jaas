# jaas


#Jaas 02:

Comment System.setProperty("java.security.auth.login.config","jaas.config");
-Djava.security.auth.login.config=file:filpath

#Jaas 03 :
comment System.setProperty("java.security.auth.login.config","jaas.config");
Go to your projects ..JRE/lib/security/
Open java.SECURITY file
search for login.config.url.1= path to jaas config file


#Jaas Web App :
#Authentication and Authorization using mysql in JBOSS Web App


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

Insert sample User and Role
INSERT INTO `users` (`username`, `password`) VALUES ('abhi', 'nn');
INSERT INTO `roles` (`rolename`) VALUES ('admin');
INSERT INTO `users_roles` (`username`, `rolename`) VALUES ('abhi', 'admin');
COMMIT;


Add The driver as a module in wildfly:
	1.Enter in the file path ${EAP_HOME}/modules/system/layers/base and create the directories com/mysql/driver/main.
	2.Add the Mysql connector jar to this folder
	3. Create the module.xml in this folder
		 <module xmlns="urn:jboss:module:1.3" name="com.mysql.driver">
	 	<resources>
	  	<resource-root path="mysql-connector-java-5.1.33.jar" />
	 	</resources>
	 	<dependencies>
	  	<module name="javax.api"/>
	  	<module name="javax.transaction.api"/>
	 	</dependencies>
		</module>		

Configure mysql module in standalone.xml

Navigate to <jboss_home>/standalone/configuration and open standalone.xml .

You will find a datasources tag under which you need to put this


 <datasource jta="false" jndi-name="java:/JaasMysql" pool-name="JaasMysql" enabled="true" use-ccm="false">
      <connection-url>jdbc:mysql://localhost:3306/JaasUserAndRoles</connection-url>
      <driver-class>com.mysql.jdbc.Driver</driver-class>
      <driver>mysql</driver>
      <security>
          <user-name>root</user-name>
          <password>root</password>
      </security>
      <validation>
          <validate-on-match>false</validate-on-match>
          <background-validation>false</background-validation>
      </validation>
      <statement>
          <share-prepared-statements>false</share-prepared-statements>
      </statement>
 </datasource>

Add following code to subsystems tag.

<subsystem xmlns="urn:jboss:domain:jpa:1.1">
            <jpa default-datasource="java:/JaasMysql"/>
</subsystem>

Configure jdbc driver using previously created module. Add following into drivers tag in standalone.xml


<driver name="mysql" module="com.mysql.driver">
    <xa-datasource-class>com.mysql.jdbc.Driver</xa-datasource-class>
</driver>


You can test the above configuration from Admin console for wildfly.
	Access localhost:9990 .
	Follow instruction to add user.
	login with created user .
	Click on Datasources, If everything is configured propery you will be able to see mysql datasource in  datasources    `     listing.
	Select your data source , navigate to Connection tab , click Test Connection. 
