package com.service;

import javax.servlet.http.HttpServletRequest;

import com.bean.MxNewsData;
import com.bean.MxUsersData;

/**
 * ����ҵ��
 * @author zw
 *
 */

public interface IWeixinNewsService {

	//�������
	void addNews(MxNewsData newsData);
	//����openid��ȡ�û�
	MxUsersData getUser(String open_id);
}
