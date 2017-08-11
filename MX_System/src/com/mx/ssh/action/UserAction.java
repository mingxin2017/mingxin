package com.mx.ssh.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;


import com.mx.ssh.bean.MxUsersData;
import com.mx.ssh.bean.PageBean;
import com.mx.ssh.service.IUserService;
import com.opensymphony.xwork2.ActionSupport;


/**
 * @author Administrator  2017
 *
 */


@Action(value = "userAction", results = { 
		@Result(name = "success", location = "/success.jsp"),
		@Result(name = "input", location = "/register.jsp") })
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
}
