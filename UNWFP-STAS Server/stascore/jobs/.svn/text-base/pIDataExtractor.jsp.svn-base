<%@ page import="com.spacetimeinsight.stas.config.GenericConfigurationManager,com.enterprisehorizons.magma.config.dbadmin.ModelConfigConstants" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ page import="java.util.*,com.enterprisehorizons.util.StringUtils, com.spacetimeinsight.magma.job.JobConstants,
com.enterprisehorizons.magma.job.bd.JobScheduleDelegator,com.spacetimeinsight.magma.job.bean.StiJobBean,
com.spacetimeinsight.stas.datasource.GenericDatasourceManager,com.spacetimeinsight.stas.datasource.IDatasourceConstants,
com.enterprisehorizons.magma.jobs.forms.PIDataExtractorForm,com.spacetimeinsight.db.scheduler.model.ServerEnvMaster,com.spacetimeinsight.db.model.util.DataModelsCache, com.spacetimeinsight.db.scheduler.model.*"%>
<%@page import="com.enterprisehorizons.magma.server.util.ServerUtils"%>

<%@ taglib prefix="tiles" uri="/tags/struts-tiles"%>
<%@ include file="/common/dojo.jsp" %>
<script type="text/javascript" src="js/jobs.js"></script>
<%
boolean  showCheckBox = false;
List <ServerEnvMaster> allServerEnvList = (List) DataModelsCache.getInstance().retrieve(ServerEnvMaster.class.getName());
if(allServerEnvList != null && allServerEnvList.size() > 1)
showCheckBox = true;

String checkboxstatusStr = (String)request.getAttribute("checkboxstatus");
boolean  checkboxstatus = false;

if(checkboxstatusStr != null && "true".equals(checkboxstatusStr))
checkboxstatus = true;

boolean  readOnly = false;
String tmp = (String)request.getAttribute("mode");
if(tmp != null && (tmp.equals("readonly") || tmp.equals("delete")))
    readOnly = true;


	boolean  checkBoxFlag = false;
	PIDataExtractorForm formObj = (PIDataExtractorForm)request.getAttribute("pIDataExtractorForm");

if(!checkboxstatus && StringUtils.isNull(formObj.getCommonJobFlag()) || (tmp != null && (tmp.equals("readonly")) ))
    checkboxstatus = true;




List  allWeekTypeMasterList = (List) DataModelsCache.getInstance().retrieve(StiTriggerWeekTypeMaster.class.getName());

if(allWeekTypeMasterList == null){
	StiTriggerWeekTypeMaster weekMasterDtls = new StiTriggerWeekTypeMaster();
	allWeekTypeMasterList = weekMasterDtls.retrieveAll();
}
pageContext.setAttribute("allWeekTypeMasterList", allWeekTypeMasterList);

List  allDayTypeMasterList = (List) DataModelsCache.getInstance().retrieve(StiTriggerDayTypeMaster.class.getName());

if(allDayTypeMasterList == null){
	StiTriggerDayTypeMaster dayMasterDtls = new StiTriggerDayTypeMaster();
	allDayTypeMasterList = dayMasterDtls.retrieveAll();
}
pageContext.setAttribute("allDayTypeMasterList", allDayTypeMasterList);

List  allMonthTypeMasterList = (List) DataModelsCache.getInstance().retrieve(StiTriggerMonthTypeMaster.class.getName());

pageContext.setAttribute("allMonthTypeMasterList", allMonthTypeMasterList);
%>
<html:html locale="true">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>View / Update Job</title>
<!---------------------------------------------------------------------------------------------------------------------------------------------------------------------------- -->
<%
    com.spacetimeinsight.db.config.model.Datasource[] datasourceArray =  GenericDatasourceManager
                   .getInstance().getDatasourceNames(IDatasourceConstants.DATABASE_TYPES_PI);
%>
<!---------------------------------------------------------------------------------------------------------------------------------------------------------------------------- -->


    <script>
// JOB Constants
var JOB_TYPE_ECOSYSTEM = "<%= JobConstants.JOB_TYPE_ECOSYSTEM+"" %>";
var JOB_TYPE_FILECLEANUP = "<%= JobConstants.JOB_TYPE_FILECLEANUP %>";
var JOB_TYPE_PIDATAEXTRACTOR = "<%= JobConstants.JOB_TYPE_PIDATAEXTRACTOR %>";
var JOB_TYPE_URLEXTRACTOR = "<%= JobConstants.JOB_TYPE_URLEXTRACTOR %>";
var JOB_TYPE_CUSTOM = "<%= JobConstants.JOB_TYPE_CUSTOM %>";
var JOB_TYPE_CLEAR_CACHE = "<%= JobConstants.JOB_TYPE_CLEAR_CACHE %>";


var START_JOB_ABSOLUTE = "<%= JobConstants.START_JOB_ABSOLUTE+"" %>"; //1
var START_JOB_AFTER_SERVER_START = "<%= JobConstants.START_JOB_AFTER_SERVER_START+"" %>"; //2
var START_JOB_EXPRESSION = "<%= JobConstants.START_JOB_EXPRESSION+"" %>"; //3


var REPEAT_TRIGGER_IN_SECONDS = "<%= JobConstants.REPEAT_TRIGGER_IN_SECONDS +"" %>";
var REPEAT_TRIGGER_IN_MINUTES = "<%= JobConstants.REPEAT_TRIGGER_IN_MINUTES +"" %>";
var REPEAT_TRIGGER_IN_HOURS = "<%= JobConstants.REPEAT_TRIGGER_IN_HOURS +"" %>";
var REPEAT_TRIGGER_IN_DAILY = "<%= JobConstants.REPEAT_TRIGGER_IN_DAILY +"" %>";
var REPEAT_TRIGGER_IN_WEEKLY = "<%= JobConstants.REPEAT_TRIGGER_IN_WEEKLY +"" %>";
var REPEAT_TRIGGER_IN_MONTHLY = "<%= JobConstants.REPEAT_TRIGGER_IN_MONTHLY +"" %>";
var REPEAT_TRIGGER_IN_YEARLY= "<%= JobConstants.REPEAT_TRIGGER_IN_YEARLY +"" %>";
var REPEAT_TRIGGER_IN_EXECUTEONCE = "<%= JobConstants.REPEAT_TRIGGER_IN_EXECUTEONCE +"" %>";

function showConcernDiv(){
	if(dijit.byId('startTimeType').value == START_JOB_ABSOLUTE){
		document.getElementById('afterServerStartDivDtls').style.display = 'none';
		document.getElementById('expressionDivDtls').style.display = 'none';
		document.getElementById('absoluteDivDtls').style.display = '';
		resetSchedulerFormValues(START_JOB_ABSOLUTE);
		document.getElementById('recurrenceLabelDtls').style.display = '';
	}else if(dijit.byId('startTimeType').value == START_JOB_AFTER_SERVER_START){
		document.getElementById('afterServerStartDivDtls').style.display = '';
		document.getElementById('expressionDivDtls').style.display = 'none';
		document.getElementById('absoluteDivDtls').style.display = 'none';
		resetSchedulerFormValues(START_JOB_AFTER_SERVER_START);
		document.getElementById('recurrenceLabelDtls').style.display = '';
	}else if(dijit.byId('startTimeType').value == START_JOB_EXPRESSION){
		document.getElementById('afterServerStartDivDtls').style.display = 'none';
		document.getElementById('expressionDivDtls').style.display = '';
		document.getElementById('absoluteDivDtls').style.display = 'none';
		resetSchedulerFormValues(START_JOB_EXPRESSION);
		document.getElementById('recurrenceLabelDtls').style.display = '';
	}else{
		document.getElementById('afterServerStartDivDtls').style.display = 'none';
		document.getElementById('expressionDivDtls').style.display = 'none';
		document.getElementById('absoluteDivDtls').style.display = 'none';
		resetSchedulerFormValues('');
		document.getElementById('recurrenceLabelDtls').style.display = 'none';
	}
}

function updateDIVStyles(status){
	document.getElementById('afterServerStartDivDtls').style.display = '';
	document.getElementById('expressionDivDtls').style.display = '';
	document.getElementById('absoluteDivDtls').style.display = '';
}

