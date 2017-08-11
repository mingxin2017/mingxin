package com.mx.ssh.dao;

import java.util.List;

import com.mx.ssh.bean.MxNewsData;
import com.mx.ssh.bean.MxUsersReporterScore;
import com.mx.ssh.bean.MxUsersReporterSignUp;
import com.mx.ssh.bean.MxUsersReporterTeam;
import com.mx.weixin.util.MxKeyValue;

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
