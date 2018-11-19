package com.josh.main;

import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@ComponentScan({"com.josh.routes","com.josh.processors", "com.josh.main"})
public class Application {
	public static Logger logger = LoggerFactory.getLogger(Application.class);
	public static String OutputFilePath = "F:\\_dev\\Randoli\\test\\";
	
	public static void main(String[] args) {
	    SpringApplication.run(Application.class, args);
	  }
	
	@Bean
	  public ServletRegistrationBean<CamelHttpTransportServlet> camelServletRegistrationBean() {
	    ServletRegistrationBean<CamelHttpTransportServlet> registration = 
	    		new ServletRegistrationBean<CamelHttpTransportServlet>(new CamelHttpTransportServlet(), "/camel/*");
	    registration.setName("CamelServlet");
	    return registration;
	  }
}
