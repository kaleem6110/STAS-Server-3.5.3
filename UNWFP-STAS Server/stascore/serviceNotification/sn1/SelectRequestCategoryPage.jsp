<%@page import="java.util.*" %>
<%@page import="com.sae.userselfservice.dao.SAEMRequestCategory" %>
<%@page import="com.sae.userselfservice.constants.AEConstants"%>
<%@page import="com.sae.common.constants.RequestConstants"%>
<%
	List categories = (List) request.getAttribute(AEConstants.GUI_REQUEST_KEY_REQUEST_CATEGORY_ARRAY);
	
%>
<%@include file="CommonHeader.jsp" %>
<SCRIPT LANGUAGE="JavaScript">
function isRadioSelected(paramObj) {
	var radioObj = paramObj;
	returnFlag = false;
	for (count = 0 ; count < radioObj.length ; count++) {
		if (radioObj[count].checked) {
			returnFlag = true;
		}
	}
	return returnFlag;
}
function callSubmit() {
	if (isRadioSelected(document.forms[0].requestCategory) && isRadioSelected(document.forms[0].userCategory)) {
		document.forms[0].action="<%= contextPath %>/RequestForm";
		document.forms[0].submit();
	} else {
		alert("Select the Request Category and User Category to proceed");
	}
}
function hide_tooltip(){
	document.getElementById("toolTipWindow").style.left=0
	document.getElementById("toolTipWindow").style.top=0
	document.getElementById("toolTipWindow").style.width=1;
	document.getElementById("toolTipWindow").style.height=1;
	document.getElementById("toolTipWindow").innerHTML="";
}
function show_tooltip(event,txt){
	document.getElementById("toolTipWindow").style.left=event.clientX+20
	document.getElementById("toolTipWindow").style.top=event.clientY+20
	document.getElementById("toolTipWindow").style.width=300;
	document.getElementById("toolTipWindow").style.height=40;
	document.getElementById("toolTipWindow").innerHTML=txt;

}

function setRequestCategoryDesc(reqCatDesc) {
	//alert(reqCatDesc);
	document.getElementById("requestCateDesc").value=reqCatDesc;
}
</script>
 <BODY>
		<!-- Div for showing the text on mouse over. -->
		<div id='toolTipWindow' style='position:absolute;left:0;top:0;width:1;heig ht:1;border:1px solid black;background-color:rgb(250,250,255)'>
		</div>
		<tr>
		<td>
			<div align="center">
				<span>   
					<table width="100% cellpadding="0" cellspacing="0">
						<tr><td align="left">
							<span style="padding-left: 8px;padding-right:8px;"><img src="images/41.gif"/></span>
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
		
		<tr><td>
		<table style="width:100%;" cellpadding="0" cellspacing="" border="0" >
     
			<table width="100%" cellpadding="0" cellspacing="0" border="0" >
				<tr>
					 <td width="5" height="30" valign="top" bgcolor="#bebebe"><img src="images/Widget_topLeft.gif" width="5" height="30" /> </td>
	            <td class="widget_title_form"  height="30" width="100%" bgcolor="#bebebe">Request Category</td>
	            <td width="5" height="30" valign="top" bgcolor="#bebebe"><img src="images/Widget_topRight.gif" width="5" height="30" /> </td>
				
				</tr>
			</table>
		

			<table cellpadding="2px" cellspacing="0" border="0" width="100%" style="width: 100%;border-left: 1px solid #d9d9d9;border-right: 1px solid #d9d9d9;">
	         	<tr>
	         	<td >
					<table border="0" width="100%" cellpadding="2" cellspacing="0" border="0" style="margin-top:1px;" >
						<tr>
						<td>
						<b>Select Request Category</b>
						</td>
						</tr>
					</table>
				</td>
				</tr>
				<tr>
				<td>
					<table border="0" width="100%" cellpadding="2" cellspacing="0" style="margin-top:1px;" >
						<%
						boolean isNewRow = false;
						String checkedText = "";
						String reqCateDescr = "";
						if (categories != null ) {
							for (int rowCount = 0 ; rowCount < categories.size() ; rowCount++ ) {
								SAEMRequestCategory requestCategory = (SAEMRequestCategory) categories.get(rowCount);
								if (rowCount % 2 == 0) {
									isNewRow = true;
								} else {
									isNewRow = false;
								}
								System.out.println(rowCount +" New Row Flag " + isNewRow);
								if (isNewRow) {
									out.println("<TR>");
								}
								if (requestCategory.getName().equals(RequestConstants.REQ_CASE_CHANGE_OF_ACCESS)) {
									checkedText = "CHECKED";
								} else {
									checkedText = "";
								}
								if(0 == rowCount)
									reqCateDescr = requestCategory.getDescription();
						%>
								<td><input type="radio" name="requestCategory" onclick="javascript:setRequestCategoryDesc('<%=  requestCategory.getDescription() %>')" value="<%= requestCategory.getName()%>" <%= checkedText %>></td>
								<td onmouseover="javascript:show_tooltip(event, '<%= requestCategory.getHoverText() %>')" onmouseout="javascript:hide_tooltip()"><%= requestCategory.getDescription()%></td>
						<%
							}
						} else {
						%>
							<tr>
								<td>No Request Categories found in the Database</td>
								<td>&nbsp;</td>
							</tr>
						<%
						}
						%>
						<input type="hidden" id="requestCateDesc" name="requestCateDesc" value="<%= reqCateDescr %>">
						
					</table>
					
					<br>
					<table>
					<tr>
						<td><b>Select User Category</b></td>
					</tr>
					</table>
					<table>
						<tr>
							<td><input type="radio" name="userCategory" value="Yourself" CHECKED  ></td>
							<td onmouseover="javascript:show_tooltip(event, '<%= AEConstants.USER_CATEGORY_HOVER_TEXT_REQUEST_FOR_YOURSELF %>')" onmouseout="javascript:hide_tooltip()">Request for Yourself</td>
							<td><input type="radio" name="userCategory" value="AnotherUser" ></td>
							<td onmouseover="javascript:show_tooltip(event, '<%= AEConstants.USER_CATEGORY_HOVER_TEXT_REQUEST_FOR_ANOTHER %>')" onmouseout="javascript:hide_tooltip()">Request for Another User</td>
						</tr>
					</table>
				</td>
				</tr>
		</table>
		
        <table cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr  valign="top"><td align="left" bgcolor="#d9d9d9"
                        style="border-left: 1px solid #d9d9d9; border-right: 1px solid #d9d9d9;">
            <input type="button" class="tblbtn" value="Next" onclick="javascript:callSubmit()"/>
         	</td>
            </tr>
        </table>
	

		</table>

        <!-- Common jsp table closing -->     
        </td></tr></table>         
        </td></tr></table>
        </td></tr></table>
        </td></tr></table>

</FORM>  
</BODY>
</HTML>
