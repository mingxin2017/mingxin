package com.mx.ssh.dao;

import java.util.List;

import com.mx.ssh.bean.MxActivitiesPublicityContent;
import com.mx.ssh.bean.MxActivitiesPublicityData;

public interface IActivitiesPublicityDAO {

	/**
	 * ���ݻ����id��ȡ����������б�
	 */
	List<MxActivitiesPublicityData> getActivitiesPublicityByType(
			int activitiesType);

	/**
	 * ����id��ȡ�������������
	 * @param pdID
	 * @return
	 */
	MxActivitiesPublicityContent getActivitiesPublicityContent(int pdID);

}
