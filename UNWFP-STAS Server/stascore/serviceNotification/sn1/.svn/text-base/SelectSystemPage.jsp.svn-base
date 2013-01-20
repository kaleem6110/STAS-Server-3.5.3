<%@page import="java.util.*" %>
<%@page import="com.sae.userselfservice.dao.SearchResults" %>
<%@page import="com.sae.userselfservice.constants.AEConstants" %>
<%@page import="com.sae.userselfservice.service.SubmitSystemConnector" %>
<% 
	System.out.println("Select System Pages 1");
	Map<Long,String> systemTypes = (Map<Long,String>)session.getAttribute(AEConstants.GUI_SESSION_KEY_SYSTEM_TYPE_NAMES);
	List<SubmitSystemConnector> searchSysConn = (List<SubmitSystemConnector>)session.getAttribute(AEConstants.GUI_SESSION_KEY_SEARCH_RESULT_SYSTEMS);
	String selAccessType = (String)session.getAttribute(AEConstants.GUI_SESSION_KEY_SELECTED_ACCESS_TYPE);
	System.out.println("selAccessType...."+selAccessType);
	List<SubmitSystemConnector> resultSysConn = (List<SubmitSystemConnector>)session.getAttribute(AEConstants.GUI_SESSION_KEY_SELECTED_SYSTEMS);
	String selFilter = (String)session.getAttribute(AEConstants.GUI_SESSION_KEY_SELECTED_SYSTEM_FILTER);
	System.out.println("selFilter...."+selFilter);
	System.out.println("selAccessType...."+selAccessType);
	
	
	
	String selectedIndex = "";
	Map<String,String> matchedIndex = new HashMap<String,String>();
	if(null != resultSysConn && resultSysConn.size() > 0) {
		for(SubmitSystemConnector resultSysConItem:resultSysConn){
			selectedIndex += resultSysConItem.getId().toString()+",";
			matchedIndex.put(resultSysConItem.getId().toString(),resultSysConItem.getId().toString());
		}
		if(selectedIndex.length() >= 2)
			selectedIndex = selectedIndex.substring(0,selectedIndex.length()-1);
	}
	String selectedSearchIndex = "";
	if (searchSysConn != null) {
		int resultLength = searchSysConn.size(); 
		for (int selCount = 0 ; selCount < resultLength ; selCount++) {
			SubmitSystemConnector sysConn = (SubmitSystemConnector) searchSysConn.get(selCount);
			if(!matchedIndex.containsKey(sysConn.getId().toString())) {
				selectedSearchIndex += sysConn.getId() + ",";
			}
		}
		if(selectedSearchIndex.length() >= 2)
			selectedSearchIndex = selectedSearchIndex.substring(0,selectedSearchIndex.length()-1);
	}
	
	/*if(null != request.getAttribute("selectedIndex"))
		selectedIndex = (String)request.getAttribute("selectedIndex");*/

%>
<%@include file="CommonHeader.jsp" %>
<SCRIPT SRC="scripts/ajax.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
function callBack() {
	document.forms[0].action="<%= contextPath %>/RequestForm";
	document.forms[0].submit();

}

function callCancel() {
    document.forms[0].method="Get";
    document.forms[0].action="<%= contextPath %>/SelectRequest";
    document.forms[0].submit();
}
function changeSystemTypes() {
	//alert('changeSystemTypes');
	var selectedSysType = document.forms[0].selectSystemTypes.options[document.forms[0].selectSystemTypes.selectedIndex].value;
	if(selectedSysType == '-1'){
		alert("Please select the valid System Type");
		return;
	}
	//alert(selectedSysType); 
	
	xmlHttp = getXmlHttpObject();
    if (xmlHttp==null) {
        alert ("Your browser does not support AJAX!");
        return;
    } 
    var sysTypes = selectedSysType.split("#");
    var url="<%= contextPath %>/UserSearch";
    url=url+"?selSystemTypeId="+sysTypes[0];
    url=url+"&requestFrom=<%= AEConstants.GUI_PAGE_NAME_VALUE_SELECT_SYSTEM_PAGE%>";
    
    // On ready state call displayResults()
    xmlHttp.onreadystatechange = populateSystemSelect;
    xmlHttp.open("GET",url,true);
    xmlHttp.send(null);
}

