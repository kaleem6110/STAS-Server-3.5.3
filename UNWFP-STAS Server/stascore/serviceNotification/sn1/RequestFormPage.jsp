<%@ taglib uri="/WEB-INF/SelfService.tld" prefix="selfservice" %>
<%@page import="java.util.*" %>
<%@page import="com.sae.userselfservice.dao.SAEMRequestAttribute" %>
<%@page import="com.sae.userselfservice.constants.AEConstants"%>
<%@page import="com.sae.common.constants.RequestConstants"%>

 
<%
	List requestAttrs = (List) session.getAttribute(AEConstants.GUI_SESSION_KEY_REQ_ATTR_ARRAY);
	String userCategory = (String)session.getAttribute(AEConstants.GUI_SESSION_KEY_SELECTED_USER_CATEGORY);
%>
<%@include file="CommonHeader.jsp" %>
<SCRIPT SRC="scripts/calendar.js"></SCRIPT>
<SCRIPT SRC="scripts/ajax.js"></SCRIPT>
<link rel="stylesheet" href="elegant.css" type="text/css"/>
<SCRIPT LANGUAGE="JavaScript">
    // Create a Java script Array to identify 
    // the Mandatory Fields and force the user to enter it. 
    var mandatoryFields = new Array();
    var mandatoryFieldLabels = new Array();
    // TO validate the inputs from the Requestor
    function callValidator() {
        var fieldObj = null;
        var count = 0;
        var message = "Please enter/select the following Mandatory Fields: \n";
        for (fieldCount = 0 ; fieldCount < mandatoryFields.length; fieldCount++) {
            fieldObj = document.getElementById(mandatoryFields[fieldCount]);
            
            if (fieldObj.value == null || fieldObj.value == "") {
                message += mandatoryFieldLabels[fieldCount] + "\n";
                count++;
            }
        }
        if (count > 0) {
            alert(message);
            return false;
        } else {
        return true;
    }
}
function callBack() {
    document.forms[0].method="Get";
    document.forms[0].action="<%= contextPath %>/SelectRequest";
    document.forms[0].submit();
}

function callCancel() {
    document.forms[0].method="Get";
    document.forms[0].action="<%= contextPath %>/SelectRequest";
    document.forms[0].submit();
}

