package com.mx.publicMethos;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * 过滤器，判断用户是否登录
 * @author zw
 *
 */
public class SessionFilter implements Filter{
	
	public FilterConfig config; 
	
	//<!--在释放时回调的方法-->
	public void destroy() {
		this.config = null;  
		
	}
	//<!--处理过滤器的方法-->
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
        HttpServletRequest hrequest = (HttpServletRequest)request;  
        HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper((HttpServletResponse)response);  
          
        String logonStrings = config.getInitParameter("logonStrings");        //<!--登录登陆页面-->  
        String includeStrings = config.getInitParameter("includeStrings");    //<!--过滤资源后缀参数-->  
        String redirectPath = hrequest.getContextPath() + config.getInitParameter("redirectPath");//<!--没有登陆转向页面-->  
        String disabletestfilter = config.getInitParameter("disabletestfilter");//<!-- 过滤器是否有效-->  
          
        if (disabletestfilter.toUpperCase().equals("Y")) {    //<!--过滤无效-->  
            chain.doFilter(request, response);  
            return;  
        }  
        String[] logonList = logonStrings.split(";");  
        String[] includeList = includeStrings.split(";");  
          
        if (!this.isContains(hrequest.getRequestURI(), includeList)) {//<!--只对指定过滤参数后缀进行过滤-->  
            chain.doFilter(request, response);  
            return;  
        }  
          
        if (this.isContains(hrequest.getRequestURI(), logonList)) {//<!--对登录页面不进行过滤-->  
            chain.doFilter(request, response);  
            return;  
        }  
          
        String user = ( String ) hrequest.getSession().getAttribute("userId");//<!--判断用户是否登录-->  
        if (user == null) {  
            wrapper.sendRedirect(redirectPath);  
            return;  
        }else {  
            chain.doFilter(request, response);  
            return;  
        } 
		
	}
	//<!--Filter实例化后进行初始化的回调方法-->
	public void init(FilterConfig arg0) throws ServletException {
		config = arg0; 
		
	}
	//自定义是否包含字符串方法
    public  boolean isContains(String container, String[] regx) {  
        boolean result = false;  
  
        for (int i = 0; i < regx.length; i++) {  
            if (container.indexOf(regx[i]) != -1) {  
                return true;  
            }  
        }  
        return result;  
    } 
	
}
