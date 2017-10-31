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
	<%--<link rel="stylesheet" href="<%=basePath%>WeixinPages/common/css/mui.min.css">
	
	
	
	
--%>

<jsp:include page="pub_js.jsp"/>

<style type="text/css">
.rich{
	width:80px;
    border:1px solid #0997F7;
    overflow: auto;
}
.rich:empty:before{
    content:attr(placeholder);
    font-size: 16px;
    color: #999;
}
.rich:focus:before{
    content:none;
}
.footer-btn {
  text-align: center;
  padding-top: 1px;
}
.footer-btn .upload-img {
  height: 8%;
  min-height:25px;
  display: inline-block;
  vertical-align: bottom;
  width: 25px;
  margin-right: 10px;
  background-size: 100%;
}
.input-file-css{
	position:absolute;
	left:0;
	opacity:0;
	width:100%;
}

.SubBtn{
	width:100%;
	height:10%;
	min-height:30px;
	text-align:center; /*水平居中*/
	
}
</style>

</head>

<body>

	<header class="mui-bar mui-bar-nav" id="myspaceMainHeader"> 
		<a class="mui-btn mui-btn-blue mui-btn-link mui-pull-left" onclick="quitPage();">退出</a>
		<h1 id="title" class="mui-title">讨论区</h1>
		<div id="operate" class="mui-btn mui-btn-blue mui-btn-link mui-pull-right" ">发帖</div>
	</header>
	<nav class="mui-bar mui-bar-tab" id="footerTab"> 
		<a  id="1" class="mui-tab-item mui-active"  > 
			<span class="mui-icon mui-icon-chat"><!-- <span class="mui-badge">8</span> --></span> 
			<span class="mui-tab-label">讨论区</span>
		</a> 
		<a  id="2"class="mui-tab-item"  > 
			<span class="mui-icon mui-icon-image">
			<!-- <span class="mui-badge">3</span> -->
			</span> 
			<span class="mui-tab-label">照片墙</span> 
		</a> 
		<a  id="3"class="mui-tab-item" > 
			<span class="mui-icon mui-icon-contact"></span>
			<span class="mui-tab-label">通讯录</span>
		</a> 
		<a  id="4"class="mui-tab-item">
			<span class="mui-icon mui-icon-gear"></span> 
			<span class="mui-tab-label">个人空间</span> 
		</a> 
	</nav>
	<div id="iframeContent" class="mui-content" >
		<input id="myspaceId" name="myspaceId" type="hidden" value="${sessionScope.myspaceId}"/>
		<input id="userId" name="userId" type="hidden" value="${sessionScope.userInfo.userId}"/>
		<iframe style="width:100%"  id="mainContent" name="mainContent"
			 src="getActivitiesMySpaceCommentList.action" />
	</div>
	
	<script type="text/javascript" src="<%=basePath%>WeixinPages/common/js/jquery-1.11.2.js"></script>
	<script src="<%=basePath%>WeixinPages/common/js/mui.min.js"></script>
	<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
	<script src="<%=basePath%>WeixinPages/common/imgDeal/dist/lrz.bundle.js?v=09bcc27"></script>
	<script type="text/javascript" src="<%=basePath%>WeixinPages/common/js/dialog.js"></script>
<script type="text/javascript" charset="utf-8">
mui.init();
var subpages = ['getActivitiesMySpaceCommentList.action', 'getActivitiesMySpaceMaterialList.action', 'getActivitiesMySpaceUsersList.action', 'getActivitiesMySpaceMine.action'];
var activeTab = 0;//当前激活选项
var title = document.getElementById("title");

$(function(){
	alert(subpages[0]); 
	$("#mainContent").load(subpages[0]);
	
	
	
});





 //选项卡点击事件
mui(".mui-bar-tab").on('tap','.mui-tab-item',function(){
	alert('1');
	var targetTab = this.getAttribute('id');
	if (targetTab == activeTab) {
		return;
	}
	

	
	//更换标题
	title.innerHTML = this.querySelector('.mui-tab-label').innerHTML;
	
	if(targetTab == '1'){
		//$("#mainContent").load(subpages[0]);
		$("#mainContent").src=subpages[0];
	}
	if(targetTab == '2'){
		//$("#mainContent").load(subpages[1]);
		$("#mainContent").src=subpages[1];
	}
	if(targetTab == '3'){
		$("#mainContent").src=subpages[2];
		//$("#mainContent").load(subpages[2]);
	}
	if(targetTab == '4'){
		//$("#mainContent").load(subpages[3]);
		$("#mainContent").src=subpages[3];
	}
	
	
});

</script>
</body>

</html>



