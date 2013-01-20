<%@ taglib prefix="tiles" uri="/tags/struts-tiles"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@page import="com.enterprisehorizons.magma.server.admin.AdminConfigUtils"%>
<%@page import="com.enterprisehorizons.magma.server.util.ServerUtils"%>
<html:html locale="true">
<head>
<title>Space . Time . Insight - <tiles:getAsString name="stiTitle" ignore="true"/></title>
<%@ include file="/mobile/common/style.jsp"%>
</head>
<body class="bodyBG bodyText" topmargin="0" leftmargin="0">
<table width="100%" height="100%" cellspacing="0" cellpadding="0" border="0">
	<tr>
    	<td valign="top" height="5%">
			<!-- Header Starts Here -->
			<tiles:get name="stiHeader"/>
			<!-- Header Ends Here -->
		</td>
		</tr>
		<tr>
			<td bgcolor="#6e94af" valign="top" height="5%">
				<!-- Menu Start here -->
				<tiles:get name="stiMenu"/>
				<!-- Menu Ends here -->
			</td>
		</tr>
		<tr>
        	<td valign="top">
				<div id="windowIframe" frameborder="0" width="100%" height="100%"><tiles:get name="body"/></div>
			</td>
		</tr>
		<tr>
     	    <td valign="top" height="5%">
            	<!-- Footer Start here -->
				<tiles:get name="stiFooter"/>
                <!-- Footer Ends here -->
			</td>
		</tr>
</table>    
</body>
</html:html>
