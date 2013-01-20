<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@page import="com.enterprisehorizons.magma.server.util.ServerUtils"%>
<%@ include file="/common/dojo.jsp"%>
<html:html locale="true">
<head>

<style>
.dateClass {
	padding-top:4px;
	}
</style>
<script>
var tr_selected = '';
function hover(obj){
	obj.className='class_hover';
}

function unHover(obj, row_num){
	if (tr_selected != row_num) obj.className = 'class_no_hover bgcolor';
}

	function previousPage(){
        var pageNo = document.getElementById("pageNo").value;

        if(parseInt(pageNo)>1){
            document.forms[0].pageNo.value = (parseInt(pageNo)-1);
            document.forms[0].action="./ManageRules.do?operation=showAllViolations";
            document.forms[0].submit();
        }
    }


    function nextPage(){
        var pageNo = document.getElementById("pageNo").value;
        var totalNoOfPages = document.getElementById("totalNoOfPages").value;
        if(parseInt(pageNo)<totalNoOfPages){
            document.forms[0].pageNo.value = (parseInt(pageNo)+1);
            document.forms[0].action="./ManageRules.do?operation=showAllViolations";
            document.forms[0].submit();
        }
    }

    function gotoPage(pageNo){
            document.forms[0].pageNo.value = pageNo;
            document.forms[0].action="./ManageRules.do?operation=showAllViolations";
            document.forms[0].submit();
    }

    function onEnterGoToPage(){
        if(window.event.keyCode==13){
            var pageNo = document.getElementById("enteredPageNo").value;
            var totalNoOfPages = document.getElementById("totalNoOfPages").value;
            if(parseInt(pageNo)>=1 && parseInt(pageNo)<=totalNoOfPages){
                document.forms[0].pageNo.value = pageNo;
                document.forms[0].action="./ManageRules.do?operation=showAllViolations";
                document.forms[0].submit();
            }       
        }
    }


function checkDates() {

	/*startDate 	= document.getElementById("startDate").value;
	endDate		= document.getElementById("endDate").value; */
	startDate	= dijit.byId('startDate').value;
	endDate		= dijit.byId('endDate').value;
	if(startDate == null || startDate == ""){
		showEmptyDialog("<bean:message key="rules.check.startdate.select" bundle="rules" />", "<bean:message key="rules.violation.title" bundle="rules" />");
        return false;
	}

	if(endDate == null || endDate == ""){
		showEmptyDialog("<bean:message key="rules.check.enddate.select" bundle="rules" />", "<bean:message key="rules.violation.title" bundle="rules" />");
        return false;
	}



	if (new Date(startDate) > new Date(endDate)){
		showEmptyDialog("<bean:message key="rules.check.date" bundle="rules" />", "<bean:message key="rules.violation.title" bundle="rules" />");
        return false;
    } 

	document.getElementById('pageNo').value='1';	
    
	document.forms[0].submit();
}


</script>

</head>
<body class="tundra bodybg">
<form name="rulesForm" method="post" action="<%=ServerUtils.getContextName(request)%>/ManageRules.do?operation=showAllViolations">
<!----  HIden Variables -->

