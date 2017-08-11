package com.mx.ssh.dao;

import java.util.List;

import com.mx.ssh.bean.MxNewsData;
import com.mx.ssh.bean.MxUsersReporterScore;
import com.mx.ssh.bean.MxUsersReporterSignUp;
import com.mx.ssh.bean.MxUsersReporterTeam;
import com.mx.weixin.util.MxKeyValue;

public interface IReporterBusinessDAO {
	/**
	 * 获取小记者团队
	 * @return
	 */
	public List<MxUsersReporterTeam> getReporterTeams();
	/**
	 * 小记者申请
	 * @param mxUsersReporterSignUp
	 */
	public void reporterApply(MxUsersReporterSignUp mxUsersReporterSignUp);
	/**
	 * 用户id获取团队信息
	 * @param userId
	 * @return
	 */
	public MxUsersReporterTeam getTeamByUserId(Integer userId);
	/**
	 * 用户id获取分数信息
	 * @param userId
	 * @return
	 */
	public MxUsersReporterScore getScoreByUserId(Integer userId);
	/**
	 * 用户id获取新闻
	 * @param userId
	 * @return
	 */
	public List<MxNewsData> getNewsDataByUserId(Integer userId);
	/**
	 * 根据新闻id获取新闻
	 * @param newsId
	 * @return
	 */
	public MxNewsData getNewsDataByNewsId(Integer newsId);
}
