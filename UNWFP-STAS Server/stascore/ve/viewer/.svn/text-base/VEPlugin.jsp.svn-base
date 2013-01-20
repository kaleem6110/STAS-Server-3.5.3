<%@page import="com.enterprisehorizons.constants.CommonConstants"%>
<%@page import="com.enterprisehorizons.magma.server.util.ServerUtils"%>
<%@page import="com.enterprisehorizons.magma.server.admin.AdminConfigUtils" %>
<%@page import="com.enterprisehorizons.util.StringUtils"%>
<%
    String serverUrl = ServerUtils.getServerContextBaseUrl(request);
    String serverRootUrl = ServerUtils.getServerRootUrl(request);
    String groupId = request.getParameter("groupId");
    String domainId = request.getParameter("domainId");
    String moduleId = request.getParameter("moduleId");
    String mapMode = request.getParameter("mapMode");
    
    String mapLoadFunction = "";
    if(mapMode == null || mapMode =="3D") {
        mapLoadFunction = "GetMap(\'3D\')";
    }else{
    	mapLoadFunction = "GetMap(\'2D\')";
    }
    
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

    String veServerUrl = AdminConfigUtils.getConfigValue("vetileserverurl");
//    String coreServerUrl = serverRootUrl+CommonConstants.FORWARD_SLASH + CommonConfigUtils.getConfigValue("coreservercontext")+CommonConstants.FORWARD_SLASH;
//    String adminServerUrl = serverRootUrl+CommonConstants.FORWARD_SLASH + CommonConfigUtils.getConfigValue("adminservercontext")+CommonConstants.FORWARD_SLASH;
    
%>
<html>
    <head>
        <title></title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta http-equiv="Pragma" content="no-cache">
        <link rel="stylesheet" href="<%=ServerUtils.getContextName(request)%>/ve/viewer/style.css" type="text/css" media="screen" />
        <link rel="stylesheet" href="<%=ServerUtils.getContextName(request)%>/ve/viewer/customInfoBox.css" type="text/css" media="screen" />

        <script type="text/javascript" src="http://dev.virtualearth.net/mapcontrol/mapcontrol.ashx?v=6.2"></script>
        <script type="text/javascript" src="<%=ServerUtils.getContextName(request)%>/ve/viewer/VEObjects.js"></script>
        <script type="text/javascript" src="<%=ServerUtils.getContextName(request)%>/ve/viewer/utilFunctions.js"></script>
        <script type="text/javascript" src="<%=ServerUtils.getContextName(request)%>/ve/viewer/motionpack.js"></script>
        <script type="text/javascript" src="<%=ServerUtils.getContextName(request)%>/js/swfobject.js"></script>
        <script type="text/javascript" src="<%=ServerUtils.getContextName(request)%>/ve/viewer/VEPluginFunctions.js"></script>
        <script type="text/javascript">

            var formatterUrl = "<%=veServerUrl%>/formatImg";
            var tileServerUrl = "<%=veServerUrl%>/tiles?quadKey=%4";
            var serverUrl = "<%=serverUrl%>";
            var groupId = "<%=groupId%>";
            var domainId = "<%=domainId%>";
            var moduleId = "<%=moduleId%>";
    function openInfoframe(urlToOpen){
        parent.openInfoframe(urlToOpen);
    }
        </script>
    </head>
   
    <body  onload="<%=mapLoadFunction%>" onResize="mapResize()" class="pluginbody">
        <div id='map3d' style='height: 100%;width: 100%'></div>
    </body>
</html> 
