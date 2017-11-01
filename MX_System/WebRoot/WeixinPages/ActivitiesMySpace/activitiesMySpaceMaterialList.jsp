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
		
		<!--App自定义的css-->
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
		</style>

	</head>

	<body>
	<header class="mui-bar mui-bar-nav" id="myspaceMainHeader"> 
		<a class="mui-btn mui-btn-blue mui-btn-link mui-pull-left" onclick="quitPage();">退出</a>
		<h1 id="title" class="mui-title">讨论区</h1>
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
		<c:forEach items="${userMySpaceMaterialList}" var="item1">
		
			<div class="mui-card">
				<div class="mui-card-header mui-card-media">
					<img data-lazyload="${item1.userData.weixinHeadUrl}" />
					<div class="mui-media-body">
						${item1.userData.userRealName}
						<p>更新于<fmt:formatDate value="${item1.userMySpaceMaterialList[0].createDate}" pattern="yyyy-MM-dd　HH:mm"/></p>
					</div>
				</div>
				<div class="mui-card-content" >
			<ul class="mui-table-view mui-grid-view" id="${item1.userData.userId}">
		      <c:forEach items="${item1.userMySpaceMaterialList}" var="item2">
			 
			  <li class="mui-table-view-cell mui-media mui-col-xs-3">
				<p>
					<img data-lazyload="${item2.loadUrl}" data-preview-src="" data-preview-group="${item1.userData.userId}" />
				</p>
				</li>
				
			  </c:forEach>
			  </ul>
				</div>
			</div>
			
			</c:forEach>
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
	<script>
		mui.previewImage();//设置图片预览
		
		mui.init();//mui初始化
		
		(function($) {//设置图片懒加载
			$(document).imageLazyload({
				placeholder: '/MX_System/WeixinPages/common/images/60x60.gif'
			});
		})(mui);
		
		mui('#footerTab').on('tap','a',function(){
		    window.top.location.href=this.href;
		});

		function operate(obj){
					var d = dialog({
						fixed: true,
						content: '<div id="showImage" class="rich">预览</div><div class="footer-btn " ><span ><i class="mui-icon mui-icon-image"></i>选图</span><input onchange="showImage(this);" class="input-file-css" type="file" capture="camera" accept="image/*" name="imgFile" id="imgFile"></div>',
						
						button : [ {
									value : '取消'
									},{
										value : '上传',
										callback : function() {
											//alert("11111");
											var imgFile = document.getElementById('imgFile').files[0];//获取选择的文件
											//alert(imgFile);
											var myspaceId=$('#myspaceId').val();
											var userId=$('#userId').val();//用户id
											doUploadImage(userId,myspaceId,imgFile);
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
		function doUploadImage(userId,myspaceId,imgFile){
			var d=dialog({content:'上传中...'}).showModal();//加载中弹出框
			//alert(1111);
	         lrz(imgFile, {width: 1080})
	            .then(function (rst) {
	            	//alert(222);
	                $.ajax({
	                    url: 'UploadImage.action',
	                    type: 'post',
	                    data: {img: rst.base64,userId:userId,myspaceId:myspaceId},
	                    dataType: 'json',
	                    timeout: 200000,
	                    success: function (response) {
	                        if (response.done == '0') {
	                        	d.content(response.msg);
	                        	setTimeout(function () {
	        		    			d.close().remove();
	        		    		}, 1500);
	                        	//刷新页面
	                        	document.getElementById('mainContent').src= "getActivitiesMySpaceMaterialList.action";
	                        	
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
