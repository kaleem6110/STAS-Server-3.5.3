<%
  response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
  response.setHeader("Pragma","no-cache"); //HTTP 1.0
  response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>

<%@ taglib uri="/tags/struts-bean" prefix="bean"%>

<%@page import="com.enterprisehorizons.magma.server.util.ServerUtils"%>
<%@page import="com.enterprisehorizons.magma.server.util.CommonUtils"%>
<%@ include file="style.jsp"%>
<script type="text/javascript"
	src="js/windowsjs/javascripts/prototype.js"> </script>
<script type="text/javascript" src="js/windowsjs/javascripts/effects.js"> </script>
<script type="text/javascript" src="js/windowsjs/javascripts/window.js"> </script>
<script type="text/javascript"
	src="js/windowsjs/javascripts/window_effects.js"> </script>
<script type="text/javascript" src="js/windowsjs/javascripts/debug.js"> </script>
<link href="js/windowsjs/themes/default.css" rel="stylesheet"
	type="text/css" />
<link href="js/windowsjs/themes/alert.css" rel="stylesheet"
	type="text/css" />
<link href="js/windowsjs/themes/alert_lite.css" rel="stylesheet"
	type="text/css" />
<link href="js/windowsjs/themes/spread.css" rel="stylesheet"
	type="text/css">
</link>
<link href="js/windowsjs/themes/alphacube.css" rel="stylesheet"
    type="text/css" />

<style type="text/css">
@import "<%=ServerUtils.getContextName(request)%>/js/dojo/resources/dojo.css";
@import "<%=ServerUtils.getContextName(request)%>/js/dijit/themes/tundra/tundra.css";
</style>

<script>

var titleMesssage;
		
function showReportBug() {
	titleMessage='<strong class="bannerText"><bean:message key="header.reportbug" bundle="admin"/></strong>';
    
    var innerData = '<fieldset style="width: 100%">';
	innerData=innerData+"<table><tr><td>&nbsp;</td></tr><tr><td padding-left='5px' class='logo'></td></tr>";
    innerData=innerData+"<tr class=\"bannerText1\"><td padding-left='10px'><bean:message key="header.reportbug.email" bundle="admin"/><a class=\"hyperLinkColor\" target='_blank' href='mailto:<bean:message key="header.reportbug.email.url" bundle="admin"/>'><bean:message key="header.reportbug.email.title" bundle="admin"/></a></td></tr>";
    innerData=innerData+"<tr class=\"bannerText1\"><td padding-left='10px'><bean:message key="header.reportbug.ticket" bundle="admin"/><a class=\"hyperLinkColor\" target='_blank' href='<bean:message key="header.reportbug.ticket.url" bundle="admin"/>'><bean:message key="header.reportbug.ticket.title" bundle="admin"/></a></td></tr></table></fieldset>";
    openInfoframe(innerData,titleMessage,110);
}

function showAbout() {
	titleMessage='<strong class="bannerText"><bean:message key="header.about" bundle="admin"/></strong>';
    
    var innerData = '<fieldset style="width: 100%">';
    innerData=innerData+"<table><tr><td>&nbsp;</td></tr><tr><td padding-left='5px'  class='logo'></td></tr>";
    innerData=innerData+"<tr ><td padding-left='10px'><b><bean:message key="header.about.title" bundle="admin"/></b></td></tr><tr><td padding-left='5px'> <bean:message key="header.about.version" bundle="admin"/> <bean:message key="header.about.version.value" bundle="buildinfo"/></td></tr>";
    innerData=innerData+"<tr><td padding-left='10px'><bean:message key="header.about.build.id" bundle="admin"/> <bean:message key="current.build.id" bundle="buildinfo"/></td></tr>";
    innerData=innerData+"<tr><td padding-left='10px'><bean:message key="header.about.copyright" bundle="admin"/></td></tr><tr><td>&nbsp;</td></tr>";
    innerData=innerData+"<tr class=\"bannerText1\"><td padding-left='10px' ><a class=\"hyperLinkColor\" target='_blank' href='common/TermsAndConditions.html'><bean:message key="header.about.license.title" bundle="admin"/></a></td></tr></table></fieldset>";
    openInfoframe(innerData,titleMessage,180);
}

var infoFrameWin;
function openInfoframe(innerData,title,numHeight){
    try{
         infoFrameWin = new Window({id: "infoFrameWin",showEffect:Element.show,hideEffect:Element.hide,className: "alphacube",minimizable: false,maximizable: false, resizable: false, title: title, width:400, height:numHeight, destroyOnClose:true, closable:true, wiredDrag: false, draggable: false}); 
         infoFrameWin.getContent().innerHTML = innerData;
         infoFrameWin.showCenter(true);
         infoFrameWin.toFront();   
    }   
    catch(ef){
    	if(infoFrameWin!=null) {
    		infoFrameWin.destroy();
    	}      
    }
}
</script>
  
 