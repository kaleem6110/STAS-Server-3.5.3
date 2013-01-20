<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib prefix="tiles" uri="/tags/struts-tiles"%>

<%@ page import="com.spacetimeinsight.db.scheduler.model.ServerEnvMaster,com.spacetimeinsight.stas.config.GenericConfigurationManager,java.util.ArrayList,com.spacetimeinsight.admin.uiconfig.bean.ConfigDetailBean,com.enterprisehorizons.magma.server.admin.AdminConfigUtils" %>
<%@page import="com.enterprisehorizons.magma.server.util.ServerUtils"%>
<%
java.util.ArrayList   sectionsLst = (java.util.ArrayList ) request.getAttribute("sectionsLst");
java.util.ArrayList   tabsLst = (java.util.ArrayList ) session.getAttribute("tabsLst");
String tabValue = (String) request.getAttribute("selTabValue");
String xmlName = (String) request.getAttribute("xmlName");
String maxValue = "0";
String newTabValue = (String) request.getAttribute("newTabValue");
String newXmlName = (String) request.getAttribute("newXmlName");
String isNewTab  = (String) request.getAttribute("isNewTab");
java.util.ArrayList   sectionsLstNtVisi = (java.util.ArrayList ) request.getAttribute("sectionsLstNtVisi");
java.util.ArrayList   serverIpList = (java.util.ArrayList ) request.getAttribute("serverEnvIPList");
boolean displayCheckBox = false ; 
Boolean tmpCh = (Boolean) request.getAttribute("showCheckBox");
if(tmpCh != null){
    displayCheckBox  = tmpCh.booleanValue();
}
//((Boolean) request.getAttribute("showCheckBox")).booleanValue();
java.util.ArrayList   xmlFileSectionsList = (java.util.ArrayList ) request.getAttribute("xmlFileSections");
boolean propertyExists = false;
%>

<html:html locale="true"> 
<head>
<%@ include file="/common/dojo.jsp"%>
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Expires" content="0">
<title><bean:message key="uiconfig.title" bundle="uiconfig"/></title>
<script type="text/javascript" src="js/sessionTimeOut.js"></script>
<script type="text/javascript" src="js/ecoweb.js" ></script>  
<script>
var tabVal = "<%=tabValue%>"; 
var xmlName = "<%=xmlName%>";
var propNameArray = new Array();
var tabNameArray = new Array();
var displayNameArray = new Array();
var i = 0;
var newTabVal = "";
count = 1; 
var newXmlName = '<%=newXmlName%>';
var onFocusSelectedText = "";
var temp;

function onFocusSelectText(tmp,type)
{
    if(type == 'dynamic')   
    {
        if(onFocusSelectedText.value != "")
        { 
            onFocusSelectedText = tmp.value; 
        }
    }
    else
    {
        if(onFocusSelectedText.value != "")
        { 
            onFocusSelectedText = tmp.value;
        }
    }
}  

function validInput(temp) {
    var input =  document.getElementById(temp).value;
    validateSplOnTxtBox(input);



    /* 
     var dirformat = /^[a-zA-Z0-9 _.:@$-\/\\]+$/;
   if(dirformat.test(input)||(input == "")){ 
   
   }else{

     showEmptyDialog("Only _.:@$-\/ and SPACE are allowed as Special Characters", "Manage Configurations"); 
  }
  */
}


function chkBlankText(tmp,type)
{
    if( tmp != null && tmp.value == "" && (tmp.value.length)  == 0)
    {

            showEmptyDialog("Property value is mandatory. Please enter property value.", "Manage Configurations");     
            if(type == 'dynamic')   
            {
                tmp.value = onFocusSelectedText;
            }
            else        
            {
                tmp.attr("value",onFocusSelectedText);                
            }
            tmp.focus();
            return false;
    }
    return true;
}
 
function chkForDuplicatePropName()
{ 
     var displayName = dojo.byId('newDisplayName').value ;
    var properyName = dojo.byId('newProperty').value;
   
      for(count1 = 0; count1 < displayNameArray.length; count1++)
    { 

       if(displayNameArray[count1].toUpperCase() == displayName.toUpperCase())
       {
           showEmptyDialog("Entered display name already exist", "Manage Configurations");  
           return false;
       }
    }
      for(count1 = 0; count1 < propNameArray.length; count1++)
    { 

       if(propNameArray[count1].toUpperCase() == properyName.toUpperCase())
       {
           showEmptyDialog("Entered property name already exist", "Manage Configurations");  
           return false;
       }
    }
           return true;

}

var confirmationBox;




function doconfirmation() {
    confirmationBox.hide();
    var tmpDojoObj = document.getElementById('newSectionId');
    if(tmpDojoObj != null)
    {
        if(tmpDojoObj.style.display == 'block')
        {
//          showEmptyDialog('Please click (+) to add tab ', "Manage Configurations");          
            showEmptyDialog("Please click + to add tab", "Manage Configurations");  
            return;
        }else{
            showEmptyDialog('Please click save button', "Manage Configurations");          
            return;
        }
    }
}

 
 function chkForTabName(tmp)
 {
     if(tmp.toUpperCase() == ADD_SECTION || tmp.length == 0) 
     {
            showEmptyDialog("Please enter valid tab name and click (+) to proceed", "Manage Configurations");  
            return false;
     }
 
     for(count1 = 0; count1 < tabNameArray.length; count1++)
     {
        if(tabNameArray[count1].toUpperCase() == tmp.toUpperCase())
        {
            showEmptyDialog("Entered tab already exists", "Manage Configurations");  
            return false;
        }
     }
     newXmlName = tmp;
  
 }
 
 
function submitUrl()
{
        var obj = document.getElementById("sectionHrefId");
        var tmp = document.getElementById("newSection").value;
        var tmpDojoObj = dijit.byId('newSection');
        re = /^[A-Za-z0-9 ]{1,255}$/;
      //  var spchars =" ' ( ) { } \\ ~ ! @ ^ & * + \" | : =  , < > %";

        if(tmp.length == 0 ) 
        {
           tmpDojoObj.focus();
           showEmptyDialog("Please enter valid tab name as tab name is mandatory", "Manage Configurations");  
            return false;
        }       
        if(tmp.length != 0 && tmp.toUpperCase() == ADD_SECTION)
        {
           tmpDojoObj.focus();
           showEmptyDialog("Please enter valid tab name", "Manage Configurations");  
            return false;
        }
      /*  if(!validateSplOnTxtBox(tmp)){
            tmpDojoObj.focus();
           //showEmptyDialog("Please do not enter any of the following characters: \n"+ spchars, "Manage Configurations");  
            return false;
        }*/
        xmlName = tmp;
        
        ++newTabVal;
        var tmp = '<%=ServerUtils.getContextName(request)%>/manageConfigAction.do?tabValue='+ newTabVal+ '&xmlName=' + xmlName+ '&mode=newTab';
        obj.href = tmp;
}

