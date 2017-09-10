<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html lang="en" class="feedback">
<head>
<base href="<%=basePath%>">
<title>添加新闻</title>
	<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>WeixinPages/common/css/mui.min.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath%>WeixinPages/common/css/feedback.css" />
	<link href="<%=basePath%>WeixinPages/common/css/mui.picker.css" rel="stylesheet" />
	<link href="<%=basePath%>WeixinPages/common/css/mui.poppicker.css" rel="stylesheet" />
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
<style type="text/css">
	.file {
	    position: relative;
	    display: inline-block;
	    background: #D0EEFF;
	    border: 1px solid #99D3F5;
	    border-radius: 4px;
	    padding: 4px 12px;
	    overflow: hidden;
	    color: #1E88C7;
	    text-decoration: none;
	    text-indent: 0;
	    line-height: 20px;
	}
	.file input {
	    position: absolute;
	    font-size: 100px;
	    right: 0;
	    top: 0;
	    opacity: 0;
	}
	.file:hover {
	    background: #AADFFD;
	    border-color: #78C3F3;
	    color: #004974;
	    text-decoration: none;
	}
</style>
		<header class="mui-bar mui-bar-nav">
			<a id="submit1" onclick="saveInfo();"  class="mui-btn mui-btn-blue mui-btn-link mui-pull-left">保存</a>
			<a id="newsList" class="mui-btn mui-btn-blue mui-btn-link mui-pull-right"
				href="weixin/applyNewsList.action">投稿列表</a> 
			<h1 class="mui-title">添加新闻</h1>
		</header>
		<div class="mui-content">
			<div class="mui-content-padded">
				<div class="mui-input-row">
					<input id='headLine' type="text" class="mui-input-clear contact" placeholder="标题..." />
				</div>
				<div class="mui-input-row">
					<input id='leadText' type="text" class="mui-input-clear contact" placeholder="引言..." />
				</div>
				<div class="mui-input-row">
					<input id='writerName' type="text" class="mui-input-clear contact" placeholder="笔名..." />
				</div>
				<div class="mui-input-row">
					<input id='subId' type="hidden" class="mui-input-clear contact" placeholder="单位..." />
				    <input id='newsTypeId' type="hidden" class="mui-input-clear contact" placeholder="单位..." />
					<input id='showCityPicker' type="text" class="mui-input-clear contact" placeholder="单位..." />
				</div>
				<div class="row mui-input-row">
					<textarea id='content' style="height:160px" class="mui-input-clear question" placeholder="内容..."></textarea>
				</div>
				<form id="formData" class="file" style="margin:1 1;">
					选取图片<input type="file" name="upload" id="file" multiple="multiple" onchange="return FileUpload_onselect();"/><br/>
				</form>
				<div id="showImage"></div>
				<div style="display:none;"><canvas id="canvas"></canvas></div>
				<input type="button" onclick="doUploadFile();" value="提交"/>
				
				<code id="response"></code>
			</div>	
		</div>
		
		<script src="<%=basePath%>WeixinPages/common/js/mui.min.js"></script>
		<script src="<%=basePath%>WeixinPages/common/js/mui.picker.js"></script>
		<script src="<%=basePath%>WeixinPages/common/js/mui.poppicker.js"></script>
		<script type="text/javascript">
			(function($, doc) {
				var schools = ${schools};
				var regions = ${regions};
				var cityData = [{
				value: '4',
				text: '学校',
				children:  schools
				}, {
					value: '5',
					text: '地区',
					children: regions
				}]; 
	    		//引入mui的picker控件
			    /***************************picker*************************/
				$.init();
				$.ready(function() {	
					//级联示例
					var cityPicker = new $.PopPicker({
						layer: 2
					});
					cityPicker.setData(cityData);
					var showCityPickerButton = doc.getElementById('showCityPicker');
					var newsTypeId = doc.getElementById('newsTypeId');
					var subId = doc.getElementById('subId');
					showCityPickerButton.addEventListener('tap', function(event) {
						cityPicker.show(function(items) {
							showCityPickerButton.value = "你选择的区域是:" + items[0].text + " " + items[1].text;
							newsTypeId.value = items[0].value;
							subId.value = items[1].value;
							//返回 false 可以阻止选择框的关闭
							//return false;
						});
						
					}, false);
				});
				/***************************picker*************************/
			})(mui, document);
	</script>
