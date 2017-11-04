package com.mx.ssh.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mx.ssh.bean.MxUsersData;
import com.mx.ssh.bean.PageBean;
import com.mx.ssh.dao.ISysUsersDAO;

@Service("userService")
public class UserServiceImpl implements com.mx.ssh.service.IUserService {
	
	@Autowired
	private ISysUsersDAO sysUsersDAO;
	
	
	/**���ã���֤�û���¼
	 * ����1��username�û���
	 * ����2��password����
	 * ����ֵ���ɹ������û����󣬷���null
	 * ����ʱ�䣺8:17:12 PM  �����ߣ�wulm
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

	public MxUsersData userLogin(String userName, String userPwd) {
		// TODO Auto-generated method stub
		return sysUsersDAO.userLogin(userName,userPwd);
	}

	public PageBean<MxUsersData> findByPage(int page) {
		// TODO Auto-generated method stub
		PageBean<MxUsersData> pageBean =new PageBean<MxUsersData>();
        pageBean.setCurrentPage(page);
        int limit=5;//ÿҳ����
        pageBean.setPageSize(limit);
        int totalCount=sysUsersDAO.findTotalCount();
        pageBean.setAllRow(totalCount);
        int totalpage=(int)Math.ceil(totalCount/limit);
        pageBean.setTotalPage(totalpage);
        //ÿҳ��ʾ�����ݼ���
        int begin=(page-1)*limit;
        List<MxUsersData> list=sysUsersDAO.findUsersByPage(begin,limit);
        pageBean.setList(list);
        return pageBean;
	}

	public boolean addAdmin(MxUsersData user) {
		// TODO Auto-generated method stub
		return sysUsersDAO.addAdmin(user);
	}

	public boolean deleteUsers(List<Integer> idList) {
		// TODO Auto-generated method stub
		return sysUsersDAO.deleteUsers(idList);
	}

	public PageBean<MxUsersData> searchUser(String txtSearch) {
		// TODO Auto-generated method stub
		/*PageBean<MxUsersData> pageBean =new PageBean<MxUsersData>();
        pageBean.setCurrentPage(page);
        int limit=5;//ÿҳ����
        pageBean.setPageSize(limit);
        int totalCount=sysUsersDAO.findTotalCount();
        pageBean.setAllRow(totalCount);
        int totalpage=(int)Math.ceil(totalCount/limit);
        pageBean.setTotalPage(totalpage);
        //ÿҳ��ʾ�����ݼ���
        int begin=(page-1)*limit;
        List<MxUsersData> list=sysUsersDAO.findUsersByPage(begin,limit);
        pageBean.setList(list);
        return pageBean;*/
		return sysUsersDAO.searchUser(txtSearch);
	}

	public boolean setState(int i, int userId) {
		// TODO Auto-generated method stub
		return sysUsersDAO.setState(i,userId);
	}

	public boolean deleteUser(int userId) {
		// TODO Auto-generated method stub
		return sysUsersDAO.deleteUser(userId);
	}

	public boolean restoreUserPassword(int userId) {
		// TODO Auto-generated method stub
		return sysUsersDAO.restoreUserPassword(userId);
	}

	public boolean updateUserData(MxUsersData user) {
		// TODO Auto-generated method stub
		return sysUsersDAO.updateUserData(user);
	}

	
	
}
