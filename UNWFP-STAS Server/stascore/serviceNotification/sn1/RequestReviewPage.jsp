<%@page import="java.util.*" %>
<%@page import="com.sae.userselfservice.dao.SAEMRequestAttribute" %>
<%@page import="com.sae.userselfservice.dao.SearchResults" %>
<%@page import="com.sae.userselfservice.constants.AEConstants"%>
<%@page import="com.sae.common.constants.RequestConstants"%>
<%@page import="com.sae.userselfservice.service.SubmitSystemConnector"%>

<%
	List requestAttrs = (List) session.getAttribute(AEConstants.GUI_SESSION_KEY_REQ_ATTR_ARRAY);

	ArrayList selectedRoles = (ArrayList) session.getAttribute(AEConstants.GUI_SESSION_KEY_SELECTED_ROLE_LIST);
	System.out.println("Selected Roles --> Request Review Page   " + selectedRoles);

	String selectedCategory = (String) session.getAttribute(AEConstants.GUI_SESSION_KEY_SELECTED_REQUEST_CATEGORY);
	List<SubmitSystemConnector> selSysConnectors = (List<SubmitSystemConnector>)session.getAttribute(AEConstants.GUI_SESSION_KEY_SELECTED_SYSTEMS);
	String screenName = null;
	boolean isBackVisible = false;
%>
<%@include file="CommonHeader.jsp" %>
<SCRIPT LANGUAGE="JavaScript">
function callEditScreen() {
	document.forms[0].action="<%= contextPath %>/RequestFormEdit";
	document.forms[0].submit();
}
function callBack() {
	document.forms[0].action="<%= contextPath %>/RequestSystem";
	document.forms[0].submit();
}
function callSubmit() {
	document.forms[0].action="<%= contextPath %>/SubmitRequest";
	document.forms[0].submit();

}

function callCancel() {
    document.forms[0].method="Get";
    document.forms[0].action="<%= contextPath %>/SelectRequest";
    document.forms[0].submit();
}
</SCRIPT>
<%
	if (selectedCategory.equals(RequestConstants.REQ_CASE_NEW_HIRE) 
        		|| selectedCategory.equals(RequestConstants.REQ_CASE_CHANGE_OF_ACCESS)) {
	//screenName = "Screen 3 --> <b>Screen 4</b>";
	isBackVisible = true;
		} else {
	//screenName = "<b>Screen 3</b>";
		}
