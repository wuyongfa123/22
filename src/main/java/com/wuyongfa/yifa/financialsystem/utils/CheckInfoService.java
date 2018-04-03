package com.wuyongfa.yifa.financialsystem.utils;

import java.util.Date;
import java.util.List;

import com.wuyongfa.yifa.financialsystem.model.CheckInfo;
import com.wuyongfa.yifa.financialsystem.page.Page;

/**
 * 考勤管理业务层
 *	
 * @author wuyongfa@chinasoftinc.com
 * 2017年6月15日
 */
public interface CheckInfoService {

	/**
	 * 分页条件查询考勤信息
	 * @param page
	 * @param checkInfo
	 * @return
	 */
	Page selectCheckInfo(Page page, CheckInfo checkInfo);

	/**
	 * 添加考勤信息
	 * @param checkInfo
	 * @return
	 */
	int addCheckInfo(CheckInfo checkInfo);

	/**
	 * 修改员工考勤信息
	 * @param checkInfo
	 * @return
	 */
	int updateCheckInfo(CheckInfo checkInfo);

	/**
	 * 批量删除员工考勤信息
	 * @param targets
	 * @return
	 */
	int deleteCheckInfo(List<Integer> targets);

	/**
	 * 根据主键查询考勤信息
	 * @param id
	 * @return
	 */
	CheckInfo selectCheckInfoById(int id);

	/**
	 * 查询员工时间范围内考勤情况
	 * @param employeeId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	List<CheckInfo> selectCheckInfo(Integer employeeId, Date startTime,
			Date endTime);

}
