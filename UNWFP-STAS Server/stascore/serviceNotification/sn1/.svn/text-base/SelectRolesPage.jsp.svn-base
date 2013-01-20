<%@page import="java.util.*" %>
<%@page import="com.sae.userselfservice.dao.SearchResults" %>
<%@page import="com.sae.userselfservice.constants.AEConstants" %>
<%@page import="com.sae.userselfservice.service.SubmitSystemConnector" %>

<% 
	System.out.println("Select Roles Pages 1");
	Object searchResultArray[] = (Object[]) session.getAttribute(AEConstants.GUI_SESSION_KEY_SEARCH_RESULT_ROLE_ARRAY);
	System.out.println("Select Roles Pages 2");
	ArrayList selectedRoles = (ArrayList) session.getAttribute(AEConstants.GUI_SESSION_KEY_SELECTED_ROLE_LIST);
	List<SubmitSystemConnector> listSysConnectors = (List<SubmitSystemConnector>)session.getAttribute(AEConstants.GUI_SESSION_KEY_SELECTED_SYSTEMS);
	
	String selectedIndex = "";
	Map<String,String> matchedIndex = new HashMap<String,String>();
	if(null != selectedRoles && selectedRoles.size() > 0) {
		for(Object objItem:selectedRoles){
			SearchResults selRoleItem = (SearchResults)objItem;
			selectedIndex += selRoleItem.getId()+",";
			matchedIndex.put(selRoleItem.getId()+"",selRoleItem.getId()+"");
		}
		if(selectedIndex.length() >= 2)
			selectedIndex = selectedIndex.substring(0,selectedIndex.length()-1);
	}
	
	String selectedSearchIndex = "";
	if (searchResultArray != null) {
		int resultLength = searchResultArray.length; 
		for (int selCount = 0 ; selCount < resultLength ; selCount++) {
			SearchResults searchResulstObj = (SearchResults) searchResultArray[selCount];
			
			if(!matchedIndex.containsKey(searchResulstObj.getId()+"")) {
				selectedSearchIndex += searchResulstObj.getId() + ",";
			}
		}
		if(selectedSearchIndex.length() >= 2)
			selectedSearchIndex = selectedSearchIndex.substring(0,selectedSearchIndex.length()-1);
	}
	
	String selAccessType = (String)session.getAttribute(AEConstants.GUI_SESSION_KEY_SELECTED_ROLE_ACCESS_TYPE);
	System.out.println("selAccessType...."+selAccessType);
	

%>
<%@include file="CommonHeader.jsp" %>
<SCRIPT LANGUAGE="JavaScript">
function callBack() {
	document.forms[0].action="<%= contextPath %>/CollectRequestDetails";
	document.forms[0].submit();

}

function callCancel() {
    document.forms[0].method="Get";
    document.forms[0].action="<%= contextPath %>/SelectRequest";
    document.forms[0].submit();
}

function callSubmit() {

	// Get Check box object of the selected roles
	/*var selectRoleChkObj = document.forms[0].selectResultOption;
	var selectedIndex = "";
	if (selectRoleChkObj != null) {
		if (selectRoleChkObj.length == null) {
			selectedIndex = selectRoleChkObj.value;
		}
		for (rowCount = 0 ; rowCount < selectRoleChkObj.length ; rowCount++) {
			selectedIndex += selectRoleChkObj[rowCount].value + ",";
		}
	}

	document.forms[0].selectedIndex.value= selectedIndex;*/
	//alert(document.forms[0].selectedIndex.value);

	document.forms[0].action="<%= contextPath %>/RequestReview";
	document.forms[0].submit();
}

function addAll() {
	//alert('alert all');
	var selSearchIndexes = document.forms[0].selectedSearchIndex.value;
	//alert('selSeachIndexes..'+selSearchIndexes);
	var selSearchSplit = selSearchIndexes.split(',');
	for(j=0;j<selSearchSplit.length;j++) {
		moveRows('searchResultTable', 'selectedRolesTable',selSearchSplit[j],'Add');
	}
		
}

