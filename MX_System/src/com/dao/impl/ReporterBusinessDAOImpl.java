package com.dao.impl;


import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

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



}
