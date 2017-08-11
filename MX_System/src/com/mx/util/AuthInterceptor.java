package com.mx.util;

import javax.servlet.http.HttpSession;  
import org.apache.struts2.ServletActionContext;  

import com.mx.ssh.bean.MxUsersData;
import com.opensymphony.xwork2.ActionInvocation;  
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;  
  
public class AuthInterceptor extends AbstractInterceptor{  
  
    @Override  
    public String intercept(ActionInvocation invocation) throws Exception {  
        HttpSession session = ServletActionContext.getRequest().getSession();  
        MxUsersData userInfo = (MxUsersData)session.getAttribute("userInfo");  //查询session是否有用户登录信息
        if(userInfo == null){//错误,跳转到未登录界面
            return "noLogin";  
        }else{  
            return invocation.invoke();  
        }  
    }  
  
}  