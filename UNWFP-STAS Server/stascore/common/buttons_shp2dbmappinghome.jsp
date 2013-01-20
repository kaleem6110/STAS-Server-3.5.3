<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<script>
function showAlertMessage()
{
	if(dijit.byId('cmbMappedDefinitions') != null && dijit.byId('cmbMappedDefinitions').value == ''){
		showEmptyDialog('<bean:message key="shpDbMap.check.correct.value" bundle="shpDbMap"/>','<bean:message key="admin.common.alert.title" bundle="admin"/>');
		return false;
	}
	else if(dijit.byId('cmbMappedDefinitions') != null && dijit.byId('cmbMappedDefinitions').value != ''){
		confirmationDialog(MSG_CONFIRM);
	}
	else{
		showEmptyDialog("<bean:message key='shpDbMap.check.correct.value' bundle='shpDbMap'/>","<bean:message key='admin.common.alert.title' bundle='admin'/>");
		return false;		
	}	
}
</script>

       <tr  height="30" colspan="2" class="barColor">
       <td height="30" colspan="2" class="barColor" align="center">	  
          <button dojoType="dijit.form.Button"  type="button" onclick="goToDBHomePage()"><bean:message key="shpDbMap.home" bundle="shpDbMap"/> </button>
            <button dojoType="dijit.form.Button" id="View" name="btnView" type="button" onclick="return submitForm(this);"> <bean:message key="shpDbMap.view" bundle="shpDbMap"/> </button>
            <button dojoType="dijit.form.Button"  id="Add" name="btnAdd" type="button" onclick="return submitForm(this);"> <bean:message key="shpDbMap.add" bundle="shpDbMap"/> </button>
            <button dojoType="dijit.form.Button" id="Update" name="btnUpdate" type="button" onclick="return submitForm(this);"> <bean:message key="shpDbMap.update" bundle="shpDbMap"/> </button>
            <button dojoType="dijit.form.Button" id="Delete" name="btnDelete" type="button" onclick="showAlertMessage();"> <bean:message key="shpDbMap.delete" bundle="shpDbMap"/> </button>


            </td>
        </tr>