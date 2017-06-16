package com.dao.impl;


import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dao.IReporterBusinessDAO;
import com.publicMethos.ISqlUtil;


public class ReporterBusinessDAOImpl extends HibernateDaoSupport implements IReporterBusinessDAO{
	private ISqlUtil sqlUtil;//依赖注入执行自定义sql语句的通用类
	
	public ISqlUtil getSqlUtil() {
		return sqlUtil;
	}
	public void setSqlUtil(ISqlUtil sqlUtil) {
		this.sqlUtil = sqlUtil;
	}



}
