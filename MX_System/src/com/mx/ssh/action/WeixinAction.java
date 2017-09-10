package com.mx.ssh.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import sun.misc.BASE64Decoder;

import com.mx.ssh.bean.MxNewsContent;
import com.mx.ssh.bean.MxNewsData;
import com.mx.ssh.bean.MxRegion;
import com.mx.ssh.bean.MxSchools;
import com.mx.ssh.bean.MxUsersData;
import com.mx.ssh.service.IUserService;
import com.mx.ssh.service.IWeixinNewsService;
import com.mx.ssh.service.IWeixinService;
import com.mx.ssh.util.MsgResult;
import com.mx.ssh.util.TextValue;
import com.mx.weixin.pojo.JDSDKTicket;
import com.mx.weixin.pojo.SNSUserInfo;
import com.mx.weixin.pojo.WeixinOauth2Token;
import com.mx.weixin.util.JsapiTicketUtil;
import com.mx.weixin.util.MxKeyValue;
import com.mx.weixin.util.OAuth2TokenUtil;
import com.mx.weixin.util.ResultUtil;
import com.mx.weixin.util.WeixinSignUtil;

 
@Controller//���Ʋ��Springע��
@Scope("prototype")//֧�ֶ���
@Namespace(value="/weixin") //��ʾ��ǰAction���������ռ� 
@ParentPackage(value = "json-default") 
public class WeixinAction { 

	private static final long serialVersionUID = -1627548805862485475L;
	private final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private IWeixinService weixinService;
	
	@Autowired
	private IWeixinNewsService weixinNewsService;//΢��������ҵ��
	
	@Autowired
	private IUserService userService;
	
    private String uploadPath = "D:\\upload"; // �ϴ��ļ���Ŀ¼  
    private File upload;//uploadΪ����nameֵ
    private String uploadFileName;//nameֵ+FileName
    private String uploadContentType;//nameֵ+ContextType
    
    private Map<String, Object> dataMap;  
    
    public Map<String, Object> getDataMap() {  
        return dataMap;  
    }  
     
