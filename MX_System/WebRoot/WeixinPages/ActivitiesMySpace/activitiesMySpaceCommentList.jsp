<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
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

<script type="text/javascript">
function ClickPraise(commentId,userId){
	document.getElementById('praise_'+commentId).onclick='';
	document.getElementById('praise_'+commentId).style.color='gray';
	var numTip=document.getElementById('span'+commentId);
	var num=parseInt(numTip.innerHTML);
	//numTip.innerHTML=num+1;
	
	 $.ajax({
         url:'activitiesMySpace/CommentClickPraise.action',
         type: 'post',
         data: {'commentId':commentId,'userId':userId},
         dataType: 'json',
         async:true,//关闭异步
         success: function (response) {
             if (response.done == '0') {
            	 numTip.innerHTML=num+1;
            	 document.getElementById('praise_'+commentId).style.color='gray';
                 return true;
             } else {
            	 var d=dialog().show();
             	d.content(response.msg);
             	setTimeout(function () {
		    			d.close().remove();
		    		}, 1000);
                 return alert(response.msg);
             }
         },

         error: function (jqXHR, textStatus, errorThrown) {

             if (textStatus == 'timeout') {
                 a_info_alert('请求超时');

                 return false;
             }

             alert(jqXHR.responseText);
         }
     });
}


function ClickComment_comment(obj,myspaceId,commentId,userId){
	var d = dialog({
		content: '<textarea autofocus id="subTxt" rows="1" cols="25" placeholder="请输入评论内容">',
		quickClose: true,// 点击空白处快速关闭
		height: '2em',
		button : [ {
							value : '发送',
							callback : function() {
								var txt = $('#subTxt').val();//获取输入的值
								if(txt!=''){
									doSaveMyspaceComment_comment(userId,myspaceId,commentId,txt);
								}
								
							},
							autofocus : true
				}]
		
	}).show(obj);
}

