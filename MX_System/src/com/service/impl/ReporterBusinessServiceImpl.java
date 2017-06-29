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
    //��ȡС�����Ŷ�
	public List<MxUsersReporterTeam> getReporterTeams() {
		return reporterBusinessDAO.getReporterTeams();
	}
	//С��������
	public void reporterApply(MxUsersReporterSignUp mxUsersReporterSignUp) {
		reporterBusinessDAO.reporterApply(mxUsersReporterSignUp);
	}
    //�û�id��ȡ�Ŷ���Ϣ
	public MxUsersReporterTeam getTeamByUserId(Integer userId) {
		return reporterBusinessDAO.getTeamByUserId(userId);
	}
	//�û�id��ȡ������Ϣ
	public MxUsersReporterScore getScoreByUserId(Integer userId) {
		return reporterBusinessDAO.getScoreByUserId(userId);
	}
	//�û�id��ȡ����
	public List<MxNewsData> getNewsDataByUserId(Integer userId) {
		return reporterBusinessDAO.getNewsDataByUserId(userId);
	}
	//����id��ȡ����
	public MxNewsData getNewsDataByNewsId(Integer newsId) {
		return reporterBusinessDAO.getNewsDataByNewsId(newsId);
	}
	


	
}