function addAll() {
	//alert('alert all');
	var selSeachIndexes = document.forms[0].selectedSearchIndex.value;
	//alert('selSeachIndexes..'+selSeachIndexes);
	var selSearchSplit = selSeachIndexes.split(',');
	for(j=0;j<selSearchSplit.length;j++) {
		moveRows('searchSystemTable', 'selectedSystemTable',selSearchSplit[j],'Add');
	}
		
}

function removeAll() {
	
	var selIndexes = document.forms[0].selectedIndex.value;
	var selIndexSplit = selIndexes.split(',');
	for(k=0;k<selIndexSplit.length;k++) {
		moveRows('selectedSystemTable', 'searchSystemTable',selIndexSplit[k],'Remove');
	}	
}
function moveRows(sourceTable, destinationTable,selectedRow,opearation) {
	var sourceTableObj = document.getElementById(sourceTable);
	var destinationTableObj = document.getElementById(destinationTable);

	//Create an Array for collecting the deleted row objects - Array of type Table Row object
	var deletedRowsArray = new Array();
	var deleteRowCount = 0;
	//alert("selected row...."+selectedRow);
	
	//Get the Row Object for the Check box selected
	sourceRow = document.getElementById("row"+selectedRow);

	//Add a Row to the Destination Table to the end of teh table
	destRow = destinationTableObj.insertRow(destinationTableObj.rows.length);
	//Set the id attribute to the row to track it back
	destRow.setAttribute("id", "row"+selectedRow);
                             

	//Add Cells to the new Row object created
	/*var destCell0 = destRow.insertCell(0);
	destCell0.innerHTML = "<input type=Checkbox name="+destinationOption+" value="+roleChkObj.value+">";
                             destCell0.setAttribute("style", "border-bottom:1px solid #d9d9d9");*/

	var cell1 = destRow.insertCell(0);
	cell1.innerHTML = sourceRow.cells[0].innerHTML;
    cell1.setAttribute('style', 'border-bottom:1px solid #d9d9d9');
                             
	cell2 = destRow.insertCell(1);
	cell2.innerHTML = sourceRow.cells[1].innerHTML;
    cell2.setAttribute('style', 'border-bottom:1px solid #d9d9d9');

	cell3 = destRow.insertCell(2);
	cell3.innerHTML = sourceRow.cells[2].innerHTML;
    cell3.setAttribute('style', 'border-bottom:1px solid #d9d9d9');

	cell4 = destRow.insertCell(3);
	cell4.innerHTML = sourceRow.cells[3].innerHTML;
    cell4.setAttribute('style', 'border-bottom:1px solid #d9d9d9');

            
	cell5 = destRow.insertCell(4);
       //alert(opearation);
    if(opearation == 'Add') {
             	//alert("here add");
		cell5.innerHTML = "<a href=\"javascript:moveRows(\'selectedSystemTable\', \'searchSystemTable\',\'"+selectedRow+"\',\'Remove\')\" alt=\"Remove\">Remove<\/a>&nbsp;";
	} else {
		cell5.innerHTML = "<a href=\"javascript:moveRows(\'searchSystemTable\', \'selectedSystemTable\',\'"+selectedRow+"\',\'Add\')\" alt=\"Add\">Add<\/a>&nbsp;";
	}
	cell5.setAttribute('style', 'border-bottom:1px solid #d9d9d9');               
	
	var selIndexes = document.forms[0].selectedIndex.value;
	var selSearchIndexes = document.forms[0].selectedSearchIndex.value;
	//alert('before selSeachIndexes... '+selSearchIndexes);
	var newSelIndex = "";
	if(opearation == 'Add') {
		if(selIndexes != '')
			selIndexes += ","+selectedRow;
		else
			selIndexes = selectedRow;
		var splitSearchIndex =  selSearchIndexes.split(',');
		selSearchIndexes= '';
		var splitSearLen = 	splitSearchIndex.length;
		for(i=0;i<splitSearLen;i++) {
			if(splitSearchIndex[i] != selectedRow)
				selSearchIndexes += splitSearchIndex[i] +",";
		}
		if(selSearchIndexes.length >= 2)
			selSearchIndexes = selSearchIndexes.substring(0,selSearchIndexes.length-1);
	} else {
		var splitStr =  selIndexes.split(',');
		selIndexes = '';
		var splitStrLength = splitStr.length;
		//alert('splitStrLength...'+splitStrLength);
		for(i1=0;i1<splitStrLength;i1++) {
			//alert('splitStr...'+splitStr[i]);
			if(splitStr[i1] != selectedRow) {
				//alert('notmatched');
					selIndexes += splitStr[i1] + ",";
			}
			//alert('end of for...'+selIndexes);
		}
					
		if(selSearchIndexes != '')
			selSearchIndexes += ","+selectedRow;
		else
			selSearchIndexes = selectedRow;
			
		if(selIndexes.length >= 2 )
			selIndexes = selIndexes.substring(0,selIndexes.length-1);
	}
	
	document.forms[0].selectedIndex.value = selIndexes;
	document.forms[0].selectedSearchIndex.value = selSearchIndexes;
	//alert('after selectedSearchIndex...'+document.forms[0].selectedSearchIndex.value); 
	
	// Collect the Row Objects that are selected to delete from the Source table
	deletedRowsArray[deleteRowCount] = sourceRow;
	deleteRowCount++;

	// Delete rows form Source table
	if (deletedRowsArray.length > 0) {
		var rIndex = deletedRowsArray[0].sectionRowIndex;
		deleteRows(deletedRowsArray);
	}
}

