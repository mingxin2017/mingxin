<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.mx.weixin.pojo.SNSUserInfo,java.lang.*"%>
<%String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";%>
<head>
<base href="<%=basePath%>">
<title>小记者管理页面</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<!--标准mui.css-->
		<link rel="stylesheet" href="<%=basePath%>WeixinPages/common/css/mui.min.css">
		<!--App自定义的css-->
		<link rel="stylesheet" type="text/css" href="<%=basePath%>WeixinPages/common/css/app.css"/>
		<style>
			
			.title{
				margin: 20px 15px 10px;
				color: #6d6d72;
				font-size: 15px;
			}
			
			.oa-contact-cell.mui-table .mui-table-cell {
				padding: 11px 0;
				vertical-align: middle;
			}
			
			.oa-contact-cell {
				position: relative;
				margin: -11px 0;
			}
	
			.oa-contact-avatar {
				width: 75px;
			}
			.oa-contact-avatar img {
				border-radius: 50%;
			}
			.oa-contact-content {
				width: 100%;
			}
			.oa-contact-name {
				margin-right: 20px;
			}
			.oa-contact-name, oa-contact-position {
				float: left;
			}
		</style>
		
		<style>
			html,
			body {
				background-color: #efeff4;
			}
			.mui-scroll {
				background-color: #efeff4;
			}
			.mui-page-left {
				-webkit-transform: translate3d(0, 0, 0);
				transform: translate3d(0, 0, 0);
			}
			.mui-ios .mui-page-left {
				-webkit-transform: translate3d(-20%, 0, 0);
				transform: translate3d(-20%, 0, 0);
			}
			.mui-navbar {
				position: fixed;
				right: 0;
				left: 0;
				z-index: 10;
				height: 44px;
				background-color: #f7f7f8;
			}
			.mui-navbar .mui-bar {
				position: absolute;
				background: transparent;
				text-align: center;
			}
			.mui-android .mui-navbar-inner.mui-navbar-left {
				opacity: 0;
			}
			.mui-ios .mui-navbar-left .mui-left,
			.mui-ios .mui-navbar-left .mui-center,
			.mui-ios .mui-navbar-left .mui-right {
				opacity: 0;
			}
			.mui-navbar .mui-btn-nav {
				-webkit-transition: none;
				transition: none;
				-webkit-transition-duration: .0s;
				transition-duration: .0s;
			}
			.mui-navbar .mui-bar .mui-title {
				display: inline-block;
				width: auto;
			}
			.mui-page-shadow {
				position: absolute;
				right: 100%;
				top: 0;
				width: 16px;
				height: 100%;
				z-index: -1;
				content: '';
			}
			.mui-page-shadow {
				background: -webkit-linear-gradient(left, rgba(0, 0, 0, 0) 0, rgba(0, 0, 0, 0) 10%, rgba(0, 0, 0, .01) 50%, rgba(0, 0, 0, .2) 100%);
				background: linear-gradient(to right, rgba(0, 0, 0, 0) 0, rgba(0, 0, 0, 0) 10%, rgba(0, 0, 0, .01) 50%, rgba(0, 0, 0, .2) 100%);
			}
			.mui-navbar-inner.mui-transitioning,
			.mui-navbar-inner .mui-transitioning {
				-webkit-transition: opacity 300ms ease, -webkit-transform 300ms ease;
				transition: opacity 300ms ease, transform 300ms ease;
			}
/* 			.mui-page {
				display: none;
			} */
