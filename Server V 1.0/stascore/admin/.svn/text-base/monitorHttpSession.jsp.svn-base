<%@ include file="/common/dojo.jsp" %>

<%@ taglib prefix="tiles" uri="/tags/struts-tiles"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@page import="com.spacetimeinsight.cache.ICacheConstants"%>
<%@page import="com.spacetimeinsight.admin.bean.MonitorSessionBean"%>
<%@page import="com.spacetimeinsight.admin.bean.MonitorArtifactBean"%>
<%@page import="com.spacetimeinsight.admin.bean.MonitorUserBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>



<html:html locale="true">
<head>
<% 
    String tabId=(String)session.getAttribute("tabId");
    String refreshRate=(String)session.getAttribute("refreshRate");
%>
<meta http-equiv="refresh" content="${sessionScope.refreshRate}" />
<script>

    function hover(obj){
        obj.className='class_hover';
    }

    function unHover(obj){
       obj.className = 'class_no_hover bgcolor';
    }

    function showMonitor(){
        window.location = "monitor.do";
    }
    
  function showData(tabId) {
     window.location = "<%=ServerUtils.getContextName(request)%>/monitorHttpSessionAction.do?tabId="+tabId;
  }
    
    function changeRefreshRate(){
        document.forms[0].submit();
    }


</script>



<style type="text/css">

 a:active {
    outline: none;
    color:#000000;
}
a:focus {
    -moz-outline-style: none;
    color:#000000;
}

#tabs_container1 {
    width: 1000px;
    font-family: Tahoma;
    font-size: 12px; 
    font-weight: bold;    
    color:#000000;
}

#tabs_container ul.tabs {
    list-style: none;
    border-bottom: 1px solid #ccc;
    height: 21px;
    margin: 0;
    font-family: Tahoma;
    font-size: 12px; 
    font-weight: bold;       
    color:#000000;
    
}
#tabs_container ul.tabs li {
    float: left;
    font-family: Tahoma;
    font-size: 12px; 
    font-weight: bold;    
    color:#000000;
    
}
#tabs_container ul.tabs li a {
    padding: 3px 10px;
    display: block;
    border-left: 1px solid #ccc;
    border-top: 1px solid #ccc;
    border-right: 1px solid #ccc;
    margin-right: 2px;
    text-decoration: none;

}
#tabs_container ul.tabs li.active a {
    background-color: #fff;
    padding-top: 4px;
    font-family: Tahoma;
    font-size: 12px; 
    color:#000000;
    font-weight: bold;    
}
div.tab_contents_container {

    border-left: 1px solid #ccc;
    border-bottom: 1px solid #ccc;
    border-right: 1px solid #ccc;   
    padding: 10px;
}
div.tab_contents {
    display: none;
}
div.tab_contents_active {
border: 0px  #ccc;
    display: block;
}
div.clear {
    clear: both;
} 

</style>
</head>


<body class="tundra bodybg" >
<html:form action="/monitorHttpSessionAction.do">

<hidden name="tabId" value="<%=tabId%>">

