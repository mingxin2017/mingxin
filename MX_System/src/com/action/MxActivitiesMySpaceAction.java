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
	 * @throws UnsupportedEncodingException 
	 */
	public String DoSaveActivitiesMySpaceComment() throws UnsupportedEncodingException{
		HttpServletRequest request = ServletActionContext.getRequest();//请求request对象
		request.setCharacterEncoding("UTF-8");
		HttpServletResponse response = ServletActionContext.getResponse(); //response对象返回数据给前台
		response.setContentType("application/json; charset=utf-8");
		
		int myspaceId = Integer
				.parseInt(request.getParameter("myspaceId") == null ? "-100"
						: request.getParameter("myspaceId"));
		
		String myspaceComment=new String(request.getParameter("txtEncode").getBytes("ISO-8859-1"), "UTF-8"); //中文解码
		
		
		
		Map<String, String> map = new HashMap<String, String>();
		/*if(imgName==null){
			map.put("done", "-1");
			map.put("imgSrc", showPath+"nofound.jpg");
			map.put("msg", "图片上传失败了!");
		}else{
			map.put("done", "0");
			map.put("imgSrc", showPath+imgName);//显示图片的完整相对路径
			map.put("msg", "图片上传成功!");
			System.out.println("用户上传图片至："+showPath+imgName);
		}*/
		
        JSONObject jsonObject = JSONObject.fromObject(map);
       // response.getWriter().write(jsonObject.toString()); 
		
		return "";
	}

}