/* 	 		.mui-pages .mui-page {
				display: block;
			}  */
			.mui-page .mui-table-view:first-child {
				margin-top: 15px;
			}
			.mui-page .mui-table-view:last-child {
				margin-bottom: 30px;
			}
			.mui-table-view {
				margin-top: 20px;
			}
			
			.mui-table-view span.mui-pull-right {
				color: #999;
			}
			.mui-table-view-divider {
				background-color: #efeff4;
				font-size: 14px;
			}
			.mui-table-view-divider:before,
			.mui-table-view-divider:after {
				height: 0;
			}
			.head {
				height: 40px;
			}
			#head {
				line-height: 40px;
			}
			.head-img {
				width: 40px;
				height: 40px;
			}
			#head-img1 {
				position: absolute;
				bottom: 10px;
				right: 40px;
				width: 40px;
				height: 40px;
			}
			.update {
				font-style: normal;
				color: #999999;
				margin-right: -25px;
				font-size: 15px
			}
			.mui-fullscreen {
				position: fixed;
				z-index: 20;
				background-color: #000;
			}
			.mui-ios .mui-navbar .mui-bar .mui-title {
				position: static;
			}
			/*问题反馈在setting页面单独的css*/
			#feedback .mui-popover{
				position: fixed;
			}
			#feedback .mui-table-view:last-child {
			    margin-bottom: 0px;
			}
			#feedback .mui-table-view:first-child {
			    margin-top: 0px;
			}
			/*问题反馈在setting页面单独的css==end*/

		</style>


		<link rel="stylesheet" type="text/css" href="<%=basePath%>WeixinPages/common/css/feedback.css" />
