package com.wuyongfa.yifa.financialsystem.service;

import java.util.List;

import com.wuyongfa.yifa.financialsystem.model.PurchaseInfo;
import com.wuyongfa.yifa.financialsystem.page.Page;

/**
 * 资源采购管理层
 *	
 * @author wuyongfa@chinasoftinc.com
 * 2017年6月19日
 */
public interface PurchaseService {

	/**
	 * 分页查询采购信息
	 * @param page 
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @return
	 */
	Page selectPurchaseInfo(Page page, String startTime, String endTime);
	
	/**
	 * 添加资源采购记录
	 * @param purchaseInfo
	 * @return
	 */
	int addPurchaseInfo(PurchaseInfo purchaseInfo);

	/**
	 * 修改资源采购记录
	 * @param purchaseInfo
	 * @return
	 */
	int updatePurchaseInfo(PurchaseInfo purchaseInfo);

	/**
	 * 批量删除资源采购记录
	 * @param targets
	 * @return
	 */
	int deletePurchaseInfo(List<Integer> ids);

	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	PurchaseInfo selectById(int id);

}
