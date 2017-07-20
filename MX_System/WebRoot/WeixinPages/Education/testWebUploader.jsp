<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
    <link href="<%=basePath%>WeixinPages/common/css/mui.min.css"
		rel="stylesheet" />
</head>
<body>
	<div class="mui-content ">
		<input type="file" style="/* visibility: hidden */"
			accept="video/*" name="" value="录视频"><br/><br/>
		<input type="file" style="/* visibility: hidden */" 
			accept="audio/*" name="" value="录音">
	</div>
</body>
</html>

<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<meta http-equiv="Content-Language"  content="ja" />
<meta http-equiv="Content-Script-Type" content="text/javascript" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<title>DEMO</title>
<link href="<%=basePath%>WeixinPages/common/css/webuploader/webuploader.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath%>WeixinPages/common/js/jquery-1.11.2.js"></script>
<script type="text/javascript" src="<%=basePath%>WeixinPages/common/js/webuploader/webuploader.js"></script>
<script type="text/javascript" src="<%=basePath%>WeixinPages/common/js/webuploader/webuploader.min.js"></script>
<script type="text/javascript" src="<%=basePath%>WeixinPages/common/js/webuploader/upload3.js"></script>
</head>
<body>
<div id="uploader" class="wu-example">
    <!--用来存放文件信息-->
    <div id="thelist" class="uploader-list"></div>
    <div class="btns">
        <div id="attach"></div>
        <input type="button" value="上传" id="upload"/> 
    </div>
</div>


<div id="uploader1" class="wu-example">
    <!--用来存放文件信息-->
    <div id="thelist1" class="uploader-list"></div>
    <div class="btns">
        <div id="multi"></div>
        <input type="button" value="上传" id="multiUpload"/> 
    </div>
</div>

</body>
</html>