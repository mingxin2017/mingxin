<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>鸣心-活动空间</title>
    <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		
		<style>
			html,
			body {
				background-color: #efeff4;
			}
		</style>
		<script type="text/javascript" charset="utf-8">
			mui.init();

			//设置默认打开首页显示的子页序号；/MX_System/WebRoot/WeixinPages/activitiesMySpaceCommentList.jsp
			var Index = 0;
			//把子页的路径写在数组里面
			var subpages = ['home.html', 'html/message.html', 'html/setting.html'];

			//所有的plus-*方法写在mui.plusReady中或者后面。
			mui.plusReady(function() {
				//获取当前页面所属的Webview窗口对象
				var self = plus.webview.currentWebview();
				for(var i = 0; i < 3; i++) {
					//创建webview子页
					var sub = plus.webview.create(
						subpages[i], //子页url
						subpages[i], //子页id
						{
							top: '45px', //设置距离顶部的距离
							bottom: '50px' //设置距离底部的距离
						}
					);
					//如不是我们设置的默认的子页则隐藏，否则添加到窗口中
					if(i != Index) {
						sub.hide();
					}
					//将webview对象填充到窗口
					self.append(sub);
				}
			});
		</script>
	<!--标准mui.css-->
	<link rel="stylesheet" href="<%=basePath%>WeixinPages/common/css/mui.min.css">
	<!--mui js-->
	<script src="<%=basePath%>WeixinPages/common/js/mui.min.js"></script>
	
  </head>
  
  <body>
		<header class="mui-bar mui-bar-nav">
			<h1 class="mui-title">标题</h1>
||||||| .r91
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>鸣心-活动空间</title>
    <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<!--标准mui.css-->
	<link rel="stylesheet" href="<%=basePath%>WeixinPages/common/css/mui.min.css">
	<!--App自定义的css-->
	<link rel="stylesheet" type="text/css" href="<%=basePath%>WeixinPages/common/css/app.css" />
	<script src="<%=basePath%>WeixinPages/common/js/mui.min.js"></script>
	<script src="<%=basePath%>WeixinPages/common/js/jquery-1.11.2.js"></script>
	<!--列表js-->
	<script>
	mui.init({
		swipeBack: false //启用右滑关闭功能
	});
	
	var title = document.getElementById("title");
	 //选项卡点击事件
	mui('.mui-bar-tab').on('tap', 'a', function(e) {
		alert("ddd");
		var targetTab = this.getAttribute('href');
		if (targetTab == activeTab) {
			return;
		}
		//更换标题
		title.innerHTML = this.querySelector('.mui-tab-label').innerHTML;
		//显示目标选项卡
		//若为iOS平台或非首次显示，则直接显示
		if(mui.os.ios||aniShow[targetTab]){
			plus.webview.show(targetTab);
		}else{
			//否则，使用fade-in动画，且保存变量
			var temp = {};
			temp[targetTab] = "true";
			mui.extend(aniShow,temp);
			plus.webview.show(targetTab,"fade-in",300);
		}
		//隐藏当前;
		plus.webview.hide(activeTab);
		//更改当前活跃的选项卡
		activeTab = targetTab;
	});
	
	</script>
	<script type="text/javascript">
		function showIframe(pageNum){
			var url;
			alert("ddd");
			if(pageNum==1){
				url="http://mingxin.imwork.net/MX_System/activitiesMySpace!getActivitiesMySpaceCommentList.action";
			}else if(pageNum==2){
				url="http://mingxin.imwork.net/MX_System/activitiesMySpace!getActivitiesMySpaceMaterialList.action";
			}else if(pageNum==3){
				url="http://mingxin.imwork.net/MX_System/activitiesMySpace!getActivitiesMySpaceUsersList.action";
			}else if(pageNum==4){
				url="http://mingxin.imwork.net/MX_System/activitiesMySpace!getActivitiesMySpaceManage.action";
			}else{
				return;
			}
			$("#mainContent").attr("src",url).ready();
			//alert("dddd");
		}
	</script>
	
  </head>
  
  <body>
  		<header class="mui-bar mui-bar-nav">
			<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
			<h1 id="title" class="mui-title">活动空间</h1>

	<head>
		<meta charset="utf-8">
		<title>Hello MUI</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">

		<link rel="stylesheet" href="<%=basePath%>WeixinPages/common/css/mui.min.css">
		<script src="<%=basePath%>WeixinPages/common/js/mui.min.js"></script>
		<script src="<%=basePath%>WeixinPages/common/js/webviewGroup.js" type="text/javascript" charset="utf-8"></script>
		
		<style>
			html,
			body {
				background-color: #efeff4;
			}
			
			.mui-bar~.mui-content .mui-fullscreen {
				top: 44px;
				height: auto;
			}
			
			.mui-pull-top-tips {
				position: absolute;
				top: -20px;
				left: 50%;
				margin-left: -25px;
				width: 40px;
				height: 40px;
				border-radius: 100%;
				z-index: 1;
			}
			
			.mui-bar~.mui-pull-top-tips {
				top: 24px;
			}
			
			.mui-pull-top-wrapper {
				width: 42px;
				height: 42px;
				display: block;
				text-align: center;
				background-color: #efeff4;
				border: 1px solid #ddd;
				border-radius: 25px;
				background-clip: padding-box;
				box-shadow: 0 4px 10px #bbb;
				overflow: hidden;
			}
			
			.mui-pull-top-tips.mui-transitioning {
				-webkit-transition-duration: 200ms;
				transition-duration: 200ms;
			}
			
			.mui-pull-top-tips .mui-pull-loading {
				/*-webkit-backface-visibility: hidden;
				-webkit-transition-duration: 400ms;
				transition-duration: 400ms;*/
				margin: 0;
			}
			
			.mui-pull-top-wrapper .mui-icon,
			.mui-pull-top-wrapper .mui-spinner {
				margin-top: 7px;
			}
			
			.mui-pull-top-wrapper .mui-icon.mui-reverse {
				/*-webkit-transform: rotate(180deg) translateZ(0);*/
			}
			
			.mui-pull-bottom-tips {
				text-align: center;
				background-color: #efeff4;
				font-size: 15px;
				line-height: 40px;
				color: #777;
			}
			
			.mui-pull-top-canvas {
				overflow: hidden;
				background-color: #fafafa;
				border-radius: 40px;
				box-shadow: 0 4px 10px #bbb;
				width: 40px;
				height: 40px;
				margin: 0 auto;
			}
			
			.mui-pull-top-canvas canvas {
				width: 40px;
			}
			
			.mui-slider-indicator.mui-segmented-control {
				background-color: #efeff4;
			}
		</style>
	</head>

	<body>
		<header class="mui-bar mui-bar-nav">
			<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
			<h1 class="mui-title">(webview)</h1>

		</header>

		<nav class="mui-bar mui-bar-tab">
			<a class="mui-tab-item mui-active">
				<span class="mui-icon mui-icon-home"></span>
				<span class="mui-tab-label">首页</span>
			</a>
			<a class="mui-tab-item">
				<span class="mui-icon mui-icon-phone"></span>
				<span class="mui-tab-label">消息</span>
			</a>
			<a class="mui-tab-item">
				<span class="mui-icon mui-icon-gear"></span>
				<span class="mui-tab-label">设置</span>
			</a>
		</nav>
		
	</body>
