<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />

<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>SystemPages/common/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>SystemPages/common/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>SystemPages/common/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>SystemPages/common/static/h-ui.admin/skin/default/skin.css"
	id="skin" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>SystemPages/common/static/h-ui.admin/css/style.css" />

<link href="<%=basePath%>SystemPages/common/lib/webuploader/0.1.5/webuploader.css"
	rel="stylesheet" type="text/css" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<!--/meta 作为公共模版分离出去-->

<title>管理活动空间</title>
</head>
<body>
	<article class="page-container">
		<form class="form form-horizontal" id="form-activitySpace-edit">
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span
					class="c-red">*</span>活动空间名称：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text"
						value="${activitySpace.myspaceName}" placeholder="活动空间名称"
						id="myspaceName" name="myspaceName">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span
					class="c-red">*</span>空间封面图片：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<div><img id="imgPreview" name="imgPreview" src="${activitySpace.coverImageUrl}" height="150" width="150" /></div>
					<div id="picker" name="picker">选择封面图片</div>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span
					class="c-red">*</span>空间描述：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<textarea name="others" id="others" cols="" rows=""
						class="textarea" placeholder="说点什么...100个字符以内" dragonfly="true"
						onKeyUp="$.Huitextarealength(this,100)"></textarea>
					
				</div>
			</div>
			<div class="row cl">
				<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
					<input class="btn btn-primary radius" type="submit"
						value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
				</div>
			</div>
		</form>
	</article>

	<!--_footer 作为公共模版分离出去-->
	<script type="text/javascript"
		src="<%=basePath%>SystemPages/common/lib/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>SystemPages/common/lib/layer/2.4/layer.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>SystemPages/common/static/h-ui/js/H-ui.min.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>SystemPages/common/static/h-ui.admin/js/H-ui.admin.js"></script>
	<!--/_footer 作为公共模版分离出去-->

	<!--请在下方写此页面业务相关的脚本-->
	<script type="text/javascript"
		src="<%=basePath%>SystemPages/common/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>SystemPages/common/lib/jquery.validation/1.14.0/validate-methods.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>SystemPages/common/lib/jquery.validation/1.14.0/messages_zh.js"></script>
		
	<script type="text/javascript"
		src="<%=basePath%>SystemPages/common/lib/webuploader/0.1.5/webuploader.min.js"></script>
	
	<script type="text/javascript">
		$(function() {
			$('.skin-minimal input').iCheck({
				checkboxClass : 'icheckbox-blue',
				radioClass : 'iradio-blue',
				increaseArea : '20%'
			});

			var uploader = WebUploader.create({

				auto:true,
				
				fileVal:'imgFile',
				
			    // swf文件路径
			    swf: '<%=basePath%>SystemPages/common/lib/webuploader/0.1.5/Uploader.swf',

			    // 文件接收服务端。
			    server: ('uploadCoverImage.action?number='+Math.random()),

			    // 选择文件的按钮。可选。
			    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
			    pick: '#picker',
			    
	             accept: {
	                 title: 'Images',
	                 extensions: 'gif,jpg,jpeg,bmp,png',
	                 mimeTypes: 'image/*'
	             },
			    
			    // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
			    resize: false
			});
			
			
			// 当有文件被添加进队列的时候
			uploader.on( 'fileQueued', function( file ) {
				// 创建缩略图
			    // 如果为非图片文件，可以不用调用此方法。
			    // thumbnailWidth x thumbnailHeight 为 100 x 100
			    uploader.makeThumb( file, function( error, src ) {
			        if ( error ) {
			            //$img.replaceWith('<span>不能预览</span>');
			            return;
			        }
					var img=$('#imgPreview');
			        img.attr( 'src', src );
			    }, 100, 100 );
			});
			
			$("#form-admin-add")
					.validate(
							{
								rules : {
									adminName : {
										required : true,
										minlength : 3,
										maxlength : 16
									},
									password : {
										required : true,
									},
									password2 : {
										required : true,
										equalTo : "#password"
									},
									sex : {
										required : true,
									},
									phone : {
										required : true,
										isPhone : true,
									},
									email : {
										required : true,
										email : true,
									},
									adminRole : {
										required : true,
									},
								},
								onkeyup : false,
								focusCleanup : true,
								success : "valid",
								submitHandler : function(form) {
									var url = 'addAdmin.action?number='
											+ Math.random();
									$(form)
											.ajaxSubmit(
													{
														type : 'post',
														url : url,
														success : function(data) {
															alert(data.msg);
															//if(data.done=='0'){
															//	layer.msg('添加管理员成功',{icon:1,time:15000});
															//}else{
															//	layer.msg('添加管理员失败',{icon:1,time:15000});
															//}
															var index = parent.layer
																	.getFrameIndex(window.name);
															var refresh = parent.document
																	.getElementById('btn-refresh');
															refresh.click();
															parent.layer
																	.close(index);
														},
														error : function(
																XmlHttpRequest,
																textStatus,
																errorThrown) {
															alert('错误');
															//layer.msg('error!',{icon:1,time:1000});
														}
													});

								}
							});
		});
	</script>
	<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>