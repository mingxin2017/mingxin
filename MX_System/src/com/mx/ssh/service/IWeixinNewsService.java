package com.mx.ssh.service;

import java.util.List;

import com.mx.ssh.bean.MxNewsContent;
import com.mx.ssh.bean.MxNewsData;
import com.mx.ssh.bean.MxNewsType;
import com.mx.ssh.bean.MxRegion;
import com.mx.ssh.bean.MxSchools;
import com.mx.ssh.bean.MxUsersData;

/**
 * ����ҵ��
 * @author zw
 *
 */

public interface IWeixinNewsService {

	//�������
	void addNews(MxNewsData newsData,MxNewsContent newsContent);
	//����openid��ȡ�û�
	MxUsersData getUser(String open_id);
	/**
	 * ��ѯ���������б�
	 * @return
	 */
	List<MxNewsType> getNewsType();
	/**
	 * ��ѯѧУ�б�
	 * @return
	 */
	List<MxSchools> getSchools();
	/**
	 * ��ѯ����б�
	 * @return
	 */
	List<MxRegion> getRegion();
	/**
	 * �����û�id��ȡ����
	 */
	List<MxNewsData> getNewsByUserId(String userId);
	/**
	 * �����û�id��ѯ������������
	 */
	int getCountNewsByUserId(String userId,String state);
	/**
	 * ��������id��ѯ���ŷ���
	 * @param newsId
	 * @return
	 */
	MxNewsData getNewsById(String newsId);
	/**
	 * ��������id��ѯ���ŷ���
	 * @param newsTypeId
	 * @return
	 */
	List<MxNewsData> getNewsByNewsTypeId(String newsTypeId);
}
