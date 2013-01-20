<%
	String id = request.getParameter("id");
	Object[][][] data = com.enterprisehorizons.excel.ExcelUtils.readAllContents(com.enterprisehorizons.magma.server.admin.AdminConfigUtils.getUploadDirectory()+"ServicePersonnel.xls",0);
	Object[][] personelData = data[0];
	if(id==null) id="0";
	Double idAsDouble = new Double(id);
	
%>
<%
	for(int i=0; i<personelData.length; i++){
	  if(personelData[i]==null) continue;
	  if(idAsDouble.equals(personelData[i][0])){
%>	  
	    <employee>
		<employeeid><%=personelData[i][0]%></employeeid>
		<longitude><%=personelData[i][1]%></longitude>
		<latitude><%=personelData[i][2]%></latitude>
		<name><%=personelData[i][3]%> <%=personelData[i][6]%></name>
		<costcenter><%=personelData[i][4]%></costcenter>
		<manager><%=personelData[i][5]%></manager>
		<phone><%=personelData[i][7]%></phone>
		<email><%=personelData[i][8]%></email>
		<status><%=personelData[i][9]%></status>
	    </employee>
<%
	  }
	}

%>