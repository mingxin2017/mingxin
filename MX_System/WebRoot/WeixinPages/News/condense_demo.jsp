<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";%>
<head>
<base href="<%=basePath%>">
<title>html5+canvas实现图片的压缩</title>
<script type="text/javascript" src="<%=basePath%>WeixinPages/common/js/jquery-1.11.2.js"></script>
<script type="text/javascript" src="<%=basePath%>WeixinPages/common/js/jquery.form.js"></script>
</head>
<body>
	<form id="formData"><!-- action="weixin/fileUp.action" method="post" enctype="multipart/form-data" -->
		文件名称：<input type="text" name="fileName"/><br/>
		文件上传：<input type="file" name="upload" id="file" multiple="multiple" onchange="return FileUpload_onselect();"/><br/>
		文件描述：<input type="text" name="desc"/><br/>
		<div id="showImage">预览</div>
		<div style="display:none;"><canvas id="canvas"></canvas></div>
		<input type="button" onclick="doUploadFile();" value="提交"/>
	</form>
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

	</script>
</body>