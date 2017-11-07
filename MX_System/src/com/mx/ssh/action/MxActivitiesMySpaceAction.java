package com.mx.ssh.action;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.mx.ssh.bean.MxActivitiesData;
import com.mx.ssh.bean.MxActivitiesMySpaceComment;
import com.mx.ssh.bean.MxActivitiesMySpaceData;
import com.mx.ssh.bean.MxActivitiesMySpaceInviteCode;
import com.mx.ssh.bean.MxActivitiesMySpaceMaterial;
import com.mx.ssh.bean.MxActivitiesMySpaceUsers;
import com.mx.ssh.bean.MxUsersData;
import com.mx.ssh.bean.sysBean.ActivitiesUserMySpaceMaterial;
import com.mx.ssh.bean.sysBean.ActivitiesUserMySpaceMine;
import com.mx.ssh.service.IActivitiesMySpaceService;
import com.mx.ssh.service.IUserService;
import com.mx.ssh.util.ImageMethod;
import com.mx.ssh.util.PasswordUtil;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Result;

@Controller
// 控制层的Spring注解
@Scope("prototype")
// 支持多例
@ParentPackage("sys-default")
// 表示struts继承的父包
@Namespace(value = "/activitiesMySpace")
// 表示当前Action所在命名空间
public class MxActivitiesMySpaceAction extends ActionSupport {

	// spring扫描注入
	@Autowired
	private IActivitiesMySpaceService activitiesMySpaceService;

	@Autowired
	private IUserService userService;

	/**
	 * 跳转到用户个人空间列表页面
	 * 
	 */

