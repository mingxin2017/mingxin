package com.action;

import com.service.IWeixinNewsService;

/**
 * 小记者相关业务处理
 * 新闻类、小记者管理（新闻、团队、评分）
 * @author zw
 *
 */
public class MxReporterBusinessAction {
	
	private static final long serialVersionUID = -1627548805862485475L;

	private IWeixinNewsService weixinNewsService;//微信新闻类业务

	public IWeixinNewsService getWeixinNewsService() {
		return weixinNewsService;
	}

	public void setWeixinNewsService(IWeixinNewsService weixinNewsService) {
		this.weixinNewsService = weixinNewsService;
	}
	
	//获取小记者管理页面
	public String loadReporterManage(){
		return "ReporterManage";
	}
	
	
}
