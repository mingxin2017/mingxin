<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>
<meta charset="utf-8">
<title>Hello MUI</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<link rel="stylesheet"
	href="<%=basePath%>WeixinPages/common/css/mui.min.css">
	<!-- 自定义图标字体 -->
<link rel="stylesheet"
	href="<%=basePath%>WeixinPages/common/css/icomoon.css">
</head>

<body>
	<div class="mui-card">
		<ul class="mui-table-view">
			<li class="mui-table-view-cell mui-collapse"><a
				class="mui-navigate-right" href="#">个人信息</a>
				<div class="mui-collapse-content">
					<form class="mui-input-group">
						<div class="mui-input-row">
							<label>姓名</label> <input type="text" class="mui-input-clear"
								placeholder="输入姓名">
						</div>
						<div class="mui-input-row">
							<label>手机号</label> <input type="text" class="mui-input-clear"
								placeholder="输入手机号">
						</div>
						<div class="mui-input-row">
							<label>联系地址</label> <input type="text" class="mui-input-clear"
								placeholder="输入地址">
						</div>
						<div class="mui-input-row">
							<label>电子邮箱</label> <input type="text" class="mui-input-clear"
								placeholder="输入电子邮箱">
						</div>
						<div class="mui-button-row">
							<button class="mui-btn mui-btn-primary" type="button"
								onclick="return false;">提交修改</button>
						</div>
					</form>
				</div></li>
			<li class="mui-table-view-cell mui-collapse "><a
				class="mui-navigate-right" href="#">我的照片(8)</a>
				<div class="mui-collapse-content">
							<div id="slider" class="mui-slider">
								<div class="mui-slider-group mui-slider-loop">
									<!-- 额外增加的一个节点(循环轮播：第一个节点是最后一张轮播) -->
									<div class="mui-slider-item mui-slider-item-duplicate">
										
											<img src="/MX_System/WeixinPages/common/uploadImg/myspaceImg/100/o8iGuv66gILabgoSL3Ibz8euYiZk/8.jpg">
										
									</div>
									<!-- 第一张 -->
									<div class="mui-slider-item">
										
											<img src="/MX_System/WeixinPages/common/uploadImg/myspaceImg/100/o8iGuv66gILabgoSL3Ibz8euYiZk/1.jpg">
										
									</div>
									<!-- 第二张 -->
									<div class="mui-slider-item">
										
											<img src="/MX_System/WeixinPages/common/uploadImg/myspaceImg/100/o8iGuv66gILabgoSL3Ibz8euYiZk/2.jpg">
										
									</div>
									<!-- 第三张 -->
									<div class="mui-slider-item">
										
											<img src="/MX_System/WeixinPages/common/uploadImg/myspaceImg/100/o8iGuv66gILabgoSL3Ibz8euYiZk/3.jpg">
										
									</div>
									<!-- 第四张 -->
									<div class="mui-slider-item">
										
											<img src="/MX_System/WeixinPages/common/uploadImg/myspaceImg/100/o8iGuv66gILabgoSL3Ibz8euYiZk/4.jpg">
										
									</div>
									<!-- 第5张 -->
									<div class="mui-slider-item">
										
											<img src="/MX_System/WeixinPages/common/uploadImg/myspaceImg/100/o8iGuv66gILabgoSL3Ibz8euYiZk/5.jpg">
										
									</div>
									<!-- 第6张 -->
									<div class="mui-slider-item">
										
											<img src="/MX_System/WeixinPages/common/uploadImg/myspaceImg/100/o8iGuv66gILabgoSL3Ibz8euYiZk/6.jpg">
										
									</div>
									<!-- 第7张 -->
									<div class="mui-slider-item">
										
											<img src="/MX_System/WeixinPages/common/uploadImg/myspaceImg/100/o8iGuv66gILabgoSL3Ibz8euYiZk/7.jpg">
										
									</div>
									<!-- 第8张 -->
									<div class="mui-slider-item">
										
											<img src="/MX_System/WeixinPages/common/uploadImg/myspaceImg/100/o8iGuv66gILabgoSL3Ibz8euYiZk/8.jpg">
										
									</div>
									<!-- 额外增加的一个节点(循环轮播：最后一个节点是第一张轮播) -->
									<div class="mui-slider-item mui-slider-item-duplicate">
										
											<img src="/MX_System/WeixinPages/common/uploadImg/myspaceImg/100/o8iGuv66gILabgoSL3Ibz8euYiZk/1.jpg">
										
									</div>
								</div>
								
							</div>
						</div>
			</li>
			<li class="mui-table-view-cell mui-collapse"><a
				class="mui-navigate-right" href="#">我的讨论(10)</a>
				<div class="mui-collapse-content">
				<ul class="mui-table-view mui-table-view-striped mui-table-view-condensed">
		          <li class="mui-table-view-cell">
		            <div class="mui-table">
		                <div class="mui-table-cell mui-col-xs-10">
		                    <h4 class="mui-ellipsis-3">活动很有趣，下次还要参加！</h4>
		                    <h5>发表时间：2017-06-06 12:06</h5>
		                </div>
		                <div class="mui-table-cell mui-col-xs-2 mui-text-right">
		                    <span class="mui-icon icomoon icon-thumbs-up"></span>51</a>
		                     
		                </div>
		            </div>
		        </li>
		        <li class="mui-table-view-cell">
		            <div class="mui-table">
		                <div class="mui-table-cell mui-col-xs-10">
		                    <h4 class="mui-ellipsis-6">回忆起在学校的美好时光，老同学能再相聚这是人生幸事。</h4>
		                    <h5>发表时间：2017-06-06 12:06</h5>
		                </div>
		                <div class="mui-table-cell mui-col-xs-2 mui-text-right">
		                    <span class="mui-icon icomoon icon-thumbs-up"></span>16</a>
		                     
		                </div>
		            </div>
		        </li>
		         <li class="mui-table-view-cell">
		            <div class="mui-table">
		                <div class="mui-table-cell mui-col-xs-10">
		                    <h4 class="mui-ellipsis-6">活动很有趣，下次还要参加！</h4>
		                    <h5>发表时间：2017-06-06 12:06</h5>
		                </div>
		                <div class="mui-table-cell mui-col-xs-2 mui-text-right">
		                    <span class="mui-icon icomoon icon-thumbs-up"></span>7</a>
		                     
		                </div>
		            </div>
		        </li>
		        <li class="mui-table-view-cell">
		            <div class="mui-table">
		                <div class="mui-table-cell mui-col-xs-10">
		                    <h4 class="mui-ellipsis-6">回忆起在学校的美好时光，老同学能再相聚这是人生幸事。</h4>
		                    <h5>发表时间：2017-06-06 12:06</h5>
		                </div>
		                <div class="mui-table-cell mui-col-xs-2 mui-text-right">
		                    <span class="mui-icon icomoon icon-thumbs-up"></span>11</a>
		                     
		                </div>
		            </div>
		        </li>
		         <li class="mui-table-view-cell">
		            <div class="mui-table">
		                <div class="mui-table-cell mui-col-xs-10">
		                    <h4 class="mui-ellipsis-6">活动很有趣，下次还要参加！</h4>
		                    <h5>发表时间：2017-06-06 12:06</h5>
		                </div>
		                <div class="mui-table-cell mui-col-xs-2 mui-text-right">
		                    <span class="mui-icon icomoon icon-thumbs-up"></span>64</a>
		                     
		                </div>
		            </div>
		        </li>
		        <li class="mui-table-view-cell">
		            <div class="mui-table">
		                <div class="mui-table-cell mui-col-xs-10">
		                    <h4 class="mui-ellipsis-6">回忆起在学校的美好时光，老同学能再相聚这是人生幸事。</h4>
		                    <h5>发表时间：2017-06-06 12:06</h5>
		                </div>
		                <div class="mui-table-cell mui-col-xs-2 mui-text-right">
		                    <span class="mui-icon icomoon icon-thumbs-up"></span>34</a>
		                     
		                </div>
		            </div>
		        </li>
		         <li class="mui-table-view-cell">
		            <div class="mui-table">
		                <div class="mui-table-cell mui-col-xs-10">
		                    <h4 class="mui-ellipsis-6">活动很有趣，下次还要参加！</h4>
		                    <h5>发表时间：2017-06-06 12:06</h5>
		                </div>
		                <div class="mui-table-cell mui-col-xs-2 mui-text-right">
		                    <span class="mui-icon icomoon icon-thumbs-up"></span>51</a>
		                     
		                </div>
		            </div>
		        </li>
		        <li class="mui-table-view-cell">
		            <div class="mui-table">
		                <div class="mui-table-cell mui-col-xs-10">
		                    <h4 class="mui-ellipsis-6">回忆起在学校的美好时光，老同学能再相聚这是人生幸事。</h4>
		                    <h5>发表时间：2017-06-06 12:06</h5>
		                </div>
		                <div class="mui-table-cell mui-col-xs-2 mui-text-right">
		                    <span class="mui-icon icomoon icon-thumbs-up"></span>67</a>
		                     
		                </div>
		            </div>
		        </li>
		         <li class="mui-table-view-cell">
		            <div class="mui-table">
		                <div class="mui-table-cell mui-col-xs-10">
		                    <h4 class="mui-ellipsis-6">活动很有趣，下次还要参加！</h4>
		                    <h5>发表时间：2017-06-06 12:06</h5>
		                </div>
		                <div class="mui-table-cell mui-col-xs-2 mui-text-right">
		                    <span class="mui-icon icomoon icon-thumbs-up"></span>40</a>
		                     
		                </div>
		            </div>
		        </li>
		        <li class="mui-table-view-cell">
		            <div class="mui-table">
		                <div class="mui-table-cell mui-col-xs-10">
		                    <h4 class="mui-ellipsis-6">回忆起在学校的美好时光，老同学能再相聚这是人生幸事。</h4>
		                    <h5>发表时间：2017-06-06 12:06</h5>
		                </div>
		                <div class="mui-table-cell mui-col-xs-2 mui-text-right">
		                    <span class="mui-icon icomoon icon-thumbs-up"></span>55</a>
		                     
		                </div>
		            </div>
		        </li>
		        </ul>
				</div></li>
		</ul>
	</div>
	<script src="<%=basePath%>WeixinPages/common/js/mui.min.js"></script>
	<script src="<%=basePath%>WeixinPages/common/js/mui.zoom.js"></script>
	<script src="<%=basePath%>WeixinPages/common/js/mui.previewimage.js"></script>
	<script>
			mui.init();
			
			//mui.previewImage();
		</script>
</body>
</html>
