package com.wuyongfa.yifa.financialsystem.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wuyongfa.yifa.financialsystem.model.CncProcess;
import com.wuyongfa.yifa.financialsystem.page.Page;
import com.wuyongfa.yifa.financialsystem.response.Response.Response;
import com.wuyongfa.yifa.financialsystem.service.CncProcessService;
import com.wuyongfa.yifa.financialsystem.utils.BatchObject;
/**
 * CNC加工件管理控制层
 *	
 * @author wuyongfa@chinasoftinc.com
 * 2017年7月20日
 */
@RestController
@RequestMapping(value="/v1/fm")
public class CncProcessController {

	@Autowired
	private CncProcessService cncProcessService;

	/**
	 * 分页查询
	 * @param page
	 * @param orderCode 订单编码
	 */
	@RequestMapping(value = "cncProcess",method = RequestMethod.GET)
	public Response selectByPage(Page page,@RequestParam(value = "orderCode",required = true) String orderCode){

		Page.threadLocal.set(page);
		page = cncProcessService.selectByPage(page,orderCode);
		Page.threadLocal.remove();

		return new Response().success(page);
	}

	/**
	 * 批量插入cnc加工件
	 * @param cncProcesses
	 * @return
	 */
	@RequestMapping(value = "cncProcess/insert/{orderCode}",method = RequestMethod.POST)
	public Response addProcess(@RequestBody List<CncProcess> cncProcesses,@PathVariable(value="orderCode",required = true) String orderCode){
		String result = "fail";

		int n = cncProcessService.addProcess(cncProcesses,orderCode);
		if (n>0) {
			result = "success";
		}
		return new Response().success(result); 
	}

	/**
	 * 修改cncProcess数据
	 * @param id
	 * @param cncProcess
	 * @return
	 */
	@RequestMapping(value="cncProcess/{id}",method = RequestMethod.POST)
	public Response updateCncProcess(@PathVariable("id") int id,@RequestBody CncProcess cncProcess){
		String result = "fail";
		cncProcess.setId(id);
		int n = cncProcessService.updateCncProcess(cncProcess);
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
	@RequestMapping(value="cncProcess/{id}",method = RequestMethod.GET)
	public Response selectById(@PathVariable("id") int id){
		CncProcess cncProcess = cncProcessService.selectById(id);
		return new Response().success(cncProcess); 
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping(value="cncProcess",method = RequestMethod.DELETE)
	public Response deleteCncProcess(@RequestBody BatchObject<Integer> temps){
		String result = "fail";
		int n = cncProcessService.deleteCncProcess(temps.getTargets());
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
	@RequestMapping(value="/cncProcess/{id}/end",method=RequestMethod.POST)
	public Response endOrder(@PathVariable int id){
		String result = "fail";
		CncProcess cncProcess = new CncProcess();
		cncProcess.setId(id);
		cncProcess.setCompletionTime(new Date());
		cncProcess.setStatus("02");
		int n = cncProcessService.updateCncProcess(cncProcess);
		if (n>0) {
			result = "success";
		}
		return new Response().success(result);
	}
}
