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
	<!--引入CSS-->
<link rel="stylesheet" type="text/css" href="<%=basePath%>WeixinPages/common/css/webuploader.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>WeixinPages/common/css/uploaderStyle.css">
<!--引入JS-->
<script type="text/javascript" src="<%=basePath%>WeixinPages/common/js/webuploader/webuploader.js"></script>
</head>
<body>
	<%--<div class="mui-content ">
		<input type="file" style="/* visibility: hidden */"
			accept="video/*" name="" value="录视频"><br/><br/>
		<input type="file" style="/* visibility: hidden */" 
			accept="audio/*" name="" value="录音">
	</div>
	--%>
	<div id="wrapper">
        <div id="container">
            <!--头部，相册选择和格式选择-->

            <div id="uploader">
                <div class="queueList">
                    <div id="dndArea" class="placeholder">
                        <div id="filePicker"></div>
                        <p>或将照片拖到这里，单次最多可选300张</p>
                    </div>
                </div>
                <div class="statusBar" style="display:none;">
                    <div class="progress">
                        <span class="text">0%</span>
                        <span class="percentage"></span>
                    </div><div class="info"></div>
                    <div class="btns">
                        <div id="filePicker2"></div><div class="uploadBtn">开始上传</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript" src="<%=basePath%>WeixinPages/common/js/webuploader/jquery.js"></script>
    <script type="text/javascript" src="<%=basePath%>WeixinPages/common/js/webuploader/upload.js"></script>
</body>
</html>