<table width="101.2%" cellspacing="0" cellpadding="0" align="center"  border="0" >
    <tr>
        <td style="padding-left:67px;padding-top:37px;" class="pageTitle paddingTitle"><bean:message key="admin.monitor.session" bundle="admin" /></td>
    </tr>
    <tr>
        <td style="padding-left:67px;" class="paddingTitleDesc"><strong><bean:message key="admin.monitor.session.description" bundle="admin" /></strong></td>  
    </tr>   
    <tr>
        <td style="padding-left:67px;padding-top:30px;">
            <table width="100%" cellspacing="0" cellpadding="0" align="center" border="0">
                <tr align="center" >
                    <td>
                        <table width="75%" cellspacing="0" cellpadding="0" align="left">
                            <tr>
                                <td align="right">
                                    <strong><bean:message key="admin.monitor.refreshrate" bundle="admin" /></strong>
                                    <select id="refreshRate" name="refreshRate" dojoType="dijit.form.FilteringSelect" style="width:60;">
                                        <option value="60">1</option>
                                        <option value="120">2</option>
                                        <option value="300">5</option>
                                        <option value="600">10</option>
                                        <option value="1200">20</option>
                                        <option value="1800">30</option>
                                    </select>
                                    <button dojoType="dijit.form.Button" id="btnSetRefreshRate" type="button"  onclick="changeRefreshRate();"><bean:message key="admin.monitor.set.button" bundle="admin" />
                                    </button>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td style="padding-top:30px;">
                        <table width="75%" cellspacing="0" cellpadding="0" style="align:left;" border="0">
                            <tr>
                                <td>
                                    <strong> 
                                        <div id="tabs_container" style="align:left;" >
                                            <ul class="tabs">
                                                <li class='<%=tabId.equals("session")?"active":"tab panelColor"%>'><a style="cursor:hand" onclick="showData('session')"><bean:message key="admin.monitor.session.view" bundle="admin" /></a></li>
                                                <li class='<%=tabId.equals("artifact")?"active":"tab panelColor"%>'><a style="cursor:hand" onclick="showData('artifact')"><bean:message key="admin.monitor.artifact.view" bundle="admin" /></a></li>
                                                <li class='<%=tabId.equals("user")?"active":"tab panelColor"%>'><a style="cursor:hand" onclick="showData('user')"><bean:message key="admin.monitor.user.view" bundle="admin" /></a></li>  
                                            </ul>
                                            <logic:equal name="tabId" value="session">                                            
                                            <div class="bodybg" id="tab1" style="visibility:<%=tabId.equals("session")?"visible":"hidden"%>">
                                                <table style="align:left;" border="0" width="100%" class="bgcolorReports ">
                                                    <tr class="subHeaddings">
                                                        <td class="tab panelColor" align="left" valign="middle">&nbsp;<bean:message key="admin.monitor.number" bundle="admin" /></td>
                                                        <td class="tab panelColor" align="left" valign="middle">&nbsp;<bean:message key="admin.monitor.session.id" bundle="admin" /></td>
                                                        <td class="tab panelColor" align="left" valign="middle">&nbsp;<bean:message key="admin.monitor.artifact.name" bundle="admin" /></td>
                                                        <td class="tab panelColor" align="left" valign="middle">&nbsp;<bean:message key="admin.monitor.artifact.count" bundle="admin" /></td>
                                                    </tr>
                                                    <logic:notPresent name="listMonitorBySession" scope="request" >
                                                        <tr class="bgcolor">
                                                            <td colspan="4">
                                                                <label class = "label"><bean:message key="admin.monitor.no.data.available" bundle="admin" />
                                                                </label>
                                                            </td>
                                                        </tr>
                                                    </logic:notPresent> 
                                                    <logic:present name="listMonitorBySession" scope="request" >    
                                                        <logic:iterate id="sessionList" name="listMonitorBySession" scope="request" indexId="sessionListCount">
                                                            <tr class="bgcolor" onMouseOver="hover(this);" onMouseOut="unHover(this)">
                                                                <td align="left" valign="middle">&nbsp;&nbsp;${sessionListCount+1}</td>
                                                                <td align="left" valign="middle">&nbsp;&nbsp;<bean:write name='sessionList' property='sessionId' /></td>
                                                                <td align="left" valign="middle">&nbsp;&nbsp;<bean:write name='sessionList' property='artifactName' /></td>
                                                                <td align="left" valign="middle">&nbsp;&nbsp;<bean:write name='sessionList' property='noOfArtifacts' /></td>
                                                            </tr>
                                                        </logic:iterate>
                                                    </logic:present>    
                                                </table>
                                            </div>
                                            </logic:equal> 
                                            
                                            <logic:equal name="tabId" value="artifact">  
                                            <div id="tab2" class="bodybg" style="visibility:<%=tabId.equals("artifact")?"visible":"hidden"%>">
                                                <table style="align:left;" border="0" width="100%" class="bgcolorReports ">
                                                    <tr class="subHeaddings">
                                                        <td class="tab panelColor" align="left" valign="middle">&nbsp;<bean:message key="admin.monitor.number" bundle="admin" /></td>
                                                        <td class="tab panelColor" align="left" valign="middle">&nbsp;<bean:message key="admin.monitor.artifact.name" bundle="admin" /></td>
                                                        <td class="tab panelColor" align="left" valign="middle">&nbsp;<bean:message key="admin.monitor.artifact.count" bundle="admin" /></td>
                                                        <td class="tab panelColor" align="left" valign="middle">&nbsp;<bean:message key="admin.monitor.session.count" bundle="admin" /></td>
                                                    </tr>
                                                    <logic:notPresent name="listMonitorByArtifacts" scope="request" >
                                                        <tr class="bgcolor">
                                                            <td colspan="4">
                                                                <label class = "label"><bean:message key="admin.monitor.no.data.available" bundle="admin" />
                                                                </label>
                                                            </td>
                                                        </tr>
                                                    </logic:notPresent>
                                                    <logic:present name="listMonitorByArtifacts" scope="request" >
                                                        <logic:iterate id="artifactList" name="listMonitorByArtifacts" scope="request" indexId="artifactListCount">
                                                            <tr class="bgcolor" onMouseOver="hover(this);" onMouseOut="unHover(this)">
                                                                <td align="left" valign="middle">&nbsp;&nbsp;${artifactListCount+1}</td>
                                                                <td align="left" valign="middle">&nbsp;&nbsp;<bean:write name='artifactList' property='artifactName' /></td>
                                                                <td align="left" valign="middle">&nbsp;&nbsp;<bean:write name='artifactList' property='noOfArtifacts' /></td>
                                                                <td align="left" valign="middle">&nbsp;&nbsp;<bean:write name='artifactList' property='noOfSessions' /></td>
                                                            </tr>
                                                        </logic:iterate>
                                                    </logic:present> 
                                                </table>
                                            </div>
                                             </logic:equal> 
                                             
                                            <logic:equal name="tabId" value="user">   
                                            <div id="tab3" class="bodybg" style="visibility:<%=tabId.equals("user")?"visible":"hidden"%>">
                                                <table style="align:left;" border="0" width="100%" class="bgcolorReports ">
                                                    <tr class="subHeaddings">
                                                        <td class="tab panelColor" align="left" valign="middle">&nbsp;<bean:message key="admin.monitor.number" bundle="admin" /></td>
                                                        <td class="tab panelColor" align="left" valign="middle">&nbsp;<bean:message key="admin.monitor.login.id" bundle="admin" /></td>
                                                        <td class="tab panelColor" align="left" valign="middle">&nbsp;<bean:message key="admin.monitor.session.count" bundle="admin" /></td>
                                                        <td class="tab panelColor" align="left" valign="middle">&nbsp;<bean:message key="admin.monitor.artifact.count" bundle="admin" /></td>
                                                    </tr>
                                                    <logic:notPresent name="listMonitorByUser" scope="request" >
                                                        <tr class="bgcolor">
                                                            <td colspan="4">
                                                                <label class = "label"><bean:message key="admin.monitor.no.data.available" bundle="admin" />
                                                                </label>
                                                            </td>
                                                        </tr>
                                                    </logic:notPresent>
                                                    <logic:present name="listMonitorByUser" scope="request" >
                                                        <logic:iterate id="userList" name="listMonitorByUser" scope="request" indexId="userListCount">
                                                            <tr class="bgcolor" onMouseOver="hover(this);" onMouseOut="unHover(this)">
                                                                <td align="left" valign="middle">&nbsp;&nbsp;${userListCount+1}</td>
                                                                <td align="left" valign="middle">&nbsp;&nbsp;<bean:write name='userList' property='loginId' /></td>
                                                                <td align="left" valign="middle">&nbsp;&nbsp;<bean:write name='userList' property='noOfSessions' /></td>
                                                                <td align="left" valign="middle">&nbsp;&nbsp;<bean:write name='userList' property='noOfArtifacts' /></td>
                                                            </tr>
                                                        </logic:iterate>
                                                    </logic:present>
                                                </table>
                                            </div>
                                            </logic:equal> 
                                                    
                                        </div>
                                    </strong>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </td>
    </tr>        
</table>
<!-- Button Panel -->
<table width="101.2%" border="0" >
    <tr>
        <td style="padding-top:55px;">&nbsp;</td>
    </tr>
    <tr align="right" class="barColor">
        <td align="center" width="100%">
            <button dojoType="dijit.form.Button"  type="button"  onclick="window.location =  '<%=ServerUtils.getContextName(request)%>/adminMain.do'  "><bean:message key="admin.monitor.home.button" bundle="admin" />
            </button>
			<button dojoType="dijit.form.Button" id="btnRefresh" type="button"  onclick="window.location =('<%=ServerUtils.getContextName(request)%>/monitorHttpSessionAction.do?operation=return')"><bean:message key="admin.monitor.refresh.button" bundle="admin" />
            </button>
            <button dojoType="dijit.form.Button" id="btnBack" type="button"  onclick="showMonitor()"><bean:message key="admin.monitor.back.button" bundle="admin" />
            </button>
        </td>
    </tr>
</table>
</html:form>
</body>
<script>
   dojo.addOnLoad(loadValues); 
   function loadValues(){
      dijit.byId('refreshRate').setValue("${sessionScope.refreshRate}");
      window.status="Done";
    }
</script>
</html:html>