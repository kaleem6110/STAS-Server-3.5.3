<%@page import="com.enterprisehorizons.magma.server.admin.CacheConfigUtils , com.enterprisehorizons.util.StringUtils"%>
<%@page import="com.enterprisehorizons.magma.server.admin.Shp2DBMappingUtils"%>
<%@page import="com.enterprisehorizons.magma.server.util.ServerUtils"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>

<%
        String dbModelName = request.getParameter("cmbDBModels");
        String shapeFileName = request.getParameter("cmbShapeFile");

        String[] dbModelFields = Shp2DBMappingUtils.getModelFields(dbModelName,"mapping");
        String[][] shapeFileAttributes = Shp2DBMappingUtils.getShapeFileAttributes(shapeFileName);
        int noOfShapeAttrs  =  shapeFileAttributes == null ? 0 : shapeFileAttributes.length;

%>

<html>
<head>
<!-- Importing the Styles -->

<jsp:include page="/common/charsetmeta.jsp"/>
<%@ include file="/common/style.jsp"%>
<script type="text/javascript" src="<%=ServerUtils.getContextName(request)%>/js/dojo/dojo.js" djConfig="isDebug: false, parseOnLoad: true"></script>
<script type="text/javascript" src="<%=ServerUtils.getContextName(request)%>/js/dijit/form/_testCommon.js"></script>

<script type="text/javascript">
         dojo.require("dojo.parser");
         dojo.require("dijit.form.Form");
         dojo.require("dijit.form.Button");
         dojo.require("dijit.form.ComboBox");
         dojo.require("dijit.form.FilteringSelect");
         dojo.require("dijit.Dialog");
          var divarray=[];
</script>

<style>
    @import "<%=ServerUtils.getContextName(request)%>/js/dojo/resources/dojo.css";
    @import "<%=ServerUtils.getContextName(request)%>/js/dijit/themes/tundra/tundra.css";
    @import "<%=ServerUtils.getContextName(request)%>/js/dijit/themes/tundra/tundra_rtl.css";
    @import "<%=ServerUtils.getContextName(request)%>/js/dijit/tests/css/dijitTests.css";
    @import "<%=ServerUtils.getContextName(request)%>/js/dojox/form/resources/FileInput.css";
    @import "<%=ServerUtils.getContextName(request)%>/js/dijit/themes/dijit.css";
    @import "<%=ServerUtils.getContextName(request)%>/js/dijit/themes/dijit_rtl.css";
  
        body .txtareamedium {
            width: 25em;
            height: 5em;
        }

</style>


<title><bean:message key="shpDbMap.shpDbMap" bundle="shpDbMap"/> </title>


<script type="text/javascript" src="js/windows.js"></script>

