<%@ taglib prefix="tiles" uri="/tags/struts-tiles"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
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
			<td class="headerMenuBar" valign="top" height="5%">
				<!-- Menu Start here -->
				<tiles:get name="stiMenu"/>
				<!-- Menu Ends here -->
			</td>
		</tr>
		<tr>
        	<td valign="top">
				<iframe height="100%" id="windowIframe" frameborder="0" width="100%" src="<%=ServerUtils.getContextName(request)%><tiles:getAsString name="body"/>">
				</iframe>
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