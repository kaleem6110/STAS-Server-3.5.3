<%@ page import="com.spacetimeinsight.stas.config.GenericConfigurationManager,com.enterprisehorizons.magma.config.dbadmin.ModelConfigConstants" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ page import="java.util.*, com.spacetimeinsight.magma.job.JobConstants, com.enterprisehorizons.magma.job.bd.JobScheduleDelegator,
com.spacetimeinsight.magma.job.bean.StiJobBean,com.spacetimeinsight.stas.datasource.*,
com.spacetimeinsight.admin.server.util.PropertyFileUtils,com.spacetimeinsight.db.scheduler.model.ServerEnvMaster,com.spacetimeinsight.db.model.util.DataModelsCache, com.spacetimeinsight.db.scheduler.model.*,com.enterprisehorizons.magma.server.admin.AdminConfigUtils"%>
<%@ include file="/common/dojo.jsp" %>
<%@page import="com.enterprisehorizons.magma.server.util.ServerUtils"%>
<script type="text/javascript" src="js/ecoweb.js" ></script>
<script type="text/javascript" src="js/jobs.js"></script>
<%@ taglib prefix="tiles" uri="/tags/struts-tiles"%>


<%
boolean  showCheckBox = false;
List  allServerEnvList = (List) DataModelsCache.getInstance().retrieve(ServerEnvMaster.class.getName());
if(allServerEnvList != null && allServerEnvList.size() > 1){
showCheckBox = true;
}

String checkboxstatusStr = (String)request.getAttribute("checkboxstatus");
boolean  checkboxstatus = false;

