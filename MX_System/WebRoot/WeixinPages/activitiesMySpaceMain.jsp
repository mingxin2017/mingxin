<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>

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