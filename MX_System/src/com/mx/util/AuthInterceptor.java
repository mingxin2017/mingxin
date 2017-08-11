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
        MxUsersData userInfo = (MxUsersData)session.getAttribute("userInfo");  //��ѯsession�Ƿ����û���¼��Ϣ
        if(userInfo == null){//����,��ת��δ��¼����
            return "noLogin";  
        }else{  
            return invocation.invoke();  
        }  
    }  
  
}  