function removeAll() {
	//alert('remove all');
	var selIndexes = document.forms[0].selectedIndex.value;
	var selIndexSplit = selIndexes.split(',');
	for(k=0;k<selIndexSplit.length;k++) {
		moveRows('selectedRolesTable', 'searchResultTable',selIndexSplit[k],'Remove');
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
    cell1.setAttribute("style", "border-bottom:1px solid #d9d9d9");
	
	cell2 = destRow.insertCell(1);
	cell2.innerHTML = sourceRow.cells[1].innerHTML;
    cell2.setAttribute("style", "border-bottom:1px solid #d9d9d9");
	
	cell3 = destRow.insertCell(2);
	cell3.innerHTML = sourceRow.cells[2].innerHTML;
    cell3.setAttribute("style", "border-bottom:1px solid #d9d9d9");

	cell4 = destRow.insertCell(3);
	cell4.innerHTML = sourceRow.cells[3].innerHTML;
    cell4.setAttribute("style", "border-bottom:1px solid #d9d9d9");

	/*cell5 = destRow.insertCell(4);
	cell5.innerHTML = sourceRow.cells[4].innerHTML;
    cell5.setAttribute("style", "border-bottom:1px solid #d9d9d9");*/
             
    cell6 = destRow.insertCell(4);
    //alert(opearation);
    if(opearation == 'Add') {
    	//alert("here add");
		cell6.innerHTML = "<a href=\"javascript:moveRows(\'selectedRolesTable\', \'searchResultTable\',\'"+selectedRow+"\',\'Remove\')\" alt=\"Remove\">Remove<\/a>&nbsp;";
	} else {
		cell6.innerHTML = "<a href=\"javascript:moveRows(\'searchResultTable\', \'selectedRolesTable\',\'"+selectedRow+"\',\'Add\')\" alt=\"Add\">Add<\/a>&nbsp;";
	}
	cell6.setAttribute("style", "border-bottom:1px solid #d9d9d9");               
	
	var selIndexes = document.forms[0].selectedIndex.value;
	var selSearchIndexes = document.forms[0].selectedSearchIndex.value;
	var newSelIndex = "";
	//alert("selected Index..."+document.forms[0].selectedIndex.value);	
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
		for(i=0;i<splitStrLength;i++) {
			//alert('splitStr...'+splitStr[i]);
			if(splitStr[i] != selectedRow) {
				//alert('notmatched');
					selIndexes += splitStr[i] + ",";
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
	//alert("changed value.."+document.forms[0].selectedIndex.value);
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
function disableSearch() {
	if( null != document.forms[0].selectSystem.value)
		document.forms[0].selectSystem.value = "";
	if( null != document.forms[0].floorZoneName.value)
		document.forms[0].floorZoneName.value = "";
	if( null != document.forms[0].roleName.value)
		document.forms[0].roleName.value = "";
	if( null != document.forms[0].description.value)
		document.forms[0].description.value = "";
	document.forms[0].selectSystem.disabled = true;
	document.forms[0].floorZoneName.disabled = true;
	document.forms[0].roleName.disabled = true;
	document.forms[0].description.disabled = true;

	greyOutField(document.forms[0].selectSystem);
	greyOutField(document.forms[0].floorZoneName);
	greyOutField(document.forms[0].roleName);
	greyOutField(document.forms[0].description);

}
function enableSearch() {
	document.forms[0].selectSystem.disabled = false;
	document.forms[0].floorZoneName.disabled = false;
	document.forms[0].roleName.disabled = false;
	document.forms[0].description.disabled = false;

	document.forms[0].selectSystem.style.backgroundColor = "";
	document.forms[0].floorZoneName.style.backgroundColor = "";
	document.forms[0].roleName.style.backgroundColor = "";
	document.forms[0].description.style.backgroundColor = "";

}
function showSearchTable(tableID)
{
      if (document.getElementById(tableID).style.display == 'none')
      {
            document.getElementById(tableID).style.display = 'block';
      }
}
function hideSearchTable(tableID)
{
	document.getElementById(tableID).style.display = 'none';
}
function callSearch() {
	document.forms[0].requestFrom.value= "<%= AEConstants.GUI_PAGE_NAME_VALUE_SELECT_ROLES_SEARCH_PAGE %>";
	document.forms[0].action="<%= contextPath %>/RequestSystem";
	document.forms[0].submit();
}

function callSuggest() {
	document.forms[0].requestFrom.value= "<%= AEConstants.GUI_PAGE_NAME_VALUE_SELECT_ROLES_SUGGEST_PAGE%>";
	document.forms[0].action="<%= contextPath %>/RequestSystem";
	document.forms[0].submit();
}
function callManual() {
	document.forms[0].requestFrom.value= "<%= AEConstants.GUI_PAGE_NAME_VALUE_SELECT_ROLES_SEARCH_PAGE%>";
	document.forms[0].action="<%= contextPath %>/RequestSystem";
	document.forms[0].submit();
}

function showFacilityWindow(rowID) {
	//alert("Show Facility   "  + rowID);
	document.getElementById('facilityList'+rowID).style.visibility = 'visible' ;        
	document.getElementById('facilityList'+rowID).style.zIndex = '100';
}
function hideFacilitiesWindow(rowID) {
	document.getElementById('facilityList'+rowID).style.visibility = 'hidden' ;
}
function hideOwnersWindow(rowID) {
	document.getElementById('ownerList'+rowID).style.visibility = 'hidden' ;
}
function showOwnerWindow (rowID) {
	document.getElementById('ownerList'+rowID).style.visibility = 'visible' ;        
	document.getElementById('ownerList'+rowID).style.zIndex = '100';

}

function changeAccessType(seleAccessType) {
	//alert("chagne access.."+seleAccessType);
	document.forms[0].selectedAccessType.value = seleAccessType;
	
}

function showSytemType() {
	//alert('show system type');
	var selectSystem = document.getElementById('selectSystem');
	var selOptLen = selectSystem.options.length;
	//alert('selOptLen..'+selOptLen);
	var selectedVal = document.getElementById('selectSystem').value;
	//alert(selectedVal);
	for(i=0;i<selOptLen;i++) {
		optVal = selectSystem.options[i].value;
		if(optVal != -1){
		if(optVal == selectedVal)
			document.getElementById('sysType'+optVal).style.visibility = 'visible' ;
		else
			document.getElementById('sysType'+optVal).style.visibility = 'hidden' ;
		}
	}
	
	
	
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

		<table style="width:100%;" cellpadding="0" cellspacing="" border="0" >
			<tr> <td>     
			<table width="100%" cellpadding="0" cellspacing="0" border="0" style="margin-top:1px;">
				<tr>
				 <td width="5" height="30" valign="top" bgcolor="#bebebe"><img src="images/Widget_topLeft.gif" width="5" height="30" /> </td>
	             <td class="widget_title_form"  height="30" width="100%" bgcolor="#bebebe">Select Roles</td>
	             <td width="5" height="30" valign="top" bgcolor="#bebebe"><img src="images/Widget_topRight.gif" width="5" height="30" /> </td>
				</tr>
			</table>
			</td></tr>
             <table cellpadding="2px" cellspacing="0" border="0" width="100%" style="width: 100%;border: 2px solid #d9d9d9">
             	<%
             		String manualCheck = "checked";
             		String suggestCheck = "";
             		String userCheck = "";
             		String disabled = "";
             		if(selAccessType != null) {
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
                <tr><td>
				<table>
					<tr>
						<td><input type="radio" name="accessType" onclick="javascript:changeAccessType('1');enableSearch();callManual()"; <%= manualCheck %>> Manually search for Roles</td>
						<td><input type="radio" name="accessType" <%= suggestCheck %>
                                                        onclick="javascript:disableSearch();changeAccessType('2');callSuggest();"> Suggest Roles</td>
						<td><input type="radio" name="accessType" onclick="javascript:changeAccessType('3')" <%= userCheck %>> Roles similar to another user</td>
					</tr>
				</table>
				</td></tr>
				
			   	<tr>
                                     <td colspan="3" style="padding:3px 3px 3px 10px;"><b>Search</b></td> 
			   	</tr>
			   	
			   	<tr>
		   		<td></td>
		   		</tr>
				
				<tr><td colspan="3">
					<table border="0" width="100%" cellpadding="2" cellspacing="0" style="margin-top:1px;" >
						<tr>
							<td style="padding-left: 10px" width="15%"><b>System </b></td>
							<td width="20%">
								<select name="selectSystem"  <%=disabled %> style="width:150px"  onchange="javascript:showSytemType()">     
									<option value="-1">Select System</option>
									<%
									for (SubmitSystemConnector sysConn : listSysConnectors) {
										
									
									%>
									<option value="<%=sysConn.getId() %>"><%= sysConn.getConnectorName() %></option>
									<%
									}
									%>
								</select>
							</td>
							<td width="30%" align="left">
							<%
								for (SubmitSystemConnector sysConn : listSysConnectors) {
							%>
								<div id="sysType<%= sysConn.getId() %>" align="left" style="position:absolute;top:235px; left:500px; width:200px; height:120px; visibility:hidden;">
									<font size="2px"><%= sysConn.getSystemType() %></font>
								</div>
							<%
								}
							%>
							</td>

							<td width="10%"><b>Facility</b></td>
							<td width="25%"><input type="text" name="floorZoneName" style="width:150px" <%=disabled %>  /></td>
						</tr>
						<tr>
							<td style="padding-left: 10px" width="15%"><b>Role Name </b></td>
							<td  width="20%"><input type="text" name="roleName" style="width:150px" <%=disabled %>  /></td>
							<td width="30%">&nbsp;</td>
							<td width="10%"><b>Keywords </b></td>
							<td width="25"><input type="text" name="description" style="width:150px" <%=disabled %>  ></td>
						</tr>
						<tr style="text-align :left">
							<td style="padding-left: 10px"><input type="button" value="Search" class="tblbtn" style="width :60px" onclick="callSearch()" <%=disabled %>  /></td>
						</tr>
					</table>
				 </td></tr>
				 <tr><td colspan="3"><br></td></tr>
				 <tr>
                    <td colspan="3" style="padding:0px 3px 3px 10px;"><b>Results</b></td> 
			   	</tr>
			
				<tr><td colspan="4">
					<div width="100%" style="height:120px; overflow-y:auto; overflow-x:none; border-bottom:2px solid #d9d9d9;"><!-- border-left:1px solid #d9d9d9;  -->
						<table border="0" width="100%" cellpadding="4" cellspacing="0" style="margin-top:1px;" id="searchResultTable">
                                <tr class="widget_subHeader" bgcolor="#d9d9d9" style="padding:3px 0 3px 0; border-left:1px solid #d9d9d9; border-right:1px solid #d9d9d9;">
                                        
                                        <td style ="width :32%"><b>Role</b></td>
                                        <td style="width :33%"><b>Description</b></td>
                                        <td style="width :20%"><b>System</b></td>
                                        <td style="width :8%"><b>Owner</b></td>
                                        <td style="width :5%"><b>Action</b></td>
                                        <td style="width :2%"><b><a href="javascript:addAll()" alt="AddAll">++</a></b></td>
                                </tr>
						<%
							System.out.println("Select Roles Pages 3");
							if (searchResultArray != null) {
								// Loop thru the Search Results Array 
								int searchResultsArrayLength = searchResultArray.length;
								for (int rowCount = 0 ; rowCount < searchResultsArrayLength ; rowCount++) {
									SearchResults searchResulstObj = (SearchResults) searchResultArray[rowCount]; 
						
									System.out.println("Select Roles Pages 4 "+ searchResulstObj.getId());
						
									boolean selectedFlag = false;
									if (selectedRoles != null) {
										// Loop thru the Selected Search Results Obj to find the match 
										for (int selCount = 0 ; selCount < selectedRoles.size() ; selCount++) {
											SearchResults selectedObj = (SearchResults) selectedRoles.get(selCount);
											if (searchResulstObj.compareTo(selectedObj) == 0) {
												selectedFlag = true;
												break;
											}
										}
									}
									System.out.println("Select Roles Pages 5 "+ searchResulstObj.getId());
						
									/*List facilities = searchResulstObj.getFacilities();
									String defaultFacility = "";
									String allFacilities = "";
									if (facilities != null && facilities.size() > 0) {
										for (int facCount = 0 ; facCount < facilities.size() ; facCount++) {
											if (facCount == 0 ) {
												defaultFacility = (String) facilities.get(facCount);
											}
											allFacilities += "<tr><td>" +(String) facilities.get(facCount) + "</td></tr>"; 
										}
									}*/
						
									List owners = searchResulstObj.getOwners();
									String defaultOwner = "";
									String allOwners = "";
									System.out.println("*****Owner..."+owners);
									if (owners != null && owners.size() > 0) {
										System.out.println("*****OwnerCount..."+owners.size());
										for (int ownCount = 0 ; ownCount < owners.size() ; ownCount++) {
											if (ownCount == 0 ) {
												defaultOwner = (String) owners.get(ownCount);
											}
											allOwners += "<tr><td>" +(String) owners.get(ownCount) + "</td></tr>"; 
										}
									}
						
									if (!selectedFlag) {
						%>			
										<tr id="row<%= searchResulstObj.getId() %>">
											<td style="border-bottom:1px solid #d9d9d9"><%= searchResulstObj.getRoleName() %>&nbsp;</td>
											<td style="border-bottom:1px solid #d9d9d9"><%= searchResulstObj.getDescription() %>&nbsp;</td>
											<td style="border-bottom:1px solid #d9d9d9"><%= searchResulstObj.getSystemName() %>

											</td>
											<td style="border-bottom:1px solid #d9d9d9"><%= defaultOwner %>
												<% if ( null != owners && owners.size() > 1) { %>
												<a href="javascript:showOwnerWindow(<%= searchResulstObj.getId() %>)" >...</a>
												<% } %>&nbsp;
											</td>
											<td style="border-bottom:1px solid #d9d9d9"><a href="javascript:moveRows('searchResultTable', 'selectedRolesTable','<%=searchResulstObj.getId()%>','Add')" alt="Add">Add</a>&nbsp;</td>
											<td style="border-bottom:1px solid #d9d9d9">
												<div id="ownerList<%= searchResulstObj.getId() %>" style="position:absolute; visibility:hidden; border:2px solid #d9d9d9;  solid #d9d9d9; border-top:2px solid #d9d9d9; top:300px; left:875px; width:200px; height:120px; background-color:White;">
													<table>
														   <tr >
															   <td class="widget_subHeader" bgcolor="#d9d9d9" style="width:200px; border-left:1px solid #d9d9d9; border-right:1px solid #d9d9d9;" ><b><font size="2px">Owners</font></b></td> 
														   </tr>
															<%= allOwners %>
															<tr><td>
															<input type="button" class="tblbtn" name="Cancel" value="Close" onClick="hideOwnersWindow(<%= searchResulstObj.getId() %>)">
															</td></tr>
													</table>
												</div>
						
											</td>
											
										</tr> 		
										
						<%
									}
								}
							} else {
						%>
									<tr><td>&nbsp;</td></tr>
						<%
							}
						%>		
					</table>
					</div>
					<!-- <table>
							<tr style ="text-align :left;height:30px; vertical-align :bottom">
								<td colspan="4"><input type="button" name="Add" value="Add" class="tblbtn" onclick="javascript:moveRows('searchResultTable', 'selectedRolesTable', 'searchResultOption', 'selectResultOption')" style="width :60px"></td>
							</tr> 
					</table>  -->
				</td></tr>
				<tr><td colspan="3"><br></td></tr>
				<tr>
                                     <td colspan="3" style="padding:0px 3px 3px 10px;"><b>Role Cart</b></td> 
			   	</tr>
				<tr><td colspan="4">
				<div width="100%" style="height:120px; overflow-y:auto; overflow-x:none; border-bottom:2px solid #d9d9d9;"> <!-- border-left:1px solid #d9d9d9;   -->
					<table border="0" width="100%" cellpadding="4" cellspacing="0" style="margin-top:1px;" id="selectedRolesTable">
                        <tr class="widget_subHeader" bgcolor="#d9d9d9" style="padding:3px 0 3px 0; border-left:1px solid #d9d9d9; border-right:1px solid #d9d9d9;">
                                
                                <td style ="width :32%"><b>Role</b></td>
                                <td style="width :33%"><b>Description</b></td>
                                <td style="width :19%"><b>System</b></td>
                                <td style="width :7%"><b>Owner</b></td>
                                <td style="width :5%"><b>Action</b></td>
                                <td style="width :4%"><b><a href="javascript:removeAll()" alt="RemoveAll">--</a></b></td>
                        </tr>
		
						<%
								if (selectedRoles != null) {
									for (int selCount = 0 ; selCount < selectedRoles.size() ; selCount++) {
										SearchResults selectedObj = (SearchResults) selectedRoles.get(selCount);
										/*List facilities = selectedObj.getFacilities();
										String defaultFacility = "";
										String allFacilities = "";
										if (facilities != null && facilities.size() > 0) {
											for (int facCount = 0 ; facCount < facilities.size() ; facCount++) {
												if (facCount == 0 ) {
													defaultFacility = (String) facilities.get(facCount);
												}
												allFacilities += "<tr><td>" +(String) facilities.get(facCount) + "</td></tr>"; 
											}
										}*/
							
										List owners = selectedObj.getOwners();
										String defaultOwner = "";
										String allOwners = "";
										System.out.println("*****Owner..."+owners);
										if (owners != null && owners.size() > 0) {
											System.out.println("*****OwnerCount..."+owners.size());
											for (int ownCount = 0 ; ownCount < owners.size() ; ownCount++) {
												if (ownCount == 0 ) {
													defaultOwner = (String) owners.get(ownCount);
												}
												allOwners += "<tr><td>" +(String) owners.get(ownCount) + "</td></tr>"; 
											}
										}
						%>
										<tr id="row<%= selectedObj.getId() %>">
											
											<td style="border-bottom:1px solid #d9d9d9"><%= selectedObj.getRoleName() %>&nbsp;</td>
											<td style="border-bottom:1px solid #d9d9d9"><%= selectedObj.getDescription() %>&nbsp;</td>
											<td style="border-bottom:1px solid #d9d9d9"><%= selectedObj.getSystemName() %>
											</td>
											<td style="border-bottom:1px solid #d9d9d9"><%= defaultOwner %>
												<% if ( null != owners && owners.size() > 1) { %>
												<a href="javascript:showOwnerWindow(<%= selectedObj.getId() %>)" >...</a>
												<% } %>&nbsp;
											</td>
											<td style="border-bottom:1px solid #d9d9d9"><a href="javascript:moveRows('selectedRolesTable', 'searchResultTable','<%=selectedObj.getId()%>','Remove')" alt="Remove">Remove</a>&nbsp;</td>
										</tr>
						<%
									}
								}
						%>
					</table>
				</div>
				<!-- <table>
						<tr style ="text-align :left;height:30px; vertical-align :bottom">
							<td colspan="4"><input type="button" name="Remove" class="tblbtn" value="Remove" onclick="javascript:moveRows('selectedRolesTable', 'searchResultTable', 'selectResultOption', 'searchResultOption')" style="width :80px"/>
							</td>
						</tr> 
				</table> -->
				</table>
				
		        <table cellpadding="0" cellspacing="0" border="0" width="100%">
		            <tr height="25px" valign="center"><td align="left" bgcolor="#d9d9d9"
		                        style="border-left: 1px solid #d9d9d9; border-right: 1px solid #d9d9d9;">
			  			    <input type="HIDDEN" name="requestFrom" value=""> 
							<input type="HIDDEN" name="selectedIndex" value="<%=selectedIndex%>" >
							<input type="HIDDEN" name="selectedSearchIndex" value="<%=selectedSearchIndex %>">
							<input type="HIDDEN" name="selectedAccessType">
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

</HTML>
