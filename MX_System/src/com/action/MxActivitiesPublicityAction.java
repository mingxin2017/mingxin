package com.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

import com.bean.MxActivitiesPublicityData;

import com.service.IActivitiesPublicityService;

public class MxActivitiesPublicityAction {

	private IActivitiesPublicityService activitiesPublicityService;

	/**
	 * ����*���������action�е�Ĭ�ϴ����� ����
	 */
	public String execute() throws Exception {

		return null;
	}

	/**
	 * ��ȡУ�ѻ����������б�
	 */
	public String getAlumniActivitiesList() {

		HttpServletRequest request = ServletActionContext.getRequest();
		String activitiesType = request.getParameter("type");
		List<MxActivitiesPublicityData> activitiesPublicityList = activitiesPublicityService
				.getActivitiesPublicityByType(Integer.parseInt(activitiesType));

		if (activitiesPublicityList != null && activitiesType != null) {
			request.getSession().setAttribute("activitiesPublicityList",
					activitiesPublicityList);
			request.getSession().setAttribute("activitiesType",
					Integer.parseInt(activitiesType));
		}
		return "ActivitiesPublicityList";
	}

	/**
	 * ��ת�������������ϸ����ҳ
	 * @return
	 */
	public String gotoActivitiesDetail(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		List<MxActivitiesPublicityData> apList=(List<MxActivitiesPublicityData>) request.getSession().getAttribute("activitiesPublicityList");
		String pdID=request.getParameter("publicityDataId");
		if(apList!=null&&pdID!=null){
			for(int i=0;i<apList.size();i++){
				MxActivitiesPublicityData apd=apList.get(i);
				if(apd.getPublicityDataId()==Integer.parseInt(pdID)){
					request.getSession().setAttribute("ActivitiesPublicityDetail",apd);
				}
			}
		}
		return "ActivitiesPublicityDetail";
	}
	
	public IActivitiesPublicityService getActivitiesPublicityService() {
		return activitiesPublicityService;
	}

	public void setActivitiesPublicityService(
			IActivitiesPublicityService activitiesPublicityService) {
		this.activitiesPublicityService = activitiesPublicityService;
	}

}
