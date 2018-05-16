package com.lamadrid.store.utilities;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Encryptor {
	
	private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	public static String encryptPassword(String password){		
		return encoder.encode(password);
	}
	
	public static void checkIfPasswordMatches(String passwordText, String passwordEncrypt) throws InvalidParamException{
		if(!encoder.matches(passwordText, passwordEncrypt)){
			throw new InvalidParamException();
		}
	}
	
	public static BCryptPasswordEncoder getPasswordEncoder(){
		return encoder;
	}
}

