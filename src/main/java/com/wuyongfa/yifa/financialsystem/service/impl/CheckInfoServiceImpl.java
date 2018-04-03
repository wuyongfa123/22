package com.wuyongfa.yifa.financialsystem.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wuyongfa.yifa.financialsystem.dao.CheckInfoMapper;
import com.wuyongfa.yifa.financialsystem.dao.EmployeeInfoMapper;
import com.wuyongfa.yifa.financialsystem.model.CheckInfo;
import com.wuyongfa.yifa.financialsystem.model.EmployeeInfo;
import com.wuyongfa.yifa.financialsystem.page.Page;
import com.wuyongfa.yifa.financialsystem.utils.CheckInfoService;

@Service
public class CheckInfoServiceImpl implements CheckInfoService {
	
	private static final Logger logger = Logger.getLogger(CheckInfoServiceImpl.class);
	
	@Autowired
	private CheckInfoMapper checkInfoMapper;
	
	@Autowired
	private EmployeeInfoMapper employeeInfoMapper;

	@Override
	public Page selectCheckInfo(Page page, CheckInfo checkInfo) {

		String employeeName = StringUtils.isEmpty(checkInfo.getEmployeeName())?null:checkInfo.getEmployeeName();

		page.setData(checkInfoMapper.selectAllByPage(employeeName));
		return page;
	}

	@Override
	public int addCheckInfo(CheckInfo checkInfo) {
		//补充数据
		EmployeeInfo employeeInfo = employeeInfoMapper.selectByPrimaryKey(checkInfo.getEmployeeId());
		
		if (employeeInfo == null) {
			logger.debug("未查到当前员工信息");
			return 0;
		}
		checkInfo.setEmployeeName(employeeInfo.getName());//员工姓名
		
		checkInfo.setCreatedAt(new Date());
		checkInfo.setUpdatedAt(new Date());
		checkInfo.setDeleteFlag("0");
		
		switch (checkInfo.getCheckType()) {//01、迟到；02、旷工；03、早退；04、请假
		case "01":
			checkInfo.setCheckTypeName("迟到");
			break;
		case "02":
			checkInfo.setCheckTypeName("旷工");
			break;
		case "03":
			checkInfo.setCheckTypeName("早退");
			break;
		case "04":
			checkInfo.setCheckTypeName("请假");
			break;
		case "05":
			checkInfo.setCheckTypeName("其他");
			break;
		}

		return checkInfoMapper.insertSelective(checkInfo);
	}

	@Override
	public int updateCheckInfo(CheckInfo checkInfo) {
		checkInfo.setUpdatedAt(new Date());
		switch (checkInfo.getCheckType()) {//01、迟到；02、旷工；03、早退；04、请假
		case "01":
			checkInfo.setCheckTypeName("迟到");
			break;
		case "02":
			checkInfo.setCheckTypeName("旷工");
			break;
		case "03":
			checkInfo.setCheckTypeName("早退");
			break;
		case "04":
			checkInfo.setCheckTypeName("请假");
			break;
		case "05":
			checkInfo.setCheckTypeName("其他");
			break;
		}

		
		return checkInfoMapper.updateByPrimaryKeySelective(checkInfo);
	}

	@Override
	public int deleteCheckInfo(List<Integer> ids) {

		return checkInfoMapper.deleteCheckInfo(ids);
	}

	@Override
	public CheckInfo selectCheckInfoById(int id) {
		
		return checkInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<CheckInfo> selectCheckInfo(Integer employeeId, Date startTime,
			Date endTime) {
		return checkInfoMapper.selectByTime(employeeId,startTime,endTime);
	}
}
