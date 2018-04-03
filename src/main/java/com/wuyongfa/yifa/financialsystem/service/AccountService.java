package com.wuyongfa.yifa.financialsystem.service;

import java.util.List;

import com.wuyongfa.yifa.financialsystem.model.AccountInfo;
import com.wuyongfa.yifa.financialsystem.model.PurchaseInfo;
import com.wuyongfa.yifa.financialsystem.page.Page;

/**
 * 账目信息服务层
 *	
 * @author wuyongfa@chinasoftinc.com
 * 2017年6月23日
 */
public interface AccountService {

	/**
	 * 分页条件查询账目信息
	 * @param page
	 * @param condition
	 * @return
	 */
	Page selectAccountInfo(Page page, String condition);

	/**
	 * 添加账目信息
	 * @param accountInfo
	 * @return
	 */
	int addAccountInfo(AccountInfo accountInfo);

	/**
	 * 修改账目信息
	 * @param accountInfo
	 * @return
	 */
	int updateAccountInfo(AccountInfo accountInfo);

	/**
	 * 删除账目信息
	 * @param ids
	 * @return
	 */
	int deleteAccountInfo(List<Integer> ids);

	/**
	 * 根据条件删除账目信息
	 * @param column primaryKey column name
	 * @param targets
	 */
	int deleteByfilter(String column, List<Integer> targets);

	/**
	 * 根据主键查询账目信息
	 * @param id
	 * @return
	 */
	AccountInfo selectById(int id);

	/**
	 * 根据采购信息，添加账目信息
	 * @param purchaseInfo
	 * @return
	 */
	int insertByPurchase(PurchaseInfo purchaseInfo);

}
