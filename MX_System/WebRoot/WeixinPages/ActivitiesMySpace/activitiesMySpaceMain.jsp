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
	href="<%=basePath%>WeixinPages/common/css/mui.min.css">
	<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
	<script src="<%=basePath%>WeixinPages/common/js/dialog.js"></script>
	
<script type="text/javascript">

function showIframe(pageTag,obj){
	//alert(1010);
	var current = document.querySelector(".mui-bar-tab>.mui-tab-item.mui-active");
	if (obj !== current) {
		current.classList.remove('mui-active');
		obj.classList.add('mui-active');
	}else{return;}
	
	//更换标题
	var title = document.getElementById("title");
	var buttonValue=obj.querySelector('.mui-tab-label').innerHTML
	title.innerHTML = buttonValue;
	
	//更换按钮名称
	var operateButton=document.getElementById("operate");
	
	var url;
	if(pageTag==1){
		operateButton.innerHTML="发言";
		url='activitiesMySpace!getActivitiesMySpaceCommentList.action';
	}else if(pageTag==2){
		operateButton.innerHTML="上传照片";
		url='activitiesMySpace!getActivitiesMySpaceMaterialList.action';
	}else if(pageTag==3){
		operateButton.innerHTML="刷新";
		url='activitiesMySpace!getActivitiesMySpaceUsersList.action';
	}else if(pageTag==4){
		operateButton.innerHTML="个人信息";
		url='activitiesMySpace!getActivitiesMySpaceMine.action';
	}else{return;}
	document.getElementById('mainContent').src=url;
	
}

function operate(){
	alert(1111);
	
}

function quitPage(){
     wx.closeWindow();
}
</script>
</head>

<body>

	<header class="mui-bar mui-bar-nav" id="myspaceMainHeader"> 
		<a class="mui-btn mui-btn-blue mui-btn-link mui-pull-left" onclick="quitPage();">退出空间</a>
		<h1 id="title" class="mui-title">讨论区</h1>
		<button id="operate" class="mui-btn mui-btn-blue mui-btn-link mui-pull-right" onclick="operate();">发表讨论</button>
	</header>
	<nav class="mui-bar mui-bar-tab"> 
		<a  class="mui-tab-item mui-active" href= "JavaScript:void(0);" onclick="showIframe(1,this);" > 
			<span class="mui-icon mui-icon-chat"><span class="mui-badge">8</span></span> 
			<span class="mui-tab-label">讨论区</span>
		</a> 
		<a  class="mui-tab-item"  href= "JavaScript:void(0);" onclick="showIframe(2,this);" > 
			<span class="mui-icon mui-icon-image">
			<span class="mui-badge">3</span></span> 
			<span class="mui-tab-label">照片墙</span> 
		</a> 
		<a  class="mui-tab-item" href= "JavaScript:void(0);" onclick="showIframe(3,this);" > 
			<span class="mui-icon mui-icon-contact"></span> 
			<span class="mui-tab-label">通讯录</span>
		</a> 
		<a  class="mui-tab-item" href= "JavaScript:void(0);" onclick="showIframe(4,this);" >
			<span class="mui-icon mui-icon-gear"></span> 
			<span class="mui-tab-label">我的空间</span> 
		</a> 
	</nav>
	<div id="iframeContent" class="mui-content" >
		<iframe style="width:100%;height:425px;" id="mainContent" name="mainContent"
			 src="activitiesMySpace!getActivitiesMySpaceCommentList.action" />
	</div>
</body>
</html>



