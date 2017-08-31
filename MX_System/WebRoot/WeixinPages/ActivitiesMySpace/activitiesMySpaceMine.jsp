<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>
<meta charset="utf-8">
<title>Hello MUI</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<link rel="stylesheet"
	href="<%=basePath%>WeixinPages/common/css/mui.min.css">
	<!-- 自定义图标字体 -->
<link rel="stylesheet"
	href="<%=basePath%>WeixinPages/common/css/icomoon.css">
</head>

<body>
	<div class="mui-card">
		<ul class="mui-table-view">
			<li class="mui-table-view-cell mui-collapse"><a
				class="mui-navigate-right" href="#">个人信息</a>
				<div class="mui-collapse-content">
					<form class="mui-input-group">
						<div class="mui-input-row">
							<label>姓名</label> <input type="text" class="mui-input-clear"
								placeholder="输入姓名" value="${sessionScope.userInfo.userRealName}">
						</div>
						<div class="mui-input-row">
							<label>手机号</label> <input type="text" class="mui-input-clear"
								placeholder="输入手机号" value="${sessionScope.userInfo.userPhoneNum}">
						</div>
						<div class="mui-input-row">
							<label>联系地址</label> <input type="text" class="mui-input-clear"
								placeholder="输入地址" value="${sessionScope.userInfo.userAddr}">
						</div>
						<div class="mui-input-row">
							<label>电子邮箱</label> <input type="text" class="mui-input-clear"
								placeholder="输入电子邮箱" value="${sessionScope.userInfo.userEmail}">
						</div>
						<div class="mui-input-row">
							<label>身份证号码</label> <input type="text" class="mui-input-clear"
								placeholder="输入身份证号码" value="${sessionScope.userInfo.userIdcardNum}">
						</div>
						<div class="mui-input-row">
							<label>年级班级</label> <input type="text" class="mui-input-clear"
								placeholder="例：古十中87届18班" value="${sessionScope.userInfo.others}">
						</div>
						<div class="mui-button-row">
							<button class="mui-btn mui-btn-primary" type="button"
								onclick="return false;">提交修改</button>
						</div>
					</form>
				</div></li>
			<li class="mui-table-view-cell mui-collapse "><a
				class="mui-navigate-right" href="#">我的照片(${fn:length(sessionScope.myspaceUserMine.activitiesMySpaceMaterialMineList)})</a>
				<div class="mui-collapse-content">
							<div id="slider" class="mui-slider">
								<div class="mui-slider-group ">
									<c:forEach items="${sessionScope.myspaceUserMine.activitiesMySpaceMaterialMineList}" var="item" varStatus="st">
									
									<!-- 第一张 -->
									<div class="mui-slider-item" >
											<img src="${item.loadUrl}" style="width:100%;">
									</div>
									
									</c:forEach>
								</div>
								
							</div>
						</div>
			</li>
			<li class="mui-table-view-cell mui-collapse"><a
				class="mui-navigate-right" href="#">我的讨论(${fn:length(sessionScope.myspaceUserMine.activitiesMySpaceCommentMineList)})</a>
				<div class="mui-collapse-content">
				<ul class="mui-table-view mui-table-view-striped mui-table-view-condensed">
		          
		          <c:forEach items="${sessionScope.myspaceUserMine.activitiesMySpaceCommentMineList}" var="item">
		          <li id="li${item.commentId}" class="mui-table-view-cell mui-media">
		          	<div class="mui-slider-right mui-disabled">
						<button  onclick="DeleteComment(${item.commentId});" class="mui-btn mui-btn-red ">删除</button>
					</div>
		            <div class="mui-slider-handle">
		                <div class="mui-table-cell mui-col-xs-10">
		                    <h8 class="mui-ellipsis-3">${item.commentTxt}</h8>
		                    <h5>发表时间：<fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd　HH:mm"/></h5>
		                </div>
		                
		            </div>
		        </li>
		        
		        </c:forEach>
		        
		        </ul>
				</div></li>
		</ul>
	</div>
	<script src="<%=basePath%>WeixinPages/common/js/mui.min.js"></script>
	<script src="<%=basePath%>WeixinPages/common/js/mui.zoom.js"></script>
	<script src="<%=basePath%>WeixinPages/common/js/mui.previewimage.js"></script>
	<script>
			mui.init();
			
			//mui.previewImage();
		</script>
</body>
</html>
