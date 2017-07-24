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
	
<head>
    <title></title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="<%=basePath%>WeixinPages/common/fileupload/css/fileinput.css" type="text/css"></link>
	<link rel="stylesheet" href="<%=basePath%>WeixinPages/common/fileupload/css/theme.css" type="text/css"></link>
	
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="<%=basePath%>WeixinPages/common/fileupload/js/fileinput.js">
	</script><script type="text/javascript" src="<%=basePath%>WeixinPages/common/fileupload/js/zh.js"></script>
</head>
<body>
		<label class="control-label">Select File</label>
		<form enctype="multipart/form-data"  method="post">
		<input id="myFile" name="myFile" type="file" class="file-loading" accept="audio/*"/>
		</form>
		<div id="errorBlock" class="help-block"></div>
		<script>
		$(document).on('ready',
				function() {
			$input=$("#myFile");
			$input.fileinput({
						language: "zh",
						showPreview: false,
						//browseClass: "btn btn-primary",
						showUpload: true, //是否显示上传按钮
						//showCaption: false,//是否显示标题
						allowedFileExtensions: ["amr","wma","mp3","awb","aac"],//主要的手机音频格式
						//allowedFileExtensions: ["MOV","MP4","AVI","3GP","mkv"],//主要的手机视频格式RM、rmvb、3gp、mp4、avi、flv、f4v、mpg、vob、dat、wmv、asf、mkv、dv、mov、ts、webm
						//allowedFileTypes : ['audio'],
						elErrorContainer: "#errorBlock",
						//enctype: 'multipart/form-data',
					    uploadUrl: "uploadFile!ajaxAttachUpload.action", // server upload action
					    uploadAsync: false,
					    maxFileCount: 1
						
					}).on("filebatchselected", function(event, files) {
					    // trigger upload method immediately after files are selected
					    //$input.fileinput("upload");
					});
					//上传文件成功，回调函数 
				    //$('#input-43').on("fileuploaded", function(event, data, previewId, index) {
				    //	alert("ddd");
				    //var result = data.response; //后台返回的json
				    //console.log(result.status);
				    //console.log(result.id);
				   // alert(result.status);
				    //$('#picid').val(result.id);//拿到后台传回来的id，给图片的id赋值序列化表单用
				    //如果是上传多张图
				    /*
				    //计数标记，用于确保全部图片都上传成功了，再提交表单信息
				    var fileCount = $('#file-pic').fileinput('getFilesCount');
				    if(fileCount==1){
				    $.ajax({//上传文件成功后再保存图片信息
				        url:'BannerPicAction!savaForm.action',
				        data:$('#form1').serialize(),//form表单的值
				        success:function(data,status){
				            ...
				        },
				        cache:false,                    //不缓存
				    });
				    }
				    
				    $.ajax({//上传文件成功后再保存图片信息
				        url:'BannerPicAction!savaForm.action',
				        type:'post',
				        dataType:'json',
				        data:$('#form1').serialize(),//form表单的值
				        success:function(data,status){
				            if (status == "success") {

				                if(data.status == "success"){//提交成功

				                    //跳转回添加页面

				                }else{
				                    alert("添加失败,编码的错误!");
				                }

				                } else {
				                    alert("添加失败,ajax请求返回失败!");
				            }
				        },
				        cache:false,                    //不缓存
				    });*/
				    //});

				});
		</script>
	</body>
</html>
