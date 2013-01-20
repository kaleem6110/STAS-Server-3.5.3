<%@page import="com.enterprisehorizons.magma.server.util.ServerUtils"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
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
    function goToDBHomePage(){
            window.location = "<%=ServerUtils.getContextName(request)%>/admin/AdminMain.jsp";
    }
</script>

<script type="text/javascript">
            dojo.require("dijit.layout.ContentPane");
            dojo.require("dijit.form.Button");    
</script>
</head>     
<body class="tundra bodybg" >
<table width="101.4%" cellspacing="0" cellpadding="0" align="center"  border="0" >
            <tr>
                <td  class="pageTitle paddingTitle">      
                <bean:message key="rules.configureRules.header" bundle="rules" />
                </td>
            </tr>
            <tr>
               <td class="paddingTitleDesc">
                 <strong><bean:message key="rules.configureRules.description" bundle="rules"/></strong>
                </td>  
            </tr>
            <tr>
                <td style="padding-left:48px;padding-top:30px;">
                <table cellpadding="0" cellspacing="19px" border="0">
                <tr>
            <td>
                <div dojoType="dijit.layout.ContentPane" region="center" style="font-size:13px;font-weight:bold;color:#FFFFFF; text-align:center;vertical-align:middle;" class="thumbnail-itemOut" onClick="renderPage('<%=ServerUtils.getContextName(request)%>/configRoot.do?operation=showConfigViewer')" >
                    <div  class="thumbnail-itemIn"  >
                            <img src="<%=ServerUtils.getContextName(request)%>/images/portal/icon_default_report_data.png" />
                    </div>
                    <bean:message key="rules.configureRules.default.reportData" bundle="rules"/>    
                </div>
            </td>
        </tr>
       </table>
       </td>
            
        </tr>
               <tr align = "right" >         
          <td height="30"  class="barColor">
              <table width="100%" cellspacing="0" cellpadding="0">
              <tr>
                  <td width='1000px' align="right">
					                  
                  <button dojoType="dijit.form.Button" class="register"  type="button" onClick="window.location =  '<%=ServerUtils.getContextName(request)%>/RulesHome.do?operation=loadRulesHome'  "> <bean:message key="dbconfig.home"/> </button> 
				  <button dojoType="dijit.form.Button" class="register"  type="button" onClick="window.location =  '<%=ServerUtils.getContextName(request)%>/RulesHome.do?operation=loadRulesHome'"> <bean:message key="dbconfig.back"/> </button>
                  </td>  
				  <td>&nbsp;</td>				   
				  
				</tr>                          
                </table>
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