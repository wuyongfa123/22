package com.wuyongfa.yifa.financialsystem.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component
public class NewFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {		
		System.out.println("初始化");
	}

	@SuppressWarnings("unused")
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		System.out.println("开始过滤");
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) request;
		
		if (true) {
			//重定向
			httpServletResponse.sendRedirect("/");
		}else{
			//放行
			chain.doFilter(httpServletRequest, httpServletResponse);
		}
	} 

	@Override
	public void destroy() {
	}

}
