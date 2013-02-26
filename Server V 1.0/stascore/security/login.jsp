<%@page import="com.spacetimeinsight.db.model.util.DataModelsCache"%>
<%@page import="com.spacetimeinsight.db.config.model.LanguageMaster"%>
<%@page import="com.enterprisehorizons.magma.server.util.CommonUtils"%>
<%@page import="com.enterprisehorizons.magma.server.admin.AdminConfigUtils"%>
<%@page import="com.enterprisehorizons.magma.server.util.ServerUtils"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Locale"%>
<%@page import="com.spacetimeinsight.web.common.constants.WebConstants,com.enterprisehorizons.util.StringUtils"%>

<%
    List languagesList = DataModelsCache.getInstance().retrieve(
            LanguageMaster.class.getName());

    String apptheme = AdminConfigUtils.getApplicationTheme();
    if (apptheme == null || apptheme.equals("null")) {
        apptheme = "default";
    }
%>

<%@ taglib prefix="tiles" uri="/tags/struts-tiles"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>

<html:html locale="true">
<head>
    <title>Space . Time . Insight</title>
    <%@ include file="/common/style.jsp"%>
    <jsp:include page="../common/charsetmeta.jsp"/> 
    <jsp:include page="/common/HeaderCommon.jsp"/>
</head>

