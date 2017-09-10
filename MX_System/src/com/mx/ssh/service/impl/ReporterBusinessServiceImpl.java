package com.mx.ssh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.ssh.bean.MxNewsData;
import com.mx.ssh.bean.MxUsersReporterScore;
import com.mx.ssh.bean.MxUsersReporterSignUp;
import com.mx.ssh.bean.MxUsersReporterTeam;
import com.mx.ssh.dao.IReporterBusinessDAO;

@Service("reporterBusinessService")
public class ReporterBusinessServiceImpl implements com.mx.ssh.service.IReporterBusinessService {
	
	@Autowired
	private IReporterBusinessDAO reporterBusinessDAO;

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
