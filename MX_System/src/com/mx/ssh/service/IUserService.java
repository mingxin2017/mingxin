package com.mx.ssh.service;

import java.util.List;

import com.mx.ssh.bean.MxUsersData;
import com.mx.ssh.bean.PageBean;

public interface IUserService {

	public List<MxUsersData> getAllUsers();
	MxUsersData validLogin(String username , String password);
	
	MxUsersData getUserByID(Integer userId);
	public MxUsersData validateWeixinUser(String openId);
	public MxUsersData getUserByOpenId(String openId);
	public MxUsersData userLogin(String userName, String userPwd);
	public PageBean<MxUsersData> findByPage(int page);
	public boolean addAdmin(MxUsersData user);
	public boolean deleteUsers(List<Integer> idList);
	public PageBean<MxUsersData> searchUser(String txtSearch);
	public boolean setState(int i, int userId);
	public boolean deleteUser(int userId);
	public boolean restoreUserPassword(int userId);
	public boolean updateUserData(MxUsersData user);
}
