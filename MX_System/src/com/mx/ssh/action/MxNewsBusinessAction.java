package com.mx.ssh.action;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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

import com.mx.ssh.bean.MxNewsComment;
import com.mx.ssh.bean.MxNewsCommentBack;
import com.mx.ssh.bean.MxNewsCommentPraise;
import com.mx.ssh.bean.MxNewsPraise;
import com.mx.ssh.service.IUserService;
import com.mx.ssh.service.IWeixinNewsService;
import com.mx.ssh.service.IWeixinService;
import com.mx.ssh.util.MsgResult;

@Controller//���Ʋ��Springע��
@Scope("prototype")//֧�ֶ���
@Namespace(value="/mxNewsBusinessAction") //��ʾ��ǰAction���������ռ� 
@ParentPackage(value = "json-default") 
public class MxNewsBusinessAction {
	
	private static final long serialVersionUID = -1627548805862485475L;
	private final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private IWeixinService weixinService;
	
	@Autowired
	private IWeixinNewsService weixinNewsService;//΢��������ҵ��
	
	@Autowired
	private IUserService userService;
	
    private Map<String, Object> dataMap;  
    
    public Map<String, Object> getDataMap() {  
        return dataMap;  
    }  
     
    public void setDataMap(Map<String, Object> dataMap) {  
        this.dataMap = dataMap;  
    } 
	
	//demo:�ƶ�������ˢ�¡��������ظ����� dropload
	@Action(value = "dropload", results = { @Result(name = "dropload", location = "/WeixinPages/News/dropload.jsp") })
	public String dropload() throws UnsupportedEncodingException{
		return "dropload";
	}
	//��������id��ȡ��������
	@Action( value = "getNewsCommentByNewsId", results = @Result(name="result" ,type = "json", params = {  
            "noCache", "true",         // �Ƿ����û���  
            "enableGZIP", "false",      // �Ƿ����ӦJSON����GZIP  
            "contentType", "text/html;charset=utf-8",// ������Ӧ����������  
            "root", "dataMap",         // ���ø�����  
            "ignoreHierarchy", "true"  // ���Ի���  
    })) 
	public String getNewsCommentByNewsId() throws UnsupportedEncodingException{
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("UTF-8");
		String newsId = request.getParameter("newsId");
	    dataMap = new HashMap<String, Object>();      
        dataMap.put("lists", weixinNewsService.getCommentByNewsId(newsId));
		return "result";
	}
	//�����۵���
	@Action( value = "addCommentPraise", results = @Result(name="result" ,type = "json", params = {  
            "noCache", "true",         // �Ƿ����û���  
            "enableGZIP", "false",      // �Ƿ����ӦJSON����GZIP  
            "contentType", "text/html;charset=utf-8",// ������Ӧ����������  
            "root", "dataMap",         // ���ø�����  
            "ignoreHierarchy", "true"  // ���Ի���  
    })) 
	public String addCommentPraise() throws UnsupportedEncodingException{
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("UTF-8");
		String commentId = request.getParameter("commentId");
		String userId = (String) request.getSession().getAttribute("userId");
		MxNewsCommentPraise commentPraise = new MxNewsCommentPraise();
		commentPraise.setCommentId(Integer.parseInt(commentId));
		commentPraise.setOperator(Integer.parseInt(userId));
		commentPraise.setCreateDate(new Date());
		MsgResult msg = new MsgResult();
		try{
			int countPraiseNum = weixinNewsService.addCommentPraise(commentPraise);
			msg.setMsg("���޳ɹ���");
			msg.setData(countPraiseNum);
		}catch(Exception e){
			msg.setMsg(e.getStackTrace().toString());
			msg.setSuccess(false);
		}
	    dataMap = new HashMap<String, Object>();      
        dataMap.put("data", msg);
		return "result";
	}
	//������ȡ������
		@Action( value = "delCommentPraise", results = @Result(name="result" ,type = "json", params = {  
	            "noCache", "true",         // �Ƿ����û���  
	            "enableGZIP", "false",      // �Ƿ����ӦJSON����GZIP  
	            "contentType", "text/html;charset=utf-8",// ������Ӧ����������  
	            "root", "dataMap",         // ���ø�����  
	            "ignoreHierarchy", "true"  // ���Ի���  
	    })) 
		public String delCommentPraise() throws UnsupportedEncodingException{
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("UTF-8");
			String commentId = request.getParameter("commentId");
			String userId = (String) request.getSession().getAttribute("userId");
			MxNewsCommentPraise commentPraise = new MxNewsCommentPraise();
			commentPraise.setCommentId(Integer.parseInt(commentId));
			commentPraise.setOperator(Integer.parseInt(userId));
			MsgResult msg = new MsgResult();
			try{
				int countPraiseNum = weixinNewsService.delCommentPraise(commentPraise);
				msg.setMsg("ȡ�����޳ɹ���");
				msg.setData(countPraiseNum);
			}catch(Exception e){
				msg.setMsg(e.getMessage());
				msg.setSuccess(false);
			}
		    dataMap = new HashMap<String, Object>();      
	        dataMap.put("data", msg);
			return "result";
		}
		//�����µ��޲���
		@Action( value = "praise", results = @Result(name="result" ,type = "json", params = {  
	            "noCache", "true",         // �Ƿ����û���  
	            "enableGZIP", "false",      // �Ƿ����ӦJSON����GZIP  
	            "contentType", "text/html;charset=utf-8",// ������Ӧ����������  
	            "root", "dataMap",         // ���ø�����  
	            "ignoreHierarchy", "true"  // ���Ի���  
	    })) 
		public String praise() throws UnsupportedEncodingException{
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("UTF-8");
			String type = request.getParameter("type");
			String newsId = request.getParameter("newsId");
			String userId = (String) request.getSession().getAttribute("userId");
			MxNewsPraise praise = new MxNewsPraise();
			praise.setNewsId(Integer.parseInt(newsId));
			praise.setOperator(Integer.parseInt(userId));
			praise.setCreateDate(new Date());
			MsgResult msg = new MsgResult();
			try{
				int countPraiseNum = 0;
				if(type.equals("add")){
					countPraiseNum = weixinNewsService.addPraise(praise);
					msg.setMsg("���޳ɹ���");
				}else if(type.equals("del")){
					countPraiseNum = weixinNewsService.delPraise(praise);
					msg.setMsg("ȡ�����޳ɹ���");
				}
				msg.setData(countPraiseNum);
			}catch(Exception e){
				msg.setMsg(e.getStackTrace().toString());
				msg.setSuccess(false);
			}
		    dataMap = new HashMap<String, Object>();      
	        dataMap.put("data", msg);
			return "result";
		}
		
