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
	
	
	/**
	 * 压缩并上传图片方法
	 * @throws IOException
	 * @throws ServletException
	 */
	public void UploadImage() throws IOException, ServletException {
		
		HttpServletRequest request = ServletActionContext.getRequest();//请求request对象
		request.setCharacterEncoding("UTF-8");
		HttpServletResponse response = ServletActionContext.getResponse();//response对象返回数据给前台
		response.setContentType("application/json; charset=utf-8");
		String userId=request.getParameter("userId").toString();//获取用户id
		String myspaceId=request.getParameter("myspaceId").toString();//获取活动空间id
		String base64Img=request.getParameter("img").toString();
		base64Img=base64Img.replace("data:image/jpeg;base64,", "");//去除base64中无用的文件头
		String savePath="/WeixinPages/common/uploadImg/myspaceImg/"+myspaceId+"/"+userId+"/";//保存图片的绝对路径
		String realSavePath=request.getSession().getServletContext().getRealPath(savePath);
		String imgName=ImageMethod.Base64SaveAsImage(base64Img, realSavePath);//保存图片到系统应用文件夹中
		
		
		Map<String, String> map = new HashMap<String, String>();
		String showPath=request.getContextPath() +savePath;
		
		if(imgName==null){
			map.put("done", "-1");
			map.put("imgSrc", "/");
			map.put("msg", "图片上传失败了!");
		}else{
			map.put("done", "0");
			map.put("imgSrc", showPath+imgName);//显示图片的完整相对路径
			map.put("msg", "图片上传成功!");
			System.out.println("用户上传图片至："+showPath+imgName);
		}
		
        JSONObject jsonObject = JSONObject.fromObject(map);
        response.getWriter().write(jsonObject.toString()); 

	}

}
