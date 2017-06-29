<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>鸣心-活动空间</title>
    <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		
		<style>
			html,
			body {
				background-color: #efeff4;
			}
		</style>
		
	<!--标准mui.css-->
	<link rel="stylesheet" href="<%=basePath%>WeixinPages/common/css/mui.min.css">
	<!--mui js-->
	<script src="<%=basePath%>WeixinPages/common/js/mui.min.js"></script>
	
  </head>
  
  <body>
		<header class="mui-bar mui-bar-nav">
			<h1 class="mui-title">标题</h1>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>鸣心-活动空间</title>
    <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<!--标准mui.css-->
	<link rel="stylesheet" href="<%=basePath%>WeixinPages/common/css/mui.min.css">
	<!--App自定义的css-->
	<link rel="stylesheet" type="text/css" href="<%=basePath%>WeixinPages/common/css/app.css" />
	<script src="<%=basePath%>WeixinPages/common/js/mui.min.js"></script>
	<script src="<%=basePath%>WeixinPages/common/js/jquery-1.11.2.js"></script>
	<!--列表js-->
	<script>
	mui.init({
		swipeBack: false //启用右滑关闭功能
	});
	
	var title = document.getElementById("title");
	 //选项卡点击事件
	mui('.mui-bar-tab').on('tap', 'a', function(e) {
		alert("ddd");
		var targetTab = this.getAttribute('href');
		if (targetTab == activeTab) {
			return;
		}
		//更换标题
		title.innerHTML = this.querySelector('.mui-tab-label').innerHTML;
		//显示目标选项卡
		//若为iOS平台或非首次显示，则直接显示
		if(mui.os.ios||aniShow[targetTab]){
			plus.webview.show(targetTab);
		}else{
			//否则，使用fade-in动画，且保存变量
			var temp = {};
			temp[targetTab] = "true";
			mui.extend(aniShow,temp);
			plus.webview.show(targetTab,"fade-in",300);
		}
		//隐藏当前;
		plus.webview.hide(activeTab);
		//更改当前活跃的选项卡
		activeTab = targetTab;
	});
	
	</script>
	<script type="text/javascript">
		function showIframe(pageNum){
			var url;
			alert("ddd");
			if(pageNum==1){
				url="http://mingxin.imwork.net/MX_System/activitiesMySpace!getActivitiesMySpaceCommentList.action";
			}else if(pageNum==2){
				url="http://mingxin.imwork.net/MX_System/activitiesMySpace!getActivitiesMySpaceMaterialList.action";
			}else if(pageNum==3){
				url="http://mingxin.imwork.net/MX_System/activitiesMySpace!getActivitiesMySpaceUsersList.action";
			}else if(pageNum==4){
				url="http://mingxin.imwork.net/MX_System/activitiesMySpace!getActivitiesMySpaceManage.action";
			}else{
				return;
			}
			$("#mainContent").attr("src",url).ready();
			//alert("dddd");
		}
	</script>
	
  </head>
  
  <body>
  		<header class="mui-bar mui-bar-nav">
			<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
			<h1 id="title" class="mui-title">活动空间</h1>

	<head>
		<meta charset="utf-8">
		<title>Hello MUI</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">

		<link rel="stylesheet" href="<%=basePath%>WeixinPages/common/css/mui.min.css">
		<script src="<%=basePath%>WeixinPages/common/js/mui.min.js"></script>
		<script src="<%=basePath%>WeixinPages/common/js/webviewGroup.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript" charset="utf-8">
			 //mui初始化
			mui.init();
			 //获取标题栏
			var title = document.getElementById("title");
			 //选项卡点击事件
			mui('.mui-bar-tab').on('tap', 'a', function(e) {
				//更换标题
				title.innerHTML = this.querySelector('.mui-tab-label').innerHTML;
				
				var targetTab = this.getAttribute('target');
				
				if (targetTab == 'commentList') {
					return;
				}else if(targetTab == 'materialList'){
					
				}else if(targetTab == 'usersList'){
					
				}else if(targetTab == 'mineList'){
					
				}else{alert("未知的标签");}
				
			});
		</script>
		
	</head>

	<body>
		<header class="mui-bar mui-bar-nav">
			<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
			<h1 id="title" class="mui-title">鸣心-活动空间</h1>
		</header>
		<nav class="mui-bar mui-bar-tab">
			<a target="commentList" class="mui-tab-item mui-active" href="JavaScript:void(0);">
				<span class="mui-icon mui-icon-home"></span>
				<span class="mui-tab-label">讨论区</span>
			</a>
			<a target="materialList" class="mui-tab-item" href="JavaScript:void(0);">
				<span class="mui-icon mui-icon-email"><span class="mui-badge">9</span></span>
				<span class="mui-tab-label">图片</span>
			</a>
			<a target="usersList" class="mui-tab-item" href="JavaScript:void(0);">
				<span class="mui-icon mui-icon-contact"></span>
				<span class="mui-tab-label">通讯录</span>
			</a>
			<a target="mineList" class="mui-tab-item" href="JavaScript:void(0);">
				<span class="mui-icon mui-icon-gear"></span>
				<span class="mui-tab-label">我的空间</span>
			</a>
		</nav>
		<div id="iframeContent" class="mui-content">
    	<iframe style="width:100%;height:82%;" id="mainContent" current="commentList" src="activitiesMySpace!getActivitiesMySpaceCommentList.action"/>
	</div>
	</body>
</html>

		

