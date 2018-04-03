package com.wuyongfa.yifa.financialsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wuyongfa.yifa.financialsystem.model.PurchaseInfo;
import com.wuyongfa.yifa.financialsystem.page.Page;
import com.wuyongfa.yifa.financialsystem.response.Response.Response;
import com.wuyongfa.yifa.financialsystem.service.PurchaseService;
import com.wuyongfa.yifa.financialsystem.utils.BatchObject;

/**
 * 资源采购管理层
 *	
 * @author wuyongfa@chinasoftinc.com
 * 2017年6月19日
 */
@RestController
@RequestMapping(value="/v1/fm")
public class PurchaseController {
	
	@Autowired
	private PurchaseService purchaseService;
	
	/**
	 * 分页条件查询采购信息
	 * @param page
	 * @param purchaseInfo
	 * @return
	 */
	@RequestMapping(value="/purchaseInfo",method=RequestMethod.GET)
	public Response selectPurchaseInfo(Page page,@RequestParam(value="startTime",required = false) String startTime,
			@RequestParam(value="endTime",required = false) String endTime){
		Page.threadLocal.set(page);
		page = purchaseService.selectPurchaseInfo(page,startTime,endTime);

		Page.threadLocal.remove();
		return new Response().success(page);
	}

	/**
	 * 添加采购信息
	 * @param PurchaseInfo
	 * @return
	 */
	@RequestMapping(value="/purchaseInfo",method=RequestMethod.POST)
	public Response addPurchaseInfo(@RequestBody PurchaseInfo purchaseInfo){
		String result = "fail";
		int n = purchaseService.addPurchaseInfo(purchaseInfo);
		if (n>0) {
			result = "success";
		}
		return new Response().success(result);
	}

	/**
	 * 修改资源采购信息
	 * @param PurchaseInfo
	 * @return
	 */
	@RequestMapping(value="/purchaseInfo/{id}",method=RequestMethod.POST)
	public Response updatePurchaseInfo(@RequestBody PurchaseInfo purchaseInfo,@PathVariable("id") int id){
		String result = "fail";
		int n = purchaseService.updatePurchaseInfo(purchaseInfo);
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
	@RequestMapping(value="/purchaseInfo/{id}",method=RequestMethod.GET)
	public Response selectById(@PathVariable("id") int id){
		
		PurchaseInfo purchaseInfo = purchaseService.selectById(id);
		
		return new Response().success(purchaseInfo);
	}
	/**
	 * 删除采购信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/purchaseInfo",method=RequestMethod.DELETE)
	public Response deletePurchaseInfo(@RequestBody BatchObject<Integer> temps){
		String result = "fail";
		int n = purchaseService.deletePurchaseInfo(temps.getTargets());
		if (n>0) {
			result = "success";
		}
		return new Response().success(result);
	}
}
