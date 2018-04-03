package com.wuyongfa.yifa.financialsystem.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wuyongfa.yifa.financialsystem.model.OrderInfo;
import com.wuyongfa.yifa.financialsystem.page.Page;
import com.wuyongfa.yifa.financialsystem.response.Response.Response;
import com.wuyongfa.yifa.financialsystem.service.OrderService;
import com.wuyongfa.yifa.financialsystem.utils.BatchObject;

/**
 * 订单控制层
 *	
 * @author wuyongfa@chinasoftinc.com
 * 2017年6月19日
 */
@RestController
@RequestMapping(value="/v1/fm")
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	/**
	 * 分页条件查询订单信息
	 * @param page
	 * @param orderInfo
	 * @return
	 */
	@RequestMapping(value="/orderInfo",method=RequestMethod.GET)
	public Response selectOrderInfo(Page page,
			@RequestParam(value="orderCode",required = false) String orderCode,
			@RequestParam(value="startTime",required = false) String startTime,
			@RequestParam(value="endTime",required = false) String endTime){
		Page.threadLocal.set(page);
		page = orderService.selectOrderInfo(page,orderCode,startTime,endTime);

		Page.threadLocal.remove();
		return new Response().success(page);
	}

	/**
	 * 添加订单信息
	 * @param OrderInfo
	 * @return
	 */
	@RequestMapping(value="/orderInfo",method=RequestMethod.POST)
	public Response addOrderInfo(@RequestBody OrderInfo orderInfo){
		String result = "fail";
		
		int n = orderService.addOrderInfo(orderInfo);
		if (n>0) {
			result = "success";
		}
		return new Response().success(result);
	}

	/**
	 * 修改订单信息
	 * @param OrderInfoInfo
	 * @return
	 */
	@RequestMapping(value="/orderInfo/{id}",method=RequestMethod.POST)
	public Response updateOrderInfo(@RequestBody OrderInfo orderInfo,@PathVariable("id") int id){
		String result = "fail";
		int n = orderService.updateOrderInfo(orderInfo);
		if (n>0) {
			result = "success";
		}
		return new Response().success(result);
	}
	
	@RequestMapping(value="/orderInfo/{id}",method=RequestMethod.GET)
	public Response selectByCode(@PathVariable("id") int id){
		
		OrderInfo orderInfo = orderService.selectByCode(id);
		
		return new Response().success(orderInfo);
	}
	
	/**
	 * 删除订单信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/orderInfo",method=RequestMethod.DELETE)
	public Response deleteOrderInfo(@RequestBody BatchObject<Integer> temps){
		String result = "fail";
		int n = orderService.deleteOrderInfo(temps.getTargets());
		if (n>0) {
			result = "success";
		}
		return new Response().success(result);
	}
	
	/**
	 * 完结订单
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/orderInfo/{id}/endOrder",method=RequestMethod.POST)
	public Response endOrder(@PathVariable int id){
		String result = "fail";
		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setId(id);
		orderInfo.setEndTime(new Date());
		orderInfo.setStatus("02");
		int n = orderService.updateOrderInfo(orderInfo);
		if (n>0) {
			result = "success";
		}
		return new Response().success(result);
	}
}
