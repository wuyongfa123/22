package com.wuyongfa.yifa.financialsystem.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wuyongfa.yifa.financialsystem.dao.PurchaseInfoMapper;
import com.wuyongfa.yifa.financialsystem.model.PurchaseInfo;
import com.wuyongfa.yifa.financialsystem.page.Page;
import com.wuyongfa.yifa.financialsystem.service.AccountService;
import com.wuyongfa.yifa.financialsystem.service.PurchaseService;
import com.wuyongfa.yifa.financialsystem.utils.DateUtils;

@Service
@Transactional
public class PurchaseServiceImpl implements PurchaseService {
	@Autowired
	private PurchaseInfoMapper purchaseInfoMapper;
	
	@Autowired
	private AccountService accountService;

	@Override
	public Page selectPurchaseInfo(Page page,String startTime, String endTime) {

		page.setData(purchaseInfoMapper.selectAllByPage(DateUtils.parse2(startTime),DateUtils.parse2(endTime)));
		return page;
	}

	@Override
	public int addPurchaseInfo(PurchaseInfo purchaseInfo) {
		purchaseInfo.setCreatedAt(new Date());
		purchaseInfo.setUpdatedAt(new Date());
		purchaseInfo.setDeleteFlag("0");

		switch (purchaseInfo.getPurchaseTypeCode()) {
		case "01":
			purchaseInfo.setPurchaseTypeName("设备");
			break;
		case "02":
			purchaseInfo.setPurchaseTypeName("配件");
			break;
		case "03":
			purchaseInfo.setPurchaseTypeName("刀具");
			break;
		case "04":
			purchaseInfo.setPurchaseTypeName("日常");
			break;

		default:
			purchaseInfo.setPurchaseTypeName("其他");
			break;
		}
		//插入采购表
		int n = purchaseInfoMapper.insertSelective(purchaseInfo);
		//插入账目表
		accountService.insertByPurchase(purchaseInfo);
		
		
		return n;
	}

	@Override
	public int updatePurchaseInfo(PurchaseInfo purchaseInfo) {
		purchaseInfo.setUpdatedAt(new Date());
		return purchaseInfoMapper.updateByPrimaryKeySelective(purchaseInfo);
	}

	@Override
	public int deletePurchaseInfo(List<Integer> ids) {

		return purchaseInfoMapper.deletePurchaseInfos(ids);
	}

	@Override
	public PurchaseInfo selectById(int id) {

		return purchaseInfoMapper.selectByPrimaryKey(id);
	}
}
