<%@page import="com.enterprisehorizons.magma.server.util.ServerUtils ,  com.spacetimeinsight.arra.ARRAConstants"%>				 
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ include file="/common/style.jsp"%>

<%@ taglib prefix="tiles" uri="/tags/struts-tiles"%>


<html:html locale="true">
<head>
	<script type="text/javascript" src="<%=ServerUtils.getContextName()%>/js/dijit/form/_testCommon.js"></script>
	
	
	<style>
		@import "<%=ServerUtils.getContextName()%>/js/dojo/resources/dojo.css";
		@import "<%=ServerUtils.getContextName()%>/js/dijit/themes/tundra/tundra.css";
		@import "<%=ServerUtils.getContextName()%>/js/dijit/themes/tundra/tundra_rtl.css";
		@import "<%=ServerUtils.getContextName()%>/js/dijit/tests/css/dijitTests.css";
		@import "<%=ServerUtils.getContextName()%>/js/dojox/form/resources/FileInput.css"; 
		
	
		body .txtareamedium {
		    width: 25em;
		    height: 5em;
		}
	
	</style>
	
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
	<title>MaintainData</title>
	
	<script>
	function onLoad(){
	document.forms[0].idShow.disabled=true;
	document.forms[0].xlsExport.disabled=true;
	
	}
	function onChangeOfDate(){
	document.forms[0].idShow.disabled=false;
	document.forms[0].xlsExport.disabled=false;
	}
		function showReport() {
			cycName = document.forms[0].cycleNameId.value;
			var f = document.getElementById('iframe1');
			f.src = '<%=ServerUtils.getContextName()%>/stringServlet/projectManagementReportServlet?cycledate='+cycName + "&randValue=" + Math.random();
			f.style.display="";
		}
		function exportToExcel() {
			cycName = document.forms[0].cycleNameId.value;
			var f = document.getElementById('iframe1');
			f.src = '<%=ServerUtils.getContextName()%>/stringServlet/projectManagementReportServlet?type=xls&cycledate='+cycName + "&randValue=" + Math.random();
			f.style.display="";
		}
	</script>
</head>

<body class="tundra bodybg" onload="onLoad();" >

<html:form action="/projectManagement.do?operation=showProjectReport"   >
	<center>
	<table width="101.4%" cellspacing="0" cellpadding="0" align="right" border=0>
	<tr>
	<td class="pageTitle paddingTitle" >    
		<table>
			<%
	        	String[] cycleList = (String[] ) request.getAttribute(ARRAConstants.RPT_CYCLE_LIST); 
	        %>
			 <tr>
                <td  class="pageTitle">Reports</td>
            </tr>
            <tr>
               <td class="paddingTitleDesc bodytext" style="padding-left:0px">
                 <strong>Monthly Status Report</strong>
                </td>  
            </tr>
		 
	   
	    	<tr>
				<td>&nbsp;</td>
	    	</tr>
	     <tr>	
	    	<td>		
	        	<table id="table2" height="50" cellspacing="2" cellpadding="2" class="border1">
	            	<tr>
	                	<td height="27" class="tableBgColor">
	                    	<table>
	                        	<tbody>
							   		<tr >
	                                	<td  align="center" valign="center"> <label class="bodytext"><strong>Select Reporting Cycle : </strong></label></td>
	                                	<td align="center" valign="center">
											<select id="cycleNameId" name="cycleName"  size="2"  style='width:100px;' onchange="onChangeOfDate();">
											<% 
												if(cycleList != null) {
													for(int indx=0; indx < cycleList.length; indx++) {
														String cycleName = (String) cycleList[indx];
		                                    %>
														<option value="<%=cycleName %>" id="<%=cycleName%>"><%=cycleName %></option>
		                                    <% 
													} 
												} 
											%>
		                                    </select>
	                                	</td>
										<td valign="center" align="center">
											<button dojoType="dijit.form.Button"  id="idShow" name="btnShow" type="button" onClick="showReport()">Preview PDF</button>    
											<button dojoType="dijit.form.Button"  id="xlsExport" name="xlsExport" type="button" onClick="exportToExcel()">Export To Excel</button>    
										</td>
	                            	</tr>
	                        	</tbody>
	                    	</table>
	                	</td>
	            	</tr>
	        	</table>        
	    	</td>
			<td>&nbsp;</td>
		</tr>	    
	</table>
	</td>
	</tr>
	<tr colspan="3" >
		<td>	   
	     	<iframe id="iframe1"  height="300" width="97%" style="display:none"></</iframe>
		</td>
	 </tr>
	</table>
</html:form>
</body>
</html:html>