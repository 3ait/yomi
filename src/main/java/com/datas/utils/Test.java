package com.datas.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.plugin.utils.Md5;

public class Test {
	
	public void newProduct() throws IOException{
		
		
		PrintWriter pw = new PrintWriter( new OutputStreamWriter(new FileOutputStream(new File("d:/new-sq.txt"))));
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("D:/a.csv")), "utf-8"));
		String line = null;
		
		//INSERT INTO `items` VALUES ('1230', 'Viscose', 'BLS-SZJ-11', 'item-1230', '57', '4.50', '0.00', 8'1377751476_BLS-SZJ-11.JPG', 'Mateiral: Viscose', '0', '0', '1', '25', '0');
		while((line=br.readLine())!=null){
			System.out.println(line);
			
			String[] s = line.split(",");
			if(s.length==3){
				
				StringBuffer newsb = new StringBuffer();
				newsb.append("insert into product (product_name,product_name_alias,mpn,default_src,price1,price2,stock,sold_num,description,status,create_time,modify_time)");
				newsb.append(" values ");
				newsb.append(" ( ");
				newsb.append("'").append(s[0]).append("'").append(",");
				newsb.append("'").append(s[2]).append("'").append(",");
				newsb.append("'").append(s[1]).append("'").append(",");
				//src
				newsb.append("'").append("'").append(",");
				newsb.append("'").append(1).append("'").append(",");
				newsb.append("'").append(1).append("'").append(",");
				newsb.append("'").append(0).append("'").append(",");
				newsb.append("'").append(0).append("'").append(",");
				newsb.append("'").append("'").append(",");
				newsb.append("'").append(1).append("'").append(",");
				newsb.append("now()").append(",");
				newsb.append("now()");
				newsb.append(");");
				pw.println(newsb);
			}
		}
		
		pw.flush();
		pw.close();
		br.close();
		
		
	}
	
	public void newCustomer() throws IOException{
		
		
		PrintWriter pw = new PrintWriter( new OutputStreamWriter(new FileOutputStream(new File("d:/newcustomer.txt"))));
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("D:/member.sql")), "utf-8"));
		String line = null;
		
		//INSERT INTO `member` VALUES ('1755', 'Glengyle', 'Ebony/Stev', 'Hessey', 'accounts@glengyle.co.nz', '1', '09-6369540', '021433810', '0', '13 Fleming Street', 'Onehunga', 'Auckland', '', '', '', '', '', 'f08f84d1817fbec882308b904d9d0daedbc67056', '0', '', '351.56', 'steve@glengyle.co.nz\nebony@glengyle.co.nz', '2012-01-01', '0');
//		Pattern p = Pattern.compile("INSERT INTO `member` VALUES \\('(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', \\);");
//		Pattern p = Pattern.compile("INSERT INTO `member` VALUES \\('(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', \\);");
		
		Pattern p1 = Pattern.compile(".*'(.*)'");
		while((line=br.readLine())!=null){
			
			String[] strs = line.split(",");
			if(strs.length!=24){
				System.out.println(line);
				continue;
			}
			for(int i=0;i<strs.length;i++){
				Matcher m = p1.matcher(strs[i]);
				if(m.matches()){
					strs[i] = m.group(1).replaceAll("'", "");
				}
			}
			StringBuffer newsb = new StringBuffer();
			newsb.append("insert into CUSTOMER (email,name,company_email,phone,password,status,shipping_address,address,tel,balance,customer_type,memo,company_name,create_time,modify_time)");
			newsb.append(" values ");
			newsb.append(" ( ");
			//email
			newsb.append("'").append(strs[4]).append("'").append(",");
			//name
			newsb.append("'").append(strs[2] + " " +  strs[3]).append("'").append(",");
			//company_email
			newsb.append("'").append(strs[4]).append("'").append(",");
			//phone
			newsb.append("'").append(strs[7]).append("'").append(",");
			//password
			newsb.append("'").append(Md5.getMd5String("1234")).append("'").append(",");
			//status
			newsb.append("'").append(strs[5]).append("'").append(",");
			//shipping_address
			newsb.append("'").append(strs[9] + ", " +  strs[10] + ", " +  strs[11]).append("'").append(",");
			//address
			if(!"".equals(strs[13])){
				newsb.append("'").append(strs[13] + ", " + strs[14] + ", " +  strs[15]).append("'").append(",");
			}else{
				newsb.append("'").append(strs[9] + ", " +  strs[10] + ", " +  strs[11]).append("'").append(",");
			}
			//tel
			newsb.append("'").append(strs[6]).append("'").append(",");
			//balance
			newsb.append("'").append(strs[20]).append("'").append(",");
			//customer
			newsb.append("'").append("customer").append("'").append(",");
			//memo
			newsb.append("'").append(strs[21]).append("'").append(",");
			newsb.append("'").append(strs[1]).append("'").append(",");
			newsb.append("now()").append(",");
			newsb.append("now()");
			newsb.append(");");
			pw.println(newsb);
			

		}
		
		pw.flush();
		pw.close();
		br.close();
		
		
	}
	
	public void insertRankProductPrice() throws IOException{
		
		
		PrintWriter pw = new PrintWriter( new OutputStreamWriter(new FileOutputStream(new File("d:/new.txt"))));
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("D:/aa.txt")), "utf-8"));
		String line = null;
		
		//INSERT INTO `member` VALUES ('1755', 'Glengyle', 'Ebony/Stev', 'Hessey', 'accounts@glengyle.co.nz', '1', '09-6369540', '021433810', '0', '13 Fleming Street', 'Onehunga', 'Auckland', '', '', '', '', '', 'f08f84d1817fbec882308b904d9d0daedbc67056', '0', '', '351.56', 'steve@glengyle.co.nz\nebony@glengyle.co.nz', '2012-01-01', '0');
//		Pattern p = Pattern.compile("INSERT INTO `member` VALUES \\('(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', \\);");
//		Pattern p = Pattern.compile("INSERT INTO `member` VALUES \\('(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', \\);");
		
		Pattern p1 = Pattern.compile(".*'(.*)'");
		while((line=br.readLine())!=null){
			
			StringBuffer newsb = new StringBuffer();
			newsb.append(String.format("INSERT into rank_product_price (product_id,rank_customer_id,price,price1, description) values (%s,%s,%s,%s,'');", line,"7","0","0",""));
			newsb.append(String.format("INSERT into rank_product_price (product_id,rank_customer_id,price,price1, description) values (%s,%s,%s,%s,'');", line,"8","0","0",""));
			newsb.append(String.format("INSERT into rank_product_price (product_id,rank_customer_id,price,price1, description) values (%s,%s,%s,%s,'');", line,"9","0","0",""));
			newsb.append(String.format("INSERT into rank_product_price (product_id,rank_customer_id,price,price1, description) values (%s,%s,%s,%s,'');", line,"10","0","0",""));
			
			pw.println(newsb);
			

		}
		
		pw.flush();
		pw.close();
		br.close();
		
		
	}
	
	public static void main(String[] args) throws IOException, FileNotFoundException {
		Test test = new Test();
//		test.newCustomer();
//		test.newProduct();
		test.insertRankProductPrice();
		
	}
	
}
