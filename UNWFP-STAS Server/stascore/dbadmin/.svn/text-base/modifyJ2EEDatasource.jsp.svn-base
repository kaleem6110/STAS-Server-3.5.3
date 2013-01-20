<%@page import="com.spacetimeinsight.db.model.util.DataModelsCache"%>
<%@page import="com.spacetimeinsight.db.config.model.DatabaseTypesMaster"%>
<%@page import="com.spacetimeinsight.db.config.model.DatabaseConfigurations"%>
<%@page import="java.util.List, com.spacetimeinsight.db.model.util.SecurityDBUtils"%>
<%@page import="com.enterprisehorizons.magma.server.util.ServerUtils"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ include file="/common/dojo.jsp" %>
<html:html locale="true">

<%
	String dbConfigurationId = (String) request.getAttribute("dbConfigurationId");
	request.removeAttribute("dbConfigurationId");
	List databaseConfigurationsList = DataModelsCache.getInstance().retrieve(
			DatabaseConfigurations.class.getName());
	pageContext.setAttribute("databaseConfigurationsList", databaseConfigurationsList);

	List databaseTypesMasterList = DataModelsCache.getInstance().retrieve(
			DatabaseTypesMaster.class.getName());
	pageContext.setAttribute("databaseTypesMasterList", databaseTypesMasterList);
%>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Update Datasource</title>        
<!-- SCRIPT SECTION -->   
<script>
var dbConnectionMasterArray = [];
<logic:present name="databaseTypesMasterList" scope="page">
<logic:iterate name="databaseTypesMasterList" id="databaseTypesMasterDtls">
	dbConnectionMasterArray.push({dClass: '<bean:write name="databaseTypesMasterDtls" property="driverClass" />', dUrl: '<bean:write name="databaseTypesMasterDtls" property="connectionUrl" filter="false"/>', conType: '<bean:write name="databaseTypesMasterDtls" property="dbConnectionType" />', id: '<bean:write name="databaseTypesMasterDtls" property="id" />'});	
</logic:iterate>
</logic:present>

function resetHandler(){
	dijit.byId("newEcoDataSourceForm").reset();            
}

</script>
<!-- END SCRIPT SECTION -->
</head>

<body class="tundra bodybg">
<form name="newEcoDataSourceForm"  id="newEcoDataSourceForm" method="post" action="<%=ServerUtils.getContextName(request)%>/ConfigureDataSource.do?operation=configureJ2EEDsource&action=update" dojoType='dijit.form.Form'>

<table width="101.4%">
<tr>
	<td class="pageTitle paddingTitle">
    <table width="100%">    
    <tr>
		<td class="redtitle" height="60px" align="left" valign="top">
			<strong class="pageTitle paddingTitle" style="padding-left:0px"><bean:message key="j2eeDS.modify.datasource" bundle="j2eeDS" /><br/>
            <span class="paddingTitleDesc bodytext" style="padding-left:0px"><bean:message key="j2eeDS.modify.datasourceDesc" bundle="j2eeDS" /></span></strong>
	    </td>
 	    <td>
			
		</td>
    </tr>
    <tr>
    <td>
		<span><html:errors/></span><br/>
		<table width="100%">
		<tr>
			<td>
				<table width="100%">
				<tbody>
				<tr>
					<td>      
						 
                    </td>
                </tr>
	            <tr>
					<td align="left">
<!-- ==========================================================================================================  -->

<table border="0" width="100%">
<tbody>
<tr>
	<td>      
    	
    </td>
