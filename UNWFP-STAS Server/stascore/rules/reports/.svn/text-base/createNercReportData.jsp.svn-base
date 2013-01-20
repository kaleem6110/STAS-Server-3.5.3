<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@page import="com.enterprisehorizons.magma.server.util.ServerUtils"%>
<%@ include file="/common/dojo.jsp"%>

<html:html locale="true">
<head>
<script type="text/javascript" src="<%=ServerUtils.getContextName(request)%>/js/rules.js" ></script>

<script>


function IsNumeric(strString, paramName)
   //  check for valid numeric strings	
   {
	   var strStringVal = "";
	   if(strString != null)
		   strStringVal = trimAll(strString);
		else   
			return false;   

		   
	   var strValidChars = "0123456789.-";
	   var strChar;
	   var blnResult = true;
	
		
	   //  test strString consists of valid characters listed above

		   for (i = 0; i < strStringVal.length && blnResult == true; i++)
		   {
			  strChar = strStringVal.charAt(i);
			  if (strValidChars.indexOf(strChar) == -1)
			  {
				   showEmptyDialog("<bean:message key="rules.alert.title" bundle="rules" />"+ paramName, "<bean:message key="rules.alert.title" bundle="rules" />");			   				 
					return false;
			  }
		   }
			if(isSplCharInString(strStringVal))
			{
			   return false;
			}

	   return blnResult;
   }





	/*
		Trim the string
	*/
	function trimAll(sString)
	{
		while (sString.substring(0,1) == ' ')
		{
			sString = sString.substring(1, sString.length);
		}
		while (sString.substring(sString.length-1,  sString.length) == ' ')
		{
			sString = sString.substring(0,sString.length-1);
		}
		return sString;
	}


	function isSplCharInString(str,paramName)
		{	
			var spchar, getChar, SpecialChar;	
			var spchar="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz.?:;,[]()_-";
			SpecialChar='Yes';
			//var spchars =" ' ( ) { } \\ ~ ! @ ^ & * + \" | : =  , < > "; 

			
			for(var i=0; i<str.length; i++)
			{
				if(str.charAt(i) ==' ' )	
				{
					continue;
				}				
				SpecialChar='Yes';						
				for(var j=0; j<spchar.length;j++)
				{
					if(str.charAt(i) == spchar.charAt(j))
					{			
						SpecialChar = 'No';
						break;	
					}
				}	
				if(SpecialChar == 'Yes')
				{
					showEmptyDialog("<bean:message key="rules.check.splchar" bundle="rules" />"+ paramName, "<bean:message key="rules.alert.title" bundle="rules" />");	
					return true;
				}
			}
			
			if (SpecialChar == 'No')
			{
				return false;
			}
		}
	 
     function isValidTime(startTime){
     
		if(startTime != null && startTime.length == 0 ){
		   showEmptyDialog("<bean:message key="rules.check.timestamp" bundle="rules" />", "<bean:message key="rules.alert.title" bundle="rules" />");
		     
            return false;
            }
       if(startTime != null && startTime.length > 0){
			var startTimeArray = startTime.split(":");
			if(isNaN(startTimeArray[0]) || startTimeArray[0].indexOf('-') != -1 ){
				 showEmptyDialog("<bean:message key="rules.check.timestamp" bundle="rules" />", "<bean:message key="rules.alert.title" bundle="rules" />");
				
				return false;
			}

			if(isNaN(startTimeArray[1]) || startTimeArray[1].indexOf('-') != -1){
				 showEmptyDialog("<bean:message key="rules.check.timestamp" bundle="rules" />", "<bean:message key="rules.alert.title" bundle="rules" />");
				
				return false;
			}

			if(isNaN(startTimeArray[2]) || startTimeArray[2].indexOf('-') != -1){
				 showEmptyDialog("<bean:message key="rules.check.timestamp" bundle="rules" />", "<bean:message key="rules.alert.title" bundle="rules" />");
		
				return false;
			}
			if((startTimeArray.length-1) != 3){
					try{
					if(startTimeArray[0] > 23 || startTimeArray[0] < 0||(!isInt(startTimeArray[0]))){
						
						showEmptyDialog("<bean:message key="rules.check.hour" bundle="rules" />", "<bean:message key="rules.alert.title" bundle="rules" />");
				
						return false;
					}
					if(startTimeArray[1] > 59 || startTimeArray[1] < 0||(!isInt(startTimeArray[1]))){
					
						showEmptyDialog("<bean:message key="rules.check.minute" bundle="rules" />", "<bean:message key="rules.alert.title" bundle="rules" />");
					
						return false;
					}
					if(startTimeArray[2] > 59 || startTimeArray[2] < 0||(!isInt(startTimeArray[2]))){
					    
						showEmptyDialog("<bean:message key="rules.check.seconds" bundle="rules" />", "<bean:message key="rules.alert.title" bundle="rules" />");
						
						return false;
					}
				}catch(err){
					showEmptyDialog("<bean:message key="rules.check.timestamp" bundle="rules" />", "<bean:message key="rules.alert.title" bundle="rules" />");
					
					return false;
				}
			}
     }
     return true;
}

