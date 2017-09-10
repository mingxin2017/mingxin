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

 
@Controller//控制层的Spring注解
@Scope("prototype")//支持多例
@Namespace(value="/weixin") //表示当前Action所在命名空间 
@ParentPackage(value = "json-default") 
public class WeixinAction { 

	private static final long serialVersionUID = -1627548805862485475L;
	private final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private IWeixinService weixinService;
	
	@Autowired
	private IWeixinNewsService weixinNewsService;//微信新闻类业务
	
	@Autowired
	private IUserService userService;
	
    private String uploadPath = "D:\\upload"; // 上传文件的目录  
    private File upload;//upload为表单的name值
    private String uploadFileName;//name值+FileName
    private String uploadContentType;//name值+ContextType
    
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
     * 填写服务器配置,验证服务器地址的有效性   设置接口配置信息URL和Token时使用
     * 结果echostr原样以xml形式返回
     * @throws IOException 
     */
	@Action(value = "checkMsgFromWeixinServer")  
	public void checkMsgFromWeixinServer() throws IOException{
		// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// 接收参数微信加密签名、 时间戳、随机数
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		// 随机字符串
		String echostr = request.getParameter("echostr");
		// 请求校验
		if (WeixinSignUtil.checkSignature(signature, timestamp, nonce)) {
			logger.info("设置接口配置信息URL和Token成功！");
		}else{
			echostr = "";
			logger.info("设置接口配置信息URL和Token失败，请查看后重试！");
		}
		PrintWriter out = response.getWriter();
		out.print(echostr);
		out.close();
		out = null;
	}
	
