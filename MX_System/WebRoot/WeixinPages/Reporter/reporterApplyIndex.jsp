<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.weixin.pojo.SNSUserInfo,java.lang.*"%>
<%String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";%>
<head>
<base href="<%=basePath%>">
<title>小记者申请页面</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">

		<!--标准mui.css-->
		<link rel="stylesheet" href="<%=basePath%>WeixinPages/common/css/mui.min.css">
		<style>
			h5 {
				margin: 5px 7px;
			}
		</style>
<!-- <script type="text/javascript">
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
</script> -->	
</head>
<body>
		<header class="mui-bar mui-bar-nav">
			<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
			<h1 class="mui-title">input（输入框）</h1>
		</header>
		<div class="mui-content">
			<div class="mui-content-padded" style="margin: 5px;">
				<h5>默认搜索框：</h5>
				<div class="mui-input-row mui-search">
					<input type="search" class="mui-input-clear" placeholder="">
				</div>
				<h5 class="mui-plus-visible">语音输入搜索框：</h5>
				<div class="mui-input-row mui-search mui-plus-visible">
					<input id="search" type="search" class="mui-input-speech mui-input-clear" placeholder="带语音输入的搜索框">
				</div>
				<h5>密码框：</h5>
				<div class="mui-input-row mui-password">
					<input type="password" class="mui-input-password">
				</div>
				<form class="mui-input-group">
					<div class="mui-input-row">
						<label>Input</label>
						<input type="text" placeholder="普通输入框">
					</div>
					<div class="mui-input-row">
						<label>Input</label>
						<input type="text" class="mui-input-clear" placeholder="带清除按钮的输入框">
					</div>

					<div class="mui-input-row mui-plus-visible">
						<label>Input</label>
						<input type="text" class="mui-input-speech mui-input-clear" placeholder="语音输入">
					</div>
					<div class="mui-button-row">
						<button type="button" class="mui-btn mui-btn-primary" onclick="">确认</button>&nbsp;&nbsp;
						<button type="button" class="mui-btn mui-btn-danger" onclick="">取消</button>
					</div>
				</form>
				<div class="mui-input-row" style="margin: 10px 5px;">
					<textarea id="textarea" rows="5" placeholder="多行文本框"></textarea>
				</div>
			</div>
		</div>
		<script src="<%=basePath%>WeixinPages/common/js/mui.min.js"></script>
		<script>
		    var code = "${code}";
		    var state = "${state}";
			mui.init({
				swipeBack: true //启用右滑关闭功能
			});
			 //语音识别完成事件
			document.getElementById("search").addEventListener('recognized', function(e) {
				console.log(e.detail.value);
			});

			var nativeWebview, imm, InputMethodManager;
			var initNativeObjects = function() {
				if (mui.os.android) {
					var main = plus.android.runtimeMainActivity();
					var Context = plus.android.importClass("android.content.Context");
					InputMethodManager = plus.android.importClass("android.view.inputmethod.InputMethodManager");
					imm = main.getSystemService(Context.INPUT_METHOD_SERVICE);
				} else {
					nativeWebview = plus.webview.currentWebview().nativeInstanceObject();
				}
			};
			var showSoftInput = function() {
				if (mui.os.android) {
					imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
				} else {
					nativeWebview.plusCallMethod({
						"setKeyboardDisplayRequiresUserAction": false
					});
				}
				setTimeout(function() {
					var inputElem = document.querySelector('input');
					inputElem.focus();
					inputElem.parentNode.classList.add('mui-active'); //第一个是search，加上激活样式
				}, 200);
			};
			mui.plusReady(function() {
				initNativeObjects();
				showSoftInput();
			});

		</script>
</body>

