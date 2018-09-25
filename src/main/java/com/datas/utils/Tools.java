package com.datas.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tools {

	public static boolean emailCheck(String input){
		String emailReg = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		Pattern p;
		Matcher m;
		p = Pattern.compile(emailReg);
		m = p.matcher(input);
		
		return m.matches();
	}
}