</tr>
<tr>
	<td align="left"  >
	<logic:present name="databaseConfigurationsList" >
	<logic:iterate name="databaseConfigurationsList" id="databaseConfigurationsDtls">
		<logic:equal name="databaseConfigurationsDtls" property="id" value="<%=dbConfigurationId %>">
			

    <table width="800" border="0">
    <tr>   
		<td align="right" >
			<label class="label"><bean:message key="j2eeDS.datasourceName" bundle="j2eeDS" /><span style='color:red;font-size:12px'>*</span> : </label>
		</td>
		<td align="left" >
			<b><bean:write name="databaseConfigurationsDtls" property="datasourceName" /></b>
			<input type="hidden" name='datasourceName' id='datasourceName' value='<bean:write name="databaseConfigurationsDtls" property="datasourceName" />' />
		</td>

		<td width="30px"></td>
		
		<td align="right" >
			<label class="label">
				<bean:message key="j2eeDS.providerType" bundle="j2eeDS" /><span style='color:red;font-size:12px'>*</span> : </label>
		</td>
		<td align="left" >
			<input type="hidden" name="previousDatabaseConnectionTypeId" id="previousDatabaseConnectionTypeId" value="<bean:write name="databaseConfigurationsDtls" property="databaseConnectionTypeId" />" />
			<select dojoType="dijit.form.FilteringSelect"  name='databaseConnectionTypeId' id='databaseConnectionTypeId' value='<bean:write name="databaseConfigurationsDtls" property="databaseConnectionTypeId" />' onChange="resetFormValuesOnProviderType()" > 
				<Option value=""><bean:message key="validation.msg.select" bundle="splchvalidation"/></Option>	
				<Option value="2" >DBCP</Option>
				<Option value="1">RAW</Option>				
				<Option value="3">Proxol</Option>
			</select>
		</td>		

		
		
	</tr>
	
	<tr><td height="13px"></td></tr>
	
	<tr>
		<td align="right" >
			<label class="label"><bean:message key="j2eeDS.description" bundle="j2eeDS" /> : </label>
		</td>  
		<td align="left" >
			<textarea dojoType="dijit.form.SimpleTextarea" id="description" class="txtareamedium" name="description" dojoType="dijit.form.ValidationTextBox" required="false" trim="true" ucfirst="true" style="width:200px" ><bean:write name="databaseConfigurationsDtls" property="description" /></textarea>
		</td>

		

		<td width="30px"></td>
		
		

		<td colspan="3" align="right" style="padding-right:3px">
		<table border=0>
		<tr>
			<td align="right" >
				<label class="label"><bean:message key="j2eeDS.initialSize" bundle="j2eeDS" /> : </label>
			</td>
			<td align="left" >
				<input type="text" style="height:1.7em"  name='initialSize' maxlength='30' id='initialSize' dojoType='dijit.form.ValidationTextBox'  value='<bean:write name="databaseConfigurationsDtls" property="initialSize" />'/> 
			</td>
			
		</tr>
		<tr><td height="13px"></td></tr>
		<tr>
			<td align="right" width="100%">
				<label class="label"><bean:message key="j2eeDS.maxActive" bundle="j2eeDS" /> : </label>
			</td>
			<td align="left" >
				<input type="text" style="height:1.7em"  name='maxActive' maxlength='30' id='maxActive' dojoType='dijit.form.ValidationTextBox'  value='<bean:write name="databaseConfigurationsDtls" property="maxActive" />'/>
			</td>
			
			
		</tr>
		</table>
		</td>

	</tr>


	<tr><td height="13px"></td></tr>

	<tr>  
		<td align="right" >
			<label class="label"><bean:message key="j2eeDS.driverName" bundle="j2eeDS" /><span style='color:red;font-size:12px'>*</span> : </label>
		</td>  
		<td align="left" >
			<select dojoType="dijit.form.FilteringSelect"  name='databaseTypeId' id='databaseTypeId' value='<bean:write name="databaseConfigurationsDtls" property="databaseTypeId" />' selected onChange="loadDatabaseConnectionValues(this);" > 
				<Option value=""><bean:message key="validation.msg.select" bundle="splchvalidation"/></Option>	
				<logic:present name="databaseTypesMasterList" scope="page">
				<logic:iterate name="databaseTypesMasterList" id="databaseTypesMasterDtls">
					<option value="<bean:write name="databaseTypesMasterDtls" property="id" />">
						<bean:write name="databaseTypesMasterDtls" property="driverName" />
					</option>
				</logic:iterate>
				</logic:present>			
			</select>
		
		</td>		
		


		<td width="30px"></td>
		

		<td align="right" >
			<label class="label"><bean:message key="j2eeDS.maxIdle" bundle="j2eeDS" /> : </label>
		</td>
		<td align="left" >
			<input type="text" style="height:1.7em"  name='maxIdle' maxlength='30' id='maxIdle' dojoType='dijit.form.ValidationTextBox'  value='<bean:write name="databaseConfigurationsDtls" property="maxIdle" />'/>
		</td>		

	</tr>
	
	<tr><td height="13px"></td></tr>
	
	<tr> 
		<td align="right" >
			<label class="label"><bean:message key="j2eeDS.url" bundle="j2eeDS" /><span style='color:red;font-size:12px'>*</span> : </label>
		</td>
		<td align="left" >
			<input type="text" style="height:1.7em"  name='url' maxlength='500' id='url' dojoType='dijit.form.ValidationTextBox'    trim='true' required='true' invalidMessage='Please provide value'  value='<bean:write name="databaseConfigurationsDtls" property="url" />'/>
		</td>
		
		<td width="30px"></td>
		<td align="right" >
			<label class="label"><bean:message key="j2eeDS.minIdle" bundle="j2eeDS" /> : </label>
		</td>
		<td align="left" >
			<input type="text" style="height:1.7em"  name='minIdle' maxlength='30' id='minIdle' dojoType='dijit.form.ValidationTextBox'  value='<bean:write name="databaseConfigurationsDtls" property="minIdle" />'/>
		</td>
		

	</tr>

	<tr><td height="13px"></td></tr>

	<tr>
		<td align="right" >
			<label class="label"><bean:message key="j2eeDS.username" bundle="j2eeDS" /> : </label>
		</td>
		<td align="left" >
			<input type="text" style="height:1.7em"  name='userName' maxlength='30' id='userName' dojoType='dijit.form.ValidationTextBox'    trim='true'   value='<bean:write name="databaseConfigurationsDtls" property="userName" />'/>
	  	</td>

		<td width="30px"></td>
		<td align="right" >
			<label class="label"><bean:message key="j2eeDS.maxWait" bundle="j2eeDS" />: </label>
		</td>
		<td align="left" >
			<input type="text" style="height:1.7em"  name='maxWait' maxlength='30' id='maxWait' dojoType='dijit.form.ValidationTextBox'  value='<bean:write name="databaseConfigurationsDtls" property="maxWait" />'/> 
		</td>
		

	</tr>

	<tr><td height="13px"></td></tr>

	<tr>
		<td align="right" >
			<label class="label"><bean:message key="j2eeDS.password" bundle="j2eeDS" /> : </label>
		</td>
		<td>
			<% String virtualPassword = null; %>
			<logic:present name="databaseConfigurationsDtls" property="password" scope="page">  
					 <bean:define id="encryptedPasswordLabel" name="databaseConfigurationsDtls" property="password" type="java.lang.String" /> 
					 <%
						if(encryptedPasswordLabel != null){
							virtualPassword = SecurityDBUtils.getVirtualPassword(encryptedPasswordLabel);
						}
					%>
			</logic:present>  
			<logic:notPresent name="databaseConfigurationsDtls" property="password"  scope="page">  
					 <bean:define id="encryptedPasswordLabel" value="" type="java.lang.String"/>      
					 <%						
						if(encryptedPasswordLabel != null){
							virtualPassword = SecurityDBUtils.getVirtualPassword(encryptedPasswordLabel);
						}
					%>
			</logic:notPresent>
			
			

			
			<input type='password' name='password' dojoType='dijit.form.ValidationTextBox' maxLength="30"   trim='true'  invalidMessage='Please provide value'  id='password' value='<%=virtualPassword %>'/>

			
		</td>	
		
		<td width="30px"></td>
		<td align="right" >
			<label class="label"><bean:message key="j2eeDS.testWhileIdle" bundle="j2eeDS" /> : </label>
			<input type="hidden" name="testOnBorrow" id="testOnBorrow" value="false">
		</td>
		<td align="left" >
			<select dojoType="dijit.form.FilteringSelect" name='testWhileIdle' id='testWhileIdle' value='<bean:write name="databaseConfigurationsDtls" property="testWhileIdle" />' selected> 
				<Option value="false">false</Option>
				<Option value="true">true</Option>
			</select>
		</td>
	</tr>

	<tr><td height="13px"></td></tr>

	<tr>
		
		<td align="right" colspan="2" style="padding-right:40px" width="50%"> 
		<table border=0 width="100%">
		<tr>
			<td align="right" >
				<label class="label"><bean:message key="j2eeDS.defaultAutoCommit" bundle="j2eeDS" /> : </label>
			</td>
			<td align="left" >
				<select dojoType="dijit.form.FilteringSelect"   name='defaultAutoCommit' id='defaultAutoCommit' value='<bean:write name="databaseConfigurationsDtls" property="defaultAutoCommit" />' selected> 
				    <Option value="false">false</Option>
					<Option value="true">true</Option>				
				</select>
			</td>
		</tr>
		<tr><td height="13px"></td></tr>
		<tr>
			<td align="right" >
				<label class="label"><bean:message key="j2eeDS.validationQuery" bundle="j2eeDS" /> : </label>
			</td>
			<td align="left" >
				<input type="text" style="height:1.7em"  name='validationQuery'  id='validationQuery' dojoType='dijit.form.ValidationTextBox'  trim='true'  value='<bean:write name="databaseConfigurationsDtls" property="validationQuery" />'/>
			</td>
		</tr>
		</table>
		</td>

		

		<td width="30px"></td>
		<td align="right" >
			<label class="label"><bean:message key="j2eeDS.customProps" bundle="j2eeDS" /> : </label>
		</td>  
		<td align="left" >
			<textarea dojoType="dijit.form.SimpleTextarea" id="connectionProperties" class="txtareamedium" name="connectionProperties" dojoType="dijit.form.ValidationTextBox" required="false" trim="true" ucfirst="true" style="width:200px" ><bean:write name="databaseConfigurationsDtls" property="connectionProperties" /></textarea>
		</td>
		

	</tr>
	
	<input type="hidden" name="encryptedPassword" id="encryptedPassword" value="<bean:write name="databaseConfigurationsDtls" property="password" />"/><input type="hidden" name="virtualPassword" id="virtualPassword" value="<%=virtualPassword %>"/>
	<input type="hidden" name="id" id="id" value="<bean:write name="databaseConfigurationsDtls" property="id" />"/>
	
	</table>
			</logic:equal>
	</logic:iterate>
	</logic:present>
	</td>
