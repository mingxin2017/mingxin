<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>
<head>
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<link href="<%=basePath%>SystemPages/static/h-ui/css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>SystemPages/static/h-ui.admin/css/H-ui.login.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>SystemPages/static/h-ui.admin/css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>SystemPages/lib/Hui-iconfont/1.0.8/iconfont.css" rel="stylesheet" type="text/css" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<script type="text/javascript" src="<%=basePath%>WeixinPages/common/js/jquery-1.11.2.js"></script>
<script type="text/javascript" src="<%=basePath%>WeixinPages/common/js/dialog.js"></script>

<title>鸣心-后台登录</title>
<meta name="keywords" content="H-ui.admin v3.1,H-ui网站后台模版,后台模版下载,后台管理系统模版,HTML后台模版下载">
<meta name="description" content="H-ui.admin v3.1，是一款由国人开发的轻量级扁平化网站后台模板，完全免费开源的网站后台管理系统模版，适合中小型CMS后台系统。">

<script type="text/javascript">
function doLogin(){
	
	var userName=document.getElementById("userName").value;
	var userPwd=document.getElementById("userPwd").value;
	//numTip.innerHTML=num+1;
	if(userName==""||userPwd==""){
		alert("请输入用户名和密码！");
		return;
	}
	 $.ajax({
         url:'userAction/doLogin.action',
         type: 'post',
         data: {'userName':userName,'userPwd':userPwd},
         dataType: 'json',
         success: function (response) {
             if (response.done == '0') {
            	 //document.getElementById('praise_'+commentId).style.color='gray';
                 window.location.href="userAction/gotoIndex.action";
            	 return true;
             } else {
            	 var d=dialog().showModal();
             	d.content(response.msg);
             	setTimeout(function () {
		    			d.close().remove();
		    		}, 2000);
                 //return alert(response.msg);
             }
         },
         error: function (jqXHR, textStatus, errorThrown) {
             if (textStatus == 'timeout') {
                 a_info_alert('请求超时');
                 return false;
             }
			alert("错误");
			//alert(jqXHR);
			 //alert(jqXHR.status);
             //alert(jqXHR.readyState);
			//alert(textStatus);
            //alert(errorThrown);
           
         }
     });
}

function doReset(){
	document.getElementById("userName").value="";
	document.getElementById("userPwd").value="";
}
</script>

</head>
<body>
<input type="hidden" id="TenantId" name="TenantId" value="" />
<div class="header"></div>
<div class="loginWraper">
  <div id="loginform" class="loginBox">
    <div class="form form-horizontal" >
      <div class="row cl">
        <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60d;</i></label>
        <div class="formControls col-xs-8">
          <input id="userName" name="" type="text" placeholder="管理员账号" class="input-text size-L">
        </div>
      </div>
      <div class="row cl">
        <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60e;</i></label>
        <div class="formControls col-xs-8">
          <input id="userPwd" name="" type="password" placeholder="密码" class="input-text size-L">
        </div>
      </div>
      <%--<div class="row cl">
        <div class="formControls col-xs-8 col-xs-offset-3">
          <input class="input-text size-L" type="text" placeholder="验证码" onblur="if(this.value==''){this.value='验证码:'}" onclick="if(this.value=='验证码:'){this.value='';}" value="验证码:" style="width:150px;">
          <img src=""> <a id="kanbuq" href="javascript:;">看不清，换一张</a> </div>
      </div>
      --%><div class="row cl">
        <%--<div class="formControls col-xs-8 col-xs-offset-3">
          <label for="online">
            <input type="checkbox" name="online" id="online" value="">
            使我保持登录状态</label>
        </div>
      --%>
      </div>
      
      <div class="row cl">
        <div class="formControls col-xs-8 col-xs-offset-3">
          <input  type="button" onclick="doLogin();" class="btn btn-success radius size-L" value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;">
          <input type="reset" onclick="doReset();" class="btn btn-danger radius size-L" value="&nbsp;清&nbsp;&nbsp;&nbsp;&nbsp;空&nbsp;">
        </div>
      </div>
    </div>
  </div>
</div>
<div class="footer">Copyright 鸣心文化传播有限公司</div>
<script type="text/javascript" src="<%=basePath%>SystemPages/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="<%=basePath%>SystemPages/static/h-ui/js/H-ui.min.js"></script>
</body>
</html>