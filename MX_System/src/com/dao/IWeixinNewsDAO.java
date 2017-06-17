package com.dao;

import com.bean.MxNewsData;
import com.bean.MxUsersData;

/**
 * 新闻业务类dao
 * @author zw
 *
 */

public interface IWeixinNewsDAO {

	/**
     * 添加新闻
     * @param
     */ 
	public void addNews(MxNewsData newsData);
	/**
	 * 根据openid获取用户
	 * @param open_id
	 * @return
	 */
	public MxUsersData getUser(String open_id);
}
