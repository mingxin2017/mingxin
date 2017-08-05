package com.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.bean.MxActivitiesData;
import com.bean.MxActivitiesMySpaceComment;
import com.bean.MxActivitiesMySpaceData;
import com.bean.MxActivitiesMySpaceInviteCode;
import com.bean.MxActivitiesMySpaceMaterial;
import com.bean.MxActivitiesMySpaceUsers;
import com.bean.MxUsersData;
import com.bean.sysBean.ActivitiesUserMySpaceMaterial;
import com.bean.sysBean.ActivitiesUserMySpaceMine;
import com.service.IActivitiesMySpaceService;
import com.service.IUserService;
import com.util.ImageMethod;
import com.util.PasswordUtil;
import com.weixin.pojo.SNSUserInfo;
import com.weixin.util.WeixinSignUtil;
import com.weixin.util.WeixinUtil;

public class MxActivitiesMySpaceAction {

	//依赖注入内容
	private IActivitiesMySpaceService activitiesMySpaceService;
	private IUserService userService;
	public IUserService getUserService() {
		return userService;
	}
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
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
	 * 跳转到用户个人空间列表页面
	 * 
	 */
	public String gotoActivitiesMySpaceList() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		SNSUserInfo snsUserInfo=WeixinUtil.validateWeixinWebUser(request);
		if(snsUserInfo==null){
			return "noFocus";
		}
		MxUsersData userInfo=userService.getUserByOpenId(snsUserInfo.getOpenId());//根据openId获取用户系统信息
		
