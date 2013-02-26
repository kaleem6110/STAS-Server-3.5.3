<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@page import="com.enterprisehorizons.constants.CommonConstants"%>
<%@page import="com.enterprisehorizons.magma.server.util.ServerUtils"%>
<%@page import="com.enterprisehorizons.magma.server.admin.CommonConfigUtils" %>
<%@page import="com.enterprisehorizons.util.StringUtils"%>
<%@page import="com.enterprisehorizons.magma.server.admin.AdminConfigUtils,com.spacetimeinsight.stas.config.*"%>

<%
    String serverUrl = ServerUtils.getServerContextBaseUrl(request);
    String editMode = request.getParameter("editMode");
    String swfName = request.getParameter("swf");
    String appTheme = AdminConfigUtils.getApplicationTheme();
    if (appTheme == null || appTheme.equals("null")) {
        appTheme = "default";
    }

%>
<jsp:include page="/common/commonheader.jsp"/>
<html lang="en">


<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<script type="text/javascript" src="<%=serverUrl %>js/ge/flash_detect.js"></script>
<script type="text/javascript"> 
	if(!FlashDetect.installed || !FlashDetect.versionAtLeast(9)){
        alert("<bean:message key="flash.required" bundle="admin" />");       
    }
</script>
<script type="text/javascript" src="<%=ServerUtils.getContextName(request)%>/js/swfobject.js"></script>



<style>
body { margin: 0px; overflow:hidden; bgcolor:#000000; width:100%; height:100%}
</style>
</head>

<body scroll="no">
<script language="JavaScript" type="text/javascript">


function createGEFrameObject(){
	var flashvars = {};
	var params = {serverUrl: "<%=serverUrl%>",
				coreServerUrl: "<%=serverUrl%>",
				adminServerUrl: "<%=serverUrl%>",
				editMode: <%=editMode%>,
				theme: "<%=appTheme%>",
				wmode:"transparent"
				};
   var attributes = {
	id: "swfLoader",
	name: "swfLoader",
	style: "z-index:1;border: 1px solid silver; height: 100%; width: 100%; position:absolute; left:0px;top:0px;",
	wmode:"transparent"
   };
   

    var swfLocationUrl = "<%=serverUrl%><%=swfName%>";
    swfobject.embedSWF(swfLocationUrl, "swfLocationDivId", "100%", "100%", "9.0.0",flashvars, params, attributes);
}
    createGEFrameObject();
</script>
<div id='swfLocationDivId' style="z-index:1">

</body>
</html>