function deleteRows(rowObjArray) {
	for (var i=0; i<rowObjArray.length; i++) {
		var rIndex = rowObjArray[i].sectionRowIndex;
		rowObjArray[i].parentNode.deleteRow(rIndex);
	}
}
function greyOutField(fieldObj) {
	fieldObj.style.backgroundColor = "#C0C0C0";
}

function callSearch() {
	document.forms[0].requestFrom.value= "<%= AEConstants.GUI_PAGE_NAME_VALUE_SELECT_SYSTEM_SEARCH_PAGE %>";
	document.forms[0].action="<%= contextPath %>/CollectRequestDetails";
	document.forms[0].submit();
}

function disableSearch() {

	//alert("disable search");
}
function enableSearch() {
	

}

function callSubmit() {
	document.forms[0].requestFrom.value= "<%= AEConstants.GUI_PAGE_NAME_VALUE_SELECT_SYSTEM_PAGE %>";
	document.forms[0].action="<%= contextPath %>/RequestSystem";
	document.forms[0].submit();
}

function changeAccessType(seleAccessType) {

	//alert("chagne access.."+seleAccessType);
	document.forms[0].selectedAccessType.value = seleAccessType;
	
}

function callSuggest() {
	//alert("suggest");
	document.forms[0].requestFrom.value= "<%= AEConstants.GUI_PAGE_NAME_VALUE_SELECT_SYSTEM_SUGGEST_PAGE %>";
	document.forms[0].action="<%= contextPath %>/CollectRequestDetails";
	document.forms[0].submit();
}

function callManual() {

	document.forms[0].requestFrom.value= "<%= AEConstants.GUI_PAGE_NAME_VALUE_SELECT_SYSTEM_MANUAL_SEARCH_PAGE %>";
	document.forms[0].action="<%= contextPath %>/CollectRequestDetails";
	document.forms[0].submit();
}

function callUser() {
	callUserSearch();
	//document.forms[0].requestFrom.value= "<%= AEConstants.GUI_PAGE_NAME_VALUE_SELECT_SYSTEM_USER_SEARCH_PAGE %>";
	//document.forms[0].action="<%= contextPath %>/CollectRequestDetails";
	//document.forms[0].submit();
}

function callSearch() {
	/*if(document.forms[0].selectSystemTypes.value == '-1') {
		alert("Please select a valid System Type");
		return;
	}*/
	document.forms[0].requestFrom.value= "<%= AEConstants.GUI_PAGE_NAME_VALUE_SELECT_SYSTEM_SEARCH_PAGE %>";
	document.forms[0].action="<%= contextPath %>/CollectRequestDetails";
	document.forms[0].submit();
}
function callUserSearch() {
    document.getElementById('dvRCnMon').style.visibility = 'visible' ;        
    document.getElementById('dvRCnMon').style.zIndex = '100';
    document.getElementById('dvsrch').style.visibility = 'visible';
    document.getElementById('dvsrch').style.zIndex = '100';
}

