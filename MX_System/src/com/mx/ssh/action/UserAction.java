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
//控制层的Spring注解
@Scope("prototype")
//支持多例
@Namespace(value = "/userAction")
public class UserAction extends ActionSupport {  
	
	private static final long serialVersionUID = 1L;

	private IUserService userService;     //该对象采用 Spring 依赖注入
		
	//Spring依赖注入的对象必须有get、set方法。方法命名规则：get+变量名。为了便于记忆，变量名第一个字母可以大写。
    public void setUserService(IUserService userLoginService)    
    {
        this.userService = userLoginService;
    }
    public IUserService getUserService()
    {
        return userService;
    }
	
	public int pageCurrent=1; //当前显示的用户信息表页面标志
	
	@Override
	public String execute() throws Exception {
		//user.action调用的方法
		return "LoginSuccess";

	}

	public String goToeditUser(int userId){
		
		return "editUser";
	}
	
	
	
	
	
	/**作用：局部刷新用户页面表格，根据传入页码和条件参数
	 * 返回值：
	 * 创建时间：5:50:37 PM  创建者：wulm
	 */
	public void updateUserList() {
	    try {
	        List<MxUsersData> list = userService.getAllUsers();
	        PageBean buff=new PageBean();
	        buff.setList(list);
	        buff.setAllRow(list.size());
			JSONArray jsonList = JSONArray.fromObject(buff);//用户列表
			String jsonStr=jsonList.toString();
			jsonStr=jsonStr.replaceAll("list", "rows");
			jsonStr=jsonStr.replaceAll("allRow", "total");
			jsonStr=jsonStr.substring(1,jsonStr.length()-1);
	        HttpServletResponse response =   ServletActionContext.getResponse();
	        response.setContentType("text/plain;charset=UTF-8");//设置返回数据文本编码为utf-8
	        response.getWriter().write(jsonStr);//返回json数据到前台
	        System.out.println(jsonStr);
	        response.getWriter().flush();
	        
	    }catch (Exception e){
	        e.printStackTrace();
	    }
	    
	}
	
	
	/*
	 * 后台用户登录
	 */
	@Action(value = "doLogin")
	public void doLogin() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();// 请求request对象
		request.setCharacterEncoding("UTF-8");
		HttpServletResponse response = ServletActionContext.getResponse();// response对象返回数据给前台
		response.setContentType("application/json; charset=utf-8");

		String userName = request.getParameter("userName").toString();// 获取用户名
		String userPwd = request.getParameter("userPwd").toString();// 获取密码

		MxUsersData userInfo = userService.userLogin(userName,
				userPwd);

		Map<String, String> map = new HashMap<String, String>();
		if (null==userInfo) {
			map.put("done", "-1");
			map.put("msg", "用户名或密码错误!");
			JSONObject jsonObject = JSONObject.fromObject(map);
			response.getWriter().write(jsonObject.toString());
			//return null;
		} else {
			map.put("done", "0");
			map.put("msg", "登录成功!");
			request.getSession().setAttribute("userInfo", userInfo);//设置管理员session值
			JSONObject jsonObject = JSONObject.fromObject(map);
			response.getWriter().write(jsonObject.toString());
			//return "sysIndex";
		}
	}
	
	/*
	 * 后台用户登录
	 */
	@Action(value = "gotoIndex", results = { 
			@Result(name = "sysIndex", location = "/SystemPages/index.jsp")})
	public String gotoIndex(){
		return "sysIndex";
	}
	
	/*
	 * 系统默认页面
	 */
	@Action(value = "gotoDefault", results = { 
			@Result(name = "welcome", location = "/SystemPages/welcome.jsp")})
	public String gotoDefault(){
		return "welcome";
	}
	
	/*
	 * 后台用户列表页面
	 */
	@Action(value = "gotoUserList", results = { 
			@Result(name = "users", location = "/SystemPages/Users/usersList.jsp")})
	public String gotoUserList() throws IOException{
		
		PageBean<MxUsersData> allUsers = userService.findByPage(1);
//		HttpServletRequest request = ServletActionContext.getRequest();// 请求request对象
//		request.setCharacterEncoding("UTF-8");
//		request.setAttribute("allUsers", allUsers);
		 ActionContext.getContext().getValueStack().set("allUsers", allUsers);
		return "users";
	}
	
	/*
	 * 后台用户退出登录
	 */
	@Action(value = "/userLogout", results = { 
			@Result(name = "login", location = "/SystemPages/login.jsp")})
	public String userLogout() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();// 请求request对象
		request.setCharacterEncoding("UTF-8");
		request.getSession().removeAttribute("userInfo");
		return "login";
	}
	
	
}
