package com.mx.ssh.util;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mx.ssh.bean.MxUsersData;
import com.mx.ssh.service.IUserService;
import com.mx.weixin.util.WeixinUtil;
import com.opensymphony.xwork2.ActionInvocation;  
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;  
  

@Component
public class AuthInterceptor extends AbstractInterceptor{  

	private static final long serialVersionUID = -6378441318183725486L;
	
	@Autowired
	private IUserService userService;
	
    @Override  
    public String intercept(ActionInvocation invocation) throws Exception {
    	System.out.println("�����¼������");
    	HttpServletRequest request = ServletActionContext.getRequest();
    	MxUsersData userInfo = (MxUsersData)request.getSession().getAttribute("userInfo");//��session�л�ȡ�û���Ϣ
    	//System.out.println(userInfo);
    	if(userInfo==null){//��session�û���Ϣ�����ڣ�����֤�Ƿ���΢�ŵ��û���֤���ƴ��������û���Ϣ
    		//SNSUserInfo snsUserInfo=WeixinUtil.validateWeixinWebUser(request);//��ȡ��ҳ��֤������Ϣ

    		//System.out.println(snsUserInfo.getOpenId());
    		//if(snsUserInfo!=null){
    		//	userInfo=userService.getUserByOpenId(snsUserInfo.getOpenId());//����openId��ȡ�û�ϵͳ��Ϣ
    		//	request.getSession().setAttribute("userInfo", userInfo);//�Ǵ�΢����ҳ��֤��������������session�û���Ϣ
    		//	return invocation.invoke();
    		//}else{
    		//	return "noLogin";
    		//}
    		
    		userInfo=WeixinUtil.validate_getUser(request);
    		if(userInfo==null){//�������û���û���Ϣ��˵���û�û�е�¼
    			return "noLogin";
    		}else{//��Ϊ�գ����Ѿ�������session��������ִ��
    			return invocation.invoke();
    		}
    		
    	}else{
            return invocation.invoke();
        }
    }  
  
}  