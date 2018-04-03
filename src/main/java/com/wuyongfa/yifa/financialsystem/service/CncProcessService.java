package com.wuyongfa.yifa.financialsystem.service;

import java.util.List;

import com.wuyongfa.yifa.financialsystem.model.CncProcess;
import com.wuyongfa.yifa.financialsystem.page.Page;

/**
 * cnc加工件管理层
 *	
 * @author wuyongfa@chinasoftinc.com
 * 2017年7月20日
 */
public interface CncProcessService {

	/**
	 * 分页查询
	 * @param page
	 * @param orderCode
	 * @return
	 */
	Page selectByPage(Page page, String orderCode);

	/**
	 * 添加加工件信息
	 * @param cncProcesses
	 * @param orderCode 
	 * @return
	 */
	int addProcess(List<CncProcess> cncProcesses, String orderCode);

	/**
	 * 根据主键修改cncProcess
	 * @param cncProcess
	 * @return
	 */
	int updateCncProcess(CncProcess cncProcess);

	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	CncProcess selectById(int id);

	/**
	 * 逻辑删除
	 * @param ids
	 * @return
	 */
	int deleteCncProcess(List<Integer> ids);

}
