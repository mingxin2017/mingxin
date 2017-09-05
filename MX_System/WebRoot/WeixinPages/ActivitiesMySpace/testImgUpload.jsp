<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
	<div>
		<form id="form1" name="form1" enctype="multipart/form-data"
			method="post" action="1.php">
			<input type="file" id="file" name="file" style="display:none" /> <input
				type="button" name="button" value="点我就像点击“浏览”按钮一样"
				onclick="javascript:openBrowse();" /> <input type="text"
				id="filename" />
		</form>
</body>
<script type="text/javascript">
	function openBrowse() {
		var ie = navigator.appName == "Microsoft Internet Explorer" ? true
				: false;
		if (ie) {
			document.getElementById("file").click();
			document.getElementById("filename").value = document
					.getElementById("file").value;
		} else {
			var a = document.createEvent("MouseEvents");//FF的处理 
			a.initEvent("click", true, true);
			document.getElementById("file").dispatchEvent(a);
		}
	}
</script>
</html>
