<%@ taglib prefix="tiles" uri="/tags/struts-tiles"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@page import="com.enterprisehorizons.magma.server.admin.AdminConfigUtils"%>
<%@page import="com.enterprisehorizons.magma.server.util.ServerUtils"%>
<html:html locale="true">
<head>

<title>Space . Time . Insight - <tiles:getAsString name="stiTitle" ignore="true"/></title>

<%@ include file="/common/dojo.jsp"%>
</head>
<%
	
	String apptheme = AdminConfigUtils.getApplicationTheme();
	if (apptheme == null || apptheme.equals("null")) {
		apptheme = "default";
	}

%>
<body class="tundra bodybg">

<table width="100%" height="100%" cellspacing="0" cellpadding="0" border="0">
	<tr>
		<td>
		 <td width="100%" valign="top">
			<% if(apptheme.equals("blue"))
			   {
			      out.print("<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" class=\"bgTiling\">"); 
			   }
                           else
			   {
			      out.print("<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" ");
			   }
			%>
	<tr>
    	<td width="100%" valign="top">

			<table width="100%" cellpadding="0" cellspacing="0" border="0">
     			<tr>
					<td width="100%" colspan="3" align="left" class="title" style="padding-left:0px;">
						<!-- Header Starts Here -->

						<tiles:get name="stiHeader"/>

						<!-- Header Ends Here -->
					</td>
			   </tr>
			   <tr>
					<td height="30" class="bgMenuColor" colspan="3">
						<!-- Menu Start here -->
						<tiles:get name="stiMenu"/>
						<!-- Menu Ends here -->
					</td>
					
				</tr>
			</table>
		</td>
	</tr>
     <tr>
       	<td height="1" class="bgColoring"></td>
     </tr>
   	 <tr>
     	<div id="windowIframe" frameborder="0" width="100%" height="100%"><tiles:get name="body"/></div>
	</tr>
</table>	
</body>
</html:html>
