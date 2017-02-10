package com.mystock.entity;
/**
 * 数据库里保存的字段的类
 * @author Administrator
 *
 */
public class UserInfo {

	
	private String gidcode;
	
	
	public UserInfo(String gidcode) {
		super();
		this.gidcode = gidcode;
	}
	
	public UserInfo() {
		super();
	}

	public String getGidcode() {
		return gidcode;
	}
	public void setGidcode(String gidcode) {
		this.gidcode = gidcode;
	}
	
	
	
}
