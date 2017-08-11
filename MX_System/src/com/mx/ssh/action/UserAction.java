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
}
