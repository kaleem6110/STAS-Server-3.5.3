<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib prefix="tiles" uri="/tags/struts-tiles"%>
<%@ page import="com.spacetimeinsight.admin.undeploy.bean.FileDetailBean , java.util.ArrayList" %>
<%@page import="com.enterprisehorizons.magma.server.util.ServerUtils"%>
<html:html locale="true">
<head>
<%@ include file="/common/dojo.jsp"%>
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Expires" content="0">
<title> <bean:message key="undeploy.title" bundle="undeploy"/></title>

<script>



function deleteDS(){
    document.forms[0].submit();   
}

function dodelete(){
	document.forms[0].submit();
}
    
function isAnyFileSelected(){
 
 	var files = document.getElementById('selFile');
 	
 	if(files != null){
 		
 		for(var count=0;count <files.length;count++)      
    	{
			if(files[count].selected){
				return true;
			}	
		}
		
	}
	
	return false;
}
 
function checkSelectAndDeleteFiles(){

	if(!isAnyFileSelected()){
		// No file Selected
		showDialogBox("<bean:message key="updeploy.check.select.ecosystem" bundle="delete" />");
	}else{
		// At least one file selected by user.
		confirmationDialog("<bean:message key="updeploy.check.ecosystem" bundle="delete" />");
	}	
 		
}
 
function showDialogBox(date2Display){
	var  dialogTitle = '<bean:message key="updeploy.ecofiles" bundle="delete" />';
	// set the content of the dialog:
    if(confDtls != null) { 
    	confDtls.attr("title",  dialogTitle);
		confDtls.attr("content", "<center><table ><tr><td align=center></tr><tr><td align='center'>"+date2Display+"</td></tr><tr><td align='center'>  <button dojoType='dijit.form.Button' type='button' onClick = 'confDtls.hide();'><bean:message key="admin.common.dialog.ok" bundle="admin"/></button> </td></td></tr></table></center>");
		confDtls.show();
    }
}

var  confDtls;

dojo.addOnLoad(function(){

	confDtls = new dijit.Dialog({
    	title: "Loading...",
    	style: "width: 300px;height:125px"
    });
     
});
</script> 

    <style type="text/css">
     #select {
        width:255px;
        height:300px;
        overflow:auto;
    }
     </style>


    <script type="text/javascript">
        dojo.require("dijit.dijit"); // optimize: load dijit layer
        dojo.require("dijit.form.MultiSelect");
    </script>


</head>     
<body onLoad='' class="tundra bodybg">
<html:form  action="unDeployEcoAction.do" method="POST" enctype="multipart/form-data">
<table width="101.2%"><tr><td width="100%">
<table width="100%" cellspacing="0" cellpadding="0" align="center"  border="0" >
            <tr>
                <td  class="pageTitle paddingTitle">      
                        <bean:message key="undeploy.undeployEco" bundle="undeploy"/>
                </td>
            </tr>
            <tr>
               <td class="paddingTitleDesc bodytext">
                     <strong><bean:message key="undeploy.description" bundle="undeploy"/></strong>
               </td>  
            </tr>

            <tr>
                <td style="padding-left:67px; width:500px;">
                   <div style="display:block" style="" style="width: 500px; height: 25px; overflow-y: auto;     scrollbar-arrow-color:blue; scrollbar-face-color: #e7e7e7; scrollbar-3dlight-color: #a0a0a0; scrollbar-darkshadow-color:#888888" >
					   <label class="success"><b>
						   <logic:present name="fileName">
								<bean:write name="fileName"/>   
						   </logic:present>						  
							</b>
					   </label>
					   <label class="error"><b>
						   <logic:present name="errorMessage">
								<bean:write name="errorMessage"/>   
						   </logic:present>
							</b>
					   </label>
					    
                   </div> 
                </td>  
            </tr>
            <tr>
                <td style="padding-top:30px;padding-left:30px;width="900px">
                    <table cellspacing="0" cellpadding="0" width="100%" >
                        <tr>
                                <td style="padding-left:30px;align:left;">
                                    <table cellspacing="0" cellpadding="0" border="0" align="left">
                                        <tr>
                                            <td>
                                                    <table cellpadding="10" cellspacing="" border="0" width="100%">
                                                        <tr>
                                                             <td height="27" align="right"  style="width:132px;" nowrap="nowrap" >
                                                                     <label class="label"><bean:message key="undeploy.selectFiles" bundle="undeploy"/></label>:
                                                            </td>
                                                            <td style="padding-left:10px; align:left;" width="90px">
                                                                    <select id="selFile" multiple="true" name="selFile"  style="height:100px; width:180px; border:5px solid #ededed;"> 
                                                                            <logic:present name="fileList">
                                                                                <logic:notEqual name="fileList"  value="null">
                                                                                    <logic:iterate id="fileList1" name="fileList" >
                                                                            <option title='<bean:write name="fileList1" property="fileName" />' value='<bean:write name="fileList1" property="fileId" />'><bean:write name="fileList1" property="fileName" />
                                                                            </option>
                                                                                    </logic:iterate>
                                                                                </logic:notEqual>   
                                                                            </logic:present>
                                                                            <logic:notPresent name="fileList">
                                                                            <option value="-2">
                                                                                --<bean:message key="delete.nofileFound" bundle="delete"/>--
                                                                            </option>
                                                                            </logic:notPresent>
                                                                    </select>                               
                                                            </td>
                                                        </tr>       
                                                        <tr>                                        
                                                            <td><b><span id="result"></b></span>
                                                            </td>
                                                        </tr>
                                                    </table>
                                            </td> 
                                        </tr>
                                    </table>
                                </td>   
                            </tr>
                    </table>    
                </td>
            </tr>

        <TR><td height="10px">&nbsp;</td></TR>
<tr><td width="100%">

<table border="0" width="100%" id="" cellspacing="0" cellpadding="0">
     <tr class="barColor">
            <td colspan="7"  align="left" style="padding-left:244px;"> 
                    <button dojoType="dijit.form.Button"  type="button" id="idBack" name="btnBack1" onClick="window.location= '<%=ServerUtils.getContextName(request)%>/adminMain.do'">
                    <bean:message key="undeploy.home" bundle="undeploy"/>
                    </button>
                    <button dojoType="dijit.form.Button"   type="button" id="idUnDeploy" name="btnUnDeploy" onClick="checkSelectAndDeleteFiles();" >
                    <bean:message key="undeploy.undeploy" bundle="undeploy"/>
                    </button>
    </tr>
</table>
</td>
</tr></table>
<html:hidden name="deleteUploadForm" property="flag" value="" />   
</html:form>
</body>
</html:html>
                                            
