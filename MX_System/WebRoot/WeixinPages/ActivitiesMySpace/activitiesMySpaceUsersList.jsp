<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>myspaceUsersList</title>
    <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<!--标准mui.css-->
<link rel="stylesheet"
	href="<%=basePath%>WeixinPages/common/css/mui.min.css">

  </head>
  
  <body>
  <div class="mui-content">
   <ul class="mui-table-view">
   			<%for(int i=0;i<10;i++){ %>
				<li class="mui-table-view-cell mui-media">
					<a href="javascript:;">
						<img class="mui-media-object mui-pull-left" data-lazyload="http://wx.qlogo.cn/mmopen/gITwFOywPbkCx8BxwYc41oAGjuBeFianAbtHl8URmaCMTe9lib6EicNuHSibGJzSfT6Y88Nos1poHITnB7vUs7foHphNpibcgFEja/0">
						<div class="mui-media-body">
							小M<span class="mui-badge mui-badge-success">组织者</span><span class="mui-badge mui-badge-primary">老班长</span>
							<p class='mui-ellipsis'>157XXXXXXXX</p>
						</div>
					</a>
				</li>
				<li class="mui-table-view-cell mui-media">
					<a href="javascript:;">
						<img class="mui-media-object mui-pull-left" data-lazyload="http://wx.qlogo.cn/mmopen/gITwFOywPbkCx8BxwYc41oAGjuBeFianAbtHl8URmaCMTe9lib6EicNuHSibGJzSfT6Y88Nos1poHITnB7vUs7foHphNpibcgFEja/0">
						<div class="mui-media-body">
							小M<span class="mui-badge mui-badge-warning">财物</span><span class="mui-badge mui-badge-danger">数学科代表</span>
							<p class='mui-ellipsis'>157XXXXXXXX</p>
						</div>
					</a>
				</li>
				<li class="mui-table-view-cell mui-media">
					<a href="javascript:;">
						<img class="mui-media-object mui-pull-left" data-lazyload="http://wx.qlogo.cn/mmopen/gITwFOywPbkCx8BxwYc41oAGjuBeFianAbtHl8URmaCMTe9lib6EicNuHSibGJzSfT6Y88Nos1poHITnB7vUs7foHphNpibcgFEja/0">
						<div class="mui-media-body">
							小M<span class="mui-badge mui-badge-purple">小鲜肉</span>
							<p class='mui-ellipsis'>157XXXXXXXX</p>
						</div>
					</a>
				</li>
				<li class="mui-table-view-cell mui-media">
					<a href="javascript:;">
						<img class="mui-media-object mui-pull-left" data-lazyload="http://wx.qlogo.cn/mmopen/gITwFOywPbkCx8BxwYc41oAGjuBeFianAbtHl8URmaCMTe9lib6EicNuHSibGJzSfT6Y88Nos1poHITnB7vUs7foHphNpibcgFEja/0">
						<div class="mui-media-body">
							小M<span class="mui-badge mui-badge-danger">土豪M</span>
							<p class='mui-ellipsis'>157XXXXXXXX</p>
						</div>
					</a>
				</li>
				<li class="mui-table-view-cell mui-media">
					<a href="javascript:;">
						<img class="mui-media-object mui-pull-left" data-lazyload="http://wx.qlogo.cn/mmopen/gITwFOywPbkCx8BxwYc41oAGjuBeFianAbtHl8URmaCMTe9lib6EicNuHSibGJzSfT6Y88Nos1poHITnB7vUs7foHphNpibcgFEja/0">
						<div class="mui-media-body">
							小M<span class="mui-badge mui-badge-success">帅气</span>
							<p class='mui-ellipsis'>157XXXXXXXX</p>
						</div>
					</a>
				</li>
				<li class="mui-table-view-cell mui-media">
					<a href="javascript:;">
						<img class="mui-media-object mui-pull-left" data-lazyload="http://wx.qlogo.cn/mmopen/gITwFOywPbkCx8BxwYc41oAGjuBeFianAbtHl8URmaCMTe9lib6EicNuHSibGJzSfT6Y88Nos1poHITnB7vUs7foHphNpibcgFEja/0">
						<div class="mui-media-body">
							小M<span class="mui-badge mui-badge-purple">会做菜</span>
							<p class='mui-ellipsis'>157XXXXXXXX</p>
						</div>
					</a>
				</li>
				<li class="mui-table-view-cell mui-media">
					<a href="javascript:;">
						<img class="mui-media-object mui-pull-left" data-lazyload="http://wx.qlogo.cn/mmopen/gITwFOywPbkCx8BxwYc41oAGjuBeFianAbtHl8URmaCMTe9lib6EicNuHSibGJzSfT6Y88Nos1poHITnB7vUs7foHphNpibcgFEja/0">
						<div class="mui-media-body">
							小M<span class="mui-badge mui-badge-danger">宅男女神</span>
							<p class='mui-ellipsis'>157XXXXXXXX</p>
						</div>
					</a>
				</li>
				<li class="mui-table-view-cell mui-media">
					<a href="javascript:;">
						<img class="mui-media-object mui-pull-left" data-lazyload="http://wx.qlogo.cn/mmopen/gITwFOywPbkCx8BxwYc41oAGjuBeFianAbtHl8URmaCMTe9lib6EicNuHSibGJzSfT6Y88Nos1poHITnB7vUs7foHphNpibcgFEja/0">
						<div class="mui-media-body">
							小M<span class="mui-badge mui-badge-warning">装B大神</span>
							<p class='mui-ellipsis'>157XXXXXXXX</p>
						</div>
					</a>
				</li>
				<%} %>
			</ul>
			
    </div>
  </body>
  	<script src="<%=basePath%>WeixinPages/common/js/mui.min.js"></script>
	<script src="<%=basePath%>WeixinPages/common/js/mui.lazyload.js"></script>
	<script src="<%=basePath%>WeixinPages/common/js/mui.lazyload.img.js"></script>
<script>
	mui.init();
	(function($) {
		$(document).imageLazyload({
			placeholder: '/MX_System/WeixinPages/common/images/60x60.gif'
		});
	})(mui);
</script>
</html>