<script type="text/JavaScript">
function showEmptyDialog(data2Display, dialogTitle){
      
        var emptyDlg = new dijit.Dialog({
            title: "<bean:message key='admin.common.dialog.loading' bundle='admin'/>",
            style: "width: 300px;height:125px"
        });
        if(emptyDlg != null) {          
            emptyDlg.attr("title",  dialogTitle);
            emptyDlg.attr("content", "<center><table ><tr><td align=center></tr><tr><td align='center'>"+data2Display+"</td></tr><tr><td align='center'>    <button dojoType=dijit.form.Button type='submit'><bean:message key='admin.common.dialog.ok' bundle='admin'/></button></td></td></tr></table></center>");
            emptyDlg.show();
        }
    }


    function performSubmit(){

        var integerRadius = dijit.byId("txtRadius").value;
        var cmbCollatorValue = dijit.byId("cmbCollator").value;
        var cmbCollateByValue = dijit.byId("cmbCollateBy").value;
        re = /^\d+$/;           
        
        if(cmbCollatorValue != '' && cmbCollatorValue == 'com.enterprisehorizons.magma.server.util.MergingTieLineCollator'){
            
            if(integerRadius =='' || cmbCollateByValue == '') {
                showEmptyDialog("<bean:message key='admin.common.mandatory.alert' bundle='admin'/>","<bean:message key='admin.common.alert.title' bundle='admin'/>");
                return false;
            }

            if(integerRadius!= "" && !re.test(integerRadius)){
                showEmptyDialog("<bean:message key='admin.common.check.numeric' bundle='admin'/>","<bean:message key='admin.common.alert.title' bundle='admin'/>");        
                return false;
            }
        }
      document.getElementById("frmShp2DBMappingAdd").submit();       
    }

    function goToDBHomePage(){
            window.location = "<%=ServerUtils.getContextName(request)%>/adminMain.do";
    }
    function goBack(){
        
            window.location = "<%=ServerUtils.getContextName(request)%>/admin/shp2dbmappingaddselect.jsp";
    }
    function showOrHideCustomText(cmbBox) {
        var name = cmbBox.name;
        var selValue = dijit.byId(name).getDisplayedValue()
        if(selValue != "<bean:message key='shpDbMap.custom.label' bundle='shpDbMap'/>" ) {
            document.getElementById('div'+name).style.visibility = 'hidden';
            document.getElementById('txt'+name).value = '';
            document.getElementById("txt"+name).disabled=true;
        } else {
            document.getElementById('div'+name).style.visibility = 'visible';
            document.getElementById("txt"+name).disabled=false;
        }
    }

    function showOrHideRadiusCollatorDiv(cmbBox) {
        var selValue = dijit.byId('cmbCollator').getDisplayedValue()
        if(selValue != 'Radius'){
            document.getElementById('divRadiusCollator').style.visibility = 'hidden';
            document.getElementById('cmbCollateBy').style.visibility = 'hidden';

            document.getElementById("txtRadius").value ="";
            document.getElementById("txtRadius").disabled=true;

            
            document.getElementById("cmbCollateBy").disabled=true;
            document.getElementById("cmbCollateBy").value ="";

            
        } else {
            document.getElementById('divRadiusCollator').style.visibility = 'visible';
            
            document.getElementById("txtRadius").disabled=false;
            

            document.getElementById("cmbCollateBy").value ="";
            
            document.getElementById("cmbCollateBy").disabled=false;
            dijit.byId("cmbCollateBy").reset();
        }
        
    }
    
</script>

</head>

