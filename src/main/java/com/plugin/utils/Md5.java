package com.plugin.utils;

import java.security.MessageDigest;

/**
 * 
 * @author yaoliang
 *
 */
public class Md5 {

	/**
	 * md5加密
	 * @param input
	 * @return String
	 */
	public static String getMd5String(String input){
		StringBuilder output = new StringBuilder();  
		try {
			MessageDigest md5 = MessageDigest.getInstance("md5");  
		    byte[] cipherData = md5.digest(input.getBytes());  
		    for(byte cipher : cipherData) {  
		        String toHexStr = Integer.toHexString(cipher & 0xff);  
		        output.append(toHexStr.length() == 1 ? "0" + toHexStr : toHexStr);  
		    }  
			
		} catch (Exception e) {
		}
		return output.toString(); 
	}
	
	public static void main(String[] args){
		String org = "123456";
		System.out.println(org + ":" + getMd5String(org));
	}
}
