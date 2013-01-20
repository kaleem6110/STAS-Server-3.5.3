<%@page import="com.enterprisehorizons.constants.CommonConstants"%>
<%@page import="com.enterprisehorizons.magma.server.util.ServerUtils"%>
<%@page import="com.enterprisehorizons.magma.server.admin.CommonConfigUtils" %>
<%@page import="com.enterprisehorizons.magma.server.admin.AdminConfigUtils,com.spacetimeinsight.stas.config.*"%>
<%@page import="com.enterprisehorizons.util.StringUtils"%>
<%@page import="com.spacetimeinsight.db.model.util.DataModelsCacheHelper"%>
<%@page import="com.spacetimeinsight.security.bean.UserBean"%>

<%

    String groupId = request.getParameter(ServerUtils.PARAM_GROUP_ID);
    String domainId = request.getParameter(ServerUtils.PARAM_DOMAIN_ID);
    String userId = request.getParameter(ServerUtils.PARAM_USER_ID);
    String moduleId = request.getParameter(ServerUtils.PARAM_MODULE_ID);
    String languageId = request.getParameter(ServerUtils.PARAM_LANGUAGE_ID);
    String languageCd = request.getParameter(ServerUtils.PARAM_LANGUAGE_CODE);
    String serverUrl = ServerUtils.getServerContextBaseUrl(request);
	UserBean userBean = (UserBean) session.getAttribute(ServerUtils.USER_BEAN_NAME);
	String bingMapCredentials = AdminConfigUtils.getBingMapsKey();

	if(StringUtils.isNull(domainId)) {
        domainId = String.valueOf(userBean.getDomainId());
    }
    if(domainId == null) {
        domainId = "";
    }

    if(StringUtils.isNull(userId)) {
        userId = String.valueOf(userBean.getUserId());
    }
    if(userId == null) {
        userId = "";
    }

    if(StringUtils.isNull(groupId)) {
        groupId = StringUtils.tokenize(userBean.getGroupIds());
    }
     if(groupId == null) {
        groupId = "";
    }

    if(moduleId == null) {
        moduleId = "";
    }

    if(StringUtils.isNull(languageId)) {
        languageId = String.valueOf(userBean.getLanguageId());
    }
    if(languageId == null) {
        languageId = "";
    }

    if(StringUtils.isNull(languageCd)) {
        languageCd = userBean.getLanguageCd();
    }
    if(languageCd == null) {
        languageCd = "";
    }

	String bingMapCulture = languageCd;
	if(bingMapCulture.contains("_"))
		bingMapCulture=bingMapCulture.replace("_","-");

	String coreServerUrl = serverUrl;
    String adminServerUrl=serverUrl;

%>
<% response.addHeader("P3P","CP='IDC DSP COR ADM DEVi TAIi PSA PSD IVAi IVDi CONi HIS OUR IND CNT'"); %>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>BingFrame</title>
    <style type="text/css">
        html, body
        {
            height: 100%;
            overflow: hidden;
        }
        body
        {
            padding: 0;
            margin: 0;
        }
        #silverlightControlHost
        {
            height: 100%;
            text-align: center;
            overflow: hidden;
        }
    </style>

    <script type="text/javascript" src="<%=serverUrl%>/ve/SLViewer/Silverlight.js"></script>
    <script type="text/javascript" src="<%=serverUrl%>/ve/SLViewer/VEFunctions.js"></script>
    <script type="text/javascript" src="<%=serverUrl%>/ve/SLViewer/swfobject.js"></script>
     

    <script type="text/javascript">
         function onSilverlightError(sender, args) {
            var appSource = "";
            if (sender != null && sender != 0) {
                appSource = sender.getHost().Source;
            }

            var errorType = args.ErrorType;
            var iErrorCode = args.ErrorCode;

            if (errorType == "ImageError" || errorType == "MediaError") {
                return;
            }

            var errMsg = "Unhandled Error in Silverlight Application " + appSource + "\n";

            errMsg += "Code: " + iErrorCode + "    \n";
            errMsg += "Category: " + errorType + "       \n";
            errMsg += "Message: " + args.ErrorMessage + "     \n";

            if (errorType == "ParserError") {
                errMsg += "File: " + args.xamlFile + "     \n";
                errMsg += "Line: " + args.lineNumber + "     \n";
                errMsg += "Position: " + args.charPosition + "     \n";
            }
            else if (errorType == "RuntimeError") {
                if (args.lineNumber != 0) {
                    errMsg += "Line: " + args.lineNumber + "     \n";
                    errMsg += "Position: " + args.charPosition + "     \n";
                }
                errMsg += "MethodName: " + args.methodName + "     \n";
            }

            throw new Error(errMsg);
        }
        
        function ZoomToLocation(latitude,longitude,zoomLevel)
        {
			var pluginid = "appHostObject";
			if (document.getElementById(pluginid) != undefined && document.getElementById(pluginid) != null) 
			{
				document.getElementById(pluginid).content.Page.ZoomInToLocation(latitude,longitude,zoomLevel);
			}
        }
        
        function addLayer(name, url, isSelect, id) {
        	var pluginid = "appHostObject";
			if (document.getElementById(pluginid) != undefined && document.getElementById(pluginid) != null)
			{
				document.getElementById(pluginid).content.Page.AddLayerToMap(name,url,isSelect,id);
			}
   		}
   		
    </script>
   
</head>
<body>
    <form id="form1"  style="height: 100%">
    <div id="silverlightControlHost">
        <object data="data:application/x-silverlight-2," type="application/x-silverlight-2"
            width="100%" height="100%" id="appHostObject">
            <param name="source" value="<%=serverUrl%>/ve/SLViewer/ClientBin/BingMap.xap" />
            <param name="onError" value="onSilverlightError" />
            <param name="background" value="transparent" />
            <param name="minRuntimeVersion" value="3.0.40624.0" />
            <param name="autoUpgrade" value="true" />
            <param name="InitParams" value="coreServerUrl=<%=coreServerUrl%>,ServerUrl=<%=adminServerUrl%>,bingMapCredentials=<%=bingMapCredentials%>,bingMapCulture=<%=bingMapCulture%>,GroupId=<%=groupId%>,ModuleId=<%=moduleId%>,DomainId=<%=domainId%>,LanguageId=<%=languageId%>" />
            <param name="windowless" value="true" />
            <param name="EnableGPUAcceleration" value="true" />
            <param name="enableHtmlAccess" value="true" />
            <a href="http://go.microsoft.com/fwlink/?LinkID=149156&v=3.0.40624.0" style="text-decoration: none">
                <img src="http://go.microsoft.com/fwlink/?LinkId=108181" alt="Get Microsoft Silverlight"
                    style="border-style: none" />
            </a>
        </object>
        <iframe id="_sl_historyFrame" style="visibility: hidden; height: 0px; width: 0px;
            border: 0px"></iframe>
    </div>
    </form>
</body>
</html>

