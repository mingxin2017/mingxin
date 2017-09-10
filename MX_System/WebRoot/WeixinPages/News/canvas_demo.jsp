<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";%>
<head>
<base href="<%=basePath%>">
<title>html5+canvas</title>
<script type="text/javascript" src="<%=basePath%>WeixinPages/common/js/jquery-1.11.2.js"></script>
<script type="text/javascript" src="<%=basePath%>WeixinPages/common/js/jquery.form.js"></script>
</head>
<body>
    <div style="border:1px solid red;">
    img:<img id="tulip" alt="" src="<%=basePath%>WeixinPages/common/image/1.jpg" style="height:200px;width:200px;">
    </div>
    <div style="border:1px solid red;">
    canvas:<canvas id="canvas" ></canvas>
    </div>
    <div id="showImage">预览</div>
	<script type="text/javascript">
		var canvas=document.getElementById("canvas");
		//var canvas = document.createElement("canvas");
		var ctx=canvas.getContext("2d");
		var img=document.getElementById("tulip");

		console.log(img);         
		var width = img.width;
		var height = img.height;
		console.log("img width:"+width+",height:"+height);
		
		ctx.drawImage(img,0,0,width,height);
		var ndata = canvas.toDataURL('image/jpeg', 1);  // quality值越小，所绘制出的图像越模糊
		console.log(ndata);
		
		var img1 = new Image();
		img1.width=300;
		img1.height=300;
		img1.src = "<%=basePath%>WeixinPages/common/image/1.jpg";
		$('#showImage').append(img1);
		var br = document.createElement("br");
		$('#showImage').append(br);
		var canvas1 = document.createElement("canvas");
		$('#showImage').append(canvas1);
		var ctx1=canvas1.getContext("2d");
		ctx1.drawImage(img1,0,0,150,150);
		
	</script>
</body>