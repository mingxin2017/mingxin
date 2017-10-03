<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";%>
<head>
<base href="<%=basePath%>">
<title>评论回复</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">

		<!--标准mui.css-->
		<link rel="stylesheet" href="<%=basePath%>WeixinPages/common/css/mui.min.css">
		<link rel="stylesheet" type="text/css" href="<%=basePath%>WeixinPages/common/css/icons-extra.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>WeixinPages/common/dropload/dropload.css" />
<style type="text/css">
	body{
		background:#fff;
	}
	.mainBody{
		padding:25 25 0 25;
	}
	.comment,.commentBack,.toCommentBack{
		/* border:1px solid red; */
		height:auto;
		width:100%;
	}
	.commentHeader,.commentBackHeader{
		position:relative;
		/* border:1px solid red; */
		height:41px;
		width:100%;
        margin:0;
        padding:3;
	}
 	.weixinHeadUrl,.weixinBackHeadUrl{
		float:left;
		width: 40px;
        height: 40px;
        margin:0;
        padding:0;
	} 
	.weixinHeadUrl img,.weixinBackHeadUrl img{
		width: 39px;
        height: 39px;
        margin:0;
        padding:0;
	}
	.commentContent{
		position:relative;
		/* border:1px solid red; */
		height:auto;
		width:100%;
		padding-left:40px;
		padding-bottom:20px;
	}
	.commentBackContent{
		position:relative;
		/* border:1px solid red; */
		height:auto;
		width:100%;
		padding:10 0 0 50;
		font-size: 1.0rem;

	}
	.commentTxt,.commentController{
		margin:0;
        padding:0;
        padding-top:10;
    	font-size: 1.0rem;
	}
	.commentBackLi{
		list-style-type: none;
		margin:0;
		-webkit-animation: opacity 0.3s linear;
        animation: opacity 0.3s linear;
		-ms-flex-align:center;
        -webkit-box-align:center;
        box-align:center;
        -webkit-align-items:center;
        align-items:center;
        padding:1.125%;
        color: #333;
        text-decoration: none;
	}
	input{
		border: none;
        font-size: 0.7rem;
	}
	.commentHead,.commentBackHead{
		padding:3;
	}
	.date{
		color: #999;
	}
	.commentBack{
		padding:5 5 0 5;
		/* border:1px solid red; */
	}
	.commentBackBar{
		padding:10 0 5 0;
		/* border:1px solid red; */
		font-size: 1.0rem;
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
</head>
<body>
<div class="mainBody">
<div id="comment" class="comment">
	<div class="commentHeader">
	 <div class="weixinHeadUrl">
	  	<img src="${MxNewsComment.weixinHeadUrl}" alt="">
	 </div>
	 <div class="commentHead">
	   <a class="weixinNikeName" style="display:block;" onclick="userComments(${MxNewsComment.operator});">${MxNewsComment.weixinNikeName}</a>
	   <input id="commentDate" class="date"></input>
	 </div>
	</div>
	<div class="commentContent">
		<div class="commentTxt">${MxNewsComment.commentTxt}</div>
		<div class="commentController">   
		   <a id="commentPraise" style="color:${(MxNewsComment.praiseState=='0')?'#aaa':'#007aff'};">
		   		<span id="praiseSpan" class="mui-icon-extra mui-icon-extra-like" onclick="commentPraise();" title="${(MxNewsComment.praiseState=='0')?'点赞':'取消点赞'}"></span>
		   </a>
		   <input id="commentPraiseNum" value="${MxNewsComment.praiseNum}" readonly style="border:0;width:30px;font-size:8px;text-align:left;padding-right:10px;"/>
		   		
		   <a style="color:#aaa;" onclick="ShowDiv('MyDiv','fade');">
 			    <span class="mui-icon mui-icon-chat" title="回复" ></span>
 		   </a>
 		   <input id="commentBackNum" value="${MxNewsComment.backNum}" readonly style="border:0;width:30px;font-size:8px;text-align:left;padding-right:10px;"/>
		</div>
	</div>
</div>
<div id="commentBack" class="commentBack">
 <div class="commentBackBar">回复评论（${MxNewsComment.backNum}条）</div>
 <div id="commentBackList" class="commentBackList">
	<div class="content">
     <div class="lists">
<%-- 	     <li class="commentBackLi">
			<div class="commentBackHeader">
			 <div class="weixinBackHeadUrl">
			  	<img src="${MxNewsComment.weixinHeadUrl}" alt="">
			 </div>
			 <div class="commentBackHead">
			    <div class="weixinBackNikeName" style="height:40px;line-height: 40px; text-align:left;display:inline;">${MxNewsComment.weixinNikeName} 
				</div>
				<span class="backDate" style="width:auto; text-align:right;border:1px solid red;">${MxNewsComment.createDate}</span> 
			 </div>
			</div>
			<div>
				<div class="commentBackContent">
				七步诗
				魏晋.曹植
				煮豆燃豆萁，漉豉以为汁， 
				萁在釜下燃，豆在釜中泣， 
				本是同根生，相煎何太急！
				</div>
			</div>
		 </li> --%>
	    </div>
   	</div>
  </div>
</div>
</div>
<div style="height:100px;background:#fff;"></div>
<div id="toCommentBack" style="bottom: 0;position: fixed;z-index: 10;right: 0;left: 0;border:1px solid gray;height:50px;background:#fff;">
	  <a href="javascript:void(0);" onclick="ShowDiv('MyDiv','fade');" title="回复口">
	    <input type="text" style="border-radius:15px;width:99%;margin-top:4px;margin-left:5px;font-size:15px;background:url(<%=basePath%>WeixinPages/common/images/icons/pencil.png) no-repeat 0 center;background-attachment:fixed;background-position:10 center;padding-left:20px;" value="回复 ${MxNewsComment.weixinNikeName}:"/>
	  </a>
</div>
<div id="fade" class="black_overlay" onclick="CloseDiv('MyDiv','fade');"></div>
<div id="MyDiv" class="white_content">
    <textarea id="backTxt" rows="5" placeholder="回复 ${MxNewsComment.weixinNikeName}:" style='border:1px solid gray;margin:6px 2%;width:96%;'></textarea>
	<a href="javascript:void(0);" onclick="submitBack();" style="width:70px;float:right;">发表</a>
</div>
</body>
<script src="<%=basePath%>WeixinPages/common/js/mui.min.js "></script>
<script src="<%=basePath%>WeixinPages/common/js/mui.view.js "></script>
<script>
	mui.init();


</script>
<script type="text/javascript" src="<%=basePath%>WeixinPages/common/js/jquery-1.11.2.js"></script>
<script type="text/javascript">
        //弹出隐藏层
        function ShowDiv(show_div, bg_div) {
            document.getElementById(show_div).style.display = 'block';
            document.getElementById(bg_div).style.display = 'block';
            var bgdiv = document.getElementById(bg_div);
            bgdiv.style.width = document.body.scrollWidth;
            // bgdiv.style.height = $(document).height();
            $("#" + bg_div).height($(document).height());
            //添加焦点
            document.getElementById("backTxt").focus();
        };
        //关闭弹出层
        function CloseDiv(show_div, bg_div) {
            document.getElementById(show_div).style.display = 'none';
            document.getElementById(bg_div).style.display = 'none';
        };
    //评论回复    
	function submitBack(){
		var backTxt = $("#backTxt").val().trim();
		if(backTxt==null || backTxt==""){
			mui.toast("评论回复内容不能为空！");
			return;
		}
		$.ajax({
			type: "post",
			url: "mxNewsBusinessAction/addNewsCommentBack.action",
			data: {commentId:"${MxNewsComment.commentId}",
				   backTxt:backTxt},
		    dataType: 'json',//接收回调数据形式
			success: function (data) {
					if(data.data.success){
						mui.toast(data.data.msg);
						//刷新页面或者跳转到管理界面
						window.location.reload();
					}; 
			},
			error: function (msg) {
				mui.toast("评论失败，请联系管理员！");
			}
		});
	}
//评论点赞
function commentPraise(){
	if($("#commentPraise").css('color') == 'rgb(170, 170, 170)'){
		$("#commentPraise").css('color','#007aff');
		$("#praiseSpan").attr("title","取消点赞");
		//插入一条评论的点赞数据
		$.ajax({
			type: "post",
			url: "mxNewsBusinessAction/addCommentPraise.action",
			data: {commentId:"${MxNewsComment.commentId}"},
		    dataType: 'json',//接收回调数据形式
			success: function (data) {
				    console.log(data);
						mui.toast(data.data.msg);
					//更新评论点赞数
					if(data.data.success){
						$("#commentPraiseNum").val(data.data.data);
					}
			},
			error: function (msg) {
				mui.toast("点赞失败，请联系管理员！");
			}
		});
	}else{
		$("#commentPraise").css('color','#aaa');
		$("#praiseSpan").attr("title","点赞");
		//取消点赞
		$.ajax({
			type: "post",
			url: "mxNewsBusinessAction/delCommentPraise.action",
			data: {commentId:"${MxNewsComment.commentId}"},
		    dataType: 'json',//接收回调数据形式
			success: function (data) {
				    console.log(data);
					mui.toast(data.data.msg);
					//更新评论点赞数
					if(data.data.success){
						$("#commentPraiseNum").val(data.data.data);
					}
			},
			error: function (msg) {
				mui.toast("取消点赞失败，请联系管理员！");
			}
		});
	}
}
</script>
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

var createDate="${MxNewsComment.createDate}";
$(function(){
	var createDateNew = getDateDiff(getDateTimeStamp((createDate).replace(/-/g,"/").replace("T"," ")));
	$("#commentDate").val(createDateNew);
	
	/**********************动态加载回复***********************/
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
	                url: '<%=basePath%>mxNewsBusinessAction/getCommentBackByCommentId.action?commentId=${MxNewsComment.commentId}', 
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
	                        result +=   '<li class="commentBackLi">'
	                        				+'<div class="commentBackHeader">'
		                        				+'<div class="weixinBackHeadUrl">'
		                        				+'<img src="'+data.lists[i].weixinHeadUrl+'" alt="">'
		                        				+'</div>'
											    +'<div class="commentBackHead">'
											        +'<div class="weixinBackNikeName" style="height:40px;line-height: 40px; text-align:left;display:inline;font-size: 0.9rem;">'+data.lists[i].weixinNikeName+'</div>'
													+'<span class="backDate" style="width:auto; text-align:right;padding-left:20px;font-size: 0.7rem;color: #999;">'+getDateDiff(getDateTimeStamp((data.lists[i].createDate).replace(/-/g,"/").replace("T"," ")))+'</span>'
											    +'</div>'
										    +'</div>'
										    +'<div class="commentBackContent">'+data.lists[i].commentBack+'</div>';
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
//用户评论页面
function userComments(userId){
    var url = "mxNewsBusinessAction/userCommentsPage.action?userId="+userId;
	window.location.href = url;
}
</script>


