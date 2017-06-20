package com.dao;

import java.util.List;

import com.bean.MxNewsContent;
import com.bean.MxNewsData;
import com.bean.MxNewsType;
import com.bean.MxRegion;
import com.bean.MxSchools;
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
	 * ��ѯ���������б�
	 * @return
	 */
	public List<MxNewsType> getNewsType();
	/**
	 * ��ȡѧУ
	 * @return
	 */
	public List<MxSchools> getSchools();
	/**
	 * ��ȡ����
	 * @return
	 */
	public List<MxRegion> getRegion();
}
