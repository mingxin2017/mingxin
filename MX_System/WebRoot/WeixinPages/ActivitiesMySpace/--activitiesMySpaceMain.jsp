<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="utf-8">
<title>鸣心-活动空间</title>
	<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<link rel="stylesheet"
	href="<%=basePath%>WeixinPages/common/css/mui.min.css"/>
	

</head>

<body>

		<header class="mui-bar mui-bar-nav">
			<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
			<h1 id="title" class="mui-title">首页</h1>
		</header>
		<nav class="mui-bar mui-bar-tab">
			<a id="defaultTab" class="mui-tab-item mui-active" href="activitiesMySpace/getActivitiesMySpaceMaterialList.action">
				<span class="mui-icon mui-icon-home"></span>
				<span class="mui-tab-label">首页</span>
			</a>
			<a class="mui-tab-item" href="activitiesMySpace/getActivitiesMySpaceCommentList.action">
				<span class="mui-icon mui-icon-email"><span class="mui-badge">9</span></span>
				<span class="mui-tab-label">消息</span>
			</a>
			<a class="mui-tab-item" href="www.hao123.com">
				<span class="mui-icon mui-icon-contact"></span>
				<span class="mui-tab-label">通讯录</span>
			</a>
			<a class="mui-tab-item" href="www.sina.com">
				<span class="mui-icon mui-icon-gear"></span>
				<span class="mui-tab-label">设置</span>
			</a>
		</nav>
		<div id="iframeContent" class="mui-content" >
			<input id="myspaceId" name="myspaceId" type="hidden" value="${sessionScope.myspaceId}"/>
			<input id="userId" name="userId" type="hidden" value="${sessionScope.userInfo.userId}"/>
			<div style="width:100%"  id="mainContent" name="mainContent" ></div>
		</div>
	<script src="<%=basePath%>WeixinPages/common/js/mui.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>WeixinPages/common/js/jquery-1.11.2.js"></script>
	<script type="text/javascript" charset="utf-8">
	
	
			 //mui初始化
			mui.init();
			var subpages = ['getActivitiesMySpaceMaterialList.action', 'getActivitiesMySpaceCommentList.action', 'www.hao123.com', 'www.sina.com'];
			var subpage_style = {
				top: '45px',
				bottom: '51px'
			};
			
			var aniShow = {};
			
			$(function(){
				alert(subpages[0]); 
				$("#mainContent").load(subpages[0]);
			});
			
			 //创建子页面，首个选项卡页面显示，其它均隐藏；
			mui.plusReady(function() {
				alert(22222);
				$("#mainContent").load(subpages[1]);
				
				//var self = plus.webview.currentWebview();
				//for (var i = 0; i < 4; i++) {
				//	var temp = {};
					//var sub = plus.webview.create(subpages[i], subpages[i], subpage_style);
					//if (i > 0) {
					//	sub.hide();
					//}else{
					//	temp[subpages[i]] = "true";
					//	mui.extend(aniShow,temp);
					//}
					//self.append(sub);
				//}
			});
			//当前激活选项
			var activeTab = subpages[0];
			var title = document.getElementById("title");
			 //选项卡点击事件
			mui('.mui-bar-tab').on('tap', 'a', function(e) {
				alert('1');
				var targetTab = this.getAttribute('href');
				if (targetTab == activeTab) {
					return;
				}
				//更换标题
				title.innerHTML = this.querySelector('.mui-tab-label').innerHTML;
				//显示目标选项卡
				//若为iOS平台或非首次显示，则直接显示
				if(mui.os.ios||aniShow[targetTab]){
					alert(targetTab);
					//plus.webview.show(targetTab);
					$("#mainContent").load(targetTab);
					//$("#mainContent").html('255565255');
					alert('2');
				}else{
					alert(targetTab);
					//否则，使用fade-in动画，且保存变量
					var temp = {};
					temp[targetTab] = "true";
					mui.extend(aniShow,temp);
					
					//plus.webview.show(targetTab,"fade-in",300);
					$("#mainContent").load(targetTab);
					alert('3');
				}
				//隐藏当前;
				plus.webview.hide(activeTab);
				//更改当前活跃的选项卡
				activeTab = targetTab;
				alert('4');
			});
			 //自定义事件，模拟点击“首页选项卡”
			document.addEventListener('gohome', function() {
				var defaultTab = document.getElementById("defaultTab");
				//模拟首页点击
				mui.trigger(defaultTab, 'tap');
				//切换选项卡高亮
				var current = document.querySelector(".mui-bar-tab>.mui-tab-item.mui-active");
				if (defaultTab !== current) {
					current.classList.remove('mui-active');
					defaultTab.classList.add('mui-active');
				}
			});
		</script>
</body>

</html>



