package com.wuyongfa.yifa.financialsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wuyongfa.yifa.financialsystem.model.ConsumptionInfo;
import com.wuyongfa.yifa.financialsystem.page.Page;
import com.wuyongfa.yifa.financialsystem.response.Response.Response;
import com.wuyongfa.yifa.financialsystem.service.ConsumptionService;
import com.wuyongfa.yifa.financialsystem.utils.BatchObject;

/**
 * 资源消费管理层
 *	
 * @author wuyongfa@chinasoftinc.com
 * 2017年6月19日
 */
@RestController
@RequestMapping(value="/v1/fm")
public class ConsumptionController {
	
	@Autowired
	private ConsumptionService consumptionService;

	
	/**
	 * 分页条件查询消费信息
	 * @param page
	 * @param ConsumptionInfo
	 * @return
	 */
	@RequestMapping(value="/consumptionInfo",method=RequestMethod.GET)
	public Response selectConsumptionInfo(Page page,@RequestParam(value="startTime",required = false) String startTime,
			@RequestParam(value="endTime",required = false) String endTime,
			@RequestParam(value="employeeName",required = false) String employeeName){
		Page.threadLocal.set(page);
		page = consumptionService.selectConsumptionInfo(page,startTime,endTime,employeeName);

		Page.threadLocal.remove();
		return new Response().success(page);
	}

	/**
	 * 添加消费信息
	 * @param ConsumptionInfo
	 * @return
	 */
	@RequestMapping(value="/consumptionInfo",method=RequestMethod.POST)
	public Response addConsumptionInfo(@RequestBody ConsumptionInfo ConsumptionInfo){
		String result = "fail";
		int n = consumptionService.addConsumptionInfo(ConsumptionInfo);
		if (n>0) {
			result = "success";
		}
		return new Response().success(result);
	}

	/**
	 * 修改资源消费信息
	 * @param ConsumptionInfo
	 * @return
	 */
	@RequestMapping(value="/consumptionInfo/{id}",method=RequestMethod.POST)
	public Response updateConsumptionInfo(@RequestBody ConsumptionInfo ConsumptionInfo,@PathVariable("id") int id){
		String result = "fail";
		int n = consumptionService.updateConsumptionInfo(ConsumptionInfo);
		if (n>0) {
			result = "success";
		}
		return new Response().success(result);
	}
	
	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/consumptionInfo/{id}",method=RequestMethod.GET)
	public Response selectByKey(@PathVariable("id") int id){
		ConsumptionInfo consumptionInfo = consumptionService.selectByKey(id);
		return new Response().success(consumptionInfo);
	}
	
	/**
	 * 删除消费信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/consumptionInfo",method=RequestMethod.DELETE)
	public Response deleteConsumptionInfo(@RequestBody BatchObject<Integer> temps){
		String result = "fail";
		int n = consumptionService.deleteConsumptionInfo(temps.getTargets());
		if (n>0) {
			result = "success";
		}
		return new Response().success(result);
	}
}
