package com.wuyongfa.yifa.financialsystem.model.vo;

import java.util.List;

import com.wuyongfa.yifa.financialsystem.model.CncProcess;
import com.wuyongfa.yifa.financialsystem.model.OrderInfo;

/**
 * cnc加工件与订单关联表
 *	
 * @author wuyongfa@chinasoftinc.com
 * 2017年7月18日
 */
public class CncOrderVo {
	private OrderInfo orderInfo;
	
	private List<CncProcess> cncProcesses;

	public OrderInfo getOrderInfo() {
		return orderInfo;
	}

	public void setOrderInfo(OrderInfo orderInfo) {
		this.orderInfo = orderInfo;
	}

	public List<CncProcess> getCncProcesses() {
		return cncProcesses;
	}

	public void setCncProcesses(List<CncProcess> cncProcesses) {
		this.cncProcesses = cncProcesses;
	}
	
	
}
