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
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title></title>
    
    <link href="<%=basePath%>WeixinPages/common/css/webuploader/stream-v1.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath%>WeixinPages/common/js/webuploader/jquery-ui.min.js"></script>
<script type="text/javascript" src="<%=basePath%>WeixinPages/common/js/webuploader/webuploader.min.js"></script>

    <link rel="stylesheet" type="text/css" href="<%=basePath%>WeixinPages/common/css/webuploader/webuploader.css">
   <!--引入JS-->
<script type="text/javascript" src="<%=basePath%>WeixinPages/common/js/webuploader/webuploader.js"></script>
 <script src="<%=basePath%>WeixinPages/common/js/webuploader/md5.js" type="text/javascript" charset="utf-8"></script>

<script type="text/javascript">
/*********************************WebUpload 单文件上传 begin*****************************************/
$(function(){
    var $list = $("#thelist");
    //var $list=document.getElementById();
 	// 实例化   
    var  uploader = WebUploader.create({
           auto:false, //是否自动上传
            pick: {
                id: '#attach',
                name:"file",  //这个地方 name 没什么用，虽然打开调试器，input的名字确实改过来了。但是提交到后台取不到文件。如果想自定义file的name属性，还是要和fileVal 配合使用。
                label: '选择文件',
                multiple:false            //默认为true，true表示可以多选文件，HTML5的属性
            },
            swf: '<%=basePath%>WeixinPages/common/js/webuploader/Uploader.swf',  
            ////在这里必需要引入swf文件，webuploader初始化要用
            //fileVal:'multiFile',  //自定义file的name属性，我用的版本是0.1.5 ,打开客户端调试器发现生成的input 的name 没改过来。
                                             //名字还是默认的file,但不是没用哦。虽然客户端名字没改变，但是提交到到后台，是要用multiFile 这个对象来取文件的，用file 是取不到文件的
                                             // 建议作者有时间把这个地方改改啊，搞死人了。。
            server: "uploadFile!ajaxAttachUpload.action",
            duplicate:true,//是否可重复选择同一文件
            resize: false,
            formData: {
                "status":"file",
                "contentsDto.contentsId":"0000004730",
                "uploadNum":"0000004730",
                "existFlg":'false'
            },  
            compress: null,//图片不压缩
            chunked: true,  //分片处理
            chunkSize: 5 * 1024 * 1024, //每片5M
            chunkRetry:false,//如果失败，则不重试
            threads:2,//上传并发数。允许同时最大上传进程数。
            // runtimeOrder: 'flash',  
            // 禁掉全局的拖拽功能。这样不会出现图片拖进页面的时候，把图片打开。  
            disableGlobalDnd: true
        });  

        // 当有文件添加进来的时候
       uploader.on("fileQueued",function(file) {
    	   alert(55555);
           console.log("fileQueued:");
           $list.append( "<div id='"+  file.id + "' class='item'>" +
               "<h4 class='info'>" + file.name + "</h4>" +
               "<p class='state'>等待上传...</p>" +
           "</div>" );
       });

       //当所有文件上传结束时触发
       uploader.on("uploadFinished",function(){
           console.log("uploadFinished:");
       });

        //当某个文件上传到服务端响应后，会派送此事件来询问服务端响应是否有效。
        uploader.on("uploadAccept",function(object,ret){
            //服务器响应了
            //ret._raw  类似于 data
            var data =JSON.parse(ret._raw);
            if(data.resultCode != "1" && data.resultCode != "3"){
                if(data.resultCode == "9"){
                    uploader.reset();
                    alert("error");
                    return false;
                }
            }else{
                //E05017
                uploader.reset();
                alert("error");
                return false;
            }
           });

       //当文件上传成功时触发。
         uploader.on( "uploadSuccess", function( file ) {
           $( "#"+file.id ).find("p.state").text("已上传");
       });

       uploader.on( "uploadError", function( file ) {
           $( "#"+file.id ).find("p.state").text("上传出错");
           uploader.cancelFile(file);
           uploader.removeFile(file,true);
           uploader.reset();
       });


       $("#upload").on("click", function() {
    	   alert(1111);
           uploader.upload();
       });

});
/*********************************WebUpload 单文件上传 end*******************************************/
</script>
<script type="text/javascript" src="../common/js/webuploader/jquery-1.10.2.min.js"></script></head>
<body>

<div id="uploader" class="wu-example">
    <!--用来存放文件信息-->
    <div>1</div><div>2</div><div>3</div><div>4</div>
    <div id="thelist" class="uploader-list">333333</div>
    <div class="btns">
        <div id="attach"></div>
        <input type="button" value="上传" id="upload"/> 
    </div>
</div>


</body>
</html>