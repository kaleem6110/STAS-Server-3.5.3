<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-nested" prefix="nested"%>
<%@page import="com.spacetimeinsight.db.model.util.DataModelsCache"%>
<%@page import="com.enterprisehorizons.magma.server.util.ServerUtils"%>

<%@page import="java.util.List"%>
<%@page import="com.spacetimeinsight.db.config.model.DatabaseTypesMaster"%>
<%@page import="com.spacetimeinsight.db.config.model.DBConnectionTypeMaster"%>

<%
    List databaseTypesMasterList = DataModelsCache.getInstance().retrieve(
            DatabaseTypesMaster.class.getName());

List dbConnectionTypeMasterList = DataModelsCache.getInstance().retrieve(
        DBConnectionTypeMaster.class.getName());

    pageContext.setAttribute("databaseTypesMasterList", databaseTypesMasterList);
    pageContext.setAttribute("dbConnectionTypeMasterList", dbConnectionTypeMasterList);
%>

<head>
<%@ include file="/common/dojo.jsp"%>
 <% 
     int jdbCounter=1;
 %>

<script>

    function hover(obj){
        obj.className='class_hover';
    }

    function unHover(obj, row_num){
        if (tr_selected != row_num) obj.className = 'class_no_hover bgcolor';

    }

    function previousPage(){
        var pageNo = document.getElementById("pageNo").value;

        if(parseInt(pageNo)>1){
            document.forms[0].pageNo.value = (parseInt(pageNo)-1);
            document.forms[0].operation.value='showAllJ2EEDatasource';
            document.forms[0].action="./WizardHomeAction.do";
            document.forms[0].submit();
        }
    }


    function nextPage(){
        var pageNo = document.getElementById("pageNo").value;
        var totalNoOfPages = document.getElementById("totalNoOfPages").value;
        if(parseInt(pageNo)<totalNoOfPages){
            document.forms[0].pageNo.value = (parseInt(pageNo)+1);
            document.forms[0].operation.value='showAllJ2EEDatasource';
            document.forms[0].action="./WizardHomeAction.do";
            document.forms[0].submit();
        }
    }

    function gotoPage(pageNo){
            document.forms[0].pageNo.value = pageNo;
            document.forms[0].operation.value='showAllJ2EEDatasource';
            document.forms[0].action="./WizardHomeAction.do";
            document.forms[0].submit();
    }

    function onEnterGoToPage(){
        if(window.event.keyCode==13){ 
            var pageNo = document.getElementById("enteredPageNo").value;
            var totalNoOfPages = document.getElementById("totalNoOfPages").value;
            if(parseInt(pageNo)>=1 && parseInt(pageNo)<=totalNoOfPages){
                document.forms[0].pageNo.value = pageNo;
                document.forms[0].operation.value='showAllJ2EEDatasource';
                document.forms[0].action="./WizardHomeAction.do";
                document.forms[0].submit();
            }       
        }
    }

  function configureDS(){
    window.location = "createJ2EEDatasource.do";
  }

    function showModelMaintainance(){
        window.location = "dbadmin.do";
    }

function openInfoframe(innerData, titleText, numWidth){
     try{
        infoFrameWin.destroy();
     }catch(er){

     }
    try{
          infoFrameWin = new Window({id: "infoFrameWin", className: "alphacube", title: titleText, width:(screen.width)*(0.4), height:(screen.height)*(0.45),top:(screen.height)*(0.10),left:(screen.width)*(0.10), closable:true, wiredDrag: true}); 
          infoFrameWin.getContent().innerHTML = innerData; 
          infoFrameWin.setDestroyOnClose(); 
          infoFrameWin.show();
          infoFrameWin.toFront();   
    }
    catch(ef){}
 }

var url, deleteUrl;
var rowSelected = 'no';
function updateDS(){
    window.location = url;
}


function dodelete(){
    window.location = deleteUrl;
}



