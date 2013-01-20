<%@ page import="com.enterprisehorizons.magma.config.dbadmin.*, 
				 com.enterprisehorizons.magma.config.dbadmin.bd.*, java.io.*,java.util.*,
				 com.spacetimeinsight.primavera.*,
				 com.primavera.integration.client.bo.object.ProjectRisk"%>

<%
	List<ProjectRisk> list = (java.util.List)request.getAttribute("projectRiskList");
	ProjectRisk projectRisk = null;
	for ( int i=0; list != null && i < list.size(); i++ ) {
		projectRisk = (ProjectRisk ) list.get(i);
		out.println(" Risk Name : " + (projectRisk != null ? projectRisk.getName() : "No Risks Found") + "<br>");
	}

%>