function resetSchedulerFormValues(typeOfStartType){
	if(typeOfStartType == START_JOB_AFTER_SERVER_START){
		dijit.byId('startDate').setAttribute('disabled', true);
		dijit.byId('startTime').setAttribute('disabled', false);
	}else if(typeOfStartType == START_JOB_ABSOLUTE){
		resetTimestampComponent(false);
	}else if(typeOfStartType == START_JOB_EXPRESSION){
		resetTimestampComponent(false);
	}else{
		resetTimestampComponent(false);
	}

}


function resetTimestampComponent(status){
	dijit.byId('startDate').setAttribute('disabled', status);
	dijit.byId('startTime').setAttribute('disabled', status);
}


    var addedURLRowsNumbers = "";
      function submitForm(btn) {
            //document.forms[0].submitName.value=btn.value;
            //document.forms[0].submitName.value=btn.name;
            var isValidForSubmit;
            if(btn.value == dijit.byId('idBack').value){
                document.forms[0].action = "configureJobSchdAction.do?operation=view";
                document.forms[0].submit();
            }else if(btn.value == dijit.byId('idUpdate').value){
                if(!chkForSpecialChars())
                {
                    return false;
                }
                if(!validateCommonFields())
                    return false;
                isValidForSubmit = validatePIDataExtractor();
                if(!isValidForSubmit)
                    return false;

				if(checkIntervalValue()){
					return false;
				}

               dijit.byId('jobName').setAttribute('disabled', false);
				dijit.byId('startTimeType').setAttribute('disabled', false);

                if(!performSubmit()) {
					dijit.byId('jobName').setAttribute('disabled', true);
				    dijit.byId('startTimeType').setAttribute('disabled', true);
				}
            }else{
               if(!performSubmit()) {
                  dijit.byId('jobName').setAttribute('disabled', true);
				dijit.byId('startTimeType').setAttribute('disabled', true);
				 }
            }
        }


function checkIntervalValue(){
	return isFraction(dijit.byId('interval').value,"Interval must be integer");
}



    </script>
</head>
<body onLoad="" class="tundra bodybg">

<html:form action="/pIDATAExtractorAction.do?operation=onUpdatePIDB"  >
<table width="100%"><tr><td width="100%" border="0">
<table width="100%" cellspacing="0" cellpadding="0" align="center" border="0" >
          <tr>
                <td  class="pageTitle paddingTitle">
			  <% if (!readOnly) { %>
                     <bean:message key="jobs.updateHeader" bundle="jobs"/>
					   </td>
                            </tr>
                            <tr>
                       <td class="paddingTitleDesc">
                        <bean:message key="jobs.updatesubHeader" bundle="jobs"/>


                    <%} else if (tmp == "delete") { %>
                         <bean:message key="jobs.deleteHeader" bundle="jobs"/>
					   </td>
                            </tr>
                            <tr>
                       <td class="paddingTitleDesc">
                        <bean:message key="jobs.deletesubHeader" bundle="jobs"/>

					<% } else { %>
 <bean:message key="jobs.viewHeader" bundle="jobs"/>
					   </td>
                            </tr>
                            <tr>
                       <td class="paddingTitleDesc">
                        <bean:message key="jobs.viewsubHeader" bundle="jobs"/>


					<%}%>
                </td>
            </tr>
          <tr>
                 <td style="padding-left:67px; width:800px;">
                                   <font color="blue" style="font-family: Tahoma;font-size: 12px;" ><b>
                                    <html:messages id="saveStatus" message="true" bundle="jobs"><bean:write name="saveStatus" /></html:messages>
                                    </b></font>
                                    <font color="red" style="font-family: Tahoma;font-size: 12px;"><b><html:errors bundle="jobs"/></b></font>
                </td>
            </tr>
      <tr height="30px">
                 <td>&nbsp;</td>
            </tr>
            <tr>
                <td>
<!-- ##################################             -->
<table cellspacing="0" cellpadding="0" width="100%" border="0">
<html:hidden  property="id" name="pIDataExtractorForm"  />
<tr>
 <td style="padding-left:36px;align:left;">
	<table cellspacing="0" cellpadding="0" border="0" align="left">
		<tr>
			<td>
			<table cellpadding="10" cellspacing="" border="0" width="100%">
				<tr>
					<td height="27" align="right"  style="width:90px;padding-left:12px;" nowrap="nowrap" >
						<label class="label"><bean:message key="jobs.jobName" bundle="jobs"/></label><label class="error">*</label><b>:</b>
					</td>
					<td style="padding-left:10px;align:left;" width="90px">
						<input type="text"  id="jobName" name="jobName"  class="medium1" style="height:1.7em;" maxlength="45"
							dojoType="dijit.form.ValidationTextBox" required="true" trim="true" ucfirst="true"  value="<bean:write name="pIDataExtractorForm" property="jobName" />" />
					</td>
				</tr>
				<tr>
					<td style="width:90px;" >&nbsp;</td>
					<td style="padding-left:10px; align:left;" width="90px">
						<b><span id="result"></b></span>
					</td>
                </tr>
			</table>
			</td>
			<td style="width:30px;">

			</td>
			<td align="center">
			<table cellpadding="10" cellspacing="0" border="0">
				<tr>
					<td style="padding-left:10px">
						<table cellpadding="10" cellspacing="0" border="0" width="100%">
							<tr>
								<td height="27" align="right"  style="width:90px;" valign="middle" >
									<label class="label"><bean:message key="jobs.startType" bundle="jobs"/></label><label class="error">*</label><b>:</b>
								</td>
								<td style="padding-left:10px;align:left;valign:middle;" style="width:90px;" valign="middle">
									<select id="startTimeType" dojoType="dijit.form.FilteringSelect"  name="startTimeType" autoComplete="false" onChange="showConcernDiv()" >
										<option value=""><bean:message key="validation.msg.select" bundle="splchvalidation"/></option>
										<logic:present name="allStartTimesList">
											<logic:iterate name="allStartTimesList" id="allStartTimesDtls">
												<option value="<bean:write name="allStartTimesDtls" property="key"/>">
													<bean:message  bundle="jobs" name="allStartTimesDtls" property="value" />
												</option>
											</logic:iterate>
                                        </logic:present>
									</select>
								</td>
							</tr>
							<tr>
								<td>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</td>
</tr>
</table>
</td>
</tr>

<tr>
	<td height="13" colspan="8" align="left" class="bodytext"></td>
</tr>

<tr>
<td style="padding-left:36px;align:left;">
	<table cellspacing="0" cellpadding="0" border="0" align="left">
		<tr>
			<td>
			<table cellpadding="10" cellspacing="" border="0" width="100%">
				<tr>
					<td height="27" align="right"  style="width:90px;padding-left:12px;" nowrap="nowrap" >
						 <label class="label"><bean:message key="jobs.description" bundle="jobs"/></label><b>:</b>
					</td>
					<td style="padding-left:10px;align:left;" width="90px">
						<textarea dojoType="dijit.form.SimpleTextarea" rows="5" cols="30" id="description" class="medium1" style="height:5.7em;" name="description"  ><bean:write name="pIDataExtractorForm" property="description" /></textarea>
					</td>
				</tr>
				<tr>
					<td style="width:90px;" >&nbsp;</td>
					<td style="padding-left:10px; align:left;" width="90px">

					</td>
                </tr>
			</table>
			</td>
			<td style="width:30px;">

			</td>
			<td align="center" style="padding-left:25px">
			<table cellpadding="10" cellspacing="0" border="0">
				<tr>
					<td>
						<table cellpadding="10" cellspacing="0" border="0" width="100%">
							<tr>
								<td  align="right" >
									 <label class="label"><bean:message key="jobs.startDate" bundle="jobs"/><span id="startDateMand">:</label>
								</td>
								<td style="padding-left:10px;align:left;valign:middle;" style="width:90px;" valign="middle">
									<div id="dateDiv" >
									<input id="startDate" type="text" name="startDate" style="width:194px;height:1.7em;" dojoType="dijit.form.DateTextBox" trim="true"  promptMessage="mm/dd/yyyy" onChange="dojo.byId('calendarDiv').innertHTML=arguments[0];" invalidMessage="Invalid date. Use mm/dd/yyyy format." /><font size="1">&nbsp;<label><bean:message key="jobs.startDateFormat" bundle="jobs"/></label></font>
									</div>
									<div id="calendarDiv"  style="position:absolute;z-index:100;display:none; top:176px; left:600px; width:200; height:200; border:2px solid #EAEAEA;background-color:blue;opacity:1;">

									</div>
								</td>
							</tr>
							<tr height="13px">
								<td>
								</td>
							</tr>
							<tr>
								<td align="right">
									 <label class="label"><bean:message key="jobs.startTime" bundle="jobs"/><span id="startTimeMand"></span><label class="error">*</label><b>:</b>
								</td>
								<td style="padding-left:10px;align:left;valign:middle;" style="width:90px;" valign="middle">
									 <div id="startTimeDiv" style="width:194px;height:1.7em;">
										<input type="text"   id="startTime" name="startTime" class="medium1" style="height:1.7em;" dojoType="dijit.form.ValidationTextBox"   invalidMessage="Invalid time. Use HH:mm:ss where HH is 00 - 23 hours."/><font size="1">&nbsp;<label><bean:message key="jobs.startTimeFormat" bundle="jobs"/></label></font>
									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</td>
