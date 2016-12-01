package com.rthings.jaas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;

public class RthingsCallbackHandler implements CallbackHandler {

	@Override
	public void handle(Callback[] callbacks) throws IOException,
			UnsupportedCallbackException {
		NameCallback nameCallback=null;
		PasswordCallback passwordCallback=null;
		for(Callback callback:callbacks){
			if(callback instanceof NameCallback){
				nameCallback=(NameCallback)callback;
				System.out.println(nameCallback.getPrompt());
				String name=new BufferedReader(new InputStreamReader(System.in)).readLine();
				nameCallback.setName(name);
			}else if(callback instanceof PasswordCallback){
				passwordCallback=(PasswordCallback)callback;
				System.out.println(passwordCallback.getPrompt());
				String pwd=new BufferedReader(new InputStreamReader(System.in)).readLine();
				passwordCallback.setPassword(pwd.toCharArray());
			}
		}
		
		
	}

}
