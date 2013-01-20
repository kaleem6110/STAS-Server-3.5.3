<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ include file="/common/dojo.jsp"%>
<%@page import="com.enterprisehorizons.magma.server.util.ServerUtils"%>
<%
java.util.List ruleIncidentList  = (java.util.List) request.getAttribute("ruleIncidentBean");
boolean alertExists = false;
boolean violationExists = false;
%>

<html:html locale="true">
<head>
<script type="text/javascript" src="<%=ServerUtils.getContextName(request)%>/js/rules.js" ></script>
<script>
var txtAlertViolationStr ="";
function validateUniqueInAlertVioliation(textValue,textBoxObj) { 
    var listenserstore = null;  
    var rasProcessStore = null;

    prepareStr();
    var uniqueCount =0;
    var array   = txtAlertViolationStr.split(',');

    for(var k=0;k<array.length-1;k++){
        
        if(array[k] == textValue)
         uniqueCount++;
    }
    if(uniqueCount >1){
        textBoxObj.focus();
        showEmptyDialog(textValue + " should not be taken more than once", "Alert");
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


var listenerArrValue = [];
function delayFormValidation(){
    showProgressDialog('In Progress...','');
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
                    if(alertObj != null && alertObj.value.length == 0 && qryObj != null && qryObj.value.length !=0 )
                    {
                        showEmptyDialog("<bean:message key="rules.check.alert.violation.mandatory" bundle="rules" />", "");   
                        return false;
                    }
                }
              if(objName.indexOf('violationQry') != -1){
                  var suffixVal = objName.substring(12) ;
                  var violationObj =document.getElementById("violationName"+suffixVal);     
                  var qryObj = document.getElementById(objName);
                    if(violationObj != null && violationObj.value.length == 0 && qryObj != null && qryObj.value.length !=0 )
                    {
                        showEmptyDialog("<bean:message key="rules.check.alert.violation.mandatory" bundle="rules" />", "");   
                        return false;
                    }
                }
                
                
         }  
    }


    if(validateTextBoxes())
    {   
        var alerVoilationCount = 0;
        if(dijit.byId("rulesForm").validate()){
            for(var i=0;i<allAlertViolationTextBox.length;i++){
                    var textBoxObj=  document.getElementById(allAlertViolationTextBox[i].name);
                    if(textBoxObj != null && textBoxObj.value != ""){
                        alerVoilationCount++;
                            if(!validateUniqueInAlertVioliation(textBoxObj.value,textBoxObj)){
                                return false;
                            }
                    }
            }

            if(allAlertViolationTextBox.length != 0 && alerVoilationCount == 0 ){
                showEmptyDialog("<bean:message key="rules.check.incident.count" bundle="rules" />", "");   
                return false;
            }
        }
    
    
        if(!validateNameFields())   
        {
            //showEmptyDialog("Name is mandatory, Please assign name", "Alert");    
            return false;
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
                                {//debugger;
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
            
        document.getElementById('violationListenerCount').value = violationCount;
        document.getElementById('violationQryCount').value = violationCount;
        document.getElementById('alertListenerCount').value = alertCount;
        document.getElementById('alertQryCount').value = alertCount;
        document.getElementById('operation').value = 'updateIncidentRules';
        document.rulesForm.submit();
    }
    else
    {
        return false;   
    }
} 

function setIncidentId(id)
{
    document.getElementById('incidentId').value = id; 
}


function validateTextBoxes()
{
    
        for(i=0;i<allTextBox.length;i++){
            if(document.getElementById(allTextBox[i].value) != null)    
            if(document.getElementById(allTextBox[i].value).innerHTML == '<b class="error"><bean:message key="rules.label.notavailable" bundle="rules" /></b>' || document.getElementById(allTextBox[i].value).innerHTML == '<B class=error><bean:message key="rules.label.notavailable" bundle="rules" /></B>'){
                
                showEmptyDialog("<bean:message key="rules.check.name.available" bundle="rules" />", "<bean:message key="rules.alert.title" bundle="rules" />");  
                return false;
            }
        }

        return true;
}


function validateNameFields()
            {
                var elements = document.forms[0].elements;
                newValues="";
                for (var i = 0; i<elements.length; i++)
                {
                            if(elements[i].type == "text")
                            {
                                        newValues =elements[i].value;
                                        if(newValues.length != 0)
                                        {
                                            if(!validateUniqueInAlertVioliation(elements[i].value,elements[i])){
                                                return false;
                                            }
                                        }
                            }
                }

                for(var i=0;i<allAlertViolationTextBox.length;i++){
                    var tempValueStr = document.getElementById(allAlertViolationTextBox[i].name) ;
                    if(tempValueStr != null && tempValueStr.value != ''){
                        var tempValue = tempValueStr.name;              
                        var seqAlert= tempValue.split('alertName');
                        var qryname ='alertQry'+seqAlert[1];
                            if(seqAlert.length-1 == 1){
                                var qryAlertObj = document.getElementById(qryname);
                                    if(qryAlertObj.value.length == 0){
                                        qryAlertObj.focus();
                                        showEmptyDialog( " <bean:message key="rules.check.query.alert" bundle="rules" /> "+tempValueStr.value , "<bean:message key="rules.alert.title" bundle="rules" />");
                                        return false;
                                    }
                            }   
                        var seqViolation= tempValue.split('violationName');
                        var qryViolationname ='violationQry'+seqViolation[1];
                        if(seqViolation.length-1 == 1){
                            var qryViolationObj = document.getElementById(qryViolationname);
                                if(qryViolationObj.value.length == 0){
                                    qryViolationObj.focus();
                                    showEmptyDialog( " <bean:message key="rules.check.query.violation" bundle="rules" /> "+tempValueStr.value  , "<bean:message key="rules.alert.title" bundle="rules" />");
                                    return false;
                                }
                        }
                    }
                }
                 return true;                       
            }

<logic:present name="allListenersList">                                                                                
<logic:iterate id="allListenersList1" name="allListenersList" >
var listenerName =  '<bean:write name="allListenersList1" property="listenerName"/>';   
var listenerId =    '<bean:write name="allListenersList1" property="listenerId"/>'; 
listenerArrValue.push({value:listenerId , label:listenerName});
</logic:iterate>
</logic:present>
var listenerlist= {identifier:"value",items:listenerArrValue};
listenserstore = new dojo.data.ItemFileReadStore({data: listenerlist});


var rasProcessListValue= [];

rasProcessListValue.push({value:"" , label:""});
<logic:present name="allRASProcessesList" scope="session">                                                                                
<logic:notEqual name="allRASProcessesList"  value="null">
    <logic:iterate id="allRASProcessesList1" name="allRASProcessesList" >
    var rasProcessName =    '<bean:write name="allRASProcessesList1" property="rasProcessName" />'; 
    var rasProcessId =  '<bean:write name="allRASProcessesList1" property="rasProcessId" />';   
    rasProcessListValue.push({value:rasProcessId , label:rasProcessName});
    </logic:iterate>
</logic:notEqual>   
</logic:present>
var rasProcessList= {identifier:"value",items:rasProcessListValue};
rasProcessStore = new dojo.data.ItemFileReadStore({data: rasProcessList});





</script>
</head>
<body class="tundra bodybg">
<form name="rulesForm"  id="rulesForm" method="post" dojoType='dijit.form.Form'  action="<%=ServerUtils.getContextName(request)%>/ConfigureRules.do"> 
<!----  HIden Variables -->
<input type="hidden" name="violationListenerCount"  id="violationListenerCount"/>
<input type="hidden" name="violationQryCount" id="violationQryCount"/>
<input type="hidden" name="alertListenerCount" id="alertListenerCount"/>
<input type="hidden" name="alertQryCount" id="alertQryCount" />
<input type="hidden" name="operation" id="operation" value="updateIncidentRules"/> 
<input type="hidden" name="incidentId" id="incidentId" value="<bean:write name="rulesForm" property="incidentId"/>"/> 

 <table width="100%" cellspacing="0" cellpadding="0" >
    <tr>
        <td align="left" valign="top">
            <table width="1000" cellpadding="0" cellspacing="0" align="left" id="mainTable">
                <tr>
                  <td  align="left" valign="top" >
                      <table width="1000" cellspacing="0" cellpadding="0"> 
                        <tr>
                          <td>&nbsp;</td>
                           <td  class="pageTitle paddingTitle"><strong><bean:message key="rules.updateIncidentRules" bundle="rules" /> </strong><br />
                            <span class="bodytext"></span></td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                           <td class="paddingTitleDesc">
                                <bean:message key="rules.updateIncidentRules.description" bundle="rules" />               
                            </td>  
                        </tr>

                        <tr>
                          <td>&nbsp;</td>
                          <td colspan="2" class="error">
                                <html:errors bundle="rules"/> 
                              </td>
                        </tr>

                        <tr>
                            <td>&nbsp;</td>
                             <td style="padding-top:30px;padding-left:67px;width:900px">  
                                <table width="90%" cellspacing="0" cellpadding="0" border="0" >
                                    <tr>
                                        <td width="13%"><strong><bean:message key="rules.incidentName" bundle="rules" /><label class="error">*</label>:</strong></td>
                                        <td width="30%">&nbsp;
                                          <input type="text" id="incidentName" name="incidentName" class="medium" maxLength="45" readonly="readonly"
                                    dojoType="dijit.form.ValidationTextBox" required="true" trim="true" ucfirst="true" value="<bean:write name="rulesForm" property="incidentName"/>" />
                                    </td>
                                    <td width="30px"></td>                                    
                                    <td width="10%"><strong><bean:message key="rules.description" bundle="rules" />:</strong></td>
                                    <td>&nbsp;<textarea dojoType="dijit.form.SimpleTextarea" rows="5" cols="30" id="incidentDesc" style="width:28em;height:7em"
            name="incidentDesc"><bean:write name="rulesForm" property="incidentDesc"/></textarea>
                                    </td>
                                    
                                    </tr>
                                    <tr>
                                        <td height="13px"></td>
                                    </tr>
                                </table>  
                            </td>
                         </tr>
                        </table>
                </td>
                </tr>
                </table>    
            </td>
            

                                  
<!--                    *********************************************************************************************************************************************  -->
<!--                    *******************************************DYNAMIC ALERT DETAILS****************************************************************  -->
<!--                    *********************************************************************************************************************************************  -->

			<tr><td valign="top">
                        <table  border="0" cellspacing="0" cellpadding="0" >
                                    <tr>
									    <td style="padding-left: 100px;">&nbsp;</td>
                                        <td colspan=5 valign="top">
                                            <fieldset>
                                            <legend class="redtitle1" ><bean:message key="rules.alertCaption" bundle="rules" /></legend>
                                            <table border="0">
                                            <tr><td align="right" style="padding-right:0px;" width="100%" colspan="3" valign="top">
                                                    <img src="<%=ServerUtils.getContextName(request)%>/images/icon_plus_1.png"  height="9px" style="cursor : pointer" onClick="addAlertDtls()" title="Add alert" />
                                            </td></tr>  
                                            <tr><td>
                                            <tbody id="alertDtls" width="">
                                            </tbody>
                                            <tbody id="incidentTable" >
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
                                            
                                            <tr id="alertRow<%=count2%>">
                                                <script type="">alertCount = alertCount +1;</script>                                            
                                                <td>                                                                   
                                                <fieldset style="width:870px;">
                                                     <table id="incidentTable" width="100%" border="0">
                                                    <tr ><td>
                                                                <table border="0">
                                                                    <tr>
                                                                     <input type="hidden" name="alertConditionId<%=count2%>" value="<%=conditionId%>" />
                                                                        <td  width="3%"   align="right" style="padding-left:0px;" nowrap="nowrap">
                                                                            <strong>&nbsp;Name&nbsp;<label class="error">*</label>:</strong></td>
                                                                        <td align="left"  width="9%">
                                                                                <input type="text"    id="alertName<%=count2%>" name="alertName<%=count2%>" class="medium" maxLength="45" readonly="readonly"  dojoType="dijit.form.ValidationTextBox" required="true" trim="true" ucfirst="true" value='<bean:write name="ruleIncidentList1" property="incidentConditionName"/>'   />
                                                                        </td>
                                                                        <script type="">prepareTextBoxArray(dojo.byId('alertName<%=count2%>')); </script>
                                                                        <td  width="100px" align="right" style="padding-left: 100px;"><strong>&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="rules.violationQry" bundle="rules" />&nbsp;<label class="error">*</label>:</strong>
                                                                        </td>
                                                                        <td width="52px" align="left">
                                                                            <textarea  id="alertQry<%=count2%>" style="width:40em;height:3em" readonly="readonly" onBlur="chkQueryDescriptionLen(this);"
                                                                            name="alertQry<%=count2%>"  required="true" trim="true"   ucfirst="true"  ><bean:write name="ruleIncidentList1" property="incidentCondition"/></textarea>
                                                                            </td>
                                                                        </td>
                                                                    </tr>
                                                                </table>
                                                            </td>
                                                    </tr>
                                                    <%if(itr != null){%> 
                                                    <tr id="alertParamMapTitle" style="display:block; ">
                                                        <td class="redtitle1" colspan="2" style="padding-left:71px;"><bean:message key="rules.parammap.label" bundle="rules" /></td>
                                                    </tr>
                                                    <%}%>                                           

                                                    <tr id="alertParamMapDtls" style="display:block" >
                                                        <td colspan="6" align="left" style="padding-left:70px" >
                                                            <table> 
                                                                <%int count1 = 0;
                                                                if(itr != null)
                                                                while (itr.hasNext()) {
                                                                String key = (String) itr.next();
                                                                java.util.HashMap   hashConfigMap = new java.util.HashMap();
                                                                java.util.Vector vecParamMap = (java.util.Vector) configParamsMap.get(key);
                                                                if (key != null ) {
                                                                int  configType = ((Integer) vecParamMap.get(1)).intValue();
                                                                String  configValue = (String) vecParamMap.get(0);
                                                                Long  configId = null;
                                                                if(vecParamMap.get(2) != null)
                                                                    configId = (Long) vecParamMap.get(2); 
                                                                %>  
                                                                                        <tr>
                                                                                            <td><strong><bean:message key="rules.label.name" bundle="rules" /> <label class="error">*</label>:</strong></td>
                                                                                            <input type="hidden" name="alertConfigId<%=count2%>_<%=count1%>" value="<%=configId%>" />
                                                                                            <td>&nbsp;<input type="text"  readonly="readonly"   id="alertParamName<%=count2%>_<%=count1%>" name="alertParamName<%=count2%>_<%=count1%>" class="medium" maxLength="45"
                                                                dojoType="dijit.form.TextBox" required="true" trim="true" ucfirst="true" value="<%=key%>"  />
                                                                                            </td>
                                                                                            <td width="13px"></td>
                                                                                            <td><strong>&nbsp;&nbsp;&nbsp;<bean:message key="rules.label.value" bundle="rules" />&nbsp;<label class="error">*</label>:</strong></td>
                                                                                            <td>&nbsp;<input type="text"    id="alertParamValue<%=count2%>_<%=count1%>" name="alertParamValue<%=count2%>_<%=count1%>" class="medium" maxLength="45"  ucfirst="true" value="<%=configValue%>"   dojoType="dijit.form.TextBox"  />

                                                                                            </td>
                                                                                            <td><strong>&nbsp;&nbsp;<bean:message key="rules.label.type" bundle="rules" /> <label class="error">*</label>:</strong>
                                                                                            
                                                                                            <select id="alertParamType<%=count2%>_<%=count1%>" dojoType="dijit.form.FilteringSelect" searchAttr="label" readonly="readonly" store="attrStore"  name="alertParamType<%=count2%>_<%=count1%>" style="width:10.5em;"    autoComplete="false"     invalidMessage="Invalid Listener" style="width:100px" value=<%=configType%>>
                                                                                            </select>
                                                                                            </td>
                                                                                            <script type="">allParamNameBox.push("alertParamName<%=count2%>_<%=count1%>");</script>                                                                                 </tr>
                                                                        <%count1++;}} %>
                                                                </table> 
                                                            </td>
                                                        </tr>   
                                                        <tr><td coslpan="4"><br></td></tr>
                                                        <tr>
                                                            <td width="" align="left" style="" nowrap="nowrap" >
                                                            <strong><bean:message key="rules.listener" bundle="rules" />:</strong>&nbsp;
                                                            <select id="alertListener<%=count2%>"  dojoType="dijit.form.FilteringSelect"  name="alertListener<%=count2%>"    autoComplete="false"     invalidMessage="Invalid Listener" value="<%=listenerId%>" onChange="enableRAS(this,'alertRASProcess<%=count2%>');" >
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
                                                          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;                                                        
                                                            <strong><bean:message key="rules.rasProcess" bundle="rules" />:</strong>&nbsp;
                                                            <select id="alertRASProcess<%=count2%>" dojoType="dijit.form.FilteringSelect"  name="alertRASProcess<%=count2%>"    autoComplete="true"     invalidMessage="Invalid Listener" value="<%=rasProcessId%>" <%if(rasProcessId.equals("0")){%>disabled="disabled" <%}%>>
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
                                                            
                                            </tr>
                                                        
                                                    </table>
                                                    </fieldset> 
                                                    </td> 
                                                    <td valign="top" nowrap="nowrap" align="right">
                                                             <!--
                                                    <%if(count2 == 0){%>
                                               <img src="<%=ServerUtils.getContextName(request)%>/images/icon_plus_1.png" width="9px" height="9px" style="cursor : pointer" onclick="addAlertDtls()"/ >  
                                                        <img src="<%=ServerUtils.getContextName(request)%>/images/minus.png" width="9px" height="9px" style="cursor : pointer" onclick="removeAlertDtls('alertRow<%=count2%>', 'incidentTable' );"/ >                                                    <%}else{%>
                                             <img src="<%=ServerUtils.getContextName(request)%>/images/icon_plus_1.png" width="9px" height="9px" style="cursor : pointer" onclick="addAlertDtls()"/ > 
                                                        <img src="<%=ServerUtils.getContextName(request)%>/images/minus.png" width="9px" height="9px" style="cursor : pointer" onclick="removeAlertDtls('alertRow<%=count2%>', 'incidentTable' );"/ >
                                                    <%}%> 
                                                    -->
                                          <img src="<%=ServerUtils.getContextName(request)%>/images/minus.png" width="9px" height="9px" style="cursor : pointer" onClick="removeAlertDtls('alertRow<%=count2%>', 'incidentTable' );"/ >
                                                    </td>

                                            <%}%>
											  
                                                    </logic:iterate>
                                                </logic:notEqual>
                                            </logic:present>
                                            </tbody>
                                            </td></tr>
                                             
                                            </table>    
                            <%if(!alertExists){%>   
                            <script type="">
                                    dojo.addOnLoad(addAlertDtls); </script>
                            <%}%>
                                            
                                        </td>
                                    </tr>       
                        </table>
            </td>        

