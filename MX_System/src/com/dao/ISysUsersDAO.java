package com.dao;

import java.util.HashMap;
import java.util.List;

import com.bean.PageBean;
import com.bean.SysUsers;

public interface ISysUsersDAO {

	/**
     * 获取所有用户
     * @param
     */ 
	public List<SysUsers> getAll();
	
	/**
     * 根据id查找用户
     * @param id 需要查找的用户id
     */  
    SysUsers get(Integer id);

    /**
     * 修改用户
     * @param SysUsers 需要修改的用户
     */  
    void update(SysUsers SysUsers);

    /**
     * 删除用户
     * @param id 需要删除的用户id
     */  
    void delete(Integer id);

    /**
     * 根据用户名，密码查找用户
     * @param SysUsersname 查询所需的用户名
     * @param pass 查询所需的密码
     * @return 对应的用户
     */ 
    SysUsers findSysUsersByNameAndPass(String SysUsersname , String password);
	
    /**
     * 根据条件列表、页码值查找用户
     * @param conditionList sql查询语句所需条件列表
     * @param pageNum 页码值
     * @return 对应的PageBean对象，包含分页显示用户的所有信息
     */ 
	PageBean<SysUsers> getUsersPageBean(HashMap<String, String> conditionList,int pageNum);
	
	
	
}
