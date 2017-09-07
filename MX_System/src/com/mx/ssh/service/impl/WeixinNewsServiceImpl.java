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