function doSaveMyspaceComment_comment(userId,myspaceId,commentId,commentTxt){
	$.ajax({
		    type: "POST",
		    url: "activitiesMySpace/DoSaveMyspaceComment_comment.action", //orderModifyStatus
		    data: {"userId":userId,"commentId":commentId,"commentTxt":commentTxt,"myspaceId":myspaceId},
		    dataType:"json",
		    success: function(data){
		    	if(data.done=='0'){
		    		document.getElementById('comment2_'+commentId).innerHTML+=('<h5><b>${sessionScope.userInfo.userRealName} 说：</b>“'+commentTxt+'”</h5>');
		    		alert('发送成功');
		    	}else{
		    		var dd = dialog('发送失败');
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

function DeleteComment_comment(commentId){

		/* var d = dialog({
			fixed: true,
			content: '确定删除本条评论吗？',
			button : [{
						value : '取消'
						},{
							value : '确定',
							callback : function() {
								DoDeleteComment_comment(commentId);
							},
							autofocus : true
						}]
					}).showModal(); */
	
	var truthBeTold = window.confirm("确定删除本条评论吗？");
	if (truthBeTold) {
		DoDeleteComment_comment(commentId);
	}
}

//删除帖子评论
function DoDeleteComment_comment(commentId){
	$.ajax({
		    type: "POST",
		    url: "activitiesMySpace/DoDeleteMyspaceComment_comment.action", //orderModifyStatus
		    data: {"commentId":commentId},
		    dataType:"json",
		    success: function(data){
		    	if(data.done=='0'){
		    		var c=document.getElementById('comment3_'+commentId);
		    		alert(c);
		    		c.parentNode.removeChild(c);
		    		alert('成功删除');
		    	}else{
		    		var dd = dialog('删除失败');
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
</script>
</head>

<body>
	
	<c:forEach items="${userMySpaceCommentList}" var="item">
	<c:if test="${item.parentCommentId eq -1}">
	<%int commentNum=0; %>
		<c:forEach items="${userMySpaceCommentList}" var="item1">
				<c:if test="${item1.parentCommentId eq item.commentId}">
					<% commentNum=commentNum+1; %>
				</c:if> 
		</c:forEach>
	<div class="mui-card">
		<div class="mui-card-header mui-card-media" >
			<img data-lazyload="${item.mxUsersData.weixinHeadUrl}" />
			<div class="mui-media-body">
				${item.mxUsersData.userRealName}
				<p>发表于<fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd　HH:mm"/>
					<span class="mui-badge mui-badge-danger">新</span>
				</p>
			</div>
		</div>
		<div class="mui-card-content">
			<div class="mui-card-content-inner">
				<font size="3" face="arial">${item.commentTxt}</font>
			</div>
		</div>
		<div class="mui-card-footer">
			
			<%boolean isClicked=false; %>
			<c:set value="${fn:split(item.praiseUserIds, ',') }" var="userIds" />
			<c:forEach items="${userIds}" var="clickUserId">
				<c:if test="${clickUserId==sessionScope.userInfo.userId}"><%isClicked=true; %> </c:if>
			</c:forEach>
			
			
			<%if(isClicked==false){ %>  
       			<a id="praise_${item.commentId}" class="mui-card-link" href="javascript:void(0);" onclick="ClickPraise(${item.commentId},${sessionScope.userInfo.userId});"> 
					<span class="mui-icon icomoon icon-thumbs-up"></span><span id="span${item.commentId}">${item.praiseClickNum}</span>赞
				</a>      
  				
   			 <%}else{ %>
     			<a id="a${item.commentId}" class="mui-card-link" href="javascript:void(0);" style="color:gray"> 
					<span class="mui-icon icomoon icon-thumbs-up"></span><span id="span${item.commentId}">${item.praiseClickNum}</span>赞
				</a>
   			<%} %>
   			<%-- <c:if test="${item.mxUsersData.userId eq sessionScope.userInfo.userId}">
			<a id="comment_${item.commentId}" class="mui-card-link" href="javascript:void(0);" style="color:gray"> 
				<span class="mui-icon mui-icon-chatboxes"></span><span id="span${item.commentId}"><%=commentNum %></span>评论
			</a> 
			</c:if> --%>
			<%-- <c:if test="${item.mxUsersData.userId ne sessionScope.userInfo.userId}"> --%>
			<a id="comment_${item.commentId}" class="mui-card-link" href="javascript:void(0);" onclick="ClickComment_comment(this,${sessionScope.myspaceId},${item.commentId},${sessionScope.userInfo.userId});"> 
				<span class="mui-icon mui-icon-chatboxes"></span><span id="span${item.commentId}"><%=commentNum %></span>评论
			</a> 
			<%-- </c:if>--%>
		</div>
		
			<div style="padding:0px 20px 20px 40px;" id="comment2_${item.commentId}">
				<c:forEach items="${userMySpaceCommentList}" var="item2">
					<c:if test="${item2.parentCommentId eq item.commentId}">
					<c:if test="${item.mxUsersData.userId eq sessionScope.userInfo.userId}">
					<div class="mui-row" id="comment3_${item2.commentId}"> 
						<div class="mui-col-xs-10">
     						<h5><b>${item2.mxUsersData.userRealName} 说：</b>“${item2.commentTxt}”</h5>
     					</div>
     					<div class="mui-col-xs-2">
     						<a style=" color:#EE7942;" href="javascript:void(0);" onclick="DeleteComment_comment(${item2.commentId});">删除</a>
     					</div>
     				</div>
					</c:if> 
					<c:if test="${item.mxUsersData.userId ne sessionScope.userInfo.userId}">
					<div class="mui-row" id="comment3_${item2.commentId}"> 
						<div class="mui-col-xs-12">
     						<h5><b>${item2.mxUsersData.userRealName} 说：</b>“${item2.commentTxt}”</h5>
     					</div>
     				</div>
					</c:if>
					</c:if>
				</c:forEach>
			</div>
	</div>
	</c:if>
	</c:forEach>
	
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
		lazyLoadApi.refresh(true);
	})(mui);
</script>
</html>
