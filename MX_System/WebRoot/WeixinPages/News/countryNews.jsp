<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
 
    %>
<html>
<head>
<base href="<%=basePath%>">
<title>鸣心-古田风情</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="green">
<script src="<%=basePath%>WeixinPages/common/js/mui.min.js"></script>
<link href="<%=basePath%>WeixinPages/common/css/mui.min.css"
	rel="stylesheet" />
<script>
		mui.init({
			swipeBack:true //启用右滑关闭功能
		});
		
		
	</script>
</head>
<body>

<div class="mui-content">
			
			<div id="slider" class="mui-slider">
				<div class="mui-slider-group ">
					<!-- 额外增加的一个节点(循环轮播：第一个节点是最后一张轮播) -->
					<div class="mui-slider-item mui-slider-item-duplicate">
						<a href="#">
							<img src="<%=basePath%>WeixinPages/common/images/yuantiao.jpg">
							<p class="mui-slider-title">静静看这世界</p>
						</a>
					</div>
					<div class="mui-slider-item">
						<a href="#">
							<img src="<%=basePath%>WeixinPages/common/images/shuijiao.jpg">
							<p class="mui-slider-title">幸福就是可以一起睡觉</p>
						</a>
					</div>
					<div class="mui-slider-item">
						<a href="#">
							<img src="<%=basePath%>WeixinPages/common/images/muwu.jpg">
							<p class="mui-slider-title">想要一间这样的木屋，静静的喝咖啡</p>
						</a>
					</div>
					<div class="mui-slider-item">
						<a href="#">
							<img src="<%=basePath%>WeixinPages/common/images/cbd.jpg">
							<p class="mui-slider-title">Color of SIP CBD</p>
						</a>
					</div>
					<div class="mui-slider-item">
						<a href="#">
							<img src="<%=basePath%>WeixinPages/common/images/yuantiao.jpg">
							<p class="mui-slider-title">静静看这世界</p>
						</a>
					</div>
					<!-- 额外增加的一个节点(循环轮播：最后一个节点是第一张轮播) -->
					<div class="mui-slider-item mui-slider-item-duplicate">
						<a href="#">
							<img src="<%=basePath%>WeixinPages/common/images/shuijiao.jpg">
							<p class="mui-slider-title">幸福就是可以一起睡觉</p>
						</a>
					</div>
				</div>
				<div class="mui-slider-indicator mui-text-right">
					<div class="mui-indicator mui-active"></div>
					<div class="mui-indicator"></div>
					<div class="mui-indicator"></div>
					<div class="mui-indicator"></div>
				</div>
			</div>
		</div>


<div class="mui-content">
			
			<div class="title">
				缩略图居左
			</div>
			<ul class="mui-table-view">
				<c:forEach items="${MxNewsDataList}" var="item" varStatus="status">  
					<li class="mui-table-view-cell mui-media">
						<a href="weixin/newsPage.action?newsId=${item.newsId}">
							<img class="mui-media-object mui-pull-left" src="<%=basePath%>WeixinPages/common/images/shuijiao.jpg">
							<div class="mui-media-body">
								标题：${item.newsHeadline}
								<p class='mui-ellipsis'>引言：${item.newsLeadText}</p>
							</div>
						</a>
					</li>
			    </c:forEach>

			</ul>
			<div class="title">
				缩略图居右
			</div>
			<ul class="mui-table-view">
				<c:forEach items="${MxNewsDataList}" var="item" varStatus="status"> 
					<li class="mui-table-view-cell mui-media">
						<a href="weixin/newsPage.action?newsId=${item.newsId}">
							<img class="mui-media-object mui-pull-right" src="<%=basePath%>WeixinPages/common/images/yuantiao.jpg">
							<div class="mui-media-body">
								标题：${item.newsHeadline}
								<p class='mui-ellipsis'>引言：${item.newsLeadText}</p>
							</div>
						</a>
					</li>
				</c:forEach>
				
			</ul>
			<div class="title">
				右侧带导航箭头
			</div>
			<ul class="mui-table-view mui-table-view-chevron">
				<c:forEach items="${MxNewsDataList}" var="item" varStatus="status"> 
					<li class="mui-table-view-cell mui-media">
						<a class="mui-navigate-right" href="weixin/newsPage.action?newsId=${item.newsId}">
							<img class="mui-media-object mui-pull-left" src="<%=basePath%>WeixinPages/common/images/cbd.jpg">
							<div class="mui-media-body">
								标题：${item.newsHeadline}
								<p class='mui-ellipsis'>引言：${item.newsLeadText}</p>
							</div>
						</a>
					</li>
				</c:forEach>
			</ul>
			<div class="title">
				card（圆角列表）
			</div>
			<div class="mui-card" style="margin-bottom: 35px;">
				<ul class="mui-table-view">
					<c:forEach items="${MxNewsDataList}" var="item" varStatus="status"> 
						<li class="mui-table-view-cell mui-media">
							<a href="weixin/newsPage.action?newsId=${item.newsId}">
								<img class="mui-media-object mui-pull-right" src="<%=basePath%>WeixinPages/common/images/muwu.jpg">
								<div class="mui-media-body">
									标题：${item.newsHeadline}
									<p class='mui-ellipsis'>引言：${item.newsLeadText}</p>
								</div>
							</a>
						</li>
					</c:forEach>
					
				</ul>
			</div>
		</div>


</body>
</html>
