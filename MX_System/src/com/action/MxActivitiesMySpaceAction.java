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
	 * 　　*活动空间action中的默认处理方法 　　
	 */
	public String execute() throws Exception {

		return null;
	}

	/**
	 * 跳转到活动空间页面主框架
	 */
	public String gotoActivitiesMySpaceMain() {

		return "activitiesMySpaceMain";
	}
	
	/**
	 * 获取活动空间评论内容列表
	 */
	public String getActivitiesMySpaceCommentList() {

		return "activitiesMySpaceCommentList";
	}

	/**
	 * 获取活动空间素材列表
	 */
	public String getActivitiesMySpaceMaterialList() {

		return "activitiesMySpaceMaterialList";
	}
	
	/**
	 * 获取活动空间用户
	 */
	public String getActivitiesMySpaceUsersList() {

		return "activitiesMySpaceUsersList";
	}
	
	/**
	 * 获取活动空间我的内容列表
	 */
	public String getActivitiesMySpaceMine() {

		return "activitiesMySpaceMine";
	}
	
	/**
	 * 保存活动空间讨论内容
	 * @return
	 * @throws IOException 
	 */
	public void DoSaveActivitiesMySpaceComment() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();//请求request对象
		request.setCharacterEncoding("UTF-8");
		HttpServletResponse response = ServletActionContext.getResponse(); //response对象返回数据给前台
		response.setContentType("application/json; charset=utf-8");
		
		int userId=Integer
				.parseInt(request.getParameter("userId") == null ? "-100"
						: request.getParameter("userId"));
		
		int myspaceId = Integer
				.parseInt(request.getParameter("myspaceId") == null ? "-100"
						: request.getParameter("myspaceId"));
		
		String myspaceComment=request.getParameter("txt").toString(); //中文解码
		
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
			map.put("done", "0");//保存成功
		}else{
			map.put("done", "-1");//保存失败
		}
		
        JSONObject jsonObject = JSONObject.fromObject(map);
        response.getWriter().write(jsonObject.toString()); 
		
		//return "";
	}

}