<body class="bodybg bodyText">
    <html:form action="/loginAction.do" method="post">
    <table width="100%" height="100%" align="center" cellspacing="0" cellpadding="0">
        <tr>
            <td>
                <table width="100%" height="100%" align="center" cellspacing="0" cellpadding="0">
                    <tr>
                        <td valign="top">
                            <% if(apptheme.equals("blue"))
                               {
                                  out.print("<table height=\"55\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" class=\"bgTiling\">");
                               }
                                           else
                               {
                                  out.print("<table height=\"55\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" class=\"bgHeaderImage\">");
                               }
                            %>
                            <tr>
                                <td width="30%" valign="top" class="logo"></td>
                                <td width="40%" align="Center" valign="top">
                                <%
                                    String path=CommonUtils.getCustomerLogoPath(request);
                                    if(path.endsWith(".jpeg") || path.endsWith(".jpg") || path.endsWith(".gif") || path.endsWith(".png")
                                        || path.endsWith(".JPEG") || path.endsWith(".JPG") || path.endsWith(".GIF") || path.endsWith(".PNG")){
                                            out.print("<img src=\""+path+"\" height=\"20\"/>");
                                    } else{
                                        out.print("");
                                    }
                                %>
                                </td>
                                <td width="30%" align="right" valign="top">
                                    <strong class="white1text1">
                                        <a id="link1" class="bannerText" href="javascript:showReportBug()"><bean:message key="header.reportbug" bundle="admin"/></a>&nbsp;&nbsp;                            
                                        <a id="link2"class="bannerText" href="javascript:showAbout()"><bean:message key="header.about" bundle="admin"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    </strong>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="3" height="30" class="bgMenuColor">
                                <!-- Menu Start here -->
                                    <div class="mattblacktabs" style="padding-left:10px">
                                        <ul>
                                            <logic:notPresent name="stiUser">
                                                <li class="selected"><a href="<%=ServerUtils.getContextName(request)%>/welcome.do"
                                                    onClick="changeStyle(this)"><bean:message key="admin.login.welcome.label" bundle="admin"/> </a></li>
                                            </logic:notPresent>
                                        </ul>
                                    </div>
                                        <!-- Menu Ends here -->
                                        <script>
                                                function changeStyle(obj){
                                                    obj.className="selected";
                                                }
                                        </script>
                                </td>
                            </tr>
                        </td>
                    </tr>   
                </table>
            </td>
        </tr>       
        <tr>
            <td valign="center">
                <table id="loginTableHeader" width="415" height="200" cellpadding="0" cellspacing="0" class="border1" align="center" bgcolor="#FFFFFF">
                    <tr>
                        <td colspan="2" height="37" align="left" valign="middle" class="bannerSignIn">&nbsp;&nbsp;&nbsp;&nbsp;<img src="<%=ServerUtils.getContextName(request)%>/images/portal/signin.PNG"  width="16" height="16" />&nbsp;
                            <strong class="signInText"><font size="2.5"><bean:message key="login.signin" bundle="security" /></font></strong>
                        </td>
                    </tr>
                    <tr>
                        <td align="center">
                            <table height="170" align="center" cellpadding="0" cellspacing="0">
                                <tr>
                                    <td width="24%" align="right" class="bodytext" style="padding-right: 6px;">
                                        <strong><bean:message key="login.loginid" bundle="security" />:</strong>
                                    </td>
                                    <td class="bodytext"><input type="text" autocomplete="off" id="login" name="login" style="width: 16em" required="true" trim="true" ucfirst="true" /></td>
                                </tr>
                                <tr>
                                    <td height="13" align="right" class="bodytext" style="padding-right: 6px;">
                                        <strong class="bodytext"><bean:message key="login.password" bundle="security" />:</strong>
                                    </td>
                                    <td class="bodytext"><input type="password" id="password" name="password" style="width: 16em" required="true" trim="true" ucfirst="true" /></td>
                                </tr>
                               <input type="hidden" name="isSessionExpired" value="newSession" />
                                <tr>
                                    <td align="right" class="bodytext" style="padding-right: 6px;">
                                        <strong><bean:message key="login.language" bundle="security" />:</strong>
                                    </td>
                                    <td>
                                        <select id="languageId" name="languageId" autoComplete="false" onchange="changelocale(this);" style="width:212px">
                                            <%
                                                Locale locale = (Locale)request.getSession(false).getAttribute(WebConstants.LOCALE_SESSION_CLASS_NAME);
                                                int count = languagesList == null ? 0 : languagesList.size();
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
                                    <td colspan="2" align="right" valign="top" style="padding-right: 10px;">
                                        <button type="submit" onClick="return dosubmit();"/>
                                            <bean:message key="login.signin" bundle="security"/>
                                    </td>
                                </tr>

                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td height="15" align="center" class="error">
                            <html:errors bundle="security" />
                        </td>
                    </tr>
                </table>
            </td>
        </tr>   
        <noscript>
        <tr>
            <td class="error" align="center">
                <bean:message key="admin.noscript" bundle="admin"/>
            </td>
        </tr>
        </noscript>
        <tr>
            <td>
            	<input type="hidden" name="timeZone" value="">
                <input type="hidden" name="pageType" value="loginPage">
                <input type="hidden" name="languagechange" value="">
                    <logic:present name="stiUser">
                        <script>
                            //window.location = '<%=ServerUtils.getContextName(request)%>/loginAction.do';
                        </script>
                    </logic:present>
            </td>
        </tr>
        <tr>
            <td>
                <table width="90%" height=100% align="center">
                    <tr>
                        <td align=center> <font size="2" color=grey></font></td>
                    </tr>
                    <tr>
                        <td><%=AdminConfigUtils.getConfidentialityMessage()%></td>
                    </tr>
                    <tr>
                        <td height="50" align=center><font size="2" color=grey>&nbsp;</font></td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
    <script type="text/javascript" src="js/common.js"></script>
    <script>
        window.onload = loadFormValues;
        function loadFormValues(){
        setTimeout(function (){document.getElementById('login').focus();}, 500); //Added setTimeout() to fix focus issue for IE8.
            try{
                parent.refreshSessionInvalid();
            }catch(err){
            }

            try{
                parent.parent.refreshSessionInvalid();
            }catch(err){
            }
        }

        function dosubmit(){
    		document.forms[0].timeZone.value=getTimeZone();
            if(document.forms[0].languagechange.value != 'changeEvent')
                document.forms[0].languagechange.value = 'submitEvent';
            if((document.getElementById('languageId').value) == 0){
                showEmptyDialog('<bean:message key="login.err.language" bundle="security"/>','<bean:message key="login.msg.login" bundle="security"/>');
            return false;
            }
        }

        function changelocale(localecode){
            if((document.getElementById('languageId').value) == 0)
            return false;
            if((document.getElementById('login').value) == "" && (document.getElementById('password').value) == ""){
                document.forms[0].languagechange.value='changeEvent';
                document.forms[0].submit();
            }
            else if((document.getElementById('login').value) == "")
                return;
        }
    </script>
	</html:form>
</body>
</html:html>