package com.action;

import com.service.IReporterBusinessService;
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
    private IReporterBusinessService reporterBusinessService;//С����ҵ��
	public IWeixinNewsService getWeixinNewsService() {
		return weixinNewsService;
	}

	public void setWeixinNewsService(IWeixinNewsService weixinNewsService) {
		this.weixinNewsService = weixinNewsService;
	}
	
	public IReporterBusinessService getReporterBusinessService() {
		return reporterBusinessService;
	}

	public void setReporterBusinessService(
			IReporterBusinessService reporterBusinessService) {
		this.reporterBusinessService = reporterBusinessService;
	}
	//��ȡС��������ҳ��
	public String loadReporterApply(){
		return "ReporterApply";
	}
	//��ȡС���߹���ҳ��
	public String loadReporterManage(){
		System.out.println("manage action");
		return "ReporterManage";
	}
	
	
}
