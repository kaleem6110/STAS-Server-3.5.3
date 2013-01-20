<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ page import="com.spacetimeinsight.stas.config.GenericConfigurationManager,com.enterprisehorizons.magma.config.dbadmin.ModelConfigConstants" %>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ page import="java.util.*,com.enterprisehorizons.util.StringUtils, com.spacetimeinsight.magma.job.JobConstants, 
com.enterprisehorizons.magma.job.bd.JobScheduleDelegator,com.spacetimeinsight.magma.job.bean.StiJobBean,
com.spacetimeinsight.stas.datasource.GenericDatasourceManager,
com.enterprisehorizons.magma.jobs.forms.EcoSystemForm,com.spacetimeinsight.db.scheduler.model.ServerEnvMaster,com.spacetimeinsight.db.model.util.DataModelsCache, com.spacetimeinsight.db.scheduler.model.*"%>
<script type="text/javascript" src="js/jobs.js"></script>
<script type="text/javascript" src="js/ecoweb.js"></script>

<%@ taglib prefix="tiles" uri="/tags/struts-tiles"%>
<%@page import="com.enterprisehorizons.magma.server.util.ServerUtils"%>
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
else  
    readOnly = false;

	boolean  checkBoxFlag = false;
	EcoSystemForm formObj = (EcoSystemForm)request.getAttribute("ecoSystemForm");

if(!checkboxstatus && StringUtils.isNull(formObj.getCommonJobFlag()) || (tmp != null && (tmp.equals("readonly")) ))
    checkboxstatus = true;

    com.spacetimeinsight.config.scheduler.Dependentecosystems dependEcosystemMap = (com.spacetimeinsight.config.scheduler.Dependentecosystems) session
            .getAttribute("dependEcosystemMap");
    int ecoCount = -1;
    if (dependEcosystemMap != null) {
        ecoCount = dependEcosystemMap.getEcosystementryCount();
    }



	
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
<title>View / Update Job</title>

<script>

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

function resetHandler(){
	
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
</script>

<%@ include file="/common/dojo.jsp"%>

</head>
<body onLoad="" class="tundra bodybg">
<html:form method="post" action="/ecoSystemAction.do?operation=onUpdateEcosystem"> 
<input type="hidden"  name="encryptedPassword" id="encryptedPassword" />
<input type="hidden"  name="virtualPassword" id="virtualPassword" />
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

    try{
        var countrownumber ;
    if(<%=ecoCount%> == -1)
countrownumber = 0;
else
countrownumber = <%=ecoCount%>-1 ;
    }
     catch (ex) { 
    //if exception occurs 
    alert(ex); 
  }
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

                isValidForSubmit = validateEcoSystem('fromUpdateMode');
            
                if(!isValidForSubmit)
                    return false;

				if(checkIntervalValue()){		
					return false;
				}
				
				if(!checkValidPassword(dijit.byId('ecoPassword').value,dojo.byId('virtualPassword').value,true, true)){
					return false;
				} 

				if(!isValidUsernamePassword(dijit.byId('ecoUserId').value,dijit.byId('ecoPassword').value)){
					showEmptyDialog("User ID should not be blank on providing password", "Error");
					return false;
				}
				dijit.byId('jobName').setAttribute('disabled', false);
				dijit.byId('startTimeType').setAttribute('disabled', false);
                if(!performSubmit()) {
					dijit.byId('jobName').setAttribute('disabled', true);
				    dijit.byId('startTimeType').setAttribute('disabled', true);
				}
            }else{
				dijit.byId('jobName').setAttribute('disabled', false);
				dijit.byId('startTimeType').setAttribute('disabled', false);
                if(!performSubmit()) {
					dijit.byId('jobName').setAttribute('disabled', true);
				    dijit.byId('startTimeType').setAttribute('disabled', true);
				}
            }
        }
		
		function checkIntervalValue(){
			return isFraction(dijit.byId('interval').value,"Interval must be integer");
		}


        

var paramDivs = 1;
var ecoDivs = 1;
</script>

    <script language="javascript"> 
var addedRowsNumbers = "";
var addedURLRowsNumbers = "";
var initial_count = new Array(); 
var rows_limit = 0; // Set to 0 to disable limitation 
var dbColumnsArray = [];
var ecosystemName = "";
var selectedEcoSystemName = "";
 var rows_count = 0;


<logic:present name="ecoModelFileList" scope="request">
    <logic:iterate name="ecoModelFileList" id="ecoModelFileDtls">
        dbColumnsArray.push({column: "<bean:write name="ecoModelFileDtls" property="ecoSystemFilePath"/>" , label: "<bean:write name="ecoModelFileDtls" property="ecoSystemName"/>"});      
    </logic:iterate>
