package com.wuyongfa.yifa.financialsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wuyongfa.yifa.financialsystem.model.EmployeeInfo;
import com.wuyongfa.yifa.financialsystem.model.PayrollInfo;
import com.wuyongfa.yifa.financialsystem.page.Page;
import com.wuyongfa.yifa.financialsystem.response.Response.Response;
import com.wuyongfa.yifa.financialsystem.service.PayrollService;
import com.wuyongfa.yifa.financialsystem.utils.BatchObject;

/**
 * 工资发放管理层
 *	
 * @author wuyongfa@chinasoftinc.com
 * 2017年6月14日
 */
@RestController
@RequestMapping(value="/v1/fm")
public class PayrollController {
	@Autowired
	private PayrollService payrollService;

	/**
	 * 分页条件查询
	 * @param page
	 * @param employeeName
	 * @return
	 */
	@RequestMapping(value="/payroll",method=RequestMethod.GET)
	public Response selectPayroll(Page page,@RequestParam(name="name",required=false) String employeeName){
		Page.threadLocal.set(page);
		page = payrollService.selectPayroll(page,employeeName);

		Page.threadLocal.remove();
		return new Response().success(page);
	}

	/**
	 * 批量录入工资发放信息
	 * @param payrollInfos
	 * @return
	 */
	/*@RequestMapping(value="/payroll/add",method=RequestMethod.POST)
	public Response addPayrolls(@RequestBody BatchObject<PayrollInfo> temps){

		int n = payrollService.addPayrolls(temps.getTargets());

		return new Response().success(n);
	}*/

	/**
	 * 单条录入工资信息
	 * @param temps
	 * @return
	 */
	@RequestMapping(value="/payroll/add",method=RequestMethod.POST)
	public Response addPayroll(@RequestBody PayrollInfo payrollInfo){

		String result = "fail";

		int n = payrollService.addPayroll(payrollInfo);

		if (n>0) {
			result = "success";
		}

		return new Response().success(result);
	}

	/**
	 * 批量逻辑删除工资发放记录
	 * @param temps
	 * @return
	 */
	@RequestMapping(value="/payroll",method=RequestMethod.DELETE)
	public Response batchDelete(@RequestBody BatchObject<Integer> temps){

		String result = "fail";
		int n = payrollService.batchDelete(temps.getTargets());

		if (n>0) {
			result = "success";
		}
		return new Response().success(result);
	}

	/**
	 * 更新单条工资发放信息
	 * @param payrollInfo
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/payroll/{id}",method=RequestMethod.POST)
	public Response updatePayroll(@RequestBody PayrollInfo payrollInfo ,@PathVariable("id") Integer id){
		payrollInfo.setId(id);
		String result = "fail";
		int n = payrollService.updatePayroll(payrollInfo);

		if (n>0) {
			result = "success";
		}
		return new Response().success(result);
	}

	/**
	 * 根据员工编码以及月份计算工资信息
	 * @param employeeId
	 * @param month
	 * @return
	 */
	@RequestMapping(value="/payroll/{employeeId}/{month}",method=RequestMethod.GET)
	public Response calculationPayroll(@PathVariable("employeeId") Integer employeeId,
			@PathVariable("month") String month){
		if (employeeId == null) {
			return new Response().success(null);
		}
		PayrollInfo payrollInfo = payrollService.calculationPayroll(employeeId,month);

		return new Response().success(payrollInfo);
	}
	/**
	 * 根据主键查询记录
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/payroll/{id}",method=RequestMethod.GET)
	public Response selectById(@PathVariable("id") Integer id){
		PayrollInfo payrollInfo = payrollService.selectById(id);

		return new Response().success(payrollInfo);
	}

	/**
	 * 查询指定月没有发工资的员工
	 * @param month
	 * @return
	 */
	@RequestMapping(value="/payroll/employee/{month}",method=RequestMethod.GET)
	public Response selectEmployeeWithoutPay(@PathVariable("month") String month){
		
		List<EmployeeInfo> employeeInfos = payrollService.selectEmployeeWithoutPay(month);
		
		return new Response().success(employeeInfos);
	}
	
}
