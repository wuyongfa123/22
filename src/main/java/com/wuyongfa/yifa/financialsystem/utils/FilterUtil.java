package com.wuyongfa.yifa.financialsystem.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

/**
 * 处理过滤器工具类
 * @author chenqinghao@cmss.chinamobile.com
 * @date 2016年11月4日  下午4:42:55
 */
@Component
public class FilterUtil {
 
	public static Map<String, List<Object>> buildFilters(List<Filter> filters) {
		
		//过滤器Map不能为null
		Map<String, List<Object>> alarmFilterMap = new HashMap<String, List<Object>>();
    	
    	/**获取前置过滤条件**/
    	if (filters != null && !filters.isEmpty()) {
    		for (Iterator<Filter> iter = filters.iterator(); iter.hasNext();) {
    			Filter f = iter.next();
    			
    			List<Object> filterValues = alarmFilterMap.get(f.getFilterName());
    			if(filterValues == null){
    				filterValues = new ArrayList<Object>();
    			}
    			filterValues.add(f.getFilterValue());
    			
    			alarmFilterMap.put(f.getFilterName(), filterValues);
    		}
    	}
    	
    	return alarmFilterMap;
	}
	
}
