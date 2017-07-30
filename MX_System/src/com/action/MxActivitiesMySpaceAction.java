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

	//����ע������
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
	
	//��ǰ�û�id����
	private  MxUsersData userInfo;//�����û�������Ϣ
	public MxUsersData getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(MxUsersData userInfo) {
		this.userInfo = userInfo;
	}

	//��ռ�id����
	private int myspaceId;
	public int getMyspaceId() {
		return myspaceId;
	}
	public void setMyspaceId(int myspaceId) {
		this.myspaceId = myspaceId;
	}

	//��ռ����ۻ���
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

	//�����ռ�
	private List<MxActivitiesMySpaceUsers> mySpaceUsersList;
	public List<MxActivitiesMySpaceUsers> getMySpaceUsersList() {
		return mySpaceUsersList;
	}
	public void setMySpaceUsersList(List<MxActivitiesMySpaceUsers> mySpaceUsersList) {
		this.mySpaceUsersList = mySpaceUsersList;
	}
	
	//������˿ռ��װ������
	private ActivitiesUserMySpaceMine myspaceUserMine;
	public ActivitiesUserMySpaceMine getMyspaceUserMine() {
		return myspaceUserMine;
	}
	public void setMyspaceUserMine(ActivitiesUserMySpaceMine myspaceUserMine) {
		this.myspaceUserMine = myspaceUserMine;
	}
	
	/**
	 * ����*��ռ�action�е�Ĭ�ϴ����� ����
	 */
	public String execute() throws Exception {

		return null;
	}

	/**
	 * ��ת���û����˿ռ��б�ҳ��
	 * 
	 */
	public String gotoActivitiesMySpaceList() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		SNSUserInfo snsUserInfo=WeixinUtil.validateWeixinWebUser(request);
		if(snsUserInfo==null){
			return "noFocus";
		}
		
		userInfo=userService.getUserByOpenId(snsUserInfo.getOpenId());//����openId��ȡ�û�ϵͳ��Ϣ
		
		if(userInfo==null){
			//û���û���Ϣ��˵��δ��ע���ں�
			return "noFocus";
		}else{
			List<MxActivitiesMySpaceUsers> userMySpaceList = activitiesMySpaceService.getMySpaceListByUserId(userInfo.getUserId());
			//if(userMySpaceList.size()==0){
				//δ�μӹ��κλ
				//return "currentActivitiesList";//��ת����ǰ���ڱ����Ļ�б�
			//}else{
				List<MxActivitiesMySpaceData> userMySpaceDataList=activitiesMySpaceService.getMySpaceListBySpceIds(userMySpaceList);
				request.setAttribute("userMySpaceDataList",userMySpaceDataList);//���ض����б��ǰ̨
				
				return "activitiesMySpaceList";
			//}
		}
		
		
	}

	/**
	 * ��ת����ռ�ҳ�������
	 */
	public String gotoActivitiesMySpaceMain() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		myspaceId=Integer.parseInt(request.getParameter("myspaceId"));
		//request.setAttribute("myspaceId",myspaceId);
		
		return "activitiesMySpaceMain";
	}

	/**
	 * ��ȡ��ռ����������б�
	 */
	public String getActivitiesMySpaceCommentList() {
		//if(userMySpaceCommentList==null||userMySpaceCommentList.size()==0){
		userMySpaceCommentList=activitiesMySpaceService.getUserMySpaceCommontList(myspaceId);
		//}
		return "activitiesMySpaceCommentList";
	}

	/**
	 * ��ȡ��ռ��ز��б�
	 */
	public String getActivitiesMySpaceMaterialList() {
		userMySpaceMaterialList=activitiesMySpaceService.getUserMySpaceMaterialList(myspaceId);
		return "activitiesMySpaceMaterialList";
	}

	/**
	 * ��ȡ��ռ��û�
	 */
	public String getActivitiesMySpaceUsersList() {
		mySpaceUsersList=activitiesMySpaceService.getMySpaceUsersList(myspaceId);
		
		return "activitiesMySpaceUsersList";
		//return "testWebUploader";
	}

	/**
	 * ��ȡ��ռ��ҵ������б�
	 */
	public String getActivitiesMySpaceMine() {
		myspaceUserMine=activitiesMySpaceService.getMySpaceUserMine(userInfo.getUserId(),myspaceId);
		return "activitiesMySpaceMine";
	}

	/**
	 * �����ռ���������
	 */
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
	public void DoCreateInviteCodeUrl() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();// ����request����
		request.setCharacterEncoding("UTF-8");
		HttpServletResponse response = ServletActionContext.getResponse();// response���󷵻����ݸ�ǰ̨
		response.setContentType("application/json; charset=utf-8");
		String initiator_userId = request.getParameter("initiator_userId").toString();// ��ȡ�û�id
		String myspaceId = request.getParameter("myspaceId").toString();// ��ȡ��ռ�id
		MxActivitiesData activitiesData=activitiesMySpaceService.getActivityByMyspaceId(Integer.parseInt(myspaceId));
		String inviteCodeStr=PasswordUtil.createPWD(6);//����8λ�����֤��
		MxActivitiesMySpaceInviteCode inviteCode=new MxActivitiesMySpaceInviteCode(activitiesData,Integer.parseInt(myspaceId),Integer.parseInt(initiator_userId), inviteCodeStr, 0,-1,new Timestamp(System.currentTimeMillis()));
		boolean b=activitiesMySpaceService.addActivityInviteCode(inviteCode);
		
		//���ɵ���������WeixinSignUtil.serverUrl+"activitiesMySpace!validateInviteCode.action?activityId=1&myspaceId=5&inviteCode=jdfjsj"
		
		
		Map<String, String> map = new HashMap<String, String>();
		if (b==false) {
			map.put("done", "-1");
			map.put("inviteCodeUrl", "");
			map.put("msg", "���ɻ��������ʧ��!");
		} else {

			//String inviteUrl=WeixinSignUtil.serverUrl+"activitiesMySpace!validateInviteCode.action?activityId="
			//			+activitiesData.getActivitiesId()+"&myspaceId="+myspaceId+"&inviteCode="+inviteCodeStr;
			//String inviteUrl=inviteCodeStr;
			map.put("done", "0");
			map.put("inviteCode", inviteCodeStr);// ��ʾͼƬ���������·��
			map.put("msg", "�ɹ�����������Чʱ��Ϊ24Сʱ��");
			System.out.println("���ɻ�������ӳɹ���");
		}
		JSONObject jsonObject = JSONObject.fromObject(map);
		response.getWriter().write(jsonObject.toString());
		
	}
	
	/*
	 * ��֤����������Ч��
	 */
	public void validateInviteCode() throws IOException{
		//��ȡ��������Ϣ���ж��������Ƿ���ڣ��ж��������Ƿ����

		HttpServletRequest request = ServletActionContext.getRequest();// ����request����
		request.setCharacterEncoding("UTF-8");
		HttpServletResponse response = ServletActionContext.getResponse();// response���󷵻����ݸ�ǰ̨
		response.setContentType("application/json; charset=utf-8");
		String userId = request.getParameter("userId").toString();// ��ȡ�û�id
		String inviteCode = request.getParameter("inviteCode").toString();// ��ȡ��ռ���֤��
		//MxActivitiesData activitiesData=activitiesMySpaceService.getActivityByMyspaceId(Integer.parseInt(myspaceId));
		//String inviteCodeStr=PasswordUtil.createPWD(6);//����8λ�����֤��
		//MxActivitiesMySpaceInviteCode getInviteCode=new MxActivitiesMySpaceInviteCode(activitiesData,Integer.parseInt(myspaceId),Integer.parseInt(userId), inviteCodeStr, 0,-1);
		boolean b=activitiesMySpaceService.validateInviteCode(inviteCode,Integer.parseInt(userId));
		
		//���ɵ���������WeixinSignUtil.serverUrl+"activitiesMySpace!validateInviteCode.action?activityId=1&myspaceId=5&inviteCode=jdfjsj"
		
		
		Map<String, String> map = new HashMap<String, String>();
		if (b==false) {
			map.put("done", "-1");
			//map.put("inviteCodeUrl", "");
			map.put("msg", "��֤����Ч!");
		} else {

			//String inviteUrl=WeixinSignUtil.serverUrl+"activitiesMySpace!validateInviteCode.action?activityId="
			//			+activitiesData.getActivitiesId()+"&myspaceId="+myspaceId+"&inviteCode="+inviteCodeStr;
			//String inviteUrl=inviteCodeStr;
			map.put("done", "0");
			//map.put("inviteCode", inviteCodeStr);// ��ʾͼƬ���������·��
			map.put("msg", "�ѳɹ��μӻ��");
			System.out.println("�û��ɹ��μӻ��");
		}
		JSONObject jsonObject = JSONObject.fromObject(map);
		response.getWriter().write(jsonObject.toString());
		
	}
	
	
	
	/*
	 * ѹ�����ϴ�ͼƬ����
	 */
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
		String imgPath=showPath+imgName;
		
		
		MxActivitiesMySpaceMaterial material=new MxActivitiesMySpaceMaterial();
		material.setCreateDate(new Timestamp(System.currentTimeMillis()));
		material.setDescribe("ͼƬ����");
		material.setLoadUrl(imgPath);
		material.setMaterialType(1);//ͼƬ����Ϊ1
		material.setMyspaceId(Integer.parseInt(myspaceId));
		material.setOthers("");
		material.setSubmitUserId(Integer.parseInt(userId));
		boolean saveToSQL=activitiesMySpaceService.saveActivitiesMySpaceMaterial(material);
		
		if (imgName == null||saveToSQL==false) {
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
	public void CommentClickPraise() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();// ����request����
		request.setCharacterEncoding("UTF-8");
		HttpServletResponse response = ServletActionContext.getResponse();// response���󷵻����ݸ�ǰ̨
		response.setContentType("application/json; charset=utf-8");
		int userId = Integer.parseInt(request.getParameter("userId").toString());// ��ȡ�û�id
		int commentId = Integer.parseInt(request.getParameter("commentId").toString());// ��ȡ��ռ�id
		
		boolean isClicked=activitiesMySpaceService.commentClickPraise(commentId,userId);
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
	
	public void testWebUploader(){
		System.out.println("testWebUploader");
	}
}
