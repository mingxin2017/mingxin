package com.service.impl;




import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.bean.MxNewsContent;
import com.bean.MxNewsData;
import com.bean.MxNewsType;
import com.bean.MxRegion;
import com.bean.MxSchools;
import com.bean.MxUsersData;
import com.dao.IWeixinNewsDAO;
import com.weixin.pojo.WeixinUserInfo;
import com.weixin.task.WeixinGetTokenTimerTask;
import com.weixin.util.MessageUtil;
import com.weixin.util.WeixinUtil;


public class WeixinNewsServiceImpl implements com.service.IWeixinNewsService{

	private IWeixinNewsDAO weixinNewsDAO;//����ע���û�dao

	public IWeixinNewsDAO getWeixinNewsDAO() {
		return weixinNewsDAO;
	}

	public void setWeixinNewsDAO(IWeixinNewsDAO weixinNewsDAO) {
		this.weixinNewsDAO = weixinNewsDAO;
	}

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


}
