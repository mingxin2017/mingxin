<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";%>
<head>
<base href="<%=basePath%>">
<title>拉动加载</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">

		<!--标准mui.css-->
		<link rel="stylesheet" href="<%=basePath%>WeixinPages/common/css/mui.min.css">
		<style>
			h5 {
				margin: 5px 7px;
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

	<style>
    *{
        margin: 0;
        padding:0;
        -webkit-tap-highlight-color:rgba(0,0,0,0);
        -webkit-text-size-adjust:none;
    }
    html{
        font-size:10px;
    }
    body{
        background-color: #f5f5f5;
        font-size: 1.2em;
    }
    .header{
        height: 44px;
        line-height: 44px;
        border-bottom: 1px solid #ccc;
        background-color: #eee;
    }
    .header h1{
        text-align: center;
        font-size: 2rem;
        font-weight: normal;
    }
    .content{
        background-color: #fff;
    }
    .content .item{
        display: -webkit-box;
        display: -webkit-flex;
        display: -ms-flexbox;
        display: flex;
        -ms-flex-align:center;
        -webkit-box-align:center;
        box-align:center;
        -webkit-align-items:center;
        align-items:center;
        padding:3.125%;
        border-bottom: 1px solid #ddd;
        color: #333;
        text-decoration: none;
    }
    .content .item img{
        display: block;
        width: 40px;
        height: 40px;
        border:1px solid #ddd;
    }
    .content .item h3{
        display: block;
        -webkit-box-flex: 1;
        -webkit-flex: 1;
        -ms-flex: 1;
        flex: 1;
        width: 100%;
        max-height: 40px;
        overflow: hidden;
        line-height: 20px;
        margin: 0 10px;
        font-size: 1.2rem;
    }
    .content .item .date{
        display: block;
        height: 20px;
        line-height: 20px;
        color: #999;
    }
    .opacity{
        -webkit-animation: opacity 0.3s linear;
        animation: opacity 0.3s linear;
    }
    @-webkit-keyframes opacity {
        0% {
            opacity:0;
        }
        100% {
            opacity:1;
        }
    }
    @keyframes opacity {
        0% {
            opacity:0;
        }
        100% {
            opacity:1;
        }
    }
    </style>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>WeixinPages/common/dropload/dropload.css" />

</head>
<body id="body">
	<div class="header">
	    <h1>就当我是新闻页吧</h1>
	</div>
	<div class="content">
	    <div class="lists">
	    </div>
	</div>
	

	
	
	<script src="<%=basePath%>WeixinPages/common/js/mui.min.js "></script>
	<script src="<%=basePath%>WeixinPages/common/js/mui.view.js "></script>
	<%-- <script src='<%=basePath%>WeixinPages/common/js/feedback.js'></script> --%>
	<script>
		mui.init();
	
	
	</script>
	<!-- jQuery1.7以上 或者 Zepto 二选一，不要同时都引用 -->
	<script type="text/javascript" src="<%=basePath%>WeixinPages/common/js/jquery-1.11.2.js"></script>
	<%-- <script src="<%=basePath%>WeixinPages/common/dropload/zepto.min.js "></script> --%>
	<script src="<%=basePath%>WeixinPages/common/dropload/dropload.min.js "></script>
	<script src="<%=basePath%>WeixinPages/common/dropload/json/more.json "></script>
	<script src="<%=basePath%>WeixinPages/common/dropload/json/update.json "></script>
	<script type="text/javascript">
		$(function(){
		    var counter = 0;
		    // 每页展示4个
		    var num = 4;
		    var pageStart = 0,pageEnd = 0;
		
		    // dropload
		    $('.content').dropload({
		        scrollArea : window,
		        loadDownFn : function(me){
		            $.ajax({
		                type: 'GET',
		                url: '<%=basePath%>mxNewsBusinessAction/getNewsCommentByNewsId.action', 
		                dataType: 'json',
		                success: function(data){
		                    var result = '';
		                    counter++;
		                    pageEnd = num * counter;
		                    pageStart = pageEnd - num;
		
		                    for(var i = pageStart; i < pageEnd; i++){
		                        result +=   '<a class="item opacity" href="'+data.lists[i].link+'">'
		                                        +'<img src="'+data.lists[i].pic+'" alt="">'
		                                        +'<h3>'+data.lists[i].operator+':'+data.lists[i].commentTxt+'</h3>'
		                                        +'<span class="date">'+data.lists[i].createDate+'</span>'
		                                    +'</a>';
		                        if((i + 1) >= data.lists.length){
		                            // 锁定
		                            me.lock();
		                            // 无数据
		                            me.noData();
		                            break;
		                        }
		                    }
		                    // 为了测试，延迟1秒加载
		                    setTimeout(function(){
		                        $('.lists').append(result);
		                        // 每次数据加载完，必须重置
		                        me.resetload();
		                    },1000);
		                },
		                error: function(xhr, type){
		                    console.log('Ajax error!'); 
		                    // 即使加载出错，也得重置
		                    me.resetload();
		                }
		            });
		        }
		    });
		});
	</script> 	
</body>



