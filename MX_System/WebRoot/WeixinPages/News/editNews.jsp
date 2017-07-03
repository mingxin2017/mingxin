<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.weixin.pojo.SNSUserInfo,java.lang.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
<base href="<%=basePath%>">
<title>添加新闻</title>
	<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no">

	<title>wangEditor mobile test</title>

	<!--
		<script src="http://192.168.1.100:8881/target/target-script-min.js#anonymous"></script>
	-->

	<!--引用css-->
	<link rel="stylesheet" type="text/css" href="<%=basePath%>WeixinPages/common/wangEditorMobile/dist/css/wangEditor-mobile.css">
	
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
			height:420px; 
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
	<div class="maintitle" style="text-align:center;">
		<b>新闻编辑</b> 
	</div>
	<center>
		<div style="text-align:left;">
			标题：<input type="text" id="headline" class="w100" placeholder="标题"> <br>
			引言：<input type="text" id="leadText" class="w100" placeholder="引言"> <br>
			笔名：<input type="text" id="writer_name" class="w100" placeholder="笔名"><br>
			类型：<select id="newsTypeId" class="select" style="width:70px;" onchange="updateChildTypeContent();">
					<c:forEach items="${msNewsType}" var="c" varStatus="st">
					    <option value="${c.newsTypeId}">${c.typeName}</option>
					</c:forEach>
   				</select>
   				<select id="subId" class="select" style="width:70px;">
   				</select>
		</div>
		<div class="container">
			<textarea id="content" style="width:100%;height:100%;">
<%-- 				<h3>济南的冬天</h3>
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
				<img src="<%=basePath%>WeixinPages/common/wangEditorMobile/img/daminghu.png" style="max-width:100%;">
				<p>（图为大明湖雪景）</p> 
				<p><br></p>--%>
			</textarea>
		</div>
	</center>
	<div>
		 <button type="button" id="save" onClick="onCommit();" style="width:80px;">提交</button>
	</div>
	<footer>
		&copy; 2016
		<a href="http://wangeditor.github.io/m/index.html" target="_blank">
			wangEditor.github.io/m
		</a>
	</footer>
	<script type="text/javascript" src="<%=basePath%>WeixinPages/common/wangEditorMobile/dist/js/lib/zepto.js"></script>
	<script type="text/javascript" src="<%=basePath%>WeixinPages/common/wangEditorMobile/dist/js/lib/zepto.touch.js"></script>
	<script type="text/javascript" src="<%=basePath%>WeixinPages/common/wangEditorMobile/dist/js/wangEditor-mobile.js"></script>
	<script type="text/javascript">
	$(function () {
		// 全局配置
		// ___E.config.menus = ['bold', 'color', 'quote'];
		// 生成编辑器
		var editor = new ___E('content');
		// 自定义配置
		editor.config.uploadImgUrl = '/upload';
		// editor.config.menus = ['bold', 'quote', 'list','img'];
		// 初始化
		editor.init();
		console.log(editor.$txt);
	});
	</script>
 	<script src="<%=basePath%>WeixinPages/common/richtext/js/jquery-1.11.2.js"></script>
	<script type="text/javascript">
		//接收参数
		var code = "${code}";
		var state = "${state}";
		//类型改变事件
		function updateChildTypeContent(){
			var selectedValue = $('#newsTypeId').val();
			//第一步：移除所有下级选择框
		    var typeTr = $("#subId");
		    typeTr.empty();
		    //第二步：如果选项值不为空 ,添加下一级选择框的值
		    if(selectedValue != null && "" != selectedValue){
		        var requestUrl = "weixin!loadKeyValue.action";//这里是拿数据的地址
		        $.ajax({
		            async : false,
		            type : "post",
		            url : requestUrl,
		            dataType : "json",
		            data : {
		                newsTypeId:selectedValue// 传进去的值
		            },
		            success : function(data) {
		                for(var obj in data){ 
		                	var List = data[obj]; 
							for(var i in List){ //第二层循环取list中的对象 
								var tr = "";                     
			                    tr = tr + "<option value='" + List[i].key + "'>" + List[i].value + "</option>";
			                    $('#subId').append(tr);
							} 
		                } 
		            }
		        });
		       }
		}
		//提交事件
		function onCommit(){
			  var headline = $("#headline").val();
			  var leadText = $("#leadText").val();
			  var writer_name = $("#writer_name").val();
			  var content = $("#content").val();
			  var newsTypeId = $("#newsTypeId").val();
			  var subId = $("#subId").val();
			  var data = { 
						headline:headline,
						leadText:leadText,
						writer_name:writer_name,
						content:content,
						newsTypeId:newsTypeId,
						subId:subId,
						code:code,
						state:state
			  };
			  //请求添加数据
				$.ajax({
					type : "POST",
					url : 'http://d1a7069951.iask.in/MX_System/weixin!addNews.action',
					data : data,
					success : function(data) {
						if (data.success) {
							console.log("success");
							//resultPage("success");
	/* 						$.messager.show({
								msg : data.message,
								title : '提示'
							}); */
						} else {
							console.log(type);
						/* 	parent.$.messager.alert("错误", data.message, "error", function() {
								return false;
							}); */
						}
					},
					error : function(e) {
						/* parent.$.messager.alert("提示", "操作失败，请联系管理员."); */
					}
				});
		}
	</script>
</body>
