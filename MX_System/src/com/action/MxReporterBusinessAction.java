package com.action;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

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
		System.out.println("apply action");
		//��ȡcode��state����ǰ��
		HttpServletRequest request = ServletActionContext.getRequest();
			try {
				request.setCharacterEncoding("UTF-8");
				//��ȡcode
				String code = request.getParameter("code");
				String state = request.getParameter("state");
				// ����Ҫ���ݵĲ���
				System.out.println(code+","+state);
				request.setAttribute("code", code);
				request.setAttribute("state", state);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		return "ReporterApply";
	}
	//��ȡС���߹���ҳ��
	public String loadReporterManage(){
		System.out.println("manage action");
		return "ReporterManage";
	}
	
	
}
