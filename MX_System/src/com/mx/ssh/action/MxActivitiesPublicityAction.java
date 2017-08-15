package com.mx.ssh.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;


import com.mx.ssh.bean.MxActivitiesPublicityContent;
import com.mx.ssh.bean.MxActivitiesPublicityData;
import com.mx.ssh.bean.MxUsersData;
import com.mx.ssh.service.IActivitiesPublicityService;
import com.mx.weixin.pojo.SNSUserInfo;
import com.mx.weixin.util.WeixinSignUtil;
import com.mx.weixin.util.WeixinUtil;

public class MxActivitiesPublicityAction {

	private IActivitiesPublicityService activitiesPublicityService;

	/**
	 * ����*���������action�е�Ĭ�ϴ������� ����
	 */
	public String execute() throws Exception {

		return null;
	}

	/**
	 * ��ȡУ�ѻ����������б�
	 */
	public String getActivitiesPublicityList() {

		HttpServletRequest request = ServletActionContext.getRequest();
		
		/**
		 * �˷�����֤΢���û��Ƿ��Ѿ���ע���ں�
		 * ����struts.xml������noFucus��error����Ӧҳ��
		 */
		
		
		SNSUserInfo userInfo=WeixinUtil.validateWeixinWebUser(request);
//		
//		if(userInfo==null){
//			return "noFocus";
//		}
		
		String activitiesType = request.getParameter("type");
		List<MxActivitiesPublicityData> activitiesPublicityList = activitiesPublicityService
				.getActivitiesPublicityByType(Integer.parseInt(activitiesType));

		if (activitiesPublicityList != null && activitiesType != null) {
			request.getSession().setAttribute("activitiesPublicityList",
					activitiesPublicityList);
			request.getSession().setAttribute("activitiesType",
					Integer.parseInt(activitiesType));
			request.getSession().setAttribute("appID",WeixinSignUtil.AppID);
			request.getSession().setAttribute("serverUrl",WeixinSignUtil.serverUrl);
		}
		return "ActivitiesPublicityList";
	}

	/**
	 * ��ת�������������ϸ����ҳ
	 * @return
	 */
	public String gotoActivitiesDetail(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		int pdID=Integer.parseInt(request.getParameter("publicityDataId"));
		MxActivitiesPublicityContent apd=activitiesPublicityService.getActivitiesPublicityContent(pdID);
			
					request.getSession().setAttribute("ActivitiesPublicityDetail",apd);
				
		return "ActivitiesPublicityDetail";
	}
	
	public String gotoArtEditor(){
		return "ArtEditor";
	}
	
	
	public IActivitiesPublicityService getActivitiesPublicityService() {
		return activitiesPublicityService;
	}

	public void setActivitiesPublicityService(
			IActivitiesPublicityService activitiesPublicityService) {
		this.activitiesPublicityService = activitiesPublicityService;
	}

}