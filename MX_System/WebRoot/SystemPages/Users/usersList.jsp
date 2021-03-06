﻿<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<base href="<%=basePath%>" />
<meta http-equiv="X-UA-Compatible" content="IE=9;IE=8;IE=7;IE=EDGE">
<meta charset="UTF-8">
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
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>SystemPages/common/lib/layui/css/layui.css"
	media="all" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->

<title>用户管理</title>
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i>用户管理 <span class="c-gray en">&gt;</span>用户列表
		<a class="btn btn-primary radius r" id="btn-refresh"
			style="line-height:1.6em;margin-top:3px"
			href="javascript:location.replace(location.href);" title="刷新"> <i
			class="Hui-iconfont">&#xe68f;刷新当前页面</i> </a>
	</nav>
	<div class="page-container">
		<div>
			<div class="f-l">
				<a href="javascript:;" onclick="datadel()"
					class="btn btn-danger radius"> <i class="Hui-iconfont">&#xe6e2;</i>
					批量删除</a> <a href="javascript:;"
					onclick="member_add('添加管理员','userAction/gotoUserAdd.action','','')"
					class="btn btn-primary radius"> <i class="Hui-iconfont">&#xe600;</i>
					添加管理员</a>
			</div>
			<div class="f-r">
				<input type="text" class="input-text" style="width:250px"
					placeholder="输入微信昵称、姓名、电话、邮箱" id="txtSearch" name="txtSearch">
				<button type="button" onclick="searchUsers();"
					class="btn btn-primary radius" id="" name="">
					<i class="Hui-iconfont">&#xe665;</i> 搜索
				</button>
			</div>
		</div>
		
		
		<div class="mt-20">
			<table
				class="table table-border table-bordered table-hover table-bg tale-sort"
				id="tableList">
				<thead class="text-c">
					<tr class="text-c">
						<th width="39px"><input type="checkbox" name="" value="">全选</th>
						<th>用户类型</th>
						<th>用户名</th>
						<th>微信昵称</th>
						<th>姓名</th>
						<th>性别</th>
						<th>手机</th>
						<th>邮箱</th>
						<th>地址</th>
						<th>最近登录</th>
						<th>状态</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${allUsers.list}" var="item">
						<tr class="text-c">
							<td><input type="checkbox" value="${item.userId}"
								name="userIds"></td>
							<td><c:if test="${item.userTypeId eq 1100}">管理员</c:if> <c:if
									test="${item.userTypeId eq -1}">微信用户</c:if></td>
							<td>${item.userName}</td>
							<td>${item.weixinNikeName}</td>
							<td>${item.userRealName}</td>
							<c:if test="${item.userSex eq 2}">
								<td>女</td>
							</c:if>
							<c:if test="${item.userSex eq 1}">
								<td>男</td>
							</c:if>
							<c:if test="${item.userSex eq 0}">
								<td>保密</td>
							</c:if>
							<c:if test="${item.userSex ne 1&&item.userSex ne 2&&item.userSex ne 0}">
								<td>未知</td>
							</c:if>

							<td>${item.userPhoneNum}</td>
							<td>${item.userEmail}</td>
							<td>${item.userAddr}</td>
							<td><fmt:formatDate value="${item.lastLoginTime}"
									pattern="yyyy-MM-dd　HH:mm" /></td>
							<td class="td-status"><c:if test="${item.userState eq 0}">
									<span class="label label-success radius">正常</span>
								</c:if> <c:if test="${item.userState eq -1}">
									<span class="label label-danger radius">停用</span>
								</c:if></td>
							<td class="td-manage"><c:if test="${item.userState eq 0}">
									<a style="text-decoration:none"
										onClick="user_stop_open(this,0,'${item.userId}')" href="javascript:;"
										title="停用"><i class="Hui-iconfont">&#xe631;</i> </a>
								</c:if> <c:if test="${item.userState eq -1}">
									<a style="text-decoration:none"
										onClick="user_stop_open(this,1,'${item.userId}')" href="javascript:;"
										title="启用"><i class="Hui-iconfont">&#xe6e1;</i> </a>
								</c:if> <a title="编辑" href="javascript:;"
								onclick="member_edit('编辑','userAction/gotoUserEdit.action?userId=${item.userId}','','510')"
								class="ml-5" style="text-decoration:none"><i
									class="Hui-iconfont">&#xe6df;</i> </a> 
									<a style="text-decoration:none" class="ml-5" onclick="restore_password(${item.userId})" href="javascript:;" title="重置密码"><i class="Hui-iconfont">&#xe63f;</i></a> 
								<a title="删除" href="javascript:;"
								onclick="user_del(this,'${item.userId}')" class="ml-5"
								style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i>
							</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

		</div>
		<div class="text-c">
			<div id="Per_All" class="f-l">每页显示${allUsers.pageSize}条，共${allUsers.allRow}条</div>
			<div class="f-c" id="_pager"></div>
		</div>
	</div>

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
		src="<%=basePath%>SystemPages/common/lib/My97DatePicker/4.8/WdatePicker.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>SystemPages/common/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>SystemPages/common/lib/layui/layui.js"
		charset="utf-8"></script>
	<script type="text/javascript">

	layui.use('laypage', function(){
	  var laypage = layui.laypage;
	 
	  //执行一个laypage实例
	  laypage.render({
		    elem: '_pager'
		    ,count: ${allUsers.allRow}
		    ,limit:${allUsers.pageSize}
		    ,theme: '#1E9FFF'
		    ,jump: function(obj, first){
		    	//alert(123);
		    	    //首次不执行
		    	    if(!first){
		    	    	var url='userAction/getUsersByPage.action?number='+Math.random();
		    	      	getPage(obj.curr,url);
		    	    }
		    	  }
		  });
	});
	
	
