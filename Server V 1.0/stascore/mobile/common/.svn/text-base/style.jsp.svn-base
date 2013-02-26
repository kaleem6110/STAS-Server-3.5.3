<%@page import="com.enterprisehorizons.magma.server.admin.AdminConfigUtils"%>
<%@page import="com.enterprisehorizons.magma.server.util.ServerUtils"%>

<%
    String appTheme = AdminConfigUtils.getApplicationTheme();
    if (appTheme == null || appTheme.equals("null")) {
        appTheme = "default";
    }
%>
<link href="<%=ServerUtils.getContextName(request)%>/themes/mobile/<%=appTheme %>/style.css" rel="stylesheet" type="text/css" />