function enableBtn(selectedObj, dsType){
       document.getElementById('dbConfigurationId').value = selectedObj.value;
       url ="<%=ServerUtils.getContextName(request)%>/ConfigureDataSource.do?operation=configureJ2EEDsource&action=view&dbConfigurationId="+selectedObj.value;
       deleteUrl ="<%=ServerUtils.getContextName(request)%>/ConfigureDataSource.do?operation=configureJ2EEDsource&action=delete&dbConfigurationId="+selectedObj.value;

       rowSelected = 'yes';
}
</script>
<logic:present name="ecoDatasourceList" scope="request" >
<script>
function isAnyRowSelected(displayMessage,callfrom){
    if(rowSelected == 'no'){
        showEmptyDialog(displayMessage,'DataSources');
        return false;
    }else{
        if(callfrom == 'update'){
            updateDS();
        }else if(callfrom == 'delete'){
            confirmationDialog('Do you want to delete the record?');
        }

    }
    return true;
}
</script>
 </logic:present>

 
 <logic:notPresent name="ecoDatasourceList" scope="request" >
 <script>
function isAnyRowSelected(displayMessage,callfrom){
        if(callfrom == 'update'){
            showEmptyDialog('<bean:message key="dbconfig.no.record.update"/>','Alert');
            }
        else if(callfrom == 'delete'){
            showEmptyDialog('<bean:message key="dbconfig.no.record.delete"/>','Alert');
        }
    return true;
}
 </script>
 </logic:notPresent>
<script>
var tr_selected = '';
     </script>

