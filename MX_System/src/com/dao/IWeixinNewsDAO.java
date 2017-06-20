package com.dao;

import java.util.List;

import com.bean.MxNewsContent;
import com.bean.MxNewsData;
import com.bean.MxNewsType;
import com.bean.MxRegion;
import com.bean.MxSchools;
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
	public void addNews(MxNewsData newsData,MxNewsContent newsContent);
	/**
	 * 根据openid获取用户
	 * @param open_id
	 * @return
	 */
	public MxUsersData getUser(String open_id);
	/**
	 * 查询新闻类型列表
	 * @return
	 */
	public List<MxNewsType> getNewsType();
	/**
	 * 获取学校
	 * @return
	 */
	public List<MxSchools> getSchools();
	/**
	 * 获取地区
	 * @return
	 */
	public List<MxRegion> getRegion();
}
