package com.mx.ssh.service;

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
	/**
	 * ��������
	 * @param comment
	 */
	void addComment(MxNewsComment comment);
	/**
	 * ��������id��ѯ���۷���
	 * @param newsId
	 * @return
	 */
	List<MxNewsComment> getCommentByNewsId(String newsId);
	//����id��ѯ������������
	int getCountCommentByNewsId(String newsId);
	//ɾ������
	void delComment(MxNewsComment comment);
	//�������۵ĵ���
	public int addCommentPraise(MxNewsCommentPraise commentPraise);
	//ɾ�����۵ĵ���
	public int delCommentPraise(MxNewsCommentPraise commentPraise);
	//��������id��ѯ����������
	public int getCountCommentPraiseByCommentId(String commentId);
	//��������id���û�id��ѯ����״̬����
	public int getCommentPraiseById(String commentId,String userId);
	//�������µ���
	public int addPraise(MxNewsPraise praise);
	//ɾ�����µ���
	public int delPraise(MxNewsPraise praise);
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
