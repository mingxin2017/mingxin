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
import org.apache.struts2.convention.annotation.Results;
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
import com.mx.weixin.pojo.SNSUserInfo;
import com.mx.weixin.util.WeixinUtil;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Result;

@Controller
// ���Ʋ��Springע��
@Scope("prototype")
// ֧�ֶ���
@ParentPackage("sys-default")
// ��ʾstruts�̳еĸ���
@Namespace(value = "/activitiesMySpace")
// ��ʾ��ǰAction���������ռ�
public class MxActivitiesMySpaceAction extends ActionSupport {

	// springɨ��ע��
	@Autowired
	private IActivitiesMySpaceService activitiesMySpaceService;

	@Autowired
	private IUserService userService;

	/**
	 * ��ת���û����˿ռ��б�ҳ��
	 * 
	 */

	@Action(value = "gotoActivitiesMySpaceList", results = { @Result(name = "activitiesMySpaceList", location = "/WeixinPages/ActivitiesMySpace/activitiesMySpaceAllList.jsp") })
	public String gotoActivitiesMySpaceList() {
		try {
			System.out
					.println("����MxActivitiesMySpaceAction.gotoActivitiesMySpaceList");
			HttpServletRequest request = ServletActionContext.getRequest();

			MxUsersData userInfo = (MxUsersData) request.getSession()
					.getAttribute("userInfo");// ��ȡsession�е��û���Ϣ

			System.out.println("userInfo" + userInfo);

			List<MxActivitiesMySpaceUsers> userMySpaceList = activitiesMySpaceService
					.getMySpaceListByUserId(userInfo.getUserId());

			List<MxActivitiesMySpaceData> userMySpaceDataList = activitiesMySpaceService
					.getMySpaceListBySpceIds(userMySpaceList);
			request.setAttribute("userMySpaceDataList", userMySpaceDataList);// ���ض����б���ǰ̨

			System.out.println("���MxActivitiesMySpaceAction.gotoActivitiesMySpaceListִ��");

		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "activitiesMySpaceList";

	}

	/*
	 * ��ת����ռ�ҳ�������
	 */
	@Action(value = "gotoActivitiesMySpaceMain", 
			results = { 
			@Result(name = "activitiesMySpaceMain", location = "/WeixinPages/ActivitiesMySpace/activitiesMySpaceMain.jsp"),
			@Result(name = "illegal", location = "/WeixinPages/illegal.jsp")
	})
	public String gotoActivitiesMySpaceMain() {

		HttpServletRequest request = ServletActionContext.getRequest();
		int myspaceId = Integer.parseInt(request.getParameter("myspaceId"));
		request.getSession().setAttribute("myspaceId", myspaceId);// �����û��鿴�Ŀռ�id
		MxUsersData userInfo = (MxUsersData) request.getSession().getAttribute("userInfo");
		int userId = -11;
		if (userInfo != null) {
			userId = userInfo.getUserId();
		}
		boolean b = activitiesMySpaceService.validateMySpaceUser(myspaceId,userId);
		if (b == true) {
			return "activitiesMySpaceMain";
		} else {
			return "illegal";
		}

	}

	/**
	 * ��ȡ��ռ����������б�
	 */
	@Action(value = "getActivitiesMySpaceCommentList", results = { 
			@Result(name = "activitiesMySpaceCommentList", location = "/WeixinPages/ActivitiesMySpace/activitiesMySpaceCommentList.jsp") 
			
	})
	public String getActivitiesMySpaceCommentList() {
		HttpServletRequest request = ServletActionContext.getRequest();
		// if(userMySpaceCommentList==null||userMySpaceCommentList.size()==0){
		List<MxActivitiesMySpaceComment> userMySpaceCommentList = activitiesMySpaceService
				.getUserMySpaceCommontList((Integer) request.getSession()
						.getAttribute("myspaceId"));
		// }
		request.setAttribute("userMySpaceCommentList",
				userMySpaceCommentList);// �����û��鿴�Ŀռ�����
		return "activitiesMySpaceCommentList";
	}

	/**
	 * ��ȡ��ռ��ز��б�
	 */
	@Action(value = "getActivitiesMySpaceMaterialList", results = { 
			@Result(name = "activitiesMySpaceMaterialList", location = "/WeixinPages/ActivitiesMySpace/activitiesMySpaceMaterialList.jsp") 
			
	})
	public String getActivitiesMySpaceMaterialList() {
		HttpServletRequest request = ServletActionContext.getRequest();
		List<ActivitiesUserMySpaceMaterial> userMySpaceMaterialList = activitiesMySpaceService
				.getUserMySpaceMaterialList((Integer) request.getSession()
						.getAttribute("myspaceId"));
		request.setAttribute("userMySpaceMaterialList",
				userMySpaceMaterialList);// �����û��鿴�Ŀռ��ز��б�
		return "activitiesMySpaceMaterialList";
	}

	/**
	 * ��ȡ��ռ��û�
	 */
	@Action(value = "getActivitiesMySpaceUsersList", results = { 
			@Result(name = "activitiesMySpaceUsersList", location = "/WeixinPages/ActivitiesMySpace/activitiesMySpaceUsersList.jsp") 
			
	})
	public String getActivitiesMySpaceUsersList() {
		HttpServletRequest request = ServletActionContext.getRequest();
		List<MxActivitiesMySpaceUsers> mySpaceUsersList = activitiesMySpaceService
				.getMySpaceUsersList((Integer) request.getSession()
						.getAttribute("myspaceId"));
		request.setAttribute("mySpaceUsersList", mySpaceUsersList);// �����û��鿴�Ŀռ��û��б�
		return "activitiesMySpaceUsersList";
		// return "testWebUploader";
	}

	/**
	 * ��ȡ��ռ��ҵ������б�
	 */
	@Action(value = "getActivitiesMySpaceMine", results = { 
			@Result(name = "activitiesMySpaceMine", location = "/WeixinPages/ActivitiesMySpace/activitiesMySpaceMine.jsp") 
			
	})
	public String getActivitiesMySpaceMine() {
		HttpServletRequest request = ServletActionContext.getRequest();
		MxUsersData userInfo = (MxUsersData) request.getSession().getAttribute(
				"userInfo");
		ActivitiesUserMySpaceMine myspaceUserMine = activitiesMySpaceService
				.getMySpaceUserMine(userInfo.getUserId(), (Integer) request
						.getSession().getAttribute("myspaceId"));
		request.getSession().setAttribute("myspaceUserMine", myspaceUserMine);// �����û��鿴�Ŀռ������Ϣ
		return "activitiesMySpaceMine";
	}

	/**
	 * �����ռ���������
	 */
	@Action(value = "DoSaveActivitiesMySpaceComment")
	public void DoSaveActivitiesMySpaceComment() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();// ����request����
		request.setCharacterEncoding("UTF-8");
		HttpServletResponse response = ServletActionContext.getResponse(); // response���󷵻����ݸ�ǰ̨
		response.setContentType("application/json; charset=utf-8");

		int userId = Integer
				.parseInt(request.getParameter("userId") == null ? "-100"
						: request.getParameter("userId"));

		int myspaceId = Integer
				.parseInt(request.getParameter("myspaceId") == null ? "-100"
						: request.getParameter("myspaceId"));

		String myspaceComment = request.getParameter("txt").toString(); // ���Ľ���

		MxActivitiesMySpaceComment activitiesMySpaceComment = new MxActivitiesMySpaceComment();
		MxUsersData user = new MxUsersData();
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
			map.put("done", "0");// ����ɹ�
		} else {
			map.put("done", "-1");// ����ʧ��
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		response.getWriter().write(jsonObject.toString());

	}

