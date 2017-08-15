package com.mx.ssh.service.impl;




import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.ssh.bean.MxNewsContent;
import com.mx.ssh.bean.MxNewsData;
import com.mx.ssh.bean.MxNewsType;
import com.mx.ssh.bean.MxRegion;
import com.mx.ssh.bean.MxSchools;
import com.mx.ssh.bean.MxUsersData;
import com.mx.ssh.dao.IWeixinNewsDAO;
import com.mx.weixin.pojo.WeixinUserInfo;
import com.mx.weixin.task.WeixinGetTokenTimerTask;
import com.mx.weixin.util.MessageUtil;
import com.mx.weixin.util.WeixinUtil;

@Service("weixinNewsService")
public class WeixinNewsServiceImpl implements com.mx.ssh.service.IWeixinNewsService{

	@Autowired
	private IWeixinNewsDAO weixinNewsDAO;//����ע���û�dao

	public IWeixinNewsDAO getWeixinNewsDAO() {
		return weixinNewsDAO;
	}

	public void setWeixinNewsDAO(IWeixinNewsDAO weixinNewsDAO) {
		this.weixinNewsDAO = weixinNewsDAO;
	}

	//��������
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