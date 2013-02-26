<%@page import="com.enterprisehorizons.magma.server.util.ServerUtils"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ page import="com.spacetimeinsight.stas.config.GenericConfigurationManager,com.enterprisehorizons.magma.server.admin.AdminConfigUtils,com.spacetimeinsight.stas.config.ConfigurationConstants"%>

<html>
<head>
<%@ include file="/common/dojo.jsp"%>
<!--- Importing the Styles -->
<style>
    @import "<%=ServerUtils.getContextName(request)%>/js/dojo/resources/dojo.css";
    @import "<%=ServerUtils.getContextName(request)%>/js/dijit/themes/tundra/tundra.css";
    @import "<%=ServerUtils.getContextName(request)%>/js/dijit/themes/tundra/tundra_rtl.css";
</style>
<script type="text/javascript" src="<%=ServerUtils.getContextName(request)%>/js/dojo/dojo.js" djConfig="isDebug: false, parseOnLoad: true"></script>
<script type="text/JavaScript">
    function goToDBHomePage(){
            window.location = "<%=ServerUtils.getContextName(request)%>/adminMain.do";
    }
</script>

<script type="text/javascript">
            dojo.require("dijit.layout.ContentPane");
            dojo.require("dijit.form.Button");    
</script>
<%
boolean showFlag = true;

if (AdminConfigUtils.isLDAPDatasource())
	showFlag = false;
