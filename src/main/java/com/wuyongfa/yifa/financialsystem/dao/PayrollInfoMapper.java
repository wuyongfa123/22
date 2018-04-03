package com.wuyongfa.yifa.financialsystem.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.wuyongfa.yifa.financialsystem.model.PayrollInfo;
@Mapper
public interface PayrollInfoMapper {

    int insertSelective(PayrollInfo record);

    PayrollInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PayrollInfo record);

    /**
     * 根据员工信息查询工资发放信息
     * @param employeeName
     * @return
     */
	List<PayrollInfo> selectAllByPage(@Param("employeeName") String employeeName);

	/**
	 * 批量插入数据
	 * @param payrollInfos
	 * @return
	 */
	int addPayrolls(@Param("payrollInfos") List<PayrollInfo> payrollInfos);

	/**
	 * 批量逻辑删除工资发放记录
	 * @param targets
	 * @return
	 */
	int batchDelete(@Param("ids") List<Integer> targets);

	/**
	 * 条件查询工资发放信息
	 * @param filterMap
	 * @return
	 */
	List<PayrollInfo> selectByFilter(@Param("filters") Map<String, List<Object>> filterMap);

	/**
	 * 根据月份，查询发了工资的员工id
	 * @param month
	 * @return
	 */
	List<Integer> selectEmployeeIdByMonth(@Param("month") String month);
}