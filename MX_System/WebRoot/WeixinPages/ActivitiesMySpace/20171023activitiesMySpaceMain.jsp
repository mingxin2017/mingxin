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
	<script src="<%=basePath%>WeixinPages/common/imgDeal/dist/lrz.bundle.js?v=09bcc27"></script>
	<script type="text/javascript" src="<%=basePath%>WeixinPages/common/js/jquery-1.11.2.js"></script>
	<script type="text/javascript" src="<%=basePath%>WeixinPages/common/js/dialog.js"></script>
	
	
<style type="text/css">
.rich{
	width:80px;
    border:1px solid #0997F7;
    overflow: auto;
}
.rich:empty:before{
    content:attr(placeholder);
    font-size: 16px;
    color: #999;
}
.rich:focus:before{
    content:none;
}
.footer-btn {
  text-align: center;
  padding-top: 1px;
}
.footer-btn .upload-img {
  height: 8%;
  min-height:25px;
  display: inline-block;
  vertical-align: bottom;
  width: 25px;
  margin-right: 10px;
  background-size: 100%;
}
.input-file-css{
	position:absolute;
	left:0;
	opacity:0;
	width:100%;
}

.SubBtn{
	width:100%;
	height:10%;
	min-height:30px;
	text-align:center; /*水平居中*/
	
}
</style>
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
	
	var current = document.querySelector(".mui-tab-item.mui-active");
	//alert(current);
	//alert(obj);
	if (obj !== current) {
		var dl=dialog('加载中...').showModal();
		current.classList.remove('mui-active');
		obj.classList.add('mui-active');
	
	
	//更换标题
	var title = document.getElementById("title");
	var buttonValue=obj.querySelector('.mui-tab-label').innerHTML;
	title.innerHTML = buttonValue;
	
	//更换按钮名称
	var operateButton=document.getElementById("operate");
	
	var url;
	if(pageTag==1){
		operateButton.innerHTML="发帖";
		url='getActivitiesMySpaceCommentList.action';
	}else if(pageTag==2){
		operateButton.innerHTML="上传照片";
		url='getActivitiesMySpaceMaterialList.action';
	}else if(pageTag==3){
		if(${sessionScope.userInfo.userTypeId}==1100){//活动发起人的类型为1100
			operateButton.innerHTML="发出邀请";
			
		}else{
			operateButton.innerHTML="";
		}
		url='getActivitiesMySpaceUsersList.action';
		
	}else if(pageTag==4){
		operateButton.innerHTML="";
		url='getActivitiesMySpaceMine.action';
	}else{return;}
	document.getElementById('mainContent').src=url;
	$("#mainContent").load(function(){//iframe加载完成后关闭加载框
        dl.close().remove();
    });  
	}else{return;}
}

