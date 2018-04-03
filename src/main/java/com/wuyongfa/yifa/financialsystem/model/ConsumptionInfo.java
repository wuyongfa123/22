package com.wuyongfa.yifa.financialsystem.model;

import java.util.Date;

/**
 * 资源消费
 *	
 * @author wuyongfa@chinasoftinc.com
 * 2017年6月19日
 */
public class ConsumptionInfo {
    private Integer id;

    private Integer employeeId;
    
    private String employeeName;

    private String purchaseTypeCode;

    private String purchaseTypeName;

    private String detailTypeCode;

    private String detailTypeName;

    private String specifications;

    private Integer number;

    private String remark;

    private Date createdAt;

    private Date updatedAt;

    private String deleteFlag;

    private Date operateTime;

    public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getPurchaseTypeCode() {
        return purchaseTypeCode;
    }

    public void setPurchaseTypeCode(String purchaseTypeCode) {
        this.purchaseTypeCode = purchaseTypeCode == null ? null : purchaseTypeCode.trim();
    }

    public String getPurchaseTypeName() {
        return purchaseTypeName;
    }

    public void setPurchaseTypeName(String purchaseTypeName) {
        this.purchaseTypeName = purchaseTypeName == null ? null : purchaseTypeName.trim();
    }

    public String getDetailTypeCode() {
        return detailTypeCode;
    }

    public void setDetailTypeCode(String detailTypeCode) {
        this.detailTypeCode = detailTypeCode == null ? null : detailTypeCode.trim();
    }

    public String getDetailTypeName() {
        return detailTypeName;
    }

    public void setDetailTypeName(String detailTypeName) {
        this.detailTypeName = detailTypeName == null ? null : detailTypeName.trim();
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications == null ? null : specifications.trim();
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag == null ? null : deleteFlag.trim();
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }
}