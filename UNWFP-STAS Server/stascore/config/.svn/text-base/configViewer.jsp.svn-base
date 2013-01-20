<%@page import="com.enterprisehorizons.magma.server.util.ServerUtils"%>
<%@ page import=" com.enterprisehorizons.magma.config.dbadmin.*" %>
<%@ taglib uri="/WEB-INF/ConfigRenderer.tld" prefix="config"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/WEB-INF/ConfigRenderer.tld" prefix="config"%>

<html:html locale="true">

<head>
<%@ include file="/common/dojo.jsp" %>
<link href="<%=ServerUtils.getContextName(request)%>/css/sti.css" rel="stylesheet" type="text/css" />

<style>

	@import "<%=ServerUtils.getContextName(request)%>/js/dojo/resources/dojo.css";
			@import "<%=ServerUtils.getContextName(request)%>/js/dijit/themes/tundra/tundra.css";
			@import "<%=ServerUtils.getContextName(request)%>/js/dijit/themes/tundra/tundra_rtl.css";
			@import "<%=ServerUtils.getContextName(request)%>/js/dijit/tests/css/dijitTests.css";
			@import "<%=ServerUtils.getContextName(request)%>/js/dojox/form/resources/FileInput.css"; 
			@import "<%=ServerUtils.getContextName(request)%>/css/style.css";

       body .txtareamedium {
		width: 15em;
		height: 5em;
	   }
		
</style>
 

<title>Create Record</title>

