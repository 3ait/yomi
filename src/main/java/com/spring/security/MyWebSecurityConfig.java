package com.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.datas.easyorder.db.dao.UserRepository;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()
			.antMatchers("/static/**").permitAll()
			
			.antMatchers("/management").permitAll()
			.antMatchers("/management/order/**").hasAnyRole(UserRepository.STATUS_SUPER_ADMIN,UserRepository.STATUS_STAFF,UserRepository.STATUS_ADMIN)
			.antMatchers("/management/product/**").hasAnyRole(UserRepository.STATUS_SUPER_ADMIN,UserRepository.STATUS_STAFF,UserRepository.STATUS_ADMIN)
			
			//Superadmin
			.antMatchers("/management/**").hasAnyRole(UserRepository.STATUS_SUPER_ADMIN)
			//API
			.antMatchers("/api/management/**").hasAnyRole(UserRepository.STATUS_SUPER_ADMIN,UserRepository.STATUS_ADMIN,UserRepository.STATUS_STAFF)
			
			.and().formLogin().loginPage("/login").permitAll().successForwardUrl("/management/order/")
			.and().formLogin().loginPage("/login").permitAll().failureForwardUrl("/management")
			.and().logout().logoutUrl("/logout").logoutSuccessUrl("/management").permitAll();
	}

	@Autowired
	MyUserDetailsService myUserDetailsService;

	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(myUserDetailsService).passwordEncoder(new Md5PasswordEncoder());
	}

}
