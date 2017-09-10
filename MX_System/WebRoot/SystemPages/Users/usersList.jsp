<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>"/>
<meta charset="UTF-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->

<link rel="stylesheet" type="text/css" href="<%=basePath%>SystemPages/common/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>SystemPages/common/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>SystemPages/common/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>SystemPages/common/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>SystemPages/common/static/h-ui.admin/css/style.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>SystemPages/common/lib/layui/css/layui.css"  media="all"/>
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->

<title>用户管理</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i>用户管理 <span class="c-gray en">&gt;</span>用户列表 
	<a class="btn btn-primary radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" >
	<i class="Hui-iconfont">&#xe68f;刷新当前页面</i></a>
</nav>
<div class="page-container">
	<div>
	<div class="f-l">
		<a href="javascript:;" onclick="datadel()" class="btn btn-danger radius">
				<i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a> 
			<a href="javascript:;" onclick="member_add('添加用户','member-add.html','','510')" class="btn btn-primary radius">
				<i class="Hui-iconfont">&#xe600;</i> 添加用户</a>
	</div>
	<div class="f-r">
		<input type="text" class="input-text" style="width:250px" placeholder="输入用户名称、电话、邮箱" id="" name="">
		<button type="submit" class="btn btn-primary radius" id="" name=""><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
	</div>
	</div>
	
	<div class="mt-20">
	<table class="table table-border table-bordered table-hover table-bg tale-sort"   id="tb">
		<thead class="text-c">
			<tr class="text-c">
				<th width="25"><input type="checkbox" name="" value=""></th>
				<th width="80">用户类型</th>
				<th width="100">用户名</th>
				<th width="100">微信昵称</th>
				<th width="40">性别</th>
				<th width="90">手机</th>
				<th width="150">邮箱</th>
				<th width="">地址</th>
				<th width="130">最近登录</th>
				<th width="70">状态</th>
				<th width="100">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${allUsers.list}" var="item">
			<tr class="text-c">
				<td><input type="checkbox" value="1" name=""></td>
				<td><c:if test="${item.userTypeId eq 1100}">管理员</c:if><c:if test="${item.userTypeId eq -1}">微信用户</c:if></td>
				<td><u style="cursor:pointer" class="text-primary" onclick="member_show('张三','member-show.html','10001','360','400')">${item.userRealName}</u></td>
				<td>${item.weixinNikeName}</td>
				<td>${item.userSex}</td>
				<td>${item.userPhoneNum}</td>
				<td>${item.userEmail}</td>
				<td>${item.userAddr}</td>
				<td><fmt:formatDate value="${item.lastLoginTime}" pattern="yyyy-MM-dd　HH:mm"/></td>
				<td class="td-status">
					<c:if test="${item.userState eq 0}">
						<span class="label label-success radius">正常</span>
					</c:if>
					<c:if test="${item.userState eq -1}">
						<span class="label label-danger radius">停用</span>
					</c:if>
				</td>
				<td class="td-manage">
					<c:if test="${item.userState eq 0}">
						<a style="text-decoration:none" onClick="member_stop(this,'10001')" href="javascript:;" title="停用"><i class="Hui-iconfont">&#xe631;</i></a> 
					</c:if>
					<c:if test="${item.userState eq -1}">
						<a style="text-decoration:none" onClick="member_open(this,'10001')" href="javascript:;" title="启用"><i class="Hui-iconfont">&#xe6e1;</i></a>
					</c:if>
					<a title="编辑" href="javascript:;" onclick="member_edit('编辑','member-add.html','4','','510')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a> 
					<%--<a style="text-decoration:none" class="ml-5" onClick="change_password('修改密码','change-password.html','10001','600','270')" href="javascript:;" title="修改密码"><i class="Hui-iconfont">&#xe63f;</i></a> 
					--%><a title="删除" href="javascript:;" onclick="member_del(this,'1')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a>
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	
	</div>
	<div class="text-c">
      <div id="_pager"></div>
   </div>
</div>

<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="<%=basePath%>SystemPages/common/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="<%=basePath%>SystemPages/common/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="<%=basePath%>SystemPages/common/static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="<%=basePath%>SystemPages/common/static/h-ui.admin/js/H-ui.admin.js"></script> 
<!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="<%=basePath%>SystemPages/common/lib/My97DatePicker/4.8/WdatePicker.js"></script> 
<script type="text/javascript" src="<%=basePath%>SystemPages/common/lib/datatables/1.10.0/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="<%=basePath%>SystemPages/common/lib/layui/layui.js" charset="utf-8"></script>
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
		    	   
		    	    
		    	    //首次不执行
		    	    if(!first){
		    	    	 //obj包含了当前分页的所有参数，比如：
			    	    alert(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
			    	    alert(obj.limit); //得到每页显示的条数
		    	      //do something
		    	      $("#tb tbody").html("");
		    	      //getPage(obj.curr);
		    	    }
		    	  }
		  });
	});

