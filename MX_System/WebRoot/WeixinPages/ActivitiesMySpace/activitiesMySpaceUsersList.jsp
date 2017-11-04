<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page import="com.mx.ssh.bean.MxUsersData;"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>鸣心-活动空间</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=yes"/>
<!--标准mui.css-->
<link rel="stylesheet"
	href="<%=basePath%>WeixinPages/common/css/mui.min.css">
<script type="text/javascript"
	src="<%=basePath%>WeixinPages/common/js/jquery-1.11.2.js"></script>
<script type="text/javascript"
	src="<%=basePath%>WeixinPages/common/js/dialog.js"></script>
<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
	<script>
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


//退出个人空间
function quitPage() {
	wx.closeWindow();
}
</script>
<c:if test="${sessionScope.userInfo.userTypeId== 1100}">
	<script type="text/javascript">
	
	function operate(obj){
		
				var d = dialog({
					fixed: true,
					//title:'确定生成邀请链接？',
					content:'<div id="showInviteCode">确定生成活动邀请码？</div>',
					button : [ {
						value : '取消'
						},{
							value : '生成',
							autofocus : true,
							callback : function() {
								$.ajax({
								    type: "POST",
								    url: "<%=basePath%>activitiesMySpace/DoCreateInviteCodeUrl.action", //生成活动邀请链接
								    data: {"initiator_userId":${userInfo.userId},"myspaceId":${sessionScope.myspaceId}},
								    dataType:"json",
								    async:false,
								    cache:false,
								    success: function(data){
								    	if(data.done=='0'){
								    		//document.getElementById('showInviteCode').innerHTML='<input type=\"text\" value=\"'+data.inviteCodeUrl+'\"/>';
								    		var dd = dialog({
								    				title:'复制以下内容，发送给参加人员',
								    				//content:'<div>该邀请码有效期为24小时</div><input type=\"text\" value=\"'+data.inviteCode+'\"/>\r\n<',
								    				content:'<textarea rows=\"5\" cols=\"25\" readonly=\"readonly\">邀请码('+data.inviteCode+')，复制整条信息至-鸣心公众号-个人中心-个人空间粘贴即可接受邀请。邀请码有效期至2017-11-03 15:25止。</textarea>',
								                    okValue: '完成',
								                    ok: function() {
								                        dd.close().remove();
								                    }
								    		}).showModal();
								    		
								    		//setTimeout(function () {
								    		//	dd.close().remove();
								    		//}, 2000);
								    	}else{
								    		var dd = dialog('生成失败');
								    		setTimeout(function () {
								    			dd.close().remove();
								    		}, 1000);
								    	}
									},
									error: function(json){
										var ddd = dialog('提交数据异常，请刷新后重试...').show();
										setTimeout(function () {
											ddd.close().remove();
							    		}, 1500);
									}
							    });
							
						}
						}]
					
				}).showModal();
				
		}
	
	
	function DeleteMySpaceUser(myspaceId,userId){
		var d = dialog({
			fixed: true,
			content: '确定将该用户移出本活动吗？',
			button : [{
						value : '取消'
						},{
							value : '确定',
							callback : function() {
								$.ajax({
								    type: "POST",
								    url: "activitiesMySpace/removeMyspaceUser.action", //验证邀请码
								    data: {"userId":userId,"myspaceId":myspaceId},
								    dataType:"json",
								    async:false,//关闭异步，设置同步
								    success: function(data){
								    	//var done=parseInt(data.done);
								    	if(data.done=='0'){//移出用户成功
								    		//刷新当前页面
								    		dialog(data.msg, function(){}).showModal();
								    	
								    		var _element=document.getElementById(userId);
								    		var _parentElement = _element.parentNode;
								            if(_parentElement){
								                   _parentElement.removeChild(_element);
								            }
								    	}else{
								    		dialog(data.msg, function(){}).showModal();
								    	}
									},
									error: function(json){
										alert('错误');
									}
							    });
							},
							autofocus : true
						}]
					}).showModal();
	}
	
	