</tr>
</tbody>
</table>
<!-- ==========================================================================================================  -->						
                    </td>
                </tr>
                <tr>
					<td></td>
                    <td></td>
		        </tr>
                </tbody>
                </table>
            </td>
        </tr>
        </table>    
    </td>
    </tr>    
   </table>
   </td> 
   <td>
	
   </td>	 
</tr>
<tr  align = "right" class="barColor">
	<td width="890">
		<button dojoType="dijit.form.Button" type="button" onClick="window.location ='<%=ServerUtils.getContextName(request)%>/adminMain.do'"> <bean:message key="dbconfig.home" /> </button>
		
		<button dojoType="dijit.form.Button" type="button" id='backBtn' onClick="window.location ='<%=ServerUtils.getContextName(request)%>/WizardHomeAction.do?operation=showAllJ2EEDatasource&pageNo=1'">  <bean:message key="dbconfig.back" /> </button>
        <button dojoType="dijit.form.Button" type="button" id='submitBtn' onClick="submitHandler()" > <bean:message key="dbconfig.save" /> </button>
        <button dojoType="dijit.form.Button" type="reset" id='resetBtn' onClick="resetHandler()" > <bean:message key="dbconfig.reset" /> </button>
	</td>
</tr>
</table>


<!-- SCRIPT SECTION-->
<script>
function submitHandler(){
	if(dijit.byId("newEcoDataSourceForm").validate() && validateHandler()){
		
		testDatabaseConnection();
	}
}