    public void setDataMap(Map<String, Object> dataMap) {  
        this.dataMap = dataMap;  
    }  
	public String getUploadPath() {
		return uploadPath;
	}


	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}


	public File getUpload() {
		return upload;
	}


	public void setUpload(File upload) {
		this.upload = upload;
	}


	public String getUploadFileName() {
		return uploadFileName;
	}


	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}


	public String getUploadContentType() {
		return uploadContentType;
	}


	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

    /**
     * ��д����������,��֤��������ַ����Ч��   ���ýӿ�������ϢURL��Tokenʱʹ��
     * ���echostrԭ����xml��ʽ����
     * @throws IOException 
     */
	@Action(value = "checkMsgFromWeixinServer")  
	public void checkMsgFromWeixinServer() throws IOException{
		// ��������Ӧ�ı��������ΪUTF-8����ֹ�������룩
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// ���ղ���΢�ż���ǩ���� ʱ����������
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		// ����ַ���
		String echostr = request.getParameter("echostr");
		// ����У��
		if (WeixinSignUtil.checkSignature(signature, timestamp, nonce)) {
			logger.info("���ýӿ�������ϢURL��Token�ɹ���");
		}else{
			echostr = "";
			logger.info("���ýӿ�������ϢURL��Tokenʧ�ܣ���鿴�����ԣ�");
		}
		PrintWriter out = response.getWriter();
		out.print(echostr);
		out.close();
		out = null;
	}
	
	/**
	 * ����*΢��action�еĴ�����
	 */
	@Action(value = "defaultMethod")
	public String defaultMethod() throws Exception {
		
        System.out.println("����weixinAction.defaultMethod");   
		// ��������Ӧ�ı��������ΪUTF-8����ֹ�������룩
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		// ���ղ���΢�ż���ǩ���� ʱ����������
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");

		// ����ַ���
		String echostr = request.getParameter("echostr");
		// System.out.println(signature+"...............................");
		PrintWriter out = response.getWriter();
		
		//System.out.println("signature;timestamp;nonce"+signature+','+timestamp+','+nonce);
		
		//���ز���
		String respParams= null;

		// ����У��
		if (WeixinSignUtil.checkSignature(signature, timestamp, nonce)) {
			String method = ServletActionContext.getRequest().getMethod();
			if (method.equals("POST")) {
				// ���ú��ķ�������մ�������
				String respXml = weixinService.processRequest(request);
				out.print(respXml);
			} else {
				out.print(echostr);
			}

		}
		out.close();
		out = null;
		return respParams;
	}

	
	@Action(value = "getWebAccessToken")
	public String getWebAccessToken() throws ServletException, IOException {
       
		// ��������Ӧ�ı��������ΪUTF-8����ֹ�������룩
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		// �û�ͬ����Ȩ���ܻ�ȡ��code
		String code = request.getParameter("code");
		String state = request.getParameter("state");
		if (!"authdeny".equals(code)) {
			// ��ȡ��ҳ��Ȩaccess_token
			WeixinOauth2Token weixinOauth2Token = OAuth2TokenUtil
					.getOauth2AccessToken(WeixinSignUtil.AppID,
							WeixinSignUtil.AppSecret, code);
			// ��ҳ��Ȩ�ӿڷ���ƾ֤
			String accessToken = weixinOauth2Token.getAccessToken();
			// �û���ʶ
			String openId = weixinOauth2Token.getOpenId();
			// ��ȡ�û���Ϣ
			SNSUserInfo snsUserInfo = OAuth2TokenUtil.getSNSUserInfo(
					accessToken, openId);
			System.out.println(snsUserInfo.getOpenId() + ","
					+ snsUserInfo.getNickname() + ","
					+ snsUserInfo.getHeadImgUrl());
			// ����Ҫ���ݵĲ���
			request.setAttribute("snsUserInfo", snsUserInfo);
			request.setAttribute("state", state);
		}
		// ��ת��index.jsp
		//request.getRequestDispatcher("login.jsp").forward(request, response);

		return "weixinHome";
	}

	
	//����ʱ�����ʱ����ҳ��ת����
	@Action(value = "getCountryNews", results = { @Result(name = "getCountryNews", location = "/WeixinPages/News/countryNews.jsp") })
	public String getCountryNews() throws UnsupportedEncodingException{
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("UTF-8");
		//String newsTypeId = request.getParameter("newsTypeId");
		request.setAttribute("MxNewsDataList", weixinNewsService.getNewsByNewsTypeId("5"));
		return "getCountryNews";
	}
	
	
	//����ʱ��ѧУʱ����ҳ��ת����
	@Action(value = "getSchoolNews", results = { @Result(name = "getSchoolNews", location = "/WeixinPages/News/schoolNews.jsp") })
	public String getSchoolNews() throws UnsupportedEncodingException{
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("UTF-8");
		//String newsTypeId = request.getParameter("newsTypeId");
		request.setAttribute("MxNewsDataList", weixinNewsService.getNewsByNewsTypeId("4"));
		return "getSchoolNews";
	}
	
	//����Ͷ��ҳ��
	@Action(value = "loadEditNews", results = { @Result(name = "loadEditNews", location = "/WeixinPages/News/editNews.jsp") })
	public String loadEditNews() throws UnsupportedEncodingException{
		System.out.println("page:loadEditNews");
		
		// ��������Ӧ�ı��������ΪUTF-8����ֹ�������룩
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("UTF-8");

		// �û�ͬ����Ȩ���ܻ�ȡ��code
/*		String code = request.getParameter("code");
		String state = request.getParameter("state");
		if (!"authdeny".equals(code)) {
			// ��ȡ��ҳ��Ȩaccess_token
			WeixinOauth2Token weixinOauth2Token = OAuth2TokenUtil.getOauth2AccessToken(WeixinSignUtil.AppID,WeixinSignUtil.AppSecret, code);
			// ��ҳ��Ȩ�ӿڷ���ƾ֤
			String accessToken = weixinOauth2Token.getAccessToken();
			// �û���ʶ
			String openId = weixinOauth2Token.getOpenId();
			
			// ��ȡ�û���Ϣ
			SNSUserInfo snsUserInfo = OAuth2TokenUtil.getSNSUserInfo(accessToken, openId);
			System.out.println(snsUserInfo.getOpenId() + "," + snsUserInfo.getNickname() + "," + snsUserInfo.getHeadImgUrl());
			//�û�id�洢��session
			request.getSession().setAttribute("userId", snsUserInfo.getOpenId() );
		}*/
		//openid��ѯ�û���Ϣ
		MxUsersData userInfo = userService.getUserByOpenId("oNYP41OJ_d3fR-d4O479D8jFX3-A");
		logger.info(userInfo.toString());
		request.getSession().setAttribute("userId", ""+userInfo.getUserId());
		//json�ַ���  ��λ����
		List<TextValue> list = new ArrayList<TextValue>();
		List<MxSchools> schools = weixinNewsService.getSchools();
		for(MxSchools sch:schools){
				TextValue mod = new TextValue();
				mod.setValue(sch.getSchoolId().toString());
				mod.setText(sch.getSchoolName());
				list.add(mod);
		}
		JSONArray jsonArray = JSONArray.fromObject(list);
		request.setAttribute("schools", ""+jsonArray);
		List<TextValue> list1 = new ArrayList<TextValue>();
		List<MxRegion> regions = weixinNewsService.getRegion();
		for(MxRegion reg:regions){
			TextValue mod = new TextValue();
			mod.setValue(reg.getRegionId().toString());
			mod.setText(reg.getRegionName());
			list1.add(mod);
		}
		JSONArray jsonArray1 = JSONArray.fromObject(list1);
		request.setAttribute("regions", ""+jsonArray1);
		return "loadEditNews";
	}
	
	//����ѧУ������������
	public String loadKeyValue(){
		System.out.println("loadKeyValue");
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			request.setCharacterEncoding("UTF-8");
			//��ȡnewsTypeId
			String newsTypeId = request.getParameter("newsTypeId");
			//��װ�ɶ���
			List<MxKeyValue> list = new ArrayList<MxKeyValue>();
			if(newsTypeId.equals("4")){
				List<MxSchools> schools = weixinNewsService.getSchools();
				for(MxSchools sch:schools){
					System.out.println(sch.toString());
					MxKeyValue mod = new MxKeyValue();
					mod.setKey(sch.getSchoolId());
					mod.setValue(sch.getSchoolName());
					list.add(mod);
				}
			}
			if(newsTypeId.equals("5")){
				List<MxRegion> regions = weixinNewsService.getRegion();
				for(MxRegion reg:regions){
					System.out.println(reg.toString());
					MxKeyValue mod = new MxKeyValue();
					mod.setKey(reg.getRegionId());
					mod.setValue(reg.getRegionName());
					list.add(mod);
				}
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("data", list);
			try {
				ResultUtil.toJson(ServletActionContext.getResponse(), map);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//�������
	@Action(value = "addNews", results = @Result(name="result" ,type = "json", params = {  
            "noCache", "true",         // �Ƿ����û���  
            "enableGZIP", "false",      // �Ƿ����ӦJSON����GZIP  
            "contentType", "text/html;charset=utf-8",// ������Ӧ����������  
            "root", "dataMap",         // ���ø�����  
            "ignoreHierarchy", "false"  // ���Ի���  
    })) 
	public String addNews() throws UnsupportedEncodingException{
		System.out.println("addNews");
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("UTF-8");
		String userId = (String) request.getSession().getAttribute("userId");
		System.out.println(userId);
		
		//��ȡ���� 
		String newsHeadline = request.getParameter("headLine");
		String newsLeadText = request.getParameter("leadText");
		String writer_name = request.getParameter("writerName");
		String newsMainContent = request.getParameter("content");
		String newsTypeId = request.getParameter("newsTypeId");
		String subId = request.getParameter("subId");

		System.out.println(newsHeadline+","+newsLeadText+","+writer_name+","+newsMainContent
				+","+newsTypeId+","+subId);
		
		
		//������� 
		MxNewsData newsData = new MxNewsData();
		newsData.setNewsHeadline(newsHeadline);
		newsData.setNewsLeadText(newsLeadText);
		newsData.setWriterName(writer_name);
		newsData.setNewsTypeId(new Integer(newsTypeId));
		if(newsTypeId.equals("4")){
			newsData.setSchoolId(new Integer(subId));
		}else if(newsTypeId.equals("5")){
			newsData.setRegionId(new Integer(subId));
		}
		//����openid���û�״̬Ϊ��Ч��ȡuserid
		newsData.setNewsWriterId(Integer.parseInt(userId));
		System.out.println(newsData.toString());
		
		
		//����������ݴӱ�
		MxNewsContent newsContent = new MxNewsContent();
		newsContent.setNewsHeadline(newsHeadline);
		newsContent.setNewsLeadText(newsLeadText);
		newsContent.setNewsMainContent(newsMainContent);
		weixinNewsService.addNews(newsData,newsContent);
	
		
		MsgResult msg = new MsgResult();
	    msg.setMsg("����ɹ���");
	    dataMap = new HashMap<String, Object>();      
        dataMap.put("data", msg);
		return "result";
/*		//json����
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", "success");
		ResultUtil.toJson(ServletActionContext.getResponse(), map);*/
		
	}
	
	
	//����ҳ��
	@Action(value = "newsPage", results = { @Result(name = "newsPage", location = "/WeixinPages/News/newsPage.jsp") })
	public String newsPage() throws UnsupportedEncodingException{
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("UTF-8");
		String newsId = request.getParameter("newsId");
		request.setAttribute("MxNewsData", weixinNewsService.getNewsById(newsId));
		return "newsPage";
	}
	
	//Ͷ���б�ҳ��
	@Action(value = "applyNewsList", results = { @Result(name = "applyNewsList", location = "/WeixinPages/News/applyNewsList.jsp") })
	public String applyNewsList() throws UnsupportedEncodingException{
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("UTF-8");
		String userId = (String) request.getSession().getAttribute("userId");
		request.setAttribute("MxNewsDataList", weixinNewsService.getNewsByUserId(userId));
		request.setAttribute("countAll", weixinNewsService.getCountNewsByUserId(userId,"-1"));
		request.setAttribute("countDS", weixinNewsService.getCountNewsByUserId(userId,"0"));
		request.setAttribute("countWTG", weixinNewsService.getCountNewsByUserId(userId,"1"));
		request.setAttribute("countTG", weixinNewsService.getCountNewsByUserId(userId,"2"));
		return "applyNewsList";
	}
	//demo:�ļ��ϴ�ҳ��
	@Action(value = "fileUpload_demo", results = { @Result(name = "fileUpload_demo", location = "/WeixinPages/News/fileUpload_demo.jsp") })
	public String fileUpload_demo(){
		return "fileUpload_demo";
	}
	//�ϴ��ļ�
	@Action(value = "fileUp")
	public void fileUp(){
		logger.info("��½�ɹ���");
		logger.info("��ʱ�ļ�·����"+upload.getAbsolutePath());
		logger.info("��ʱ�ļ���:"+upload.getName());
		logger.info("�ļ���С:"+upload.length());
		logger.info("�ϴ��ļ���:"+uploadFileName);
		logger.info("�ϴ��ļ����ͣ�"+uploadContentType);
		System.out.println();
    	logger.info(ServletActionContext.getServletContext().getRealPath("").substring(0, 3)+"upload");
    	System.out.println();
        try {  
            //������Ҫ���ľ����ṩһ���������Ĵ�ŵ�ַ����  
            String realPath = ServletActionContext.getServletContext().getRealPath("").substring(0, 3)+"upload";  
            //�ж�·���Ƿ����,�����ڴ���  
            File dir = new File(realPath);  
            if (!dir.exists()) {  
                dir.mkdirs();  
            }  
        	logger.info(dir.getAbsolutePath());
        	System.out.println();
            //��һ���������ļ� ,�ڶ����������ļ��ڷ������е�λ��,fileUtils ��org.apache.commons.io.FileUtils�ṩ�õ��ֳɵķ���  
            FileUtils.copyFile(upload,new File(dir,uploadFileName));//copy�ļ�,���������б����ļ�  
            //FileUtils.moveFile(upload,new File(dir,uploadFileName));//�����ļ�,�Ƽ�ʹ��,�ޱ����ļ�  
        } catch (Exception e) {  
            e.printStackTrace();  
            logger.info(e.getMessage());
        }  

	}
	//demo���ƶ�ǰ��ͼƬѹ���ϴ�
	@Action(value = "condense_demo", results = { @Result(name = "condense_demo", location = "/WeixinPages/News/condense_demo.jsp") })
	public String condense_demo(){
		return "condense_demo";
	}
	//demo:����ѹ����ͼƬ
	@Action(value = "uploadImage")
	public void uploadImage(){
		logger.info("uploadImage");
		HttpServletRequest request = ServletActionContext.getRequest();
		String base64 = request.getParameter("img");
		System.out.println();
		logger.info(base64);
        // ͨ��base64��ת��ͼƬ
		base64 = base64.replaceAll("data:image/jpeg;base64,", "");      
        BASE64Decoder decoder = new BASE64Decoder();
        // Base64����      
        byte[] imageByte = null;
        try {
            imageByte = decoder.decodeBuffer(base64);      
            for (int i = 0; i < imageByte.length; ++i) {      
                if (imageByte[i] < 0) {// �����쳣����      
                    imageByte[i] += 256;      
                }      
            }      
        } catch (Exception e) {
             e.printStackTrace(); 
        }  
        // �����ļ���
        String files = new SimpleDateFormat("yyyyMMddHHmmssSSS")
                .format(new Date())
                + (new Random().nextInt(9000) % (9000 - 1000 + 1) + 1000)
                + ".png";
        // �����ļ�·��
        String filename = "D:/condense_file/" + files;     
        try {
            // �����ļ�         
            File imageFile = new File(filename);
            imageFile.createNewFile();
               if(!imageFile.exists()){
                   imageFile.createNewFile();
                }
                OutputStream imageStream = new FileOutputStream(imageFile);
                imageStream.write(imageByte);
                imageStream.flush();
                imageStream.close();                    
        } catch (Exception e) {         
            e.printStackTrace();
        }
	}
	//demo��canvas��ͼ
	@Action(value = "canvas_demo", results = { @Result(name = "canvas_demo", location = "/WeixinPages/News/canvas_demo.jsp") })
	public String canvas_demo(){
		return "canvas_demo";
	}
	//demo:����json
	@Action( value = "getUserDemo", results = @Result(name="result" ,type = "json", params = {  
	                 "noCache", "true",         // �Ƿ����û���  
	                 "enableGZIP", "true",      // �Ƿ����ӦJSON����GZIP  
	                 "contentType", "text/html;charset=utf-8",// ������Ӧ����������  
	                 "root", "dataMap",         // ���ø�����  
	                 "ignoreHierarchy", "true"  // ���Ի���  
	         }))  
	 public String getUserDemo() {  
		 /*MxUsersData userInfo = userService.getUserByOpenId("oNYP41OJ_d3fR-d4O479D8jFX3-A");
		 MsgResult msg = new MsgResult();
		 msg.setMsg("success");
		 msg.setData(userInfo.getPassword());
		 dataMap = new HashMap<String, Object>();      
	     dataMap.put("data", msg);  */
		 List<MxSchools> schools = weixinNewsService.getSchools();
		 dataMap = new HashMap<String, Object>();      
	     dataMap.put("MxSchools", schools);
	     return "result";  
	 }  
	
	//demo:���ط�����appid��ǩ����noncestr��timestamp
	@Action( value = "getJDSDKTicket", results = @Result(name="result" ,type = "json", params = {  
            "noCache", "true",         // �Ƿ����û���  
            "enableGZIP", "true",      // �Ƿ����ӦJSON����GZIP  
            "contentType", "text/html;charset=utf-8",// ������Ӧ����������  
            "root", "dataMap",         // ���ø�����  
            "ignoreHierarchy", "true"  // ���Ի���  
    })) 
	public String getJDSDKTicket(){
		JDSDKTicket ticket = new JDSDKTicket();
		String url = "http://d1a7069951.iask.in/MX_System/weixin/getJDSDKTicket.action";
		ticket.setSignature(JsapiTicketUtil.signatureCreateUtil(url));
		logger.info(ticket.toString());
		dataMap = new HashMap<String, Object>();      
	    dataMap.put("ticket", ticket);
		return "result";
	}
	//demo:jdsdk�ӿ�
	@Action(value = "JDSDKIndex", results = { @Result(name = "JDSDKIndex", location = "/WeixinPages/News/JDSDKIndex_demo.jsp") })
	public String JDSDKIndex() throws UnsupportedEncodingException{
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("UTF-8");
		JDSDKTicket ticket = new JDSDKTicket();
		String url = "http://d1a7069951.iask.in/MX_System/weixin/JDSDKIndex.action";
		ticket.setSignature(JsapiTicketUtil.signatureCreateUtil(url));
		logger.info(ticket.toString());
		request.setAttribute("JDSDKTicket", ticket);
		return "JDSDKIndex";
	}
}
