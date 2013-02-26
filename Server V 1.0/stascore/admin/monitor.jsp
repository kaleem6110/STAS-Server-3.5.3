<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ include file="/common/dojo.jsp"%>
<html>
<head>

<style>
    @import "<%=ServerUtils.getContextName(request)%>/js/dojo/resources/dojo.css";
    @import "<%=ServerUtils.getContextName(request)%>/js/dijit/themes/tundra/tundra.css";
    @import "<%=ServerUtils.getContextName(request)%>/js/dijit/themes/tundra/tundra_rtl.css";   
</style>
<jsp:include page="/common/charsetmeta.jsp"/>  
</head>
<body class="tundra bodybg" >
<table width="100%" cellspacing="0" cellpadding="0">
    <tr>
        <td  class="pageTitle paddingTitle">      
            <bean:message key="admin.monitor" bundle="admin"/>
        </td>
    </tr>
    <tr>
        <td class="paddingTitleDesc bodytext">
            <strong><bean:message key="admin.monitor.description" bundle="admin" /></strong>
        </td>     
    </tr>
    <tr>
        <td style="padding-left:48px;padding-top:30px;">
            <table cellpadding="0" cellspacing="19px" border="0">
                <tr>
                    <td>
                        <div dojoType="dijit.layout.ContentPane" region="center" class="thumbnail-itemOut"  onclick="window.location =('<%=ServerUtils.getContextName(request)%>/monitorCacheAction.do?operation=return')">
                            <div class="thumbnail-itemIn"  >                      
                                <img src="<%=ServerUtils.getContextName(request)%>/images/portal/icon_cachestatistics.png" />
                            </div>
                            <bean:message key="admin.monitor.cache" bundle="admin" />
                        </div>
                    </td>
                    <td>
                        <div dojoType="dijit.layout.ContentPane" region="center"  class="thumbnail-itemOut"  onclick="window.location =('<%=ServerUtils.getContextName(request)%>/monitorHttpSessionAction.do?operation=return&tabId=session')">
                            <div  class="thumbnail-itemIn"  >
                                <img src="<%=ServerUtils.getContextName(request)%>/images/portal/icon_sessionstatistics.png" />
                            </div>
                            <bean:message key="admin.monitor.session" bundle="admin" />
                        </div>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>
<!-- Button Panel -->
  <table width="101.2%" border=0 >
    <tr>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td>&nbsp;</td>
    </tr>
    <tr  align="right" class="barColor">
      <td align="center" width="100%"><button dojoType="dijit.form.Button"  type="button"  onclick="window.location =  '<%=ServerUtils.getContextName(request)%>/adminMain.do'  ">
        <bean:message key="admin.monitor.home.button" bundle="admin" />
        </button>
        </td>
    </tr>
   </table>
</body>
</html>