package com.wuyongfa.yifa.financialsystem.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wuyongfa.yifa.financialsystem.dao.AccountInfoMapper;
import com.wuyongfa.yifa.financialsystem.dao.EmployeeInfoMapper;
import com.wuyongfa.yifa.financialsystem.model.AccountInfo;
import com.wuyongfa.yifa.financialsystem.model.EmployeeInfo;
import com.wuyongfa.yifa.financialsystem.model.PurchaseInfo;
import com.wuyongfa.yifa.financialsystem.page.Page;
import com.wuyongfa.yifa.financialsystem.service.AccountService;
@Service
@Transactional
public class AccountServiceImpl implements AccountService{

	@Autowired
	private AccountInfoMapper accountInfoMapper;

	@Autowired
	private EmployeeInfoMapper employeeInfoMapper;

	@Override
	public Page selectAccountInfo(Page page, String condition) {
		page.setData(accountInfoMapper.selectAllByPage(condition));
		return page;
	}

	@Override
	public int addAccountInfo(AccountInfo accountInfo) {

		EmployeeInfo employeeInfo = employeeInfoMapper.selectByPrimaryKey(accountInfo.getEmployeeId());

		//封装数据
		accountInfo.setAccountTypeName("01".equals(accountInfo.getAccountTypeCode())?"收入":"支出");
		accountInfo.setEmployeeName(employeeInfo.getName());
		accountInfo.setCreatedAt(new Date());
		accountInfo.setUpdatedAt(new Date());
		accountInfo.setDeleteFlag("0");
		return accountInfoMapper.insertSelective(accountInfo);
	}

	@Override
	public int updateAccountInfo(AccountInfo accountInfo) {
		EmployeeInfo employeeInfo = employeeInfoMapper.selectByPrimaryKey(accountInfo.getEmployeeId());
		//封装数据
		accountInfo.setAccountTypeName("01".equals(accountInfo.getAccountTypeCode())?"收入":"支出");
		accountInfo.setEmployeeName(employeeInfo.getName());
		accountInfo.setUpdatedAt(new Date());
		return accountInfoMapper.updateByPrimaryKeySelective(accountInfo);
	}

	@Override
	public int deleteAccountInfo(List<Integer> ids) {

		return this.deleteByfilter("id", ids);
	}

	@Override
	public int deleteByfilter(String column, List<Integer> targets) {
		Map<String, List<Integer>> mapFilters = new HashMap<>();
		mapFilters.put(column, targets);

		return accountInfoMapper.deleteAccountInfo(mapFilters);
	}

	@Override
	public AccountInfo selectById(int id) {

		return accountInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public int insertByPurchase(PurchaseInfo purchaseInfo) {
		AccountInfo accountInfo = new AccountInfo();
		accountInfo.setAccountTypeCode("02");
		accountInfo.setAccountTypeName("支出");
		accountInfo.setMoney(purchaseInfo.getTotalPrice());
		accountInfo.setTarget(purchaseInfo.getPurchaseTypeName());
		accountInfo.setRemark(purchaseInfo.getRemark());
		accountInfo.setOperateTime(purchaseInfo.getOperateTime());
		accountInfo.setCreatedAt(new Date());
		accountInfo.setUpdatedAt(new Date());
		accountInfo.setDeleteFlag("0");
		
		return accountInfoMapper.insertSelective(accountInfo);
	}

}
