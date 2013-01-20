<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ page import="com.spacetimeinsight.stas.config.GenericConfigurationManager,com.enterprisehorizons.magma.server.admin.AdminConfigUtils,com.spacetimeinsight.stas.config.ConfigurationConstants,com.enterprisehorizons.magma.admin.processmanager.ProcessManagerHelper "%>
<%@page import="com.enterprisehorizons.magma.server.util.ServerUtils"%>

<%
	Object obj = request.getSession(false).getAttribute("stiUser");
	if(obj == null){ %>
	<script>
		window.location = "<%=ServerUtils.getContextName(request)%>/";				
	</script>
<%	}

boolean showLdapText = false;

if (AdminConfigUtils.isLDAPDatasource())
	showLdapText = true;

%>
<html>
<head>

<style>
    @import "<%=ServerUtils.getContextName(request)%>/js/dojo/resources/dojo.css";
    @import "<%=ServerUtils.getContextName(request)%>/js/dijit/themes/tundra/tundra.css";
    @import "<%=ServerUtils.getContextName(request)%>/js/dijit/themes/tundra/tundra_rtl.css";   
</style>
<script type="text/javascript" src="<%=ServerUtils.getContextName(request)%>/js/dojo/dojo.js" djConfig="isDebug: false, parseOnLoad: true"></script>
<script type="text/javascript">
            dojo.require("dijit.layout.ContentPane");
