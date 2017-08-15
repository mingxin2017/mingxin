package com.mx.ssh.service;

import java.util.List;

import com.mx.ssh.bean.MxUsersData;
import com.mx.ssh.bean.PageBean;
import com.mx.ssh.dao.ISysUsersDAO;

public interface IUserService {

	public List<MxUsersData> getAllUsers();
	MxUsersData validLogin(String username , String password);
	
	MxUsersData getUserByID(Integer userId);
	public MxUsersData validateWeixinUser(String openId);
	public MxUsersData getUserByOpenId(String openId);
	
}