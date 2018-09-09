package com.datas.easyorder.controller.administrator.category.logic;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import com.plugin.mail.logic.SendMail;

public class Test{
	
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("d:/test")));
		String str = null;
		while( (str = br.readLine())!=null){
			
			SendMail s = new SendMail();
			s.init("leo@3a.co.nz", "leo@3a.co.nz", "smtp.gmail.com", "521yaoliang", "", "true", str);
			boolean sendRet = s.send("Important Notice from 3a IT solutions", "We are aware of an issue affecting our website service .We are currently investigating. We will continue to provide additional updates as this incident develops. Thank you for your patience and understanding.");
			System.out.println(str + "==" + sendRet);
		}
		br.close();
		
	
	}
}
