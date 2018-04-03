package com.wuyongfa.yifa.financialsystem.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wuyongfa.yifa.financialsystem.dao.EmployeeInfoMapper;
import com.wuyongfa.yifa.financialsystem.dao.PayrollInfoMapper;
import com.wuyongfa.yifa.financialsystem.model.AccountInfo;
import com.wuyongfa.yifa.financialsystem.model.CheckInfo;
import com.wuyongfa.yifa.financialsystem.model.EmployeeInfo;
import com.wuyongfa.yifa.financialsystem.model.PayrollInfo;
import com.wuyongfa.yifa.financialsystem.page.Page;
import com.wuyongfa.yifa.financialsystem.service.AccountService;
import com.wuyongfa.yifa.financialsystem.service.PayrollService;
import com.wuyongfa.yifa.financialsystem.utils.CheckInfoService;
import com.wuyongfa.yifa.financialsystem.utils.DateUtils;

@Service
@Transactional
public class PayrollServiceImpl implements PayrollService{
	@Autowired
	private PayrollInfoMapper payrollInfoMapper;

	@Autowired
	private EmployeeInfoMapper employeeInfoMapper;
	
	@Autowired
	private CheckInfoService checkInfoService;
	
	@Autowired
	private AccountService accountService;

	@Override
	public Page selectPayroll(Page page, String employeeName) {

		page.setData(payrollInfoMapper.selectAllByPage(employeeName));
		return page;
	}

	@Override
	public int addPayrolls(List<PayrollInfo> payrollInfos) {
		
		if (payrollInfos.size()>0) {
			return payrollInfoMapper.addPayrolls(payrollInfos);
		}else{
			return 0;
		}
	}

	@Override
	public int batchDelete(List<Integer> targets) {
		int n = 0;
		if (targets.size()>0) {
			n = payrollInfoMapper.batchDelete(targets);
			accountService.deleteByfilter("payroll_id",targets);
		}
		return n;
	}

	@Override
	public int updatePayroll(PayrollInfo payrollInfo) {
		payrollInfo.setUpdatedAt(new Date());
		
		return payrollInfoMapper.updateByPrimaryKeySelective(payrollInfo);
	}

	@Override
	public PayrollInfo calculationPayroll(Integer employeeId, String month) {
	
		EmployeeInfo employeeInfo = employeeInfoMapper.selectByPrimaryKey(employeeId);
		if (employeeInfo == null) {
			return null;
		}
		
		Date startTime = DateUtils.getMinMonthDate(DateUtils.parse1(month));
		Date endTime = DateUtils.getPerFirstDayOfMonth(DateUtils.parse1(month));
		List<CheckInfo> checkInfos = checkInfoService.selectCheckInfo(employeeId, startTime, endTime);
		
		//计算请假天数和旷工天数
		Double leaveDay = 0.0;//请假天数
		Double absenteeism = 0.0;//旷工天数
		for (CheckInfo checkInfo : checkInfos) {
			switch (checkInfo.getCheckType()) {
			case "04"://请假
				leaveDay += checkInfo.getDays();
				break;

			default://旷工
				absenteeism += checkInfo.getDays();
				break;
			}
		}
		
		/*
		 * 目前暂时不做工作日、出勤日、罚款规则等计算
		 */
		
		//封装工资信息
		PayrollInfo payrollInfo = new PayrollInfo();
		payrollInfo.setEmployeeId(employeeInfo.getId());
		payrollInfo.setEmployeeName(employeeInfo.getName());
		payrollInfo.setLeaveDay(leaveDay);
		payrollInfo.setAbsenteeism(absenteeism);
		payrollInfo.setRealWages(employeeInfo.getSalary());
		payrollInfo.setBasePay(employeeInfo.getSalary());
		payrollInfo.setMonth(month);
		
		return payrollInfo;
	}

	@Override
	public int addPayroll(PayrollInfo payrollInfo) {
		
		payrollInfo.setCreatedAt(new Date());
		payrollInfo.setUpdatedAt(new Date());
		payrollInfo.setDeleteFlag("0");
		int n = payrollInfoMapper.insertSelective(payrollInfo);
		
		/*
		 * 同步插入到消费记录中
		 */
		AccountInfo accountInfo = new AccountInfo();
		accountInfo.setTarget(payrollInfo.getEmployeeName());
		accountInfo.setAccountTypeCode("02");
		accountInfo.setAccountTypeName("支出");
		accountInfo.setMoney(payrollInfo.getRealWages());
		accountInfo.setOperateTime(payrollInfo.getPayDate());
		accountInfo.setEmployeeId(payrollInfo.getEmployeeId());
		accountInfo.setEmployeeName(payrollInfo.getEmployeeName());
		accountInfo.setRemark("工资");
		accountInfo.setPayrollId(payrollInfo.getId());
		
		accountService.addAccountInfo(accountInfo);
		
		return n;
	}

	@Override
	public PayrollInfo selectById(Integer id) {
		
		return payrollInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<EmployeeInfo> selectEmployeeWithoutPay(String month) {
		
		List<Integer> ids = payrollInfoMapper.selectEmployeeIdByMonth(month);
		
		List<EmployeeInfo> employeeInfos = employeeInfoMapper.selectOther(ids);
		
		return employeeInfos;
	}
}

