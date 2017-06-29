package com.dao;

import java.util.List;

import com.bean.MxNewsData;
import com.bean.MxUsersReporterScore;
import com.bean.MxUsersReporterSignUp;
import com.bean.MxUsersReporterTeam;
import com.weixin.util.MxKeyValue;

public interface IReporterBusinessDAO {
	/**
	 * ��ȡС�����Ŷ�
	 * @return
	 */
	public List<MxUsersReporterTeam> getReporterTeams();
	/**
	 * С��������
	 * @param mxUsersReporterSignUp
	 */
	public void reporterApply(MxUsersReporterSignUp mxUsersReporterSignUp);
	/**
	 * �û�id��ȡ�Ŷ���Ϣ
	 * @param userId
	 * @return
	 */
	public MxUsersReporterTeam getTeamByUserId(Integer userId);
	/**
	 * �û�id��ȡ������Ϣ
	 * @param userId
	 * @return
	 */
	public MxUsersReporterScore getScoreByUserId(Integer userId);
	/**
	 * �û�id��ȡ����
	 * @param userId
	 * @return
	 */
	public List<MxNewsData> getNewsDataByUserId(Integer userId);
	/**
	 * ��������id��ȡ����
	 * @param newsId
	 * @return
	 */
	public MxNewsData getNewsDataByNewsId(Integer newsId);
}