<link href="<%=ServerUtils.getContextName(request)%>/css/sti.css" rel="stylesheet" type="text/css" />
   <script type="text/javascript">
   var count=0;
   var selectObjectArray = [];
   
  

   function resetForm(){
var configTypeNameObj = document.getElementById("configTypeName");
	  if(configTypeNameObj.value=="")
	   {
		   showEmptyDialog( "<bean:message key='rules.config.file.select' bundle='rules'/>" , "<bean:message key='rules.config.alert.title' bundle='rules'/>");
		   return false;
	   }
      dojo.byId("configForm").reset();  
      for(i=1;i<=count;i++) {
	     if(document.getElementById("tname"+i)!=null) {
           document.getElementById("tname"+i).value="";
		   document.getElementById("tvalue"+i).value="";
		 }
      }	  
   }
    
   function loader(){         
   }
   
   
   function saveForm() {  
		   
	   if(document.getElementById("configTypeName").value=="")
	   {
		   showEmptyDialog( "<bean:message key='rules.config.file.select' bundle='rules'/>" , "<bean:message key='rules.config.alert.title' bundle='rules'/>");
		   return false;
	   }
	   if(!validateProps())
	   {
		  return false; 
	   }
      if(document.configForm.configTypeName.value!="") {
	           document.configForm.operation.value="saveConfigProperties";	              	   
               document.configForm.submit();
      }
   }
   var newPropCount = 0;
   function insertRow() {
	    var tbody = document.getElementById("propTable").getElementsByTagName("TBODY")[0];   
		count=count+1;	    
	    var row = document.createElement("TR");     
   		var tmpRowId = 'newPropertyRow' + count; 
	    row.setAttribute("id",tmpRowId);
	    var td1 = document.createElement("TD");
   	    td1.setAttribute("align","right");
   	    td1.setAttribute("nowrap","nowrap");   	    	    
   	    var td2 = document.createElement("TD");
   	    td2.setAttribute("align","left");   	 
   	    td2.setAttribute("nowrap","nowrap");   	 

		var strHtml = '<input type="text" dojoType="dijit.form.ValidationTextBox" style="width:200px;height:1.7em" name="propName" id="tname'+count+'">';
		
		td1.innerHTML = strHtml; 
	    // append data to row 
	    
	    var strHtml1 = '<td align="left" ><input type="text" style="width:202px;height:1.7em" dojoType="dijit.form.ValidationTextBox" name="propValue" id="tvalue'+count+'"><img src="<%=ServerUtils.getContextName(request)%>/images/minus.png" width="9px" height="9px" style="cursor : pointer" onclick="removeNewPropertyRow(\''+tmpRowId+'\', \'propInnerTable\' );"/ ></td>';
		td2.innerHTML = strHtml1; 	
	    row.appendChild(td1);	   
	    row.appendChild(td2);	   	    
	    // append row to table

	    tbody.appendChild(row);	
	    ++newPropCount;
		
	   }
   
   
  function insertRow1(){
   
      var trTag=document.createElement("TR");
	  var tdtag1=document.createElement("TD");
	  var tdtag2=document.createElement("TD");
	  var inputTag1=document.createElement("input");
	  var inputTag2=document.createElement("input");
	  
	  inputTag1.type="text";
	  inputTag2.type="text";
	  inputTag1.name="propName";
	  inputTag2.name="propValue";
	  tdtag1.appendChild(inputTag1);
	  tdtag2.appendChild(inputTag2);
	  
	  trTag.appendChild(tdtag1);
	  trTag.appendChild(tdtag2);
	  
      /* 
        trTag.style.visibility='visible';
	    tdtag1.style.visiblity='visible';
	    tdtag2.style.visiblity='visible';
	    inputTag1.style.visibility='visible';
	    inputTag2.style.visibility='visible';
	  */	  	 
      document.getElementById("propTable").firstChild.appendChild(trTag);	  
	  refreshData();
   }
   
   dojo.addOnLoad(loader); // Execute on load      
      
   </script>
   
  <script>
	function validateForm(Object){
		if(Object.value == "" ){
			 
			 document.getElementById('validateResult').innerHTML = "<bean:message key='admin.common.mandatory.alert' bundle='admin'/>";
		}else{
			var allMandatoryfieldsSelected = true;
			for(i=0; i<selectObjectArray.length;i++){
				if(document.getElementById(selectObjectArray[i].name).value == "" ){
					allMandatoryfieldsSelected = false
					break;
				}
			}
			if(allMandatoryfieldsSelected){				
				
				document.getElementById('validateResult').innerHTML = "";
			}
		}			
	}
    // run every time
	function refreshData() {
	    window.status="Done";  
	}
	
  </script>
   
		
   <script>
   
        function closeWindow(){
            window.close();
        }
        
        function renderFields(){	                
        var configTypeNameObj = document.getElementById("configTypeName");
   
             if(configTypeNameObj.value!="") {
             url="<%=ServerUtils.getContextName(request)%>/configRoot.do?operation=renderConfigProperties&configTypeName="+configTypeNameObj.value;			
			 window.location=url;
             }else{

					var labelRowObj = document.getElementById("labelRow");

					if(labelRowObj != null){
						
						labelRowObj.style.display = "none";

						var propTableObj = document.getElementById("propTable");

						if(propTableObj != null){
							propTableObj.style.display = "none";
						}
						
					}

			 } 
        }
		
		
		
        function gotoBack() {
            window.location="<%=ServerUtils.getContextName(request)%>/configRoot.do?operation=showConfigHome";
        }
                
		var allObjects = new Array();
		var i=0;
        function imposeMaxLength(Object, MaxLen){
			
			if(Object.value.length > MaxLen ){
				 allObjects[i++] = Object;
				
				 document.getElementById('validateResult').innerHTML = Object.name+ " <bean:message key='rule.config.property.chr.len' bundle='rules'/> "+ MaxLen;
			}else{
				
				 document.getElementById('validateResult').innerHTML = "";
			}
			
			return (Object.value.length <= MaxLen);

        }       
        
	function trimAll(sString)
	{
	
	   /*
	   
	    if(sString=='undefined' || sString==null)
	        return "";
	        
	        */
	        	        	     	      	      	      
		while (sString.substring(0,1) == ' ')
		{
			sString = sString.substring(1, sString.length);
		}
		while (sString.substring(sString.length-1,  sString.length) == ' ')
		{
			sString = sString.substring(0,sString.length-1);
		}
		return sString;
	}        
        
        function validateProps()
        {
	
		 var allowedChar	=	" 0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz.?:_,[]()-+{}()@=";
		 var errorMsg		=	"The allowed list of characters is : <br> 0-9 a-z A-Z and .?:_,[]()-+{}()@=";

		 for(var count = 1;count <= newPropCount ;count++)
			{
				var tmpPropObj = document.getElementById('tname'+count);
				var tmpValueObj = document.getElementById('tvalue'+count);
				if(tmpPropObj != null && (trimAll(tmpPropObj.value).length ==0 || trimAll(tmpValueObj.value).length == 0))
				{
	                showEmptyDialog("<bean:message key='rule.config.check.property' bundle='rules'/>", "<bean:message key='rules.config.alert.title' bundle='rules'/>");          
	                return false;
				}
				
			}

		var tab3Obj = document.getElementById("propInnerTable");
		var tab4Obj = document.getElementById("propSavedTable");
		var tab3Rows = tab3Obj.rows.length; 
		var tab4Rows ; 		
		if(tab4Obj != null)
			tab4Rows = tab4Obj.rows.length; 
		else
			tab4Rows = 0;
		var totalRows = tab3Rows + tab4Rows;
		

			for(var count = 1;count <= newPropCount ;count++)
			{
				var tmpPropObj = document.getElementById('tname'+count);
				var tmpValueObj = document.getElementById('tvalue'+count);
				for(var count1 = 1;count1 <= tab4Rows ;count1++)
				{
						var tmpPropObj1 = document.getElementById('propName' + count1);
						if(tmpPropObj != null && (tmpPropObj1 != null)	)
						{
							if(tmpPropObj != null)	
							if(trimAll(tmpPropObj.value) == trimAll(tmpPropObj1.value))
							{
									showEmptyDialog( tmpPropObj.value +  "<bean:message key='rule.config.check.property.exist' bundle='rules'/>", "<bean:message key='rules.config.alert.title' bundle='rules'/>");          
									return false;
							}
						}
				}
			}


				
			if(totalRows >2)
			for(var count = 1;count <= totalRows ;count++)
			{
				var tmpPropObj = document.getElementById('tname'+count);
				var tmpValueObj = document.getElementById('tvalue'+count);
				
				var count1 = 0;
				for(var count1 = 1;count1 <= newPropCount ;count1++)
				{
					var tmpPropObj1 = document.getElementById('tname'+count1);
					var tmpValueObj = document.getElementById('tvalue'+count1);
					if(count != count1)
					if(tmpPropObj != null && tmpPropObj1 != null )	
					{
						if(trimAll(tmpPropObj.value) == trimAll(tmpPropObj1.value))
						{
								showEmptyDialog( tmpPropObj.value +  "<bean:message key='rule.config.check.property.exist' bundle='rules'/>", "<bean:message key='rules.config.alert.title' bundle='rules'/>");          
								return false;
						}
					}
				}
			}	
	

			for(var count = 1;count <= totalRows ;count++)
			{
				var tmpPropObj = document.getElementById('tname'+count);
				var tmpValueObj = document.getElementById('tvalue'+count);
				var count1 = 0;
				if(tmpPropObj != null && tmpValueObj != null )	
				{
					if(tmpPropObj.value.length >50)
					{
							showEmptyDialog( '<bean:message key="rule.config.property.name.length" bundle="rules"/>', '<bean:message key="rules.config.alert.title" bundle="rules"/>');          
							return false;
					}
					if(tmpValueObj.value.length >255)
					{
							showEmptyDialog(  '<bean:message key="rule.config.property.value.length" bundle="rules"/>', '<bean:message key="rules.config.alert.title" bundle="rules"/>');          
							return false;
					}	
					if(isSpecialChar(tmpPropObj, allowedChar))
					{
							tmpPropObj.focus();
							showEmptyDialog(errorMsg , '<bean:message key="rules.config.alert.title" bundle="rules"/>');
							return false;
					}	

					
					if(isSpecialChar(tmpValueObj, allowedChar))
					{
							tmpValueObj.focus();
							showEmptyDialog(errorMsg , '<bean:message key="rules.config.alert.title" bundle="rules"/>');

							return false;
					}	
									
				}
				
				var tmpPropValueObj = document.getElementById('propValue'+count);
				if(tmpPropValueObj != null )	
				{
					if(tmpPropValueObj.value.length >255)
					{
							showEmptyDialog( "<bean:message key='rule.config.property.value.length' bundle='rules'/>", "<bean:message key='rules.config.alert.title' bundle='rules'/>");          
							return false;
					}
					if(isSpecialChar(tmpPropValueObj, allowedChar))
					{
							tmpPropValueObj.focus();
							showEmptyDialog(errorMsg , "<bean:message key='rules.config.alert.title' bundle='rules'/>");

							return false;
					}	

				}
			}	
			return true;
	} 
        
        