</logic:present>
   
function delAddedRowsNumbers(tmp)
{
    var rowNumbersArray = new Array(); 
    rowNumbersArray = addedRowsNumbers.split(',');
    for(countArr = 0;countArr < rowNumbersArray.length;countArr++)
    {
        var tmp1 = rowNumbersArray[countArr];
        if(tmp == tmp1)
        {
            rowNumbersArray[countArr] = 'n';
            break;
        }
    }
addedRowsNumbers = rowNumbersArray.join(",");
}

function addRow(table_id) 

{ 
columnList = {identifier:"column",items:dbColumnsArray};
columnStore = new dojo.data.ItemFileReadStore({data: columnList});
var tbl = document.getElementById(table_id); 
// counting rows in table 
rows_count = tbl.rows.length; 
var rowNumbersArray = new Array();          
rowNumbersArray = addedRowsNumbers.split(',');
var prefix = rowNumbersArray.length;
ecosystemName = "ecoSystem" +  prefix;
 selectedEcoSystemName = "selectedEcoSystem" + prefix;
 addedRowsNumbers = addedRowsNumbers + prefix + ",";
  if (initial_count[table_id] == undefined) 
  { 
    // if it is first adding in this table setting initial rows count 
    initial_count[table_id] = rows_count; 
  } 
  // determining real count of added fields 
  var tFielsNum = rows_count - initial_count[table_id]; 
  if (rows_limit!=0 && tFielsNum >= rows_limit) return false; 
  var text = 'Text field'; 
  
//  var input = '<input type="text" name="postvar[]" style="width:100%;"/>'; 
//  var remove= '<input type="button" value="X" onclick="removeRow(\''+table_id+'\',this.parentNode.parentNode)" style="width:100%;"/>'; 
   
  try { 
    var newRow = tbl.insertRow(rows_count); 
    var newCell = newRow.insertCell(0); 
   var input = ' &nbsp;&nbsp;<select  id="'+ ecosystemName+'"  name="' + ecosystemName + '"    autoComplete="false"><option value=""><bean:message key="validation.msg.select" bundle="splchvalidation"/></option>' +
    <logic:present name="ecoModelFileList" scope="request"> 
    <logic:iterate name="ecoModelFileList" id="ecoModelFileDtls">
    '<option value="<bean:write name="ecoModelFileDtls" property="ecoSystemFilePath"/>"><bean:write name="ecoModelFileDtls" property="ecoSystemName"/></option>'+
    </logic:iterate>
    </logic:present>  
    '</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select id="'+selectedEcoSystemName+'"  name="'+selectedEcoSystemName +'" autoComplete="false"><option value=""><bean:message key="validation.msg.select" bundle="splchvalidation"/></option><option value="1"><bean:message key="jobs.runmode.once" bundle="jobs" /></option><option value="2"><bean:message key="jobs.runmode.everytime" bundle="jobs" /></option></select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<textarea dojoType="dijit.form.SimpleTextarea" rows="5" cols="30" style="height:1.7em;width:20em"  id="'+ecosystemName+'ParameterValueMap" name="'+ecosystemName+'ParameterValueMap"></textarea><%if (!readOnly) {%><a href="#" onclick="javascript:removeRow(\'mytable1\',this.parentNode.parentNode,'+ prefix +')"><img src="<%=ServerUtils.getContextName(request)%>/images/portal/icon_dash_1.png" / ></a><%}%>';
   
	newCell.innerHTML =input; 

	 document.getElementById(ecosystemName).value="";
	 var ecosystemNamefilteringSelectAsDependEcoSys = new dijit.form.FilteringSelect({
	id: ecosystemName,
	name:ecosystemName   
   }, ecosystemName);
dijit.byId(ecosystemNamefilteringSelectAsDependEcoSys).setValue('');
	var selectedEcoSystemNamefilteringSelectAsRun = new dijit.form.FilteringSelect({
	id: selectedEcoSystemName,
	name:selectedEcoSystemName   
   }, selectedEcoSystemName); 
    dijit.byId(selectedEcoSystemNamefilteringSelectAsRun).setValue('');
   
//    var newCell = newRow.insertCell(1); 
//    newCell.innerHTML = input; 
//    var newCell = newRow.insertCell(2); 
//    newCell.innerHTML = remove; 
    countrownumber++
  } catch (ex) { 
    //if exception occurs 
    alert(ex); 
  } 


} 

function removeRow(tbl,row,prefix) 
{ 
  var table = document.getElementById(tbl); 
  var rownum = row.rowIndex;
  try
  { 
        table.deleteRow(rownum); 
        delAddedRowsNumbers(prefix);    
        countrownumber--;
  } catch (ex) { 
    alert(ex); 
  } 

} 

