package com.wuyongfa.yifa.financialsystem.intercept;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class InterceptConfiger extends WebMvcConfigurerAdapter {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor(new LoginIntercepter()).addPathPatterns("/v1/initRedisdata/xx/**");
		
		super.addInterceptors(registry);
	}
}
