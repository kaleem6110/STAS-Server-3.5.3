<%@ taglib prefix="logic" uri="/tags/struts-logic" %>
<%@ page import="java.util.*" %>
<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>

<%@page import="com.enterprisehorizons.magma.server.util.Cache"%>
<%@page import="com.wfp.security.form.DeviceStatisticsBean"%>
<%@page import="com.enterprisehorizons.magma.server.SessionManager"%>
<%@page import="com.enterprisehorizons.magma.server.Session"%>
<%@page import="com.wfp.utils.RBRegionsUtils"%><h1>Custom - JBoss cache testing with PI data extractor</h1>

 
 <%
 	Map<String, Map<String, DeviceStatisticsBean>> map = Collections.synchronizedMap( RBRegionsUtils.getStaffTackingCache()); 
 	
	 if(map != null){
		for(Map.Entry<String, Map<String, DeviceStatisticsBean>> entry: map.entrySet()){
%>
	Key: <%=entry.getKey() %><br>
	<% Session sessionVar = SessionManager.getInstance().getSession(entry.getKey()); 
		if(sessionVar == null){
			//map.remove(entry.getKey());
			%>
			
		<%
			//map.remove(entry.getKey());
			
		}
	%>
<%			
		} 
	 }
 	
 	
 %>









