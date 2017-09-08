package com.mx.ssh.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


import com.mx.ssh.bean.MxUsersData;
import com.mx.ssh.bean.PageBean;
import com.mx.ssh.service.IUserService;
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

	private IUserService userService;     //�ö������ Spring ����ע��
		
	//Spring����ע��Ķ��������get��set������������������get+��������Ϊ�˱��ڼ��䣬��������һ����ĸ���Դ�д��
    public void setUserService(IUserService userLoginService)    
    {
        this.userService = userLoginService;
    }
    public IUserService getUserService()
    {
        return userService;
    }
	
	public int pageCurrent=1; //��ǰ��ʾ���û���Ϣ��ҳ���־
	
	@Override
	public String execute() throws Exception {
		//user.action���õķ���
		return "LoginSuccess";

	}

	public String goToeditUser(int userId){
		
		return "editUser";
	}
	
	
	
	
	
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
		
		PageBean<MxUsersData> allUsers = userService.findByPage(1);
//		HttpServletRequest request = ServletActionContext.getRequest();// ����request����
//		request.setCharacterEncoding("UTF-8");
//		request.setAttribute("allUsers", allUsers);
		 ActionContext.getContext().getValueStack().set("allUsers", allUsers);
		return "users";
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
	
	
}
