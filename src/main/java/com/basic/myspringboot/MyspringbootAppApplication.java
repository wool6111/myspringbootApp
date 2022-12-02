package com.basic.myspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyspringbootAppApplication {

	public static void main(String[] args) {
		// SpringApplication.run(MyspringbootAppApplication.class, args);
		SpringApplication springApplication = new SpringApplication(MyspringbootAppApplication.class);
		// Application Type 설정
		// WebApplicationType.NONE 으로 설정하면 Tomcat이 동작하지 않는다.
		springApplication.setWebApplicationType(WebApplicationType.SERVLET);
		springApplication.run(args);
	}

}
