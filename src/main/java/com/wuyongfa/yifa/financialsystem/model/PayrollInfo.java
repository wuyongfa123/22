package com.wuyongfa.yifa.financialsystem.model;

import java.util.Date;

/**
 * 工资发放记录
 *	
 * @author wuyongfa@chinasoftinc.com
 * 2017年6月14日
 */
public class PayrollInfo {
    private Integer id;

    private Integer employeeId;
    
    private String employeeName;

    private Integer monthDay;

    private Integer workDay;

    private Double leaveDay;

    private Double absenteeism;

    private Double deductWages;

    private Double realWages;

    private Date payDate;

    private Date createdAt;

    private Date updatedAt;

    private String deleteFlag;
    
    private Double basePay;
    
    private String month;//月份
    
    public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Double getBasePay() {
		return basePay;
	}

	public void setBasePay(Double basePay) {
		this.basePay = basePay;
	}

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

    public Integer getMonthDay() {
        return monthDay;
    }

    public void setMonthDay(Integer monthDay) {
        this.monthDay = monthDay;
    }

    public Integer getWorkDay() {
        return workDay;
    }

    public void setWorkDay(Integer workDay) {
        this.workDay = workDay;
    }

    public Double getLeaveDay() {
		return leaveDay;
	}

	public void setLeaveDay(Double leaveDay) {
		this.leaveDay = leaveDay;
	}

	public Double getAbsenteeism() {
        return absenteeism;
    }

    public void setAbsenteeism(Double absenteeism) {
        this.absenteeism = absenteeism;
    }

    public Double getDeductWages() {
        return deductWages;
    }

    public void setDeductWages(Double deductWages) {
        this.deductWages = deductWages;
    }

    public Double getRealWages() {
        return realWages;
    }

    public void setRealWages(Double realWages) {
        this.realWages = realWages;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
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
}