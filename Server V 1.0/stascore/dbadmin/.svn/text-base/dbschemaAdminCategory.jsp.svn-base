<%@page import="com.enterprisehorizons.magma.server.util.ServerUtils"%>
<%@ page import="java.util.*,com.enterprisehorizons.db.util.*,com.enterprisehorizons.util.* , com.enterprisehorizons.magma.config.dbadmin.*" %> 
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>

<%@ taglib prefix="tiles" uri="/tags/struts-tiles"%>



<html>
<head>
<%@ include file="/common/dojo.jsp"%>
<!--- Importing the Styles -->

<style>
    @import "<%=ServerUtils.getContextName(request)%>/js/dojo/resources/dojo.css";
    @import "<%=ServerUtils.getContextName(request)%>/js/dijit/themes/tundra/tundra.css";
    @import "<%=ServerUtils.getContextName(request)%>/js/dijit/themes/tundra/tundra_rtl.css";
            
    .thumbnail-item {
        float: left; 
        margin: 5px; 
        padding: 25px; 
        border: 2px #fff solid; 
        cursor: pointer;
        text-align: center;
        background-color: #fafafa;
        width: 150px;
        height: 150px;
        font-size: 11px;
        color: #990000;
        border-color:'#cccccc'
    }

    .thumbnail-item img {
        margin: 0px;
    }
	<%request.getSession().removeAttribute(ModelConfigConstants.GO_BACK);%>
</style>
<script type="text/JavaScript">
    function goToDBHomePage(){
            window.location = "<%=ServerUtils.getContextName(request)%>/adminMain.do";
    }
</script>
<script type="text/javascript" src="<%=ServerUtils.getContextName(request)%>/js/dojo/dojo.js" djConfig="isDebug: false, parseOnLoad: true"></script>
<script type="text/javascript">
            dojo.require("dijit.layout.ContentPane");
            dojo.require("dijit.form.Button");      
</script>

</head>     
<body class="tundra bodybg">
<form name= "adminCategory">

   <table width="101.2%">
		<tr>
        <td class="pageTitle paddingTitle" height="300px" align="left" valign="top" >
		  <table cellspacing="0" cellpadding="0">		  
		  <tr>     
               <td height="60px" align="left" valign="top"  class="redtitle" >
			  <strong class="pageTitle paddingTitle" style="padding-left:0px"><bean:message key="dbconfig.modelMaintainHome"/><br/>
                 <span class="paddingTitleDesc bodytext" style="padding-left:0px"><bean:message key="dbconfig.data.category.desc"/></span></strong>
			  </td>  		  
              <td width="67px">&nbsp;</td>	               
          </tr>
          <tr>
				
				<td>
					<table>
					  <tr>		   
					   <td >
						 <div dojoType="dijit.layout.ContentPane" region="center" class="thumbnail-itemOut" onClick="renderPage('<%=ServerUtils.getContextName(request)%>/masterMaintenanceHomeAction.do?screenCategory=platform')">
						<div  class="thumbnail-itemIn"  >
			                        
			                            <img src="<%=ServerUtils.getContextName(request)%>/images/portal/icon_platform.png" />
			                        
			                    </div>
			                	<label>Platform</label>
			                </div>
						</td> 
					   <td width="30" align="left">&nbsp;</td>
					   <td>	
					   <div dojoType="dijit.layout.ContentPane" region="center" class="thumbnail-itemOut" onClick="renderPage('<%=ServerUtils.getContextName(request)%>/masterMaintenanceHomeAction.do?screenCategory=config')"> 
					   <div  class="thumbnail-itemIn"  >
			                        
			                      <img src="<%=ServerUtils.getContextName(request)%>/images/portal/icon_config.png" />
			                        
			                    </div>
			                	<label>Configuration</label>
			                </div>	
					    </td> 
					   <td width="30" align="left">&nbsp;</td>	
					    <td>
			                <div dojoType="dijit.layout.ContentPane" region="center" class="thumbnail-itemOut"  onclick="renderPage('<%=ServerUtils.getContextName(request)%>/WizardHomeAction.do?operation=showAllJ2EEDatasource&pageNo=1')">
			                    <div  class="thumbnail-itemIn"  >
			                        
			                            <img src="<%=ServerUtils.getContextName(request)%>/images/portal/icon_choose_datasource.png" />
			                        
			                    </div>
			                	<label>J2EE Datasources</label>
			                </div>
			            </td>
					   <td width="30" align="left">&nbsp;</td>
					      <!--
					   <td width="150" align="left" valign="top">       <a href="<%=ServerUtils.getContextName(request)%>/masterMaintenanceHomeAction.do?screenCategory=stem"><img src="<%=ServerUtils.getContextName(request)%>/images/stem.gif" /></a></td>
					   -->
					    <td>	
					   <div dojoType="dijit.layout.ContentPane" region="center" class="thumbnail-itemOut" onClick="renderPage('<%=ServerUtils.getContextName(request)%>/ams.do')"> 
					   <div  class="thumbnail-itemIn"  >
			                        
			                      <img src="<%=ServerUtils.getContextName(request)%>/images/portal/alertMessaging.png" />
			                        
			                    </div>
			                	<label>Alerts</label>
			                </div>	
					    </td> 
					   <td width="30" align="left">&nbsp;</td>	
					   <td>
                <div dojoType="dijit.layout.ContentPane" region="center" class="thumbnail-itemOut"  onclick="renderPage('<%=ServerUtils.getContextName(request)%>/exportAdmin.do')">
                    <div  class="thumbnail-itemIn"  >
                        
                            <img src="<%=ServerUtils.getContextName(request)%>/images/portal/icon_export.png" />
                      
                    </div>
        		
        			<label>Export Master Data </label>   		
        		</div>
           </td>
            <td width="30" align="left">&nbsp;</td>
			
			 <td>
                <div dojoType="dijit.layout.ContentPane" region="center" class="thumbnail-itemOut"  onclick="renderPage('<%=ServerUtils.getContextName(request)%>/CEPAction.do')">
                    <div  class="thumbnail-itemIn"  >
                        
                            <img src="<%=ServerUtils.getContextName(request)%>/images/portal/icon_CEP.png" />
                      
                    </div>
        		
        			<label>CEP Configuration</label>   		
        		</div>
           </td>
			
					   <!-- td>
                <div dojoType="dijit.layout.ContentPane" region="center" class="thumbnail-itemOut"  onclick="renderPage('<%=ServerUtils.getContextName(request)%>/importAdmin.do')">
                    <div  class="thumbnail-itemIn"  >
                        
                            <img src="<%=ServerUtils.getContextName(request)%>/images/portal/icon_import.png" />
                      
                    </div>
        		
        			<label>Import MasterData </label>   		
        		</div>
           </td -->
					 </tr>		
					</table>
				</td>
				<td>&nbsp;</td>	
		  </tr>
        <tr>
		   <td>&nbsp;</td>
		   <td>&nbsp;</td>
		</tr>
        </table>
	</td>
	</tr>	
	   <tr align = "right" class="barColor">		 
		  <td height="30"  width="410">
		      <table width="100%" cellspacing="0" cellpadding="0">
                  <td width="76%">&nbsp;</td>		   
                  <td align="right"><button dojoType="dijit.form.Button" class="register"  type="button" onClick="goToDBHomePage()"> <bean:message key="dbconfig.home"/> </button> 
                  </td>  
				</table>
		  </td>
         </tr>
	</table>
	 
</form>
</body>
</html>

<script>
function renderPage(url){
	window.location = url;
}
</script>