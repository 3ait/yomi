package com.spring.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.datas.easyorder.db.entity.User;

public class MyUserDetails implements UserDetails{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;  
    private String username;  
    private String password;  
    private List<GrantedAuthority> authorities ;
	
    
    public MyUserDetails(User user){
    	this.username = user.getEmail();
    	this.password = user.getPassword();
    	this.id = user.getId();
    	authorities = new ArrayList<>();
    	authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getStatus()));
    }
    
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities ;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
}
