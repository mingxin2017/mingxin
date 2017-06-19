package com.dao;

import java.util.List;

import com.bean.MxNewsContent;
import com.bean.MxNewsData;
import com.bean.MxNewsType;
import com.bean.MxUsersData;

/**
 * ����ҵ����dao
 * @author zw
 *
 */

public interface IWeixinNewsDAO {

	/**
     * �������
     * @param
     */ 
	public void addNews(MxNewsData newsData,MxNewsContent newsContent);
	/**
	 * ����openid��ȡ�û�
	 * @param open_id
	 * @return
	 */
	public MxUsersData getUser(String open_id);
	/**
	 * ������Ŵӱ�
	 * @param newsContent
	 */
	public void addNewsContent(MxNewsContent newsContent);
	/**
	 * ��ѯ���������б�
	 * @return
	 */
	public List<MxNewsType> getNewsType();
}
