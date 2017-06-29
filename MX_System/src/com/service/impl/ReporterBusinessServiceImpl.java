package com.service.impl;

import java.util.List;

import com.bean.MxNewsData;
import com.bean.MxUsersReporterScore;
import com.bean.MxUsersReporterSignUp;
import com.bean.MxUsersReporterTeam;
import com.dao.IReporterBusinessDAO;
import com.weixin.util.MxKeyValue;

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
    //用户id获取团队信息
	public MxUsersReporterTeam getTeamByUserId(Integer userId) {
		return reporterBusinessDAO.getTeamByUserId(userId);
	}
	//用户id获取分数信息
	public MxUsersReporterScore getScoreByUserId(Integer userId) {
		return reporterBusinessDAO.getScoreByUserId(userId);
	}
	//用户id获取新闻
	public List<MxNewsData> getNewsDataByUserId(Integer userId) {
		return reporterBusinessDAO.getNewsDataByUserId(userId);
	}
	//新闻id获取新闻
	public MxNewsData getNewsDataByNewsId(Integer newsId) {
		return reporterBusinessDAO.getNewsDataByNewsId(newsId);
	}
	


	
}
