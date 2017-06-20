package com.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bean.MxNewsContent;
import com.bean.MxNewsData;
import com.bean.MxNewsType;
import com.bean.MxRegion;
import com.bean.MxSchools;
import com.bean.MxUsersData;

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
}
