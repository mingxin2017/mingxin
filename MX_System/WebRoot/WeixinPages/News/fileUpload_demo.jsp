<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";%>
<head>
<base href="<%=basePath%>">
<title>文件上传</title>
</head>
<body>
hello world!
<form action="weixin/fileUp.action" method="post" enctype="multipart/form-data">
	文件名称：<input type="text" name="fileName"/><br/>
	文件上传：<input type="file" name="upload"/><br/>
	文件描述：<input type="text" name="desc"/><br/>
<input type="submit" value="提交"/>
</form>
</body>