</script>
</head>
<body class="tundra bodybg"  >
    <html:form action="WizardHomeAction"  >
        <html:hidden property="operation" value="database" styleId="operation"/>
            <input type="hidden" name="dbConfigurationId" id="dbConfigurationId" />
            <table width="100%" cellspacing="0" cellpadding="0">
            <tr>
    
                <td align="left">
                    <table cellpadding="0" cellspacing="0" align="left">
                      <tr>
      
                          <td  valign="top"  align="justify">
                              <table cellspacing="0" cellpadding="0">
       
                                <tr>
                                    <td  class="pageTitle paddingTitle">      
                                         <bean:message key="j2eeDS.configuredJ2EEDSFieldset" bundle="j2eeDS"/>
                                    </td>
                                </tr>
       
                                <tr>
                                    <td  class="paddingTitleDesc bodytext">      
                                        <strong> <bean:message key="j2eeDS.configuredDSDescription" bundle="j2eeDS"/> </strong>
                                    </td>
                                 </tr>
    
        
        
        
        <table width="600" cellspacing="0" cellpadding="0" border="0" >
 
        <tr>
    
          <td height="30" valign="bottom" class="paddingTitleDesc redtitle"><%  String curdStatus = (String) request.getSession(false).getAttribute("curdStatus");
          request.getSession(false).removeAttribute("curdStatus");    
                %>
                <div id="curdStatus">
                <% if(curdStatus == "Inserted"){  %>
                <label class="success">
                <bean:message key="j2eeDS.inserted" bundle="j2eeDS" />
                </label>
                <%  }else if (curdStatus == "Deleted"){  %>
                <label class="success">
                <bean:message key="j2eeDS.deleted" bundle="j2eeDS" />
                </label>
                <%      }else if (curdStatus == "Not Deleted"){ %>
                <label class="success">
                <bean:message key="j2eeDS.notDeleted" bundle="j2eeDS" />
                </label>
                <%      }else if (curdStatus == "Not Inserted"){ %>
                <label class="success">
                <bean:message key="j2eeDS.notInserted" bundle="j2eeDS" />
                </label>
                <%      }else if (curdStatus == "Updated"){ %>
                <label class="success">
                <bean:message key="j2eeDS.updated" bundle="j2eeDS" />
                </label>
                <%      }else if (curdStatus == "Not Updated"){ %>
                <label class="error">
                <bean:message key="j2eeDS.notUpdated" bundle="j2eeDS" />
                </label>
                <%  }else if (curdStatus == "errorInUpdating"){ %>
                <label class="error">
                    <bean:message key="j2eeDS.errorInUpdating" bundle="j2eeDS" />
                </label>
                <%} else if (curdStatus == "Datasource Not Found"){ %>
                <label class="error">
                    J2EE Datasource not found or Deleted
                 </label>
                <%}%> 
        </td> 

    </tr>
    
    
       <tr>
      <logic:notPresent name="ecoDatasourceList" scope="request" >
     <td style = "padding-left: 67px;padding-top: 20px;"><label class = "label">
          <bean:message key="j2eeDS.noRecordFound" bundle="j2eeDS" />
          </label>
          </td>
           </logic:notPresent>
             <logic:present name="ecoDatasourceList" scope="request" >
      <td height="30" valign="top" class="redtitle" align="right"><img src="<%=ServerUtils.getContextName(request)%>/images/portal/btn_remove_all_on.png" id="start_on" style="display:none;cursor:pointer" onClick="gotoPage(1);"/> <img src="<%=ServerUtils.getContextName(request)%>/images/portal/btn_remove_all_off.png" id="start_off" style="display:none;cursor:pointer"/> <img src="<%=ServerUtils.getContextName(request)%>/images/portal/btn_remove_one_on.png" id="previous_on" style="display:none;cursor:pointer" onClick="previousPage();"/> <img src="<%=ServerUtils.getContextName(request)%>/images/portal/btn_remove_one_off.png" id="previous_off" style="display:none;cursor:pointer"/>
        <span style="valign=top"><input type="text" id="enteredPageNo" size="5" name="enteredPageNo" style="height:30;width:50"
                    onkeypress="onEnterGoToPage();"  
                    value="<bean:write name="resultBean" property="pageNo"/> of <bean:write name="resultBean" property="totalNoOfPages"/>"  disabled="true"/></span> <img src="<%=ServerUtils.getContextName(request)%>/images/portal/btn_add_one_on.png" id="next_on" style="display:none;cursor:pointer" onClick="nextPage()"/> <img src="<%=ServerUtils.getContextName(request)%>/images/portal/btn_add_one_off.png" id="next_off" style="display:none;cursor:pointer"/> <img src="<%=ServerUtils.getContextName(request)%>/images/portal/btn_add_all_on.png" id="end_on" style="display:none;cursor:pointer" onClick="gotoPage('<bean:write name="resultBean" property="totalNoOfPages"/>')" /> <img src="<%=ServerUtils.getContextName(request)%>/images/portal/btn_add_all_off.png" id="end_off" style="display:none;cursor:pointer"/></td>
    </td>
     </logic:present>
    
    </tr>

    <tr>
      
      <td height="30" align="right" valign="top" class="redtitle"><logic:present name="ecoDatasourceList" scope="request" >
          <table id="myTable" class=bgcolorReports cellSpacing=1 
                              cellPadding=0 width="89%" border="0">
            <tr class=subHeaddings>
              <td class="panelColor" valign="middle">&nbsp;<bean:message key="datasource.actionLabel" bundle="ds" /></td>
              <td class="panelColor" valign="middle">&nbsp;Provider Type&nbsp;</td>
              <td class="panelColor" valign="middle">&nbsp;Driver Name&nbsp;</td>
              <td class="panelColor" valign="middle">&nbsp;Datasource Name&nbsp;</td>
              <!--   <td valign="middle">&nbsp;User Name&nbsp;</td>
              <td valign="middle">&nbsp;URL&nbsp;</td>
              <td valign="middle">&nbsp;Description&nbsp;</td> -->
            </tr>
           <logic:iterate name="ecoDatasourceList" scope="request" id="ecoDatasourceDtls"  >
           <tr  class="bgcolor" onMouseOver="hover(this);" onMouseOut="unHover(this, 1);" 
                               ondblclick='showSelectedRow("<bean:write name="ecoDatasourceDtls" property="id"/>","<bean:write name="ecoDatasourceDtls" property="datasourceName"/>"); viewDatasource("<bean:write name="ecoDatasourceDtls" property="id"/>","<bean:write name="ecoDatasourceDtls" property="datasourceName" />")'>
    
            <td class="textnormal12" align="center"> 
                <input type="radio" name="datasource" id="<bean:write name="ecoDatasourceDtls" property="id"/>" value="<bean:write name="ecoDatasourceDtls" property="id"/>" onclick="enableBtn(this, '<bean:write name="ecoDatasourceDtls" property="id"/>')" />
            </td>
            <td class="textnormal12">
                <bean:define id="providerLabel" > 
                    <bean:write name="ecoDatasourceDtls" property="databaseConnectionTypeId" />
                </bean:define>                      
                <logic:present name="dbConnectionTypeMasterList" scope="page">
                    <logic:iterate name="dbConnectionTypeMasterList" scope="page" id="dbConnectionTypeMasterDtls">
                        <logic:equal name="dbConnectionTypeMasterDtls" value="<%=providerLabel %>" property="dbConnectTypeId" scope="page">
                            <bean:write name="dbConnectionTypeMasterDtls" property="name"/>
                            <input type="hidden" name="id<bean:write name="ecoDatasourceDtls" property="id"/>" value="<bean:write name="dbConnectionTypeMasterDtls" property="name"/>" id="id<bean:write name="ecoDatasourceDtls" property="id"/>" />
                        </logic:equal>
                    </logic:iterate>
                </logic:present>    
                
                
            </td>
            <td class="textnormal12">
                <bean:define id="driverNameLabel" > 
                    <bean:write name="ecoDatasourceDtls" property="databaseTypeId" />
                </bean:define>                      
                <logic:present name="databaseTypesMasterList" scope="page">
                    <logic:iterate name="databaseTypesMasterList" scope="page" id="databaseTypesMasterDtls">
                            <logic:equal name="databaseTypesMasterDtls" value="<%=driverNameLabel %>" property="id" scope="page">
                            <bean:write name="databaseTypesMasterDtls" property="driverName"/>
                            <input type="hidden" name="driverName<bean:write name="ecoDatasourceDtls" property="id"/>" value="<bean:write name="databaseTypesMasterDtls" property="driverName"/>" id="driverName<bean:write name="ecoDatasourceDtls" property="id"/>" >
                        </logic:equal>
                    </logic:iterate>
                </logic:present>                
            </td>
            <td class="textnormal12" style="overflow:hidden;word-break:break-all;">
                <bean:write name="ecoDatasourceDtls" property="datasourceName" />               
            </td>
            <!-- <td class="textnormal12" style="overflow:hidden;word-break:break-all;">
                <bean:write name="ecoDatasourceDtls" property="userName" />
            </td>
            <td class="textnormal12" style="overflow:hidden;word-break:break-all;">
                <bean:write name="ecoDatasourceDtls" property="url" />
            </td>
            <td class="textnormal12" style="overflow:hidden;word-break:break-all;">
                <bean:write name="ecoDatasourceDtls" property="description" />
            </td> -->
              </tr>
            </logic:iterate>
          </table>
        </logic:present>
     
        <input type="hidden" name="totalNoOfPages" id="totalNoOfPages" value="<bean:write name="resultBean" property="totalNoOfPages"/>
        "/>
        <input type="hidden" name="pageNo" id="pageNo" value="<bean:write name="resultBean" property="pageNo"/>
        " />
  </table>
  </tr>
  </table>
  </tr>
  </table>
    </td>
          
          </tr>
          
        </table>
      </table>
      </td>
      
      </tr>
      
    </table>
  </table>
  </td>
  </tr>
  </table>
  <!-- Button Panel -->
  <table width="101.2%" border=0 >
    <tr>
      <td>&nbsp;</td>
    </tr>
    <tr  align="right" class="barColor">
      <td  align="right" width="970"><button dojoType="dijit.form.Button"  type="button"  onclick="window.location =  '<%=ServerUtils.getContextName(request)%>/adminMain.do'  ">
        <bean:message key="datasource.home" bundle="ds" />
        </button>
        <button dojoType="dijit.form.Button" id="btnBack" type="button"  onclick="showModelMaintainance()">
         <bean:message key="dbconfig.back" />
        </button>
        <button dojoType="dijit.form.Button"  type="button"  onclick="configureDS()">
        <bean:message key="datasource.add" bundle="ds" />
        </button>
        <button dojoType="dijit.form.Button"  type="button"  onclick="return isAnyRowSelected('Please select any record to update','update');" id="updateBtn">
        <bean:message key="datasource.update" bundle="ds" />
        </button>
        <button dojoType="dijit.form.Button"  type="button"  onclick="return isAnyRowSelected('Please select any record to delete','delete');" id="deleteBtn">
        <bean:message key="datasource.delete" bundle="ds" />
        </button>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        </td>
    </tr>
    <tr>
      <td>&nbsp;</td>
    </tr>
 </table>

