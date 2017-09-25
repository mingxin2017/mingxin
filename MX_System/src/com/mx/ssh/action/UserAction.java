package com.mx.ssh.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


import com.mx.ssh.bean.MxUsersData;
import com.mx.ssh.bean.PageBean;
import com.mx.ssh.service.IUserService;
import com.mx.ssh.util.JsonDateValueProcessorUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


/**
 * @author Administrator  2017
 *
 */

@Controller
//���Ʋ��Springע��
@Scope("prototype")
//֧�ֶ���
@Namespace(value = "/userAction")
public class UserAction extends ActionSupport {  
	
	private static final long serialVersionUID = 1L;

	@Autowired
	private IUserService userService;     //�ö������ Spring ����ע��,��ǩɨ��ע��
		
	
	public int pageCurrent=1; //��ǰ��ʾ���û���Ϣ��ҳ���־
	
	@Override
	public String execute() throws Exception {
		//user.action���õķ���
		return "LoginSuccess";

	}

//	public String goToeditUser(int userId){
//		
//		return "editUser";
//	}
	
	
	
	
	
	/**���ã��ֲ�ˢ���û�ҳ���񣬸��ݴ���ҳ�����������
	 * ����ֵ��
	 * ����ʱ�䣺5:50:37 PM  �����ߣ�wulm
	 */
	public void updateUserList() {
	    try {
	        List<MxUsersData> list = userService.getAllUsers();
	        PageBean buff=new PageBean();
	        buff.setList(list);
	        buff.setAllRow(list.size());
			JSONArray jsonList = JSONArray.fromObject(buff);//�û��б�
			String jsonStr=jsonList.toString();
			jsonStr=jsonStr.replaceAll("list", "rows");
			jsonStr=jsonStr.replaceAll("allRow", "total");
			jsonStr=jsonStr.substring(1,jsonStr.length()-1);
	        HttpServletResponse response =   ServletActionContext.getResponse();
	        response.setContentType("text/plain;charset=UTF-8");//���÷��������ı�����Ϊutf-8
	        response.getWriter().write(jsonStr);//����json���ݵ�ǰ̨
	        System.out.println(jsonStr);
	        response.getWriter().flush();
	        
	    }catch (Exception e){
	        e.printStackTrace();
	    }
	    
	}
	
	
	/*
	 * ��̨�û���¼
	 */
	@Action(value = "doLogin")
	public void doLogin() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();// ����request����
		request.setCharacterEncoding("UTF-8");
		HttpServletResponse response = ServletActionContext.getResponse();// response���󷵻����ݸ�ǰ̨
		response.setContentType("application/json; charset=utf-8");

		String userName = request.getParameter("userName").toString();// ��ȡ�û���
		String userPwd = request.getParameter("userPwd").toString();// ��ȡ����

		MxUsersData userInfo = userService.userLogin(userName,
				userPwd);

		Map<String, String> map = new HashMap<String, String>();
		if (null==userInfo) {
			map.put("done", "-1");
			map.put("msg", "�û������������!");
			JSONObject jsonObject = JSONObject.fromObject(map);
			response.getWriter().write(jsonObject.toString());
			//return null;
		} else {
			map.put("done", "0");
			map.put("msg", "��¼�ɹ�!");
			request.getSession().setAttribute("userInfo", userInfo);//���ù���Աsessionֵ
			JSONObject jsonObject = JSONObject.fromObject(map);
			response.getWriter().write(jsonObject.toString());
			//return "sysIndex";
		}
	}
	
	/*
	 * ��̨�û���¼
	 */
	@Action(value = "gotoIndex", results = { 
			@Result(name = "sysIndex", location = "/SystemPages/index.jsp")})
	public String gotoIndex(){
		return "sysIndex";
	}
	
	/*
	 * ϵͳĬ��ҳ��
	 */
	@Action(value = "gotoDefault", results = { 
			@Result(name = "welcome", location = "/SystemPages/welcome.jsp")})
	public String gotoDefault(){
		return "welcome";
	}
	
	/*
	 * ��̨�û��б�ҳ��
	 */
	@Action(value = "gotoUserList", results = { 
			@Result(name = "users", location = "/SystemPages/Users/usersList.jsp")})
	public String gotoUserList() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();// ����request����
		request.setCharacterEncoding("UTF-8");
		
		PageBean<MxUsersData> allUsers = userService.findByPage(1);
		
		request.setAttribute("allUsers", allUsers);
