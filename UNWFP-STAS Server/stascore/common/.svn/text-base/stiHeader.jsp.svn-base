<%@page import="com.enterprisehorizons.magma.server.util.CommonUtils"%>
<%@page import="com.enterprisehorizons.magma.server.util.ServerUtils"%>
<%@page import="com.spacetimeinsight.security.bean.UserBean"%>
<%@page import="com.spacetimeinsight.security.bean.ModuleBean"%>
<%@page import="java.util.*"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%><head>
  <jsp:include page="/common/style.jsp"/>
  <jsp:include page="/common/HeaderCommon.jsp"/>
</head>

<table width="100%" cellspacing="0" cellpadding="0"  border="0" class="bgHeaderImage">
   <tr>
	<td width="30%" align="left" valign="top" class="logo">
		 
	</td>
	
	<%
	    String path=CommonUtils.getCustomerLogoPath(request);
		if(path.endsWith(".jpeg") || path.endsWith(".jpg") || path.endsWith(".gif") || path.endsWith(".png")
		   || path.endsWith(".JPEG") || path.endsWith(".JPG") || path.endsWith(".GIF") || path.endsWith(".PNG"))
	    {
		out.print("<td width=\"40%\" align=\"center\"><img src=\""+path+"\" height=\"20\" /></td>");
		}
            else
	    {
	       out.print("<td width=\"40%\" align=\"center\"></td>");  
	    }	
	%>	

	
			<script type="text/javascript">
			dojo.require("dijit.form.Button");
			dojo.require("dijit.Menu");
			/* Getting list of modules */
			dojo.addOnLoad(function() {
					<% 
						UserBean userBean = (UserBean)session.getAttribute("stiUser");
						List moduleList = userBean.getModules();
						for(int i=0;i<moduleList.size();i++){
							ModuleBean moduleBean = (ModuleBean)moduleList.get(i);
						}
					%>
					var button = new dijit.form.Button({
						label: "Modules",
						name: "programmatic2",
						id: "moduleButton", 
						style: "display: none; "						
					}); 
				});
			</script>
	<td width="30%" align="right" > 
			<strong class="white1text1 bannerText">
			<bean:message key="stiheader.welcome" bundle="security" />  
				<logic:present name="stiUser" >
					<bean:write name="stiUser" property="firstName" />&nbsp;<bean:write name="stiUser" property="lastName" />&nbsp;&nbsp; 
				</logic:present>
				<a class="white1text1 bannerText" href="javascript:showReportBug()"><bean:message key="header.reportbug" bundle="admin"/></a>&nbsp; &nbsp;
				<a class="white1text1 bannerText" href="javascript:showAbout()"><bean:message key="header.about" bundle="admin"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<img src='<%=ServerUtils.getContextName(request)%>/images/portal/toggleBtn.png' id="toggleButton" title="Hide/Display Menu" onclick="show()">  
					  <script >  
					  /*add tooltip commented as not able to change the font size*/
							function show(){

					  /*Toggling logic for menutabs getting menutabs id from stiLayout*/
								var menutabs = document.getElementById("menutabs");		
								if(menutabs.style.visibility == 'visible') {
									menutabs.style.visibility='hidden';
									menutabs.style.display='none';
								} else {
									menutabs.style.visibility = 'visible';
									menutabs.style.display='';
								}
								var moduleButton = dijit.byId("moduleButton").domNode;
								if(moduleButton.style.display=='') {
									moduleButton.style.display='none';
								} else {
								//	moduleButton.style.display='';
								}
							}
						 </script> 
				</img>&nbsp;&nbsp;
			</strong>
	 </td>
   </tr>
</table>
