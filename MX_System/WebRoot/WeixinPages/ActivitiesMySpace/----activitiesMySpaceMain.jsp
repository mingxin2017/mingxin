<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

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
		</header>
		<nav class="mui-bar mui-bar-tab">
			<a class="mui-tab-item mui-active" href="javascript:void(0)" onclick="showIframe(1);">
				<span class="mui-icon mui-icon-chat"></span>
				<span class="mui-tab-label">讨论</span>
			</a>
			<a class="mui-tab-item " href="javascript:void(0)" onclick="showIframe(2);">
				<span class="mui-icon mui-icon-image"></span>
				<span class="mui-tab-label">图库</span>
			</a>
			<a class="mui-tab-item" href="javascript:void(0)" onclick="showIframe(3);">
				<span class="mui-icon mui-icon-contact"></span>
				<span class="mui-tab-label">通讯录</span>
			</a>
			<a class="mui-tab-item" href="javascript:void(0)" onclick="showIframe(4);">
				<span class="mui-icon mui-icon-gear"></span>
				<span class="mui-tab-label">设置</span>
			</a>
		</nav>
    	<div class="mui-content">
    		<iframe style="width:100%;" id="mainContent" src="http://mingxin.imwork.net/MX_System/activitiesMySpace!getActivitiesMySpaceCommentList.action"/>
		</div>
  </body>
</html>
