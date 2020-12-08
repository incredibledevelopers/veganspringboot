package com.vegan;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import nz.net.ultraq.thymeleaf.LayoutDialect;

@Configuration
public class VeganConfig implements WebMvcConfigurer{

	@Bean
	public LayoutDialect layoutDialect() {
	    return new LayoutDialect();
	}
	
	@Override
	 public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("ad-index");
		registry.addViewController("/orders").setViewName("orders");
		registry.addViewController("/rate-card").setViewName("rate-card");
	}
}