	/*
	 * ���ɻ�ռ���������
	 */
	@Action(value = "DoCreateInviteCodeUrl")
	public void DoCreateInviteCodeUrl() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();// ����request����
		request.setCharacterEncoding("UTF-8");
		HttpServletResponse response = ServletActionContext.getResponse();// response���󷵻����ݸ�ǰ̨
		response.setContentType("application/json; charset=utf-8");
		String initiator_userId = request.getParameter("initiator_userId")
				.toString();// ��ȡ�û�id
		String myspaceId = request.getParameter("myspaceId").toString();// ��ȡ��ռ�id
		MxActivitiesData activitiesData = activitiesMySpaceService
				.getActivityByMyspaceId(Integer.parseInt(myspaceId));
		String inviteCodeStr = PasswordUtil.createPWD(6);// ����8λ�����֤��
		MxActivitiesMySpaceInviteCode inviteCode = new MxActivitiesMySpaceInviteCode(
				activitiesData, Integer.parseInt(myspaceId),
				Integer.parseInt(initiator_userId), inviteCodeStr, 0, -1,
				new Timestamp(System.currentTimeMillis()));
		boolean b = activitiesMySpaceService.addActivityInviteCode(inviteCode);

		// ���ɵ���������WeixinSignUtil.serverUrl+"activitiesMySpace!validateInviteCode.action?activityId=1&myspaceId=5&inviteCode=jdfjsj"

