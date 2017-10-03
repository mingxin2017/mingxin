<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
 
    %>
<html>
<head>
<base href="<%=basePath%>">
<title>鸣心-校园视界</title>
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

<div class="mui-content" style="height:60px;text-align:left;">
  <div class="wrap" style="height:50px;padding:5px;border:1px #ccc solid;margin:0;">
   <p style="display:inline;float:left;font-weight:bold;font-size:13px;color:red;padding:0 10px;">快讯<br>时间 </p>     
 	<div style="float:left;text-align:center;padding:5px;">
	 	<div class="roll-wrap" id="roll-wrap" style="height:31px;font-size:20px;float:left;overflow:hidden;">
			 <ul style="margin:0;">
	            <li style="margin:5;">JS文本向上滚动1</li>
	            <li style="margin:5;">JS文本向上滚动2</li>
	            <li style="margin:5;">JS文本向上滚动3</li>
	            <li style="margin:5;">JS文本向上滚动4</li>
	            <li style="margin:5;">JS文本向上滚动5</li>
	            <li style="margin:5;">JS文本向上滚动6</li>
	            <li style="margin:5;">JS文本向上滚动7</li>
	        </ul>
	    </div>
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
<script type="text/javascript" src="<%=basePath%>WeixinPages/common/js/jquery-1.11.2.js"></script>
<!-- 快讯单行滚动 -->		
<script type="text/javascript"> 
function scrollTxt(){
    var controls={}, 
        values={},
        t1=200, /*播放动画的时间*/
        t2=2000, /*播放时间间隔*/
        si;
    controls.rollWrap=$("#roll-wrap");
    controls.rollWrapUl=controls.rollWrap.children();
    controls.rollWrapLIs=controls.rollWrapUl.children();
    values.liNums=controls.rollWrapLIs.length;
    values.liHeight=controls.rollWrapLIs.eq(0).height();
    values.ulHeight=controls.rollWrap.height();
    this.init=function(){
        autoPlay();
        pausePlay();
    }
    /*滚动*/
    function play(){
        controls.rollWrapUl.animate({"margin-top" : "-"+values.liHeight}, t1, function(){
            $(this).css("margin-top" , "0").children().eq(0).appendTo($(this));
        });
    }
    /*自动滚动*/
    function autoPlay(){
        /*如果所有li标签的高度和大于.roll-wrap的高度则滚动*/
        if(values.liHeight*values.liNums > values.ulHeight){
            si=setInterval(function(){
                play();
            },t2);
        }
    }
    /*鼠标经过ul时暂停滚动*/
    function pausePlay(){
        controls.rollWrapUl.on({
            "mouseenter":function(){
                clearInterval(si);
            },
            "mouseleave":function(){
                autoPlay();
            }
        });
    }
}
new scrollTxt().init();
</script>

</body>
</html>