function assignTotalNoOfEcos()
{
        //var total = document.getElementById('mytable1').rows.length;
        document.getElementById('totalNoOfEcoSystems').value = addedRowsNumbers.split(',');
        return true;
}
</script>

    <table width="70%">
        <tr>
            <td width="100%">
            <table width="100%" cellspacing="0" cellpadding="0" align="center"
                border="0">
                <tr>
                    <td class="pageTitle paddingTitle">
                    <%
                        if (!readOnly) {
                    %> <bean:message key="jobs.updateHeader"
                        bundle="jobs" /></td>
                </tr>
                <tr>
                    <td class="paddingTitleDesc"><bean:message
                        key="jobs.updatesubHeader" bundle="jobs" /> <%
    } else if (tmp == "delete") {
 %>
                    <bean:message key="jobs.deleteHeader" bundle="jobs" /></td>
                </tr>
                <tr>
                    <td class="paddingTitleDesc"><bean:message
                        key="jobs.deletesubHeader" bundle="jobs" /> <%
    } else {
 %> <bean:message
                        key="jobs.viewHeader" bundle="jobs" /></td>
                </tr>
                <tr>
                    <td class="paddingTitleDesc"><bean:message
                        key="jobs.viewsubHeader" bundle="jobs" /> <%
    }
 %>
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
<html:hidden  property="id" name="EcoSystemForm"  />
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
							dojoType="dijit.form.ValidationTextBox" required="true" trim="true" ucfirst="true"  value="<bean:write name="ecoSystemForm" property="jobName" />" />                             
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
						<textarea dojoType="dijit.form.SimpleTextarea" rows="5" cols="30" id="description" class="medium1" style="height:5.7em;" name="description"  ><bean:write name="ecoSystemForm" property="description" /></textarea>
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
						<select  id="jobType" dojoType="dijit.form.FilteringSelect"name="jobType" autoComplete="false"onchange="javascript:statusChange();" value="<bean:write name="ecoSystemForm" property="jobType" />">
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
								<html:checkbox  name="ecoSystemForm" property="commonJobFlag" onclick="checkBoxAlert(this);" disabled="<%=checkboxstatus%>"></html:checkbox>
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
<html:hidden  property="id" name="EcoSystemForm"  />
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
					<td width = "100" style="padding-left: 67px;">
					<div id="div3" style="display: block;">
					<table class="tableBgColor">
						<tr height="10px">
							<td>&nbsp;</td>
						</tr>

						<tr>
							<td>
						<tr>
							<td class="pageTitle paddingTitle" style="padding-left: 30px; align: left;">
							<bean:message key="jobs.ecosystemLabel" bundle="jobs" /></td>
						</tr>
						<tr>
							<td class="paddingTitleDesc bodytext" style="padding-left: 30px;"><bean:message
								key="jobs.ecosystem.description" bundle="jobs" /></td>
						</tr>
						<tr>
							<td height="13" colspan="2" align="left" class=""></td>
						</tr>
						<tr>
							<td style="padding-left:30px;width="90px">
							<table cellspacing="0" cellpadding="0" border = "0">
								<tr>
									<td style="padding-left:100px;align:right"><label class="label"><bean:message key="jobs.ecosystemLabel" bundle="jobs" /></label><label class="error">*</label><b>:</b>
									</td>
								<td style="padding-left: 10px; align: left;" width="90px">
									<select id="ecoSystemName"
										dojoType="dijit.form.FilteringSelect" name="ecoSystemName"
										autoComplete="false" onChange="resetCredentials(this, 2)">
										<logic:present name="ecoModelFileList" scope="request">
											<logic:iterate name="ecoModelFileList" id="ecoModelFileDtls">
												<option
													value="<bean:write name="ecoModelFileDtls" property="ecoSystemFilePath"/>"><bean:write name="ecoModelFileDtls" property="ecoSystemName" /></option>
											</logic:iterate>
										</logic:present>
									</select></td>
								</tr>


								<tr>
									<td height="13" colspan="2" align="left" class=""></td>
								</tr>
								<tr>
									<td style="padding-left:120px;align:right"><label class="label"><bean:message	key="jobs.userId" bundle="jobs" /></label><label class="error"></label><b>:</b>
									</td>
									<td height="27" style="padding-left: 10px; align: left"><input
										type="text" id="ecoUserId" name="ecoUserId" class="medium1"
										style="height: 1.7em;" dojoType="dijit.form.ValidationTextBox"
										required="true" trim="true" ucfirst="true"
										value="<bean:write name="ecoSystemForm" property="ecoUserId" />" />
									</td>

                                </tr>

								<tr>
									<td height="27" align="left" style="padding-left:110px;align:right">
									<label class="label"><bean:message key="jobs.password"	bundle="jobs" /></label><label class="error"></label><b>:</b></td>
									<td height="27"style="align: left; width: 30; padding-left: 10px">
									<input type="password" id="ecoPassword" name="ecoPassword"
										class="medium1" dojoType="dijit.form.ValidationTextBox" onclick="resetFieldById('ecoPassword')"
										required="true" trim="true" ucfirst="true" value="<bean:write name="ecoSystemForm" property="ecoPassword" />"
										/>
									</input></td>
								</tr>

                                <tr>
                                    <td height="13" colspan="2" align="left" class="bodytext"></td>
                                </tr>

								<tr>
									<td height="13" align="left" style="padding-left:110px;align:right"><label class="label"><bean:message key="jobs.GenerateKMLKMZ" bundle="jobs" /></label>&nbsp;<b>:</b></td>
									<td style="padding-left: 9px;align: right;">
										<select id="generateKML"
												dojoType="dijit.form.FilteringSelect"
												name="generateKML" autoComplete="false"
												<%if (readOnly) {%> disabled=true <%}%>
												value="<bean:write name="ecoSystemForm" property="generateKML"/>">
												<option value=""><bean:message key="validation.msg.select" bundle="splchvalidation"/></option>
												<option value="generateKML"><bean:message key="jobs.KML" bundle="jobs" /></option>
												<option value="generateKMZ"><bean:message key="jobs.KMZ" bundle="jobs" /></option>
											</select>
										</td>
								</tr>
								<tr>
									<td height="13" colspan="2" align="left" class=""></td>
								</tr>
								<tr>
							<td style="padding-left:100px;align:right"><label class="label"><bean:message	key="jobs.parameters" bundle="jobs" />:</label>
									</td>
									<td height="27" style="align: left; width: 300;">&nbsp;&nbsp;&nbsp;<textarea
										dojoType="dijit.form.SimpleTextarea" rows="5" cols="5"
										id="parameterValueMap" style="width: 192px;"
										name="parameterValueMap"><bean:write
										name="ecoSystemForm" property="parameterValueMap"/></textarea>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;<sub><bean:message key="jobs.suggestParams" bundle="jobs" /></sub></td>
								</tr>

								<tr>
									<td style="padding-left: 30">&nbsp;</td>
									<td style="padding-left: 30">&nbsp;</td>
								</tr>
								<tr>
									<td align="right" style="width:140;padding-left: 30px;padding-top: 9px">
									<label class="label"><bean:message	key="jobs.dependentEcosystems" bundle="jobs" />:</label></td>
									<td>
									<table border="0" id="mytable1">
										<%
											if (ecoCount == -1 || ecoCount == 0) {
										%>
										<script>
                                                                    addedRowsNumbers = '1,' ;
                                                                </script>
										<tr>
											<td style="padding-left:8" colspan="2" nowrap="nowrap"><select
												id="ecoSystem1" dojoType="dijit.form.FilteringSelect"
												name="ecoSystem1" autoComplete="false" <%if (readOnly) {%> disabled=true <%}%> >
												<option value=""><bean:message key="validation.msg.select" bundle="splchvalidation"/></option>
												<logic:present name="ecoModelFileList" scope="request">
													<logic:iterate name="ecoModelFileList"
														id="ecoModelFileDtls">
														<option
															value="<bean:write name="ecoModelFileDtls" property="ecoSystemFilePath"/>"><bean:write
															name="ecoModelFileDtls" property="ecoSystemName"/>
															 </option>
													</logic:iterate>
												</logic:present>
											</select>&nbsp;<label class="label"><bean:message
                                        key="jobs.run" bundle="jobs" />:</label><select id="selectedEcoSystem1"
												dojoType="dijit.form.FilteringSelect"
												name="selectedEcoSystem1" autoComplete="false"
												<%if (readOnly) {%> disabled=true <%}%>
												value="<bean:write name="ecoSystemForm" property="selectedEcoSystem1"  />">
												<option value=""><bean:message key="validation.msg.select" bundle="splchvalidation"/></option>
												<option value="1"><bean:message key="jobs.runmode.once" bundle="jobs" /></option>
												<option value="2"><bean:message key="jobs.runmode.everytime" bundle="jobs" /></option>
											</select> &nbsp;<label class="label"><bean:message
                                        key="jobs.parameters" bundle="jobs" />: </label><textarea
												<%if (readOnly) {%> disabled=true <%}%>
												dojoType="dijit.form.SimpleTextarea" rows="5" cols="30"
												id="ecoSystem1ParameterValueMap"
												style="height: 1.7em; width: 20em"
												name="ecoSystem1ParameterValueMap"></textarea> <sub><bean:message
                                        key="jobs.suggestParamsinshort" bundle="jobs" /></sub>
											<%
												if (!readOnly) {
											%> <a href="#"
												onclick="javascript:addRow('mytable1');return false;"><img
												src="<%=ServerUtils.getContextName(request)%>/images/portal/icon_plus_1.png" /></a> <%
 	}
 %>
                                            </td>
                                        </tr>
                                        <%
                                            } else {
                                        %>
                                        <%
                                            java.util.List ecoModelFileList = (java.util.List) request.getAttribute("ecoModelFileList");
                                                        for (int i = 0; i < ecoCount; i++) {
                                                            String ecoName = dependEcosystemMap
                                                                    .getEcosystementry(i).getName();
                                                            String ecotype = dependEcosystemMap
                                                                    .getEcosystementry(i).getType();
                                                            String ecoParamMap = dependEcosystemMap
                                                                    .getEcosystementry(i).getParameters();
                                                            int suffixInt = i + 1;
                                        %>


                                        <tr>
                                            <td colspan="2" nowrap="nowrap" valign="top"><select
                                                id="ecoSystem<%=suffixInt%>"
                                                dojoType="dijit.form.FilteringSelect"
                                                name="ecoSystem<%=suffixInt%>" autoComplete="false">
                                                <option value=""><bean:message key="validation.msg.select" bundle="splchvalidation"/></option>
                                                <%
                                                    for (int count = 0; count < ecoModelFileList.size(); count++) {
                                                                        com.enterprisehorizons.magma.jobs.beans.JobBean jobBean = (com.enterprisehorizons.magma.jobs.beans.JobBean) ecoModelFileList
                                                                                .get(count);
                                                %>

												<option value="<%=jobBean.getEcoSystemFilePath()%>"
													<%if (ecoName.equals(jobBean
										.getEcoSystemFilePath())) {%>
													selected="selected" <%}%>><%=jobBean.getEcoSystemName()%></option>
												<%
													}
												%>
											</select> <select id="selectedEcoSystem<%=suffixInt%>"
												dojoType="dijit.form.FilteringSelect"
												name="selectedEcoSystem<%=suffixInt%>" autoComplete="false"
												value="">
												<option value="" <%if (ecotype.equals("")) {%>
													selected="selected" <%}%>></option>
												<option value="1" <%if (ecotype.equals("1")) {%>
													selected="selected" <%}%>><bean:message key="jobs.runmode.once" bundle="jobs" /></option>
												<option value="2" <%if (ecotype.equals("2")) {%>
													selected="selected" <%}%>><bean:message key="jobs.runmode.everytime" bundle="jobs" /></option>
											</select>&nbsp;<bean:message
                                        key="jobs.parameters" bundle="jobs" />:</label><b>:</b><textarea
												dojoType="dijit.form.SimpleTextarea" rows="5" cols="30"
												id="ecoSystem<%=suffixInt%>ParameterValueMap"
												style="height: 1.7em; width: 20em;"
												name="ecoSystem<%=suffixInt%>ParameterValueMap"><%=ecoParamMap%></textarea>
											<sub><bean:message
                                        key="jobs.suggestParamsinshort" bundle="jobs" />: </label></sub> <%
 	if (!readOnly) {
 %> <%
    if (suffixInt == 1) {
 %>
                                            <a href="#" onclick="javascript:addRow('mytable1')"> <img
                                                src="<%=ServerUtils.getContextName(request)%>/images/portal/icon_plus_1.png" /> <%
    } else {
 %>
                                            <img src="<%=ServerUtils.getContextName(request)%>/images/portal/icon_dash_1.png" 
                                                style="cursor: hand;"
                                                onclick="javascript:removeRow('mytable1',this.parentNode.parentNode,<%=suffixInt%>)" />

                                            <%
                                                }
                                            %> <%
    }
 %>
                                            </td>
                                        </tr>
                                        <%
                                            }
                                                    }
                                        %>



                                    </table>
                                    </td>
                                    <td height="27"
                                        style="align: left; width: 550; padding-top: 0;" valign="top">
                                    </td>
                                </tr>
                            </table>
                            </td>
                        </tr>
                    </table>
                    </td>
                </tr>
                <tr>
                    <td height="13" colspan="2" align="left" class="bodytext"></td>
                </tr>
            </table>
            </div>
            </td>
            <td style="padding-left: 30">&nbsp;</td>
        </tr>
    </table>
    </td>
    </tr>
    <TR>
        <td height="10px">&nbsp;</td>
    </TR>
   
	
		</table>
		</td>
	</tr>


	</table>


	<table id= "buttonTbl" width = "100%">
	<tr>
        <td>

        <table border="0" width="100%" id="" cellspacing="0" cellpadding="0"
            align="left">
            <tr class="barColor">
                <td colspan="7" align="center">
                <button dojoType="dijit.form.Button" type="button" id="idHome"
                    name="btnHome" value="<bean:message key="jobs.home" bundle="jobs" />"
                    onClick="window.location= '<%=ServerUtils.getContextName(request)%>/adminMain.do' ">
                <bean:message key="jobs.home" bundle="jobs" /></button>
                <button dojoType="dijit.form.Button" type="button" id="idBack"
                    name="btnBack" value="<bean:message key="jobs.back" bundle="jobs" />" onClick="return submitForm(this);">
                <bean:message key="jobs.back" bundle="jobs" /></button>
                <%
                    if (!readOnly) {
                %>
                <button dojoType="dijit.form.Button" id="idUpdate" name="btnupdate"
                    value="<bean:message key="jobs.save" bundle="jobs" />"
                    onClick="assignTotalNoOfEcos();return submitForm(this); ">
                <bean:message key="jobs.save" bundle="jobs" /></button>
                <button dojoType="dijit.form.Button" id="idReset" name="btnReset" onClick="resetValues()" >   
                <bean:message key="jobs.reset" bundle="jobs"/>
            </button>

                <%
                    }
                %> <%
    if (tmp == "delete") {
 %>
				<button dojoType="dijit.form.Button" id="idDelete" name="btndelete"
					value="<bean:message key="jobs.delete" bundle="jobs" />"
					onclick="confirmationDialog('Do you want to delete the record?');">
				<bean:message key="jobs.delete" bundle="jobs" /></button>

				<%
					}
				%>
								
			
            
				</td>
			</tr>
	</table>
	<input type="hidden" id ="totalNoOfEcoSystems" name="totalNoOfEcoSystems" value="" />
		 <input type="hidden" name="operation" value="">
		           <input type="hidden" name="commonFlagIndicator" id="commonFlagIndicator" value="<%= formObj.getCommonJobFlag()%>">
		 
