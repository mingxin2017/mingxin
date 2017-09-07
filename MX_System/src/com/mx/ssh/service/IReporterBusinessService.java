package com.mx.ssh.service;

import java.util.List;

import com.mx.ssh.bean.MxNewsData;
import com.mx.ssh.bean.MxUsersReporterScore;
import com.mx.ssh.bean.MxUsersReporterSignUp;
import com.mx.ssh.bean.MxUsersReporterTeam;


public interface IReporterBusinessService {
	/**
	 * ��ȡС�����Ŷ�
	 * @return
	 */
	List<MxUsersReporterTeam> getReporterTeams();
	/**
	 * С��������
	 * @param mxUsersReporterTeam
	 */
	void reporterApply(MxUsersReporterSignUp mxUsersReporterSignUp);
	/**
	 * �û�id��ȡ�Ŷ���Ϣ
	 * @param userId
	 * @return
	 */
	MxUsersReporterTeam getTeamByUserId(Integer userId);
	/**
	 * �û�id��ȡ������Ϣ
	 * @param userId
	 * @return
	 */
	MxUsersReporterScore getScoreByUserId(Integer userId);
	/**
	 * �û�id��ȡ����
	 * @param userId
	 * @return
	 */
	List<MxNewsData> getNewsDataByUserId(Integer userId);
	/**
	 * ����id��ȡ����
	 * @param newsId
	 * @return
	 */
	MxNewsData getNewsDataByNewsId(Integer newsId);
}
