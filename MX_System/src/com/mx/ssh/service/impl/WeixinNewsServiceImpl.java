package com.mx.ssh.service.impl;




import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.ssh.bean.MxNewsContent;
import com.mx.ssh.bean.MxNewsData;
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


}
