package com.wuyongfa.yifa.financialsystem.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.wuyongfa.yifa.financialsystem.model.PurchaseInfo;
@Mapper
public interface PurchaseInfoMapper {
    int insert(PurchaseInfo record);

    int insertSelective(PurchaseInfo record);

    PurchaseInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PurchaseInfo record);

    int updateByPrimaryKey(PurchaseInfo record);

    /**
     * 分页条件查询
     * @param purchaseInfo
     * @return
     */
	List<PurchaseInfo> selectAllByPage(@Param("startTime") Date startTime,@Param("endTime") Date endTime);

	/**
	 * 批量逻辑删除
	 * @param ids
	 * @return
	 */
	int deletePurchaseInfos(@Param("ids") List<Integer> ids);
}