%>
			<!--<tr>
               <td style="text-align :left " colspan="2"><font color="Blue">Screen 1  Screen 2 <%= screenName %></font></td>
			</tr>-->
			
		<div align="center">
			<span>   
				<table width="100% cellpadding="0" cellspacing="0">
					<tr><td align="left">
						<span style="padding-left: 8px;padding-right:8px;"><img src="images/44.gif"/></span>
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
     
			<table width="100%" cellpadding="0" cellspacing="0" border="0" style="margin-top:1px;">
				<tr>
					 <td width="5" height="30" valign="top" bgcolor="#bebebe"><img src="images/Widget_topLeft.gif" width="5" height="30" /> </td>
	            <td class="widget_title_form"  height="30" width="100%" bgcolor="#bebebe">Request Review</td>
	            <td width="5" height="30" valign="top" bgcolor="#bebebe"><img src="images/Widget_topRight.gif" width="5" height="30" /> </td>
				
				</tr>
			</table>			

			<table cellpadding="2" cellspacing="2" border="0" width="100%" style="width: 100%;border: 2px solid #d9d9d9">			

            	<tr><td colspan="2">
				<!-- STANDARD AND CUSTOM ATTRIBUTES ARE LISTED HERE -->
			    <!-- <table border="0" width="100%" cellpadding="3" cellspacing="0" style="margin-top:1px;" >-->	
			    <table width="100%" cellpadding="2" cellspacing="2">		            
				<%
		        	for (int rowCount = 0 ; rowCount < requestAttrs.size() ; rowCount++) {
						SAEMRequestAttribute reqAttribute = (SAEMRequestAttribute) requestAttrs.get(rowCount);
						List valueList = reqAttribute.getValueList();
						String listedValues = "";
						String delimeter = "";
						for (int valueCount = 0 ; valueCount < valueList.size() ; valueCount++) {
							listedValues += delimeter + (String) valueList.get(valueCount);
							delimeter = ",";
						}
				%>
					<!-- if (rowCount % 4 == 0) {
								out.println("<tr class=\"widget_rowOdd\">");
							} else if (rowCount % 4 == 2) {
								out.println("<tr class=\"widget_rowEven\">");
							} -->
								<td  style="width:23%; text-align: right"><b><%=reqAttribute.getLabelName()%> </b></td>
								<td style="width:3%; text-align: right"></td>
								<td style="width:24%"><%=listedValues%></td>
					<%
						if (rowCount % 2 == 1) {
								out.println("</tr>");
							}
					}
					%>
				</table>
				</td></tr>

            	<%
            		if(null != selSysConnectors && selSysConnectors.size() > 0) {
            	%>
				<!-- SELECTED ROLES ARE LISTED HERE -->
				<tr><td>
					<table width="100%" cellpadding="0" cellspacing="0" border="0" style="margin-top:1px;">
					<tr>
						 <td width="5" height="30" valign="top" bgcolor="#bebebe"><img src="images/Widget_topLeft.gif" width="5" height="30" /> </td>
		            <td class="widget_title_form"  height="30" width="100%" bgcolor="#bebebe">Selected Systems</td>
		            <td width="5" height="30" valign="top" bgcolor="#bebebe"><img src="images/Widget_topRight.gif" width="5" height="30" /> </td>
					
					</tr>
					</table>
				 </td></tr>
				
				 <tr><td colspan="2">
		            <table border="0" width="100%" cellpadding="2" cellspacing="0" style="margin-top:1px;" class="widget_tableContent">
			            <tr>
			            <td width="40%"><b>System Type</b></td>
			            <td width="30%"><b>System</b></td>
			            <td width="30%"><b>Description</b></td>
			            </tr>
						<%
							int selSysConnLen = selSysConnectors.size();
														for (int rowCount = 0 ; rowCount < selSysConnLen ; rowCount++) {
																SubmitSystemConnector sysConnItem = (SubmitSystemConnector) selSysConnectors.get(rowCount);
																if (rowCount % 2 == 1) {
																	out.println("<tr  class=\"widget_rowOdd\">");
																} else {
																	out.println("<tr  class=\"widget_rowEven\">");
																}
						%>
									<td width="40%"><%= sysConnItem.getSystemType() %></td>
									<td width="30%"><%= sysConnItem.getConnectorName() %></td>								
									<td width="30%"><%= sysConnItem.getConnectorDescription() %></td>
									
								</tr>
						<%
							}
						%>
					</table>
				</td></tr>				             
	            <%
            	}
    	        %>



				<%
					if (selectedRoles != null && selectedRoles.size() > 0) {
				%>
				<!-- SELECTED ROLES ARE LISTED HERE -->
				<tr><td>
					<table width="100%" cellpadding="0" cellspacing="0" border="0" style="margin-top:1px;">
					<tr>
						 <td width="5" height="30" valign="top" bgcolor="#bebebe"><img src="images/Widget_topLeft.gif" width="5" height="30" /> </td>
		            <td class="widget_title_form"  height="30" width="100%" bgcolor="#bebebe">Selected Roles</td>
		            <td width="5" height="30" valign="top" bgcolor="#bebebe"><img src="images/Widget_topRight.gif" width="5" height="30" /> </td>
					
					</tr>
					</table>
				 </td></tr>
				
				 <tr><td colspan="2">
		            <table border="0" width="100%" cellpadding="2" cellspacing="0" style="margin-top:1px;" class="widget_tableContent">
		            <tr>
		            <td  width="40%"><b>Role</b></td>
		            <td width="35%"><b>Description</b></td>
		            <td width="25%"><b>Owner</b></td>
		            </tr>
						<%
							for (int rowCount = 0 ; rowCount < selectedRoles.size() ; rowCount++) {
								SearchResults searchResultObj = (SearchResults) selectedRoles.get(rowCount);
								if (rowCount % 2 == 1) {
									out.println("<tr  class=\"widget_rowOdd\">");
								} else {
									out.println("<tr  class=\"widget_rowEven\">");
								}
						%>
									<td width="40%"><%=searchResultObj.getRoleName()%></td>
									<td width="35%"><%=searchResultObj.getDescription()%></td>								
									<td width="25%">
									<%
										List owners = searchResultObj.getOwners(); 
										String listedValues = "";
										for(int cnt = 0; cnt < owners.size(); cnt++)
										{
											if(cnt !=0)
											{
												listedValues = listedValues + ",";
											}
											listedValues = listedValues + owners.get(cnt);
										}
									%>
									<%=listedValues%></td>
									
								</tr>
						<%
							}
						%>
					</table>
				</td></tr>
				<%
					}
				%>
            </table>
		    <table cellpadding="0" cellspacing="0" border="0" width="100%">
	            <tr height="25px" valign="center"><td align="left" bgcolor="#d9d9d9"
	                        style="border-left: 1px solid #d9d9d9; border-right: 1px solid #d9d9d9;">
		<%				if (isBackVisible) {
		%>
		                <input type="button" class="tblbtn" value="Back" style="width:60px" onclick="callBack()"/>
		<%				}
		%>
		                <input type="button" class="tblbtn" value="Edit" style="width:60px" onclick="callEditScreen()"/>
						<input type="HIDDEN" name="requestFrom" value="<%= AEConstants.GUI_PAGE_NAME_VALUE_REQUEST_REVIEW_PAGE %>">
		                <input type="button" class="tblbtn" value="Submit" style="width:60px;" Onclick="callSubmit()"/>
			            <input type="button" class="tblbtn" value="Cancel" style="width:60px" Onclick="callCancel()" />
	                </td>
	            </tr>
	        </table>	
            </table>
            </table>
            </td>
            </tr>
          </table>
</BODY>
</HTML>