function showTabSection()
{
/*        
        if(!chkNewXMlNameExists())
        {
            return;
        }
 */

    if(dijit.byId('btnDeleteTab') != null)
      dijit.byId('btnDeleteTab').setAttribute('disabled', true);        
var newSectionDivObj = document.getElementById("newSectionId");
if(newSectionDivObj != null && newSectionDivObj.style.display == 'none' && newXmlName != "null" &&  newXmlName != "")    
{
    showEmptyDialog("Please save " + newXmlName + " tab ." , "Manage Configurations");  
    return;
}
    var sectionObj = document.getElementById("newSectionId");
    sectionObj.style.display = 'block';
   var txtNewSectionObj = dojo.byId("newSection");
    if(txtNewSectionObj != null)
       txtNewSectionObj.focus();
    var hrf = '<%=ServerUtils.getContextName(request)+"/manageConfigAction.do?tabValue=" + ((Integer.parseInt(maxValue))+1)+"&xmlName=new"%>';
    sectionObj.value = hrf; 
    var plusObj = document.getElementById("plusSectionId");
    plusObj.style.display = 'none';
    if(dijit.byId('btnRestoreDefaultId') != null)
          dijit.byId('btnRestoreDefaultId').setAttribute('disabled', true);                 
    if(dijit.byId('btnRefreshId') != null)
      dijit.byId('btnRefreshId').setAttribute('disabled', true);        
    if(dijit.byId('btnSaveId') != null)
      dijit.byId('btnSaveId').setAttribute('disabled', true);       

}


 function submitForm(displayCheckBox) 
 {
if(<%=!(xmlFileSectionsList.contains(xmlName))%>)
    {
        var isNewTab  = '<logic:present name="isNewTab"><bean:write name="isNewTab"/></logic:present>';
        //alert(isNewTab + ":::::" + isNewTab.length  + ":::::" + (isNewTab == '') );
        var propAdded = false;
        if(dojo.byId('newDisplayName').value != "" &&  dojo.byId('newProperty').value != "" &&  dojo.byId('newPropertyVal').value != ""  &&  dojo.byId('newPropertyVal').value != "Add Value" && dojo.byId('newProperty').value != "Add Property" && dojo.byId('newDisplayName').value != "Add Display Name" )
        { 
            if(!chkForDuplicatePropName())
            {
                return ;
            }

            dojo.byId('newPropertyVal').name = dojo.byId('newProperty').value;
            dojo.byId('newDisplayName').name = dojo.byId('newProperty').value+"_displayName";
            document.getElementById("isVisible").name = dojo.byId('newProperty').value+"_isVisible";
            var newSelectObj = document.getElementById("fieldTypeSelect");
            var index = newSelectObj.selectedIndex;
            var fieldTypeText = newSelectObj.options[index].text;
            document.getElementById("newFieldType").name = dojo.byId('newProperty').value+"_fieldType";         
            document.getElementById("newFieldType").value = fieldTypeText;
            if(displayCheckBox) 
                document.getElementById("newPropertyServerSpecific").name = dojo.byId('newProperty').value+"_serverSpecific";           
            propAdded = true;

        }       

    
        if(propAdded != true && isNewTab == '' && count == 1 && ( (dojo.byId('newProperty').value == "Add Property" || dojo.byId('newDisplayName').value == "Add Display Name" || dojo.byId('newPropertyVal').value == "Add Value") || dojo.byId('newProperty').value == "" || dojo.byId('newDisplayName').value == "" || dojo.byId('newPropertyVal').value == ""  )  )
        {
                if(!(dojo.byId('newProperty').value == "Add Property" && dojo.byId('newDisplayName').value == "Add Display Name" && dojo.byId('newPropertyVal').value == "Add Value")) 
                {
                  showEmptyDialog('<bean:message key="uiconfig.alert.enterPropNameVal" bundle="uiconfig"/>', "Manage Configurations");        
                  return;
                }
        }
        else if(propAdded != true && isNewTab == "true"  && count == 1 && ( (dojo.byId('newProperty').value == "Add Property" || dojo.byId('newDisplayName').value == "Add Display Name" || dojo.byId('newPropertyVal').value == "Add Value") || dojo.byId('newProperty').value == "" || dojo.byId('newDisplayName').value == "" || dojo.byId('newPropertyVal').value == ""  )  )
        {
                if(!(dojo.byId('newProperty').value == "Add Property" && dojo.byId('newDisplayName').value == "Add Display Name" && dojo.byId('newPropertyVal').value == "Add Value")) {
                
                  showEmptyDialog('<bean:message key="uiconfig.alert.enterPropNameVal" bundle="uiconfig"/>', "Manage Configurations");        
                  return;
                }
        }            
         
    }
    document.forms[0].action = "manageConfigAction.do?mode=save&tabValue=" + tabVal+"&xmlName=" + xmlName;
    for(count1=0;count1 < propNameArray.length; count1++ )  
    {
        var obj = document.getElementById(propNameArray[count1]+ '_isVisible');
        if(obj != null)
        {
            obj.disabled = "";
        }
        if(document.getElementById('sessionAlertTime')!= null){
        	 var dirformat = /^[0-9]+$/;
             var flag = dirformat.test(document.getElementById('sessionAlertTime').value);
             if(!flag ){
               showEmptyDialog("Please enter numeric value in Session Alert Time(Secs) ","Error");
               return false ;
              }  
             
        	maxSessionAlert='<%=session.getMaxInactiveInterval()%>';
            var newSessionVal = document.getElementById("sessionAlertTime").value;
		    var initDiff = maxSessionAlert - newSessionVal;
			var newDiff = maxSessionAlert - initDiff;
          		
            if((newDiff < 120)||(newDiff > 300)){
            	showEmptyDialog("Session alert time should be between 120-300 seconds","Error");
                return false ;
                break;
            }
        }
    }
    
    document.forms[0].submit();
   //document.getElementById('btnSaveId').disabled=true;
    dijit.byId('btnSaveId').setAttribute('disabled', true); 
 }
 
 function showAddProp() 
 {
    var obj = document.getElementById("newPropertyDiv");
    if(obj.style.display == 'block')
    {
        obj.style.display = 'none';
    }
    else
    {
            obj.style.display = 'block';
    }   
 } 
 
 function addProperty(displayName,prop,val,isvisible,serverSpecificStatus)
 {
 
  
    if(displayName== "" || prop == "" || val == "" || prop.toUpperCase() == "ADD PROPERTY" || displayName.toUpperCase() == "ADD DISPLAY NAME" || val.toUpperCase() == "ADD VALUE")     
    {
        //alert('<bean:message key="uiconfig.alert.enterPropNameVal" bundle="uiconfig"/>')
         showEmptyDialog('<bean:message key="uiconfig.alert.enterPropNameVal" bundle="uiconfig"/>', "Manage Configurations");        
         return;
    }
    else
    {
        if(serverSpecificStatus != null)     
         addRow( "propertiesTable",displayName,prop,val,isvisible.value,serverSpecificStatus,false);    
        else
         addRow( "propertiesTable",displayName,prop,val,isvisible.value,null,true);             
         dojo.byId('newDisplayName').value = "Add Display name";         
         dojo.byId('newProperty').value = "Add Property";
         document.getElementById('fieldTypeSelect').selectedIndex = 0;
         var obj  = document.getElementById("newPropertyTextSpan");  
      obj.innerText = '';
      obj.innerHTML = '<input type="text"  id="newPropertyVal" name="" maxlength="255" class="body" style="width:202px;"  trim="true"   value="Add Value" onfocus="javascript:selectVal(\'newPropertyVal\');" dojoType="dijit.form.ValidationTextBox" onclick="javascript:selectVal(\'newPropertyVal\');" onblur="isSplCharGivenExists(this.value,escapeChars);copyDefaultValue(this.id)"/>';
   
         dojo.byId('newPropertyVal').value = "Add Value";
         document.getElementById("isVisible").value='true';
        if(serverSpecificStatus != null)     
             document.getElementById("newPropertyServerSpecific").checked= false;
        //chkForDuplicatePropName(prop);         
        propNameArray.push(prop);     
        displayNameArray.push(displayName);  

    } 

 }
 
 
 function selectVal(tmp)
 {
    if(document.getElementById(tmp).value.toUpperCase() == "ADD DISPLAY NAME" || document.getElementById(tmp).value.toUpperCase() == "ADD PROPERTY" || document.getElementById(tmp).value.toUpperCase() == "ADD VALUE")
        document.getElementById(tmp).value = "";
        document.getElementById(tmp).select();
 }

  function selectTab(tmp)
 {
    if(document.getElementById(tmp).value == "Add Section")
        document.getElementById(tmp).value = "";

        document.getElementById(tmp).select();
 }
 
