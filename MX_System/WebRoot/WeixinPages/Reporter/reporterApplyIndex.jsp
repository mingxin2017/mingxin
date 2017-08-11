<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.mx.weixin.pojo.SNSUserInfo,java.lang.*"%>
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
		<link rel="stylesheet" type="text/css" href="<%=basePath%>WeixinPages/common/css/app.css" />
		<link href="<%=basePath%>WeixinPages/common/css/mui.picker.css" rel="stylesheet" />
		<link href="<%=basePath%>WeixinPages/common/css/mui.poppicker.css" rel="stylesheet" />
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
			<a id="submit" class="mui-btn mui-btn-blue mui-btn-link mui-pull-right">保存</a>			
			<h1 class="mui-title">小记者申请</h1>
		</header>
		<div class="mui-content">
			<div class="mui-content-padded">
				<h5 class="mui-content-padded">团队选择</h5>
				<div class="mui-input-row">
					<input id='showUserPicker' type="text" class="mui-input-clear contact" placeholder="点击选择团队..." />
				</div>
				<code id="response"></code>
			</div>
		</div>
		<script src="<%=basePath%>WeixinPages/common/js/mui.min.js"></script>
		<script src="<%=basePath%>WeixinPages/common/js/mui.picker.js"></script>
		<script src="<%=basePath%>WeixinPages/common/js/mui.poppicker.js"></script>
		<script>
			(function($, doc) {
				$.init();
				$.ready(function() {
					//普通示例
					var userPicker = new $.PopPicker();
					userPicker.setData([{
						value: 'ywj',
						text: '董事长 叶文洁'
					}, {
						value: 'aaa',
						text: '总经理 艾AA'
					}, {
						value: 'lj',
						text: '罗辑'
					}, {
						value: 'ymt',
						text: '云天明'
					}, {
						value: 'shq',
						text: '史强'
					}, {
						value: 'zhbh',
						text: '章北海'
					}, {
						value: 'zhy',
						text: '庄颜'
					}, {
						value: 'gyf',
						text: '关一帆'
					}, {
						value: 'zhz',
						text: '智子'
					}, {
						value: 'gezh', 
						text: '歌者'
					}]);
					var showUserPickerButton = doc.getElementById('showUserPicker');
					showUserPickerButton.addEventListener('tap', function(event) {
 						userPicker.show(function(items) {
 							showUserPickerButton.value = JSON.stringify(items[0]);
							//返回 false 可以阻止选择框的关闭
							//return false; 
						}); 
					}, false);
				});
				/*********************************************************/
				//ajax请求
				var network = true; 
				if(mui.os.plus){
					mui.plusReady(function () {
						if(plus.networkinfo.getCurrentType()==plus.networkinfo.CONNECTION_NONE){
							network = false;
						}
					});
				}
				var respnoseEl = document.getElementById("response");
				//成功响应的回调函数
				var success = function(response) {
					var dataType = 'json';
					if (dataType === 'json') {
						response = JSON.stringify(response);
					} else if (dataType === 'xml') {
						response = new XMLSerializer().serializeToString(response).replace(/</g, "&lt;").replace(/>/g, "&gt;");
					}
					respnoseEl.innerHTML = response;
				};
				var ajax = function() {
					//利用RunJS的Echo Ajax功能测试
					var url = 'mxReporterBusiness!reporterApply.action';
					//请求方式，默认为Get；
					var type = 'post';
					//预期服务器范围的数据类型
					var dataType = 'json';
					//发送数据
					var data = JSON.parse(doc.getElementById('showUserPicker').value);
					respnoseEl.innerHTML = '正在请求中...';
					if (type === 'get') {
						if (dataType === 'json') {
							$.getJSON(url, data, success);
						} else {
							$.get(url, data, success, dataType);
						}
					} else if (type === 'post') {
						$.post(url, data, success, dataType);
					}
				};
				//发送请求按钮的点击事件
				document.getElementById("submit").addEventListener('tap', function() {
					if(network){
						ajax();
					}else{
						mui.toast("当前网络不给力，请稍后再试");
					}
				});
			})(mui, document);
		</script>

</body>

