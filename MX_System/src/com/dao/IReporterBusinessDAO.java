package com.dao;

import java.util.List;

import com.bean.MxUsersReporterSignUp;
import com.bean.MxUsersReporterTeam;

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
	
}
