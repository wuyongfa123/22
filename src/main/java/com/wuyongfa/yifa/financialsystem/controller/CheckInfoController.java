package com.wuyongfa.yifa.financialsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wuyongfa.yifa.financialsystem.model.CheckInfo;
import com.wuyongfa.yifa.financialsystem.page.Page;
import com.wuyongfa.yifa.financialsystem.response.Response.Response;
import com.wuyongfa.yifa.financialsystem.utils.BatchObject;
import com.wuyongfa.yifa.financialsystem.utils.CheckInfoService;

/**
 * 员工考勤管理层
 *	
 * @author wuyongfa@chinasoftinc.com
 * 2017年6月15日
 */
@RestController
@RequestMapping(value="/v1/fm")
public class CheckInfoController {
	@Autowired
	private CheckInfoService checkInfoService;
	
	
	/**
	 * 分页条件查询
	 * @param page
	 * @param checkInfo
	 * @return
	 */
	@RequestMapping(value="/checkInfo",method=RequestMethod.GET)
	public Response selectCheckInfo(Page page,CheckInfo checkInfo){
		Page.threadLocal.set(page);
		page = checkInfoService.selectCheckInfo(page,checkInfo);

		Page.threadLocal.remove();
		return new Response().success(page);
	}

	/**
	 * 添加员工考勤信息
	 * @param checkInfo
	 * @return
	 */
	@RequestMapping(value="/checkInfo",method=RequestMethod.POST)
	public Response addCheckInfo(@RequestBody CheckInfo checkInfo){
		String result = "fail";
		int n = checkInfoService.addCheckInfo(checkInfo);
		if (n>0) {
			result = "success";
		}
		return new Response().success(result);
	}

	/**
	 * 修改员工考勤信息
	 * @param checkInfo
	 * @return
	 */
	@RequestMapping(value="/checkInfo/{id}",method=RequestMethod.POST)
	public Response updateCheckInfo(@RequestBody CheckInfo checkInfo,@PathVariable("id") int id){
		String result = "fail";
		int n = checkInfoService.updateCheckInfo(checkInfo);
		if (n>0) {
			result = "success";
		}
		return new Response().success(result);
	}
	/**
	 * 根据主键查询考勤信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/checkInfo/{id}",method=RequestMethod.GET)
	public Response selectCheckInfoById(@PathVariable("id") int id){
		CheckInfo checkInfo = checkInfoService.selectCheckInfoById(id);
		return new Response().success(checkInfo);
	}
	
	/**
	 * 删除员工考勤信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/checkInfo",method=RequestMethod.DELETE)
	public Response deleteCheckInfo(@RequestBody BatchObject<Integer> temps){
		String result = "fail";
		int n = checkInfoService.deleteCheckInfo(temps.getTargets());
		if (n>0) {
			result = "success";
		}
		return new Response().success(result);
	}
}
