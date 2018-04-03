package com.wuyongfa.yifa.financialsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wuyongfa.yifa.financialsystem.model.UserInfo;
import com.wuyongfa.yifa.financialsystem.response.Response.Response;
import com.wuyongfa.yifa.financialsystem.service.UserInfoService;

@RestController
@RequestMapping(value="/v1/fm/user")
public class UserInfoController{
	@Autowired
	private UserInfoService userInfoService;
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public Response login(@RequestBody UserInfo userInfo){
		
		userInfo = userInfoService.selectUser(userInfo);
		
		return new Response().success(userInfo);
	}
}
