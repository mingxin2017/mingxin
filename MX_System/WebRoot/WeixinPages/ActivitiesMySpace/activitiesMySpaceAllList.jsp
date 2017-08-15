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
    
    <title>活动空间</title>
	<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<link href="<%=basePath%>WeixinPages/common/css/mui.min.css"
		rel="stylesheet" />
	<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
	<script type="text/javascript" src="<%=basePath%>WeixinPages/common/js/jquery-1.11.2.js"></script>
	<script type="text/javascript" src="<%=basePath%>WeixinPages/common/js/dialog.js"></script>
	
	<script type="text/javascript">
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
	</script>
	<script>
		mui.init({
		swipeBack : false//启用右滑关闭功能
	});
	</script>
	<script type="text/javascript">
	//退出个人空间
	function quitPage() {
		wx.closeWindow();
	}
	
	
	function inputInviteCode(){
		var d = dialog({
			fixed: true,
			content: '<div>粘贴邀请码信息</div><textarea id=\"inviteCode\" rows=\"3\" cols=\"25\"></textarea>',
			button : [ {
						value : '取消'
						},{
							value : '提交',
							callback : function() {
								//alert("11111");
								var inviteCodeAll = document.getElementById('inviteCode').value;//获取选择的文件
								var inviteCode=inviteCodeAll.substring(inviteCodeAll.indexOf("(")+1,inviteCodeAll.indexOf(")"));
								//alert(inviteCode);
								if(inviteCode==''){
									alert('未输入验证码！');
								}else{
									validateInviteCode(inviteCode);
									d.close().remove();
								}
							},
							autofocus : true
						} ]

					}).showModal();
	}
	
	function validateInviteCode(inviteCode){
		$.ajax({
		    type: "POST",
		    url: "activitiesMySpace!validateInviteCode.action", //验证邀请码
		    data: {"userId":${sessionScope.userInfo.userId},"inviteCode":inviteCode},
		    dataType:"json",
		    async:false,//关闭异步，设置同步
		    success: function(data){
		    	//var done=parseInt(data.done);
		    	if(data.done=='0'){//加入活动成功
		    		//刷新当前页面
		    		dialog(data.msg, function(){}).showModal();
		    		//window.location.reload();
		    		//alert(data.liData);
		    		var t=document.getElementById('tableList');
		    		//alert(t);
		    		t.innerHTML+=data.liData;
		    		
		    	}else{
		    		dialog(data.msg, function(){}).showModal();
		    	}
			},
			error: function(json){
				alert('错误');
			}
	    });
	}
	</script>
  </head>
  
  <body>
	
	<header class="mui-bar mui-bar-nav" id="myspaceMainHeader"> 
		<a class="mui-btn mui-btn-blue mui-btn-link mui-pull-left" onclick="quitPage();">退出</a>
		<h1 id="title" class="mui-title">我的活动</h1>
		<div id="operate" class="mui-btn mui-btn-blue mui-btn-link mui-pull-right" onclick="inputInviteCode();">输入邀请码</div>
	</header>
	<div class="mui-content " id="mainContent">
		<ul class="mui-table-view" id="tableList">
			<%--<c:if
				test="${userMySpaceDataList==null || fn:length(userMySpaceDataList) == 0}">
				<li>暂未参加任何活动</li>
				<li><button class="mui-button">点我参看最新活动</button></li>
			</c:if>
			--%>
			<c:forEach items="${userMySpaceDataList}" var="item">
				<li  class="mui-table-view-cell mui-media">
					<a href="activitiesMySpace/gotoActivitiesMySpaceMain.action?myspaceId=${item.myspaceId}">
						<img  class="mui-media-object mui-pull-left"  src="${item.coverImageUrl}">
						<div class="mui-media-body" style="color:black" >
							${item.myspaceName}
							<p class="mui-ellipsis" style="color:#87CEFA" >${item.describe}</p>
						</div>
					</a>
				</li>
			</c:forEach>
		</ul>
	</div>
</body>
</html>
