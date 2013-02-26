<%@page import="com.enterprisehorizons.magma.server.admin.CacheConfigUtils , com.enterprisehorizons.util.StringUtils"%>
<%@page import="com.enterprisehorizons.magma.server.util.ServerUtils"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<head>


<title>Space . Time . Insight</title>





<script type="text/javascript" src="<%=ServerUtils.getContextName(request)%>/js/dojo/dojo.js"
    djConfig="isDebug: false, parseOnLoad: true"></script>
<%@ include file="/common/dojo.jsp"%>
    

<script type="text/javascript">

function showEmptyDialog(data2Display, dialogTitle){
               var  emptyDlg = new dijit.Dialog({
            title: "<bean:message key='admin.common.dialog.loading' bundle='admin' />",
            style: "width: 300px;height:125px"
        }); 
        // set the content of the dialog:
        if(emptyDlg != null) {          
            emptyDlg.attr("title",  dialogTitle);
            emptyDlg.attr("content", "<center><table ><tr><td align=center></tr><tr><td align='center'>"+data2Display+"</td></tr><tr><td align='center'>    <button dojoType=dijit.form.Button type='submit'>OK</button></td></td></tr></table></center>");
            emptyDlg.show();
        }
    }
    function performValidate(){
    
        var password = dojo.byId('currentPassword').value;
        var newpassword =  dojo.byId('newPassword').value;
        var retypenewpassword =  dojo.byId('repeatNewPassword').value;
     
        if(password != '' && newpassword != '' && retypenewpassword != ''){
            if( newpassword.length <6  ||  newpassword.length>20 ) {
               showEmptyDialog('<bean:message key="admin.changepassword.check.len" bundle="admin" />','<bean:message key="admin.common.alert.title" bundle="admin" />');
               dojo.byId('newPassword').focus();
               return false;
            }
            if( retypenewpassword.length<6 ||  retypenewpassword.length>20 ) {
               showEmptyDialog('<bean:message key="admin.changepassword.check.match" bundle="admin" />','<bean:message key="admin.common.alert.title" bundle="admin" />');
               dojo.byId('repeatNewPassword').focus();
               return false;
            }
            if(newpassword != retypenewpassword ){
                showEmptyDialog('<bean:message key="admin.changepassword.check.match" bundle="admin" />', '<bean:message key="admin.common.alert.title" bundle="admin" />');
                dojo.byId('repeatNewPassword').focus();
            }
            else{
                document.forms[0].submit();
                return true
            }
            return false;
        }else{
            showEmptyDialog('<bean:message key="dbconfig.model.check.mandantory" />','<bean:message key="admin.common.alert.title" bundle="admin" />'); 
            return false;
        }

        return false
    
    }
</script>


</head>





<html:html locale="true">
<title>Chanage Password</title>

<body class="tundra bodybg">
<html:form action="/changePasswordAction.do" method="post">



<table width="100%" cellspacing="0" cellpadding="0" align="center" >
<tr>
<td class="pageTitle paddingTitle">    
						<tr>
              				<td class="pageTitle paddingTitle"><bean:message key='admin.changepassword.title' bundle='admin' /><br /></td>
    						<td width="67px">&nbsp;</td>
           				</tr>
   						<tr>
                            <td class="paddingTitleDesc bodytext"><bean:message key="admin.password.change.msg" bundle="admin"/></td> 
                        </tr>
						<tr>
                            <td style="padding-left:70px;">&nbsp;</td>
                        </tr>
						 
            <tr>
                 <td style="padding-left:67px; width:800px;"> 
                                   <font color="blue" style="font-family: Tahoma;font-size: 12px;" ><b>
                                    <html:messages id="saveStatus" message="true" bundle="security"><bean:write name="saveStatus" /></html:messages>
                                    </b></font> 
                                    <font color="red" style="font-family: Tahoma;font-size: 12px;"><b><html:errors bundle="security"/></b></font> 
                </td>  
            </tr>
        <tr>
         <td>
             <table  width="100%" id="table2" height="54" cellspacing="0" cellpadding="0" border="0">
                
             
                 <tr>
                <td style="padding-left:64px;padding-top:15px">
                    <table border="0" >
                        <tbody>
                                                    <input type="hidden" name="promptPasswordChange" value="<%=request.getParameter("promptPasswordChange")%>"/>
                                                        <tr>
                                                            <td colspan="1" class="bodytext" align="right"><strong><bean:message key='admin.changepassword.old.label' bundle='admin' /><font color="red" size="1">*</font>: </strong></td>
                                                            <td class="bodytext">  
                                                                <input type="password"   id="currentPassword" name="currentPassword"                   dojoType="dijit.form.ValidationTextBox" required="true" trim="true" ucfirst="true"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td height="13px"></td>
                                                        </tr>
                                                        <tr>
                                                            <td colspan="1" class="bodytext" align="right"><strong><bean:message key='admin.changepassword.new.label' bundle='admin' /><font color="red" size="1">*</font>: </strong></td>
                                                            <td class="bodytext"> 
                                                                <input type='password' id="newPassword" name="newPassword" required="true" trim="true" ucfirst="true" dojoType="dijit.form.ValidationTextBox"/>
                                                            </td>
                                                        </tr>
                                                        <tr><td height="13px"></td>
                                                        </tr>
                                
                                                        <tr><td colspan="1" class="bodytext" align="right"><strong><bean:message key='admin.changepassword.confirm.label' bundle='admin' /><font color="red" size="1">*</font>: </strong></td>
                                                            <td class="bodytext"><input type='password' id="repeatNewPassword" name="repeatNewPassword" required="true" trim="true" ucfirst="true" dojoType="dijit.form.ValidationTextBox"/>
                                                            </td>
                                                        </tr>
                                                        <tr><td colspan="2" class="bodytext" align="right"></td>
                                                        </tr>
                             </tbody>

                    </table>
                </td>
            </tr>
        </table>        
    </td>
    <td>&nbsp;</td>
     </tr>      
     <tr>
        <td></td>
        <td>&nbsp;</td>
    </tr>

 </td>
  <tr  height="30" colspan="2" class="barColor" >
       <td height="30" colspan="2" class="barColor" align="center">   
            <button dojoType="dijit.form.Button"  id="idSubmit" name="btnSubmit" type="submit" onclick="performValidate();return false;" /> <bean:message key='admin.common.submit' bundle='admin' /></button>&nbsp;&nbsp;
            <button dojoType="dijit.form.Button" id="idSReset" name="btnReset" type="reset" > <bean:message key='admin.common.reset' bundle='admin' /></button>
         </td>
       </tr>
    </td>
    </tr>
    </table>
    
</html:form>
<script>
dojo.addOnLoad(loadFormValues);
function loadFormValues(){
    dojo.byId('currentPassword').focus();
}


</script>

</body>

</html:html>


<!-- jsp:include page="../common/adminnavigation.jsp"/ -->
<!-- jsp:include page="../common/footer.jsp"/ -->