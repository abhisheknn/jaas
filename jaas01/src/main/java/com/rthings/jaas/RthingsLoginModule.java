package com.rthings.jaas;

import java.io.IOException;
import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

public class RthingsLoginModule implements LoginModule {

	public static final String USER="root";
	public static final String PASSWORD="password";
	private  CallbackHandler rthingsCallbackHandler=null;
	private boolean authenticationSuccessFlag=false;	
	
	@Override
	public void initialize(Subject subject, CallbackHandler callbackHandler,
			Map<String, ?> sharedState, Map<String, ?> options) {
	this.rthingsCallbackHandler=callbackHandler;
		
	}

	@Override
	public boolean login() throws LoginException {
		Callback callBacks[]= new Callback[2];
		callBacks[0]= new NameCallback("Username : \n");
		callBacks[1]= new PasswordCallback("Password :\n", false);
		try {
			rthingsCallbackHandler.handle(callBacks);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedCallbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String username= ((NameCallback) callBacks[0]).getName();
		String password=new String(((PasswordCallback) callBacks[1]).getPassword());
		if(USER.equals(username) && PASSWORD.equals(password)){
			System.out.println("Authentication Successful");
			authenticationSuccessFlag=true;
		}else{
			authenticationSuccessFlag=false;
			throw new FailedLoginException("Authentication Failure");
		}	
		return authenticationSuccessFlag;
	}

	@Override
	public boolean commit() throws LoginException {
		return authenticationSuccessFlag;
	}

	@Override
	public boolean abort() throws LoginException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean logout() throws LoginException {
		// TODO Auto-generated method stub
		return false;
	}

	
}