		if(userInfo==null){
			//没有用户信息，说明未关注公众号
			return "noFocus";
		}else{
			List<MxActivitiesMySpaceUsers> userMySpaceList = activitiesMySpaceService.getMySpaceListByUserId(userInfo.getUserId());
			//if(userMySpaceList.size()==0){
				//未参加过任何活动
				//return "currentActivitiesList";//跳转到当前正在报名的活动列表
			//}else{
				List<MxActivitiesMySpaceData> userMySpaceDataList=activitiesMySpaceService.getMySpaceListBySpceIds(userMySpaceList);
				request.setAttribute("userMySpaceDataList",userMySpaceDataList);//返回对象列表给前台
				request.getSession().setAttribute("userInfo",userInfo);//设置用户信息session
				
				return "activitiesMySpaceList";
			//}
		}
		
		
	}

	/**
	 * 跳转到活动空间页面主框架
	 */
	public String gotoActivitiesMySpaceMain() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		int myspaceId=Integer.parseInt(request.getParameter("myspaceId"));
		request.getSession().setAttribute("myspaceId",myspaceId);//缓存用户查看的空间id
		MxUsersData userInfo=(MxUsersData) request.getSession().getAttribute("userInfo");
		int userId=-11;
		if(userInfo!=null){
			userId=userInfo.getUserId();
		}
		boolean b=activitiesMySpaceService.validateMySpaceUser(myspaceId,userId);
		if(b==true){
			return "activitiesMySpaceMain";
		}else{
			return "error";
		}
		
	}

	/**
	 * 获取活动空间评论内容列表
	 */
	public String getActivitiesMySpaceCommentList() {
		HttpServletRequest request = ServletActionContext.getRequest();
		//if(userMySpaceCommentList==null||userMySpaceCommentList.size()==0){
		List<MxActivitiesMySpaceComment> userMySpaceCommentList=activitiesMySpaceService.getUserMySpaceCommontList((Integer)request.getSession().getAttribute("myspaceId"));
		//}
		request.getSession().setAttribute("userMySpaceCommentList",userMySpaceCommentList);//缓存用户查看的空间讨论
		return "activitiesMySpaceCommentList";
	}

	/**
	 * 获取活动空间素材列表
	 */
	public String getActivitiesMySpaceMaterialList() {
		HttpServletRequest request = ServletActionContext.getRequest();
		List<ActivitiesUserMySpaceMaterial> userMySpaceMaterialList=activitiesMySpaceService.getUserMySpaceMaterialList((Integer)request.getSession().getAttribute("myspaceId"));
		request.getSession().setAttribute("userMySpaceMaterialList",userMySpaceMaterialList);//缓存用户查看的空间素材列表
		return "activitiesMySpaceMaterialList";
	}

	/**
	 * 获取活动空间用户
	 */
	public String getActivitiesMySpaceUsersList() {
		HttpServletRequest request = ServletActionContext.getRequest();
		List<MxActivitiesMySpaceUsers> mySpaceUsersList=activitiesMySpaceService.getMySpaceUsersList((Integer)request.getSession().getAttribute("myspaceId"));
		request.getSession().setAttribute("mySpaceUsersList",mySpaceUsersList);//缓存用户查看的空间用户列表
		return "activitiesMySpaceUsersList";
		//return "testWebUploader";
	}

	/**
	 * 获取活动空间我的内容列表
	 */
	public String getActivitiesMySpaceMine() {
		HttpServletRequest request = ServletActionContext.getRequest();
		MxUsersData userInfo=(MxUsersData) request.getSession().getAttribute("userInfo");
		ActivitiesUserMySpaceMine myspaceUserMine=activitiesMySpaceService.getMySpaceUserMine(userInfo.getUserId(),(Integer)request.getSession().getAttribute("myspaceId"));
		request.getSession().setAttribute("myspaceUserMine",myspaceUserMine);//缓存用户查看的空间个人信息
		return "activitiesMySpaceMine";
	}

	/**
	 * 保存活动空间讨论内容
	 */
	public void DoSaveActivitiesMySpaceComment() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();// 请求request对象
		request.setCharacterEncoding("UTF-8");
		HttpServletResponse response = ServletActionContext.getResponse(); // response对象返回数据给前台
		response.setContentType("application/json; charset=utf-8");

		int userId = Integer
				.parseInt(request.getParameter("userId") == null ? "-100"
						: request.getParameter("userId"));

		int myspaceId = Integer
				.parseInt(request.getParameter("myspaceId") == null ? "-100"
						: request.getParameter("myspaceId"));

		String myspaceComment = request.getParameter("txt").toString(); // 中文解码

		MxActivitiesMySpaceComment activitiesMySpaceComment = new MxActivitiesMySpaceComment();
		MxUsersData user=new MxUsersData();
		user.setUserId(userId);
		activitiesMySpaceComment.setMxUsersData(user);
		activitiesMySpaceComment.setMyspaceId(myspaceId);
		activitiesMySpaceComment.setCommentTxt(myspaceComment);
		activitiesMySpaceComment.setState(0);
		activitiesMySpaceComment.setPraiseUserIds("");
		activitiesMySpaceComment.setPraiseClickNum(0);
		activitiesMySpaceComment.setCreateDate(new Timestamp(System
				.currentTimeMillis()));

		boolean flag = activitiesMySpaceService
				.saveActivitiesMySpaceComment(activitiesMySpaceComment);

		Map<String, String> map = new HashMap<String, String>();
		if (flag) {
			map.put("done", "0");// 保存成功
		} else {
			map.put("done", "-1");// 保存失败
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		response.getWriter().write(jsonObject.toString());

	}

	/*
	 * 生成活动空间邀请链接
	 */
	public void DoCreateInviteCodeUrl() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();// 请求request对象
		request.setCharacterEncoding("UTF-8");
		HttpServletResponse response = ServletActionContext.getResponse();// response对象返回数据给前台
		response.setContentType("application/json; charset=utf-8");
		String initiator_userId = request.getParameter("initiator_userId").toString();// 获取用户id
		String myspaceId = request.getParameter("myspaceId").toString();// 获取活动空间id
		MxActivitiesData activitiesData=activitiesMySpaceService.getActivityByMyspaceId(Integer.parseInt(myspaceId));
		String inviteCodeStr=PasswordUtil.createPWD(6);//生成8位随机验证码
		MxActivitiesMySpaceInviteCode inviteCode=new MxActivitiesMySpaceInviteCode(activitiesData,Integer.parseInt(myspaceId),Integer.parseInt(initiator_userId), inviteCodeStr, 0,-1,new Timestamp(System.currentTimeMillis()));
		boolean b=activitiesMySpaceService.addActivityInviteCode(inviteCode);
		
		//生成的邀请链接WeixinSignUtil.serverUrl+"activitiesMySpace!validateInviteCode.action?activityId=1&myspaceId=5&inviteCode=jdfjsj"
		
		
		Map<String, String> map = new HashMap<String, String>();
		if (b==false) {
			map.put("done", "-1");
			map.put("inviteCodeUrl", "");
			map.put("msg", "生成活动邀请链接失败!");
		} else {

			//String inviteUrl=WeixinSignUtil.serverUrl+"activitiesMySpace!validateInviteCode.action?activityId="
			//			+activitiesData.getActivitiesId()+"&myspaceId="+myspaceId+"&inviteCode="+inviteCodeStr;
			//String inviteUrl=inviteCodeStr;
			map.put("done", "0");
			map.put("inviteCode", inviteCodeStr);// 显示图片的完整相对路径
			map.put("msg", "成功，该链接有效时间为24小时！");
			System.out.println("成功生成活动邀请码！");
		}
		JSONObject jsonObject = JSONObject.fromObject(map);
		response.getWriter().write(jsonObject.toString());
		
	}
	
	/*
	 * 验证活动申请码的有效性
	 */
	public void validateInviteCode() throws IOException{
		//获取邀请码信息，判断邀请码是否存在，判断邀请码是否过期

		HttpServletRequest request = ServletActionContext.getRequest();// 请求request对象
		request.setCharacterEncoding("UTF-8");
		HttpServletResponse response = ServletActionContext.getResponse();// response对象返回数据给前台
		response.setContentType("application/json; charset=utf-8");
		String userId = request.getParameter("userId").toString();// 获取用户id
		String inviteCode = request.getParameter("inviteCode").toString();// 获取活动空间验证码
		int b=activitiesMySpaceService.validateInviteCode(inviteCode,Integer.parseInt(userId));
		
		//System.out.println("//////"+b);
		
		//生成的邀请链接WeixinSignUtil.serverUrl+"activitiesMySpace!validateInviteCode.action?activityId=1&myspaceId=5&inviteCode=jdfjsj"
		
		Map<String, String> map = new HashMap<String, String>();
		if (b==-1) {
			map.put("done", "-1");
			map.put("msg", "系统错误,请联系平台管理员!");
		} else if(b==-10){
			map.put("done", "-10");
			map.put("msg", "验证码不存在!");
		}else if(b==-11){
			map.put("done", "-11");
			map.put("msg", "验证码已过期，请联系活动组织者!");
		}else if(b==-12){
			map.put("done", "-12");
			map.put("msg", "您已参加该活动!");
		}else if(b>0){
			
			MxActivitiesMySpaceData userMySpace=activitiesMySpaceService.getMySpaceBySpaceId(b);
			
			map.put("done", "0");
			map.put("msg", "参加活动成功！");
			map.put("liData","<li  class='mui-table-view-cell mui-media'>" +
					"<a href='activitiesMySpace!gotoActivitiesMySpaceMain.action?myspaceId="+userMySpace.getMyspaceId()+"'>" +
							"<img  class='mui-media-object mui-pull-left'  src='"+userMySpace.getCoverImageUrl()+"'>" +
									"<div class='mui-media-body' style='color:black' >"+userMySpace.getMyspaceName()+
											"<p class='mui-ellipsis' style='color:#87CEFA' >" +
													userMySpace.getDescribe()+"</p></div></a></li>");
			
		
			System.out.println("用户成功参加活动！");
		}
		JSONObject jsonObject = JSONObject.fromObject(map);
		response.getWriter().write(jsonObject.toString());
		
	}
	
	
	
	/*
	 * 压缩并上传图片方法
	 */
	public void UploadImage() throws IOException, ServletException {

		HttpServletRequest request = ServletActionContext.getRequest();// 请求request对象
		request.setCharacterEncoding("UTF-8");
		HttpServletResponse response = ServletActionContext.getResponse();// response对象返回数据给前台
		response.setContentType("application/json; charset=utf-8");
		String userId = request.getParameter("userId").toString();// 获取用户id
		String myspaceId = request.getParameter("myspaceId").toString();// 获取活动空间id
		String base64Img = request.getParameter("img").toString();
		base64Img = base64Img.replace("data:image/jpeg;base64,", "");// 去除base64中无用的文件头
		String savePath = "/WeixinPages/common/uploadImg/myspaceImg/"
				+ myspaceId + "/" + userId + "/";// 保存图片的绝对路径
		String realSavePath = request.getSession().getServletContext()
				.getRealPath(savePath);
		String imgName = ImageMethod.Base64SaveAsImage(base64Img, realSavePath);// 保存图片到系统应用文件夹中

		Map<String, String> map = new HashMap<String, String>();
		String showPath = request.getContextPath() + savePath;
		String imgPath=showPath+imgName;
		
		
		MxActivitiesMySpaceMaterial material=new MxActivitiesMySpaceMaterial();
		material.setCreateDate(new Timestamp(System.currentTimeMillis()));
		material.setDescribe("图片描述");
		material.setLoadUrl(imgPath);
		material.setMaterialType(1);//图片类型为1
		material.setMyspaceId(Integer.parseInt(myspaceId));
		material.setOthers("");
		material.setSubmitUserId(Integer.parseInt(userId));
		boolean saveToSQL=activitiesMySpaceService.saveActivitiesMySpaceMaterial(material);
		
		if (imgName == null||saveToSQL==false) {
			map.put("done", "-1");
			map.put("imgSrc", "/");
			map.put("msg", "图片上传失败了!");
		} else {
			map.put("done", "0");
			map.put("imgSrc", imgPath);// 显示图片的完整相对路径
			map.put("msg", "图片上传成功!");
			System.out.println("用户上传图片至：" + imgPath);
		}
		JSONObject jsonObject = JSONObject.fromObject(map);
		response.getWriter().write(jsonObject.toString());

	}

	/*
	 * 评论点赞
	 */
	public void CommentClickPraise() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();// 请求request对象
		request.setCharacterEncoding("UTF-8");
		HttpServletResponse response = ServletActionContext.getResponse();// response对象返回数据给前台
		response.setContentType("application/json; charset=utf-8");
		int userId = Integer.parseInt(request.getParameter("userId").toString());// 获取用户id
		int commentId = Integer.parseInt(request.getParameter("commentId").toString());// 获取活动空间id
		
		boolean isClicked=activitiesMySpaceService.commentClickPraise(commentId,userId);
		Map<String, String> map = new HashMap<String, String>();
		if (!isClicked) {
			map.put("done", "-1");
			map.put("msg", "已赞过!");
		} else {
			map.put("done", "0");
			map.put("msg", "点赞成功!");
		}
		JSONObject jsonObject = JSONObject.fromObject(map);
		response.getWriter().write(jsonObject.toString());
		
	}
	
	public void testWebUploader(){
		System.out.println("testWebUploader");
	}
}
