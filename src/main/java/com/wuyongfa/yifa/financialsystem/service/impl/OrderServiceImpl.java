package com.wuyongfa.yifa.financialsystem.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wuyongfa.yifa.financialsystem.dao.CncProcessMapper;
import com.wuyongfa.yifa.financialsystem.dao.OrderInfoMapper;
import com.wuyongfa.yifa.financialsystem.model.OrderInfo;
import com.wuyongfa.yifa.financialsystem.page.Page;
import com.wuyongfa.yifa.financialsystem.service.OrderService;
import com.wuyongfa.yifa.financialsystem.utils.DateUtils;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderInfoMapper orderInfoMapper;
	@Autowired
	private CncProcessMapper cncProcessMapper;

	@Override
	public Page selectOrderInfo(Page page, String orderCode, String startTime,String endTime) {

		List<OrderInfo> orders = orderInfoMapper.selectAllByPage(orderCode,DateUtils.parse2(startTime),DateUtils.parse2(endTime));

		page.setData(orders);
		return page;
	}

	@Override
	public int addOrderInfo(OrderInfo orderInfo) {
		orderInfo.setStatus("01");
		orderInfo.setCreatedAt(new Date());
		orderInfo.setUpdatedAt(new Date());
		orderInfo.setDeleteFlag("0");
		//插入订单信息
		int n = orderInfoMapper.insertSelective(orderInfo); 

		return n;
	}

	@Override
	public int updateOrderInfo(OrderInfo orderInfo) {
		orderInfo.setUpdatedAt(new Date());
		return orderInfoMapper.updateByPrimaryKeySelective(orderInfo);
	}

	@Override
	public int deleteOrderInfo(List<Integer> ids) {

		return orderInfoMapper.deleteOrderInfo(ids);
	}

	@Override
	public OrderInfo selectByCode(int id) {

		return orderInfoMapper.selectByPrimaryKey(id);
	}
}
