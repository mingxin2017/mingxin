package com.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.bean.MxActivitiesMySpaceComment;
import com.service.IActivitiesMySpaceService;

public class MxActivitiesMySpaceAction {

	private IActivitiesMySpaceService activitiesMySpaceService;

	public IActivitiesMySpaceService getActivitiesMySpaceService() {
		return activitiesMySpaceService;
	}

	public void setActivitiesMySpaceService(
			IActivitiesMySpaceService activitiesMySpaceService) {
		this.activitiesMySpaceService = activitiesMySpaceService;
	}

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
	 * @throws IOException 
	 */
	public void DoSaveActivitiesMySpaceComment() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();//����request����
		request.setCharacterEncoding("UTF-8");
		HttpServletResponse response = ServletActionContext.getResponse(); //response���󷵻����ݸ�ǰ̨
		response.setContentType("application/json; charset=utf-8");
		
		int userId=Integer
				.parseInt(request.getParameter("userId") == null ? "-100"
						: request.getParameter("userId"));
		
		int myspaceId = Integer
				.parseInt(request.getParameter("myspaceId") == null ? "-100"
						: request.getParameter("myspaceId"));
		
		String myspaceComment=request.getParameter("txt").toString(); //���Ľ���
		
		MxActivitiesMySpaceComment activitiesMySpaceComment=new MxActivitiesMySpaceComment();
		
		activitiesMySpaceComment.setSubmitUserId(userId);
		activitiesMySpaceComment.setMyspaceId(myspaceId);
		activitiesMySpaceComment.setCommentTxt(myspaceComment);
		activitiesMySpaceComment.setState(0);
		activitiesMySpaceComment.setPraiseClickNum(0);
		activitiesMySpaceComment.setCreateDate(new Timestamp(System.currentTimeMillis()));
		
		boolean flag=activitiesMySpaceService.saveActivitiesMySpaceComment(activitiesMySpaceComment);
		
		Map<String, String> map = new HashMap<String, String>();
		if(flag){
			map.put("done", "0");//����ɹ�
		}else{
			map.put("done", "-1");//����ʧ��
		}
		
        JSONObject jsonObject = JSONObject.fromObject(map);
        response.getWriter().write(jsonObject.toString()); 
		
		//return "";
	}

}
