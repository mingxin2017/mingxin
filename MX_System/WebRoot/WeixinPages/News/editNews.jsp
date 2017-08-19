<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- <%@ page import="com.weixin.pojo.SNSUserInfo,java.lang.*"%> --%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html lang="en" class="feedback">
<head>
<base href="<%=basePath%>">
<title>添加新闻</title>
	<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>WeixinPages/common/css/mui.min.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath%>WeixinPages/common/css/feedback.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath%>WeixinPages/common/h5uploader/html5uploader.css" />
	<link href="<%=basePath%>WeixinPages/common/css/mui.picker.css" rel="stylesheet" />
	<link href="<%=basePath%>WeixinPages/common/css/mui.poppicker.css" rel="stylesheet" />
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
		<header class="mui-bar mui-bar-nav">
			<a id="submit" class="mui-btn mui-btn-blue mui-btn-link mui-pull-left">保存</a>
			<a id="newsList" class="mui-btn mui-btn-blue mui-btn-link mui-pull-right"
				href="weixin!applyNewsList.action">投稿列表</a> 
			<h1 class="mui-title">添加新闻</h1>
		</header>
		<div class="mui-content">
			<div class="mui-content-padded">
				<div class="mui-input-row">
				标题：<input id='head_line' type="text" class="mui-input-clear contact" placeholder="标题..." />
				</div>
				<div class="mui-input-row">
					<input id='lead_text' type="text" class="mui-input-clear contact" placeholder="引言..." />
				</div>
				<div class="mui-input-row">
					<input id='writer_name' type="text" class="mui-input-clear contact" placeholder="笔名..." />
				</div>
				<div class="mui-input-row">
					<input id='subId' type="hidden" class="mui-input-clear contact" placeholder="单位..." />
				    <input id='newsTypeId' type="hidden" class="mui-input-clear contact" placeholder="单位..." />
					<input id='showCityPicker' type="text" class="mui-input-clear contact" placeholder="单位..." />
				</div>
				<div class="row mui-input-row">
					<textarea id='content' style="height:160px" class="mui-input-clear question" placeholder="内容..."></textarea>
				</div>

  				<!-- <div id="upload"></div> -->
  				<form action="weixin!fileUp.action" method="post" enctype="multipart/form-data">
				图片：<input type="file" name="upload"/>
				<input type="submit" value="提交"/>
				</form>
				<code id="response"></code>
			</div>	
		</div>
		
		<script src="<%=basePath%>WeixinPages/common/js/mui.min.js"></script>