function copyDefaultValue(tmp)
{
    if(document.getElementById(tmp).id == "newDisplayName" && document.getElementById(tmp).value == "")
        document.getElementById(tmp).value = "Add Display Name";
    if(document.getElementById(tmp).id == "newProperty" && document.getElementById(tmp).value == "")
        document.getElementById(tmp).value = "Add Property";
    if(document.getElementById(tmp).id == "newPropertyVal" && document.getElementById(tmp).value == "")
        document.getElementById(tmp).value = "Add Value";
        
}
         
function selectPasswordVal(virtualPwd,valObj)
 {
    
    /*
    if(valObj.value == "")
     {
        valObj.setValue(virtualPwd);
     }
     else
     {
        valObj.setValue('');
     }  

*/
 }
 
 
 function addRow(in_tbl_name ,displayName,prop,val,isVisible,serverSpecificStatus,hideChkBx)
  {
 
var fieldTypeObj = document.getElementById('fieldTypeSelect')
   var  index = fieldTypeObj.selectedIndex;
  var fieldTypeVal = document.getElementById('fieldTypeSelect').options[index].text;

    var tbody = document.getElementById(in_tbl_name).getElementsByTagName("TBODY")[0];
    // create row
    var row = document.createElement("TR");
    // create table cell 1
    var td1 = document.createElement("TD")
    td1.setAttribute("align","left");
   // td1.setAttribute("padding-right","200px");

 //td1.setAttribute("width","1300px");
 
    var strHtml1 = '';
    var inputTypeVal = 'text';
    var fieldTypeValue = "";
if(fieldTypeVal.toUpperCase() == 'PASSWORD') 
{
    inputTypeVal = 'password';
    fieldTypeValue = '<input type="hidden" name="'+ prop +'_fieldType" value="password"/>';

}
 
//debugger;
if(!hideChkBx){
 if(serverSpecificStatus == false)
  {

    strHtml1 = '<td height="27" colspan="3"  nowrap="nowrap">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text"  style="width:202px;" readonly="readonly" name="' + prop + "_displayName" + '" title="'+displayName +'" value="' + displayName.substring(0,35)+'" />:&nbsp;&nbsp;&nbsp;&nbsp;<input type="text"  style="width:202px;" readonly="readonly" title="'+prop +'" value="'+prop.substring(0,35) +'"</input>:&nbsp;<input type="' + inputTypeVal+ '"  onfocus="onFocusSelectText(this,\'dynamic\');"  onblur="return chkBlankText(this,\'dynamic\');"  id="" name="'+ prop+'" class="body" readonly="readonly" style="width:202px;"  dojoType="dijit.form.ValidationTextBox" required="true" trim="true" maxlength="255" ucfirst="true"   value="'+ val +'" />&nbsp;<input type="checkbox"  id=""  name="' + prop + "_serverSpecific" + '"  />&nbsp;&nbsp;&nbsp; <a href="#" onclick=\'javascript:delRow(" + prop +");\'><img src="<%=ServerUtils.getContextName(request)%>/images/portal/icon_dash_1.png" width="9px" height="9px" / ></a><input type="hidden" name="' +prop +"_isVisible" +'"  value="true"  />' + fieldTypeValue+    '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>';    
         
  }else{

    strHtml1 = '<td height="27" colspan="3"  nowrap="nowrap" style="padding-right:200px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text"  style="width:202px;" readonly="readonly" name="' + prop + "_displayName" + '" title="'+displayName +'" value="' + displayName.substring(0,35)+'" />:&nbsp;&nbsp;&nbsp;&nbsp;<input type="text"  style="width:202px;" readonly="readonly" title="'+prop +'" value="'+prop.substring(0,35) +'"</input>:&nbsp;<input type="' + inputTypeVal+ '" onfocus="onFocusSelectText(this,\'dynamic\');"  onblur="return chkBlankText(this,\'dynamic\');"  id="" name="'+ prop+'" class="body" readonly="readonly" style="width:202px;"  required="true" trim="true" maxlength="255" ucfirst="true"   value="'+ val +'" />&nbsp;<input type="checkbox" checked="checked" id=""  name="' + prop + "_serverSpecific" + '" dojoType="dijit.form.CheckBox"  />&nbsp;&nbsp;&nbsp; <a href="#" onclick=\'javascript:delRow(" + prop +");\'><img src="<%=ServerUtils.getContextName(request)%>/images/portal/icon_dash_1.png" width="9px" height="9px" / ></a><input type="hidden" name="' +prop +"_isVisible" +'"  value="true"  />' + fieldTypeValue+    '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>';    
}
}else
{
        strHtml1 = '<td height="27" colspan="3"  nowrap="nowrap" style="padding-right:200px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text"  style="width:202px;" readonly="readonly" name="' + prop + "_displayName" + '" title="'+displayName +'" value="' + displayName.substring(0,35)+'" />:&nbsp;&nbsp;&nbsp;&nbsp;<input type="text"  style="width:202px;" readonly="readonly" title="'+prop +'" value="'+prop.substring(0,35) +'"</input>:&nbsp;<input type="' + inputTypeVal+ '" onfocus="onFocusSelectText(this,\'dynamic\');"  onblur="return chkBlankText(this,\'dynamic\');"  id="" name="'+ prop+'" class="body" readonly="readonly" style="width:202px;" required="true" trim="true" maxlength="255" ucfirst="true"   value="'+ val +'" />&nbsp;&nbsp;&nbsp; <a href="#" onclick=\'javascript:delRow(" + prop +");\'><img src="<%=ServerUtils.getContextName(request)%>/images/portal/icon_dash_1.png" width="9px" height="9px" / ></a><input type="hidden" name="' +prop +"_isVisible" +'"  value="true"  />' + fieldTypeValue+ '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>';    

}

 
    td1.innerHTML = strHtml1; 
    // append data to row 
    row.appendChild(td1);
     // add to count variable
    count = parseInt(count) + 1;
    // append row to table
    tbody.appendChild(row);
  }
  function delRow(propName)
  {
    for(var count = 0;count < propNameArray.length ; count++)
      {
             if(propName != null && propName.name != null)
             if(propName.name == propNameArray[count])
             {
                 propNameArray[count] = '';
                 break;
             }
    
      }  
      
     var displayName = propName.name + "_displayName";      
     var tmpDisplayNameObj = document.getElementById(displayName);
     var tmpDisplayNameVal = "";
     if(tmpDisplayNameObj != null)
        tmpDisplayNameVal =  tmpDisplayNameObj.value;
     tmpDisplayNameVal = tmpDisplayNameVal.substring(0,35);
    for(var count = 0;count < displayNameArray.length ; count++)
      {     
             if(tmpDisplayNameVal != null)
             if(tmpDisplayNameVal == displayNameArray[count])
             {
                 displayNameArray[count] = '';
                 break;
             }
    
      }       
      
    var current = window.event.srcElement;
    count = parseInt(count) - 1;
    //here we will delete the line
    while ( (current = current.parentElement)  && current.tagName !="TR");
         current.parentElement.removeChild(current);

  }




