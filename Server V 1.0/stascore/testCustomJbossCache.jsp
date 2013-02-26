<%@ taglib prefix="logic" uri="/tags/struts-logic" %>
<%@ page import="java.util.*" %>
<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>


<%@page import="com.enterprisehorizons.magma.ecoweb.action.TestCustomJBossCacheAction"%>
<%@page import="com.enterprisehorizons.magma.server.util.Cache"%><h1>Custom - JBoss cache testing with PI data extractor</h1>

 
 <%
 	Map map = (HashMap) Cache.retrieve("cacheKey"); 
 	
 	out.println(map.values());
 	
 %>









