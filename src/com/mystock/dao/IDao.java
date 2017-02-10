package com.mystock.dao;

import java.util.List;

import com.mystock.entity.UserInfo;

public interface IDao {
	
	 public List<UserInfo> GetUserList(); 
	 public Long SaveGidInfo(String user);
	 public int DelUserInfo(String gidCode); 
}
