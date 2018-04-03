package com.wuyongfa.yifa.financialsystem.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.wuyongfa.yifa.financialsystem.model.OrderInfo;
@Mapper
public interface OrderInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(OrderInfo record);

    OrderInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderInfo record);

    /**
     * 分页条件查询订单信息
     * @param filterMaps
     * @return
     */
    List<OrderInfo> selectAllByPage(@Param("orderCode") String orderCode,
    		@Param("startTime") Date startTime,@Param("endTime") Date endTime);

	/**
	 * 逻辑批量删除订单信息
	 * @param ids
	 * @return
	 */
	int deleteOrderInfo(@Param("ids") List<Integer> ids);
}