//		 ActionContext.getContext().getValueStack().set("allUsers", allUsers);
		return "users";
	}
	
	
	@Action(value = "getUsersByPage")
	public void getUsersByPage() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();// ����request����
		request.setCharacterEncoding("UTF-8");
		HttpServletResponse response = ServletActionContext.getResponse();// response���󷵻����ݸ�ǰ̨
		response.setContentType("application/json; charset=utf-8");
		
		String page=request.getParameter("page");
		
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig .setExcludes( new String[]{ "mxActivitiesMySpaceUserses" , "mxActivitiesMySpaceComments" } );//���˵������������תΪjson��ʽ����
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessorUtil());//��json������Date��������תΪyyyy-MM-dd HH:mm:ss�ַ���
		//JSONObject jsonModel= JSONArray.fromObject(yourObject, jsonConfig );

		if(!"".equals(page)){
			PageBean<MxUsersData> pageBean= userService.findByPage(Integer.parseInt(page));
			JSONObject jsonModel = JSONObject.fromObject(pageBean,jsonConfig); 
			response.getWriter().write(jsonModel.toString());
			System.out.println(jsonModel.toString());
		}else{
			PageBean<MxUsersData> pageBean= userService.findByPage(1);
			JSONObject jsonModel = JSONObject.fromObject(pageBean,jsonConfig); 
			response.getWriter().write(jsonModel.toString());
		}
		
	}
	
	
	/*
	 * ��̨�û��˳���¼
	 */
	@Action(value = "/userLogout", results = { 
			@Result(name = "login", location = "/SystemPages/login.jsp")})
	public String userLogout() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();// ����request����
		request.setCharacterEncoding("UTF-8");
		request.getSession().removeAttribute("userInfo");
		return "login";
	}
	
	/*
	 * ����û�ҳ��
	 */
	@Action(value = "gotoUserAdd", results = { 
			@Result(name = "addUser", location = "/SystemPages/Users/userAdd.jsp")})
	public String gotoUserAdd(){
		return "addUser";
	}
	
	/*
	 * ���ϵͳ����Ա
	 */
	@Action(value = "addAdmin")
	public void addAdmin() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();// ����request����
		request.setCharacterEncoding("UTF-8");
		HttpServletResponse response = ServletActionContext.getResponse();// response���󷵻����ݸ�ǰ̨
		response.setContentType("application/json; charset=utf-8");
		
		String adminName=request.getParameter("adminName");
		String password=request.getParameter("password");
		int sex=Integer.parseInt(request.getParameter("sex").trim());
		String phone=request.getParameter("phone");
		String email=request.getParameter("email");
		
		//System.out.println("roleId:"+request.getParameter("adminRole")+"  sex:"+sex);
		
		int adminRole=("0".equals(request.getParameter("adminRole").trim())?1100:1111);
		String others=request.getParameter("others");
		
		
		MxUsersData user=new MxUsersData(adminName,password,adminRole,
				"","","",
				new Date(),"", sex/*Integer userSex*/,
				"","",0,
				-1, email,phone,
				-1,"",0,
				-1, "",new Date(),
				"","","");
		
		boolean isDone=userService.addAdmin(user);
		
		Map<String, String> map = new HashMap<String, String>();
		if (!isDone) {
			map.put("done", "-1");
			map.put("msg", "��ӹ���Աʧ��!");
		} else {
			map.put("done", "0");
			map.put("msg", "��ӹ���Ա�ɹ�!");
		}
		JSONObject jsonObject = JSONObject.fromObject(map);
		response.getWriter().write(jsonObject.toString());
	}
	
	
	/*
	 * ����ɾ���û�
	 */
	@Action(value = "deleteUsers")
	public void deleteUsers() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();// ����request����
		request.setCharacterEncoding("UTF-8");
		HttpServletResponse response = ServletActionContext.getResponse();// response���󷵻����ݸ�ǰ̨
		response.setContentType("application/json; charset=utf-8");
		
		String ids=request.getParameter("ids");
		System.out.println(ids);
		List<Integer> idList=new ArrayList();
		if(ids!=null&&ids!=""){
			JSONArray array= JSONArray.fromObject(ids);
			List<String> list=JSONArray.toList(array);
			for(int i=0;i<list.size();i++){
				System.out.println(list.get(i)+"-----");
				idList.add(i, Integer.parseInt(list.get(i)));
			}
			
			boolean done=userService.deleteUsers(idList);
			
			Map<String, String> map = new HashMap<String, String>();
			if(done==true){
				map.put("done", "0");
				JSONObject jsonObject = JSONObject.fromObject(map);
				response.getWriter().write(jsonObject.toString());
			}else{
				map.put("done", "-1");
				JSONObject jsonObject = JSONObject.fromObject(map);
				response.getWriter().write(jsonObject.toString());
			}
			
		}
