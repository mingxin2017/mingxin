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
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<!--标准mui.css-->
		<link rel="stylesheet" href="<%=basePath%>WeixinPages/common/css/mui.min.css"/>
		
		<!--上传图片的css-->

		
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
		
		<!--图片预览的css-->
		<style type="text/css">
			.mui-preview-image.mui-fullscreen {
				position: fixed;
				z-index: 20;
				background-color: #000;
			}
			.mui-preview-header,
			.mui-preview-footer {
				position: absolute;
				width: 100%;
				left: 0;
				z-index: 10;
			}
			.mui-preview-header {
				height: 44px;
				top: 0;
			}
			.mui-preview-footer {
				height: 50px;
				bottom: 0px;
			}
			.mui-preview-header .mui-preview-indicator {
				display: block;
				line-height: 25px;
				color: #fff;
				text-align: center;
				margin: 15px auto 4;
				width: 70px;
				background-color: rgba(0, 0, 0, 0.4);
				border-radius: 12px;
				font-size: 16px;
			}
			.mui-preview-image {
				display: none;
				-webkit-animation-duration: 0.5s;
				animation-duration: 0.5s;
				-webkit-animation-fill-mode: both;
				animation-fill-mode: both;
			}
			.mui-preview-image.mui-preview-in {
				-webkit-animation-name: fadeIn;
				animation-name: fadeIn;
			}
			.mui-preview-image.mui-preview-out {
				background: none;
				-webkit-animation-name: fadeOut;
				animation-name: fadeOut;
			}
			.mui-preview-image.mui-preview-out .mui-preview-header,
			.mui-preview-image.mui-preview-out .mui-preview-footer {
				display: none;
			}
			.mui-zoom-scroller {
				position: absolute;
				display: -webkit-box;
				display: -webkit-flex;
				display: flex;
				-webkit-box-align: center;
				-webkit-align-items: center;
				align-items: center;
				-webkit-box-pack: center;
				-webkit-justify-content: center;
				justify-content: center;
				left: 0;
				right: 0;
				bottom: 0;
				top: 0;
				width: 100%;
				height: 100%;
				margin: 0;
				-webkit-backface-visibility: hidden;
			}
			.mui-zoom {
				-webkit-transform-style: preserve-3d;
				transform-style: preserve-3d;
			}
			.mui-slider .mui-slider-group .mui-slider-item img {
				width: auto;
				height: auto;
				max-width: 100%;
				max-height: 100%;
			}
			.mui-android-4-1 .mui-slider .mui-slider-group .mui-slider-item img {
				width: 100%;
			}
			.mui-android-4-1 .mui-slider.mui-preview-image .mui-slider-group .mui-slider-item {
				display: inline-table;
			}
			.mui-android-4-1 .mui-slider.mui-preview-image .mui-zoom-scroller img {
				display: table-cell;
				vertical-align: middle;
			}
			.mui-preview-loading {
				position: absolute;
				width: 100%;
				height: 100%;
				top: 0;
				left: 0;
				display: none;
			}
			.mui-preview-loading.mui-active {
				display: block;
			}
			.mui-preview-loading .mui-spinner-white {
				position: absolute;
				top: 50%;
				left: 50%;
				margin-left: -25px;
				margin-top: -25px;
				height: 50px;
				width: 50px;
			}
			.mui-preview-image img.mui-transitioning {
				-webkit-transition: -webkit-transform 0.5s ease, opacity 0.5s ease;
				transition: transform 0.5s ease, opacity 0.5s ease;
			}
			@-webkit-keyframes fadeIn {
				0% {
					opacity: 0;
				}
				100% {
					opacity: 1;
				}
			}
			@keyframes fadeIn {
				0% {
					opacity: 0;
				}
				100% {
					opacity: 1;
				}
			}
			@-webkit-keyframes fadeOut {
				0% {
					opacity: 1;
				}
				100% {
					opacity: 0;
				}
			}
			@keyframes fadeOut {
				0% {
					opacity: 1;
				}
				100% {
					opacity: 0;
				}
			}
			p img {
				max-width: 100%;
				height: auto;
			}
			.mui-slider-img-content {
				position: absolute;
				bottom: 10px;
				left: 10px;
				right: 10px;
				color: red;
				text-align: center;
				line-height: 21px
			}
		</style>

	</head>

	<body>
	<header class="mui-bar mui-bar-nav" id="myspaceMainHeader"> 
		<a class="mui-btn mui-btn-blue mui-btn-link mui-pull-left" onclick="quitPage();">退出</a>
		<h1 id="title" class="mui-title">照片墙</h1>
		<a id="operate" class="mui-btn mui-btn-blue mui-btn-link mui-pull-right" onclick="operate(this);">上传照片</a>
	</header>
	<nav class="mui-bar mui-bar-tab" id="footerTab"> 
		<a  id="tab1" class="mui-tab-item" href="<%=basePath%>activitiesMySpace/getActivitiesMySpaceCommentList.action"  > 
			<span class="mui-icon mui-icon-chat"><!-- <span class="mui-badge">8</span> --></span> 
			<span class="mui-tab-label">讨论区</span>
		</a> 
		<a  id="tab2" class="mui-tab-item mui-active" href="<%=basePath%>activitiesMySpace/getActivitiesMySpaceMaterialList.action"> 
			<span class="mui-icon mui-icon-image">
			<!-- <span class="mui-badge">3</span> -->
			</span> 
			<span class="mui-tab-label">照片墙</span> 
		</a> 
		<a  id="tab3" class="mui-tab-item" href="<%=basePath%>activitiesMySpace/getActivitiesMySpaceUsersList.action" > 
			<span class="mui-icon mui-icon-contact"></span>
			<span class="mui-tab-label">通讯录</span>
		</a> 
		<a  id="tab4" class="mui-tab-item" href="<%=basePath%>activitiesMySpace/getActivitiesMySpaceMine.action" >
			<span class="mui-icon mui-icon-gear"></span> 
			<span class="mui-tab-label">个人空间</span> 
		</a> 
	</nav>
	
		<div class="mui-content">
		<%int imgNum=-1; %>
		<c:forEach items="${userMySpaceMaterialList}" var="item1">
		
			<div class="mui-card">
				<div class="mui-card-header mui-card-media">
					<img data-lazyload="${item1.userData.weixinHeadUrl}" />
					<div class="mui-media-body">
						${item1.userData.userRealName}
						<p>更新于<fmt:formatDate value="${item1.userMySpaceMaterialList[0].createDate}" pattern="yyyy-MM-dd　HH:mm"/></p>
					</div>
				</div>
				<c:if test="${item1.userData.userId eq sessionScope.userInfo.userId}">
					<%--<c:set var="id"  value="${param.id}"/>--%>
					<c:set var="len" value="${fn:length(item1.userMySpaceMaterialList)}" scope="request"></c:set>
					<%imgNum=(Integer)request.getAttribute("len");%>
				</c:if>
			<div class="mui-card-content" >
			<ul class="mui-table-view mui-grid-view" id="${item1.userData.userId}">
				
		
		      <c:forEach items="${item1.userMySpaceMaterialList}" var="item2">
			 	
			  <li class="mui-table-view-cell mui-media mui-col-xs-3">
				<p>
					<img data-lazyload="${item2.loadUrl}" data-preview-src="" data-preview-group="${item1.userData.userId}" data-content="${item2.describe}"/>
				</p>
				</li>
				
			  </c:forEach>
			  
			  
			  </ul>
				</div>
			</div>
			
			</c:forEach>
			<input id="imgNum" type="hidden" value="<%=imgNum%>"/>
		</div>
	</body>
	<script type="text/javascript" src="<%=basePath%>WeixinPages/common/js/jquery-1.11.2.js"></script>
	<script type="text/javascript" src="<%=basePath%>WeixinPages/common/js/dialog.js"></script>
	<script src="<%=basePath%>WeixinPages/common/imgDeal/dist/lrz.bundle.js?v=09bcc27"></script>
	<script src="<%=basePath%>WeixinPages/common/js/mui.min.js "></script>
	<script src="<%=basePath%>WeixinPages/common/js/mui.zoom.js"></script>
	<script src="<%=basePath%>WeixinPages/common/js/mui.previewimage.js"></script>
	<script src="<%=basePath%>WeixinPages/common/js/mui.lazyload.js"></script>
	<script src="<%=basePath%>WeixinPages/common/js/mui.lazyload.img.js"></script>
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
	
	
		mui.previewImage();//设置图片预览
		
		mui.init();//mui初始化
		
		(function($) {//设置图片懒加载
			$(document).imageLazyload({
				placeholder: '/MX_System/WeixinPages/common/images/60x60.gif'
			});
		})(mui);
		
		
		mui('#footerTab').on('tap','a',function(){
			var current = document.querySelector(".mui-tab-item.mui-active");
			if (this !== current) {
				var dl=dialog('加载中...').showModal();
				current.classList.remove('mui-active');
				this.classList.add('mui-active');
				window.top.location.href=this.href;
			}
		});
		

		function operate(obj){
			var imgNum=parseInt($('#imgNum').val());
			if(imgNum!=-1&&imgNum>=12){
				var ddd = dialog('您已上传12张照片，无法上传更多！').showModal();
				setTimeout(function () {
					ddd.close().remove();
	    		}, 1500);
				return;
			}
					var d = dialog({
						fixed: true,
						content: '<div id="showImage" class="rich">预览</div><div class="footer-btn " ><span ><i class="mui-icon mui-icon-image"></i>选图</span><input onchange="showImage(this);" class="input-file-css" type="file" accept="image/*" name="imgFile" id="imgFile"></div>',
						
						button : [ {
									value : '取消'
									},{
										value : '上传',
										callback : function() {
											var imgFile = document.getElementById('imgFile').files[0];//获取选择的文件
											doUploadImage(imgFile);
										},
										autofocus : true
									} ]

								}).showModal();
				

			}
		
		//上传图片显示缩略图
		function showImage(obj){
			//alert(obj.files[0]);
			var file=obj.files[0];
			var img = new Image();
			img.width=80;
			var url = img.src = URL.createObjectURL(file);
			var $img = $(img);
	        img.onload = function() {
	            URL.revokeObjectURL(url);
	            $('#showImage').empty().append($img);
	        }
		}


		//上传图片事件
		function doUploadImage(imgFile){
			var d=dialog({content:'上传中...'}).showModal();//加载中弹出框
	         lrz(imgFile, {height:1080,width:1080})//压缩图片
	            .then(function (rst) {
	                $.ajax({
	                    url: 'UploadImage.action',
	                    type: 'post',
	                    data: {img: rst.base64},
	                    dataType: 'json',
	                    timeout: 200000,
	                    success: function (response) {
	                        if (response.done == '0') {
	                        	d.content(response.msg);
	                        	setTimeout(function () {
	        		    			d.close().remove();
	        		    		}, 1500);
	                        	//刷新页面
	                        	window.location.reload(true);
	                            return true;
	                        } else {
	                        	d.content(response.msg);
	                        	setTimeout(function () {
	        		    			d.close().remove();
	        		    		}, 1500);
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

	            })
	            .catch(function (err) {

	            })
	            .always(function () {

	            });
		}
		
	</script>

</html>