	@Action(value = "gotoActivitiesMySpaceList", results = { @Result(name = "activitiesMySpaceList", location = "/WeixinPages/ActivitiesMySpace/activitiesMySpaceAllList.jsp") })
	public String gotoActivitiesMySpaceList(){
		try {
			System.out
					.println("进入MxActivitiesMySpaceAction.gotoActivitiesMySpaceList");
			HttpServletRequest request = ServletActionContext.getRequest();

			MxUsersData userInfo = (MxUsersData) request.getSession()
					.getAttribute("userInfo");// 获取session中的用户信息

			System.out.println("userInfo" + userInfo);

			List<MxActivitiesMySpaceUsers> userMySpaceList = activitiesMySpaceService
					.getMySpaceListByUserId(userInfo.getUserId());

			List<MxActivitiesMySpaceData> userMySpaceDataList = activitiesMySpaceService
					.getMySpaceListBySpceIds(userMySpaceList);
			request.setAttribute("userMySpaceDataList", userMySpaceDataList);// 返回对象列表给前台

			System.out.println("完成MxActivitiesMySpaceAction.gotoActivitiesMySpaceList执行");

		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "activitiesMySpaceList";

	}

	/*
	 * 跳转到活动空间页面主框架
	 */
	@Action(value = "gotoActivitiesMySpaceMain", 
			results = { 
			@Result(name = "activitiesMySpaceMain", location = "/WeixinPages/ActivitiesMySpace/activitiesMySpaceCommentList.jsp"),
			@Result(name = "illegal", location = "/WeixinPages/illegal.jsp")
	})
	public String gotoActivitiesMySpaceMain() throws Exception{

		HttpServletRequest request = ServletActionContext.getRequest();
		int myspaceId = Integer.parseInt(request.getParameter("myspaceId"));
		request.getSession().setAttribute("myspaceId", myspaceId);// 缓存用户查看的空间id
		MxUsersData userInfo = (MxUsersData) request.getSession().getAttribute("userInfo");
		int userId = -11;
		if (userInfo != null) {
			userId = userInfo.getUserId();
		}
		boolean b = activitiesMySpaceService.validateMySpaceUser(myspaceId,userId);
		if (b == true) {
			this.getActivitiesMySpaceCommentList();
			return "activitiesMySpaceMain";
		} else {
			return "illegal";
		}

	}

	/**
	 * 获取活动空间评论内容列表
	 */
	@Action(value = "getActivitiesMySpaceCommentList", results = { 
			@Result(name = "activitiesMySpaceCommentList", 
					location = "/WeixinPages/ActivitiesMySpace/activitiesMySpaceCommentList.jsp") 
		})
	public String getActivitiesMySpaceCommentList() throws Exception {
		try {

			HttpServletRequest request = ServletActionContext.getRequest();

			//int myspaceId = Integer.parseInt(request.getParameter("myspaceId"));

			List<MxActivitiesMySpaceComment> userMySpaceCommentList = activitiesMySpaceService
					.getUserMySpaceCommontList((Integer) request.getSession()
							.getAttribute("myspaceId"));

			request.setAttribute("userMySpaceCommentList",
					userMySpaceCommentList);// 缓存用户查看的空间讨论

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "activitiesMySpaceCommentList";
	}

	/**
	 * 获取活动空间素材列表
	 */
	@Action(value = "getActivitiesMySpaceMaterialList", results = { 
			@Result(name = "activitiesMySpaceMaterialList", location = "/WeixinPages/ActivitiesMySpace/activitiesMySpaceMaterialList.jsp") 
			
	})
	public String getActivitiesMySpaceMaterialList() throws Exception{
		
		HttpServletRequest request = ServletActionContext.getRequest();
		List<ActivitiesUserMySpaceMaterial> userMySpaceMaterialList = activitiesMySpaceService
				.getUserMySpaceMaterialList((Integer) request.getSession()
						.getAttribute("myspaceId"));
		request.setAttribute("userMySpaceMaterialList",
				userMySpaceMaterialList);// 缓存用户查看的空间素材列表
		
		return "activitiesMySpaceMaterialList";
	}

	/**
	 * 获取活动空间用户
	 */
	@Action(value = "getActivitiesMySpaceUsersList", results = { 
			@Result(name = "activitiesMySpaceUsersList", location = "/WeixinPages/ActivitiesMySpace/activitiesMySpaceUsersList.jsp") 
			
	})
	public String getActivitiesMySpaceUsersList() throws Exception{
		
		HttpServletRequest request = ServletActionContext.getRequest();
		List<MxActivitiesMySpaceUsers> mySpaceUsersList = activitiesMySpaceService
				.getMySpaceUsersList((Integer) request.getSession()
						.getAttribute("myspaceId"));
		request.setAttribute("mySpaceUsersList", mySpaceUsersList);// 缓存用户查看的空间用户列表
		
		return "activitiesMySpaceUsersList";
		// return "testWebUploader";
	}

	/**
	 * 获取活动空间我的内容列表
	 */
	@Action(value = "getActivitiesMySpaceMine", results = { 
			@Result(name = "activitiesMySpaceMine", location = "/WeixinPages/ActivitiesMySpace/activitiesMySpaceMine.jsp") 
			
	})
	public String getActivitiesMySpaceMine() throws Exception{
		
		HttpServletRequest request = ServletActionContext.getRequest();
		MxUsersData userInfo = (MxUsersData) request.getSession().getAttribute(
				"userInfo");
		ActivitiesUserMySpaceMine myspaceUserMine = activitiesMySpaceService
				.getMySpaceUserMine(userInfo.getUserId(), (Integer) request
						.getSession().getAttribute("myspaceId"));
		request.getSession().setAttribute("myspaceUserMine", myspaceUserMine);// 缓存用户查看的空间个人信息
		
		return "activitiesMySpaceMine";
	}

	/**
	 * 保存活动空间讨论内容
	 */
	@Action(value = "DoSaveActivitiesMySpaceComment")
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
		MxUsersData user = new MxUsersData();
		user.setUserId(userId);
		activitiesMySpaceComment.setMxUsersData(user);
		activitiesMySpaceComment.setMyspaceId(myspaceId);
		activitiesMySpaceComment.setCommentTxt(myspaceComment);
		activitiesMySpaceComment.setState(0);
		activitiesMySpaceComment.setPraiseUserIds("");
		activitiesMySpaceComment.setPraiseClickNum(0);
		activitiesMySpaceComment.setParentCommentId(-1);
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
	@Action(value = "DoCreateInviteCodeUrl")
	public void DoCreateInviteCodeUrl() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();// 请求request对象
		request.setCharacterEncoding("UTF-8");
		HttpServletResponse response = ServletActionContext.getResponse();// response对象返回数据给前台
		response.setContentType("application/json; charset=utf-8");
		String initiator_userId = request.getParameter("initiator_userId")
				.toString();// 获取用户id
		String myspaceId = request.getParameter("myspaceId").toString();// 获取活动空间id
		MxActivitiesData activitiesData = activitiesMySpaceService
				.getActivityByMyspaceId(Integer.parseInt(myspaceId));
		String inviteCodeStr = PasswordUtil.createPWD(6);// 生成8位随机验证码
		MxActivitiesMySpaceInviteCode inviteCode = new MxActivitiesMySpaceInviteCode(
				activitiesData, Integer.parseInt(myspaceId),
				Integer.parseInt(initiator_userId), inviteCodeStr, 0, -1,
				new Timestamp(System.currentTimeMillis()));
		boolean b = activitiesMySpaceService.addActivityInviteCode(inviteCode);

		// 生成的邀请链接WeixinSignUtil.serverUrl+"activitiesMySpace!validateInviteCode.action?activityId=1&myspaceId=5&inviteCode=jdfjsj"

		Map<String, String> map = new HashMap<String, String>();
		if (b == false) {
			map.put("done", "-1");
			map.put("inviteCodeUrl", "");
			map.put("msg", "生成活动邀请链接失败!");
		} else {

			// String
			// inviteUrl=WeixinSignUtil.serverUrl+"activitiesMySpace!validateInviteCode.action?activityId="
			// +activitiesData.getActivitiesId()+"&myspaceId="+myspaceId+"&inviteCode="+inviteCodeStr;
			// String inviteUrl=inviteCodeStr;
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
	@Action(value = "validateInviteCode")
	public void validateInviteCode() throws IOException {
		// 获取邀请码信息，判断邀请码是否存在，判断邀请码是否过期

		HttpServletRequest request = ServletActionContext.getRequest();// 请求request对象
		request.setCharacterEncoding("UTF-8");
		HttpServletResponse response = ServletActionContext.getResponse();// response对象返回数据给前台
		response.setContentType("application/json; charset=utf-8");
		String userId = request.getParameter("userId").toString();// 获取用户id
		String inviteCode = request.getParameter("inviteCode").toString();// 获取活动空间验证码
		int b = activitiesMySpaceService.validateInviteCode(inviteCode,
				Integer.parseInt(userId));

		// System.out.println("//////"+b);

		// 生成的邀请链接WeixinSignUtil.serverUrl+"activitiesMySpace!validateInviteCode.action?activityId=1&myspaceId=5&inviteCode=jdfjsj"

		Map<String, String> map = new HashMap<String, String>();
		if (b == -1) {
			map.put("done", "-1");
			map.put("msg", "系统错误,请联系平台管理员!");
		} else if (b == -10) {
			map.put("done", "-10");
			map.put("msg", "验证码不存在!");
		} else if (b == -11) {
			map.put("done", "-11");
			map.put("msg", "验证码已过期，请联系活动组织者!");
		} else if (b == -12) {
			map.put("done", "-12");
			map.put("msg", "您已参加该活动!");
		} else if (b > 0) {

			MxActivitiesMySpaceData userMySpace = activitiesMySpaceService
					.getMySpaceBySpaceId(b);

			map.put("done", "0");
			map.put("msg", "参加活动成功！");
			map.put("liData",
					"<li  class='mui-table-view-cell mui-media'>"
							+ "<a href='activitiesMySpace/gotoActivitiesMySpaceMain.action?myspaceId="
							+ userMySpace.getMyspaceId()
							+ "'>"
							+ "<img  class='mui-media-object mui-pull-left'  src='"
							+ userMySpace.getCoverImageUrl()
							+ "'>"
							+ "<div class='mui-media-body' style='color:black' >"
							+ userMySpace.getMyspaceName()
							+ "<p class='mui-ellipsis' style='color:#87CEFA' >"
							+ userMySpace.getDescribe() + "</p></div></a></li>");

			System.out.println("用户成功参加活动！");
		}
		JSONObject jsonObject = JSONObject.fromObject(map);
		response.getWriter().write(jsonObject.toString());

	}

	/*
	 * 压缩并上传图片方法
	 */
	@Action(value = "UploadImage")
	public void UploadImage() throws IOException, ServletException {

		HttpServletRequest request = ServletActionContext.getRequest();// 请求request对象
		request.setCharacterEncoding("UTF-8");
		HttpServletResponse response = ServletActionContext.getResponse();// response对象返回数据给前台
		response.setContentType("application/json; charset=utf-8");
		int userId = ((MxUsersData)request.getSession().getAttribute("userInfo")).getUserId();// 获取用户id
		String myspaceId =request.getSession().getAttribute("myspaceId").toString();// 获取活动空间id
		String base64Img = request.getParameter("img").toString();
		String base64ImgPreview = request.getParameter("imgPreview").toString();
		base64Img = base64Img.replace("data:image/jpeg;base64,", "");// 去除base64中无用的文件头
		base64ImgPreview = base64ImgPreview.replace("data:image/jpeg;base64,","");// 去除base64中无用的文件头
		String savePath = "/WeixinPages/common/uploadImg/myspaceImg/"
				+ myspaceId + "/" + userId + "/";// 保存图片的服务器相对路径
		String realSavePath = request.getSession().getServletContext()
				.getRealPath(savePath);//系统文件夹目录路径
		String imgName = ImageMethod.Base64SaveAsImage(base64ImgPreview,base64Img,realSavePath);// 保存图片到系统应用文件夹中

		Map<String, String> map = new HashMap<String, String>();
		String showPath = request.getContextPath() + savePath;//服务器url绝对路径
		String imgPath = showPath +"img/"+ imgName;
		String imgPreviewPath = showPath +"preview/"+ imgName;

		MxActivitiesMySpaceMaterial material = new MxActivitiesMySpaceMaterial();
		material.setCreateDate(new Timestamp(System.currentTimeMillis()));
		material.setDescribe("图片描述");
		material.setLoadUrl(imgPath);
		material.setPreviewImgUrl(imgPreviewPath);
		material.setMaterialType(1);// 图片类型为1
		material.setMyspaceId(Integer.parseInt(myspaceId));
		material.setOthers("");
		material.setSubmitUserId(userId);
		boolean saveToSQL = activitiesMySpaceService
				.saveActivitiesMySpaceMaterial(material);

		if (imgName == null || saveToSQL == false) {
			map.put("done", "-1");
			//map.put("imgSrc", "/");
			map.put("msg", "图片上传失败了!");
		} else {
			map.put("done", "0");
			//map.put("imgSrc", imgPath);// 显示图片的完整相对路径
			map.put("msg", "图片上传成功!");
			System.out.println("用户上传图片至：" + imgPath);
		}
		JSONObject jsonObject = JSONObject.fromObject(map);
		response.getWriter().write(jsonObject.toString());

	}

	/*
	 * 删除图片方法
	 */
	@Action(value = "DeleteImage")
	public void DeleteImage() throws IOException, ServletException {

		HttpServletRequest request = ServletActionContext.getRequest();// 请求request对象
		request.setCharacterEncoding("UTF-8");
		HttpServletResponse response = ServletActionContext.getResponse();// response对象返回数据给前台
		response.setContentType("application/json; charset=utf-8");
		
		int userId = ((MxUsersData)request.getSession().getAttribute("userInfo")).getUserId();// 获取用户id
		String myspaceId =request.getSession().getAttribute("myspaceId").toString();// 获取活动空间id
		int materialId = Integer.parseInt(request.getParameter("materialId").trim());
		String loadUrl=request.getParameter("loadUrl").trim();
		
		String imgName = loadUrl.substring(loadUrl.indexOf(userId+"/")+1, loadUrl.length());//截取文件名

		String imgSavePath = "/WeixinPages/common/uploadImg/myspaceImg/"
				+ myspaceId + "/" + userId + "/"+imgName;// 保存图片的服务器相对路径
		
		String realSavePath = request.getSession().getServletContext()
				.getRealPath(imgSavePath);//系统文件夹目录路径
		
		boolean imgDone = ImageMethod.deleteImage(realSavePath);// 系统目录下删除文件
		
		boolean imgDone2 = (imgDone==true?activitiesMySpaceService.deleteMyspaceMaterial(materialId):false);//数据库内删除数据
		
		Map<String, String> map = new HashMap<String, String>();
		
		if (imgDone2 == false) {
			map.put("done", "-1");
			map.put("msg", "图片删除失败了!");
		} else {
			map.put("done", "0");
			map.put("msg", "图删除成功!");
		}
		JSONObject jsonObject = JSONObject.fromObject(map);
		response.getWriter().write(jsonObject.toString());

	}
	
	
	/*
	 * 评论点赞
	 */
	@Action(value = "CommentClickPraise")
	public void CommentClickPraise() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();// 请求request对象
		request.setCharacterEncoding("UTF-8");
		HttpServletResponse response = ServletActionContext.getResponse();// response对象返回数据给前台
		response.setContentType("application/json; charset=utf-8");
		int userId = Integer
				.parseInt(request.getParameter("userId").toString());// 获取用户id
		int commentId = Integer.parseInt(request.getParameter("commentId")
				.toString());// 获取活动空间id

		boolean isClicked = activitiesMySpaceService.commentClickPraise(
				commentId, userId);
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

	public void testWebUploader() {
		System.out.println("testWebUploader");
	}

	
	
	
	/*
	 * 将用户移出活动空间
	 */
	@Action(value = "removeMyspaceUser")
	public void removeMyspaceUser() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();// 请求request对象
		request.setCharacterEncoding("UTF-8");
		HttpServletResponse response = ServletActionContext.getResponse();// response对象返回数据给前台
		response.setContentType("application/json; charset=utf-8");

		int userId = Integer
				.parseInt(request.getParameter("userId").toString());// 获取用户id
		int myspaceId = Integer.parseInt(request.getParameter("myspaceId")
				.toString());// 获取活动空间id

		boolean isDone = activitiesMySpaceService.deleteMyspaceUser(myspaceId,
				userId);

		Map<String, String> map = new HashMap<String, String>();
		if (!isDone) {
			map.put("done", "-1");
			map.put("msg", "移除失败，请联系系统管理员!");
		} else {
			map.put("done", "0");
			map.put("msg", "成功移除用户!");
		}
		JSONObject jsonObject = JSONObject.fromObject(map);
		response.getWriter().write(jsonObject.toString());
	}
	
	/*
	 * 保存帖子评论内容
	 */
	@Action(value = "DoSaveMyspaceComment_comment")
	public void DoSaveMyspaceComment_comment() throws IOException{

		HttpServletRequest request = ServletActionContext.getRequest();// 请求request对象
		request.setCharacterEncoding("UTF-8");
		HttpServletResponse response = ServletActionContext.getResponse();// response对象返回数据给前台
		response.setContentType("application/json; charset=utf-8");
		int userId = Integer
				.parseInt(request.getParameter("userId").toString());// 获取用户id
		
		int myspaceId=Integer.parseInt(request.getParameter("myspaceId")
				.toString());
		
		int commentId = Integer.parseInt(request.getParameter("commentId")
				.toString());// 获取活动空间id

		String commentTxt=request.getParameter("commentTxt").toString();//获取评论内容
		
		int myspaceComment_comment=0;
		
		myspaceComment_comment = activitiesMySpaceService.saveMyspaceComment_comment(
				myspaceId,commentId, userId,commentTxt);
		Map<String, String> map = new HashMap<String, String>();
		if (0==myspaceComment_comment) {
			map.put("done", "-1");
			map.put("msg", "保存失败!");
		} else {
			map.put("done", "0");
			map.put("commentId",myspaceComment_comment+"");
			map.put("msg", "保存成功!");
		}
		JSONObject jsonObject = JSONObject.fromObject(map);
		response.getWriter().write(jsonObject.toString());

	
	}
	
	
	/*
	 * 删除帖子评论
	 */
	@Action(value = "DoDeleteMyspaceComment_comment")
	public void DoDeleteMyspaceComment_comment() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();// 请求request对象
		request.setCharacterEncoding("UTF-8");
		HttpServletResponse response = ServletActionContext.getResponse();// response对象返回数据给前台
		response.setContentType("application/json; charset=utf-8");
		int commentId = Integer.parseInt(request.getParameter("commentId")
				.toString());// 获取评论id
		boolean isDeleted = activitiesMySpaceService.deleteMyspaceComment_comment(commentId);
		Map<String, String> map = new HashMap<String, String>();
		if (!isDeleted) {
			map.put("done", "-1");
			map.put("msg", "删除失败!");
		} else {
			map.put("done", "0");
			map.put("msg", "删除成功!");
		}
		JSONObject jsonObject = JSONObject.fromObject(map);
		response.getWriter().write(jsonObject.toString());
	}
	
	/*
	 * 删除帖子(及其评论内容)
	 */
	@Action(value = "DeleteComment")
	public void DeleteComment() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();// 请求request对象
		request.setCharacterEncoding("UTF-8");
		HttpServletResponse response = ServletActionContext.getResponse();// response对象返回数据给前台
		response.setContentType("application/json; charset=utf-8");
		int commentId = Integer.parseInt(request.getParameter("commentId")
				.toString());// 获取评论id
		boolean isDeleted = activitiesMySpaceService.deleteMyspaceComment(commentId);
		Map<String, String> map = new HashMap<String, String>();
		if (!isDeleted) {
			map.put("done", "-1");
			map.put("msg", "删除失败!");
		} else {
			map.put("done", "0");
			map.put("msg", "删除成功!");
		}
		JSONObject jsonObject = JSONObject.fromObject(map);
		response.getWriter().write(jsonObject.toString());
	}
	
	
	/*
	 * 更改用户信息
	 */
	@Action(value = "SaveUserData")
	public void SaveUserData() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();// 请求request对象
		request.setCharacterEncoding("UTF-8");
		HttpServletResponse response = ServletActionContext.getResponse();// response对象返回数据给前台
		response.setContentType("application/json; charset=utf-8");
		
		int userId=((MxUsersData)request.getSession().getAttribute("userInfo")).getUserId();
		String userRealName=request.getParameter("userRealName");
		String phoneNum=request.getParameter("phoneNum");
		String userAddr=request.getParameter("userAddr");
		String userEmail=request.getParameter("userEmail");
		String userIdNum=request.getParameter("userIdNum");
		String userGradeClass=request.getParameter("userGradeClass");
		
		
		MxUsersData user=userService.getUserByID(userId);
		
		user.setUserRealName(userRealName);
		user.setUserPhoneNum(phoneNum);
		user.setUserAddr(userAddr);
		user.setUserEmail(userEmail);
		user.setUserIdcardNum(userIdNum);
		user.setOthers(userGradeClass);
		
		boolean isDone=userService.updateUserData(user);//更新用户数据
		
		Map<String, String> map = new HashMap<String, String>();
		if (!isDone) {
			map.put("done", "-1");
			map.put("msg", "提交失败!");
		} else {
			map.put("done", "0");
			map.put("msg", "提交成功!");
			request.getSession().setAttribute("userInfo", user);
		}
		JSONObject jsonObject = JSONObject.fromObject(map);
		response.getWriter().write(jsonObject.toString());
		
	}
	
	
	

}
