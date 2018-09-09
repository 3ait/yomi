package com.plugin.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateHelper {

	/**
	 * 获取当前系统时间 ，单位秒
	 */
	public static int getTime(){
		return (int) (System.currentTimeMillis()/1000);
	}
	
	/**
	 * 
	 * @param time
	 * @return
	 */
	public static String getMMDDYYYY(long time){
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		return df.format(time);
	}
	
	/**
	 * 
	 * @param time
	 * @return
	 */
	public static String getYYYYMMDD(long time){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(time);
	}
	
	/**
	 * 
	 * @param time
	 * @return
	 */
	public static String getYYYYMMDD(){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		return df.format(calendar.getTime());
	}
	
	/**
	 * 
	 * @param time
	 * @return
	 */
	public static String getYYYYMMDDhhmmss(){
		DateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
		Calendar calendar = Calendar.getInstance();
		return df.format(calendar.getTime());
	}

	
	/**
	 * 
	 * @return dd/MM/yyyy
	 */
	public static String getDDMMYYYY(Date date){
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		return df.format(date);
	}

	
	/**
	 * 
	 * @return yyyy-MM-dd
	 */
	public static Date parseYYYYMMDD(String dateTime){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = df.parse(dateTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	

	
	
}
