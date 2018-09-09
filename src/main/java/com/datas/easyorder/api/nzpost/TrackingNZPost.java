package com.datas.easyorder.api.nzpost;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component("trackingNZPost")
public class TrackingNZPost {

	//NZ POST
	private static final String  clientId = "be8245da6f5341eeaa5bdc629fc99e88";
	private static final String clientSecret = "2B3DC3D1Fe584c14B6E87789E723f8Be";
	
	
	public String getToken() throws IOException{
		String postUrl = "https://oauth.nzpost.co.nz/as/token.oauth2";
		
		HttpsURLConnection conn = (HttpsURLConnection) new URL(postUrl).openConnection();
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setRequestMethod("POST");
		conn.connect();
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
		bw.write(String.format("grant_type=client_credentials&client_id=%s&client_secret=%s",clientId,clientSecret));
		bw.flush();
		
		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8")) ;
		
		StringBuffer sb = new StringBuffer();
		String readline = null;
		while((readline=rd.readLine())!=null){
			sb.append(readline);
		}
		
		return sb.toString();
		
	}
	
	/**
	 * Get API
	 * @return String
	 * @throws JSONException
	 * @throws IOException
	 */
	public String getTracking(String Tracking) throws JSONException, IOException{
		JSONObject tokenJson = new JSONObject(getToken());
		String accessToken = tokenJson.getString("access_token");
		String tokenType = tokenJson.getString("token_type");
		
		
		
		String postUrl = "https://api.nzpost.co.nz/parceltrack/3.0/parcels/"+Tracking;
		
		HttpsURLConnection conn = (HttpsURLConnection) new URL(postUrl).openConnection();
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setRequestProperty("Authorization", tokenType + " " + accessToken);
		conn.setRequestProperty("client_id", clientId);
		conn.connect();
		
		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8")) ;
		
		StringBuffer sb = new StringBuffer();
		String readline = null;
		while((readline=rd.readLine())!=null){
			sb.append(readline);
		}
		return sb.toString();
	}
	
	public static void main(String[] args) throws IOException{
		TrackingNZPost nzpost = new TrackingNZPost();
		nzpost.getTracking("EP515775142NZ");
	}
}