</html>

		<nav class="mui-bar mui-bar-tab">
			<a class="mui-tab-item mui-active" href="javascript:void(0)" onclick="showIframe(1);">
				<span class="mui-icon mui-icon-chat"></span>
				<span class="mui-tab-label">讨论</span>
			</a>
			<a class="mui-tab-item " href="javascript:void(0)" onclick="showIframe(2);">
				<span class="mui-icon mui-icon-image"></span>
				<span class="mui-tab-label">图库</span>
			</a>
			<a class="mui-tab-item" href="javascript:void(0)" onclick="showIframe(3);">
				<span class="mui-icon mui-icon-contact"></span>
				<span class="mui-tab-label">通讯录</span>
			</a>
			<a class="mui-tab-item" href="javascript:void(0)" onclick="showIframe(4);">
				<span class="mui-icon mui-icon-gear"></span>
				<span class="mui-tab-label">设置</span>
			</a>
		</nav>
    	<div class="mui-content">
    		<iframe style="width:100%;" id="mainContent" src="http://mingxin.imwork.net/MX_System/activitiesMySpace!getActivitiesMySpaceCommentList.action"/>
		</div>
  </body>
</html>
		<div class="mui-content">
			<div id="slider" class="mui-slider mui-fullscreen">
				<div id="sliderSegmentedControl" class="mui-scroll-wrapper mui-slider-indicator mui-segmented-control mui-segmented-control-inverted">
					<div class="mui-scroll">
						<a class="mui-control-item mui-active" href="#item1mobile" data-wid="tab-top-subpage-1.html">
							111
						</a>
						<a class="mui-control-item" href="#item2mobile" data-wid="tab-top-subpage-2.html">
							222
						</a>
						<a class="mui-control-item" href="#item3mobile" data-wid="tab-top-subpage-3.html">
							333
						</a>
						<a class="mui-control-item" href="#item4mobile" data-wid="tab-top-subpage-4.html">
							444
						</a>
						<a class="mui-control-item" href="#item5mobile" data-wid="tab-top-subpage-5.html">
							555
						</a>
					</div>
				</div>

			</div>
		</div>
		<script>
			mui.init();
			
			mui.plusReady(function() {
				var group = new webviewGroup("viewgroup", {
					items: [{
						id: "tab-top-subpage-1.html",
						url: "/tab-top-subpage-1.html",
						extras: {}
					}, {
						id: "tab-top-subpage-2.html",
						url: "tab-top-subpage-2.html",
						extras: {}
					}, {
						id: "tab-top-subpage-3.html",
						url: "tab-top-subpage-3.html",
						extras: {}
					}, {
						id: "tab-top-subpage-4.html",
						url: "tab-top-subpage-4.html",
						extras: {}
					}, {
						id: "tab-top-subpage-5.html",
						url: "tab-top-subpage-5.html",
						extras: {}
					}],
					onChange: function(obj) {
						var c = document.querySelector(".mui-control-item.mui-active");
						if(c) {
							c.classList.remove("mui-active");
						}
						document.querySelector(".mui-scroll .mui-control-item:nth-child(" + (parseInt(obj.index) + 1) + ")").classList.add("mui-active");
					}
				});
				mui(".mui-scroll").on("tap", ".mui-control-item", function(e) {
					var wid = this.getAttribute("data-wid");
					group.switchTab(wid);
				});

			});
			mui.back = function() {
				var _self = plus.webview.currentWebview();
				_self.close("auto");
			}
		</script>
	</body>

</html>
