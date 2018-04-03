package com.wuyongfa.yifa.financialsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wuyongfa.yifa.financialsystem.model.AccountInfo;
import com.wuyongfa.yifa.financialsystem.page.Page;
import com.wuyongfa.yifa.financialsystem.response.Response.Response;
import com.wuyongfa.yifa.financialsystem.service.AccountService;
import com.wuyongfa.yifa.financialsystem.utils.BatchObject;

/**
 * 账目信息管理层
 *	
 * @author wuyongfa@chinasoftinc.com
 * 2017年6月19日
 */
@RestController
@RequestMapping(value="/v1/fm")
public class AccountController {
	@Autowired
	private AccountService accountService;

	/**
	 * 分页条件查询
	 * @param page
	 * @param AccountInfoName
	 * @return
	 */
	@RequestMapping(value="/AccountInfo",method=RequestMethod.GET)
	public Response selectAccountInfo(Page page,@RequestParam(value = "condition",required=false) String condition){
		Page.threadLocal.set(page);
		page = accountService.selectAccountInfo(page,condition);

		Page.threadLocal.remove();
		return new Response().success(page);
	}

	/**
	 * 添加账目信息
	 * @param accountInfo
	 * @return
	 */
	@RequestMapping(value="/AccountInfo",method=RequestMethod.POST)
	public Response addAccountInfo(@RequestBody AccountInfo accountInfo){
		String result = "fail";
		int n = accountService.addAccountInfo(accountInfo);
		if (n>0) {
			result = "success";
		}
		return new Response().success(result);
	}

	/**
	 * 修改账目信息
	 * @param accountInfo
	 * @return
	 */
	@RequestMapping(value="/AccountInfo/{id}",method=RequestMethod.POST)
	public Response updateAccountInfo(@RequestBody AccountInfo accountInfo,@PathVariable(value = "id",required=true) int id){
		String result = "fail";
		int n = accountService.updateAccountInfo(accountInfo);
		if (n>0) {
			result = "success";
		}
		return new Response().success(result);
	}
	
	/**
	 * 根据id查询账目信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/AccountInfo/{id}",method=RequestMethod.GET)
	public Response selectById(@PathVariable(value = "id",required=true) int id){
		
		AccountInfo accountInfo = accountService.selectById(id);
		
		return new Response().success(accountInfo);
	}
	
	/**
	 * 删除账目信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/AccountInfo",method=RequestMethod.DELETE)
	public Response deleteAccountInfo(@RequestBody BatchObject<Integer> temps){
		String result = "fail";
		int n = accountService.deleteAccountInfo(temps.getTargets());
		if (n>0) {
			result = "success";
		}
		return new Response().success(result);
	}
}
