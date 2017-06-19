package com.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bean.MxNewsContent;
import com.bean.MxNewsData;
import com.bean.MxNewsType;
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
	 * �����������
	 * @param newsContent
	 */
	void addNewsContent(MxNewsContent newsContent);
	/**
	 * ��ѯ���������б�
	 * @return
	 */
	List<MxNewsType> getNewsType();
}
