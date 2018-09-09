package com.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
@ComponentScan(basePackages="com",value="com")
@EnableJpaRepositories("com.datas.easyorder.db.dao")
@EntityScan("com.datas.easyorder.db.entity")
public class Application extends SpringBootServletInitializer{
	

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    	return application.sources(Application.class);
	}

	@Bean
	public EmbeddedServletContainerCustomizer  containerCustomizer(){
		return (container ->{
			ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/error.html");
			ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/error.html");
			ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error.html");
			
			container.addErrorPages(error401Page, error404Page, error500Page);
		});
	}
	
	
	public static void main(String[] args) throws Throwable {
        SpringApplication.run(Application.class, args);
    }
}