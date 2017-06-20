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

	private IWeixinNewsDAO weixinNewsDAO;//依赖注入用户dao

	public IWeixinNewsDAO getWeixinNewsDAO() {
		return weixinNewsDAO;
	}

	public void setWeixinNewsDAO(IWeixinNewsDAO weixinNewsDAO) {
		this.weixinNewsDAO = weixinNewsDAO;
	}

	//添加新闻
	public void addNews(MxNewsData newsData,MxNewsContent newsContent) {
		weixinNewsDAO.addNews(newsData,newsContent);
	}
    //根据openid获取用户
	public MxUsersData getUser(String open_id) {
		return weixinNewsDAO.getUser(open_id);
	}
    //查询新闻类型列表
	public List<MxNewsType> getNewsType() {
		return weixinNewsDAO.getNewsType();
	}
    //查询学校
	public List<MxSchools> getSchools() {
		return weixinNewsDAO.getSchools();
	}
    //查询乡村
	public List<MxRegion> getRegion() {
		return weixinNewsDAO.getRegion();
	}


}
