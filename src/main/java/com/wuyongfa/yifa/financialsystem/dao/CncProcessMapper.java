package com.wuyongfa.yifa.financialsystem.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.wuyongfa.yifa.financialsystem.model.CncProcess;
@Mapper
public interface CncProcessMapper {
    int insertSelective(CncProcess record);

    CncProcess selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CncProcess record);

    /**
     * 批量插入cnc加工件信息
     * @param cncProcesses
     */
	int batchInsert(@Param("cncProcesses") List<CncProcess> cncProcesses);

	/**
	 * 根据订单编号，分页查询数据
	 * @param orderCode
	 * @return
	 */
	List<CncProcess> selectByPage(@Param("orderCode") String orderCode);

	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	int batchDelete(@Param("ids") List<Integer> ids);
}