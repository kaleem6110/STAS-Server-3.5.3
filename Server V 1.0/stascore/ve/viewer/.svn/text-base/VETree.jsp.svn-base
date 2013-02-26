<%@page import="com.enterprisehorizons.constants.CommonConstants"%>
<%@page import="com.enterprisehorizons.magma.server.util.ServerUtils"%>
<%@page import="com.enterprisehorizons.magma.server.admin.CommonConfigUtils" %>
<%@page import="com.enterprisehorizons.util.StringUtils"%>
<%
    String serverUrl = ServerUtils.getServerContextBaseUrl(request);
    String serverRootUrl = ServerUtils.getServerRootUrl(request);
    String groupId = request.getParameter("groupId");
    String domainId = request.getParameter("domainId");
    String moduleId = request.getParameter("moduleId");
    
    if(domainId == null) {
            domainId = "";
    }

    if(moduleId == null) {
        moduleId = "";
    }
    
    String swf = request.getParameter("swf");
    if(swf==null)   {
        swf="VETree2";
    }

    String veServerUrl = serverRootUrl+CommonConstants.FORWARD_SLASH + CommonConfigUtils.getConfigValue("veservercontext");
    String coreServerUrl = serverRootUrl+CommonConstants.FORWARD_SLASH + CommonConfigUtils.getConfigValue("coreservercontext")+CommonConstants.FORWARD_SLASH;
    String adminServerUrl = serverRootUrl+CommonConstants.FORWARD_SLASH + CommonConfigUtils.getConfigValue("adminservercontext")+CommonConstants.FORWARD_SLASH;
    
%>
<html>
    <head>
        <title></title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta http-equiv="Pragma" content="no-cache">
        <link rel="stylesheet" href="style.css" type="text/css" media="screen" />
        <link rel="stylesheet" href="customInfoBox.css" type="text/css" media="screen" />

        <link href="../js/windowsjs/themes/default.css" rel="stylesheet" type="text/css"/>  
        <link href="../js/windowsjs/themes/alphacube.css" rel="stylesheet" type="text/css"/>
        <link href="../js/windowsjs/themes/pwc-os.css" rel="stylesheet" type="text/css"/>


        <script type="text/javascript" src="../js/windowsjs/javascripts/prototype.js"> </script>
        <script type="text/javascript" src="../js/windowsjs/javascripts/effects.js"> </script>
        <script type="text/javascript" src="../js/windowsjs/javascripts/window.js"> </script>
        <script type="text/javascript" src="../js/windowsjs/javascripts/window_effects.js"> </script>
        <script type="text/javascript" src="../js/windowsjs/javascripts/debug.js"> </script>
        <script type="text/javascript" src="../js/windowsjs/javascripts/pwc-os.js"> </script>

        <script type="text/javascript" src="http://dev.virtualearth.net/mapcontrol/mapcontrol.ashx?v=6.2"></script>
        <script type="text/javascript" src="VEObjects.js"></script>
        <script type="text/javascript" src="utilFunctions.js"></script>
        <script type="text/javascript" src="motionpack.js"></script>
        <script type="text/javascript" src="<%=ServerUtils.getContextName(request)%>/js/swfobject.js"></script>
        <script type="text/javascript" src="VEFunctions.js"></script>
        <script type="text/javascript">

            var formatterUrl = "<%=veServerUrl%>/formatImg";
            //var overlayUrl = "<%=veServerUrl%>/veImgOverlay?overlayImageURL=<IMAGEURL>&latitude=<LATITUDE>&longitude=<LONGITUDE>&action=Add";
            var tileServerUrl = "<%=veServerUrl%>/tiles?quadKey=%4";
            var serverUrl = "<%=serverUrl%>";
            var coreServerUrl = "<%=coreServerUrl%>";
            var adminServerUrl = "<%=adminServerUrl%>";
            var groupId = "<%=groupId%>";
            var domainId = "<%=domainId%>";
            var moduleId = "<%=moduleId%>";

        </script>
    </head>
   
    <body  class="tundra" onload="" onmousemove="hideControlMap3d()">
        <div id="dock" style="border: 1px solid silver;"></div>   
        <div id="container"  style='height: 500%;' ></div>
        <script>
            veTreeWin()
            map3dWin()
        </script>   
    </body>
</html> 
