package com.wuyongfa.yifa.financialsystem.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;


/**
 * 过滤器配置类
 *	
 * @author wuyongfa@chinasoftinc.com
 * 2018年1月29日
 */
public class FilterConfiguration {
	
	@Bean
	public FilterRegistrationBean newFilter(){
		FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
		filterRegistration.setFilter(getNewFilter());
		filterRegistration.addUrlPatterns("/*");//添加过滤规则
		//添加初始化参数
		filterRegistration.addInitParameter("paramName", "paramValue");
		//加不需要忽略的格式信息.  
		//filterRegistration.addInitParameter("exclusions", "/login/*,/**/static/**,**.jpg,/**/pdf/**,/**/error/**,/v1/initRedisdata/**");
		filterRegistration.setName("newFilter");
		filterRegistration.setOrder(0);
		
		
		return filterRegistration;
	}
	
	@Bean(name="newFilter")
	public NewFilter getNewFilter(){
		return new NewFilter();
	}
	
}
