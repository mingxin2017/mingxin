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
	document.getElementById('aaa'+commentId).onclick='';
	document.getElementById('aaa'+commentId).style.color='gray';
	var numTip=document.getElementById('span'+commentId);
	var num=parseInt(numTip.innerHTML);
	//numTip.innerHTML=num+1;
	
	 $.ajax({
         url:'activitiesMySpace!CommentClickPraise.action',
         type: 'post',
         data: {'commentId':commentId,'userId':userId},
         dataType: 'json',
         timeout: 2000000,
         success: function (response) {
             if (response.done == '0') {
            	 numTip.innerHTML=num+1;
            	 document.getElementById('aaa'+commentId).style.color='gray';
             	//var d=dialog().show();
             	//d.content(response.msg);
             	//setTimeout(function () {
		    	//		d.close().remove();
		    	//	}, 1500);
             	//刷新页面
             	//document.getElementById('mainContent').src= "activitiesMySpace!getActivitiesMySpaceMaterialList.action";
             	
             	
                 return true;
             } else {
            	 //numTip.innerHTML=num-1;
            	 //num=num-1;
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
</script>
</head>

<body>
	
	<c:forEach items="${userMySpaceCommentList}" var="item">
	<div class="mui-card">
		<div class="mui-card-header mui-card-media" >
			<img data-lazyload="${item.mxUsersData.weixinHeadUrl}" />
			<div class="mui-media-body">
				${item.mxUsersData.weixinNikeName}
				<p>发表于<fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd　HH:mm"/>
					<span class="mui-badge mui-badge-danger">新</span>
				</p>
			</div>
		</div>
		<div class="mui-card-content">
			<div class="mui-card-content-inner">
				${item.commentTxt}
			</div>
		</div>
		<div class="mui-card-footer">
			
			<%boolean isClicked=false; %>
			<c:set value="${fn:split(item.praiseUserIds, ',') }" var="userIds" />
			<c:forEach items="${userIds}" var="clickUserId">
				<c:if test="${clickUserId==userInfo.userId}"><%isClicked=true; %> </c:if>
			</c:forEach>
			
			<a></a>
			<%if(isClicked==false){ %>  
       				<a id="aaa${item.commentId}" class="mui-card-link" href="javascript:void(0);" onclick="ClickPraise(${item.commentId},${userInfo.userId});"> 
					<span class="mui-icon icomoon icon-thumbs-up"></span><span id="span${item.commentId}">${item.praiseClickNum}</span>
					</a>      
  				
   			 <%}else{ %>
     			<a id="a${item.commentId}" class="mui-card-link" href="javascript:void(0);" style="color:gray"> 
					<span class="mui-icon icomoon icon-thumbs-up"></span><span id="span${item.commentId}">${item.praiseClickNum}</span>
				</a>
   			<%} %>
			
		</div>
	</div>
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