<body class="tundra bodybg">
<form name="frmShp2DBMappingAdd" id="frmShp2DBMappingAdd" method="post" action="<%=ServerUtils.getContextName(request)%>/shptodb.do">
    <table width="101.2%" cellspacing="0" cellpadding="0" align="center" >
        <tr>
            <td class="pageTitle paddingTitle" > 
                <table border = "0">
                 <tr>
                   <td height="10px" align="left" valign="top"  class="pageTitle paddingTitle" colspan="2" style="padding-left:0px"><strong><bean:message key='shpDbMap.dbmodel.label.addAttribute' bundle='shpDbMap'/></strong></td>
                </tr>
                <tr>
                   <td align="left" colspan="2"><strong class="paddingTitleDesc bodytext" style="padding-left:0px"><bean:message key='shpDbMap.attribute.add' bundle='shpDbMap'/></strong></td>
                </tr>
                 <tr>
                     <td height="27" align="left" >
                 </tr>
                 <tr>
                        <td width="8%" style="padding-top:10px"><label class="label"><bean:message key='shpDbMap.dbmodel.label' bundle='shpDbMap'/></label>
                        </td>
                        <td width="60%" class="bodytext" style="padding-top:8px"><%=dbModelName%>
                        </td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td style="padding-top:10px"><label class="label"><bean:message key='shpDbMap.shapefile.label' bundle='shpDbMap'/></label>
                        </td>
                        <td class="bodytext" style="padding-top:10px"> <%=shapeFileName%>
                        </td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td style="padding-top:10px"><label class="label">[ATTR=<i>VALUE</i>]</label>
                        </td>
                        <td class="bodytext" style="padding-top:24px"> <% for(int i = 0; i < noOfShapeAttrs; i++) {%> [<%=shapeFileAttributes[i][0]%>=<i><%=shapeFileAttributes[i][1]%></i>] &nbsp; <% }%>
                        </td>
                       
                    </tr>
                     <tr>
                            <td height="13">&nbsp;
                            </td>
                     </tr>
                </table>
                 </tr>
                 <tr>
                    <td>
                       <table width="70%" border="0" align="left" cellpadding="2" cellspacing="2" >
                         <tr >
                              <td width="10%" align="right" class="redtitle1" font = "Arial"  >
                               <strong class="bodytext1">  <bean:message key="shpDbMap.modelattrname" bundle="shpDbMap"/>&nbsp;</strong>
                              </td>
                             <td class="redtitle1" width="10%" align="center" font = "Arial" >
                                  <strong class="bodytext1"> <bean:message key="shpDbMap.shapefileattrname" bundle="shpDbMap"/></strong>

                              </td>
                          </tr>
                        
                    <%
                        int noOfModelFields  =  dbModelFields == null ? 0 : dbModelFields.length;
                     
                    String displayField = null;
                        StringBuffer tempStr = new StringBuffer();
                        
                            
                               for(int i = 0; i < noOfModelFields; i++) {
                            
                                displayField = null;
                                tempStr = null;
                                displayField = dbModelFields[i];
                            
                                if(displayField != null){
                                 tempStr =  new StringBuffer();
                                for(int j= 0; j< displayField.length(); j++) {%>


                                    <%if(j == 0)
                                        tempStr.append((""+displayField.charAt(j)).toUpperCase());
                                    else
                                        tempStr.append(""+displayField.charAt(j));
                                }
                                }
                        %>             
                         <tr>
                           <td width="40%" align="right"> 
                             <label class="label"><%=tempStr.toString()%> :&nbsp;
                             </label>
                            </td>
                        
                            <td width="22%" class="bodytext" style="padding-top:10px">
                            <select id="<%=dbModelFields[i]%>"  name="<%=dbModelFields[i]%>" onChange="return showOrHideCustomText(this);" dojoType="dijit.form.FilteringSelect">
                                <option value=""><bean:message key="validation.msg.select" bundle="splchvalidation"/></option>
                                <option value="custom"><bean:message key="shpDbMap.custom.label" bundle="shpDbMap"/></option>
                        <%
                            for(int j = 0; j < noOfShapeAttrs; j++) {
                        %>  
                                    <option value="<%=shapeFileAttributes[j][0]+"_"+j%>"><%=shapeFileAttributes[j][0]%></option>
                        <%
                            }
                        %>   
                            <script>
                                var value = "<%=dbModelFields[i]%>";

                               
                             divarray.push({divlist: value});
                            
                            dojo.byId('<%=dbModelFields[i]%>').Value='';
                                </script>
                            </select>
                             </td>
                             <td class="bodytext" style="padding-top:10px"><div id="div<%=dbModelFields[i]%>" style="visibility:hidden;"><input type="text" dojoType="dijit.form.ValidationTextBox"  disabled name="txt<%=dbModelFields[i]%>" MAXLENGTH=100></div>
                             </td>           
                         </tr>
                    <%
                        }
                    %>          
                         <tr>
                            <td align="right"><label class="label"> <bean:message key="shpDbMap.collator.label" bundle="shpDbMap"/>:&nbsp;</label>
                            </td>
                        
                             <td class="bodytext" style="padding-top:10px"><select id = "cmbCollator"  name="cmbCollator" onChange="return showOrHideRadiusCollatorDiv(this);" dojoType="dijit.form.FilteringSelect">
                                <option value=""><bean:message key="validation.msg.select" bundle="splchvalidation"/></option>
                                <option value="com.enterprisehorizons.conversion.shp.custom.TieLineStringCollater"><bean:message key="shpDbMap.point.label" bundle="shpDbMap"/></option>
                                <option value="com.enterprisehorizons.magma.server.util.MergingTieLineCollator"><bean:message key="shpDbMap.radius.label" bundle="shpDbMap"/></option>
                            </select>
                             </td>
                             <td >&nbsp;
                             </td>  
                           </tr>
                        </table>
                      </td>
                   </tr>

                    <tr>                    
                        <td colspan="4">
                          <table  width="100%" >
                             <tr>
                                 <td>
                                    <div id="divRadiusCollator" style="visibility:hidden;" align="center">
                                      <table border="0" width="70%">
                                          <tr>
                                              <td width="18%" align="right">
                                                 <label class="label"> <bean:message key="shpDbMap.radius.label" bundle="shpDbMap"/> <font color="red" size="1">*</font>: </label>
                                               </td>
                                              
                                               <td class="bodytext"  width="20%"  valign="middle"  style="padding-top:10px">
                                                  <input type="text" dojoType="dijit.form.ValidationTextBox" name="txtRadius" id="txtRadius" MAXLENGTH=100 disabled>
                                              </td>
                                              <td width="10%" align="right"  valign="middle">
                                                 <label class="label" ><bean:message key="shpDbMap.collateby.label" bundle="shpDbMap"/> <font color="red" size="1">*</font>: </label></td>
                                             <td style="padding-top:10px"  valign="middle">
                                                   <select id="cmbCollateBy" name="cmbCollateBy" dojoType="dijit.form.FilteringSelect">
                                                         <option value=""><bean:message key="validation.msg.select" bundle="splchvalidation"/>
                                                         </option>
                                <%
                                    for(int j = 0; j < noOfShapeAttrs; j++) {
                                %>  
                                                         <option value="<%=j%>"><%=shapeFileAttributes[j][0]%>
                                                         </option>
                                <%
                                    }
                                %>          
                                                     </select>
                                              </td>
                                           </tr>
                                      </table>
                                     </div>
                                  </td>
                               </tr>
                           </table>
                       </td>
                   </tr>
        
                 <td>&nbsp;
                  
                 </td>
           </tr>
          <tr>
             <td>&nbsp;</td>
            </tr>
          <tr  height="30" colspan="2" class="barColor">
              <td height="30" colspan="2" class="barColor" align="center">
                 <button dojoType="dijit.form.Button"  type="button" onClick="window.location ='<%=ServerUtils.getContextName(request)%>/adminMain.do'"><bean:message key="admin.common.home" bundle="admin"/> </button>
                  <button dojoType="dijit.form.Button"  type="button" onClick="goBack();"><bean:message key="admin.common.back" bundle="admin"/> 
                  </button>
                 <button dojoType="dijit.form.Button"  id="idSubmit" name="btnSubmit" type="button" onClick="return performSubmit();"> <bean:message key="admin.common.save" bundle="admin"/>  </button>
                   <button dojoType="dijit.form.Button"  id="idSReset" name="btnReset" type="reset" onClick="performReset();"/> <bean:message key="admin.common.reset" bundle="admin"/>    </button>
                 </td>
       </tr>

</table>
                
    <input type="hidden" name="dbModelName" value="<%=dbModelName%>"/>
    <input type="hidden" name="shapeFileName" value="<%=shapeFileName%>"/>
    <input type="hidden" name="operation" value="add"/>

</form>
</body>
</html>
<script type="text/JavaScript">
    try{
     frm.document[0].idSubmit.disabled = true;
     frm.document[0].idSReset.disabled = true;
    }catch(er){}

    function performReset(){
        for(i=0;i<divarray.length;i++){
            if(i == 0){
                dijit.byId(divarray[i].divlist).focus();
            }
            dijit.byId(divarray[i].divlist).reset();
                    }
    dijit.byId("cmbCollator").setValue('');
        document.getElementById('cmbCollateBy').style.visibility = 'hidden';
            document.getElementById("cmbCollateBy").disabled=true;
            document.getElementById("cmbCollateBy").value ="";
    //frmShp2DBMappingAdd.reset();
        }
dojo.addOnLoad(selVal);
function selVal(){
    dijit.byId('cmbCollator').setValue('');
        document.getElementById('cmbCollateBy').style.visibility = 'hidden';
            document.getElementById("cmbCollateBy").disabled=true;
            document.getElementById("cmbCollateBy").value ="";
}


        </script>