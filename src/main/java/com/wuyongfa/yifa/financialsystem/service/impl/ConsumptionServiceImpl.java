package com.wuyongfa.yifa.financialsystem.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wuyongfa.yifa.financialsystem.dao.ConsumptionInfoMapper;
import com.wuyongfa.yifa.financialsystem.dao.EmployeeInfoMapper;
import com.wuyongfa.yifa.financialsystem.model.ConsumptionInfo;
import com.wuyongfa.yifa.financialsystem.model.EmployeeInfo;
import com.wuyongfa.yifa.financialsystem.page.Page;
import com.wuyongfa.yifa.financialsystem.service.ConsumptionService;
import com.wuyongfa.yifa.financialsystem.utils.DateUtils;

@Service
public class ConsumptionServiceImpl implements ConsumptionService{

	@Autowired
	private ConsumptionInfoMapper consumptionInfoMapper;
	@Autowired
	private EmployeeInfoMapper employeeInfoMapper;
	@Override
	public Page selectConsumptionInfo(Page page, String startTime, String endTime, String employeeName){
		page.setData(consumptionInfoMapper.selectAllByPage(employeeName,DateUtils.parse2(startTime),DateUtils.parse2(endTime)));
		
		return page;
	}

	@Override
	public int addConsumptionInfo(ConsumptionInfo consumptionInfo) {
		
		EmployeeInfo employeeInfo = employeeInfoMapper.selectByPrimaryKey(consumptionInfo.getEmployeeId());
		
		consumptionInfo.setEmployeeName(employeeInfo.getName());
		switch (consumptionInfo.getPurchaseTypeCode()) {
		case "01":
			consumptionInfo.setPurchaseTypeName("设备");
			break;
		case "02":
			consumptionInfo.setPurchaseTypeName("配件");
			break;
		case "03":
			consumptionInfo.setPurchaseTypeName("刀具");
			break;
		case "04":
			consumptionInfo.setPurchaseTypeName("日常");
			break;

		default:
			consumptionInfo.setPurchaseTypeName("其他");
			break;
		}
		
		consumptionInfo.setCreatedAt(new Date());
		consumptionInfo.setUpdatedAt(new Date());
		consumptionInfo.setDeleteFlag("0");
		return consumptionInfoMapper.insertSelective(consumptionInfo);
	}

	@Override
	public int updateConsumptionInfo(ConsumptionInfo consumptionInfo) {
		switch (consumptionInfo.getPurchaseTypeCode()) {
		case "01":
			consumptionInfo.setPurchaseTypeName("设备");
			break;
		case "02":
			consumptionInfo.setPurchaseTypeName("配件");
			break;
		case "03":
			consumptionInfo.setPurchaseTypeName("刀具");
			break;
		case "04":
			consumptionInfo.setPurchaseTypeName("日常");
			break;

		default:
			consumptionInfo.setPurchaseTypeName("其他");
			break;
		}
		consumptionInfo.setUpdatedAt(new Date());
		return consumptionInfoMapper.updateByPrimaryKeySelective(consumptionInfo);
	}

	@Override
	public int deleteConsumptionInfo(List<Integer> ids) {
		
		return consumptionInfoMapper.deleteConsumptionInfo(ids);
	}

	@Override
	public ConsumptionInfo selectByKey(int id) {
		
		return consumptionInfoMapper.selectByPrimaryKey(id);
	}

}
