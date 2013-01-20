<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@page import="com.enterprisehorizons.magma.server.util.ServerUtils"%>
<%@ include file="/common/dojo.jsp"%>
<html:html locale="true">
<head>


<script>
var tr_selected = '';
function hover(obj){
	obj.className='class_hover';
}

function unHover(obj, row_num){
	if (tr_selected != row_num) obj.className = 'class_no_hover bgcolor';
}

function checkDates() {

	startDate 	= document.getElementById("startDate").value;
	endDate		= document.getElementById("endDate").value;
	
	if (new Date(startDate) > new Date(endDate)){
		showEmptyDialog("<bean:message key="rules.check.date" bundle="rules" />", "<bean:message key="rules.report.title" bundle="rules" />");
        return false;
    } 
    
	document.forms[0].submit();
}

</script>

</head>
<body class="tundra bodybg">
<form name="searchToGenerateNercReportForm" method="post" action="<%=ServerUtils.getContextName(request)%>/searchToGenerateNercReportAction.do?operation=searchNercReportGenerateData">
<!----  HIden Variables -->

<input type="hidden" name="operation" id="operation" value="searchNercReportGenerateData"/>

 <table width="101.4%" cellspacing="0" cellpadding="0"> 
    <tr>
        <td align="left">
            <table width="1000" cellpadding="0" cellspacing="0" align="left" id="mainTable">
                <tr>
                  <td  align="left" valign="top"  align="justify">
                      <table width="1000" cellspacing="0" cellpadding="0" border="0">
                        <tr>
                          <td width="67">&nbsp;</td>
                          <td width="938" height="37" align='center'> 
					
					</td>
                        </tr>
                        <tr>
                          <td>&nbsp;</td>
                          <td class="pageTitle"><strong><bean:message key="rules.violations.nerc" bundle="rules" /></td>
                        </tr>  
  						<tr>
						   <td class="paddingTitleDesc" colspan="2">
								<bean:message key="rules.searchToGenerateNercReportCaption.description" bundle="rules" />               
							</td>  
						</tr>
                        <tr>  
                          <td>&nbsp;</td>
                          <td colspan="2" class="error">
                                <html:errors bundle="ecoweb"/> 
                          </td>
                        </tr>

                        <tr>
                            <td>&nbsp;</td>
                            <td align="left" valign="top" class="redtitle">

 
<!-- 

************************************************************************************************

--> 

