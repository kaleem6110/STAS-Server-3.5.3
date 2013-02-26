<%@page import="com.enterprisehorizons.dbutils.ConnectionPoolStatistics"%>
<%@page import="com.enterprisehorizons.dbutils.ConnectionPoolUtils,com.enterprisehorizons.magma.server.util.ServerUtils"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
  <title> Connection Pool Monitor</title>
  <meta http-equiv="refresh" content="1" > 
  <%@ include file="/common/style.jsp" %> 

 </head>

<body class="tundra bodybg">
<%
	ConnectionPoolStatistics[] poolStatistics = ConnectionPoolUtils.getConnectionPoolStatistics();
	int count = poolStatistics == null ? 0 : poolStatistics.length;
%>
<center><h2>Connection Pool Statistics</h2></center>
<p>
<table width="100%">
<tr align = "left" class="barColor">
	<td class="pageTitle">Server Name</td>
	<td class="pageTitle">Server IP</td>
	
</tr>
<tr>
	<td><%=ServerUtils.getSystemServerName()%></td>
	<td><%=ServerUtils.getSystemServerIP()%></td>
	
</tr>
</table>
<table width="100%">
<tr align = "left" class="barColor">
	<td class="pageTitle">Name</td>
	<td class="pageTitle">Type</td>
	<td class="pageTitle">Active #</td>
	<td class="pageTitle">Available #</td>
	<td class="pageTitle">Max #</td>
	<td class="pageTitle">Offline #</td>
	<td class="pageTitle">Served #</td>
	<td class="pageTitle">Refused #</td>
	<td class="pageTitle">Start Date</td>
</tr>
<%
	for(int i = 0; i < count; i++) {
%>
<tr>
	<td><%=poolStatistics[i].getPoolName()%></td>
	<td><%=poolStatistics[i].getPoolType().toString()%></td>
	<td><%=poolStatistics[i].getActiveCount() == -1 ? "NA" : poolStatistics[i].getActiveCount()%></td>
	<td><%=poolStatistics[i].getAvailableCount() == -1 ? "NA" : poolStatistics[i].getAvailableCount()%></td>
	<td><%=poolStatistics[i].getMaxCount() == -1 ? "NA" : poolStatistics[i].getMaxCount()%></td>
	<td><%=poolStatistics[i].getOfflineCount() == -1 ? "NA" : poolStatistics[i].getOfflineCount()%></td>
	<td><%=poolStatistics[i].getServedCount() == -1 ? "NA" : poolStatistics[i].getServedCount()%></td>
	<td><%=poolStatistics[i].getRefusedCount() == -1 ? "NA" : poolStatistics[i].getRefusedCount()%></td>
	<td><%=poolStatistics[i].getServedDate() == null ? "NA" : poolStatistics[i].getServedDate()%></td>
</tr>
<%
	}
%>
</table>
 </body>
</html>

