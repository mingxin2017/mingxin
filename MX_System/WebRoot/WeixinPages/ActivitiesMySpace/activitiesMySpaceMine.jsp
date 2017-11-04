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
<meta charset="utf-8">
<title>鸣心-活动空间</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<link rel="stylesheet" href="<%=basePath%>WeixinPages/common/css/mui.min.css">

<script type="text/javascript" src="<%=basePath%>WeixinPages/common/js/jquery-1.11.2.js"></script>

<!-- 自定义图标字体 -->
<link rel="stylesheet" href="<%=basePath%>WeixinPages/common/css/icomoon.css">

	
	<script src="<%=basePath%>WeixinPages/common/js/mui.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>WeixinPages/common/js/dialog.js"></script>
	<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
	
</head>
<body>
	<header class="mui-bar mui-bar-nav" id="myspaceMainHeader"> 
		<a class="mui-btn mui-btn-blue mui-btn-link mui-pull-left" onclick="quitPage();">退出</a>
		<h1 id="title" class="mui-title">个人空间</h1>
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
		<a  id="tab3" class="mui-tab-item" href="<%=basePath%>activitiesMySpace/getActivitiesMySpaceUsersList.action" > 
			<span class="mui-icon mui-icon-contact"></span>
			<span class="mui-tab-label">通讯录</span>
		</a> 
		<a  id="tab4" class="mui-tab-item mui-active" href="<%=basePath%>activitiesMySpace/getActivitiesMySpaceMine.action" >
			<span class="mui-icon mui-icon-gear"></span> 
			<span class="mui-tab-label">个人空间</span> 
		</a> 
	</nav>
	
	<div class="mui-content">
	<div class="mui-card">
		<ul class="mui-table-view">
			<li class="mui-table-view-cell mui-collapse"><a
				class="mui-navigate-right" href="#">个人信息</a>
				<div class="mui-collapse-content">
					<form class="mui-input-group" id="userForm">
						<div class="mui-input-row">
							<label>姓名</label> <input type="text" class="mui-input-clear" id="userRealName" name="userRealName"
								placeholder="输入姓名" value="${sessionScope.userInfo.userRealName}" >
						</div>
						<div class="mui-input-row">
							<label>手机号</label> <input type="text" class="mui-input-clear" id="phoneNum" name="phoneNum"
								placeholder="输入手机号" value="${sessionScope.userInfo.userPhoneNum}" >
						</div>
						<div class="mui-input-row">
							<label>联系地址</label> <input type="text" class="mui-input-clear" id="userAddr" name="phoneNum"
								placeholder="输入地址" value="${sessionScope.userInfo.userAddr}" >
						</div>
						<div class="mui-input-row">
							<label>电子邮箱</label> <input type="text" class="mui-input-clear" id="userEmail" name="userEmail"
								placeholder="输入电子邮箱" value="${sessionScope.userInfo.userEmail}" >
						</div>
						<div class="mui-input-row">
							<label>身份证号码</label> <input type="text" class="mui-input-clear" id="userIdNum" name="userIdNum"
								placeholder="输入身份证号码" value="${sessionScope.userInfo.userIdcardNum}">
						</div>
						<div class="mui-input-row">
							<label>年级班级</label> <input type="text" class="mui-input-clear" id="userGradeClass" name="userGradeClass"
								placeholder="例：古十中87届18班" value="${sessionScope.userInfo.others}" >
						</div>
						<div class="mui-button-row">
							<button class="mui-btn mui-btn-primary" type="button" onclick="submitUserForm();">安全提交个人信息</button>
						</div>
					</form>
				</div></li>
			<li class="mui-table-view-cell mui-collapse "><a
				class="mui-navigate-right" href="#">我的照片(${fn:length(sessionScope.myspaceUserMine.activitiesMySpaceMaterialMineList)})</a>
				<div class="mui-collapse-content">
							<div id="slider" class="mui-slider">
								<div class="mui-slider-group ">
									<c:forEach items="${sessionScope.myspaceUserMine.activitiesMySpaceMaterialMineList}" var="item" varStatus="st">
									
									<!-- 第一张 -->
									<div class="mui-slider-item" >
											<div>
											<img src="${item.loadUrl}" style="width:100%;">
											<div style="position:absolute;top:0;left:0;width:200px;height:10px;z-index:100;">
												<button type="button" class="mui-btn mui-btn-danger" onclick="deleteImage(${item.materialId},'${item.loadUrl}');">删除该图片</button></p>
											</div>
											</div>
											
									</div>
									
									</c:forEach>
								</div>
								
							</div>
						</div>
						
			</li>
			<li class="mui-table-view-cell mui-collapse"><a
				class="mui-navigate-right" href="#">我的讨论(${fn:length(sessionScope.myspaceUserMine.activitiesMySpaceCommentMineList)})</a>
				<div class="mui-collapse-content">
				<ul class="mui-table-view mui-table-view-striped mui-table-view-condensed">
		          
		          <c:forEach items="${sessionScope.myspaceUserMine.activitiesMySpaceCommentMineList}" var="item">
		          <li id="li${item.commentId}" class="mui-table-view-cell mui-media">
		          	<div class="mui-slider-right mui-disabled">
						<button  onclick="DeleteComment(${item.commentId});" class="mui-btn mui-btn-red ">删除</button>
					</div>
		            <div class="mui-slider-handle">
		                <div class="mui-table-cell mui-col-xs-10">
		                    <h8 class="mui-ellipsis-3">${item.commentTxt}</h8>
		                    <h5>发表时间：<fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd　HH:mm"/></h5>
		                </div>
		                
		            </div>
		        </li>
		        
		        </c:forEach>
		        
		        </ul>
				</div></li>
		</ul>
	</div>
	</div>
	
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
	<script>
			mui.init();
			
			mui('#footerTab').on('tap','a',function(){
			    window.top.location.href=this.href;
			});

			//删除图片
			function deleteImage(materialId,loadUrl){
				
                mui.confirm('确认删除该图片？', '删除提示',new Array('是','否'), function(e) {
                    if (e.index == 0) {
                    
				mui.post('DeleteImage.action'
						,{materialId:materialId,loadUrl:loadUrl}
					,function(data){
					//服务器返回响应，根据响应结果，分析是否登录成功；...
					if(data.done=='0'){
						var dddd = dialog('删除成功').show();
						setTimeout(function () {
							dddd.close().remove();
			    		}, 1500);
						window.location.reload(true);
					}else{
						var ddd = dialog('删除失败').show();
						setTimeout(function () {
							ddd.close().remove();
			    		}, 1500);
					}
				},'json');
				
			    
                    } else {
                        return;
                    }
                });
				
			}
			
			//提交表单保存
			function submitUserForm(){
				mui.confirm('确认提交修改吗？', '提示',new Array('否','是'), function(e) {
                    if (e.index == 1) {
                    	var userRealName=$("#userRealName").val();
                    	var phoneNum=$("#phoneNum").val();
                    	var userAddr=$("#userAddr").val();
                    	var userEmail=$("#userEmail").val();
                    	var userIdNum=$("#userIdNum").val();
                    	var userGradeClass=$("#userGradeClass").val();
					if(userRealName==''||phoneNum==''||userAddr==''||userEmail==''||userIdNum==''||userGradeClass==''){alert("请将信息填写完整！");return;}
					//alert(222);
				    // You can do something, then submit form by native
				    // this.submit();
				    // or use ajax submit
				   mui.post("SaveUserData.action"
						   ,{userRealName:userRealName,phoneNum:phoneNum,userAddr:userAddr,userEmail:userEmail,userIdNum:userIdNum,userGradeClass:userGradeClass}
						   ,function(data){
				            // some code
				        	if(data.done=='0'){
				        		mui.toast('提交成功',{ duration:'2000', type:'div' });//停留2s
								window.location.reload(true);
							}else{
								mui.toast('提交失败',{ duration:'long', type:'div' });//停留3s
							}
				        },'json');
				
			    
                    } else {
                        return;
                    }
                });
			}
		</script>
</body>
</html>