<input type="hidden" name="operation" id="operation" value="showAllViolations"/>
<input type="hidden" name="pageNo" id="pageNo" value="<bean:write name="violationManageRulesForm" property="pageNo"/>"/>
<input type="hidden" name="totalNoOfPages" id="totalNoOfPages" value="<bean:write name="violationManageRulesForm" property="totalNoOfPages"/>"/>

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
                          <td class="pageTitle"><strong><bean:message key="rules.violations" bundle="rules" /></strong></td>
                        </tr>
  						<tr>
						   <td class="paddingTitleDesc" colspan="2">
								<bean:message key="rules.violationCaption.description" bundle="rules" />               
							</td>  
						</tr>
                        <tr>
										<td>&nbsp;</td>
                                        <td style="width:500px;" nowrap="nowrap" align="left">
                                                           <font color="blue" style="font-family: Tahoma;font-size: 12px;" ><b>
                                                            <html:messages id="ruleSaved" message="true" bundle="rules"><bean:write name="ruleSaved" /></html:messages> 
                                                            </b></font> 
                                                            <font color="red" style="font-family: Tahoma;font-size: 12px;"><b><html:errors bundle="rules"/></b></font> 
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
      		<td colspan="6"> 
			<table>
				<tr>
					<td style="padding-top:7px"><strong><bean:message key="rules.violation.startdate" bundle="rules" /></strong></td>
					<td style="padding-top:7px">
						<input	name="startDate" type="text" id="startDate"  dojoType="dijit.form.DateTextBox" required="true" trim="true"	promptMessage="mm/dd/yyyy" onChange="dojo.byId('startDateDiv').innertHTML=arguments[0];" invalidMessage="Invalid date. Use mm/dd/yyyy format." class="dateClass" value='<bean:write name="violationManageRulesForm" property="startDate" />'  />
						<div id="startDateDiv"  style="position:absolute;z-index:100;display:none; top:176px; left:600px; width:200; height:200; border:2px solid #EAEAEA;background-color:blue;opacity:1;">
						</div>							  
					</td>
					<td width="26%"></td>
					<td style="padding-top:7px"><strong><bean:message key="rules.violation.enddate" bundle="rules" /></strong></td>
					<td style="padding-top:7px">
						<input	name="endDate" type="text" id="endDate"  dojoType="dijit.form.DateTextBox" required="true" trim="true"	promptMessage="mm/dd/yyyy" onChange="dojo.byId('endDateDiv').innertHTML=arguments[0];" invalidMessage="Invalid date. Use mm/dd/yyyy format." class="dateClass"   value='<bean:write name="violationManageRulesForm" property="endDate" />' />
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
	  
	<logic:present name="allViolationList" scope="request">
	<tr>
		  <td height="30" valign="top"  align="right" colspan="6">
		  <img src="<%=ServerUtils.getContextName(request)%>/images/portal/btn_remove_all_on.png" id="start_on" style="display:none;cursor:pointer" onClick="gotoPage(1);"/> 
		  <img src="<%=ServerUtils.getContextName(request)%>/images/portal/btn_remove_all_off.png" id="start_off" style="display:none;cursor:pointer"/> 
		  <img src="<%=ServerUtils.getContextName(request)%>/images/portal/btn_remove_one_on.png" id="previous_on" style="display:none;cursor:pointer" onClick="previousPage();"/> 
		  <img src="<%=ServerUtils.getContextName(request)%>/images/portal/btn_remove_one_off.png" id="previous_off" style="display:none;cursor:pointer"/>
		  <span style="valign=top"><input type="text" id="enteredPageNo" size="5" name="enteredPageNo" style="height:30;width:60;font-size:0.8em;text-align:center"
		   onkeypress="onEnterGoToPage();"  
			value="<bean:write name="violationManageRulesForm" property="pageNo"/> of <bean:write name="violationManageRulesForm" property="totalNoOfPages"/>" tooltip="<bean:write name="violationManageRulesForm" property="pageNo"/> of <bean:write name="violationManageRulesForm" property="totalNoOfPages"/>"  disabled="true"/></span> 
			<img src="<%=ServerUtils.getContextName(request)%>/images/portal/btn_add_one_on.png" id="next_on" style="display:none;cursor:pointer" onClick="nextPage()"/> 
			<img src="<%=ServerUtils.getContextName(request)%>/images/portal/btn_add_one_off.png" id="next_off" style="display:none;cursor:pointer"/> 
			<img src="<%=ServerUtils.getContextName(request)%>/images/portal/btn_add_all_on.png" id="end_on" style="display:none;cursor:pointer" 
			onClick="gotoPage('<bean:write name="violationManageRulesForm" property="totalNoOfPages"/>')" /> 
			<img src="<%=ServerUtils.getContextName(request)%>/images/portal/btn_add_all_off.png" 
			id="end_off" style="display:none;cursor:pointer"/></td>
     </tr>
    
      
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
				<bean:message key="rules.violationProperties" bundle="rules" />
			</td>
			<td style="padding-left:10px">
				<bean:message key="rules.timeOfViolationLabel" bundle="rules" />
			</td>
			<td style="padding-left:10px">
				<bean:message key="rules.feedReportData" bundle="rules" />
			</td>
		</tr>
	<% int i = 1; %>
	<bean:define name="violationManageRulesForm" property="pageNo" id="violationPageNumber" />
	  <%  i = ( 10 * (Integer.parseInt(violationPageNumber+"") - 1) );  %>
	<logic:iterate name="allViolationList" scope="request" id="violationDtls">
		<tr class="bgcolor" onMouseOver="hover(this);" onMouseOut="unHover(this, 1);">
			<td class="textnormal12" style="padding-left:10px">
				<%=++i %>
			</td>
			<td class="textnormal12" style="padding-left:10px">	
				<bean:write name="violationDtls" property="incidentName" />
			</td>
			<td class="textnormal12" style="padding-left:10px">
				<bean:write name="violationDtls" property="violationConditionName" />
			</td>
			<td class="textnormal12" style="padding-left:10px">
				<bean:write name="violationDtls" property="violationProperties" />
			</td>
			<td class="textnormal12" style="padding-left:10px">
				<bean:write name="violationDtls" property="createdOn"/>
			</td>
			<td class="textnormal12" style="padding-left:10px">
				 <a href="<%=ServerUtils.getContextName(request)%>/createNercReportData.do?operation=loadNercInteroperabilityDtls&incidentId=<bean:write name="violationDtls" property="incidentId"/>&violationConditionId=<bean:write name="violationDtls" property="violationConditionId"/>&propertySetId=<bean:write name="violationDtls" property="propertySetId"/>&startDate=<logic:present name="violationManageRulesForm" property="startDate"><bean:write name="violationManageRulesForm" property="startDate" /></logic:present>&endDate=<logic:present name="violationManageRulesForm" property="endDate"><bean:write name="violationManageRulesForm" property="endDate" /></logic:present>&flag=fromViolations">
                            <img src="<%=ServerUtils.getContextName(request)%>/images/portal/icon_config.png" WIDTH=50 HEIGHT=30 ALT="Feed Report Data"/>
                        </a>

			</td>
		</tr>
	</logic:iterate>
	</logic:present>
	<logic:notPresent name="allViolationList" scope="request">
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
					<button dojoType="dijit.form.Button" class="register"  type="button" onClick="window.location =  '<%=ServerUtils.getContextName(request)%>/RulesHome.do?operation=loadRulesHome'"> <bean:message key="dbconfig.back"/> </button>                  
                  			  
                     </td>
    </tr>
