<!-- saved from url=(0014)about:internet -->
<%@page import="com.enterprisehorizons.magma.server.util.ServerUtils"%>
<%
	String empId =  request.getParameter("empId");
	String serverUrl =  request.getParameter("serverUrl");
%>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<style>
body { margin: 0px; overflow:hidden }
</style>
<script type="text/javascript" src="<%=ServerUtils.getContextName(request)%>/js/swfobject.js"></script>
<script>
	function createEmployeeInfoObject(){
		var flashvars = {};
		var params = {};
		var attributes = {
			id: "searchWin",
			name: "search",
			style: "border: 1px solid silver; height: 499px; width: 300px; position:absolute; left:10px;top:215px;"
		};
	
		var swfUrl = "CustomViews.swf?serverUrl=<%=serverUrl%>&empId=<%=empId%>";
		//swfUrl="CustomViews.swf";
		swfobject.embedSWF(swfUrl, "employeeInfo", "600", "250", "9.0.0",flashvars, params, attributes);
	}
	
	function openServiceRequest(){
		window.location = "<%=serverUrl%>/serviceNotification/sn1/popup.htm"
			
	}
</script>
</head>
<body onload="createEmployeeInfoObject();">
<div id="employeeInfo">
</div>
</body>
</html>
