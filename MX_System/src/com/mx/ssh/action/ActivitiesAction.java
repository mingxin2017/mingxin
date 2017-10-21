package com.mx.ssh.action;

import java.io.File;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mx.ssh.bean.MxActivitiesData;
import com.mx.ssh.bean.MxActivitiesMySpaceData;
import com.mx.ssh.bean.MxUsersData;
import com.mx.ssh.bean.PageBean;
import com.mx.ssh.service.IActivitiesService;
import com.mx.ssh.util.ImageMethod;
import com.mx.ssh.util.JsonDateValueProcessorUtil;
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
	
	//����ֵ�����ļ����������Ӧ����upload3.js�е�name���ԣ�name����ֵΪfile����ʱstruts�Ϳ��Ի�ȡ��file���ļ����󣬲���Ҫʵ������struts��ܻ��Զ�ע�����ֵ���򿪵��Դ��ڣ���һ�¾�������
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



	//���ļ��ϴ����ļ�����spring�ϴ����ԣ��ļ�����ʽΪname����+FileName
	private String imgFileFileName;

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
	
	
	/*
	 * �����ռ�ҳ��
	 */
	@Action(value = "gotoSpaceManage", results = { 
			@Result(name = "gotoSpaceManage", location = "/SystemPages/ActivitiesMySpace/activitySpaceEdit.jsp")})
	public String gotoSpaceManage() throws IOException{
		
		HttpServletRequest request = ServletActionContext.getRequest();// ����request����
		request.setCharacterEncoding("UTF-8");
		
		int activitiesId=Integer.parseInt(request.getParameter("activitiesId").trim()); 
		
		MxActivitiesMySpaceData activitySpace=activitiesService.getSpaceByActivityId(activitiesId);
		
		request.setAttribute("activitySpace", activitySpace);
		
		return "gotoSpaceManage";
	}
	
	
	
	/*
	 *�ϴ������
	 */
	@Action(value = "uploadCoverImage")
	public void uploadCoverImage() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();// ����request����
		request.setCharacterEncoding("UTF-8");
		HttpServletResponse response = ServletActionContext.getResponse();// response���󷵻����ݸ�ǰ̨
		response.setContentType("application/json; charset=utf-8");

		File file = this.getImgFile();
		
		System.out.println(file.getName());