if(checkboxstatusStr != null && "true".equals(checkboxstatusStr)){
checkboxstatus = true;
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





<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Add Job</title>


<script type="">
dojo.require("dijit.form.SimpleTextarea");

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
var	CRON_WEEK_TYPE_FIRST = "<%= JobConstants.CRON_WEEK_TYPE_FIRST +"" %>";
var	CRON_WEEK_TYPE_SECOND = "<%= JobConstants.CRON_WEEK_TYPE_SECOND +"" %>";
var	CRON_WEEK_TYPE_THIRD ="<%= JobConstants.CRON_WEEK_TYPE_THIRD +"" %>";
var	CRON_WEEK_TYPE_FOURTH ="<%= JobConstants.CRON_WEEK_TYPE_FOURTH +"" %>";
var	CRON_WEEK_TYPE_LAST = "<%= JobConstants.CRON_WEEK_TYPE_LAST +"" %>";



var countrownumber = 0;
 function chkDescriptionLen()
 {
     if(dijit.byId('description').attr('value').length > 500)
     {

         showEmptyDialog("Length of text description should be less than 500", "Error");
		 	dijit.byId('description').focus();
			return false;

     }
			return true;

  }

function chkubmit(obj){
	if(!chkDescriptionLen()){
		return false;
	}

	var selectedJobType = dijit.byId('jobType').value;
	if(selectedJobType == JOB_TYPE_ECOSYSTEM){
		if(!isValidUsernamePassword(dijit.byId('ecoUserId').value,dijit.byId('ecoPassword').value)){
			showEmptyDialog("User ID should not be blank on providing password", "Error");
			return false;
		}
	}else if(selectedJobType == JOB_TYPE_URLEXTRACTOR){
		if(!isValidUsernamePassword(dijit.byId('userId').value,dijit.byId('password').value)){
			showEmptyDialog("User ID should not be blank on providing password", "Error");
			return false;
		}
	}

	return checkStartTime(obj)
}

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

  function delAddedURLRowsNumbers(tmp)
{
    var rowNumbersArray = new Array();
    rowNumbersArray = addedURLRowsNumbers.split(',');
    for(countArr = 0;countArr < rowNumbersArray.length;countArr++)
    {
        var tmp1 = rowNumbersArray[countArr];
        if(tmp == tmp1)
        {
            rowNumbersArray[countArr] = 'n';
            break;
        }
    }
addedURLRowsNumbers = rowNumbersArray.join(",");
}


function clearTextArea()
{
		dijit.byId('description').setValue('');
        dijit.byId('parameterValueMap').setValue('');
        dijit.byId('timerParameterValueMap').setValue('');
        dijit.byId('tagQuery').setValue('');
        dijit.byId('pilistener').setValue('');
		dijit.byId('startTimeType').setValue('');
		dijit.byId('startTimeType').reset();
        dijit.byId('jobType').setValue(dijit.byId('jobType').value);
		dijit.byId('ecoSystemName').setValue('');
		dijit.byId('ecoSystem1').setValue('');
		dijit.byId('selectedEcoSystem1').setValue('');
		dijit.byId('dataSource').setValue('');
        document.getElementById('result').innerHTML='';
		if(dijit.byId('jobType').attr('value') == JOB_TYPE_CLEAR_CACHE)
		{
			if(document.getElementById('combobox2') != null && document.getElementById('combobox1') != null)
			{
				MoveItems('L', 1);
				dijit.byId('jobType').setValue(JOB_TYPE_CLEAR_CACHE);
			}
		}
}

function checkStartTime(obj) {

	if(isAllCharactersSame(dijit.byId('password').value)){
		showEmptyDialog("Password should not contain same characters", "Error");
		return false;
	}

	return submitForm(obj);
}

function checkIntervalValue(selectedObject){
	var intervalStatus =  isFraction(selectedObject.value,"Interval must be integer");

}

</script>

<script language="javascript">
var addedRowsNumbers = "1,";
var addedURLRowsNumbers = "1,";
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


function addRow(table_id)
{

if(table_id == 'mytable1')
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
 //ecosystemName = "ecoSystem" + (rows_count+1 );
 //selectedEcoSystemName = "selectedEcoSystem" + (rows_count +1);
  try {
    var newRow = tbl.insertRow(rows_count);
    var newCell = newRow.insertCell(0);


	    var input = ' &nbsp;&nbsp;<select  id="'+ ecosystemName+'"  name="' + ecosystemName + '"    autoComplete="false"><option value=""><bean:message key="validation.msg.select" bundle="splchvalidation"/></option>' +
    <logic:present name="ecoModelFileList" scope="request">
    <logic:iterate name="ecoModelFileList" id="ecoModelFileDtls">
    '<option value="<bean:write name="ecoModelFileDtls" property="ecoSystemFilePath"/>"><bean:write name="ecoModelFileDtls" property="ecoSystemName"/></option>'+
    </logic:iterate>
    </logic:present>
    '</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select id="'+selectedEcoSystemName+'"  name="'+selectedEcoSystemName +'" autoComplete="false"><option value=""><bean:message key="validation.msg.select" bundle="splchvalidation"/></option><option value="1">Once</option><option value="2">Everytime</option></select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<textarea dojoType="dijit.form.SimpleTextarea" rows="5" cols="30" style="height:1.7em;width:20em" id="'+ecosystemName+'ParameterValueMap" name="'+ecosystemName+'ParameterValueMap"></textarea><a href="#" onclick="javascript:removeRow(\'mytable1\',this.parentNode.parentNode,'+ prefix +')"><img src="<%=ServerUtils.getContextName()%>/images/portal/icon_dash_1.png"  / ></a>';

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

  } catch (ex) {
    //if exception occurs
    alert(ex);
  }

//if(dijit.byId(eval(ecosystemName)) != null)
//{
//  dijit.byId(eval(ecosystemName)).store = columnStore;
//}
}
else
{

columnList = {identifier:"column",items:dbColumnsArray};
columnStore = new dojo.data.ItemFileReadStore({data: columnList});
var tbl = document.getElementById(table_id);
// counting rows in table
 rows_count = tbl.rows.length;
  if (initial_count[table_id] == undefined)
  {
    // if it is first adding in this table setting initial rows count
    initial_count[table_id] = rows_count;
  }
  // determining real count of added fields
    var text = 'Text field';
  var tFielsNum = rows_count - initial_count[table_id];
  if (rows_limit!=0 && tFielsNum >= rows_limit) return false;
var rowNumbersArray = new Array();
rowNumbersArray = addedURLRowsNumbers.split(',');
var prefix = rowNumbersArray.length;
addedURLRowsNumbers = addedURLRowsNumbers + prefix + ",";
 var urlName = "url" +  +  prefix;

  try {
    var newRow = tbl.insertRow(rows_count);
    var newCell = newRow.insertCell(0);
    var input = '<input type="text"    id="'+ urlName +'" name="' + urlName + '" style="width:300px;height:1.7em;" dojoType="dijit.form.ValidationTextBox" required="true" trim="true" ucfirst="true"   />' +
     '<a href="#" onclick="javascript:removeURLRow(\'mytable2\',this.parentNode.parentNode,'+ prefix +');"><img src="<%=ServerUtils.getContextName()%>/images/portal/icon_dash_1.png"  / ></a>';
    newCell.innerHTML =input;
//    var newCell = newRow.insertCell(1);
//    newCell.innerHTML = input;
//    var newCell = newRow.insertCell(2);
//    newCell.innerHTML = remove;

  } catch (ex) {
    //if exception occurs
    alert(ex);
  }
}
countrownumber++;
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

function removeURLRow(tbl,row,prefix)
{
  var table = document.getElementById(tbl);
  var rownum = row.rowIndex;
  try
  {
        table.deleteRow(rownum);
        delAddedURLRowsNumbers(prefix);
  } catch (ex) {
    alert(ex);
  }

}


function assignTotalNoOfEcos()
{
        //var total = document.getElementById('mytable1').rows.length;
		if(document.getElementById('totalNoOfEcoSystems') != null)
			document.getElementById('totalNoOfEcoSystems').value = addedRowsNumbers.split(',');
        return true;
}



function assignTotalNoOfURLS()
{
	if(document.getElementById('totalNoOfUrls') != null)
        document.getElementById('totalNoOfUrls').value = addedURLRowsNumbers.split(',');
    return true;
}

</script>

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

function updateDIVStyles(status){
	document.getElementById('afterServerStartDivDtls').style.display = '';
	document.getElementById('expressionDivDtls').style.display = '';
	document.getElementById('absoluteDivDtls').style.display = '';
}

function resetSchedulerFormValues(typeOfStartType){
	dijit.byId('startDate').setValue('');
	dijit.byId('startTime').setValue('');
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
</head>
<body class="tundra bodybg"  style="overflow-x:hidden;overflow-y:auto">

<html:form action="/addJobAction.do" method="post">


<input type="hidden"  name="encryptedPassword" id="encryptedPassword" />
<input type="hidden"  name="virtualPassword" id="virtualPassword" />

<!---------------------------------------------------------------------------------------------------------------------------------------------------------------------------- -->
<%


    com.spacetimeinsight.db.config.model.Datasource[] datasourceArray =  GenericDatasourceManager
                   .getInstance().getDatasourceNames(IDatasourceConstants.DATABASE_TYPES_PI);

//com.spacetimeinsight.db.config.model.Datasource[] datasourceArray =  GenericDatasourceManager
         //           .getInstance().getAllDatasource();

String showCommonJob = GenericConfigurationManager.getInstance().getProperty("admin","IS_CLUSTERED" );


%>
<!---------------------------------------------------------------------------------------------------------------------------------------------------------------------------- -->
<table width="70%"  >
<tr><td width="100%" >
<table width="100%" cellspacing="0" cellpadding="0" align="center"    >
            <tr>
                <td  class="pageTitle paddingTitle">
                 <bean:message key="jobs.addJob" bundle="jobs"/>
                </td>
            </tr>
            <tr>
               <td class="paddingTitleDesc">
                 <strong><bean:message key="jobs.addJob.description" bundle="jobs"/></strong>
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
                <td >
<!--  ----       -              ----------------------------------------------------------------------------------------- -->

<table cellspacing="0" cellpadding="0" width="100%" border="0">
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
							dojoType="dijit.form.ValidationTextBox" required="true" trim="true" ucfirst="true"  onblur="loadData('CheckTimerName',this, setCheckTimerNameStatus, 'insert');"/>
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
									<select id="startTimeType" dojoType="dijit.form.FilteringSelect"  name="startTimeType" autoComplete="false" onChange="showConcernDiv()">
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
	<td colspan="8" align="left" class="bodytext"></td>
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
						<textarea dojoType="dijit.form.SimpleTextarea" rows="5" cols="30" id="description" class="medium1" style="height:5.7em;" name="description"  ></textarea>
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
						<select  id="jobType" dojoType="dijit.form.FilteringSelect"name="jobType" autoComplete="false"onchange="javascript:statusChange();">
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
								<html:checkbox  name="AddJobForm" property="commonJobFlag" onclick="checkBoxAlert(this);" disabled="<%=checkboxstatus%>"></html:checkbox>
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

<!--
<tr>
<td style="padding-left:65px;align:left;" >
	<table cellspacing="0" cellpadding="0" border="0" align="left" width="100%">
		<tr>
			<td>
			<table cellpadding="10"  border="0" width="100%">
				<tr>
					<td height="27" align="left" width="100px">
						 <label class="label"><bean:message key="jobs.intervalType" bundle="jobs"/></label><label class="error">*</label><b>:</b>
					</td>

					<td>
						<select  id="intervalType" dojoType="dijit.form.FilteringSelect"  name="intervalType" autoComplete="false" >
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
			</table>
			</td>

		</tr>
	</table>
</td>
</tr>
-->




            <tr>
                    <td style="padding-left:67px;">

        <!--  File Clean up Timer   -->
                 <div id="div4" style="display:none;" >
                     <table class="tableBgColor" border="0">
                        <tr height="10px">
                            <td>&nbsp;

                            </td>
                        </tr>
                        <tr>
                            <td class="pageTitle " style="padding-left:30px;align:left;">
                                <bean:message key="jobs.filecleanupFieldset" bundle="jobs"/>
                            </td>
                        </tr>
                        <tr>
                           <td class="paddingTitleDesc" style="padding-left:31px;align:left;">
                                 <strong><bean:message key="jobs.fileCleanup.description" bundle="jobs"/></strong>
                            </td>
                        </tr>
                        <tr>
                            <td height="13" colspan="8" align="left" class=""></td>
                        </tr>
                        <tr>
                            <td style="padding-left:0px;">
                                <table cellspacing="0" cellpadding="1" width="100%" >
                                    <tr>
                                        <td style="padding-left:9px;align:left;">
                                            <table cellspacing="0" cellpadding="0" border="0" align="left" >
                                                <tr>
                                                    <td>
                                                            <table cellpadding="10" cellspacing="" border="0" width="100%">
                                                                <tr>
                                                                     <td height="27" align="right"  style="padding-right:10px;width:90px;" nowrap="nowrap" >
                                                                      <label class="label"><bean:message key="jobs.directory" bundle="jobs"/></label><label class="error">*</label><b>:</b>
                                                                    </td>
                                                                    <td style="align:left;" width="120px"  >
                                                                      <input type="text"  id="directory" name="directory" class="medium" style="height:1.7em;"
                                                    dojoType="dijit.form.ValidationTextBox" required="true" trim="true" ucfirst="true"  />
                                                                    </td>
                                                                </tr>
                                                            </table>
                                                    </td>
                                                    <td style="width:60px;">
                                                    </td>
                                                    <td align="center">
                                                            <table cellpadding="" cellspacing="0" border="0">
                                                                <tr>
                                                                    <td height="27" align="right" style="width:74;">
                                                                        <label class="label"><bean:message key="jobs.fileTypes" bundle="jobs"/></label><label class="error">*</label><b>:</b>
                                                                    </td>
                                                                    <td style="padding-left:10px; align:left;" width="40px">
                <input type="text"  id="selectedFileType" name="selectedFileType" style="height:1.7em;"
                                                                                        dojoType="dijit.form.ValidationTextBox" required="true" trim="true" ucfirst="true"  />

                                                                    </td>
                                                                     <td><bean:message key="jobs.fileType" bundle="jobs"/></td>
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
                        <td style="padding-left:20px;">
                            <table cellspacing="0" cellpadding="1" width="100%" border="0" align="left">
                                <tr>
                                        <td style="padding-left:10px;align:left;">
                                            <table cellspacing="0" cellpadding="0" border="0" align="left" >
                                                <tr>
                                                    <td>
                                                            <table cellpadding="0" cellspacing="" border="0" width="100%">
                                                                <tr>
                                                                     <td height="27" align="left"  style="padding-right:10px;width:158px;" nowrap="nowrap" >
                                                                        <label class="label"><bean:message key="jobs.includeSubDirectory" bundle="jobs"/></label><b>:</b>
                                                                    </td><td style = "align = left">
        <html:checkbox  name="AddJobForm" property="includeSubdirectories"  ></html:checkbox> </td>
		</tr>
                                                            </table>
                                                    </td>

                                                    <td style="width:53px;">
                                                    </td>
                                                    <td align="right" >
                                                            <table cellpadding="0" cellspacing="0" border="0">
                                                                <tr>
                                                                    <td height="27" align="right" style="width:170;">
                                                                    <label class="label"><bean:message key="jobs.fileCleanip.noOfDaysOld" bundle="jobs"/></label><b>:</b>
                                                                    </td>
                                                                    <td style="padding-left:8px; align:left;" width="45px">
                                                                        <input type="text"  id="noOfDaysOld" name="noOfDaysOld" style="height:1.7em;"
                                                                                        dojoType="dijit.form.NumberTextBox" constraints="{min:0,max:365,places:0}"  invalidMessage="The value entered is invalid" required="false" trim="true" ucfirst="true"  />

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

                    <tr></tr>
                    </table>
        </div>



<!--  Custom-->

        <div id="div5" style="display:none;" >
            <table class="tableBgColor">
                <tr height="10px">
                    <td>&nbsp;

                    </td>
                </tr>
                <tr>
                    <td class="pageTitle" style="padding-left:30px;align:left;">
                        <bean:message key="jobs.customFieldset" bundle="jobs"/>
                    </td>
                </tr>
                <tr>
                   <td class="paddingTitleDesc;align:left;" style="padding-left:30px;align:left;">
                         <strong><bean:message key="jobs.customJob.description" bundle="jobs"/><strong>
                    </td>
                </tr>
                <tr>
                    <td height="13" colspan="8" align="left" class=""></td>
                </tr>
                <tr>
                    <td style="padding-left:10px;">
                        <table cellspacing="0" cellpadding="1" width="100%" border ="0">
                            <tr>
                                <td style="padding-left:0px;align:left;">
                                    <table cellspacing="0" cellpadding="0" border="0" align="left" >
                                        <tr>
                                            <td>
                                                    <table cellpadding="0" cellspacing="" border="0" width="100%">
                                                        <tr>
                                                             <td height="27" align="right"  style="padding-right:10px;width:100px;" nowrap="nowrap" >
                                             <label class="label"><label class="label"><bean:message key="jobs.timerClass" bundle="jobs"/></label><label class="error">*</label><b>:</b>
                                                            </td>
                                                            <td style="align:left;" width="90px">
                                                                <input type="text"  id="timerTask" name="timerTask" style="height:1.7em;"
                                                            dojoType="dijit.form.ValidationTextBox" required="true" trim="true" ucfirst="true"    />
                                                             </tr>
                                                    </table>
                                            </td>
                                           <td style="width:40px;">&nbsp;

                                            </td>
                                            <td align="center">
                                                    <table cellpadding="10" cellspacing="0" border="0">
                                                        <tr>
                                                            <td height="27" align="left" style="width:80;">
                                                                <label class="label"><bean:message key="jobs.parameters" bundle="jobs"/></label><b>:</b>
                                                            </td>
                                                            <td style="padding-left:5px; align:left;" width="200px">
                                                                    <textarea dojoType="dijit.form.SimpleTextarea" rows="5" cols="30" style="width:210px;height:80px;" id="timerParameterValueMap"
                                                name="timerParameterValueMap" trim="true"></textarea>
                                                <sub><bean:message key="jobs.suggestParams" bundle="jobs"/></sub>
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
            <tr><td style="padding-left:30">&nbsp;</td><td style="padding-left:30">&nbsp;</td></tr>
            </table>
        </div>


<!-- Cache Ecosystem  -->
             <div id="div6" style="display:none;" >
            <table width="70%" class="tableBgColor" border="0">
<tr height="10px">
                <td>&nbsp;

                </td>
            </tr>
            <tr>
                <td class="pageTitle " style="padding-left:30px;align:left;">
                    <bean:message key="jobs.cacheechosyslist" bundle="jobs"/>
                </td>
            </tr>
            <tr>
               <td class="paddingTitleDesc;align:left;" style="padding-left:30px;align:left;">
                 <strong><bean:message key="jobs.clearCache.description" bundle="jobs"/></strong>
                </td>
            </tr>
            <tr>
                <td height="13" colspan="8" align="left" class=""></td>
            </tr>
            <tr>
                <td style="padding-left:30px;">
                    <table cellspacing="0" cellpadding="1"  border="0" >
                        <tr>
                                <td style="align:left;">
                                    <table cellspacing="0" cellpadding="0" border="0" align="left" >
                                        <tr>
                                            <td align="left" >
                                                    <table cellpadding="0" cellspacing="" border="0" >
                                                        <tr>
                                                             <td height="27" align="left"  style="padding-left:2px;width:156px;" nowrap="nowrap" >
                                                                <label class="label"><label class="label"><bean:message key="jobs.cacheechosyslist" bundle="jobs"/></label><label class="error">*</label><font style="font-weight: normal;" ><b>:</b></font>
                                                            </td>
                                                            <td style="align:left;" width="110px">
                                                                    <select id="combobox1"   dojoType="dijit.form.MultiSelect" multiple name="combobox1"  size="10" ondblclick="return MoveItems('R', 0);" style="width:240px;">
                                                                    <logic:present name="cacheList" scope="request">
                                                                        <logic:iterate name="cacheList" id="cacheListID">
                                                                        <option value="<bean:write name="cacheListID" property="cacheechosystem"/>">
                                                                        <bean:write name="cacheListID" property="cacheechosystem"/></option>
                                                                        </logic:iterate>
                                                                        </logic:present>
                                                                    </select>
                                                            </td>
                                                        </tr>
                                                    </table>
                                            </td>
                                            <td  STYLE="WIDTH:30PX" valign="middle">
                                                <table>
                                                    <tr>
                                                        <td>
                                                           <img alt="" src="<%=ServerUtils.getContextName()%>/images/portal/btn_move_right_one.png"  onclick="return MoveItems('R', 0);"  id="shift2" style="display:block;cursor:pointer" />
                                                       </td>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                           <img alt="" src="<%=ServerUtils.getContextName()%>/images/portal/btn_move_left_one.png"  onclick="MoveItems('L', 0);"  id="shift2" style="display:block;cursor:pointer" />
                                                       </td>
                                                    </tr>
                                                </table>
                                            </td>
                                            <td>
                                            <select id="combobox2"   dojoType="dijit.form.MultiSelect" multiple name="combobox2" style="width:240px;" size="10" ondblclick="return MoveItems('L', 0);">
                                            </select>
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                    </table>
                </td>
            </tr>
            </td></tr>
                        <tr>
                <td style="padding-left:30px;width:600px">
                    <table cellspacing="0" cellpadding="1" width="100%" border="0" align="left">
                        <tr>
                                <td style="align:left;">
                                    <table cellspacing="0" cellpadding="0" border="0" align="left" >
                                        <tr>
                                            <td>
                                                    <table cellpadding="0" cellspacing="" border="0" width="100%">
                                                            <logic:present name="isCacheListAvail" scope="session">
                                                            <tr><td height="27" align="right" style="width: 120px" ><label class="label"><tr>
                                                            <td height="27" align="right" style="width: 180px" >
                                                            <label class="error">&nbsp;<bean:message key="jobs.iscachedArtifactAvail" bundle="jobs"/></label>
                                                            </td>
                                                            </tr></label></td><tr>
                                                            </logic:present>
                                                    </table>
                                            </td>
                                            <td style="width:60px;">
                                            </td>
                                            <td align="center">
                                                    <table cellpadding="" cellspacing="0" border="0">
                                                        <tr>
                                                            <td height="27" align="right" style="width:110;">
                                                                </td>
                                                            <td style="padding-left:10px; align:left;" width="90px">
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

            <tr><td style="padding-left:30">&nbsp;</td><td style="padding-left:30">&nbsp;</td></tr>
            </table>
        </div>
<!-- PI DATA EXTRACTOR  -->

                  <div id="div1" style="display:none;" >

                        <table width="70%" class="tableBgColor" style="align:left" >
                            <tr height="10px">
                                <td>&nbsp;

                                </td>
                            </tr>
                            <tr>
                                <td class="pageTitle paddingTitle" style="padding-left:30px;align:left;">
                                    <bean:message key="jobs.pidbFieldset" bundle="jobs"/>
                                </td>
                            </tr>
                            <tr>
                               <td class="paddingTitleDesc" style="padding-left:30;" align="left">
                                     <strong><bean:message key="jobs.piData.description" bundle="jobs"/></strong>
                                </td>
                            </tr>
                            <tr>
                                <td height="13" colspan="2" align="left" class=""></td>
                            </tr>

                            <tr>
                                <td style="padding-left:0px;" width = "100%"  >
                                <table cellspacing="0" style="align:left" cellpadding="0"  width = "100%" border = "0">
								<tr>
								     <td style="padding-left:30px;" width = "24%">
                                        <label class="label"><bean:message key="jobs.datasourceName" bundle="jobs"/></label><label class="error">*</label><b>:</b> </td>
										  <td style="padding-left:8px;align:left;" width="76%">
                                              <select  id="dataSource" dojoType="dijit.form.FilteringSelect" name="dataSource" autoComplete="false">
                                                 <option value="-1"><bean:message key="validation.msg.select" bundle="splchvalidation"/></option>
                                                                            <%
                                                                               int count = datasourceArray == null ? 0 : datasourceArray.length;
                                                                                for (int i = 0; i < count; i++) { %>
                                                                                  <option value="<%=datasourceArray[i].getId()%>"><%=datasourceArray[i].getDatasourceName()%> </option>
                                                                            <%} %>
                                               </select>
                                          </td>

                                </tr>
                          <tr>
                              <td height="13" colspan="2" align="left" class=""></td>
                          </tr>

                           <tr>
                              <td>&nbsp;</td>
                              <td>&nbsp;<html:radio  name="AddJobForm" property="tags" value="tags"></html:radio>&nbsp;<label class="label"><bean:message key="jobs.tags" bundle="jobs"/></label>     &nbsp;<html:radio  name="AddJobForm" property="tags" value="query"></html:radio>&nbsp; <label class="label"><bean:message key="jobs.query"			bundle="jobs"/></label> &nbsp; &nbsp;<html:radio  name="AddJobForm" property="tags" value="custom"></html:radio>&nbsp; <label class="label"><bean:message key="jobs.custom" bundle="jobs"/></label>&nbsp;
                             </td>
                           </tr>
                        <tr>
                           <td height="13" colspan="2" align="left" class=""></td>
                        </tr>
                        <tr>
                            <td style="padding-left:0px"  align="right">
                                <label class="label"><bean:message key="jobs.tagqryOption" bundle="jobs"/></label><label class="error">*</label><b>:</b></td>
                                <td style="padding-left:10px;align:left;"><textarea dojoType="dijit.form.SimpleTextarea" rows="5" cols="30" id="tagQuery"  style="width:270;height:5.7em;" name="tagQuery" style="height:1.7em;" trim="true"></textarea>
                            </td>
                        </tr>
					    <tr>
                            <td height="13" colspan="2" align="left" class=""></td>
                        </tr>

                        <tr>
						    <td height="27" align="right" style="padding-left:0px;"><label class="label"><bean:message key="jobs.pilistener" bundle="jobs"/></label><b>:</b></td>
                             <td style="padding-left:10px;align:left;width:200;"><textarea dojoType="dijit.form.SimpleTextarea" rows="5" cols="30" trim="true" id="pilistener"  name="piListener" style="width:270;height:5.7em;"  ></textarea>
                             </td>
                       </tr>
                        <tr>
                        <td height="13" colspan="2" align="left" class=""></td>
                        </tr>

                        <tr>
						   <td align="right"><label class="label"><bean:message key="jobs.metadata" bundle="jobs"/></label><b>:</b></td>
                             <td height="27" align="left"  style="width:200px;" nowrap="nowrap" >&nbsp;
                             <html:checkbox  name="AddJobForm" property="loadMetaData" ></html:checkbox><label class="label">&nbsp;</td>
					   </tr>
				        <tr>
                        <td height="13" colspan="2" align="left" class=""></td>
                        </tr>
                             <tr>
                                 <td align="right">
                                   <label class="label"><bean:message key="jobs.cachekey" bundle="jobs"/></label><b>:</b>
                                 </td>
                                 <td height="27"  style="align:left;width:160;"">
                                     &nbsp; <input type="text"  id="cacheKey" name="cacheKey" class="medium" style="height:1.7em;"
                                                        dojoType="dijit.form.ValidationTextBox" required="true" trim="true" ucfirst="true"    />
                                 </td>
                             </tr>
                          </table>
			          </td>
				   </tr>
			</table>
        </div>


               <!--  URL Extractor -->
            <div id="div2" style="display:none;">
            <table width="70%" class="tableBgColor">
            <tr height="10px">
                <td>&nbsp;

                </td>
            </tr>

            <tr><td >
            <tr>
                <td class="pageTitle " style="padding-left:30px;align:left;">
                    <bean:message key="jobs.urlExtractorFieldset" bundle="jobs"/>
                </td>
            </tr>
            <tr>
               <td class="paddingTitleDesc" style="padding-left:30px;">
                 <strong><bean:message key="jobs.urlExtractor.description" bundle="jobs"/></strong>
                </td>
            </tr>
            <tr>
                <td height="13" colspan="8" align="left" class=""></td>
            </tr>
            <tr>
                <td style="padding-left:0px;width="900px">
                    <table cellspacing="0" cellpadding="0" width="100%" >
                        <tr>
                                <td style="padding-left:0px;align:left;">
                                    <table cellspacing="0" cellpadding="0" border="0" align="left" >
                                        <tr>
                                            <td>
                                                    <table cellpadding="10" cellspacing="" border="0" width="100%">
                                                        <tr>
                                                             <td height="27" align="right"  style="width:172px;" nowrap="nowrap" >
                                                                <label class="label"><bean:message key="jobs.urls" bundle="jobs"/></label><label class="error">*</label><b>:</b>
                                                            </td>
                                                            <td style="padding-left:8px; align:left;" width="325px" nowrap="nowrap">
                                                                <table id="mytable2"><tr><td>
                                                             <input type="text" id="url1" name="url1" style="width:300px;height:1.7em;"
                                                                                dojoType="dijit.form.ValidationTextBox" required="true" trim="true" ucfirst="true"   /><a href="#" onClick="javascript:addRow('mytable2');"><img src="<%=ServerUtils.getContextName()%>/images/portal/icon_plus_1.png"  / ></a>
                                                                </td></tr></table>
                                                            </td>
                                                        </tr>
                                                    </table>
                                            </td>
                                            <td style="width:30px;">
                                            </td>
                                            <td align="center">
                                                    <table cellpadding="" cellspacing="0" border="0">
                                                        <tr>
                                                            <td height="27" align="right" style="width:100;">
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
            <td height="13" colspan="8" align="left" class=""></td>
            </tr>
            <tr>
                <td style="padding-left:30px;">
                    <table cellspacing="0" cellpadding="0" width="100%" >
                        <tr>
                                <td style="padding-left:0px;align:left;">
                                    <table cellspacing="0" cellpadding="0" border="0" align="left">
                                        <tr>
                                            <td>
                                                    <table cellpadding="10" cellspacing="" border="0" width="100%">
                                                        <tr>
                                                             <td height="27" align="right"  style="width:140px;" nowrap="nowrap" >
                                                                    <label class="label"><bean:message key="jobs.destination" bundle="jobs"/></label><label class="error">*</label><b>:</b></td>
                                                                    <td style="padding-left:8px;align:left;width:325;">
                                                                        &nbsp;<input type="text" id="destination" name="destination" style="width:300px;height:1.7em;"
                                                                                        dojoType="dijit.form.ValidationTextBox" required="true" trim="true" ucfirst="true"  />

                                                            </td>
                                                        </tr>
                                                    </table>
                                            </td>
                                            <td style="width:30px;">
                                            </td>
                                            <td align="center">
                                                    <table cellpadding="" cellspacing="0" border="0">
                                                        <tr>
                                                            <td height="27" align="right" style="width:100;">
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
            <td height="13" colspan="8" align="left" class=""></td>
            </tr>
			<tr>
                <td style="padding-left:30px;width="900px">
                    <table cellspacing="0" cellpadding="0" width="100%" >
                        <tr>
                                <td style="padding-left:0px;align:left;">
                                    <table cellspacing="0" cellpadding="0" border="0" align="left">
                                        <tr>
                                            <td>
                                                    <table cellpadding="10" cellspacing="" border="0" width="100%">
                                                        <tr>
                                                             <td height="27" align="right"  style="width:140px;" nowrap="nowrap" >
                                                                    <label class="label"><bean:message key="jobs.urlsTimeOut" bundle="jobs"/></label>:</td>
                                                                    <td style="padding-left:14px;align:left;width:325;">
                                                                         <input type="text" id="urlTimeOut" name="urlTimeOut" style="width:300px;height:1.7em;"
                                                                                        dojoType="dijit.form.NumberTextBox" constraints="{min:0,max:86400,places:0}" invalidMessage="The value entered is invalid" />

                                                            </td>
                                                        </tr>
                                                    </table>
                                            </td>
                                            <td style="width:30px;">
                                            </td>
                                            <td align="center">
                                                    <table cellpadding="" cellspacing="0" border="0">
                                                        <tr>
                                                            <td height="27" align="right" style="width:100;">
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
                <td style="padding-left:30px;width="900px">
                    <table cellspacing="0" cellpadding="0" width="100%" >
                        <tr>
                                <td style="padding-left:0px;align:left;">
                                    <table cellspacing="0" cellpadding="0" border="0" align="left">
                                        <tr>
                                            <td>
                                                    <table cellpadding="10" cellspacing="" border="0" width="100%">
                                                        <tr>
                                                             <td height="27" align="right"  style="width:140px;" nowrap="nowrap" >
                                                                 <label class="label"><bean:message key="jobs.userId" bundle="jobs"/></label>&nbsp;<b>:</b>
                                                            </td>
                                                            </td>
                                                             <td height="27" align="left"  style="width:130px;padding-left:8px" nowrap="nowrap"  >
                                                             &nbsp;<input type="text"  id="userId" name="userId"  class="medium1" ucfirst="true" style="height:1.7em;" maxlength="255"
                                                                dojoType="dijit.form.ValidationTextBox" trim="true" />
                                                            </td>
                                                            </td>

                                                        </tr>
                                                    </table>
                                            </td>
                                            <td style="width:30px;">
                                            </td>
                                            <td align="left">
                                                    <table cellpadding="10" cellspacing="0" border="0">
                                                        <tr>
                                                            <td height="27" align="right" style="padding-right:12px;width:80;">
                                                                <label class="label"><bean:message key="jobs.password" bundle="jobs"/></label><b>:</b>
                                                             </td>
                                                            <td height="27"  style="align:left;width:30;"">
                                                                       <input type="password"   id="password" name="password" class="medium"style="height:1.7em;"
                                                                                        dojoType="dijit.form.ValidationTextBox" required="true" trim="true" ucfirst="true" maxlength="255" />
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
            </td>
            </tr>
            <tr>
                <td style="padding-left:30px;width="900px">
                    <table cellspacing="0" cellpadding="0" width="100%" >
                        <tr>
                                <td style="padding-left:0px;align:left;">
                                    <table cellspacing="0" cellpadding="0" border="0" align="left">
                                        <tr>
                                            <td>
                                                    <table cellpadding="10" cellspacing="" border="0" width="100%">
                                                        <tr>
                                                             <td height="27" align="left"  style="width:280px;" nowrap="nowrap" >
                                                                 <html:checkbox  name="AddJobForm" property="overwrite" ></html:checkbox>
                                                                                         <label class="label"><bean:message key="jobs.overwrite" bundle="jobs"/></label>
                                                                <html:checkbox  name="AddJobForm" property="extract" ></html:checkbox>
                                                                                         <label class="label"><bean:message key="jobs.extract" bundle="jobs"/></label>
                                                            </td>
                                                            </td>

                                                        </tr>
                                                    </table>
                                            </td>
                                            <td style="width:30px;">
                                            </td>
                                            <td align="left">
                                                    <table cellpadding="10" cellspacing="0" border="0">
                                                        <tr>
                                                            <td height="27" align="left" style="padding-right:0px;width:400;" nowrap="nowrap">
                                                               <html:checkbox  name="AddJobForm" property="extractChildDirectory" ></html:checkbox>
                                                                 <label class="label"><bean:message key="jobs.extractChildDir" bundle="jobs"/></label>
                                                              <html:checkbox  name="AddJobForm" property="deleteSourceFile" ></html:checkbox>
                                                                 <label class="label"><bean:message key="jobs.delSrcFile" bundle="jobs"/></label>
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
            </td></tr>


<tr>      <td style="padding-left:30">&nbsp;</td><td style="padding-left:30">&nbsp;</td></tr>
            </table>
        </div>


    <!--   ECO SYSTEM-->

     <div id="div3" style="display:none;" >
            <table width="70%" class="tableBgColor">
<tr height="10px">
                <td>&nbsp;

                </td>
            </tr>

            <tr><td >
            <tr>
                <td class="pageTitle paddingTitle" style="padding-left:30px;align:left;">
                    <bean:message key="jobs.ecosystemLabel" bundle="jobs"/>
                </td>
            </tr>
            <tr>
               <td class="paddingTitleDesc" style="padding-left:30px;">
                 <strong><bean:message key="jobs.ecosystem.description" bundle="jobs"/></strong>
                </td>
            </tr>
            <tr>
                <td height="13" colspan="2" align="left" class=""></td>
            </tr>
            <tr>
                <td style="padding-left:30px;width="900px">
                    <table cellspacing="0" cellpadding="0" width="100%" >
                        <tr>
                                    <td align="right">
                                            <label class="label"><bean:message key="jobs.ecosystemLabel" bundle="jobs"/></label><label class="error">*</label><b>:</b>
                                                            </td>
                                                            <td style="padding-left:10px; align:left;" width="90px">
<!--                                                                 <select  id="ecoSystemName" dojoType="dijit.form.FilteringSelect"  name="ecoSystemName"   autoComplete="false"  onchange="resetCredentials(this, 1)"  >
-->
                                                         <select  id="ecoSystemName" dojoType="dijit.form.FilteringSelect"  name="ecoSystemName"   autoComplete="false"   >
                                                                    <option value=""><bean:message key="validation.msg.select" bundle="splchvalidation"/></option>
                                                                    <logic:present name="ecoModelFileList" scope="request">
                                                                        <logic:iterate name="ecoModelFileList" id="ecoModelFileDtls">
                                                                            <option value="<bean:write name="ecoModelFileDtls" property="ecoSystemFilePath"/>"><bean:write name="ecoModelFileDtls" property="ecoSystemName"/></option>
                                                                        </logic:iterate>
                                                                    </logic:present>
                                                                 </select>
                                                            </td>
                                                        </tr>
            <tr>
            <td height="13" colspan="2" align="left" class=""></td>
            </tr>
            <tr>
                <td style="padding-left:36px;width="900px" align="right">
                    <label class="label"><bean:message key="jobs.userId" bundle="jobs"/></label><label class="error"></label><b>:</b>
                           </td>
                              <td height="27"  style="padding-left:10px;align:left;width:70;">
                                     <input type="text"  id="ecoUserId" name="ecoUserId" class="medium" style="height:1.7em;" maxlength="255"
                                    dojoType="dijit.form.ValidationTextBox" required="true" trim="true" ucfirst="true" />
                                </input>
                                                            </td>

                                                        </tr>

            <tr>
                <td height="13" colspan="2" align="left" class=""></td>
            </tr>

                                                        <tr>
                                                            <td height="27" align="right">
                                                                  <label class="label"><bean:message key="jobs.password" bundle="jobs"/></label><label class="error"></label><b>:</b></td>
                                                            <td height="27"  style="padding-left:10px;align:left;width:30;">
                                                              <input type="password"  id="ecoPassword" name="ecoPassword" class="medium" style="height:1.7em;" maxlength="255"
                                    dojoType="dijit.form.ValidationTextBox" required="true" trim="true" ucfirst="true"  />
                                </input>
                                                                </td>
                                                        </tr>
            <tr>
            <td height="13" colspan="2" align="left" class="bodytext"></td>
            </tr>

            <tr>
			   <td height="13" align="left" style="padding-left:110px;align:right"><label class="label"><bean:message key="jobs.GenerateKMLKMZ" bundle="jobs" /></label>&nbsp;<b>:</b></td>
                <td style="padding-left:5px;width="900px">&nbsp;<select id="generateKML" dojoType="dijit.form.FilteringSelect" name="generateKML" autoComplete="false" property="generateKML">
												<option value=""><bean:message key="validation.msg.select" bundle="splchvalidation"/></option>
												<option value="generateKML"><bean:message key="jobs.KML" bundle="jobs" /></option>
												<option value="generateKMZ"><bean:message key="jobs.KMZ" bundle="jobs" /></option>
											</select>

<!--        <html:radio  name = "AddJobForm" property="generateKML" value="generateKML" ></html:radio>
		<label class="label"><bean:message key="jobs.generateKML" bundle="jobs"/></label>

        <html:radio  name = "AddJobForm" property="generateKML" value="generateKMZ" ></html:radio>
		<label class="label"><bean:message key="jobs.generateKMZ" bundle="jobs"/></label>  -->
                                                            </td>
                                                        </tr>
            <tr>
            <td height="13" colspan="2" align="left" class=""></td>
            </tr>
            <tr>
                <td style="padding-left:28px;width="900px" align="right">
                <label class="label"><bean:message key="jobs.parameters" bundle="jobs"/></label><label class="error"></label><b>:</b>
                                                            </td>
                                                            <td height="27"  style="align:left;width:300;padding-left:2px;">&nbsp;
                                                             <textarea dojoType="dijit.form.SimpleTextarea" style="width: 192px;" rows="5" cols="30" id="parameterValueMap" style="width:65%;height:6.5em;" name="parameterValueMap"   ></textarea>
                                    &nbsp;&nbsp;<sub><bean:message key="jobs.suggestParams" bundle="jobs"/></sub>
                                                            </td>
															</tr>

            <tr>
			 <td style="padding-left:30">&nbsp;</td>
			 <td style="padding-left:30">&nbsp;</td>
			</tr>

            <tr>
                  <td height="27" align="right" style="width:140;padding-top:10px" align="right">
                 <label class="label"><bean:message key="jobs.dependentEcosystems" bundle="jobs"/></label><label class="error"></label><b>:</b>
                                                            </td>
                                                            <td>
                                                                 <table border="0" id="mytable1" > <tr>
                                                                    <td colspan="2" nowrap="nowrap"   valign="top">&nbsp;
                                                                            <select  id="ecoSystem1" dojoType="dijit.form.FilteringSelect"  name="ecoSystem1"    autoComplete="false"    >
                                                                                <option value=""><bean:message key="validation.msg.select" bundle="splchvalidation"/></option>
                                                                                <logic:present name="ecoModelFileList" scope="request">
                                                                                    <logic:iterate name="ecoModelFileList" id="ecoModelFileDtls">
                                                                                        <option value="<bean:write name="ecoModelFileDtls" property="ecoSystemFilePath"/>"><bean:write name="ecoModelFileDtls" property="ecoSystemName"/></option>
                                                                                    </logic:iterate>
                                                                                </logic:present>
                                                                             </select>
                                                                            &nbsp;<label class="label"><bean:message key="jobs.run" bundle="jobs"/></label><label class="error"></label><b>:</b><select  id="selectedEcoSystem1"    dojoType="dijit.form.FilteringSelect"  name="selectedEcoSystem1"    autoComplete="false"   value="<bean:write name="AddJobForm" property="selectedEcoSystem1" />">
                                                                                <option value=""><bean:message key="validation.msg.select" bundle="splchvalidation"/></option>
                                                                                <option value="1">Once</option>
                                                                                <option value="2">Everytime</option>
                                                                            </select>
																			&nbsp;<label class="label"><bean:message key="jobs.parameters" bundle="jobs"/></label><label class="error"></label><b>:</b><textarea dojoType="dijit.form.SimpleTextarea" rows="5" cols="30" id="ecoSystem1ParameterValueMap" style="height:1.7em;width:20em" name="ecoSystem1ParameterValueMap"></textarea>
										                                    <sub><bean:message key="jobs.suggestParamsinshort" bundle="jobs"/></sub>
                                                                                <a href="#" onClick="javascript:addRow('mytable1');return false;"><img src="<%=ServerUtils.getContextName()%>/images/portal/icon_plus_1.png" /></a>
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
            </table>
        </div>
       </td>
                   <td style="padding-left:30">&nbsp;</td>
    </tr>
    <tr height="10px">
         <td>&nbsp;

        </td>
    </tr>


<input type="hidden" name="operation" id="operation" value="" />

</table>

<script>
dojo.addOnLoad(loadFormValues);
function loadFormValues(){  dijit.byId('startTimeType').setValue('');
                            dijit.byId('ecoSystemName').setValue('');
                            dijit.byId('ecoSystem1').setValue('');
                            dijit.byId('generateKML').setValue('');
	if(null != <%=request.getAttribute("jobTypeId") %>)
	    dijit.byId('jobType').setValue('<%=request.getAttribute("jobTypeId") %>');
	                             else
	 dijit.byId('jobType').setValue('-1');
    document.forms[0].tags[0].checked=true;
    if(dijit.byId('startTimeType').value == START_JOB_ABSOLUTE){
		var obj = document.getElementById("dateDiv");
		obj.style.display = "block";
    }
    if(dijit.byId('jobType').value == JOB_TYPE_CLEAR_CACHE){
		<% if (pageContext.getRequest().getAttribute("cacheList")== null || ((ArrayList)(pageContext.getRequest().getAttribute("cacheList"))).size() == 0)
            { %> dijit.byId('idCreate').setAttribute('disabled', true);
             dijit.byId('idReset').setAttribute('disabled', true);
            <% }
            %>


    }
     statusChange();
	 //getMSTList();
}
</script>
</td></tr>
<TR><td height="10px">&nbsp;</td></TR>
</table>



<table width = "101.2%" border="0" id="" cellspacing="0" cellpadding="0">
     <tr class="barColor">
           <td colspan="6" align="center">
            <button dojoType="dijit.form.Button" type="button" id="idHome" name="btnHome" onClick="window.location= '<%=ServerUtils.getContextName()%>/adminMain.do' ">
            <bean:message key="jobs.home" bundle="jobs"/>
            </button>

            <button dojoType="dijit.form.Button" type="button" id="idBack" name="btnBack" onClick="window.location= 'configureJobSchdAction.do?operation=view'">
            <bean:message key="jobs.back" bundle="jobs"/>
            </button>

            <button  dojoType="dijit.form.Button" type="button" id="idCreate" name="btnAdd" onClick="assignTotalNoOfURLS();assignTotalNoOfEcos(); return chkubmit(this);">
            <bean:message key="jobs.save" bundle="jobs"/>
            </button>

            <button dojoType="dijit.form.Button" type="reset" id="idReset" name="btnReset" onClick="clearTextArea();" onMouseOut="loadDefaultValues()">
            <bean:message key="jobs.reset" bundle="jobs"/>
            </button>


            </td>
    </tr>
</table>

<input type="hidden" name="totalNoOfEcoSystems" value=""   />
<input type="hidden" name="totalNoOfUrls" value=""   />
</html:form>
<script>
function loadDefaultValues(){
    dijit.byId('startTimeType').setValue(dijit.byId('startTimeType').value);
    dijit.byId('jobType').setValue(dijit.byId('jobType').value);
	dijit.byId('ecoSystemName').setValue(dijit.byId('ecoSystemName').value);
	dijit.byId('ecoSystem1').setValue(dijit.byId('ecoSystem1').value);
	dijit.byId('selectedEcoSystem1').setValue(dijit.byId('selectedEcoSystem1').value);
	dijit.byId('dataSource').setValue(dijit.byId('dataSource').value);
	
    document.forms[0].tags[0].checked=true;
}
function checkBoxAlert(boxName){

	if(boxName.checked == true)
		 showEmptyDialog("<bean:message key='jobs.server.msg.all' bundle='jobs'/>", "Error");
	else
          showEmptyDialog("<bean:message key='jobs.server.msg.specific' bundle='jobs'/>", "Error");



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