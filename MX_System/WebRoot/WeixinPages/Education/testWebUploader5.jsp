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
		<label class="control-label">选择视频</label>
		<input id="input-43" name="uploadFile" type="file"  class="file-loading" >
		<div id="errorBlock" class="help-block" style="word-wrap: break-word;"></div>
		<script>
			$(document).on('ready',
				function() {
					$("#input-43").fileinput({
						language: "zh",
						showPreview: false,
						//showCaption: false,
						allowedFileExtensions: ["mp4"],
						browseClass: "btn btn-success",
						browseLabel: "选择",
						browseIcon: "<i class=\"glyphicon glyphicon-facetime-video\"></i> ",
						removeClass: "btn btn-danger",
						removeLabel: "删除",
						removeIcon: "<i class=\"glyphicon glyphicon-trash\"></i> ",
						uploadClass: "btn btn-info",
						uploadLabel: "上传",
						uploadIcon: "<i class=\"glyphicon glyphicon-upload\"></i> ",
						elErrorContainer: "#errorBlock",
						enctype: 'multipart/form-data',
					    uploadUrl: "uploadFile!ajaxAttachUpload.action", // server upload action
					    uploadAsync: true,
					    maxFileCount: 1
						
					});
					//上传文件成功，回调函数 
				    $('#input-43').on("fileuploaded", function(event, data, previewId, index) {
				    	alert("ddd");
				    var result = data.response; //后台返回的json
				    //console.log(result.status);
				    //console.log(result.id);
				    alert(result.status);
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
				    });

				});
		</script>
	</body>
</html>
