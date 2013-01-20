<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@page import="com.enterprisehorizons.magma.server.util.ServerUtils"%>
<%@ include file="/common/dojo.jsp"%>
<html:html locale="true">
<head>
<link href="css/sti.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=ServerUtils.getContextName(request)%>/js/rules.js" ></script>

<script>
var txtAlertViolationStr ="";
function validateUniqueInAlertVioliation(textValue,textBoxObj) { 
		prepareStr();
var uniqueCount =0;
var array  	= txtAlertViolationStr.split(',');

	for(var k=0;k<array.length-1;k++){
		
		if(array[k] == textValue)
         uniqueCount++;
	}
		if(uniqueCount >1){
        textBoxObj.focus();
	showEmptyDialog("'" + textValue + "' <bean:message key="rules.check.value.usage" bundle="rules" />", "<bean:message key="rules.alert.title" bundle="rules" />");
	return false;
	}
	
	return true;

	}

	function prepareStr() { 
		txtAlertViolationStr ="";
		
		for(var j=0;j<allAlertViolationTextBox.length;j++)
			{
				if(document.getElementById(allAlertViolationTextBox[j].name) != null)
					txtAlertViolationStr = txtAlertViolationStr+document.getElementById(allAlertViolationTextBox[j].name).value+",";
		}
	}	
	function delayFormValidation(){
      showProgressDialog('<bean:message key="admin.common.inprogress" bundle="admin" />','');
	  setTimeout ( "validateForm()", 1500 );
	}
