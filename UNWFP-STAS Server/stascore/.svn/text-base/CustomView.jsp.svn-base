<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@page import="com.enterprisehorizons.magma.server.util.ServerUtils"%>
<%@page import="com.enterprisehorizons.magma.server.util.CommonUtils"%>
<%@page import="com.enterprisehorizons.magma.server.admin.AdminConfigUtils"%>


<%
    String apptheme = AdminConfigUtils.getApplicationTheme();
    if (apptheme == null || apptheme.equals("null")) {
        apptheme = "default";
    }
%>

<head>
    <title>Space . Time . Insight</title>
    <%@ include file="/common/style.jsp"%>
    
</head>
<script>
    function hover(obj){
        obj.className='class_hover';
    }

    function unHover(obj, row_num){      
            obj.className = 'class_no_hover_job';

    }
</script>
<body class="bodybg bodyText">
<%
	Map<String, String[]> requestMap = request.getParameterMap();
%>

<h3><%=requestMap.get("layername")[0]%></h3>
<br/>
<br/>


<table width="100%" cellspacing="0" cellpadding="0" align="center"  border="0" > 
		<tr class="tableBgColor" >
				<th colspan="3" align="left">Ecosystem particular details.</th>				
		</tr>	
		<tr class="tableBgColor" >
			<th colspan="3" align="left" height="13px"></th>				
		</tr>
	
<% 
if(requestMap != null){
	Iterator it = requestMap.entrySet().iterator();
	
	while(it.hasNext()){
		Map.Entry<String, String[]> pairs = (Map.Entry)it.next();

		if(!"layername".equalsIgnoreCase(pairs.getKey())){
		%>
			<tr  >
				<th ><%=pairs.getKey() %></th>
				<th width="15px">&nbsp;</th>
				<td ><%=pairs.getValue()[0] %></td>
			</tr>
			<tr><td height="15px"></td></tr>
		<%
		}
	}
}
	
%>
	
	
</table>

</body>