package com.wuyongfa.yifa.financialsystem.service;

import java.util.List;

import com.wuyongfa.yifa.financialsystem.model.EmployeeInfo;
import com.wuyongfa.yifa.financialsystem.page.Page;

/**
 * 员工信息service层
 *	
 * @author wuyongfa@chinasoftinc.com
 * 2017年6月6日
 */
public interface EmployeeService {

	/**
	 * 查询员工信息
	 * @return
	 */
	Page selectEmployee(Page page, EmployeeInfo employeeInfo);

	/**
	 * 向数据库中插入数据
	 * @param employeeInfo
	 */
	int addEmployee(EmployeeInfo employeeInfo);

	/**
	 * 修改员工信息
	 * @param employeeInfo
	 * @return
	 */
	int updateEmployee(EmployeeInfo employeeInfo);

	/**
	 * 删除员工信息
	 * @param targets
	 * @return
	 */
	int deleteEmployee(List<Integer> targets);

	/**
	 * 根据主键查询员工信息
	 * @param id
	 * @return
	 */
	EmployeeInfo getEmployeeByid(int id);

	/**
	 * 查询所有员工信息
	 * @return
	 */
	List<EmployeeInfo> selectAll();

}
