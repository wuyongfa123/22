package com.wuyongfa.yifa.financialsystem.service;

import java.util.List;

import com.wuyongfa.yifa.financialsystem.model.OrderInfo;
import com.wuyongfa.yifa.financialsystem.page.Page;

/**
 * 订单服务层
 *	
 * @author wuyongfa@chinasoftinc.com
 * 2017年6月19日
 */
public interface OrderService {

	/**
	 * 分页条件查询订单信息
	 * @param page
	 * @param orderCode 订单编号
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @return
	 */
	Page selectOrderInfo(Page page, String orderCode, String startTime,
			String endTime);

	/**
	 * 添加订单信息的同时，添加cnc加工件信息
	 * @param orderInfo 订单信息
	 * @return
	 */
	int addOrderInfo(OrderInfo orderInfo);

	/**
	 * 修改订单信息
	 * @param orderInfo
	 * @return
	 */
	int updateOrderInfo(OrderInfo orderInfo);

	/**
	 * 根据订单ID删除订单信息
	 * @param ids
	 * @return
	 */
	int deleteOrderInfo(List<Integer> ids);

	/**
	 * 根据主键查询订单信息
	 * @param id
	 * @return
	 */
	OrderInfo selectByCode(int id);

}