<table  id="myTable" class="bgcolorReports" cellSpacing="1" cellPadding="3" width="100%" align="left" border="0">
	<tbody>
	<tr>
      		<td colspan="5"> 
			<table>
				<tr>
					<td style="padding-top:7px"><strong><bean:message key="rules.violation.startdate" bundle="rules" /></strong></td>
					<td style="padding-top:7px">
						<input	name="startDate" type="text" id="startDate"  dojoType="dijit.form.DateTextBox" required="true" trim="true"	promptMessage="mm/dd/yyyy" onChange="dojo.byId('startDateDiv').innertHTML=arguments[0];" invalidMessage="Invalid date. Use mm/dd/yyyy format." class="dateClass" value='<bean:write name="searchToGenerateNercReportForm" property="startDate" />' />
						<div id="startDateDiv"  style="position:absolute;z-index:100;display:none; top:176px; left:600px; width:200; height:200; border:2px solid #EAEAEA;background-color:blue;opacity:1;">
						</div>							  
					</td>
					<td width="26%"></td>
					<td style="padding-top:7px"><strong><bean:message key="rules.violation.enddate" bundle="rules" /></strong></td>
					<td style="padding-top:7px">
						<input	name="endDate" type="text" id="endDate"  dojoType="dijit.form.DateTextBox" required="true" trim="true"	promptMessage="mm/dd/yyyy" onChange="dojo.byId('endDateDiv').innertHTML=arguments[0];" invalidMessage="Invalid date. Use mm/dd/yyyy format." class="dateClass" value='<bean:write name="searchToGenerateNercReportForm" property="endDate" />' />
						<div id="endDateDiv"  style="position:absolute;z-index:100;display:none; top:176px; left:600px; width:200; height:200; border:2px solid #EAEAEA;background-color:blue;opacity:1;">
						</div>
					</td>
					<td>
						 <button dojoType="dijit.form.Button" id="go" name="go" type="button"  onClick="checkDates();">
                             <bean:message key="admin.common.go" bundle="admin" />
                         </button>
					</td>
				</tr>
			</table>
		</td>
      </tr>
	<logic:present name="allNercReportDataList" scope="request">
		<tr class="subHeaddings">
			<td style="padding-left:10px">
				<bean:message key="rules.snoLabel" bundle="rules" />
			</td>
			<td style="padding-left:10px">
				<bean:message key="rules.incidentNameLabel" bundle="rules" />
			</td>
			<td style="padding-left:10px">
				<bean:message key="rules.violationNameLabel" bundle="rules" />
			</td>
			<td style="padding-left:10px">
				<bean:message key="rules.timeOfViolationLabel" bundle="rules" />
			</td>
			<td style="padding-left:10px">
				<bean:message key="rules.filingPersonName" bundle="rules" />
			</td>
			<td style="padding-left:10px">
				<bean:message key="rules.reportFiledOn" bundle="rules" />
			</td>
			<td style="padding-left:10px">
				<bean:message key="rules.generateReport" bundle="rules" />
			</td>
		</tr>
	<% int i = 1; %>
	<logic:iterate name="allNercReportDataList" scope="request" id="violationDtls">
		<tr class="bgcolor" onMouseOver="hover(this);" onMouseOut="unHover(this, 1);">
			<td class="textnormal12" style="padding-left:10px">
				<%=i++ %>
			</td>
			<td class="textnormal12" style="padding-left:10px">	
				<bean:write name="violationDtls" property="incidentName" />
			</td>
			<td class="textnormal12" style="padding-left:10px">
				<bean:write name="violationDtls" property="conditionName" />
			</td>
			<td class="textnormal12" style="padding-left:10px">
				<bean:write name="violationDtls" property="reportedOn"/>
			</td>
			<td class="textnormal12" style="padding-left:10px">
				<bean:write name="violationDtls" property="filingPersonName" />
			</td>
			<td class="textnormal12" style="padding-left:10px">
				<bean:write name="violationDtls" property="createdOn"/>
			</td>	
			<td class="textnormal12" style="padding-left:10px">
				 <a href="<%=ServerUtils.getContextName(request)%>/generateAndViewJasperReportServlet?reportType=EOP004-NERC&incidentName=<bean:write name="violationDtls" property="incidentName"/>&conditionName=<bean:write name="violationDtls" property="conditionName"/>&triggeredOn=<bean:write name="violationDtls" property="reportedOn"/>&recordId=<bean:write name="violationDtls" property="id"/>">
                            <img src="<%=ServerUtils.getContextName(request)%>/images/portal/icon_config.png" WIDTH=50 HEIGHT=30 ALT='Generate Report'/>
                        </a>
			</td>
		</tr>
	</logic:iterate>
	</logic:present>
	<logic:notPresent name="allNercReportDataList" scope="request">
		<tr>
			<td colspan="5" class="redtitle1" >
				<bean:message key="rules.noViolationsFound" bundle="rules" /> 
			</td>
		</tr>
		<tr> 
			<td colspan="5" class="redtitle1" >&nbsp;
				
			</td>
		</tr>
	</logic:notPresent>
		<tr>
			<td colspan="5" class="redtitle1" >&nbsp;
				
			</td>
		</tr>
	</tbody>
</table>


<!-- 

************************************************************************************************END

-->

                            </td>
                        </tr>
                                    
                    </table>
                </td>
                </tr>
            </table>
        </td>
    </tr>
                <tr>
                    <td colspan="5" class="barColor" align="right" width="1000px">
                       	<button dojoType="dijit.form.Button" class="register"  type="button"  onclick="window.location =  '<%=ServerUtils.getContextName(request)%>/RulesHome.do?operation=loadRulesHome'"> <bean:message key="dbconfig.home"/> </button>
						<button dojoType="dijit.form.Button"  type="button"  onclick="window.location =  '<%=ServerUtils.getContextName(request)%>/complianceReportsHome.do'  ">
                            <bean:message key="datasource.back" bundle="ds" />
                        </button>
                        
                        </td>
    </tr>
</table>
</form>
</body>
</html:html>
 