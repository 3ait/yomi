package com.datas.easyorder.api.diyparcel;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

@Component("TrackingDiyParcel")
public class TrackingDiyParcel {
	
	public List<String> getTracking(String trackingNum) throws Exception{
		String url = "http://diyparcel.com/parcelTrackingForCN?trackingNumber=" + trackingNum.trim();
		
		HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.connect();
		
		
		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8")) ;
		
		StringBuffer sb = new StringBuffer();
		String readline = null;
		while((readline=rd.readLine())!=null){
			sb.append(readline.trim());
		}
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
		
		Document doc = dbBuilder.parse(new ByteArrayInputStream(sb.toString().getBytes()));
		NodeList nList = doc.getDocumentElement().getLastChild().getFirstChild().getChildNodes();
		
		List<String> list = new ArrayList<>();
		for(int i=0;i<nList.getLength();i++){
			list.add(nList.item(i).getTextContent());
		}
		return list;
	}
	

	public static void main(String[] args) throws Exception{
		TrackingDiyParcel trackingDiyParcel = new TrackingDiyParcel();
		trackingDiyParcel.getTracking("51158320305485");
	}
	
}