</script>
</c:if>
</head>

<body>
	<header class="mui-bar mui-bar-nav" id="myspaceMainHeader"> 
		<a class="mui-btn mui-btn-blue mui-btn-link mui-pull-left" onclick="quitPage();">退出</a>
		<h1 id="title" class="mui-title">通讯录</h1>
		<c:if test="${sessionScope.userInfo.userTypeId== 1100}">
		<a id="operate" class="mui-btn mui-btn-blue mui-btn-link mui-pull-right" onclick="operate(this);">生成邀请码</a>
		</c:if>
	</header>
	<nav class="mui-bar mui-bar-tab" id="footerTab"> 
		<a  id="tab1" class="mui-tab-item" href="<%=basePath%>activitiesMySpace/getActivitiesMySpaceCommentList.action"  > 
			<span class="mui-icon mui-icon-chat"><!-- <span class="mui-badge">8</span> --></span> 
			<span class="mui-tab-label">讨论区</span>
		</a> 
		<a  id="tab2" class="mui-tab-item" href="<%=basePath%>activitiesMySpace/getActivitiesMySpaceMaterialList.action"> 
			<span class="mui-icon mui-icon-image">
			<!-- <span class="mui-badge">3</span> -->
			</span> 
			<span class="mui-tab-label">照片墙</span> 
		</a> 
		<a  id="tab3" class="mui-tab-item mui-active" href="<%=basePath%>activitiesMySpace/getActivitiesMySpaceUsersList.action" > 
			<span class="mui-icon mui-icon-contact"></span>
			<span class="mui-tab-label">通讯录</span>
		</a> 
		<a  id="tab4" class="mui-tab-item" href="<%=basePath%>activitiesMySpace/getActivitiesMySpaceMine.action" >
			<span class="mui-icon mui-icon-gear"></span> 
			<span class="mui-tab-label">个人空间</span> 
		</a> 
	</nav>

	<div class="mui-content">
		<ul class="mui-table-view">
			<c:forEach items="${mySpaceUsersList}" var="item">
				<li class="mui-table-view-cell mui-media"
					id="${item.mxUsersData.userId}">
					<div class="mui-slider-handle">
						<img class="mui-media-object mui-pull-left"
							data-lazyload="${item.mxUsersData.weixinHeadUrl}">
						<div class="mui-media-body">
							${item.mxUsersData.userRealName}
							<c:if test="${item.mxUsersData.userTypeId== 1100}">
								<span class="mui-badge mui-badge-success">组织者</span>
							</c:if>
							<a href="tel:${item.mxUsersData.userPhoneNum}"><p class='mui-ellipsis'>电话${item.mxUsersData.userPhoneNum}</p></a>
						</div>
					</div> <c:if test="${sessionScope.userInfo.userTypeId== 1100}">
						<c:if test="${item.mxUsersData.userTypeId!= 1100}">
							<div class="mui-slider-right mui-disabled">
								<button id='deleteBtn' type="button"
									onclick="DeleteMySpaceUser(${sessionScope.myspaceId},${item.mxUsersData.userId});"
									class="mui-btn mui-btn-red ">删除</button>
							</div>
						</c:if>
					</c:if></li>
			</c:forEach>
		</ul>
	</div>
</body>
<script src="<%=basePath%>WeixinPages/common/js/mui.min.js"></script>
<script src="<%=basePath%>WeixinPages/common/js/mui.lazyload.js"></script>
<script src="<%=basePath%>WeixinPages/common/js/mui.lazyload.img.js"></script>
<script>
	mui.init();
	(function($) {
		$(document).imageLazyload({
			placeholder: '/MX_System/WeixinPages/common/images/60x60.gif'
		});
	})(mui);
	mui('#footerTab').on('tap','a',function(){
	    window.top.location.href=this.href;
	});

</script>
</html>