function isInt(str){
                   var regex=/^[0-9]+$/; 
                   if(regex.test(str)){                  
                      return true;
                       } 
				   else {
                      return false;
                      }
		    	   }


function submitReport() {

	if ( document.getElementById("orgFilingReport")!= null && document.getElementById("orgFilingReport").value.length == 0 ) {
			showEmptyDialog("<bean:message key="rules.check.org.report" bundle="rules" />", "<bean:message key="rules.report.title" bundle="rules" />");
			return false;
	   }
	
	if ( document.getElementById("filingPersonName") != null && document.getElementById("filingPersonName").value.length == 0 ) {
			showEmptyDialog("<bean:message key="rules.check.person.name" bundle="rules" />", "<bean:message key="rules.report.title" bundle="rules" />");
			return false;
	   }

	if (document.getElementById("telephoneNumber") != null &&  document.getElementById("telephoneNumber").value.length == 0 ) {
			showEmptyDialog("<bean:message key="rules.check.telephone" bundle="rules" />", "<bean:message key="rules.report.title" bundle="rules" />");
			return false;
	   }

	if ( trim(document.getElementById("causeActionPlan").value).length == 0 ) {
			showEmptyDialog("<bean:message key="rules.check.disturbance" bundle="rules" />", "<bean:message key="rules.report.title" bundle="rules" />");
 			return false;
	   }
		
	if(isSplCharInString(document.getElementById("orgFilingReport").value,'Organization Filing Report')){
    
        return false;
       }
    if(isSplCharInString(document.getElementById("filingPersonName").value,'Filing Person Name')){
    
      return false;
      }
    if(isSplCharInString(document.getElementById("telephoneNumber").value,'Telephone Number')){
    
      return false;
      }
    if((document.getElementById("timeOfDisturbance") != null)&& (!isValidTime(document.getElementById("timeOfDisturbance").value))){
    
    return false;
     }
	if((document.getElementById("generationTrippedMVTotal") != null) && (!IsNumeric(document.getElementById("generationTrippedMVTotal").value, 'MW Total'))){		
		return false;
	}	
	
	if((document.getElementById("listGenerationTripped") != null) && (!IsNumeric(document.getElementById("listGenerationTripped").value, 'List Generation Tripped'))){
		return false;
	}

	if((document.getElementById("frequencePriorDisturbance") != null)&& (!IsNumeric(document.getElementById("frequencePriorDisturbance").value, 'Just prior to disturbance (Hz)'))){
		return false;
	}

	if((document.getElementById("frequenceAfterDisturbanceMax")!= null) && (!IsNumeric(document.getElementById("frequenceAfterDisturbanceMax").value, 'Immediately after disturbance (Hz max.)'))){
		return false;
	}

	if((document.getElementById("frequenceAfterDisturbanceMin")!= null) && (!IsNumeric(document.getElementById("frequenceAfterDisturbanceMin").value, 'Immediately after disturbance (Hz min.)') )){
		return false;
	}

	if((document.getElementById("noOffFirmCustomersEffected") != null) && (!IsNumeric(document.getElementById("noOffFirmCustomersEffected").value, 'Number of affected Customers (Firm)'))){
		return false;
	}

	if((document.getElementById("noOffInterruptibleCustomersEffected") != null) && (!IsNumeric(document.getElementById("noOffInterruptibleCustomersEffected").value, 'Number of affected Customers (Interruptible)'))){
			return false;
    }

	if( (document.getElementById("interruptibleDemandLoss") != null) && (!IsNumeric(document.getElementById("interruptibleDemandLoss").value, 'Demand lost (Interruptible)'))){
			return false;
	}

	if(((document.getElementById("firmDemandTripped")!= null)) && (!IsNumeric(document.getElementById("firmDemandTripped").value, 'Demand tripped (Firm)'))){
			return false;
	}
	
	if((document.getElementById("interruptibleDemandTripped") != null)&&(!IsNumeric(document.getElementById("interruptibleDemandTripped").value, 'Demand tripped (Interruptible)'))){
			return false;
	}
   
    if((document.getElementById("initialTransmission")!=null)&& isSplCharInString(document.getElementById("initialTransmission").value,'Intial Transmission')){
    
      return false;
    }
   if((document.getElementById("finalTransmission")!= null)&& isSplCharInString(document.getElementById("finalTransmission").value,'Final Transmission')){
    
      return false;
    }
  
    if((document.getElementById("initialGeneration") != null) && isSplCharInString(document.getElementById("initialGeneration").value,'Intial Generation')){
    
      return false;
    }
   if((document.getElementById("finalGeneration"))&& isSplCharInString(document.getElementById("finalGeneration").value,'Final Generation')){
    
      return false;
    }
  
   if((document.getElementById("initialDemand"))&&(!IsNumeric(document.getElementById("initialDemand").value, 'Initial Demand'))){		
		return false;
	}	
   if((document.getElementById("finalDemand") != null)&&(!IsNumeric(document.getElementById("finalDemand").value, 'Final Demand'))){		
		return false;
    }  
    
	//return false;
	document.createNercReportForm.action='<%=ServerUtils.getContextName(request)%>/createNercReportData.do?operation=saveNercInteroperabilityDtls&pageNo=1';
	document.createNercReportForm.submit();
    }