<logic:present name="ecoDatasourceList" scope="request" >
  <script>
    dojo.addOnLoad(loadFormValues); 
        function loadFormValues(){
                
                if(<bean:write name="resultBean" property="totalNoOfPages"/>  <= 1 && <bean:write name="resultBean" property="pageNo"/> <= 1){
                        document.getElementById('start_on').style.display='none';
                        document.getElementById('start_off').style.display='';
                        document.getElementById('previous_on').style.display='none';
                        document.getElementById('previous_off').style.display='';
                        document.getElementById('next_on').style.display='none';
                        document.getElementById('next_off').style.display='';
                        document.getElementById('end_on').style.display='none';
                        document.getElementById('end_off').style.display='';                        
                }else if(<bean:write name="resultBean" property="totalNoOfPages"/>  > 1 && <bean:write name="resultBean" property="pageNo"/> <= 1){
                         document.getElementById('start_on').style.display='none';
                        document.getElementById('start_off').style.display='';
                        document.getElementById('previous_on').style.display='none';
                        document.getElementById('previous_off').style.display='';
                        document.getElementById('next_on').style.display='';
                        document.getElementById('next_off').style.display='none';
                        document.getElementById('end_on').style.display='';
                        document.getElementById('end_off').style.display='none';
                }else if(<bean:write name="resultBean" property="totalNoOfPages"/>  > 1 && (<bean:write name="resultBean" property="pageNo"/> > 1 && (<bean:write name="resultBean" property="pageNo"/> != <bean:write name="resultBean" property="totalNoOfPages"/>))){
                        document.getElementById('start_on').style.display='';
                        document.getElementById('start_off').style.display='none';
                        document.getElementById('previous_on').style.display='';
                        document.getElementById('previous_off').style.display='none';
                        document.getElementById('next_on').style.display='';
                        document.getElementById('next_off').style.display='none';
                        document.getElementById('end_on').style.display='';
                        document.getElementById('end_off').style.display='none';
                }else if((<bean:write name="resultBean" property="totalNoOfPages"/>  > 1) && (<bean:write name="resultBean" property="pageNo"/> == <bean:write name="resultBean" property="totalNoOfPages"/>)){                     
                        document.getElementById('start_on').style.display='';
                        document.getElementById('start_off').style.display='none';
                        document.getElementById('previous_on').style.display='';
                        document.getElementById('previous_off').style.display='none';
                        document.getElementById('next_on').style.display='none';
                        document.getElementById('next_off').style.display='';
                        document.getElementById('end_on').style.display='none';
                        document.getElementById('end_off').style.display='';
                        
                }


        }

