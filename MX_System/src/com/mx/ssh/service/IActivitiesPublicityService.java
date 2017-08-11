package com.mx.ssh.service;

import java.util.List;

import com.mx.ssh.bean.MxActivitiesPublicityContent;
import com.mx.ssh.bean.MxActivitiesPublicityData;

public interface IActivitiesPublicityService {

	/**
	 * ��ȡ����������б�
	 * @param activitiesType
	 * @return
	 */
	public List<MxActivitiesPublicityData> getActivitiesPublicityByType(int activitiesType);

	/**
	 * ����id��ȡ�������������
	 * @param pdID
	 * @return
	 */
	public MxActivitiesPublicityContent getActivitiesPublicityContent(int pdID);

	
	
}