</script>
<jsp:include page="/common/charsetmeta.jsp"/>
<script type="text/javascript" src="<%=ServerUtils.getContextName(request)%>/js/sessionTimeOut.js"></script>  
<%@ include file="/common/style.jsp"%>
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
                 <bean:message key="admin.title" bundle="admin"/>
                </td>
            </tr>
            <tr>
               <td class="paddingTitleDesc bodytext">
                 <strong><bean:message key="admin.title.description" bundle="admin"/></strong>
                </td>  
            </tr>
            <tr>
                <td style="padding-left:48px;padding-top:30px;">
                <table cellpadding="0" cellspacing="19px" border="0"><tr>
            <td>
                <div dojoType="dijit.layout.ContentPane" region="center" class="thumbnail-itemOut"  onclick="renderPage('<%=ServerUtils.getContextName(request)%>/WizardHomeAction.do?operation=showAllDatasource&pageNo=1')">
                    <div  class="thumbnail-itemIn"  >
                       
                            <img src="<%=ServerUtils.getContextName(request)%>/images/portal/icon_datsources.png" />
                   
                    </div>
                    <bean:message key="admin.datasources" bundle="admin"/>
                </div>
            </td>
            <td>
                <div dojoType="dijit.layout.ContentPane" region="center"  class="thumbnail-itemOut"  onclick="renderPage('<%=ServerUtils.getContextName(request)%>/dbadmin.do')">
                    <div  class="thumbnail-itemIn"  >
                        
                            <img src="<%=ServerUtils.getContextName(request)%>/images/portal/icon_database_maintenance.png" />
                       
                    </div>
                    <bean:message key="admin.databaseMaintenance" bundle="admin"/>
                </div>
            </td>
            <td>
                <div dojoType="dijit.layout.ContentPane" region="center" class="thumbnail-itemOut"  onclick="renderPage('<%=ServerUtils.getContextName(request)%>/startStopAction.do?operation=refresh&jobName=')">
                    <div  class="thumbnail-itemIn"  >
                        
                            <img src="<%=ServerUtils.getContextName(request)%>/images/portal/icon_schedule.png" />
                       
                    </div>
            <bean:message key="admin.schedule.text" bundle="admin"/>
                </div>
            </td>
            <td>
                <div dojoType="dijit.layout.ContentPane" region="center" class="thumbnail-itemOut"  onclick="renderPage('<%=ServerUtils.getContextName(request)%>/cache.do')">
                    <div  class="thumbnail-itemIn"  >
                        
                            <img src="<%=ServerUtils.getContextName(request)%>/images/portal/icon_cache.png" />
                        
                    </div>
                <bean:message key="admin.cache" bundle="admin"/>
                </div>
            </td>
            <td>
                <div dojoType="dijit.layout.ContentPane" region="center" class="thumbnail-itemOut"  onclick="renderPage('<%=ServerUtils.getContextName(request)%>/shapedbMapping.do')">
                    <div  class="thumbnail-itemIn"  >
                       
                            <img src="<%=ServerUtils.getContextName(request)%>/images/portal/icon_shape_to_db_mapping.png" />
                      
                    </div>
            <bean:message key="admin.shapeToDatabaseMapping" bundle="admin"/>
                </div>
            </td>
            <td>
                <div dojoType="dijit.layout.ContentPane" region="center" class="thumbnail-itemOut"  onclick="renderPage('<%=ServerUtils.getContextName(request)%>/shapedbExport.do')">
                    <div  class="thumbnail-itemIn"  >
                       
                            <img src="<%=ServerUtils.getContextName(request)%>/images/portal/icon_shape_to_db.png" />
                       
                    </div>
                <bean:message key="admin.shapeToDatabase" bundle="admin"/>
                </div>
            </td>
            
            
        </tr>
        <tr>
			 <td>
                <div dojoType="dijit.layout.ContentPane" region="center" class="thumbnail-itemOut"  onclick="renderPage('<%=ServerUtils.getContextName(request)%>/generateKmlKmz.do')">
                    <div  class="thumbnail-itemIn"  >
                        
                            <img src="<%=ServerUtils.getContextName(request)%>/images/portal/icon_ge.png" />
                      
                    </div>
                <bean:message key="admin.kmlKmzGenerator" bundle="admin"/>
                </div>
            </td>
            <!-- commented this due to the Flex Component has in place. 
            <td>
                <div dojoType="dijit.layout.ContentPane" region="center" class="thumbnail-itemOut"  onclick="renderPage('<%=ServerUtils.getContextName(request)%>/multiUploadAction.do?flag=show&frommainpage=true')">
                    <div  class="thumbnail-itemIn"  >
                       
                            <img src="<%=ServerUtils.getContextName(request)%>/images/portal/icon_upload_files.png" />
                      
                    </div>
                <bean:message key="admin.uploadFiles" bundle="admin"/>
                </div>
            </td>
            -->
            
            <td>
                <div dojoType="dijit.layout.ContentPane" region="center" class="thumbnail-itemOut"  onclick="renderPage('<%=ServerUtils.getContextName(request)%>/flexMultiFileUploadAction.do?flag=show&frommainpage=true')">
                    <div  class="thumbnail-itemIn"  >
                       
                            <img src="<%=ServerUtils.getContextName(request)%>/images/portal/icon_upload_files.png" />
                      
                    </div>
                <bean:message key="admin.uploadFiles" bundle="admin"/>
                </div>
            </td>
            <td>
                <div dojoType="dijit.layout.ContentPane" region="center" class="thumbnail-itemOut"  onclick="renderPage('<%=ServerUtils.getContextName(request)%>/deleteFileAction.do?flag=view')">
                    <div  class="thumbnail-itemIn"  >
                        
                            <img src="<%=ServerUtils.getContextName(request)%>/images/portal/icon_delete_uploaded_files.png" />
                       
                    </div>
            <bean:message key="admin.deleteUploadedFiles" bundle="admin"/>
                </div>
            </td>
            <!--
            <td>
                <div dojoType="dijit.layout.ContentPane" region="center" class="thumbnail-itemOut"  onclick="renderPage('<%=ServerUtils.getContextName(request)%>/NewEcoDataSourceAction.do?operation=onLoad')" >
                    <div  class="thumbnail-itemIn"  >
                        
                            <img src="<%=ServerUtils.getContextName(request)%>/images/portal/icon_ecoweb_wizard.png" />
                       
                    </div>
            <bean:message key="admin.ecowebWizard" bundle="admin"/>
                </div>
            </td>
            -->
            <td>
                <div dojoType="dijit.layout.ContentPane" region="center" class="thumbnail-itemOut"  onclick="renderPage('<%=ServerUtils.getContextName(request)%>/unDeployEcoAction.do')">
                    <div  class="thumbnail-itemIn"  >
                       
                            <img src="<%=ServerUtils.getContextName(request)%>/images/portal/icon_undeploy_ecosys_files.png" />
                     
                    </div>
            <bean:message key="admin.undeployEcosystemFiles" bundle="admin"/>
                </div>
            </td>
            <td>
                <div dojoType="dijit.layout.ContentPane" region="center" class="thumbnail-itemOut"  onclick="renderPage('<%=ServerUtils.getContextName(request)%>/manageConfigAction.do')">
                    <div  class="thumbnail-itemIn"  >
                      
                            <img src="<%=ServerUtils.getContextName(request)%>/images/portal/icon_manage_config.png" />
                      
                    </div>
        <bean:message key="admin.manageConfigurations" bundle="admin"/>
                </div>
            </td>
                <td>
                <div dojoType="dijit.layout.ContentPane" region="center" class="thumbnail-itemOut"  onclick="renderPage('<%=ServerUtils.getContextName(request)%>/dbaAdminSecurity.do')">
                    <div  class="thumbnail-itemIn"  >
                        
                            <img src="<%=ServerUtils.getContextName(request)%>/images/portal/icon_security.png" />
                      
                    </div>
                <%if(showLdapText){%>   
                    <bean:message key="admin.security.LDAP" bundle="admin"/>
                <%}else{%>
                    <bean:message key="admin.security" bundle="admin"/>             
                <%}%>   
                </div>
           </td>
            </tr>
			<tr>
           	<td>
                <div dojoType="dijit.layout.ContentPane" region="center" class="thumbnail-itemOut"  onclick="renderPage('<%=ServerUtils.getContextName(request)%>/sgigAdmin.do')">
                    <div  class="thumbnail-itemIn"  >
                        
                            <img src="<%=ServerUtils.getContextName(request)%>/images/portal/icon_sgig_reporting.png" />
                      
                    </div>
        		
        			<bean:message key="admin.sgig" bundle="admin"/>        		
        		</div>
           </td>
           <!--
           <%						
			if(ProcessManagerHelper.isRasLinkEnabled()==true)   
		 	 {
		   %>	
				<td>
                <div dojoType="dijit.layout.ContentPane" region="center" style="font-size:13px;font-weight:bold;color:#FFFFFF; text-align:center;vertical-align:middle;" class="thumbnail-itemOut"  onclick="renderPage('<%=ServerUtils.getContextName(request)%>/showProcessManagerRoot.do')">
                    <div  class="thumbnail-itemIn"  >
                        
                            <img src="<%=ServerUtils.getContextName(request)%>/images/portal/icon_process_mngr.png" />
                    </div>
                    <bean:message key="admin.process.manager.title" bundle="admin"/>
                </div>
                </td>			 		
		   <%
			}
		   %>
		  -->
		 <td>
                <div dojoType="dijit.layout.ContentPane" region="center" class="thumbnail-itemOut"  onclick="renderPage('<%=ServerUtils.getContextName(request)%>/monitor.do')">
                    <div  class="thumbnail-itemIn"  >
                        
                            <img src="<%=ServerUtils.getContextName(request)%>/images/portal/icon_monitor.png" />
                       
                    </div>
                    
                    <bean:message key="admin.monitor" bundle="admin"/>       
                </div>
            </td>
            
               <td>
                <div dojoType="dijit.layout.ContentPane" region="center" class="thumbnail-itemOut"  onclick="renderPage('<%=ServerUtils.getContextName(request)%>/pi.do')">
                    <div  class="thumbnail-itemIn"  >
                        
                            <img src="<%=ServerUtils.getContextName(request)%>/images/portal/pi.png" />
                       
                    </div>
                    
                    <bean:message key="admin.pi" bundle="admin"/>       
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
  </table>
</body>
</html>
<script>
function renderPage(url){
	window.location = url;
}
dojo.addOnLoad(init);
function init(){
	try{
		parent.setIFrameProperties(false);
	}catch(err){
		//ERROR. when included the application under IFrame
	}
}
</script>