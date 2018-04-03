package com.wuyongfa.yifa.financialsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wuyongfa.yifa.financialsystem.dao.UserInfoMapper;
import com.wuyongfa.yifa.financialsystem.model.UserInfo;
import com.wuyongfa.yifa.financialsystem.service.UserInfoService;

@Service
public class UserInfoServiceImpl implements UserInfoService {
	@Autowired
	private UserInfoMapper userInfoMapper;

	@Override
	public UserInfo selectUser(UserInfo userInfo) {
		
		return userInfoMapper.selectUser(userInfo);
	}
}
