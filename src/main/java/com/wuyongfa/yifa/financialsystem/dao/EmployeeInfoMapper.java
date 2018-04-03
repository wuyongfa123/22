package com.wuyongfa.yifa.financialsystem.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.wuyongfa.yifa.financialsystem.model.EmployeeInfo;
@Mapper
public interface EmployeeInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EmployeeInfo record);

    int insertSelective(EmployeeInfo record);

    EmployeeInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EmployeeInfo record);

    int updateByPrimaryKey(EmployeeInfo record);

    /**
     * 查询人员信息
     * @param employeeName
     * @return
     */
	List<EmployeeInfo> selectAllByPage(@Param("employeeInfo") EmployeeInfo employeeInfo);

	/**
	 * 逻辑删除员工信息
	 * @param targets
	 * @return
	 */
	int deleteEmployee(@Param("ids")List<Integer> ids);

	/**
	 * 根据员工姓名模糊查询对应员工Id
	 * @param employeeName
	 * @return
	 */
	List<Integer> selectIdByName(@Param("name") String employeeName);
	
	/**
	 * 查询所有员工信息
	 * @return
	 */
	List<EmployeeInfo> selectAll();

	/**
	 * 查询除此之外的员工信息
	 * @param ids
	 * @return
	 */
	List<EmployeeInfo> selectOther(@Param("ids") List<Integer> ids);
}