</head>
<body>
<!-- 		<header class="mui-bar mui-bar-nav">
			<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
			<h1 class="mui-title">底部选项卡-div模式</h1>
		</header> -->
		<nav class="mui-bar mui-bar-tab">
			<a class="mui-tab-item mui-active" href="#tabbar">
				<span class="mui-icon mui-icon-home"></span>
				<span class="mui-tab-label">主页</span>
			</a>
			<a class="mui-tab-item" href="#tabbar-with-chat">
				<span class="mui-icon mui-icon-eye"><span class="mui-badge">9</span></span>
				<span class="mui-tab-label">新闻</span>
			</a>
			<a class="mui-tab-item" href="#tabbar-with-contact">
				<span class="mui-icon mui-icon-contact"></span>
				<span class="mui-tab-label">团队</span>
			</a>
			<a class="mui-tab-item" href="#tabbar-with-map">
				<span class="mui-icon mui-icon-starhalf"></span>
				<span class="mui-tab-label">积分</span>
			</a>
		</nav>
		<div class="mui-content">
			<div id="tabbar" class="mui-control-content mui-active">
					<!--页面主结构开始-->
			 		<div id="app" class="mui-views">
						<div class="mui-view">
							<div class="mui-pages">
							</div>
						</div>
					</div> 
					<!--页面主结构结束-->
			
			        <div id="setting" class="mui-page">	
						<div class="mui-card" style="padding:0;height:100px;">				
						<ul class="mui-table-view mui-table-view-chevron">
							<li class="mui-table-view-cell mui-media"> 
									<img class="mui-media-object mui-pull-left head-img" id="head-img" src="${weixin_userInfo.headImgUrl}">
									<div class="mui-media-body">
										${weixin_userInfo.nickname} <p style="display:inline;">笔名:[oo]</p> 
										<p class='mui-ellipsis'>账号:[account]</p>
									</div>
							</li>
						</ul> 
						</div>
						
 						<div class="mui-card">
							<div class="mui-card-content">
								<div class="mui-card-content-inner">
									${MxUsersReporterTeam.teamName} 积分：${MxUsersReporterScore.score}  排名：[10]
								</div>
							</div>
						</div>
						
						<div class="mui-card">
							<div class="mui-card-header">新闻信息：</div>
							<div class="mui-card-content">
								<div class="mui-card-content-inner">
									${MxUsersReporterScore.newsNum} 
								</div>
							</div>
							<div class="mui-card-footer"> 评论数：${MxUsersReporterScore.commentNum} 点赞数：${MxUsersReporterScore.praiseClickNum}</div>
						</div> 


				   </div>

				
			</div>
			<div id="tabbar-with-chat" class="mui-control-content">
				<div class="title" style="text-align:center;"><h4>我的新闻</h4></div>
				
				<div class="mui-card">
					<div class="mui-card-header">新闻概要：</div>
					<div class="mui-card-content">
						<div class="mui-card-content-inner">
							${MxUsersReporterScore.newsNum}条  ${MxUsersReporterScore.commentNum}评论 ${MxUsersReporterScore.praiseClickNum}点赞 
						</div>
					</div>
					<div class="mui-card-footer">最近更新[100]条</div>
				</div>
				
					<div class="mui-card" style="padding:0px;">
						<ul class="mui-table-view" style="margin:0;padding:0px;">
							<li class="mui-table-view-cell mui-collapse">
								<a class="mui-navigate-right" href="#">最新</a>
								<div class="mui-collapse-content1">
									<ul class="mui-table-view mui-table-view-chevron1">
										<c:forEach items="${MxNewsData}" var="c" varStatus="st">
											<li class="mui-table-view-cell"><a href="http://d1a7069951.iask.in/MX_System/mxReporterBusiness!getNewsPage.action?newsId=${c.newsId}" class="mui-navigate-right">${c.newsHeadline}</a></li>
										</c:forEach>								
									</ul>
								</div>
							</li>
							<li class="mui-table-view-cell mui-collapse">
								<a class="mui-navigate-right" href="#">历史</a>
								<div class="mui-collapse-content1">
									<ul class="mui-table-view mui-table-view-chevron1">
										<c:forEach items="${MxNewsData}" var="c" varStatus="st">
											<li class="mui-table-view-cell"><a href="http://d1a7069951.iask.in/MX_System/mxReporterBusiness!getNewsPage.action?newsId=${c.newsId}" class="mui-navigate-right">${c.newsHeadline}</a></li>
										</c:forEach>	
									</ul>
								</div>
							</li>
						</ul>
					</div>
		

									
				
			</div>
			<div id="tabbar-with-contact" class="mui-control-content">
				<div class="title" style="text-align:center;"><h4>我的团队</h4></div>
				
				<div class="mui-card">
					<div class="mui-card-header">团队信息：</div>
					<div class="mui-card-content">
						<div class="mui-card-content-inner">
						            公告：本团活动即将开始。
						    <br>
							100人  副队长  积分排名10
						</div>
					</div>
					<div class="mui-card-footer">最近加入10人</div>
				</div>
				
				<div class="mui-card">
				<ul class="mui-table-view mui-table-view-striped mui-table-view-condensed">
				<li class="mui-table-view-cell">
					<div class="mui-slider-cell">
						<div class="oa-contact-cell mui-table">
							<div class="oa-contact-avatar mui-table-cell">
								<img src="../images/60x60.gif" />
							</div>
							<div class="oa-contact-content mui-table-cell">
								<div class="mui-clearfix">
									<h4 class="oa-contact-name">叶文洁</h4>
									<span class="oa-contact-position mui-h6">董事长</span>
								</div>
								<p class="oa-contact-email mui-h6">
									yewenjie@sina.com
								</p>
							</div>
						</div>
					</div>
				</li>
				<li class="mui-table-view-cell">
					<div class="mui-slider-cell">
						<div class="oa-contact-cell mui-table">
							<div class="oa-contact-avatar mui-table-cell">
								<img src="../images/60x60.gif" />
							</div>
							<div class="oa-contact-content mui-table-cell">
								<div class="mui-clearfix">
									<h4 class="oa-contact-name">艾AA</h4>
									<span class="oa-contact-position mui-h6">总经理</span>
								</div>
								<p class="oa-contact-email mui-h6">
									aaa@163.com
								</p>
							</div>
						</div>
					</div>
				</li>
				<li class="mui-table-view-cell">
					<div class="mui-slider-cell">
						<div class="oa-contact-cell mui-table">
							<div class="oa-contact-avatar mui-table-cell">
								<img src="../images/60x60.gif" />
							</div>
							<div class="oa-contact-content mui-table-cell">
								<div class="mui-clearfix">
									<h4 class="oa-contact-name">罗辑</h4>
									<span class="oa-contact-position mui-h6">员工</span>
								</div>
								<p class="oa-contact-email mui-h6">
									luoji@126.com
								</p>
							</div>
						</div>
					</div>
				</li>
				<li class="mui-table-view-cell">
					<div class="mui-slider-cell">
						<div class="oa-contact-cell mui-table">
							<div class="oa-contact-avatar mui-table-cell">
								<img src="../images/60x60.gif" />
							</div>
							<div class="oa-contact-content mui-table-cell">
								<div class="mui-clearfix">
									<h4 class="oa-contact-name">云天明</h4>
									<span class="oa-contact-position mui-h6">员工</span>
								</div>
								<p class="oa-contact-email mui-h6">
									ytm@163.com
								</p>
							</div>
						</div>
					</div>
				</li>
				<li class="mui-table-view-cell">
					<div class="mui-slider-cell">
						<div class="oa-contact-cell mui-table">
							<div class="oa-contact-avatar mui-table-cell">
								<img src="../images/60x60.gif" />
							</div>
							<div class="oa-contact-content mui-table-cell">
								<div class="mui-clearfix">
									<h4 class="oa-contact-name">史强</h4>
									<span class="oa-contact-position mui-h6">员工</span>
								</div>
								<p class="oa-contact-email mui-h6">
									shiqiang@gmail.com
								</p>
							</div>
						</div>
					</div>
				</li>
			</ul>
			</div>
			</div>
			
			<div id="tabbar-with-map" class="mui-control-content">
				<div class="title" style="text-align:center;"><h4>我的积分</h4></div>
						<div class="mui-card">
							<div class="mui-card-header">积分详情：</div>
							<div class="mui-card-content">
								<div class="mui-card-content-inner">
								    <ul>
								      <li>我的学校 （+2）</li>
								      <li>运动会之歌 （+3）   </li>
								    </ul>
								</div>
							</div>
							<div class="mui-card-footer"> 总积分：5</div>
						</div>
				</div>
			</div>
		
		
	
		
		
		
	</body>
	<script src="<%=basePath%>WeixinPages/common/js/mui.min.js"></script>
	<script src="<%=basePath%>WeixinPages/common/js/mui.view.js"></script>
	<script src="<%=basePath%>WeixinPages/common/js/feedback.js"></script>
	<script>
		mui.init({
			//swipeBack:true //启用右滑关闭功能
		});
		//初始化单页view
 		var viewApi = mui('#app').view({
			defaultPage: '#setting'
		}); 
	
		var view = viewApi.view;
		(function($) {
			//处理view的后退与webview后退
			var oldBack = $.back;
			$.back = function() {
				if (viewApi.canBack()) { //如果view可以后退，则执行view的后退
					viewApi.back();
				} else { //执行webview后退
					oldBack();
				}
			};
			//监听页面切换事件方案1,通过view元素监听所有页面切换事件，目前提供pageBeforeShow|pageShow|pageBeforeBack|pageBack四种事件(before事件为动画开始前触发)
			//第一个参数为事件名称，第二个参数为事件回调，其中e.detail.page为当前页面的html对象
			view.addEventListener('pageBeforeShow', function(e) {
				//				console.log(e.detail.page.id + ' beforeShow');
			});
			view.addEventListener('pageShow', function(e) {
				//				console.log(e.detail.page.id + ' show');
			});
			view.addEventListener('pageBeforeBack', function(e) {
				//				console.log(e.detail.page.id + ' beforeBack');
			});
			view.addEventListener('pageBack', function(e) {
				//				console.log(e.detail.page.id + ' back');
			});
		})(mui);
	</script>