function validateForm(){
	hideProgressDialog();
	
    if(!chkDescriptionLenIncidentRules()){
		return false;
	}
    if(isSplChar(dijit.byId('incidentDesc').attr('value')))
	{
		return false;
	}
	
	for(var j=0;j< allAlertViolationNameBox.length;j++)
	{       
		  var objName = allAlertViolationNameBox[j].name;
		  if(objName != null)
		  {
			  if(objName.indexOf('alertQry') != -1){
				  var suffixVal = objName.substring(8) ;
				  var alertObj =document.getElementById("alertName"+suffixVal);		
  				  var qryObj = document.getElementById(objName);
   				 if(alertObj != null &&  trimAll(alertObj.value).length == 0 && qryObj != null && trimAll(qryObj.value).length !=0 )
					{
						showEmptyDialog("<bean:message key="rules.check.alert.mandatory" bundle="rules" />", "");	
						return false;
					}
				}
			  if(objName.indexOf('violationQry') != -1){
				  var suffixVal = objName.substring(12) ;
				  var violationObj =document.getElementById("violationName"+suffixVal);		
  				  var qryObj = document.getElementById(objName);
					if(violationObj != null && trimAll(violationObj.value).length == 0 && qryObj != null && trimAll(qryObj.value).length !=0 )
					{
						showEmptyDialog("<bean:message key="rules.check.alert.mandatory" bundle="rules" />", "");	
						return false;
					}
				} 
		 }	
	}

	if(dijit.byId("incidentName").value == "")
	{
			dijit.byId("incidentName").focus();
			showEmptyDialog("<bean:message key="rules.check.incident.name" bundle="rules" />", "<bean:message key="rules.alert.title" bundle="rules" />");	
			
			return false;
	}
	showProgressDialog('<bean:message key="admin.common.inprogress" bundle="admin" />','');
	 hideProgressDialog();
    document.getElementById('violationListenerCount').value = violationCount;
    document.getElementById('violationQryCount').value = violationCount;
    document.getElementById('alertListenerCount').value = alertCount;
    document.getElementById('alertQryCount').value = alertCount;
    document.getElementById('operation').value = 'incidentRules';
	var alerVoilationCount = 0;
	if(dijit.byId("rulesForm").validate()){

		for(var i=0;i<allAlertViolationTextBox.length;i++){
		
		var textBoxObj=  document.getElementById(allAlertViolationTextBox[i].name);
		
		if(textBoxObj != null && trimAll(textBoxObj.value) != ""){
		alerVoilationCount++;
		if(!validateUniqueInAlertVioliation(textBoxObj.value,textBoxObj)){
			
		return false;
		}
		}
		}
		if(alerVoilationCount == 0 ){
			showEmptyDialog("<bean:message key="rules.check.alert.count" bundle="rules" />", "");	
			return false;
		}
		for(var i=0;i<allAlertViolationTextBox.length;i++){
        var tempValueStr = document.getElementById(allAlertViolationTextBox[i].name) ;

		if(tempValueStr  != null && trimAll(tempValueStr.value) != ''){
				var tempValue = tempValueStr.name;				
				var seqAlert= tempValue.split('alertName');
				var qryname ='alertQry'+seqAlert[1];
				if(seqAlert.length-1 == 1){
					var qryAlertObj = document.getElementById(qryname);
				if(qryAlertObj != null && trimAll(qryAlertObj.value).length == 0 ){
					qryAlertObj.focus();
					showEmptyDialog( "<bean:message key="rules.check.query.alert" bundle="rules" /> "+tempValueStr.value , "<bean:message key="rules.alert.title" bundle="rules" />");
					return false;
				}
			}	

				var seqViolation= tempValue.split('violationName');
								var qryViolationname ='violationQry'+seqViolation[1];

				if(seqViolation.length-1 == 1){
             var qryViolationObj = document.getElementById(qryViolationname);
				if(qryViolationObj!= null && trimAll(qryViolationObj.value).length == 0){
					qryViolationObj.focus();
					showEmptyDialog( "<bean:message key="rules.check.query.violation" bundle="rules" /> "+tempValueStr.value  , "<bean:message key="rules.alert.title" bundle="rules" />");
					return false;
				}
				}
				
		
		}
		}

		for(i=0;i<allTextBox.length;i++){
			var tempArray = allTextBox[i].value.split('result');
			var tempObj = document.getElementById(tempArray[0]) ;
			//alert(allTextBox.length);
			//if(document.getElementById(allTextBox[i].value) == null)
			//	continue;
			if(document.getElementById(allTextBox[i].value) != null && (document.getElementById(allTextBox[i].value).innerHTML == '<b class="error"><bean:message key="rules.label.notavailable" bundle="rules" /></b>' || document.getElementById(allTextBox[i].value).innerHTML == '<B class=error><bean:message key="rules.label.notavailable" bundle="rules" /></B>')){
				tempObj.focus();
				showEmptyDialog(tempObj.value+" <bean:message key="rules.check.value.unavailable" bundle="rules" />", "<bean:message key="rules.alert.title" bundle="rules" />");	
					return false;
			}
		}
		

		for(var j=0;j< allParamNameBox.length;j++)
		{
		  var objName =  "";
		  var suffixVal =  "";
		  var paramNameObj = "";
		  var paramValObj = "";
		  var paramTypeObj = "";		   
		  if(allParamNameBox[j] != "")	
			  objName = allParamNameBox[j];
		  if(objName != null)
		  {
			  if(objName.indexOf('alertParamName') != -1){
				   suffixVal = objName.substring(14) ;
			

				//For alert	
							  paramNameObj = document.getElementById('alertParamName'+suffixVal);
							  paramValObj = document.getElementById('alertParamValue'+suffixVal);
							  paramTypeObj = document.getElementById('alertParamType'+suffixVal);
				
							if((paramNameObj != null && paramNameObj.value.length == 0) || (paramValObj != null &&  paramValObj.value.length ==0) || (paramTypeObj != null && paramTypeObj.value.length ==0) )
							{
								showEmptyDialog("<bean:message key="rules.check.name.mandatory" bundle="rules" />", "");	
								return false;
							}

							if(paramNameObj != null){
								if(!IsNumeric(paramNameObj.value,paramValObj.value,paramTypeObj.value))
								{
									return false;
								}
							}							

				}	
				
			  if(objName.indexOf('violationParamName') != -1){
				   suffixVal = objName.substring(18) ;
				
				//For violation			
				
							  paramNameObj = document.getElementById('violationParamName'+suffixVal);
							  paramValObj = document.getElementById('violationParamValue'+suffixVal);
							  paramTypeObj = document.getElementById('violationParamType'+suffixVal);
				
							if((paramNameObj != null && paramNameObj.value.length == 0) || (paramValObj != null &&  paramValObj.value.length ==0) || (paramTypeObj != null && paramTypeObj.value.length ==0) )
							{
								showEmptyDialog("<bean:message key="rules.check.name.mandatory" bundle="rules" />", "");	
								return false;
							}

							if(paramNameObj != null){
								if(!IsNumeric(paramNameObj.value,paramValObj.value,paramTypeObj.value))
								{
									return false;
								}
							}							
				}
			}
		}
		
    document.rulesForm.submit();
	}
	else
		return false;
	
}

