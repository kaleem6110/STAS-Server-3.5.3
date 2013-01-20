<%@ taglib prefix="tiles" uri="/tags/struts-tiles"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@page import="com.enterprisehorizons.magma.server.admin.AdminConfigUtils"%>
<%@page import="com.enterprisehorizons.magma.server.util.ServerUtils"%>
<html:html locale="true">
<head>

<title>Space . Time . Insight - <tiles:getAsString name="stiTitle" ignore="true"/></title>

<script type="text/javascript">
	function setIFrameProperties(setPropStatus){
		if (/MSIE (\d+\.\d+);/.test(navigator.userAgent)){ //test for MSIE x.x;
		 var ieversion=new Number(RegExp.$1)               // capture x.x portion and store as a number

		 if (ieversion>=8){
		  //document.write("You're using IE8 or above")
		 }
		 else if (ieversion>=7){
		  //document.write("You're using IE7.x")
		 }
		 else if (ieversion>=6){
		  //document.write("You're using IE6.x")		 
		  if(setPropStatus){			  
			document.getElementById('windowIframe').style.height = screen.height;
		  }else{
			document.getElementById('windowIframe').style.height = '';
		  }
			//alert(document.getElementById('windowIframe').scrolling);
		 }
		 else if (ieversion>=5){
		  //document.write("You're using IE5.x")
		 }

		}
		else{
		 //document.write("n/a")
		}
	}
</script>
<script type="text/javascript" src="js/dojo/dojo.js"
    djConfig="isDebug: false, parseOnLoad: true"></script>
    <script type="text/javascript" src="js/ecoweb.js" ></script>
<script type="text/javascript">
    dojo.require("dijit.Dialog");
    dojo.require("dijit.form.Button");  
</script>
<style type="text/css">
@import "<%=ServerUtils.getContextName(request)%>/js/dojo/resources/dojo.css";
@import "<%=ServerUtils.getContextName(request)%>/js/dijit/themes/tundra/tundra.css";
</style>
<%@ include file="/common/commonValidation.jsp" %>
<script type="text/javascript" src="js/windows.js"></script>
</head>
    	
<body class="tundra bodybg" >
<%
	
	String apptheme = AdminConfigUtils.getApplicationTheme();
	if (apptheme == null || apptheme.equals("null")) {
		apptheme = "default";
	}

%>
<table width="100%" height="100%" cellspacing="0" cellpadding="0" border="0">
	<tr>
    	     <td width="100%" valign="top">
			<% if(apptheme.equals("blue"))
			   {
			      out.print("<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" class=\"bgTiling\">"); 
			   }
                           else
			   {
			      out.print("<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" ");
			   }
			%>

			   <tr>
					<td width="100%" colspan="3" align="left" class="title" style="padding-left:0px;">
						<!-- Header Starts Here -->

						<tiles:get name="stiHeader"/>

						<!-- Header Ends Here -->
					</td>
			   </tr>
			   
			   <tr id="menutabs" style="visibility: visible">					
					<td height="30" class="bgMenuColor" colspan="3">	
						<!-- Menu Start here -->
						<tiles:get name="stiMenu"/>
						<!-- Menu Ends here -->
					</td>									
			   </tr>			   
			</table>
	       </td>
	</tr>
		<tr>
			<td height="1" class="bgColoring"></td>
		</tr>

	<tr>
     	    <td width="100%" align="left"  height="100%">
			<table width="100%" height="100%" border="0">
				<tr>
					<td height="100%">
						<iframe id="windowIframe"  frameborder="0" width="100%" height="100%" src="<%=ServerUtils.getContextName(request)%><tiles:getAsString name="body"/>">
						</iframe>
					</td>
				</tr>
		        </table>    
	    </td>
	</tr>
</table>	
</body>
</html:html>
<script>
    dojo.addOnLoad(showChangePasswordMessage); 
        
      function showChangePasswordMessage() {
		  
       <%
	    String changePassword=request.getParameter("changePassword");
	    if(changePassword!=null && changePassword.equals("true")) 
	    {
	     %>
			 //alert(11111111111);
	      showEmptyDialog("<bean:message key='admin.changepassword.dialog' bundle='admin'/>","<bean:message key='admin.common.alert.title' bundle='admin'/>");
	       //window.location = '<%=ServerUtils.getContextName(request)%>/loginAction.do';
	     <%
	    }
	    %>

	   }
   </script>