function callCancelSearch() {
	document.getElementById('dvRCnMon').style.visibility = 'hidden' ;        
    document.getElementById('dvsrch').style.visibility = 'hidden';
    document.forms[0].searchUserId.value = "";
    
}

function changeFilter(selFilter) {
	document.forms[0].selectedFilter.value = selFilter;
	document.forms[0].requestFrom.value= "<%= AEConstants.GUI_PAGE_NAME_VALUE_SELECT_SYSTEM_FILTER_PAGE %>";
	document.forms[0].action="<%= contextPath %>/CollectRequestDetails";
    document.forms[0].submit();
}

</SCRIPT>


		<div align="center">
			<span>   
				<table width="100% cellpadding="0" cellspacing="0">
					<tr><td align="left">
						<span style="padding-left: 8px;padding-right:8px;"><img src="images/43.gif"/></span>
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
		
        <div id="dvRCnMon" style="position:absolute; visibility:hidden; border-left:2px solid #d9d9d9;  solid #d9d9d9; border-top:2px solid #d9d9d9; top:200px; left:375px; width:400px; height:150px; background-color:White;">
        	<table width="100%" border="1px" rules="none" style="border:1 px silver; border-collapse:collapse">
              <tr>
                  <td>
                      <div id="dvsrch" style=" visibility:hidden; top:0px; left:0px; width:400px; height:150px; background-color:White;">
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
                                      <tr><td colspan="2">
                                              <input type="button" class="tblbtn" onclick="callUserSearch()" value="Search">
                                              &nbsp;&nbsp;<input type="button" class="tblbtn" 
                                                                 onclick="callCancelSearch()" value="Cancel">
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
            </table>   
        </div>

		<table style="width:100%;" cellpadding="0" cellspacing="" border="0" >
			<tr> <td>     
			<table width="100%" cellpadding="0" cellspacing="0" border="0" style="margin-top:1px;">
				<tr>
				 <td width="5" height="30" valign="top" bgcolor="#bebebe"><img src="images/Widget_topLeft.gif" width="5" height="30" /> </td>
	             <td class="widget_title_form"  height="30" width="100%" bgcolor="#bebebe">Select Systems</td>
	             <td width="5" height="30" valign="top" bgcolor="#bebebe"><img src="images/Widget_topRight.gif" width="5" height="30" /> </td>
				</tr>
			</table>
			</td></tr>
             <table cellpadding="2px" cellspacing="0" border="0" width="100%" style="width: 100%;border: 2px solid #d9d9d9">
                <tr><td>
				<table>
					<tr>
						<%
							String manualCheck = "checked";
							String suggestCheck = "";
							String userCheck = "";
							String disabled = "";
							if(null != selAccessType) {
								if(selAccessType.equals("1")) {
									manualCheck = "checked";
								} else if(selAccessType.equals("2")) {
									suggestCheck = "checked";
									disabled = "DISABLED";
								} else if(selAccessType.equals("3")) {
									userCheck = "checked";
								}
							}
						%>
						<td><input type="radio" name="accessType" value="1" onclick="javascript:enableSearch();changeAccessType('1');callManual()" <%=manualCheck%> > Manually search for Systems</td>
						<td><input type="radio" name="accessType" value="2" onclick="javascript:disableSearch();changeAccessType('2');callSuggest()" <%=suggestCheck%> > Suggest Systems</td>
						<td><input type="radio" name="accessType" value="3" onclick="javascript:changeAccessType('3');callUser()" <%=userCheck%> > Systems similar to another user</td>
					</tr>
				</table>
				</td></tr>
				
			   	
                
                <tr><td>
                <%
                	
                	String allFilterChecked = "checked";
                	String logicalFilterChecked = "";
                	String physicalFilterChecked = "";
                	String assetsFilterChecked = "";
                	if(null != selFilter && !("").equals(selFilter)){
                		if(("1").equals(selFilter))
                			logicalFilterChecked = "checked";
                		else if(("2").equals(selFilter))
                			physicalFilterChecked = "checked";
                		else if(("3").equals(selFilter))
                			assetsFilterChecked = "checked";
                		
                	}
                		
                %>
				<table cellpadding="8px" width="100%">
					<tr>
						<td width="100%">
						<b>Filter:&nbsp;</b>
						<input type="radio" name="filter" value="-1" <%=allFilterChecked %> onclick="javascript:changeFilter('-1');" <%=disabled %> >All
						<input type="radio" name="filter" value="1" <%=logicalFilterChecked %> onclick="javascript:changeFilter('1');" <%=disabled %> >Logical/IT Access
						<input type="radio" name="filter" value="2" <%=physicalFilterChecked %> onclick="javascript:changeFilter('2');" <%=disabled %> >Physical Access
						<input type="radio" name="filter" value="3" <%=assetsFilterChecked %> onclick="javascript:changeFilter('3');" <%=disabled %> >Assets</td>
					</tr>
				</table>
				</td></tr>
				
			   	<!-- <tr>
                	<tr><td colspan="3"><br></td></tr>
			   	</tr>
			   	<tr>
		   		<td></td>
		   		</tr>-->
				<tr><td colspan="2">
					<table border="0" width="100%" cellpadding="8" cellspacing="0" style="margin-top:1px;" >
						<tr>
							<%
							
							%>
							<td style="width :15%" align="right"><b>System Type </b></td>
							<td style="width :30%" align="left">
							<select name="selectSystemTypes" <%=disabled %> >     
										<option value="-1">All</option>		
								<%
									
									for (Long sysTypeId : systemTypes.keySet()) {
										String sysTypeName = systemTypes.get(sysTypeId);
										
								%>
										<option value="<%= sysTypeId %>"><%=  sysTypeName %></option>
								<%
									}
								%>    
							</select> 
							</td>
							<td width="5%" ></td>
							<td style="width :20%" align="right"><b>System Category</b></td>
							<td style="width :20%" align="left">
							<select name="selectSystemCategory" <%=disabled %> >     
								<option value="-1">All</option>
								<option value="1">Produciton</option>
								<option value="2">Development</option>
								<option value="3">QA</option>
								<option value="4">Others</option>
							</select>
							</td>
							<td style="padding-left: 10px"><input type="button" value="Search" class="tblbtn" style="width :60px" onclick="callSearch()" <%=disabled %> /></td>
						</tr>
					</table>
				 </td></tr>
				 
				<tr>
                    <td colspan="5" style="padding:0px 3px 3px 10px;"><b>Search Result</b></td> 
			   	</tr>
				<tr><td colspan="4">
				<div width="100%" style="height:120px; overflow-y:auto; overflow-x:none; border-bottom:2px solid #d9d9d9;"> <!-- border-left:1px solid #d9d9d9;   -->
					<table border="0" width="100%" cellpadding="4" cellspacing="0" style="margin-top:1px;" id="searchSystemTable">
                         <tr class="widget_subHeader" bgcolor="#d9d9d9" style="padding:3px 0 3px 0; border-left:1px solid #d9d9d9; border-right:1px solid #d9d9d9;">
                                 <td style ="width :15%"><b>System </b></td>
                                 <td style ="width :15%"><b>Type</b></td>
                                 <td style="width :25%"><b>Name</b></td>
                                 <td style="width :35%"><b>Description</b></td>
                                 <td style="width :8%"><b>Action</b></td>
                                 <td style="width :2%"><b><a href="javascript:addAll()" alt="AddAll">++</a></b></td>
                         </tr>
						<%
						if (searchSysConn != null) {
							int resultLength = searchSysConn.size(); 
							for (int selCount = 0 ; selCount < resultLength ; selCount++) {
								SubmitSystemConnector sysConn = (SubmitSystemConnector) searchSysConn.get(selCount);
								if(!matchedIndex.containsKey(sysConn.getId().toString())) {
						%>
								<tr id="row<%= sysConn.getId() %>">
									
									<td style="border-bottom:1px solid #d9d9d9"><%= sysConn.getSystem() %>&nbsp;</td>
									<td style="border-bottom:1px solid #d9d9d9"><%= sysConn.getSystemType() %>&nbsp;</td>
									<td style="border-bottom:1px solid #d9d9d9"><%= sysConn.getConnectorName() %>&nbsp;</td>
									<td style="border-bottom:1px solid #d9d9d9"><%= sysConn.getConnectorDescription() %>&nbsp;</td>
									<td style="border-bottom:1px solid #d9d9d9"><a href="javascript:moveRows('searchSystemTable', 'selectedSystemTable','<%=sysConn.getId()%>','Add')" alt="Add">Add</a>&nbsp;</td>
								</tr>
						<%
								}
							}
						}
						%>                                                
					</table>
				</div>
				<tr><td colspan="3"><br></td></tr>
				<tr>
                    <td colspan="5" style="padding:0px 3px 3px 10px;"><b>System Cart</b></td> 
			   	</tr>
				<tr><td colspan="4">
				<div width="100%" style="height:120px; overflow-y:auto; overflow-x:none; border-bottom:2px solid #d9d9d9;"> <!-- border-left:1px solid #d9d9d9;   -->
					<table border="0" width="100%" cellpadding="4" cellspacing="0" style="margin-top:1px;" id="selectedSystemTable">
                         <tr class="widget_subHeader" bgcolor="#d9d9d9" style="padding:3px 0 3px 0; border-left:1px solid #d9d9d9; border-right:1px solid #d9d9d9;">
                                 <td style ="width :15%"><b>System </b></td>
                                 <td style ="width :15%"><b>Type</b></td>
                                 <td style="width :25%"><b>Name</b></td>
                                 <td style="width :35%"><b>Description</b></td>
                                 <td style="width :7%"><b>Action</b></td>
                                 <td style="width :3%"><b><a href="javascript:removeAll()" alt="RemoveAll">--</a></b></td>
                         </tr>
						<%
						if (resultSysConn != null) {
							int resultLength = resultSysConn.size();
							for (int selCount = 0 ; selCount < resultLength ; selCount++) {
								SubmitSystemConnector resConn = (SubmitSystemConnector) resultSysConn.get(selCount);
						%>
								<tr  id="row<%= resConn.getId() %>">
									
									<td style="border-bottom:1px solid #d9d9d9"><%= resConn.getSystem() %>&nbsp;</td>
									<td style="border-bottom:1px solid #d9d9d9"><%= resConn.getSystemType() %>&nbsp;</td>
									<td style="border-bottom:1px solid #d9d9d9"><%= resConn.getConnectorName() %>&nbsp;</td>
									<td style="border-bottom:1px solid #d9d9d9"><%= resConn.getConnectorDescription() %>&nbsp;</td>
									<td ><a href="javascript:moveRows('selectedSystemTable', 'searchSystemTable','<%=resConn.getId()%>','Remove')" alt="Remove">Remove</a>&nbsp;</td>
								</tr>
						<%
							}
						}
						%>                          
					</table>
				</div>
				
				</table>
				</table>
				
		        <table cellpadding="0" cellspacing="0" border="0" width="100%">
		            <tr height="25px" valign="center"><td align="left" bgcolor="#d9d9d9"
		                        style="border-left: 1px solid #d9d9d9; border-right: 1px solid #d9d9d9;">
			  			    <input type="HIDDEN" name="requestFrom" value=""> 
							<input type="HIDDEN" name="selectedSystemIndex">
							<input type="HIDDEN" name="selectedIndex" value="<%=selectedIndex %>">
							<input type="HIDDEN" name="selectedSearchIndex" value="<%=selectedSearchIndex %>">
							
							<input type="HIDDEN" name="selectedAccessType">
							<input type="HIDDEN" name="selectedFilter">
							
							<input type="button" class="tblbtn" name="Back" value="Back" onclick="javascript:callBack()">
							<input type="button" class="tblbtn" name="Next" value="Next" onclick="javascript:callSubmit()">
							<input type="button" class="tblbtn" name="Cancel" value="Cancel" onclick="javascript:callCancel()" >
		                </td>
		            </tr>
		        </table>				
			    </td></tr>
			  </table> 
        </table>
        </td>
        </tr>
        </table>
	</FORM>
</BODY>
<%@page import="com.sae.userselfservice.service.SubmitSystemConnector;"%>
</HTML>
