package com.wuyongfa.yifa.financialsystem.service;

import java.util.List;

import com.wuyongfa.yifa.financialsystem.model.ConsumptionInfo;
import com.wuyongfa.yifa.financialsystem.page.Page;

/**
 * 资源消费服务层
 *	
 * @author wuyongfa@chinasoftinc.com
 * 2017年6月19日
 */
public interface ConsumptionService {

	/**
	 * 分页条件查询采购信息
	 * @param page
	 * @param employeeName 
	 * @param consumptionInfo
	 * @return
	 */
	Page selectConsumptionInfo(Page page, String startTime, String endTime, String employeeName);

	/**
	 * 添加采购信息
	 * @param consumptionInfo
	 * @return
	 */
	int addConsumptionInfo(ConsumptionInfo consumptionInfo);

	/**
	 * 修改采购信息
	 * @param consumptionInfo
	 * @return
	 */
	int updateConsumptionInfo(ConsumptionInfo consumptionInfo);

	/**
	 * 批量删除采购信息
	 * @param targets
	 * @return
	 */
	int deleteConsumptionInfo(List<Integer> ids);

	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	ConsumptionInfo selectByKey(int id);

}