</tr>
</table>
</td>
</tr>

<tr>
	<td height="13px" style="padding-left:67px;">
	<table cellpadding="10" cellspacing="" border="0" width="300" >
				<tr>
					<td height="27" align="left"  colspan="2" >
						 <label class="label"><bean:message key="jobs.jobType" bundle="jobs"/></label><label class="error">*</label><b>:</b>
					</td>
					<td height="27" align="left"  colspan="2" >
						<select  id="jobType" dojoType="dijit.form.FilteringSelect"name="jobType" autoComplete="false"onchange="javascript:statusChange();" value="<bean:write name="pIDataExtractorForm" property="jobType" />">
							<option value="-1"><bean:message key="validation.msg.select" bundle="splchvalidation"/></option>
							<logic:present name="allJobsTypesList">
							<logic:iterate name="allJobsTypesList" id="allJobsTypesDtls">
							<option value="<bean:write name="allJobsTypesDtls" property="key"/>">
								<bean:message  bundle="jobs" name="allJobsTypesDtls" property="value" />
							</option>
							</logic:iterate>
							</logic:present>
						</select>
					</td>
					<td align="center">
						<%if(AdminConfigUtils.isClustered()&& showCheckBox){%>
						<table cellpadding="" cellspacing="0" border="0">
						<tr>
							<td height="27" align="right" style="width:120px;" nowrap="nowrap" valign="middle">
								<label class="label"><bean:message key="jobs.commonJob" bundle="jobs"/>:</label>&nbsp;
							</td>
							<td style="padding-left:10px;align:left;width:100px;">
								<html:checkbox  name="pIDataExtractorForm" property="commonJobFlag" onclick="checkBoxAlert(this);" disabled="<%=checkboxstatus%>"></html:checkbox>
							</td>
						</tr>
						</table>
						<%}%>
					</td>
				</tr>
	</table>
	</td>
</tr>




<tr>
<html:hidden  property="id" name="pIDataExtractorForm"  />
<td style="padding-left:65px;align:left;">
	<table cellspacing="0" cellpadding="0" border="0" align="left" width="780" >
		<tr>
			<td width="35%">
			<table cellpadding="10" cellspacing="" border="0" width="100%"  id="recurrenceLabelDtls" style="display:none">
				<tr>
					<td height="27" align="left"  colspan="2" >
						  <label><b><bean:message key="jobs.recur.recurranceLabel" bundle="jobs"/></b></label>
					</td>
				</tr>
			</table>
			</td>
			<td style="padding-left:60px">

			</td>

		</tr>
	</table>
</td>

</tr>

<!---   DIV Started for AFTER SERVER START JOB  -->
<tr id="afterServerStartDivDtls" style="display:none">
<td style="padding-left:65px;align:left;" >

    <table cellspacing="0" cellpadding="0" border="0" align="left" width="100%">
        <tr>
            <td>
            <table cellpadding="0"  border="0" width="367px">
                <tr>
                    <td height="27" align="left" width="100px">
                        <input type="radio" dojoType="dijit.form.RadioButton" name="afterServerStartsPattern" id="afterServerStartsPatternEvery" value="selectedAfterServerStartsEvery" onClick="resetAfterServerStartsFormValues()"/><label><b><bean:message key="jobs.recur.everyLabel" bundle="jobs"/></b></label>
                    </td>

                    <td>
                        <input type="text"   id="interval" name="interval" style="width:100px;height:1.7em" dojoType="dijit.form.ValidationTextBox" required="false" invalidMessage="Invalid Number" maxlength="10" onkeyup="return setFocus();"/>
                    </td>

                    <td>
                        <select  id="intervalType" dojoType="dijit.form.FilteringSelect"  name="intervalType" autoComplete="true" value='' onChange="return setFocus();">
                            <option value=''><bean:message key="validation.msg.select" bundle="splchvalidation"/></option>
                            <logic:present name="allIntervalsSimpleList">
                            <logic:iterate name="allIntervalsSimpleList" id="allIntervalsDtls">
                                <option value="<bean:write name="allIntervalsDtls" property="key"/>">
                                    <bean:message  bundle="jobs" name="allIntervalsDtls" property="value" />
                                </option>
                            </logic:iterate>
                            </logic:present>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="radio" dojoType="dijit.form.RadioButton" name="afterServerStartsPattern" id="afterServerStartsPatternOnce" value="selectedAfterServerStartsOnce" onClick="resetAfterServerStartsFormValues()"/><label><b><bean:message key="jobs.recur.onceLabel" bundle="jobs"/></b></label>
                    </td>

                </tr>
            </table>
            </td>

        </tr>
    </table>

</td>
</tr>
<script>
function resetAfterServerStartsFormValues(){
    if(dijit.byId('afterServerStartsPatternEvery').checked == true){
        resetDisableFlag(false);
    }else if(dijit.byId('afterServerStartsPatternOnce').checked == true){
        resetDisableFlag(true);
    }
}

function resetDisableFlag(status){
    if(status){
        dojo.byId('interval').value = '';
        dijit.byId('intervalType').setValue('');
    }
    dijit.byId('interval').setAttribute('disabled', status);
    dijit.byId('intervalType').setAttribute('disabled', status);
}
</script>

<!---   DIV Ended for AFTER SERVER START JOB  -->

<!---   DIV Started for EXPRESSION JOB  -->
<tr id="expressionDivDtls" style="display:none">
<td style="padding-left:65px;align:left;" >


    <table cellspacing="0" cellpadding="0" border="0" align="left" width="100%">
        <tr>
            <td>
            <table cellpadding="0"  border="0" width="20%">
                <tr>
                    <td>
                         <label class="label"><bean:message key="jobs.recur.expressionLabel" bundle="jobs"/></label>&nbsp;<label class="error">* </label><label class="label"><b>:</b></label>
                    </td>
                    <td>
                        <input type="text"   id="expression" name="expression" style="height:1.7em" dojoType="dijit.form.ValidationTextBox" required="false" trim="true"  />
                    </td>
                </tr>
            </table>
            </td>

        </tr>
    </table>

</td>
</tr>
<!---   DIV Ended for EXPRESSION JOB  -->

