<%@ page import="com.enterprisehorizons.magma.server.util.ServerUtils"%>
<%@page import="com.enterprisehorizons.util.StringUtils"%>
<%@ page import="com.spacetimeinsight.security.bean.UserBean"%>
<%@page import="com.enterprisehorizons.magma.server.admin.AdminConfigUtils"%>
<%@page import="com.wfp.utils.WFPConfigUtils, com.wfp.offline.synchronize.ISynchronizationServiceConstants"%>

<%
    if(session == null || session.getAttribute(ServerUtils.USER_BEAN_NAME) == null){
        response.sendRedirect("/");
        return;
    }
    UserBean userBean = (UserBean) session.getAttribute(ServerUtils.USER_BEAN_NAME); 

    String serverUrl = ServerUtils.getServerContextBaseUrl(request);
    String userName = request.getParameter("userName");
    String swfName = request.getParameter("swf");
    String appTheme = AdminConfigUtils.getApplicationTheme();
	String refreshInterval = WFPConfigUtils.getRefreshInterval();
	String serverType;
	
	if(WFPConfigUtils.isCentralServer()){
		serverType = "centralServer";
	}else{
		serverType = "terminalServer";
	}
    
    if(StringUtils.isNull(userName)) {
        userName = String.valueOf(userBean.getFirstName())+" "+String.valueOf(userBean.getLastName());
    }
    if(userName == null) {
        userName = "";
    }
    
    if(StringUtils.isNull(swfName)) {
        swfName = "DataSynchronization.swf";
    }
    
    if (appTheme == null || appTheme.equals("null")) {
        appTheme = "default";
    }  
    
%>

<head>
<script type="text/javascript" src="<%=ServerUtils.getContextName(request)%>/js/swfobject.js"></script>
<script language="JavaScript" type="text/javascript">
<!--
// -----------------------------------------------------------------------------
// Globals
// Major version of Flash required
var requiredMajorVersion = 9;
// Minor version of Flash required
var requiredMinorVersion = 0;
// Minor version of Flash required
var requiredRevision = 124;
// -----------------------------------------------------------------------------
// -->

function createDataSynchronizationFrameObject(){
        var flashvars = {};
        var params = {serverUrl: "<%=serverUrl%>",
                      userName: "<%=userName%>",
                      theme: "<%=appTheme%>",
					  serverType: "<%=serverType%>",
					  refreshInterval: "<%=refreshInterval%>",
					  syncConflictId: "<%=ISynchronizationServiceConstants.SYNC_STATUS_CONFLICT%>"
							};
       var attributes = {
        id: "DataSynchronizationFrame",
        name: "DataSynchronizationFrame",
        style: "z-index:1;border: 1px solid silver; height: 100%; width: 100%; position:absolute; left:0px;top:0px;",
        wmode:"transparent",
        allowFullScreen: "true"
       };

        var dataSynchronizationUrl = "<%=serverUrl%>/wfp/synchronization/<%=swfName%>";
        swfobject.embedSWF(dataSynchronizationUrl, "dataSynchronizationDiv", "100%", "100%", "9.0.0",flashvars, params, attributes);
    }
    createDataSynchronizationFrameObject();

</script>
</head>

<body scroll="no">
<div id="dataSynchronizationDiv" style="z-index:1">
</div>

</body>
</html>
