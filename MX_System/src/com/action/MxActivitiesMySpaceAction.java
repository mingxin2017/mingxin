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
	
	//当前用户id缓存
	private  MxUsersData userInfo;//保存用户个人信息
	public MxUsersData getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(MxUsersData userInfo) {
		this.userInfo = userInfo;
	}

	//活动空间id缓存
	private int myspaceId;
	public int getMyspaceId() {
		return myspaceId;
	}
	public void setMyspaceId(int myspaceId) {
		this.myspaceId = myspaceId;
	}

	//活动空间讨论缓存
	private List<MxActivitiesMySpaceComment> userMySpaceCommentList;
	public List<MxActivitiesMySpaceComment> getUserMySpaceCommentList() {
		return userMySpaceCommentList;
	}
	public void setUserMySpaceCommentList(
			List<MxActivitiesMySpaceComment> userMySpaceCommentList) {
		this.userMySpaceCommentList = userMySpaceCommentList;
	}

	private List<ActivitiesUserMySpaceMaterial> userMySpaceMaterialList;
	public List<ActivitiesUserMySpaceMaterial> getUserMySpaceMaterialList() {
		return userMySpaceMaterialList;
	}
	public void setUserMySpaceMaterialList(
			List<ActivitiesUserMySpaceMaterial> userMySpaceMaterialList) {
		this.userMySpaceMaterialList = userMySpaceMaterialList;
	}

	//保存活动空间
	private List<MxActivitiesMySpaceUsers> mySpaceUsersList;
	public List<MxActivitiesMySpaceUsers> getMySpaceUsersList() {
		return mySpaceUsersList;
	}
	public void setMySpaceUsersList(List<MxActivitiesMySpaceUsers> mySpaceUsersList) {
		this.mySpaceUsersList = mySpaceUsersList;
	}
	
	//保存个人空间封装类数据
	private ActivitiesUserMySpaceMine myspaceUserMine;
	public ActivitiesUserMySpaceMine getMyspaceUserMine() {
		return myspaceUserMine;
	}
	public void setMyspaceUserMine(ActivitiesUserMySpaceMine myspaceUserMine) {
		this.myspaceUserMine = myspaceUserMine;
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
		
		userInfo=userService.getUserByOpenId(snsUserInfo.getOpenId());//根据openId获取用户系统信息
		
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
				
				return "activitiesMySpaceList";
			//}
		}
		
		
	}

	/**
	 * 跳转到活动空间页面主框架
	 */
	public String gotoActivitiesMySpaceMain() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		myspaceId=Integer.parseInt(request.getParameter("myspaceId"));
		//request.setAttribute("myspaceId",myspaceId);
		
		return "activitiesMySpaceMain";
	}

	/**
	 * 获取活动空间评论内容列表
	 */
	public String getActivitiesMySpaceCommentList() {
		//if(userMySpaceCommentList==null||userMySpaceCommentList.size()==0){
		userMySpaceCommentList=activitiesMySpaceService.getUserMySpaceCommontList(myspaceId);
		//}
		return "activitiesMySpaceCommentList";
	}

	/**
	 * 获取活动空间素材列表
	 */
	public String getActivitiesMySpaceMaterialList() {
		userMySpaceMaterialList=activitiesMySpaceService.getUserMySpaceMaterialList(myspaceId);
		return "activitiesMySpaceMaterialList";
	}

	/**
	 * 获取活动空间用户
	 */
	public String getActivitiesMySpaceUsersList() {
		mySpaceUsersList=activitiesMySpaceService.getMySpaceUsersList(myspaceId);
		
		return "activitiesMySpaceUsersList";
		//return "testWebUploader";
	}

	/**
	 * 获取活动空间我的内容列表
	 */
	public String getActivitiesMySpaceMine() {
		myspaceUserMine=activitiesMySpaceService.getMySpaceUserMine(userInfo.getUserId(),myspaceId);
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
			System.out.println("生成活动邀请链接成功！");
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
		//MxActivitiesData activitiesData=activitiesMySpaceService.getActivityByMyspaceId(Integer.parseInt(myspaceId));
		//String inviteCodeStr=PasswordUtil.createPWD(6);//生成8位随机验证码
		//MxActivitiesMySpaceInviteCode getInviteCode=new MxActivitiesMySpaceInviteCode(activitiesData,Integer.parseInt(myspaceId),Integer.parseInt(userId), inviteCodeStr, 0,-1);
		boolean b=activitiesMySpaceService.validateInviteCode(inviteCode,Integer.parseInt(userId));
		
		//生成的邀请链接WeixinSignUtil.serverUrl+"activitiesMySpace!validateInviteCode.action?activityId=1&myspaceId=5&inviteCode=jdfjsj"
		
		
		Map<String, String> map = new HashMap<String, String>();
		if (b==false) {
			map.put("done", "-1");
			//map.put("inviteCodeUrl", "");
			map.put("msg", "验证码无效!");
		} else {

			//String inviteUrl=WeixinSignUtil.serverUrl+"activitiesMySpace!validateInviteCode.action?activityId="
			//			+activitiesData.getActivitiesId()+"&myspaceId="+myspaceId+"&inviteCode="+inviteCodeStr;
			//String inviteUrl=inviteCodeStr;
			map.put("done", "0");
			//map.put("inviteCode", inviteCodeStr);// 显示图片的完整相对路径
			map.put("msg", "已成功参加活动！");
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