function removeNewPropertyRow(rowNum, divId ){
try{ 

		var tbl = document.getElementById(divId);
		var row = document.getElementById(rowNum);
		tbl.removeChild(row); 

		var tab3Obj = document.getElementById("propInnerTable");
		var tab4Obj = document.getElementById("propSavedTable");
		var tab3Rows = tab3Obj.rows.length; 
		var tab4Rows ; 		
		if(tab4Obj != null)
			tab4Rows = tab4Obj.rows.length; 
		else
			tab4Rows = 0;
		
		
		if(tab3Rows == 1 &&  tab4Rows == 0  )	
		{
			insertRow();
		}
		
}
catch(e){alert(e);}
}
     
               
   </script>
</head>

<body class="tundra bodybg" onLoad="refreshData()">


<table width="101.4%">
<tr>
  <td class="pageTitle paddingTitle">
   <table>    
    <tr>
          <td height="60px" align="left" colspan="2" valign="top"  class="redtitle"><strong class="pageTitle paddingTitle"><bean:message key="rules.configureRulesViewer.header" bundle="rules" /><br/>
                 <span class="paddingTitleDesc bodytext"><bean:message key="rules.configureRulesViewer.description" bundle="rules"/> </span> </strong>
	      </td>
    </tr>	
    <tr>
			
			<td colspan="2" style="align:left;width:500px;" nowrap="nowrap" >
							   <font color="blue" style="font-family: Tahoma;font-size: 12px;" ><b>
								 <html:messages id="configSaved" message="true" bundle="rules"><bean:write name="configSaved" /></html:messages>
								</b></font> 
								<font color="red" style="font-family: Tahoma;font-size: 12px;"><b><html:errors bundle="rules"/></b></font>  
			</td>    
    </tr>
	
    <tr>
    <td>
        <span id="ctl00_mainbody_lblError" class="error"><html:errors/></span><br/><div id="validateResult" class="error"></div><br/>
		
        <table border="0" id="table2" height="54" cellspacing="0" cellpadding="0">		
            <tr>
                <td height="27" align="left" style="width: 227px">
                    <table width="500" align="left" border="0"> 
                        <tbody border="0" >
								<form dojoType="dijit.form.Form" id="configForm" name="configForm" method="get" >                                
                            <tr>
                                <td class="redtitle1" width="430px;" nowrap="nowrap" colspan="2" nowrap="nowrap">        
								&nbsp;
									<bean:message key="rules.configureRulesViewer.selectFile" bundle="rules"/> :                                   
								<input type="hidden" name="operation" value="renderConfigProperties"/>
								<logic:present name="ATTRIBUTE_CONFIG_TYPES">
									<logic:notEqual name="ATTRIBUTE_CONFIG_TYPES"  value="null">
										<select id="configTypeName" name="configTypeName" onChange="renderFields();">
										<option value=""></option>
											<logic:iterate id="configType" name="ATTRIBUTE_CONFIG_TYPES" scope="request">
											   <option value="<bean:write name='configType'/>"><bean:write name="configType"/></option>
											 </logic:iterate>
										 </select>
									</logic:notEqual>									
								</logic:present>
                                </td>
                            </tr>
                            <tr>
                                <td   class="error" align="center" height="13" colspan="2" >&nbsp;      
                                     
                                </td>
                            </tr>
							<tr id="labelRow" style="display:none;">
							<td colspan="2" style="padding-left:0;padding-right:0;" >
								<table width="100%" border="0" cellpadding="0" cellspacing="0">
									<td nowrap="nowrap" align="right"  style="padding-right:187px;"><label class="label" ><bean:message key='rule.config.property.name.label' bundle='rules'/></label>		
									&nbsp;&nbsp;&nbsp;<label class="label"><bean:message key='rule.config.property.value.label' bundle='rules'/></label></td>		
								</table>	
							</td>
							</tr>
                            <tr>
                                <td align="left" colspan="2"  >
                                  <logic:present name="configTypeName" scope="request"> 
                                      <script>
									   var obj = document.getElementById("labelRow");
										if(obj != null)		 
										 obj.style.display = "block";
								       document.configForm.configTypeName.value="<bean:write name='configTypeName'/>"
								      </script>
							         <table id="propTable"  border="0" width="430px">
								     <tbody id="propInnerTable"> 
							         <tr><td align="right" colspan="2">
                     								<img src="<%=ServerUtils.getContextName(request)%>/images/plus.png" width="9px" height="9px" style="cursor : pointer" onClick="insertRow()" title="Add property" />							         
							         </td></tr> 
							         </tbody>
                                       <tbody  id="propSavedTable" width="430px" >  									 
                                       <config:render configTypeName='<%=(String)request.getAttribute("configTypeName")%>' />
									   </tbody>
									
                                     </table> 

                                  </logic:present>         
                                                                                                 
								
                                </td>  
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                            </tr>
</form>
                        </tbody>
                    </table>
                </td>
            </tr>
        </table>    
    </td>
    </tr>    
   </table>
 </td> 
 <td width="67px">&nbsp;</td>	 
 </tr>
 
    <tr>
        <td align="middle" class="barColor" width="101.4%" colspan=2>
			 	<button dojoType="dijit.form.Button" type="button" onClick="window.location =  '<%=ServerUtils.getContextName(request)%>/RulesHome.do?operation=loadRulesHome'  "> <bean:message key="dbconfig.home"/> </button> 
            	<button dojoType="dijit.form.Button" type="button" onClick="gotoBack()"> <bean:message key="dbconfig.back" /> </button>&nbsp;
  				<button dojoType="dijit.form.Button" type="button" id='submitBtn' onClick="saveForm()" > <bean:message key="admin.common.save" bundle="admin"/> </button>&nbsp;
                <button dojoType="dijit.form.Button" type="reset" id='resetBtn' onClick="resetForm()">   <bean:message key="admin.common.reset" bundle="admin"/> </button> 
      
			  
		
        </td>
    </tr>
 </table> 

</body>
</html:html> 