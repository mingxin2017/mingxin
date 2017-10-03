package com.mx.ssh.service.impl;




import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.mx.ssh.dao.IWeixinNewsDAO;

@Service("weixinNewsService")
public class WeixinNewsServiceImpl implements com.mx.ssh.service.IWeixinNewsService{

	@Autowired
	private IWeixinNewsDAO weixinNewsDAO;//����ע���û�dao

	//�������
	public void addNews(MxNewsData newsData,MxNewsContent newsContent) {
		weixinNewsDAO.addNews(newsData,newsContent);
	}
    //����openid��ȡ�û�
	public MxUsersData getUser(String open_id) {
		return weixinNewsDAO.getUser(open_id);
	}
    //��ѯ���������б�
	public List<MxNewsType> getNewsType() {
		return weixinNewsDAO.getNewsType();
	}
    //��ѯѧУ
	public List<MxSchools> getSchools() {
		return weixinNewsDAO.getSchools();
	}
    //��ѯ���
	public List<MxRegion> getRegion() {
		return weixinNewsDAO.getRegion();
	}
    //�����û�id��ȡ����
	public List<MxNewsData> getNewsByUserId(String userId) {
		return weixinNewsDAO.getNewsByUserId(userId);
	}
	//�����û�id��ѯ������������
	public int getCountNewsByUserId(String userId,String state) {
		return weixinNewsDAO.getCountNewsByUserId(userId,state);
	}
    //��������id��ѯ���ŷ���
	public MxNewsData getNewsById(String newsId) {
		return weixinNewsDAO.getNewsById(newsId);
	}
	//��������id��ѯ���ŷ���
	public List<MxNewsData> getNewsByNewsTypeId(String newsTypeId) {
		return weixinNewsDAO.getNewsByNewsTypeId(newsTypeId);
	}
	//��������
	public void addComment(MxNewsComment comment) {
		weixinNewsDAO.addComment(comment);
	}
	//��������id��ѯ���۷���
	public List<MxNewsComment> getCommentByNewsId(String newsId) {
		return weixinNewsDAO.getCommentByNewsId(newsId);
	}
	//����id��ѯ������������
	public int getCountCommentByNewsId(String newsId) {
		return weixinNewsDAO.getCountCommentByNewsId(newsId);
	}
	//ɾ������
	public void delComment(MxNewsComment comment) {
		weixinNewsDAO.delComment(comment);
	}
	//�������۵ĵ���
	public int addCommentPraise(MxNewsCommentPraise commentPraise) {
		weixinNewsDAO.addCommentPraise(commentPraise);
		return weixinNewsDAO.getCountCommentPraiseByCommentId(commentPraise.getCommentId().toString());
		
	}
	//ɾ�����۵ĵ���
	public int delCommentPraise(MxNewsCommentPraise commentPraise) {
		weixinNewsDAO.delCommentPraise(commentPraise);
		return weixinNewsDAO.getCountCommentPraiseByCommentId(commentPraise.getCommentId().toString());
	}
	//��������id��ѯ����������
	public int getCountCommentPraiseByCommentId(String commentId) {
		return weixinNewsDAO.getCountCommentPraiseByCommentId(commentId);
	}
	//��������id���û�id��ѯ����״̬����
	public int getCommentPraiseById(String commentId, String userId) {
		return weixinNewsDAO.getCommentPraiseById(commentId, userId);
	}
	public int addPraise(MxNewsPraise praise) {
		weixinNewsDAO.addPraise(praise);
		return weixinNewsDAO.getCountPraiseByNewsId(praise.getNewsId().toString());
	}
	public int delPraise(MxNewsPraise praise) {
		weixinNewsDAO.delPraise(praise);
		return weixinNewsDAO.getCountPraiseByNewsId(praise.getNewsId().toString());
	}
	public int getCountPraiseByNewsId(String newsId) {
		return weixinNewsDAO.getCountPraiseByNewsId(newsId);
	}
	//��������id��ѯ���۷���
	public MxNewsComment getCommentById(String commentId) {
		return weixinNewsDAO.getCommentById(commentId);
	}
	//�������۵Ļظ�
	public void addCommentBack(MxNewsCommentBack commentBack) {
		weixinNewsDAO.addCommentBack(commentBack);
		
	}
	//��������id��ѯ���ۻظ�
	public List<MxNewsCommentBack> getCommentBackByCommentId(String commentId) {
		return weixinNewsDAO.getCommentBackByCommentId(commentId);
	}
	//�����û�id��ѯ���۷���
	public List<MxNewsComment> getCommentByUserId(String userId) {
		return weixinNewsDAO.getCommentByUserId(userId);
	}
	

}
