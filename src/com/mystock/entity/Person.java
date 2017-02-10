package com.mystock.entity;

import cn.bmob.v3.BmobObject;

public class Person extends BmobObject {
    private String name;
    private String passWord;

    public Person(String name, String passWord) {
		super();
		this.name = name;
		this.passWord = passWord;
	}
	public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return passWord;
    }
    public void setAddress(String address) {
        this.passWord = address;
    }
}