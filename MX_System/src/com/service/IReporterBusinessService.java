package com.service;

import java.util.List;

import com.bean.MxNewsData;
import com.bean.MxUsersReporterScore;
import com.bean.MxUsersReporterSignUp;
import com.bean.MxUsersReporterTeam;
import com.weixin.util.MxKeyValue;


public interface IReporterBusinessService {
	/**
	 * 获取小记者团队
	 * @return
	 */
	List<MxUsersReporterTeam> getReporterTeams();
	/**
	 * 小记者申请
	 * @param mxUsersReporterTeam
	 */
	void reporterApply(MxUsersReporterSignUp mxUsersReporterSignUp);
	/**
	 * 用户id获取团队信息
	 * @param userId
	 * @return
	 */
	MxUsersReporterTeam getTeamByUserId(Integer userId);
	/**
	 * 用户id获取分数信息
	 * @param userId
	 * @return
	 */
	MxUsersReporterScore getScoreByUserId(Integer userId);
	/**
	 * 用户id获取新闻
	 * @param userId
	 * @return
	 */
	List<MxNewsData> getNewsDataByUserId(Integer userId);
	/**
	 * 新闻id获取新闻
	 * @param newsId
	 * @return
	 */
	MxNewsData getNewsDataByNewsId(Integer newsId);
}
