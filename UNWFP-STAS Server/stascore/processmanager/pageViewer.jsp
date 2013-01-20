<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ page import="com.spacetimeinsight.integrations.ras.utils.*"%>
<%@ page import="com.spacetimeinsight.security.bean.UserBean"%>
<%@ page import="com.enterprisehorizons.magma.server.util.ServerUtils"%>
<%@page import="com.enterprisehorizons.magma.server.util.ServerUtils"%>
 <%
        response.setHeader("Cache-Control", "no-cache"); // HTTP 1.1
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0
		response.setDateHeader("Expires", 0); // prevents caching at the proxy
 %>


 <%
     UserBean userBean=(UserBean) request.getSession().getAttribute(ServerUtils.USER_BEAN_NAME);
     String title="admin.process.manager."+request.getParameter("type")+".title";
     String desc="admin.process.manager."+request.getParameter("type")+".desc";
     String pagecode=request.getParameter("pagecode");  
 %>

<html>
<head>
<link href="<%=ServerUtils.getContextName(request)%>/css/sti.css" rel="stylesheet" type="text/css" />
<style>
    @import "<%=ServerUtils.getContextName(request)%>/js/dojo/resources/dojo.css";
    @import "<%=ServerUtils.getContextName(request)%>/js/dijit/themes/tundra/tundra.css";
    @import "<%=ServerUtils.getContextName(request)%>/js/dijit/themes/tundra/tundra_rtl.css";
            
    
</style>
<script type="text/javascript" src="<%=ServerUtils.getContextName(request)%>/js/dojo/dojo.js" djConfig="isDebug: false, parseOnLoad: true"></script>
<script type="text/JavaScript">
    function goToHomePage(){
                window.location = "<%=ServerUtils.getContextName(request)%>/showProcessManagerRoot.do";
           
    }
</script>

<script type="text/javascript">
            dojo.require("dijit.layout.ContentPane");
            dojo.require("dijit.form.Button");    
</script>
</head>     
<body class="tundra bodybg" >
<table width="100%" cellspacing="0" cellpadding="0" align="center"   >
   <logic:notMatch parameter="pagecode"  value="inbox">
            <tr>
                <td  class="pageTitle paddingTitle">      
                 <strong><bean:message key="<%=title%>" bundle="admin"/></strong>
                </td>
            </tr>
            <tr>
               <td class="paddingTitleDesc">
                 <strong><bean:message key="<%=desc%>" bundle="admin"/></strong>
                </td>  
            </tr>
     </logic:notMatch>       
            <tr>
                <td style="padding-left:48px">
                <table cellpadding="0" cellspacing="19px" border="0" style="width:100%">
                <tr>
                   <td>
                      <%
                         String url=null;
                         try {
                        	 url=RASURLCompiler.compileURL(userBean.getLoginId()!=null?userBean.getLoginId():userBean.getUserUniqueId(), pagecode,session.getId());
                        	 %>
                        	   <iframe frameborder="0" style="width:100%;height:435px" src="<%=url%>"> 
                                    This browser does not support IFrame tag</iframe>
                        	 <%
                         } catch(Exception ex) {
                        	 %>
                        	    <span class="error"><bean:message key="admin.process.manager.ras.error" bundle="admin"/></span>
                        	 <%                             
                         }
                      %>                   
                   </td>
                </tr> 
                </table>
                </td>                   
        </tr>
        <logic:notMatch parameter="pagecode"  value="inbox">
               <tr align = "right" >         
          <td height="30"  class="barColor">
              <table width="100%" cellspacing="0" cellpadding="0" border=0>                          
                  <td  align="right"><button dojoType="dijit.form.Button" class="register"  type="button" onClick="goToHomePage()"> <bean:message key="dbconfig.back"/> </button> 			  
                  </td>  
				  <td>&nbsp;</td>				   
                </table>
          </td>
         </tr>
                 
        </logic:notMatch>
</table>
</body>
</html>
