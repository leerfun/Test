package com.mystock.entity;
/**
 * ���ݿ��ﱣ����ֶε���
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