var listenerArrValue = [];

</script>

</head>
<body class="tundra bodybg">
<form name="rulesForm" id="rulesForm" dojoType='dijit.form.Form'
	method="post" action="<%=ServerUtils.getContextName(request)%>/ConfigureRules.do"
	enctype="multipart/form-data"><!----  HIden Variables --> <input
	type="hidden" name="violationListenerCount" id="violationListenerCount" />
<input type="hidden" name="violationQryCount" id="violationQryCount" />
<input type="hidden" name="alertListenerCount" id="alertListenerCount" />
<input type="hidden" name="alertQryCount" id="alertQryCount" /> <input
	type="hidden" name="operation" id="operation" value="incidentRules" />

<table width="100%" cellspacing="0" cellpadding="0">
	<tr>
		<td align="left">
		<table width="1000" cellpadding="0" cellspacing="0" align="left"
			id="mainTable" border="0">
			<tr>
			    <td width="200px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td align="left" valign="top" align="justify">
				<table width="100%"cellspacing="0" cellpadding="0" border="0">
				    <tr>
						<td width="67px">&nbsp;</td>
						<td width="938" height="37">&nbsp;</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td height="37" valign="top" class="redtitle"><strong><bean:message
							key="rules.incidentRules" bundle="rules" /> </strong><br />
						<span class="bodytext"></span></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td style="width: 500px;" nowrap="nowrap" align="left"><font
							color="blue" style="font-family: Tahoma; font-size: 12px;"><b>
						<html:messages id="incidentCreated" message="true" bundle="rules">
							<bean:write name="incidentCreated" />
						</html:messages> </b></font> <font color="red" style="font-family: Tahoma; font-size: 12px;"><b><html:errors
							bundle="rules" /></b></font></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td colspan="2" class="error"><!--  <html:errors bundle="ecoweb"/>   -->
						</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td align="left" class="redtitle">
						<table width="100%" cellspacing="0" cellpadding="0" border="0">
							<tr>
								<td width="13%"><strong><bean:message
									key="rules.incidentName" bundle="rules" /><label class="error">*</label>:</strong>&nbsp;</td>
								<td width="10%"><input type="text" id="incidentName"
									name="incidentName" class="medium" maxLength="45"
									dojoType="dijit.form.ValidationTextBox" trim="true"
									ucfirst="true" onBlur="validateUniqueness(this);" />
								<span id="incidentNameresult" align="center"><b>&nbsp;</b></span>
								</td>
								<td width="50px">&nbsp;</td>
								<td width="10%"><strong><bean:message
									key="rules.description" bundle="rules" />:</strong>&nbsp;</td>
								<td>&nbsp;<textarea dojoType="dijit.form.SimpleTextarea"
									rows="5" cols="30" id="incidentDesc"
									style="width: 28em; height: 7em" name="incidentDesc"></textarea></td>
							</tr>
							<tr>
								<td height="13px"></td>
							</tr>
							<!--                    *********************************************************************************************************************************************  -->
							<!--                    ****************************************************   DYNAMIC ALERTS DETAILS STARTS  *******************************************************  -->
							<!--                    *********************************************************************************************************************************************  -->
							<tr>
								<td class="redtitle1" colspan="5" height="30px">&nbsp;</td>
							</tr>
							<tr>
								<td colspan="5" width="100%">
								<fieldset><legend class="redtitle1"><bean:message
									key="rules.alertCaption" bundle="rules" /></legend>
								<table width="100%">
									<tr>
										<td align="right" style="padding-right: 5px;" colspan="5"
											width="100%"><img src="<%=ServerUtils.getContextName(request)%>/images/icon_plus_1.png"
											width="9px" height="9px" style="cursor: pointer"
											onclick="addAlertDtls()" title="Add Alert" /></td>
									</tr>
									<tr>
										<td width="100%" colspan="5">
											<tbody id="alertDtls"></tbody>
											<tbody id="incidentTable" width="100%" border="0">
												<tr id="alertRow1">
													<td width="100%">
													<fieldset>
													<table width="100%" border="0">
														<tr>
															<td>&nbsp;</td>
														</tr>
														<tr>
															<td width="100%">
														<tr>
															<td colspan="3" width="100%">
															<table border="0" width="100%">
																<tr>
																	<td width="10%" align="right"
																		style="padding-left: 20px;" nowrap="nowrap"><strong><bean:message key="rules.manageRules.name" bundle="rules"/><label
																		class="error">*</label>:</strong>&nbsp;</td>
																	<td align="left" width="30%"><input type="text"
																		id="alertName0" name="alertName0" class="medium"
																		maxLength="45" required="true" trim="true"
																		ucfirst="true"
																		onBlur="prepareTextBoxArray(this);validateUniqueness(this);" /></br><span id="alertName0result" align="center"><b>&nbsp;</b></span>
																	</td>																	
																	<td width="20%" align="right" style="padding-left: 20px;"><strong><bean:message
																		key="rules.violationQry" bundle="rules" /><label
																		class="error">*</label>:</strong>&nbsp;</td>
																	<td width="40%"><textarea id="alertQry0"
																		style="width: 40em; height: 3em"
																		onblur="prepareAlertViolationNameArray(this);chkQueryDescriptionLen(this);"
																		name="alertQry0" required="true" trim="true"
																		ucfirst="true"
																		onkeyup="showAlertParamDtls(this,'alertParamDtls0', 'alertParamMapTitle', 'alertParamMapDtls', 0)"></textarea>
																	</td>
																</tr>
															</table>
															</td>															
														</tr>
														<tr id="alertParamMapTitle" style="display: none">
															<td class="redtitle1" colspan="2"
																style="padding-left: 71px;"><bean:message key="rules.parammap.label" bundle="rules" /></td>
														</tr>
														<tr id="alertParamMapDtls" style="display: none">
															<td colspan="6" align="left" style="padding-left: 70px">
															<table border="0">
																<tbody id="alertParamDtls0"></tbody>
															</table>
															</td>
														</tr>
														<tr>
															<td coslpan="3"><br>
															</td>
														</tr>
														<tr>
															<td colspan="3" width="100%">
															<table border="0" width="75%">
																<tr>
																	<td width="10%" align="right" style="padding-left: 20px;"><strong><bean:message
																		key="rules.listener" bundle="rules" />:</strong>&nbsp;</td>
																	<td width="30%" ><select
																		id="alertListener0"
																		dojoType="dijit.form.FilteringSelect"
																		name="alertListener0" autoComplete="true"
																		invalidMessage="Invalid Listener"
																		onchange="enableRAS(this,'alertRASProcess0');">
																		<option value=""></option>
																		<logic:present name="allListenersList" scope="session">
																			<logic:notEqual name="allListenersList" value="null">
																				<logic:iterate id="allListenersList1"
																					name="allListenersList">
																					<option
																						title='<bean:write name="allListenersList1" property="listenerName" />'
																						value='<bean:write name="allListenersList1" property="listenerId" />'><bean:write
																						name="allListenersList1" property="listenerName" />
																					</option>
																				</logic:iterate>
																			</logic:notEqual>
																		</logic:present>
																		<logic:equal name="allListenersList" value="null">
																			<option value=""><bean:message
																				key="rules.listeners.nodatafound" bundle="rules" /></option>
																		</logic:equal>
																	</select></td>
																	<td width="20%" align="right"><strong><bean:message
																		key="rules.rasProcess" bundle="rules" />:</strong>&nbsp;</td>
																	<td width="38%" align="left" style=""><select
																		id="alertRASProcess0"
																		dojoType="dijit.form.FilteringSelect"
																		name="alertRASProcess0" autoComplete="true"
																		invalidMessage="Invalid Listener" disabled="true">
																		<option value=""></option>
																		<logic:present name="allRASProcessesList"
																			scope="session">
																			<logic:notEqual name="allRASProcessesList" value="null">
																				<logic:iterate id="allRASProcessesList1"
																					name="allRASProcessesList">
																					<option
																						title='<bean:write name="allRASProcessesList1" property="rasProcessName" />'
																						value='<bean:write name="allRASProcessesList1" property="rasProcessId" />'><bean:write
																						name="allRASProcessesList1"
																						property="rasProcessName" /></option>
																				</logic:iterate>
																			</logic:notEqual>
																		</logic:present>
																		<logic:equal name="allRASProcessesList1" value="null">
																			<option value=""><bean:message
																				key="rules.listeners.nodatafound" bundle="rules" /></option>
																		</logic:equal>
																	</select></td>
																</tr>
															</table>
															</td>
														</tr>
														<tr>
															<td coslpan="4"><br>
															</td>
														</tr>
														</td>
														</tr>
													</table>
													</fieldset>
													</td>
													<td valign="top" align="right"><img
														src="<%=ServerUtils.getContextName(request)%>/images/icon_dash_1.png" width="9px" height="9px"
														style="cursor: pointer"
														onclick="removeAlertDtls('alertRow1', 'incidentTable' );"
														title='<bean:message key="rules.removeAlert" bundle="rules"/>'/ >
													</td>
												</tr>
											</tbody>
										</td>
									</tr>
								</table>
								</fieldset>
								</td>
							</tr>
							<!--                    *********************************************************************************************************************************************  -->
							<!--                    ****************************************************   DYNAMIC ALERTS DETAILS ENDS  *********************************************************  -->
							<!--                    *********************************************************************************************************************************************  -->



							<!--                    *********************************************************************************************************************************************  -->
							<!--                    *************************************************   DYNAMIC VIOLATION DETAILS STARTS  *******************************************************  -->
							<!--                    *********************************************************************************************************************************************  -->


							<tr>
								<td class="redtitle1" colspan="5" height="30px">&nbsp;</td>
							</tr>
							<tr>
								<td colspan="5" width="100%">
								<fieldset><legend class="redtitle1"><bean:message
									key="rules.violationCaption" bundle="rules" /></legend>
								<table width="100%">
									<tr>
										<td align="right" style="padding-right: 5px;" colspan="5"
											width="100%"><img src="<%=ServerUtils.getContextName(request)%>/images/icon_plus_1.png"
											width="9px" height="9px" style="cursor: pointer"
											onclick="addVioliationDtls()" title="Add Violation" /></td>
									</tr>
									<tr>
										<td width="100%" colspan="5">
									<tbody id="violationDtls"></tbody>
									<tbody id="incidentTable1" width="100%" border="0">
										<tr id="violationRow1">
											<td width="100%">
											<fieldset>
											<table width="100%" border="0">
												<tr>
													<td>&nbsp;</td>
												</tr>
												<tr>
													<td width="100%">
														<tr>
															<td colspan="3" width="100%">
															<table border="0" width="100%">
																<tr>
																	<td width="10%" align="right"
																		style="padding-left: 20px;" nowrap="nowrap"><strong><bean:message key="rules.manageRules.name" bundle="rules"/><label
																		class="error">*</label>:</strong>&nbsp;</td>
																	<td align="left" width="30%"><input type="text"
																		id="violationName0" name="violationName0" class="medium"
																		maxLength="45" required="true" trim="true"
																		ucfirst="true"
																		onBlur="prepareTextBoxArray(this);validateUniqueness(this);" />
																	<span id="violationName0result" align="center"><b>&nbsp;</b></span>
																	</td>
																	<td width="20%" align="right"><strong><bean:message
																		key="rules.violationQry" bundle="rules" />&nbsp;<label
																		class="error">*</label>:</strong>&nbsp;</td>
																	<td width="40%"><textarea id="violationQry0"
																		style="width: 40em; height: 3em"
																		onblur="prepareAlertViolationNameArray(this);chkQueryDescriptionLen(this);"
																		name="violationQry0" required="true" trim="true"
																		ucfirst="true"
																		onkeyup="showVioliationParamDtls(this,'violationParamDtls0', 'violationParamMaptitle', 'violationParamMapDtls', 0)"></textarea>
																	</td>
																</tr>
															</table>
															</td>
														</tr>
														<tr id="violationParamMaptitle" style="display: none">
															<td class="redtitle1" colspan="2"
																style="padding-left: 71px;"><bean:message key="rules.parammap.label" bundle="rules" /></td>
														</tr>
														<tr id="violationParamMapDtls" style="display: none">
															<td colspan="6" align="left" style="padding-left: 70px">
															<table border="0">
																<tbody id="violationParamDtls0"></tbody>
															</table>
															</td>
														</tr>
														<tr>
															<td coslpan="3"><br>
															</td>
														</tr>
														<tr>
															<td>
															<table border="0" width="75%">
																<tr>
																	<td width="10%" align="right"><strong><bean:message
																		key="rules.listener" bundle="rules" />:</strong>&nbsp;</td>
																	<td width="30%" align="left" style=""><select
																		id="violiationListener0"
																		dojoType="dijit.form.FilteringSelect"
																		name="violationListener0" autoComplete="true"
																		invalidMessage="Invalid Listener"
																		onchange="enableRAS(this,'violationRASProcess0');">
																		<option value=""></option>
																		<logic:present name="allListenersList" scope="session">
																			<logic:notEqual name="allListenersList" value="null">
																				<logic:iterate id="allListenersList1"
																					name="allListenersList">
																					<option
																						title='<bean:write name="allListenersList1" property="listenerName" />'
																						value='<bean:write name="allListenersList1" property="listenerId" />'><bean:write
																						name="allListenersList1" property="listenerName" />
																					</option>
																				</logic:iterate>
																			</logic:notEqual>
																		</logic:present>
																		<logic:equal name="allListenersList" value="null">
																			<option value=""><bean:message
																				key="rules.listeners.nodatafound" bundle="rules" /></option>
																		</logic:equal>
																	</select></td>
																	<td width="22%" align="right" style=""><strong><bean:message
																		key="rules.rasProcess" bundle="rules" />:</strong>&nbsp;</td>
																	<td width="38%" align="left" style=""><select
																		id="violationRASProcess0"
																		dojoType="dijit.form.FilteringSelect"
																		name="violationRASProcess0" autoComplete="true"
																		invalidMessage="Invalid Listener" disabled="true">
																		<option value=""></option>
																		<logic:present name="allRASProcessesList"
																			scope="session">
																			<logic:notEqual name="allRASProcessesList" value="null">
																				<logic:iterate id="allRASProcessesList1"
																					name="allRASProcessesList">
																					<option
																						title='<bean:write name="allRASProcessesList1" property="rasProcessName" />'
																						value='<bean:write name="allRASProcessesList1" property="rasProcessId" />'><bean:write
																						name="allRASProcessesList1"
																						property="rasProcessName" /></option>
																				</logic:iterate>
																			</logic:notEqual>
																		</logic:present>
																		<logic:equal name="allRASProcessesList1" value="null">
																			<option value=""><bean:message
																				key="rules.listeners.nodatafound" bundle="rules" /></option>
																		</logic:equal>
																	</select></td>
																</tr>
															</table>
															</td>
														</tr>
														<tr>
															<td coslpan="4"><br></td>
														</tr>
													</td>
												</tr>
											</table>
											</fieldset>
											</td>
											<td valign="top" align="right"><img
												src="<%=ServerUtils.getContextName(request)%>/images/icon_dash_1.png" width="9px" height="9px"
												style="cursor: pointer"
												onclick="removeVioliationDtls('violationRow1', 'incidentTable1' );"
												title='<bean:message key="rules.removeViolation" bundle="rules"/>'/ ></td>
										</tr>
									</tbody>
									</td>
									</tr>
								</table>
								</fieldset>
								</td>
							</tr>

							<!--                    *********************************************************************************************************************************************  -->
							<!--                    *************************************************   DYNAMIC VIOLATION DETAILS ENDS  *********************************************************  -->
							<!--                    *********************************************************************************************************************************************  -->

						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td colspan="5" width="1000px" height="13px">
				
				</td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td colspan="5" class="barColor" align="right" width="1000px">
		<button dojoType="dijit.form.Button" type="button"
			onclick="window.location =  '<%=ServerUtils.getContextName(request)%>/RulesHome.do?operation=loadRulesHome ' ">
		<bean:message key="datasource.home" bundle="ds" /></button>
		<button dojoType="dijit.form.Button" type="button"
			onClick="window.location='<%=ServerUtils.getContextName(request)%>/RulesHome.do?operation=loadRules'">
		<bean:message key="datasource.back" bundle="ds" /></button>
		<button dojoType="dijit.form.Button" id="save" type="button"
			onClick="return delayFormValidation()"><bean:message
			key="datasource.save" bundle="ds" /></button>
		</td>
	</tr>