</html:form>   
<script>

function resetFieldById(fieldName) {
document.getElementById(fieldName).value="";
}

function resetValues(){	 
	window.location.href="<%=ServerUtils.getContextName(request)%>/configureJobSchdAction.do?operation=view&action=edit&jobsType=<%=String.valueOf(JobConstants.JOB_TYPE_ECOSYSTEM)%>&selEcoSystem1=<bean:write name="ecoSystemForm" property="id"/>";	 	   
}  

dojo.addOnLoad(loadFormValues); 
    								
	function loadFormValues(){
		 dijit.byId('ecoSystemName').setValue('');	
		document.getElementById('encryptedPassword').value = '<bean:write name="ecoSystemForm" property="encryptedPassword" />';
		document.getElementById('virtualPassword').value = '<bean:write name="ecoSystemForm" property="ecoPassword" />';
		dijit.byId('startTimeType').setValue('<bean:write name="ecoSystemForm" property="startTimeType" />');
        dojo.byId('startTime').setAttribute('value', '<bean:write name="ecoSystemForm" property="startTime" />');
        dijit.byId('startDate').setDisplayedValue('<bean:write name="ecoSystemForm" property="startDate" />');                   
        document.getElementById('startTime').value= '<bean:write name="ecoSystemForm" property="startTime" />';
		dijit.byId('generateKML').setValue('<bean:write name="ecoSystemForm" property="generateKML" />');
		document.getElementById('startDate').value= '<bean:write name="ecoSystemForm" property="startDate" />';
		
		dojo.byId('interval').value = '<bean:write name="ecoSystemForm" property="interval" />';
		dijit.byId('intervalType').setValue('<bean:write name="ecoSystemForm" property="intervalType" />');
        var status = false; 
		dijit.byId('jobName').setAttribute('disabled', true);
		dijit.byId('startTimeType').setAttribute('disabled', true);
		dijit.byId('jobType').setAttribute('disabled', true);
        if(<%=readOnly%>){
			status = true;              
        }
        dijit.byId('description').setAttribute('disabled', status);         
        if(status){
			dijit.byId('startDate').setAttribute('disabled', status);   
		} 
		if(dijit.byId('startTimeType').value == START_JOB_AFTER_SERVER_START){
			if('<bean:write name="ecoSystemForm" property="afterServerStartsPattern" />' == 'EVERY'){
				dijit.byId('afterServerStartsPatternEvery').setAttribute('checked',true);
				dijit.byId('afterServerStartsPatternEvery').setAttribute('disabled',status);
				dijit.byId('afterServerStartsPatternOnce').setAttribute('disabled',status);
			}else if('<bean:write name="ecoSystemForm" property="afterServerStartsPattern" />' == 'ONCE'){
				dijit.byId('afterServerStartsPatternOnce').setAttribute('checked',true);
				dijit.byId('afterServerStartsPatternEvery').setAttribute('disabled',status);
				dijit.byId('afterServerStartsPatternOnce').setAttribute('disabled',status);
			}
		}else if(dijit.byId('startTimeType').value == START_JOB_ABSOLUTE){
			dijit.byId('cronTypeId').setValue('<bean:write name="ecoSystemForm" property="cronTypeId" />');
			dijit.byId('absoluteInterval').setValue('<bean:write name="ecoSystemForm" property="absoluteInterval" />');
			dijit.byId('cronTypeId').setAttribute('disabled', status);
			dijit.byId('absoluteInterval').setAttribute('disabled', status);
			populateAbsoluteStartTypeValues('<bean:write name="ecoSystemForm" property="cronTypeId" />', status);
		}else if(dijit.byId('startTimeType').value == START_JOB_EXPRESSION){
			dijit.byId('expression').setValue('<bean:write name="ecoSystemForm" property="expression" />');
			dojo.byId('expression').setAttribute('disabled', status);			
		}

		dijit.byId('cronTypeId').setAttribute('disabled', status);   
		dijit.byId('absoluteInterval').setAttribute('disabled', status);
		
        dijit.byId('startTime').setAttribute('disabled', status);   
        dijit.byId('interval').setAttribute('disabled', status);    
        dijit.byId('intervalType').setAttribute('disabled', status);    
        dijit.byId('ecoSystemName').setAttribute('disabled', status);   
        dijit.byId('ecoUserId').setAttribute('disabled', status);   
        dijit.byId('ecoPassword').setAttribute('disabled', status); 
        dijit.byId('parameterValueMap').setAttribute('disabled', status);   
		dijit.byId('absoluteDailyPatternEveryDay').setAttribute('disabled',status);
		dijit.byId('absoluteDailyPatternEveryWeekDay').setAttribute('disabled',status);
        var total = <%=ecoCount%>;  
		for(count = 1;count <= total; count++) {
			addedRowsNumbers    = addedRowsNumbers + count +',';
            var econame = 'ecoSystem' + count;
            var ecoselected =   'selectedEcoSystem' + count;
            var ecoParams =   'selectedEcoSystem' + count;  
            var ecoParamValueMap = 'ecoSystem'+count+'ParameterValueMap';
            dijit.byId(econame).setAttribute('disabled', status);  
            dijit.byId(ecoselected).setAttribute('disabled', status);  
            dijit.byId(ecoParams).setAttribute('disabled', status); 
            try{
				dijit.byId(ecoParamValueMap).setDisabled(status);
            }catch(er){
			}
        }

        // if('<bean:write name="ecoSystemForm" property="generateKML" />' == 'generateKML')
		//	document.forms[0].generateKML[0].checked = true;
        //if('<bean:write name="ecoSystemForm" property="generateKMZ" />' == 'generateKMZL')
		//	document.forms[0].generateKML[1].checked = true;

		dijit.byId('ecoSystemName').setValue('<bean:write name="ecoSystemForm" property="ecoSystemName" />');	
        hideProgressDialog();
}

        function  dodelete(){
 		   document.getElementById('operation').value = 'deleteJob';
 			 document.forms[0].action='configureJobSchdAction.do';
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
		if('<bean:write name="ecoSystemForm" property="absoluteDailyPattern" />' == 'selectedAbsoluteDailyPatternEveryDay'){
			dijit.byId('absoluteDailyPatternEveryDay').setAttribute('checked',true);
			dijit.byId('absoluteDailyInterval').setValue('<bean:write name="ecoSystemForm" property="absoluteDailyInterval" />');
			dijit.byId('absoluteDailyPatternEveryWeekDay').setAttribute('disabled',status);
			dijit.byId('absoluteDailyInterval').setAttribute('disabled',status);
		}else if('<bean:write name="ecoSystemForm" property="absoluteDailyPattern" />' == 'selectedAbsoluteDailyPatternEveryWeekDay'){
			dijit.byId('absoluteDailyPatternEveryWeekDay').setAttribute('checked',true);
			dijit.byId('absoluteDailyInterval').setAttribute('disabled',status);
			dijit.byId('absoluteDailyPatternEveryDay').setAttribute('disabled',status);
		}
	}

	if(cronTypeId == REPEAT_TRIGGER_IN_WEEKLY){
		if('<bean:write name="ecoSystemForm" property="absoluteWeeklyPatternSunday" />' == 'absoluteWeeklyPatternSunday'){
			dijit.byId('absoluteWeeklyPatternSunday').setAttribute('checked',true);				
		}

		if('<bean:write name="ecoSystemForm" property="absoluteWeeklyPatternMonday" />' == 'absoluteWeeklyPatternMonday'){
			dijit.byId('absoluteWeeklyPatternMonday').setAttribute('checked',true);				
		}

		if('<bean:write name="ecoSystemForm" property="absoluteWeeklyPatternTuesday" />' == 'absoluteWeeklyPatternTuesday'){
			dijit.byId('absoluteWeeklyPatternTuesday').setAttribute('checked',true);			
		}
		
		if('<bean:write name="ecoSystemForm" property="absoluteWeeklyPatternWednesday" />' == 'absoluteWeeklyPatternWednesday'){
			dijit.byId('absoluteWeeklyPatternWednesday').setAttribute('checked',true);
		}

		if('<bean:write name="ecoSystemForm" property="absoluteWeeklyPatternThursday" />' == 'absoluteWeeklyPatternThursday'){
			dijit.byId('absoluteWeeklyPatternThursday').setAttribute('checked',true);			
		}

		if('<bean:write name="ecoSystemForm" property="absoluteWeeklyPatternFriday" />' == 'absoluteWeeklyPatternFriday'){
			dijit.byId('absoluteWeeklyPatternFriday').setAttribute('checked',true);				
		}

		if('<bean:write name="ecoSystemForm" property="absoluteWeeklyPatternSaturday" />' == 'absoluteWeeklyPatternSaturday'){
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
		if('<bean:write name="ecoSystemForm" property="absoluteMonthlyPattern" />' == 'selectedAbsoluteMonthlyPatternEveryDay'){
			dijit.byId('absoluteMonthlyPatternEveryDay').setAttribute('checked',true);
			dijit.byId('absoluteMonthlyDayInterval').setValue('<bean:write name="ecoSystemForm" property="absoluteMonthlyDayInterval" />');
		dijit.byId('absoluteMonthlyWeekType').setAttribute('disabled', true);
		dijit.byId('absoluteMonthlyDayType').setAttribute('disabled', true);
			
		}else if('<bean:write name="ecoSystemForm" property="absoluteMonthlyPattern" />' == 'selectedAbsoluteMonthlyPatternEveryMonth'){
			dijit.byId('absoluteMonthlyPatternEveryMonth').setAttribute('checked',true);		
			dijit.byId('absoluteMonthlyWeekType').setValue('<bean:write name="ecoSystemForm" property="absoluteMonthlyWeekType" />');
			dijit.byId('absoluteMonthlyDayType').setValue('<bean:write name="ecoSystemForm" property="absoluteMonthlyDayType" />');
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
		if('<bean:write name="ecoSystemForm" property="absoluteYearlyPattern" />' == 'selectedAbsoluteYearlyPattern'){
			dijit.byId('absoluteYearlyPatternEveryMonth').setAttribute('checked',true);
			dijit.byId('absoluteYearlyMonthType').setValue('<bean:write name="ecoSystemForm" property="absoluteYearlyMonthType" />');
			dijit.byId('absoluteYearlyDayInterval').setValue('<bean:write name="ecoSystemForm" property="absoluteYearlyDayInterval" />');
			dijit.byId('absoluteYearlyMonthlyWeekType').setAttribute('disabled',true);
		    dijit.byId('absoluteYearlyMonthlyDayType').setAttribute('disabled',true);
		    dijit.byId('absoluteYearlyMonthlyMonthType').setAttribute('disabled',true);
		}else if('<bean:write name="ecoSystemForm" property="absoluteYearlyPattern" />' == 'selectedAbsoluteYearlyPatternEveryMonthDay'){
			dijit.byId('absoluteYearlyPatternEveryMonthDay').setAttribute('checked',true);		
			dijit.byId('absoluteYearlyMonthlyWeekType').setValue('<bean:write name="ecoSystemForm" property="absoluteYearlyMonthlyWeekType" />');
			dijit.byId('absoluteYearlyMonthlyDayType').setValue('<bean:write name="ecoSystemForm" property="absoluteYearlyMonthlyDayType" />');
			dijit.byId('absoluteYearlyMonthlyMonthType').setValue('<bean:write name="ecoSystemForm" property="absoluteYearlyMonthlyMonthType" />');
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

