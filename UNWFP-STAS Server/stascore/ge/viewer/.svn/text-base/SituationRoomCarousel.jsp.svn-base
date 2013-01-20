<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@page import="com.enterprisehorizons.constants.CommonConstants"%>
<%@page import="com.enterprisehorizons.magma.server.util.ServerUtils"%>
<%@page import="com.enterprisehorizons.magma.server.admin.CommonConfigUtils,com.enterprisehorizons.magma.server.admin.AdminConfigUtils" %>
<%@page import="com.spacetimeinsight.security.bean.UserBean"%>
<%@page import="com.spacetimeinsight.security.bean.ModuleBean"%>
<%@page import="java.util.*"%>
<%
	String serverRootUrl = ServerUtils.getServerRootUrl(request);
	String serverUrl = ServerUtils.getServerContextBaseUrl(request);
    String domainId = request.getParameter(ServerUtils.PARAM_DOMAIN_ID);
    String userId = request.getParameter(ServerUtils.PARAM_USER_ID);
    String languageId = request.getParameter(ServerUtils.PARAM_LANGUAGE_ID);
     String appTheme = AdminConfigUtils.getApplicationTheme();
    //UserBean user = (UserBean)session.getAttribute(IWebSessionContants.SECURITY_USER_BEAN);
    //ModuleBean[] modules = (ModuleBean[])user.getModules().toArray(new ModuleBean[0]);
    int i=0;
%>
<html>
<head>
<%
	Object obj = request.getSession(false).getAttribute("stiUser");
	if(obj == null){ 
		response.sendRedirect(ServerUtils.getContextName(request)+"/");
		return;
	}   
 
%>

<script type="text/javascript" src="<%=ServerUtils.getContextName(request)%>/js/swfobject.js"></script>
<script>
var modulesUrl = new Array();
<logic:iterate id="stiModulesList" name="stiUser"  property="modules" >
	<logic:notEmpty name="stiModulesList" property="url">
			modulesUrl.push({'id':<bean:write name="stiModulesList" property="id"/>,'url':'<bean:write name="stiModulesList" property="url"/>','index':<%=i%>});
	</logic:notEmpty>
	<% i++; %>		 
</logic:iterate>
function createSituationRoom(){
        var flashvars = {wmode: "transparent"}; 
        var params = {serverUrl: "<%=serverUrl%>",
                            domainId: "<%=domainId%>",
                            userId: "<%=userId%>",
                            languageId:"<%=languageId%>",
                            wmode: "transparent",
                            theme:"<%=appTheme%>"
                            };
        var attributes = {
            id: "situationRoom",
            name: "situationRoom",
            style: "border: 1px solid silver; height: 100%; width: 100%; position:absolute; left:0px;top:0px;",
            wmode: "transparent",
            allowFullScreen: "true"
        };
    
        var geFrameUrl = "<%=ServerUtils.getContextName(request)%>/ge/viewer/SituationRoomCarousel.swf";
        swfobject.embedSWF(geFrameUrl, "situationRoomDiv", "100%", "100%", "9.0.0",flashvars, params, attributes);
}
function gotoModuleTab(moduleId,selectLayerIds,selectedRBRegion,isModuleLevel){ 
	for(i=0;i<modulesUrl.length;i++){
		if(modulesUrl[i].id==moduleId){
			return parent.loadGEFrame(modulesUrl[i].url,modulesUrl[i].index,selectLayerIds,selectedRBRegion,isModuleLevel);
		}
	}	
}
createSituationRoom();
</script>
</head>
<body style="margin:0px" scroll="no">
<div id="situationRoomDiv" width="100%" height="100%">
</div>
</body>
</html>