<!---   DIV Started for ABSOLUTE JOB  -->
<tr id="absoluteDivDtls" style="display:none">
<td style="padding-left:65px;align:left;" >

    <table cellspacing="0" cellpadding="0" border="0" align="left" width="100%">
        <tr>
            <td>
            <table cellpadding="0"  border="0" width="20%">
                <tr>
                    <td>
                        <select  id="cronTypeId" dojoType="dijit.form.FilteringSelect"  name="cronTypeId" autoComplete="true" onChange="resetAbsoluteFormValues()" value="">
                            <option value=''><bean:message key="validation.msg.select" bundle="splchvalidation"/></option>
                            <logic:present name="allIntervalsCronList">
                            <logic:iterate name="allIntervalsCronList" id="cronTriggerMasterDtls">
                                <option value="<bean:write name="cronTriggerMasterDtls" property="key"/>">
                                    <bean:message  bundle="jobs" name="cronTriggerMasterDtls" property="value"  />
                                </option>
                            </logic:iterate>
                            </logic:present>
                        </select>
                    </td>
                    <td width="13px">&nbsp;

                    </td>
                    <!--  SECONDS / MINUTES / HOURS  PATTERN -->
                    <td id="absoluteIntervalSMHDIVLabelDtls" style="display:none">
                        <label class="label"><b><bean:message key="jobs.recur.everyLabel" bundle="jobs"/></b></label>&nbsp;<label class="error">*</label><b>:</b>
                    </td>
                    <td id="absoluteIntervalSMHDIVComponentDtls" style="display:none">
                        <input type="text"  id="absoluteInterval" name="absoluteInterval" style="height:1.7em;" dojoType="dijit.form.ValidationTextBox" maxlength="10"/>
                    </td>
                    <!--  DAILY PATTERN -->
                    <td id="absoluteIntervalDailyDIVLabelDtls" style="display:none">
                        <table border="0" width="320px">
                            <tr>
                                <td width="70px">
                                    <input type="radio" dojoType="dijit.form.RadioButton" name="absoluteDailyPattern" id="absoluteDailyPatternEveryDay" value="selectedAbsoluteDailyPatternEveryDay" onClick="resetAbsoluteDailyPatternFormValues()" /><label><b><bean:message key="jobs.recur.everyLabel" bundle="jobs"/></b></label>
                                </td>
                                <td>
                                    <input type="text"  id="absoluteDailyInterval" name="absoluteDailyInterval" style="height:1.7em;width:100px;" dojoType="dijit.form.ValidationTextBox" maxlength="2" onkeyup="return setdailyFocus();"/> <label><b><bean:message key="jobs.recur.daysLabel" bundle="jobs"/></b></label>
                                </td>
                            </tr>

                            <tr>
                                <td colspan="2">
                                    <input onClick="resetAbsoluteDailyPatternFormValues()" type="radio" dojoType="dijit.form.RadioButton" name="absoluteDailyPattern" id="absoluteDailyPatternEveryWeekDay" value="selectedAbsoluteDailyPatternEveryWeekDay" /><label><b><bean:message key="jobs.recur.weekdaysLabel" bundle="jobs"/></b></label>
                                </td>
                            </tr>
                        </table>
                    </td>

                    <!--  WEEKLY PATTERN -->
                    <td id="absoluteIntervalWeeklyDIVLabelDtls" style="display:none">
                        <table border="0" width="320px">
                            <tr>
                                <td  colspan="2">
                                    <b><label><bean:message key="jobs.recur.recureveryweekonLabel" bundle="jobs"/>:</label></b>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <input type='checkbox'  dojotype='dijit.form.CheckBox' name="absoluteWeeklyPatternSunday" id="absoluteWeeklyPatternSunday" value="absoluteWeeklyPatternSunday" /><label><bean:message key="job.cron.day.type.sunday" bundle="jobs"/></label>
                                    <input type='checkbox'  dojotype='dijit.form.CheckBox' name="absoluteWeeklyPatternMonday" id="absoluteWeeklyPatternMonday" value="absoluteWeeklyPatternMonday" /><label><bean:message key="job.cron.day.type.monday" bundle="jobs"/></label>
                                    <input type='checkbox'  dojotype='dijit.form.CheckBox' name="absoluteWeeklyPatternTuesday" id="absoluteWeeklyPatternTuesday" value="absoluteWeeklyPatternTuesday" /><label><bean:message key="job.cron.day.type.tuesday" bundle="jobs"/></label>
                                    <input type='checkbox'  dojotype='dijit.form.CheckBox' name="absoluteWeeklyPatternWednesday" id="absoluteWeeklyPatternWednesday" value="absoluteWeeklyPatternWednesday" /><label><bean:message key="job.cron.day.type.wednesday" bundle="jobs"/></label>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <input type='checkbox'  dojotype='dijit.form.CheckBox' name="absoluteWeeklyPatternThursday" id="absoluteWeeklyPatternThursday" value="absoluteWeeklyPatternThursday" /><label><bean:message key="job.cron.day.type.thursday" bundle="jobs"/></label>
                                    <input type='checkbox'  dojotype='dijit.form.CheckBox' name="absoluteWeeklyPatternFriday" id="absoluteWeeklyPatternFriday" value="absoluteWeeklyPatternFriday" /><label><bean:message key="job.cron.day.type.friday" bundle="jobs"/></label>
                                    <input type='checkbox'  dojotype='dijit.form.CheckBox' name="absoluteWeeklyPatternSaturday" id="absoluteWeeklyPatternSaturday" value="absoluteWeeklyPatternSaturday" /><label><bean:message key="job.cron.day.type.saturday" bundle="jobs"/></label>
                                </td>
                            </tr>

                        </table>
                    </td>

                    <!--  MONTHLY PATTERN -->
                    <td id="absoluteIntervalMonthlyDIVLabelDtls" style="display:none">
                        <table border="0" width="720px">
                            <tr>
                                <td width="70px">
                                    <input type="radio" dojoType="dijit.form.RadioButton" name="absoluteMonthlyPattern" id="absoluteMonthlyPatternEveryDay" value="selectedAbsoluteMonthlyPatternEveryDay"  onClick="resetAbsoluteMonthlyPatternFormValues()" /><label><b><bean:message key="job.cron.day.type.day" bundle="jobs"/></b></label>
                                </td>
                                <td colspan="2" style="padding-left:5px">
                                    <input type="text"  id="absoluteMonthlyDayInterval" name="absoluteMonthlyDayInterval" style="height:1.7em;width:100px;" dojoType="dijit.form.ValidationTextBox" maxlength="2" onkeyup="return setmonthFocus();"/> <label><b><bean:message key="jobs.recur.ofeverymonthLabel" bundle="jobs"/></b></label>
                                </td>

                            </tr>

                            <tr>
                                <td colspan="2">
                                    <input type="radio" dojoType="dijit.form.RadioButton" name="absoluteMonthlyPattern" id="absoluteMonthlyPatternEveryMonth" value="selectedAbsoluteMonthlyPatternEveryMonth" onClick="resetAbsoluteMonthlyPatternFormValues()"/><label><b><bean:message key="jobs.recur.theLabel" bundle="jobs"/></b></label>
                                </td>
                                <td>
                                    <select  id="absoluteMonthlyWeekType" dojoType="dijit.form.FilteringSelect"  name="absoluteMonthlyWeekType" autoComplete="true" value=''  onChange="return setmonthFocus();">
                                        <option value=''><bean:message key="validation.msg.select" bundle="splchvalidation"/></option>
                                        <logic:present name="allWeekTypeMasterList">
                                        <logic:iterate name="allWeekTypeMasterList" id="allWeekDtls">
                                            <option value="<bean:write name="allWeekDtls" property="id"/>">
                                                <bean:message  bundle="jobs" name="allWeekDtls" property="type" />
                                            </option>
                                        </logic:iterate>
                                        </logic:present>
                                    </select>
                                </td>
                                <td>
                                    <select  id="absoluteMonthlyDayType" dojoType="dijit.form.FilteringSelect"  name="absoluteMonthlyDayType" autoComplete="true" value='' onChange="return setmonthFocus();">
                                        <option value=''><bean:message key="validation.msg.select" bundle="splchvalidation"/></option>
                                        <logic:present name="allDayTypeMasterList">
                                        <logic:iterate name="allDayTypeMasterList" id="allDayDtls">
                                            <option value="<bean:write name="allDayDtls" property="id"/>">
                                                <bean:message  bundle="jobs" name="allDayDtls" property="type" />
                                            </option>
                                        </logic:iterate>
                                        </logic:present>
                                    </select><label><b><bean:message key="jobs.recur.ofeverymonthLabel" bundle="jobs"/></b></label>
                                </td>

                            </tr>

                        </table>
                    </td>


                    <!--  YEARLY PATTERN -->
                    <td id="absoluteIntervalYearlyDIVLabelDtls" style="display:none">
                        <table border="0" width="720px">
                            <tr>
                                <td width="70px">
                                    <input type="radio" dojoType="dijit.form.RadioButton" name="absoluteYearlyPattern" id="absoluteYearlyPatternEveryMonth" value="selectedAbsoluteYearlyPattern" onClick="resetAbsoluteYearlyPatternFormValues()" /><label><b><bean:message key="jobs.recur.everyLabel" bundle="jobs"/></b></label>
                                </td>
                                <td colspan="2" style="padding-left:5px">
                                     <select  id="absoluteYearlyMonthType" dojoType="dijit.form.FilteringSelect"  name="absoluteYearlyMonthType" autoComplete="true" value='' onChange="return setyearFocus();">
                                        <option value=''><bean:message key="validation.msg.select" bundle="splchvalidation"/></option>
                                        <logic:present name="allMonthTypeMasterList">
                                        <logic:iterate name="allMonthTypeMasterList" id="allMonthDtls">
                                            <option value="<bean:write name="allMonthDtls" property="id"/>">
                                                <bean:message  bundle="jobs" name="allMonthDtls" property="type" />
                                            </option>
                                        </logic:iterate>
                                        </logic:present>
                                    </select>
                                </td>
                                <td colspan="1" >
                                    <input type="text"  id="absoluteYearlyDayInterval" name="absoluteYearlyDayInterval" style="height:1.7em;width:100px;" dojoType="dijit.form.ValidationTextBox" maxlength="2" onkeyup="return setyearFocus();"/>
                                </td>
                            </tr>

                            <tr>
                                <td colspan="2">
                                    <input type="radio" dojoType="dijit.form.RadioButton" name="absoluteYearlyPattern" id="absoluteYearlyPatternEveryMonthDay" value="selectedAbsoluteYearlyPatternEveryMonthDay" onClick="resetAbsoluteYearlyPatternFormValues()"/><label><b><bean:message key="jobs.recur.theLabel" bundle="jobs"/></b></label>
                                </td>
                                <td>
                                    <select  id="absoluteYearlyMonthlyWeekType" dojoType="dijit.form.FilteringSelect"  name="absoluteYearlyMonthlyWeekType" autoComplete="true" value='' onChange="return setyearFocus();">
                                        <option value=''><bean:message key="validation.msg.select" bundle="splchvalidation"/></option>
                                        <logic:present name="allWeekTypeMasterList">
                                        <logic:iterate name="allWeekTypeMasterList" id="allWeekDtls">
                                            <option value="<bean:write name="allWeekDtls" property="id"/>">
                                                <bean:message  bundle="jobs" name="allWeekDtls" property="type" />
                                            </option>
                                        </logic:iterate>
                                        </logic:present>
                                    </select>
                                </td>
                                <td>
                                    <select  id="absoluteYearlyMonthlyDayType" dojoType="dijit.form.FilteringSelect"  name="absoluteYearlyMonthlyDayType" autoComplete="true" value='' onChange="return setyearFocus();">
                                        <option value=''><bean:message key="validation.msg.select" bundle="splchvalidation"/></option>
                                        <logic:present name="allDayTypeMasterList">
                                        <logic:iterate name="allDayTypeMasterList" id="allDayDtls">
                                            <option value="<bean:write name="allDayDtls" property="id"/>">
                                                <bean:message  bundle="jobs" name="allDayDtls" property="type" />
                                            </option>
                                        </logic:iterate>
                                        </logic:present>
                                    </select> <b> of</b>
                                </td>
                                <td>
                                     <select  id="absoluteYearlyMonthlyMonthType" dojoType="dijit.form.FilteringSelect"  name="absoluteYearlyMonthlyMonthType" autoComplete="true" value='' onChange="return setyearFocus();">
                                        <option value=''><bean:message key="validation.msg.select" bundle="splchvalidation"/></option>
                                        <logic:present name="allMonthTypeMasterList">
                                        <logic:iterate name="allMonthTypeMasterList" id="allMonthDtls">
                                            <option value="<bean:write name="allMonthDtls" property="id"/>">
                                                <bean:message  bundle="jobs" name="allMonthDtls" property="type" />
                                            </option>
                                        </logic:iterate>
                                        </logic:present>
                                    </select>
                                </td>
                            </tr>

                        </table>
                    </td>

                </tr>
            </table>
            </td>

        </tr>
    </table>