	/**
	 * 　　*微信action中的处理方法
	 */
	@Action(value = "defaultMethod")
	public String defaultMethod() throws Exception {
		
        System.out.println("进入weixinAction.defaultMethod");   
		// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		// 接收参数微信加密签名、 时间戳、随机数
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");

		// 随机字符串
		String echostr = request.getParameter("echostr");
		// System.out.println(signature+"...............................");
		PrintWriter out = response.getWriter();
		
		//System.out.println("signature;timestamp;nonce"+signature+','+timestamp+','+nonce);
		
		//返回参数
		String respParams= null;

		// 请求校验
		if (WeixinSignUtil.checkSignature(signature, timestamp, nonce)) {
			String method = ServletActionContext.getRequest().getMethod();
			if (method.equals("POST")) {
				// 调用核心服务类接收处理请求
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
       
		// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		// 用户同意授权后，能获取到code
		String code = request.getParameter("code");
		String state = request.getParameter("state");
		if (!"authdeny".equals(code)) {
			// 获取网页授权access_token
			WeixinOauth2Token weixinOauth2Token = OAuth2TokenUtil
					.getOauth2AccessToken(WeixinSignUtil.AppID,
							WeixinSignUtil.AppSecret, code);
			// 网页授权接口访问凭证
			String accessToken = weixinOauth2Token.getAccessToken();
			// 用户标识
			String openId = weixinOauth2Token.getOpenId();
			// 获取用户信息
			SNSUserInfo snsUserInfo = OAuth2TokenUtil.getSNSUserInfo(
					accessToken, openId);
			System.out.println(snsUserInfo.getOpenId() + ","
					+ snsUserInfo.getNickname() + ","
					+ snsUserInfo.getHeadImgUrl());
			// 设置要传递的参数
			request.setAttribute("snsUserInfo", snsUserInfo);
			request.setAttribute("state", state);
		}
		// 跳转到index.jsp
		//request.getRequestDispatcher("login.jsp").forward(request, response);

		return "weixinHome";
	}

	
	//鸣心时报乡村时报主页跳转方法
	@Action(value = "getCountryNews", results = { @Result(name = "getCountryNews", location = "/WeixinPages/News/countryNews.jsp") })
	public String getCountryNews() throws UnsupportedEncodingException{
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("UTF-8");
		//String newsTypeId = request.getParameter("newsTypeId");
		request.setAttribute("MxNewsDataList", weixinNewsService.getNewsByNewsTypeId("5"));
		return "getCountryNews";
	}
	
	
	//鸣心时报学校时报主页跳转方法
	@Action(value = "getSchoolNews", results = { @Result(name = "getSchoolNews", location = "/WeixinPages/News/schoolNews.jsp") })
	public String getSchoolNews() throws UnsupportedEncodingException{
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("UTF-8");
		//String newsTypeId = request.getParameter("newsTypeId");
		request.setAttribute("MxNewsDataList", weixinNewsService.getNewsByNewsTypeId("4"));
		return "getSchoolNews";
	}
	
	//新闻投稿页面
	@Action(value = "loadEditNews", results = { @Result(name = "loadEditNews", location = "/WeixinPages/News/editNews.jsp") })
	public String loadEditNews() throws UnsupportedEncodingException{
		System.out.println("page:loadEditNews");
		
		// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("UTF-8");

		// 用户同意授权后，能获取到code
/*		String code = request.getParameter("code");
		String state = request.getParameter("state");
		if (!"authdeny".equals(code)) {
			// 获取网页授权access_token
			WeixinOauth2Token weixinOauth2Token = OAuth2TokenUtil.getOauth2AccessToken(WeixinSignUtil.AppID,WeixinSignUtil.AppSecret, code);
			// 网页授权接口访问凭证
			String accessToken = weixinOauth2Token.getAccessToken();
			// 用户标识
			String openId = weixinOauth2Token.getOpenId();
			
			// 获取用户信息
			SNSUserInfo snsUserInfo = OAuth2TokenUtil.getSNSUserInfo(accessToken, openId);
			System.out.println(snsUserInfo.getOpenId() + "," + snsUserInfo.getNickname() + "," + snsUserInfo.getHeadImgUrl());
			//用户id存储到session
			request.getSession().setAttribute("userId", snsUserInfo.getOpenId() );
		}*/
		//openid查询用户信息
		MxUsersData userInfo = userService.getUserByOpenId("oNYP41OJ_d3fR-d4O479D8jFX3-A");
		logger.info(userInfo.toString());
		request.getSession().setAttribute("userId", ""+userInfo.getUserId());
		//json字符串  单位参数
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
	
	//级联学校和乡村参数返回
	public String loadKeyValue(){
		System.out.println("loadKeyValue");
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			request.setCharacterEncoding("UTF-8");
			//获取newsTypeId
			String newsTypeId = request.getParameter("newsTypeId");
			//封装成对象
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
	
	//添加新闻
	@Action(value = "addNews", results = @Result(name="result" ,type = "json", params = {  
            "noCache", "true",         // 是否启用缓存  
            "enableGZIP", "false",      // 是否对响应JSON启用GZIP  
            "contentType", "text/html;charset=utf-8",// 设置响应的内容类型  
            "root", "dataMap",         // 设置根对象  
            "ignoreHierarchy", "false"  // 忽略基类  
    })) 
	public String addNews() throws UnsupportedEncodingException{
		System.out.println("addNews");
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("UTF-8");
		String userId = (String) request.getSession().getAttribute("userId");
		System.out.println(userId);
		
		//获取参数 
		String newsHeadline = request.getParameter("headLine");
		String newsLeadText = request.getParameter("leadText");
		String writer_name = request.getParameter("writerName");
		String newsMainContent = request.getParameter("content");
		String newsTypeId = request.getParameter("newsTypeId");
		String subId = request.getParameter("subId");

		System.out.println(newsHeadline+","+newsLeadText+","+writer_name+","+newsMainContent
				+","+newsTypeId+","+subId);
		
		
		//添加新闻 
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
		//根据openid和用户状态为有效获取userid
		newsData.setNewsWriterId(Integer.parseInt(userId));
		System.out.println(newsData.toString());
		
		
		//添加新闻内容从表
		MxNewsContent newsContent = new MxNewsContent();
		newsContent.setNewsHeadline(newsHeadline);
		newsContent.setNewsLeadText(newsLeadText);
		newsContent.setNewsMainContent(newsMainContent);
		weixinNewsService.addNews(newsData,newsContent);
	
		
		MsgResult msg = new MsgResult();
	    msg.setMsg("保存成功！");
	    dataMap = new HashMap<String, Object>();      
        dataMap.put("data", msg);
		return "result";
/*		//json返回
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", "success");
		ResultUtil.toJson(ServletActionContext.getResponse(), map);*/
		
	}
	
	
	//新闻页面
	@Action(value = "newsPage", results = { @Result(name = "newsPage", location = "/WeixinPages/News/newsPage.jsp") })
	public String newsPage() throws UnsupportedEncodingException{
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("UTF-8");
		String newsId = request.getParameter("newsId");
		request.setAttribute("MxNewsData", weixinNewsService.getNewsById(newsId));
		return "newsPage";
	}
	
	//投稿列表页面
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
	//demo:文件上传页面
	@Action(value = "fileUpload_demo", results = { @Result(name = "fileUpload_demo", location = "/WeixinPages/News/fileUpload_demo.jsp") })
	public String fileUpload_demo(){
		return "fileUpload_demo";
	}
	//上传文件
	@Action(value = "fileUp")
	public void fileUp(){
		logger.info("登陆成功！");
		logger.info("临时文件路径："+upload.getAbsolutePath());
		logger.info("临时文件名:"+upload.getName());
		logger.info("文件大小:"+upload.length());
		logger.info("上传文件名:"+uploadFileName);
		logger.info("上传文件类型："+uploadContentType);
		System.out.println();
    	logger.info(ServletActionContext.getServletContext().getRealPath("").substring(0, 3)+"upload");
    	System.out.println();
        try {  
            //我们需要做的就是提供一个服务器的存放地址即可  
            String realPath = ServletActionContext.getServletContext().getRealPath("").substring(0, 3)+"upload";  
            //判断路径是否存在,不存在创建  
            File dir = new File(realPath);  
            if (!dir.exists()) {  
                dir.mkdirs();  
            }  
        	logger.info(dir.getAbsolutePath());
        	System.out.println();
            //第一个参数是文件 ,第二个参数是文件在服务器中的位置,fileUtils 是org.apache.commons.io.FileUtils提供好的现成的方法  
            FileUtils.copyFile(upload,new File(dir,uploadFileName));//copy文件,服务器中有备份文件  
            //FileUtils.moveFile(upload,new File(dir,uploadFileName));//剪切文件,推荐使用,无备份文件  
        } catch (Exception e) {  
            e.printStackTrace();  
            logger.info(e.getMessage());
        }  

	}
	//demo：移动前端图片压缩上传
	@Action(value = "condense_demo", results = { @Result(name = "condense_demo", location = "/WeixinPages/News/condense_demo.jsp") })
	public String condense_demo(){
		return "condense_demo";
	}
	//demo:接收压缩的图片
	@Action(value = "uploadImage")
	public void uploadImage(){
		logger.info("uploadImage");
		HttpServletRequest request = ServletActionContext.getRequest();
		String base64 = request.getParameter("img");
		System.out.println();
		logger.info(base64);
        // 通过base64来转化图片
		base64 = base64.replaceAll("data:image/jpeg;base64,", "");      
        BASE64Decoder decoder = new BASE64Decoder();
        // Base64解码      
        byte[] imageByte = null;
        try {
            imageByte = decoder.decodeBuffer(base64);      
            for (int i = 0; i < imageByte.length; ++i) {      
                if (imageByte[i] < 0) {// 调整异常数据      
                    imageByte[i] += 256;      
                }      
            }      
        } catch (Exception e) {
             e.printStackTrace(); 
        }  
        // 生成文件名
        String files = new SimpleDateFormat("yyyyMMddHHmmssSSS")
                .format(new Date())
                + (new Random().nextInt(9000) % (9000 - 1000 + 1) + 1000)
                + ".png";
        // 生成文件路径
        String filename = "D:/condense_file/" + files;     
        try {
            // 生成文件         
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
	//demo：canvas画图
	@Action(value = "canvas_demo", results = { @Result(name = "canvas_demo", location = "/WeixinPages/News/canvas_demo.jsp") })
	public String canvas_demo(){
		return "canvas_demo";
	}
	//demo:返回json
	@Action( value = "getUserDemo", results = @Result(name="result" ,type = "json", params = {  
	                 "noCache", "true",         // 是否启用缓存  
	                 "enableGZIP", "true",      // 是否对响应JSON启用GZIP  
	                 "contentType", "text/html;charset=utf-8",// 设置响应的内容类型  
	                 "root", "dataMap",         // 设置根对象  
	                 "ignoreHierarchy", "true"  // 忽略基类  
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
	
	//demo:返回服务器appid、签名、noncestr、timestamp
	@Action( value = "getJDSDKTicket", results = @Result(name="result" ,type = "json", params = {  
            "noCache", "true",         // 是否启用缓存  
            "enableGZIP", "true",      // 是否对响应JSON启用GZIP  
            "contentType", "text/html;charset=utf-8",// 设置响应的内容类型  
            "root", "dataMap",         // 设置根对象  
            "ignoreHierarchy", "true"  // 忽略基类  
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
	//demo:jdsdk接口
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
