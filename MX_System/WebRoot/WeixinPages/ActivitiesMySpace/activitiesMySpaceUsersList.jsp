<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page import="com.bean.MxUsersData;" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>活动用户列表</title>
    <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<!--标准mui.css-->
<link rel="stylesheet"
	href="<%=basePath%>WeixinPages/common/css/mui.min.css">

  </head>
  
  <body>
  <div class="mui-content">
   <ul class="mui-table-view">
   			<c:forEach items="${sessionScope.mySpaceUsersList}" var="item">
				<li class="mui-table-view-cell mui-media">
					<div class="mui-slider-handle">
						<img class="mui-media-object mui-pull-left" data-lazyload="${item.mxUsersData.weixinHeadUrl}">
						<div class="mui-media-body">
							${item.mxUsersData.userRealName}
							<c:if test="${item.mxUsersData.userTypeId== 1100}">
								<span class="mui-badge mui-badge-success">组织者</span>
							</c:if>
							<p class='mui-ellipsis'>电话${item.mxUsersData.userPhoneNum}</p>
						</div>
					</div>
					<c:if test="${sessionScope.userInfo.userTypeId== 1100}">
					<c:if test="${item.mxUsersData.userTypeId!= 1100}">
					<div class="mui-slider-right mui-disabled">
						<button id='deleteBtn'  type="button" onclick="DeleteMySpaceUser();" class="mui-btn mui-btn-red ">删除</button>
					</div>
					</c:if>
					</c:if>
				</li>
			</c:forEach>
			</ul>
    </div>
  </body>
  	<script src="<%=basePath%>WeixinPages/common/js/mui.min.js"></script>
	<script src="<%=basePath%>WeixinPages/common/js/mui.lazyload.js"></script>
	<script src="<%=basePath%>WeixinPages/common/js/mui.lazyload.img.js"></script>
<script>
	mui.init();
	(function($) {
		$(document).imageLazyload({
			placeholder: '/MX_System/WeixinPages/common/images/60x60.gif'
		});
	})(mui);
</script>
</html>
