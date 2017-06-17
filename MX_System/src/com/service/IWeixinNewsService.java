package com.service;

import javax.servlet.http.HttpServletRequest;

import com.bean.MxNewsData;
import com.bean.MxUsersData;

/**
 * 新闻业务
 * @author zw
 *
 */

public interface IWeixinNewsService {

	//添加新闻
	void addNews(MxNewsData newsData);
	//根据openid获取用户
	MxUsersData getUser(String open_id);
}
