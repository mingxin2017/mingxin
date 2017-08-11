<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.mx.weixin.pojo.SNSUserInfo,java.lang.*"%>
<%String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";%>
<head>
<base href="<%=basePath%>">
<title>新闻页面</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">

		<!--标准mui.css-->
		<link rel="stylesheet" href="<%=basePath%>WeixinPages/common/css/mui.min.css">
		<style>
			h5 {
				margin: 5px 7px;
			}
		</style>
<!-- <script type="text/javascript">
	// 对浏览器的UserAgent进行正则匹配，不含有微信独有标识的则为其他浏览器
	var useragent = navigator.userAgent;
	if (useragent.match(/MicroMessenger/i) != 'MicroMessenger') {
		// 这里警告框会阻塞当前页面继续加载
		alert('已禁止本次访问：您必须使用微信内置浏览器访问本页面！');
		// 以下代码是用javascript强行关闭当前页面
		var opened = window.open('about:blank', '_self');
		opened.opener = null;
		opened.close();
	}
</script> -->	

		
		<link rel="stylesheet" type="text/css" href="<%=basePath%>WeixinPages/common/css/app.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>WeixinPages/common/css/feedback.css" />
</head>
	<body>
		<div class="mui-card">
			<div class="mui-card-header mui-card-media">
					<img src="<%=basePath%>WeixinPages/common/image/logo_mx.png" />
					<div class="mui-media-body">
						${MxNewsData.writerName}
						<p>发表于${MxNewsData.createDate}</p>
					</div>
			</div>
			<div class="mui-card-header mui-card-media" style="height:40vw;background-image:url(WeixinPages/common/image/cbd.jpg)"></div>
			<div class="mui-card-content">
				<div class="mui-card-content-inner">
					${MxNewsData.newsHeadline}<br>
					${MxNewsData.newsLeadText}
				</div>
			</div>
			<div class="mui-card-footer">
			<div>[点赞区]</div>
			<div>[评论区]</div>
			</div>
		</div>

	</body>
	<script src="<%=basePath%>WeixinPages/common/js/mui.min.js "></script>
	<script src="<%=basePath%>WeixinPages/common/js/mui.view.js "></script>
	<script src='<%=basePath%>WeixinPages/common/js/feedback.js'></script>
	<script>
		mui.init();

	
	</script>


