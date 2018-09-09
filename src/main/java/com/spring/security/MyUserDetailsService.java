package com.spring.security;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.datas.easyorder.db.dao.UserRepository;
import com.datas.easyorder.db.entity.User;

@Service
public class MyUserDetailsService implements UserDetailsService{

	Logger logger = LogManager.getLogger(MyUserDetailsService.class);
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findOneByEmailAndStatusNot(username,UserRepository.STATUS_CANCELED);
		
		UserDetails userDetail = null;
		if(user!=null){
			userDetail = new MyUserDetails(user);
		}else{
			logger.error("User or password error.");
			 throw new UsernameNotFoundException(username + "not exist.");
		}
		
		return userDetail;
	}

}
