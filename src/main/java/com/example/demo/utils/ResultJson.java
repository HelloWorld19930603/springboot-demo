package com.example.demo.utils;


/**
 * 操作结果实体类
 *
 */
public class ResultJson implements java.io.Serializable {

	private static final long serialVersionUID = 6493265179641167222L;

	private boolean status = false;

	private String msg;

	private Object obj;
	
	public ResultJson() {}
	
	public ResultJson(boolean status) {
		this.status = status;
	}
	
	public ResultJson(boolean status, String msg) {
		this.status = status;
		this.msg = msg;
	}

	public ResultJson(boolean status, String msg,Object obj) {
		this.status = status;
		this.msg = msg;
		this.obj = obj;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public void addMsg(String msg) {
		this.msg += msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

}
