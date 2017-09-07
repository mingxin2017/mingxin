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
    	System.out.println("进入登录拦截器");
    	HttpServletRequest request = ServletActionContext.getRequest();
    	MxUsersData userInfo = (MxUsersData)request.getSession().getAttribute("userInfo");//从session中获取用户信息
    	//System.out.println(userInfo);
    	if(userInfo==null){//若session用户信息不存在，则验证是否有微信的用户验证机制传过来的用户信息
    		//SNSUserInfo snsUserInfo=WeixinUtil.validateWeixinWebUser(request);//获取网页验证个人信息

    		//System.out.println(snsUserInfo.getOpenId());
    		//if(snsUserInfo!=null){
    		//	userInfo=userService.getUserByOpenId(snsUserInfo.getOpenId());//根据openId获取用户系统信息
    		//	request.getSession().setAttribute("userInfo", userInfo);//是从微信网页验证过来的请求，设置session用户信息
    		//	return invocation.invoke();
    		//}else{
    		//	return "noLogin";
    		//}
    		
    		userInfo=WeixinUtil.validate_getUser(request);
    		if(userInfo==null){//如果还是没有用户信息，说明用户没有登录
    			return "noLogin";
    		}else{//不为空，且已经设置了session，则向下执行
    			return invocation.invoke();
    		}
    		
    	}else{
            return invocation.invoke();
        }
    }  
  
}  