<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.weixin.pojo.SNSUserInfo,java.lang.*"%>
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
    <div style="text-align:left;"> 
		<select id="teamId" class="select" style="width:150px;">
			<c:forEach items="${MxUsersReporterTeam}" var="c" varStatus="st">
			   <option value="${c.teamId}">${c.teamName}</option>
			</c:forEach>
		</select>
	</div>
	<div>
		 <button type="button" id="save" onClick="onCommit();" style="width:80px;">提交</button>
	</div>
	<script src="<%=basePath%>WeixinPages/common/richtext/js/jquery-1.11.2.js"></script>
	<script type="text/javascript">
		//接收参数
		var code = "${code}";
		var state = "${state}";
		//提交事件
		function onCommit(){
			  var teamId = $("#teamId").val();
			  var data = { 
						teamId:teamId,
						code:code,
						state:state
			  };
			  //请求添加数据
				$.ajax({
					type : "POST",
					url : 'http://d1a7069951.iask.in/MX_System/mxReporterBusiness!reporterApply.action',
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

