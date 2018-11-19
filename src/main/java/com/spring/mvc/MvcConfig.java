package com.spring.mvc;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.datas.easyorder.controller.mobile.my.interceptor.MobileTokenInterceptor;
import com.datas.easyorder.controller.web.customer.interceptor.CustomerInterceptor;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*").allowedMethods("*").allowedHeaders("*");
	}
	
	

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		//客户登录拦截器 mobile
		registry.addInterceptor(new MobileTokenInterceptor())
		.addPathPatterns("/m/api/customers/**")
		.excludePathPatterns("/m/api/customers")
		.excludePathPatterns("/m/api/customers/email")
		.excludePathPatterns("/m/api/customers/login")
		.excludePathPatterns("/m/api/customer/payment/return")
		.excludePathPatterns("/m/api/customer/payment/notice")
		.excludePathPatterns("/m/api/customers/register");

		
		
		//WEB
		registry.addInterceptor(new CustomerInterceptor())	
		.addPathPatterns("/customer/**")
		.excludePathPatterns("/customer")
		.excludePathPatterns("/customer/email/check")
		.excludePathPatterns("/customer/login")
		.excludePathPatterns("/customer/login")
		.excludePathPatterns("/customer/payment/return")
		.excludePathPatterns("/customer/payment/notice")
		.excludePathPatterns("/customer/register");

	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		 registry.addResourceHandler("/**").addResourceLocations("file:attachment");
		 registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
		 registry.addResourceHandler("/**.css").addResourceLocations("classpath:/templates/");
		 registry.addResourceHandler("/**.js").addResourceLocations("classpath:/templates/");
	}


	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
	}

}