function viewDatasource(j2eeDSId,datasourceName){

    innerData = '<br><fieldset style="width: 100%"><legend class="bigtext"><strong><bean:message key="j2eeDS.propertywindow.properties" bundle="j2eeDS"/></strong></legend> <table cellspacing="5" cellPadding="3" ><tr><td style="padding-left:20px" class="textnormal12" ><strong><bean:message key='j2eeDS.providerType' bundle='j2eeDS'/></strong></td><td><strong>&nbsp;&nbsp;:&nbsp;</strong></td><td align="left" class="textnormal12">'+document.getElementById("id"+j2eeDSId).value+'</td></tr><tr><td style="padding-left:20px" class="textnormal12"><strong><bean:message key='j2eeDS.driverName' bundle='j2eeDS'/></strong></td><td><strong>&nbsp;&nbsp;:&nbsp;</strong></td><td class="textnormal12" align="left">'+document.getElementById("driverName"+j2eeDSId).value+'</td></tr><tr><td style="padding-left:20px" class="textnormal12"><strong><bean:message key='j2eeDS.datasourceName' bundle='j2eeDS'/></strong></td><td><strong>&nbsp;&nbsp;:&nbsp;</strong></td><td class="textnormal12" align="left">'+datasourceName+'</td></tr></table></fieldset>';
    openInfoframe(innerData,'<bean:message key="j2eeDS.propertywindow.datasource" bundle="j2eeDS"/>',200);
    
    }   


        function showSelectedRow(radioButtonId, datasourceId){
            //alert(document.getElementById(radioButtonId).checked = true)
            dojo.byId(radioButtonId).setAttribute('checked', true);
            
            enableBtn(dojo.byId(radioButtonId), datasourceId);
        }

        
</script>
</logic:present>
</html:form>
</body>
