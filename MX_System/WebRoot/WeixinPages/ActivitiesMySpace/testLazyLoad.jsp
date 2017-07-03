<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

	<head>
		<meta charset="utf-8">
		<title>Hello MUI</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">

		<!--标准mui.css-->
		<link rel="stylesheet" href="<%=basePath%>WeixinPages/common/css/mui.min.css">
		<style type="text/css">
			p {
				text-indent: 22px;
			}
			.des {
				margin: .5em 0;
			}
			.des>li {
				font-size: 14px;
				color: #8f8f94;
			}
		</style>
	</head>

	<body>
		<header class="mui-bar mui-bar-nav">
			<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
			<h1 class="mui-title">lazyload（延迟加载） </h1>
		</header>
		<div class="mui-content">
			<div class="mui-content-padded">
				<p>延迟加载的理念：页面初始化时，暂不加载处于屏幕可见区域之外的图片。该方案会有如下几大好处：</p>
				<ul class="des">
					<li>加快页面渲染速度</li>
					<li>提升页面滚动性能</li>
					<li>默认不下载屏幕外的图片，减少网络流量</li>
				</ul>
			</div>
			<ul id="list" class="mui-table-view mui-table-view-chevron">
				<%for(int i=1;i<=32;i++){ %>
				<li class="mui-table-view-cell mui-media">
					<a class="mui-navigate-right">
						<img class="mui-media-object mui-pull-left" data-lazyload="/MX_System/WeixinPages/common/uploadImg/myspaceImg/100/o8iGuv66gILabgoSL3Ibz8euYiZk/<%=i %>.jpg">
					</a>
				</li>
				<%} %>
				<%for(int i=1;i<=32;i++){ %>
				<li class="mui-table-view-cell mui-media">
					<a class="mui-navigate-right">
						<img class="mui-media-object mui-pull-left" data-lazyload="/MX_System/WeixinPages/common/uploadImg/myspaceImg/100/o8iGuv66gILabgoSL3Ibz8euYiZk/<%=i %>.jpg">
					</a>
				</li>
				<%} %>
				<%for(int i=1;i<=32;i++){ %>
				<li class="mui-table-view-cell mui-media">
					<a class="mui-navigate-right">
						<img class="mui-media-object mui-pull-left" data-lazyload="/MX_System/WeixinPages/common/uploadImg/myspaceImg/100/o8iGuv66gILabgoSL3Ibz8euYiZk/<%=i %>.jpg">
					</a>
				</li>
				<%} %>
				<%for(int i=1;i<=32;i++){ %>
				<li class="mui-table-view-cell mui-media">
					<a class="mui-navigate-right">
						<img class="mui-media-object mui-pull-left" data-lazyload="/MX_System/WeixinPages/common/uploadImg/myspaceImg/100/o8iGuv66gILabgoSL3Ibz8euYiZk/<%=i %>.jpg">
					</a>
				</li>
				<%} %>
			</ul>
		</div>
	</body>
	<script src="<%=basePath%>WeixinPages/common/js/mui.min.js "></script>
	<script src="<%=basePath%>WeixinPages/common/js/mui.lazyload.js"></script>
	<script src="<%=basePath%>WeixinPages/common/js/mui.lazyload.img.js"></script>
	<script>
		mui.init();
		
		(function($) {
			
			$(document).imageLazyload({
				placeholder: '/MX_System/WeixinPages/common/images/60x60.gif'
			});
		})(mui);
	</script>

</html>
