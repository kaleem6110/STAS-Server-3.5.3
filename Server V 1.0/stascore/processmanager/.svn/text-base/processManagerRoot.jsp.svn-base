<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ page import="com.spacetimeinsight.integrations.ras.utils.*"%>
<%@page import="com.enterprisehorizons.magma.server.util.ServerUtils"%>
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
            window.location = "<%=ServerUtils.getContextName(request)%>/admin/AdminMain.jsp";
    }
</script>

<script type="text/javascript">
            dojo.require("dijit.layout.ContentPane");
            dojo.require("dijit.form.Button");    
</script>
</head>     
<body class="tundra bodybg" >
<table width="100%" cellspacing="0" cellpadding="0" align="center"  border="0" >
            <tr>
                <td  class="pageTitle paddingTitle">      
                 <bean:message key="admin.process.manager.title" bundle="admin"/>
                </td>
            </tr>
            <tr>
               <td class="paddingTitleDesc">
                 <bean:message key="admin.process.manager.desc" bundle="admin"/>
                </td>  
            </tr>
            <tr>
                <td style="padding-left:48px;padding-top:30px;">
                <table cellpadding="0" cellspacing="19px" border="0">
                <tr>
                 <td>
                <div dojoType="dijit.layout.ContentPane" region="center" style="font-size:13px;font-weight:bold;color:#FFFFFF; text-align:center;vertical-align:middle;" class="thumbnail-itemOut" >
                    <div  class="thumbnail-itemIn"  >
                        <a href="<%=ServerUtils.getContextName(request)%>/showPageViewer.do?type=mailconfig&pagecode=<%=RASURLContants.RAS_PAGE_URL_MAILCONFIG%>">
                            <img src="<%=ServerUtils.getContextName(request)%>/images/portal/icon_manage_config.png" />
                        </a>
                    </div>
                    <bean:message key="admin.process.manager.mailconfig.title" bundle="admin"/>
                </div>
            </td>
                 <td>
                <div dojoType="dijit.layout.ContentPane" region="center" style="font-size:13px;font-weight:bold;color:#FFFFFF; text-align:center;vertical-align:middle;" class="thumbnail-itemOut" >
                    <div  class="thumbnail-itemIn"  >
                        <a href="<%=ServerUtils.getContextName(request)%>/showPageViewer.do?type=actor&pagecode=<%=RASURLContants.RAS_PAGE_URL_ACTOR_TEMPLATE%>">
                            <img src="<%=ServerUtils.getContextName(request)%>/images/portal/icon_actor_template.png" />
                        </a>
                    </div>
                    <bean:message key="admin.process.manager.actor.title" bundle="admin"/>
                </div>
            </td>
            <td>
                <div dojoType="dijit.layout.ContentPane" region="center" style="font-size:13px;font-weight:bold;color:#FFFFFF; text-align:center;vertical-align:middle;" class="thumbnail-itemOut" >
                    <div  class="thumbnail-itemIn"  >
                        <a href="<%=ServerUtils.getContextName(request)%>/showPageViewer.do?type=task&pagecode=<%=RASURLContants.RAS_PAGE_URL_TASK_TEMPLATE%>">
                            <img src="<%=ServerUtils.getContextName(request)%>/images/portal/icon_task_template.png" />
                        </a>
                    </div>
                    <bean:message key="admin.process.manager.task.title" bundle="admin"/>
                </div>
            </td>
            <td>
                <div dojoType="dijit.layout.ContentPane" region="center" style="font-size:13px;font-weight:bold;color:#FFFFFF; text-align:center;vertical-align:middle;" class="thumbnail-itemOut" >
                    <div  class="thumbnail-itemIn"  >
                        <a href="<%=ServerUtils.getContextName(request)%>//showPageViewer.do?type=process&pagecode=<%=RASURLContants.RAS_PAGE_URL_PROCESS_LIST%>">
                            <img src="<%=ServerUtils.getContextName(request)%>/images/portal/icon_process_list.png" />
                        </a>
                    </div>
                    <bean:message key="admin.process.manager.process.title" bundle="admin"/>
                </div>
            </td>
            <td>
                <div dojoType="dijit.layout.ContentPane" region="center" style="font-size:13px;font-weight:bold;color:#FFFFFF; text-align:center;vertical-align:middle;" class="thumbnail-itemOut" >
                    <div  class="thumbnail-itemIn"  >
                       <a href="<%=ServerUtils.getContextName(request)%>//showPageViewer.do?type=monitor&pagecode=<%=RASURLContants.RAS_PAGE_URL_WORKFLOW_MONTIOR%>">
                            <img src="<%=ServerUtils.getContextName(request)%>/images/portal/icon_monitor_process.png" />
                        </a>
                    </div>
                    <bean:message key="admin.process.manager.monitor.title" bundle="admin"/>
                </div>
            </td>       
                 
                </tr>
          <tr>
           
           <td>
                <div dojoType="dijit.layout.ContentPane" region="center" style="font-size:13px;font-weight:bold;color:#FFFFFF; text-align:center;vertical-align:middle;" class="thumbnail-itemOut" >
                    <div  class="thumbnail-itemIn"  >
                        <a href="<%=ServerUtils.getContextName(request)%>/showPageViewer.do?type=importexport&pagecode=<%=RASURLContants.RAS_PAGE_URL_EXPORTIMPORT%>">
                            <img src="<%=ServerUtils.getContextName(request)%>/images/portal/icon_import_export.png" />
                        </a>
                    </div>
                    <bean:message key="admin.process.manager.importexport.title" bundle="admin"/>
                </div>
            </td>
        </tr>
       </table>
       </td>
            
        </tr>
               <!--tr align = "right" >         
          <td height="30"  class="barColor">
              <table width="100%" cellspacing="0" cellpadding="0">                          
                  <td width='1000px' align="right"><button dojoType="dijit.form.Button" class="register"  type="button" onclick="goToHomePage()"> <bean:message key="dbconfig.home"/> </button> 			  
                  </td>  
				  <td>&nbsp;</td>				   
                </table>
          </td>
         </tr-->
                 
</table>
</body>
</html>
