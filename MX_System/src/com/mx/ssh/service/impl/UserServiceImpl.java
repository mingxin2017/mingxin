package com.mx.ssh.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.ssh.bean.MxUsersData;
import com.mx.ssh.bean.PageBean;
import com.mx.ssh.dao.ISysUsersDAO;

@Service("userService")
public class UserServiceImpl implements com.mx.ssh.service.IUserService {
	
	@Autowired
	private ISysUsersDAO sysUsersDAO;
	
	
	/**作用：验证用户登录
	 * 参数1：username用户名
	 * 参数2：password密码
	 * 返回值：成功返回用户对象，否则null
	 * 创建时间：8:17:12 PM  创建者：wulm
	 */
	public MxUsersData validLogin(String username, String password) {

		MxUsersData user = sysUsersDAO.findSysUsersByNameAndPass(username,
				password);
		if (user != null) {
			return user;
		} else {
			return null;
		}

	}

	public MxUsersData getUserByID(Integer userId) {
		// TODO Auto-generated method stub
		try {
			MxUsersData User = sysUsersDAO.get(userId);
			if (User != null) {
				return User;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public List<MxUsersData> getAllUsers(){
		List<MxUsersData> list =sysUsersDAO.getAll();
		return list;
	}

	
	public MxUsersData validateWeixinUser(String openId) {
		// TODO Auto-generated method stub
		return sysUsersDAO.validateWeixinUser(openId);
	}
	public MxUsersData getUserByOpenId(String openId) {
		// TODO Auto-generated method stub
		return sysUsersDAO.getUserByOpenId(openId);
	}

	
	
}
