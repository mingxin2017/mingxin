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


import com.mx.ssh.bean.MxActivitiesData;
import com.mx.ssh.bean.MxUsersData;
import com.mx.ssh.bean.PageBean;
import com.mx.ssh.service.IActivitiesService;
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
@Namespace(value = "/activitiesAction")
public class ActivitiesAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;

	@Autowired
	private IActivitiesService activitiesService;     //�ö������ Spring ����ע��
	
	
	@Override
	public String execute() throws Exception {
		//user.action���õķ���
		return "LoginSuccess";

	}

	@Action(value = "gotoActivitiesList", results = { 
			@Result(name = "activitiesList", location = "/SystemPages/Activities/activitiesList.jsp")})
	public String gotoActivitiesList() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();// ����request����
		request.setCharacterEncoding("UTF-8");
		
		PageBean<MxActivitiesData> allActivities = activitiesService.findByPage(1);
		
		request.setAttribute("allActivities", allActivities);
		return "activitiesList";
	}
	
	
	/*
	 * ����ɾ���
	 */
	@Action(value = "deleteActivities")
	public void deleteActivities() throws IOException{
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
			
			boolean done=activitiesService.deleteActivities(idList);
			
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
		
	}
	
	/*
	 * ��ӻҳ��
	 */
	@Action(value = "gotoActivitiesAdd", results = { 
			@Result(name = "addActivities", location = "/SystemPages/Activities/activitiesAdd.jsp")})
	public String gotoActivitiesAdd(){
		return "addActivities";
	}
	
	
	
}
