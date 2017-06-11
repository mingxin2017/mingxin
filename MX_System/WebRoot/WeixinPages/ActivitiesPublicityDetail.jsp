<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>
<base href="<%=basePath%>">
<title>鸣心-不一样的风景</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="green">
<script src="<%=basePath%>WeixinPages/common/js/mui.min.js"></script>
<link href="<%=basePath%>WeixinPages/common/css/mui.min.css"
	rel="stylesheet" />
<script>
		mui.init({
			swipeBack:false //启用右滑关闭功能
		});
	</script>
</head>

<body>
	<div class="mui-content">

		<ul class="mui-table-view">
			<c:if
				test="${sessionScope.ActivitiesPublicityDetail==null}">
				<li>暂无数据</li>
			</c:if>


			<div class="mui-media-body">
				${sessionScope.ActivitiesPublicityDetail.publicityArticle}</div>
	</div>
</body>
</html>
