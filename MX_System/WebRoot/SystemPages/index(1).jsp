<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.fastrise.fastdev.security.SysSecurityHolder"%>
<%
	String con = SysSecurityHolder.getLoginUserOwnCompany();
%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>港务集团生产经营系统</title>
<link href="${ctx}/resources/js/easyui/themes/default/easyui.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/resources/js/easyui/themes/icon.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/resources/css/portal/portal.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/resources/css/icons.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/resources/js/easyui/extend/jquery.ext.css" rel="stylesheet" type="text/css" />
<!-- 引用禁止后退键的js -->
<script type="text/javascript" src="${ctx}/resources/js/extBrowser.js"></script>
<script src="${ctx}/resources/js/jquery-1.11.0.min.js" type="text/javascript"></script>
<script src="${ctx}/resources/js/extJquery.js" type="text/javascript"></script>
<script src="${ctx}/resources/js/easyui/jquery.easyui.min.js" type="text/javascript"></script>
<script src="${ctx}/resources/js/easyui/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
<script src="${ctx}/resources/js/extEasyUI.js" type="text/javascript"></script>
<script src="${ctx}/resources/js/Default.js" type="text/javascript"></script>
<script src="${ctx}/resources/js/portal/jquery.portal.js" type="text/javascript"></script>
<link rel="stylesheet" href="${ctx}/resources/js/ui/css/ui.css" type="text/css" />
<link rel="stylesheet" href="${ctx}/resources/js/ui/css/icon.css" type="text/css" />


<script type="text/javascript" src="${ctx}/resources/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/easyui/extend/jquery.my97.js"></script>

<%@ include file="/common/kindeditor.jsp"%>
<%@ include file="/common/msg.jsp"%>
<script src="${ctx}/pages/system/index.js" type="text/javascript"></script>
<script type="text/javascript">
	var ctx = "${ctx}";
	var _layout = "${layout}";
	var _portals = "${portals}";
	var msgWindows = null;
	var userNewWarning = "${userNewWarning}";
	var jzxImpNewWarning = "${jzxImpNewWarning}";
	var seaCodeNewWarning = "${seaCodeNewWarning}";
</script>
<link rel="stylesheet" href="${ctx}/resources/css/common/index.css" type="text/css" />
<link rel="stylesheet" href="${ctx}/resources/css/common/style.css" type="text/css" />
<script type="text/javascript">
	var _gaq = _gaq || [];
	_gaq.push(['_setAccount', 'UA-38040071-1']);
	_gaq.push(['_trackPageview']);

	(function() {
		var ga = document.createElement('script');
		ga.type = 'text/javascript';
		ga.async = true;
		ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www')
				+ '.google-analytics.com/ga.js';
		var s = document.getElementsByTagName('script')[0];
		s.parentNode.insertBefore(ga, s);
		//消息窗口
		var con = <%=con%>
		if (con == '0') {
			openMsg();
		};
	})();