function submitToHome() 
{ 
document.forms[0].action ='<%=ServerUtils.getContextName(request)%>/adminMain.do'; 
if(newXmlName != 'null'   )
{

  // set the content of the dialog:
    var data2Display = 'Do you want to save ' + newXmlName + ' tab ?';
  if(confirmationBox != null) { 
      confirmationBox.attr("title",  'Manage Configurations');
      confirmationBox.attr("content", "<center><table ><tr><td align=center></tr><tr><td align='center'>"+data2Display+"</td></tr><tr><td align='center'>  <button dojoType='dijit.form.Button' onClick='doconfirmation()' type='button'>Yes</button> <button dojoType='dijit.form.Button' onClick='document.forms[0].submit()' type='button' >No</button></td></td></tr></table></center>");
      confirmationBox.show();
  }
    
} 
    else{
    document.forms[0].submit();
      }

}


function chkNewXMlNameExists() 
{
    if(newXmlName != 'null'   )
    {
          showEmptyDialog('Please click save button', "Manage Configurations");        
          return false;
    }
    else
    {
        return true ;
    }    
}



function submitTab(url)
{    
  document.forms[0].action = url;
  if(newXmlName != 'null')
  {
  // set the content of the dialog:
    var data2Display = 'Do you want to save ' + newXmlName + ' tab ?';
  if(confirmationBox != null) { 
      confirmationBox.attr("title",  'Manage Configurations');
      confirmationBox.attr("content", "<center><table ><tr><td align=center></tr><tr><td align='center'>"+data2Display+"</td></tr><tr><td align='center'>  <button dojoType='dijit.form.Button' onClick='doconfirmation()' type='button'>Yes</button> <button dojoType='dijit.form.Button' onClick='document.forms[0].submit()' type='button'>No</button></td></td></tr></table></center>");
      confirmationBox.show();
  }
    
} 
else{
        document.forms[0].submit();
      }

}


function submitTabForHome(url)
{ 
document.forms[0].action ='<%=ServerUtils.getContextName(request)%>/adminMain.do';
if(newXmlName != 'null')
{
        
  // set the content of the dialog:
    var data2Display = 'Do you want to save ' + newXmlName + ' tab ?';
  if(confirmationBox != null) { 
      confirmationBox.attr("title",  'Manage Configurations');
      confirmationBox.attr("content", "<center><table ><tr><td align=center></tr><tr><td align='center'>"+data2Display+"</td></tr><tr><td align='center'>  <button dojoType='dijit.form.Button' onClick='doconfirmation()' type='button'>Yes</button> <button dojoType='dijit.form.Button' onClick='document.forms[0].submit()' type='button' >No</button></td></td></tr></table></center>");
      confirmationBox.show();
  }
    
} 
else{
    document.forms[0].submit();
      }

}


function changeMinusStatus(id,imgId)
{
var checkBoxObj = document.getElementById(id);
var imageObj = document.getElementById(imgId);
    if(tabVal > 2)
        if(checkBoxObj.checked)
        {
            imageObj.style.display = "block";
        }else
        {
            imageObj.style.display = "none";    
        }
}


function removeProperty(markedForDelete,chkBoxDiv,rowNum, divId){
    
/*  
    var key = "";
    if(markedForDelete.indexOf("_markedForDelete") != -1)
    {
        key = markedForDelete.substring(0,markedForDelete.indexOf("_markedForDelete"));
    }

    for(var count = 0;count < propNameArray.length ; count++)
      {
             if(key == propNameArray[count])
             {
                 propNameArray[count] = '';
                 break;
             }
    
      }  
      
     var displayName = key + "_displayName";      
     var tmpDisplayNameObj = document.getElementById(displayName);
     var tmpDisplayNameVal = "";
     if(tmpDisplayNameObj != null)
        tmpDisplayNameVal =  tmpDisplayNameObj.value;
     tmpDisplayNameVal = tmpDisplayNameVal.substring(0,35);
    for(var count = 0;count < displayNameArray.length ; count++)
      {     
             if(tmpDisplayNameVal != null)
             if(tmpDisplayNameVal == displayNameArray[count])
             {
                 displayNameArray[count] = '';
                 break;
             }
      }  
      */      
    var mainTable = document.getElementById(divId);
    var row = document.getElementById(rowNum);
    
    <%String apptheme = AdminConfigUtils.getApplicationTheme();
    if (apptheme.equals("blue")) {%>
        row.setAttribute("bgColor","#d0e3f5");
    <%}
    else{%>
        row.setAttribute("bgColor","#DCDADA");
   <% }%>
   
    row.setAttribute("title","Marked for delete");
    var chkBoxDivObj = document.getElementById(chkBoxDiv);
    chkBoxDivObj.style.display = 'none';
    var minusImgObj = document.getElementById(rowNum+'_img');
    minusImgObj.style.display = 'none';
    var plusImgObj = document.getElementById(rowNum+'_imgPlus');
    plusImgObj.style.display = 'block';
    var markedForDeleteObj = document.getElementById(markedForDelete);
    markedForDeleteObj.value = 'yes';
    //mainTable.removeChild(row); 
}  
 
function undoDelete(markedForDelete,chkBoxDiv,rowNum, divId){

/*
    var key = "";
    if(markedForDelete.indexOf("_markedForDelete") != -1)
    {
        key = markedForDelete.substring(0,markedForDelete.indexOf("_markedForDelete"));
    }
    var propExists = false;
    for(var count = 0;count < propNameArray.length ; count++)
    {
             if(key.substring(0,35) == propNameArray[count])
             {
                propExists = true;
                 break;
             }
    }  
    
    if(!propExists) 
    {
        propNameArray.push(key);
    }
    else
    {
       // showEmptyDialog("Entered property name already exist", "Manage Configurations");      
       // return false;           
    }   
    
      
     var displayName = key + "_displayName";      
     var tmpDisplayNameObj = document.getElementById(displayName);
     var tmpDisplayNameVal = "";
     if(tmpDisplayNameObj != null)
        tmpDisplayNameVal =  tmpDisplayNameObj.value;
     tmpDisplayNameVal = tmpDisplayNameVal.substring(0,35);
     var displayNameExists = false;
    for(var count = 0;count < displayNameArray.length ; count++)
      {     
             if(tmpDisplayNameVal != null)
             if(tmpDisplayNameVal == displayNameArray[count])
             {
                displayNameExists = true;
                 break;
             }
      }       
    
    if(!displayNameExists)  
    {
        displayNameArray.push(tmpDisplayNameVal);  
    }
    else
    {
       // showEmptyDialog("Entered display name already exist", "Manage Configurations");   
      //  return false;
    }
    */
    
//      propNameArray.push(key);     
//      displayNameArray.push(tmpDisplayNameVal);  



    var mainTable = document.getElementById(divId);
    var row = document.getElementById(rowNum);
    row.setAttribute("bgColor","white");
    row.setAttribute("title","");   
    var chkBoxDivObj = document.getElementById(chkBoxDiv);
    chkBoxDivObj.style.display = 'block';
    var minusImgObj = document.getElementById(rowNum+'_img');
    minusImgObj.style.display = 'block';
    var plusImgObj = document.getElementById(rowNum+'_imgPlus');
    plusImgObj.style.display = 'none';
    var markedForDeleteObj = document.getElementById(markedForDelete); 
    markedForDeleteObj.value = 'no';
    //mainTable.removeChild(row); 
}     

