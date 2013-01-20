 <%@ page import="com.enterprisehorizons.magma.config.dbadmin.ModelConfigConstants" %>
 <%@ page import="java.util.List" %>
<%@ page import="com.spacetimeinsight.magma.job.bean.JobMonitorBean" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ page import="java.util.*, com.spacetimeinsight.magma.job.JobConstants, com.enterprisehorizons.magma.job.bd.JobScheduleDelegator,com.spacetimeinsight.magma.job.bean.StiJobBean"%>
<%@page import="com.enterprisehorizons.magma.server.util.ServerUtils"%>
<%@ taglib prefix="tiles" uri="/tags/struts-tiles"%>

<%@ include file="/common/dojo.jsp" %>

<%
    int jobsCount = 0;
%>

<html:html locale="true">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><bean:message key="jobs.label.title.monitorschedulers" bundle="jobs"/></title>


<style type="text/css">

 a:active {
    outline: none;
}
a:focus {
    -moz-outline-style: none;
}
#tabs_container {
    width: 900px;
    font-family: Tahoma;
    font-size: 12px;
    font-weight: bold;
    color:#000000;
}

#tabs_container1 {
    width: 900px;
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
<script type="text/javascript">

    function startJob(jbName){
        document.getElementById('operation').value='processJobStart';
        document.startStopJobForm.jobName.value=jbName;
        document.startStopJobForm.submit();
    }


    function stopJob(jbName){
        document.getElementById('operation').value='processJobStop';
        document.startStopJobForm.jobName.value=jbName;
        document.startStopJobForm.submit();
    }

    function disableJob(jbName){
        document.getElementById('operation').value='processDisableJob';
        document.startStopJobForm.jobName.value=jbName;
        document.startStopJobForm.submit();
    }

    function enableJob(jbName){
        document.getElementById('operation').value='processEnableJob';
        document.startStopJobForm.jobName.value=jbName;
        document.startStopJobForm.submit();
    }

    
    function refreshJobs(){
        document.getElementById('operation').value='refresh';
        document.startStopJobForm.submit();
    }
    
    function back(){
        document.getElementById('operation').value='back';
        document.startStopJobForm.submit();
    }
    
     function hover(obj){
        obj.className='class_hover';
    }

    function unHover(obj, row_num){      
            obj.className = 'class_no_hover_job';

    }


 function submitToHome() 
 {
        document.forms[0].action = "<%=ServerUtils.getContextName(request)%>/";
        document.forms[0].submit();
 }
 


    </script>
    
    
</head>

