package com.action;

import com.service.IWeixinNewsService;

/**
 * С�������ҵ����
 * �����ࡢС���߹������š��Ŷӡ����֣�
 * @author zw
 *
 */
public class MxReporterBusinessAction {
	
	private static final long serialVersionUID = -1627548805862485475L;

	private IWeixinNewsService weixinNewsService;//΢��������ҵ��

	public IWeixinNewsService getWeixinNewsService() {
		return weixinNewsService;
	}

	public void setWeixinNewsService(IWeixinNewsService weixinNewsService) {
		this.weixinNewsService = weixinNewsService;
	}
	
	//��ȡС���߹���ҳ��
	public String loadReporterManage(){
		return "ReporterManage";
	}
	
	
}
