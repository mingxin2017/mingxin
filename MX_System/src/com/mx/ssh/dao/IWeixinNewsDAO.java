package com.mx.ssh.dao;

import java.util.List;

import com.mx.ssh.bean.MxNewsComment;
import com.mx.ssh.bean.MxNewsCommentBack;
import com.mx.ssh.bean.MxNewsCommentPraise;
import com.mx.ssh.bean.MxNewsContent;
import com.mx.ssh.bean.MxNewsData;
import com.mx.ssh.bean.MxNewsPraise;
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
	
	/**
	 * ��������
	 * @param comment
	 */
	public void addComment(MxNewsComment comment);
	//ɾ������
	public void delComment(MxNewsComment comment);
	/**
	 * ��������id��ѯ���۷���
	 * @param newsId
	 * @return
	 */
	public List<MxNewsComment> getCommentByNewsId(String newsId);
	//����id��ѯ������������
	public int getCountCommentByNewsId(String newsId);
	//�������۵ĵ���
	public void addCommentPraise(MxNewsCommentPraise commentPraise);
	//ɾ�����۵ĵ���
	public void delCommentPraise(MxNewsCommentPraise commentPraise);
	//��������id��ѯ����������
	public int getCountCommentPraiseByCommentId(String commentId);
	//��������id���û�id��ѯ����״̬����
	public int getCommentPraiseById(String commentId,String userId);
	//�������µ���
	public void addPraise(MxNewsPraise praise);
	//ɾ�����µ���
	public void delPraise(MxNewsPraise praise);
	//��������id��ѯ����������
	public int getCountPraiseByNewsId(String newsId);
	//��������id��ѯ���۷���
	public MxNewsComment getCommentById(String commentId);
	//�������۵Ļظ�
	public void addCommentBack(MxNewsCommentBack commentBack);
	//��������id��ѯ���ۻظ�
	public List<MxNewsCommentBack> getCommentBackByCommentId(String commentId);
	//�����û�id��ѯ���۷���
	public List<MxNewsComment> getCommentByUserId(String userId);
}
