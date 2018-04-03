/**

 * @Title: Response.java

 * @Package cn.aadata.rest

 * @Description: TODO

 * Copyright: Copyright (c) 2016

 * Company:苏州市环亚数据技术有限公司

 * 

 * @author chenpan@aa-data.cn

 * @date 2016年11月15日 上午10:31:07

 * @version V1.0

 */

package com.wuyongfa.yifa.financialsystem.response.Response;

/**
 * 
 * @ClassName: Response
 * 
 * @Description: REST统一响应结构
 * 
 * @author chenpan@aa-data.cn
 * 
 * @date 2016年11月15日 上午10:31:07
 *
 * 
 * 
 * 
 */

public class Response {
	private static final String OK = "ok";
	private static final String ERROR = "error";

	private Meta meta;
	private Object data;

	public Response success() {
		this.meta = new Meta(true, OK);
		return this;
	}

	public Response success(Object data) {
		this.meta = new Meta(true, OK);
		this.data = data;
		return this;
	}

	public Response failure() {
		this.meta = new Meta(false, ERROR);
		return this;
	}

	public Response failure(String message) {
		this.meta = new Meta(false, message);
		return this;
	}

	public Meta getMeta() {
		return meta;
	}

	public Object getData() {
		return data;
	}

	public class Meta {

		private boolean success;
		private String message;

		public Meta(boolean success) {
			this.success = success;
		}

		public Meta(boolean success, String message) {
			this.success = success;
			this.message = message;
		}

		public boolean isSuccess() {
			return success;
		}

		public String getMessage() {
			return message;
		}
	}
}
