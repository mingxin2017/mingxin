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
//控制层的Spring注解
@Scope("prototype")
//支持多例
@Namespace(value = "/userAction")
public class UserAction extends ActionSupport {  
	
	private static final long serialVersionUID = 1L;

	@Autowired
	private IUserService userService;     //该对象采用 Spring 依赖注入,标签扫描注入
		
	
	public int pageCurrent=1; //当前显示的用户信息表页面标志
	
	@Override
	public String execute() throws Exception {
		//user.action调用的方法
		return "LoginSuccess";

	}

//	public String goToeditUser(int userId){
//		
//		return "editUser";
//	}
	
	
	
	
	
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
		HttpServletRequest request = ServletActionContext.getRequest();// 请求request对象
		request.setCharacterEncoding("UTF-8");
		
		PageBean<MxUsersData> allUsers = userService.findByPage(1);
		
		request.setAttribute("allUsers", allUsers);
//		 ActionContext.getContext().getValueStack().set("allUsers", allUsers);
		return "users";
	}
	
	
	@Action(value = "getUsersByPage")
	public void getUsersByPage() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();// 请求request对象
		request.setCharacterEncoding("UTF-8");
		HttpServletResponse response = ServletActionContext.getResponse();// response对象返回数据给前台
		response.setContentType("application/json; charset=utf-8");
		
		String page=request.getParameter("page");
		
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig .setExcludes( new String[]{ "mxActivitiesMySpaceUserses" , "mxActivitiesMySpaceComments" } );//过滤掉外键才能正常转为json格式数据
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessorUtil());//将json中所有Date类型数据转为yyyy-MM-dd HH:mm:ss字符串
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
	
	/*
	 * 添加用户页面
	 */
	@Action(value = "gotoUserAdd", results = { 
			@Result(name = "addUser", location = "/SystemPages/Users/userAdd.jsp")})
	public String gotoUserAdd(){
		return "addUser";
	}
	
	/*
	 * 添加系统管理员
	 */
	@Action(value = "addAdmin")
	public void addAdmin() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();// 请求request对象
		request.setCharacterEncoding("UTF-8");
		HttpServletResponse response = ServletActionContext.getResponse();// response对象返回数据给前台
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
			map.put("msg", "添加管理员失败!");
		} else {
			map.put("done", "0");
			map.put("msg", "添加管理员成功!");
		}
		JSONObject jsonObject = JSONObject.fromObject(map);
		response.getWriter().write(jsonObject.toString());
	}
	
	
	/*
	 * 批量删除用户
	 */
	@Action(value = "deleteUsers")
	public void deleteUsers() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();// 请求request对象
		request.setCharacterEncoding("UTF-8");
		HttpServletResponse response = ServletActionContext.getResponse();// response对象返回数据给前台
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
	 * 查询用户
	 */
	@Action(value = "searchUsers")
	public void searchUsers() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();// 请求request对象
		request.setCharacterEncoding("UTF-8");
		HttpServletResponse response = ServletActionContext.getResponse();// response对象返回数据给前台
		response.setContentType("application/json; charset=utf-8");
		
		String txtSearch=request.getParameter("txtSearch");
		
		PageBean<MxUsersData> allUsers = userService.searchUser(txtSearch);
		
		//request.setAttribute("allUsers", allUsers);
		
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig .setExcludes( new String[]{ "mxActivitiesMySpaceUserses" , "mxActivitiesMySpaceComments" } );//过滤掉外键才能正常转为json格式数据
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessorUtil());//将json中所有Date类型数据转为yyyy-MM-dd HH:mm:ss字符串
		
		
		JSONObject jsonModel = JSONObject.fromObject(allUsers,jsonConfig); 
		response.getWriter().write(jsonModel.toString());
		
		//return "users";
	}
	
	
	/*
	 * 启用、禁用用户
	 */
	@Action(value = "open_close_User")
	public void open_close_User() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();// 请求request对象
		request.setCharacterEncoding("UTF-8");
		HttpServletResponse response = ServletActionContext.getResponse();// response对象返回数据给前台
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
			if ("1".equals(operate)) {// 启用
				map.put("operate", "1");
			} else {// 禁用
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
	 * 删除用户
	 */
	@Action(value = "deleteUser")
	public void deleteUser() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();// 请求request对象
		request.setCharacterEncoding("UTF-8");
		HttpServletResponse response = ServletActionContext.getResponse();// response对象返回数据给前台
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
	 *重置用户密码 
	 */
	@Action(value = "restorePassword")
	public void restorePassword() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();// 请求request对象
		request.setCharacterEncoding("UTF-8");
		HttpServletResponse response = ServletActionContext.getResponse();// response对象返回数据给前台
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
	 * 编辑用户信息页面
	 */
	@Action(value = "gotoUserEdit", results = { 
			@Result(name = "eidtUser", location = "/SystemPages/Users/userEdit.jsp")})
	public String gotoUserEdit() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();// 请求request对象
		request.setCharacterEncoding("UTF-8");
		HttpServletResponse response = ServletActionContext.getResponse();// response对象返回数据给前台
		response.setContentType("application/json; charset=utf-8");

		int userId = Integer.parseInt(request.getParameter("userId").trim());

		MxUsersData user=userService.getUserByID(userId);
		
		request.setAttribute("editUser", user);
		
		return "eidtUser";
	}
	
	
	
}
