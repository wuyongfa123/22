package com.wuyongfa.yifa.financialsystem.model;

import java.util.Date;

/**
 * CNC加工件model类
 *	
 * @author wuyongfa@chinasoftinc.com
 * 2017年7月13日
 */
public class CncProcess {
    private Integer id;

    private String orderCode;
    
    private String processCode;

    private Double unitPrice;

    private String status;

    private Date completionTime;

    public String getProcessCode() {
		return processCode;
	}

	public void setProcessCode(String processCode) {
		this.processCode = processCode;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode == null ? null : orderCode.trim();
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(Date completionTime) {
        this.completionTime = completionTime;
    }
}