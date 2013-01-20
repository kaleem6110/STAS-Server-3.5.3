 <%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib prefix="tiles" uri="/tags/struts-tiles"%>
<%@ include file="/common/dojo.jsp" %>
<%@page import="com.enterprisehorizons.magma.server.util.ServerUtils"%>
<%
java.util.List incidentList = (java.util.List) request.getAttribute("rulesList");
%>



<html:html locale="true">
<head>
<title>Manage Rules</title>
<link href="css/sti.css" rel="stylesheet" type="text/css" />
<script type="">


 
 
var tr_selected = '';


function hover(obj){
        obj.className='class_hover';
}

function unHover(obj, row_num){
	if (tr_selected != row_num) obj.className = 'class_no_hover bgcolor';
}
    
function submitForm(tmp)
{
	if(tmp.value == dijit.byId('idCreate').value)
	{
		document.forms[0].action = "<%=ServerUtils.getContextName(request)%>/ManageRules.do?operation=createRule";
		document.forms[0].submit();
	}
	else if(tmp.value == dijit.byId('idUpdate').value)
	{
	if(!validateRadioFields())
	{
		showEmptyDialog("Please select rule ", "Manage Rules");
	return false;
	}	
		document.forms[0].action = "<%=ServerUtils.getContextName(request)%>/ManageRules.do?operation=updateRule";
		document.forms[0].submit();
	}
	else if(tmp.value == dijit.byId('idView').value)
	{
	if(!validateRadioFields())
	{
		showEmptyDialog("Please select rule ", "Manage Rules");
		return false;
	}	
		document.forms[0].action = "<%=ServerUtils.getContextName(request)%>/ManageRules.do?operation=viewIncidentRule";
		document.forms[0].submit();
	}
	else if(tmp.value == dijit.byId('idDelete').value)
	{
		if(!validateRadioFields())
		{
			showEmptyDialog("Please select rule ", "Manage Rules");
			return false;
		}	
		document.forms[0].action = "<%=ServerUtils.getContextName(request)%>/ManageRules.do?operation=viewIncidentRule&status=delete";
		document.forms[0].submit();
	}   
	else if(tmp.value == dijit.byId('idBack').value)
	{
		document.forms[0].action = "<%=ServerUtils.getContextName(request)%>/RulesHome.do?operation=loadRulesHome ";
		document.forms[0].submit();
	}            
}
    
function setValue(tmp,tmp1)
{
	this.value = tmp1;
}  
    
function validateRadioFields()
{
	var inStatus = false;
	var tmp = document.getElementsByName('incidentId');
   	for(var count=0;count <tmp.length;count++)      
    {
		if(tmp[count].checked)
		{
			inStatus = true;
			break;
		}	
	}		
	return inStatus;
}

 
    

</script>