//		JSONArray array= JSONArray.fromObject(ids);
//		
//		List<String> list=JSONArray.toList(array); 
		
	}
	
	
	/*
	 * ��ѯ�û�
	 */
	@Action(value = "searchUsers")
	public void searchUsers() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();// ����request����
		request.setCharacterEncoding("UTF-8");
		HttpServletResponse response = ServletActionContext.getResponse();// response���󷵻����ݸ�ǰ̨
		response.setContentType("application/json; charset=utf-8");
		
		String txtSearch=request.getParameter("txtSearch");
		
		PageBean<MxUsersData> allUsers = userService.searchUser(txtSearch);
		
		//request.setAttribute("allUsers", allUsers);
		
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig .setExcludes( new String[]{ "mxActivitiesMySpaceUserses" , "mxActivitiesMySpaceComments" } );//���˵������������תΪjson��ʽ����
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessorUtil());//��json������Date��������תΪyyyy-MM-dd HH:mm:ss�ַ���
		
		
		JSONObject jsonModel = JSONObject.fromObject(allUsers,jsonConfig); 
		response.getWriter().write(jsonModel.toString());
		
		//return "users";
	}
	
	
	/*
	 * ���á������û�
	 */
	@Action(value = "open_close_User")
	public void open_close_User() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();// ����request����
		request.setCharacterEncoding("UTF-8");
		HttpServletResponse response = ServletActionContext.getResponse();// response���󷵻����ݸ�ǰ̨
		response.setContentType("application/json; charset=utf-8");

		String operate = request.getParameter("operate");
		int userId = Integer.parseInt(request.getParameter("userId"));

		boolean done = false;
		if ("1".equals(operate)) {
			done = userService.setState(0, userId);
		} else {
			done = userService.setState(-1, userId);
		}

		Map<String, String> map = new HashMap<String, String>();
		if (done == true) {
			map.put("done", "0");
			if ("1".equals(operate)) {// ����
				map.put("operate", "1");
			} else {// ����
				map.put("operate", "0");
			}
			JSONObject jsonObject = JSONObject.fromObject(map);
			response.getWriter().write(jsonObject.toString());
		} else {
			map.put("done", "-1");
			map.put("operate", "-1");
			JSONObject jsonObject = JSONObject.fromObject(map);
			response.getWriter().write(jsonObject.toString());
		}
	}
	
	
	/*
	 * ɾ���û�
	 */
	@Action(value = "deleteUser")
	public void deleteUser() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();// ����request����
		request.setCharacterEncoding("UTF-8");
		HttpServletResponse response = ServletActionContext.getResponse();// response���󷵻����ݸ�ǰ̨
		response.setContentType("application/json; charset=utf-8");

		int userId = Integer.parseInt(request.getParameter("userId"));

		boolean	done = userService.deleteUser(userId);
		
		Map<String, String> map = new HashMap<String, String>();
		if (done == true) {
			map.put("done", "0");
		} else {
			map.put("done", "-1");
		}
		JSONObject jsonObject = JSONObject.fromObject(map);
		response.getWriter().write(jsonObject.toString());
	}
	
	
	/*
	 *�����û����� 
	 */
	@Action(value = "restorePassword")
	public void restorePassword() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();// ����request����
		request.setCharacterEncoding("UTF-8");
		HttpServletResponse response = ServletActionContext.getResponse();// response���󷵻����ݸ�ǰ̨
		response.setContentType("application/json; charset=utf-8");

		int userId = Integer.parseInt(request.getParameter("userId"));

		boolean	done = userService.restoreUserPassword(userId);
		
		Map<String, String> map = new HashMap<String, String>();
		if (done == true) {
			map.put("done", "0");
		} else {
			map.put("done", "-1");
		}
		JSONObject jsonObject = JSONObject.fromObject(map);
		response.getWriter().write(jsonObject.toString());
	}
	
	
	/*
	 * �༭�û���Ϣҳ��
	 */
	@Action(value = "gotoUserEdit", results = { 
			@Result(name = "eidtUser", location = "/SystemPages/Users/userEdit.jsp")})
	public String gotoUserEdit() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();// ����request����
		request.setCharacterEncoding("UTF-8");
		HttpServletResponse response = ServletActionContext.getResponse();// response���󷵻����ݸ�ǰ̨
		response.setContentType("application/json; charset=utf-8");

		int userId = Integer.parseInt(request.getParameter("userId").trim());

		MxUsersData user=userService.getUserByID(userId);
		
		request.setAttribute("editUser", user);
		
		return "eidtUser";
	}
	
	
	
}
