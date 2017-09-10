<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";%>
<head>
<base href="<%=basePath%>">
<title>投稿列表</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">

		<!--标准mui.css-->
		<link rel="stylesheet" href="<%=basePath%>WeixinPages/common/css/mui.min.css">
		<style>
			h5 {
				margin: 5px 7px;
			}
			.mui-card .mui-control-content {
				padding: 10px; 
			}
			
			.mui-control-content {
				height: 500px;
			}
		</style>
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
			<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
			<h1 class="mui-title">投稿列表(${countAll})</h1>
		</header>
			<div class="mui-content">
			<div style="padding: 10px 10px;">
				<div id="segmentedControl" class="mui-segmented-control">
					<a class="mui-control-item mui-active" href="#item1">待审核(${countDS})</a>
					<a class="mui-control-item" href="#item2">审核未通过(${countWTG})</a>
					<a class="mui-control-item" href="#item3">审核已通过(${countTG})</a>
				</div>
			</div>
			<div>
				<div id="item1" class="mui-control-content mui-active">
					<div id="scroll" class="mui-scroll-wrapper">
						<div class="mui-scroll">
							<ul id="OA_task_1" class="mui-table-view">
								<c:forEach items="${MxNewsDataList}" var="item" varStatus="status">  
									 <c:if test="${item.state==0}">
										<li class="mui-table-view-cell">
											<div class="mui-slider-right mui-disabled">
												<a class="mui-btn mui-btn-red">删除</a>
											</div>
											<div class="mui-slider-handle" >
											     <a href="javascript:void(0);" onclick="locationTo(${item.newsId});">标题：${item.newsHeadline}</a>
											</div>
										</li>
									</c:if>
							    </c:forEach> 
							</ul>
						</div>
					</div>
				</div>
				<div id="item2" class="mui-control-content">
					<ul class="mui-table-view">
						<c:forEach items="${MxNewsDataList}" var="item"> 
						     <c:if test="${item.state==1}">
								<li class="mui-table-view-cell">
									<div class="mui-slider-right mui-disabled">
										<a class="mui-btn mui-btn-red">删除</a>
									</div>
									<div class="mui-slider-handle" >
										<a href="javascript:void(0);" onclick="locationTo(${item.newsId});">标题：${item.newsHeadline}</a>
									</div>
								</li>
							</c:if>
					 	</c:forEach> 
					</ul>
				</div>
				<div id="item3" class="mui-control-content">
					<ul class="mui-table-view">
						<c:forEach items="${MxNewsDataList}" var="item"> 
						    <c:if test="${item.state==2}"> 
								<li class="mui-table-view-cell">
									<div class="mui-slider-right mui-disabled">
										<a class="mui-btn mui-btn-red">删除</a>
									</div>
									<div class="mui-slider-handle" >
										<a href="javascript:void(0);" onclick="locationTo(${item.newsId});">标题：${item.newsHeadline}</a>
									</div>
								</li>
							</c:if>
					    </c:forEach> 
					</ul>
				</div>
			</div>
		</div>
</body>
<script src="<%=basePath%>WeixinPages/common/js/mui.min.js "></script>
<script>
	var MxNewsDataList = "${MxNewsDataList[0].newsHeadline}";
	mui.init();
	(function($) {
		//$.swipeoutOpen(el,direction)//打开指定列的滑动菜单，el:指定列的dom对象，direction：取值left|right，指定打开的是左侧或右侧滑动菜单
		//$.swipeoutClose(el);//关闭指定列的滑动菜单，el:指定列的dom对象
		//				setTimeout(function() {
		//					$.swipeoutOpen(document.getElementById("OA_task_1").querySelector('li:last-child'), 'left');
		//					setTimeout(function() {
		//						$.swipeoutClose(document.getElementById("OA_task_1").querySelector('li:last-child'));
		//					}, 1000);
		//				}, 1000);
		//第一个demo，拖拽后显示操作图标，点击操作图标删除元素；
		$('#OA_task_1').on('tap', '.mui-btn', function(event) {
			var elem = this;
			var li = elem.parentNode.parentNode;
			mui.confirm('确认删除该条记录？', 'Hello MUI', btnArray, function(e) {
				if (e.index == 0) {
					li.parentNode.removeChild(li);
				} else {
					setTimeout(function() {
						$.swipeoutClose(li);
					}, 0); 
				}
			});
		});
		var btnArray = ['确认', '取消'];
})(mui);
</script>
<script type="text/javascript" src="<%=basePath%>WeixinPages/common/js/jquery-1.11.2.js"></script>
<script type="text/javascript" src="<%=basePath%>WeixinPages/common/js/jquery.form.js"></script>
<script type="text/javascript">
       //删除新闻 条数刷新
       //weixin/newsPage.action
       function locationTo(newsId){
       		window.location.href="weixin/newsPage.action?newsId="+newsId;
       };
</script>