function trim(str) {
	return str.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
}


</script>
</head>
<body class="tundra bodybg">
<form name="createNercReportForm" method="post" action="<%=ServerUtils.getContextName(request)%>/createNercReportData.do">
<!----  HIden Variables -->

<input type="hidden" name="operation" id="operation" value="saveNercInteroperabilityDtls"/>
<input type="hidden" name="startDate" value="<logic:present name="startDate"><bean:write name="startDate" /></logic:present>"/>
<input type="hidden" name="endDate"  value="<logic:present name="endDate"><bean:write name="endDate" /></logic:present>"/>
<input type="hidden" name="flag"  value="<logic:present name="flag"><bean:write name="flag" /></logic:present>"/>

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
                          <td class="pageTitle"><strong><bean:message key="rules.nercInterOperabilityCaption" bundle="rules" /> </strong></td>
                        </tr>
  						<tr>
						   <td class="paddingTitleDesc" colspan="2">
								<bean:message key="rules.nercInterOperabilityCaption.description" bundle="rules" />               
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


                                 <table
									 style="page-break-before: always; width: 841px; height: 676px;"
									 border="0" cellpadding="2" cellspacing="0">
											<col width="31"> <col width="282"><col width="466"><tbody>
											 
											  <tr>
												<td
									 style="width: 31px; text-align: center; vertical-align: middle;">
												<p> <input name="isIROLViolationReport" value="1" checked="checked"
									 style="width: 0.14in; height: 0.14in;" type="checkbox">&nbsp;&nbsp;
												</p>
												<p></p>
												</td>
												<td colspan="2" width="752">
												<p>&nbsp;<font face="Tahoma, Arial, Helvetica, sans-serif"
									 size="2"><strong><bean:message key="rules.validationReportIROL" bundle="rules" /></strong></font></p>
												</td>
											  </tr>
											  <tr>
												<td sdval="1" sdnum="1033;" width="31">
												<p align="center"><font
									 face="Tahoma, Arial, Helvetica, sans-serif"><font
									 style="font-size: 9pt;" size="2"><b>1.</b></font></font></p>
												</td>
												<td style="width: 300px;">
												<p align="right">&nbsp;<font
									 face="Tahoma, Arial, Helvetica, sans-serif"><font
									 style="font-size: 9pt;" size="2"><b><bean:message key="rules.organizationName" bundle="rules" /></b>
									 <label class="error">*</label>:
									 </font></p>
												</td>
												<td>
												<p>&nbsp;<input name="orgFilingReport" size="50"
									 style="width: 4.21in; " type="text" id="orgFilingReport" class="medium" maxLength="45"
									dojoType="dijit.form.ValidationTextBox" required="true" trim="true" value='<bean:write name="createNercReportForm" property="orgFilingReport"/>'	ucfirst="true"></p>
												</td>
											  </tr>
											  <tr>
												<td sdval="2" sdnum="1033;" width="31">
												<p align="center"><font
									 face="Tahoma, Arial, Helvetica, sans-serif"><font
									 style="font-size: 9pt;" size="2"><b>2.</b></font></font></p>
												</td>
												<td width="300">
												<p align="right">&nbsp;<font
									 face="Tahoma, Arial, Helvetica, sans-serif"><font
									 style="font-size: 9pt;" size="2"><b><bean:message key="rules.personName" bundle="rules" /></b>
									 <label class="error">*</label>:
									 </font></p>
												</td>
												<td>
												<p>&nbsp;<input name="filingPersonName" size="50"
									 style="width: 4.21in; height: 0.25in;" type="text" id="filingPersonName" class="medium" maxLength="45"
									dojoType="dijit.form.ValidationTextBox" required="true" trim="true" value='<bean:write name="createNercReportForm" property="filingPersonName"/>'	ucfirst="true"></p>
												</td>
											  </tr>
											  <tr>
												<td sdval="3" sdnum="1033;" width="31">
												<p align="center"><font
									 face="Tahoma, Arial, Helvetica, sans-serif"><font
									 style="font-size: 9pt;" size="2"><b>3.</b></font></font></p>
												</td>
												<td style="width: 300px;">
												<p align="right">&nbsp;<font
									 face="Tahoma, Arial, Helvetica, sans-serif"><font
									 style="font-size: 9pt;" size="2"><b><bean:message key="rules.phoneNumber" bundle="rules" /></b>
									 <label class="error">*</label>:
									 </font></p>
												</td>
												<td>
												<p>&nbsp;<input name="telephoneNumber" size="50"
									 style="width: 4.21in; height: 0.25in;" type="text" id="telephoneNumber" class="medium" maxLength="45"
									dojoType="dijit.form.ValidationTextBox" required="true" trim="true" value='<bean:write name="createNercReportForm" property="telephoneNumber"/>'	ucfirst="true"></p>
												</td>
											  </tr>
											  <tr>
												<td sdval="4" sdnum="1033;" width="31">
												<p align="center"><font
									 face="Tahoma, Arial, Helvetica, sans-serif"><font
									 style="font-size: 9pt;" size="2"><b>4.</b></font></font></p>
												</td>
												<td style="vertical-align: top;" width="300">
												<table style="width: 293px; height: 133px;">
												  <tbody>
													<tr>
													  <td style="width: 285px; padding-top:23px" align="right"><font
									 color="#000000"><font face="Tahoma, Arial, Helvetica, sans-serif"><font
									 style="font-size: 9pt;" size="2" class="redtitle1"><b><bean:message key="rules.datetimeOfDisturbance" bundle="rules" /></b></font></font></font></td>
													</tr>
													<tr>
													  <td style="width: 285px; height: 30px;"></td>
													</tr>
													<tr>
													  <td
									 style="width: 285px; text-align: right; height: 30px;"><font
									 color="#000000"><font face="Tahoma, Arial, Helvetica, sans-serif"><font
									 style="font-size: 9pt;" size="2"><b><bean:message key="rules.dateOfDisturbance" bundle="rules" /></b></font></font></font></td>
													</tr>
													<tr>
													  <td
									 style="width: 285px; text-align: right; height: 30px;"><font
									 color="#000000"><font face="Tahoma, Arial, Helvetica, sans-serif"><font
									 style="font-size: 9pt;" size="2"><b><bean:message key="rules.timeOfDisturbance" bundle="rules" /></b></font></font></font></td>
													</tr>
												  </tbody>
												</table>
												</td>
												<td style="vertical-align: top;" width="300">
												<table style="height: 101px; width: 468px;">
												  <tbody>
													<tr>
													  <td style="width: 460px; height: 30px;"></td>
													</tr>
													<tr>
													  <td style="width: 460px; height: 30px;"></td>
													</tr>
													<tr>
													  <td style="width: 460px; height: 30px;"><input
									 name="dateoFDisturbance" type="text" id="dateoFDisturbance" 
									 dojoType="dijit.form.DateTextBox" required="true" trim="true" value='<bean:write name="createNercReportForm" property="reportedOn"/>' ucfirst="true">
																		  </td>
													</tr>
													<tr>
													  <td style="width: 460px; height: 30px;"><input
									 name="timeOfDisturbance" type="text" id="timeOfDisturbance" class="medium" maxLength="45"
									dojoType="dijit.form.ValidationTextBox" required="true" trim="true"	value='<bean:write name="createNercReportForm" property="reportedTime"/>' ucfirst="true"></td>
													</tr>
												  </tbody>
												</table>
												</td>
											  </tr>
											  <tr>
												<td sdval="5" sdnum="1033;" height="32" width="31">
												<p align="center"><font
									 face="Tahoma, Arial, Helvetica, sans-serif"><font
									 style="font-size: 9pt;" size="2"><b>5.</b></font></font></p>
												</td>
												<td
									 style="width: 300px; text-align: left; vertical-align: top;">
												<p align="right"><font
									 face="Tahoma, Arial, Helvetica, sans-serif"><font
									 style="font-size: 9pt;" size="2"><b><bean:message key="rules.disturbanceOriginate" bundle="rules" /> </b></font></font></p>
												</td>
												<td width="466">
												<p style="" align="left"><font style="font-size: 2pt;"
									 size="1"><font color="#000000"><font
									 face="Tahoma, Arial, Helvetica, sans-serif"><font
									 style="font-size: 9pt;" size="2"><b>&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;Yes <input type="radio" checked="checked" value="yes" name="isDisturbanceOccured"
									 >&nbsp;
									&nbsp;No<input type="radio"   value="no" name="isDisturbanceOccured"
									 ></b></font></font></font></font></p>
												</td>
											  </tr>
											  <tr>
												<td sdval="6" sdnum="1033;"
									 style="width: 31px; text-align: center; vertical-align: top;">
												<p style="text-align: center;"><font
									 face="Tahoma, Arial, Helvetica, sans-serif"><font
									 style="font-size: 9pt;" size="2"><b>6.</b></font></font></p>
												</td>
												<td
									 style="width: 300px; text-align: left; vertical-align: top;">
												<p style="height: 106px;" align="right"><font
									 face="Tahoma, Arial, Helvetica, sans-serif"><font
									 style="font-size: 9pt;" size="2"><b><bean:message key="rules.disturbanceDescription" bundle="rules" /></b>
									 <label class="error">*</label>:
									 </font></p>
												</td>
												<td style="text-align: left; vertical-align: middle;"><textarea
									 cols="40" rows="6" name="causeActionPlan" id="causeActionPlan"><bean:write name="createNercReportForm" property="causeActionPlan"/></textarea></td>
											  </tr>
											  <tr>
												<td sdval="7" sdnum="1033;" width="31">
												<p align="center"><font
									 face="Tahoma, Arial, Helvetica, sans-serif"><font
									 style="font-size: 9pt;" size="2"><b>7.</b></font></font></p>
												</td>
												<td style="vertical-align: top;" width="300">
												<table style="width: 293px; height: 133px;">
												  <tbody>
													<tr>
													  <td style="width: 285px; height: 30px;padding-top:20px" align="right"><font
									 color="#000000"><font face="Tahoma, Arial, Helvetica, sans-serif"><font
									 style="font-size: 9pt;" size="2" class="redtitle1"><b><bean:message key="rules.generationTripped" bundle="rules" /></b></font></font></font></td>
													</tr>
													<tr>
													  <td style="width: 285px; height: 30px;"></td>
													</tr>
													<tr>
													  <td
									 style="width: 285px; text-align: right; height: 30px;"><font
									 color="#000000"><font face="Tahoma, Arial, Helvetica, sans-serif"><font
									 style="font-size: 9pt;" size="2"><b><bean:message key="rules.totalMWTripped" bundle="rules" /></b></font></font></font></td>
													</tr>
													<tr>
													  <td
									 style="width: 285px; text-align: right; height: 30px;"><font
									 color="#000000"><font face="Tahoma, Arial, Helvetica, sans-serif"><font
									 style="font-size: 9pt;" size="2"><b><bean:message key="rules.generationTrippedList" bundle="rules" /></b></font></font></font></td>
													</tr>
												  </tbody>
												</table>
												</td>
												<td style="vertical-align: top;" width="300">
												<table style="width: 468px; height: 101px;">
												  <tbody>
													<tr>
													  <td style="width: 460px; height: 30px;"></td>
													</tr>
													<tr>
													  <td style="width: 460px; height: 30px;"></td>
													</tr>
													<tr>
													  <td style="width: 460px; height: 30px;"><input
									 name="generationTrippedMVTotal" type="text" id="generationTrippedMVTotal" class="medium" maxLength="45"
									dojoType="dijit.form.ValidationTextBox" required="true" trim="true" value='<bean:write name="createNercReportForm" property="generationTrippedMVTotal"/>'ucfirst="true"></td>
													</tr>
													<tr>
													  <td style="width: 460px; height: 30px;"><input
									 name="listGenerationTripped" type="text" id="listGenerationTripped" class="medium" maxLength="45"
									dojoType="dijit.form.ValidationTextBox" required="true" trim="true" value='<bean:write name="createNercReportForm" property="listGenerationTripped"/>'ucfirst="true"></td>
													</tr>
												  </tbody>
												</table>
												</td>
											  </tr>
											  <tr>
												<td sdval="7" sdnum="1033;" width="31">
												<p align="center"><font
									 face="Tahoma, Arial, Helvetica, sans-serif"><font
									 style="font-size: 9pt;" size="2"><b>8.</b></font></font></p>
												</td>
												<td style="vertical-align: top;" width="300">
												<table style="width: 293px; height: 133px;">
												  <tbody>
													<tr>
													  <td style="width: 285px; height: 30px;padding-top:20px" align="right"><font
									 color="#000000"><font face="Tahoma, Arial, Helvetica, sans-serif"><font
									 style="font-size: 9pt;" size="2" class="redtitle1"><b><bean:message key="rules.frequency" bundle="rules" /></b></font></font></font></td>
													</tr>
													<tr>
														<td>&nbsp;</td>
													</tr>
													<tr>
													  <td
									 style="width: 285px; height: 30px; text-align: right;"><font
									 color="#000000"><font face="Tahoma, Arial, Helvetica, sans-serif"><font
									 style="font-size: 9pt;" size="2"><b><bean:message key="rules.priorDisturbance" bundle="rules" /></b></font></font></font></td>
													</tr>
													<tr>
													  <td
									 style="width: 285px; text-align: right; height: 30px;"><font
									 color="#000000"><font face="Tahoma, Arial, Helvetica, sans-serif"><font
									 style="font-size: 9pt;" size="2"><b><bean:message key="rules.maxFrequency" bundle="rules" /></b></font></font></font></td>
													</tr>
													<tr>
													  <td
									 style="width: 285px; text-align: right; height: 30px;"><font
									 color="#000000"><font face="Tahoma, Arial, Helvetica, sans-serif"><font
									 style="font-size: 9pt;" size="2"><b><bean:message key="rules.minFrequency" bundle="rules" /></b></font></font></font></td>
													</tr>
												  </tbody>
												</table>
												</td>
												<td style="vertical-align: top;" width="300">
												<table style="width: 468px; height: 101px;">
												  <tbody>
													<tr>
														<td>&nbsp;</td>
													</tr>
													<tr>
													  <td style="width: 460px; height: 30px;"></td>
													</tr>
													<tr>
													  <td style="width: 460px; height: 30px;"><input
									 name="frequencePriorDisturbance" type="text" id="frequencePriorDisturbance" class="medium" maxLength="45"
									dojoType="dijit.form.ValidationTextBox" required="true" trim="true" value='<bean:write name="createNercReportForm" property="frequencePriorDisturbance"/>'ucfirst="true"></td>
													</tr>
													<tr>
													  <td style="width: 460px; height: 30px;"><input
									 name="frequenceAfterDisturbanceMax" type="text" id="frequenceAfterDisturbanceMax" class="medium" maxLength="45"
									dojoType="dijit.form.ValidationTextBox" required="true" trim="true" value='<bean:write name="createNercReportForm" property="frequenceAfterDisturbanceMax"/>'ucfirst="true"></td>
													</tr>
													<tr>
													  <td style="width: 460px; height: 30px;"><input
									 name="frequenceAfterDisturbanceMin" type="text" id="frequenceAfterDisturbanceMin" class="medium" maxLength="45"
									dojoType="dijit.form.ValidationTextBox" required="true" trim="true" value='<bean:write name="createNercReportForm" property="frequenceAfterDisturbanceMin"/>'ucfirst="true"></td>
													</tr>
												  </tbody>
												</table>
												</td>
											  </tr>
											  <tr>
												<td sdval="3" sdnum="1033;" width="31">
												<p align="center"><font
									 face="Tahoma, Arial, Helvetica, sans-serif"><font
									 style="font-size: 9pt;" size="2"><b>9.</b></font></font></p>
												</td>
												<td style="width: 300px;">
												<p align="right"><font
									 face="Tahoma, Arial, Helvetica, sans-serif"><font
									 style="font-size: 9pt;" size="2"><b><bean:message key="rules.listOfTransmissionLinesTripped" bundle="rules" /></b></font></font></p>
												</td>
												<td
									 style="width: 466px; text-align: left; vertical-align: top;">
												<p>&nbsp;<input name="listTransmissionLine" type="text"  id="listTransmissionLine" class="medium" maxLength="45"
									dojoType="dijit.form.ValidationTextBox" required="true" trim="true" value='<bean:write name="createNercReportForm" property="listTransmissionLine"/>'ucfirst="true"></p>
												</td>
											  </tr>
											  <tr>
												<td sdval="7" sdnum="1033;" width="31">
												<p align="center"><font
									 face="Tahoma, Arial, Helvetica, sans-serif"><font
									 style="font-size: 9pt;" size="2"><b>10.</b></font></font></p>
												</td>
												<td style="vertical-align: top;" width="300">
												<table style="width: 293px; height: 133px;">
												  <tbody>
													<tr>
													  <td style="height: 30px; width: 285px;"><font
									 color="#000000"><font face="Tahoma, Arial, Helvetica, sans-serif"><font
									 style="font-size: 9pt;" size="2"><b><br>
													  </b></font></font></font></td>
													</tr>
													<tr>
													  <td
									 style="height: 30px; text-align: right; width: 285px;"><font
									 color="#000000"><font face="Tahoma, Arial, Helvetica, sans-serif"><font
									 style="font-size: 9pt;" size="2"><b><bean:message key="rules.demandTripped" bundle="rules" /></b></font></font></font></td>
													</tr>
													<tr>
													  <td
									 style="text-align: right; height: 30px; width: 285px;"><font
									 color="#000000"><font face="Tahoma, Arial, Helvetica, sans-serif"><font
									 style="font-size: 9pt;" size="2"><b><bean:message key="rules.totalCustomersAffected" bundle="rules" /></b></font></font></font></td>
													</tr>
													<tr>
													  <td
									 style="text-align: right; height: 30px; width: 285px;"><font
									 color="#000000"><font face="Tahoma, Arial, Helvetica, sans-serif"><font
									 style="font-size: 9pt;" size="2"><b><bean:message key="rules.demandLost" bundle="rules" /></b></font></font></font></td>
													</tr>
												  </tbody>
												</table>
												</td>
												<td style="vertical-align: top;" width="300">
												<table style="width: 300px; height: 101px;" border="0">
												  <tbody>
													<tr>
													  <td
									 style="height: 30px; width: 226px; text-align: center;"><font
									 color="#000000"><font face="Tahoma, Arial, Helvetica, sans-serif"><font
									 style="font-size: 9pt;" size="2"><b><bean:message key="rules.firm" bundle="rules" /></b></font></font></font></td>
													  <td style="width: 226px; text-align: center;"><font
									 color="#000000"><font face="Tahoma, Arial, Helvetica, sans-serif"><font
									 style="font-size: 9pt;" size="2"><b><bean:message key="rules.interruptible" bundle="rules" /></b></font></font></font></td>
													</tr>
													<tr>
													  <td colspan="1"
									 style="height: 30px; width: 226px; text-align: center;"><input
									 name="firmDemandTripped" type="text" id="firmDemandTripped" class="medium" maxLength="45"
									dojoType="dijit.form.ValidationTextBox" required="true" trim="true" value='<bean:write name="createNercReportForm" property="interruptibleDemandTripped"/>'ucfirst="true"></td>
													  <td style="text-align: center;"><input name="interruptibleDemandTripped"
									 type="text" id="interruptibleDemandTripped" class="medium" maxLength="45"
									dojoType="dijit.form.ValidationTextBox" required="true" trim="true" value='<bean:write name="createNercReportForm" property="interruptibleDemandTripped"/>'ucfirst="true"></td>
													</tr>
													<tr>
													  <td colspan="1"
									 style="height: 30px; width: 226px; text-align: center;"><input
									 name="noOffFirmCustomersEffected" type="text" id="noOffFirmCustomersEffected" class="medium" maxLength="45"
									dojoType="dijit.form.ValidationTextBox" required="true" trim="true" value='<bean:write name="createNercReportForm" property="noOffFirmCustomersEffected"/>'ucfirst="true"></td>
													  <td style="text-align: center;"><input name="noOffInterruptibleCustomersEffected"
									 type="text" id="noOffInterruptibleCustomersEffected" class="medium" maxLength="45"
									dojoType="dijit.form.ValidationTextBox" required="true" trim="true" value='<bean:write name="createNercReportForm" property="noOffInterruptibleCustomersEffected"/>'ucfirst="true"></td>
													</tr>
													<tr>
													  <td colspan="1"
									 style="height: 30px; width: 226px; text-align: center;"><input
									 name="firmDemandLoss" type="text" id="firmDemandLoss" class="medium" maxLength="45"
									dojoType="dijit.form.ValidationTextBox" required="true" trim="true" value='<bean:write name="createNercReportForm" property="firmDemandLoss"/>'ucfirst="true"></td>
													  <td style="text-align: center;"><input name="interruptibleDemandLoss"
									 type="text" id="interruptibleDemandLoss" class="medium" maxLength="45"
									dojoType="dijit.form.ValidationTextBox" required="true" trim="true" value='<bean:write name="createNercReportForm" property="interruptibleDemandLoss"/>'ucfirst="true"></td>
													</tr>
												  </tbody>
												</table>
												</td>
											  </tr>
											  <tr>
												<td sdval="7" sdnum="1033;" width="31">
												<p align="center"><font
									 face="Tahoma, Arial, Helvetica, sans-serif"><font
									 style="font-size: 9pt;" size="2"><b>11.</b></font></font></p>
													</td>
												<td style="vertical-align: top;" width="300">
												<table style="width: 293px; height: 133px;">
												  <tbody>
													<tr>
													  <td style="height: 30px; width: 285px;padding-top:20px" align="right"><font
									 color="#000000"><font face="Tahoma, Arial, Helvetica, sans-serif"><font
									 style="font-size: 9pt;" size="2" class="redtitle1"><b><bean:message key="rules.restorationTime" bundle="rules" /><br>
													  </b></font></font></font></td>
													</tr>
													<tr>
														<td>&nbsp;</td>
													</tr>
													<tr>
													  <td
									 style="height: 30px; text-align: right; width: 285px;"><font
									 color="#000000"><font face="Tahoma, Arial, Helvetica, sans-serif"><font
									 style="font-size: 9pt;" size="2"><b><bean:message key="rules.transmissionRestorationTime" bundle="rules" /></b></font></font></font></td>
													</tr>
													<tr>
													  <td
									 style="text-align: right; height: 30px; width: 285px;"><font
									 color="#000000"><font face="Tahoma, Arial, Helvetica, sans-serif"><font
									 style="font-size: 9pt;" size="2"><b><bean:message key="rules.generationRestorationTime" bundle="rules" /></b></font></font></font></td>
													</tr>
													<tr>
													  <td
									 style="text-align: right; height: 30px; width: 285px;"><font
									 color="#000000"><font face="Tahoma, Arial, Helvetica, sans-serif"><font
									 style="font-size: 9pt;" size="2"><b><bean:message key="rules.demandRestorationTime" bundle="rules" /></b></font></font></font></td>
													</tr>
												  </tbody>
												</table>
												</td>
												<td style="vertical-align: top;" width="300">
												<table style="width: 300px; height: 101px;" border="0">
												  <tbody>
													<tr>
														<td>&nbsp;</td>
													</tr>
													<tr>
													  <td
									 style="height: 30px; width: 226px; text-align: center;"><font
									 color="#000000"><font face="Tahoma, Arial, Helvetica, sans-serif"><font
									 style="font-size: 9pt;" size="2"><b><bean:message key="rules.restorationTimeInitial" bundle="rules" /></b></font></font></font></td>
													  <td style="width: 226px; text-align: center;"><font
									 color="#000000"><font face="Tahoma, Arial, Helvetica, sans-serif"><font
									 style="font-size: 9pt;" size="2"><b><bean:message key="rules.restorationTimeFinal" bundle="rules" /></b></font></font></font></td>
													</tr>
													<tr>
													  <td colspan="1"
									 style="height: 30px; width: 226px; text-align: center;"><input
									 name="initialTransmission" type="text" id="initialTransmission" class="medium" maxLength="45"
									dojoType="dijit.form.ValidationTextBox" required="true" trim="true" value='<bean:write name="createNercReportForm" property="initialTransmission"/>'ucfirst="true"></td>
													  <td style="text-align: center;"><input name="finalTransmission"
									 type="text" id="finalTransmission" class="medium" maxLength="45"
									dojoType="dijit.form.ValidationTextBox" required="true" trim="true" value='<bean:write name="createNercReportForm" property="finalTransmission"/>'ucfirst="true"></td>
													</tr>
													<tr>
													  <td colspan="1"
									 style="height: 30px; width: 226px; text-align: center;"><input
									 name="initialGeneration" type="text" id="initialGeneration" class="medium" maxLength="45"
									dojoType="dijit.form.ValidationTextBox" required="true" trim="true" value='<bean:write name="createNercReportForm" property="initialGeneration"/>'ucfirst="true"></td>
													  <td style="text-align: center;"><input name="finalGeneration"
									 type="text" id="finalGeneration" class="medium" maxLength="45"
									dojoType="dijit.form.ValidationTextBox" required="true" trim="true" value='<bean:write name="createNercReportForm" property="finalGeneration"/>'ucfirst="true"></td>
													</tr>
													<tr>
													  <td colspan="1"
									 style="height: 30px; width: 226px; text-align: center;"><input
									 name="initialDemand" type="text" id="initialDemand" class="medium" maxLength="45"
									dojoType="dijit.form.ValidationTextBox" required="true" trim="true" value='<bean:write name="createNercReportForm" property="initialDemand"/>'ucfirst="true"></td>
													  <td style="text-align: center;"><input name="finalDemand"
									 type="text" id="finalDemand" class="medium" maxLength="45"
									dojoType="dijit.form.ValidationTextBox" required="true" trim="true" value='<bean:write name="createNercReportForm" property="finalDemand"/>'ucfirst="true"></td>
													</tr>
												  </tbody>
												</table>
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
<logic:notEqual name="flag"  value="fromDashboard">                    
                        <button dojoType="dijit.form.Button"  type="button"  onclick="window.location =  '<%=ServerUtils.getContextName(request)%>/RulesHome.do?operation=loadRulesHome'  ">
                            <bean:message key="datasource.home" bundle="ds" />
                        </button>
