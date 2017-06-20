package com.service.impl;

import java.util.List;

import com.bean.MxUsersReporterSignUp;
import com.bean.MxUsersReporterTeam;
import com.dao.IReporterBusinessDAO;

public class ReporterBusinessServiceImpl implements com.service.IReporterBusinessService {
	
	private IReporterBusinessDAO reporterBusinessDAO;

	public IReporterBusinessDAO getReporterBusinessDAO() {
		return reporterBusinessDAO;
	}

	public void setReporterBusinessDAO(IReporterBusinessDAO reporterBusinessDAO) {
		this.reporterBusinessDAO = reporterBusinessDAO;
	}
    //获取小记者团队
	public List<MxUsersReporterTeam> getReporterTeams() {
		return reporterBusinessDAO.getReporterTeams();
	}
	//小记者申请
	public void reporterApply(MxUsersReporterSignUp mxUsersReporterSignUp) {
		reporterBusinessDAO.reporterApply(mxUsersReporterSignUp);
	}
	


	
}
