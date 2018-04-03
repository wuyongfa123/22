package com.wuyongfa.yifa.financialsystem.service;

import com.wuyongfa.yifa.financialsystem.model.UserInfo;

public interface UserInfoService {

	/**
	 * 查询用户登录信息
	 * @param userInfo
	 * @return
	 */
	UserInfo selectUser(UserInfo userInfo);

}