<body class="tundra bodybg" style="overflow-x:hidden;overflow-y:auto">
<html:form action="startStopAction.do"  >
<table width="100%" cellspacing="0" cellpadding="0" align="center"  border="0" >
	<tr>
		<td  class="pageTitle paddingTitle">      
		 <bean:message key="jobs.manageJobs" bundle="jobs"/>
		</td>
	</tr>
	<tr>
	   <td class="paddingTitleDesc">
		 <strong><bean:message key="jobs.manageJobs.description" bundle="jobs"/></strong>
		</td>  
	</tr>
	<tr>
		<td style="padding-left:280px; width:500px;">
		   <label class="success"><b>
			
			</b></label> 
		</td>  
	</tr>
	<tr>
		<td style="padding-top:30px;padding-left:67px;width:900px">
			<table width="900px" cellspacing="0" cellpadding="0" style="align:left;" >
				<tr>
					<td>
						<div id="tabs_container" style="align:left;">
							<table width="900px" cellpadding="0" cellspacing="0" border="0">
								<tr>
									<td style="align:left;" valign="middle">
										<ul class="tabs">
											<li class="tab panelColor">
												<a href="<%=ServerUtils.getContextName(request)%>/configureJobSchdAction.do?operation=view"  style="color:#000000"><b><bean:message key="jobs.configureSchedulers" bundle="jobs"/> </b></a>          
											</li>
											<li class="active" >
												<a href="#" onclick="refreshJobs()"><b><bean:message key="jobs.monitorSchedulers" bundle="jobs"/></b></a>
											</li>
										</ul>      
									</td>   
								</tr>
							</table>
						</div>  
					</td>
				</tr>   
			</table>
			<div class="clear"></div>
			<div class="tab_contents_container" style="background:#ffffff;">
			<table border="0" width="100%"   height="54" cellspacing="0" cellpadding="0" >
				<tr>
					<td>
						<table border="1" cellspacing="0" cellpadding="0"  width="100%" class="bgcolorReports ">
							<tr>
								<td  class="tab panelColor" style="width: 25px"align="left"  valign="middle">&nbsp;&nbsp;&nbsp;<label class="label"><bean:message key="jobs.manageJobsName" bundle="jobs"/></label></td>
								<td class="tab panelColor" height="27" align="left" style="width: 25px"  valign="middle">&nbsp;&nbsp;&nbsp;<label class="label"><bean:message key="jobs.manageJobsStatus" bundle="jobs"/></label></td>
								<td class="tab panelColor" height="27" align="left" style="width: 95px" valign="middle" >&nbsp;&nbsp;&nbsp;<label class="label"><bean:message key="jobs.lastRuntime" bundle="jobs"/></label></td>
								<td class="tab panelColor" height="27" align="left" style="width: 96px" valign="middle" >&nbsp;&nbsp;&nbsp;<label class="label"><bean:message key="jobs.nextRuntime" bundle="jobs"/></label></td>
								<td class="tab panelColor" height="27" align="left" style="width: 100px" valign="middle" >&nbsp;&nbsp;&nbsp;<label class="label"><bean:message key="jobs.actionLabel" bundle="jobs"/></label></td>
							</tr>
	<!--  displaying the inprocess jobs.. -->
					<logic:present scope="request" name="inProcessJobs">
							<tr class="bgcolor">
								<td height="27" colspan="5" align="left" style="width: 100px" colspan="1"><br></td>
							</tr>
							<tr>
								<td height="27" colspan="5" align="left" style="width: 140px" colspan="1" class="panelColor" nowrap="nowrap" >&nbsp;&nbsp;<label class="label">  <b><bean:message key="jobs.jobsInProcess" bundle="jobs"/></b> </label></td>
							</tr>
			
						<%! int p=0; %>
						<%try{%>
						<logic:iterate property="inProcessJobs" id="jobs" name="startStopJobForm" type="JobMonitorBean">
							<tr class="tableBgColor" onMouseOver="hover(this);" onMouseOut="unHover(this, 1);">
								<td height="27" align="left" style="width: 150px;word-wrap: break-word" >&nbsp;&nbsp;&nbsp;
									<label class="body">&nbsp;&nbsp;&nbsp; <bean:write name="jobs" property="jobName"/></label></td>
								<td height="27" align="center" style="width: 75px">&nbsp;&nbsp;&nbsp;<img src="<%=ServerUtils.getContextName(request)%>/images/portal/icon_tick.png" width="16" height="16"/></td>
								<td height="27" align="left" style="width: 250px">
									<label class="body">&nbsp;&nbsp;&nbsp; <bean:write name="jobs" property="currentExecutionTime" /></label>
								</td>
								<td height="27" align="left" style="width: 250px">
									<label class="body">&nbsp;&nbsp;&nbsp; <bean:write name="jobs" property="nextExecutionTime" /></label>
								</td>      
								<td height="27" align="Center" style="width: 150px">&nbsp; 
								</td>                                                
							</tr>
						<%
							jobsCount = 1; i++;
						%>
						</logic:iterate>     
						<%}catch(Exception ex){%>
							<tr class="tableBgColor" onMouseOver="hover(this);" onMouseOut="unHover(this, 1);">
								<td height="27" align="left" style="width: 150px" colspan="7">&nbsp;&nbsp;&nbsp;
									<label class="body"> <bean:message key="jobs.err.unexpectederrmsg" bundle="jobs"/>
									</label>
								</td>
							</tr>
						<%}%>
					</logic:present>
					 
	<!--  displaying the running jobs.. -->       
					<logic:present scope="request" name="runningJobs">
							<tr class="bgcolor">
								<td height="27" colspan="5" align="left" style="width: 100px" colspan="1"><br></td>
							</tr>
							<tr>
								<td height="27" colspan="5" align="left" style="width: 100px" colspan="1" class="panelColor"  ><label class="label">  <b> &nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="jobs.jobsRunning" bundle="jobs"/></b> </label></td>
							</tr>
						<%! int i=0; %>
						<logic:iterate property="runningJobs" id="jobs" name="startStopJobForm" type="JobMonitorBean">
							<tr class="tableBgColor" onMouseOver="hover(this);" onMouseOut="unHover(this, 1);">
								<td height="27" align="left" style="width: 150px;word-wrap: break-word" >&nbsp;&nbsp;&nbsp;
									<label class="body">&nbsp;&nbsp;&nbsp; <bean:write name="jobs" property="jobName"/></label></td>
								<td height="27" align="center" style="width: 75px">&nbsp;&nbsp;&nbsp;<img src="<%=ServerUtils.getContextName(request)%>/images/portal/icon_tick.png" width="16" height="16"/></td>
								<td height="27" align="left" style="width: 250px">
									<label class="body">&nbsp;&nbsp;&nbsp; <bean:write name="jobs" property="currentExecutionTime" /></label>
								</td>
								<td height="27" align="left" style="width: 250px">
									<label class="body">&nbsp;&nbsp;&nbsp;<bean:write name="jobs" property="nextExecutionTime" /></label>
								</td>      
								<td height="27" align="center" >
									<button dojoType="dijit.form.Button"  type="button"  id="idStop<%=i%>" name="idStop<%=i%>"  onClick="stopJob('<bean:write name="jobs" property="jobName"/>'); dijit.byId('idStop<%=i%>').setAttribute('disabled',true);">
									<bean:message key="jobs.status.label.stop" bundle="jobs"/>
									</button>
									<button dojoType="dijit.form.Button"  type="button"  id="idStart<%=i%>" name="idStart<%=i%>" onClick="disableJob('<bean:write name="jobs" property="jobName"/>'); dijit.byId('idStart<%=i%>').setAttribute('disabled',true);">
									<bean:message key="jobs.status.label.disable" bundle="jobs"/>
									</button>   
								</td>                                                
							</tr>
						<%
							jobsCount = 1; i++;
						%>
						</logic:iterate>               
					</logic:present>

	<!--  displaying the misfired jobs.. -->
					<logic:present scope="request" name="misFiredJobs">
							<tr class="bgcolor">
								<td height="27" colspan="5" align="left" style="width: 100px" colspan="1"><br></td>
							</tr>
							<tr>
								<td height="27" colspan="5" align="left" style="width: 100px" colspan="1" class="panelColor" >&nbsp;&nbsp;&nbsp;<label class="label"> <b> <bean:message key="jobs.jobsMisfired" bundle="jobs"/></b></label></td>
							</tr>    
						<%! int k=0;%>
						<logic:iterate property="misFiredJobs" id="jobs" name="startStopJobForm" type="JobMonitorBean">
							<tr class="tableBgColor" onMouseOver="hover(this);" onMouseOut="unHover(this, 1);">
								<td height="27" align="left" style="width: 150px;word-wrap: break-word" >&nbsp;&nbsp;&nbsp;
									<label class="body"><bean:write name="jobs" property="jobName"/></label></td>
								<td height="27" align="center" style="width: 75px">&nbsp;&nbsp;&nbsp;<img src="<%=ServerUtils.getContextName(request)%>/images/portal/icon_error.png" width="16" height="16"/></td>
								<td height="27" align="left" style="width: 250px">
									<label class="body">&nbsp;&nbsp;&nbsp; <bean:write name="jobs" property="currentExecutionTime" /></label>
								</td>
								<td height="27" align="left" style="width: 250px;word-wrap: break-word">&nbsp;&nbsp;
									<label class="body" > 
									<div dojoType="dijit.form.DropDownButton">
										<span><label class="body"> <bean:message key="jobs.status.label.jobmisfired" bundle="jobs"/></label></span>
									<div dojoType="dijit.TooltipDialog" id="tooltipDlg<%=k%>" >
										<table width="50%">
											<tr>
												<td><label class="body"> <bean:write name="jobs" property="failedReason" /></label></td>
											</tr>
										</table>
									</div>
									</div>
								</td>
								<td height="27" align="center" >
									<button dojoType="dijit.form.Button"  type="button"  id="missstoptId<%=k%>" name="missstopId<%=k%>" onClick="stopJob('<bean:write name="jobs" property="jobName"/>'); dijit.byId('missstoptId<%=k%>').setAttribute('disabled',true);">
									<bean:message key="jobs.status.label.stop" bundle="jobs"/>
									</button>
									<button dojoType="dijit.form.Button"  type="button"  id="idMissStart<%=k%>" name="idMissStart<%=k%>" onClick="disableJob('<bean:write name="jobs" property="jobName"/>'); dijit.byId('idMissStart<%=k%>').setAttribute('disabled',true);">
									<bean:message key="jobs.status.label.disable" bundle="jobs"/>
									</button>   
								</td>                                                
							</tr>
						<%
							jobsCount = 1; k++;
						%>
						</logic:iterate>             
					</logic:present>     
			  
	<!--  displaying the stopped jobs.. -->       
					<logic:present scope="request" name="stoppedJobs">
							<tr class="bgcolor">
								<td height="27" colspan='5' align="left" style="width: 100px" colspan="1"><br></td>
							</tr>
							<tr>
								<td height="27" colspan="5" class="panelColor" >&nbsp;&nbsp;&nbsp;<label class="label">  <b><bean:message key="jobs.jobsStopped" bundle="jobs"/></b> </label>
								</td>
							</tr>
						<%! int j=0;%>
						<logic:iterate property="stoppedJobs" id="jobs" name="startStopJobForm" type="JobMonitorBean">
							<tr class="tableBgColor" onMouseOver="hover(this);" onMouseOut="unHover(this, 1);">
								<td height="27" align="left" style="width: 150px;word-wrap: break-word" > &nbsp;&nbsp;&nbsp;
								<span><label class="body"><bean:write name="jobs" property="jobName"/>
							 	</label><p></span></td>
								<td height="27" align="center" style="width: 75px">&nbsp;&nbsp;&nbsp;<img src="<%=ServerUtils.getContextName(request)%>/images/portal/icon_warning.png" width="16" height="16"/></td>
								<td height="27" align="left" style="width: 250px">
									<label class="body">&nbsp;&nbsp;&nbsp; <bean:write name="jobs" property="prevExecutionTime" /></label>
								</td>
								<td height="27" align="left" style="width: 250px">&nbsp;&nbsp;&nbsp;
									<label class="body">  <bean:message key="jobs.notScheduled" bundle="jobs"/></label>
								</td>
								<td height="27" align="Center" >
									<button dojoType="dijit.form.Button"  type="button"  id="startId<%=j%>" name="startId<%=j%>"   onClick="startJob('<bean:write name="jobs" property="jobName"/>'); dijit.byId('startId<%=j%>').setAttribute('disabled',true);">
									<bean:message key="jobs.start" bundle="jobs"/>
									</button>       
									<button dojoType="dijit.form.Button"  type="button"  id="idstopStart<%=j%>" name="idstopStart<%=j%>" onClick="disableJob('<bean:write name="jobs" property="jobName"/>'); dijit.byId('idstopStart<%=j%>').setAttribute('disabled',true);">
									<bean:message key="jobs.disable" bundle="jobs"/>
									</button>   
								</td>                                                
							</tr>
						<%
							jobsCount = 1; j++;
						%>
						</logic:iterate>             
					</logic:present>
			  
	<!--  displaying the disabled jobs.. -->
					<logic:present scope="request" name="disabledJobs">
						<%! int l=0;%>
							<tr class="bgcolor">
								<td height="27" colspan='5' align="left" style="width: 100px" colspan="1"><br></td>
							</tr>
							<tr>
								<td height="27" colspan='5' align="left" style="width: 100px" colspan="1" class="panelColor" >&nbsp;&nbsp;&nbsp;
									<label class="label">  <b><bean:message key="jobs.jobsDisabled" bundle="jobs"/> </b></label>
								</td>
							</tr>
						<logic:iterate property="disabledJobs" id="jobs" name="startStopJobForm" type="JobMonitorBean">
							<tr class="tableBgColor" onMouseOver="hover(this);" onMouseOut="unHover(this, 1);">
								<td height="27" align="left" style="width: 150px;word-wrap: break-word" >&nbsp;&nbsp;&nbsp;
									<label class="body"><bean:write name="jobs" property="jobName"/></label></td>
								<td height="27" align="center" style="width: 75px">&nbsp;&nbsp;&nbsp;<img src="<%=ServerUtils.getContextName(request)%>/images/portal/icon_error.png" width="16" height="16"/></td>
								<td height="27" align="left" style="width: 250px">&nbsp;&nbsp;&nbsp;
									<label class="body"> <bean:write name="jobs" property="currentExecutionTime" /></label>
								</td>
								<td height="27" align="left" style="width: 250px">&nbsp;&nbsp;&nbsp;
									<label class="body">  <bean:write name="jobs" property="nextExecutionTime" /> </label>
								</td>
								<td height="27" align="center" >
									<button dojoType="dijit.form.Button"  type="button"  id="idDisabledStart<%=l%>" name="idDisabledStart<%=l%>" onClick="enableJob('<bean:write name="jobs" property="jobName"/>'); dijit.byId('idDisabledStart<%=l%>').setAttribute('disabled',true);">
									<bean:message key="jobs.enable" bundle="jobs"/>
									</button>   
								</td>                                                
							</tr>
						<%
							jobsCount = 1; l++;
						%>
						</logic:iterate>             
					</logic:present>
                        </table>
                    </td>
                </tr>
            </table>
            <table  width="900">
                <tr>
					<td colspan='5' align='center'>
						<%
						if(jobsCount==0){
						%>
							<bean:message key="jobs.noJobsFound" bundle="jobs"/>
						<%
						}
						%>
					</td>
				</tr>
			</table> 
            </div>
        </td>
	</tr>
</table>
    <input type="hidden" name="operation" id="operation" value="onLoad" />
    <input type="hidden" name="jobName" id="jobName" value="" />
  
<!-- Button Panel -->
<table width="101.2%"  border="0">
    <tr align="center" class="barColor">
		<td  align="center">
            <button dojoType="dijit.form.Button"  type="button"  id="btnBack" name="btnBack" onClick="window.location= '<%=ServerUtils.getContextName(request)%>/adminMain.do'">
                <bean:message key="jobs.home" bundle="jobs"/>
            </button>
            <button dojoType="dijit.form.Button"  type="button"  id="refresh" name="refresh"  onClick="refreshJobs()">
                <bean:message key="jobs.refreshJobs" bundle="jobs"/>
			</button>
        </td>         
    </tr>
</table>
</html:form>        
</body>
</html:html>