function operate(){
	var operate=document.getElementById('operate').innerHTML;
	if(operate=="发帖"){
		var d = dialog({
			fixed: true,
			content: '<textarea autofocus id="subTxt" rows="3" cols="25" placeholder="发帖内容">',
			button : [ {
							value : '发送',
							callback : function() {
								var txt = $('#subTxt').val();//获取输入的值
								//var myspaceId=$('#myspaceId').val();
								//var userId=$('#userId').val();//用户id
								doSaveMyspaceComment(${sessionScope.userInfo.userId},${sessionScope.myspaceId},txt);
							},
							autofocus : true
						}, {
							value : '取消'
						} ]

					}).showModal();
		} else if (operate == "上传照片") {
			var d = dialog({
				fixed: true,
				content: '<div id="showImage" class="rich">预览</div><div class="footer-btn " ><span ><i class="mui-icon mui-icon-image"></i>选图</span><input onchange="showImage(this);" class="input-file-css" type="file" capture="camera" accept="image/*" name="imgFile" id="imgFile"></div>',
				
				button : [ {
							value : '取消'
							},{
								value : '上传',
								callback : function() {
									//alert("11111");
									var imgFile = document.getElementById('imgFile').files[0];//获取选择的文件
									//alert(imgFile);
									var myspaceId=$('#myspaceId').val();
									var userId=$('#userId').val();//用户id
									doUploadImage(userId,myspaceId,imgFile);
								},
								autofocus : true
							} ]

						}).showModal();
		} else if (operate == "发出邀请") {
			
			var d = dialog({
				fixed: true,
				//title:'确定生成邀请链接？',
				content:'<div id="showInviteCode">确定生成活动邀请码？</div>',
				button : [ {
					value : '取消'
					},{
						value : '生成',
						autofocus : true,
						callback : function() {
							$.ajax({
							    type: "POST",
							    url: "DoCreateInviteCodeUrl.action", //生成活动邀请链接
							    data: {"initiator_userId":${userInfo.userId},"myspaceId":${sessionScope.myspaceId}},
							    dataType:"json",
							    async:false,
							    cache:false,
							    success: function(data){
							    	if(data.done=='0'){
							    		//document.getElementById('showInviteCode').innerHTML='<input type=\"text\" value=\"'+data.inviteCodeUrl+'\"/>';
							    		
							    		var dd = dialog({
							    				title:'复制以下内容，发送给参加人员',
							    				//content:'<div>该邀请码有效期为24小时</div><input type=\"text\" value=\"'+data.inviteCode+'\"/>\r\n<',
							    				content:'<div>邀请码有效期为24小时，过期需要重新生成</div><textarea rows=\"3\" cols=\"25\">邀请码('+data.inviteCode+')\n复制该信息至鸣心公众号-个人中心-个人空间粘贴即可接受邀请。</textarea>',
							                    okValue: '完成',
							                    ok: function() {
							                        dd.close().remove();
							                    }
							    		}).showModal();
							    		
							    		//setTimeout(function () {
							    		//	dd.close().remove();
							    		//}, 2000);
							    	}else{
							    		var dd = dialog('生成失败');
							    		setTimeout(function () {
							    			dd.close().remove();
							    		}, 1000);
							    	}
								},
								error: function(json){
									var ddd = dialog('提交数据异常，请刷新后重试...').show();
									setTimeout(function () {
										ddd.close().remove();
						    		}, 1500);
								}
						    });
						
					}
					}]
				
			
			}).showModal();
			//setTimeout(function () {
    		//	d.close().remove();
    		//}, 1500);
			
		} else if (operate == "刷新") {
			alert(operate);
		} else {
			alert("错误");
			return;
		}

	}

	//上传图片显示缩略图
	function showImage(obj){
		//alert(obj.files[0]);
		var file=obj.files[0];
		var img = new Image();
		img.width=80;
		var url = img.src = URL.createObjectURL(file);
		var $img = $(img);
        img.onload = function() {
            URL.revokeObjectURL(url);
            $('#showImage').empty().append($img);
        }
	}


	//上传图片事件
	function doUploadImage(userId,myspaceId,imgFile){
		var d=dialog({content:'上传中...'}).showModal();//加载中弹出框
		//alert(1111);
         lrz(imgFile, {width: 1080})
            .then(function (rst) {
            	//alert(222);
                $.ajax({
                    url: 'UploadImage.action',
                    type: 'post',
                    data: {img: rst.base64,userId:userId,myspaceId:myspaceId},
                    dataType: 'json',
                    timeout: 200000,
                    success: function (response) {
                        if (response.done == '0') {
                        	d.content(response.msg);
                        	setTimeout(function () {
        		    			d.close().remove();
        		    		}, 1500);
                        	//刷新页面
                        	document.getElementById('mainContent').src= "getActivitiesMySpaceMaterialList.action";
                        	
                            return true;
                        } else {
                        	d.content(response.msg);
                        	setTimeout(function () {
        		    			d.close().remove();
        		    		}, 1500);
                            return alert(response.msg);
                        }
                    },

                    error: function (jqXHR, textStatus, errorThrown) {

                        if (textStatus == 'timeout') {
                            a_info_alert('请求超时');

                            return false;
                        }

                        alert(jqXHR.responseText);
                    }
                });

            })
            .catch(function (err) {

            })
            .always(function () {

            });
	}
	
	//退出个人空间
	function quitPage() {
		wx.closeWindow();
	}
	
	//保存空间评论
	function doSaveMyspaceComment(userId,myspaceId,txt){
		$.ajax({
		    type: "POST",
		    url: "DoSaveActivitiesMySpaceComment.action", //orderModifyStatus
		    data: {"userId":userId,"myspaceId":myspaceId,"txt":txt},
		    dataType:"json",
		    async:false,
		    cache:false,
		    success: function(data){
		    	if(data.done=='0'){
		    		document.getElementById('mainContent').src='getActivitiesMySpaceCommentList.action';
		    		var dd = dialog('发送成功').show();
		    		setTimeout(function () {
		    			dd.close().remove();
		    		}, 2000);
		    	}else{
		    		var dd = dialog('发送失败');
		    	}
			},
			error: function(json){
				var ddd = dialog('提交数据异常，请刷新后重试...').show();
				setTimeout(function () {
					ddd.close().remove();
	    		}, 1500);
			}
	    });
	}
	
</script>
</head>

<body>

	<header class="mui-bar mui-bar-nav" id="myspaceMainHeader"> 
		<a class="mui-btn mui-btn-blue mui-btn-link mui-pull-left" onclick="quitPage();">退出</a>
		<h1 id="title" class="mui-title">讨论区</h1>
		<div id="operate" class="mui-btn mui-btn-blue mui-btn-link mui-pull-right" onclick="operate();">发帖</div>
	</header>
	<nav class="mui-bar mui-bar-tab" id="footerTab"> 
		<a  class="mui-tab-item mui-active" href= "JavaScript:void(0);" onclick="showIframe(1,this);" > 
			<span class="mui-icon mui-icon-chat"><!-- <span class="mui-badge">8</span> --></span> 
			<span class="mui-tab-label">讨论区</span>
		</a> 
		<a  class="mui-tab-item"  href= "JavaScript:void(0);" onclick="showIframe(2,this);" > 
			<span class="mui-icon mui-icon-image">
			<!-- <span class="mui-badge">3</span> -->
			</span> 
			<span class="mui-tab-label">照片墙</span> 
		</a> 
		<a  class="mui-tab-item" href= "JavaScript:void(0);" onclick="showIframe(3,this);" > 
			<span class="mui-icon mui-icon-contact"></span> 
			<span class="mui-tab-label">通讯录</span>
		</a> 
		<a  class="mui-tab-item" href= "JavaScript:void(0);" onclick="showIframe(4,this);" >
			<span class="mui-icon mui-icon-gear"></span> 
			<span class="mui-tab-label">个人空间</span> 
		</a> 
	</nav>
	<div id="iframeContent" class="mui-content" >
		<input id="myspaceId" name="myspaceId" type="hidden" value="${sessionScope.myspaceId}"/>
		<input id="userId" name="userId" type="hidden" value="${sessionScope.userInfo.userId}"/>
		<iframe style="width:100%"  id="mainContent" name="mainContent"
			 src="getActivitiesMySpaceCommentList.action" />
	</div>
</body>

</html>



