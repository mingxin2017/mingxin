package com.action;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

public class MxActivitiesMySpaceAction {

	

	/**
	 * ����*��ռ�action�е�Ĭ�ϴ����� ����
	 */
	public String execute() throws Exception {

		return null;
	}

	/**
	 * ��ת����ռ�ҳ�������
	 */
	public String gotoActivitiesMySpaceMain() {

		return "activitiesMySpaceMain";
	}
	
	/**
	 * ��ȡ��ռ����������б�
	 */
	public String getActivitiesMySpaceCommentList() {

		return "activitiesMySpaceCommentList";
	}

	/**
	 * ��ȡ��ռ��ز��б�
	 */
	public String getActivitiesMySpaceMaterialList() {

		return "activitiesMySpaceMaterialList";
	}
	
	/**
	 * ��ȡ��ռ��û�
	 */
	public String getActivitiesMySpaceUsersList() {

		return "activitiesMySpaceUsersList";
	}
	
	/**
	 * ��ȡ��ռ��ҵ������б�
	 */
	public String getActivitiesMySpaceMine() {

		return "activitiesMySpaceMine";
	}
	
	/**
	 * �����ռ���������
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String DoSaveActivitiesMySpaceComment() throws UnsupportedEncodingException{
		HttpServletRequest request = ServletActionContext.getRequest();//����request����
		request.setCharacterEncoding("UTF-8");
		HttpServletResponse response = ServletActionContext.getResponse(); //response���󷵻����ݸ�ǰ̨
		response.setContentType("application/json; charset=utf-8");
		
		int myspaceId = Integer
				.parseInt(request.getParameter("myspaceId") == null ? "-100"
						: request.getParameter("myspaceId"));
		
		String myspaceComment=new String(request.getParameter("txtEncode").getBytes("ISO-8859-1"), "UTF-8"); //���Ľ���
		
		
		
		Map<String, String> map = new HashMap<String, String>();
		/*if(imgName==null){
			map.put("done", "-1");
			map.put("imgSrc", showPath+"nofound.jpg");
			map.put("msg", "ͼƬ�ϴ�ʧ����!");
		}else{
			map.put("done", "0");
			map.put("imgSrc", showPath+imgName);//��ʾͼƬ���������·��
			map.put("msg", "ͼƬ�ϴ��ɹ�!");
			System.out.println("�û��ϴ�ͼƬ����"+showPath+imgName);
		}*/
		
        JSONObject jsonObject = JSONObject.fromObject(map);
       // response.getWriter().write(jsonObject.toString()); 
		
		return "";
	}

}