</td>
</tr>

<script>
function resetAbsoluteFormValues(){
    if(dijit.byId('cronTypeId').value  == REPEAT_TRIGGER_IN_SECONDS){
        resetViewAbsoluteSMHDIVDtls('');
        resetViewAbsoluteDailyDIVDtls('none');
        resetViewAbsoluteWeeklyDIVDtls('none');
        resetViewAbsoluteMonthlyDIVDtls('none');
        resetViewAbsoluteYearlyDIVDtls('none');
    }else if(dijit.byId('cronTypeId').value  == REPEAT_TRIGGER_IN_MINUTES){
        resetViewAbsoluteSMHDIVDtls('');
        resetViewAbsoluteDailyDIVDtls('none');
        resetViewAbsoluteWeeklyDIVDtls('none');
        resetViewAbsoluteMonthlyDIVDtls('none');
        resetViewAbsoluteYearlyDIVDtls('none');
    }else if(dijit.byId('cronTypeId').value  == REPEAT_TRIGGER_IN_HOURS){
        resetViewAbsoluteSMHDIVDtls('');
        resetViewAbsoluteDailyDIVDtls('none');
        resetViewAbsoluteWeeklyDIVDtls('none');
        resetViewAbsoluteMonthlyDIVDtls('none');
        resetViewAbsoluteYearlyDIVDtls('none');
    }else if(dijit.byId('cronTypeId').value  == REPEAT_TRIGGER_IN_DAILY){
        resetViewAbsoluteSMHDIVDtls('none');
        resetViewAbsoluteDailyDIVDtls('');
        resetViewAbsoluteWeeklyDIVDtls('none');
        resetViewAbsoluteMonthlyDIVDtls('none');
        resetViewAbsoluteYearlyDIVDtls('none');
    }else if(dijit.byId('cronTypeId').value  == REPEAT_TRIGGER_IN_WEEKLY){
        resetViewAbsoluteSMHDIVDtls('none');
        resetViewAbsoluteDailyDIVDtls('none');
        resetViewAbsoluteWeeklyDIVDtls('');
        resetViewAbsoluteMonthlyDIVDtls('none');
        resetViewAbsoluteYearlyDIVDtls('none');
    }else if(dijit.byId('cronTypeId').value  == REPEAT_TRIGGER_IN_MONTHLY){
        resetViewAbsoluteSMHDIVDtls('none');
        resetViewAbsoluteDailyDIVDtls('none');
        resetViewAbsoluteWeeklyDIVDtls('none');
        resetViewAbsoluteMonthlyDIVDtls('');
        resetViewAbsoluteYearlyDIVDtls('none');
    }else if(dijit.byId('cronTypeId').value  == REPEAT_TRIGGER_IN_YEARLY){
        resetViewAbsoluteSMHDIVDtls('none');
        resetViewAbsoluteDailyDIVDtls('none');
        resetViewAbsoluteWeeklyDIVDtls('none');
        resetViewAbsoluteMonthlyDIVDtls('none');
        resetViewAbsoluteYearlyDIVDtls('');
    }else if(dijit.byId('cronTypeId').value  == REPEAT_TRIGGER_IN_EXECUTEONCE){
        resetViewAbsoluteSMHDIVDtls('none');
        resetViewAbsoluteDailyDIVDtls('none');
        resetViewAbsoluteWeeklyDIVDtls('none');
        resetViewAbsoluteMonthlyDIVDtls('none');
        resetViewAbsoluteYearlyDIVDtls('none');
    }else {
        resetViewAbsoluteSMHDIVDtls('none');
        resetViewAbsoluteDailyDIVDtls('none');
        resetViewAbsoluteWeeklyDIVDtls('none');
        resetViewAbsoluteMonthlyDIVDtls('none');
        resetViewAbsoluteYearlyDIVDtls('none');
    }
}

