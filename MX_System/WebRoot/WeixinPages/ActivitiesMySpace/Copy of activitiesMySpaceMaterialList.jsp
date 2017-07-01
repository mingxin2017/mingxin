<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
<meta charset="utf-8">
    <title>图片区</title>
    <meta name="viewport"
	content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<!--标准mui.css-->
	<link rel="stylesheet" href="<%=basePath%>WeixinPages/common/css/mui.min.css">
    <link rel="stylesheet" href="<%=basePath%>WeixinPages/common/css/myspaceMaterial.css">
    <script src="<%=basePath%>WeixinPages/common/js/mui.min.js"></script>
	<script src="<%=basePath%>WeixinPages/common/js/mui.zoom.js"></script>
	<script src="<%=basePath%>WeixinPages/common/js/mui.previewimage.js"></script>
  </head>
  
  <body>
  
   	<div class="mui-card">
		<div class="mui-card-header mui-card-media">
			<img src="http://wx.qlogo.cn/mmopen/gITwFOywPbkCx8BxwYc41oAGjuBeFianAbtHl8URmaCMTe9lib6EicNuHSibGJzSfT6Y88Nos1poHITnB7vUs7foHphNpibcgFEja/0" />
			<div class="mui-media-body">
				小M
				<p>更新于 2016-06-30 15:30</p>
			</div>
		</div>
		<div class="mui-card-content" style="height:120px;">
			<div class="mui-slider">
				<div class="mui-slider-group ">
					<div class="mui-slider-item mui-slider-item-duplicate">
						<ul class="mui-table-view mui-grid-view">
		                    <li class="mui-table-view-cell mui-media mui-col-xs-4"><p>
		                    <img data-preview-src="" data-preview-group="1" class="mui-media-object" src="/MX_System/WeixinPages/uploadImg/myspaceImg/100/o8iGuv66gILabgoSL3Ibz8euYiZk/yuantiao.jpg">
		                            </p></li>
		                
		                    <li class="mui-table-view-cell mui-media mui-col-xs-4"><p>
		                    <img data-preview-src="" data-preview-group="1" class="mui-media-object" src="/MX_System/WeixinPages/uploadImg/myspaceImg/100/o8iGuv66gILabgoSL3Ibz8euYiZk/yuantiao.jpg">
		                            </p></li>
		                    
		                    <li class="mui-table-view-cell mui-media mui-col-xs-4"><p>
		                    <img data-preview-src="" data-preview-group="1" class="mui-media-object" src="/MX_System/WeixinPages/uploadImg/myspaceImg/100/o8iGuv66gILabgoSL3Ibz8euYiZk/yuantiao.jpg">
		                            </p></li>
		                </ul>
		            </div>
		            <div class="mui-slider-item mui-slider-item-duplicate">
						<ul class="mui-table-view mui-grid-view">
		                    <li class="mui-table-view-cell mui-media mui-col-xs-4"><p>
		                    <img data-preview-src="" data-preview-group="1" class="mui-media-object" src="/MX_System/WeixinPages/uploadImg/myspaceImg/100/o8iGuv66gILabgoSL3Ibz8euYiZk/yuantiao.jpg">
		                            </p></li>
		                
		                    <li class="mui-table-view-cell mui-media mui-col-xs-4"><p>
		                    <img data-preview-src="" data-preview-group="1" class="mui-media-object" src="/MX_System/WeixinPages/uploadImg/myspaceImg/100/o8iGuv66gILabgoSL3Ibz8euYiZk/yuantiao.jpg">
		                            </p></li>
		                    
		                    <li class="mui-table-view-cell mui-media mui-col-xs-4"><p>
		                    <img data-preview-src="" data-preview-group="1" class="mui-media-object" src="/MX_System/WeixinPages/uploadImg/myspaceImg/100/o8iGuv66gILabgoSL3Ibz8euYiZk/yuantiao.jpg">
		                            </p></li>
		                </ul>
		            </div>
		            <div class="mui-slider-item mui-slider-item-duplicate">
						<ul class="mui-table-view mui-grid-view">
		                    <li class="mui-table-view-cell mui-media mui-col-xs-4"><p>
		                    <img data-preview-src="" data-preview-group="1" class="mui-media-object" src="/MX_System/WeixinPages/uploadImg/myspaceImg/100/o8iGuv66gILabgoSL3Ibz8euYiZk/yuantiao.jpg">
		                            </p></li>
		                
		                    <li class="mui-table-view-cell mui-media mui-col-xs-4"><p>
		                    <img data-preview-src="" data-preview-group="1" class="mui-media-object" src="/MX_System/WeixinPages/uploadImg/myspaceImg/100/o8iGuv66gILabgoSL3Ibz8euYiZk/yuantiao.jpg">
		                            </p></li>
		                    
		                    <li class="mui-table-view-cell mui-media mui-col-xs-4"><p>
		                    <img data-preview-src="" data-preview-group="1" class="mui-media-object" src="/MX_System/WeixinPages/uploadImg/myspaceImg/100/o8iGuv66gILabgoSL3Ibz8euYiZk/yuantiao.jpg">
		                            </p></li>
		                </ul>
		            </div>
			
		</div>
		
	</div>
   </div>
   </div>
   
   
  
  </body>
  <script>
		mui.previewImage(function(img){
			plus.webview.currentWebview().setStyle({
				top:'0px',
				bottom:'0px'
			});
		},function(img){
			plus.webview.currentWebview().setStyle({
				top:'0px',
				bottom:'51px'
			});
		});
	</script>
</html>
