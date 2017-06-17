package com.action;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.service.IReporterBusinessService;
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
    private IReporterBusinessService reporterBusinessService;//小记者业务
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
	//获取小记者申请页面
	public String loadReporterApply(){
		System.out.println("apply action");
		//获取code和state传回前端
		HttpServletRequest request = ServletActionContext.getRequest();
			try {
				request.setCharacterEncoding("UTF-8");
				//获取code
				String code = request.getParameter("code");
				String state = request.getParameter("state");
				// 设置要传递的参数
				System.out.println(code+","+state);
				request.setAttribute("code", code);
				request.setAttribute("state", state);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		return "ReporterApply";
	}
	//获取小记者管理页面
	public String loadReporterManage(){
		System.out.println("manage action");
		return "ReporterManage";
	}
	
	
}
