package com.wuyongfa.yifa.financialsystem.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wuyongfa.yifa.financialsystem.dao.EmployeeInfoMapper;
import com.wuyongfa.yifa.financialsystem.model.EmployeeInfo;
import com.wuyongfa.yifa.financialsystem.page.Page;
import com.wuyongfa.yifa.financialsystem.service.EmployeeService;
import com.wuyongfa.yifa.financialsystem.utils.DateUtils;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeInfoMapper emInfoMapper;

	@Override
	public Page selectEmployee(Page page, EmployeeInfo employeeInfo) {
		
		page.setData(emInfoMapper.selectAllByPage(employeeInfo));
		return page;
	}

	@Override
	public int addEmployee(EmployeeInfo employeeInfo) {
		
		employeeInfo.setAge(DateUtils.getAge(employeeInfo.getBirthday()));
		employeeInfo.setCreatedAt(new Date());
		employeeInfo.setUpdatedAt(new Date());
		employeeInfo.setDeleteFlag("0");
		int n = emInfoMapper.insertSelective(employeeInfo);
		return n;
	}

	@Override
	public int updateEmployee(EmployeeInfo employeeInfo) {
		employeeInfo.setUpdatedAt(new Date());
		return emInfoMapper.updateByPrimaryKeySelective(employeeInfo);
	}

	@Override
	public int deleteEmployee(List<Integer> targets) {
		return emInfoMapper.deleteEmployee(targets);
	}

	@Override
	public EmployeeInfo getEmployeeByid(int id) {		
		return emInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<EmployeeInfo> selectAll() {
		
		return emInfoMapper.selectAll();
	}

}
