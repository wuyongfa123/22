package com.wuyongfa.yifa.financialsystem.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.wuyongfa.yifa.financialsystem.model.CheckInfo;
@Mapper
public interface CheckInfoMapper {

    int insert(CheckInfo record);

    int insertSelective(CheckInfo record);

    CheckInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CheckInfo record);

    int updateByPrimaryKey(CheckInfo record);

    /**
     * 条件查询所有考勤信息
     * @param filterMaps
     * @return
     */
	List<CheckInfo> selectAllByPage(@Param("employeeName") String employeeName);

	/**
	 * 逻辑删除考勤信息
	 * @param targets
	 * @return
	 */
	int deleteCheckInfo(@Param("ids") List<Integer> ids);

	/**
	 * 查询范围内考勤情况
	 * @param employeeId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	List<CheckInfo> selectByTime(@Param("employeeId")Integer employeeId,
			@Param("startTime") Date startTime,@Param("endTime") Date endTime);
}