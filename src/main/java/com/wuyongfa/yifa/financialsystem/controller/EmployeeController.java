package com.wuyongfa.yifa.financialsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wuyongfa.yifa.financialsystem.model.EmployeeInfo;
import com.wuyongfa.yifa.financialsystem.page.Page;
import com.wuyongfa.yifa.financialsystem.response.Response.Response;
import com.wuyongfa.yifa.financialsystem.service.EmployeeService;
import com.wuyongfa.yifa.financialsystem.utils.BatchObject;

/**
 * 员工信息管理控制层
 *	
 * @author wuyongfa@chinasoftinc.com
 * 2017年6月6日
 */
@RestController
@RequestMapping(value="/v1/fm")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	/**
	 * 分页条件查询
	 * @param page
	 * @param employeeName
	 * @return
	 */
	@RequestMapping(value="/employee",method=RequestMethod.GET)
	public Response selectEmployee(Page page,EmployeeInfo employeeInfo){
		Page.threadLocal.set(page);
		page = employeeService.selectEmployee(page,employeeInfo);

		Page.threadLocal.remove();
		return new Response().success(page);
	}

	/**
	 * 添加员工信息
	 * @param employeeInfo
	 * @return
	 */
	@RequestMapping(value="/employee",method=RequestMethod.POST)
	public Response addEmployee(@RequestBody EmployeeInfo employeeInfo){
		String result = "fail";
		int n = employeeService.addEmployee(employeeInfo);
		if (n>0) {
			result = "success";
		}
		return new Response().success(result);
	}

	/**
	 * 修改员工信息
	 * @param employeeInfo
	 * @return
	 */
	@RequestMapping(value="/employee/{id}",method=RequestMethod.POST)
	public Response updateEmployee(@RequestBody EmployeeInfo employeeInfo,@PathVariable("id") int id){
		String result = "fail";
		int n = employeeService.updateEmployee(employeeInfo);
		if (n>0) {
			result = "success";
		}
		return new Response().success(result);
	}
	/**
	 * 根据id查询员工信息
	 * @param employeeInfo
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/employee/{id}",method=RequestMethod.GET)
	public Response getEmployeeByid(@PathVariable("id") Integer id){
		EmployeeInfo employeeInfo = employeeService.getEmployeeByid(id);
		return new Response().success(employeeInfo);
	}
	/**
	 * 删除员工信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/employee",method=RequestMethod.DELETE)
	public Response deleteEmployee(@RequestBody BatchObject<Integer> temps){
		String result = "fail";
		int n = employeeService.deleteEmployee(temps.getTargets());
		if (n>0) {
			result = "success";
		}
		return new Response().success(result);
	}
	
	/**
	 * 查询所有用户信息
	 * @return
	 */
	@RequestMapping(value="/employee/all")
	public Response selectAll(){
		List<EmployeeInfo> employeeInfos = employeeService.selectAll();
		return new Response().success(employeeInfos);
	}
}
