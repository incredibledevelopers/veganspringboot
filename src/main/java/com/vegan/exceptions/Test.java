package com.vegan.exceptions;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Test {

	public static void main(String[] args) {
		String prefix = "OD#";
		String  s = "OD#1";
		String s1 = s.substring(s.indexOf(prefix)+prefix.length(),s.length());
		System.out.println(s1);
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "Prajakta#965";//Umesh#943,Vaishnavi#954,Prajakta#965 
        String encodedPassword = encoder.encode(rawPassword);
         
        System.out.println(encodedPassword);
	}

}
