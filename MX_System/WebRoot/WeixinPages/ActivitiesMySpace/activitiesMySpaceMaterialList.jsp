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
<meta name="viewport"
	content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<!--标准mui.css-->
<link rel="stylesheet"
	href="<%=basePath%>WeixinPages/common/css/mui.min.css">
<!--App自定义的css-->
<style type="text/css">
.mui-preview-image.mui-fullscreen {
	position: fixed;
	z-index: 20;
	background-color: #000;
}

.mui-preview-header,.mui-preview-footer {
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

.mui-preview-image.mui-preview-out .mui-preview-header,.mui-preview-image.mui-preview-out .mui-preview-footer
	{
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

.mui-android-4-1 .mui-slider.mui-preview-image .mui-slider-group .mui-slider-item
	{
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

@
-webkit-keyframes fadeIn { 0% {
	opacity: 0;
}

100%
{
opacity
:
 
1;
}
}
@
keyframes fadeIn { 0% {
	opacity: 0;
}

100%
{
opacity
:
 
1;
}
}
@
-webkit-keyframes fadeOut { 0% {
	opacity: 1;
}

100%
{
opacity
:
 
0;
}
}
@
keyframes fadeOut { 0% {
	opacity: 1;
}

100%
{
opacity
:
 
0;
}
}
p img {
	max-width: 100%;
	height: auto;
}
</style>

</head>


<body>



	<div class="mui-content">

		<%for(int i=0;i<4;i++){ %>

		<div class="mui-card">
			<div class="mui-card-header mui-card-media">
				<img
					src="http://wx.qlogo.cn/mmopen/gITwFOywPbkCx8BxwYc41oAGjuBeFianAbtHl8URmaCMTe9lib6EicNuHSibGJzSfT6Y88Nos1poHITnB7vUs7foHphNpibcgFEja/0" />
				<div class="mui-media-body">
					小M
					<p>更新于 2016-06-30 15:30</p>
				</div>
			</div>
			<div class="mui-content-padded">
				<ul class="mui-table-view mui-grid-view">
					<li class="mui-table-view-cell mui-media mui-col-xs-3">
						<p>
							<img
								src="/MX_System/WeixinPages/uploadImg/myspaceImg/100/o8iGuv66gILabgoSL3Ibz8euYiZk/<%=i*8+1 %>.jpg"
								data-preview-src="" data-preview-group="<%=i %>" />
						</p></li>
					<li class="mui-table-view-cell mui-media mui-col-xs-3">
						<p>
							<img
								src="/MX_System/WeixinPages/uploadImg/myspaceImg/100/o8iGuv66gILabgoSL3Ibz8euYiZk/<%=i*8+2 %>.jpg"
								data-preview-src="" data-preview-group="<%=i %>" />
						</p></li>
					<li class="mui-table-view-cell mui-media mui-col-xs-3">
						<p>
							<img
								src="/MX_System/WeixinPages/uploadImg/myspaceImg/100/o8iGuv66gILabgoSL3Ibz8euYiZk/<%=i*8+3 %>.jpg"
								data-preview-src="" data-preview-group="<%=i %>" />
						</p></li>
					<li class="mui-table-view-cell mui-media mui-col-xs-3">
						<p>
							<img
								src="/MX_System/WeixinPages/uploadImg/myspaceImg/100/o8iGuv66gILabgoSL3Ibz8euYiZk/<%=i*8+4 %>.jpg"
								data-preview-src="" data-preview-group="<%=i %>" />
						</p></li>
					<li class="mui-table-view-cell mui-media mui-col-xs-3">
						<p>
							<img
								src="/MX_System/WeixinPages/uploadImg/myspaceImg/100/o8iGuv66gILabgoSL3Ibz8euYiZk/<%=i*8+5 %>.jpg"
								data-preview-src="" data-preview-group="<%=i %>" />
						</p></li>
					<li class="mui-table-view-cell mui-media mui-col-xs-3">
						<p>
							<img
								src="/MX_System/WeixinPages/uploadImg/myspaceImg/100/o8iGuv66gILabgoSL3Ibz8euYiZk/<%=i*8+6 %>.jpg"
								data-preview-src="" data-preview-group="<%=i %>" />
						</p></li>
					<li class="mui-table-view-cell mui-media mui-col-xs-3">
						<p>
							<img
								src="/MX_System/WeixinPages/uploadImg/myspaceImg/100/o8iGuv66gILabgoSL3Ibz8euYiZk/<%=i*8+7 %>.jpg"
								data-preview-src="" data-preview-group="<%=i %>" />
						</p></li>
					<li class="mui-table-view-cell mui-media mui-col-xs-3">
						<p>
							<img
								src="/MX_System/WeixinPages/uploadImg/myspaceImg/100/o8iGuv66gILabgoSL3Ibz8euYiZk/<%=i*8+8 %>.jpg"
								data-preview-src="" data-preview-group="<%=i %>" />
						</p></li>
				</ul>
			</div>
		</div>
		<%} %>
		<%for(int i=0;i<4;i++){ %>

		<div class="mui-card">
			<div class="mui-card-header mui-card-media">
				<img
					src="http://wx.qlogo.cn/mmopen/gITwFOywPbkCx8BxwYc41oAGjuBeFianAbtHl8URmaCMTe9lib6EicNuHSibGJzSfT6Y88Nos1poHITnB7vUs7foHphNpibcgFEja/0" />
				<div class="mui-media-body">
					小M
					<p>更新于 2016-06-30 15:30</p>
				</div>
			</div>
			<div class="mui-content-padded">
				<ul class="mui-table-view mui-grid-view">
					<li class="mui-table-view-cell mui-media mui-col-xs-3">
						<p>
							<img
								data-lazyload="/MX_System/WeixinPages/uploadImg/myspaceImg/100/o8iGuv66gILabgoSL3Ibz8euYiZk/<%=i*8+1 %>.jpg"
								data-preview-src="" data-preview-group="<%=i %>" />
						</p></li>
					<li class="mui-table-view-cell mui-media mui-col-xs-3">
						<p>
							<img
								data-lazyload="/MX_System/WeixinPages/uploadImg/myspaceImg/100/o8iGuv66gILabgoSL3Ibz8euYiZk/<%=i*8+2 %>.jpg"
								data-preview-src="" data-preview-group="<%=i %>" />
						</p></li>
					<li class="mui-table-view-cell mui-media mui-col-xs-3">
						<p>
							<img
								data-lazyload="/MX_System/WeixinPages/uploadImg/myspaceImg/100/o8iGuv66gILabgoSL3Ibz8euYiZk/<%=i*8+3 %>.jpg"
								data-preview-src="" data-preview-group="<%=i %>" />
						</p></li>
					<li class="mui-table-view-cell mui-media mui-col-xs-3">
						<p>
							<img
								data-lazyload="/MX_System/WeixinPages/uploadImg/myspaceImg/100/o8iGuv66gILabgoSL3Ibz8euYiZk/<%=i*8+4 %>.jpg"
								data-preview-src="" data-preview-group="<%=i %>" />
						</p></li>
					<li class="mui-table-view-cell mui-media mui-col-xs-3">
						<p>
							<img
								data-lazyload="/MX_System/WeixinPages/uploadImg/myspaceImg/100/o8iGuv66gILabgoSL3Ibz8euYiZk/<%=i*8+5 %>.jpg"
								data-preview-src="" data-preview-group="<%=i %>" />
						</p></li>
					<li class="mui-table-view-cell mui-media mui-col-xs-3">
						<p>
							<img
								data-lazyload="/MX_System/WeixinPages/uploadImg/myspaceImg/100/o8iGuv66gILabgoSL3Ibz8euYiZk/<%=i*8+6 %>.jpg"
								data-preview-src="" data-preview-group="<%=i %>" />
						</p></li>
					<li class="mui-table-view-cell mui-media mui-col-xs-3">
						<p>
							<img
								data-lazyload="/MX_System/WeixinPages/uploadImg/myspaceImg/100/o8iGuv66gILabgoSL3Ibz8euYiZk/<%=i*8+7 %>.jpg"
								data-preview-src="" data-preview-group="<%=i %>" />
						</p></li>
					<li class="mui-table-view-cell mui-media mui-col-xs-3">
						<p>
							<img
								data-lazyload="/MX_System/WeixinPages/uploadImg/myspaceImg/100/o8iGuv66gILabgoSL3Ibz8euYiZk/<%=i*8+8 %>.jpg"
								data-preview-src="" data-preview-group="<%=i %>" />
						</p></li>
				</ul>
			</div>
		</div>
		<%} %>
		<%for(int i=0;i<4;i++){ %>
		<div class="mui-card">
			<div class="mui-card-header mui-card-media">
				<img
					src="http://wx.qlogo.cn/mmopen/gITwFOywPbkCx8BxwYc41oAGjuBeFianAbtHl8URmaCMTe9lib6EicNuHSibGJzSfT6Y88Nos1poHITnB7vUs7foHphNpibcgFEja/0" />
				<div class="mui-media-body">
					小M
					<p>更新于 2016-06-30 15:30</p>
				</div>
			</div>
			<div class="mui-content-padded">
				<ul class="mui-table-view mui-grid-view">
					<li class="mui-table-view-cell mui-media mui-col-xs-3">
						<p>
							<img
								data-lazyload="/MX_System/WeixinPages/uploadImg/myspaceImg/100/o8iGuv66gILabgoSL3Ibz8euYiZk/<%=i*8+1 %>.jpg"
								data-preview-src="" data-preview-group="<%=i %>" />
						</p></li>
					<li class="mui-table-view-cell mui-media mui-col-xs-3">
						<p>
							<img
								data-lazyload="/MX_System/WeixinPages/uploadImg/myspaceImg/100/o8iGuv66gILabgoSL3Ibz8euYiZk/<%=i*8+2 %>.jpg"
								data-preview-src="" data-preview-group="<%=i %>" />
						</p></li>
					<li class="mui-table-view-cell mui-media mui-col-xs-3">
						<p>
							<img
								data-lazyload="/MX_System/WeixinPages/uploadImg/myspaceImg/100/o8iGuv66gILabgoSL3Ibz8euYiZk/<%=i*8+3 %>.jpg"
								data-preview-src="" data-preview-group="<%=i %>" />
						</p></li>
					<li class="mui-table-view-cell mui-media mui-col-xs-3">
						<p>
							<img
								data-lazyload="/MX_System/WeixinPages/uploadImg/myspaceImg/100/o8iGuv66gILabgoSL3Ibz8euYiZk/<%=i*8+4 %>.jpg"
								data-preview-src="" data-preview-group="<%=i %>" />
						</p></li>
					<li class="mui-table-view-cell mui-media mui-col-xs-3">
						<p>
							<img
								data-lazyload="/MX_System/WeixinPages/uploadImg/myspaceImg/100/o8iGuv66gILabgoSL3Ibz8euYiZk/<%=i*8+5 %>.jpg"
								data-preview-src="" data-preview-group="<%=i %>" />
						</p></li>
					<li class="mui-table-view-cell mui-media mui-col-xs-3">
						<p>
							<img
								data-lazyload="/MX_System/WeixinPages/uploadImg/myspaceImg/100/o8iGuv66gILabgoSL3Ibz8euYiZk/<%=i*8+6 %>.jpg"
								data-preview-src="" data-preview-group="<%=i %>" />
						</p></li>
					<li class="mui-table-view-cell mui-media mui-col-xs-3">
						<p>
							<img
								data-lazyload="/MX_System/WeixinPages/uploadImg/myspaceImg/100/o8iGuv66gILabgoSL3Ibz8euYiZk/<%=i*8+7 %>.jpg"
								data-preview-src="" data-preview-group="<%=i %>" />
						</p></li>
					<li class="mui-table-view-cell mui-media mui-col-xs-3">
						<p>
							<img
								data-lazyload="/MX_System/WeixinPages/uploadImg/myspaceImg/100/o8iGuv66gILabgoSL3Ibz8euYiZk/<%=i*8+8 %>.jpg"
								data-preview-src="" data-preview-group="<%=i %>" />
						</p></li>
				</ul>
			</div>
		</div>
		<%} %>
		<%for(int i=0;i<4;i++){ %>
		<div class="mui-card">
			<div class="mui-card-header mui-card-media">
				<img
					src="http://wx.qlogo.cn/mmopen/gITwFOywPbkCx8BxwYc41oAGjuBeFianAbtHl8URmaCMTe9lib6EicNuHSibGJzSfT6Y88Nos1poHITnB7vUs7foHphNpibcgFEja/0" />
				<div class="mui-media-body">
					小M
					<p>更新于 2016-06-30 15:30</p>
				</div>
			</div>
			<div class="mui-content-padded">
				<ul class="mui-table-view mui-grid-view">
					<li class="mui-table-view-cell mui-media mui-col-xs-3">
						<p>
							<img
								data-lazyload="/MX_System/WeixinPages/uploadImg/myspaceImg/100/o8iGuv66gILabgoSL3Ibz8euYiZk/<%=i*8+1 %>.jpg"
								data-preview-src="" data-preview-group="<%=i %>" />
						</p></li>
					<li class="mui-table-view-cell mui-media mui-col-xs-3">
						<p>
							<img
								data-lazyload="/MX_System/WeixinPages/uploadImg/myspaceImg/100/o8iGuv66gILabgoSL3Ibz8euYiZk/<%=i*8+2 %>.jpg"
								data-preview-src="" data-preview-group="<%=i %>" />
						</p></li>
					<li class="mui-table-view-cell mui-media mui-col-xs-3">
						<p>
							<img
								data-lazyload="/MX_System/WeixinPages/uploadImg/myspaceImg/100/o8iGuv66gILabgoSL3Ibz8euYiZk/<%=i*8+3 %>.jpg"
								data-preview-src="" data-preview-group="<%=i %>" />
						</p></li>
					<li class="mui-table-view-cell mui-media mui-col-xs-3">
						<p>
							<img
								data-lazyload="/MX_System/WeixinPages/uploadImg/myspaceImg/100/o8iGuv66gILabgoSL3Ibz8euYiZk/<%=i*8+4 %>.jpg"
								data-preview-src="" data-preview-group="<%=i %>" />
						</p></li>
					<li class="mui-table-view-cell mui-media mui-col-xs-3">
						<p>
							<img
								data-lazyload="/MX_System/WeixinPages/uploadImg/myspaceImg/100/o8iGuv66gILabgoSL3Ibz8euYiZk/<%=i*8+5 %>.jpg"
								data-preview-src="" data-preview-group="<%=i %>" />
						</p></li>
					<li class="mui-table-view-cell mui-media mui-col-xs-3">
						<p>
							<img
								data-lazyload="/MX_System/WeixinPages/uploadImg/myspaceImg/100/o8iGuv66gILabgoSL3Ibz8euYiZk/<%=i*8+6 %>.jpg"
								data-preview-src="" data-preview-group="<%=i %>" />
						</p></li>
					<li class="mui-table-view-cell mui-media mui-col-xs-3">
						<p>
							<img
								data-lazyload="/MX_System/WeixinPages/uploadImg/myspaceImg/100/o8iGuv66gILabgoSL3Ibz8euYiZk/<%=i*8+7 %>.jpg"
								data-preview-src="" data-preview-group="<%=i %>" />
						</p></li>
					<li class="mui-table-view-cell mui-media mui-col-xs-3">
						<p>
							<img
								data-lazyload="/MX_System/WeixinPages/uploadImg/myspaceImg/100/o8iGuv66gILabgoSL3Ibz8euYiZk/<%=i*8+8 %>.jpg"
								data-preview-src="" data-preview-group="<%=i %>" />
						</p></li>
				</ul>
			</div>
		</div>
		<%} %>
		<%for(int i=0;i<4;i++){ %>
		<div class="mui-card">
			<div class="mui-card-header mui-card-media">
				<img
					src="http://wx.qlogo.cn/mmopen/gITwFOywPbkCx8BxwYc41oAGjuBeFianAbtHl8URmaCMTe9lib6EicNuHSibGJzSfT6Y88Nos1poHITnB7vUs7foHphNpibcgFEja/0" />
				<div class="mui-media-body">
					小M
					<p>更新于 2016-06-30 15:30</p>
				</div>
			</div>
			<div class="mui-content-padded">
				<ul class="mui-table-view mui-grid-view">
					<li class="mui-table-view-cell mui-media mui-col-xs-3">
						<p>
							<img
								data-lazyload="/MX_System/WeixinPages/uploadImg/myspaceImg/100/o8iGuv66gILabgoSL3Ibz8euYiZk/<%=i*8+1 %>.jpg"
								data-preview-src="" data-preview-group="<%=i %>" />
						</p></li>
					<li class="mui-table-view-cell mui-media mui-col-xs-3">
						<p>
							<img
								data-lazyload="/MX_System/WeixinPages/uploadImg/myspaceImg/100/o8iGuv66gILabgoSL3Ibz8euYiZk/<%=i*8+2 %>.jpg"
								data-preview-src="" data-preview-group="<%=i %>" />
						</p></li>
					<li class="mui-table-view-cell mui-media mui-col-xs-3">
						<p>
							<img
								data-lazyload="/MX_System/WeixinPages/uploadImg/myspaceImg/100/o8iGuv66gILabgoSL3Ibz8euYiZk/<%=i*8+3 %>.jpg"
								data-preview-src="" data-preview-group="<%=i %>" />
						</p></li>
					<li class="mui-table-view-cell mui-media mui-col-xs-3">
						<p>
							<img
								data-lazyload="/MX_System/WeixinPages/uploadImg/myspaceImg/100/o8iGuv66gILabgoSL3Ibz8euYiZk/<%=i*8+4 %>.jpg"
								data-preview-src="" data-preview-group="<%=i %>" />
						</p></li>
					<li class="mui-table-view-cell mui-media mui-col-xs-3">
						<p>
							<img
								data-lazyload="/MX_System/WeixinPages/uploadImg/myspaceImg/100/o8iGuv66gILabgoSL3Ibz8euYiZk/<%=i*8+5 %>.jpg"
								data-preview-src="" data-preview-group="<%=i %>" />
						</p></li>
					<li class="mui-table-view-cell mui-media mui-col-xs-3">
						<p>
							<img
								data-lazyload="/MX_System/WeixinPages/uploadImg/myspaceImg/100/o8iGuv66gILabgoSL3Ibz8euYiZk/<%=i*8+6 %>.jpg"
								data-preview-src="" data-preview-group="<%=i %>" />
						</p></li>
					<li class="mui-table-view-cell mui-media mui-col-xs-3">
						<p>
							<img
								data-lazyload="/MX_System/WeixinPages/uploadImg/myspaceImg/100/o8iGuv66gILabgoSL3Ibz8euYiZk/<%=i*8+7 %>.jpg"
								data-preview-src="" data-preview-group="<%=i %>" />
						</p></li>
					<li class="mui-table-view-cell mui-media mui-col-xs-3">
						<p>
							<img
								data-lazyload="/MX_System/WeixinPages/uploadImg/myspaceImg/100/o8iGuv66gILabgoSL3Ibz8euYiZk/<%=i*8+8 %>.jpg"
								data-preview-src="" data-preview-group="<%=i %>" />
						</p></li>
				</ul>
			</div>
		</div>
		<%} %>
		<%for(int i=0;i<4;i++){ %>
		<div class="mui-card">
			<div class="mui-card-header mui-card-media">
				<img
					src="http://wx.qlogo.cn/mmopen/gITwFOywPbkCx8BxwYc41oAGjuBeFianAbtHl8URmaCMTe9lib6EicNuHSibGJzSfT6Y88Nos1poHITnB7vUs7foHphNpibcgFEja/0" />
				<div class="mui-media-body">
					小M
					<p>更新于 2016-06-30 15:30</p>
				</div>
			</div>
			<div class="mui-content-padded">
				<ul class="mui-table-view mui-grid-view">
					<li class="mui-table-view-cell mui-media mui-col-xs-3">
						<p>
							<img
								data-lazyload="/MX_System/WeixinPages/uploadImg/myspaceImg/100/o8iGuv66gILabgoSL3Ibz8euYiZk/<%=i*8+1 %>.jpg"
								data-preview-src="" data-preview-group="<%=i %>" />
						</p></li>
					<li class="mui-table-view-cell mui-media mui-col-xs-3">
						<p>
							<img
								data-lazyload="/MX_System/WeixinPages/uploadImg/myspaceImg/100/o8iGuv66gILabgoSL3Ibz8euYiZk/<%=i*8+2 %>.jpg"
								data-preview-src="" data-preview-group="<%=i %>" />
						</p></li>
					<li class="mui-table-view-cell mui-media mui-col-xs-3">
						<p>
							<img
								data-lazyload="/MX_System/WeixinPages/uploadImg/myspaceImg/100/o8iGuv66gILabgoSL3Ibz8euYiZk/<%=i*8+3 %>.jpg"
								data-preview-src="" data-preview-group="<%=i %>" />
						</p></li>
					<li class="mui-table-view-cell mui-media mui-col-xs-3">
						<p>
							<img
								data-lazyload="/MX_System/WeixinPages/uploadImg/myspaceImg/100/o8iGuv66gILabgoSL3Ibz8euYiZk/<%=i*8+4 %>.jpg"
								data-preview-src="" data-preview-group="<%=i %>" />
						</p></li>
					<li class="mui-table-view-cell mui-media mui-col-xs-3">
						<p>
							<img
								data-lazyload="/MX_System/WeixinPages/uploadImg/myspaceImg/100/o8iGuv66gILabgoSL3Ibz8euYiZk/<%=i*8+5 %>.jpg"
								data-preview-src="" data-preview-group="<%=i %>" />
						</p></li>
					<li class="mui-table-view-cell mui-media mui-col-xs-3">
						<p>
							<img
								data-lazyload="/MX_System/WeixinPages/uploadImg/myspaceImg/100/o8iGuv66gILabgoSL3Ibz8euYiZk/<%=i*8+6 %>.jpg"
								data-preview-src="" data-preview-group="<%=i %>" />
						</p></li>
					<li class="mui-table-view-cell mui-media mui-col-xs-3">
						<p>
							<img
								data-lazyload="/MX_System/WeixinPages/uploadImg/myspaceImg/100/o8iGuv66gILabgoSL3Ibz8euYiZk/<%=i*8+7 %>.jpg"
								data-preview-src="" data-preview-group="<%=i %>" />
						</p></li>
					<li class="mui-table-view-cell mui-media mui-col-xs-3">
						<p>
							<img
								data-lazyload="/MX_System/WeixinPages/uploadImg/myspaceImg/100/o8iGuv66gILabgoSL3Ibz8euYiZk/<%=i*8+8 %>.jpg"
								data-preview-src="" data-preview-group="<%=i %>" />
						</p></li>
				</ul>
			</div>
		</div>
		<%} %>
		<%for(int i=0;i<4;i++){ %>
		<div class="mui-card">
			<div class="mui-card-header mui-card-media">
				<img
					src="http://wx.qlogo.cn/mmopen/gITwFOywPbkCx8BxwYc41oAGjuBeFianAbtHl8URmaCMTe9lib6EicNuHSibGJzSfT6Y88Nos1poHITnB7vUs7foHphNpibcgFEja/0" />
				<div class="mui-media-body">
					小M
					<p>更新于 2016-06-30 15:30</p>
				</div>
			</div>
			<div class="mui-content-padded">
				<ul class="mui-table-view mui-grid-view">
					<li class="mui-table-view-cell mui-media mui-col-xs-3">
						<p>
							<img
								data-lazyload="/MX_System/WeixinPages/uploadImg/myspaceImg/100/o8iGuv66gILabgoSL3Ibz8euYiZk/<%=i*8+1 %>.jpg"
								data-preview-src="" data-preview-group="<%=i %>" />
						</p></li>
					<li class="mui-table-view-cell mui-media mui-col-xs-3">
						<p>
							<img
								data-lazyload="/MX_System/WeixinPages/uploadImg/myspaceImg/100/o8iGuv66gILabgoSL3Ibz8euYiZk/<%=i*8+2 %>.jpg"
								data-preview-src="" data-preview-group="<%=i %>" />
						</p></li>
					<li class="mui-table-view-cell mui-media mui-col-xs-3">
						<p>
							<img
								data-lazyload="/MX_System/WeixinPages/uploadImg/myspaceImg/100/o8iGuv66gILabgoSL3Ibz8euYiZk/<%=i*8+3 %>.jpg"
								data-preview-src="" data-preview-group="<%=i %>" />
						</p></li>
					<li class="mui-table-view-cell mui-media mui-col-xs-3">
						<p>
							<img
								data-lazyload="/MX_System/WeixinPages/uploadImg/myspaceImg/100/o8iGuv66gILabgoSL3Ibz8euYiZk/<%=i*8+4 %>.jpg"
								data-preview-src="" data-preview-group="<%=i %>" />
						</p></li>
					<li class="mui-table-view-cell mui-media mui-col-xs-3">
						<p>
							<img
								data-lazyload="/MX_System/WeixinPages/uploadImg/myspaceImg/100/o8iGuv66gILabgoSL3Ibz8euYiZk/<%=i*8+5 %>.jpg"
								data-preview-src="" data-preview-group="<%=i %>" />
						</p></li>
					<li class="mui-table-view-cell mui-media mui-col-xs-3">
						<p>
							<img
								data-lazyload="/MX_System/WeixinPages/uploadImg/myspaceImg/100/o8iGuv66gILabgoSL3Ibz8euYiZk/<%=i*8+6 %>.jpg"
								data-preview-src="" data-preview-group="<%=i %>" />
						</p></li>
					<li class="mui-table-view-cell mui-media mui-col-xs-3">
						<p>
							<img
								data-lazyload="/MX_System/WeixinPages/uploadImg/myspaceImg/100/o8iGuv66gILabgoSL3Ibz8euYiZk/<%=i*8+7 %>.jpg"
								data-preview-src="" data-preview-group="<%=i %>" />
						</p></li>
					<li class="mui-table-view-cell mui-media mui-col-xs-3">
						<p>
							<img
								data-lazyload="/MX_System/WeixinPages/uploadImg/myspaceImg/100/o8iGuv66gILabgoSL3Ibz8euYiZk/<%=i*8+8 %>.jpg"
								data-preview-src="" data-preview-group="<%=i %>" />
						</p></li>
				</ul>
			</div>
		</div>
		<%} %>
	</div>
</body>

<script src="<%=basePath%>WeixinPages/common/js/mui.min.js"></script>
<script src="<%=basePath%>WeixinPages/common/js/mui.zoom.js"></script>
<script src="<%=basePath%>WeixinPages/common/js/mui.previewimage.js"></script>
<script src="<%=basePath%>WeixinPages/common/js/mui.lazyload.js"></script>
<script src="<%=basePath%>WeixinPages/common/js/mui.lazyload.img.js"></script>
<script>
	mui.init();
	mui.previewImage();
	(function($) {
		
		$(document).imageLazyload({
			placeholder: '/MX_System/WeixinPages/common/images/60x60.gif'
		});
	})(mui);
	</script>

</html>
