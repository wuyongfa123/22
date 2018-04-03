package com.wuyongfa.yifa.financialsystem.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * 日期工具类
 *	
 * @author wuyongfa@chinasoftinc.com
 * 2017年6月28日
 */
public class DateUtils {

	/**
	 * 获取当前月第一天
	 * 描述:<描述函数实现的功能>.
	 * @param repeatDate
	 * @return
	 */
	public static Date getMinMonthDate(){
		Calendar calendar = Calendar.getInstance();

		calendar.setTime(new Date());

		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();        
	}

	/**
	 * 
	 * 描述:获取下一个月的第一天.
	 * 
	 * @return
	 */
	public static Date getPerFirstDayOfMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 获取指定时间当前月第一天
	 * 描述:<描述函数实现的功能>.
	 * @param repeatDate
	 * @return
	 */
	public static Date getMinMonthDate(Date date){

		if (date == null) {
			date = new Date();
		}

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);

		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();        
	}

	/**
	 * 
	 * 描述:获取指定时间下一个月的第一天.
	 * 
	 * @return
	 */
	public static Date getPerFirstDayOfMonth(Date date) {

		if (date == null) {
			date = new Date();
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 日期格式化成字符串时间
	 * @param date
	 * @return
	 */
	public static String format(Date date){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
	}

	/**
	 * 
	 * @param time  yyyy-MM
	 * @return
	 */
	public static Date parse1(String time){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		try {
			return format.parse(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param time  yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static Date parse2(String time){

		if (StringUtils.isEmpty(time)) {
			return null;
		}

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return format.parse(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	/**
	 * 由出生日期获得年龄
	 * @param birthDay
	 * @return
	 * @throws Exception
	 */
	public static int getAge(Date birthDay){  
		Calendar cal = Calendar.getInstance();  

		if (cal.before(birthDay)) {  
			throw new IllegalArgumentException(  
					"The birthDay is before Now.It's unbelievable!");  
		}  
		int yearNow = cal.get(Calendar.YEAR);  
		int monthNow = cal.get(Calendar.MONTH);  
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);  
		cal.setTime(birthDay);  

		int yearBirth = cal.get(Calendar.YEAR);  
		int monthBirth = cal.get(Calendar.MONTH);  
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);  

		int age = yearNow - yearBirth;  

		if (monthNow <= monthBirth) {  
			if (monthNow == monthBirth) {  
				if (dayOfMonthNow < dayOfMonthBirth) age--;  
			}else{  
				age--;  
			}  
		}  
		return age;  
	}  
}