<!--                    *********************************************************************************************************************************************  -->
<!--                    *******************************************DYNAMIC VIOLATION DETAILS****************************************************************  -->
<!--                    *********************************************************************************************************************************************  -->

             <tr>
									    <td style="padding-left: 100px;">&nbsp;</td>
                                        <td colspan=5 >&nbsp; </td>
             </tr> 										
             <tr><td valign="top">
                        <table border="0" cellspacing="0" cellpadding="0"  >
                                    <tr>
									    <td style="padding-left: 100px;">&nbsp;</td>
                                        <td colspan=5 >
                                            <fieldset>
                                            <legend class="redtitle1" ><bean:message key="rules.violationCaption" bundle="rules" /></legend>
                                            <table border="0" width="100%">
                                            <tr><td align="right" colspan="3" width="100%">
                                                    <img src="<%=ServerUtils.getContextName(request)%>/images/icon_plus_1.png"  height="9px" style="cursor : pointer" onClick="addVioliationDtls()" title="Add violation" />
                                            </td></tr>  
                                            <tr><td>
                                            <tbody id="violationDtls" >
                                            </tbody>
                                            <tbody id="incidentTable1" >
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
                                                if(incidentType == 2)
                                                {count2 = count2+1;violationExists = true;
                                            %>
                                            
                                            <tr id="violationRow<%=count2%>">
                                                <script type="">violationCount = violationCount +1;</script>                                            
                                                <td>                                                                   
                                                <fieldset style="width:870px;">
                                                     <table id="violationTable" width="100%" border="0">
                                                    <tr ><td>
                                                                <table border="0">
                                                                    <tr>
                                                                     <input type="hidden" name="violationConditionId<%=count2%>" value="<%=conditionId%>" />
                                                                        <td  width="3%"   align="right" style="padding-left:0px;" nowrap="nowrap">
                                                                            <strong>&nbsp;Name&nbsp;<label class="error">*</label>:</strong></td>
                                                                        <td align="left"  width="9%">
                                                                                <input type="text"    id="violationName<%=count2%>" name="violationName<%=count2%>" class="medium" maxLength="45" readonly="readonly"  dojoType="dijit.form.ValidationTextBox" required="true" trim="true" ucfirst="true" value='<%=incidentName%>'   />
                                                                        </td>
                                                                        <script type="">prepareTextBoxArray(dojo.byId('violationName<%=count2%>')); </script>
                                                                        <td  width="40px" style="padding-left: 100px;" align="right"><strong>&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="rules.violationQry" bundle="rules" />&nbsp;<label class="error">*</label>:</strong>
                                                                        </td>
                                                                        <td width="52px" align="left">
                                                                            <textarea  id="violationQry<%=count2%>" style="width:40em;height:3em" readonly="readonly" onBlur="chkQueryDescriptionLen(this);"
                                                                            name="violationQry<%=count2%>"  required="true" trim="true"   ucfirst="true"  ><%=conditionQuery%></textarea>
                                                                            </td>
                                                                        </td>
                                                                    </tr>
                                                                </table>
                                                            </td>
                                                    </tr>
                                                    <%if(itr != null){%> 
                                                    <tr id="violationParamMaptitle" style="display:block; ">
                                                        <td class="redtitle1" colspan="2" style="padding-left:71px;">ParamsMap</td>
                                                    </tr>
                                                    <%}%>                                           

                                                    <tr id="violationParamMapDtls" style="display:block" >
                                                        <td colspan="6" align="left" style="padding-left:70px" >
                                                            <table> 
                                                                <%int count1 = 0;
                                                                if(itr != null)
                                                                while (itr.hasNext()) {
                                                                String key = (String) itr.next();
                                                                java.util.HashMap   hashConfigMap = new java.util.HashMap();
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
                                                                                            <input type="hidden" name="violationConfigId<%=count2%>_<%=count1%>" value="<%=configId%>" />
                                                                                            <td>&nbsp;<input type="text"  readonly="readonly"   id="violationParamName<%=count2%>_<%=count1%>" name="violationParamName<%=count2%>_<%=count1%>" class="medium" maxLength="45"
                                                                dojoType="dijit.form.TextBox" required="true" trim="true" ucfirst="true" value="<%=key%>"  />
                                                                                            </td>
                                                                                            <td width="13px"></td>
                                                                                            <td><strong>&nbsp;&nbsp;&nbsp;Value&nbsp;<label class="error">*</label>:</strong></td>
                                                                                            <td>&nbsp;<input type="text"    id="violationParamValue<%=count2%>_<%=count1%>" name="violationParamValue<%=count2%>_<%=count1%>" class="medium" maxLength="45"  ucfirst="true" value="<%=configValue%>"  dojoType="dijit.form.TextBox"  />
                                                                                            </td>
                                                                                            <td><strong>&nbsp;&nbsp;Type <label class="error">*</label>:</strong>
                                                                                            
                                                                                            <select id="violationParamType<%=count2%>_<%=count1%>" dojoType="dijit.form.FilteringSelect" searchAttr="label" readonly="readonly" store="attrStore"  name="violationParamType<%=count2%>_<%=count1%>" style="width:10.5em;"    autoComplete="false"     invalidMessage="Invalid Listener" style="width:100px" value=<%=configType%>>
                                                                                            </select>
                                                                                            </td>
                                                                            <script type="">allParamNameBox.push("violationParamName<%=count2%>_<%=count1%>");</script>                                                                                 </tr>
                                                                        <%count1++;}} %>
                                                                </table> 
                                                            </td>
                                                        </tr>   
                                                        <tr><td coslpan="4"><br></td></tr>
                                                        <tr>
                                                            <td  align="left"   nowrap="nowrap" coslpan="4">
                                                            <strong><bean:message key="rules.listener" bundle="rules" />:</strong>&nbsp;
                                                            <select id="violationListener<%=count2%>"  dojoType="dijit.form.FilteringSelect"  name="violationListener<%=count2%>"    autoComplete="false"     invalidMessage="Invalid Listener" value="<%=listenerId%>" onChange="enableRAS(this,'violationRASProcess<%=count2%>');" >
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
                                                        
                                                         &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;                                                        
                                                        <span style="nowrap:nowrap;"><strong ><bean:message key="rules.rasProcess" bundle="rules" />:</strong>&nbsp;</span> 
                                                            <select id="violationRASProcess<%=count2%>" dojoType="dijit.form.FilteringSelect"  name="violationRASProcess<%=count2%>"    autoComplete="true"     invalidMessage="Invalid Listener" value="<%=rasProcessId%>" <%if(rasProcessId.equals("0")){%>disabled="disabled" <%}%>>
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
                                                            <tr>
                                                <td>&nbsp;
                                                
                                                </td>   
                                            </tr>
                                                        
                                                    </table>
                                                    </fieldset> 
                                                    </td> 
                                                    <td valign="top" nowrap="nowrap" align="right">
                                                             <!--
                                                    <%if(count2 == 0){%>
                                               <img src="<%=ServerUtils.getContextName(request)%>/images/icon_plus_1.png" width="9px" height="9px" style="cursor : pointer" onclick="addAlertDtls()"/ >  
                                                        <img src="<%=ServerUtils.getContextName(request)%>/images/minus.png" width="9px" height="9px" style="cursor : pointer" onclick="removeAlertDtls('alertRow<%=count2%>', 'incidentTable' );"/ >                                                    <%}else{%>
                                             <img src="<%=ServerUtils.getContextName(request)%>/images/icon_plus_1.png" width="9px" height="9px" style="cursor : pointer" onclick="addAlertDtls()"/ > 
                                                        <img src="<%=ServerUtils.getContextName(request)%>/images/minus.png" width="9px" height="9px" style="cursor : pointer" onclick="removeAlertDtls('alertRow<%=count2%>', 'incidentTable' );"/ >
                                                    <%}%> 
                                                    -->
                                          <img src="<%=ServerUtils.getContextName(request)%>/images/minus.png"  height="9px" style="cursor : pointer" onClick="removeVioliationDtls('violationRow<%=count2%>', 'incidentTable1' );"/ >
                                                    </td>

                                            <%}%>
                                                    </logic:iterate>
                                                </logic:notEqual>
                                            </logic:present>
                                            </tbody>
                                            </td></tr>
                                             

                                            </table>
                                        <%if(!violationExists){%>   
                                        <script type="">dojo.addOnLoad(addVioliationDtls); </script>

                                        <%}%>
                                        </td>
                                    </tr>       
                        </table>
            </td>
			
			</tr>		
			</table>          
  
 <table width="100%" cellspacing="0" cellpadding="0">
                <tr>
                    <td colspan="5" class="barColor" align="right" width="1000px">
                        <button dojoType="dijit.form.Button"  type="button"   onclick="window.location =  '<%=ServerUtils.getContextName(request)%>/RulesHome.do?operation=loadRulesHome ' ">
                            <bean:message key="datasource.home" bundle="ds" />
                        </button>
                        <button dojoType="dijit.form.Button"  type="button" onClick="window.location='<%=ServerUtils.getContextName(request)%>/RulesHome.do?operation=loadRules'">
                            <bean:message key="datasource.back" bundle="ds" />
                         </button>
                         <button dojoType="dijit.form.Button" id="save" type="button" onClick="return delayFormValidation()" >
                             <bean:message key="datasource.update" bundle="ds" />
                         </button>
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


</script>
</html:html>
