package com.wuyongfa.yifa.financialsystem.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.wuyongfa.yifa.financialsystem.model.ConsumptionInfo;
@Mapper
public interface ConsumptionInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ConsumptionInfo record);

    int insertSelective(ConsumptionInfo record);

    ConsumptionInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ConsumptionInfo record);

    int updateByPrimaryKey(ConsumptionInfo record);

	/**
	 * 逻辑删除消费信息
	 * @param ids
	 * @return
	 */
	int deleteConsumptionInfo(@Param("ids") List<Integer> ids);
	/**
     * 分页条件查询资源消费信息
     * @param filterMaps
     * @return
     */
	List<ConsumptionInfo> selectAllByPage(@Param("employeeName") String employeeName, @Param("startTime") Date startTime,@Param("endTime") Date endTime);
}