function callSubmit() {
    if (callValidator()) {
        document.forms[0].action="<%= contextPath %>/CollectRequestDetails";
        document.forms[0].submit();
    }
}
// To show the pop up div table to search the User
function callUserSearch() {
    document.getElementById('dvRCnMon').style.visibility = 'visible' ;        
    document.getElementById('dvRCnMon').style.zIndex = '100';
    document.getElementById('dvsrch').style.visibility = 'visible';
    document.getElementById('dvsrch').style.zIndex = '100';
}
// To hide the pop up div and clean up the search results
function callCancelSearch() {
    document.getElementById('dvRCnMon').style.visibility = 'hidden' ;        
    document.getElementById('dvsrch').style.visibility = 'hidden';
    document.getElementById('srchResult').style.visibility = 'hidden';
    // Get the Object of the Results table and 
    // delete the rows except the heading row
    resultsTableObj = document.getElementById('searchResultTable');
    deleteRows(resultsTableObj);
    
    document.forms[0].searchUserId.value = "";
    document.forms[0].searchFirstName.value = "";
    document.forms[0].searchLastName.value = "";
}
function deleteRows(resultsTableObj) {
    for (rowCount = resultsTableObj.rows.length -1 ; rowCount > 0; rowCount--) {
        resultsTableObj.deleteRow(rowCount);
    }
}
// AJAX call
function callSearch() {
    xmlHttp = getXmlHttpObject();
    if (xmlHttp==null) {
        alert ("Your browser does not support AJAX!");
        return;
    } 
    
    var url="<%= contextPath %>/UserSearch";
    url=url+"?searchUserId="+ document.forms[0].searchUserId.value;
    url=url+"&searchFirstName="+ document.forms[0].searchFirstName.value;
    url=url+"&searchLastName="+ document.forms[0].searchLastName.value;
    url=url+"&sid="+Math.random();
    // On ready state call displayResults()
    xmlHttp.onreadystatechange = displayResults;
    xmlHttp.open("GET",url,true);
    xmlHttp.send(null);
    
}
// Displays the results in the form of Table rows. 
function displayResults () {
    var xmlDoc;
    var id;
    var firstName;
    var lastName;
    var email; 
    var resultsTableObj = document.getElementById("searchResultTable");
    
    if (xmlHttp.readyState==4 && xmlHttp.status == 200) { 
        document.getElementById('srchResult').style.visibility = 'visible';
        
        // Response Object is in the form of XML Document. 
        xmlDoc = getXMLDocument(xmlHttp.responseText);
        
        // Parse the XML document to get the values
        var users = xmlDoc.getElementsByTagName("UserIdentity");
        for (userCount = 0 ; userCount < users.length ; userCount++) {
            id = users[userCount].getElementsByTagName("Id")[0].childNodes[0].nodeValue;
            firstName = users[userCount].getElementsByTagName("FirstName")[0].childNodes[0].nodeValue;
            lastName = users[userCount].getElementsByTagName("LastName")[0].childNodes[0].nodeValue;
            email = users[userCount].getElementsByTagName("Email")[0].childNodes[0].nodeValue;
            
            addRowsToTable(resultsTableObj, id, firstName, lastName, email);
            
        }
    }
}
// This is to add rows to the Search Results table. 
// Search Results table already contains a Heading row. So add it to the end. 
function addRowsToTable(resultsTableObj, id, firstName, lastName, email) {
    tableRow = resultsTableObj.insertRow(resultsTableObj.rows.length);
    
    //Add Cells to the new Row object created
    var cell0 = tableRow.insertCell(0);
    cell0.innerHTML = "<input type=radio name=\"selectUser\" value="+id+">";
    
    var cell1 = tableRow.insertCell(1);
    cell1.innerHTML = "<input type=text name=\"selectUserId\" value=\""+ id +"\" readonly>";
    
    cell2 = tableRow.insertCell(2);
    cell2.innerHTML = "<input type=text name=\"selectFirstName\" value=\""+ firstName +"\" readonly>";
    
    cell3 = tableRow.insertCell(3);
    cell3.innerHTML = "<input type=text name=\"selectLastName\" value=\""+ lastName +"\" readonly><input type=HIDDEN name=\"selectEmail\" value=\""+ email +"\" >";
    
}
function callSelectUser() {
    var selectUserObj = document.forms[0].selectUser;
    // If there is only one row then treat it as single element
    /*if (selectUserObj.checked) {
    document.forms[0].UserId.value = document.forms[0].selectUserId.value;
    document.forms[0].FirstName.value = document.forms[0].selectFirstName.value;
    document.forms[0].LastName.value = document.forms[0].selectLastName.value;
    document.forms[0].Email.value = document.forms[0].selectEmail.value;
    } else { // If there is more than one row then treat it as array
    for (rowCount = 0 ; rowCount < selectUserObj.length ; rowCount++) {
    if (selectUserObj[rowCount].checked) {
    document.forms[0].UserId.value = document.forms[0].selectUserId[rowCount].value;
    document.forms[0].FirstName.value = document.forms[0].selectFirstName[rowCount].value;
    document.forms[0].LastName.value = document.forms[0].selectLastName[rowCount].value;
    document.forms[0].Email.value = document.forms[0].selectEmail[rowCount].value;
    }
    }
    }*/
    
    var selectedUserId;
    if (selectUserObj.checked) {
        selectedUserId = document.forms[0].selectUserId.value;
    } else { // If there is more than one row then treat it as array
    for (rowCount = 0 ; rowCount < selectUserObj.length ; rowCount++) {
        if (selectUserObj[rowCount].checked) {
            selectedUserId = document.forms[0].selectUserId[rowCount].value;
        }
    }
}
document.forms[0].action="<%= contextPath %>/RequestForm?selectedUserId="+selectedUserId;
//alert(document.forms[0].action)
document.forms[0].submit();

// Call this method to clean the results and hide the search results table
//callCancelSearch();
}
</SCRIPT>

