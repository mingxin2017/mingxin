package com.service;

import java.util.List;

import com.bean.MxUsersReporterSignUp;
import com.bean.MxUsersReporterTeam;


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
}
