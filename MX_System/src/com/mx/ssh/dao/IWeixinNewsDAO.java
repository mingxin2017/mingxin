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
}
