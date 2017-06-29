package com.service;

import java.util.List;

import com.bean.MxNewsData;
import com.bean.MxUsersReporterScore;
import com.bean.MxUsersReporterSignUp;
import com.bean.MxUsersReporterTeam;
import com.weixin.util.MxKeyValue;


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
