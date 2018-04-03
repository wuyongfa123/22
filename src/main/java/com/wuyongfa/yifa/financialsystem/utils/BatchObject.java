/**
 * @author xuewenlong@cmss.chinamobile.com
 * @updated 2016年11月15日
 */
package com.wuyongfa.yifa.financialsystem.utils;

/**
 * @author xuewenlong@cmss.chinamobile.com
 * @updated 2016年11月15日
 */


import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

public class BatchObject<T> {

    
    @NotEmpty(message = "请选择删除目标")
    private List<T> targets;

    private String status;
    
    private String poolId;
    
    private String userId;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<T> getTargets() {
        return targets;
    }

    public void setTargets(List<T> targets) {
        this.targets = targets;
    }

    public String getPoolId() {
        return poolId;
    }

    public void setPoolId(String poolId) {
        this.poolId = poolId;
    }

   

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "BatchDelete [targets=" + targets + ", status=" + status + ", poolId=" + poolId
                + ", userId=" + userId + "]";
    }
    
}
