package com.wuyongfa.yifa.financialsystem.utils;
/**
 * 
 * @author chenqinghao@cmss.chinamobile.com
 * @date 2016年11月2日  下午2:08:01
 */
public class Filter {
	
	private String filterName;

    private Object filterValue;
    
    public Filter (){
    	
    }
    
    public Filter(String filterName, Object filterValue) {
		super();
		this.filterName = filterName;
		this.filterValue = filterValue;
	}



	public String getFilterName() {
        return filterName;
    }

    public void setFilterName(String filterName) {
        this.filterName = filterName == null ? null : filterName.trim();
    }

    public Object getFilterValue() {
        return filterValue;
    }

    public void setFilterValue(Object filterValue) {
        this.filterValue = filterValue == null ? null : filterValue;
    }
}