</script>
<!--
<script src="${ctx}/pages/business/szhmt/busicharge/chargeInvoice/TaxCardX.js" type="text/javascript"></script>
-->
</head>
<body class="easyui-layout" style="overflow-y: hidden" scroll="no">
	<noscript>
		<div
			style="position: absolute; display: none; z-index: 100000; height: 2046px; top: 0px; left: 0px; width: 100%; background: white; text-align: center;">
			<img src="${ctx}/resources/images/noscript.gif" alt='抱歉，请开启脚本支持！' />
		</div>
	</noscript>
	<div region="north" style="height: 50px; border: none; background-color: #B2DDFF;">
		<div class="header">
			<ul class="hlink" style="margin: 0;">
				<li>
					<strong>您好!${userName}</strong>
				</li>
				<li class="mes">
					<img class="icon" src="${ctx}/resources/images/stay.png" />
					<a href="${ctx}/selectSystem.html">切换系统</a>
				</li>
				<li>
					<img class="icon" src="${ctx}/resources/images/pwd.png" />
					<a style="cursor: hand;" href="#" onclick="javascript:addTab('修改密码','${ctx}/system/password/index.html');">修改密码</a>
				</li>
				<li id="show_msg" style="display: none">
					<img class="icon" src="${ctx}/resources/images/message.png" />
					<a style="cursor: hand;" href="#" onclick="openMsg();">消息提示</a>
				</li>
				<li>
					<img class="icon" src="${ctx}/resources/images/exit.png" />
					<a href="${ctx}/logout">退出</a>
				</li>
			</ul>
			<div class="logo" style="float: left">
				<img src="${ctx}/resources/images/logo.png" />
			</div>
			<%-- <div id="sessionInfoDiv" style="position: absolute; right: 20px; top: 8px;">
				<input id="session_pledge_point_id" type="hidden" value="${sessionScope.UserPointId}"> 
				<strong><font size="2px">当前质押点：<font size="2px" color="red"><b id="login_point">${sessionScope.UserPledgeName}-${sessionScope.UserPointName}</b></font></font></strong>
			</div> --%>
		</div>
	</div>
	<div region="south" data-options="border:false" style="height: 18px;">
		<%@ include file="/common/footer.jsp"%>
	</div>
	<div region="west" split="true" title="导航菜单" style="width: 180px; overflow: hidden;" icon="icon-redo">
		<div id="menu" class="easyui-accordion" fit="true" border="false">${accordion}</div>
	</div>
	<div region="center" id="mainPanle" style="overflow: hidden;">
		<div id="tabs" class="easyui-tabs" data-options="tools:'#index_tab_tools', fit:true, border:false"
			style="overflow: hidden;">
			<div title="主页" id="home" style="overflow: hidden;">
				<div class="easyui-layout" data-options="fit:true">
					<div data-options="region:'north', split:false, border:false" style="height: 50px"></div>
					<div data-options="region:'west', split:false, border:false" style="width: 50px;"></div>
					<div id="warning_div" data-options="region:'center', split:true, border:false"></div>
				</div>
				<!-- <iframe style="width: 100%;height: 100%;" frameborder="0" id="reportFrame" src="/SZHMT/ReportServer?reportlet=portal_chart_main.cpt&op=view&&year=2015"></iframe> -->
				<%-- <div id="portalLayout">
					<div data-options="region:'center',border:false">
						<div id="portal" style="position: relative;">
							<c:if test="${layout eq '1:3'}">
								<div style="width: 25%;"></div>
								<div style="width: 75%;"></div>
							</c:if>
							<c:if test="${layout eq '3:1'}">
								<div style="width: 75%;"></div>
								<div style="width: 25%;"></div>
							</c:if>
							<c:if test="${layout eq '1:1'}">
								<div style="width: 50%;"></div>
								<div style="width: 50%;"></div>
							</c:if>

						</div>
					</div>
				</div> --%>
			</div>
		</div>
	</div>
	<div id="index_tabsMenu" style="width: 120px; display: none;">
		<div title="refresh" data-options="iconCls:'ui-icon-refresh'">刷新</div>
		<div class="menu-sep"></div>
		<div title="close">关闭</div>
		<div title="closeOther">关闭其他</div>
		<div title="closeAll">关闭所有</div>
	</div>


	<div id="index_tab_tools" class="tabtools">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'ui-icon-ref'"
			onclick="tabRefresh()"></a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'ui-icon-delete'"
			onclick="tabClose()"></a>
	</div>
	<!-- 新的消息提示窗口 -->
	<div id="msg_win"></div>
	<!--
	<object id="GoldTax" classid="clsid:7126812F-8D2A-11D6-9C69-00E04C103A76" width="0" height="0"></object>
	-->
	<!-- <div id="ChangePointWindow" class="easyui-window" data-options="closed:'true'" style="width: 500px; height: 200px; padding: 10px;"></div> -->
</body>
<!-- bootstrap -->
<link rel="stylesheet" href="${ctx}/resources/HuiAlert/css/demo.css" type="text/css" />
<script type="text/javascript" src="${ctx}/resources/mask/jquery.mask.js"></script>
<link rel="stylesheet" href="${ctx}/resources/lobibox/css/lobibox.min.css" type="text/css" />
<script type="text/javascript" src="${ctx}/resources/lobibox/js/lobibox.min.js"></script>
</html>