</logic:notEqual>

<logic:equal name="flag"  value="fromViolations">
                        <button dojoType="dijit.form.Button"  type="button" onClick="window.location= '<%=ServerUtils.getContextName(request)%>/ManageRules.do?operation=showAllViolations&pageNo=1&startDate=<logic:present name="startDate"><bean:write name="startDate" /></logic:present><logic:present name="searchNercViolationsForm" property="startDate"><bean:write name="searchNercViolationsForm" property="startDate" /></logic:present>&endDate=<logic:present name="endDate"><bean:write name="endDate" /></logic:present><logic:present name="searchNercViolationsForm" property="endDate"><bean:write name="searchNercViolationsForm" property="endDate" /></logic:present>&flag=fromViolations'">
                            <bean:message key="datasource.back" bundle="ds" /> 
                         </button>
</logic:equal>

<logic:equal name="flag"  value="fromNerc">
                        <button dojoType="dijit.form.Button"  type="button" onClick="window.location= '<%=ServerUtils.getContextName(request)%>/searchNercViolationsAction.do?operation=searchNercViolations&startDate=<logic:present name="startDate"><bean:write name="startDate" /></logic:present><logic:present name="searchNercViolationsForm" property="startDate"><bean:write name="searchNercViolationsForm" property="startDate" /></logic:present>&endDate=<logic:present name="endDate"><bean:write name="endDate" /></logic:present><logic:present name="searchNercViolationsForm" property="endDate"><bean:write name="searchNercViolationsForm" property="endDate" /></logic:present>&flag=fromNerc'">
                            <bean:message key="datasource.back" bundle="ds" /> 
                         </button>
</logic:equal>                         


                           
                         <button dojoType="dijit.form.Button" id="save" type="button" onClick="return submitReport()" >
                             <bean:message key="datasource.save" bundle="ds" />
                         </button>
                        </td>
    </tr>
</table>
</form>
</body> 
</html:html>
 