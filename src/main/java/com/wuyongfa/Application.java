package com.wuyongfa;

import java.util.Arrays;
import java.util.Date;

import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@EnableAsync
@EnableScheduling
@ComponentScan
@ServletComponentScan
public class Application 
{

	public static void main( String[] args )
	{
		ApplicationContext ctx = SpringApplication.run(Application.class, args);

		//仅在测试环境中使用
		System.out.println("通过Spring Boot启动了Http Server，以下是Spring Boot扫描的Bean列表：");
		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);
		for (String beanName : beanNames) {
			System.out.println("found bean -> " + beanName);
		}

	}
	
	@Test
	public void test(){
		System.out.println(new Date().getTime());
	}
}