function resetViewAbsoluteSMHDIVDtls(status){
    document.getElementById('absoluteIntervalSMHDIVLabelDtls').style.display = status;
    document.getElementById('absoluteIntervalSMHDIVComponentDtls').style.display = status;
}

function resetViewAbsoluteDailyDIVDtls(status){
    document.getElementById('absoluteIntervalDailyDIVLabelDtls').style.display = status;
}

function resetViewAbsoluteWeeklyDIVDtls(status){
    document.getElementById('absoluteIntervalWeeklyDIVLabelDtls').style.display = status;
}

function resetViewAbsoluteMonthlyDIVDtls(status){
    document.getElementById('absoluteIntervalMonthlyDIVLabelDtls').style.display = status;
}

function resetViewAbsoluteYearlyDIVDtls(status){
    document.getElementById('absoluteIntervalYearlyDIVLabelDtls').style.display = status;
}
</script>



<!---   DIV Ended for ABSOLUTE JOB  -->


            <tr>
                <td height="13" colspan="8" align="left" class=""></td>
            </tr>
                <tr>

                  <td style="padding-left:67px;">

                  <div id="div1" style="display:block;" >
                        <% if (!readOnly) { %>

							<table id = "PITable" class="tableBgColor"  width = "52.5%">

						<%} else if (tmp == "delete") { %>

                    		<table id = "PITable" class="tableBgColor"  width = "50.9%">

						<% } else { %>

 							<table id = "PITable" class="tableBgColor"  width = "50.8%">

						<%}%>

                            <tr height="10px">
                                <td>&nbsp;

                                </td>
                            </tr>
                            <tr>
                                <td class="pageTitle " style="padding-left:30px;align:left;">
                                    <bean:message key="jobs.pidbFieldset" bundle="jobs"/>
                                </td>
                            </tr>
                            <tr>
                               <td class="paddingTitleDesc" style="padding-left:30;" align="left">
                                     <bean:message key="jobs.piData.description" bundle="jobs"/>
                                </td>
                            </tr>
                            <tr>
                                <td height="13" colspan="8" align="left" class=""></td>
                            </tr>
                            <tr>
                                <td>
                                    <table cellspacing="0" cellpadding="0" width="100%" border = "0"  >
                                        <tr>
                                            <td style="padding-left:34px;align:right;" width = "23%">
                                               <label class="label"><bean:message key="jobs.datasourceName" bundle="jobs"/></label><label class="error">*</label><b>:</b>
                                                                        </td>
                                                                        <td style="padding-left:10px; align:left;" width = "78%">
                    <select  id="dataSource" dojoType="dijit.form.FilteringSelect"   name="dataSource"    autoComplete="false"    value="<bean:write name="pIDataExtractorForm" property="dataSource" />" >
                                                    <option value="-1"><bean:message key="validation.msg.select" bundle="splchvalidation"/></option>
                                                    <%
                                                            for (int i = 0; i < datasourceArray.length; i++) { %>
                                                                <option value="<%=datasourceArray[i].getId()%>"><%=datasourceArray[i].getDatasourceName()%> </option>
<%                                                          } %>


                                                </select>

                                                                        </td>
																		</tr>
																		 <tr>
                        <td height="13" colspan="2" align="left" class=""></td>
                        </tr>

                        <tr>
				            <td>&nbsp;</td>
						          <td height="27" align="left"  style="width:250px;" nowrap="nowrap" colspan="1" > &nbsp;
                                   <html:radio  name="pIDataExtractorForm" property="tags" value="tags"></html:radio>&nbsp;<label class="label"><bean:message key="jobs.tags" bundle="jobs"/></label>
                                   <html:radio  name="pIDataExtractorForm" property="tags" value="query"></html:radio>&nbsp; <label class="label"><bean:message key="jobs.query" bundle="jobs"/></label>&nbsp;<html:radio  name="pIDataExtractorForm" property="tags" value="custom"></html:radio>&nbsp; <label class="label"><bean:message key="jobs.custom" bundle="jobs"/></label>&nbsp;
                                </td>
                        </tr>
						<tr>
                          <td height="13" colspan="2" align="left" class=""></td>
                        </tr>

                         <tr>
                            <td align="right">
							 <label class="label"><bean:message key="jobs.tagqryOption" bundle="jobs"/></label><label class="error">*</label><b>:</b></td>
							 <td style="padding-left:10px;align:left;">
							 <textarea dojoType="dijit.form.SimpleTextarea" id="tagQuery" name="tagQuery" style="height:5.7em;width=280px;" trim="true"><bean:write name="pIDataExtractorForm" property="tagQuery" /></textarea>
                           </td>

                        </tr>

                       <tr>
                        <td height="13" colspan="2" align="left" class=""></td>
                        </tr>

                        <tr>
                            <td align="right" style="padding-left:0px;width=900px">
							<label class="label"><bean:message key="jobs.pilistener" bundle="jobs"/></label><b>:</b></td>
                                                                                <td style="padding-left:10px;align:left;">
                                                                                <textarea dojoType="dijit.form.SimpleTextarea" trim="true" rows="5" cols="30" id="pilistener"  name="pilistener"  style="height:5.7em;width=280px;"  ><bean:write name="pIDataExtractorForm" property="pilistener"/></textarea>
                                                                            </td>
                                                                    </tr>

                        <tr>
                            <td height="13" colspan="1" align="left" class="bodytext"></td>
                        </tr>
                        <tr>
						   <td align="right"><label class="label"><bean:message key="jobs.metadata" bundle="jobs"/></label><b>:</b></td>
                            <td style="padding-left:0px;width="900px">
                                  &nbsp;
									<html:checkbox  name="pIDataExtractorForm" property="loadMetaData" ></html:checkbox>
						   </td>
                       </tr>
                       <tr>
                         <td height="13" colspan="2" align="left" class=""></td>
                       </tr>
                       <tr>
			              <td align="right">
			               <label class="label" ><bean:message key="jobs.cachekey" bundle="jobs"/></label><b>:</b>
                          </td> <td height="27"  style="align:left;width:160;padding-left:10px;">
                                  <input type="text"  id="cacheKey" name="cacheKey" class="medium" style="height:1.7em;"
                                            dojoType="dijit.form.ValidationTextBox" required="true" trim="true" ucfirst="true" value="<bean:write name="pIDataExtractorForm" property="cacheKey"/>"   />
                               </td>
                                </tr>
                             </table>
                          </td>
                        </tr>
                        </table>
        </div>
</td></tr>
<TR><td height="10px">&nbsp;</td></TR>
<tr><td width="100%"  >
<table border="0" width="101.2%">
     <tr class="barColor">
           <td colspan="12" align="center" >
		   <button dojoType="dijit.form.Button" type="button" id="idHome" name="btnHome" value='Home' onClick="window.location= '<%=ServerUtils.getContextName()%>/adminMain.do' "> <bean:message key="jobs.home" bundle="jobs"/></button>
                            <button dojoType="dijit.form.Button"  type="button"  id="idBack" name="btnBack" value="<bean:message key="jobs.back" bundle="jobs"/>" onClick="return submitForm(this);">
                                        <bean:message key="jobs.back" bundle="jobs"/>
                    </button>
                    <% if (!readOnly) { %>
                    <button dojoType="dijit.form.Button" id="idUpdate" name="btnupdate" value="<bean:message key="jobs.save" bundle="jobs"/>" onClick="return submitForm(this);" >
                            <bean:message key="jobs.save" bundle="jobs"/>
                        </button>
                         <button dojoType="dijit.form.Button" id="idReset" name="btnReset" onClick="resetValues()" >
                           <bean:message key="jobs.reset" bundle="jobs"/>
                         </button>

                    <%}%>

                    <% if (tmp == "delete") { %>
                        <button dojoType="dijit.form.Button" id="idDelete" name="btndelete" value="<bean:message key="jobs.delete" bundle="jobs"/>" onclick="confirmationDialog('Do you want to delete the record?');" >
                            <bean:message key="jobs.delete" bundle="jobs"/>
                        </button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp

                    <%}%>

                    </td>
    </tr>
</table>
 <input type="hidden" name="operation" value="">
           <input type="hidden" name="commonFlagIndicator" id="commonFlagIndicator" value="<%= formObj.getCommonJobFlag()%>">

