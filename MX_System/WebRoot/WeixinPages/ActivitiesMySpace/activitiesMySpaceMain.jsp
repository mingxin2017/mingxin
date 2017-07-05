<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="utf-8">
<title>鸣心-活动空间</title>
<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
<link rel="stylesheet"
	href="<%=basePath%>WeixinPages/common/css/mui.min.css">
	<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
	<script type="text/javascript" src="<%=basePath%>WeixinPages/common/js/jquery-1.11.2.js"></script>
	<script type="text/javascript" src="<%=basePath%>WeixinPages/common/js/dialog.js"></script>
	
<script type="text/javascript">
$(document).ready(function() {
    //设置iframe自适应屏幕高度
    var header = document.getElementById("myspaceMainHeader");
    var headerHeight=header.offsetHeight;
    var footer=document.getElementById("footerTab");
    var footerHeiht=footer.offsetHeight;
	var h = document.body.offsetHeight; //获取网页可见区域高度
	var iframe=document.getElementById("mainContent");
	iframe.height=(h-footerHeiht-headerHeight);
	
	//wx.hideAllNonBaseMenuItems();//隐藏右上角微信所有非基础按钮
});


function showIframe(pageTag,obj){
	//alert(1010);
	var current = document.querySelector(".mui-bar-tab>.mui-tab-item.mui-active");
	if (obj !== current) {
		current.classList.remove('mui-active');
		obj.classList.add('mui-active');
	}else{return;}
	
	//更换标题
	var title = document.getElementById("title");
	var buttonValue=obj.querySelector('.mui-tab-label').innerHTML;
	title.innerHTML = buttonValue;
	
	//更换按钮名称
	var operateButton=document.getElementById("operate");
	
	var url;
	if(pageTag==1){
		operateButton.innerHTML="发言";
		url='activitiesMySpace!getActivitiesMySpaceCommentList.action';
	}else if(pageTag==2){
		operateButton.innerHTML="上传照片";
		url='activitiesMySpace!getActivitiesMySpaceMaterialList.action';
	}else if(pageTag==3){
		operateButton.innerHTML="刷新";
		url='activitiesMySpace!getActivitiesMySpaceUsersList.action';
	}else if(pageTag==4){
		operateButton.innerHTML="";
		url='activitiesMySpace!getActivitiesMySpaceMine.action';
	}else{return;}
	document.getElementById('mainContent').src=url;
	
}

function operate(){
	
	var operate=document.getElementById('operate').innerHTML;
	//alert(operate);
	if(operate=="发言"){
		//alert(operate);
		var d = dialog({
			fixed: true,
			title:'输入内容',
			content: '<textarea autofocus id="subTxt" rows="3" cols="25" placeholder="发言内容">',
			
			button : [ {
							value : '发送',
							callback : function() {
								var txt = $('#subTxt').val();//获取输入的值
								var myspaceId=$('#myspaceId').val();
								var userId=$('#userId').val();//用户id
								//var txtEncode=encodeURI(txt); //对文本框内容进行编码
								//txtEncode=encodeURI(txtEncode); //对文本框内容进行编码
			  					//var url="activitiesMySpace!DoSaveActivitiesMySpaceComment.action?myspaceId="+myspaceId+"&myspaceComment="+txtEncode;
			        			//window.location.href=url;
								//var dd=dialog().showModal();
								//this.content(txt);
								//return false;
								doSaveMyspaceComment(userId,myspaceId,txt);
							},
							autofocus : true
						}, {
							value : '取消'
						} ]

					}).showModal();
		} else if (operate == "上传照片") {
			alert(operate);
		} else if (operate == "刷新") {
			alert(operate);
		} else {
			alert("错误");
			return;
		}

	}

	function quitPage() {
		wx.closeWindow();
	}
	
	function doSaveMyspaceComment(userId,myspaceId,txt){
		$.ajax({
		    type: "POST",
		    url: "activitiesMySpace!DoSaveActivitiesMySpaceComment.action", //orderModifyStatus
		    data: {"userId":userId,"myspaceId":myspaceId,"txt":txt},
		    dataType:"json",
		    async:false,
		    cache:false,
		    success: function(data){
		    	if(data.done=='0'){
		    		var dd = dialog('发送成功').show();
		    		document.getElementById('mainContent').src='activitiesMySpace!getActivitiesMySpaceCommentList.action';
		    		dd.close().remove();
		    	}else{
		    		var dd = dialog('发送失败');
		    	}
			},
			error: function(json){
				var ddd = dialog('提交数据异常，请刷新后重试...');
			}
	    });
	}
	
</script>
</head>

<body>

	<header class="mui-bar mui-bar-nav" id="myspaceMainHeader"> 
		<a class="mui-btn mui-btn-blue mui-btn-link mui-pull-left" onclick="quitPage();">退出空间</a>
		<h1 id="title" class="mui-title">讨论区</h1>
		<button id="operate" class="mui-btn mui-btn-blue mui-btn-link mui-pull-right" onclick="operate();">发言</button>
	</header>
	<nav class="mui-bar mui-bar-tab" id="footerTab"> 
		<a  class="mui-tab-item mui-active" href= "JavaScript:void(0);" onclick="showIframe(1,this);" > 
			<span class="mui-icon mui-icon-chat"><span class="mui-badge">8</span></span> 
			<span class="mui-tab-label">讨论区</span>
		</a> 
		<a  class="mui-tab-item"  href= "JavaScript:void(0);" onclick="showIframe(2,this);" > 
			<span class="mui-icon mui-icon-image">
			<span class="mui-badge">3</span></span> 
			<span class="mui-tab-label">照片墙</span> 
		</a> 
		<a  class="mui-tab-item" href= "JavaScript:void(0);" onclick="showIframe(3,this);" > 
			<span class="mui-icon mui-icon-contact"></span> 
			<span class="mui-tab-label">通讯录</span>
		</a> 
		<a  class="mui-tab-item" href= "JavaScript:void(0);" onclick="showIframe(4,this);" >
			<span class="mui-icon mui-icon-gear"></span> 
			<span class="mui-tab-label">我的空间</span> 
		</a> 
	</nav>
	<div id="iframeContent" class="mui-content" >
		<input id="myspaceId" name="myspaceId" type="hidden" value="123"/>
		<input id="userId" name="userId" type="hidden" value="321"/>
		<iframe style="width:100%"  id="mainContent" name="mainContent"
			 src="activitiesMySpace!getActivitiesMySpaceCommentList.action" />
	</div>
</body>

</html>



