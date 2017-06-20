package com.dao.impl;


import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bean.MxUsersReporterSignUp;
import com.bean.MxUsersReporterTeam;
import com.dao.IReporterBusinessDAO;
import com.publicMethos.ISqlUtil;


public class ReporterBusinessDAOImpl extends HibernateDaoSupport implements IReporterBusinessDAO{
	private ISqlUtil sqlUtil;//����ע��ִ���Զ���sql����ͨ����
	
	public ISqlUtil getSqlUtil() {
		return sqlUtil;
	}
	public void setSqlUtil(ISqlUtil sqlUtil) {
		this.sqlUtil = sqlUtil;
	}
	//��ȡС�����Ŷ�
	@SuppressWarnings("unchecked")
	public List<MxUsersReporterTeam> getReporterTeams() {
		return getHibernateTemplate().find("from com.bean.MxUsersReporterTeam");
	}
	//С��������
	public void reporterApply(MxUsersReporterSignUp mxUsersReporterSignUp) {
		try {
			getHibernateTemplate().save(mxUsersReporterSignUp);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}
    
	
    

}