</head>
<body class="tundra bodybg">
<html:form action="ManageRules.do"  enctype="multipart/form-data">
<table width="100%" cellspacing="0" cellpadding="0" align="center"  border="0" >
            <tr>
                <td  class="pageTitle paddingTitle">      
                    <bean:message key="rules.configureRules" bundle="rules" />
                </td>
            </tr>
            <tr>
               <td class="paddingTitleDesc">
                    <bean:message key="rules.manageRules.description" bundle="rules" />               
                </td>  
            </tr>
                        <tr>
                                        <td style="padding-left:67px; width:500px;" nowrap="nowrap" align="left">
                                                           <font color="blue" style="font-family: Tahoma;font-size: 12px;" ><b>
                                                            <html:messages id="incidentDeleted" message="true" bundle="rules"><bean:write name="incidentDeleted" /></html:messages> 
                                                            </b></font> 
                                                            <font color="red" style="font-family: Tahoma;font-size: 12px;"><b><html:errors bundle="rules"/></b></font> 
                                        </td>  
                      </tr>

                        
                        <tr>
                          <td>&nbsp;</td>
                          <td colspan="2" class="error">
                              <!--  <html:errors bundle="ecoweb"/>   -->
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
                                                           
                                                             <table border="0" width="900px"   height="54" cellspacing="0" cellpadding="0" >
                                                                    <tr>
                                                                        <td>
                                                                         
                                                                            <table border="1" cellspacing="0" cellpadding="0"  width="100%" class="bgcolorReports ">
                                                                                <div  style="background:#ffffff;">                                                                         
                                                                                <tr  class="" bgcolor="#ADADAD"   >
                                                                                    <td  align="left"  valign="middle" >&nbsp;<label class="label"><bean:message key="rules.manageRules.select" bundle="rules" /></label></td>
                                                                                    <td height="27" align="left" style="width: 125px;"  valign="middle">&nbsp;<label class="label"><bean:message key="rules.manageRules.name" bundle="rules" /></label></td>
                                                                                    <td height="27" align="left" style="width: 300px;" valign="middle" >&nbsp;<label class="label"><bean:message key="rules.descriptionLabel" bundle="rules" /></label></td>
                                                                                </tr>
                                                                                </div>
                                                                                <%for(int count=0;count < incidentList.size(); count++){
                                                                                    com.spacetimeinsight.rules.bean.RuleIncidentBean ruleIncidentBean =(com.spacetimeinsight.rules.bean.RuleIncidentBean) incidentList.get(count);      
                                                                                    String id = ruleIncidentBean.getIncidentId()+"";
                                                                                    String incidentName = ruleIncidentBean.getIncidentName();
                                                                                    String incidentDesc = ruleIncidentBean.getIncidentDesc(); 
%>  
                                                                                 <tr class="bgcolor" onMouseOver="hover(this);" onMouseOut="unHover(this, 1);">
                                                                                    <td height="27" align="center" valign="middle" style="width:25px;" ><html:radio property="incidentId" value="<%=id%>" /></td>
                                                                                    <td height="27" align="left" style="width:125px;">
                                                                                        <label class="body">&nbsp;<%=incidentName%></label
                                                                                    ></td>
                                                                                    <td height="27" align="left" style="width: 300px;">
                                                                                        &nbsp;<label class="body"><%=incidentDesc%> </label> 
                                                                                    </td>
                                                                               </tr>
                                                                            <%}%>   
                                                                            </table>
                                                                        </td> 
                                                                    </tr>
                                                            </table>     
                                                        
                </td>
            </tr>   
<tr><td>&nbsp;</td></tr>
            
    </table>
      <input type="hidden" name="operation" id="operation" value="" />

<!-- Button Panel -->
<table width="100%"  border="0">
    <tr align="center" class="barColor">

            <td  align="center">
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;                                                             
            
                    <button dojoType="dijit.form.Button" id = "idHome"  type="button"   name="btnHome" value="<bean:message key="rules.home" bundle="rules"/>"  onclick="window.location =  '<%=ServerUtils.getContextName(request)%>/RulesHome.do?operation=loadRulesHome'">
                        <bean:message key="rules.home" bundle="rules"/> 
                    </button>
                    <button dojoType="dijit.form.Button" id = "idBack"  type="button"   name="btnBack" value="<bean:message key="rules.back" bundle="rules"/>" onClick="return submitForm(this);" >
                        <bean:message key="rules.back" bundle="rules"/>
                    </button>
                    <button  dojoType="dijit.form.Button" id = "idView" type="button" name="btnView" value="<bean:message key="rules.view" bundle="rules"/>" onClick="return submitForm(this);"><bean:message key="rules.view" bundle="rules"/></button>
                    <button  dojoType="dijit.form.Button"  id = "idCreate" name="btCreate" value="<bean:message key="rules.create" bundle="rules"/>" onClick="return submitForm(this);"><bean:message key="rules.create" bundle="rules"/></button>
                    <button  dojoType="dijit.form.Button" id = "idUpdate" type="button" name="btnUpdate" value="<bean:message key="rules.update" bundle="rules"/>" onClick="return submitForm(this);"><bean:message key="rules.update" bundle="rules"/></button>
					<button  dojoType="dijit.form.Button" id = "idDelete" type="button" name="btnDelete" value="<bean:message key="rules.delete" bundle="rules"/>" onClick="return submitForm(this);"><bean:message key="rules.delete" bundle="rules"/></button>
                              </td>
    </tr>

    </tr>
</table>
 
</html:form>        

</body>
</html:html>