function getPage(curr){
	 $.getJSON('/page', {
	        page: curr //向服务端传的参数
	      }, function(res){
	          //此处输出内容
	          var table = $("#tb");
	          //table.attr({class:"layui-table admin-table",id:"page"});

	          //var thead = $("<thead><tr><th>编号</th><th>姓名</th><th>行为</th><th>时间</th><th>操作</th></tr></thead>");
	          //table.append(thead);
	          var tbody = $("#tb tbody");
	          //tbody.attr({id:"content"});

	          $(function(){
	                var datas = res.list;
	                 $.each(datas,function(index,value){
	                	 
	                	 tbody.innerHTML+='<tr class="text-c">';
	                	 
	                	 
	                	 <tr class="text-c">
	     				<td><input type="checkbox" value="1" name=""></td>
	     				<td><c:if test="${item.userTypeId eq 1100}">管理员</c:if><c:if test="${item.userTypeId eq -1}">微信用户</c:if></td>
	     				<td><u style="cursor:pointer" class="text-primary" onclick="member_show('张三','member-show.html','10001','360','400')">${item.userRealName}</u></td>
	     				<td>${item.weixinNikeName}</td>
	     				<td>${item.userSex}</td>
	     				<td>${item.userPhoneNum}</td>
	     				<td>${item.userEmail}</td>
	     				<td>${item.userAddr}</td>
	     				<td><fmt:formatDate value="${item.lastLoginTime}" pattern="yyyy-MM-dd　HH:mm"/></td>
	     				<td class="td-status">
	     					<c:if test="${item.userState eq 0}">
	     						<span class="label label-success radius">正常</span>
	     					</c:if>
	     					<c:if test="${item.userState eq -1}">
	     						<span class="label label-danger radius">停用</span>
	     					</c:if>
	     				</td>
	     				<td class="td-manage">
	     					<c:if test="${item.userState eq 0}">
	     						<a style="text-decoration:none" onClick="member_stop(this,'10001')" href="javascript:;" title="停用"><i class="Hui-iconfont">&#xe631;</i></a> 
	     					</c:if>
	     					<c:if test="${item.userState eq -1}">
	     						<a style="text-decoration:none" onClick="member_open(this,'10001')" href="javascript:;" title="启用"><i class="Hui-iconfont">&#xe6e1;</i></a>
	     					</c:if>
	     					<a title="编辑" href="javascript:;" onclick="member_edit('编辑','member-add.html','4','','510')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a> 
	     					<%--<a style="text-decoration:none" class="ml-5" onClick="change_password('修改密码','change-password.html','10001','600','270')" href="javascript:;" title="修改密码"><i class="Hui-iconfont">&#xe63f;</i></a> 
	     					--%><a title="删除" href="javascript:;" onclick="member_del(this,'1')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a>
	     				</td>
	     			</tr>
	     			
	     			
	     			
	     			
	                	 
	                	 
	                	 
	                    var tr = $("<tr></tr>");
	                    tr.attr({class:"text-c"});
	                    tr.append("<td>"+ (++index) + "</td>");
	                    tbody.append(tr);
	                    tr.append("<td>"+ value.staffName + "</td>");
	                    tbody.append(tr);
	                    tr.append("<td>"+ value.operation + "</td>");
	                    tbody.append(tr);
	                    tr.append("<td>"+ value.createTime + "</td>");
	                      tbody.append(tr);
	                      var td = $("<td></td>");
	                      var div = $("<div></div>");
	                      div.attr({class:"layui-btn-group"});
	                      var button1 = $("<button detailId=" + value.weeklyId +">详情</button>");
	                      button1.attr({class:"layui-btn detail"});
	                      var button2 = $("<button recoveryId=" + value.weeklyId +">恢复</button>");
	                      button2.attr({class:"layui-btn recovery"});
	                      div.append(button1);div.append(button2);
	                      td.append(div);
	                      tr.append(td);
	                      tbody.append(tr);
	                  }); 
	              });
	          table.append(tbody);
	         $("#log-list").append(table);
	        // $("#log-list").innerHTML = table;

	      });
}

/*用户-添加*/
function member_add(title,url,w,h){
	layer_show(title,url,w,h);
}
/*用户-查看*/
function member_show(title,url,id,w,h){
	layer_show(title,url,w,h);
}
/*用户-停用*/
function member_stop(obj,id){
	layer.confirm('确认要停用吗？',function(index){
		$.ajax({
			type: 'POST',
			url: '',
			dataType: 'json',
			success: function(data){
				$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="member_start(this,id)" href="javascript:;" title="启用"><i class="Hui-iconfont">&#xe6e1;</i></a>');
				$(obj).parents("tr").find(".td-status").html('<span class="label label-defaunt radius">已停用</span>');
				$(obj).remove();
				layer.msg('已停用!',{icon: 5,time:1000});
			},
			error:function(data) {
				console.log(data.msg);
			},
		});		
	});
}

/*用户-启用*/
function member_start(obj,id){
	layer.confirm('确认要启用吗？',function(index){
		$.ajax({
			type: 'POST',
			url: '',
			dataType: 'json',
			success: function(data){
				$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="member_stop(this,id)" href="javascript:;" title="停用"><i class="Hui-iconfont">&#xe631;</i></a>');
				$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已启用</span>');
				$(obj).remove();
				layer.msg('已启用!',{icon: 6,time:1000});
			},
			error:function(data) {
				console.log(data.msg);
			},
		});
	});
}
/*用户-编辑*/
function member_edit(title,url,id,w,h){
	layer_show(title,url,w,h);
}
/*密码-修改*/
function change_password(title,url,id,w,h){
	layer_show(title,url,w,h);	
}
/*用户-删除*/
function member_del(obj,id){
	layer.confirm('确认要删除吗？',function(index){
		$.ajax({
			type: 'POST',
			url: '',
			dataType: 'json',
			success: function(data){
				$(obj).parents("tr").remove();
				layer.msg('已删除!',{icon:1,time:1000});
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