</script>

<script type="text/javascript">

/*搜索*/
function searchUsers(){
	var txtSearch=document.getElementById("txtSearch").value;
	var url='userAction/searchUsers.action?number='+Math.random();
	$.ajax({
		type: 'POST',
		url:url,
		data:{'txtSearch':txtSearch},
		dataType:'json',
		success:function(response){
			//alert(111);
			
			var list=response.list;
			
			var tb=flushList(list);
			
			if(tb.length!=0){
				$("#tableList tbody").html(tb);
				//$('#tableList tbody').innerHTML+=tb;
			}else{
				$("#tableList tbody").html("");
			}
			
			document.getElementById("Per_All").innerHTML = ('每页显示'+response.pageSize+'条，共'+response.allRow+'条');
			
			layui.use('laypage', function(){
				  var laypage = layui.laypage;
				  //执行一个laypage实例
				  laypage.render({
					    elem: '_pager'
					    ,count: response.allRow
					    ,limit:response.pageSize
					    ,theme: '#1E9FFF'
					  });
				});
			
			
			//location.replace(location.href);
			
		},
        error: function (jqXHR, textStatus, errorThrown) {
            if (textStatus == 'timeout') {
                a_info_alert('请求超时');
                return false;
            }
            alert(jqXHR.responseText);
        }
	});
}

/*局部刷新列表*/
var flushList=function(list){
	var role;
	var tb='';
	$.each(list, function(index, item){
		if(item.userTypeId=='1100'){
   		 	role='管理员';
   	 	}else{
   		 	role='微信用户';
   	 	}
		tb+=('<tr class="text-c">');
        tb+=('<td><input type="checkbox" name="userIds" value="'+item.userId+'"></td>');
        tb+=('<td>'+role+'</td>');
        tb+=('<td>'+item.userName+'</td>');
        tb+=('<td>'+item.weixinNikeName+'</td>');
        tb+=('<td>'+item.userRealName+'</td>');
        if(item.userSex=='1'){
        	tb+=('<td>男</td>');
        }else if(item.userSex=='2'){
        	tb+=('<td>女</td>');
        }else if(item.userSex=='0'){
        	tb+=('<td>保密</td>');
        }else{
        	tb+=('<td>未知</td>');
        }
        tb+=('<td>'+item.userPhoneNum+'</td>');
        tb+=('<td>'+item.userEmail+'</td>');
        tb+=('<td>'+item.userAddr+'</td>');
        tb+=('<td>'+item.lastLoginTime+'</td>');
        if(item.userState=='0'){
            tb+=('<td class="td-status"><span class="label label-success radius">正常</span></td>');
            tb+=('<td class="td-manage">');
            tb+=('<a style="text-decoration:none" onClick="user_stop_open(this,0,'+item.userId+')" href="javascript:;" title="停用"><i class="Hui-iconfont">&#xe631;</i></a>');
        }else{
        	tb+=('<td class="td-status"><span class="label label-danger radius">停用</span></td>');
            tb+=('<td class="td-manage">');
            tb+=('<a style="text-decoration:none" onClick="user_stop_open(this,1,'+item.userId+')" href="javascript:;" title="启用"><i class="Hui-iconfont">&#xe6e1;</i></a>');
        }
        tb+=('<a title="编辑" href="javascript:;" onclick="member_edit(\'编辑\',\'userAction/gotoUserEdit.action?userId='+item.userId+'\',\'\',\'510\')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a>');
        tb+=('<a style="text-decoration:none" class="ml-5" onclick="restore_password('+item.userId+')" href="javascript:;" title="重置密码"><i class="Hui-iconfont">&#xe63f;</i></a>');
        tb+=('<a title="删除" href="javascript:;" onclick="user_del(this,'+item.userId+')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a>');
        tb+=('</td></tr>');
		
	});
	return tb;
}

