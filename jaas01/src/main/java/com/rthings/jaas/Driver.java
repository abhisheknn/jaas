package com.rthings.jaas;

import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

public class Driver {
public static void main(String[] args) {
	System.setProperty("java.security.auth.login.config","jaas.config");
	LoginContext loginContext=null;
	try {
		loginContext= new LoginContext("RthingsJassConfig",new RthingsCallbackHandler());
	} catch (LoginException e) {
		// TODO Auto-generated catch block
		System.out.println(e.getMessage());
		System.exit(0);
	}
	
	while(true){
		try {
		loginContext.login();
		System.exit(0);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
}
}
