<%@page import="com.enterprisehorizons.magma.server.admin.Shp2DBMappingUtils"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@page import="com.enterprisehorizons.magma.server.util.ServerUtils"%>
<html>
<head>

<%@ include file="/common/dojo.jsp" %>




<style>
        body .txtareamedium {
            width: 25em;
            height: 5em;
        }

</style>


<title><bean:message key="shptoDb.exportshptodb" bundle="shptoDb"/>  </title>

<script language="javascript">

dojo.addOnLoad(performReset);
    function goToDBHomePage(){
		
            window.location = "<%=ServerUtils.getContextName(request)%>/adminMain.do";
			
    }
 function performReset(){
	
	if(dijit.byId("dbModelName") != null){
		dijit.byId('dbModelName').setValue('');
	}
	}
	 function performSubmit(){
		
	if(dijit.byId("dbModelName") != null )
		if(dijit.byId('dbModelName').attr('value') == ''){
			showEmptyDialog('<bean:message key="shpDbMap.dbmodel.check.selection" bundle="shpDbMap"/>', "<bean:message key='admin.common.alert.title' bundle='admin'/>");
		}else{
			  frm.submit(); 		
		}	 
    }
</script>
</head>

<body class="tundra bodybg"> 

<%      
    String[][] dbModels = Shp2DBMappingUtils.getMappedModelNames();
    int noOfModels = dbModels == null ? 0 : dbModels.length;                
%>


<form name="frm" method="post" action="<%=ServerUtils.getContextName(request)%>/shptodb.do">

<table width="101.2%" cellspacing="0" cellpadding="0" align="center" >

	<tr>

		<td class="pageTitle paddingTitle">    
			<bean:message key="shptoDb.exportshptodb" bundle="shptoDb"/>
		</td>

	</tr>		  


	<tr>

		<td class="pageTitle paddingTitleDesc">    
			<bean:message key="shptoDb.exportshptodbDescription" bundle="shptoDb"/>
		</td>
		
	</tr>		  
	
	<tr>


		<td style="padding-left:67px;padding-top:10px;">
			
			<label class = "success">
            <html:messages id="saveStatus" message="true" bundle="shptoDb">
            <logic:present name="saveStatus">
            <bean:write name="saveStatus" />
            </logic:present>                                     
            </html:messages>
            </label>
           
			<label class = "error">
			<html:errors bundle="shptoDb"/>
			</label>
			
       </td>

   </tr>



   
	<tr>
		
		<%
        if(noOfModels == 0) { 
        %>
        
			<table id= "noMappingFiles">
				<tr>
					<td style = "padding-left: 67px;padding-top: 20px;"><label class = "label"><bean:message key="shptoDb.nomapmodelexist" bundle="shptoDb"/></label>
				    </td>
				</tr>
			</table>
			
        <%
        } else { 
        %>      
	
			<table id= "mappingFiles">

				<tr>

					<td style = "padding-left: 67px;padding-top: 20px;"> 
						<label class="label"> <bean:message key="shptoDb.dbmodeltoexport" bundle="shptoDb"/>:</label>
					</td>
			
					<td style = "padding-left: 10px;padding-top: 20px;">  

						<select id="dbModelName" name="dbModelName" dojoType="dijit.form.FilteringSelect" size="1">

							<option value="" ><bean:message key="validation.msg.select" bundle="splchvalidation"/></option>
							<%
								for(int i = 0; i < noOfModels; i++) {
							%>  
								<option value="<%=dbModels[i][0]%>"><%=dbModels[i][1]%></option>
							<%
								}
							%>          

						 </select>

					</td>

				</tr>

			</table>
		<%
			}
		%>

	</tr>

</table>

<table id = "buttonPanel" width = "101.2%">
	<tr><td>&nbsp;</td></tr>

	<tr  height="30" colspan="2" class="barColor">
       <td height="30" colspan="2" class="barColor" align="center">	 
            <button dojoType="dijit.form.Button"  type="button" onClick="window.location= '<%=ServerUtils.getContextName(request)%>/adminMain.do'"><bean:message key="admin.common.home" bundle="admin"/></button>
            <button dojoType="dijit.form.Button"  id="idSubmit" name="btnSubmit" type="button"  onclick="return performSubmit();"> <bean:message key="admin.common.submit" bundle="admin"/>  </button>
            <button dojoType="dijit.form.Button"  id="idReset" name="btnReset" type="Reset" onClick="performReset();"/><bean:message key="admin.common.reset" bundle="admin"/>    </button>
        </td>
    </tr>
</table>






    <input type="hidden" name="operation" value="export"/>
</form>
</body>
</html>

<!-- jsp:include page="../common/adminnavigation.jsp"/ -->
<!-- jsp:include page="../common/footer.jsp"/ -->