<%-- 		<script src="<%=basePath%>WeixinPages/common/js/feedback.js" type="text/javascript" charset="utf-8"></script>
 --%>		<script src="<%=basePath%>WeixinPages/common/js/mui.picker.js"></script>
		<script src="<%=basePath%>WeixinPages/common/js/mui.poppicker.js"></script>
		<script src="<%=basePath%>WeixinPages/common/js/city.data.js" type="text/javascript" charset="utf-8"></script>
		<!-- <script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script> -->
		<script src="<%=basePath%>WeixinPages/common/js/zepto.min.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
			(function($, doc) {
	    
			    /****************************************************/
			    
				$.init();
				$.ready(function() {	
					//-----------------------------------------
					//级联示例
					var cityPicker = new $.PopPicker({
						layer: 2
					});
					cityPicker.setData(cityData);
					var showCityPickerButton = doc.getElementById('showCityPicker');
					var newsTypeId = doc.getElementById('newsTypeId');
					var subId = doc.getElementById('subId');
					showCityPickerButton.addEventListener('tap', function(event) {
						cityPicker.show(function(items) {
							showCityPickerButton.value = "你选择的区域是:" + items[0].text + " " + items[1].text;
							newsTypeId.value = items[0].value;
							subId.value = items[1].value;
							//返回 false 可以阻止选择框的关闭
							//return false;
						});
						
					}, false);
					//-----------------------------------------
				});
				
				
				/*********************************/
var index = 1;
	var size = null;
	var imageIndexIdNum = 0;
	var starIndex = 0;
	var feedback = {
		question: document.getElementById('question'), 
		contact: document.getElementById('contact'), 
		
		headLine: document.getElementById('head_line'),
		leadText: document.getElementById('lead_text'),
		writerName: document.getElementById('writer_name'),
		content: document.getElementById('content'),
		submitBtn: document.getElementById('submit'),
		newsTypeId: document.getElementById('newsTypeId'),
		subId : doc.getElementById('subId')
	};
	
	var url = 'https://service.dcloud.net.cn/feedback';
	feedback.files = [];
	feedback.uploader = null;  
	feedback.deviceInfo = null; 
	mui.plusReady(function() {
		//设备信息，无需修改
		feedback.deviceInfo = {
			appid: plus.runtime.appid, 
			imei: plus.device.imei, //设备标识
			images: feedback.files, //图片文件
			p: mui.os.android ? 'a' : 'i', //平台类型，i表示iOS平台，a表示Android平台。
			md: plus.device.model, //设备型号
			app_version: plus.runtime.version,
			plus_version: plus.runtime.innerVersion, //基座版本号
			os:  mui.os.version,
			net: ''+plus.networkinfo.getCurrentType()
		};
	}); 
	/**
	 *提交成功之后，恢复表单项 
	 */
	feedback.clearForm = function() {
		feedback.question.value = '';
		feedback.contact.value = '';
		feedback.imageList.innerHTML = '';
		feedback.newPlaceholder();
		feedback.files = [];
		index = 0;
		size = 0;
		imageIndexIdNum = 0;
		starIndex = 0;
		//清除所有星标
		mui('.icons i').each(function (index,element) {
			if (element.classList.contains('mui-icon-star-filled')) {
				element.classList.add('mui-icon-star')
	  			element.classList.remove('mui-icon-star-filled')
			}
		})
	};
	feedback.getFileInputArray = function() {
		//return [].slice.call(feedback.imageList.querySelectorAll('.file'));
	};
	feedback.addFile = function(path) {
		feedback.files.push({name:"images"+index,path:path,id:"img-"+index});
		index++;
	};
	/**
	 * 初始化图片域占位
	 */
	 
	feedback.newPlaceholder = function() {
		var fileInputArray = feedback.getFileInputArray();
		if (fileInputArray &&
			fileInputArray.length > 0 &&
			fileInputArray[fileInputArray.length - 1].parentNode.classList.contains('space')) {
			return;
		};
		imageIndexIdNum++;
		var placeholder = document.createElement('div');
		placeholder.setAttribute('class', 'image-item space');
		var up = document.createElement("div");
		up.setAttribute('class','image-up')
		//删除图片
		var closeButton = document.createElement('div');
		closeButton.setAttribute('class', 'image-close');
		closeButton.innerHTML = 'X';
		closeButton.id = "img-"+index;
		//小X的点击事件
		closeButton.addEventListener('tap', function(event) {
 			setTimeout(function() {
				for(var temp=0;temp<feedback.files.length;temp++){
					if(feedback.files[temp].id==closeButton.id){
						feedback.files.splice(temp,1);
					}
				}
				feedback.imageList.removeChild(placeholder);
			}, 0);
			return false;
		}, false);
		
		//添加图片
		var fileInput = document.createElement('div');
		fileInput.setAttribute('class', 'file');
		fileInput.setAttribute('id', 'image-' + imageIndexIdNum);
		fileInput.addEventListener('tap', function(event) {
			var self = this;
			var index = (this.id).substr(-1);
			
			//调用jssdk选取图片
			wx.chooseImage({
			  count: 1, // 默认9
			  sizeType: ['original', 'compressed'],// 可以指定是原图还是压缩图，默认二者都有
			  sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
		      success: function (res) {
		        images.localId = res.localIds;// 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
		        //多图片添加
		        for(var i=0;i<images.localId.length;i++){
					//文件进行限制
					if (!self.parentNode.classList.contains('space')) { 
					    //已有图片、替换图片
						feedback.files.splice(index-1,1,{name:"images"+index,path:images.localId[i]});
					} else { 
						//添加图片
						placeholder.classList.remove('space');
						feedback.addFile(images.localId[i]);
						feedback.newPlaceholder();
					}
					up.classList.remove('image-up');
					placeholder.style.backgroundImage = 'url(' + images.localId[i] + ')';
			    }
		      }
		    });
		}, false);

		placeholder.appendChild(closeButton);
		placeholder.appendChild(up);
		placeholder.appendChild(fileInput);
		feedback.imageList.appendChild(placeholder);
	};
	feedback.newPlaceholder();
	
	//ajax请求
	if(mui.os.plus){
		mui.plusReady(function () {
			if(plus.networkinfo.getCurrentType()==plus.networkinfo.CONNECTION_NONE){
				network = false;
			}
		});
	}
	var respnoseEl = document.getElementById("response");
	//成功响应的回调函数
	var success = function(response) {
		var dataType = 'json';
		if (dataType === 'json') {
			response = JSON.stringify(response);
		} else if (dataType === 'xml') {
			response = new XMLSerializer().serializeToString(response).replace(/</g, "&lt;").replace(/>/g, "&gt;");
		}
		respnoseEl.innerHTML = response;
	};
	var ajax = function() {
	
		//利用RunJS的Echo Ajax功能测试
		var url = 'weixin!addNews.action';
		
		//请求方式，默认为Get；
		var type = 'post';
		//预期服务器范围的数据类型
		var dataType = 'json';
		//发送数据
		var data = {
			headLine : feedback.headLine.value,
			leadText : feedback.leadText.value,
			writerName : feedback.writerName.value,
			content : feedback.content.value,
			newsTypeId : feedback.newsTypeId.value,
			subId : feedback.subId.value,
			code : "${code}",
			state : "${state}"
		};
		respnoseEl.innerHTML = '正在请求中...';
		if (type === 'get') {
			if (dataType === 'json') {
				$.getJSON(url, data, success);
			} else {
				$.get(url, data, success, dataType);
			}
		} else if (type === 'post') {
			$.post(url, data, success, dataType);
		}
	};
	
	//保存,进行格式校验
	feedback.submitBtn.addEventListener('tap', function(event) {
	    
	    if(feedback.headLine.value == ''||
		    feedback.leadText.value == ''||
		    feedback.writerName.value == ''||
		    feedback.content.value == ''){
	    	return mui.toast('请填写完整信息！');
	    }
		if (feedback.headLine.value.length > 5) {
			return mui.toast('标题信息超长,请重新填写！');
		}
		if (feedback.leadText.value.length > 20) {
			return mui.toast('引言信息超长,请重新填写！');
		}
		if (feedback.writerName.value.length > 20) {
			return mui.toast('笔名信息超长,请重新填写！');
		}
		if (feedback.content.value.length > 200) {
			return mui.toast('内容信息超长,请重新填写！');
		}
        
		ajax();
		
		
	}, false);
	
	
	
	//上传
	feedback.send = function(content) {
		feedback.uploader = plus.uploader.createUpload(url, {
			method: 'POST'
		}, function(upload, status) {
//			plus.nativeUI.closeWaiting()
			console.log("upload cb:"+upload.responseText);
			if(status==200){
				var data = JSON.parse(upload.responseText);
				//上传成功，重置表单
				if (data.ret === 0 && data.desc === 'Success') {
//					mui.toast('反馈成功~')
					console.log("upload success");
//					feedback.clearForm();
				}
			}else{
				console.log("upload fail");
			}
			
		});
		//添加上传数据
		mui.each(content, function(index, element) {
			if (index !== 'images') {
				console.log("addData:"+index+","+element);
				feedback.uploader.addData(index, element)
			} 
		});
		//添加上传文件
		mui.each(feedback.files, function(index, element) {
			var f = feedback.files[index];
			console.log("addFile:"+JSON.stringify(f));
			feedback.uploader.addFile(f.path, {
				key: f.name
			});
		});
		//开始上传任务
		feedback.uploader.start();
		mui.alert("感谢反馈，点击确定关闭","问题反馈","确定",function () {
			feedback.clearForm();
			mui.back();
		});
	};
			})(mui, document);
			
			

			
		</script>
		<script src="<%=basePath%>WeixinPages/common/h5uploader/jquery.js"></script>
		<script src="<%=basePath%>WeixinPages/common/h5uploader/jquery.html5uploader.js"></script>
<script type="text/javascript">

/* $(function(){

	$('#upload').html5uploader({

		auto:false,

		multi:true,

		removeTimeout:9999999,

		url:'weixin!ImageUpLoader.action',

		onUploadStart:function(){

			alert('开始上传');

			},

		onInit:function(){

			alert('初始化');

			},

		onUploadComplete:function(){

			alert('上传完成');

			},
 		onUploadError:function(file,error,state){
 			alert(state);
 		}
		});

	}); */

</script>
	</body>
</html>
