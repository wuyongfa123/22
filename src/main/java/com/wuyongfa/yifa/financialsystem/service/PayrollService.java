package com.wuyongfa.yifa.financialsystem.service;

import java.util.List;

import com.wuyongfa.yifa.financialsystem.model.EmployeeInfo;
import com.wuyongfa.yifa.financialsystem.model.PayrollInfo;
import com.wuyongfa.yifa.financialsystem.page.Page;

/**
 * 工资发放服务层
 *	
 * @author wuyongfa@chinasoftinc.com
 * 2017年6月14日
 */
public interface PayrollService {

	/**
	 * 查询工资发放记录
	 * @param page
	 * @param employeeName
	 * @return
	 */
	Page selectPayroll(Page page, String employeeName);

	/**
	 * 批量录入工资发放信息
	 * @param payrollInfos
	 * @return
	 */
	int addPayrolls(List<PayrollInfo> payrollInfos);

	/**
	 * 批量删除工资发放记录
	 * @param targets
	 * @return
	 */
	int batchDelete(List<Integer> targets);

	/**
	 * 更新工资发放信息
	 * @param payrollInfo
	 * @return
	 */
	int updatePayroll(PayrollInfo payrollInfo);

	/**
	 * 计算员工工资信息
	 * @param employeeId
	 * @param month
	 * @return
	 */
	PayrollInfo calculationPayroll(Integer employeeId, String month);

	/**
	 * 单挑插入工资信息
	 * @param payrollInfo
	 * @return
	 */
	int addPayroll(PayrollInfo payrollInfo);

	/**
	 * 根据主键查询记录
	 * @param id
	 * @return
	 */
	PayrollInfo selectById(Integer id);

	/**
	 * 查询指定月没有发放工资的员工
	 * @param month
	 * @return
	 */
	List<EmployeeInfo> selectEmployeeWithoutPay(String month);

}
