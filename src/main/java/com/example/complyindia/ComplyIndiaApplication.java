package com.example.complyindia;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//import com.example.complyindia.model.Auth.SpringSecurityAuditorAware;

@SpringBootApplication
//@EnableJpaAuditing(auditorAwareRef="auditorAware")
//public class ComplyIndiaApplication extends SpringBootServletInitializer{
//	
//	@Bean
//	public AuditorAware<String> auditorAware(){
//		return new SpringSecurityAuditorAware();
//	}
//	
//	@Override
//	protected  SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//		return application.sources(ComplyIndiaApplication.class);
//	}
//
//	public static void main(String[] args) {
//		SpringApplication.run(ComplyIndiaApplication.class, args);
//	}
//	
//	
//
//}




public class ComplyIndiaApplication{
	
//	@Bean
//	public AuditorAware<String> auditorAware(){
//		return new SpringSecurityAuditorAware();
//	}
	
	public static void main(String[] args) {
		SpringApplication.run(ComplyIndiaApplication.class, args);
	}
	
	

}
