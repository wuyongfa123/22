package com.wuyongfa.yifa.financialsystem.page;

import java.util.List;

public class Page extends BasePage{

	/**
	  * @Fields serialVersionUID : TODO
	  */
	private static final long serialVersionUID = 3156829217373929903L;
	
	public static ThreadLocal<Page> threadLocal = new ThreadLocal<Page>();
	
	private List<?> data; 
	
	public Page() {
	}
	
	public Page(int currentPage, int pageSize, int totalCount) {
	    super(currentPage, pageSize, totalCount);
	}
	
	public Page(int currentPage, int pageSize, int totalCount, List<?> data) {
	    super(currentPage, pageSize, totalCount);
	    this.data = data;
	}
	
	public List<?> getData() {
	    return data;
	}
	
	public void setData(List<?> data) {
	    this.data = data;
	}
}