<tr>
<td>
	<div align="center">
		<span>   
			<table width="100% cellpadding="0" cellspacing="0">
				<tr><td align="left">
					<span style="padding-left: 8px;padding-right:8px;"><img src="images/42.gif"/></span>
				</td></tr>
				<tr><td style="padding-left: 8px;padding-right:8px;">
					<table cellpadding="0" cellspacing="0">
						<tr>
							<td width="58px;" style="position: relative; top: -10px;">
								<span class="topGuidedProcedure"></span>
							</td>
							<td width="110px;" style="position: relative; top: -10px;">
								<span class="topGuidedProcedure"></span>
							</td>
							<td width="65px;" style="position: relative; top: -10px;">
								<span class="topGuidedProcedure"></span>
							</td>
						</tr>
					</table>
				</td></tr>
			</table>
		</span>
	</div>		
</td>
</tr>

<tr>
    <td>
    
        <table style="width:100%;" cellpadding="0" cellspacing="" border="0" >
            <tr>
                <td>
                    <table width="100%" cellpadding="0" cellspacing="0" border="0" style="margin-top:1px;">
                        <tr>
                            <td width="5" height="30" valign="top" bgcolor="#bebebe"><img src="images/Widget_topLeft.gif" width="5" height="30" /> </td>
                            <td class="widget_title_form"  height="30" width="100%" bgcolor="#bebebe">Request Form</td>
                            <td width="5" height="30" valign="top" bgcolor="#bebebe"><img src="images/Widget_topRight.gif" width="5" height="30" /> </td>
                        </tr>
                    </table>
                    <table cellpadding="6px" cellspacing="2px" border="0" width="100%" style="width: 100%;border: 2px solid #d9d9d9">
                        <tr>
                            <td>
                                <div id="dvRCnMon" style="position:absolute; visibility:hidden; border-left:2px solid #d9d9d9;  solid #d9d9d9; border-top:2px solid #d9d9d9; top:200px; left:375px; width:600px; height:240px; background-color:White;">
                                <table width="100%" border="1px" rules="none" style="border:1 px silver; border-collapse:collapse">
                                    <tr>
                                        <td>
                                            <div id="dvsrch" style=" visibility:hidden; top:0px; left:0px; width:600px; background-color:White;">
                                            <table width="100%">
                                                <tr>
                                                    <td colspan="3" class="widget_subHeader" bgcolor="#d9d9d9" style="padding:3px 0 3px 0; border-left:1px solid #d9d9d9; border-right:1px solid #d9d9d9;"><b><font size="2px">User Search</font></b></td> 
                                                </tr>
                                            </table>
                                            <table width="100%">
                                                <tr><td width="1%">&nbsp;</td>
                                                    <td>
                                                        <table width="100%">
                                                            <tr>
                                                                <td ><b>User ID</b></td>
                                                                <td><input name="searchUserId" type="text"  class="input" /></td>
                                                            </tr>
                                                            <tr>
                                                                <td><b>First Name</b></td>
                                                                <td><input name="searchFirstName" type="text"  class="input" /></td>
                                                            </tr>
                                                            <tr>
                                                                <td><b>Last Name</b></td>
                                                                <td><input name="searchLastName" type="text"  class="input" /></td>
                                                            </tr>
                                                            <tr><td colspan="2">
                                                                    <input type="button" class="tblbtn" onclick="javascript:callSearch()" value="Search">
                                                                    &nbsp;&nbsp;<input type="button" class="tblbtn" 
                                                                                       onclick="javascript:callCancelSearch()" value="Cancel">
                                                            </td></tr>		           		            
                                                        </table>
                                                    </td>
                                                </tr>
                                            </table>
                                            <table width="98%" style="margin-top:1px;margin-left:10px;">
                                                <tr><td style="background-color:#d4d6d7; height:3px;"></td></tr>
                                            </table> 
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <div id="srchResult" style=" visibility:hidden;  left:0px; width:600px; background-color:white">
                                        <table width="100%" >
                                            <tr>
                                                <td colspan="3" class="widget_subHeader" bgcolor="#d9d9d9" style="padding:3px 0 3px 0; border-left:1px solid #d9d9d9; border-right:1px solid #d9d9d9;"><b><font size="2px">Search Results</b></strong></td>
                                            </tr>
                                        </table>
                                        <table border="0" width="100%" cellpadding="2" cellspacing="0" style="margin-top:1px;" id="searchResultTable">
                                            <tr class="widget_subHeader" bgcolor="#d9d9d9" style="padding:3px 0 3px 0; border-left:1px solid #d9d9d9; border-right:1px solid #d9d9d9;">
                                                <td><b>Select</b></td>
                                                <td ><b>User Id</b></td>
                                                <td ><b>First Name</b></td>
                                                <td ><b>Last Name</b></td>
                                            </tr>
                                        </table>
                                        <table style="margin-top:5px;margin-left:10px;">
                                            <tr><td><input type="button" class="tblbtn" class="tblbtn" onclick="callSelectUser()" value="Done">
                                                    &nbsp;&nbsp;<input type="button" class="tblbtn"  class="tblbtn" onclick="callCancelSearch()" value="Cancel">
                                            </td></tr>
                                        </table>
                                    </div>
                                </td>
                            </tr>
                        </table>   
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <table border="0" width="100%" cellpadding="3" cellspacing="3" border="0" style="margin-top:1px;" >
                    <tr>
                    <td><b>Request Category</b></td>
                    <td><%=session.getAttribute("requestCateDesc") %></td>
                    <td></td>
                    <td></td>
                    </tr>
                    <%
                        boolean isNewRow = false;
                        for (int attrCount = 0; attrCount < requestAttrs.size(); attrCount++) {
                            SAEMRequestAttribute reqAttribute = (SAEMRequestAttribute) requestAttrs.get(attrCount);
                            List valueList = reqAttribute.getValueList();
                            String values = "";
                            String delimeter = "";
                            if (valueList != null) {
                                for (int valueCount = 0; valueCount < valueList.size(); valueCount++) {
                                    values += delimeter + (String) valueList.get(valueCount);
                                    delimeter = ",";
                                }
                            }
							
                            if (attrCount % 2 == 0) {
                                isNewRow = true;
                            } else {
                                isNewRow = false;
                            }

                            if (isNewRow) {
                                out.println("<tr>");
                            }
                        %>
                        <td><b><%= reqAttribute.getLabelName() %></b></td>
                        <SCRIPT LANGUAGE="JavaScript">
                             <%
                            if (reqAttribute.getMandatory() == 1) {
                             %>
                                 mandatoryFields[mandatoryFields.length] = "<%= reqAttribute.getAttributeName() %>";
                                 mandatoryFieldLabels[mandatoryFieldLabels.length] = "<%= reqAttribute.getLabelName() %>";
                             <%
                            }
                             %>
                        </SCRIPT>
                        <%
                            if (reqAttribute.getDisplayType() == AEConstants.HTML_CONTROL_LABEL) {
                        %>
                        <td><%= values %></td>
                        <%
                        } else if (reqAttribute.getDisplayType() == AEConstants.HTML_CONTROL_TEXT_BOX) {
                        %>
                        <td>
                            <input type="text" name="<%= reqAttribute.getAttributeName() %>" value="<%= values %>" size="40" maxlength="<%= reqAttribute.getLength() %>" id="<%= reqAttribute.getAttributeName() %>">
                            <%
                            if (reqAttribute.getDataType() == AEConstants.HTML_CONTROL_DATA_TYPE_VALUE_DATE) {
                            %>
                            <img src="images/calendar.gif" width="15" height="18" onclick="event.cancelBubble=true;lcs('<%= reqAttribute.getAttributeName() %>')">
                            <%
                            }
                            if (reqAttribute.getAttributeName().equals(RequestConstants.REQ_STD_ATTR_USER_ID) && 
                            		(AEConstants.USER_CATEGORY_REQUEST_FOR_ANOTHER_USER_VALUE).equals(userCategory)) {
                            %>
                            <a href="javascript:callUserSearch()"><img src="images/arrowrightmonth.gif" width="12" height="12" border="0"/></a>
                            <%
                            }		
                            %>
                        </td>
                        <%
                        } else if (reqAttribute.getDisplayType() == AEConstants.HTML_CONTROL_RADIO_BUTTON) {
                        %>
                        <td><selfservice:GenerateOption name="<%= reqAttribute.getAttributeName() %>" entityName="<%= reqAttribute.getLookupTable() %>" controlType="<%= AEConstants.HTML_CONTROL_RADIO_BUTTON %>" defaultValue="<%= values %>"></selfservice:GenerateOption></td>
                        <%
                        } else if (reqAttribute.getDisplayType() == AEConstants.HTML_CONTROL_DROP_DOWN) {

                        %>
                        <td>
                            <select name="<%= reqAttribute.getAttributeName() %>" id="<%= reqAttribute.getAttributeName() %>" style="width:205px; height:20px;">
                                <option value="">Select a Value</option>
                                <selfservice:GenerateOption name="<%= reqAttribute.getAttributeName() %>" entityName="<%= reqAttribute.getLookupTable() %>" controlType="<%= AEConstants.HTML_CONTROL_DROP_DOWN %>" defaultValue="<%= reqAttribute.getDefaultValue() %>"></selfservice:GenerateOption>
                            </select>
                        </td>
                        <%
                        } else if (reqAttribute.getDisplayType() == AEConstants.HTML_CONTROL_CHECK_BOX) {
                        %>
                        <td><selfservice:GenerateOption name="<%= reqAttribute.getAttributeName() %>" entityName="<%= reqAttribute.getLookupTable() %>" controlType="<%= AEConstants.HTML_CONTROL_CHECK_BOX %>" ></selfservice:GenerateOption></td>
                        <%
                        } else if (reqAttribute.getDisplayType() == AEConstants.HTML_CONTROL_HIDDEN) {
                        %>					
                        <td><input type="HIDDEN" name="<%= reqAttribute.getAttributeName() %>" value="<%= values %>" size="<%= reqAttribute.getLength() %>" maxlength="<%= reqAttribute.getLength() %>"></td>					
                        <%
                        } else if (reqAttribute.getDisplayType() == AEConstants.HTML_CONTROL_TEXT_AREA) {
                        %>				
                        <td><textArea name="<%= reqAttribute.getAttributeName() %>"  id="<%= reqAttribute.getAttributeName() %>"><%= values %></textArea></td>					
                        <%
                        } else if (reqAttribute.getDisplayType() == AEConstants.HTML_CONTROL_MULTIPLE_SELECT) {
                        %>				
                        <td>
                            <select multiple="multiple" size="3" name="<%= reqAttribute.getAttributeName() %>" id="siteSelection">
                                <selfservice:GenerateOption name="<%= reqAttribute.getAttributeName() %>" entityName="<%= reqAttribute.getLookupTable() %>" controlType="<%= AEConstants.HTML_CONTROL_DROP_DOWN %>" ></selfservice:GenerateOption>
                            </select>
                        </td>					
                        <%				}
                        }
                        if (isNewRow) {
                            out.println("</tr>");
                        }
                        %>          
                    </table>
                </td>
            </tr>
        </table>
        <table cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr height="25px" valign="center"><td align="left" bgcolor="#d9d9d9"
                        style="border-left: 1px solid #d9d9d9; border-right: 1px solid #d9d9d9;">
                    <input type="HIDDEN" name="requestFrom" value="<%= AEConstants.GUI_PAGE_NAME_VALUE_REQUEST_FORM_PAGE %>"> 
                    <input type="button" class="tblbtn" value="Back" style="width: 60px" onclick="callBack()"/>
                    <input type="button" class="tblbtn" value="Next" style="width: 60px" onclick="callSubmit()"/>
                    <input type="button"  class="tblbtn" value="Cancel" style="width: 60px" onclick="callCancel()" />
                </td>
            </tr>
        </table>
    </td>
    </tr>
      


          
</table>

</td></tr></table>
</td></tr></table>
</td></tr></table>
</FORM>  
</BODY>
<%@page import="com.sae.userselfservice.service.SubmitSystemConnector;"%>
</HTML>
