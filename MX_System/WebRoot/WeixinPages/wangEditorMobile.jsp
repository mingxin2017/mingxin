<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	
	<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no">

	<title>wangEditorMobile</title>

	<!--
		<script src="http://192.168.1.100:8881/target/target-script-min.js#anonymous"></script>
	-->

	<!--引用css-->
	<link rel="stylesheet" type="text/css" href="<%=basePath%>WeixinPages/common/css/wangEditor-mobile.css">
	<script type="text/javascript" src="<%=basePath%>WeixinPages/common/js/zepto.js"></script>
	<script type="text/javascript" src="<%=basePath%>WeixinPages/common/js/zepto.touch.js"></script>
	<script type="text/javascript" src="<%=basePath%>WeixinPages/common/js/wangEditor-mobile.js"></script>
	
	<style type="text/css">

		body {
			background-color:#f1f1f1;
			text-shadow: 0 0 2px #ccc;
		}

		.wangEditor-mobile-menu-container {
			text-shadow: none;
		}

		a {
			text-decoration: none;
		}

		.title {
			padding: 15px 0;
			text-align: center;
			opacity: 0.7;
		}

		.container {
			width:100%; 
			height:100%; 
			border:1px solid #ccc;
			background-color: #fff;
			text-align: left;
			box-shadow: 0 0 10px #ccc;
			text-shadow: none;
		}

		footer {
			width: 100%;
			text-align: center;
			padding: 10px 0;
			opacity: 0.6;
			margin-top: 10px;
		}

	</style>
</head>
<body>
	
	<center>
		<div class="container" >
			<!-- <textarea id="textarea2" style="width:100%;height:10%;"></textarea> -->
			<textarea id="textarea1" style="width:100%;height:80%;">
				<input type="text" style="style="font-size:30px";width:100%;height:50px;"/>
				<h3>济南的冬天</h3>
				<p><font color="#ff0000">对于一个在北平住惯的人，像我，冬天要是不刮风，便觉得是奇迹；济南的冬天是没有风声的。</font></p>
				<p>对于一个刚由伦敦回来的人，像我，冬天要能看得见日光，便觉得是怪事；济南的冬天是响晴的……<img src="http://wangeditor.github.io/expressions/20.gif"></p>
				<blockquote style="display: block; border-left-width: 5px; border-left-style: solid; border-left-color: rgb(208, 229, 242); padding: 4px 0px 4px 10px; margin: 4px 0px; background-color: rgb(241, 241, 241);">
					老舍1930年前后来到山东，先后在济南齐鲁大学和青岛山东大学任教7年之久，山东被称为他的“第二故乡”。
				</blockquote>
				<strong>作者其他作品：</strong>
				<ul>
					<li><span>骆驼祥子</span></li>
					<li><span>茶馆</span></li>
					<li><span>四世同堂</span></li>
				</ul>
				<p><br></p>
				<img src="img/daminghu.png" style="max-width:100%;">
				<p>（图为大明湖雪景）</p>
				<p><br></p>
			</textarea>
		</div>
	</center>
	

	
	<script type="text/javascript">
	$(function () {

		// 全局配置
		// ___E.config.menus = ['bold', 'color', 'quote'];

		// 生成编辑器
		var editor = new ___E('textarea1');

		// 自定义配置
		editor.config.uploadImgUrl = '/upload';

		// editor.config.menus = ['bold', 'quote', 'list','img'];

		// 初始化
		editor.init();

		console.log(editor.$txt);
	});
	</script>
</body>
</html>
