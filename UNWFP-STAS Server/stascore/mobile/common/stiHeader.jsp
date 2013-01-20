<%@page import="com.enterprisehorizons.magma.server.util.CommonUtils"%>
<%@page import="com.enterprisehorizons.magma.server.util.ServerUtils"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%><head>
  <jsp:include page="/mobile/common/style.jsp"/>  
</head>


<table width="100%" cellspacing="0" cellpadding="0"  border="0" >
   	<tr>
        <td colspan="2" align="left" valign="top">
            <img src="<%=ServerUtils.getContextName(request)%>/images/mobile/logo.png" /> 
        </td>
   	</tr>
	<tr>
    	<td align="left" class="bodyText">
        <!-- Removed 
        	<a href="javascript:showReportBug()"><bean:message key="header.reportbug" bundle="admin"/></a>&nbsp;
            <a href="javascript:showAbout()"><bean:message key="header.about" bundle="admin"/></a>
        -->    
		</td>
		<td align="right" class="bodyText"> 
        	<bean:message key="stiheader.welcome" bundle="security" />  
          	<logic:present name="stiUser" >
            	<bean:write name="stiUser" property="firstName" />&nbsp;<bean:write name="stiUser" property="lastName" />
          	</logic:present>
     	</td>
  	</tr>
</table>
                