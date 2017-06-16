package com.service.impl;

import com.dao.IReporterBusinessDAO;

public class ReporterBusinessServiceImpl implements com.service.IReporterBusinessService {
	
	private IReporterBusinessDAO reporterBusinessDAO;

	public IReporterBusinessDAO getReporterBusinessDAO() {
		return reporterBusinessDAO;
	}

	public void setReporterBusinessDAO(IReporterBusinessDAO reporterBusinessDAO) {
		this.reporterBusinessDAO = reporterBusinessDAO;
	}
	


	
}
