package com.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.bean.PageBean;
import com.bean.SysUsers;
import com.dao.ISysUsersDAO;

public class UserServiceImpl implements com.service.IUserService {
	
	private ISysUsersDAO sysUsersDAO;
	
	public ISysUsersDAO getSysUsersDAO() {
		return this.sysUsersDAO;
	}
	public void setSysUsersDAO(ISysUsersDAO userDAO) {
		this.sysUsersDAO = userDAO;
	}

	
	
	/**���ã���֤�û���¼
	 * ����1��username�û���
	 * ����2��password����
	 * ����ֵ���ɹ������û����󣬷���null
	 * ����ʱ�䣺8:17:12 PM  �����ߣ�wulm
	 */
	public SysUsers validLogin(String username, String password) {

		SysUsers user = sysUsersDAO.findSysUsersByNameAndPass(username,
				password);
		if (user != null) {
			return user;
		} else {
			return null;
		}

	}

	public SysUsers getUserByID(Integer userId) {
		// TODO Auto-generated method stub
		try {
			SysUsers User = sysUsersDAO.get(userId);
			if (User != null) {
				return User;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public List<SysUsers> getAllUsers(){
		List<SysUsers> list =sysUsersDAO.getAll();
		return list;
	}

	public PageBean<SysUsers> queryForPage(int pageCurrent) {
		HashMap<String, String> conditionList=new HashMap<String, String>();
		
		HttpServletRequest request= ServletActionContext.getRequest();
		int userType=(Integer)
				(request.getSession().getAttribute("userType")
						==null?-1:request.getSession().getAttribute("userType"));
		
		String userName=(String) 
				(request.getSession().getAttribute("userName")
						==null?"":request.getSession().getAttribute("userName"));
		if(pageCurrent<=0){
			pageCurrent=1;
		}
		if(userType!=-1){
			conditionList.put("user_type=", userType+"");
		}
		if(userName!=""){
			conditionList.put("user_name like", " '%"+userName+"%'");
		}
		
		PageBean<SysUsers> pb = sysUsersDAO.getUsersPageBean(conditionList,pageCurrent);	
		//System.out.println("service��pageBean:��ҳ��"+pb.getTotalPage()+";��ǰҳ��"+pb.getCurrentPage()+";�ܼ�¼��:"+pb.getAllRow());
		return pb;
	}
}
