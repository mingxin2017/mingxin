package com.mx.ssh.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.mx.ssh.bean.MxActivitiesData;
import com.mx.ssh.bean.PageBean;
import com.mx.ssh.service.IActivitiesService;
import com.mx.ssh.util.ImageMethod;
import com.opensymphony.xwork2.ActionSupport;



/**
 * @author Administrator  2017
 *
 */

@Controller
//控制层的Spring注解
@Scope("prototype")
//支持多例
@Namespace(value = "/activitiesAction")
public class ActivitiesAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	
	//属性值，单文件的情况，对应的是upload3.js中的name属性，name属性值为file，此时struts就可以获取到file的文件对象，不需要实例化，struts框架会自动注入对象值，打开调试窗口，看一下就明白了
	private File imgFile;
	public File getImgFile() {
		return imgFile;
	}

	public void setImgFile(File imgFile) {
		this.imgFile = imgFile;
	}

	public String getImgFileFileName() {
		return imgFileFileName;
	}

	public void setImgFileFileName(String imgFileFileName) {
		this.imgFileFileName = imgFileFileName;
	}



	//单文件上传的文件名，spring上传特性，文件名格式为name属性+FileName
	private String imgFileFileName;

	@Autowired
	private IActivitiesService activitiesService;     //该对象采用 Spring 依赖注入
	
	
	@Override
	public String execute() throws Exception {
		//user.action调用的方法
		return "LoginSuccess";

	}

	@Action(value = "gotoActivitiesList", results = { 
			@Result(name = "activitiesList", location = "/SystemPages/Activities/activitiesList.jsp")})
	public String gotoActivitiesList() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();// 请求request对象
		request.setCharacterEncoding("UTF-8");
		
		PageBean<MxActivitiesData> allActivities = activitiesService.findByPage(1);
		
		request.setAttribute("allActivities", allActivities);
		return "activitiesList";
	}
	
	
	/*
	 * 批量删除活动
	 */
	@Action(value = "deleteActivities")
	public void deleteActivities() throws IOException{
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
	 * 添加活动页面
	 */
	@Action(value = "gotoActivitiesAdd", results = { 
			@Result(name = "addActivities", location = "/SystemPages/Activities/activitiesAdd.jsp")})
	public String gotoActivitiesAdd(){
		return "addActivities";
	}
	
	
	
	/*
	 *上传活动封面
	 */
	@Action(value = "uploadCoverImage")
	public void uploadCoverImage() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();// 请求request对象
		request.setCharacterEncoding("UTF-8");
		HttpServletResponse response = ServletActionContext.getResponse();// response对象返回数据给前台
		response.setContentType("application/json; charset=utf-8");

		File file = this.getImgFile();
		
		System.out.println(file.getName());
//		String base64Img=request.getParameter("img").toString();
//		base64Img=base64Img.replace("data:image/jpeg;base64,", "");//去除base64中无用的文件头
		String realSavePath=request.getSession().getServletContext().getRealPath("/SystemPages/common/uploadImg/activityCoverImage/");//保存图片的绝对路径
		
		String imgName=ImageMethod.SaveImage(file, realSavePath);//保存图片到系统应用文件夹中
		
		
		Map<String, String> map = new HashMap<String, String>();
		String showPath=request.getContextPath() +"/SystemPages/common/uploadImg/activityCoverImage/";
		//System.out.println(showPath+imgName);
		if(imgName==null){
			map.put("done", "-1");
			map.put("imgSrc", showPath+"nofound.jpg");
			map.put("msg", "图片上传失败了!");
		}else{
			map.put("done", "0");
			map.put("imgSrc", showPath+imgName);//显示图片的完整相对路径
			map.put("msg", "图片上传成功!");
			System.out.println("用户上传图片至："+showPath+imgName);
		}
		
        JSONObject jsonObject = JSONObject.fromObject(map);
        response.getWriter().write(jsonObject.toString()); 

	}
		
	
	
	
}