</html:form>

<script>

function resetValues(){
	window.location.href="<%=ServerUtils.getContextName()%>/configureJobSchdAction.do?operation=view&action=edit&jobsType=<%=String.valueOf(JobConstants.JOB_TYPE_PIDATAEXTRACTOR)%>&selPIDataExtractor=<bean:write name="pIDataExtractorForm" property="id"/>";
}

dojo.addOnLoad(loadFormValues);
function loadFormValues(){
	var status = false;
    if(<%= readOnly%>){
		status = true;
    }
    dojo.byId('startTime').setAttribute('value', '<bean:write name="pIDataExtractorForm" property="startTime" />');
    document.getElementById('startTime').value= '<bean:write name="pIDataExtractorForm" property="startTime" />';
	dijit.byId('startTimeType').setValue('<bean:write name="pIDataExtractorForm" property="startTimeType" />');
    dijit.byId('startDate').setDisplayedValue('<bean:write name="pIDataExtractorForm" property="startDate" />');
	dijit.byId('startTimeType').setAttribute('disabled', true);
	dijit.byId('jobName').setAttribute('disabled', true);
	if(dijit.byId('startTimeType').value == START_JOB_AFTER_SERVER_START){
		if('<bean:write name="pIDataExtractorForm" property="afterServerStartsPattern" />' == 'EVERY'){
			dijit.byId('afterServerStartsPatternEvery').setAttribute('checked',true);
			dijit.byId('afterServerStartsPatternEvery').setAttribute('disabled',status);
			dijit.byId('afterServerStartsPatternOnce').setAttribute('disabled',status);
		}else if('<bean:write name="pIDataExtractorForm" property="afterServerStartsPattern" />' == 'ONCE'){
			dijit.byId('afterServerStartsPatternOnce').setAttribute('checked',true);
			dijit.byId('afterServerStartsPatternEvery').setAttribute('disabled',status);
			dijit.byId('afterServerStartsPatternOnce').setAttribute('disabled',status);
		}
	}else if(dijit.byId('startTimeType').value == START_JOB_ABSOLUTE){
		dijit.byId('cronTypeId').setValue('<bean:write name="pIDataExtractorForm" property="cronTypeId" />');
		dijit.byId('absoluteInterval').setValue('<bean:write name="pIDataExtractorForm" property="absoluteInterval" />');
		dijit.byId('cronTypeId').setAttribute('disabled', status);
		dijit.byId('absoluteInterval').setAttribute('disabled', status);
		populateAbsoluteStartTypeValues('<bean:write name="pIDataExtractorForm" property="cronTypeId" />', status);
	}else if(dijit.byId('startTimeType').value == START_JOB_EXPRESSION){
			dojo.byId('expression').value = '<bean:write name="pIDataExtractorForm" property="expression" />';
			dojo.byId('expression').setAttribute('disabled', status);
	}
	dojo.byId('interval').value = '<bean:write name="pIDataExtractorForm" property="interval" />';
	dijit.byId('intervalType').setValue('<bean:write name="pIDataExtractorForm" property="intervalType" />');


    dijit.byId('description').setAttribute('disabled', status);
    dijit.byId('startTimeType').setAttribute('disabled', status);
    if(status){
		dijit.byId('startDate').setAttribute('disabled', status);
	}
    dijit.byId('startTime').setAttribute('disabled', status);
    dijit.byId('interval').setAttribute('disabled', status);
    dijit.byId('intervalType').setAttribute('disabled', status);
    dijit.byId('jobType').setAttribute('disabled', true);
    dijit.byId('dataSource').setAttribute('disabled', status);
    dijit.byId('tagQuery').setAttribute('disabled', status);
    dijit.byId('cacheKey').setAttribute('disabled', status);
    dijit.byId('pilistener').setAttribute('disabled', status);
    dijit.byId('jobName').setAttribute('disabled', true);
	dijit.byId('absoluteDailyPatternEveryDay').setAttribute('disabled',status);
	dijit.byId('absoluteDailyPatternEveryWeekDay').setAttribute('disabled',status);
    document.forms[0].tags[0].disabled = status;
    document.forms[0].loadMetaData.disabled = status;
    document.forms[0].tags[1].disabled = status;
	document.forms[0].tags[2].disabled = status;
    if('<bean:write name="pIDataExtractorForm" property="tags"/>' == 'True'){
		document.forms[0].tags[0].checked = true;
    }else{
		document.forms[0].tags[0].checked= false;
    }

	if('<bean:write name="pIDataExtractorForm" property="query"/>' == 'True'){
		document.forms[0].tags[1].checked= true;
    }else{
		document.forms[0].tags[1].checked= false;
    }

	if('<bean:write name="pIDataExtractorForm" property="custom"/>' == 'True'){
		document.forms[0].tags[2].checked = true;
    }else{
		document.forms[0].tags[2].checked= false;
    }
}

        function  dodelete(){
 		   document.getElementById('operation').value = 'deleteJob';
 			 document.forms[0].action='configureJobSchdAction.do?operation=deleteJob';
                 document.forms[0].submit();
             }
		function checkBoxAlert(boxName){
				var msg ='<%=tmp%>';

		        //	alert(msg)
				if(msg != null  && msg == 'delete')
				{
					if(boxName.checked == true)
						 showEmptyDialog("<bean:message key='jobs.delete.all' bundle='jobs'/>", "Error");
							else
						 showEmptyDialog("<bean:message key='jobs.delete.this' bundle='jobs'/>", "Error");
				}
				else
				{
					if(boxName.checked == true)
						 showEmptyDialog("<bean:message key='jobs.update.all' bundle='jobs' />", "Error");
							else
						 showEmptyDialog("<bean:message key='jobs.udpate.this' bundle='jobs' />", "Error");
				}


		}

