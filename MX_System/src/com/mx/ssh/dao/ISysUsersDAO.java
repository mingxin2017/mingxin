package com.mx.ssh.dao;

import java.util.List;

import com.mx.ssh.bean.MxUsersData;
import com.mx.ssh.bean.PageBean;
public interface ISysUsersDAO {
	
	/**
	 * ����open_id��ѯ�û��Ƿ��ע�����ں�  0�� 1��
	 * @param open_id
	 * @return
	 */
	public int isExistUser(String open_id);

	/**
     * ����û�
     * @param
     */ 
	public void addUser(MxUsersData userData);
	
	/**
     * ��ȡ�����û�
     * @param
     */ 
	public List<MxUsersData> getAll();
	
	/**
     * ����id�����û�
     * @param id ��Ҫ���ҵ��û�id
     */  
	MxUsersData get(Integer id);

    /**
     * �޸��û�
     * @param SysUsers ��Ҫ�޸ĵ��û�
     */  
    void update(MxUsersData SysUsers);

    /**
     * ɾ���û�
     * @param id ��Ҫɾ�����û�id
     */  
    void delete(Integer id);

    /**
     * �����û�������������û�
     * @param SysUsersname ��ѯ������û���
     * @param pass ��ѯ���������
     * @return ��Ӧ���û�
     */ 
    MxUsersData findSysUsersByNameAndPass(String SysUsersname , String password);
	
	/**
	 * ͨ��openid��ȡ�û�
	 * @param parseInt
	 * @return
	 */
	public MxUsersData getUserByOpenId(String openId);

	/**
	 * �����û�״̬
	 * @param i
	 * @return
	 */
	public boolean setUserState(MxUsersData ur);

	public MxUsersData validateWeixinUser(String openId);

	public MxUsersData userLogin(String userName, String userPwd);
	
	public int findTotalCount();
	
	 public List<MxUsersData> findUsersByPage(int begin, int limit);

	public boolean addAdmin(MxUsersData user);

	public boolean deleteUsers(List<Integer> idList);

	public PageBean<MxUsersData> searchUser(String txtSearch);
	
}
