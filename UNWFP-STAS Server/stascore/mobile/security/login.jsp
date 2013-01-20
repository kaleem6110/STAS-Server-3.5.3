<%@page import="com.spacetimeinsight.db.model.util.DataModelsCache"%>
<%@page import="com.spacetimeinsight.db.config.model.LanguageMaster"%>
<%@page import="com.enterprisehorizons.magma.server.util.CommonUtils"%>
<%@page import="com.enterprisehorizons.magma.server.admin.AdminConfigUtils"%>
<%@page import="com.enterprisehorizons.magma.server.util.ServerUtils"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Locale"%>
<%@page import="com.spacetimeinsight.web.common.constants.WebConstants,com.enterprisehorizons.util.StringUtils"%>

<%
    List languagesList = DataModelsCache.getInstance().retrieve(LanguageMaster.class.getName());
%>
<%@ taglib prefix="tiles" uri="/tags/struts-tiles"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>


<html:html locale="true">
<head>
<title>Space . Time . Insight</title>
<%@ include file="/mobile/common/style.jsp"%>
</head>

<body class="bodyBG bodyText" topmargin="0" leftmargin="0">
<table cellpadding="0" cellspacing="0" border="0" width="100%"> 
	<tr>
		<td colspan="2"><img src="<%=ServerUtils.getContextName(request)%>/images/mobile/logo.png"/></td>
	</tr>
	<tr>
  		<td colspan="2" align="right">
        	<a href="<%=ServerUtils.getContextName(request)%>/mobile/loginAction.do">Welcome</a>&nbsp;
      	</td>
	</tr>
    <tr>
       	<td colspan="2" height="20" class="headerMenuBar"></td>
        		   	<!-- Menu Buttons -->
	</tr>
    <tr>
    	<td class="formbg" >
        	<table cellpadding="0" cellspacing="0" align="center"> 
				<tr>
    				<td>
						<html:form  action="/loginAction.do?prefix=mobile" method="post">
    				</td>
    			</tr>
                <tr><td colspan="2">&nbsp;</td></tr>
                <tr>         
                    <td class="formlabel bodytext"><bean:message key="login.loginid" bundle="security" />:</td>
                    <td><input name="login" type="text" id="login" size="10" class="formcomponent"/></td>
                </tr>        
                <tr>
                    <td class="formlabel bodytext"><bean:message key="login.password" bundle="security" />:</td> 
                    <td><input name="password" type="password" id="password" size="10" class="formcomponent"/></td>
                </tr>
                <tr>    
                    <td class="formlabel bodytext"><bean:message key="login.language" bundle="security" />:</td> 
                    <td class="selectboxtext">
                            <select id="languageId" name="languageId"  onchange="changelocale(this);">
                            <%
                                    Locale locale = (Locale)request.getSession(false).getAttribute(WebConstants.LOCALE_SESSION_CLASS_NAME);
                                        int count = languagesList == null ? 0 : languagesList
                                                .size();
                                    LanguageMaster language = null;
                                        for (int i = 0; i < count; i++) {
                                            language = (LanguageMaster) languagesList.get(i);
                            %>
                            <option 
                                    <%if (locale.getCountry().equals(language.getCountryCd())) {%>
                                            selected <%}%> value="<%=language.getLanguageId()%>"><%=language.getDescription()%>
                            </option>
                            <%
                                    }
                            %>
                        </select>
    	 			</td>
				</tr>
    			<tr>
                    <td></td>
                    <td>
                                <input name="submitLogin" id="login" type="submit" onClick="goToMobileHomePage()" class="btnstyle"  
                                        value='<bean:message key="login.signin" bundle="security"/>'/>
                                        
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center" class="errortext">
                        <html:errors bundle="security"/>
                        <input type="hidden" name="pageType" value="loginPage" align="center">
                        <input type="hidden" name="languagechange" value="" align="center">
                    </td>
                </tr>
        	</table>
    	</td>
    </tr>
</table>
<table>
    <tr>
    	<td class="warningtext">
			<%=AdminConfigUtils.getConfidentialityMessage()%>
	
	    	<script>
				 document.forms[0].login.focus();      
			</script>
			<noscript>
                 <bean:message key="admin.noscript" bundle="admin"/>
     		</noscript>
		</td>
    </tr>
</table>         
</html:form>
</body>
</html:html>