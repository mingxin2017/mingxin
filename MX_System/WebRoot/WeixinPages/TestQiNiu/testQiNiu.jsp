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
	
<script type="text/javascript" src="../common/js/testQiNiu/plupload.full.min.js"></script>

</head>
<body>
    <!-- 这里我们只使用最基本的html结构：一个选择文件的按钮，一个开始上传文件的按钮(甚至该按钮也可以不要) -->
    <p>
        <button id="browse">选择文件</button>
        <button id="start_upload">开始上传</button>
    </p>
    <script>
    //实例化一个plupload上传对象
    var uploader = new plupload.Uploader({
        browse_button : 'browse', //触发文件选择对话框的按钮，为那个元素id
        url : 'images/upload.shtml', //服务器端的上传页面地址
        flash_swf_url : 'js/Moxie.swf', //swf文件，当需要使用swf方式进行上传时需要配置该参数
        max_file_size: '2mb',//限制为2MB
        filters: [{title: "Image files",extensions: "jpg,gif,png"}]//图片限制
        silverlight_xap_url : 'js/Moxie.xap' //silverlight文件，当需要使用silverlight方式进行上传时需要配置该参数
    });    
    //在实例对象上调用init()方法进行初始化
    uploader.init();
    //图片选择完毕触发
    uploader.bind('FilesAdded',function(uploader,files){
       
    });
	//图片上传成功触发，ps:data是返回值（第三个参数是返回值）
	uploader.bind('FileUploaded',function(uploader,files,data){
	});
	//会在文件上传过程中不断触发，可以用此事件来显示上传进度监听（比如说上传进度）
    uploader.bind('UploadProgress',function(uploader,file){
       
    });
    //还有N多呢.....，具体参考链接==>http://www.sojson.com/jc_plupload.html 的各种事件说明。
    //最后给"开始上传"按钮注册事件
    document.getElementById('start_upload').onclick = function(){
        uploader.start(); //调用实例对象的start()方法开始上传文件，当然你也可以在其他地方调用该方法
    }
    </script>
</body>
</html>