//		String base64Img=request.getParameter("img").toString();
//		base64Img=base64Img.replace("data:image/jpeg;base64,", "");//ȥ��base64�����õ��ļ�ͷ
		String realSavePath=request.getSession().getServletContext().getRealPath("/SystemPages/common/uploadImg/activityCoverImage/");//����ͼƬ�ľ���·��
		
		String imgName=ImageMethod.SaveImage(file, realSavePath);//����ͼƬ��ϵͳӦ���ļ�����
		
		
		Map<String, String> map = new HashMap<String, String>();
		String showPath=request.getContextPath() +"/SystemPages/common/uploadImg/activityCoverImage/";
		//System.out.println(showPath+imgName);
		if(imgName==null){
			map.put("done", "-1");
			map.put("imgSrc", showPath+"nofound.jpg");
			map.put("msg", "ͼƬ�ϴ�ʧ����!");
		}else{
			map.put("done", "0");
			map.put("imgSrc", showPath+imgName);//��ʾͼƬ���������·��
			map.put("msg", "ͼƬ�ϴ��ɹ�!");
			System.out.println("�û��ϴ�ͼƬ����"+showPath+imgName);
		}
		
        JSONObject jsonObject = JSONObject.fromObject(map);
        response.getWriter().write(jsonObject.toString()); 

	}
	
	
	/*
	 * ���ϵͳ����Ա
	 */
	@Action(value = "addActivity")
	public void addActivity() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();// ����request����
		request.setCharacterEncoding("UTF-8");
		HttpServletResponse response = ServletActionContext.getResponse();// response���󷵻����ݸ�ǰ̨
		response.setContentType("application/json; charset=utf-8");
		
		boolean isDone=activitiesService.addActivity(request);
		
		Map<String, String> map = new HashMap<String, String>();
		if (!isDone) {
			map.put("done", "-1");
			map.put("msg", "��ӻʧ��!");
		} else {
			map.put("done", "0");
			map.put("msg", "�ɹ���ӻ!");
		}
		JSONObject jsonObject = JSONObject.fromObject(map);
		response.getWriter().write(jsonObject.toString());
	}
		
	
	/*
	 * ���ϵͳ����Ա
	 */
	@Action(value = "editActivitySpace")
	public void editActivitySpace() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();// ����request����
		request.setCharacterEncoding("UTF-8");
		HttpServletResponse response = ServletActionContext.getResponse();// response���󷵻����ݸ�ǰ̨
		response.setContentType("application/json; charset=utf-8");
		
		boolean isDone=activitiesService.editActivitySpace(request);
		
		Map<String, String> map = new HashMap<String, String>();
		if (!isDone) {
			map.put("done", "-1");
			map.put("msg", "��ռ���Ϣ�޸�ʧ��!");
		} else {
			map.put("done", "0");
			map.put("msg", "��ռ���Ϣ�޸Ļ!");
		}
		JSONObject jsonObject = JSONObject.fromObject(map);
		response.getWriter().write(jsonObject.toString());
	}
	
	
	/*
	 * ��ѯ�û�
	 */

	@Action(value = "searchActivity")
	public void searchActivity() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();// ����request����
		request.setCharacterEncoding("UTF-8");
		HttpServletResponse response = ServletActionContext.getResponse();// response���󷵻����ݸ�ǰ̨
		
		response.setContentType("application/json; charset=utf-8");
		
		String txtSearch=request.getParameter("txtSearch");
		PageBean<MxActivitiesData> searchActivities = activitiesService.searchActivity(txtSearch);
	
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig .setExcludes( new String[]{ "mxActivitiesMySpaceUserses" , "mxActivitiesMySpaceComments" } );//���˵������������תΪjson��ʽ����
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessorUtil());//��json������Date��������תΪyyyy-MM-dd HH:mm:ss�ַ���
		
		
		JSONObject jsonModel = JSONObject.fromObject(searchActivities,jsonConfig); 
		response.getWriter().write(jsonModel.toString());
		
	}
	
	/*
	 * ��ҳ��ѯ�
	 */
	@Action(value = "getActivitiesByPage")
	public void getActivitiesByPage() throws IOException{
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
			PageBean<MxActivitiesData> pageBean= activitiesService.findByPage(Integer.parseInt(page));
			JSONObject jsonModel = JSONObject.fromObject(pageBean,jsonConfig); 
			response.getWriter().write(jsonModel.toString());
			System.out.println(jsonModel.toString());
		}else{
			PageBean<MxActivitiesData> pageBean= activitiesService.findByPage(1);
			JSONObject jsonModel = JSONObject.fromObject(pageBean,jsonConfig); 
			response.getWriter().write(jsonModel.toString());
		}
		
	}
	
	
	/*
	 * �༭���Ϣҳ��
	 */
	@Action(value = "gotoActivityEdit", results = { 
			@Result(name = "editActivity", location = "/SystemPages/Activities/activitiesEdit.jsp")})
	public String gotoActivityEdit() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();// ����request����
		request.setCharacterEncoding("UTF-8");
		HttpServletResponse response = ServletActionContext.getResponse();// response���󷵻����ݸ�ǰ̨
		response.setContentType("application/json; charset=utf-8");

		int activitiesId = Integer.parseInt(request.getParameter("activitiesId").trim());

		MxActivitiesData activity=activitiesService.getActivityByID(activitiesId);
		
		request.setAttribute("editActivity", activity);
		
		return "editActivity";
	}
	
	
	/*
	 * �༭�����
	 */
	@Action(value = "editActivity")
	public void editActivity() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();// ����request����
		request.setCharacterEncoding("UTF-8");
		HttpServletResponse response = ServletActionContext.getResponse();// response���󷵻����ݸ�ǰ̨
		response.setContentType("application/json; charset=utf-8");
		
		
		boolean isDone=activitiesService.editActivity(request);
		
		Map<String, String> map = new HashMap<String, String>();
		if (!isDone) {
			map.put("done", "-1");
			map.put("msg", "���Ϣ�޸�ʧ��!");
		} else {
			map.put("done", "0");
			map.put("msg", "���Ϣ�޸Ļ!");
		}
		JSONObject jsonObject = JSONObject.fromObject(map);
		response.getWriter().write(jsonObject.toString());
		
	}
	
	/*
	 * ���á�ȡ���
	 */
	@Action(value = "open_close_Activities")
	public void open_close_Activities() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();// ����request����
		request.setCharacterEncoding("UTF-8");
		HttpServletResponse response = ServletActionContext.getResponse();// response���󷵻����ݸ�ǰ̨
		response.setContentType("application/json; charset=utf-8");

		String operate = request.getParameter("operate");
		int activitiesId = Integer.parseInt(request.getParameter("activitiesId"));

		boolean done = false;
		if ("1".equals(operate)) {
			done = activitiesService.setState(0, activitiesId);
		} else {
			done = activitiesService.setState(-1, activitiesId);
		}

		Map<String, String> map = new HashMap<String, String>();
		if (done == true) {
			map.put("done", "0");
			if ("0".equals(operate)) {// ����
				map.put("operate", "0");
			} else if("-1".equals(operate)){// ����
				map.put("operate", "-1");
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
	
}
