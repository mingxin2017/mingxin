package com.mx.ssh.dao;

import java.util.List;

import com.mx.ssh.bean.MxNewsContent;
import com.mx.ssh.bean.MxNewsData;
import com.mx.ssh.bean.MxNewsType;
import com.mx.ssh.bean.MxRegion;
import com.mx.ssh.bean.MxSchools;
import com.mx.ssh.bean.MxUsersData;

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
	/**
	 * �����û�id��ȡ����
	 */
	public List<MxNewsData> getNewsByUserId(String userId);
	/**
	 * �����û�id��ѯ������������
	 */
	public int getCountNewsByUserId(String userId,String state);
	/**
	 * ��������id��ѯ���ŷ���
	 * @param newsId
	 * @return
	 */
	public MxNewsData getNewsById(String newsId);
	/**
	 * ��������id��ѯ���ŷ���
	 * @param newsTypeId
	 * @return
	 */
	public List<MxNewsData> getNewsByNewsTypeId(String newsTypeId);
}
