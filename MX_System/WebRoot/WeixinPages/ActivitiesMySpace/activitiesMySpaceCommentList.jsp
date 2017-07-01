<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<title>讨论区</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<!--标准mui.css-->
<link rel="stylesheet"
	href="<%=basePath%>WeixinPages/common/css/mui.min.css">
	<!-- 自定义图标字体 -->
<link rel="stylesheet"
	href="<%=basePath%>WeixinPages/common/css/icomoon.css">
</head>

<body>

	<div class="mui-card">
		<div class="mui-card-header mui-card-media" >
			<img src="http://wx.qlogo.cn/mmopen/gITwFOywPbkCx8BxwYc41oAGjuBeFianAbtHl8URmaCMTe9lib6EicNuHSibGJzSfT6Y88Nos1poHITnB7vUs7foHphNpibcgFEja/0" />
			<div class="mui-media-body">
				小M
				<p>发表于 2016-06-30 15:30<span class="mui-badge mui-badge-danger">新</span></p>
			</div>
		</div>
		<div class="mui-card-content">
			<div class="mui-card-content-inner">
				包含页眉页脚的卡片，页眉常用来显示面板标题，页脚用来显示额外信息或支持的操作（比如点赞、评论等）</div>
		</div>
		<div class="mui-card-footer">
			<a class="mui-card-link "></a> <a class="mui-card-link"> <span
				class="mui-icon icomoon icon-thumbs-up"></span>3</a>
		</div>
	</div>
	<div class="mui-card">
		<div class="mui-card-header mui-card-media">
			<img src="http://wx.qlogo.cn/mmopen/gITwFOywPbkCx8BxwYc41oAGjuBeFianAbtHl8URmaCMTe9lib6EicNuHSibGJzSfT6Y88Nos1poHITnB7vUs7foHphNpibcgFEja/0" />
			<div class="mui-media-body">
				小M
				<p>发表于 2016-06-30 15:30<span class="mui-badge mui-badge-danger">新</span></p>
			</div>
		</div>
		<div class="mui-card-content">
			<div class="mui-card-content-inner">
				包含页眉页脚的卡片，页眉常用来显示面板标题，页脚用来显示额外信息或支持的操作（比如点赞、评论等）</div>
		</div>
		<div class="mui-card-footer">
			<a class="mui-card-link "></a> <a class="mui-card-link"> <span
				class="mui-icon icomoon icon-thumbs-up"></span>3</a>
		</div>
	</div>
	<div class="mui-card">
		<div class="mui-card-header mui-card-media">
			<img src="http://wx.qlogo.cn/mmopen/gITwFOywPbkCx8BxwYc41oAGjuBeFianAbtHl8URmaCMTe9lib6EicNuHSibGJzSfT6Y88Nos1poHITnB7vUs7foHphNpibcgFEja/0" />
			<div class="mui-media-body">
				小M
				<p>发表于 2016-06-30 15:30</p>
			</div>
		</div>
		<div class="mui-card-content">
			<div class="mui-card-content-inner">
				包含页眉页脚的卡片，页眉常用来显示面板标题，页脚用来显示额外信息或支持的操作（比如点赞、评论等）</div>
		</div>
		<div class="mui-card-footer">
			<a class="mui-card-link "></a> <a class="mui-card-link"> <span
				 scolling="yes" class="mui-icon icomoon icon-thumbs-up"></span>3</a>
		</div>
	</div>
	<div class="mui-card">
		<div class="mui-card-header mui-card-media">
			<img src="http://wx.qlogo.cn/mmopen/gITwFOywPbkCx8BxwYc41oAGjuBeFianAbtHl8URmaCMTe9lib6EicNuHSibGJzSfT6Y88Nos1poHITnB7vUs7foHphNpibcgFEja/0" />
			<div class="mui-media-body">
				小M
				<p>发表于 2016-06-30 15:30</p>
			</div>
		</div>
		<div class="mui-card-content">
			<div class="mui-card-content-inner">
				包含页眉页脚的卡片，页眉常用来显示面板标题，页脚用来显示额外信息或支持的操作（比如点赞、评论等）</div>
		</div>
		<div class="mui-card-footer">
			<a class="mui-card-link "></a> <a class="mui-card-link"> <span
				 scolling="yes" class="mui-icon icomoon icon-thumbs-up"></span>64</a>
		</div>
	</div>
	<div class="mui-card">
		<div class="mui-card-header mui-card-media">
			<img src="http://wx.qlogo.cn/mmopen/gITwFOywPbkCx8BxwYc41oAGjuBeFianAbtHl8URmaCMTe9lib6EicNuHSibGJzSfT6Y88Nos1poHITnB7vUs7foHphNpibcgFEja/0" />
			<div class="mui-media-body">
				小M
				<p>发表于 2016-06-30 15:30</p>
			</div>
		</div>
		<div class="mui-card-content">
			<div class="mui-card-content-inner">
				包含页眉页脚的卡片，页眉常用来显示面板标题，页脚用来显示额外信息或支持的操作（比如点赞、评论等）</div>
		</div>
		<div class="mui-card-footer">
			<a class="mui-card-link "></a> <a class="mui-card-link"> <span
				 scolling="yes" class="mui-icon icomoon icon-thumbs-up"></span>45</a>
		</div>
	</div>
	<div class="mui-card">
		<div class="mui-card-header mui-card-media">
			<img src="http://wx.qlogo.cn/mmopen/gITwFOywPbkCx8BxwYc41oAGjuBeFianAbtHl8URmaCMTe9lib6EicNuHSibGJzSfT6Y88Nos1poHITnB7vUs7foHphNpibcgFEja/0" />
			<div class="mui-media-body">
				小M
				<p>发表于 2016-06-30 15:30</p>
			</div>
		</div>
		<div class="mui-card-content">
			<div class="mui-card-content-inner">
				包含页眉页脚的卡片，页眉常用来显示面板标题，页脚用来显示额外信息或支持的操作（比如点赞、评论等）</div>
		</div>
		<div class="mui-card-footer">
			<a class="mui-card-link "></a> <a class="mui-card-link"> <span
				 scolling="yes" class="mui-icon icomoon icon-thumbs-up"></span>5</a>
		</div>
	</div>
</body>
</html>
