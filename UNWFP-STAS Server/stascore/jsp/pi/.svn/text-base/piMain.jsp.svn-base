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
							<strong class="pageTitle paddingTitle" style="padding-left:0px"><bean:message key="admin.pi.Admin.home" bundle="admin"/><br/>
								<span class="paddingTitleDesc bodytext" style="padding-left:0px"><bean:message key="admin.pi.Admin.category.desc" bundle="admin"/></span>
							</strong>
						</td>  		  
						<td width="67px">&nbsp;</td>	               
					</tr>
					<tr>
						<td>
							<table>
								<tr>
						            <td>
										<div dojoType="dijit.layout.ContentPane" region="center" class="thumbnail-itemOut" onClick="renderPage('<%=ServerUtils.getContextName(request)%>/piServerGroupAction.do')">
											<div  class="thumbnail-itemIn" >
												<img src="<%=ServerUtils.getContextName(request)%>/images/portal/piserver.png" />
											</div>
											<label><bean:message key="admin.pi.configure.pi.server.group" bundle="admin" /></label>
										</div>
									</td>  
									<td width="30" align="left">&nbsp;</td>
									
									
									
									<td>	
										<div dojoType="dijit.layout.ContentPane" region="center" class="thumbnail-itemOut" onClick="renderPage('<%=ServerUtils.getContextName(request)%>/piEventGroup.do')">
											<div  class="thumbnail-itemIn"  >
												<img src="<%=ServerUtils.getContextName(request)%>/images/portal/pieventgroup.png" />
											</div>
											<label><bean:message key="admin.pi.configure.Pi.Event.Group" bundle="admin" /></label>
										</div>	
									</td> 
									
								
								<td width="30" align="left">&nbsp;</td>
									<td>	
										<div dojoType="dijit.layout.ContentPane" region="center" class="thumbnail-itemOut" onClick="renderPage('<%=ServerUtils.getContextName(request)%>/piAFEventGroup.do')">
											<div  class="thumbnail-itemIn"  >
												<img src="<%=ServerUtils.getContextName(request)%>/images/portal/PIAFEventGroup.png" />
											</div>
									
												<label>PI AF Event Group</label>					
										</div>	
									</td> 

									<td width="30" align="left">&nbsp;</td>
									

									<td>	
										<div dojoType="dijit.layout.ContentPane" region="center" class="thumbnail-itemOut" onClick="renderPage('<%=ServerUtils.getContextName(request)%>/piMonitorAction.do')">
											<div  class="thumbnail-itemIn"  >
												<img src="<%=ServerUtils.getContextName(request)%>/images/portal/pimonitor.png" />
											</div>
											<label><bean:message key="admin.pi.monitor" bundle="admin" /></label>
										</div>	
									</td> 
									<!-- code for pi notification start -->
                                    <td width="30" align="left">&nbsp;</td>
                                    <td>    
                                        <div dojoType="dijit.layout.ContentPane" region="center" class="thumbnail-itemOut" onClick="renderPage('<%=ServerUtils.getContextName(request)%>/piNotificationAction.do')">
                                        <div  class="thumbnail-itemIn"  >
                                                <img src="<%=ServerUtils.getContextName(request)%>/images/portal/piNotification.png" />
                                            </div>
                                                <label>PI Notification Listeners</label>
                                        </div>  
                                    </td> 
                                        <!-- code for pi notification end -->
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
					<td align="right"><button dojoType="dijit.form.Button" class="register"  type="button" onClick="goToDBHomePage()"> <bean:message key="dbconfig.home"/> </button></td>  
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