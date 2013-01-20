<%@page import="javax.servlet.http.HttpSession"%>
<%@ taglib prefix="logic" uri="/tags/struts-logic" %>
<%
    HttpSession session = request.getSession(false); 
    if(session != null) {
    	session.invalidate();
    }
%>
<jsp:forward page="mobile/logout.do"/>