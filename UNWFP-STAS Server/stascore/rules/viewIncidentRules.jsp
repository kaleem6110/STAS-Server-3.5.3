	<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
	<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
	<%@ taglib uri="/tags/struts-html" prefix="html"%>
	<%@ include file="/common/dojo.jsp"%>
	<%@page import="com.enterprisehorizons.magma.server.util.ServerUtils"%>
	<%
	java.util.List ruleIncidentList  = (java.util.List) request.getAttribute("ruleIncidentBean");
	String status = (String) request.getAttribute("status");
	boolean deleteExists = false;
	String incidentUpdatedVal = (String) request.getAttribute("incidentUpdated");
	boolean updateExists = false;
	if(incidentUpdatedVal != null && incidentUpdatedVal.equals("true"))
	{
		updateExists = true;
	}


	if(status != null && status.equals("delete"))
	{
		deleteExists = true;
	}
	boolean alertExists = false;
	boolean violationExists = false;

	%>

	<html:html locale="true">
	<head>
	<script type="text/javascript" src="<%=ServerUtils.getContextName(request)%>/js/rules.js" ></script>
	<script>
	var listenerArrValue = [];

	   function submitForm(tmp)
	    {
	       if(tmp.value == dijit.byId('idDelete').value)
	        {
				document.getElementById('violationQryCount').value = violationCount;
				document.getElementById('alertQryCount').value = alertCount;
	            document.forms[0].action = "<%=ServerUtils.getContextName(request)%>/ConfigureRules.do?operation=deleteIncidentRule";
	            document.forms[0].submit();
	        }        
	                
	    }

	function setIncidentId(id)
	{
	    document.getElementById('incidentId').value = id;
	}

	</script>
	</head>
	<body class="tundra bodybg">	
	<form name="rulesForm" method="post" action="<%=ServerUtils.getContextName(request)%>/ConfigureRules.do">
	<!----  HIden Variables -->
	<input type="hidden" name="violationQryCount" id="violationQryCount" value=""/>
	<input type="hidden" name="alertQryCount" id="alertQryCount" value="" />
	<input type="hidden" name="operation" id="operation" value="updateIncidentRules"/> 
	<input type="hidden" name="incidentId" id="incidentId" value="<bean:write name="rulesForm" property="incidentId"/>"/> 
	<table width="100%" cellspacing="0" cellpadding="0" align="center"  border="0" >
	    <tr>
	        <td align="left">
	<table width="100%" cellspacing="0" cellpadding="0" align="center"  border="0" >
							<%if(updateExists){%>
	                       <tr>
	                         <td  class="pageTitle paddingTitle"> <strong>
								<bean:message key="rules.updateIncidentRules" bundle="rules" />
								</strong><br />
	                            <span class="bodytext"></span></td>
	                        </tr>
							<tr>
							   <td class="paddingTitleDesc">
									<bean:message key="rules.updateIncidentRules.description" bundle="rules" />               
								</td>  
							</tr>							
							<%}else if(deleteExists){%>
	                       <tr>
	                         <td  class="pageTitle paddingTitle"> <strong>
								<bean:message key="rules.deleteIncidentRules" bundle="rules" />
								</strong><br />
	                            <span class="bodytext"></span></td>
	                        </tr>
							<tr>
							   <td class="paddingTitleDesc">
									<bean:message key="rules.deleteIncidentRules.description" bundle="rules" />               
								</td>  
							</tr>							
							<%}else{%>
	                       <tr>
	                         <td  class="pageTitle paddingTitle"> <strong>
								<bean:message key="rules.viewIncidentRules" bundle="rules" /> 
								</strong><br />
	                            <span class="bodytext"></span></td>
	                        </tr>
							<tr>
							   <td class="paddingTitleDesc">
									<bean:message key="rules.viewIncidentRules.description" bundle="rules" />               
								</td>  
							</tr>							
							<%}%>
							<tr>
								<td style="padding-left:67px; width:500px;" nowrap="nowrap" align="left">
											   <font color="blue" style="font-family: Tahoma;font-size: 12px;" ><b>
												 <html:messages id="incidentUpdated" message="true" bundle="rules"><bean:write name="incidentUpdated" /></html:messages>
												</b></font> 
												<font color="red" style="font-family: Tahoma;font-size: 12px;"><b><html:errors bundle="rules"/></b></font>  
								</td>   
	                      </tr>
	                        <tr>
	                             <td style="padding-top:30px;padding-left:67px;width:900px">
	                                <table width="90%" cellspacing="0" cellpadding="0" border="00" >
	                                    <tr>
	                                        <td width="13%"><strong><bean:message key="rules.incidentName" bundle="rules" /><label class="error">*</label>:</strong></td>
	                                        <td width="30%">&nbsp;
	                                          <input type="text" id="incidentName" name="incidentName" class="medium" maxLength="45" disabled="disabled"
	                                    dojoType="dijit.form.ValidationTextBox" required="true" trim="true" ucfirst="true" value="<bean:write name="rulesForm" property="incidentName"/>" />

	                                    </td>
	                                    <td width="30px"></td>                                   
	                                    <td width="10%"><strong><bean:message key="rules.description" bundle="rules" />:</strong></td>
	                                    <td>&nbsp;<textarea dojoType="dijit.form.Textarea" id="incidentDesc" style="width:28em;height:7em" disabled="disabled"
	            name="incidentDesc" dojoType="dijit.form.ValidationTextBox" required="false" trim="true"  ucfirst="true" ><bean:write name="rulesForm" property="incidentDesc"/></textarea>
	                                    </td>
	                                    
	                                    </tr>
	                                    <tr> 
	                                        <td height="13px"></td>
	                                    </tr>
	<!--                    *********************************************************************************************************************************************  -->
	<!--                    *******************************************DYNAMIC ALERT DETAILS****************************************************************  -->
	<!--                    *********************************************************************************************************************************************  -->
	                                    <tr>
	                                        <td class="redtitle1" colspan="5" height="30px">&nbsp;</td>
	                                    </tr>
	                                    <tr>
	                                        <td colspan=5 width="100%">
	                                            <fieldset>
	                                            <legend class="redtitle1" ><bean:message key="rules.alertCaption" bundle="rules" /></legend>
												<logic:present name="ruleIncidentBean">
													<logic:notEqual name="ruleIncidentBean"  value="null">
												  <%int count2 = -1;%><%int count = 0;%>	
														<logic:iterate id="ruleIncidentList1" name="ruleIncidentBean" >
															<bean:define id="ruleIncidentList2" name="ruleIncidentList1" type="com.spacetimeinsight.rules.bean.RuleIncidentConditionBean" />													
	                                              <%  
	com.spacetimeinsight.rules.bean.RuleIncidentConditionBean ruleIncidentConditionBean = (com.spacetimeinsight.rules.bean.RuleIncidentConditionBean) ruleIncidentList2;
	                                                int incidentType = ruleIncidentConditionBean.getIncidentType();
	                                                String incidentName = ruleIncidentConditionBean.getIncidentConditionName();
	                                                String conditionQuery = ruleIncidentConditionBean.getIncidentCondition();
													String listenerId = ruleIncidentConditionBean.getListenerId() + "";
													String rasProcessId = ruleIncidentConditionBean.getRasProcessId() + "";
													java.util.HashMap configParamsMap = ruleIncidentConditionBean.getConfigParams();
													java.util.Iterator itr = null;
													if(configParamsMap != null)
														itr = configParamsMap.keySet().iterator();     
													int conditionId =  ruleIncidentConditionBean.getConditionId();                                                              
													if(incidentType == 1)
													{count2 = count2+1;alertExists = true;
	                                            %>

	                                                <table width="100%">
	                                                <tr>
	                                                    <td width="100%" colspan="5">
	                                                   <table id="incidentTable" width="100%" border=0>
	                                                    <tr>
	                                                    <td width="100%">
	                                                        <fieldset style="width:100%">                                               
	                                                            <table width="100%" border="0">
	                                                                <tr>
	                                                                    <td>&nbsp;</td>
	                                                                </tr>
	                                                                <tr>
	                                                                    <td colspan="3" width="100%">
	                                                                    <table border="0">
	                                                                    <tr>
	                                                                          <input type="hidden" name="alertConditionId<%=count2%>" value="<%=conditionId%>" />
	                                                                            <td  width="250px" colspan="2" align="left" style="padding-left:20px;" nowrap="nowrap">
	                                                                            <strong>Name:</strong>&nbsp;&nbsp;<input type="text"    id="alertName<%=count2%>" name="alertName<%=count%>" disabled="disabled" class="medium" maxLength="45"
	                                                                                        dojoType="dijit.form.ValidationTextBox" required="true" trim="true" ucfirst="true" value="<%=incidentName%>"  /></td>
	                                                                            <td width="30px">&nbsp;</td>                                                                                        
	                                                                            <td  width="100px" align="right"><strong><bean:message key="rules.alertQry" bundle="rules" />:</strong>
	                                                                            </td>
	                                                                            <td width="50%" align="right">&nbsp;
	                                                                            <textarea  id="alertQry<%=count2%>" style="width:40em;height:3em" disabled="disabled"
	                                                                            name="alertQry<%=count2%>"  required="true" trim="true"   ucfirst="true"  ><%=conditionQuery%> </textarea>
	                                                                            </td>
	                                                                    </tr>
	                                                                    </table>
	                                                                    </td>
	                                                                    <td width="13px">&nbsp;</td>
	                                                                </tr> 

	                                                                <%if(itr != null){%>
	                                                                <tr id="alertParamMapTitle" style="display:block; ">
	                                                                    <td class="redtitle1" colspan="2" style="padding-left:71px;">ParamsMap</td>
	                                                                </tr>
	                                                                <%}%>
	                                                                <tr id="alertParamMapDtls" style="display:block" >
	                                                                    <td colspan="6" align="left" style="padding-left:70px" >
	                                                                        <table>  <tbody id="alertParamDtls0">
																	<%int count1 = 0;
																	if(itr != null)
																	while (itr.hasNext()) {
																	String key = (String) itr.next();
																	java.util.HashMap	hashConfigMap = new java.util.HashMap();
																	java.util.Vector vecParamMap = (java.util.Vector) configParamsMap.get(key);
																	if (key != null ) {
																	int  configType = ((Integer) vecParamMap.get(1)).intValue();
																	String  configValue = (String) vecParamMap.get(0);
																	Long  configId = null;
																	if(vecParamMap.get(2) != null)
																		configId = (Long) vecParamMap.get(2); 
																	%>  
	    
	                                                                            <tr> 
	                                                                                    <td><strong>Name <label class="error">*</label>:</strong></td>
	                                                                                    <input type="hidden" name="alertConfigId<%=count2%>_<%=count1%>" value="<%=configId%>" />
	                                                                                    <td>&nbsp;<input type="text"  disabled="disabled"   id="alertParamName<%=count2%>_<%=count1%>" name="alertParamName<%=count2%>_<%=count1%>" class="medium" maxLength="45"
	                                                        dojoType="dijit.form.ValidationTextBox" required="true" trim="true" ucfirst="true" value="<%=key%>"  />
	                                                                                    </td>
	                                                                                    <td width="13px"></td>
	                                                                                    <td><strong>Value <label class="error">*</label>:</strong></td>
	                                                                                    <td>&nbsp;<input type="text"    id="alertParamValue<%=count2%>_<%=count1%>" disabled="disabled" name="alertParamValue<%=count2%>_<%=count1%>" class="medium" maxLength="45"
	                                                        dojoType="dijit.form.ValidationTextBox" required="true" trim="true" ucfirst="true" value="<%=configValue%>"  />
	                                                                                    </td>
	                                                                                    <td><strong>Type <label class="error">*</label>:</strong> <select id="alertParamType<%=count2%>_<%=count1%>" dojoType="dijit.form.FilteringSelect" disabled="disabled"  name="alertParamType<%=count2%>_<%=count1%>"    autoComplete="false" style="width:10.5em;"  searchAttr="label" readonly="readonly" store="attrStore" invalidMessage="Invalid Listener" style="width:100px" value=<%=configType%>>
	                                                                                    </select>                                                                                    </td>
	                                                                                    <td valign="top"> &nbsp;&nbsp;<!-- <img src="<%=ServerUtils.getContextName(request)%>/images/icon_plus_1.png" width="9px" height="9px" style="cursor : pointer" onclick="addAlertParamDtls(0)"/ >         -->                                                                        </td>
	                                                                            </tr>
																	<%count1++;}%> <script type="">alertCount ='<%=count2%>' ;</script>   <%} %>
																	<input type="hidden" name="alertConfigTotal<%=count2%>_<%=count1%>" value="<%=count1%>" />
	                                                                                </tbody>
	                                                                        </table>
	                                                                    </td>
	                                                                </tr>
	                                                   
																	<tr><td coslpan="4"><br></td></tr>
	<tr>
	                                                                    <td width="" align="left" style="" coslpan="2">&nbsp;
	                                                                    <strong><bean:message key="rules.listener" bundle="rules" />:</strong>&nbsp;
	                                                                    <select id="alertListener<%=count2%>"  dojoType="dijit.form.FilteringSelect"  name="alertListener<%=count2%>"  disabled="disabled"   autoComplete="false"     invalidMessage="Invalid Listener" value="<%=listenerId%>" >
	                                                                                <option value=""></option>                                                                  
	                                                                                <logic:present name="allListenersList" scope="session">                                                                                
	                                                                                <logic:notEqual name="allListenersList"  value="null">
	                                                                                    <logic:iterate id="allListenersList1" name="allListenersList" >
	                                                                            <option title='<bean:write name="allListenersList1" property="listenerName" />' value='<bean:write name="allListenersList1" property="listenerId" />'><bean:write name="allListenersList1" property="listenerName" />
	                                                                            </option> 
	                                                                                    </logic:iterate>
	                                                                                </logic:notEqual>   
	                                                                                </logic:present>
	                                                                                <logic:equal name="allListenersList"  value="null">                                                                                
	                                                                    <option value=""><bean:message key="rules.listeners.nodatafound" bundle="rules" /></option>
	                                                                                </logic:equal>  
	                                                                                 
	                                                                    </select>
	                                                                 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                                                                 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                                                                    <strong><bean:message key="rules.rasProcess" bundle="rules" />:</strong>&nbsp;
	                                                                    <select id="alertRASProcess<%=count2%>" dojoType="dijit.form.FilteringSelect"  name="alertRASProcess<%=count2%>"  disabled="disabled"  autoComplete="true"     invalidMessage="Invalid Listener" value="<%=rasProcessId%>" >
	                                                                                <option value=""></option>                                                                  
	                                                                                <logic:present name="allRASProcessesList" scope="session">                                                                                
	                                                                                <logic:notEqual name="allRASProcessesList"  value="null">
	                                                                                    <logic:iterate id="allRASProcessesList1" name="allRASProcessesList" >
	                                                                            <option title='<bean:write name="allRASProcessesList1" property="rasProcessName" />' value='<bean:write name="allRASProcessesList1" property="rasProcessId" />'><bean:write name="allRASProcessesList1" property="rasProcessName" />
	                                                                            </option> 
	                                                                                    </logic:iterate>
	                                                                                </logic:notEqual>   
	                                                                                </logic:present>
	                                                                                <logic:equal name="allRASProcessesList1"  value="null">                                                                                
	                                                                    <option value=""><bean:message key="rules.listeners.nodatafound" bundle="rules" /></option>
	                                                                                </logic:equal>  
	                                                                    </select>
	                                                                    </td>
																	</tr>
	                                                            </table>
	                                                        </fieldset>
	                                                    </td>

	                                                    <td valign="top">&nbsp;&nbsp; 
	                                                    <!--    <img src="<%=ServerUtils.getContextName(request)%>/images/icon_plus_1.png" width="9px" height="9px" style="cursor : pointer" onclick="addAlertDtls()"/ >    -->
	                                                    </td>
	                                                    </tr>
	                                                                                                     
	                                                 </table> 

	                                                    </td>
	                                                </tr>
													 <tbody id="alertDtls">
	                                                </tbody>
													
	                                        </td>
	                                    </tr>
								<%}%>
								
								</logic:iterate>
								</table> 
	                           </fieldset>
						</logic:notEqual>  
				</logic:present>    
				  
								<%if(!alertExists){%>
	                                                <table width="100%">
	                                                <tr>
	                                                    <td width="100%" colspan="5">
	                                                   <table id="incidentTable" width="100%" border=0>
	                                                    <tr>
	                                                    <td width="100%">
	                                                        <fieldset style="width:100%">                                               
	                                                            <table width="100%" border="0">
	                                                                <tr>
	                                                                    <td>&nbsp;</td>
	                                                                </tr>
	                                                                <tr>
	                                                                    <td colspan="3" width="100%">
	                                                                    <table border="0">
	                                                                    <tr>
	                                                                          <input type="hidden" name="alertConditionId0" value="" />
	                                                                            <td  width="250px" colspan="2" align="left" style="padding-left:20px;" nowrap="nowrap">
	                                                                            <strong>Name:</strong>&nbsp;&nbsp;<input type="text"    id="alertName0" name="alertName0" disabled="disabled" class="medium" maxLength="45"
	                                                                                        dojoType="dijit.form.ValidationTextBox" required="true" trim="true" ucfirst="true" value=""  /></td>
	                                                                            <td width="30px">&nbsp;</td>                                                                                        
	                                                                            <td  width="100px" align="right"><strong><bean:message key="rules.alertQry" bundle="rules" />:</strong>
	                                                                            </td>
	                                                                            <td width="50%" align="right">&nbsp;
	                                                                            <textarea  id="alertQry0" style="width:40em;height:3em" disabled="disabled"
	                                                                            name="alertQry0"  required="true" trim="true"   ucfirst="true"  ></textarea>
	                                                                            </td>
	                                                                    </tr>
	                                                                    </table>
	                                                                    </td>
	                                                                    <td width="13px">&nbsp;</td>
	                                                                </tr> 

	                                                
	                                                                <tr id="alertParamMapTitle" style="display:none; ">
	                                                                    <td class="redtitle1" colspan="2" style="padding-left:71px;"><bean:message key="rules.parammap.label" bundle="rules" /></td>
	                                                                </tr>
	                                                  
	                                                                <tr id="alertParamMapDtls" style="display:block" >
	                                                                    <td colspan="6" align="left" style="padding-left:70px" >
	                                                                        <table>  <tbody id="alertParamDtls0">
	                                                                                </tbody>
	                                                                        </table>
	                                                                    </td>
	                                                                </tr>
	                                                   
																	<tr><td coslpan="4"><br></td></tr>
	<tr>
	                                                                    <td width="" align="left" style="" coslpan="2">&nbsp;
	                                                                    <strong><bean:message key="rules.listener" bundle="rules" />:</strong>&nbsp;
	                                                                    <select id="alertListener0"  dojoType="dijit.form.FilteringSelect"  name="alertListener0"  disabled="disabled"   autoComplete="false"     invalidMessage="Invalid Listener" value="" >
	                                                                                <option value=""></option>                                                                  
	                                                                                <logic:present name="allListenersList" scope="session">                                                                                
	                                                                                <logic:notEqual name="allListenersList"  value="null">
	                                                                                    <logic:iterate id="allListenersList1" name="allListenersList" >
	                                                                            <option title='<bean:write name="allListenersList1" property="listenerName" />' value='<bean:write name="allListenersList1" property="listenerId" />'><bean:write name="allListenersList1" property="listenerName" />
	                                                                            </option> 
	                                                                                    </logic:iterate>
	                                                                                </logic:notEqual>   
	                                                                                </logic:present>
	                                                                                <logic:equal name="allListenersList"  value="null">                                                                                
	                                                                    <option value=""><bean:message key="rules.listeners.nodatafound" bundle="rules" /></option>
	                                                                                </logic:equal>  
	                                                                                 
	                                                                    </select>
	                                                                 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                                                                 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                                                                    <strong><bean:message key="rules.rasProcess" bundle="rules" />:</strong>&nbsp;
	                                                                    <select id="alertRASProcess0" dojoType="dijit.form.FilteringSelect"  name="alertRASProcess0"  disabled="disabled"  autoComplete="true"     invalidMessage="Invalid Listener" value="" >
	                                                                                <option value=""></option>                                                                  
	                                                                                <logic:present name="allRASProcessesList" scope="session">                                                                                
	                                                                                <logic:notEqual name="allRASProcessesList"  value="null">
	                                                                                    <logic:iterate id="allRASProcessesList1" name="allRASProcessesList" >
	                                                                            <option title='<bean:write name="allRASProcessesList1" property="rasProcessName" />' value='<bean:write name="allRASProcessesList1" property="rasProcessId" />'><bean:write name="allRASProcessesList1" property="rasProcessName" />
	                                                                            </option> 
	                                                                                    </logic:iterate>
	                                                                                </logic:notEqual>   
	                                                                                </logic:present>
	                                                                                <logic:equal name="allRASProcessesList1"  value="null">                                                                                
	                                                                    <option value=""><bean:message key="rules.listeners.nodatafound" bundle="rules" /></option>
	                                                                                </logic:equal>  
	                                                                    </select>
	                                                                    </td>
																	</tr>
	                                                            </table>
	                                                        </fieldset>
	                                                    </td>

	                                                    <td valign="top">&nbsp;&nbsp; 
	                                                    <!--    <img src="<%=ServerUtils.getContextName(request)%>/images/icon_plus_1.png" width="9px" height="9px" style="cursor : pointer" onclick="addAlertDtls()"/ >    -->
	                                                    </td>
	                                                    </tr>
	                                                                                                     
	                                                 </table> 

	                                                    </td>
	                                                </tr>
	                                             
													 <tbody id="alertDtls">

	                                                </tbody>
								<%}%>
												 
	                                        </td>
	                                    </tr>							


	<!--                    *********************************************************************************************************************************************  -->
	<!--                    *******************************************DYNAMIC VIOLATION DETAILS****************************************************************  -->
	<!--                    *********************************************************************************************************************************************  -->
	                                    <tr>
	                                        <td class="redtitle1" colspan="5" height="30px">&nbsp;</td>
	                                    </tr>                            
	                                    <tr>
	                                        <td class="redtitle1" colspan="5"> </td>
	                                    </tr>   
	                                    <tr>
	                                        <td colspan="5">
	                                            <fieldset>
	                                            <legend class="redtitle1"><bean:message key="rules.violationCaption" bundle="rules" /></legend>
													<logic:present name="ruleIncidentBean">
													<logic:notEqual name="ruleIncidentBean"  value="null">
												  <%int count2 = -1;%><%int count = 0;%>	
														<logic:iterate id="ruleIncidentList1" name="ruleIncidentBean" >
															<bean:define id="ruleIncidentList2" name="ruleIncidentList1" type="com.spacetimeinsight.rules.bean.RuleIncidentConditionBean" />													
												<% com.spacetimeinsight.rules.bean.RuleIncidentConditionBean ruleIncidentConditionBean = (com.spacetimeinsight.rules.bean.RuleIncidentConditionBean) ruleIncidentList2;
	                                                int incidentType = ruleIncidentConditionBean.getIncidentType();
	                                                String incidentName = ruleIncidentConditionBean.getIncidentConditionName();
	                                                String conditionQuery = ruleIncidentConditionBean.getIncidentCondition();
													String listenerId = ruleIncidentConditionBean.getListenerId() + "";
													String rasProcessId = ruleIncidentConditionBean.getRasProcessId() + "";
													java.util.HashMap configParamsMap = ruleIncidentConditionBean.getConfigParams();
													java.util.Iterator itr = null;
													if(configParamsMap != null)
														itr = configParamsMap.keySet().iterator();     
													int conditionId =  ruleIncidentConditionBean.getConditionId();
													
													if(incidentType == 2)
													{ count2 = count2+1;violationExists = true;
	                                            %>
	                                            <table width="100%">
	                                                <tr>
	                                                    <td width="100%" colspan="5">
	                                                    <table id="violationTable" width="100%" border=0>
	                                                    <tr>
	                                                    <td>
	                                                        <fieldset>                                              
	                                                            <table width="100%" border="0">
	                                                                <tr>
	                                                                    <td>&nbsp;</td>
	                                                                </tr>
	                                                                <tr>
	                                                                    <td colspan="3" width="100%">
	                                            

	                                                                    <table>
	                                                                        <tr>
	                                                                            <td  width="250px" colspan="2" align="left" style="padding-left:20px;" nowrap="nowrap">
	                                                                            <input type="hidden" name="violationConditionId<%=count2%>" value="<%=conditionId%>" />
	                                                                            <strong><bean:message key="rules.label.name" bundle="rules" />:</strong>&nbsp;&nbsp;<input type="text"    id="violationName<%=count2%>" name="violationName<%=count2%>" class="medium"   
	                                                                            disabled="disabled" maxLength="45" dojoType="dijit.form.ValidationTextBox" required="true" trim="true" ucfirst="true" readonly="readonly"  value="<%=incidentName%>" /></td>
	                                                                            <td width="30px">&nbsp;</td>                                                                                        
	                                                                            <td  width="40px">
	                                                                            <strong><bean:message key="rules.violationQry" bundle="rules" />:</strong>
	                                                                            </td>
	                                                                            <td width="50%">&nbsp;
	                                                                            <textarea  id="violationQry0" style="width:40em;height:3em" name="violationQry<%=count2%>" disabled="disabled" required="true" trim="true"  ucfirst="true" readonly="readonly" ><%=conditionQuery%></textarea>
	                                                                            </td>
	                                                                        </tr>
	                                                                    </table>
	                                                                    </td>
	                                                                                                
	                                                                </tr>
	                                                                <%if(itr != null){%>
	                                                                <tr id="violationParamMaptitle" style="display:block">
	                                                                    <td class="redtitle1" colspan="2" style="padding-left:71px;" align="left">ParamsMap</td>
	                                                                </tr>
	                                                                <%}%>
	                                                                <tr id="violationParamMapDtls" style="display:block">
	                                                                    <td colspan="6" align="left" style="padding-left:70px">
	                                                                    <table> <tbody id="violationParamDtls0">
	                                                                <%int count1 = 0;
																	if(itr != null)
																	while (itr.hasNext()){
																	String key = (String) itr.next();
																	java.util.HashMap	hashConfigMap = new java.util.HashMap();
																	java.util.Vector vecParamMap = (java.util.Vector) configParamsMap.get(key);
																	if (key != null ){
																	int  configType = ((Integer) vecParamMap.get(1)).intValue();
																	String  configValue = (String) vecParamMap.get(0);
																	Long  configId = null;
																	if(vecParamMap.get(2) != null)
																		configId = (Long) vecParamMap.get(2);    
																	%>
	                                                                            <tr>
	                                                                                    <td><strong><bean:message key="rules.label.name" bundle="rules" /> <label class="error">*</label>:</strong></td>
	                                                                                    <input type="hidden" name="violationConfigId<%=count2%>_<%=count1%>" value="<%=configId%>" />                                                                                    <td>&nbsp;<input type="text"    id="violationParamName<%=count2%>_<%=count1%>" disabled="disabled" name="violationParamName<%=count2%>_<%=count1%>" class="medium" maxLength="45" dojoType="dijit.form.ValidationTextBox" required="true" trim="true" ucfirst="true" readonly="readonly" value="<%=key%>" />
	                                                                                    </td>
	                                                                                    <td width="13px"></td>
	                                                                                    <td><strong><bean:message key="rules.label.value" bundle="rules" /> <label class="error">*</label>:</strong></td>
	                                                                                    <td>&nbsp;<input type="text"    id="violationParamValue<%=count2%>_<%=count1%>" disabled="disabled" name="violationParamValue<%=count2%>_<%=count1%>" class="medium" maxLength="45" dojoType="dijit.form.ValidationTextBox" required="true" trim="true" ucfirst="true"  value="<%=configValue%>" />
	                                                                                    </td>
	                                                                                    <td><strong><bean:message key="rules.label.type" bundle="rules" /> <label class="error">*</label>:</strong> <select id="violationParamType<%=count2%>_<%=count1%>" disabled="disabled" searchAttr="label" readonly="readonly" store="attrStore" dojoType="dijit.form.FilteringSelect"  name="violationParamType<%=count2%>_<%=count1%>"    autoComplete="false"     invalidMessage="Invalid Listener" style="width:10.5em;"  value="<%=configType%>">
	                                                                                    </select>
	                                                                                    </td>
	                                                                                    <td> &nbsp;&nbsp;<!--  <img src="<%=ServerUtils.getContextName(request)%>/images/icon_plus_1.png" width="9px" height="9px" style="cursor : pointer" onclick="addViolationParamDtls(0)"/ > -->
	                                                                                    </td> 
	                                                                                    
	                                                                            </tr> 
																	<%count1++;}%>  <script type="">violationCount = '<%=count2%>';</script> <%} %> 
																	<input type="hidden" name="violationConfigTotal<%=count2%>_<%=count1%>" value="<%=count1%>" />
	                                                                               
	                                                                                </tbody> 
	                                                                        </table> 
	                                                                    </td>
	                                                                </tr>
																	<tr><td coslpan="4"><br></td></tr>      
	                                                                <tr><td>&nbsp;</td>  
	                                                                </tr> 
	                                                                <tr>
	                                                                    <td width="10%" valign="middle">&nbsp;&nbsp;&nbsp;
	                                                                  <!--  <table border="0"><tr><td style="padding-left:8px;" align="left" colspan="2">   -->
	                                                                        <strong>&nbsp;<bean:message key="rules.listener" bundle="rules" />:</strong>&nbsp;
	                                                                        <select id="violationListener<%=count2%>" dojoType="dijit.form.FilteringSelect" disabled="disabled"  name="violationListener<%=count2%>"    autoComplete="false"     invalidMessage="Invalid Listener" value="<%=listenerId%>" >
	                                                                                <option value=""></option>
	                                                                                <logic:present name="allListenersList" scope="session">                                                                                
	                                                                                <logic:notEqual name="allListenersList"  value="null">
	                                                                                    <logic:iterate id="allListenersList1" name="allListenersList" >
	                                                                            <option title='<bean:write name="allListenersList1" property="listenerName" />' value='<bean:write name="allListenersList1" property="listenerId" />'><bean:write name="allListenersList1" property="listenerName" />
	                                                                            </option> 
	                                                                                    </logic:iterate>
	                                                                                </logic:notEqual>   
	                                                                                </logic:present>
	                                                                                <logic:equal name="allListenersList"  value="null">                                                                                
	                                                                    <option value=""><bean:message key="rules.listeners.nodatafound" bundle="rules" /></option>
	                                                                                </logic:equal>  
	                                                            <!--    </tr>
	                                                                    </table>  -->
	                                                                    </td>
	                                                                    <td width="" align="left" style="padding-left:150px;" valign="middle" >
	                                                                    <strong><bean:message key="rules.rasProcess" bundle="rules" />:</strong>&nbsp;
	                                                                    <select id="violationRASProcess<%=count2%>" dojoType="dijit.form.FilteringSelect"  name="violationRASProcess<%=count2%>"  disabled="disabled"  autoComplete="true"     invalidMessage="Invalid Listener" value="<%=rasProcessId%>">
	                                                                                <option value=""></option>                                                                  
	                                                                                <logic:present name="allRASProcessesList" scope="session">                                                                                
	                                                                                <logic:notEqual name="allRASProcessesList"  value="null">
	                                                                                    <logic:iterate id="allRASProcessesList1" name="allRASProcessesList" >
	                                                                            <option title='<bean:write name="allRASProcessesList1" property="rasProcessName" />' value='<bean:write name="allRASProcessesList1" property="rasProcessId" />'><bean:write name="allRASProcessesList1" property="rasProcessName" />
	                                                                            </option> 
	                                                                                    </logic:iterate>
	                                                                                </logic:notEqual>   
	                                                                                </logic:present>
	                                                                                <logic:equal name="allRASProcessesList1"  value="null">                                                                                
	                                                                    <option value=""><bean:message key="rules.listeners.nodatafound" bundle="rules" /></option>
	                                                                                </logic:equal>  
	                                                                    </select>
	                                                                    </td>
	                                                                    
	                                                                </tr>
	                                                            </table> 
	                                                        </fieldset>
	                                                    </td>
	                                                    <td valign="top">&nbsp;&nbsp;
	                                                     <!--     <img src="<%=ServerUtils.getContextName(request)%>/images/icon_plus_1.png" width="9px" height="9px" style="cursor : pointer" onclick="addVioliationDtls()"/ >   -->
	                                                    </td>
	                                                    </tr>
	                                                    </table>
	                                                    </td>
	                                                </tr>
	                                                
	                                                <tbody id="violationDtls">
	                                                </tbody>
												</fieldset>                                                
	                                        <!--    </table>   -->
	                                        </td>
	                                    </tr>
	                                    		<%}%>
														</logic:iterate>
													</logic:notEqual>  
												</logic:present>    
											<%if(!violationExists){%>

	                                            <table width="100%">
	                                                <tr>
	                                                    <td width="100%" colspan="5">
	                                                    <table id="violationTable" width="100%" border=0>
	                                                    <tr>
	                                                    <td>
	                                                        <fieldset>                                              
	                                                            <table width="100%" border="0">
	                                                                <tr>
	                                                                    <td>&nbsp;</td>
	                                                                </tr>
	                                                                <tr>
	                                                                    <td colspan="3" width="100%">
	                                            

	                                                                    <table>
	                                                                        <tr>
	                                                                            <td  width="250px" colspan="2" align="left" style="padding-left:20px;" nowrap="nowrap">
	                                                                            <input type="hidden" name="violationConditionId0" value="" />
	                                                                            <strong>Name:</strong>&nbsp;&nbsp;<input type="text"    id="violationName0" name="violationName0" class="medium"   
	                                                                            disabled="disabled" maxLength="45" dojoType="dijit.form.ValidationTextBox" required="true" trim="true" ucfirst="true" readonly="readonly"  value="" /></td>
	                                                                            <td width="30px">&nbsp;</td>                                                                                        
	                                                                            <td  width="40px">
	                                                                            <strong><bean:message key="rules.violationQry" bundle="rules" />:</strong>
	                                                                            </td>
	                                                                            <td width="50%">&nbsp;
	                                                                            <textarea  id="violationQry0" style="width:40em;height:3em" name="violationQry0" disabled="disabled" required="true" trim="true"  ucfirst="true" readonly="readonly" ></textarea>
	                                                                            </td>
	                                                                        </tr>
	                                                                    </table>
	                                                                    </td>
	                                                                                                
	                                                                </tr>
	                                                              
	                                                                <tr id="violationParamMaptitle" style="display:none">
	                                                                    <td class="redtitle1" colspan="2" style="padding-left:71px;" align="left"><bean:message key="rules.parammap.label" bundle="rules" /></td>
	                                                                </tr>
	                                                              
	                                                                <tr id="violationParamMapDtls" style="display:block">
	                                                                    <td colspan="6" align="left" style="padding-left:70px">
	                                                                    <table> <tbody id="violationParamDtls0">
	                                                                                </tbody> 
	                                                                        </table> 
	                                                                    </td>
	                                                                </tr>
																	   
	                                                                <tr><td>&nbsp;</td>  
	                                                                </tr> 
	                                                                <tr>
	                                                                    <td width="10%" valign="middle">&nbsp;&nbsp;&nbsp;
	                                                                  <!--  <table border="0"><tr><td style="padding-left:8px;" align="left" colspan="2">   -->
	                                                                        <strong>&nbsp;&nbsp;<bean:message key="rules.listener" bundle="rules" />:</strong>&nbsp;
	                                                                        <select id="violationListener0" dojoType="dijit.form.FilteringSelect" disabled="disabled"  name="violationListener0"    autoComplete="false"     invalidMessage="Invalid Listener" value="" >
	                                                                                <option value=""></option>
	                                                                                <logic:present name="allListenersList" scope="session">                                                                                
	                                                                                <logic:notEqual name="allListenersList"  value="null">
	                                                                                    <logic:iterate id="allListenersList1" name="allListenersList" >
	                                                                            <option title='<bean:write name="allListenersList1" property="listenerName" />' value='<bean:write name="allListenersList1" property="listenerId" />'><bean:write name="allListenersList1" property="listenerName" />
	                                                                            </option> 
	                                                                                    </logic:iterate>
	                                                                                </logic:notEqual>   
	                                                                                </logic:present>
	                                                                                <logic:equal name="allListenersList"  value="null">                                                                                
	                                                                    <option value=""><bean:message key="rules.listeners.nodatafound" bundle="rules" /></option>
	                                                                                </logic:equal>  
	                                                            <!--    </tr>
	                                                                    </table>  -->
	                                                                    </td>
	                                                                    <td width="" align="left" style="padding-left:138px;" valign="middle" >
	                                                                    <strong><bean:message key="rules.rasProcess" bundle="rules" />:</strong>&nbsp;
	                                                                    <select id="violationRASProcess0" dojoType="dijit.form.FilteringSelect"  name="violationRASProcess0"  disabled="disabled"  autoComplete="true"     invalidMessage="Invalid Listener" value="">
	                                                                                <option value=""></option>                                                                  
	                                                                                <logic:present name="allRASProcessesList" scope="session">                                                                                
	                                                                                <logic:notEqual name="allRASProcessesList"  value="null">
	                                                                                    <logic:iterate id="allRASProcessesList1" name="allRASProcessesList" >
	                                                                            <option title='<bean:write name="allRASProcessesList1" property="rasProcessName" />' value='<bean:write name="allRASProcessesList1" property="rasProcessId" />'><bean:write name="allRASProcessesList1" property="rasProcessName" />
	                                                                            </option> 
	                                                                                    </logic:iterate>
	                                                                                </logic:notEqual>   
	                                                                                </logic:present>
	                                                                                <logic:equal name="allRASProcessesList1"  value="null">                                                                                
	                                                                    <option value=""><bean:message key="rules.listeners.nodatafound" bundle="rules" /></option>
	                                                                                </logic:equal>  
	                                                                    </select>
	                                                                    </td>
	                                                                    
	                                                                </tr>
	                                                            </table> 
	                                                        </fieldset>
	                                                    </td>
	                                                    <td valign="top">&nbsp;&nbsp;
	                                                     <!--     <img src="<%=ServerUtils.getContextName(request)%>/images/icon_plus_1.png" width="9px" height="9px" style="cursor : pointer" onclick="addVioliationDtls()"/ >   -->
	                                                    </td>
	                                                    </tr>
	                                                    </table>
	                                                    </td>
	                                                </tr> 
	                                                <tbody id="violationDtls">
	                                                </tbody>
												</fieldset>                                                
	                                        <!--    </table>   -->
	                                        </td>
	                                    </tr>
	  
											<%}%>
		                                    
	                </td>
	                </tr>
	            </table>
	        </td>
	    </tr>
	</table>
	        </td>
	    </tr>
	</table>
	<tr><td>
	<table width="100%">

	                <tr>
	                    <td  class="barColor" align="right" width="1150px" colspan="5">
	                        <button dojoType="dijit.form.Button" id = "idHome"  type="button" value = "<bean:message key="rules.home" bundle="rules"/>"  onclick="window.location =  '<%=ServerUtils.getContextName(request)%>/RulesHome.do?operation=loadRulesHome ' ">
	                            <bean:message key="datasource.home" bundle="ds" />
	                        </button>
	                        <button dojoType="dijit.form.Button" id = "idBack" value = "<bean:message key="rules.back" bundle="rules"/>" type="button" onClick="window.location='<%=ServerUtils.getContextName(request)%>/RulesHome.do?operation=loadRules'">
	                            <bean:message key="datasource.back" bundle="ds" />
	                         </button>
							 <%if(deleteExists){%>
								 <button  dojoType="dijit.form.Button" id = "idDelete" type="button" name="btnDelete" value="<bean:message key="rules.delete" bundle="rules"/>" onClick="return submitForm(this);">
								 	<bean:message key="rules.delete" bundle="rules"/>
								 </button>
							 <%}%>     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;           
	                        </td>    
	                </tr>
	</table>  
	</td>
	</tr>
	  
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

	<logic:present name="allListenersList">                                                                                
	<logic:iterate id="allListenersList1" name="allListenersList" >
	var listenerName = 	'<bean:write name="allListenersList1" property="listenerName"/>';	
	var listenerId = 	'<bean:write name="allListenersList1" property="listenerId"/>';	
	listenerArrValue.push({value:listenerId , label:listenerName});
	</logic:iterate>
	</logic:present>
	var listenerlist= {identifier:"value",items:listenerArrValue};
	var listenserstore = new dojo.data.ItemFileReadStore({data: listenerlist});
	</script>

	</html:html>
