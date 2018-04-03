package com.wuyongfa.yifa.financialsystem.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.wuyongfa.yifa.financialsystem.model.AccountInfo;
@Mapper
public interface AccountInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AccountInfo record);

    int insertSelective(AccountInfo record);

    AccountInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AccountInfo record);

    int updateByPrimaryKey(AccountInfo record);

    /**
     * 分页查询
     * 模糊匹配target、accountTypName、employeeName字段
     * @param condition
     * @return
     */
	List<AccountInfo> selectAllByPage(@Param("condition") String condition);

	/**
	 * 批量逻辑删除账目信息
	 * @param ids
	 * @return
	 */
	int deleteAccountInfo(@Param("filters") Map<String, List<Integer>> mapFilters);
}