		Map<String, String> map = new HashMap<String, String>();
		if (b == false) {
			map.put("done", "-1");
			map.put("inviteCodeUrl", "");
			map.put("msg", "���ɻ��������ʧ��!");
		} else {

			// String
			// inviteUrl=WeixinSignUtil.serverUrl+"activitiesMySpace!validateInviteCode.action?activityId="
			// +activitiesData.getActivitiesId()+"&myspaceId="+myspaceId+"&inviteCode="+inviteCodeStr;
			// String inviteUrl=inviteCodeStr;
			map.put("done", "0");
			map.put("inviteCode", inviteCodeStr);// ��ʾͼƬ���������·��
			map.put("msg", "�ɹ�����������Чʱ��Ϊ24Сʱ��");
			System.out.println("�ɹ����ɻ�����룡");
		}
		JSONObject jsonObject = JSONObject.fromObject(map);
		response.getWriter().write(jsonObject.toString());

	}

	/*
	 * ��֤����������Ч��
	 */
	@Action(value = "validateInviteCode")
	public void validateInviteCode() throws IOException {
		// ��ȡ��������Ϣ���ж��������Ƿ���ڣ��ж��������Ƿ����

		HttpServletRequest request = ServletActionContext.getRequest();// ����request����
		request.setCharacterEncoding("UTF-8");
		HttpServletResponse response = ServletActionContext.getResponse();// response���󷵻����ݸ�ǰ̨
		response.setContentType("application/json; charset=utf-8");
		String userId = request.getParameter("userId").toString();// ��ȡ�û�id
		String inviteCode = request.getParameter("inviteCode").toString();// ��ȡ��ռ���֤��
		int b = activitiesMySpaceService.validateInviteCode(inviteCode,
				Integer.parseInt(userId));

		// System.out.println("//////"+b);

		// ���ɵ���������WeixinSignUtil.serverUrl+"activitiesMySpace!validateInviteCode.action?activityId=1&myspaceId=5&inviteCode=jdfjsj"

		Map<String, String> map = new HashMap<String, String>();
		if (b == -1) {
			map.put("done", "-1");
			map.put("msg", "ϵͳ����,����ϵƽ̨����Ա!");
		} else if (b == -10) {
			map.put("done", "-10");
			map.put("msg", "��֤�벻����!");
		} else if (b == -11) {
			map.put("done", "-11");
			map.put("msg", "��֤���ѹ��ڣ�����ϵ���֯��!");
		} else if (b == -12) {
			map.put("done", "-12");
			map.put("msg", "���ѲμӸû!");
		} else if (b > 0) {

			MxActivitiesMySpaceData userMySpace = activitiesMySpaceService
					.getMySpaceBySpaceId(b);

			map.put("done", "0");
			map.put("msg", "�μӻ�ɹ���");
			map.put("liData",
					"<li  class='mui-table-view-cell mui-media'>"
							+ "<a href='activitiesMySpace!gotoActivitiesMySpaceMain.action?myspaceId="
							+ userMySpace.getMyspaceId()
							+ "'>"
							+ "<img  class='mui-media-object mui-pull-left'  src='"
							+ userMySpace.getCoverImageUrl()
							+ "'>"
							+ "<div class='mui-media-body' style='color:black' >"
							+ userMySpace.getMyspaceName()
							+ "<p class='mui-ellipsis' style='color:#87CEFA' >"
							+ userMySpace.getDescribe() + "</p></div></a></li>");

			System.out.println("�û��ɹ��μӻ��");
		}
		JSONObject jsonObject = JSONObject.fromObject(map);
		response.getWriter().write(jsonObject.toString());

	}

	/*
	 * ѹ�����ϴ�ͼƬ����
	 */
	@Action(value = "UploadImage")
	public void UploadImage() throws IOException, ServletException {

		HttpServletRequest request = ServletActionContext.getRequest();// ����request����
		request.setCharacterEncoding("UTF-8");
		HttpServletResponse response = ServletActionContext.getResponse();// response���󷵻����ݸ�ǰ̨
		response.setContentType("application/json; charset=utf-8");
		String userId = request.getParameter("userId").toString();// ��ȡ�û�id
		String myspaceId = request.getParameter("myspaceId").toString();// ��ȡ��ռ�id
		String base64Img = request.getParameter("img").toString();
		base64Img = base64Img.replace("data:image/jpeg;base64,", "");// ȥ��base64�����õ��ļ�ͷ
		String savePath = "/WeixinPages/common/uploadImg/myspaceImg/"
				+ myspaceId + "/" + userId + "/";// ����ͼƬ�ľ���·��
		String realSavePath = request.getSession().getServletContext()
				.getRealPath(savePath);
		String imgName = ImageMethod.Base64SaveAsImage(base64Img, realSavePath);// ����ͼƬ��ϵͳӦ���ļ�����

		Map<String, String> map = new HashMap<String, String>();
		String showPath = request.getContextPath() + savePath;
		String imgPath = showPath + imgName;

		MxActivitiesMySpaceMaterial material = new MxActivitiesMySpaceMaterial();
		material.setCreateDate(new Timestamp(System.currentTimeMillis()));
		material.setDescribe("ͼƬ����");
		material.setLoadUrl(imgPath);
		material.setMaterialType(1);// ͼƬ����Ϊ1
		material.setMyspaceId(Integer.parseInt(myspaceId));
		material.setOthers("");
		material.setSubmitUserId(Integer.parseInt(userId));
		boolean saveToSQL = activitiesMySpaceService
				.saveActivitiesMySpaceMaterial(material);

		if (imgName == null || saveToSQL == false) {
			map.put("done", "-1");
			map.put("imgSrc", "/");
			map.put("msg", "ͼƬ�ϴ�ʧ����!");
		} else {
			map.put("done", "0");
			map.put("imgSrc", imgPath);// ��ʾͼƬ���������·��
			map.put("msg", "ͼƬ�ϴ��ɹ�!");
			System.out.println("�û��ϴ�ͼƬ����" + imgPath);
		}
		JSONObject jsonObject = JSONObject.fromObject(map);
		response.getWriter().write(jsonObject.toString());

	}

	/*
	 * ���۵���
	 */
	@Action(value = "CommentClickPraise")
	public void CommentClickPraise() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();// ����request����
		request.setCharacterEncoding("UTF-8");
		HttpServletResponse response = ServletActionContext.getResponse();// response���󷵻����ݸ�ǰ̨
		response.setContentType("application/json; charset=utf-8");
		int userId = Integer
				.parseInt(request.getParameter("userId").toString());// ��ȡ�û�id
		int commentId = Integer.parseInt(request.getParameter("commentId")
				.toString());// ��ȡ��ռ�id

		boolean isClicked = activitiesMySpaceService.commentClickPraise(
				commentId, userId);
		Map<String, String> map = new HashMap<String, String>();
		if (!isClicked) {
			map.put("done", "-1");
			map.put("msg", "���޹�!");
		} else {
			map.put("done", "0");
			map.put("msg", "���޳ɹ�!");
		}
		JSONObject jsonObject = JSONObject.fromObject(map);
		response.getWriter().write(jsonObject.toString());

	}

	public void testWebUploader() {
		System.out.println("testWebUploader");
	}

	/*
	 * ���û��Ƴ���ռ�
	 */
	@Action(value = "removeMyspaceUser")
	public void removeMyspaceUser() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();// ����request����
		request.setCharacterEncoding("UTF-8");
		HttpServletResponse response = ServletActionContext.getResponse();// response���󷵻����ݸ�ǰ̨
		response.setContentType("application/json; charset=utf-8");

		int userId = Integer
				.parseInt(request.getParameter("userId").toString());// ��ȡ�û�id
		int myspaceId = Integer.parseInt(request.getParameter("myspaceId")
				.toString());// ��ȡ��ռ�id

		boolean isDone = activitiesMySpaceService.deleteMyspaceUser(myspaceId,
				userId);

		Map<String, String> map = new HashMap<String, String>();
		if (!isDone) {
			map.put("done", "-1");
			map.put("msg", "�Ƴ�ʧ�ܣ�����ϵϵͳ����Ա!");
		} else {
			map.put("done", "0");
			map.put("msg", "�ɹ��Ƴ��û�!");
		}
		JSONObject jsonObject = JSONObject.fromObject(map);
		response.getWriter().write(jsonObject.toString());
	}

}