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
<meta name="viewport"
	content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<link rel="stylesheet"
	href="<%=basePath%>WeixinPages/common/css/mui.min.css">
<script src="<%=basePath%>WeixinPages/common/js/mui.min.js"></script>


</head>

<body>
	<header class="mui-bar mui-bar-nav"> <a
		class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
	<h1 id="title" class="mui-title">讨论区</h1>
	</header>
	<nav class="mui-bar mui-bar-tab"> <a tagFlag="commentList"
		class="mui-tab-item mui-active" href="JavaScript:void(0);"> <span
		class="mui-icon mui-icon-home"></span> <span class="mui-tab-label">讨论区</span>
	</a> <a tagFlag="materialList" class="mui-tab-item"
		href="JavaScript:void(0);"> <span class="mui-icon mui-icon-email"><span
			class="mui-badge">9</span>
	</span> <span class="mui-tab-label">图片</span> </a> <a tagFlag="usersList"
		class="mui-tab-item" href="JavaScript:void(0);"> <span
		class="mui-icon mui-icon-contact"></span> <span class="mui-tab-label">通讯录</span>
	</a> <a tagFlag="mineList" class="mui-tab-item" href="JavaScript:void(0);">
		<span class="mui-icon mui-icon-gear"></span> <span
		class="mui-tab-label">我的空间</span> </a> </nav>
	<div id="iframeContent" class="mui-content">
		<iframe style="width:100%;height:82%;" id="mainContent"
			src="activitiesMySpace!getActivitiesMySpaceCommentList.action" />
	</div>
	<script type="text/javascript" charset="utf-8">
			 //mui初始化
			mui.init();
			 //获取标题栏
			var title = document.getElementById("title");
			 //选项卡点击事件
			mui('.mui-bar-tab').on('tap', 'a', function(e) {
				//更换标题
				title.innerHTML = this.querySelector('.mui-tab-label').innerHTML;
				var targetTab = this.attr("tagFlag");//获取点击的标签属性
				//获取当前激活状态的标签
				var current = document.querySelector(".mui-bar-tab>.mui-tab-item.mui-active");
				if (this !== current) {
					//切换选项卡高亮
					current.classList.remove('mui-active');
					this.classList.add('mui-active');
					
					var url;
					if (targetTab == 'commentList') {
						url="activitiesMySpace!getActivitiesMySpaceCommentList.action";
					}else if(targetTab == 'materialList'){
						url="activitiesMySpace!getActivitiesMySpaceMaterialList.action";
					}else if(targetTab == 'usersList'){
						url="activitiesMySpace!getActivitiesMySpaceUsersList.action";
					}else if(targetTab == 'mineList'){
						url="activitiesMySpace!getActivitiesMySpaceManage.action";
					}else{
						alert("未知的标签");
						return;
					}
					$("#mainContent").attr("src",url).ready();//iframe切换
					
				}else{return;}
			});
		</script>
</body>
</html>