<script type="text/javascript" src="<%=basePath%>WeixinPages/common/js/jquery-1.11.2.js"></script>
<script type="text/javascript" src="<%=basePath%>WeixinPages/common/js/jquery.form.js"></script>
	<script type="text/javascript">
		var base64 = "";
		//提交方法
		function doUploadFile(){
				//按两种方试上传文件
				if(base64==""){//不压缩
					$("#formData").ajaxSubmit({
						type: "post",
						url: "weixin/fileUp.action",
						success: function (data) {
							console.log("上传成功");
						},
						error: function (msg) {
							alert("文件上传失败");
						}
					});
				}else{//压缩
					$.ajax({
						type: "post",
						url: "weixin/uploadImage.action",
						data: {img:base64},
						success: function (data) {
							console.log("上传成功");
						},
						error: function (msg) {
							alert("文件上传失败");
						}
					});
				}
				
		}
		//file input onchange事件
		function FileUpload_onselect(){
			console.log(document.getElementById('file').files);
			var files = document.getElementById('file').files;//取得文件对象
			console.log(files.length);  
	        if (files.length > 9) {//文件数量
	            alert("最多同时只可上传9张图片");
	            return;
	        }
	        for(var i=0;i<files.length;i++){//遍历文件
	        	var file=files[i];
	        	console.log(file);
	        	console.log(file.type);
	        	console.log(file.name);
	        	console.log(file.size);
	        	
	        	//不是图片格式不进行处理
	        	var file_type = file.type.split("/");//根据'/'分割字符串
	        	if (file_type[0]!='image') return;
	        	//图片预览
	        	var img = new Image();
				img.width=200;
				img.height=200;
				img.src = URL.createObjectURL(file);
				$('#showImage').empty().append(img);
				
	        	//图片小于200kb不进行处理
	        	if(file.size<=200*1024) return;
	        	//进行图片压缩
	        	img.onload = function(){
		        	var canvas=document.getElementById("canvas");
		        	console.log(canvas.width+","+canvas.height);
		        	canvas.width = 200;
		        	canvas.height = 200;
		        	var ctx = canvas.getContext("2d"); 
		        	ctx.drawImage(img,0,0,200,200);
		        	var ndata = canvas.toDataURL('image/jpeg', 1);  // quality值越小，所绘制出的图像越模糊 0~1
					console.log(ndata);
					console.log('压缩前：' + file.size);
			        console.log('压缩后：' + ndata.length);
			        console.log('压缩率：' + ~~(100 * (file.size - ndata.length) / file.size) + "%");
					base64 = ndata;
				};
				
	        }
        	
		}
		//保存信息
		function saveInfo(){
			var params = {
				headLine : $("#headLine").val(),
				leadText : $("#leadText").val(),
				writerName : $("#writerName").val(),
				subId : $("#subId").val(),
				newsTypeId : $("#newsTypeId").val(),
				showCityPicker : $("#showCityPicker").val(),
				content : $("#content").val()
			}; 
		   $.ajax({
				type: "post",
				url: "weixin/addNews.action",
				data: params,
				success: function (jsonstr) {
				    var data = JSON.parse(jsonstr); 
					if(data.data.success){
						mui.toast(data.data.msg);
						//刷新页面或者跳转到管理界面
					}; 
				},
				error: function (msg) {
					mui.toast("保存失败，请联系管理员！");
				}
			});
		};
	</script>
	</body>
</html>
