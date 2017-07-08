package com.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.bean.MxActivitiesMySpaceComment;
import com.service.IActivitiesMySpaceService;
import com.util.ImageMethod;

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
	
	
	/**
	 * ѹ�����ϴ�ͼƬ����
	 * @throws IOException
	 * @throws ServletException
	 */
	public void UploadImage() throws IOException, ServletException {
		
		HttpServletRequest request = ServletActionContext.getRequest();//����request����
		request.setCharacterEncoding("UTF-8");
		HttpServletResponse response = ServletActionContext.getResponse();//response���󷵻����ݸ�ǰ̨
		response.setContentType("application/json; charset=utf-8");
		String userId=request.getParameter("userId").toString();//��ȡ�û�id
		String myspaceId=request.getParameter("myspaceId").toString();//��ȡ��ռ�id
		String base64Img=request.getParameter("img").toString();
		base64Img=base64Img.replace("data:image/jpeg;base64,", "");//ȥ��base64�����õ��ļ�ͷ
		String savePath="/WeixinPages/common/uploadImg/myspaceImg/"+myspaceId+"/"+userId+"/";//����ͼƬ�ľ���·��
		String realSavePath=request.getSession().getServletContext().getRealPath(savePath);
		String imgName=ImageMethod.Base64SaveAsImage(base64Img, realSavePath);//����ͼƬ��ϵͳӦ���ļ�����
		
		
		Map<String, String> map = new HashMap<String, String>();
		String showPath=request.getContextPath() +savePath;
		
		if(imgName==null){
			map.put("done", "-1");
			map.put("imgSrc", "/");
			map.put("msg", "ͼƬ�ϴ�ʧ����!");
		}else{
			map.put("done", "0");
			map.put("imgSrc", showPath+imgName);//��ʾͼƬ���������·��
			map.put("msg", "ͼƬ�ϴ��ɹ�!");
			System.out.println("�û��ϴ�ͼƬ����"+showPath+imgName);
		}
		
        JSONObject jsonObject = JSONObject.fromObject(map);
        response.getWriter().write(jsonObject.toString()); 

	}

}
