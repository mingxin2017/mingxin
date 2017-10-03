<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";%>
<head>
<base href="<%=basePath%>">
<title>新闻页面</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">

		<!--标准mui.css-->
		<link rel="stylesheet" href="<%=basePath%>WeixinPages/common/css/mui.min.css">
		<link rel="stylesheet" type="text/css" href="<%=basePath%>WeixinPages/common/css/icons-extra.css" />
		<style>
			h5 {
				margin: 5px 7px;
			}
		</style>
		<style>
			 .black_overlay
		        {
		            display: none;
		            position: absolute;
		            top: 0%;
		            left: 0%;
		            width: 100%;
		            height: 100%;
		            background-color: black;
		            z-index: 1001;
		            -moz-opacity: 0.8;
		            opacity: .80;
		            filter: alpha(opacity=80);
		        }
	.white_content
        {
            display: none;
            position: absolute;
            /* top: 10%;
            left: 10%;*/ 
            width: 100%;
            height: 180px;
            border: 12px solid #ddd;
            background-color: white;
            z-index: 1002;
            overflow: auto;
            bottom: 0;
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

		
<link rel="stylesheet" type="text/css" href="<%=basePath%>WeixinPages/common/css/app.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>WeixinPages/common/css/feedback.css" />

<link rel="stylesheet" type="text/css" href="<%=basePath%>WeixinPages/common/dropload/dropload.css" />
<style>
    .content{
        background-color: #fff;
    }
    .content .item{
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
    .content .itemhead{
        -ms-flex-align:center;
        -webkit-box-align:center;
        box-align:center;
        -webkit-align-items:center;
        align-items:center;
        padding:2% 3.125%;
        border-bottom: 1px solid #ddd;
        color: #333;
        text-decoration: none;
        font-size: 0.9rem;
    }
    .weixinHeadUrl{
    	/* border:1px solid red;  */
    	float:left;
    }
    .commentHead {
     	/* border:1px solid red;  */
    	float:left;
    	height: 42px;
    	width:80%;
    }
    .weixinHeadUrl img{
        width: 40px;
        height: 40px;
        margin:0;
        padding:0;
    }
    .weixinNikeName{
         display: inline; 
        height: 40px;
        margin: 0 10px;
        font-size: 0.8rem;
        text-align:center;         
        /* border:1px solid red; */ 
    }
    .content .item .date{
        display: inline;
        height: 40px;
        line-height: 40px;
        color: #999;
        text-align:center;
        font-size: 0.7rem;
        /* border:1px solid red; */ 
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
    li {
      list-style:none;
      position:relative;
      /* height:200px; */
    }
    .commentController {
    	height: 40px;
    	text-align:center;
    	font-size: 0.7rem;
    	/* border:1px solid red;  */
    }
    .commentTxt {
    	display:block;
    	padding:10 50;
    	font-size: 0.9rem;
    }
	.commentHeader {
    	display:block;
    	height:40px;
    }
    .userInfo{
        background:#fff;
        border-bottom:1px solid #ddd;
    }
    .wxHeadUrl{
    	height:70px;
    	padding:20 0 20 0;
    	line-height:70px;
    	text-align:center;
    }
    .wxHeadUrl img{
    	height:50px;
    	width:50px;
    }
    .wxNikeName{
    	height:50px;
    	line-height:50px;
    	text-align:center;
    	font-size: 1.0rem;
    }
    .newsInfo{
    	background:#ddd;
    	margin:10 0 10 50;
    	line-height:40px;
    }
    .newsHeadline{
    	overflow:hidden;
    	text-overflow:ellipsis;
    	white-space:nowrap;
    }
    </style>
</style>
</head>
    <script type="text/javascript">
	</script>
	<body id="body">
	  <div class="head">
		  <div class="userInfo">
             <div class="wxHeadUrl">
             	<img src="${MxUsersData.weixinHeadUrl}" alt="">
             </div>
             <div class="wxNikeName">${MxUsersData.weixinNikeName}</div>
		  </div>
		  <div class="content">
			<div class="lists">
			</div>
		  </div>
		   
		  <div style="height:100px;background:#fff;"></div>
		</div>
<script src="<%=basePath%>WeixinPages/common/js/mui.min.js "></script>
<script src="<%=basePath%>WeixinPages/common/js/mui.view.js "></script>
<%-- <script src='<%=basePath%>WeixinPages/common/js/feedback.js'></script> --%>
<script>
	mui.init();


</script>
<script type="text/javascript" src="<%=basePath%>WeixinPages/common/js/jquery-1.11.2.js"></script>
<script src="<%=basePath%>WeixinPages/common/dropload/dropload.min.js "></script>
<script type="text/javascript">
// 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
// 例子：
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
// (new Date()).Format("yyyy-M-d h:m:s.S")   ==> 2006-7-2 8:9:4.18
Date.prototype.Format = function (fmt) { //author: meizz
  var o = {
    "M+": this.getMonth() + 1, //月份
    "d+": this.getDate(), //日
    "h+": this.getHours(), //小时
    "m+": this.getMinutes(), //分
    "s+": this.getSeconds(), //秒
    "q+": Math.floor((this.getMonth() + 3) / 3), //季度
    "S": this.getMilliseconds() //毫秒
  };
  if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
  for (var k in o)
  if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
  return fmt;
};

//js函数代码：字符串转换为时间戳
function getDateTimeStamp(dateStr){
 return Date.parse(dateStr.replace(/-/gi,"/"));
}
//JS、jquery实现几分钟前、几小时前、几天前等时间差显示效果
var minute = 1000 * 60;
var hour = minute * 60;
var day = hour * 24;
var halfamonth = day * 15;
var month = day * 30;
function getDateDiff(dateTimeStamp){
var now = new Date().getTime();
var diffValue = now - dateTimeStamp;
if(diffValue < 0){
 //若日期不符则弹出窗口告之
 //alert("结束日期不能小于开始日期！");
 }
var monthC =diffValue/month;
var weekC =diffValue/(7*day);
var dayC =diffValue/day;
var hourC =diffValue/hour;
var minC =diffValue/minute;
if(monthC>=1){
 result="发表于" + parseInt(monthC) + "个月前";
 }
 else if(weekC>=1){
 result="发表于" + parseInt(weekC) + "周前";
 }
 else if(dayC>=1){
 result="发表于"+ parseInt(dayC) +"天前";
 }
 else if(hourC>=1){
 result="发表于"+ parseInt(hourC) +"个小时前";
 }
 else if(minC>=1){
 result="发表于"+ parseInt(minC) +"分钟前";
 }else
 result="刚刚发表";
return result;
}

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
	                url: '<%=basePath%>mxNewsBusinessAction/getNewsCommentByUserId.action?userId=${MxUsersData.userId}', 
	                dataType: 'json',
	                success: function(data){
	                	//无数据
	                    if(data.lists.length==0){
	                    	// 锁定
	                        me.lock();
	                        // 无数据
	                        me.noData();
	                        me.resetload();
	                        return;
	                    }
	                    var result = '';
	                    counter++;
	                    pageEnd = num * counter;
	                    pageStart = pageEnd - num;

	                    for(var i = pageStart; i < pageEnd; i++){
	                        result +=   '<li class="item opacity">'
	                        				+'<div class="commentHeader">'
		                        				+'<div class="weixinHeadUrl">'
		                        				+'<img src="'+data.lists[i].weixinHeadUrl+'" alt="">'
		                        				+'</div>'
											    +'<div class="commentHead">'
											        +'<div class="weixinNikeName">'+data.lists[i].weixinNikeName+'</div>'
												+'<span class="date">'+getDateDiff(getDateTimeStamp((data.lists[i].createDate).replace(/-/g,"/").replace("T"," ")))+'</span>'
												+'<span class="commentController">   <input id="commentPraiseNum'+i+'" value="'+data.lists[i].praiseNum+'" readonly style="border:0;width:20px;font-size:8px;text-align:right;padding:2px;"/>' 
												    +'<a id="commentPraise'+i+'" style="color:'+((data.lists[i].praiseState==0)?'#aaa':'#007aff')+';"><span id="praiseSpan" class="mui-icon-extra mui-icon-extra-like" onclick="commentPraise('+i+','+data.lists[i].commentId+');" title="'+((data.lists[i].praiseState==0)?'点赞':'取消点赞')+'"></span></a>' 
												    +'<input id="commentBackNum'+i+'" value="'+data.lists[i].backNum+'" readonly style="border:0;width:20px;font-size:8px;text-align:right;padding:2px;"/>' 
												    +'<a id="commentBack'+i+'" style="color:#aaa;" onclick="commentBack('+i+','+data.lists[i].commentId+');"><span class="mui-icon mui-icon-chat" title="回复"></span></a>    </span>'
											    +'</div>'
										    +'</div>'
										    +'<div class="commentTxt">'+data.lists[i].commentTxt+'</div>'
										    +'<div class="newsInfo">'
/* 												+'<div class="weixinHeadUrl">'
		                        				+'<img src="'+data.lists[i].weixinHeadUrl+'" alt="">'
		                        				+'</div>' */
		                        				+'<a onclick="toNewsPage('+data.lists[i].newsId+');" title="文章">'
		                        					+'<div class="newsHeadline" >'+data.lists[i].newsHeadline+'</div>'
										    	+'</a>'
										    +'</div>';
										  +'</li>';
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
//评论点赞
function commentPraise(index,commentId){
	console.log("praise("+commentId+")");
	console.log($("#commentPraise"+index).css('color'));
	if($("#commentPraise"+index).css('color') == 'rgb(170, 170, 170)'){
		$("#commentPraise"+index).css('color','#007aff');
		$("#praiseSpan").attr("title","取消点赞");
		//插入一条评论的点赞数据
		$.ajax({
			type: "post",
			url: "mxNewsBusinessAction/addCommentPraise.action",
			data: {commentId:commentId},
		    dataType: 'json',//接收回调数据形式
			success: function (data) {
				    console.log(data);
						mui.toast(data.data.msg);
					//更新评论点赞数
					if(data.data.success){
						$("#commentPraiseNum"+index).val(data.data.data);
					}
			},
			error: function (msg) {
				mui.toast("点赞失败，请联系管理员！");
			}
		});
	}else{
		$("#commentPraise"+index).css('color','#aaa');
		$("#praiseSpan").attr("title","点赞");
		//取消点赞
		$.ajax({
			type: "post",
			url: "mxNewsBusinessAction/delCommentPraise.action",
			data: {commentId:commentId},
		    dataType: 'json',//接收回调数据形式
			success: function (data) {
				    console.log(data);
					mui.toast(data.data.msg);
					//更新评论点赞数
					if(data.data.success){
						$("#commentPraiseNum"+index).val(data.data.data);
					}
			},
			error: function (msg) {
				mui.toast("取消点赞失败，请联系管理员！");
			}
		});
	}
}
//文章点赞
function praise(newsId){
	console.log("praise("+newsId+")");
	console.log($("#praise").css('color'));
	if($("#praise").css('color') == 'rgb(170, 170, 170)'){
		$("#praise").css('color','#007aff');
		$("#newsPraiseSpan").attr("title","取消点赞");
		//插入一条点赞数据
		$.ajax({
			type: "post",
			url: "mxNewsBusinessAction/praise.action?type=add",
			data: {newsId:newsId},
		    dataType: 'json',//接收回调数据形式
			success: function (data) {
				    console.log(data);
					mui.toast(data.data.msg);
					//更新点赞数
					if(data.data.success){
						$("#praiseNum").val(data.data.data);
					}
			},
			error: function (msg) {
				mui.toast("点赞失败，请联系管理员！");
			}
		});
	}else{
		$("#praise").css('color','#aaa');
		$("#newsPraiseSpan").attr("title","点赞");
		//取消点赞
		$.ajax({
			type: "post",
			url: "mxNewsBusinessAction/praise.action?type=del",
			data: {newsId:newsId},
		    dataType: 'json',//接收回调数据形式
			success: function (data) {
				    console.log(data);
					mui.toast(data.data.msg); 
					//更新点赞数
					if(data.data.success){
						$("#praiseNum").val(data.data.data);
					}
			},
			error: function (msg) {
				mui.toast("取消点赞失败，请联系管理员！");
			}
		});
	}
}
//评论回复
function commentBack(index,commentId){
    var url = "mxNewsBusinessAction/commentBackPage.action?commentId="+commentId;
	window.location.href = url;
}
//文章
function toNewsPage(newsId){
	var url = "weixin/newsPage.action?newsId="+newsId;
	window.location.href = url;
}
</script>
	</body>



