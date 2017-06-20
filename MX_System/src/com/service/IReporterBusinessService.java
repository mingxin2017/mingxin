package com.service;

import java.util.List;

import com.bean.MxUsersReporterSignUp;
import com.bean.MxUsersReporterTeam;


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
}