</table>



</form>
</body>
<script>
var tempAttList = {identifier:"value",items:[{value: "" , label: ""}]};
var    attrStore = new dojo.data.ItemFileReadStore({data: tempAttList});
var attributeTypeList= [];

<logic:present name="allAttributesList">    
<logic:iterate name="allAttributesList" id="allAttributesList">
var attributeType = '<bean:write name="allAttributesList" property="attributeType"/>';
var attributeTypeId = '<bean:write name="allAttributesList" property="attributeTypeId"/>';

attributeTypeList.push({value: attributeTypeId , label: attributeType});   
</logic:iterate>
</logic:present>
tempAttList = {identifier:"value",items:attributeTypeList};
attrStore =  new dojo.data.ItemFileReadStore({data: tempAttList});

listenerArrValue.push({value:"" , label:""});

<logic:present name="allListenersList">                                                                                
<logic:iterate id="allListenersList1" name="allListenersList" >
var listenerName = 	'<bean:write name="allListenersList1" property="listenerName"/>';	
var listenerId = 	'<bean:write name="allListenersList1" property="listenerId"/>';	
listenerArrValue.push({value:listenerId , label:listenerName});
</logic:iterate>
</logic:present>
var listenerlist= {identifier:"value",items:listenerArrValue};
var listenserstore = new dojo.data.ItemFileReadStore({data: listenerlist});


var rasProcessListValue= [];

rasProcessListValue.push({value:"" , label:""});
<logic:present name="allRASProcessesList" scope="session">                                                                                
<logic:notEqual name="allRASProcessesList"  value="null">
	<logic:iterate id="allRASProcessesList1" name="allRASProcessesList" >
	var rasProcessName = 	'<bean:write name="allRASProcessesList1" property="rasProcessName" />';	
	var rasProcessId = 	'<bean:write name="allRASProcessesList1" property="rasProcessId" />';	

	rasProcessListValue.push({value:rasProcessId , label:rasProcessName});
	</logic:iterate>
</logic:notEqual>   
</logic:present>
var rasProcessList= {identifier:"value",items:rasProcessListValue};
var rasProcessStore = new dojo.data.ItemFileReadStore({data: rasProcessList});


</script>
</html:html>
