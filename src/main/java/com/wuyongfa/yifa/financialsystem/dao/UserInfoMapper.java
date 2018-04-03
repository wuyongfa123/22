package com.wuyongfa.yifa.financialsystem.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.wuyongfa.yifa.financialsystem.model.UserInfo;

@Mapper
public interface UserInfoMapper {
    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserInfo record);

    /**
     * 查询用户登录信息
     * @param userInfo
     * @return
     */
	UserInfo selectUser(@Param("userInfo") UserInfo userInfo);
}