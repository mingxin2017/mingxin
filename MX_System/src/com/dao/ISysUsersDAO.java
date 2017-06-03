package com.dao;

import java.util.HashMap;
import java.util.List;

import com.bean.PageBean;
import com.bean.SysUsers;

public interface ISysUsersDAO {

	/**
     * ��ȡ�����û�
     * @param
     */ 
	public List<SysUsers> getAll();
	
	/**
     * ����id�����û�
     * @param id ��Ҫ���ҵ��û�id
     */  
    SysUsers get(Integer id);

    /**
     * �޸��û�
     * @param SysUsers ��Ҫ�޸ĵ��û�
     */  
    void update(SysUsers SysUsers);

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
    SysUsers findSysUsersByNameAndPass(String SysUsersname , String password);
	
    /**
     * ���������б�ҳ��ֵ�����û�
     * @param conditionList sql��ѯ������������б�
     * @param pageNum ҳ��ֵ
     * @return ��Ӧ��PageBean���󣬰�����ҳ��ʾ�û���������Ϣ
     */ 
	PageBean<SysUsers> getUsersPageBean(HashMap<String, String> conditionList,int pageNum);
	
	
	
}
