package com.spring.auth2;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import com.datas.easyorder.db.dao.UserRepository;

@Service
public class MyClientDetailsService implements ClientDetailsService {

	String key = "key";
	String secret = "secret";
	
	@Override
	public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
		BaseClientDetails baseClientDetails = new BaseClientDetails();
		//设置客户端key
		baseClientDetails.setClientId(clientId);
		//设置客户端secret
		baseClientDetails.setClientSecret(secret);
		//有效时期
		baseClientDetails.setAccessTokenValiditySeconds(3600*24*30);
		
         
		List<GrantedAuthority> authorities =  new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_" + UserRepository.STATUS_ADMIN));
		
		baseClientDetails.setAuthorities(authorities);
		
		
		return baseClientDetails;
	}


}
