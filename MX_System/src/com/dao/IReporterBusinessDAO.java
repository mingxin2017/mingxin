package com.dao;

import java.util.List;

import com.bean.MxUsersReporterSignUp;
import com.bean.MxUsersReporterTeam;

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
	
}
