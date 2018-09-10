package com.datas.easyorder.controller.mobile.my.interceptor;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JwtToken {
	
	
	/** token秘钥，请勿泄露，请勿随便修改 backups:JKKLJOoasdlfj */  
	public static final String SECRET = "tokenSecretyomi";  
	/** token 过期时间: 10天 */  
	public static final int calendarField = Calendar.DATE;  
	public static final int calendarInterval = 30;  
	
	/** 
	 * JWT生成Token.<br/> 
	 *  
	 * JWT构成: header, payload, signature 
	 *  
	 * @param userId 
	 *            登录成功后用户userId, 参数userId不可传空 
	 */  
	public static String createToken(Long userId) throws Exception {  
		Date iatDate = new Date();  
		// expire time  
		Calendar nowTime = Calendar.getInstance();  
		nowTime.add(calendarField, calendarInterval);  
		Date expiresDate = nowTime.getTime();  
		
		// header Map  
		Map<String, Object> map = new HashMap<>();  
		map.put("alg", "HS256");  
		map.put("typ", "JWT");  
		
		// build token  
		// param backups {iss:Service, aud:APP}  
		String token = JWT.create().withHeader(map) // header  
				.withClaim("iss", "Service") // payload  
				.withClaim("aud", "APP").withClaim("userId", null == userId ? null : userId.toString())  
				.withIssuedAt(iatDate) // sign time  
				.withExpiresAt(expiresDate) // expire time  
				.sign(Algorithm.HMAC256(SECRET)); // signature  
		
		return token;  
	}  
	
	
	/** 
	 * 解密Token 
	 * @param token 
	 * @return 
	 * @throws Exception 
	 */  
	public static Map<String, Claim> verifyToken(String token) {  
		DecodedJWT jwt = null;  
		try {  
			JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();  
			jwt = verifier.verify(token);  
		} catch (Exception e) {  
			// e.printStackTrace();  
			// token 校验失败, 抛出Token验证非法异常  
			e.printStackTrace();
		}  
		return jwt.getClaims();  
	}  
	
	/** 
	 * 根据Token获取user_id 
	 *  
	 * @param token 
	 * @return userId 
	 */  
	public static Long getAppUID(String token) {  
		Map<String, Claim> claims = verifyToken(token);  
		Claim user_id_claim = claims.get("userId");  
		if (null == user_id_claim || StringUtils.isEmpty(user_id_claim.asString())) {  
			// token 校验失败, 抛出Token验证非法异常  
		}  
		return Long.valueOf(user_id_claim.asString());  
	}  
	
	public static void main(String[] args) throws Exception{
		String token = JwtToken.createToken(1L);
		System.out.println(token);
		
		Map<String, Claim> map = JwtToken.verifyToken(token);
		System.out.println(map.keySet());
		System.out.println("aud="+ map.get("aud").asString());
		System.out.println("iss="+ map.get("iss").asString());
		System.out.println("exp="+ map.get("exp").asString());
		System.out.println("userId="+ map.get("userId").asString());
		System.out.println("iat="+ map.get("iat").asString());
	}
}