function validateHandler(){
	if(dijit.byId('databaseTypeId').value == ""){
		showEmptyDialog("<bean:message key="j2eeDS.selectDriverName" bundle="j2eeDS" />", "Alert");
		return false;
	}

	if(dijit.byId('databaseConnectionTypeId').value == ""){
		showEmptyDialog("<bean:message key="j2eeDS.selectProviderType" bundle="j2eeDS" />", "Alert");
		return false;
	}
     if(dijit.byId('description').attr('value').length > 499){
     showEmptyDialog("<bean:message key="datasource.descriptionChkAlert" bundle="ds" />", "Datasource");
     return false;
	  }
    
    if(dijit.byId('connectionProperties').attr('value').length > 255){
        showEmptyDialog("<bean:message key="j2eeDS.additionalProvidorProperties.check" bundle="j2eeDS" />", "Datasource");
        return false;
    }

	if(!validateIntervalValue(dojo.byId('initialSize').value, 'Initial Size')){
		return false;
	}

	if(!validateIntervalValue(dojo.byId('maxActive').value, 'Max Active')){
		return false;
	}

	if(!validateIntervalValue(dojo.byId('maxIdle').value, 'Max Idle')){
		return false;
	}

	if(!validateIntervalValue(dojo.byId('minIdle').value, 'Min Idle')){
		return false;
	}

	if(!validateIntervalValue(dojo.byId('maxWait').value, 'Max Wait')){
		return false;
	}

	return true;
}