function refreshConfig(){
  document.forms[0].action = "manageConfigAction.do?mode=refreshConfig&tabValue=" + tabVal+"&xmlName=" + xmlName;
  document.forms[0].submit();
  dijit.byId('btnRefreshId').setAttribute('disabled', true);              
}  

function restoreDefault(){
confirmationDialog('Are you sure to restore defaults properties and delete existing properties for ' + xmlName + ' tab?');
 }  
 
 /* do delete method called from confirmation dialog JS code available from windows.js if user clicks on yes */
function dodelete(){
    
document.forms[0].action = "manageConfigAction.do?mode=restoreDefaults&tabValue=" + tabVal+"&xmlName=" + xmlName;
              document.forms[0].submit();
              dijit.byId('btnRestoreDefaultId').setAttribute('disabled', true);               
              return;
}

function deleteTab(){
      
  // set the content of the dialog:
    var data2Display = 'Are you sure to delete ' + xmlName.substring(0,20) + ' tab?';   

  if(confirmationBox != null) { 
      confirmationBox.attr("title",  'Manage Configurations');
      confirmationBox.attr("content", "<center><table ><tr><td align=center></tr><tr><td align='center'>"+data2Display+"</td></tr><tr><td align='center'>  <button dojoType='dijit.form.Button' onClick='doConfirmDelete(this)' name='btnYes' type='button'>Yes</button> <button dojoType='dijit.form.Button' onClick='doConfirmDelete(this);' type='button' name='btnNo'>No</button></td></td></tr></table></center>");
      confirmationBox.show();
  }

    //  document.forms[0].submit();

     
 }  
 
function doConfirmDelete(tmp) {
    confirmationBox.hide();
    if(tmp.name == 'btnYes'){
        document.forms[0].action = "manageConfigAction.do?mode=deleteTab&tabValue=" + tabVal+"&xmlName=" + xmlName;     
        document.forms[0].submit();
        dijit.byId('btnDeleteTab').setAttribute('disabled', true);                      
    }
    return;
}


function changeFieldType(fieldTypeObj)
{ 
     var  index = fieldTypeObj.selectedIndex;
     var fieldTypeVal = document.getElementById('fieldTypeSelect').options[index].text;
     var newPropObj = document.getElementById("newPropertyVal");
      var obj  = document.getElementById("newPropertyTextSpan");    
      var newFieldTypeObj = document.getElementById("newFieldType");
             
     if(fieldTypeVal.toUpperCase() == 'PASSWORD')
     {
      obj.innerText = "";
      obj.innerHTML = '<input type="password"  id="newPropertyVal" name="" maxlength="255" class="body" style="width:202px;"  trim="true"   value="" onfocus="javascript:selectVal(\'newPropertyVal\');" dojoType="dijit.form.ValidationTextBox" onclick="javascript:selectVal(\'newPropertyVal\');" onblur="isSplCharGivenExists(this.value,escapeChars);"/>';
      newFieldTypeObj.value = "password";

     }
     else
     {
      obj.innerText = '';
      obj.innerHTML = '<input type="text"  id="newPropertyVal" name="" maxlength="255" class="body" style="width:202px;"  trim="true"   value="Add Value" onfocus="javascript:selectVal(\'newPropertyVal\');" dojoType="dijit.form.ValidationTextBox" onclick="javascript:selectVal(\'newPropertyVal\');" onblur="isSplCharGivenExists(this.value,escapeChars);copyDefaultValue(this.id)"/>';
      newFieldTypeObj.value = "";
     }
}
 

</script> 
<style type="text/css">

 a:active {
    outline: none;
    color:#000000;
}
a:focus {
    -moz-outline-style: none;
    color:#000000;
}

#tabs_container1 {
    width: 1000px;
    font-family: Tahoma;
    font-size: 12px; 
    font-weight: bold;    
    color:#000000;
}

#tabs_container ul.tabs {
    list-style: none;
    border-bottom: 1px solid #ccc;
    height: 21px;
    margin: 0;
    font-family: Tahoma;
    font-size: 12px; 
    font-weight: bold;       
    color:#000000;
    
}
#tabs_container ul.tabs li {
    float: left;
    font-family: Tahoma;
    font-size: 12px; 
    font-weight: bold;    
    color:#000000;
    
}
#tabs_container ul.tabs li a {
    padding: 3px 10px;
    display: block;
    border-left: 1px solid #ccc;
    border-top: 1px solid #ccc;
    border-right: 1px solid #ccc;
    margin-right: 2px;
    text-decoration: none;

}
#tabs_container ul.tabs li.active a {
    background-color: #fff;
    padding-top: 4px;
    font-family: Tahoma;
    font-size: 12px; 
    color:#000000;
    font-weight: bold;    
}
div.tab_contents_container {

    border-left: 1px solid #ccc;
    border-bottom: 1px solid #ccc;
    border-right: 1px solid #ccc;   
    padding: 10px;
}
div.tab_contents {
    display: none;
}
div.tab_contents_active {
border: 0px  #ccc;
    display: block;
}
div.clear {
    clear: both;
} 

</style>