function populateAbsoluteStartTypeValues(cronTypeId, status){
	if(cronTypeId == REPEAT_TRIGGER_IN_DAILY){
		if('<bean:write name="pIDataExtractorForm" property="absoluteDailyPattern" />' == 'selectedAbsoluteDailyPatternEveryDay'){
			dijit.byId('absoluteDailyPatternEveryDay').setAttribute('checked',true);
			dijit.byId('absoluteDailyInterval').setValue('<bean:write name="pIDataExtractorForm" property="absoluteDailyInterval" />');
			dijit.byId('absoluteDailyPatternEveryWeekDay').setAttribute('disabled',status);
			dijit.byId('absoluteDailyInterval').setAttribute('disabled',status);
			
		}else if('<bean:write name="pIDataExtractorForm" property="absoluteDailyPattern" />' == 'selectedAbsoluteDailyPatternEveryWeekDay'){
			dijit.byId('absoluteDailyPatternEveryWeekDay').setAttribute('checked',true);
			dijit.byId('absoluteDailyInterval').setAttribute('disabled',status);
			dijit.byId('absoluteDailyPatternEveryDay').setAttribute('disabled',status);
		}
	}

	if(cronTypeId == REPEAT_TRIGGER_IN_WEEKLY){
		if('<bean:write name="pIDataExtractorForm" property="absoluteWeeklyPatternSunday" />' == 'absoluteWeeklyPatternSunday'){
			dijit.byId('absoluteWeeklyPatternSunday').setAttribute('checked',true);
		}

		if('<bean:write name="pIDataExtractorForm" property="absoluteWeeklyPatternMonday" />' == 'absoluteWeeklyPatternMonday'){
			dijit.byId('absoluteWeeklyPatternMonday').setAttribute('checked',true);
		}

		if('<bean:write name="pIDataExtractorForm" property="absoluteWeeklyPatternTuesday" />' == 'absoluteWeeklyPatternTuesday'){
			dijit.byId('absoluteWeeklyPatternTuesday').setAttribute('checked',true);
		}

		if('<bean:write name="pIDataExtractorForm" property="absoluteWeeklyPatternWednesday" />' == 'absoluteWeeklyPatternWednesday'){
			dijit.byId('absoluteWeeklyPatternWednesday').setAttribute('checked',true);
		}

		if('<bean:write name="pIDataExtractorForm" property="absoluteWeeklyPatternThursday" />' == 'absoluteWeeklyPatternThursday'){
			dijit.byId('absoluteWeeklyPatternThursday').setAttribute('checked',true);
		}

		if('<bean:write name="pIDataExtractorForm" property="absoluteWeeklyPatternFriday" />' == 'absoluteWeeklyPatternFriday'){
			dijit.byId('absoluteWeeklyPatternFriday').setAttribute('checked',true);
		}

		if('<bean:write name="pIDataExtractorForm" property="absoluteWeeklyPatternSaturday" />' == 'absoluteWeeklyPatternSaturday'){
			dijit.byId('absoluteWeeklyPatternSaturday').setAttribute('checked',true);
		}

		dijit.byId('absoluteWeeklyPatternSunday').setAttribute('disabled',status);
		dijit.byId('absoluteWeeklyPatternMonday').setAttribute('disabled',status);
		dijit.byId('absoluteWeeklyPatternTuesday').setAttribute('disabled',status);
		dijit.byId('absoluteWeeklyPatternWednesday').setAttribute('disabled',status);
		dijit.byId('absoluteWeeklyPatternThursday').setAttribute('disabled',status);
		dijit.byId('absoluteWeeklyPatternFriday').setAttribute('disabled',status);
		dijit.byId('absoluteWeeklyPatternSaturday').setAttribute('disabled',status);
	}

		if(cronTypeId == REPEAT_TRIGGER_IN_MONTHLY){
		if('<bean:write name="pIDataExtractorForm" property="absoluteMonthlyPattern" />' == 'selectedAbsoluteMonthlyPatternEveryDay'){
			dijit.byId('absoluteMonthlyPatternEveryDay').setAttribute('checked',true);
			dijit.byId('absoluteMonthlyDayInterval').setValue('<bean:write name="pIDataExtractorForm" property="absoluteMonthlyDayInterval" />');
		dijit.byId('absoluteMonthlyWeekType').setAttribute('disabled', true);
		dijit.byId('absoluteMonthlyDayType').setAttribute('disabled', true);

		}else if('<bean:write name="pIDataExtractorForm" property="absoluteMonthlyPattern" />' == 'selectedAbsoluteMonthlyPatternEveryMonth'){
			dijit.byId('absoluteMonthlyPatternEveryMonth').setAttribute('checked',true);
			dijit.byId('absoluteMonthlyWeekType').setValue('<bean:write name="pIDataExtractorForm" property="absoluteMonthlyWeekType" />');
			dijit.byId('absoluteMonthlyDayType').setValue('<bean:write name="pIDataExtractorForm" property="absoluteMonthlyDayType" />');
		dijit.byId('absoluteMonthlyDayInterval').setAttribute('disabled',true);
		}

if(status){
		dijit.byId('absoluteMonthlyPatternEveryMonth').setAttribute('disabled',status);
		dijit.byId('absoluteMonthlyWeekType').setAttribute('disabled', status);
		dijit.byId('absoluteMonthlyDayType').setAttribute('disabled', status);
		dijit.byId('absoluteMonthlyPatternEveryDay').setAttribute('disabled',status);
		dijit.byId('absoluteMonthlyDayInterval').setAttribute('disabled',status);
	}
	}

	if(cronTypeId == REPEAT_TRIGGER_IN_YEARLY){
		if('<bean:write name="pIDataExtractorForm" property="absoluteYearlyPattern" />' == 'selectedAbsoluteYearlyPattern'){
			dijit.byId('absoluteYearlyPatternEveryMonth').setAttribute('checked',true);
			dijit.byId('absoluteYearlyMonthType').setValue('<bean:write name="pIDataExtractorForm" property="absoluteYearlyMonthType" />');
			dijit.byId('absoluteYearlyDayInterval').setValue('<bean:write name="pIDataExtractorForm" property="absoluteYearlyDayInterval" />');
				dijit.byId('absoluteYearlyMonthlyWeekType').setAttribute('disabled',true);
		    dijit.byId('absoluteYearlyMonthlyDayType').setAttribute('disabled',true);
		    dijit.byId('absoluteYearlyMonthlyMonthType').setAttribute('disabled',true);
		}else if('<bean:write name="pIDataExtractorForm" property="absoluteYearlyPattern" />' == 'selectedAbsoluteYearlyPatternEveryMonthDay'){
			dijit.byId('absoluteYearlyPatternEveryMonthDay').setAttribute('checked',true);
			dijit.byId('absoluteYearlyMonthlyWeekType').setValue('<bean:write name="pIDataExtractorForm" property="absoluteYearlyMonthlyWeekType" />');
			dijit.byId('absoluteYearlyMonthlyDayType').setValue('<bean:write name="pIDataExtractorForm" property="absoluteYearlyMonthlyDayType" />');
			dijit.byId('absoluteYearlyMonthlyMonthType').setValue('<bean:write name="pIDataExtractorForm" property="absoluteYearlyMonthlyMonthType" />');
			dijit.byId('absoluteYearlyMonthType').setAttribute('disabled', true);
		    dijit.byId('absoluteYearlyDayInterval').setAttribute('disabled', true);
		}
if(status){
		dijit.byId('absoluteYearlyPatternEveryMonth').setAttribute('disabled',status);
		dijit.byId('absoluteYearlyMonthType').setAttribute('disabled', status);
		dijit.byId('absoluteYearlyDayInterval').setAttribute('disabled', status);
		dijit.byId('absoluteYearlyPatternEveryMonthDay').setAttribute('disabled',status);
		dijit.byId('absoluteYearlyMonthlyWeekType').setAttribute('disabled',status);
		dijit.byId('absoluteYearlyMonthlyDayType').setAttribute('disabled',status);
		dijit.byId('absoluteYearlyMonthlyMonthType').setAttribute('disabled',status);
}
	}
}

function setFocus(){
    if(dijit.byId('interval').attr('value').length > 0 || dijit.byId('intervalType').attr('value').length > 0){
        dijit.byId('afterServerStartsPatternEvery').setChecked(true);
    }
    return true;
}
function setdailyFocus(){
    if(dijit.byId('absoluteDailyInterval').attr('value').length > 0){
        dijit.byId('absoluteDailyPatternEveryDay').setChecked(true);
    }
    return true;
}
function setmonthFocus(){
    if(dijit.byId('absoluteMonthlyDayInterval').attr('value').length > 0){
        dijit.byId('absoluteMonthlyWeekType').setAttribute('disabled', true);
        dijit.byId('absoluteMonthlyDayType').setAttribute('disabled', true);
        dijit.byId('absoluteMonthlyPatternEveryDay').setChecked(true);
    }
    else if(dijit.byId('absoluteMonthlyWeekType').attr('value').length > 0 || dijit.byId('absoluteMonthlyDayType').attr('value').length > 0){
        dijit.byId('absoluteMonthlyDayInterval').setAttribute('disabled', true);
        dijit.byId('absoluteMonthlyPatternEveryMonth').setChecked(true);
    }
    return true;
}
function setyearFocus(){
    if(dijit.byId('absoluteYearlyMonthType').attr('value').length > 0 ||dijit.byId('absoluteYearlyDayInterval').attr('value').length > 0){
        dijit.byId('absoluteYearlyMonthlyWeekType').setAttribute('disabled', true);
        dijit.byId('absoluteYearlyMonthlyDayType').setAttribute('disabled', true);
        dijit.byId('absoluteYearlyMonthlyMonthType').setAttribute('disabled', true);
        dijit.byId('absoluteYearlyPatternEveryMonth').setChecked(true);
    }
    else if(dijit.byId('absoluteYearlyMonthlyWeekType').attr('value').length > 0 || dijit.byId('absoluteYearlyMonthlyDayType').attr('value').length > 0 || dijit.byId('absoluteYearlyMonthlyMonthType').attr('value').length > 0){
        dijit.byId('absoluteYearlyMonthType').setAttribute('disabled', true);
        dijit.byId('absoluteYearlyDayInterval').setAttribute('disabled', true);
        dijit.byId('absoluteYearlyPatternEveryMonthDay').setChecked(true);
    }
    return true;
}
</script>

</body>
</html:html>