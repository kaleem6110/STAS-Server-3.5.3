<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ page import="com.enterprisehorizons.magma.admin.processmanager.ProcessManagerHelper "%>
<%@ include file="/common/dojo.jsp"%>
<%@page import="com.enterprisehorizons.magma.server.util.ServerUtils"%>
<%
    Object obj = request.getSession(false).getAttribute("stiUser");
    if(obj == null){ %>
    <script>
        window.location = "<%=ServerUtils.getContextName(request)%>/";                
    </script>
<%  }
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
<script type="text/javascript">
            dojo.require("dijit.layout.ContentPane");
</script>
</head>     
<body class="tundra bodybg" >
<table width="100%" cellspacing="0" cellpadding="0">
    <tr>
    <td align="left">
    <table width="1000" cellpadding="0" cellspacing="0"  align="left">
      <tr>
      
      <td  align="left" valign="top"  align="justify">
      <table width="1000" cellspacing="0" cellpadding="0">
       
        <tr>
        
        <td>&nbsp;</td>
        <td height="30" align="left" valign="top" class="redtitle">
        
        <table width="900" cellspacing="0" cellpadding="0" border="0">
            <tr>
                <td  class="pageTitle paddingTitle">      
                    <bean:message key="rules.feedReportData.header" bundle="rules" />
                </td>
            </tr>
            <tr>
               <td class="paddingTitleDesc">
                    <bean:message key="rules.feedReportData.description" bundle="rules" />
                </td>  
            </tr>
            <tr>
                <td style="padding-left:48px;padding-top:30px;">
                <table cellpadding="0" cellspacing="19px" border="0"><tr>
            <td>
                <div dojoType="dijit.layout.ContentPane" region="center" style="font-size:13px;font-weight:bold;color:#FFFFFF; text-align:center;vertical-align:middle;" class="thumbnail-itemOut" onClick="renderPage('<%=ServerUtils.getContextName(request)%>/searchNercViolationsAction.do?operation=searchNercViolations')">
                    <div  class="thumbnail-itemIn"  >

                            <img src="<%=ServerUtils.getContextName(request)%>/images/portal/icon_nerc_reports.png" />
                    </div>
                    <bean:message key="rules.configureNercInteroperability" bundle="rules" />
                </div>
           </td>
          
           
         </tr>
         </table> 
         </td>          
         </tr>          
        </table>
      </table>
      </td>
      
      </tr>
          </table>
  </table>
  </td>
  </tr>
        <input type="hidden" name="operation" id="operation" value="" />
  </table>
  <table width="100%"  border="0">
    <tr align="center" class="barColor">
            <td  align="center">
				<button dojoType="dijit.form.Button" class="register"  type="button"  onclick="window.location =  '<%=ServerUtils.getContextName(request)%>/RulesHome.do?operation=loadRulesHome'"> <bean:message key="dbconfig.home"/> </button>
                    <button dojoType="dijit.form.Button"  type="button"   name="btnBack" onClick="window.location =  '<%=ServerUtils.getContextName(request)%>/RulesHome.do?operation=loadRulesHome'">
                        <bean:message key="rules.back" bundle="rules"/> 
                    </button>
					           
             </td>
    </tr>
</table>
</body> 
</html>
<script>
function renderPage(url){
	window.location = url;
}
</script>