</table>
</form>
</body>
<logic:present name="allViolationList" scope="request">
 <script>
    dojo.addOnLoad(loadFormValues); 
        function loadFormValues(){
        
                if(<bean:write name="violationManageRulesForm" property="totalNoOfPages"/>  <= 1 && <bean:write name="violationManageRulesForm" property="pageNo"/> <= 1){
                        document.getElementById('start_on').style.display='none';
						document.getElementById('start_off').style.display='';
						document.getElementById('previous_on').style.display='none';
						document.getElementById('previous_off').style.display='';
						document.getElementById('next_on').style.display='none';
						document.getElementById('next_off').style.display='';
						document.getElementById('end_on').style.display='none';
						document.getElementById('end_off').style.display='';						
                }else if(<bean:write name="violationManageRulesForm" property="totalNoOfPages"/>  > 1 && <bean:write name="violationManageRulesForm" property="pageNo"/> <= 1){
                         document.getElementById('start_on').style.display='none';
						document.getElementById('start_off').style.display='';
						document.getElementById('previous_on').style.display='none';
						document.getElementById('previous_off').style.display='';
						document.getElementById('next_on').style.display='';
						document.getElementById('next_off').style.display='none';
						document.getElementById('end_on').style.display='';
						document.getElementById('end_off').style.display='none';
                }else if(<bean:write name="violationManageRulesForm" property="totalNoOfPages"/>  > 1 && (<bean:write name="violationManageRulesForm" property="pageNo"/> > 1 && (<bean:write name="violationManageRulesForm" property="pageNo"/> != <bean:write name="violationManageRulesForm" property="totalNoOfPages"/>))){
                        document.getElementById('start_on').style.display='';
						document.getElementById('start_off').style.display='none';
						document.getElementById('previous_on').style.display='';
						document.getElementById('previous_off').style.display='none';
						document.getElementById('next_on').style.display='';
						document.getElementById('next_off').style.display='none';
						document.getElementById('end_on').style.display='';
						document.getElementById('end_off').style.display='none';
                }else if((<bean:write name="violationManageRulesForm" property="totalNoOfPages"/>  > 1) && (<bean:write name="violationManageRulesForm" property="pageNo"/> == <bean:write name="violationManageRulesForm" property="totalNoOfPages"/>)){                     
                        document.getElementById('start_on').style.display='';
						document.getElementById('start_off').style.display='none';
						document.getElementById('previous_on').style.display='';
						document.getElementById('previous_off').style.display='none';
						document.getElementById('next_on').style.display='none';
						document.getElementById('next_off').style.display='';
						document.getElementById('end_on').style.display='none';
						document.getElementById('end_off').style.display='';
						
                }


        }

</script>
</logic:present>
</html:html>
 