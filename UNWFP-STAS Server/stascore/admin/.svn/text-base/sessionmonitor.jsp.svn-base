<%@page import="java.util.HashMap" %>
<%@page import="java.util.Iterator" %>
<%@page import="com.enterprisehorizons.magma.server.Session" %>
<%@page import="com.enterprisehorizons.magma.server.SessionManager" %>
<%@page import="com.enterprisehorizons.magma.server.util.ArtifactUtils" %>
<%@page import="com.enterprisehorizons.magma.designtime.artifact.ArtifactCanvas" %>
<%@page import="com.enterprisehorizons.magma.designtime.artifact.IArtifactLayer" %>
<%@page import="com.spacetimeinsight.security.bean.UserBean" %>

<%

	response.setContentType("text/html");
	String[] activeSessions = SessionManager.getInstance().getSessionIds();
	int noOfSessions = activeSessions == null ? 0 : activeSessions.length;
	out.println("No of Active sessions = ["+noOfSessions+"]</p>");
	ArtifactCanvas canvas = null;
	IArtifactLayer[] layers = null;
	HashMap<String, Integer> layersMap = new HashMap<String,Integer>();
	HashMap<String, Integer> usersMap = new HashMap<String,Integer>();
	String artifactName = null;
	Session session1 = null;
	
	out.println(" *************** Session Details by artifacts start *************</p>");
	Integer index = 0;
	UserBean userBean = null;
	String userName = null;
	Integer userIndex = 0;
	for(int i = 0; i < noOfSessions; i++) {
		artifactName = "";
		userName = "";
		canvas = ArtifactUtils.getCanvas(activeSessions[i]);
		if(canvas != null) {			
			layers = canvas.getLayers();
			if(layers != null && layers.length >= 1) {
				artifactName = layers[0].getName();
				session1 = SessionManager.getInstance().getSession(activeSessions[i]);
				userBean = session1 == null ? null : ((UserBean)session1.get("stiUser"));
				userName = (userBean == null ? "" : userBean.getLoginId());
				out.println("Session Id = ["+activeSessions[i]+"], No of Elements = ["+session1.size()+"], No of layers = ["+layers.length+"], User = ["+userName+"] Artifact Name = ["+artifactName+"], No of Artifacts = ["+layers[0].getNoOfArtifacts()+"]</p>");				
			} 
		}
		index = layersMap.get(artifactName);
		if(index == null) {
			index = new Integer(0);
		}
		index++;
		layersMap.put(artifactName, index);


		userIndex = usersMap.get(userName);
		if(userIndex == null) {
			userIndex = new Integer(0);
		}
		userIndex++;
		usersMap.put(userName, userIndex);
	}
	out.println(" *************** Session Details by artifacts end *************</p>");
	
	out.println(" *************** Session Details grouped by artifacts start *************</p>");
	Iterator<String> iterator = layersMap.keySet().iterator();
	while(iterator.hasNext()) {
		artifactName = iterator.next();
		out.println("Artifact Name = ["+artifactName+"], No of sessions = ["+layersMap.get(artifactName)+"]</p>");
	}
	out.println(" *************** Session Details grouped by artifacts end ************* </p>");

	out.println(" *************** Session Details grouped by users start *************</p>");
	iterator = usersMap.keySet().iterator();
	while(iterator.hasNext()) {
		userName = iterator.next();
		out.println("User Name = ["+userName+"], No of sessions = ["+usersMap.get(userName)+"]</p>");
	}
	out.println(" *************** Session Details grouped by users end ************* </p>");
	/*
	out.println(" *************** Session Details start *************</p>");
	long currentTime = System.currentTimeMillis();
	long lastAccessedTime = 0;
	
	for(int i = 0; i < noOfSessions; i++) {
		session1 = SessionManager.getInstance().getSession(activeSessions[i]);
		lastAccessedTime = session1.getLastAccessedTime().getTime();
		out.println("Session Id = ["+activeSessions[i]+"], last Accessed Time = ["+session1.getLastAccessedTime()+"], In active time (minutes) = ["+((int)((currentTime - lastAccessedTime)/60000))+"]</p>");
	}
	out.println(" *************** Session Details end  ************* ");
	*/

%>