/*分页控件点击事件*/
function getPage(curr,url){
	$.ajax({
		type: 'POST',
		url:url,
		data:{'page':curr},
		dataType:'json',
		success:function(response){
			///alert(111);
			var list=response.list;
       	 	
       	 	///alert(222);
			var tb=flushList(list);
			
			if(tb.length!=0){
				$("#tableList tbody").html(tb);
				//$('#tableList tbody').innerHTML+=tb;
			}
			//alert(tb);
		},
        error: function (jqXHR, textStatus, errorThrown) {
            if (textStatus == 'timeout') {
                a_info_alert('请求超时');
                return false;
            }
            alert(jqXHR.responseText);
        }
	});
}


/*用户-停用-启用*/
function user_stop_open(obj,op,id){
	//alert('');
	var opt='';
	var url='userAction/open_close_User.action?number='+Math.random();
	if(op==1){//启用
		opt='确认启用该用户吗？';
	}else{//禁用
		opt='确认停用该用户吗？';
	}
	layer.confirm(opt,function(index){
		$.ajax({
			type: 'POST',
			url: url,
			data:{'operate':op,'userId':id},
			dataType: 'json',
			success: function(data){
				if(data.done=='0'&&data.operate=='1'){
					$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="user_stop_open(this,0,'+id+')" href="javascript:;" title="停用"><i class="Hui-iconfont">&#xe631;</i></a>');
					$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">正常</span>');
					$(obj).remove();
					layer.msg('用户已启用!',{icon: 6,time:1000});
				}else if(data.done=='0'&&data.operate=='0'){
					$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="user_stop_open(this,1,'+id+')" href="javascript:;" title="启用"><i class="Hui-iconfont">&#xe6e1;</i></a>');
					$(obj).parents("tr").find(".td-status").html('<span class="label label-danger radius">停用</span>');
					$(obj).remove();
					layer.msg('用户已停用!',{icon: 6,time:1000});
				}else{
					layer.msg('未知错误!',{icon: 5,time:1000});
				}
				
			},
			error:function(data) {
				console.log(data.msg);
			},
		});		
	});
}

/*用户-添加*/
function member_add(title,url,w,h){
	layer_show(title,url,w,h);
}

/*用户-编辑*/
function member_edit(title,url,w,h){
	layer_show(title,url,w,h);
}
/*密码-重置*/
function restore_password(id){
	layer.confirm('确认要重置用户密码？',function(index){
		$.ajax({
			type: 'POST',
			url: 'userAction/restorePassword.action?number='+Math.random(),
			data:{'userId':id},
			dataType: 'json',
			success: function(data){
				if(data.done=='0'){
					//$(obj).parents("tr").remove();
					layer.msg('密码已重置!',{icon:1,time:1000});
				}else{
					layer.msg('密码重置失败!',{icon:4,time:1000});
				}
				
			},
			error:function(data) {
				console.log(data.msg);
			},
		});		
	});
}
/*用户-删除*/
function user_del(obj,id){
	//alert('');
	layer.confirm('确认要删除该用户吗？',function(index){
		$.ajax({
			type: 'POST',
			url: 'userAction/deleteUser.action?number='+Math.random(),
			data:{'userId':id},
			dataType: 'json',
			success: function(data){
				if(data.done=='0'){
					$(obj).parents("tr").remove();
					layer.msg('已删除!',{icon:1,time:1000});
				}else{
					layer.msg('删除失败!',{icon:4,time:1000});
				}
				
			},
			error:function(data) {
				console.log(data.msg);
			},
		});		
	});
}

/*批量删除*/
function datadel(){
	var arrays = new Array();//定义一个数组 
	var i=0; 
	 $('input[name="userIds"]:checked').each(function(){ 
		 arrays[i]=$(this).val(); 
		 i=i+1;
	 });
	 if(i==0){alert('未选择任何用户');return;}
	 layer.confirm('确认要删除这些用户吗？',function(index){
			$.ajax({
				type: 'POST',
				url: ('userAction/deleteUsers.action?ids='+ JSON.stringify(arrays)),
				dataType: 'json',
				success: function(data){
					//alert('成功');
					//$(obj).parents("tr").remove();
					var refresh=document.getElementById('btn-refresh');
					refresh.click();
					layer.msg('已删除!',{icon:1,time:3600});
				},
				error:function(data) {
					console.log(data.msg);
				},
			});		
		});
}
</script>
</body>
</html>