		//���ۻظ�ҳ��
		@Action(value = "commentBackPage", results = { @Result(name = "commentBackPage", location = "/WeixinPages/News/commentBackPage.jsp") })
		public String commentBackPage() throws UnsupportedEncodingException{
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("UTF-8");
			String commentId = request.getParameter("commentId");
			request.setAttribute("MxNewsComment", weixinNewsService.getCommentById(commentId));
			return "commentBackPage";
		}
		
		//������ۻظ�
		@Action( value = "addNewsCommentBack", results = @Result(name="result" ,type = "json", params = {  
	            "noCache", "true",         // �Ƿ����û���  
	            "enableGZIP", "false",      // �Ƿ����ӦJSON����GZIP  
	            "contentType", "text/html;charset=utf-8",// ������Ӧ����������  
	            "root", "dataMap",         // ���ø�����  
	            "ignoreHierarchy", "true"  // ���Ի���  
	    })) 
		public String addNewsCommentBack() throws UnsupportedEncodingException{
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("UTF-8");
			String userId = (String) request.getSession().getAttribute("userId");
			String commentId = request.getParameter("commentId");
			String backTxt = request.getParameter("backTxt");
			MxNewsCommentBack back = new MxNewsCommentBack();
			back.setOperator(Integer.parseInt(userId));
			back.setCommentBack(backTxt);
			back.setCommentId(Integer.parseInt(commentId));
			back.setCreateDate(new Date());
			weixinNewsService.addCommentBack(back);
			
			MsgResult msg = new MsgResult();
		    msg.setMsg("���ۻظ��ɹ���");
		    dataMap = new HashMap<String, Object>();      
	        dataMap.put("data", msg);
			return "result";
		}
		//��������id��ȡ���ۻظ�
		@Action( value = "getCommentBackByCommentId", results = @Result(name="result" ,type = "json", params = {  
	            "noCache", "true",         // �Ƿ����û���  
	            "enableGZIP", "false",      // �Ƿ����ӦJSON����GZIP  
	            "contentType", "text/html;charset=utf-8",// ������Ӧ����������  
	            "root", "dataMap",         // ���ø�����  
	            "ignoreHierarchy", "true"  // ���Ի���  
	    })) 
		public String getCommentBackByCommentId() throws UnsupportedEncodingException{
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("UTF-8");
			String commentId = request.getParameter("commentId");
		    dataMap = new HashMap<String, Object>();      
	        dataMap.put("lists", weixinNewsService.getCommentBackByCommentId(commentId));
			return "result";
		}
		//�û�������ҳ��
		@Action(value = "userCommentsPage", results = { @Result(name = "userCommentsPage", location = "/WeixinPages/News/userCommentsPage.jsp") })
		public String userCommentsPage() throws UnsupportedEncodingException{
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("UTF-8");
			String userId = request.getParameter("userId");
			request.setAttribute("MxUsersData", userService.getUserByID(Integer.parseInt(userId)));
			return "userCommentsPage";
		}
		//�����û�id��ȡ����
		@Action( value = "getNewsCommentByUserId", results = @Result(name="result" ,type = "json", params = {  
	            "noCache", "true",         // �Ƿ����û���  
	            "enableGZIP", "false",      // �Ƿ����ӦJSON����GZIP  
	            "contentType", "text/html;charset=utf-8",// ������Ӧ����������  
	            "root", "dataMap",         // ���ø�����  
	            "ignoreHierarchy", "true"  // ���Ի���  
	    })) 
		public String getNewsCommentByUserId() throws UnsupportedEncodingException{
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("UTF-8");
			String userId = request.getParameter("userId");
		    dataMap = new HashMap<String, Object>();      
	        dataMap.put("lists", weixinNewsService.getCommentByUserId(userId));
			return "result";
		}
		
}
