<%@page import="com.enterprisehorizons.magma.server.admin.CacheConfigUtils , 

com.enterprisehorizons.util.StringUtils"%>
<%@page import="com.enterprisehorizons.magma.server.util.ServerUtils"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<head>
<title>Space . Time . Insight</title>
<%@ include file="/mobile/common/style.jsp"%>

<script type="text/javascript">
    function performValidate(){
        var password = document.getElementById('currentPassword').value;
        var newpassword =  document.getElementById('newPassword').value;
        var retypenewpassword =  document.getElementById('repeatNewPassword').value;
     
        if(password != '' && newpassword != '' && retypenewpassword != ''){
            if( newpassword.length <6  ||  newpassword.length>20 ) {
               alert('<bean:message key="admin.changepassword.check.len" bundle="admin"/>','<bean:message key="admin.common.alert.title" bundle="admin" />');
               document.getElementById('newPassword').focus();
               return false;
            }
            if( retypenewpassword.length<6 ||  retypenewpassword.length>20 ) {
               alert('<bean:message key="admin.changepassword.check.match" bundle="admin"/>','<bean:message key="admin.common.alert.title" bundle="admin" />');
               document.getElementById('repeatNewPassword').focus();
               return false;
            }
            if(newpassword != retypenewpassword ){
                alert('<bean:message key="admin.changepassword.check.match" bundle="admin" />','<bean:message key="admin.common.alert.title" bundle="admin" />');
               document.getElementById('repeatNewPassword').focus();
            }
            else{
                document.forms[0].submit();
                return true
            }
            return false;
        }else{
            alert('<bean:message key="dbconfig.model.check.mandantory" />','<bean:message key="admin.common.alert.title" bundle="admin" />'); 
            return false;
        }
        return false
	}
</script>
</head>

<html:html locale="true">
<title>Chanage Password</title>
<body class="bodyBG bodyText">
	<html:form action="/changePasswordAction.do?prefix=mobile" method="post">
		<table width="100%" cellspacing="0" cellpadding="0">
		<tr>
       		<td colspan="2" class="bodyText">&nbsp;</td> 
    	</tr>	
   		<tr>
       		<td colspan="2" class="bodyText" align="center"><bean:message key="admin.password.change.msg" bundle="admin"/>.</td> 
    	</tr>
        <tr>
       		<td colspan="2" class="bodyText">&nbsp;</td> 
    	</tr>
    	<tr>
    		<td>
        		<input type="hidden" name="promptPasswordChange" value="<%=request.getParameter("promptPasswordChange")%>"  />
        	</td>
    	<tr>
    		<td class="formlabel bodytext">
        		<bean:message key='admin.changepassword.old.label' bundle='admin'/> : 
        	</td>
        	<td>
            	<input type="password" id="currentPassword" name="currentPassword" required="true" trim="true" ucfirst="true" class="formcomponent" size="12"/>
			</td>
   		</tr>
    	<tr>
    		<td class="formlabel bodytext">
        		<bean:message key='admin.changepassword.new.label' bundle='admin'/> :
      		</td>
        	<td>      
            	<input type='password' id="newPassword" name="newPassword" required="true" trim="true" ucfirst="true" class="formcomponent" size="12"/>
			</td>
  		</tr>	
    	<tr>
    		<td class="formlabel bodytext">
        		<bean:message key='admin.changepassword.confirm.label' bundle='admin'/> :
      		</td>
        	<td>      
            	<input type='password' id="repeatNewPassword" name="repeatNewPassword" required="true" trim="true" ucfirst="true" class="formcomponent" size="12"/>
     		</td>
  		</tr>
    	<tr>
  			<td>
    			&nbsp;
 			</td>
    		<td>
            <input class="btnstyle"   type="submit" id="idSubmit" name="btnSubmit" onClick="performValidate();return false;" 
            			value="<bean:message key='admin.common.submit' bundle='admin' />" /> &nbsp;
    			<input class="btnstyle"   type="reset" id="idSReset" name="btnReset" value="<bean:message key='admin.common.reset' bundle='admin' />"/>
  			</td>
    	</tr>
        <tr>
      		<td  colspan="2" class="bodyText" align="center"> 
           		<html:messages id="saveStatus" message="true" bundle="security"><bean:write name="saveStatus" /></html:messages>
            	<html:errors bundle="security"/>
         	</td>  
 		</tr>
	</table>
</html:form>
</body>
</html:html>