</head>     
<% try { %>
<body onLoad='validateAndExtendSession()' class="tundra bodybg"    >
<form  action="manageConfigAction.do" method="POST"  >
<table width="101.2%" cellspacing="0" cellpadding="0" align="center"  border="0" >
            <tr>
                <td style="padding-left:67px;padding-top:37px;" class="pageTitle paddingTitle">      
                       <bean:message key="uiconfig.manageConfig" bundle="uiconfig"/>
                </td>
              </tr>
              <tr>
                <td class="paddingTitleDesc" style="width:700px">
                   <strong> <bean:message key="uiconfig.title.description" bundle="uiconfig"/> </strong>                 
                </td>  
              </tr>
              <tr>
                <td style="padding-left:67px; width:800px;"> 
                                   <font color="blue" style="font-family: Tahoma;font-size: 12px;" ><b>
                                    <html:messages id="saveStatus" message="true" bundle="uiconfig"><bean:write name="saveStatus" /></html:messages>
                                    </b></font> 
                                    <font color="red" style="font-family: Tahoma;font-size: 12px;"><b><html:errors bundle="uiconfig"/></b></font> 
                </td>  
              </tr>
              <tr>
                <td style="padding-top:30px;padding-left:67px;">
                                                         <table width="1000" cellspacing="0" cellpadding="0" style="align:left;" >
                                                                <td>
                                                                  <strong> 
                                                                    <div id="tabs_container" style="align:left;">
                                                                    <table width="1000px" cellpadding="0" cellspacing="0" border="0">
                                                                            <tr>
                                                                            <td style="align:left;">
                                                                            <ul class="tabs">
                                                                            <logic:present name="tabsLst">
                                                                                <logic:notEqual name="tabsLst"  value="null">
                                                                                     <logic:iterate id="tabsLst1" name="tabsLst" >
                                                                                             <logic:equal name="tabsLst1" property="configDetailBeanValue"  
                                                                                             value="<%=tabValue%>" >
                                                                                                  <li class="active"><font color="#000000"><script> xmlName='<bean:write name="tabsLst1" property="configDetailBeanName" />'</script>
                                                                                             </logic:equal> 
                                                                                             <logic:notEqual name="tabsLst1" property="configDetailBeanValue"  
                                                                                                 value="<%=tabValue%>"  ><li class="tab panelColor"></logic:notEqual> 
                                                                                             <%
                                                                                                    com.spacetimeinsight.admin.uiconfig.bean.ConfigDetailBean configDetailBean = (com.spacetimeinsight.admin.uiconfig.bean.ConfigDetailBean) pageContext.getAttribute("tabsLst1");
                                                                                                    String tabNme = configDetailBean.getConfigDetailBeanName();     
                                                                                                    String tabNme1 = configDetailBean.getConfigDetailBeanName();    
                                                                                                    String tabValue1 = configDetailBean.getConfigDetailBeanValue();    
                                                                                                    if(tabNme != null && tabNme.length() > 20)
                                                                                                        tabNme = tabNme.substring(0,20);
                                                                                                %> <script>tabNameArray[i]='<%=tabNme1%>';i=i+1;</script>
                                                                                                   <script>newTabVal='<%=tabValue1%>';</script>
                                                                                                <a href='javascript:submitTab("<%=ServerUtils.getContextName(request)%>/manageConfigAction.do?xmlName=<bean:write name="tabsLst1"    property="configDetailBeanName" />&tabValue=<bean:write name="tabsLst1" property="configDetailBeanValue" />");' title='<bean:write name="tabsLst1" property="configDetailBeanName" />'  class="tab" ><b><font style="color:#000000;"><%=tabNme%></font></b></a></li>
                                                                                     </logic:iterate>
                                                                                </logic:notEqual>
                                                                            </logic:present>            
                                                                <%
                                                    if(tabsLst != null) { 
                                                              /*  if(newTabValue != null && newXmlName != null)
                                                                { String urlNew = "<%=ServerUtils.getContextName(request)%>/manageConfigAction.do?tabValue=" + newTabValue +"&xmlName=" + newXmlName;
                                                                   String tabDisplayNme = newXmlName;
                                                                   if(newXmlName != null && newXmlName.length() > 20)
                                                                        tabDisplayNme = newXmlName.substring(0,20);  
                                                                    newXmlName = tabDisplayNme;
                                                                %>
                                                                        <li class="active">
                                                                              <a href="" onclick="javascript:showEmptyDialog('<bean:message key="uiconfig.alert.saveTab" bundle="uiconfig"/>','Manage Configurations');return false;" title="<%=newXmlName%>"><font style="color:#000000;"><%=tabDisplayNme%></font></a>
                                                                        </li>
                                                           <%} */%>
                                                                
                                                                  <li class="tab panelColor" valign="middle" nowrap="nowrap">
<a id="sectionHrefId" href='javascript:showTabSection();' class="tab" onClick="javascript:showTabSection();" 
>
 <div id="newSectionId" style="display:none;align:left;" >
         <input type="text"  id="newSection" name="" class="body" maxlength="255" style="width:100px;height:20px;" dojoType="dijit.form.ValidationTextBox" required="true" trim="true" ucfirst="true" value='<bean:message key="validation.msg.addsection" bundle="splchvalidation"/>' onFocus="javascript:selectVal('newSection');" onClick="javascript:selectTab('newSection');" onBlur="chkForTabName(this.value);" />
    <img src="<%=ServerUtils.getContextName(request)%>/images/portal/icon_plus_1.png" title='<bean:message key="validation.msg.addsection" bundle="splchvalidation"/>' width="9px" height="9px" align="middle" onclick='javascript:submitUrl(document.getElementById("newSection").value);' />                       
    </div>
<div id="plusSectionId" style="align:left;">
    <img src="<%=ServerUtils.getContextName(request)%>/images/portal/icon_plus_1.png" title="Add Section" width="9px" height="9px" align="middle"   />
</div>  
</a>
                                                                                                        </li>
                                                                <%}%>   
                                                                </ul>
                                                                    </td></tr></table>                                           
                                                                   </strong>
                                                                  </div>
                                                                 <div class="clear"></div>
                                                                 <div class="tab_contents_container" style="background:#ffffff;">
        
                                                                <table border="0" width="1000"  height="54" cellspacing="0" cellpadding="0" >
                                                                    <tr>
                                                                        <td height="27" align="left" width="" nowrap="nowrap">
                   <div id="tab_contents tab_contents_active" style="display:block" 
        class="tab_contents tab_contents_active manageConfigurationTabscrollbar">    <script type="">var escapeChars = "\',\"";</script>
                                                                            <table width="1000px" border="0">
                                                                                <tbody>
                                                                                <tr><td>
                                                                                <%if(!(xmlFileSectionsList.contains(xmlName))){%>
                                                                                 <table width="1000px"  border="0" id="propertiesTable">
                                                                                    <tr>
                                                                                        <td colspan="3" style="padding-right:67px;width:1000px;">
                                                                                                <table  width="1000px">
                                                                                                    <tr >
                                                                                                        <td class="tab panelColor" colspan="3" style="padding-left:73px;width:1000px;" nowrap="nowrap">
                                                                                                            <label  class="label" >Display Name</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

                                                                                                            <label  class="label" >Property Name</label>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;                                                                                                        
                                                                                                            <label  class="label" >Field Type</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                                                                            <label  class="label" >Property Value </label>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;                                                                                                             &nbsp;&nbsp;&nbsp;&nbsp;                                                                                           
                                                                                    <%if(displayCheckBox){%>                        <label  class="label" >Server Specific </label> <%}%>                                                                                                       
                                                                                                        </td>   
                                                                                                    </tr>   
                                                                                                </table>    
                                                                                        </td>   
                                                                                    </tr>   
                                                                                    <tr>
                                                                                         <td height="27" align="left" style="padding-left:73px;" colspan="3" nowrap="nowrap" >
                                                                <input type="text"  id="newDisplayName" name="" class="body" style="width:202px"  
                                                                 dojoType="dijit.form.ValidationTextBox" maxlength="255"  trim="true" ucfirst="true"   value="Add Display Name" onFocus="javascript:selectVal('newDisplayName');" onClick="javascript:selectVal('newDisplayName');"  onblur="isSplCharGivenExists(this.value,escapeChars);copyDefaultValue(this.id);"/>:
&nbsp;<input type="text"  id="newProperty" name="" class="body" style="width:202px"  
                                                                 dojoType="dijit.form.ValidationTextBox" maxlength="255"  trim="true" ucfirst="true"   value="Add Property" onFocus="javascript:selectVal('newProperty');" onClick="javascript:selectVal('newProperty');"  onblur="isSplCharGivenExists(this.value,escapeChars);copyDefaultValue(this.id);"/>:
<select  id="fieldTypeSelect"  name=""  class="body" width="240px"   title="Field Type" onChange="changeFieldType(this);" >
                                                                            <logic:present name="fieldTypeList">
                                                                                <logic:notEqual name="fieldTypeList"  value="null">
                                                                                     <logic:iterate id="fieldTypeList1" name="fieldTypeList">
                                                                                        <option value='<bean:write name="fieldTypeList1"    property="fieldTypeId" />' ><bean:write name="fieldTypeList1"    property="fieldType" /></option>  
                                                                                     </logic:iterate>
                                                                                </logic:notEqual>
                                                                            </logic:present>   
                                                                </select>:                                                               
            <span id="newPropertyTextSpan"><input type="text"  id="newPropertyVal" name="" maxlength="255" class="body" style="width:202px;"  trim="true"   value="Add Value" onFocus="javascript:selectVal('newPropertyVal');" dojoType="dijit.form.ValidationTextBox" onClick="javascript:selectVal('newPropertyVal');" onBlur="isSplCharGivenExists(this.value,escapeChars);copyDefaultValue(this.id);validInput('newPropertyVal')"/></span>
                                                                <input type="hidden" id="newFieldType" name="" value="text" />
                                                                <label class="error" title="Display name, property name, property value are mandatory ">*</label>
<%if(displayCheckBox){%>                        
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input  type="checkbox" id="newPropertyServerSpecific"  name=""/>
                                                                
<a href="#" onClick="javascript:if(chkForDuplicatePropName()){addProperty(dojo.byId('newDisplayName').value,dojo.byId('newProperty').value,dojo.byId('newPropertyVal').value,document.getElementById('isVisible'),document.getElementById('newPropertyServerSpecific').checked);}" ><img src="<%=ServerUtils.getContextName(request)%>/images/portal/icon_plus_1.png" width="9px" height="9px" title="Add property"/ ></a>

<%}else{%>                                                                
<a href="#" onClick="javascript:if(chkForDuplicatePropName()){addProperty(dojo.byId('newDisplayName').value,dojo.byId('newProperty').value,dojo.byId('newPropertyVal').value,document.getElementById('isVisible'),null);}" ><img src="<%=ServerUtils.getContextName(request)%>/images/portal/icon_plus_1.png" width="9px" height="9px" title="Add property"/ ></a>

<%}%>

        <select  id="isVisible"  name=""  class="body" width="250px"   autoComplete="false" title="Visibility" style="visibility:hidden;">
            <option selected="selected" value="true" >true</option>
            <option value="false" >false</option>  
        </select>  
                                                                                        </td>   
                                                                                    </tr>   
                                                                                </table> 
                                                                               <%}%>
                                                                                </td></tr> 
                                                                                 <tr><td align="left">                            
                                                                                    <table width="1000px" border="0"><tbody id="savedPropsTable" >
                                                                                    <%if(xmlFileSectionsList.contains(xmlName)){%>
                                                                                    <tr>
                                                                                        <td colspan="3" width="1000px">
                                                                                                <table width="100%" border="0">
                                                                                                    <tr>
                                                                                                        <td class="tab panelColor" colspan="3" style="padding-left:10px;width:1000px;">
                                                                                                            <label  class="label" >Display Name</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                                                                            <label  class="label" >Property Value </label>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;                          
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;                          
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                        <%if(displayCheckBox){%>                <label  class="label" >Server Specific </label>     <%}%>
                                                                                                        </td>   
                                                                                                    </tr>   
                                                                                                </table>    
                                                                                        </td>   
                                                                                    </tr>   
                                                                                                    <%}%> 
                                                                                    
<%
                                                                                              if(sectionsLstNtVisi != null && sectionsLstNtVisi.size() > 0)
                                                                                                {   
                                                                                                    for(int count = 0; count < sectionsLstNtVisi.size();count++)
                                                                                                    {   ConfigDetailBean fileDetail = (ConfigDetailBean)sectionsLstNtVisi.get(count) ;
                                                                                                        
                                                                                                        String key =    fileDetail.getConfigDetailBeanName()  != null ? fileDetail.getConfigDetailBeanName().trim() : "";
                                                                                                        String displayName =    fileDetail.getDisplayName();

                                                                                                     String tmpDisplayName = displayName;
                                                                                                    if(displayName != null && displayName.length() >35 )    
                                                                                                    {
                                                                                                            tmpDisplayName = displayName.substring(0,35); 
                                                                                                    } 

                                                                                                        
                                                                                            %>   
                                                                                              <script type="">propNameArray.push( '<%=key%>');</script>
                                                                                            <script type="">displayNameArray.push( '<%=tmpDisplayName%>');</script>    
                                                                                                

                                                                                                <%
                                                                                                    }
                                                                                                }
                                                                                                %>
           
<%
                                                                                              if(sectionsLst != null && sectionsLst.size() > 0)
                                                                                                {   
                                                                                                    for(int count = 0; count < sectionsLst.size();count++)
                                                                                                    {   ConfigDetailBean fileDetail = (ConfigDetailBean)sectionsLst.get(count) ;
                                                                                                        String key =    fileDetail.getConfigDetailBeanName().trim();
                                                                                                        String value = fileDetail.getConfigDetailBeanValue() != null ? fileDetail.getConfigDetailBeanValue().trim() : "";
                                                                                                        String path = fileDetail.getConfigDetailBeanPath() != null ? fileDetail.getConfigDetailBeanPath().trim() : "";
                                                                                                        String displayName =    fileDetail.getDisplayName();
                                                                                                        String virtualPassword =    fileDetail.getVirtualPassword();
                                                                                                        Boolean isVisible = fileDetail.getIsVisible();
                                                                                                        boolean isServerSpecific = fileDetail.getIsServerSpecific() != null && fileDetail.getIsServerSpecific().booleanValue();
                                                                                                        boolean isPasswordField = fileDetail.getFieldType() != null && fileDetail.getFieldType().trim().equalsIgnoreCase("password")?true:false;                                                                                                        

                                                                                                        String propName = key;
                                                                                                    if(path.equals(""))
                                                                                                    {
                                                                                                        //path = "";
                                                                                                        
                                                                                                    }
                                                                                                    else if(key.equals("name"))  
                                                                                                    {
                                                                                                        key = value;
                                                                                                        ConfigDetailBean fileDetailNxt = (ConfigDetailBean)sectionsLst.get(count + 1) ;
                                                                                                        value = fileDetailNxt.getConfigDetailBeanValue().trim();
                                                                                                        count++;    
                                                                                                    }
                                                                                                    if(key != null && key.length() >35 )    
                                                                                                    {
                                                                                                            propName = key.substring(0,35); 
                                                                                                    } 

                                                                                                    String tmpDisplayName = displayName;
                                                                                                    if(displayName != null && displayName.length() >35 )    
                                                                                                    {
                                                                                                            tmpDisplayName = displayName.substring(0,35); 
                                                                                                    } 
                                                                                                    propertyExists = true;
                                                                                            %> 
                                                                                            <script type="">propNameArray.push( '<%=key%>');</script>
                                                                                            <script type="">displayNameArray.push( '<%=tmpDisplayName%>');</script>    
                                                                                                <tr id="propRow<%=count%>">
                                                                                                    <td height="27" align="right" valign="middle" width="170px" nowrap="nowrap"><label  class="label" title="Display name :<%=displayName%>"><%=tmpDisplayName%></label>:                  <input type="hidden" name="<%=key%>_displayName" value="<%=displayName%>" /><input type="hidden" name="<%=key%>_isVisible" value="<%=isVisible%>" />
<input type="hidden" name="<%=key%>_markedForDelete" value="no" />
</td>
                                                                                                    <td height="27" align="left"  valign="middle"  width="340px" nowrap="nowrap" colspan="1">  
<%
//boolean isPwdField =(key!=null && (key.toLowerCase().indexOf("password")>=0 || key.toLowerCase().endsWith("pwd")));                 
    if(value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false")) 
    {
    

%>
        <select  id="<%=key%>"  name="<%=key%>"  class="body" width="350px"   autoComplete="false">
            <option  value="true" <%if(value.equalsIgnoreCase("true")){%>selected<%}%> >true</option>
            <option value="false" <%if(value.equalsIgnoreCase("false")){%>selected<%}%>>false</option>
        </select>   
 <%
    }else if(isPasswordField) {%>
         <input type="hidden" id="<%=key%>" name="<%=key%>" value="<%=value%>"/>     
         <input type="hidden" id="<%=key%>_virtualPassword" name="<%=key%>_virtualPassword" value="<%=virtualPassword%>"/>       
         <input type="hidden" name="<%=key%>_fieldType" value="password"/>     
         <input type="password" id="<%=key%>_userPassword" name="<%=key%>_userPassword" class="body" style="width:340px;" maxlength="255"   
         dojoType="dijit.form.ValidationTextBox" <%if(!(xmlFileSectionsList.contains(xmlName))){%>required="true" <%}%> trim="true" ucfirst="true"   value="<%=virtualPassword%>" onfocus="return onFocusSelectText(this);" <%if(xmlFileSectionsList.contains(xmlName)){%>onblur="isSplCharGivenExists(this.value,escapeChars);" <%}else{%>onblur="chkBlankText(this);return isSplCharGivenExists(this.value,escapeChars);" 
<%}%> />       
<%}else{%>                                                                                  
        <input type="text"  id="<%=key%>" name="<%=key%>" class="body" style="width:340px;" maxlength="255"  title="Value :<%=value%>" onBlur= "validInput('<%=key%>')"
         dojoType="dijit.form.ValidationTextBox" <%if(!(xmlFileSectionsList.contains(xmlName))){%>required="true" <%}%> trim="true" ucfirst="true"   value="<%=value%>" onfocus="return onFocusSelectText(this);" <%if(xmlFileSectionsList.contains(xmlName)){%>onblur="isSplCharGivenExists(this.value,escapeChars);" <%}else{%>onblur="return chkBlankText(this);isSplCharGivenExists(this.value,escapeChars);" <%}%> />    
<%}%>
</td><td nowrap="nowrap" width="460px;height:10px" align="left" valign="middle">
<div id="<%=key%>_chkBoxDiv" style="width:100px;">
<table border="0" height="8px"><tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td align="center" valign="middle">

<%
if(displayCheckBox)
if( isServerSpecific)
    {
%>
<input type="checkbox" id="<%=key%>_serverSpecific"   name="<%=key%>_serverSpecific" title="Server specific" checked='checked' />

<%
    }else{
%>
<input type="checkbox" id="<%=key%>_serverSpecific"  name="<%=key%>_serverSpecific" title="Server specific" />
<%
    }
%>
</td>
<td  valign="middle" >
        <%if(!(xmlFileSectionsList.contains(xmlName))){%>
<img src="<%=ServerUtils.getContextName(request)%>/images/portal/icon_dash_1.png" width="9px" align="middle" title="Delete property" style="cursor:pointer;display:block;" id="propRow<%=count%>_img" onClick="removeProperty('<%=key%>_markedForDelete','<%=key%>_chkBoxDiv','propRow<%=count%>', 'savedPropsTable');"/ >
        <%}%>
</td></tr></table>
</div>    
<table><tr><td align="center" valign="top" width="20px" style="cursor:pointer;display:none;" id="propRow<%=count%>_imgPlus">
            <img src="<%=ServerUtils.getContextName(request)%>/images/portal/undo.png" width="9px" title="Undo delete" height="10px"  onclick="undoDelete('<%=key%>_markedForDelete','<%=key%>_chkBoxDiv','propRow<%=count%>', 'savedPropsTable' );"/ >       
</td></tr></table>
</td>       
   
                                                                                               </tr>
                                                                                              <%
                                                                                                }  
                                                                                        }
                                                                                                %> 
                                                                                               </tbody>
                                                                                         </table>
                                                                                </td></tr>  
                                                                                </tbody>
                                                                            </table>
                                                                            </div>  
                                                                            </div>
                                                                 
            </div>                                                                
                                                                        </td>
                                                                    </tr>
                                                                </table>
                </td>  
              </tr>
              <tr>
                         <td>
                                &nbsp;&nbsp;&nbsp;
                         </td>
                     </tr>
                     </table> 
                     </td>
                     </tr>
                     <tr class="barColor">
                        <td align="left" height="20px" >
                            <table border="0"><tr><td align="left" style="padding-left:716px;"  >
<%if(newXmlName != null && !newXmlName.equals("")) 
{     
%>
  <button dojoType="dijit.form.Button"  type="button" id="idHome" name="btnHome" 
  onclick='javascript:submitTabForHome("<%=ServerUtils.getContextName(request)%>/manageConfigAction.do?xmlName=<bean:write name="tabsLst1"    property="configDetailBeanName" />&tabValue=<bean:write name="tabsLst1" property="configDetailBeanValue" />");' >
      <bean:message key="uiconfig.home" bundle="uiconfig"/> 
  </button>
<%
}else{
%>
  <button dojoType="dijit.form.Button"  type="button" id="idHome" name="btnHome" 
  onclick="javascript:submitToHome();" >
      <bean:message key="uiconfig.home" bundle="uiconfig"/> 
  </button>
<%}%>
                           
                          <button dojoType="dijit.form.Button" id="btnSaveId" name="btnSave" onClick="return submitForm(<%=displayCheckBox%>);">
                              <bean:message key="uiconfig.save" bundle="uiconfig"/>
                          </button>
                          <button dojoType="dijit.form.Button" id="btnRefreshId" name="btnRefresh" onClick="return refreshConfig();">
                              <bean:message key="uiconfig.refresh" bundle="uiconfig"/> 
                          </button>
                          <button dojoType="dijit.form.Button" id="btnRestoreDefaultId" name="btnRestoreDefault" onClick="return restoreDefault();" title="You are allowed to restore default tab only">
                              <bean:message key="uiconfig.restoreDefault" bundle="uiconfig"/>  
                          </button>
                          <button dojoType="dijit.form.Button" id="btnDeleteTab" name="btnDeleteTab" disabled="true" onClick="return deleteTab();" title="You are allowed to delete empty custom tab only">
                              <bean:message key="uiconfig.deleteTab" bundle="uiconfig"/>   
                          </button>
                          </td></tr></table>   
                        </td>
                     </tr>      
   </table> 
</form>

<script>;
    dojo.addOnLoad(disableButtonForNewTab); 
       function disableButtonForNewTab(){

         confirmationBox  = new dijit.Dialog({
      title: "Loading...",
      style: "width: 300px;height:125px"
  }); 

        <%if(!(xmlFileSectionsList.contains(xmlName))){%>                                   
        if(dijit.byId('btnRestoreDefaultId') != null)
          dijit.byId('btnRestoreDefaultId').setAttribute('disabled', true);              
        <%if(!propertyExists){%>  
            if(dijit.byId('btnDeleteTab') != null)
              dijit.byId('btnDeleteTab').setAttribute('disabled', false);            
        <%}%>
          <%}else{%>    
          <%}%>

    if(newXmlName!= 'null' && newXmlName.length != 0)
    {
        if(dijit.byId('btnRefreshId') != null)
          dijit.byId('btnRefreshId').setAttribute('disabled', true);    
        if(dijit.byId('btnDeleteTab') != null)
              dijit.byId('btnDeleteTab').setAttribute('disabled', true);                            
    }         
}
</script>
</body>
<%} catch(Exception e) {
    e.printStackTrace();
}
    %>
</html:html>