function testDatabaseConnection(){
	var databaseType = dijit.byId('databaseConnectionTypeId').value;
	var databaseConfigName = document.getElementById('datasourceName').value;
	var driverClassName = getDatabaseClassDtls(dijit.byId('databaseTypeId').value);
	var userName = dijit.byId('userName').value;
	var password = dijit.byId('password').value;
	var dbUrl = dijit.byId('url').value;
	var connectionProperties = dijit.byId('connectionProperties').value;
	var defaultAutoCommit = dijit.byId('defaultAutoCommit').value;
	var initialSize = dijit.byId('initialSize').value;
	var maxActive = dijit.byId('maxActive').value;
	var maxIdle = dijit.byId('maxIdle').value;
	var minIdle = dijit.byId('minIdle').value;
	var maxWait = dijit.byId('maxWait').value;
	var validationQuery = dijit.byId('validationQuery').value;
	//var testOnBorrow = dijit.byId('testOnBorrow').value;
	var testOnBorrow = 'false';
	var testWhileIdle= dijit.byId('testWhileIdle').value;
	var previousDatabaseConnectionTypeId = document.getElementById('previousDatabaseConnectionTypeId').value;
	var uriDtls = 'databaseType='+databaseType+'&driverClassName='+driverClassName+'&userName='+userName+'&password='+password+'&dbUrl='+dbUrl+'&connectionProperties='+connectionProperties+'&defaultAutoCommit='+defaultAutoCommit+'&initialSize='+initialSize+'&maxActive='+maxActive+'&maxIdle='+maxIdle+'&minIdle='+minIdle+'&maxWait='+maxWait+'&validationQuery='+validationQuery+'&testOnBorrow='+testOnBorrow+'&encryptedPassword='+encodeURIComponent(document.getElementById('encryptedPassword').value)+'&mode=update&databaseConfigName='+databaseConfigName+'&virtualPassword='+document.getElementById('virtualPassword').value+'&testWhileIdle='+testWhileIdle+"&previousDatabaseConnectionTypeId="+previousDatabaseConnectionTypeId;
	
	loadData('TestDatabaseConnectionStatus', uriDtls, returnConnectionStatus, 'modify');
}



function loadDatabaseConnectionValues(obj){	 
	var i=0; for(i=0;i<dbConnectionMasterArray.length; i++){
		if(dbConnectionMasterArray[i].id == dijit.byId('databaseTypeId').attr('value')){
			dijit.byId('url').setValue(dbConnectionMasterArray[i].dUrl);	
			dijit.byId('databaseConnectionTypeId').setValue(dbConnectionMasterArray[i].conType);
			//dijit.byId('databaseConnectionTypeId').setAttribute('disabled', true);
		}
	}
	if(dijit.byId('databaseTypeId').attr('value') == ''){
		dijit.byId('url').setValue(''); 
		dijit.byId('databaseConnectionTypeId').setValue('');
		//dijit.byId('databaseConnectionTypeId').setAttribute('disabled', false);	
	}
}

function getDatabaseClassDtls(objReference){
	var i=0; for(i=0;i<dbConnectionMasterArray.length; i++){
		if(dbConnectionMasterArray[i].id == objReference ){
			return dbConnectionMasterArray[i].dClass;
		}
	}
	
}


function resetFormValuesOnProviderType(){
	var providerTypeId = dijit.byId('databaseConnectionTypeId').value;
	if(providerTypeId == 1){
		resetFormValues(true);
	}else{
		resetFormValues(false);
	}
	if(providerTypeId == 1){
		dijit.byId('validationQuery').setValue('');
		//dijit.byId('connectionProperties').setValue('');
	}
}

function resetFormValues(status){
	//dijit.byId('connectionProperties').setAttribute('disabled', status );
	dijit.byId('defaultAutoCommit').setAttribute('disabled', status );
	dijit.byId('initialSize').setAttribute('disabled', status );
	dijit.byId('maxActive').setAttribute('disabled', status );
	dijit.byId('maxIdle').setAttribute('disabled', status );
	dijit.byId('minIdle').setAttribute('disabled', status );
	dijit.byId('maxWait').setAttribute('disabled', status );
	dijit.byId('validationQuery').setAttribute('disabled', status );
	//dijit.byId('testOnBorrow').setAttribute('disabled', status );	
	dijit.byId('testWhileIdle').setAttribute('disabled', status );
}

dojo.addOnLoad(loadFormValues); 

function loadFormValues(){
	resetFormValuesOnProviderType();
}
</script>
<!-- END SECTION -->
</form>
</body>
</html:html>