%>
</head>     
<body class="tundra bodybg" >
<table width="101.2%" cellspacing="0" cellpadding="0" align="center"  border="0" >
            <tr>
                <td  class="pageTitle paddingTitle"> 
                <%if(!showFlag){%>
                 <bean:message key="admin.security.LDAP.title" bundle="admin"/>                
                <%}else{%>
                 <bean:message key="admin.security.title" bundle="admin"/>                
                <%}%>     

                </td>
            </tr>
            <tr>
               <td class="paddingTitleDesc">
                <%if(!showFlag){%>
                 <strong><bean:message key="admin.security.LDAP.description" bundle="admin"/></strong>                
                <%}else{%>
                 <strong><bean:message key="admin.security.description" bundle="admin"/></strong>
                <%}%>     



                </td>  
            </tr>
            <tr>
                <td style="padding-left:48px;padding-top:30px;">
                <table cellpadding="0" cellspacing="19px" border="0">
                <tr>
                <%if(showFlag){ %>
            <td onClick="renderPage('<%=ServerUtils.getContextName(request)%>/masterTableViewAction.do?paramModelName=com.spacetimeinsight.db.security.model.User&fromScreen=Security')">
                <div dojoType="dijit.layout.ContentPane" region="center" class="thumbnail-itemOut" >
                    <div  class="thumbnail-itemIn"  >
                        
                            <img src="<%=ServerUtils.getContextName(request)%>/images/portal/icon_user.png" />
                       
                    </div>
                    <bean:message key="admin.user" bundle="admin"/>
                </div>
            </td>            
            <td onClick="renderPage('<%=ServerUtils.getContextName(request)%>/masterTableViewAction.do?paramModelName=com.spacetimeinsight.db.security.model.Group&fromScreen=Security')">
                <div dojoType="dijit.layout.ContentPane" region="center" class="thumbnail-itemOut" >
                    <div  class="thumbnail-itemIn"  >
                        
                            <img src="<%=ServerUtils.getContextName(request)%>/images/portal/icon_groups.png" />
                       
                    </div>
                    <bean:message key="admin.groups" bundle="admin"/>
                </div>
            </td>
            <td onClick="renderPage('<%=ServerUtils.getContextName(request)%>/masterTableViewAction.do?paramModelName=com.spacetimeinsight.db.security.model.UserGroup&fromScreen=Security')">
                <div dojoType="dijit.layout.ContentPane" region="center" class="thumbnail-itemOut" >
                    <div  class="thumbnail-itemIn"  >
                        
                            <img src="<%=ServerUtils.getContextName(request)%>/images/portal/icon_user_group_mapping.png" />
                      
                    </div>
                    <bean:message key="admin.usergrpmapping" bundle="admin"/>
                </div>
            </td>
            <%} %>
			   <%if(!showFlag){ %>
			   <%if(AdminConfigUtils.isAuthorizeInLDAP()){%>
                <td onClick="renderPage('<%=ServerUtils.getContextName()%>/manageADGroup.do')">
                <div dojoType="dijit.layout.ContentPane" region="center" class="thumbnail-itemOut" >
                    <div  class="thumbnail-itemIn"  >
                        
                            <img src="<%=ServerUtils.getContextName()%>/images/portal/icon_ad_group_mapping.png" />
                       
                    </div>
                    <bean:message key="admin.adgroupsyncmodule" bundle="admin"/>
                </div>
            </td>
            <%}%>
            <%if(!AdminConfigUtils.isAuthorizeInLDAP()){%>
            <td onClick="renderPage('<%=ServerUtils.getContextName(request)%>/masterTableViewAction.do?paramModelName=com.spacetimeinsight.db.security.model.Group&fromScreen=Security')">
            <div dojoType="dijit.layout.ContentPane" region="center" class="thumbnail-itemOut" >
                <div  class="thumbnail-itemIn"  >
                    
                        <img src="<%=ServerUtils.getContextName(request)%>/images/portal/icon_groups.png" />
                   
                </div>
                <bean:message key="admin.groups" bundle="admin"/>
            </div>
        </td>
        <td onClick="renderPage('<%=ServerUtils.getContextName(request)%>/authorizeInLDAP.do?paramModelName=com.spacetimeinsight.db.security.model.UserGroup&fromScreen=Security')">
            <div dojoType="dijit.layout.ContentPane" region="center" class="thumbnail-itemOut" >
                <div  class="thumbnail-itemIn"  >
                    
                        <img src="<%=ServerUtils.getContextName(request)%>/images/portal/icon_user_group_mapping.png" />
                  
                </div>
                <bean:message key="admin.usergrpmapping" bundle="admin"/>
            </div>
        </td>
        <%}%>
            
            
            
            
            
            <%}%>
            <td onClick="renderPage('<%=ServerUtils.getContextName(request)%>/masterTableViewAction.do?paramModelName=com.spacetimeinsight.db.config.model.ModuleGroupMap&fromScreen=Security')">
                <div dojoType="dijit.layout.ContentPane" region="center" class="thumbnail-itemOut" >
                    <div  class="thumbnail-itemIn"  >
                      
                            <img src="<%=ServerUtils.getContextName(request)%>/images/portal/icon_module_group_mapping.png" />
                        
                    </div>
                    <bean:message key="admin.moduleGroupMapping" bundle="admin"/>
                </div>
            </td>            
            <td onClick="renderPage('<%=ServerUtils.getContextName(request)%>/manageRoles.do')">
                <div dojoType="dijit.layout.ContentPane" region="center" class="thumbnail-itemOut" >
                    <div  class="thumbnail-itemIn"  >
                        
                            <img src="<%=ServerUtils.getContextName(request)%>/images/portal/icon_ecosys_group_mapping.png" />
                       
                    </div>
                    <bean:message key="admin.ecoSystemGroupMapping" bundle="admin"/>
                </div>
            </td>          
        </tr>
       </table>
       </td>
            
        </tr>
               <tr align = "right" >         
          <td height="30"  class="barColor">
              <table width="100%" cellspacing="0" cellpadding="0">                          
                  <td width='1000px' align="right"><button dojoType="dijit.form.Button" class="register"  type="button" onClick="goToDBHomePage()"> <bean:message key="dbconfig.home"/> </button> 			  
                  </td>  
				  <td>&nbsp;</td>				   
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